package com.openclassrooms.jeuxlogiques.controleur;

import java.util.ArrayList;

import com.openclassrooms.jeuxlogiques.enumeration.Jeu;
import com.openclassrooms.jeuxlogiques.enumeration.Mode;
import com.openclassrooms.jeuxlogiques.modele.Modele;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueCombinaison;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueMode;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public abstract class Controleur {

	/*
	 * Le contrôleur est lié au modèle et à la vue.
	 */
	protected Modele modele;
	protected Vue vue;

	protected Controleur controleur;

	protected Mode mode;
	protected FabriqueDePion fabriqueDePion;
	protected ArrayList<?> solution;

	/*
	 * Méthodes communes à tous les contrôleurs
	 */
	public void lancerNouveauJeu(DialogueJeu dialogue) {
		Jeu jeu = dialogue.getValeur();
		if (jeu != null) {
			modele = jeu.getModele();
			modele.initialiser(fabriqueDePion);
			controleur = jeu.getControleur();
			fabriqueDePion = jeu.getFabriqueDePion();
			System.out.println("Jeu sélectionné : " + jeu.getNom());
			System.out.println("Modèle : " + modele.getClass().getSimpleName());
			System.out.println("Contrôleur : " + controleur.getClass().getSimpleName());
			System.out.println("Fabrique de pions : " + fabriqueDePion.getClass().getSimpleName());
			lancerMode(new DialogueMode(vue.getFenetrePrincipale()));
		} else
			System.out.println("Aucun jeu sélectionné");
		dialogue.dispose();
	}

	private void lancerMode(DialogueMode dialogue) {
		Mode mode = dialogue.getValeur();
		if (mode != null) {
			System.out.println("Mode sélectionné : " + mode);
			if (mode.getCombinaisonQ())
				lancerCombinaison(new DialogueCombinaison(vue.getFenetrePrincipale(), modele));
		} else
			System.out.println("Aucun mode sélectionné");
		dialogue.dispose();
	}

	private void lancerCombinaison(DialogueCombinaison dialogue) {
		ArrayList<?> solution = dialogue.getValeur();
		if (solution != null) {
			System.out.println("Solution : " + solution);
		} else
			System.out.println("Aucune solution choisit");
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