package com.openclassrooms.jeuxlogiques.controleur;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Mode;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueMode;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueOption;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueSelectionCombinaison;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;

public class ControleurJeu {

	private final String separateurClef = "-";

	private ModeleJeu modele;
	private Vue vue;

	private Jeu jeu;
	private Mode mode;

	private ServiceDeCalcul serviceDeCalcul;

	public ControleurJeu() {
		modele = new ModeleJeu();
		vue = new Vue();
		modele.setVue(vue);
		modele.setControleur(this);
		modele.setJeu(Jeu.LISTE_JEUX[0]);
		jeu = modele.getJeu();
		modele.initialiser();
		vue.setModele(modele);
		vue.setControleur(this);
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
			if (mode.getSelectionCombinaisonQ())
				lancerSelectionCombinaison(fenetreProprietaire);
		}
		dialogueMode.dispose();
	}

	private void lancerSelectionCombinaison(JFrame fenetreProprietaire) {
		DialogueSelectionCombinaison dialogueSelectionCombinaison = new DialogueSelectionCombinaison(
				fenetreProprietaire, modele, this);
		HashMap<String, JLabelPion> combinaisonSecrete = dialogueSelectionCombinaison.getValeur();
		if (combinaisonSecrete != null) {
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
			vue.getMessageNbEssais().setText("1 / " + Integer.toString(modele.getNbEssais()));
			vue.getMenuItemOptionJeu().setEnabled(true);
			vue.getBoutonOptionJeu().setEnabled(true);
		}
		dialogueSelectionCombinaison.dispose();
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

	public void setPionSecret(Pion pion) {
		modele.setPionSecret(pion);
	}

	public void getPionSecret(int x) {
		modele.getPionSecret(x);
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

	public void calculerReponse() {
		modele.setCombinaisonReponse(
				serviceDeCalcul.calculerReponse(modele.getCombinaisonProposition(), modele.getCombinaisonSecrete()));
		vue.getBoutonValidation().setEnabled(false);
		if (gagnantQ()) {
			afficherCombinaisonSecrete();
			afficherMessageFinDePartie("GAGNE !", new Color(0, 127, 0));
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
				vue.getMessageNbEssais().setText(Integer.toString(1 + modele.getNbEssais() - modele.getCompteurEssais())
						+ " / " + Integer.toString(modele.getNbEssais()));
			} else {
				afficherCombinaisonSecrete();
				afficherMessageFinDePartie("PERDU !", Color.RED);
			}
		}
	}

	private boolean gagnantQ() {
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
		return nbPionsCorrects == modele.getCombinaisonSecrete().size();
	}

	private void afficherCombinaisonSecrete() {
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++) {
			vue.setPion(vue.getListePanneauSecret(), getClef(i + 1, 1), modele.getCombinaisonSecrete().get(i));
		}
	}

	private void afficherMessageFinDePartie(String message, Color couleur) {
		vue.getMessageFinDePartie().setText(message);
		vue.getMessageFinDePartie().setForeground(couleur);
		vue.getBoutonRejouerMemeJeu().setVisible(true);
	}

	public void rejouerMemeJeu(JFrame fenetreProprietaire) {
		lancerSelectionCombinaison(fenetreProprietaire);
	}

}