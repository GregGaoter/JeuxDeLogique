package com.openclassrooms.jeuxlogiques.vue.separateur;

import java.awt.Dimension;

public class SeparateurHorizontal extends Separateur {

	private static final long serialVersionUID = 1L;

	private AlignementVertical alignementVertical;

	public SeparateurHorizontal(int dimension, AlignementVertical alignementVertical) {
		super(dimension);
		this.alignementVertical = alignementVertical;
	}

	protected void setTaille() {
		setMaximumSize(new Dimension(Integer.MAX_VALUE, dimension));
	}

	protected void dessinerLigne() {
		switch (alignementVertical.name()) {
		case "Haut":
			g2D.drawLine(0, 0, getWidth(), 0);
			break;
		case "Milieu":
			g2D.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
			break;
		case "Bas":
			g2D.drawLine(0, getHeight(), getWidth(), getHeight());
			break;
		}
	}

}
