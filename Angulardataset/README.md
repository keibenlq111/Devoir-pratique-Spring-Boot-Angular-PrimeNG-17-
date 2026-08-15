# Datasets & Modèles ML — Frontend (Angular 17 + PrimeNG)

Interface web de gestion de datasets, modèles de Machine Learning et expérimentations,
développée dans le cadre du devoir pratique *Spring Boot & Angular (PrimeNG 17)* du
Master Intelligence Artificielle. Consomme l'API REST du backend Spring Boot (dossier
voisin `datasetsMachineLearning/`).

## Stack technique

- Angular 17 (composants standalone, routing avec lazy loading)
- PrimeNG 17 (tableaux, formulaires, dialogues, notifications)
- Formulaires réactifs (`ReactiveFormsModule`)
- HttpClient

## Prérequis

- Node.js 18+ et npm
- Le backend Spring Boot démarré sur `http://localhost:8080` (voir le README du dossier `datasetsMachineLearning`)

## Installation

```bash
npm install
```

## Lancement

```bash
npm start
```

L'application démarre sur **http://localhost:4200**.

> Le backend doit être démarré au préalable (`localhost:8080`) : le frontend n'embarque
> aucune donnée, tout transite par l'API REST.

## Build de production

```bash
npm run build
```

Les fichiers sont générés dans `dist/angulardataset/`.

## Structure du projet

```
src/app/
├── models/       # Interfaces TypeScript miroir des DTOs backend (Dataset, ModeleML, Experimentation)
├── services/     # Services HttpClient, un par entité
├── pages/
│   ├── dataset-list/          # Vue Datasets : tableau + formulaire réactif + dialog
│   ├── modele-list/           # Vue Modèles ML
│   └── experimentation-list/  # Vue Expérimentations (sélection dataset/modèle liés)
├── app.routes.ts     # Navigation entre les 3 vues (lazy loading)
├── app.config.ts     # Providers globaux (router, HttpClient, animations)
└── app.component.*   # Layout racine + barre de navigation
```

## Fonctionnalités par vue

Chaque vue (Datasets, Modèles ML, Expérimentations) propose :

- Un tableau (`p-table`) avec pagination, tri par colonne et recherche globale
- Un formulaire réactif dans une boîte de dialogue (`p-dialog`) pour la création et la modification, avec validation côté client (champs requis, plages numériques)
- Une confirmation avant suppression (`p-confirmDialog`)
- Des notifications de succès/erreur (`p-toast`)

La vue Expérimentations permet en plus de choisir le dataset et le modèle associés via des listes déroulantes alimentées par l'API.

## Backend associé

Voir le README du dossier `datasetsMachineLearning/` pour la configuration de la base de
données et le lancement de l'API. Documentation interactive de l'API : http://localhost:8080/swagger-ui.html
