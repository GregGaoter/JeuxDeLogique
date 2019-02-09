package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.util.ListIterator;

import javax.swing.JPanel;

public class PlateauJeu extends Plateau {

	private static final long serialVersionUID = 1L;

	public PlateauJeu(int nbCombinaisons, int nbPionsCombinaisons, String titre) {
		super(nbCombinaisons, nbPionsCombinaisons, titre);
	}

	public void setPion() {
		ListIterator<JPanel> iterateur = plateau.listIterator();
		while (iterateur.hasNext()) {
			JPanel panneauCombinaison = iterateur.next();
			for (int i = 0; i < nbPionsCombinaisons; i++)
				panneauCombinaison.add(fabriqueDePion.creerPionVide());
		}

	}

}
