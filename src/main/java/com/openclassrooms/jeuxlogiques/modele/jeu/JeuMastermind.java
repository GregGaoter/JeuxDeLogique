package com.openclassrooms.jeuxlogiques.modele.jeu;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalculMastermind;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Parametre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCouleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseCouleur;

public class JeuMastermind extends Jeu {

	private final static Logger log = Logger.getLogger(JeuMastermind.class);

	public JeuMastermind() {
		log.info("Construction du Mastermind.");
		nbPionsUtilisables = Parametre.NbCouleursUtilisables.getValeur();
		loadCombinaisonsPossiblesQ = true;
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

	public ServiceDeCalcul getServiceDeCalcul(int nbPionsCombinaisonSecrete) {
		return new ServiceDeCalculMastermind(nbPionsCombinaisonSecrete);
	}

	public String getNomFichierHTMLReglesJeu() {
		return "/regles_jeu_mastermind.html";
	}

}