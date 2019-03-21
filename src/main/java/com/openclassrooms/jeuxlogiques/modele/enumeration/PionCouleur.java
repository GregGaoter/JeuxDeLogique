package com.openclassrooms.jeuxlogiques.modele.enumeration;

/**
 * <b>PionCouleur est l'énumération contenant les pions représentant des
 * couleurs.</b></br>
 * PionCouleur implémente l'interface Pion.
 * 
 * <p>
 * Un pion couleur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Le nom de son fichier image.</li>
 * <li>Sa valeur.</li>
 * </ul>
 * </p>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public enum PionCouleur implements Pion {

	Rouge("/images/pion_rouge.png", 0), Vert("/images/pion_vert.png", 1), Bleu("/images/pion_bleu.png", 2),
	Jaune("/images/pion_jaune.png", 3), Magenta("/images/pion_magenta.png", 4), Cyan("/images/pion_cyan.png", 5),
	Gris("/images/pion_gris.png", 6), Orange("/images/pion_orange.png", 7), Marron("/images/pion_marron.png", 8),
	Rose("/images/pion_rose.png", 9);

	/**
	 * Le nom du fichier image du pion couleur.
	 * 
	 * @see PionCouleur#getNomImage()
	 */
	private final String nomImage;

	/**
	 * La valeur du pion couleur.
	 * 
	 * @see PionCouleur#getValeur()
	 */
	private final int valeur;

	/**
	 * Constructeur du pion couleur.</br>
	 * Le constructeur initialise le nom de l'image et la valeur du pion couleur.
	 * 
	 * @param nomImage le nom du fichier image du pion couleur
	 * @param min      la valeur du pion couleur
	 * @see PionCouleur#getNomImage()
	 * @see PionCouleur#getValeur()
	 */
	private PionCouleur(String nomImage, int valeur) {
		this.nomImage = nomImage;
		this.valeur = valeur;
	}

	/**
	 * Retourne le nom du fichier image du pion couleur.
	 * 
	 * @return le nom du fichier image du pion couleur.
	 */
	public String getNomImage() {
		return nomImage;
	}

	/**
	 * Retourne la valeur du pion couleur.
	 * 
	 * @return la valeur du pion couleur.
	 */
	public int getValeur() {
		return valeur;
	}

}