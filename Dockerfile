# Build the application
FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

# Running the application
FROM openjdk:21-jdk-slim
LABEL authors="Mr Lii"

# Add a new user
RUN groupadd -r testerLii && useradd -r -g testerLii testerLii

WORKDIR /app

COPY --from=build /app/target/cloudnova-task-management-api-*.jar app.jar

# Change file ownership
RUN chown -R testerLii:testerLii /app
USER testerLii


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
