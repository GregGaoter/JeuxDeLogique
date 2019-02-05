package com.openclassrooms.jeuxlogiques.enumeration;

public enum Chiffre implements Apparence {

	Zero(0), Un(1), Deux(2), Trois(3), Quatre(4), Cinq(5), Six(6), Sept(7), Huit(8), Neuf(9);

	private final int chiffre;

	private Chiffre(int chiffre) {
		this.chiffre = chiffre;
	}

	public int getChiffre() {
		return chiffre;
	}

}