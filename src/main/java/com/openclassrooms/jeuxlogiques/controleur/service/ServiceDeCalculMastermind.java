package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseCouleur;

/**
 * <b>ServiceDeCalculMastermind est la classe implémentant les méthodes de
 * calcul pour le jeu Mastermind.</b>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public class ServiceDeCalculMastermind extends ServiceDeCalcul {

	public ServiceDeCalculMastermind(int nbPionsCombinaisonSecrete) {
		super(nbPionsCombinaisonSecrete);
	}

	/**
	 * Calcul la réponse du jeu Mastermind.
	 * 
	 * @param proposition : liste des pions de la combinaison proposition.
	 * @param solution    : liste des pions de la combinaison solution.
	 * @return Liste des pions de la combinaison réponse.
	 * @see Pion
	 * @see PionCommun
	 * @see PionReponseCouleur
	 */
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

	public List<Pion> calculerProposition(ModeleJeu modele, List<Pion> derniereProposition,
			List<Pion> derniereReponse) {
		List<Pion> nouvelleProposition = new ArrayList<>(derniereProposition.size());
		Pion pionAleatoire;
		for (int i = 1; i <= derniereProposition.size(); i++) {
			pionAleatoire = modele.getPionsUtilisables().get(RandomUtils.nextInt(0, modele.getNbPionsUtilisables()));
			nouvelleProposition.add(pionAleatoire);
		}
		return nouvelleProposition;
	}

}