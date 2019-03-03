package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseCouleur;

public class ServiceDeCalculMastermind implements ServiceDeCalcul {

	// TODO Corriger le bug du calcul de la réponse de Mastermind
	public List<Pion> calculerReponse(List<Pion> proposition, List<Pion> solution) {

		List<Pion> reponse = new ArrayList<>(proposition.size());

		int nbBienPlace = 0;
		List<Integer> positionBienPlace = new ArrayList<>();
		int position = -1;
		Iterator<Pion> itProposition = proposition.iterator();
		Iterator<Pion> itSolution = solution.iterator();
		int valeurProposition, valeurSolution;
		while (itProposition.hasNext() && itSolution.hasNext()) {
			valeurProposition = itProposition.next().getValeur();
			valeurSolution = itSolution.next().getValeur();
			position++;
			if (valeurProposition == valeurSolution) {
				nbBienPlace++;
				positionBienPlace.add(position);
			}
		}

		List<Pion> propositionTemp = new ArrayList<>(proposition.size());
		List<Pion> solutionTemp = new ArrayList<>(solution.size());
		propositionTemp.addAll(proposition);
		solutionTemp.addAll(solution);
		for (int i = positionBienPlace.size() - 1; i >= 0; i--) {
			propositionTemp.remove((int) positionBienPlace.get(i));
			solutionTemp.remove((int) positionBienPlace.get(i));
		}

		propositionTemp.retainAll(solutionTemp);
		HashSet<Pion> pionsMalPlaces = new HashSet<>();
		pionsMalPlaces.addAll(propositionTemp);

		int nbMalPlace = pionsMalPlaces.size();

		for (int i = 0; i < nbBienPlace; i++)
			reponse.add(PionReponseCouleur.Noir);
		for (int i = 0; i < nbMalPlace; i++)
			reponse.add(PionReponseCouleur.Blanc);
		for (int i = 0; i < proposition.size() - nbBienPlace - nbMalPlace; i++)
			reponse.add(PionCommun.Vide);

		return reponse;
	}

}