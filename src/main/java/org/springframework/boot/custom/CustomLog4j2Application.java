package org.springframework.boot.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomLog4j2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CustomLog4j2Application.class, args); 
	}
	
	 //access command line arguments
    @Override
    public void run(String... args) throws Exception {
	
        Logger logger = LoggerFactory.getLogger(getClass());
        
		logger.debug("This is Debug Level Message");
		logger.info("This is Info Level Message");
		logger.warn("This is Warn Level Message");
		logger.error("This is Error Level Message");
		logger.error("This is Error Level Message with Exception", new Exception("Exception message"));
		logger.trace("This is Trace Level Message");
		 
		
    }
}
