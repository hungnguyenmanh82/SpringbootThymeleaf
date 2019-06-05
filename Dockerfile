FROM openjdk:8-jre-alpine

#working dir o "/" folder tren container
# app.jar o "/" folder tren container
COPY target/SpringBootThymeleaf-0.0.1-SNAPSHOT.jar app.jar

# context-path = "/"
ENTRYPOINT ["java","-jar","app.jar"]

# context-path = "/proxy2"
# ENTRYPOINT ["java","-jar","app.jar","--server.servlet.context-path=/proxy2"]

EXPOSE 8080