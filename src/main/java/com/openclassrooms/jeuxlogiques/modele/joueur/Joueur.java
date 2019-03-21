package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.SujetObservable;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.vue.Observateur;
import com.openclassrooms.jeuxlogiques.vue.PanneauBoutonValidation;

/**
 * <b>Joueur est une classe abstraite fournissant des m�thodes permettant de
 * d�finir les combinaisons propres � chaque joueur.</br>
 * La classe Joueur impl�mente l'interface SujetObservable.</b>
 * 
 * @see SujetObservable
 * @author Gr�gory Gautier
 * @version 1.0
 */
public abstract class Joueur implements SujetObservable {

	/**
	 * Texte s�parant les positions en x et en y des pions dans les clefs de
	 * recherche.
	 * 
	 * @see ControleurJeu#getClef(int, int)
	 */
	private final String separateurClef = "-";

	/**
	 * Liste contenant toutes les combinaisons possibles de la partie en cours.
	 * 
	 * @see Ordinateur#setListeCombinaisonsPossibles()
	 */
	protected List<List<Pion>> listeCombinaisonsPossibles;

	/**
	 * Nombre de combinaisons possibles de la partie en cours.
	 * 
	 * @see Ordinateur#setListeCombinaisonsPossibles()
	 */
	protected int nbCombinaisonsPossibles;

	/**
	 * Liste des pions constituant la combinaison secr�te.
	 * 
	 * @see Joueur#Joueur()
	 * @see Joueur#initialiserJoueur()
	 */
	protected List<Pion> combinaisonSecrete;

	/**
	 * Liste des pions constituant la combinaison proposition.
	 * 
	 * @see Joueur#Joueur()
	 * @see Joueur#initialiserJoueur()
	 */
	protected List<Pion> combinaisonProposition;

	/**
	 * Liste des pions constituant la combinaison r�ponse.
	 * 
	 * @see Joueur#Joueur()
	 * @see Joueur#initialiserJoueur()
	 */
	protected List<Pion> combinaisonReponse;

	/**
	 * Liste des pions constituant le panneau proposition.
	 * 
	 * @see Joueur#Joueur()
	 * @see Joueur#initialiserJoueur()
	 */
	protected HashMap<String, Pion> listePanneauProposition;

	/**
	 * Liste des pions constituant le panneau r�ponse.
	 * 
	 * @see Joueur#Joueur()
	 * @see Joueur#initialiserJoueur()
	 */
	protected HashMap<String, Pion> listePanneauReponse;

	/**
	 * Liste des pions constituant le panneau validation.
	 * 
	 * @see Joueur#Joueur()
	 * @see Joueur#initialiserJoueur()
	 */
	protected HashMap<String, JPanel> listePanneauValidation;

	/**
	 * Liste contenant les observateurs du joueur.
	 * 
	 * @see Joueur#Joueur()
	 * @see Joueur#ajouterObservateur(Observateur)
	 * @see Joueur#supprimerObservateur(Observateur)
	 * @see Joueur#notifierObservateur()
	 */
	protected List<Observateur> listeObservateurs;

	/**
	 * Mod�le des jeux.
	 * 
	 * @see Joueur#initialiserJoueur()
	 * @see Joueur#initialiserCombinaison(List)
	 * @see Joueur#setListePanneauValidation()
	 * @see Joueur#setCombinaisonReponse(List)
	 */
	protected ModeleJeu modele;

	/**
	 * Contr�leur des jeux.
	 * 
	 * @see Joueur#initialiserJoueur()
	 */
	protected ControleurJeu controleur;

	/**
	 * Nom du joueur.
	 */
	protected String nom;

	/**
	 * Compteur d'essais du joueur.
	 * 
	 * @see Joueur#initialiserJoueur()
	 * @see Joueur#setListePanneauValidation()
	 * @see Joueur#actualiserPanneauValidation()
	 * @see Joueur#setCombinaisonReponse(List)
	 * @see Joueur#decrementerCompteurEssais()
	 */
	protected int compteurEssais;

	/**
	 * Contient l'�tat d�terminant si le joueur est attaquant.
	 */
	private boolean attaquantQ;

	/**
	 * Contient l'�tat d�terminant si le joueur est humain.
	 */
	protected boolean humainQ;

