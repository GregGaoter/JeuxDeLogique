package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.List;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

/**
 * <b>ServiceDeCalcul est l'interface fournissant des m�thodes de calcul propres
 * � chaque jeu.</b>
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public interface ServiceDeCalcul {

	public List<Pion> calculerReponse(List<Pion> proposition, List<Pion> solution);

}