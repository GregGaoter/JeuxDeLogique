package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;

public class PlateauJeu extends Plateau {

	private static final long serialVersionUID = 1L;

	public PlateauJeu(int nbCombinaisons, ArrayList<JPanel> pions, String titre) {
		super(nbCombinaisons, pions, titre);
	}

	public void setPion() {
		ListIterator<JPanel> iterateurPlateau = plateau.listIterator();
		ListIterator<JPanel> iterateurPions = pions.listIterator();
		while (iterateurPlateau.hasNext()) {
			JPanel panneauCombinaison = iterateurPlateau.next();
			while (iterateurPions.hasNext())
				panneauCombinaison.add(pions.get(0));
			// panneauCombinaison.add(iterateurPions.next());
		}

	}

}
