# TP-CSR
Construction d'une usine.

Ce projet a pour but d'appliquer les concepts de coopération et de concurrence dans les systèmes et réseaux. L'objectif principal est de comprendre les problèmes de concurrence entre les programmes, de synchronisation et d'ordonnancement à l'aide de threads.
1. Classes et Fonctionnalités
   
1.1 Les Stocks
La classe Stock représente un stock de pièces. Elle comprend les méthodes suivantes :

stocker() : Ajoute une pièce au stock.
destocker() : Retire une pièce du stock.
afficher() : Affiche le nom du stock et le nombre de pièces actuellement disponibles.
Le constructeur prend en paramètres le nom du stock et le nombre initial de pièces.

1.2 Les Ateliers
La classe Atelier représente un atelier de transformation avec les méthodes :

transformer() : Retire un élément d'un stock A, attend 100 ms, puis ajoute un élément au stock B.
travailler() : Effectue un nombre n de transformations successives.
Le constructeur prend en paramètres des références sur les stocks A et B, ainsi que le nombre de transformations à effectuer.

1.3 L'Usine
La classe Usine représente une usine avec deux ateliers. Elle dispose d'un stock de pièces à transformer (initialement doté de 10 unités) et d'un stock de pièces finies (initialement vide). Chaque atelier transforme 5 unités. La méthode fonctionner() fait travailler successivement les deux ateliers et affiche l'état des stocks à la fin des travaux.
