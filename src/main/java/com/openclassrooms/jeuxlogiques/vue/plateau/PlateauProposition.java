package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.VueJeu;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public class PlateauProposition extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlateauProposition(VueJeu vue, FabriqueDePion fabriqueDePion) {
		setLayout(new GridLayout(vue.getModele().getNbEssais(), vue.getModele().getNbPionsCombinaison()));
		setBorder(BorderFactory.createTitledBorder("Proposition"));
		for (int y = 0; y < vue.getModele().getNbEssais(); y++) {
			for (int x = 0; x < vue.getModele().getNbPionsCombinaison(); x++)
				add(fabriqueDePion.creerPionVide());
		}
	}
}