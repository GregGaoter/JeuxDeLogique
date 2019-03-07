package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueSelectionCombinaison;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;

public class Humain extends Joueur {

	public void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur) {
		DialogueSelectionCombinaison dialogueSelectionCombinaison = new DialogueSelectionCombinaison(fenetreParente,
				modele, controleur);
		HashMap<String, JLabelPion> combinaisonSecrete = dialogueSelectionCombinaison.getValeur();
		if (combinaisonSecrete != null) {
			for (Map.Entry<String, JLabelPion> item : combinaisonSecrete.entrySet())
				this.combinaisonSecrete.add(item.getValue().getPion());
		}
		dialogueSelectionCombinaison.dispose();
	}

	public void setCombinaisonProposition() {

	}

}