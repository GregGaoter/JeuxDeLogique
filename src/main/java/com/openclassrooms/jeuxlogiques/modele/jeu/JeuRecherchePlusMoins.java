package com.openclassrooms.jeuxlogiques.modele.jeu;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalculRecherchePlusMoins;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionChiffre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseSymbol;

public class JeuRecherchePlusMoins extends Jeu {

	public String getNom() {
		return "Recherche +/-";
	}

	public Pion[] getPionsJeu() {
		return PionChiffre.values();
	}

	public Pion[] getPionsReponse() {
		return PionReponseSymbol.values();
	}

	public ServiceDeCalcul getServiceDeCalcul() {
		return new ServiceDeCalculRecherchePlusMoins();
	}

}