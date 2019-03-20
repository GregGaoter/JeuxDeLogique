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

public class ModeleJeu implements SujetObservable {

	private final static Logger log = Logger.getLogger(ModeleJeu.class);

	private Vue vue;
	private ControleurJeu controleur;

	private ArrayList<Observateur> listeObservateurs;

	private int nbEssais;
	private int nbPionsCombinaison;
	private int compteurEssais;

	private Jeu jeu;

	private List<Pion> combinaisonSecrete;
	private List<Pion> combinaisonProposition;
	private List<Pion> combinaisonReponse;
	private List<Pion> pionsUtilisables;

	private HashMap<String, JPanel> listePanneauValidation;

	private Joueur defenseur, attaquant;

	private Pion pionSecret;
	private Pion pionProposition;

	private String nomJoueur;

	public ModeleJeu() {
		log.info("Construction du modèle.");
		listeObservateurs = new ArrayList<>();
		nbEssais = compteurEssais = Parametre.NbEssais.getValeur();
		nbPionsCombinaison = Parametre.NbPionsCombinaison.getValeur();
		nomJoueur = "Joueur";
		defenseur = new Ordinateur();
		defenseur.setAttaquantQ(false);
		attaquant = new Humain();
		attaquant.setAttaquantQ(true);
	}

	public void initialiser() {
		log.info("Initialisation du modèle.");
		compteurEssais = nbEssais;
		combinaisonSecrete = defenseur.getCombinaisonSecrete();
		combinaisonProposition = attaquant.getCombinaisonProposition();
		combinaisonReponse = attaquant.getCombinaisonReponse();
		listePanneauValidation = attaquant.getListePanneauValidation();
		setPionsUtilisables(Arrays.asList(jeu.getPionsJeu()));
		pionProposition = PionCommun.Vide;
	}

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

	public void ajouterObservateur(Observateur observateur) {
		listeObservateurs.add(observateur);
	}

	public void supprimerObservateur(Observateur observateur) {
		listeObservateurs.remove(observateur);
	}

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

	public int getNbPionsUtilisables() {
		return jeu.getNbPionsUtilisables();
	}

	public void setNbEssais(int nbEssais) {
		this.nbEssais = nbEssais;
	}

	public void setNbPionsCombinaison(int nbPionsCombinaison) {
		this.nbPionsCombinaison = nbPionsCombinaison;
	}

	public void setNbPionsUtilisables(int nbPionsUtilisables) {
		jeu.setNbPionsUtilisables(nbPionsUtilisables);
	}

	public int getCompteurEssais() {
		return compteurEssais;
	}

	public void setCompteurEssais(int compteurEssais) {
		this.compteurEssais = compteurEssais;
	}

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

	public void getPionSecret(int x) {
		combinaisonSecrete.set(x - 1, pionSecret);
		notifierObservateur();
	}

	public void setPionSecret(Pion pionSecret) {
		this.pionSecret = pionSecret;
	}

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

	public void setPionProposition(Pion pionProposition) {
		this.pionProposition = pionProposition;
		controleur.setPionSelectionne();
	}

	public Pion getPionSelectionne() {
		return pionProposition;
	}

}