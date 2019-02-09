package com.openclassrooms.jeuxlogiques.vue.plateau;

public class ConstructeurPlateauPion extends ConstructeurPlateau {

	public ConstructeurPlateauPion(Plateau plateau) {
		super(plateau);
	}

	public void construireSocle() {
		plateau.setSocle();
	}

	public void construireCombinaison() {
		plateau.setCombinaison();
	}

}
