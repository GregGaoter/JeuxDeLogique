package com.openclassrooms.jeuxlogiques.vue.combinaison;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.VueJeu;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public class CombinaisonSolution extends JPanel {

	private static final long serialVersionUID = 1L;

	public CombinaisonSolution(VueJeu vue, FabriqueDePion fabriqueDePion) {
		setLayout(new GridLayout(1, vue.getModele().getNbPionsCombinaison()));
		for (int i = 0; i < vue.getModele().getNbPionsCombinaison(); i++)
			add(fabriqueDePion.creerPionVide());
	}

}
