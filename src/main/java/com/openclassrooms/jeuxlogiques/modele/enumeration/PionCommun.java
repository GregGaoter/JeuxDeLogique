package com.openclassrooms.jeuxlogiques.modele.enumeration;

/**
 * <b>PionCommun est l'énumération contenant les pions représentant les pions
 * communs à tous les jeux.</b></br>
 * PionCommun implémente l'interface Pion.
 * 
 * <p>
 * Un pion commun est caractérisé par les informations suivantes :
 * <ul>
 * <li>Le nom de son fichier image.</li>
 * </ul>
 * </p>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public enum PionCommun implements Pion {

	Secret("/images/pion_secret.png"), Transparent("/images/pion_transparent.png"), Vide("/images/pion_vide.png");

	/**
	 * Le nom du fichier image du pion commun.
	 * 
	 * @see PionChiffre#getNomImage()
	 */
	private final String nomImage;

	/**
	 * Constructeur du pion commun.</br>
	 * Le constructeur initialise le nom de l'image du pion commun.
	 * 
	 * @param nomImage le nom du fichier image du pion commun
	 * @see PionCommun#getNomImage()
	 */
	private PionCommun(String nomImage) {
		this.nomImage = nomImage;
	}

	/**
	 * Retourne le nom du fichier image du pion commun.
	 * 
	 * @return le nom du fichier image du pion commun.
	 */
	public String getNomImage() {
		return nomImage;
	}

	/**
	 * Retourne la valeur du pion commun.
	 * 
	 * @return la valeur du pion commun.
	 */
	public int getValeur() {
		return 0;
	}

}
