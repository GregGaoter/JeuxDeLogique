package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;

public class ControleurInitial extends ControleurJeu {

	public ControleurInitial(ModeleJeu modele) {
		super(modele);
	}

	public void configurerJeux() {
		System.out.println("Configurer les jeux");
	}

}
