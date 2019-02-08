package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.combinaison.CombinaisonProposition;

public class PlateauProposition extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlateauProposition(Vue vue) {
		setLayout(new GridLayout(0, 1));
		setBorder(BorderFactory.createTitledBorder("Proposition"));
		for (int i = 0; i < vue.getModele().getNbEssais(); i++)
			add(new CombinaisonProposition(vue, vue.getFabriqueDePion()));
	}
}