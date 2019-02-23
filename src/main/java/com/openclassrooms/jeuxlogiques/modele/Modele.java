package com.openclassrooms.jeuxlogiques.modele;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;
import com.openclassrooms.jeuxlogiques.vue.Observateur;
import com.openclassrooms.jeuxlogiques.vue.Vue;

public class Modele implements SujetObservable {

	private Vue vue;
	private Controleur controleur;

	private ArrayList<Observateur> listeObservateurs;

	private int nbEssais;
	private int nbPionsCombinaison;
	private int nbPionsUtilisables;
	private int compteurEssais;

	private Jeu jeu;

	private List<Pion> combinaisonSecrete;
	private List<Pion> combinaisonProposition;
	private List<Pion> combinaisonReponse;
	private List<Pion> pionsUtilisables;

	private Pion pionSecret;
	private Pion pionProposition;

	public Modele() {
		listeObservateurs = new ArrayList<>();
		nbEssais = 8;
		nbPionsCombinaison = 4;
		nbPionsUtilisables = 10;
		compteurEssais = nbEssais;
	}

	public void initialiser() {
		combinaisonSecrete = new ArrayList<>(nbPionsCombinaison);
		combinaisonProposition = new ArrayList<>(nbPionsCombinaison);
		combinaisonReponse = new ArrayList<>(nbPionsCombinaison);
		pionsUtilisables = new ArrayList<>(nbPionsUtilisables);
		initialiserCombinaison(combinaisonSecrete, nbPionsCombinaison);
		initialiserCombinaison(combinaisonProposition, nbPionsCombinaison);
		initialiserCombinaison(combinaisonReponse, nbPionsCombinaison);
		initialiserCombinaison(pionsUtilisables, nbPionsUtilisables);
	}

	public void initialiserCombinaison(List<Pion> combinaison, int nbPions) {
		combinaison.clear();
		for (int i = 0; i < nbPions; i++)
			combinaison.add(PionCommun.Vide);
	}

	public void setVue(Vue vue) {
		this.vue = vue;
	}

	public void setControleur(Controleur controleur) {
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
		return nbPionsUtilisables;
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

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public void getPionSecret(int x) {
		combinaisonSecrete.set(x - 1, pionSecret);
		notifierObservateur();
	}

	public void setPionSecret(Pion pionSecret) {
		this.pionSecret = pionSecret;
		System.out.println(pionSecret.getNomImage());
	}

	public void getPionProposition(int x) {
		combinaisonProposition.set(x - 1, pionProposition);
		notifierObservateur();
	}

	public void setPionProposition(Pion pionProposition) {
		this.pionProposition = pionProposition;
		System.out.println(pionProposition.getNomImage());
	}

}