	/**
	 * Contient l'�tat d�terminant si le joueur est vainqueur.
	 * 
	 * @see Joueur#Joueur()
	 */
	private boolean vainqueurQ;

	/**
	 * Bouton de validation du joueur.
	 * 
	 * @see Joueur#Joueur()
	 * @see Joueur#initialiserJoueur()
	 * @see Joueur#setListePanneauValidation()
	 * @see Joueur#actualiserPanneauValidation()
	 */
	private JButton boutonValidation;

	/**
	 * Constructeur du joueur.
	 * <p>
	 * Le constructeur initialise :
	 * <ul>
	 * <li>La combinaison secr�te</li>
	 * <li>La combinaison proposition</li>
	 * <li>La combinaison r�ponse</li>
	 * <li>La liste du panneau proposition</li>
	 * <li>La liste du panneau r�ponse</li>
	 * <li>La liste du panneau validation</li>
	 * <li>Le bouton de validation</li>
	 * <li>L'�tat victorieux du joueur</li>
	 * <li>La liste des observateurs</li>
	 * </ul>
	 * </p>
	 * 
	 * @see ArrayList
	 * @see HashMap
	 */
	public Joueur() {
		combinaisonSecrete = new ArrayList<>();
		combinaisonProposition = new ArrayList<>();
		combinaisonReponse = new ArrayList<>();
		listePanneauProposition = new HashMap<>();
		listePanneauReponse = new HashMap<>();
		listePanneauValidation = new HashMap<>();
		boutonValidation = new JButton("Valider");
		vainqueurQ = false;
		listeObservateurs = new ArrayList<>();
	}

