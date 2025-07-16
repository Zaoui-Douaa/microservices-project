FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/microservices-project-1.0.0.jar microservices-project.jar
ENTRYPOINT ["java", "-jar", "/microservices-project.jar"]

