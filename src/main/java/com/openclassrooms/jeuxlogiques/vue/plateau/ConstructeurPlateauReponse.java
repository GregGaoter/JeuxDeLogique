package com.openclassrooms.jeuxlogiques.vue.plateau;

public class ConstructeurPlateauReponse extends ConstructeurPlateau {

	public ConstructeurPlateauReponse(Plateau plateau) {
		super(plateau);
	}

	public void construireSocle() {
		plateau.setSocle();
	}

	public void construireCombinaison() {
		plateau.setCombinaison();
	}

}
