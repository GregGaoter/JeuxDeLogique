package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueSelectionCombinaison;

public class Humain extends Joueur {

	private final static Logger log = Logger.getLogger(Humain.class);

	private DialogueSelectionCombinaison dialogueSelectionCombinaison;
	private Pion pionSecret;

	public Humain() {
		log.info("Construction du joueur Humain.");
		nom = "Joueur";
		humainQ = true;
		pionSecret = PionCommun.Vide;
	}

	public void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur) {
		log.info("Lancement du choix de la combinaison secrète de " + nom + ".");
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
		dialogueSelectionCombinaison.getCombinaisonSecrete().set(x - 1, pionSecret);
		dialogueSelectionCombinaison.actualiser();
		return pionSecret;
	}

	public void setPionSecret(Pion pionSecret) {
		this.pionSecret = pionSecret;
	}

}