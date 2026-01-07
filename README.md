# Application vente de formation
Application console en Java permettant la vente de formation, la création d'un compte ainsi que l'ajout dans le panier et achat.

-----

## Documents :
Le projet est séparé en 3 documents principaux :
- bdd : Export des éléments dans la base de donnée,
- doc -> uml (fichiers + png) :
  - Diagramme de classe,
  - Diagramme de cas d'utilisation,
  - Diagrammes de séquences.
- src : Dossier de l'application Java.

## Téléchargement et versions :
Le programme est écrit en Java 8.  

-----

# Fonctionnement :
L'application se déroule en affichage console.
Lors du lancement, le programme demande à l'utilisateur d'entrer l'une des commandes disponibles.

Chaque commande de l'utilisateur va l'emmener vers une prochaine étape qui fonctionnera un peu comme une autre "page",
Celle-ci aura donc ses propres choix de commande dans cet ordre :
- En premier lieu la connection,
  - 0 pour quitter,
  - 1 pour se connecter,
  - 2 pour utiliser l'application en tant qu'invité.

- Voir la liste des formations ou son panier,
  - 0 pour revenir en arrière,
  - 1 voir les formations,
  - 2 voir le panier.

- Naviguer et filtrer les formations.
  - 0 pour revenir en arrière,
  - 1 voir la liste entière,
  - 2 filtrer pour les formations à distance,
  - 3 filtrer pour les formations en présentiel,
  - 4 filtrer à partir d'un mot clé.

Une fois dans la page de navigation des formations, l'utilisateur peut taper "view 'x'",
Le "x" étant remplacé par l'indexe de la formation souhaité dans la liste, (view 1 pour la première)
Pour voir en détail une formation proposé.  

Après avoir sélectionné une formation avec view, l'utilisateur peut entrer "add" pour ajouter une formation au panier.
