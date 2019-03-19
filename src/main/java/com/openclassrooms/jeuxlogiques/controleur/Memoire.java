package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;

public class Memoire {

	private Jeu jeu;

	public Memoire(Jeu jeu) {
		this.jeu = jeu;
	}

	public Jeu getJeu() {
		return jeu;
	}

}