FROM eclipse-temurin:17-jdk-alpine

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copie du JAR buildé (à construire avant avec mvn package)
COPY target/config-server-0.0.1-SNAPSHOT.jar app.jar

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]