package com.openclassrooms.jeuxlogiques.vue.labelpion;

import java.awt.event.MouseEvent;

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public class MouseListenerGetPionSecret extends MouseListenerPion {

	private int x;

	public MouseListenerGetPionSecret(Controleur controleur, Pion pion, int x) {
		super(controleur, pion);
		this.x = x;
	}

	public void mouseClicked(MouseEvent e) {
		controleur.getPionSecret(x);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void setPion(Pion pion) {
		this.pion = pion;
	}

	public int getX() {
		return x;
	}

}