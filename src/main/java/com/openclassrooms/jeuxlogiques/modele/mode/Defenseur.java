package com.openclassrooms.jeuxlogiques.modele.mode;

import org.apache.log4j.Logger;

public class Defenseur extends Mode {

	private final static Logger log = Logger.getLogger(Defenseur.class);

	public String getNom() {
		return "Défenseur";
	}

	protected void setListeDefenseurs() {
		log.info("Création de la liste des défenseurs du mode " + getNom() + ".");
		listeDefenseurs.clear();
		humain.setAttaquantQ(false);
		listeDefenseurs.add(humain);
	}

	protected void setListeAttaquants() {
		log.info("Création de la liste des attaquants du mode " + getNom() + ".");
		listeAttaquants.clear();
		ordinateur.setAttaquantQ(true);
		listeAttaquants.add(ordinateur);
	}

	protected void setLoadCombinaisonsPossiblesQ() {
		loadCombinaisonsPossiblesQ = true;
	}

}