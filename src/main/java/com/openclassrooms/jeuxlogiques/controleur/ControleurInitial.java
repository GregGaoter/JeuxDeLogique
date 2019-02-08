package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.modele.Modele;

public class ControleurInitial extends Controleur {

	public ControleurInitial(Modele modele) {
		super(modele);
	}

	public void configurerJeux() {
		System.out.println("Configurer les jeux");
	}

}
