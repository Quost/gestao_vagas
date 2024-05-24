FROM ubuntu:latest AS build

RUN apt-get update

# Install dependencies
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y

# Copy the source code
COPY . .

# Build the project
RUN mvn clean install

# Run the project
FROM openjdk:17-jdk-slim
EXPOSE 8080

COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

