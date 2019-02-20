package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseCouleur;

public class ServiceDeCalculMastermind implements ServiceDeCalcul {

	public List<Pion> calculerReponse(List<Pion> proposition, List<Pion> solution) {

		List<Pion> reponse = new ArrayList<>(proposition.size());

		int nbBienPlace = 0;
		Iterator<Pion> itProposition = proposition.iterator();
		Iterator<Pion> itSolution = solution.iterator();
		int valeurProposition, valeurSolution;
		while (itProposition.hasNext() && itSolution.hasNext()) {
			valeurProposition = itProposition.next().getValeur();
			valeurSolution = itSolution.next().getValeur();
			if (valeurProposition == valeurSolution)
				nbBienPlace++;
		}

		List<Pion> propositionTemp = new ArrayList<>(proposition.size());
		itProposition = proposition.iterator();
		while (itProposition.hasNext())
			propositionTemp.add(itProposition.next());

		propositionTemp.retainAll(solution);
		int nbPresent = propositionTemp.size() - nbBienPlace;

		for (int i = 0; i < nbBienPlace; i++)
			reponse.add(PionReponseCouleur.Noir);
		for (int i = 0; i < nbPresent; i++)
			reponse.add(PionReponseCouleur.Blanc);
		for (int i = 0; i < proposition.size() - nbBienPlace - nbPresent; i++)
			reponse.add(PionCommun.Vide);

		return reponse;
	}

}