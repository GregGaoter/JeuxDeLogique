package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import org.apache.commons.lang3.math.NumberUtils;

public class Case extends JPanel {

	private static final long serialVersionUID = 1L;

	private Color couleur;

	public Case(byte chiffre) {

	}

	public Case(Color couleur) {
		this.couleur = couleur;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		// g2D.setColor(pion.getCouleur());
		g2D.setColor(Color.LIGHT_GRAY);
		int diametre = NumberUtils.min(getWidth(), getHeight()) - 1;
		g2D.fillArc((getWidth() - diametre) / 2, (getHeight() - diametre) / 2, diametre, diametre, 0, 360);
		g2D.setColor(Color.BLACK);
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.drawArc((getWidth() - diametre) / 2, (getHeight() - diametre) / 2, diametre, diametre, 0, 360);
	}

}
