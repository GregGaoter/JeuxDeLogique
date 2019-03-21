package com.openclassrooms.jeuxlogiques.modele.mode;

import org.apache.log4j.Logger;

/**
 * <b>Challenger est une classe impl�mentant des m�thodes caract�risant le mode
 * Challenger.</b></br>
 * La classe Challenger �tend la classe abstraite Mode.
 * 
 * @author Gr�gory Gautier
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
	 * D�finit la liste des d�fenseurs du mode.
	 */
	protected void setListeDefenseurs() {
		log.info("Cr�ation de la liste des d�fenseurs du mode " + getNom() + ".");
		listeDefenseurs.clear();
		ordinateur.setAttaquantQ(false);
		listeDefenseurs.add(ordinateur);
	}

	/**
	 * D�finit la liste des attaquants du mode.
	 */
	protected void setListeAttaquants() {
		log.info("Cr�ation de la liste des attaquants du mode " + getNom() + ".");
		listeAttaquants.clear();
		humain.setAttaquantQ(true);
		listeAttaquants.add(humain);
	}

	/**
	 * D�finit l'�tat de chargement de la liste de toutes les combinaisons
	 * possibles.
	 */
	protected void setLoadCombinaisonsPossiblesQ() {
		loadCombinaisonsPossiblesQ = false;
	}

}