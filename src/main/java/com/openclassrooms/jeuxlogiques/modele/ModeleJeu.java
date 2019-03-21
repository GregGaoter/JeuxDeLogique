package com.openclassrooms.jeuxlogiques.modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Parametre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;
import com.openclassrooms.jeuxlogiques.modele.joueur.Humain;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.modele.joueur.Ordinateur;
import com.openclassrooms.jeuxlogiques.vue.Observateur;
import com.openclassrooms.jeuxlogiques.vue.Vue;

/**
 * <b>ModeleJeu est une classe permettant de repr�senter les donn�es du jeu en
 * cours.</b></br>
 * La classe ModeleJeu impl�mente l'interface SujetObservable.</b>
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public class ModeleJeu implements SujetObservable {

	private final static Logger log = Logger.getLogger(ModeleJeu.class);

	/**
	 * Vue des jeux.
	 */
	private Vue vue;

	/**
	 * Controleur des jeux.
	 * 
	 * @see ModeleJeu#setPionProposition(Pion)
	 */
	private ControleurJeu controleur;

	/**
	 * Jeu courant.
	 * 
	 * @see ModeleJeu#initialiser()
	 * @see ModeleJeu#getNbPionsUtilisables()
	 * @see ModeleJeu#setNbPionsUtilisables(int)
	 */
	private Jeu jeu;

	/**
	 * Liste contenant les observateurs du mod�le.
	 * 
	 * @see ModeleJeu#Joueur()
	 * @see ModeleJeu#ajouterObservateur(Observateur)
	 * @see ModeleJeu#supprimerObservateur(Observateur)
	 * @see ModeleJeu#notifierObservateur()
	 */
	private ArrayList<Observateur> listeObservateurs;

	/**
	 * Nombre d'essais possibles.
	 * 
	 * @see ModeleJeu#ModeleJeu()
	 * @see ModeleJeu#initialiser()
	 */
	private int nbEssais;

	/**
	 * Nombre de pions de la combinaison secr�te.
	 * 
	 * @see ModeleJeu#ModeleJeu()
	 */
	private int nbPionsCombinaison;

	/**
	 * Compteur du nombre d'essais restants.
	 * 
	 * @see ModeleJeu#ModeleJeu()
	 * @see ModeleJeu#initialiser()
	 * @see ModeleJeu#decrementerCompteurEssais()
	 */
	private int compteurEssais;

	/**
	 * Liste des pions constituant la combinaison secr�te.
	 * 
	 * @see ModeleJeu#initialiser()
	 * @see ModeleJeu#getPionSecret(int)
	 */
	private List<Pion> combinaisonSecrete;

	/**
	 * Liste des pions constituant la combinaison proposition.
	 * 
	 * @see ModeleJeu#initialiser()
	 */
	private List<Pion> combinaisonProposition;

	/**
	 * Liste des pions constituant la combinaison r�ponse.
	 * 
	 * @see ModeleJeu#initialiser()
	 */
	private List<Pion> combinaisonReponse;

	/**
	 * Liste de tous les pions utilisables du jeu en cours.
	 */
	private List<Pion> pionsUtilisables;

	/**
	 * Liste des pions constituant le panneau validation.
	 * 
	 * @see ModeleJeu#initialiser()
	 */
	private HashMap<String, JPanel> listePanneauValidation;

	/**
	 * D�fenseur courant. Le d�fenseur est le joueur qui a choisit la combinaison
	 * secr�te.
	 * 
	 * @see ModeleJeu#ModeleJeu()
	 * @see ModeleJeu#initialiser()
	 */
	private Joueur defenseur;

	/**
	 * Attaquant courant. L'attaquant est le joueur qui doit deviner la combinaison
	 * secr�te.
	 * 
	 * @see ModeleJeu#ModeleJeu()
	 * @see ModeleJeu#initialiser()
	 * @see ModeleJeu#getPionProposition(int)
	 */
	private Joueur attaquant;

	/**
	 * Pion secr�te s�lectionn� par le joueur d�fenseur.
	 * 
	 * @see ModeleJeu#getPionSecret(int)
	 * @see ModeleJeu#setPionSecret(Pion)
	 */
	private Pion pionSecret;

	/**
	 * Pion proposition s�lectionn� par le joueur attaquant.
	 * 
	 * @see ModeleJeu#initialiser()
	 * @see ModeleJeu#getPionProposition(int)
	 * @see ModeleJeu#setPionProposition(Pion)
	 */
	private Pion pionProposition;

	/**
	 * Nom du joueur.
	 * 
	 * @see ModeleJeu#ModeleJeu()
	 */
	private String nomJoueur;

	/**
	 * Constructeur du mod�le.
	 * <p>
	 * Le constructeur initialise :
	 * <ul>
	 * <li>La liste des observateurs</li>
	 * <li>Le nombre d'essais possibles</li>
	 * <li>Le compteur d'essais restant</li>
	 * <li>Le nombre de pions de la combinaison secr�te</li>
	 * <li>Le nom du joueur</li>
	 * <li>L'instanciation du d�fenseur</li>
	 * <li>L'instanciation de l'attaquant</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Parametre
	 * @see Humain
	 * @see Ordinateur
	 */
	public ModeleJeu() {
		log.info("Construction du mod�le.");
		listeObservateurs = new ArrayList<>();
		nbEssais = compteurEssais = Parametre.NbEssais.getValeur();
		nbPionsCombinaison = Parametre.NbPionsCombinaison.getValeur();
		nomJoueur = "Joueur";
		defenseur = new Ordinateur();
		defenseur.setAttaquantQ(false);
		attaquant = new Humain();
		attaquant.setAttaquantQ(true);
	}

	/**
	 * Initialisation du mod�le.
	 * <p>
	 * L'initialisation du mod�le d�finit les composants suivants :
	 * <ul>
	 * <li>La valeur du compteur d'essais</li>
	 * <li>La liste des pions de la combinaison secr�te</li>
	 * <li>La liste des pions de la combinaison proposition</li>
	 * <li>La liste des pions de la combinaison r�ponse</li>
	 * <li>La liste des pions du panneau de validation</li>
	 * <li>La liste des pions utilisables</li>
	 * <li>Le pion proposition</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Arrays#asList(Object...)
	 */
	public void initialiser() {
		log.info("Initialisation du mod�le.");
		compteurEssais = nbEssais;
		combinaisonSecrete = defenseur.getCombinaisonSecrete();
		combinaisonProposition = attaquant.getCombinaisonProposition();
		combinaisonReponse = attaquant.getCombinaisonReponse();
		listePanneauValidation = attaquant.getListePanneauValidation();
		setPionsUtilisables(Arrays.asList(jeu.getPionsJeu()));
		pionProposition = PionCommun.Vide;
	}

	/**
	 * Initialise la liste des pions d'une combinaison.
	 * 
	 * @param combinaison la combinaison de pions � initialiser
	 * @param nbPions     le nombre de pions de la combinaison � initialiser
	 * @see Pion
	 */
	public void initialiserCombinaison(List<Pion> combinaison, int nbPions) {
		combinaison.clear();
		for (int i = 0; i < nbPions; i++)
			combinaison.add(PionCommun.Vide);
	}

	public void setVue(Vue vue) {
		this.vue = vue;
	}

	public ControleurJeu getControleur() {
		return controleur;
	}

	public void setControleur(ControleurJeu controleur) {
		this.controleur = controleur;
	}

	/**
	 * Ajoute un observateur � la liste des observateurs.
	 * 
	 * @param observateur l'observateur � ajouter
	 */
	public void ajouterObservateur(Observateur observateur) {
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

	public int getNbEssais() {
		return nbEssais;
	}

	public int getNbPionsCombinaison() {
		return nbPionsCombinaison;
	}

	/**
	 * R�cup�re le nombre de pions utilisables du jeu.
	 * 
	 * @return le nombre de pions utilisables
	 */
	public int getNbPionsUtilisables() {
		return jeu.getNbPionsUtilisables();
	}

	/**
	 * D�finit le nombre de pions utilisables du jeu.
	 * 
	 * @param nbPionsUtilisables nombre de pions utilisables du jeu
	 */
	public void setNbPionsUtilisables(int nbPionsUtilisables) {
		jeu.setNbPionsUtilisables(nbPionsUtilisables);
	}

	public void setNbEssais(int nbEssais) {
		this.nbEssais = nbEssais;
	}

	public void setNbPionsCombinaison(int nbPionsCombinaison) {
		this.nbPionsCombinaison = nbPionsCombinaison;
	}

	public int getCompteurEssais() {
		return compteurEssais;
	}

	public void setCompteurEssais(int compteurEssais) {
		this.compteurEssais = compteurEssais;
	}

	/**
	 * D�cr�mente la valeur du compteur d'essais de l'attaquant de 1.
	 * 
	 * @see ModeleJeu#compteurEssais
	 */
	public void decrementerCompteurEssais() {
		compteurEssais--;
	}

	public void setCombinaisonSecrete(List<Pion> combinaisonSecrete) {
		this.combinaisonSecrete = combinaisonSecrete;
	}

	public void setCombinaisonProposition(List<Pion> combinaisonProposition) {
		this.combinaisonProposition = combinaisonProposition;
	}

	public void setCombinaisonReponse(List<Pion> combinaisonReponse) {
		this.combinaisonReponse = combinaisonReponse;
		notifierObservateur();
	}

	public HashMap<String, JPanel> getListePanneauValidation() {
		return listePanneauValidation;
	}

	public void setListePanneauValidation(HashMap<String, JPanel> listePanneauValidation) {
		this.listePanneauValidation = listePanneauValidation;
	}

	public void setPionsUtilisables(List<Pion> pionsUtilisables) {
		this.pionsUtilisables = pionsUtilisables;
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

	public List<Pion> getPionsUtilisables() {
		return pionsUtilisables;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public Joueur getDefenseur() {
		return defenseur;
	}

	public void setDefenseur(Joueur defenseur) {
		this.defenseur = defenseur;
	}

	public Joueur getAttaquant() {
		return attaquant;
	}

	public void setAttaquant(Joueur attaquant) {
		this.attaquant = attaquant;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	/**
	 * R�cup�re le pion de la liste des pions utilisables que le joueur a
	 * s�lectionn� avec la souris afin de le placer dans la combinaison secr�te.
	 * Cette action est valable uniquement dans la bo�te de dialogue de s�lection de
	 * la combinaison secr�te.
	 * 
	 * @param x position en x du pion dans la combinaison secr�te.
	 */
	public void getPionSecret(int x) {
		combinaisonSecrete.set(x - 1, pionSecret);
		notifierObservateur();
	}

	/**
	 * D�finit le pion de la liste des pions utilisables que le joueur a s�lectionn�
	 * avec la souris afin de constituer la combinaison secr�te. Cette action est
	 * valable uniquement dans la bo�te de dialogue de s�lection de la combinaison
	 * secr�te.
	 * 
	 * @param pion le pion s�lectionn� par un clique de la souris
	 */
	public void setPionSecret(Pion pionSecret) {
		this.pionSecret = pionSecret;
	}

	/**
	 * R�cup�re le pion de la liste des pions utilisables que le joueur a
	 * s�lectionn� avec la souris afin de le placer dans la combinaison proposition.
	 * Cette action est valable uniquement dans la zone proposition du plateau de
	 * jeu.
	 * 
	 * @param x position en x du pion dans la combinaison proposition.
	 */
	public void getPionProposition(int x) {
		try {
			attaquant.getCombinaisonProposition().set(x - 1, pionProposition);
			attaquant.setPion(attaquant.getListePanneauProposition(),
					attaquant.getClef(x, attaquant.getCompteurEssais()), pionProposition);
			setCombinaisonProposition(attaquant.getCombinaisonProposition());
			notifierObservateur();
		} catch (IndexOutOfBoundsException e) {
			log.error("IndexOutOfBoundsException : " + e.getMessage());
		}
	}

	/**
	 * D�finit le pion de la liste des pions utilisables que le joueur a s�lectionn�
	 * avec la souris afin de constituer la combinaison proposition. Cette action
	 * est valable uniquement dans la zone proposition du plateau de jeu.
	 * 
	 * @param pion le pion s�lectionn� par un clique de la souris
	 */
	public void setPionProposition(Pion pionProposition) {
		this.pionProposition = pionProposition;
		controleur.setPionSelectionne();
	}

	public Pion getPionSelectionne() {
		return pionProposition;
	}

}