package com.openclassrooms.jeuxlogiques.modele;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Parametre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.vue.Observateur;
import com.openclassrooms.jeuxlogiques.vue.Vue;

public class ModeleJeu implements SujetObservable {

	public static final int NB_COULEURS_UTILISABLES_MIN = 4;
	public static final int NB_COULEURS_UTILISABLES_MAX = 10;
	public static final int NB_PIONS_COMBINAISON_MIN = 4;
	public static final int NB_PIONS_COMBINAISON_MAX = 6;
	public static final int NB_ESSAIS_MIN = 1;
	public static final int NB_ESSAIS_MAX = 12;

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

	private List<Joueur> listeDefenseurs;
	private List<Joueur> listeAttaquants;

	private Pion pionSecret;
	private Pion pionProposition;

	private String nomJoueur;

	public ModeleJeu() {
		listeObservateurs = new ArrayList<>();
		nbEssais = compteurEssais = Parametre.NbEssais.getValeur();
		nbPionsCombinaison = Parametre.NbPionsCombinaison.getValeur();
		nomJoueur = "Joueur";
	}

	public void initialiser() {
		compteurEssais = nbEssais;
		combinaisonSecrete = new ArrayList<>(nbPionsCombinaison);
		combinaisonProposition = new ArrayList<>(nbPionsCombinaison);
		combinaisonReponse = new ArrayList<>(nbPionsCombinaison);
		pionsUtilisables = new ArrayList<>(jeu.getNbPionsUtilisables());
		listeDefenseurs = new ArrayList<>();
		listeAttaquants = new ArrayList<>();
		pionProposition = PionCommun.Vide;
		initialiserCombinaison(combinaisonSecrete, nbPionsCombinaison);
		initialiserCombinaison(combinaisonProposition, nbPionsCombinaison);
		initialiserCombinaison(combinaisonReponse, nbPionsCombinaison);
		initialiserCombinaison(pionsUtilisables, jeu.getNbPionsUtilisables());
	}

	public void initialiserCombinaison(List<Pion> combinaison, int nbPions) {
		combinaison.clear();
		for (int i = 0; i < nbPions; i++)
			combinaison.add(PionCommun.Vide);
	}

	public void setVue(Vue vue) {
		this.vue = vue;
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

	public List<Joueur> getListeDefenseurs() {
		return listeDefenseurs;
	}

	public void setListeDefenseurs(List<Joueur> listeDefenseurs) {
		this.listeDefenseurs = listeDefenseurs;
	}

	public List<Joueur> getListeAttaquants() {
		return listeAttaquants;
	}

	public void setListeAttaquants(List<Joueur> listeAttaquants) {
		this.listeAttaquants = listeAttaquants;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
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
		combinaisonProposition.set(x - 1, pionProposition);
		notifierObservateur();
	}

	public void setPionProposition(Pion pionProposition) {
		this.pionProposition = pionProposition;
		controleur.setPionSelectionne();
	}

	public Pion getPionSelectionne() {
		return pionProposition;
	}

}