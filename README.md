# Guide de compilation et d'utilisation des jeux Recherche +/- et Mastermind

## Pr�sentation du projet

**Contexte**   
Ce projet propose de cr�er une application proposant des jeux de logique. Plusieurs variantes autour de la recherche de combinaisons secr�tes sont � d�velopper :  
- La recherche d'une combinaison � l'aide d'indications +/-  
- Le Mastermind 
 
**Objectif**   
Ce projet consiste � mettre en �uvre les concepts fondamentaux de la programmation orient�e-objet en Java.

## Installation et ex�cution du projet

### Environnement d'ex�cution
**JavaSE-1.8**  
**jre1.8.0_201**

### Pr�sentation de l'arborescence du projet
**bin** : fichiers compil�s (.class, exc�cutable .jar)  
**doc** : documentation (.pdf)  
**javadoc** : documentation du code (.html)  
**lib** : librairies (Apache Common Lang, log4j)  
**logs** : fichiers des logs (.log)  
**src** : sources (.java, .properties, .xml, .html, images)  

### Ex�cution du projet

#### Mode normal

Placez-vous en ligne de commande dans le r�pertoire **"bin"** et taper la commande :  

**java -jar jeux-de-logique-0.0.1.jar**  

Apr�s le lancement, une fen�tre graphique Swing s'ouvre pour permettre de jouer aux jeux.  

#### Mode d�veloppeur

Un mode d�veloppeur peut �tre activ� par le passage d'un param�tre au lancement de l'application.  
Placez-vous en ligne de commande dans le r�pertoire **"bin"** et taper la commande :  

**java -jar jeux-de-logique-0.0.1.jar 1**  

Apr�s le lancement, une fen�tre graphique Swing s'ouvre pour permettre de jouer aux jeux dans lesquels la solution est affich�e d�s le d�part.  

### R�gles des jeux

#### Recherche +/-

**But**  
Le but du Recherche +/- est de d�couvrir la combinaison � x chiffres de l'adversaire (le d�fenseur). Pour ce faire, l'attaquant fait une proposition. Le d�fenseur indique pour chaque chiffre de la combinaison propos�e si le chiffre de sa combinaison secr�te est plus grand (+), plus petit (-) ou si c'est le bon chiffre (=).  
L'attaquant doit deviner la combinaison secr�te en un nombre limit� d'essais.  

**Exemple**  
Les pions **"+"** indiquent le chiffre de la combinaison secr�te est **plus grand** que celui de la proposition.  
Les pions **"-"** indiquent le chiffre de la combinaison secr�te est **plus petit** que celui de la proposition.  
Les pions **"="** indiquent le chiffre de la combinaison secr�te est **�gal** � celui de la proposition.  

![Exemple Recherche +/-](src/main/ressources/exemple_recherche_plus_moins.png)

#### Mastermind

**But**  
Le but du Mastermind est de d�couvrir la combinaison secr�te � x couleurs de l'adversaire (le d�fenseur). Pour ce faire, l'attaquant fait une proposition de couleurs. Le d�fenseur indique pour chaque proposition le nombre de couleur de la proposition qui apparaissent � la bonne place et � la mauvaise place dans la combinaison secr�te.  
L'attaquant doit deviner la combinaison secr�te en un nombre limit� d'essais.  

**Exemple**  
Les pions **noirs** indiquent le nombre de couleurs qui sont **bien plac�es**.  
Les pions **blancs** indiquent le nombre de couleurs qui sont **mal plac�es**.  

![Exemple Mastermind](src/main/ressources/exemple_mastermind.png)