package com.openclassrooms.jeuxlogiques.modele.enumeration;

public enum PionCommun implements Pion {

	Secret("/images/pion_secret.png"), Transparent("/images/pion_transparent.png"), Vide("/images/pion_vide.png");

	private final String nomImage;

	private PionCommun(String nomImage) {
		this.nomImage = nomImage;
	}

	public String getNomImage() {
		return nomImage;
	}

	public int getValeur() {
		return 0;
	}

}
