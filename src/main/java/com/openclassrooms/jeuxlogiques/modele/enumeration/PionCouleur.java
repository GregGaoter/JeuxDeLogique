package com.openclassrooms.jeuxlogiques.modele.enumeration;

public enum PionCouleur implements Pion {

	Rouge("/pion_rouge.png", 0), Vert("/pion_vert.png", 1), Bleu("/pion_bleu.png", 2), Jaune("/pion_jaune.png", 3),
	Magenta("/pion_magenta.png", 4), Cyan("/pion_cyan.png", 5), Gris("/pion_gris.png", 6),
	Orange("/pion_orange.png", 7), Marron("/pion_marron.png", 8), Rose("/pion_rose.png", 9);

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