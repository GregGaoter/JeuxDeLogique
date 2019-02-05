package com.openclassrooms.jeuxlogiques.vue.combinaison;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.VueJeu;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public class CombinaisonPions extends JPanel {

	private static final long serialVersionUID = 1L;

	public CombinaisonPions(VueJeu vue, FabriqueDePion fabriqueDePion) {
		setLayout(new GridLayout(1, vue.getModele().getNbPionsUtilisables()));
		for (int i = 0; i < vue.getModele().getNbPionsUtilisables(); i++)
			add(fabriqueDePion.creerPionVide());

	}

}
