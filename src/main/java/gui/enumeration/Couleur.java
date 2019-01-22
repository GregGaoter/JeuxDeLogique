package gui.enumeration;

import java.awt.Color;

public enum Couleur {

	Blanc(Color.WHITE), Bleu(Color.BLUE), Jaune(Color.YELLOW), Noir(Color.BLACK), Rouge(Color.RED),
	Turquoise(Color.CYAN), Vert(Color.GREEN), Violet(Color.MAGENTA);

	private final Color couleur;

	private Couleur(Color couleur) {
		this.couleur = couleur;
	}

	public Color getCouleur() {
		return couleur;
	}

}