package com.openclassrooms.jeuxlogiques.vue.plateau;

public class ConstructeurPlateauSolution extends ConstructeurPlateau {

	public ConstructeurPlateauSolution(Plateau plateau) {
		super(plateau);
	}

	public void construireSocle() {
		plateau.setSocle();
	}

	public void construireCombinaison() {
		plateau.setCombinaison();
	}

}
