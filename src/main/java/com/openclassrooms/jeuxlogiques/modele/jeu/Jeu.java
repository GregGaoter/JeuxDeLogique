package com.openclassrooms.jeuxlogiques.modele.jeu;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;

/**
 * <b>Jeu est une classe abstraite fournissant des méthodes permettant de
 * caractériser chaque jeu.</b>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public abstract class Jeu {

	/**
	 * Liste de tous les jeux disponibles. La liste créée une instance de chaque
	 * jeu.
	 */
	public static final Jeu[] LISTE_JEUX = { new JeuRecherchePlusMoins(), new JeuMastermind() };

	/**
	 * Pion commun à chaque jeu.
	 */
	protected PionCommun pionCommun;

	/**
	 * Nombre de pions utilisables par le jeu.
	 * 
	 * @see JeuRecherchePlusMoins#JeuRecherchePlusMoins()
	 * @see JeuMastermind#JeuMastermind()
	 */
	protected int nbPionsUtilisables;

	/**
	 * Contient l'état pour savoir si la liste des combinaisons possibles doit être
	 * charhée.
	 * 
	 * @see JeuRecherchePlusMoins#JeuRecherchePlusMoins()
	 * @see JeuMastermind#JeuMastermind()
	 */
	protected boolean loadCombinaisonsPossiblesQ;

	public Pion getPionCommun() {
		return pionCommun;
	}

	public int getNbPionsUtilisables() {
		return nbPionsUtilisables;
	}

	public boolean getLoadCombinaisonsPossiblesQ() {
		return loadCombinaisonsPossiblesQ;
	}

	public abstract String getNom();

	public abstract Pion[] getPionsJeu();

	public abstract Pion[] getPionsReponse();

	public abstract void setNbPionsUtilisables(int nbPionsUtilisables);

	public abstract ServiceDeCalcul getServiceDeCalcul(int nbPionsCombinaisonSecrete);

	public abstract String getNomFichierHTMLReglesJeu();

}