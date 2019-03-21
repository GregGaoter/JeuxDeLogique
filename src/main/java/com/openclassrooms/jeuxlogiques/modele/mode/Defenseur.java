package com.openclassrooms.jeuxlogiques.modele.mode;

import org.apache.log4j.Logger;

/**
 * <b>Defenseur est une classe impl�mentant des m�thodes caract�risant le mode
 * Defenseur.</b></br>
 * La classe Defenseur �tend la classe abstraite Mode.
 * 
 * @author Gr�gory Gautier
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
		return "D�fenseur";
	}

	/**
	 * D�finit la liste des d�fenseurs du mode.
	 */
	protected void setListeDefenseurs() {
		log.info("Cr�ation de la liste des d�fenseurs du mode " + getNom() + ".");
		listeDefenseurs.clear();
		humain.setAttaquantQ(false);
		listeDefenseurs.add(humain);
	}

	/**
	 * D�finit la liste des attaquants du mode.
	 */
	protected void setListeAttaquants() {
		log.info("Cr�ation de la liste des attaquants du mode " + getNom() + ".");
		listeAttaquants.clear();
		ordinateur.setAttaquantQ(true);
		listeAttaquants.add(ordinateur);
	}

	/**
	 * D�finit l'�tat de chargement de la liste de toutes les combinaisons
	 * possibles.
	 */
	protected void setLoadCombinaisonsPossiblesQ() {
		loadCombinaisonsPossiblesQ = true;
	}

}