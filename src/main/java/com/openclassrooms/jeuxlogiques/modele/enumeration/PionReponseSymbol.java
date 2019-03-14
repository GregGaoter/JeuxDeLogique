package com.openclassrooms.jeuxlogiques.modele.enumeration;

public enum PionReponseSymbol implements Pion {

	Egal("/images/pion_egal.png", 0), Moins("/images/pion_moins.png", -1), Plus("/images/pion_plus.png", 1);

	private final String nomImage;
	private final int valeur;

	private PionReponseSymbol(String nomImage, int valeur) {
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