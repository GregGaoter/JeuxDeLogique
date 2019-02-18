package com.openclassrooms.jeuxlogiques.modele.enumeration;

public enum PionReponseSymbol implements Pion {

	Egal("/pion_egal.png"), Moins("/pion_moins.png"), Plus("/pion_plus.png");

	private final String nomImage;

	private PionReponseSymbol(String nomImage) {
		this.nomImage = nomImage;
	}

	public String getNomImage() {
		return nomImage;
	}

	public int getValeur() {
		return 0;
	}

}