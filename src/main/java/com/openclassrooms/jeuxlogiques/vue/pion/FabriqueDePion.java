package com.openclassrooms.jeuxlogiques.vue.pion;

import javax.swing.JPanel;

public abstract class FabriqueDePion {

	public abstract JPanel creerPionSecret();

	public abstract JPanel creerPionVide();

	public abstract JPanel creerPionJeu();

	public abstract JPanel creerPionReponse();

	public abstract JPanel creerPionTransparent();

}
