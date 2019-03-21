package com.openclassrooms.jeuxlogiques.modele.jeu;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalculMastermind;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Parametre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCouleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionReponseCouleur;

/**
 * <b>JeuMastermind est une classe implémentant des méthodes caractérisant le
 * jeu Mastermind.</b></br>
 * La classe JeuMastermind étend la classe abstraite Jeu.
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public class JeuMastermind extends Jeu {

	private final static Logger log = Logger.getLogger(JeuMastermind.class);

	/**
	 * Constructeur du jeu Mastermind. Le constructeur initialise le nombre de
	 * couleurs utilisables et l'état de chargement des combinaisons possibles du
	 * jeu.
	 * 
	 * @see Parametre#NbCouleursUtilisables
	 */
	public JeuMastermind() {
		log.info("Construction du Mastermind.");
		nbPionsUtilisables = Parametre.NbCouleursUtilisables.getValeur();
		loadCombinaisonsPossiblesQ = true;
	}

	/**
	 * Donne le nom du jeu.
	 * 
	 * @return le nom du jeu
	 */
	public String getNom() {
		return "Mastermind";
	}

	/**
	 * Donne le tableau des pions utilisables du jeu.
	 * 
	 * @return tableau des pions utilisables du jeu
	 * @see ArrayUtils#subarray(int[], int, int)
	 */
	public Pion[] getPionsJeu() {
		return ArrayUtils.subarray(PionCouleur.values(), 0, nbPionsUtilisables);
	}

	/**
	 * Donne le tableau des pions réponses du jeu.
	 * 
	 * @return le tableau des pions réponses du jeu
	 * @see PionReponseCouleur#values()
	 */
	public Pion[] getPionsReponse() {
		return PionReponseCouleur.values();
	}

	public void setNbPionsUtilisables(int nbPionsUtilisables) {
		this.nbPionsUtilisables = nbPionsUtilisables;
	}

	/**
	 * Donne le service de calcul du jeu.
	 * 
	 * @param nbPionsCombinaisonSecrete le nombre de pions de la combinaison secrète
	 * @return le service de calcul du jeu
	 * @see ServiceDeCalculMastermind#ServiceDeCalculMastermind(int)
	 */
	public ServiceDeCalcul getServiceDeCalcul(int nbPionsCombinaisonSecrete) {
		return new ServiceDeCalculMastermind(nbPionsCombinaisonSecrete);
	}

	/**
	 * Donne le nom du fichier HTML contenant les règles du jeu.
	 * 
	 * @return le nom du fichier HTML contenant les règles du jeu
	 */
	public String getNomFichierHTMLReglesJeu() {
		return "/regles_jeu_mastermind.html";
	}

}