package com.openclassrooms.jeuxlogiques.enumeration;

public enum Jeu {

	RecherchePlusMoins("Recherche +/-"), Mastermind("Mastermind");

	private String nom;

	private Jeu(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

}