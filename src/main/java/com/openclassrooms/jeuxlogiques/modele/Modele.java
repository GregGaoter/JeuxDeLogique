package com.openclassrooms.jeuxlogiques.modele;

import java.util.ArrayList;

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.vue.Observateur;
import com.openclassrooms.jeuxlogiques.vue.Vue;

public class Modele implements SujetObservable {

	private Vue vue;
	private Controleur controleur;
	private ArrayList<Observateur> listeObservateurs;
	private int nbEssais;
	private int nbPionsCombinaison;
	private int nbPionsUtilisables;

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

}