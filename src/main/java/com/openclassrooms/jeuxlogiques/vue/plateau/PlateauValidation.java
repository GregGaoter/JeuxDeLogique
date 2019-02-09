package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PlateauValidation extends Plateau {

	private static final long serialVersionUID = 1L;

	public PlateauValidation(int nbCombinaisons, int nbPionsCombinaisons, String titre) {
		super(nbCombinaisons, nbPionsCombinaisons, titre);
	}

	public void setPion() {
		ListIterator<JPanel> iterateur = plateau.listIterator();
		for (int i = 0; i < plateau.size() - 1; i++)
			iterateur.next().add(fabriqueDePion.creerPionTransparent());
		JButton boutonValidation = new JButton("Valider");
		boutonValidation.setEnabled(false);
		iterateur.next().add(boutonValidation);
	}

}
