#DockerFile
FROM openjdk:17-jdk-slim
LABEL authors="anokhin"
WORKDIR /application
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]