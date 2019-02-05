package com.openclassrooms.jeuxlogiques.vue.pion;

import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.enumeration.Apparence;

public class FabriqueDePionMastermind extends FabriqueDePion {

	public JPanel creerPionSecret() {
		return new PionSecret();
	}

	public JPanel creerPionVide() {
		return new PionVide();
	}

	public JPanel creerPionJeu(Apparence apparence) {
		return new PionJeuCouleur();
	}

	public JPanel creerPionReponse(Apparence apparence) {
		return new PionReponseCouleur();
	}

	public JPanel creerPionTransparent() {
		return new PionTransparent();
	}

}
