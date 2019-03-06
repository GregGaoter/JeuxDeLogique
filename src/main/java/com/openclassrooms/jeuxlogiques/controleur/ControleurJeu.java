package com.openclassrooms.jeuxlogiques.controleur;

import java.awt.Color;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Parametre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.modele.mode.Mode;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueMode;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueOption;

public class ControleurJeu {

	private final String separateurClef = "-";

	private ModeleJeu modele;
	private Vue vue;

	private Jeu jeu;
	private boolean modeDeveloppeurQ;

	private ServiceDeCalcul serviceDeCalcul;

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
		Mode mode = dialogueMode.getValeur();
		if (mode != null) {
			Iterator<Joueur> it = mode.getListeDefenseurs().iterator();
			while (it.hasNext())
				it.next().setCombinaisonSecrete(fenetreProprietaire, modele, this);
		}
		dialogueMode.dispose();
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

	public void afficherCombinaisonSecrete() {
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
		// lancerSelectionCombinaison(fenetreProprietaire);
	}

}