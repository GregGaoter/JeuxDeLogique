package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionChiffre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseSymbol;

/**
 * <b>ServiceDeCalculRecherchePlusMoins est la classe implémentant les méthodes
 * de calcul pour le jeu Recherche +/_.</b></br>
 * La classe ServiceDeCalculRecherchePlusMoins étend la classe abstraite
 * ServiceDeCalcul.
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public class ServiceDeCalculRecherchePlusMoins extends ServiceDeCalcul {

	private final static Logger log = Logger.getLogger(ServiceDeCalculRecherchePlusMoins.class);

	/**
	 * Liste de tableau d'entier représentant l'interval de recherche de la solution
	 * pour chaque pion de la combinaison proposition.
	 * 
	 * @see ServiceDeCalculRecherchePlusMoins#calculerProposition(List, List, List)
	 */
	private List<Integer[]> rechercheReponse;

	/**
	 * Constructeur du service de calcul pour le jeu Recherche +/_. Initialise le
	 * nombre de pions de la combinaison secrète et la liste de recherche de la
	 * solution.
	 * 
	 * @param nbPionsCombinaisonSecrete nombre de pions de la combinaison secrète
	 */
	public ServiceDeCalculRecherchePlusMoins(int nbPionsCombinaisonSecrete) {
		super(nbPionsCombinaisonSecrete);
		log.info("Construction du service de calcul pour Recherche +/-.");
		rechercheReponse = new ArrayList<>();
		initialiserRechercheReponse();
	}

	private void initialiserRechercheReponse() {
		for (int i = 0; i < nbPionsCombinaisonSecrete; i++)
			rechercheReponse.add(new Integer[] { 0, 9 });
	}

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
		log.info("Calcul de la réponse de recherche +/-.");
		List<Pion> reponse = new ArrayList<>(nbPionsCombinaisonSecrete);
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

	/**
	 * Calcul la proposition de l'ordinateur pour le jeu Recherche +/_.
	 * 
	 * @param listeCombinaisonsPossibles liste des combinaisons possibles du jeu.
	 * @param derniereProposition        dernière proposition de l'ordinateur
	 * @param derniereProposition        dernière réponse obtenue
	 * @return la nouvelle proposition de l'ordinateur pour Recherche +/_
	 * @see Pion
	 * @see RandomUtils
	 */
	public List<Pion> calculerProposition(List<List<Pion>> listeCombinaisonsPossibles, List<Pion> derniereProposition,
			List<Pion> derniereReponse) {
		log.info("Calcul de la proposition de recherche +/-.");
		List<Pion> nouvelleProposition = new ArrayList<>(nbPionsCombinaisonSecrete);
		if (derniereReponse.contains(PionCommun.Vide))
			for (int i = 1; i <= nbPionsCombinaisonSecrete; i++)
				nouvelleProposition.add(PionChiffre.Cinq);
		else {
			Integer[] itemRechercheReponse;
			Pion dernierPionProposition;
			Iterator<Pion> itDerniereProposition = derniereProposition.iterator();
			Iterator<Pion> itDerniereReponse = derniereReponse.iterator();
			Iterator<Integer[]> itRechercheReponse = rechercheReponse.iterator();
			while (itDerniereProposition.hasNext() && itDerniereReponse.hasNext() && itRechercheReponse.hasNext()) {
				dernierPionProposition = itDerniereProposition.next();
				itemRechercheReponse = itRechercheReponse.next();
				switch (itDerniereReponse.next().getValeur()) {
				case -1:
					itemRechercheReponse[1] = dernierPionProposition.getValeur() - 1;
					break;
				case 1:
					itemRechercheReponse[0] = dernierPionProposition.getValeur() + 1;
					break;
				case 0:
					itemRechercheReponse[0] = itemRechercheReponse[1] = dernierPionProposition.getValeur();
				}
			}
			int rechercheReponseMin, rechercheReponseMax;
			float rechercheReponseMoyenne;
			int chiffreProposition;
			PionChiffre pionProposition;
			itRechercheReponse = rechercheReponse.iterator();
			while (itRechercheReponse.hasNext()) {
				itemRechercheReponse = itRechercheReponse.next();
				rechercheReponseMin = itemRechercheReponse[0];
				rechercheReponseMax = itemRechercheReponse[1];
				rechercheReponseMoyenne = ((float) rechercheReponseMin + (float) rechercheReponseMax) / 2.0f;
				if (rechercheReponseMoyenne <= 4)
					chiffreProposition = (rechercheReponseMin + rechercheReponseMax) / 2;
				else
					chiffreProposition = Math.round(rechercheReponseMoyenne);
				for (int i = 0; i < PionChiffre.values().length; i++) {
					pionProposition = PionChiffre.values()[i];
					if (chiffreProposition == pionProposition.getValeur())
						nouvelleProposition.add(pionProposition);
				}
			}
		}
		return nouvelleProposition;
	}

}