package com.openclassrooms.jeuxlogiques.modele.enumeration;

public enum PionCouleur implements Pion {

	Rouge("/images/pion_rouge.png", 0), Vert("/images/pion_vert.png", 1), Bleu("/images/pion_bleu.png", 2),
	Jaune("/images/pion_jaune.png", 3), Magenta("/images/pion_magenta.png", 4), Cyan("/images/pion_cyan.png", 5),
	Gris("/images/pion_gris.png", 6), Orange("/images/pion_orange.png", 7), Marron("/images/pion_marron.png", 8),
	Rose("/images/pion_rose.png", 9);

	private final String nomImage;
	private final int valeur;

	private PionCouleur(String nomImage, int valeur) {
		this.nomImage = nomImage;
		this.valeur = valeur;
	}

	public String getNomImage() {
		return nomImage;
	}

	public int getValeur() {
		return valeur;
	}

}