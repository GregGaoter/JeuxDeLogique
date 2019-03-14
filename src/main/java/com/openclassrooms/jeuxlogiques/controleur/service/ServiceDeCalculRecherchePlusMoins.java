package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionChiffre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseSymbol;

/**
 * <b>ServiceDeCalculRecherchePlusMoins est la classe implémentant les méthodes
 * de calcul pour le jeu Recherche +/_.</b>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public class ServiceDeCalculRecherchePlusMoins extends ServiceDeCalcul {

	private List<Integer[]> rechercheReponse;

	public ServiceDeCalculRecherchePlusMoins(int nbPionsCombinaisonSecrete) {
		super(nbPionsCombinaisonSecrete);
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

	public List<Pion> calculerProposition(ModeleJeu modele, List<Pion> derniereProposition,
			List<Pion> derniereReponse) {
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