package com.openclassrooms.jeuxlogiques.modele.mode;

import org.apache.log4j.Logger;

/**
 * <b>Defenseur est une classe implémentant des méthodes caractérisant le mode
 * Defenseur.</b></br>
 * La classe Defenseur étend la classe abstraite Mode.
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public class Defenseur extends Mode {

	private final static Logger log = Logger.getLogger(Defenseur.class);

	/**
	 * Donne le nom du mode.
	 * 
	 * @return le nom du mode
	 */
	public String getNom() {
		return "Défenseur";
	}

	/**
	 * Définit la liste des défenseurs du mode.
	 */
	protected void setListeDefenseurs() {
		log.info("Création de la liste des défenseurs du mode " + getNom() + ".");
		listeDefenseurs.clear();
		humain.setAttaquantQ(false);
		listeDefenseurs.add(humain);
	}

	/**
	 * Définit la liste des attaquants du mode.
	 */
	protected void setListeAttaquants() {
		log.info("Création de la liste des attaquants du mode " + getNom() + ".");
		listeAttaquants.clear();
		ordinateur.setAttaquantQ(true);
		listeAttaquants.add(ordinateur);
	}

	/**
	 * Définit l'état de chargement de la liste de toutes les combinaisons
	 * possibles.
	 */
	protected void setLoadCombinaisonsPossiblesQ() {
		loadCombinaisonsPossiblesQ = true;
	}

}