# Projet Labyrinthe
 

Nous hésitons entre 2 algorithmes de création de labyrinthe : 
* Depth-First Search (Recherche en profondeur d'abord)
* Fusion aléatoire de chemins


Finalement, j'ai choisi la méthode Depth First Search qui paraissait un peu plus
simple à mes yeux au moment du choix.
Voici les différentes étapes de l'algorithme : 

### Méthode de Depth First Search

1. Sélectionnez au hasard un nœud (ou une cellule) N.
2. Poussez la cellule N dans une file d'attente Q.
3. Marquez la cellule N comme visitée.
4. Sélectionnez au hasard une cellule adjacente A de la cellule N qui n'a pas été visitée. Si tous les voisins de N ont été visités :
- Continuez à retirer les éléments de la file d'attente Q jusqu'à ce qu'une cellule soit rencontrée avec au moins un voisin non visité - affectez cette cellule à N et passez à l'étape 4.
- Si aucune cellule n'existe : stop.
5. Cassez le mur entre N et A.
6. Attribuez la valeur A à N.
7. Passez à l'étape 2.