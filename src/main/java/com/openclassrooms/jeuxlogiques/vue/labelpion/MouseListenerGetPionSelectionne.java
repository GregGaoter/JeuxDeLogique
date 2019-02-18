package com.openclassrooms.jeuxlogiques.vue.labelpion;

import java.awt.event.MouseEvent;

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public class MouseListenerGetPionSelectionne extends MouseListenerPionSelectionne {

	public MouseListenerGetPionSelectionne(Controleur controleur, Pion pion) {
		super(controleur, pion);
	}

	public void mouseClicked(MouseEvent e) {
		controleur.getPionSelectionne();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}