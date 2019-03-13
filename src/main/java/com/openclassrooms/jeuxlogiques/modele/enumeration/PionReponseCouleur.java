package com.openclassrooms.jeuxlogiques.modele.enumeration;

public enum PionReponseCouleur implements Pion {

	Blanc("/images/pion_blanc.png"), Noir("/images/pion_noir.png");

	private final String nomImage;

	private PionReponseCouleur(String nomImage) {
		this.nomImage = nomImage;
	}

	public String getNomImage() {
		return nomImage;
	}

	public int getValeur() {
		return 0;
	}

}