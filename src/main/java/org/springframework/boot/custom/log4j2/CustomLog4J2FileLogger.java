package org.springframework.boot.custom.log4j2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.logging.LogFile;
import org.springframework.boot.logging.LoggingInitializationContext;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;

/**
 * Simple Customizaiton of Log4j2 Logging System using custom Log4j2 XMLs 
 * 
 * @author anumalasri
 * 
 */
public class CustomLog4J2FileLogger extends Log4J2LoggingSystem {

	public CustomLog4J2FileLogger(ClassLoader classLoader) {
		super(classLoader); 
	}
	
	@Override
	protected void loadDefaults(LoggingInitializationContext initializationContext, LogFile logFile) {
		System.out.println("Custom Log4J2 Applied");
		if (logFile != null) {
			loadConfiguration(getPackagedConfigFile("log4j2-custom-file.xml"), logFile);
		}
		else {
			loadConfiguration(getPackagedConfigFile("log4j2-custom.xml"), logFile);
		}
	}
	
	@Override
	protected String[] getStandardConfigLocations() {
		List<String> supportedConfigLocations = new ArrayList<String>();

		supportedConfigLocations.add("log4j2-custom.xml");
		return supportedConfigLocations.toArray(new String[supportedConfigLocations.size()]);
	}
}
