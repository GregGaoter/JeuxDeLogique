package com.openclassrooms.jeuxlogiques.vue.labelpion;

import java.awt.event.MouseEvent;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public class MouseListenerSetPionProposition extends MouseListenerPion {

	public MouseListenerSetPionProposition(ControleurJeu controleur, Pion pion) {
		super(controleur, pion);
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		// controleur.setPionProposition(pion);
	}

	public void mouseReleased(MouseEvent e) {
		controleur.setPionProposition(pion);
	}

	public void setPion(Pion pion) {
		this.pion = pion;
	}

}