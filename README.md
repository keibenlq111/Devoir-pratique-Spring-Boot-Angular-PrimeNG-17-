# Devoir pratique — Spring Boot & Angular (PrimeNG 17)

Plateforme de gestion de datasets et de modèles de Machine Learning, développée dans le
cadre du devoir pratique du Master Intelligence Artificielle : une API REST avec
**Spring Boot** et une interface **Angular 17 + PrimeNG 17** consommant cette API.

Le dépôt contient deux projets indépendants :

| Dossier | Rôle | Stack |
|---|---|---|
| [`datasetsMachineLearning/`](./datasetsMachineLearning) | Backend — API REST | Spring Boot 4, Spring Data JPA, PostgreSQL, Swagger |
| [`Angulardataset/`](./Angulardataset) | Frontend — Interface web | Angular 17, PrimeNG 17 |

## Aperçu du modèle de données

Trois entités liées :

| Entité | Attributs principaux | Relations |
|---|---|---|
| **Dataset** | nom, description, source, nombreobservations, format, dateAjout | 1 Dataset → N Expérimentations |
| **ModeleML** | nom, type, algorithme, version, dateCreation | 1 ModeleML → N Expérimentations |
| **Experimentation** | accuracy, f1Score, dureeEntrainement, dateExecution | N Expérimentations → 1 Dataset + 1 ModeleML |

## Démarrage rapide

### 1. Backend

```bash
cd datasetsMachineLearning
psql -U postgres -h localhost -c "CREATE DATABASE datasets_ml;"
# adapter spring.datasource.username / password dans application.properties si besoin
./mvnw spring-boot:run
```

API disponible sur `http://localhost:8080` — documentation interactive : `http://localhost:8080/swagger-ui.html`

### 2. Frontend

```bash
cd Angulardataset
npm install
npm start
```

Interface disponible sur `http://localhost:4200` (le backend doit tourner sur le port 8080).

> Instructions détaillées, prérequis et structure de chaque projet : voir le README de
> [`datasetsMachineLearning/`](./datasetsMachineLearning/README.md) et de
> [`Angulardataset/`](./Angulardataset/README.md).

## Endpoints de l'API

Chaque entité expose le même jeu d'endpoints REST, sous `/api/datasets`, `/api/modeles`
et `/api/experimentations` :

| Méthode | URL | Description |
|---|---|---|
| GET | `/api/{ressource}` | Liste de toutes les ressources |
| GET | `/api/{ressource}/{id}` | Détail d'une ressource |
| POST | `/api/{ressource}` | Création (`201 Created`) |
| PUT | `/api/{ressource}/{id}` | Modification |
| DELETE | `/api/{ressource}/{id}` | Suppression (`204 No Content`) |

Les erreurs suivent un format structuré commun :

```json
{
  "code": 404,
  "message": "Dataset introuvable avec l'id 999",
  "timestamp": "2026-08-12T23:47:20.013"
}
```

## Fonctionnalités de l'interface

Chaque vue Angular (Datasets, Modèles ML, Expérimentations) propose :

- Un tableau (`p-table`) avec pagination, tri par colonne et recherche globale
- Un formulaire réactif dans une boîte de dialogue (`p-dialog`) pour créer/modifier, avec validation côté client
- Une confirmation avant suppression (`p-confirmDialog`)
- Des notifications de succès/erreur (`p-toast`)

## Structure du dépôt

```
.
├── datasetsMachineLearning/   # Backend Spring Boot
│   ├── src/main/java/.../entity/       # Entités JPA
│   ├── src/main/java/.../dto/          # Objets exposés par l'API
│   ├── src/main/java/.../repository/   # Interfaces Spring Data JPA
│   ├── src/main/java/.../service/      # Logique métier
│   ├── src/main/java/.../controller/   # Endpoints REST
│   ├── src/main/java/.../exception/    # Gestion centralisée des erreurs
│   └── README.md
└── Angulardataset/             # Frontend Angular
    ├── src/app/models/         # Interfaces TypeScript
    ├── src/app/services/       # Services HttpClient
    ├── src/app/pages/          # Vues (Datasets, Modèles ML, Expérimentations)
    └── README.md
```

## Documentation complémentaire

- [Rapport de projet](./Rapport_et_documentation.pptx) — modèle de données, choix d'architecture, difficultés rencontrées
