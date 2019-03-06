package com.openclassrooms.jeuxlogiques.modele.joueur;

import javax.swing.JFrame;

import org.apache.commons.lang3.RandomUtils;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.vue.Vue;

public class Ordinateur extends Joueur {

	public void setCombinaisonSecrete(JFrame fenetreParente, ModeleJeu modele, ControleurJeu controleur) {
		Pion pionAleatoire;
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++) {
			pionAleatoire = modele.getPionsUtilisables().get(RandomUtils.nextInt(0, modele.getNbPionsUtilisables()));
			combinaisonSecrete.add(pionAleatoire);
			modele.setPionSecret(pionAleatoire);
			modele.getPionSecret(i);
		}
		Vue vue = controleur.getVue();
		for (int x = 1; x <= modele.getNbPionsCombinaison(); x++)
			vue.setPion(vue.getListePanneauSecret(), getClef(x, 1), PionCommun.Secret);
		for (int x = 1; x <= modele.getNbPionsUtilisables(); x++)
			vue.setPion(vue.getListePanneauPionUtilisable(), getClef(x, 1), modele.getPionsUtilisables().get(x - 1));
		for (int y = 1; y <= modele.getNbEssais(); y++) {
			for (int x = 1; x <= modele.getNbPionsCombinaison(); x++) {
				vue.setPion(vue.getListePanneauProposition(), getClef(x, y), PionCommun.Vide);
				vue.setPion(vue.getListePanneauReponse(), getClef(x, y), PionCommun.Vide);
			}
		}
		vue.getListePanneauValidation().get(getClef(1, modele.getCompteurEssais())).remove(vue.getBoutonValidation());
		vue.getListePanneauValidation().get(getClef(1, modele.getNbEssais())).add(vue.getBoutonValidation());
		modele.setCompteurEssais(modele.getNbEssais());
		vue.getMessageNbEssais().setText("1 / " + Integer.toString(modele.getNbEssais()));
		vue.getMenuItemOptionJeu().setEnabled(true);
		vue.getBoutonOptionJeu().setEnabled(true);
		if (controleur.getModeDeveloppeurQ())
			controleur.afficherCombinaisonSecrete();
	}

	public void setCombinaisonProposition() {

	}

}