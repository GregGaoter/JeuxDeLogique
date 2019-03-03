package com.openclassrooms.jeuxlogiques.controleur.joueur;

import java.util.HashMap;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueSelectionCombinaison;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;

public class Humain extends Joueur {

	public Humain(ModeleJeu modele, ControleurJeu controleur, HashMap<String, JLabelPion> listePanneauSecret) {
		super(modele, controleur, listePanneauSecret);
	}

	public HashMap<String, JLabelPion> getCombinaisonSecrete(JFrame fenetreParente, ModeleJeu modele,
			ControleurJeu controleur) {
		DialogueSelectionCombinaison dialogueSelectionCombinaison = new DialogueSelectionCombinaison(fenetreParente,
				modele, controleur);
		return dialogueSelectionCombinaison.getValeur();
	}

}