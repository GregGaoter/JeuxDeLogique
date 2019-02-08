package com.openclassrooms.jeuxlogiques.vue.plateau;

public abstract class ConstructeurPlateau {

	protected Plateau plateau;

	public ConstructeurPlateau() {
		plateau = new Plateau();
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public abstract void construireSocle();

	public abstract void construireCombinaison();

	public abstract void construirePion();

}
