package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.List;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

/**
 * <b>ServiceDeCalcul est une classe abstraite fournissant des m�thodes de
 * calcul propres � chaque jeu.</b>
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public abstract class ServiceDeCalcul {

	protected int nbPionsCombinaisonSecrete;

	public ServiceDeCalcul(int nbPionsCombinaisonSecrete) {
		this.nbPionsCombinaisonSecrete = nbPionsCombinaisonSecrete;
	}

	public abstract List<Pion> calculerReponse(List<Pion> proposition, List<Pion> solution);

	public abstract List<Pion> calculerProposition(List<List<Pion>> listeCombinaisonsPossibles,
			List<Pion> derniereProposition, List<Pion> derniereReponse);

}