package com.openclassrooms.jeuxlogiques.modele.enumeration;

/**
 * <b>PionReponseCouleur est l'énumération contenant les pions représentant des
 * couleurs réponse.</b></br>
 * PionReponseCouleur implémente l'interface Pion.
 * 
 * <p>
 * Un pion couleur réponse est caractérisé par les informations suivantes :
 * <ul>
 * <li>Le nom de son fichier image.</li>
 * </ul>
 * </p>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public enum PionReponseCouleur implements Pion {

	Blanc("/images/pion_blanc.png"), Noir("/images/pion_noir.png");

	/**
	 * Le nom du fichier image du pion couleur réponse.
	 * 
	 * @see PionReponseCouleur#getNomImage()
	 */
	private final String nomImage;

	/**
	 * Constructeur du pion couleur réponse.</br>
	 * Le constructeur initialise le nom de l'image du pion couleur réponse.
	 * 
	 * @param nomImage le nom du fichier image du pion couleur réponse
	 * @see PionReponseCouleur#getNomImage()
	 */
	private PionReponseCouleur(String nomImage) {
		this.nomImage = nomImage;
	}

	/**
	 * Retourne le nom du fichier image du pion couleur réponse.
	 * 
	 * @return le nom du fichier image du pion couleur réponse.
	 */
	public String getNomImage() {
		return nomImage;
	}

	/**
	 * Retourne la valeur du pion couleur réponse.
	 * 
	 * @return la valeur du pion couleur réponse.
	 */
	public int getValeur() {
		return 0;
	}

}