package com.openclassrooms.jeuxlogiques.modele.mode;

public class Challenger extends Mode {

	public String getNom() {
		return "Challenger";
	}

	protected void setListeDefenseurs() {
		listeDefenseurs.clear();
		ordinateur.setAttaquantQ(false);
		listeDefenseurs.add(ordinateur);
	}

	protected void setListeAttaquants() {
		listeAttaquants.clear();
		humain.setAttaquantQ(true);
		listeAttaquants.add(humain);
	}

	protected void setLoadCombinaisonsPossiblesQ() {
		loadCombinaisonsPossiblesQ = false;
	}

}