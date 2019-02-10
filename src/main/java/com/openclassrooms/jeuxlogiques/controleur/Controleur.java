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
	 * Le contr�leur est li� au mod�le et � la vue.
	 */
	protected Modele modele;
	protected Vue vue;

	protected Controleur controleur;

	protected Mode mode;
	protected FabriqueDePion fabriqueDePion;
	protected ArrayList<?> solution;

	/*
	 * M�thodes communes � tous les contr�leurs
	 */
	public void lancerNouveauJeu(DialogueJeu dialogue) {
		Jeu jeu = dialogue.getValeur();
		if (jeu != null) {
			modele = jeu.getModele();
			modele.initialiser(fabriqueDePion);
			controleur = jeu.getControleur();
			fabriqueDePion = jeu.getFabriqueDePion();
			System.out.println("Jeu s�lectionn� : " + jeu.getNom());
			System.out.println("Mod�le : " + modele.getClass().getSimpleName());
			System.out.println("Contr�leur : " + controleur.getClass().getSimpleName());
			System.out.println("Fabrique de pions : " + fabriqueDePion.getClass().getSimpleName());
			lancerMode(new DialogueMode(vue.getFenetrePrincipale()));
		} else
			System.out.println("Aucun jeu s�lectionn�");
		dialogue.dispose();
	}

	private void lancerMode(DialogueMode dialogue) {
		Mode mode = dialogue.getValeur();
		if (mode != null) {
			System.out.println("Mode s�lectionn� : " + mode);
			if (mode.getCombinaisonQ())
				lancerCombinaison(new DialogueCombinaison(vue.getFenetrePrincipale(), modele));
		} else
			System.out.println("Aucun mode s�lectionn�");
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
		System.out.println("Consulter les r�gles des jeux");
	}

	/*
	 * La classe abstraite du contr�leur d�finit toutes les m�thodes abstraites que
	 * la vue peut appel�e sur le contr�leur.
	 */
	public abstract void configurerJeux();

}