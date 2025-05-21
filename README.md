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

### Cloner le projet

```bash
git clone https://github.com/GarciaaMarilia/api-chatop.git
cd api-chatop
```

### Base de données

#### ✅ Cas 1 : Vous n’avez pas encore MySQL installé

#### Ubuntu/Debian:
```bash
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
```

#### macOS
```bash
brew install mysql
brew services start mysql
```

#### Windows

1. Téléchargez l’installateur MySQL depuis :
https://dev.mysql.com/downloads/installer/

2. Suivez les instructions d’installation (vous pouvez garder les options par défaut).

3. Créez un mot de passe pour l’utilisateur root lorsque demandé.

#### Vérifiez que le serveur fonctionne :
```bash
mysql -u root -p
```

#### ✅ Cas 2 : Vous avez déjà MySQL installé

#### Se connecter à MySQL :
```bash
mysql -u user -p
```

#### Créer la base de données :
```bash
CREATE DATABASE chatop_db;
```

#### Créer un utilisateur dédié au projet (recommandé) :
```bash
CREATE USER 'chatop_user'@'localhost' IDENTIFIED BY 'mot_de_passe_sécurisé';
GRANT ALL PRIVILEGES ON chatop_db.* TO 'chatop_user'@'localhost';
FLUSH PRIVILEGES;
```

Configurer la connexion dans le fichier application.properties :

```java
spring.datasource.url=jdbc:mysql://localhost:3306/chatop_db
spring.datasource.username=chatop_user
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

Une documentation interactive est générée automatiquement avec SWAGGER :

```bash
http://localhost:8080/swagger-ui.html
```