package com.openclassrooms.jeuxlogiques.vue;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public class JLabelPion extends JLabel {

	private static final long serialVersionUID = 1L;

	public JLabelPion(Pion pion) {
		setIcon(new ImageIcon(getClass().getResource(pion.getNomImage())));
		setText(Integer.toString(pion.getValeur()));
		setHorizontalTextPosition(0);
		setForeground(new Color(0, 0, 0, 0));
	}

}
