package com.openclassrooms.jeuxlogiques.modele.mode;

import com.openclassrooms.jeuxlogiques.modele.joueur.Humain;
import com.openclassrooms.jeuxlogiques.modele.joueur.Ordinateur;

public class Duel extends Mode {

	public String getNom() {
		return "Duel";
	}

	protected void setListeDefenseurs() {
		listeDefenseurs.clear();
		listeDefenseurs.add(new Humain());
		listeDefenseurs.add(new Ordinateur());
	}

	protected void setListeAttaquants() {
		listeAttaquants.clear();
		listeAttaquants.add(new Humain());
		listeAttaquants.add(new Ordinateur());
	}

}