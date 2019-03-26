# Custom Log4j2 LoggingSystem in Spring Boot

Quick effort to customize Spring Boot Log4J2 Logging System and implement custom configuration along with Spring Boot features. 

Two different implementations are described here: 

* Use Custom Log4J2 Configuration XMLs 
    
* Create Custom Log4J2 Configuration Programatically 
 

## Instructions: 

### Build 
The project can be built by installing Gradle 5.X  and running 
```
gradle clean build 
```
### Execute Program
As per Spring Boot documentation, https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-custom-log-configuration, we have to point SpringBoot Application to custom LoggingSystem using system property and rest will be taken by custom code. 

```
-Dorg.springframework.boot.logging.LoggingSystem=<Custom Logger Class name>
```

For Example: 
To use Custom File configuration, we have to execute the following java command: 
```
java -Dorg.springframework.boot.logging.LoggingSystem=org.springframework.boot.custom.log4j2.CustomLog4J2FileLogger -jar build/libs/spring-boot-custom-log4j2.jar
```
