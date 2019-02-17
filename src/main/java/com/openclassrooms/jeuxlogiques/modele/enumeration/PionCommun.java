package com.openclassrooms.jeuxlogiques.modele.enumeration;

public enum PionCommun implements Pion {

	Secret("/pion_secret.png"), Transparent("/pion_transparent.png"), Vide("/pion_vide.png");

	private final String nomImage;

	private PionCommun(String nomImage) {
		this.nomImage = nomImage;
	}

	public String getNomImage() {
		return nomImage;
	}

}
