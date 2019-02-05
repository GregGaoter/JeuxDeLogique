package com.openclassrooms.jeuxlogiques.vue.pion;

import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.enumeration.Apparence;

public abstract class PionJeu extends JPanel {

	private static final long serialVersionUID = 1L;

	public abstract void creerPionJeu(Apparence label);

}
