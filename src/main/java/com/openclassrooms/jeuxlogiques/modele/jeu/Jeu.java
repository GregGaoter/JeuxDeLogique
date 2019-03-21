package com.openclassrooms.jeuxlogiques.modele.jeu;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;

/**
 * <b>Jeu est une classe abstraite fournissant des m�thodes permettant de
 * caract�riser chaque jeu.</b>
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public abstract class Jeu {

	/**
	 * Liste de tous les jeux disponibles. La liste cr��e une instance de chaque
	 * jeu.
	 */
	public static final Jeu[] LISTE_JEUX = { new JeuRecherchePlusMoins(), new JeuMastermind() };

	/**
	 * Pion commun � chaque jeu.
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
	 * Contient l'�tat pour savoir si la liste des combinaisons possibles doit �tre
	 * charh�e.
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