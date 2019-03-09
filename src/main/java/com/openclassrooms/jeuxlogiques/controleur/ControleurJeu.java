package com.openclassrooms.jeuxlogiques.controleur;

import java.util.Arrays;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Parametre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.modele.mode.Mode;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueMode;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueOption;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueOptionJoueur;

public class ControleurJeu {

	private final String separateurClef = "-";

	private ModeleJeu modele;
	private Vue vue;

	private Jeu jeu;
	private Mode mode;
	private boolean modeDeveloppeurQ;

	private ServiceDeCalcul serviceDeCalcul;

	private boolean gagnantQ;

	public ControleurJeu(String[] modeDeveloppeur) {
		modele = new ModeleJeu();
		vue = new Vue();
		modele.setVue(vue);
		modele.setControleur(this);
		modele.setJeu(Jeu.LISTE_JEUX[0]);
		jeu = modele.getJeu();
		modele.initialiser();
		vue.setModele(modele);
		vue.setControleur(this);
		modeDeveloppeurQ = modeDeveloppeur.length > 0
				&& Integer.parseInt(modeDeveloppeur[0]) == Parametre.ModeDeveloppeur.getValeur();
		gagnantQ = false;
		// vue.creerFenetreDemarrage();
		// vue.runBarreProgression();
		vue.creerVue();
	}

	public void lancerDialogueJeu(JFrame fenetreProprietaire) {
		modele.initialiser();
		DialogueJeu dialogueJeu = new DialogueJeu(fenetreProprietaire);
		jeu = dialogueJeu.getValeur();
		if (jeu != null) {
			modele.setJeu(jeu);
			modele.setNbPionsUtilisables(jeu.getNbPionsUtilisables());
			modele.setPionsUtilisables(Arrays.asList(jeu.getPionsJeu()));
			setServiceDeCalcul(jeu.getServiceDeCalcul());
			vue.initialiserPanneaux();
			vue.getPanneauPrincipal().repaint();
			lancerMode(fenetreProprietaire);
		}
	}

	private void lancerMode(JFrame fenetreProprietaire) {
		DialogueMode dialogueMode = new DialogueMode(fenetreProprietaire);
		mode = dialogueMode.getValeur();
		if (mode != null) {
			Iterator<Joueur> itDefenseurs = mode.getListeDefenseurs().iterator();
			Iterator<Joueur> itAttaquants = mode.getListeAttaquants().iterator();
			while (itDefenseurs.hasNext()) {
				Joueur defenseur = itDefenseurs.next();
				Joueur attaquant = itAttaquants.next();
				defenseur.setModele(modele);
				attaquant.setModele(modele);
				defenseur.setControleur(this);
				attaquant.setControleur(this);
				modele.setDefenseur(defenseur);
				modele.setAttaquant(attaquant);
				modele.initialiser();
				defenseur.setCombinaisonSecrete(fenetreProprietaire, this);
				modele.setCombinaisonSecrete(defenseur.getCombinaisonSecrete());
			}
			for (int x = 1; x <= modele.getNbPionsCombinaison(); x++)
				vue.setPion(vue.getListePanneauSecret(), getClef(x, 1), PionCommun.Secret);
			for (int x = 1; x <= modele.getNbPionsUtilisables(); x++)
				vue.setPion(vue.getListePanneauPionUtilisable(), getClef(x, 1),
						modele.getPionsUtilisables().get(x - 1));
			for (int y = 1; y <= modele.getNbEssais(); y++) {
				for (int x = 1; x <= modele.getNbPionsCombinaison(); x++) {
					vue.setPion(vue.getListePanneauProposition(), getClef(x, y), PionCommun.Vide);
					vue.setPion(vue.getListePanneauReponse(), getClef(x, y), PionCommun.Vide);
				}
			}
			vue.getListePanneauValidation().get(getClef(1, modele.getCompteurEssais()))
					.remove(vue.getBoutonValidation());
			vue.getListePanneauValidation().get(getClef(1, modele.getNbEssais())).add(vue.getBoutonValidation());
			modele.setCompteurEssais(modele.getNbEssais());
			vue.getMessageNbEssais().setText("01 / " + Integer.toString(modele.getNbEssais()));
			afficherVainqueur("-");
			vue.getMenuItemOptionJeu().setEnabled(true);
			vue.getBoutonOptionJeu().setEnabled(true);
			if (getModeDeveloppeurQ())
				afficherCombinaisonSecrete();
		}
		dialogueMode.dispose();
		lancerTour();
	}

	private void lancerTour() {
		if (!gagnantQ) {
			Iterator<Joueur> itDefenseurs, itAttaquants;
			Joueur defenseur, attaquant;
			itDefenseurs = mode.getListeDefenseurs().iterator();
			itAttaquants = mode.getListeAttaquants().iterator();
			while (itDefenseurs.hasNext() && itAttaquants.hasNext()) {
				defenseur = itDefenseurs.next();
				attaquant = itAttaquants.next();
				modele.setDefenseur(defenseur);
				modele.setCombinaisonSecrete(defenseur.getCombinaisonSecrete());
				attaquant.setCombinaisonProposition();
				// modele.setAttaquant(attaquant);
			}
		}
	}

