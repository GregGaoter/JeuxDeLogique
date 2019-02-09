package com.openclassrooms.jeuxlogiques.vue.plateau;

import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePionInitiale;

public abstract class ConstructeurPlateau {

	protected Plateau plateau;

	public ConstructeurPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void construirePion() {
		plateau.setFabriqueDePion(new FabriqueDePionInitiale());
		plateau.setPion();
	}

	public abstract void construireSocle();

	public abstract void construireCombinaison();

}
