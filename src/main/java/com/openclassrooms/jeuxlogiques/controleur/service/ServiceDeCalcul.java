package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.List;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

/**
 * <b>ServiceDeCalcul est l'interface fournissant des méthodes de calcul propres
 * à chaque jeu.</b>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public interface ServiceDeCalcul {

	public List<Pion> calculerReponse(List<Pion> proposition, List<Pion> solution);

}