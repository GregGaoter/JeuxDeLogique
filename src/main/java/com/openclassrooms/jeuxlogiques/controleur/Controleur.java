package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.enumeration.Jeu;
import com.openclassrooms.jeuxlogiques.enumeration.Mode;
import com.openclassrooms.jeuxlogiques.modele.Modele;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueMode;

public abstract class Controleur {

	/*
	 * Le contrôleur est lié au modèle et à la vue.
	 */
	protected Modele modele;
	protected Vue vue;

	/*
	 * Le constructeur du contrôleur reçoit le modèle. Il crée ensuite la vue et
	 * initialise le modèle.
	 */
	public Controleur(Modele modele) {
		this.modele = modele;
		vue = new Vue(modele, this);
		// TODO Synchroniser l'affichage entre la fenêtre de démarrage et la fenêtre de
		// jeu
		vue.lancerFenetreDemarrage();
		vue.creerVue();
	}

	/*
	 * Méthodes communes à tous les contrôleurs
	 */
	public void lancerNouveauJeu(DialogueJeu dialogue) {
		Jeu jeu = dialogue.getValeur();
		if (jeu != null) {
			System.out.println("Jeu sélectionné : " + jeu.getNom());

		} else
			System.out.println("Aucun jeu sélectionné");
		dialogue.dispose();
		lancerMode(new DialogueMode(vue.getFenetrePrincipale()));
	}

	private void lancerMode(DialogueMode dialogue) {
		Mode mode = dialogue.getValeur();
		if (mode != null) {
			System.out.println("Mode sélectionné : " + mode);

		} else
			System.out.println("Aucun mode sélectionné");
		dialogue.dispose();
	}

	public void quitterApplication() {
		System.exit(0);
	}

	public void configurerLogs() {
		System.out.println("Configurer les logs");
	}

	public void consulterReglesJeux() {
		System.out.println("Consulter les règles des jeux");
	}

	/*
	 * La classe abstraite du contrôleur définit toutes les méthodes abstraites que
	 * la vue peut appelée sur le contrôleur.
	 */
	public abstract void configurerJeux();

}