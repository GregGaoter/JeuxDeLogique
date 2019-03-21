package com.openclassrooms.jeuxlogiques.vue.labelpion;

import java.awt.event.MouseListener;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

/**
 * <b>MouseListenerPion est une classe abstraite fournissant une m�thode
 * d�finissant le pion s�lectionn� par un clique de la souris.</b>
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public abstract class MouseListenerPion implements MouseListener {

	/**
	 * Pion s�lectionn� par un clique de la souris.
	 * 
	 * @see MouseListenerPion#setPion(Pion)
	 */
	protected Pion pion;

	/**
	 * Contr�leur du jeu.
	 */
	protected ControleurJeu controleur;

	/**
	 * Constructeur de l'�couteur des actions de la souris. Le constructeur
	 * initialise le contr�leur et le pion.
	 * 
	 * @param controleur contr�leur du jeu
	 * @param pion       pion s�lectionn�
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