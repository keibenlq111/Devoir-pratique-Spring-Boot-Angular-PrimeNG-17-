# Datasets & Modèles ML — Backend (Spring Boot)

API REST pour la gestion de datasets, modèles de Machine Learning et expérimentations,
développée dans le cadre du devoir pratique *Spring Boot & Angular (PrimeNG 17)* du
Master Intelligence Artificielle.

## Stack technique

- Java 21
- Spring Boot 4.1.0 (Spring Web MVC, Spring Data JPA, Spring Validation)
- PostgreSQL
- springdoc-openapi (Swagger UI)
- Lombok
- Maven

## Prérequis

- JDK 21
- Maven (ou utiliser le wrapper `mvnw` fourni, aucune installation requise)
- PostgreSQL 14+ installé et lancé localement

## Installation

### 1. Créer la base de données

```bash
psql -U postgres -h localhost -c "CREATE DATABASE datasets_ml;"
```

### 2. Configurer les identifiants

Dans [`src/main/resources/application.properties`](src/main/resources/application.properties),
adapter si besoin :

```properties
spring.datasource.username=postgres
spring.datasource.password=<votre mot de passe>
```

Le schéma des tables est créé automatiquement au démarrage (`spring.jpa.hibernate.ddl-auto=update`) :
aucun script SQL à exécuter manuellement.

## Lancement

```bash
./mvnw spring-boot:run
```

L'API démarre sur **http://localhost:8080**.

> Si `spring-boot:run` (avec DevTools) pose problème dans votre environnement, packagez et lancez le jar directement :
> ```bash
> ./mvnw -DskipTests package
> java -jar target/datasetsMachineLearning-0.0.1-SNAPSHOT.jar
> ```

## Documentation de l'API

Une fois l'application démarrée :

- Swagger UI : http://localhost:8080/swagger-ui.html
- Spécification OpenAPI (JSON) : http://localhost:8080/v3/api-docs

## Endpoints principaux

| Méthode | URL                        | Description                    |
|---------|----------------------------|---------------------------------|
| GET     | `/api/datasets`            | Liste des datasets              |
| GET     | `/api/datasets/{id}`       | Détail d'un dataset              |
| POST    | `/api/datasets`            | Créer un dataset                 |
| PUT     | `/api/datasets/{id}`       | Modifier un dataset              |
| DELETE  | `/api/datasets/{id}`       | Supprimer un dataset             |
| GET/POST/PUT/DELETE | `/api/modeles`, `/api/modeles/{id}` | Idem pour les modèles ML |
| GET/POST/PUT/DELETE | `/api/experimentations`, `/api/experimentations/{id}` | Idem pour les expérimentations |

Toutes les réponses d'erreur suivent le format :

```json
{
  "code": 404,
  "message": "Dataset introuvable avec l'id 999",
  "timestamp": "2026-08-12T23:47:20.013"
}
```

## Structure du projet

```
src/main/java/.../datasetsmachinelearning/
├── entity/       # Entités JPA (Dataset, ModeleML, Experimentation)
├── dto/          # Objets exposés par l'API REST
├── repository/   # Interfaces Spring Data JPA
├── service/      # Interfaces + implémentations métier
├── controller/   # Endpoints REST
├── exception/    # Exceptions custom + gestion centralisée des erreurs
└── config/       # Configuration CORS
```

## Frontend associé

Le client Angular se trouve dans le dossier voisin `Angulardataset/` (voir son propre README).
Le backend autorise par défaut les requêtes CORS depuis `http://localhost:4200`.
