package com.openclassrooms.jeuxlogiques.modele.enumeration;

public enum PionCouleur implements Pion {

	Bleu("/pion_bleu.png", 0), Cyan("/pion_cyan.png", 1), Gris("/pion_gris.png", 2), Jaune("/pion_jaune.png", 3),
	Magenta("/pion_magenta.png", 4), Marron("/pion_marron.png", 5), Orange("/pion_orange.png", 6),
	Rose("/pion_rose.png", 7), Rouge("/pion_rouge.png", 8), Vert("/pion_bleu.png", 9);

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