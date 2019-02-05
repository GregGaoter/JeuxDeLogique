package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.VueJeu;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public class PlateauPions extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlateauPions(VueJeu vue, FabriqueDePion fabriqueDePion) {
		setLayout(new GridLayout(1, vue.getModele().getNbPionsUtilisables()));
		setBorder(BorderFactory.createTitledBorder("Pions"));
		for (int i = 0; i < vue.getModele().getNbPionsUtilisables(); i++)
			add(fabriqueDePion.creerPionVide());
	}

}
