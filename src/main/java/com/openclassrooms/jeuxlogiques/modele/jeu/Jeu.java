package com.openclassrooms.jeuxlogiques.modele.jeu;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;

public abstract class Jeu {

	public static final Jeu[] LISTE_JEUX = { new JeuRecherchePlusMoins(), new JeuMastermind() };

	protected PionCommun pionCommun;

	public Pion getPionCommun() {
		return pionCommun;
	}

	public abstract String getNom();

	public abstract Pion[] getPionsJeu();

	public abstract Pion[] getPionsReponse();

}