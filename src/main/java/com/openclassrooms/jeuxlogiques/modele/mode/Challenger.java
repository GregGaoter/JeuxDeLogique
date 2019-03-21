package com.openclassrooms.jeuxlogiques.modele.mode;

import org.apache.log4j.Logger;

/**
 * <b>Challenger est une classe implémentant des méthodes caractérisant le mode
 * Challenger.</b></br>
 * La classe Challenger étend la classe abstraite Mode.
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public class Challenger extends Mode {

	private final static Logger log = Logger.getLogger(Challenger.class);

	/**
	 * Donne le nom du mode.
	 * 
	 * @return le nom du mode
	 */
	public String getNom() {
		return "Challenger";
	}

	/**
	 * Définit la liste des défenseurs du mode.
	 */
	protected void setListeDefenseurs() {
		log.info("Création de la liste des défenseurs du mode " + getNom() + ".");
		listeDefenseurs.clear();
		ordinateur.setAttaquantQ(false);
		listeDefenseurs.add(ordinateur);
	}

	/**
	 * Définit la liste des attaquants du mode.
	 */
	protected void setListeAttaquants() {
		log.info("Création de la liste des attaquants du mode " + getNom() + ".");
		listeAttaquants.clear();
		humain.setAttaquantQ(true);
		listeAttaquants.add(humain);
	}

	/**
	 * Définit l'état de chargement de la liste de toutes les combinaisons
	 * possibles.
	 */
	protected void setLoadCombinaisonsPossiblesQ() {
		loadCombinaisonsPossiblesQ = false;
	}

}