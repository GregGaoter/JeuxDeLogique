package com.openclassrooms.jeuxlogiques.modele.mode;

import com.openclassrooms.jeuxlogiques.modele.joueur.Humain;
import com.openclassrooms.jeuxlogiques.modele.joueur.Ordinateur;

public class Challenger extends Mode {

	public String getNom() {
		return "Challenger";
	}

	protected void setListeDefenseurs() {
		listeDefenseurs.add(new Ordinateur());
	}

	protected void setListeAttaquants() {
		listeAttaquants.add(new Humain());
	}

}