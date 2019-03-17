package com.openclassrooms.jeuxlogiques.modele.mode;

public class Defenseur extends Mode {

	public String getNom() {
		return "Défenseur";
	}

	protected void setListeDefenseurs() {
		listeDefenseurs.clear();
		humain.setAttaquantQ(false);
		listeDefenseurs.add(humain);
	}

	protected void setListeAttaquants() {
		listeAttaquants.clear();
		ordinateur.setAttaquantQ(true);
		listeAttaquants.add(ordinateur);
	}

	protected void setLoadCombinaisonsPossiblesQ() {
		loadCombinaisonsPossiblesQ = true;
	}

}