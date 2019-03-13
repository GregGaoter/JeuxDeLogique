package com.openclassrooms.jeuxlogiques.modele.joueur;

import javax.swing.JFrame;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public class Ordinateur extends Joueur {

	private final static Logger log = Logger.getLogger(Ordinateur.class);

	public Ordinateur() {
		nom = "Ordinateur";
	}

	public void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur) {
		Pion pionAleatoire;
		combinaisonSecrete.clear();
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++) {
			pionAleatoire = modele.getPionsUtilisables().get(RandomUtils.nextInt(0, modele.getNbPionsUtilisables()));
			combinaisonSecrete.add(pionAleatoire);
		}
		log.debug("Combinaison secrète de l'ordinateur : " + combinaisonSecrete);
	}

	public void setCombinaisonProposition() {
		Pion pionAleatoire;
		combinaisonProposition.clear();
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++) {
			pionAleatoire = modele.getPionsUtilisables().get(RandomUtils.nextInt(0, modele.getNbPionsUtilisables()));
			combinaisonProposition.add(pionAleatoire);
			setPion(listePanneauProposition, getClef(i, compteurEssais), pionAleatoire);
		}
		log.debug("Combinaison proposition de l'ordinateur : " + combinaisonProposition);
		// modele.setCombinaisonProposition(combinaisonProposition);
		/*
		 * for (int i = 0; i < modele.getNbPionsCombinaison(); i++) {
		 * setPion(listePanneauProposition, getClef(i + 1, modele.getCompteurEssais()),
		 * modele.getCombinaisonProposition().get(i)); } notifierObservateur();
		 */
		// controleur.getVue().getBoutonValidation().doClick();
		/*
		 * try { Thread.sleep(2000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
		/*
		 * new Thread(new Runnable() { public void run() { // notifierObservateur();
		 * controleur.getVue().getBoutonValidation().doClick(); } }).start();
		 */

	}

}