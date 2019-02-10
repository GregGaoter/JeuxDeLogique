package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public abstract class Plateau extends JPanel {

	private static final long serialVersionUID = 1L;

	protected int nbCombinaisons;
	protected ArrayList<JPanel> pions;
	protected String titre;
	protected FabriqueDePion fabriqueDePion;
	protected ArrayList<JPanel> plateau;

	public Plateau(int nbCombinaisons, ArrayList<JPanel> pions, String titre) {
		this.nbCombinaisons = nbCombinaisons;
		this.pions = pions;
		this.titre = titre;
		plateau = new ArrayList<>();
	}

	public void setSocle() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder(titre));
	}

	public void setCombinaison() {
		JPanel panneauCombinaison;
		for (int i = 0; i < nbCombinaisons; i++) {
			panneauCombinaison = new JPanel();
			panneauCombinaison.setLayout(new BoxLayout(panneauCombinaison, BoxLayout.X_AXIS));
			plateau.add(panneauCombinaison);
			add(panneauCombinaison);
		}
	}

	public FabriqueDePion getFabriqueDePion() {
		return fabriqueDePion;
	}

	public void setFabriqueDePion(FabriqueDePion fabriqueDePion) {
		this.fabriqueDePion = fabriqueDePion;
	}

	public abstract void setPion();

}
