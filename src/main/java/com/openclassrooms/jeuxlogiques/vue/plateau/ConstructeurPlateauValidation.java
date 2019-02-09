package com.openclassrooms.jeuxlogiques.vue.plateau;

public class ConstructeurPlateauValidation extends ConstructeurPlateau {

	public ConstructeurPlateauValidation(Plateau plateau) {
		super(plateau);
	}

	public void construireSocle() {
		plateau.setSocle();
	}

	public void construireCombinaison() {
		plateau.setCombinaison();
	}

}
