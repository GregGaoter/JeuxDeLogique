package com.openclassrooms.jeuxlogiques.vue.plateau;

public class ConstructeurPlateauProposition extends ConstructeurPlateau {

	public ConstructeurPlateauProposition(Plateau plateau) {
		super(plateau);
	}

	public void construireSocle() {
		plateau.setSocle();
	}

	public void construireCombinaison() {
		plateau.setCombinaison();
	}

}
