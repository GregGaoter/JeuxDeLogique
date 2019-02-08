package com.openclassrooms.jeuxlogiques.vue.plateau;

public class DirecteurConstructionPlateau {

	private ConstructeurPlateau constructeurPlateau;

	public DirecteurConstructionPlateau(ConstructeurPlateau constructeurPlateau) {
		this.constructeurPlateau = constructeurPlateau;
	}

	public Plateau getPlateau() {
		return constructeurPlateau.getPlateau();
	}

	public void construirePlateau() {
		constructeurPlateau.construireSocle();
		constructeurPlateau.construireCombinaison();
		constructeurPlateau.construirePion();
	}

}
