# eureka-server/Dockerfile

# Image de base avec JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copie du JAR généré par Maven
COPY target/eureka-server-1.0.0.jar app.jar

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]
