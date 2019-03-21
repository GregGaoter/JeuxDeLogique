package com.openclassrooms.jeuxlogiques.vue.labelpion;

import java.awt.event.MouseListener;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

/**
 * <b>MouseListenerPion est une classe abstraite fournissant une méthode
 * définissant le pion sélectionné par un clique de la souris.</b>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public abstract class MouseListenerPion implements MouseListener {

	/**
	 * Pion sélectionné par un clique de la souris.
	 * 
	 * @see MouseListenerPion#setPion(Pion)
	 */
	protected Pion pion;

	/**
	 * Contrôleur du jeu.
	 */
	protected ControleurJeu controleur;

	/**
	 * Constructeur de l'écouteur des actions de la souris. Le constructeur
	 * initialise le contrôleur et le pion.
	 * 
	 * @param controleur contrôleur du jeu
	 * @param pion       pion sélectionné
	 */
	public MouseListenerPion(ControleurJeu controleur, Pion pion) {
		this.controleur = controleur;
		this.pion = pion;
	}

	public void setControleur(ControleurJeu controleur) {
		this.controleur = controleur;
	}

	public abstract void setPion(Pion pion);

}