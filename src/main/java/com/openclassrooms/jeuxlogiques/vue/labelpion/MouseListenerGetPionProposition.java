package com.openclassrooms.jeuxlogiques.vue.labelpion;

import java.awt.event.MouseEvent;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public class MouseListenerGetPionProposition extends MouseListenerPion {

	// Position x du pion dans la combinaison proposition
	private int x;

	public MouseListenerGetPionProposition(ControleurJeu controleur, Pion pion, int x) {
		super(controleur, pion);
		this.x = x;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		controleur.getPionProposition(x);
	}

	public void setPion(Pion pion) {
		this.pion = pion;
	}

	public int getX() {
		return x;
	}

}