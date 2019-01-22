package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

import org.apache.commons.lang3.math.NumberUtils;

public class JLabelGrand extends JLabel {

	private static final long serialVersionUID = 1L;

	public JLabelGrand(String string, int pos) {
		super(string, pos);
		setFont(new Font(super.getName(), Font.PLAIN, super.getFont().getSize() * 2));
		super.setPreferredSize(new Dimension(33, 33));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		int diametre = NumberUtils.min(getWidth(), getHeight()) - 1;
		int xCercle = (getWidth() - diametre) / 2;
		int yCercle = (getHeight() - diametre) / 2;
		g2D.setColor(Color.LIGHT_GRAY);
		g2D.fillArc(xCercle, yCercle, diametre, diametre, 0, 360);
		g2D.setColor(Color.BLACK);
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.drawArc(xCercle, yCercle, diametre, diametre, 0, 360);		
	}

}
