package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseSymbol;

/**
 * <b>ServiceDeCalculRecherchePlusMoins est la classe implémentant les méthodes
 * de calcul pour le jeu Recherche +/_.</b>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public class ServiceDeCalculRecherchePlusMoins implements ServiceDeCalcul {

	private final static Logger log = Logger.getLogger(ServiceDeCalculMastermind.class);

	/**
	 * Calcul la réponse du jeu Recherche +/_.
	 * 
	 * @param proposition : liste des pions de la combinaison proposition.
	 * @param solution    : liste des pions de la combinaison solution.
	 * @return Liste des pions de la combinaison réponse.
	 * @see Pion
	 * @see PionReponseSymbol
	 */
	public List<Pion> calculerReponse(List<Pion> proposition, List<Pion> solution) {
		List<Pion> reponse = new ArrayList<>(proposition.size());
		Iterator<Pion> itProposition = proposition.iterator();
		Iterator<Pion> itSolution = solution.iterator();
		int valeurProposition, valeurSolution;
		while (itProposition.hasNext() && itSolution.hasNext()) {
			valeurProposition = itProposition.next().getValeur();
			valeurSolution = itSolution.next().getValeur();
			if (valeurProposition < valeurSolution)
				reponse.add(PionReponseSymbol.Plus);
			else if (valeurProposition > valeurSolution)
				reponse.add(PionReponseSymbol.Moins);
			else
				reponse.add(PionReponseSymbol.Egal);
		}
		log.debug("Le Recherche +/- a calculé sa réponse.");
		return reponse;
	}

}