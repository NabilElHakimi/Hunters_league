#FROM openjdk:17-jdk-slim
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 7000
#ENTRYPOINT ["java", "-jar", "/app.jar"]


FROM maven:3.8.7-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package sonar:sonar \
    -Dsonar.projectKey=Hunters-league \
    -Dsonar.host.url=http://host.docker.internal:9000 \
    -Dsonar.login=sqp_413cbcf4049a324d5a8814a6a893391de6b3d486

FROM eclipse-temurin:17-jre
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8443
ENTRYPOINT ["java", "-jar", "app.jar"]

