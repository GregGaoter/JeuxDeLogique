package com.openclassrooms.jeuxlogiques.modele;

import java.util.LinkedList;

import com.openclassrooms.jeuxlogiques.vue.Observateur;

public abstract class Modele {

	public static final int NB_PIONS_COMBINAISON_MIN = 4;
	public static final int NB_PIONS_COMBINAISON_MAX = 6;

	protected LinkedList<Observateur> observateurs;
	protected int nbPionsUtilisables;
	protected int nbPionsCombinaison;
	protected int nbEssais;
	protected int compteurEssais;
	protected LinkedList<?> solution;// Liste contenant les informations de la combinaison solution
	protected LinkedList<?> proposition;// Liste contenant les informations de la combinaison proposée
	protected LinkedList<?> reponse;// Liste contenant les informations de la combinaison réponse
	protected LinkedList<?> choixPossibles;// Liste contenant les informations des différents choix possibles pour les
											// combinaisons

	public Modele() {
		observateurs = new LinkedList<>();
		nbPionsUtilisables = 10;
		nbPionsCombinaison = 4;
		nbEssais = 8;
		compteurEssais = 0;
		solution = new LinkedList<>();
		proposition = new LinkedList<>();
		reponse = new LinkedList<>();
		choixPossibles = new LinkedList<>();
	}

	public int getNbPionsUtilisables() {
		return nbPionsUtilisables;
	}

	public int getNbPionsCombinaison() {
		return nbPionsCombinaison;
	}

	public void setNbPionsCombinaison(int nbPionsCombinaison) {
		this.nbPionsCombinaison = nbPionsCombinaison;
	}

	public int getNbEssais() {
		return nbEssais;
	}

	public void setNbEssais(int nbEssais) {
		this.nbEssais = nbEssais;
	}

	public void ajouterObservateur(Observateur observateur) {
		observateurs.add(observateur);
	}

	public void supprimerObservateur(Observateur observateur) {
		observateurs.remove(observateur);
	}

	public void notifierObservateur() {
		for (Observateur observateur : observateurs)
			observateur.actualiser();
	}

}
