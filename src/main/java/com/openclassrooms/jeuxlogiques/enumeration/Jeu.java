package com.openclassrooms.jeuxlogiques.enumeration;

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.controleur.ControleurMastermind;
import com.openclassrooms.jeuxlogiques.controleur.ControleurRecherchePlusMoins;
import com.openclassrooms.jeuxlogiques.modele.Modele;
import com.openclassrooms.jeuxlogiques.modele.ModeleMastermind;
import com.openclassrooms.jeuxlogiques.modele.ModeleRecherchePlusMoins;
import com.openclassrooms.jeuxlogiques.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.service.ServiceDeCalculMastermind;
import com.openclassrooms.jeuxlogiques.service.ServiceDeCalculRecherchePlusMoins;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePionMastermind;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePionRecherchePlusMoins;

public enum Jeu {

	RecherchePlusMoins("Recherche +/-", new ModeleRecherchePlusMoins(), new ControleurRecherchePlusMoins(),
			new FabriqueDePionRecherchePlusMoins(), new ServiceDeCalculRecherchePlusMoins()),
	Mastermind("Mastermind", new ModeleMastermind(), new ControleurMastermind(), new FabriqueDePionMastermind(),
			new ServiceDeCalculMastermind());

	private String nom;
	private Modele modele;
	private Controleur controleur;
	private FabriqueDePion fabriqueDePion;
	private ServiceDeCalcul serviceDeCalcul;

	private Jeu(String nom, Modele modele, Controleur controleur, FabriqueDePion fabriqueDePion,
			ServiceDeCalcul serviceDeCalcul) {
		this.nom = nom;
		this.modele = modele;
		this.controleur = controleur;
		this.fabriqueDePion = fabriqueDePion;
		this.serviceDeCalcul = serviceDeCalcul;
	}

	public String getNom() {
		return nom;
	}

	public Modele getModele() {
		return modele;
	}

	public Controleur getControleur() {
		return controleur;
	}

	public FabriqueDePion getFabriqueDePion() {
		return fabriqueDePion;
	}

	public ServiceDeCalcul getServiceDeCalcul() {
		return serviceDeCalcul;
	}

}