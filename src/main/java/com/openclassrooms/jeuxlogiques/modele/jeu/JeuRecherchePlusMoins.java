package com.openclassrooms.jeuxlogiques.modele.jeu;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalculRecherchePlusMoins;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Parametre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionChiffre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseSymbol;

/**
 * <b>JeuRecherchePlusMoins est une classe impl�mentant des m�thodes
 * caract�risant le jeu Recherche +/_.</b></br>
 * La classe JeuRecherchePlusMoins �tend la classe abstraite Jeu.
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public class JeuRecherchePlusMoins extends Jeu {

	private final static Logger log = Logger.getLogger(JeuRecherchePlusMoins.class);

	/**
	 * Constructeur du jeu Recherche +/_. Le constructeur initialise le nombre de
	 * pions utilisables et l'�tat de chargement des combinaisons possibles du jeu.
	 * 
	 * @see Parametre#NbCouleursUtilisables
	 */
	public JeuRecherchePlusMoins() {
		log.info("Construction de Recherche +/-");
		nbPionsUtilisables = 10;
		loadCombinaisonsPossiblesQ = false;
	}

	/**
	 * Donne le nom du jeu.
	 * 
	 * @return le nom du jeu
	 */
	public String getNom() {
		return "Recherche +/-";
	}

	/**
	 * Donne le tableau des pions utilisables du jeu.
	 * 
	 * @return tableau des pions utilisables du jeu
	 * @see PionChiffre#values()
	 */
	public Pion[] getPionsJeu() {
		return PionChiffre.values();
	}

	/**
	 * Donne le tableau des pions r�ponses du jeu.
	 * 
	 * @return le tableau des pions r�ponses du jeu
	 * @see PionReponseSymbol#values()
	 */
	public Pion[] getPionsReponse() {
		return PionReponseSymbol.values();
	}

	public void setNbPionsUtilisables(int nbPionsUtilisables) {
		nbPionsUtilisables = 10;
	}

	/**
	 * Donne le service de calcul du jeu.
	 * 
	 * @param nbPionsCombinaisonSecrete le nombre de pions de la combinaison secr�te
	 * @return le service de calcul du jeu
	 * @see ServiceDeCalculRecherchePlusMoins#ServiceDeCalculRecherchePlusMoins(int)
	 */
	public ServiceDeCalcul getServiceDeCalcul(int nbPionsCombinaisonSecrete) {
		return new ServiceDeCalculRecherchePlusMoins(nbPionsCombinaisonSecrete);
	}

	/**
	 * Donne le nom du fichier HTML contenant les r�gles du jeu.
	 * 
	 * @return le nom du fichier HTML contenant les r�gles du jeu
	 */
	public String getNomFichierHTMLReglesJeu() {
		return "/regles_jeu_recherche_plus_moins.html";
	}

}