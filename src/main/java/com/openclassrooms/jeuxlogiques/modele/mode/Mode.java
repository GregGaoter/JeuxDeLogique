package com.openclassrooms.jeuxlogiques.modele.mode;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.jeuxlogiques.modele.joueur.Humain;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.modele.joueur.Ordinateur;

public abstract class Mode {

	public static final Mode[] LISTE_MODES = { new Challenger(), new Defenseur(), new Duel() };

	protected List<Joueur> listeDefenseurs;
	protected List<Joueur> listeAttaquants;
	protected Joueur humain, ordinateur;

	public Mode() {
		listeDefenseurs = new ArrayList<>();
		listeAttaquants = new ArrayList<>();
		humain = new Humain();
		ordinateur = new Ordinateur();
		setListeDefenseurs();
		setListeAttaquants();
	}

	public List<Joueur> getListeDefenseurs() {
		return listeDefenseurs;
	}

	public List<Joueur> getListeAttaquants() {
		return listeAttaquants;
	}

	public abstract String getNom();

	protected abstract void setListeDefenseurs();

	protected abstract void setListeAttaquants();

}