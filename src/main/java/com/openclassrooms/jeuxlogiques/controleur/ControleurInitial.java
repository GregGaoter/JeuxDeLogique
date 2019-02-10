package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.modele.ModeleInitial;
import com.openclassrooms.jeuxlogiques.vue.Vue;

public class ControleurInitial extends Controleur {

	/*
	 * Le constructeur du contrôleur initial crée le modèle initial et la vue.
	 */
	public ControleurInitial() {
		modele = new ModeleInitial();
		vue = new Vue(modele, this);
		vue.creerFenetreDemarrage();
		vue.runBarreProgression();
		vue.creerVue();
	}

	public void configurerJeux() {
		System.out.println("Configurer les jeux");
	}

}
