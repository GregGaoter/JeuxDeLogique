package com.openclassrooms.jeuxlogiques.vue.pion;

import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.enumeration.Apparence;

public class FabriqueDePionRecherchePlusMoins extends FabriqueDePion {

	public JPanel creerPionSecret() {
		return new PionSecret();
	}

	public JPanel creerPionVide() {
		return new PionVide();
	}

	public JPanel creerPionJeu(Apparence apparence) {
		return new PionJeuChiffre(apparence);
	}

	public JPanel creerPionReponse(Apparence apparence) {
		return new PionReponseSymbole(apparence);
	}

	public JPanel creerPionTransparent() {
		return new PionTransparent();
	}

}
