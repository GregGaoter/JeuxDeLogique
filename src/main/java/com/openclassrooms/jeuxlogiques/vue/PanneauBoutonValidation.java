package com.openclassrooms.jeuxlogiques.vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanneauBoutonValidation extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;

	public PanneauBoutonValidation(JButton bouton) {
		try {
			image = ImageIO.read(getClass().getResource("/images/pion_transparent.png"));
		} catch (IOException e) {
		}
		setPreferredSize(new Dimension(bouton.getPreferredSize().width, image.getHeight()));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
	}

}