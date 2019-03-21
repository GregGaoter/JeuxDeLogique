package com.openclassrooms.jeuxlogiques.modele.enumeration;

/**
 * <b>PionReponseSymbol est l'�num�ration contenant les pions repr�sentant des
 * symboles r�ponses.</b></br>
 * PionReponseSymbol impl�mente l'interface Pion.
 * 
 * <p>
 * Un pion symbole r�ponse est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Le nom de son fichier image.</li>
 * </ul>
 * </p>
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public enum PionReponseSymbol implements Pion {

	Egal("/images/pion_egal.png", 0), Moins("/images/pion_moins.png", -1), Plus("/images/pion_plus.png", 1);

	/**
	 * Le nom du fichier image du pion symbole r�ponse.
	 * 
	 * @see PionReponseSymbol#getNomImage()
	 */
	private final String nomImage;

	/**
	 * La valeur du pion symbole r�ponse.
	 * 
	 * @see PionReponseSymbol#getValeur()
	 */
	private final int valeur;

	/**
	 * Constructeur du pion symbole r�ponse.</br>
	 * Le constructeur initialise le nom de l'image du pion symbole r�ponse.
	 * 
	 * @param nomImage le nom du fichier image du pion symbole r�ponse
	 * @see PionReponseSymbol#getNomImage()
	 */
	private PionReponseSymbol(String nomImage, int valeur) {
		this.nomImage = nomImage;
		this.valeur = valeur;
	}

	/**
	 * Retourne le nom du fichier image du pion symbole r�ponse.
	 * 
	 * @return le nom du fichier image du pion symbole r�ponse.
	 */
	public String getNomImage() {
		return nomImage;
	}

	/**
	 * Retourne la valeur du pion symbole r�ponse.
	 * 
	 * @return la valeur du pion symbole r�ponse.
	 */
	public int getValeur() {
		return valeur;
	}

}