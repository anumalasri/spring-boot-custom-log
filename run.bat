gradle clean build 
java -Dorg.springframework.boot.logging.LoggingSystem=org.springframework.boot.custom.log4j2.CustomLog4J2ConfigLogger -jar target/libs/spring-boot-custom-log4j2.jar