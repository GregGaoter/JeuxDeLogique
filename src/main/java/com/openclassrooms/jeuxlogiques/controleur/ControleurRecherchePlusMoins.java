package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;

public class ControleurRecherchePlusMoins extends ControleurJeu {

	public ControleurRecherchePlusMoins(ModeleJeu modele) {
		super(modele);
	}

	public void configurerJeux() {
		System.out.println("Configurer Recherche +/-");
	}

}
