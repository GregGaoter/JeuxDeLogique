package com.openclassrooms.jeuxlogiques.modele.enumeration;

/**
 * <b>PionChiffre est l'énumération contenant les pions représentant des
 * chiffres.</b></br>
 * PionChiffre implémente l'interface Pion.
 * 
 * <p>
 * Un pion chiffre est caractérisé par les informations suivantes :
 * <ul>
 * <li>Le nom de son fichier image.</li>
 * <li>Sa valeur.</li>
 * </ul>
 * </p>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public enum PionChiffre implements Pion {

	Zero("/images/pion_0.png", 0), Un("/images/pion_1.png", 1), Deux("/images/pion_2.png", 2),
	Trois("/images/pion_3.png", 3), Quatre("/images/pion_4.png", 4), Cinq("/images/pion_5.png", 5),
	Six("/images/pion_6.png", 6), Sept("/images/pion_7.png", 7), Huit("/images/pion_8.png", 8),
	Neuf("/images/pion_9.png", 9);

	/**
	 * Le nom du fichier image du pion chiffre.
	 * 
	 * @see PionChiffre#getNomImage()
	 */
	private final String nomImage;

	/**
	 * La valeur du pion chiffre.
	 * 
	 * @see PionChiffre#getValeur()
	 */
	private final int valeur;

	/**
	 * Constructeur du pion chiffre.</br>
	 * Le constructeur initialise le nom de l'image et la valeur du pion chiffre.
	 * 
	 * @param nomImage le nom du fichier image du pion chiffre
	 * @param min      la valeur du pion chiffre
	 * @see PionChiffre#getNomImage()
	 * @see PionChiffre#getValeur()
	 */
	private PionChiffre(String nomImage, int valeur) {
		this.nomImage = nomImage;
		this.valeur = valeur;
	}

	/**
	 * Retourne le nom du fichier image du pion chiffre.
	 * 
	 * @return le nom du fichier image du pion chiffre.
	 */
	public String getNomImage() {
		return nomImage;
	}

	/**
	 * Retourne la valeur du pion chiffre.
	 * 
	 * @return la valeur du pion chiffre.
	 */
	public int getValeur() {
		return valeur;
	}

}