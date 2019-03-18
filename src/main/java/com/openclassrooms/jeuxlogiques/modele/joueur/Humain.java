package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueSelectionCombinaison;

public class Humain extends Joueur {

	private DialogueSelectionCombinaison dialogueSelectionCombinaison;
	private Pion pionSecret;

	public Humain() {
		nom = "Joueur";
		humainQ = true;
		pionSecret = PionCommun.Vide;
	}

	public void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur) {
		dialogueSelectionCombinaison = new DialogueSelectionCombinaison(fenetreParente, modele, controleur);
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

	protected void setListeCombinaisonsPossibles() {
	}

	public Pion getPionSecret(int x) {
		// combinaisonSecrete.set(x - 1, pionSecret);
		dialogueSelectionCombinaison.getCombinaisonSecrete().set(x - 1, pionSecret);
		dialogueSelectionCombinaison.actualiser();
		return pionSecret;
	}

	public void setPionSecret(Pion pionSecret) {
		this.pionSecret = pionSecret;
	}

}