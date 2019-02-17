package com.openclassrooms.jeuxlogiques.modele.jeu;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionChiffre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseSymbol;

public class JeuRecherchePlusMoins extends Jeu {

	private PionChiffre pionJeu;
	private PionReponseSymbol pionReponse;

	public Pion getPionJeu() {
		return pionJeu;
	}

	public Pion getPionReponse() {
		return pionReponse;
	}

}