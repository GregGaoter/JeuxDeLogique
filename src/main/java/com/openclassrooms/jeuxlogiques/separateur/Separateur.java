package com.openclassrooms.jeuxlogiques.separateur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public abstract class Separateur extends JPanel {

	private static final long serialVersionUID = 1L;

	protected int dimension;
	protected Graphics2D g2D;

	public Separateur(int dimension) {
		this.dimension = dimension;
		setBackground(new Color(0, 0, 0, 0));
		setTaille();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2D = (Graphics2D) g;
		g2D.setColor(Color.GRAY);
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		dessinerLigne();
	}

	protected abstract void setTaille();

	protected abstract void dessinerLigne();

}
