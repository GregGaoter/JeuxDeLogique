package com.openclassrooms.jeuxlogiques.vue.plateau;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.VueJeu;
import com.openclassrooms.jeuxlogiques.vue.combinaison.CombinaisonValidation;

public class PlateauValidation extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlateauValidation(VueJeu vue) {
		setBorder(BorderFactory.createTitledBorder("Validation"));
		add(new CombinaisonValidation(vue, vue.getFabriqueDePion()));
	}

}
