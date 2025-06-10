# Utiliser une image de base avec JDK 22
FROM openjdk:22-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de votre application
COPY target/*.jar todolist-backend-0.0.1-SNAPSHOT.jar

# Installer le client PostgreSQL
RUN apt-get update && apt-get install -y postgresql-client

# Définir les variables d'environnement
ENV POSTGRES_PASSWORD=Michel2015 \
   POSTGRES_HOST=172.17.0.2 \
    POSTGRES_PORT=5432 \
    POSTGRES_NAME=todolist \
    POSTGRES_USER=postgres

# Exposer le port de l'application (si nécessaire)
EXPOSE 3020

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "todolist-backend-0.0.1-SNAPSHOT.jar"]
