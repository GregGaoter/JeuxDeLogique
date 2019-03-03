package com.openclassrooms.jeuxlogiques.controleur.joueur;

import java.util.HashMap;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;

public class Ordinateur extends Joueur {

	public Ordinateur(ModeleJeu modele, ControleurJeu controleur, HashMap<String, JLabelPion> listePanneauSecret) {
		super(modele, controleur, listePanneauSecret);
	}

	public HashMap<String, JLabelPion> getCombinaisonSecrete(JFrame fenetreParente, ModeleJeu modele,
			ControleurJeu controleur) {
		return null;
	}

}