package com.openclassrooms.jeuxlogiques.modele;

import java.util.ArrayList;

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
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

	private Jeu jeu;

	private ArrayList<Pion> combinaisonSecrete;
	private ArrayList<Pion> combinaisonProposition;
	private ArrayList<Pion> combinaisonReponse;
	private ArrayList<Pion> pionsUtilisables;

	private Pion pionSelectionne;

	public Modele() {
		listeObservateurs = new ArrayList<>();
		nbEssais = 8;
		nbPionsCombinaison = 4;
		nbPionsUtilisables = 10;
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

	public void setCombinaisonSecrete(ArrayList<Pion> combinaisonSecrete) {
		this.combinaisonSecrete = combinaisonSecrete;
	}

	public void setCombinaisonProposition(ArrayList<Pion> combinaisonProposition) {
		this.combinaisonProposition = combinaisonProposition;
	}

	public void setCombinaisonReponse(ArrayList<Pion> combinaisonReponse) {
		this.combinaisonReponse = combinaisonReponse;
	}

	public void setPionsUtilisables(ArrayList<Pion> pionsUtilisables) {
		this.pionsUtilisables = pionsUtilisables;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public Pion getPionSelectionne() {
		System.out.println(pionSelectionne.getNomImage());
		return pionSelectionne;
	}

	public void setPionSelectionne(Pion pionSelectionne) {
		this.pionSelectionne = pionSelectionne;
		System.out.println(pionSelectionne.getNomImage());
	}

}