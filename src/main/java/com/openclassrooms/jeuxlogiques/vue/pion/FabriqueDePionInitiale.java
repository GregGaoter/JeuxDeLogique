package com.openclassrooms.jeuxlogiques.vue.pion;

import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.enumeration.Apparence;

public class FabriqueDePionInitiale extends FabriqueDePion {

	public JPanel creerPionSecret() {
		return new PionVide();
	}

	public JPanel creerPionVide() {
		return new PionVide();
	}

	public JPanel creerPionJeu(Apparence apparence) {
		return new PionVide();
	}

	public JPanel creerPionReponse(Apparence apparence) {
		return new PionVide();
	}

	public JPanel creerPionTransparent() {
		return new PionTransparent();
	}

}
