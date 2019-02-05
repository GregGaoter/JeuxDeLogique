package com.openclassrooms.jeuxlogiques.vue.pion;

import javax.swing.JPanel;

public class FabriqueDePionInitiale extends FabriqueDePion {

	public JPanel creerPionSecret() {
		return new PionVide();
	}

	public JPanel creerPionVide() {
		return new PionVide();
	}

	public JPanel creerPionJeu() {
		return new PionVide();
	}

	public JPanel creerPionReponse() {
		return new PionVide();
	}

	public JPanel creerPionTransparent() {
		return new PionTransparent();
	}

}
