package com.openclassrooms.jeuxlogiques.modele.enumeration;

/**
 * <b>PionReponseSymbol est l'énumération contenant les pions représentant des
 * symboles réponses.</b></br>
 * PionReponseSymbol implémente l'interface Pion.
 * 
 * <p>
 * Un pion symbole réponse est caractérisé par les informations suivantes :
 * <ul>
 * <li>Le nom de son fichier image.</li>
 * </ul>
 * </p>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public enum PionReponseSymbol implements Pion {

	Egal("/images/pion_egal.png", 0), Moins("/images/pion_moins.png", -1), Plus("/images/pion_plus.png", 1);

	/**
	 * Le nom du fichier image du pion symbole réponse.
	 * 
	 * @see PionReponseSymbol#getNomImage()
	 */
	private final String nomImage;

	/**
	 * La valeur du pion symbole réponse.
	 * 
	 * @see PionReponseSymbol#getValeur()
	 */
	private final int valeur;

	/**
	 * Constructeur du pion symbole réponse.</br>
	 * Le constructeur initialise le nom de l'image du pion symbole réponse.
	 * 
	 * @param nomImage le nom du fichier image du pion symbole réponse
	 * @see PionReponseSymbol#getNomImage()
	 */
	private PionReponseSymbol(String nomImage, int valeur) {
		this.nomImage = nomImage;
		this.valeur = valeur;
	}

	/**
	 * Retourne le nom du fichier image du pion symbole réponse.
	 * 
	 * @return le nom du fichier image du pion symbole réponse.
	 */
	public String getNomImage() {
		return nomImage;
	}

	/**
	 * Retourne la valeur du pion symbole réponse.
	 * 
	 * @return la valeur du pion symbole réponse.
	 */
	public int getValeur() {
		return valeur;
	}

}