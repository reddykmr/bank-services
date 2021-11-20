# Start with a base image containing Java runtime
FROM openjdk:8

# Make port 8080 available to the world outside this container
EXPOSE 8086

ADD target/bank-services.jar bank-services.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","bank-services.jar"]