package com.openclassrooms.jeuxlogiques.modele.joueur;

import javax.swing.JFrame;

import org.apache.commons.lang3.RandomUtils;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public class Ordinateur extends Joueur {

	public void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur) {
		Pion pionAleatoire;
		combinaisonSecrete.clear();
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++) {
			pionAleatoire = modele.getPionsUtilisables().get(RandomUtils.nextInt(0, modele.getNbPionsUtilisables()));
			combinaisonSecrete.add(pionAleatoire);
			// modele.setPionSecret(pionAleatoire);
			// modele.getPionSecret(i);
		}
	}

	public void setCombinaisonProposition() {
		Pion pionAleatoire;
		combinaisonProposition.clear();
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++) {
			pionAleatoire = modele.getPionsUtilisables().get(RandomUtils.nextInt(0, modele.getNbPionsUtilisables()));
			combinaisonProposition.add(pionAleatoire);
		}
		modele.setCombinaisonProposition(combinaisonProposition);
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++) {
			setPion(listePanneauProposition, getClef(i + 1, modele.getCompteurEssais()),
					modele.getCombinaisonProposition().get(i));
		}
		notifierObservateur();
		/*
		 * new Thread(new Runnable() { public void run() { notifierObservateur(); //
		 * controleur.getVue().getBoutonValidation().doClick(); } }).start();
		 */

	}

}