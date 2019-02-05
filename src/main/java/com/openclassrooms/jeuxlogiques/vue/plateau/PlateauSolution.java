package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.VueJeu;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public class PlateauSolution extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlateauSolution(VueJeu vue, FabriqueDePion fabriqueDePion) {
		setLayout(new GridLayout(1, vue.getModele().getNbPionsCombinaison()));
		setBorder(BorderFactory.createTitledBorder("Solution"));
		for (int i = 0; i < vue.getModele().getNbPionsCombinaison(); i++)
			add(fabriqueDePion.creerPionVide());
	}

}
