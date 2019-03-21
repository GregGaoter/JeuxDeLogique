package com.openclassrooms.jeuxlogiques.vue.labelpion;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

/**
 * <b>JLabelPion est une classe qui représente un JLabel avec une image d'un
 * pion.</b> JLabelPion étend la classe JLabel.
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public class JLabelPion extends JLabel {

	private static final long serialVersionUID = 1L;

	/**
	 * Pion du label.
	 */
	private Pion pion;

	/**
	 * Ecouteur des actions de la souris.
	 */
	private MouseListenerPion mouseListener;

	/**
	 * Constructeur du label contenant l'image d'un pion. Le constructeur initialise
	 * le pion, l'écouteur d'action de la souris et l'image du pion.
	 * 
	 * @param pion          le pion du label
	 * @param mouseListener l'écouteur des actions de la souris du label
	 */
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