	public void lancerDialogueOption(JFrame fenetreProprietaire) {
		DialogueOption dialogueOptions = new DialogueOption(fenetreProprietaire);
		int[] listeValeurs = dialogueOptions.getValeur();
		if (listeValeurs != null) {
			modele = new ModeleJeu();
			modele.setVue(vue);
			modele.setControleur(this);
			modele.setJeu(jeu);
			modele.setNbPionsCombinaison(listeValeurs[0]);
			modele.setNbEssais(listeValeurs[1]);
			modele.setNbPionsUtilisables(listeValeurs[2]);
			modele.initialiser();
			vue.setModele(modele);
			vue.initialiserPanneaux();
		}
	}

	public void lancerDialogueOptionJoueur(JFrame fenetreProprietaire) {
		DialogueOptionJoueur dialogueOptionJoueur = new DialogueOptionJoueur(fenetreProprietaire);
		String nomJoueur = dialogueOptionJoueur.getValeur();
		if (nomJoueur != null) {
			modele.setNomJoueur(nomJoueur);
			vue.getToggleButtonJoueur().setText(nomJoueur);
		}
		dialogueOptionJoueur.dispose();
	}

	public void setPionSecret(Pion pion) {
		modele.getDefenseur().setPionSecret(pion);
	}

	public void getPionSecret(int x) {
		modele.getDefenseur().getPionSecret(x);
	}

	private String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	public void getPionProposition(int x) {
		modele.getPionProposition(x);
	}

	public void setPionProposition(Pion pion) {
		modele.setPionProposition(pion);
	}

	public ServiceDeCalcul getServiceDeCalcul() {
		return serviceDeCalcul;
	}

	public void setServiceDeCalcul(ServiceDeCalcul serviceDeCalcul) {
		this.serviceDeCalcul = serviceDeCalcul;
	}

	public Vue getVue() {
		return vue;
	}

	public boolean getModeDeveloppeurQ() {
		return modeDeveloppeurQ;
	}

	public void calculerReponse() {
		modele.setCombinaisonReponse(
				serviceDeCalcul.calculerReponse(modele.getCombinaisonProposition(), modele.getCombinaisonSecrete()));
		vue.getBoutonValidation().setEnabled(false);
		isGagnantQ();
		if (gagnantQ) {
			afficherCombinaisonSecrete();
			afficherVainqueur(modele.getNomJoueur());
		} else {
			modele.decrementerCompteurEssais();
			modele.initialiserCombinaison(modele.getCombinaisonProposition(), modele.getNbPionsCombinaison());
			modele.initialiserCombinaison(modele.getCombinaisonReponse(), modele.getNbPionsCombinaison());
			if (modele.getCompteurEssais() > 0) {
				vue.getListePanneauValidation().get(getClef(1, modele.getCompteurEssais() + 1))
						.remove(vue.getBoutonValidation());
				vue.getListePanneauValidation().get(getClef(1, modele.getCompteurEssais()))
						.add(vue.getBoutonValidation());
				vue.getPanneauPrincipal().repaint();
				int essai = 1 + modele.getNbEssais() - modele.getCompteurEssais();
				vue.getMessageNbEssais().setText((essai < 10 ? "0" : "") + Integer.toString(essai) + " / "
						+ Integer.toString(modele.getNbEssais()));
				lancerTour();
			} else {
				afficherCombinaisonSecrete();
				afficherVainqueur(modele.getNomJoueur());
			}
		}
	}

	private void isGagnantQ() {
		Iterator<Pion> itProposition = modele.getCombinaisonProposition().iterator();
		Iterator<Pion> itSolution = modele.getCombinaisonSecrete().iterator();
		int valeurProposition, valeurSolution;
		int nbPionsCorrects = 0;
		while (itProposition.hasNext() && itSolution.hasNext()) {
			valeurProposition = itProposition.next().getValeur();
			valeurSolution = itSolution.next().getValeur();
			if (valeurProposition == valeurSolution)
				nbPionsCorrects++;
		}
		gagnantQ = nbPionsCorrects == modele.getCombinaisonSecrete().size();
	}

	public void afficherCombinaisonSecrete() {
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++) {
			vue.setPion(vue.getListePanneauSecret(), getClef(i + 1, 1), modele.getCombinaisonSecrete().get(i));
		}
	}

	private void afficherVainqueur(String vainqueur) {
		vue.getVainqueur().setText(vainqueur);
	}

	public void rejouerMemeJeu(JFrame fenetreProprietaire) {
		// lancerSelectionCombinaison(fenetreProprietaire);
	}

	public void setPionSelectionne() {
		vue.getPionSelectionne()
				.setIcon(new ImageIcon(getClass().getResource(modele.getPionSelectionne().getNomImage())));
	}

}