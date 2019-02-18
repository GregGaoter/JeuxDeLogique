package com.openclassrooms.jeuxlogiques.vue.labelpion;

import java.awt.event.MouseListener;

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public abstract class MouseListenerPionSelectionne implements MouseListener {

	protected Pion pion;

	protected Controleur controleur;

	public MouseListenerPionSelectionne(Controleur controleur, Pion pion) {
		this.controleur = controleur;
		this.pion = pion;
	}

	public void setPion(Pion pion) {
		this.pion = pion;
	}

}