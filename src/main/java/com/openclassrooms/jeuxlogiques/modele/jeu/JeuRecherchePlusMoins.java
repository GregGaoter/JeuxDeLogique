package com.openclassrooms.jeuxlogiques.modele.jeu;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalculRecherchePlusMoins;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionChiffre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseSymbol;

public class JeuRecherchePlusMoins extends Jeu {

	private final static Logger log = Logger.getLogger(JeuRecherchePlusMoins.class);

	public JeuRecherchePlusMoins() {
		log.info("Construction de Recherche +/-");
		nbPionsUtilisables = 10;
		loadCombinaisonsPossiblesQ = false;
	}

	public String getNom() {
		return "Recherche +/-";
	}

	public Pion[] getPionsJeu() {
		return PionChiffre.values();
	}

	public Pion[] getPionsReponse() {
		return PionReponseSymbol.values();
	}

	public void setNbPionsUtilisables(int nbPionsUtilisables) {
		nbPionsUtilisables = 10;
	}

	public ServiceDeCalcul getServiceDeCalcul(int nbPionsCombinaisonSecrete) {
		return new ServiceDeCalculRecherchePlusMoins(nbPionsCombinaisonSecrete);
	}

	public String getNomFichierHTMLReglesJeu() {
		return "/regles_jeu_recherche_plus_moins.html";
	}

}