package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.enumeration.Jeu;
import com.openclassrooms.jeuxlogiques.enumeration.Mode;
import com.openclassrooms.jeuxlogiques.modele.Modele;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueMode;

public abstract class Controleur {

	/*
	 * Le contr�leur est li� au mod�le et � la vue.
	 */
	protected Modele modele;
	protected Vue vue;

	/*
	 * Le constructeur du contr�leur re�oit le mod�le. Il cr�e ensuite la vue et
	 * initialise le mod�le.
	 */
	public Controleur(Modele modele) {
		this.modele = modele;
		vue = new Vue(modele, this);
		// TODO Synchroniser l'affichage entre la fen�tre de d�marrage et la fen�tre de
		// jeu
		vue.lancerFenetreDemarrage();
		vue.creerVue();
	}

	/*
	 * M�thodes communes � tous les contr�leurs
	 */
	public void lancerNouveauJeu(DialogueJeu dialogue) {
		Jeu jeu = dialogue.getValeur();
		if (jeu != null) {
			System.out.println("Jeu s�lectionn� : " + jeu.getNom());

		} else
			System.out.println("Aucun jeu s�lectionn�");
		dialogue.dispose();
		lancerMode(new DialogueMode(vue.getFenetrePrincipale()));
	}

	private void lancerMode(DialogueMode dialogue) {
		Mode mode = dialogue.getValeur();
		if (mode != null) {
			System.out.println("Mode s�lectionn� : " + mode);

		} else
			System.out.println("Aucun mode s�lectionn�");
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