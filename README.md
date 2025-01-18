# MVC GridGame

Un jeu de grille en Java où un agent doit collecter des joyaux tout en évitant des gardiens. Le jeu utilise une architecture MVC et une interface console.

## Installation

```bash
# Compiler le projet
make compile

# Lancer le jeu
make run

# Nettoyer les fichiers compilés
make clean
```

## Structure du Projet

- `model/` : Classes du modèle (Agent8, Gardien, Joyaux, etc.)
- `view/` : Affichage console du jeu
- `controller/` : Gestion des interactions
- `game/` : Logique principale du jeu
- `exceptions/` : Gestion des erreurs
