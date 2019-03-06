package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueSelectionCombinaison;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;

public class Humain extends Joueur {

	public void setCombinaisonSecrete(JFrame fenetreParente, ModeleJeu modele, ControleurJeu controleur) {
		DialogueSelectionCombinaison dialogueSelectionCombinaison = new DialogueSelectionCombinaison(fenetreParente,
				modele, controleur);
		HashMap<String, JLabelPion> combinaisonSecrete = dialogueSelectionCombinaison.getValeur();
		if (combinaisonSecrete != null) {
			for (Map.Entry<String, JLabelPion> item : combinaisonSecrete.entrySet())
				this.combinaisonSecrete.add(item.getValue().getPion());
			Vue vue = controleur.getVue();
			for (int x = 1; x <= modele.getNbPionsCombinaison(); x++)
				vue.setPion(vue.getListePanneauSecret(), getClef(x, 1), PionCommun.Secret);
			for (int x = 1; x <= modele.getNbPionsUtilisables(); x++)
				vue.setPion(vue.getListePanneauPionUtilisable(), getClef(x, 1),
						modele.getPionsUtilisables().get(x - 1));
			for (int y = 1; y <= modele.getNbEssais(); y++) {
				for (int x = 1; x <= modele.getNbPionsCombinaison(); x++) {
					vue.setPion(vue.getListePanneauProposition(), getClef(x, y), PionCommun.Vide);
					vue.setPion(vue.getListePanneauReponse(), getClef(x, y), PionCommun.Vide);
				}
			}
			vue.getListePanneauValidation().get(getClef(1, modele.getCompteurEssais()))
					.remove(vue.getBoutonValidation());
			vue.getListePanneauValidation().get(getClef(1, modele.getNbEssais())).add(vue.getBoutonValidation());
			modele.setCompteurEssais(modele.getNbEssais());
			vue.getMessageNbEssais().setText("1 / " + Integer.toString(modele.getNbEssais()));
			vue.getMenuItemOptionJeu().setEnabled(true);
			vue.getBoutonOptionJeu().setEnabled(true);
			if (controleur.getModeDeveloppeurQ())
				controleur.afficherCombinaisonSecrete();
		}
		dialogueSelectionCombinaison.dispose();
	}

	public void setCombinaisonProposition() {

	}

}