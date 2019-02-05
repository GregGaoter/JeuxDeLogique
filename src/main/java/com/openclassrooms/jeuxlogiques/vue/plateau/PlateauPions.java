package com.openclassrooms.jeuxlogiques.vue.plateau;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.VueJeu;
import com.openclassrooms.jeuxlogiques.vue.combinaison.CombinaisonPions;

public class PlateauPions extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlateauPions(VueJeu vue) {
		setBorder(BorderFactory.createTitledBorder("Pions"));
		add(new CombinaisonPions(vue, vue.getFabriqueDePion()));
	}

}
