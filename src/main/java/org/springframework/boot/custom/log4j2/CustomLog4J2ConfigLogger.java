package org.springframework.boot.custom.log4j2;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.springframework.boot.logging.LogFile;
import org.springframework.boot.logging.LoggingInitializationContext;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.boot.logging.log4j2.SpringBootConfigurationFactory;
import org.springframework.core.env.Environment;

public class CustomLog4J2ConfigLogger extends Log4J2LoggingSystem {

	public CustomLog4J2ConfigLogger(ClassLoader classLoader) {
		super(classLoader);
	}

	@Override
	public void initialize(LoggingInitializationContext initializationContext, String configLocation, LogFile logFile) {

		super.initialize(initializationContext, null, logFile);
	}
	
	@Override
	protected String[] getStandardConfigLocations() {
		List<String> supportedConfigLocations = new ArrayList<String>();

		supportedConfigLocations.add("log4j2-custom.xml");
		return supportedConfigLocations.toArray(new String[supportedConfigLocations.size()]);
	}

	private LoggerContext getLoggerContext() {
		return (LoggerContext) LogManager.getContext(false);
	}
	
	@Override
	protected void loadDefaults(LoggingInitializationContext initializationContext, LogFile logFile) {
		Environment env = initializationContext.getEnvironment();
		 
		ConfigurationBuilder<BuiltConfiguration> builder = SpringBootConfigurationFactory.newConfigurationBuilder();
 		 
		builder.setConfigurationName("Custom-Log4j2");
	
        builder.setStatusLevel(Level.ERROR);
        
        AppenderComponentBuilder appenderBuilder = builder.newAppender("Stdout", "CONSOLE").
            addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(builder.newLayout("PatternLayout").
            addAttribute("pattern", "%d [ConfigLogger] [%t] %-5level: %msg%n%throwable"));
        appenderBuilder.add(builder.newFilter("MarkerFilter", Filter.Result.DENY,
            Filter.Result.NEUTRAL).addAttribute("marker", "FLOW"));
        builder.add(appenderBuilder);
        
        //Add Custom loggers
        builder.add(builder.newLogger("org.apache.logging.log4j", Level.DEBUG).
            add(builder.newAppenderRef("Stdout")).
            addAttribute("additivity", false));
        
        //Add Root Logger Configuration
        String rootLogLevel = env.getProperty("logging.level.root", "INFO");
        builder.add(builder.newRootLogger( Level.valueOf(rootLogLevel)).add(builder.newAppenderRef("Stdout")));
        
        if (logFile != null) {
        	//Add File Appender
        }
               
        Configuration config = builder.build();
		 
		LoggerContext ctx = getLoggerContext();
		Configurator.initialize(config);
			 
		ctx.start(config);
	}
	
}
