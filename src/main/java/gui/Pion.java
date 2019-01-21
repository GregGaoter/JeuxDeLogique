package gui;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Pion {

	private Color couleur;

	public Pion(Color couleur) {
		this.couleur = couleur;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

}
