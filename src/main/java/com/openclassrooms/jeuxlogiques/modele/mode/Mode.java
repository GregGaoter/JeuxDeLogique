package com.openclassrooms.jeuxlogiques.modele.mode;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;

public abstract class Mode {

	public static final Mode[] LISTE_MODES = { new Challenger(), new Defenseur(), new Duel() };

	protected List<Joueur> listeDefenseurs;
	protected List<Joueur> listeAttaquants;

	public Mode() {
		listeDefenseurs = new ArrayList<>();
		listeAttaquants = new ArrayList<>();
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