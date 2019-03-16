package com.openclassrooms.jeuxlogiques.modele.mode;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.vue.Vue;

public class Duel extends Mode {

	public String getNom() {
		return "Duel";
	}

	protected void setListeDefenseurs() {
		listeDefenseurs.clear();
		listeDefenseurs.add(ordinateur);
		listeDefenseurs.add(humain);
	}

	protected void setListeAttaquants() {
		listeAttaquants.clear();
		listeAttaquants.add(humain);
		listeAttaquants.add(ordinateur);
	}

	public void calculerVainqueur(ModeleJeu modele, ControleurJeu controleur, Vue vue) {
		System.out.println();
		Joueur attaquant = controleur.getAttaquant();
		Joueur defenseur = controleur.getDefenseur();
		if (humain.getCompteurEssais() != ordinateur.getCompteurEssais()) {
			ordinateur.setVainqueurQ(gagnantQ(ordinateur, controleur));
			System.out.println("Attaquant : " + attaquant.getNom());
			System.out.println("Défenseur : " + defenseur.getNom());
			System.out.println("Compteur humain : " + humain.getCompteurEssais());
			System.out.println("Compteur ordinateur : " + ordinateur.getCompteurEssais());
			if (!humain.getVainqueurQ() && !ordinateur.getVainqueurQ()) {
				System.out.println("Humain vainqueur : " + humain.getVainqueurQ());
				System.out.println("Ordinateur vainqueur : " + ordinateur.getVainqueurQ());
				if (humain.getCompteurEssais() > 0) {
					attaquant.decrementerCompteurEssais();
					modele.setCompteurEssais(attaquant.getCompteurEssais());
					attaquant.actualiserPanneauValidation();
					int essai = 1 + modele.getNbEssais() - attaquant.getCompteurEssais();
					vue.getMessageNbEssais().setText((essai < 10 ? "0" : "") + Integer.toString(essai) + " / "
							+ (modele.getNbEssais() < 10 ? "0" : "") + Integer.toString(modele.getNbEssais()));
					controleur.lancerTour();
				}
			} else {
				if (humain.getVainqueurQ() && ordinateur.getVainqueurQ()) {
					System.out.println("Humain vainqueur : " + humain.getVainqueurQ());
					System.out.println("Ordinateur vainqueur : " + ordinateur.getVainqueurQ());
					controleur.afficherVainqueur("Match nul");
				} else {
					System.out.println("Humain vainqueur : " + humain.getVainqueurQ());
					System.out.println("Ordinateur vainqueur : " + ordinateur.getVainqueurQ());
					controleur.afficherVainqueur(humain.getVainqueurQ() ? humain.getNom() : ordinateur.getNom());
				}
				controleur.afficherCombinaisonSecrete(defenseur);
				attaquant.getBoutonValidation().setEnabled(false);
				defenseur.getBoutonValidation().setEnabled(false);
				vue.getBoutonRejouerMemeJeu().setEnabled(true);
			}
		} else {
			System.out.println("Calcul du vainqueur non effectué");
			System.out.println("Attaquant : " + attaquant.getNom());
			System.out.println("Défenseur : " + defenseur.getNom());
			System.out.println("Compteur humain : " + humain.getCompteurEssais());
			System.out.println("Compteur ordinateur : " + ordinateur.getCompteurEssais());
			humain.setVainqueurQ(gagnantQ(humain, controleur));
			attaquant.decrementerCompteurEssais();
			modele.setCompteurEssais(attaquant.getCompteurEssais());
			attaquant.actualiserPanneauValidation();
			int essai = 1 + modele.getNbEssais() - attaquant.getCompteurEssais();
			vue.getMessageNbEssais().setText((essai < 10 ? "0" : "") + Integer.toString(essai) + " / "
					+ (modele.getNbEssais() < 10 ? "0" : "") + Integer.toString(modele.getNbEssais()));
			controleur.lancerTour();
		}
	}

}