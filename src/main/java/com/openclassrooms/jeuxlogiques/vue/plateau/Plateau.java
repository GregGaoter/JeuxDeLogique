package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public class Plateau extends JPanel {

	private static final long serialVersionUID = 1L;

	private int nbCombinaisons;
	private int nbPions;
	private FabriqueDePion fabriqueDePion;
	private ArrayList<ArrayList<JPanel>> composantsPlateau;

	public Plateau() {
		composantsPlateau = new ArrayList<ArrayList<JPanel>>();
	}

	public void setSocle(int nbCombinaisons, String titre) {
		this.nbCombinaisons = nbCombinaisons;
		setLayout(new GridLayout(nbCombinaisons, 1));
		setBorder(BorderFactory.createTitledBorder(titre));
	}

	public void setCombinaison(int nbPions) {
		this.nbPions = nbPions;
		ArrayList<JPanel> panneauCombinaison;
		for (int i = 0; i < nbCombinaisons; i++) {
			// panneauCombinaison = new JPanel(new GridLayout(1, nbPions));
			panneauCombinaison = new ArrayList<>();
			composantsPlateau.add(panneauCombinaison);
			add(panneauCombinaison);
		}
	}

	public FabriqueDePion getFabriqueDePion() {
		return fabriqueDePion;
	}

	public void setFabriqueDePion(FabriqueDePion fabriqueDePion) {
		this.fabriqueDePion = fabriqueDePion;
	}

	public void setPion(JPanel pion) {
		ListIterator<JPanel> iterateur = combinaisons.listIterator();
		while (iterateur.hasNext()) {
			JPanel panneauCombinaison = iterateur.next();
			for (int i = 0; i < nbPions; i++)
				panneauCombinaison.add(fabriqueDePion.creerPionSecret());
		}

	}

}
