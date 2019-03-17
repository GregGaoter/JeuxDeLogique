package com.openclassrooms.jeuxlogiques.modele.jeu;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;

public abstract class Jeu {

	public static final Jeu[] LISTE_JEUX = { new JeuRecherchePlusMoins(), new JeuMastermind() };

	protected PionCommun pionCommun;
	protected int nbPionsUtilisables;
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