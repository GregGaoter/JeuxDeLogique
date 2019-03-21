package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseCouleur;

/**
 * <b>ServiceDeCalculMastermind est la classe impl�mentant les m�thodes de
 * calcul pour le jeu Mastermind.</b></br>
 * La classe ServiceDeCalculMastermind �tend la classe abstraite
 * ServiceDeCalcul.
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public class ServiceDeCalculMastermind extends ServiceDeCalcul {

	private final static Logger log = Logger.getLogger(ServiceDeCalculMastermind.class);

	/**
	 * Constructeur du service de calcul pour le jeu Mastermind. Initialise le
	 * nombre de pions de la combinaison secr�te.
	 * 
	 * @param nbPionsCombinaisonSecrete nombre de pions de la combinaison secr�te
	 */
	public ServiceDeCalculMastermind(int nbPionsCombinaisonSecrete) {
		super(nbPionsCombinaisonSecrete);
		log.info("Construction du service de calcul pour le Mastermind.");
	}

	/**
	 * Calcul la r�ponse du jeu Mastermind.
	 * 
	 * @param proposition liste des pions de la combinaison proposition
	 * @param solution    liste des pions de la combinaison solution
	 * @return Liste des pions de la combinaison r�ponse
	 * @see Pion
	 * @see PionCommun
	 * @see PionReponseCouleur
	 */
	public List<Pion> calculerReponse(List<Pion> proposition, List<Pion> solution) {

		List<Pion> reponse = new ArrayList<>(proposition.size());

		/*
		 * R�cup�re les valeurs des listes proposition et r�ponse
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
		 * Compte le nombre de pions bien plac�s
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
		 * Supprime les pions bien plac�s des listes proposition et solution
		 */
		for (int i = positionBienPlace.size() - 1; i >= 0; i--) {
			listeValeursProposition.remove((int) positionBienPlace.get(i));
			listeValeursSolution.remove((int) positionBienPlace.get(i));
		}

		/*
		 * Compte le nombre de pions mal plac�s
		 */
		int nbMalPlace = 0;
		itValeursProposition = listeValeursProposition.iterator();
		while (itValeursProposition.hasNext())
			nbMalPlace += listeValeursSolution.remove(itValeursProposition.next()) ? 1 : 0;

		/*
		 * Construction de la r�ponse
		 */
		for (int i = 0; i < nbBienPlace; i++)
			reponse.add(PionReponseCouleur.Noir);
		for (int i = 0; i < nbMalPlace; i++)
			reponse.add(PionReponseCouleur.Blanc);
		for (int i = 0; i < proposition.size() - nbBienPlace - nbMalPlace; i++)
			reponse.add(PionCommun.Vide);

		return reponse;
	}

	/**
	 * Calcul la proposition de l'ordinateur pour le jeu Mastermind.
	 * 
	 * @param listeCombinaisonsPossibles liste des combinaisons possibles du jeu.
	 * @param derniereProposition        derni�re proposition de l'ordinateur
	 * @param derniereProposition        derni�re r�ponse obtenue
	 * @return la nouvelle proposition de l'ordinateur pour le Mastermind
	 * @see Pion
	 * @see ServiceDeCalculMastermind#calculerReponse(List, List)
	 * @see RandomUtils
	 */
	public List<Pion> calculerProposition(List<List<Pion>> listeCombinaisonsPossibles, List<Pion> derniereProposition,
			List<Pion> derniereReponse) {
		log.info("Calcul de la proposition du Mastermind.");
		if (!derniereProposition.contains(PionCommun.Vide))
			listeCombinaisonsPossibles
					.removeIf(c -> (!derniereReponse.equals(calculerReponse(c, derniereProposition))));
		return listeCombinaisonsPossibles.get(RandomUtils.nextInt(0, listeCombinaisonsPossibles.size()));
	}

}