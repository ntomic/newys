# NEWYS APP

##Prerequisites
- Java 11+
- gradle 5+

## Running the application
1. Localhost:   
- gradle bootRun    
- standard SpringBoot application      
2. Standalone Jar:    
- java -jar newys-1.0.0.jar   
  (location: /resources/jar/)

##App Links
http://localhost:8080/index  
(Sign in with credentials : username= "user", password = "pass")   
http://localhost:8080/articles   

##Useful
health check-up: http://localhost:8080/management/health   
swagger: http://localhost:8080/swagger   
database: http://localhost:8080/h2   
(jdbc url: jdbc:h2:file:./src/main/resources/testDB/testDB; username= "user"; password = "pass")   



