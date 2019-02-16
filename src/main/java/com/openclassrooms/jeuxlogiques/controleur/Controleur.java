package com.openclassrooms.jeuxlogiques.controleur;

import com.openclassrooms.jeuxlogiques.modele.Modele;
import com.openclassrooms.jeuxlogiques.vue.Vue;

public class Controleur {

	private Modele modele;
	private Vue vue;

	public Controleur() {
		modele = new Modele();
		vue = new Vue();
		modele.setVue(vue);
		modele.setControleur(this);
		vue.setModele(modele);
		vue.setControleur(this);
		vue.creerVue();
	}

}