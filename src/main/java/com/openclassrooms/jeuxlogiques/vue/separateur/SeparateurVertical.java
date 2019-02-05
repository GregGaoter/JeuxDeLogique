package com.openclassrooms.jeuxlogiques.vue.separateur;

import java.awt.Dimension;

public class SeparateurVertical extends Separateur {

	private static final long serialVersionUID = 1L;

	private AlignementHorizontal alignementHorizontal;

	public SeparateurVertical(int dimension, AlignementHorizontal alignementHorizontal) {
		super(dimension);
		this.alignementHorizontal = alignementHorizontal;
	}

	protected void setTaille() {
		setMaximumSize(new Dimension(dimension, Integer.MAX_VALUE));
	}

	protected void dessinerLigne() {
		switch (alignementHorizontal.name()) {
		case "Gauche":
			g2D.drawLine(1, 0, 1, getHeight());
			break;
		case "Centre":
			g2D.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
			break;
		case "Droite":
			g2D.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());
			break;
		}
	}

}
