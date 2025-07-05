FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
ADD target/gateway-service-1.0.0.jar app.jar
EXPOSE 8888
CMD ["java", "-jar", "app.jar"]
