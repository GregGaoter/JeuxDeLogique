package com.openclassrooms.jeuxlogiques.vue.pion;

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

public class PionSecret extends JPanel {

	private static final long serialVersionUID = 1L;

	public PionSecret() {
		setPreferredSize(new Dimension(40, 40));
		setLayout(new BorderLayout(0, 0));
		setBackground(new Color(0, 0, 0, 0));
		setLabel("?");
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
		g2D.setColor(new Color(204, 204, 204, 255));
		g2D.fillArc(x, y, diametre, diametre, 0, 360);
		g2D.setColor(new Color(0, 0, 0, 255));
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.drawArc(x, y, diametre, diametre, 0, 360);
	}

}
