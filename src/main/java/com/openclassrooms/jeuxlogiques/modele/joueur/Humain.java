package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueSelectionCombinaison;

public class Humain extends Joueur {

	public void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur) {
		DialogueSelectionCombinaison dialogueSelectionCombinaison = new DialogueSelectionCombinaison(fenetreParente,
				modele, controleur);
		List<Pion> combinaisonSecreteDialogue = new ArrayList<>(modele.getNbPionsCombinaison());
		combinaisonSecreteDialogue = dialogueSelectionCombinaison.getValeur();
		if (combinaisonSecreteDialogue != null) {
			combinaisonSecrete.clear();
			combinaisonSecrete.addAll(combinaisonSecreteDialogue);
		}
		dialogueSelectionCombinaison.dispose();
	}

	public void setCombinaisonProposition() {

	}

}