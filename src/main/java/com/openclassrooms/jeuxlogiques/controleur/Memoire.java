package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;

/**
 * <b>Memoire est une classe permettant de m�moriser un jeu.</b>
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public class Memoire {

	/**
	 * Le jeu � m�moriser.
	 * 
	 * @see Jeu
	 */
	private Jeu jeu;

	/**
	 * Constructeur de la m�moire.
	 * 
	 * @param jeu le jeu � m�moriser
	 * @see Jeu
	 */
	public Memoire(Jeu jeu) {
		this.jeu = jeu;
	}

	public Jeu getJeu() {
		return jeu;
	}

}