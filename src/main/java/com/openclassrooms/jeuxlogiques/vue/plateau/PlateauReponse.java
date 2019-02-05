package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.VueJeu;
import com.openclassrooms.jeuxlogiques.vue.combinaison.CombinaisonReponse;

public class PlateauReponse extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlateauReponse(VueJeu vue) {
		setLayout(new GridLayout(0, 1));
		setBorder(BorderFactory.createTitledBorder("Réponse"));
		for (int i = 0; i < vue.getModele().getNbEssais(); i++)
			add(new CombinaisonReponse(vue, vue.getFabriqueDePion()));
	}

}
