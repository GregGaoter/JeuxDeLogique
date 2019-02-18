package com.openclassrooms.jeuxlogiques.modele.enumeration;

public enum PionChiffre implements Pion {

	Zero("/pion_0.png", 0), Un("/pion_1.png", 1), Deux("/pion_2.png", 2), Trois("/pion_3.png", 3),
	Quatre("/pion_4.png", 4), Cinq("/pion_5.png", 5), Six("/pion_6.png", 6), Sept("/pion_7.png", 7),
	Huit("/pion_8.png", 8), Neuf("/pion_9.png", 9);

	private final String nomImage;
	private final int valeur;

	private PionChiffre(String nomImage, int valeur) {
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