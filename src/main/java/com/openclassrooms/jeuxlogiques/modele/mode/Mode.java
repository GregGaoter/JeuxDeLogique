package com.openclassrooms.jeuxlogiques.modele.mode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.joueur.Humain;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.modele.joueur.Ordinateur;
import com.openclassrooms.jeuxlogiques.vue.Vue;

public abstract class Mode {

	public static final Mode[] LISTE_MODES = { new Challenger(), new Defenseur(), new Duel() };

	protected List<Joueur> listeDefenseurs;
	protected List<Joueur> listeAttaquants;
	protected Joueur humain, ordinateur;
	protected boolean loadCombinaisonsPossiblesQ;

	public Mode() {
		listeDefenseurs = new ArrayList<>();
		listeAttaquants = new ArrayList<>();
		humain = new Humain();
		ordinateur = new Ordinateur();
		setListeDefenseurs();
		setListeAttaquants();
		setLoadCombinaisonsPossiblesQ();
	}

	public List<Joueur> getListeDefenseurs() {
		return listeDefenseurs;
	}

	public List<Joueur> getListeAttaquants() {
		return listeAttaquants;
	}

	public void calculerVainqueur(ModeleJeu modele, ControleurJeu controleur, Vue vue) {
		Joueur attaquant = controleur.getAttaquant();
		Joueur defenseur = controleur.getDefenseur();
		if (gagnantQ(attaquant, controleur)) {
			controleur.setGagnantQ(true);
			controleur.afficherCombinaisonSecrete(defenseur);
			controleur.afficherVainqueur(attaquant.getNom());
			vue.getMenuItemOptionJeu().setEnabled(true);
			vue.getBoutonOptionJeu().setEnabled(true);
			vue.getMenuItemOptionJoueur().setEnabled(true);
			vue.getBoutonOptionJoueur().setEnabled(true);
			attaquant.getBoutonValidation().setEnabled(false);
			defenseur.getBoutonValidation().setEnabled(false);
			vue.getBoutonRejouerMemeJeu().setEnabled(true);
		} else {
			attaquant.decrementerCompteurEssais();
			modele.setCompteurEssais(attaquant.getCompteurEssais());
			if (attaquant.getCompteurEssais() > 0) {
				attaquant.actualiserPanneauValidation();
				int essai = 1 + modele.getNbEssais() - attaquant.getCompteurEssais();
				vue.getMessageNbEssais().setText((essai < 10 ? "0" : "") + Integer.toString(essai) + " / "
						+ (modele.getNbEssais() < 10 ? "0" : "") + Integer.toString(modele.getNbEssais()));
				controleur.lancerTour();
			} else {
				controleur.setGagnantQ(true);
				controleur.afficherCombinaisonSecrete(defenseur);
				controleur.afficherVainqueur(defenseur.getNom());
				vue.getMenuItemOptionJeu().setEnabled(true);
				vue.getBoutonOptionJeu().setEnabled(true);
				vue.getMenuItemOptionJoueur().setEnabled(true);
				vue.getBoutonOptionJoueur().setEnabled(true);
				attaquant.getBoutonValidation().setEnabled(false);
				defenseur.getBoutonValidation().setEnabled(false);
				vue.getBoutonRejouerMemeJeu().setEnabled(true);
			}
		}
	}

	protected boolean gagnantQ(Joueur joueur, ControleurJeu controleur) {
		Joueur defenseur = controleur.getDefenseur();
		Iterator<Pion> itProposition = joueur.getCombinaisonProposition().iterator();
		Iterator<Pion> itSolution = defenseur.getCombinaisonSecrete().iterator();
		int valeurProposition, valeurSolution;
		int nbPionsCorrects = 0;
		while (itProposition.hasNext() && itSolution.hasNext()) {
			valeurProposition = itProposition.next().getValeur();
			valeurSolution = itSolution.next().getValeur();
			if (valeurProposition == valeurSolution)
				nbPionsCorrects++;
		}
		return nbPionsCorrects == joueur.getCombinaisonProposition().size();
	}

	public boolean getLoadCombinaisonsPossiblesQ() {
		return loadCombinaisonsPossiblesQ;
	}

	public abstract String getNom();

	protected abstract void setListeDefenseurs();

	protected abstract void setListeAttaquants();

	protected abstract void setLoadCombinaisonsPossiblesQ();

}