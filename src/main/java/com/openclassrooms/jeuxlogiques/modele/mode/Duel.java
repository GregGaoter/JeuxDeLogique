package com.openclassrooms.jeuxlogiques.modele.mode;

public class Duel extends Mode {

	public String getNom() {
		return "Duel";
	}

	protected void setListeDefenseurs() {
		listeDefenseurs.clear();
		listeDefenseurs.add(ordinateur);
		listeDefenseurs.add(humain);
	}

	protected void setListeAttaquants() {
		listeAttaquants.clear();
		listeAttaquants.add(humain);
		listeAttaquants.add(ordinateur);
	}

}