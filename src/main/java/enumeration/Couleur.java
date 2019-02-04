package enumeration;

import java.awt.Color;

public enum Couleur {

	Bleu(Color.BLUE), Gris(Color.GRAY), Jaune(Color.YELLOW), Magenta(Color.MAGENTA), Orange(Color.ORANGE),
	Rose(Color.PINK), Rouge(Color.RED), Turquoise(Color.CYAN), Vert(Color.GREEN), Violet(new Color(127, 0, 127, 255));

	private final Color couleur;

	private Couleur(Color couleur) {
		this.couleur = couleur;
	}

	public Color getCouleur() {
		return couleur;
	}

}