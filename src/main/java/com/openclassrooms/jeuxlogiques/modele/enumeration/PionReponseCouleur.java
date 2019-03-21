package com.openclassrooms.jeuxlogiques.modele.enumeration;

/**
 * <b>PionReponseCouleur est l'�num�ration contenant les pions repr�sentant des
 * couleurs r�ponse.</b></br>
 * PionReponseCouleur impl�mente l'interface Pion.
 * 
 * <p>
 * Un pion couleur r�ponse est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Le nom de son fichier image.</li>
 * </ul>
 * </p>
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public enum PionReponseCouleur implements Pion {

	Blanc("/images/pion_blanc.png"), Noir("/images/pion_noir.png");

	/**
	 * Le nom du fichier image du pion couleur r�ponse.
	 * 
	 * @see PionReponseCouleur#getNomImage()
	 */
	private final String nomImage;

	/**
	 * Constructeur du pion couleur r�ponse.</br>
	 * Le constructeur initialise le nom de l'image du pion couleur r�ponse.
	 * 
	 * @param nomImage le nom du fichier image du pion couleur r�ponse
	 * @see PionReponseCouleur#getNomImage()
	 */
	private PionReponseCouleur(String nomImage) {
		this.nomImage = nomImage;
	}

	/**
	 * Retourne le nom du fichier image du pion couleur r�ponse.
	 * 
	 * @return le nom du fichier image du pion couleur r�ponse.
	 */
	public String getNomImage() {
		return nomImage;
	}

	/**
	 * Retourne la valeur du pion couleur r�ponse.
	 * 
	 * @return la valeur du pion couleur r�ponse.
	 */
	public int getValeur() {
		return 0;
	}

}