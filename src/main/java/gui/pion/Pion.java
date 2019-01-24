package gui.pion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.commons.lang3.math.NumberUtils;

import gui.enumeration.Chiffre;
import gui.enumeration.Couleur;

public class Pion extends JPanel {

	private static final long serialVersionUID = 1L;
	private int chiffre;
	private Color faceColor, edgeColor;

	public Pion() {
		setPreferredSize(new Dimension(40, 40));
		setLayout(new BorderLayout(0, 0));
		faceColor = new Color(204, 204, 204, 204);
		edgeColor = new Color(0, 0, 0, 32);
	}

	public Pion(Chiffre chiffre) {
		this();
		faceColor = new Color(faceColor.getRed(), faceColor.getGreen(), faceColor.getBlue(), 255);
		edgeColor = new Color(edgeColor.getRed(), edgeColor.getGreen(), edgeColor.getBlue(), 255);
		this.chiffre = chiffre.getChiffre();
		setLabel(String.valueOf(this.chiffre));
	}

	public Pion(Couleur couleur) {
		this();
		this.faceColor = couleur.getCouleur();
		edgeColor = new Color(edgeColor.getRed(), edgeColor.getGreen(), edgeColor.getBlue(), 255);
	}

	public Pion(String texte) {
		this();
		switch(texte) {
		case "-":
		case "+":
			faceColor = new Color(255, 192, 192, 255);
			break;
		case "=":
			faceColor = new Color(192, 255, 192, 255);
			break;
		}
		edgeColor = new Color(edgeColor.getRed(), edgeColor.getGreen(), edgeColor.getBlue(), 255);
		setLabel(texte);
	}

	public int getChiffre() {
		return chiffre;
	}

	public Color getCouleur() {
		return faceColor;
	}

	private void setLabel(String texte) {
		JLabel label = new JLabel(texte, SwingConstants.CENTER);
		label.setFont(new Font(super.getName(), Font.PLAIN, super.getFont().getSize() * 2));
		add(label);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		int diametre = NumberUtils.min(getWidth(), getHeight()) - 5;
		int x = (getWidth() - diametre) / 2;
		int y = (getHeight() - diametre) / 2;
		g2D.setColor(faceColor);
		g2D.fillArc(x, y, diametre, diametre, 0, 360);
		g2D.setColor(edgeColor);
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.drawArc(x, y, diametre, diametre, 0, 360);
	}

}
