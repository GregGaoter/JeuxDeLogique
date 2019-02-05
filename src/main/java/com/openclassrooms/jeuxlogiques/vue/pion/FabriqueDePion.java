package com.openclassrooms.jeuxlogiques.vue.pion;

import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.enumeration.Apparence;

public abstract class FabriqueDePion {

	public abstract JPanel creerPionSecret();

	public abstract JPanel creerPionVide();

	public abstract JPanel creerPionJeu(Apparence apparence);

	public abstract JPanel creerPionReponse(Apparence apparence);

	public abstract JPanel creerPionTransparent();

}
