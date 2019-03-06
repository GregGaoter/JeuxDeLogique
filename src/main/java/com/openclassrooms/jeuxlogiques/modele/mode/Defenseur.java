package com.openclassrooms.jeuxlogiques.modele.mode;

import com.openclassrooms.jeuxlogiques.modele.joueur.Humain;
import com.openclassrooms.jeuxlogiques.modele.joueur.Ordinateur;

public class Defenseur extends Mode {

	public String getNom() {
		return "Défenseur";
	}

	protected void setListeDefenseurs() {
		listeDefenseurs.add(new Humain());
	}

	protected void setListeAttaquants() {
		listeAttaquants.add(new Ordinateur());
	}

}