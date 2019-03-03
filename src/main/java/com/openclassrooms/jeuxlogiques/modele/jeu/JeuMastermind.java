package com.openclassrooms.jeuxlogiques.modele.jeu;

import org.apache.commons.lang3.ArrayUtils;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalculMastermind;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Parametre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCouleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseCouleur;

public class JeuMastermind extends Jeu {

	public JeuMastermind() {
		nbPionsUtilisables = Parametre.NbCouleursUtilisables.getValeur();
	}

	public String getNom() {
		return "Mastermind";
	}

	public Pion[] getPionsJeu() {
		return ArrayUtils.subarray(PionCouleur.values(), 0, nbPionsUtilisables);
	}

	public Pion[] getPionsReponse() {
		return PionReponseCouleur.values();
	}

	public void setNbPionsUtilisables(int nbPionsUtilisables) {
		this.nbPionsUtilisables = nbPionsUtilisables;
	}

	public ServiceDeCalcul getServiceDeCalcul() {
		return new ServiceDeCalculMastermind();
	}

	public String getNomFichierHTMLReglesJeu() {
		return "/regles_jeu_mastermind.html";
	}

}