package com.openclassrooms.jeuxlogiques.vue.plateau;

import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePionRecherchePlusMoins;

public class ConstructeurPlateauSolution extends ConstructeurPlateau {

	public void construireSocle() {
		plateau.setSocle(1, "Solution");
	}

	public void construireCombinaison() {
		plateau.setCombinaison(4);
	}

	public void construirePion() {
		plateau.setFabriqueDePion(new FabriqueDePionRecherchePlusMoins());
		plateau.setPion(plateau.getFabriqueDePion().creerPionSecret());
	}

}
