package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.modele.ModeleInitial;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePionInitiale;

public class ControleurInitial extends Controleur {

	/*
	 * Le constructeur du contrôleur initial crée le modèle initial et la vue.
	 */
	public ControleurInitial() {
		modele = new ModeleInitial();
		fabriqueDePion = new FabriqueDePionInitiale();
		vue = new Vue(modele, this);
		modele.initialiser(fabriqueDePion);
		vue.creerFenetreDemarrage();
		vue.runBarreProgression();
		vue.creerVue();
	}

	public void configurerJeux() {
		System.out.println("Configurer les jeux");
	}

}
