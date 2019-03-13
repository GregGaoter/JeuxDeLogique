package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueSelectionCombinaison;

public class Humain extends Joueur {

	private final static Logger log = Logger.getLogger(Humain.class);

	public Humain() {
		nom = "Joueur";
	}

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
		log.debug("Combinaison secrète de l'Humain : " + combinaisonSecrete);
	}

	public void setCombinaisonProposition() {
	}

}