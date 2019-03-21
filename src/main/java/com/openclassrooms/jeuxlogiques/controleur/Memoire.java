package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;

/**
 * <b>Memoire est une classe permettant de mémoriser un jeu.</b>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public class Memoire {

	/**
	 * Le jeu à mémoriser.
	 * 
	 * @see Jeu
	 */
	private Jeu jeu;

	/**
	 * Constructeur de la mémoire.
	 * 
	 * @param jeu le jeu à mémoriser
	 * @see Jeu
	 */
	public Memoire(Jeu jeu) {
		this.jeu = jeu;
	}

	public Jeu getJeu() {
		return jeu;
	}

}