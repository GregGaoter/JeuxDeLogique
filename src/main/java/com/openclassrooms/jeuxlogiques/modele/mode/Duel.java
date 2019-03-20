package com.openclassrooms.jeuxlogiques.modele.mode;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.vue.Vue;

public class Duel extends Mode {

	private final static Logger log = Logger.getLogger(Duel.class);

	public String getNom() {
		return "Duel";
	}

	protected void setListeDefenseurs() {
		log.info("Cr�ation de la liste des d�fenseurs du mode " + getNom() + ".");
		listeDefenseurs.clear();
		listeDefenseurs.add(ordinateur);
		listeDefenseurs.add(humain);
	}

	protected void setListeAttaquants() {
		log.info("Cr�ation de la liste des attaquants du mode " + getNom() + ".");
		listeAttaquants.clear();
		listeAttaquants.add(humain);
		listeAttaquants.add(ordinateur);
	}

	public void calculerVainqueur(ModeleJeu modele, ControleurJeu controleur, Vue vue) {
		log.info("Calcul du vainqueur du mode Duel.");
		Joueur attaquant = controleur.getAttaquant();
		Joueur defenseur = controleur.getDefenseur();
		if (humain.getCompteurEssais() != ordinateur.getCompteurEssais()) {
			ordinateur.setVainqueurQ(gagnantQ(ordinateur, controleur));
			if (!humain.getVainqueurQ() && !ordinateur.getVainqueurQ()) {
				if (humain.getCompteurEssais() > 0) {
					attaquant.decrementerCompteurEssais();
					modele.setCompteurEssais(attaquant.getCompteurEssais());
					attaquant.actualiserPanneauValidation();
					int essai = 1 + modele.getNbEssais() - attaquant.getCompteurEssais();
					vue.getMessageNbEssais().setText((essai < 10 ? "0" : "") + Integer.toString(essai) + " / "
							+ (modele.getNbEssais() < 10 ? "0" : "") + Integer.toString(modele.getNbEssais()));
					controleur.lancerTour();
				} else {

					controleur.setGagnantQ(true);
					controleur.afficherVainqueur("Match nul");
					attaquant.getBoutonValidation().setEnabled(false);
					controleur.afficherCombinaisonSecrete(defenseur);
				}
			} else {
				if (humain.getVainqueurQ() && ordinateur.getVainqueurQ())
					controleur.afficherVainqueur("Match nul");
				else
					controleur.afficherVainqueur(humain.getVainqueurQ() ? humain.getNom() : ordinateur.getNom());
				controleur.setGagnantQ(true);
				if (humain.getCompteurEssais() > 0) {
					humain.getListePanneauValidation().get(humain.getClef(1, humain.getCompteurEssais())).removeAll();
					humain.getListePanneauValidation().get(humain.getClef(1, humain.getCompteurEssais() + 1))
							.add(humain.getBoutonValidation());
				}
				controleur.afficherCombinaisonSecrete(defenseur);
				vue.getMenuItemOptionJeu().setEnabled(true);
				vue.getBoutonOptionJeu().setEnabled(true);
				vue.getMenuItemOptionJoueur().setEnabled(true);
				vue.getBoutonOptionJoueur().setEnabled(true);
				attaquant.getBoutonValidation().setEnabled(false);
				defenseur.getBoutonValidation().setEnabled(false);
				vue.getBoutonRejouerMemeJeu().setEnabled(true);
			}
		} else {
			humain.setVainqueurQ(gagnantQ(humain, controleur));
			attaquant.decrementerCompteurEssais();
			modele.setCompteurEssais(attaquant.getCompteurEssais());
			attaquant.getBoutonValidation().setEnabled(false);
			attaquant.actualiserPanneauValidation();
			controleur.lancerTour();
		}
	}

	protected void setLoadCombinaisonsPossiblesQ() {
		loadCombinaisonsPossiblesQ = true;
	}

}