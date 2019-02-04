package com.openclassrooms.jeuxlogiques.enumeration;

import java.awt.Color;

public enum Reponse {

	Moins("-", new Color(255, 192, 192, 255)), Plus("+", new Color(255, 192, 192, 255)),
	Egal("=", new Color(192, 255, 192, 255));

	private final String reponse;
	private final Color couleur;

	private Reponse(String reponse, Color couleur) {
		this.reponse = reponse;
		this.couleur = couleur;
	}

	public String getReponse() {
		return reponse;
	}

	public Color getCouleur() {
		return couleur;
	}

}
