package com.openclassrooms.jeuxlogiques.controleur;

import java.util.Arrays;
import java.util.HashMap;

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

	private Mode mode;

	private ServiceDeCalcul serviceDeCalcul;

	public ControleurJeu() {
		modele = new ModeleJeu();
		vue = new Vue();
		modele.setVue(vue);
		modele.setControleur(this);
		modele.initialiser();
		vue.setModele(modele);
		vue.setControleur(this);
		vue.creerVue();
	}

	public void lancerDialogueJeu(JFrame fenetreProprietaire) {
		modele.initialiser();
		DialogueJeu dialogueJeu = new DialogueJeu(fenetreProprietaire);
		Jeu jeu = dialogueJeu.getValeur();
		if (jeu != null) {
			modele.setJeu(jeu);
			modele.setPionsUtilisables(Arrays.asList(jeu.getPionsJeu()));
			setServiceDeCalcul(jeu.getServiceDeCalcul());
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
			vue.getPanneauPrincipal().repaint();
			modele.setCompteurEssais(modele.getNbEssais());
		}
		dialogueSelectionCombinaison.dispose();
	}

	public void lancerDialogueOption(JFrame fenetreProprietaire) {
		DialogueOption dialogueOptions = new DialogueOption(fenetreProprietaire);
		int[] listeValeurs = dialogueOptions.getValeur();
		if (listeValeurs != null) {
			modele.setNbPionsCombinaison(listeValeurs[0]);
			modele.setNbEssais(listeValeurs[1]);
			modele.setNbPionsUtilisables(listeValeurs[2]);
			modele.initialiser();
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
		modele.decrementerCompteurEssais();
		modele.initialiserCombinaison(modele.getCombinaisonProposition(), modele.getNbPionsCombinaison());
		modele.initialiserCombinaison(modele.getCombinaisonReponse(), modele.getNbPionsCombinaison());
		if (modele.getCompteurEssais() < modele.getNbEssais()) {
			vue.getListePanneauValidation().get(getClef(1, modele.getCompteurEssais() + 1))
					.remove(vue.getBoutonValidation());
			vue.getListePanneauValidation().get(getClef(1, modele.getCompteurEssais())).add(vue.getBoutonValidation());
			vue.getPanneauPrincipal().repaint();
		}
	}

}