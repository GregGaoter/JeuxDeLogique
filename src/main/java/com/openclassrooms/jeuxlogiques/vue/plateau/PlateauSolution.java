package com.openclassrooms.jeuxlogiques.vue.plateau;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.VueJeu;
import com.openclassrooms.jeuxlogiques.vue.combinaison.CombinaisonSolution;

public class PlateauSolution extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlateauSolution(VueJeu vue) {
		setBorder(BorderFactory.createTitledBorder("Solution"));
		add(new CombinaisonSolution(vue, vue.getFabriqueDePion()));
	}

}
