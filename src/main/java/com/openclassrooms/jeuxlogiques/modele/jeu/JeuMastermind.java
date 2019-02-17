package com.openclassrooms.jeuxlogiques.modele.jeu;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCouleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseCouleur;

public class JeuMastermind extends Jeu {

	private PionCouleur pionJeu;
	private PionReponseCouleur pionReponse;

	public Pion getPionJeu() {
		return pionJeu;
	}

	public Pion getPionReponse() {
		return pionReponse;
	}

}