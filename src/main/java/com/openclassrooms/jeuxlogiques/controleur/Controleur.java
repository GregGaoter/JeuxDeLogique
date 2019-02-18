package com.openclassrooms.jeuxlogiques.controleur;

import java.util.HashMap;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.modele.Modele;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Mode;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueMode;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueSelectionCombinaison;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;

public class Controleur {

	private Modele modele;
	private Vue vue;

	private Mode mode;

	public Controleur() {
		modele = new Modele();
		vue = new Vue();
		modele.setVue(vue);
		modele.setControleur(this);
		vue.setModele(modele);
		vue.setControleur(this);
		vue.creerVue();
	}

	public void lancerDialogueJeu(JFrame fenetreProprietaire) {
		DialogueJeu dialogueJeu = new DialogueJeu(fenetreProprietaire);
		Jeu jeu = dialogueJeu.getValeur();
		if (jeu != null) {
			modele.setJeu(jeu);
			lancerMode(fenetreProprietaire);
		}
	}

	private void lancerMode(JFrame fenetreProprietaire) {
		DialogueMode dialogueMode = new DialogueMode(fenetreProprietaire);
		mode = dialogueMode.getValeur();
		if (mode != null) {
			if (mode.getSelectionCombinaisonQ())
				lancerSelectionCombinaison(fenetreProprietaire);
		}
		dialogueMode.dispose();
	}

	private void lancerSelectionCombinaison(JFrame fenetreProprietaire) {
		DialogueSelectionCombinaison dialogueSelectionCombinaison = new DialogueSelectionCombinaison(
				fenetreProprietaire, modele, this);
		HashMap<String, JLabelPion> combinaisonSecrete = dialogueSelectionCombinaison.getValeur();
	}

	public void setPionSelectionne(Pion pion) {
		modele.setPionSelectionne(pion);
	}

	public void getPionSelectionne() {
		modele.getPionSelectionne();
	}

}