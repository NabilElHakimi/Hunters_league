FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "/app.jar"]
