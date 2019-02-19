package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseSymbol;

public class ServiceDeCalculRecherchePlusMoins implements ServiceDeCalcul {

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
		return reponse;
	}

}