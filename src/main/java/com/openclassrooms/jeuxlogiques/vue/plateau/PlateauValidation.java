package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PlateauValidation extends Plateau {

	private static final long serialVersionUID = 1L;

	public PlateauValidation(int nbCombinaisons, ArrayList<JPanel> pions, String titre) {
		super(nbCombinaisons, pions, titre);
	}

	public void setPion() {
		ListIterator<JPanel> iterateurPlateau = plateau.listIterator();
		ListIterator<JPanel> iterateurPions = pions.listIterator();
		for (int i = 0; i < plateau.size() - 1; i++) {
			JPanel panneauCombinaison = iterateurPlateau.next();
			while (iterateurPions.hasNext())
				panneauCombinaison.add(iterateurPions.next());
		}
		JButton boutonValidation = new JButton("Valider");
		iterateurPlateau.next().add(boutonValidation);
	}
}