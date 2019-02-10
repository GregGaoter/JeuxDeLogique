package com.openclassrooms.jeuxlogiques.modele;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.Observateur;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public abstract class Modele {

	public static final int NB_PIONS_COMBINAISON_MIN = 4;
	public static final int NB_PIONS_COMBINAISON_MAX = 6;

	protected LinkedList<Observateur> observateurs;
	protected int nbPionsUtilisables;
	protected int nbPionsCombinaison;
	protected int nbEssais;
	protected int compteurEssais;
	protected ArrayList<JPanel> solution;// Liste contenant les informations de la combinaison solution
	protected ArrayList<JPanel> proposition;// Liste contenant les informations de la combinaison proposée
	protected ArrayList<JPanel> reponse;// Liste contenant les informations de la combinaison réponse
	protected ArrayList<JPanel> pionsUtilisables;// Liste contenant les informations des différents pions utilisables
													// pour
													// les
													// combinaisons
	protected ArrayList<JPanel> validation;

	public Modele() {
		observateurs = new LinkedList<>();
		nbPionsUtilisables = 10;
		nbPionsCombinaison = 4;
		nbEssais = 8;
		compteurEssais = 0;
		solution = new ArrayList<>();
		proposition = new ArrayList<>();
		reponse = new ArrayList<>();
		pionsUtilisables = new ArrayList<>();
		validation = new ArrayList<>();
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

	public void initialiser(FabriqueDePion fabriqueDePion) {
		setSolution(fabriqueDePion);
		setProposition(fabriqueDePion);
		setReponse(fabriqueDePion);
		setPionsUtilisables(fabriqueDePion);
		setValidation(fabriqueDePion);
	}

	private void setSolution(FabriqueDePion fabriqueDePion) {
		for (int i = 0; i < nbPionsCombinaison; i++)
			solution.add(fabriqueDePion.creerPionSecret());
	}

	private void setProposition(FabriqueDePion fabriqueDePion) {
		for (int i = 0; i < nbPionsCombinaison; i++)
			proposition.add(fabriqueDePion.creerPionVide());
	}

	private void setReponse(FabriqueDePion fabriqueDePion) {
		for (int i = 0; i < nbPionsCombinaison; i++)
			proposition.add(fabriqueDePion.creerPionVide());
	}

	private void setValidation(FabriqueDePion fabriqueDePion) {
		validation.add(fabriqueDePion.creerPionTransparent());
	}

	public ArrayList<JPanel> getSolution() {
		return solution;
	}

	public ArrayList<JPanel> getProposition() {
		return proposition;
	}

	public ArrayList<JPanel> getReponse() {
		return reponse;
	}

	public ArrayList<JPanel> getPionsUtilisables() {
		return pionsUtilisables;
	}

	public ArrayList<JPanel> getValidation() {
		return validation;
	}

	protected abstract void setPionsUtilisables(FabriqueDePion fabriqueDePion);

}
