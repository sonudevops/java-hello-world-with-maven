# Use a base image with Java installed
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Java application JAR file into the container
COPY HelloWorld.jar /app/HelloWorld.jar

# Specify the command to run when the container starts
CMD ["java", "-jar", "HelloWorld.jar"]
