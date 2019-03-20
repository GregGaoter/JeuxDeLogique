package com.openclassrooms.jeuxlogiques.modele.mode;

import org.apache.log4j.Logger;

public class Challenger extends Mode {

	private final static Logger log = Logger.getLogger(Challenger.class);

	public String getNom() {
		return "Challenger";
	}

	protected void setListeDefenseurs() {
		log.info("Création de la liste des défenseurs du mode " + getNom() + ".");
		listeDefenseurs.clear();
		ordinateur.setAttaquantQ(false);
		listeDefenseurs.add(ordinateur);
	}

	protected void setListeAttaquants() {
		log.info("Création de la liste des attaquants du mode " + getNom() + ".");
		listeAttaquants.clear();
		humain.setAttaquantQ(true);
		listeAttaquants.add(humain);
	}

	protected void setLoadCombinaisonsPossiblesQ() {
		loadCombinaisonsPossiblesQ = false;
	}

}