	/**
	 * Initialisation du joueur.
	 * <p>
	 * L'initialisation du joueur d�finit les composants suivants :
	 * <ul>
	 * <li>Son compteur d'essais</li>
	 * <li>Sa combinaison secr�te</li>
	 * <li>Sa combinaison proposition</li>
	 * <li>Sa combinaison r�ponse</li>
	 * <li>Sa liste du panneau proposition</li>
	 * <li>Sa liste du panneau r�ponse</li>
	 * <li>Son bouton de validation</li>
	 * <li>L'action du bouton de validation</li>
	 * <li>Sa liste du panneau validation</li>
	 * <li>Sa liste de toutes les combinaisons possibles de la partie</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Joueur#initialiserCombinaison(List)
	 * @see Joueur#setListePanneauValidation()
	 * @see Ordinateur#setListeCombinaisonsPossibles()
	 */
	public void initialiserJoueur() {
		compteurEssais = modele.getNbEssais();
		initialiserCombinaison(combinaisonSecrete);
		initialiserCombinaison(combinaisonProposition);
		initialiserCombinaison(combinaisonReponse);
		setListePanneauPion(listePanneauProposition, modele.getNbPionsCombinaison(), modele.getNbEssais(),
				PionCommun.Vide);
		setListePanneauPion(listePanneauReponse, modele.getNbPionsCombinaison(), modele.getNbEssais(), PionCommun.Vide);
		boutonValidation = new JButton("Valider");
		boutonValidation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.calculerReponse();
			}
		});
		setListePanneauValidation();
		setListeCombinaisonsPossibles();
	}

	/**
	 * Initialise la liste des pions d'une combinaison.
	 * 
	 * @param combinaison la combinaison de pions � initialiser
	 * @see Pion
	 */
	public void initialiserCombinaison(List<Pion> combinaison) {
		combinaison.clear();
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++)
			combinaison.add(PionCommun.Vide);
	}

	private void setListePanneauPion(HashMap<String, Pion> listePanneau, int xMax, int yMax, Pion pion) {
		listePanneau.clear();
		for (int y = 1; y <= yMax; y++) {
			for (int x = 1; x <= xMax; x++)
				listePanneau.put(getClef(x, y), pion);
		}
	}

	/**
	 * Initialise la liste des panneaux du panneau validation.
	 * 
	 * @see PanneauBoutonValidation#PanneauBoutonValidation(JButton)
	 */
	public void setListePanneauValidation() {
		for (int y = 1; y <= modele.getNbEssais(); y++)
			listePanneauValidation.put(getClef(1, y), new PanneauBoutonValidation(boutonValidation));
		listePanneauValidation.get(getClef(1, compteurEssais)).add(boutonValidation);
	}

	/**
	 * Actualise la liste du panneau validation. Replace le bouton de validation au
	 * num�ro de l'essai courant.
	 */
	public void actualiserPanneauValidation() {
		if (compteurEssais > 0) {
			listePanneauValidation.get(getClef(1, compteurEssais + 1)).removeAll();
			listePanneauValidation.get(getClef(1, compteurEssais)).add(boutonValidation);
		}
	}

	public HashMap<String, JPanel> getListePanneauValidation() {
		return listePanneauValidation;
	}

	public JButton getBoutonValidation() {
		return boutonValidation;
	}

	/**
	 * D�finit un nouveau pion dans une liste de pions d'un panneau.
	 * 
	 * @param listePanneau liste des pions du panneau � red�finir
	 * @param clef         clef repr�sentant la position en x et en y du pion �
	 *                     red�finir dans le panneau
	 * @param pion         nouveau pion � red�finir
	 * @see HashMap
	 */
	public void setPion(HashMap<String, Pion> listePanneau, String clef, Pion pion) {
		listePanneau.put(clef, pion);
	}

	public List<List<Pion>> getListeCombinaisonsPossibles() {
		return listeCombinaisonsPossibles;
	}

	public List<Pion> getCombinaisonSecrete() {
		return combinaisonSecrete;
	}

	public List<Pion> getCombinaisonProposition() {
		return combinaisonProposition;
	}

	public List<Pion> getCombinaisonReponse() {
		return combinaisonReponse;
	}

	/**
	 * Red�finit la liste de pions de la combinaison r�ponse.
	 * 
	 * @param combinaisonReponse nouvelle liste de pions de la combinaison r�ponse
	 * @return description
	 * @see Joueur#setPion(HashMap, String, Pion)
	 */
	public void setCombinaisonReponse(List<Pion> combinaisonReponse) {
		this.combinaisonReponse = combinaisonReponse;
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++)
			setPion(listePanneauReponse, getClef(i, compteurEssais), combinaisonReponse.get(i - 1));
	}

	public HashMap<String, Pion> getListePanneauProposition() {
		return listePanneauProposition;
	}

	public HashMap<String, Pion> getListePanneauReponse() {
		return listePanneauReponse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCompteurEssais() {
		return compteurEssais;
	}

	public void setCompteurEssais(int compteurEssais) {
		this.compteurEssais = compteurEssais;
	}

	public boolean getAttaquantQ() {
		return attaquantQ;
	}

	public void setAttaquantQ(boolean attaquantQ) {
		this.attaquantQ = attaquantQ;
	}

	public boolean getHumainQ() {
		return humainQ;
	}

	public boolean getVainqueurQ() {
		return vainqueurQ;
	}

	public void setVainqueurQ(boolean vainqueurQ) {
		this.vainqueurQ = vainqueurQ;
	}

	public void setModele(ModeleJeu modele) {
		this.modele = modele;
	}

	public void setControleur(ControleurJeu controleur) {
		this.controleur = controleur;
	}

	/**
	 * R�cup�re la clef de recherche des pions dans les listes des combinaisons.
	 * 
	 * @param x position en x du pion dans la combinaison
	 * @param y position en y du pion dans la combinaison
	 * @return la valeur de la clef de recherche
	 * @see String#valueOf(boolean)
	 */
	public String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	/**
	 * D�cr�mente la valeur du compteur d'essais du joueur de 1.
	 * 
	 * @see Joueur#compteurEssais
	 */
	public void decrementerCompteurEssais() {
		compteurEssais--;
	}

	/**
	 * Ajoute un observateur � la liste des observateurs.
	 * 
	 * @param observateur l'observateur � ajouter
	 */
	public void ajouterObservateur(Observateur observateur) {
		if (!listeObservateurs.contains(observateur))
			listeObservateurs.add(observateur);
	}

	/**
	 * Supprime un observateur de la liste des observateurs.
	 * 
	 * @param observateur l'observateur � supprimer
	 */
	public void supprimerObservateur(Observateur observateur) {
		listeObservateurs.remove(observateur);
	}

	/**
	 * Appelle la m�thode d'actualisation de tous les observateurs.
	 */
	public void notifierObservateur() {
		for (Observateur observateur : listeObservateurs)
			observateur.actualiser();
	}

	public abstract void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur);

	public abstract void setCombinaisonProposition();

	protected abstract void setListeCombinaisonsPossibles();

}