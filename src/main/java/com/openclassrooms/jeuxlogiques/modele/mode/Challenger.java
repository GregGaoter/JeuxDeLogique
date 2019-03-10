package com.openclassrooms.jeuxlogiques.modele.mode;

import com.openclassrooms.jeuxlogiques.modele.joueur.Humain;
import com.openclassrooms.jeuxlogiques.modele.joueur.Ordinateur;

public class Challenger extends Mode {

	public String getNom() {
		return "Challenger";
	}

	protected void setListeDefenseurs() {
		listeDefenseurs.clear();
		listeDefenseurs.add(new Ordinateur());
	}

	protected void setListeAttaquants() {
		listeAttaquants.clear();
		listeAttaquants.add(new Humain());
	}

}