package com.openclassrooms.jeuxlogiques.modele.jeu;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;

public abstract class Jeu {

	protected PionCommun pionCommun;

	public Pion getPionCommun() {
		return pionCommun;
	}

	public abstract Pion getPionJeu();

	public abstract Pion getPionReponse();

}