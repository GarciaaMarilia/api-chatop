# ChaTop API

Une API REST développée avec **Spring Boot 3.4.5** pour gérer l’authentification, les utilisateurs et les données, avec prise en charge de **OAuth2**, **JWT**, **validation**, **documentation OpenAPI** et connexion à une base de données **MySQL**.

## 📌 Technologies et dépendances

- Java 17
- Spring Boot
    - spring-boot-starter-web
    - spring-boot-starter-data-jpa
    - spring-boot-starter-security
    - spring-boot-starter-validation
    - spring-boot-starter-oauth2-client
- MySQL Connector (8.0.33)
- JWT (jjwt-api, jjwt-impl, jjwt-jackson)
- OpenAPI avec springdoc-openapi (2.1.0)
- Tests avec spring-boot-starter-test

## ⚙️ Configuration

### Prérequis

- Java 17 ou supérieur
- Maven
- MySQL installé et en cours d'exécution
- Un IDE comme IntelliJ, Eclipse ou VS Code

### Base de données

Créer une base de données MySQL :

```sql
CREATE DATABASE chatop_db;
```
Configurer la connexion dans le fichier application.properties ou application.yml :

```java
spring.datasource.url=jdbc:mysql://localhost:3306/chatop_db
spring.datasource.username=root
spring.datasource.password=ton_mot_de_passe
spring.jpa.hibernate.ddl-auto=update
```

### Lancer le projet

```bash
mvn spring-boot:run
```

## 🔐 Sécurité

Le projet utilise :

Spring Security

JWT pour l’authentification

## 📄 Documentation

Une documentation interactive est générée automatiquement :

```bash
http://localhost:8080/swagger-ui.html
```