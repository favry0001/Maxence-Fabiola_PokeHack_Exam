## *PokédexHack Java*

## Description

Application JavaFX permettant de rechercher des Pokémon via la PokéAPI et de les sauvegarder dans une base de données PostgreSQL.

## Fonctionnalités

- Recherche d’un Pokémon par nom ou ID
- Affichage des informations du Pokémon
- Capture d’un Pokémon
- Sauvegarde dans PostgreSQL
- Liste des Pokémon capturés

## Technologies

- Java 21
- JavaFX
- PostgreSQL
- JDBC
- Maven
- PokéAPI

## Installation

1. Installer Java 21
2. Installer PostgreSQL
3. Créer une base de données nommée pokedex
4. Exécuter le script SQL fourni
5. Modifier les paramètres de connexion dans DatabaseConnection.java
6. Lancer HelloApplication

## Architecture

Le projet suit une architecture MVC stricte, séparée par packages :
 
```
com.example.maxencefabiola_pokehack/
├── model/        → Pokemon.java (POJO)
├── dao/          → PokemonDAO (interface) + PokemonDAOImpl (JDBC/UPSERT)
├── service/      → PokeApiService (appel HTTP + parsing Jackson)
├── controller/   → PokedexController (logique UI, threading)
├── view/         → pokedex-view.fxml
└── util/         → DatabaseConnection (connexion PostgreSQL)
```

##Example du produit finale

<img width="3024" height="4032" alt="Image (2)" src="https://github.com/user-attachments/assets/0ad95220-3427-4aad-9307-591eb9b8d3f8" />

<img width="3024" height="4032" alt="Image (2)" src="https://github.com/user-attachments/assets/0ad95220-3427-4aad-9307-591eb9b8d3f8" />
<img width="3024" height="4032" alt="Image" src="https://github.com/user-attachments/assets/c4b379cc-f1e3-4a1e-bd95-e74bc05ed458" />
<img width="3024" height="4032" alt="Image (1)" src="https://github.com/user-attachments/assets/2dc0c297-5fba-49f6-80b2-904fc7452802" />


Répartition du travail

Membre    |  Responsabilités
Fabiola   |  Backend, base de données, DAO, modèle Pokemon, service API, multi-threading, gestion des erreurs
MaxVue    |  Frontend, JavaFX, CSS, Controller, animations, vidéo démo, gestion des erreurs, README

## Auteurs

- Maxence
- Fabiola
