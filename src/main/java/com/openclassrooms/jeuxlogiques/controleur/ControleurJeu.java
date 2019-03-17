package com.openclassrooms.jeuxlogiques.controleur;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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

	// private final static Logger log = Logger.getLogger(ControleurJeu.class);

	private final String separateurClef = "-";

	private ModeleJeu modele;
	private Vue vue;

	private Jeu jeu;
	private Mode mode;
	private boolean modeDeveloppeurQ;

	private Joueur defenseur, attaquant;

	private ServiceDeCalcul serviceDeCalcul;

	private boolean gagnantQ;

	private Iterator<Joueur> itDefenseurs, itAttaquants;

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
		vue.creerFenetreDemarrage();
		// vue.runBarreProgression();
		// vue.creerVue();
	}

	public void lancerDialogueJeu(JFrame fenetreProprietaire) {
		DialogueJeu dialogueJeu = new DialogueJeu(fenetreProprietaire);
		jeu = dialogueJeu.getValeur();
		if (jeu != null) {
			initialiserJeu();
			lancerMode(fenetreProprietaire);
		}
	}

	private void initialiserJeu() {
		modele.initialiser();
		modele.setJeu(jeu);
		modele.setNbPionsUtilisables(jeu.getNbPionsUtilisables());
		modele.setPionsUtilisables(Arrays.asList(jeu.getPionsJeu()));
		setServiceDeCalcul(jeu.getServiceDeCalcul(modele.getNbPionsCombinaison()));
		vue.initialiserPanneaux();
		vue.getPanneauPrincipal().repaint();
	}

	private void lancerMode(JFrame fenetreProprietaire) {
		DialogueMode dialogueMode = new DialogueMode(fenetreProprietaire);
		mode = dialogueMode.getValeur();
		if (mode != null)
			initialiserMode(fenetreProprietaire);
		dialogueMode.dispose();
	}

	private void initialiserMode(JFrame fenetreProprietaire) {
		itDefenseurs = mode.getListeDefenseurs().iterator();
		itAttaquants = mode.getListeAttaquants().iterator();
		while (itDefenseurs.hasNext() && itAttaquants.hasNext()) {
			defenseur = itDefenseurs.next();
			attaquant = itAttaquants.next();
			defenseur.setAttaquantQ(false);
			attaquant.setAttaquantQ(true);
			defenseur.ajouterObservateur(vue);
			attaquant.ajouterObservateur(vue);
			defenseur.setModele(modele);
			attaquant.setModele(modele);
			defenseur.setControleur(this);
			attaquant.setControleur(this);
		}
		itDefenseurs = mode.getListeDefenseurs().iterator();
		itAttaquants = mode.getListeAttaquants().iterator();
		defenseur = itDefenseurs.next();
		attaquant = itAttaquants.next();
		defenseur.initialiserJoueur();
		attaquant.initialiserJoueur();
		itDefenseurs = mode.getListeDefenseurs().iterator();
		while (itDefenseurs.hasNext()) {
			defenseur = itDefenseurs.next();
			defenseur.setAttaquantQ(false);
			defenseur.setCombinaisonSecrete(fenetreProprietaire, this);
		}
		itDefenseurs = mode.getListeDefenseurs().iterator();
		itAttaquants = mode.getListeAttaquants().iterator();
		defenseur = itDefenseurs.next();
		attaquant = itAttaquants.next();
		defenseur.setAttaquantQ(false);
		attaquant.setAttaquantQ(true);
		modele.setDefenseur(defenseur);
		modele.setAttaquant(attaquant);
		modele.initialiser();
		for (int x = 1; x <= modele.getNbPionsCombinaison(); x++)
			vue.setPion(vue.getListePanneauSecret(), getClef(x, 1), PionCommun.Secret);
		for (int x = 1; x <= modele.getNbPionsUtilisables(); x++)
			vue.setPion(vue.getListePanneauPionUtilisable(), getClef(x, 1), modele.getPionsUtilisables().get(x - 1));
		for (int y = 1; y <= modele.getNbEssais(); y++) {
			for (int x = 1; x <= modele.getNbPionsCombinaison(); x++) {
				vue.setPion(vue.getListePanneauProposition(), getClef(x, y), PionCommun.Vide);
				vue.setPion(vue.getListePanneauReponse(), getClef(x, y), PionCommun.Vide);
			}
		}
		vue.getMessageNbEssais().setText("01 / " + Integer.toString(modele.getNbEssais()));
		afficherVainqueur("-");
		vue.getMenuItemOptionJeu().setEnabled(true);
		vue.getBoutonOptionJeu().setEnabled(true);
		vue.getToggleButtonJoueur().setEnabled(true);
		vue.getToggleButtonOrdinateur().setEnabled(true);
		vue.getBoutonRejouerMemeJeu().setEnabled(false);
		gagnantQ = false;
		itDefenseurs = mode.getListeDefenseurs().iterator();
		itAttaquants = mode.getListeAttaquants().iterator();
		lancerTour();
	}

	public void lancerTour() {
		if (!gagnantQ) {
			if (!itDefenseurs.hasNext() && !itAttaquants.hasNext()) {
				itDefenseurs = mode.getListeDefenseurs().iterator();
				itAttaquants = mode.getListeAttaquants().iterator();
			}
			defenseur = itDefenseurs.next();
			attaquant = itAttaquants.next();
			defenseur.setAttaquantQ(false);
			attaquant.setAttaquantQ(true);
			modele.setDefenseur(defenseur);
			modele.setAttaquant(attaquant);
			modele.setCombinaisonSecrete(defenseur.getCombinaisonSecrete());
			attaquant.setCombinaisonProposition();
			modele.setCombinaisonProposition(attaquant.getCombinaisonProposition());
			modele.setListePanneauValidation(attaquant.getListePanneauValidation());
			if (modeDeveloppeurQ)
				afficherCombinaisonSecrete(defenseur);
			vue.actualiserPanneauValidation();
			vue.actualiser();
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

	public Joueur getDefenseur() {
		return defenseur;
	}

	public Joueur getAttaquant() {
		return attaquant;
	}

	public boolean getGagnantQ() {
		return gagnantQ;
	}

	public void setGagnantQ(boolean gagnantQ) {
		this.gagnantQ = gagnantQ;
	}

	public void calculerReponse() {
		attaquant.setCombinaisonReponse(
				serviceDeCalcul.calculerReponse(modele.getCombinaisonProposition(), modele.getCombinaisonSecrete()));
		modele.setCombinaisonReponse(attaquant.getCombinaisonReponse());
		vue.actualiser();
		attaquant.getBoutonValidation().setEnabled(false);
		mode.calculerVainqueur(modele, this, vue);
	}

	public void afficherCombinaisonSecrete(Joueur joueur) {
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++)
			vue.setPion(vue.getListePanneauSecret(), getClef(i + 1, 1), joueur.getCombinaisonSecrete().get(i));
	}

	public void afficherVainqueur(String vainqueur) {
		vue.getVainqueur().setText(vainqueur);
	}

	public void rejouerMemeJeu(JFrame fenetreProprietaire) {
		initialiserJeu();
		initialiserMode(fenetreProprietaire);
	}

	public void setPionSelectionne() {
		vue.getPionSelectionne()
				.setIcon(new ImageIcon(getClass().getResource(modele.getPionSelectionne().getNomImage())));
	}

	public List<Pion> setCombinaisonProposition(List<List<Pion>> listeCombinaisonsPossibles,
			List<Pion> combinaisonProposition, List<Pion> combinaisonReponse) {
		return serviceDeCalcul.calculerProposition(listeCombinaisonsPossibles, combinaisonProposition,
				combinaisonReponse);
	}

	public void setVueJoueur() {
		if (defenseur.getHumainQ()) {
			modele.setCombinaisonSecrete(attaquant.getCombinaisonSecrete());
			modele.setCombinaisonProposition(defenseur.getCombinaisonProposition());
			modele.setListePanneauValidation(defenseur.getListePanneauValidation());
			vue.actualiserVueJoueur(defenseur);
		} else {
			modele.setCombinaisonSecrete(defenseur.getCombinaisonSecrete());
			modele.setCombinaisonProposition(attaquant.getCombinaisonProposition());
			modele.setListePanneauValidation(attaquant.getListePanneauValidation());
			vue.actualiserVueJoueur(attaquant);
		}
		vue.actualiserPanneauValidation();
	}

	public void setVueOrdinateur() {
		if (!defenseur.getHumainQ()) {
			modele.setCombinaisonSecrete(attaquant.getCombinaisonSecrete());
			modele.setCombinaisonProposition(defenseur.getCombinaisonProposition());
			modele.setListePanneauValidation(defenseur.getListePanneauValidation());
			vue.actualiserVueJoueur(defenseur);
		} else {
			modele.setCombinaisonSecrete(defenseur.getCombinaisonSecrete());
			modele.setCombinaisonProposition(attaquant.getCombinaisonProposition());
			modele.setListePanneauValidation(attaquant.getListePanneauValidation());
			vue.actualiserVueJoueur(attaquant);
		}
		vue.actualiserPanneauValidation();
	}

}