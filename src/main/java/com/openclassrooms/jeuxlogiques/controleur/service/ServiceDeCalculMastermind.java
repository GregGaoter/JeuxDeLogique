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

		/*
		 * Récupère les valeurs des listes proposition et réponse
		 */
		List<Integer> listeValeursProposition = new ArrayList<>(proposition.size());
		List<Integer> listeValeursSolution = new ArrayList<>(solution.size());
		Iterator<Pion> itProposition = proposition.iterator();
		Iterator<Pion> itSolution = solution.iterator();
		while (itProposition.hasNext() && itSolution.hasNext()) {
			listeValeursProposition.add(itProposition.next().getValeur());
			listeValeursSolution.add(itSolution.next().getValeur());
		}

		/*
		 * Compte le nombre de pions bien placés
		 */
		int nbBienPlace = 0;
		List<Integer> positionBienPlace = new ArrayList<>();
		int position = -1;
		Iterator<Integer> itValeursProposition = listeValeursProposition.iterator();
		Iterator<Integer> itValeursSolution = listeValeursSolution.iterator();
		int valeurProposition, valeurSolution;
		while (itValeursProposition.hasNext() && itValeursSolution.hasNext()) {
			valeurProposition = itValeursProposition.next();
			valeurSolution = itValeursSolution.next();
			position++;
			if (valeurProposition == valeurSolution) {
				nbBienPlace++;
				positionBienPlace.add(position);
			}
		}

		/*
		 * Supprime les pions bien placés des listes proposition et solution
		 */
		for (int i = positionBienPlace.size() - 1; i >= 0; i--) {
			listeValeursProposition.remove((int) positionBienPlace.get(i));
			listeValeursSolution.remove((int) positionBienPlace.get(i));
		}

		/*
		 * Compte le nombre de pions mal placés
		 */
		int nbMalPlace = 0;
		itValeursProposition = listeValeursProposition.iterator();
		while (itValeursProposition.hasNext())
			nbMalPlace += listeValeursSolution.remove(itValeursProposition.next()) ? 1 : 0;

		/*
		 * Construction de la réponse
		 */
		for (int i = 0; i < nbBienPlace; i++)
			reponse.add(PionReponseCouleur.Noir);
		for (int i = 0; i < nbMalPlace; i++)
			reponse.add(PionReponseCouleur.Blanc);
		for (int i = 0; i < proposition.size() - nbBienPlace - nbMalPlace; i++)
			reponse.add(PionCommun.Vide);

		return reponse;
	}

}