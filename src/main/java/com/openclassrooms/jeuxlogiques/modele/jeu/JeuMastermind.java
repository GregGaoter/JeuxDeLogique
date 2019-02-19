package com.openclassrooms.jeuxlogiques.modele.jeu;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalculMastermind;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCouleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseCouleur;

public class JeuMastermind extends Jeu {

	public String getNom() {
		return "Mastermind";
	}

	public Pion[] getPionsJeu() {
		return PionCouleur.values();
	}

	public Pion[] getPionsReponse() {
		return PionReponseCouleur.values();
	}

	public ServiceDeCalcul getServiceDeCalcul() {
		return new ServiceDeCalculMastermind();
	}

}