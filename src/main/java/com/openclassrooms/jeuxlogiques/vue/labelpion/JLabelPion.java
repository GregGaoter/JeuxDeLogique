package com.openclassrooms.jeuxlogiques.vue.labelpion;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public class JLabelPion extends JLabel {

	private static final long serialVersionUID = 1L;

	private Pion pion;
	private Controleur controleur;
	private MouseListenerPion mouseListener;

	public JLabelPion(Pion pion, MouseListenerPion mouseListener) {
		this.pion = pion;
		this.mouseListener = mouseListener;
		setIcon(new ImageIcon(getClass().getResource(pion.getNomImage())));
		setText(Integer.toString(pion.getValeur()));
		setHorizontalTextPosition(0);
		setForeground(new Color(0, 0, 0, 0));
		addMouseListener(mouseListener);
	}

	public Pion getPion() {
		return pion;
	}

	public void setPion(Pion pion) {
		this.pion = pion;
	}

	public MouseListenerPion getMouseListener() {
		return mouseListener;
	}

	public void setMouseListener(MouseListenerPion mouseListener) {
		this.mouseListener = mouseListener;
	}

}