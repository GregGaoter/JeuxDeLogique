package com.openclassrooms.jeuxlogiques.controleur;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.service.ServiceDeCalcul;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Parametre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;
import com.openclassrooms.jeuxlogiques.modele.joueur.Humain;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.modele.mode.Mode;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueMode;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueOption;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueOptionJoueur;

/**
 * <b>ControleurJeu est une classe permettant de contrôler le déroulement des
 * jeux.</b>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public class ControleurJeu {

	private final static Logger log = Logger.getLogger(ControleurJeu.class);

	/**
	 * Texte séparant les positions en x et en y des pions dans les clefs de
	 * recherche.
	 * 
	 * @see ControleurJeu#getClef(int, int)
	 */
	private final String separateurClef = "-";

	/**
	 * Modèle des jeux.
	 * 
	 * @see ControleurJeu#ControleurJeu(String[])
	 * @see ControleurJeu#initialiserJeu()
	 * @see ControleurJeu#lancerTour()
	 * @see ControleurJeu#lancerDialogueOption(JFrame)
	 * @see ControleurJeu#lancerDialogueOptionJoueur(JFrame)
	 * @see ControleurJeu#getPionProposition(int)
	 * @see ControleurJeu#setPionProposition(Pion)
	 * @see ControleurJeu#calculerReponse()
	 * @see ControleurJeu#afficherCombinaisonSecrete(Joueur)
	 * @see ControleurJeu#setPionSelectionne()
	 * @see ControleurJeu#setVueJoueur()
	 * @see ControleurJeu#setVueOrdinateur()
	 */
	private ModeleJeu modele;

	/**
	 * Vue des jeux.
	 * 
	 * @see ControleurJeu#ControleurJeu(String[])
	 * @see ControleurJeu#initialiserJeu()
	 * @see ControleurJeu#lancerTour()
	 * @see ControleurJeu#lancerDialogueOption(JFrame)
	 * @see ControleurJeu#lancerDialogueOptionJoueur(JFrame)
	 * @see ControleurJeu#calculerReponse()
	 * @see ControleurJeu#afficherCombinaisonSecrete(Joueur)
	 * @see ControleurJeu#afficherVainqueur(String)
	 * @see ControleurJeu#setPionSelectionne()
	 * @see ControleurJeu#setVueJoueur()
	 * @see ControleurJeu#setVueOrdinateur()
	 */
	private Vue vue;

	/**
	 * Service de calcul courant.
	 * 
	 * @see ControleurJeu#calculerReponse()
	 * @see ControleurJeu#setCombinaisonProposition(List, List, List)
	 */
	private ServiceDeCalcul serviceDeCalcul;

	/**
	 * Jeu courant.
	 * 
	 * @see ControleurJeu#ControleurJeu(String[])
	 * @see ControleurJeu#lancerDialogueJeu(JFrame)
	 * @see ControleurJeu#initialiserJeu()
	 * @see ControleurJeu#lancerDialogueOption(JFrame)
	 * @see ControleurJeu#creerMemoire()
	 * @see ControleurJeu#restaurerMemoire(Memoire)
	 */
	private Jeu jeu;

	/**
	 * Mode courant.
	 * 
	 * @see ControleurJeu#lancerTour()
	 * @see ControleurJeu#calculerReponse()
	 */
	private Mode mode;

	/**
	 * Etat d'activation du mode développeur.
	 * 
	 * @see ControleurJeu#ControleurJeu(String[])
	 * @see ControleurJeu#lancerTour()
	 */
	private boolean modeDeveloppeurQ;

	/**
	 * Défenseur courant. Le défenseur est le joueur qui a choisit la combinaison
	 * secrète.
	 * 
	 * @see ControleurJeu#lancerTour()
	 * @see ControleurJeu#setPionSecret(Pion)
	 * @see ControleurJeu#getPionSecret(int)
	 * @see ControleurJeu#setVueJoueur()
	 * @see ControleurJeu#setVueOrdinateur()
	 */
	private Joueur defenseur;

	/**
	 * Attaquant courant. L'attaquant est le joueur qui doit deviner la combinaison
	 * secrète.
	 * 
	 * @see ControleurJeu#lancerTour()
	 * @see ControleurJeu#setPionSecret(Pion)
	 * @see ControleurJeu#getPionSecret(int)
	 * @see ControleurJeu#setVueJoueur()
	 * @see ControleurJeu#setVueOrdinateur()
	 */
	private Joueur attaquant;

	/**
	 * Etat représentant si un joueur est gagnant.
	 * 
	 * @see ControleurJeu#lancerTour()
	 */
	private boolean gagnantQ;

	private Iterator<Joueur> itDefenseurs, itAttaquants;

	/**
	 * Constructeur du contrôleur. Le constructeur instancie le modèle et la vue. Il
	 * initialise le modèle et l'état du mode développeur. Il créé ensuite la vue.
	 * 
	 * @param modeDeveloppeur paramètre passé au lancement de l'application pour
	 *                        activer le mode développeur
	 * @see ModeleJeu
	 * @see Vue
	 * @see Parametre
	 */
	public ControleurJeu(String[] modeDeveloppeur) {
		log.info("Construction du contrôleur.");
		modele = new ModeleJeu();
		vue = new Vue();
		modele.setVue(vue);
		modele.setControleur(this);
		modele.setJeu(Jeu.LISTE_JEUX[1]);
		jeu = modele.getJeu();
		modele.initialiser();
		vue.setModele(modele);
		vue.setControleur(this);
		modeDeveloppeurQ = modeDeveloppeur.length > 0
				&& Integer.parseInt(modeDeveloppeur[0]) == Parametre.ModeDeveloppeur.getValeur();
		vue.creerFenetreDemarrage();
		vue.runBarreProgression();
		vue.creerVue();
	}

	/**
	 * Lance la boîte de dialogue du choix des jeux et récupère le jeu sélectionné.
	 * Initialise ensuite le jeu et lance la sélection du mode.
	 * 
	 * @param fenetreProprietaire fenêtre parente de la boîte de dialogue de
	 *                            sélection des jeux.
	 * @see Memoire
	 * @see DialogueJeu
	 * @see ControleurJeu#creerMemoire()
	 * @see ControleurJeu#restaurerMemoire(Memoire)
	 */
	public void lancerDialogueJeu(JFrame fenetreProprietaire) {
		log.info("Lancement de la boîte de dialogue du choix des jeux.");
		Memoire memoire = creerMemoire();
		DialogueJeu dialogueJeu = new DialogueJeu(fenetreProprietaire);
		jeu = dialogueJeu.getValeur();
		if (jeu != null) {
			initialiserJeu();
			lancerMode(fenetreProprietaire);
		} else
			restaurerMemoire(memoire);
	}

	private void initialiserJeu() {
		log.info("Initialisation du jeu.");
		modele.initialiser();
		modele.setJeu(jeu);
		modele.setNbPionsUtilisables(jeu.getNbPionsUtilisables());
		modele.setPionsUtilisables(Arrays.asList(jeu.getPionsJeu()));
		setServiceDeCalcul(jeu.getServiceDeCalcul(modele.getNbPionsCombinaison()));
		vue.initialiserPanneaux();
		vue.getPanneauPrincipal().repaint();
	}

	private void lancerMode(JFrame fenetreProprietaire) {
		log.info("Lancement de la boîte de dialogue du choix des modes.");
		DialogueMode dialogueMode = new DialogueMode(fenetreProprietaire);
		mode = dialogueMode.getValeur();
		if (mode != null) {
			initialiserMode(fenetreProprietaire);
			log.info("Lancement d'une nouvelle partie.");
			lancerTour();
		}
		dialogueMode.dispose();
	}

	private void initialiserMode(JFrame fenetreProprietaire) {
		log.info("Initialisation du mode.");
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
		vue.getMessageNbEssais()
				.setText("01 / " + (modele.getNbEssais() < 10 ? "0" : "") + Integer.toString(modele.getNbEssais()));
		afficherVainqueur("-");
		vue.getMenuItemOptionJeu().setEnabled(false);
		vue.getBoutonOptionJeu().setEnabled(false);
		vue.getMenuItemOptionJoueur().setEnabled(false);
		vue.getBoutonOptionJoueur().setEnabled(false);
		vue.getToggleButtonJoueur().setEnabled(true);
		vue.getToggleButtonOrdinateur().setEnabled(true);
		vue.getBoutonRejouerMemeJeu().setEnabled(false);
		repeindreFenetrePrincipale();
		gagnantQ = false;
		itDefenseurs = mode.getListeDefenseurs().iterator();
		itAttaquants = mode.getListeAttaquants().iterator();
	}

	/**
	 * Lance un nouveau tour de jeu dans la partie en cours.
	 */
	public void lancerTour() {
		log.info("Lancement d'un tour de jeu.");
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
			if (attaquant.getHumainQ()) {
				attaquant.initialiserCombinaison(attaquant.getCombinaisonProposition());
				defenseur.getBoutonValidation().setEnabled(false);
				attaquant.setNom(modele.getNomJoueur());
			} else
				defenseur.setNom(modele.getNomJoueur());
			attaquant.setCombinaisonProposition();
			modele.setCombinaisonProposition(attaquant.getCombinaisonProposition());
			modele.setListePanneauValidation(attaquant.getListePanneauValidation());
			if (modeDeveloppeurQ)
				afficherCombinaisonSecrete(defenseur);
			vue.actualiserPanneauValidation();
			vue.actualiser();
		}
	}

	private void repeindreFenetrePrincipale() {
		int largeurFenetre = (int) vue.getFenetrePrincipale().getPreferredSize().getWidth();
		int hauteurFenetre = (int) vue.getFenetrePrincipale().getPreferredSize().getHeight();
		vue.getFenetrePrincipale().setSize(new Dimension(largeurFenetre + 1, hauteurFenetre));
		vue.getFenetrePrincipale().pack();
	}

	/**
	 * Lance la boîte de dialogue du réglage des options des jeux et récupère les
	 * valeurs. Initialise ensuite le modèle et met à jour la vue.
	 * 
	 * @param fenetreProprietaire fenêtre parente de la boîte de dialogue du réglage
	 *                            des options des jeux
	 * 
	 * @see DialogueOption
	 */
	public void lancerDialogueOption(JFrame fenetreProprietaire) {
		log.info("Lancement de la boîte de dialogue du réglage des options des jeux.");
		DialogueOption dialogueOptions = new DialogueOption(fenetreProprietaire, modele);
		int[] listeValeurs = dialogueOptions.getValeur();
		if (listeValeurs != null) {
			String nomJoueur = modele.getNomJoueur();
			modele = new ModeleJeu();
			modele.setNomJoueur(nomJoueur);
			modele.setVue(vue);
			modele.setControleur(this);
			modele.setJeu(jeu);
			modele.initialiser();
			modele.setNbPionsCombinaison(listeValeurs[0]);
			modele.setNbEssais(listeValeurs[1]);
			modele.setNbPionsUtilisables(listeValeurs[2]);
			vue.setModele(modele);
			afficherVainqueur("-");
			vue.initialiserPanneaux();
			vue.getBoutonRejouerMemeJeu().setEnabled(false);
			repeindreFenetrePrincipale();
		}
	}

	/**
	 * Lance la boîte de dialogue du réglage du nom du joueur et récupère la valeur.
	 * Initialise ensuite à jour le modèle et la vue.
	 * 
	 * @param fenetreProprietaire fenêtre parente de la boîte de dialogue du réglage
	 *                            du nom du joueur
	 * 
	 * @see DialogueOptionJoueur
	 */
	public void lancerDialogueOptionJoueur(JFrame fenetreProprietaire) {
		log.info("Lancement de la boîte de dialogue du nom du joueur.");
		DialogueOptionJoueur dialogueOptionJoueur = new DialogueOptionJoueur(fenetreProprietaire);
		String nomJoueur = dialogueOptionJoueur.getValeur();
		if (nomJoueur != null) {
			modele.setNomJoueur(nomJoueur);
			vue.getToggleButtonJoueur().setText(nomJoueur);
		}
		dialogueOptionJoueur.dispose();
	}

	/**
	 * Créé une mémoire du jeu courant.
	 * 
	 * @return une mémoire contenant l'état du jeu courant.
	 * @see Memoire
	 */
	public Memoire creerMemoire() {
		return new Memoire(jeu);
	}

	/**
	 * Restaure l'état du jeu précédent gardé en mémoire..
	 * 
	 * @param memoire la mémoire du jeu précédent
	 * @see Memoire#getJeu()
	 */
	public void restaurerMemoire(Memoire memoire) {
		jeu = memoire.getJeu();
	}

	/**
	 * Demande au joueur Humain de définir le pion de la liste des pions utilisables
	 * que le joueur a sélectionné avec la souris afin de constituer la combinaison
	 * secrète. Cette action est valable uniquement dans la boîte de dialogue de
	 * sélection de la combinaison secrète.
	 * 
	 * @param pion le pion sélectionné par un clique de la souris
	 * @see Humain#setPionSecret(Pion)
	 */
	public void setPionSecret(Pion pion) {
		((Humain) defenseur).setPionSecret(pion);
	}

	/**
	 * Demande au joueur Humain le pion de la liste des pions utilisables que le
	 * joueur a sélectionné avec la souris afin de le placer dans la combinaison
	 * secrète. Cette action est valable uniquement dans la boîte de dialogue de
	 * sélection de la combinaison secrète.
	 * 
	 * @param x position en x du pion dans la combinaison secrète.
	 * @see Humain#getPionSecret(int)
	 */
	public void getPionSecret(int x) {
		((Humain) defenseur).getPionSecret(x);
	}

	/**
	 * Récupère la clef de recherche des pions dans les listes des combinaisons.
	 * 
	 * @param x position en x du pion dans la combinaison
	 * @param y position en y du pion dans la combinaison
	 * @return la valeur de la clef de recherche
	 * @see String#valueOf(boolean)
	 */
	private String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	/**
	 * Demande au modèle le pion de la liste des pions utilisables que le joueur a
	 * sélectionné avec la souris afin de le placer dans la combinaison proposition.
	 * Cette action est valable uniquement dans la zone proposition du plateau de
	 * jeu.
	 * 
	 * @param x position en x du pion dans la combinaison proposition.
	 * @see ModeleJeu#getPionProposition(int)
	 */
	public void getPionProposition(int x) {
		modele.getPionProposition(x);
	}

	/**
	 * Demande au modèle de définir le pion de la liste des pions utilisables que le
	 * joueur a sélectionné avec la souris afin de constituer la combinaison
	 * proposition. Cette action est valable uniquement dans la zone des pions
	 * utilisables du plateau de jeu.
	 * 
	 * @param pion le pion sélectionné par un clique de la souris
	 * @see ModeleJeu#setPionProposition(Pion)
	 */
	public void setPionProposition(Pion pion) {
		modele.setPionProposition(pion);
	}

	public Jeu getJeu() {
		return jeu;
	}

	public Mode getMode() {
		return mode;
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

	/**
	 * Lance le calcul de la réponse.
	 * <p>
	 * <ul>
	 * <li>Demande au service de calcul de calculer la réponse</li>
	 * <li>Met à jour la combinaison réponse de l'attaquant et du modèle</li>
	 * <li>Actualise la vue</li>
	 * <li>Demande au mode de calculer le vainqueur</li>
	 * </ul>
	 * </p>
	 */
	public void calculerReponse() {
		log.info("Lancement du calcul de la réponse.");
		attaquant.setCombinaisonReponse(
				serviceDeCalcul.calculerReponse(modele.getCombinaisonProposition(), modele.getCombinaisonSecrete()));
		modele.setCombinaisonReponse(attaquant.getCombinaisonReponse());
		vue.actualiser();
		mode.calculerVainqueur(modele, this, vue);
	}

	/**
	 * Demande à la vue d'afficher la combinaison secrète.
	 * 
	 * @param joueur joueur a qui appartient la combinaison secrète à afficher
	 * @see Joueur
	 */
	public void afficherCombinaisonSecrete(Joueur joueur) {
		log.info("Affichage de la combinaison secrète.");
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++)
			vue.setPion(vue.getListePanneauSecret(), getClef(i + 1, 1), joueur.getCombinaisonSecrete().get(i));
	}

	/**
	 * Demande à la vue d'afficher le vainqueur.
	 * 
	 * @param vainqueur nom du vaiqueur
	 */
	public void afficherVainqueur(String vainqueur) {
		log.info("Affichage du vainqueur.");
		vue.getVainqueur().setText(vainqueur);
	}

	/**
	 * Lance une nouvelle partie du même jeu et du même mode.
	 * 
	 * @param fenetreProprietaire fenêtre parente
	 * @see ControleurJeu#lancerTour()
	 */
	public void rejouerMemeJeu(JFrame fenetreProprietaire) {
		log.info("Lancement pour rejouer au même jeu.");
		initialiserJeu();
		initialiserMode(fenetreProprietaire);
		lancerTour();
	}

	/**
	 * Demande à la vue de mettre à jour le pion sélectionné par le joueur.
	 * 
	 * @see ImageIcon
	 */
	public void setPionSelectionne() {
		vue.getPionSelectionne()
				.setIcon(new ImageIcon(getClass().getResource(modele.getPionSelectionne().getNomImage())));
	}

	/**
	 * Demande au service de calcul de calculer la combinaison proposition de
	 * l'attaquant.
	 * 
	 * @param listeCombinaisonsPossibles liste des combinaisons possibles du jeu.
	 * @param combinaisonProposition     combinaison proposition
	 * @param combinaisonReponse         combinaison réponse
	 * @return la nouvelle combinaison proposition
	 * @see Pion
	 * @see ServiceDeCalcul#calculerProposition(List, List, List)
	 */
	public List<Pion> setCombinaisonProposition(List<List<Pion>> listeCombinaisonsPossibles,
			List<Pion> combinaisonProposition, List<Pion> combinaisonReponse) {
		return serviceDeCalcul.calculerProposition(listeCombinaisonsPossibles, combinaisonProposition,
				combinaisonReponse);
	}

	/**
	 * Demande à la vue de mettre à jour l'affichage du joueur.
	 */
	public void setVueJoueur() {
		log.info("Actualisation de la vue du joueur.");
		if (defenseur.getHumainQ()) {
			modele.setCombinaisonSecrete(attaquant.getCombinaisonSecrete());
			modele.setCombinaisonProposition(defenseur.getCombinaisonProposition());
			modele.setListePanneauValidation(defenseur.getListePanneauValidation());
			vue.actualiserVueJoueur(defenseur, attaquant);
		} else {
			modele.setCombinaisonSecrete(defenseur.getCombinaisonSecrete());
			modele.setCombinaisonProposition(attaquant.getCombinaisonProposition());
			modele.setListePanneauValidation(attaquant.getListePanneauValidation());
			vue.actualiserVueJoueur(attaquant, defenseur);
		}
		vue.actualiserPanneauValidation();
	}

	/**
	 * Demande à la vue de mettre à jour l'affichage de l'ordinateur.
	 */
	public void setVueOrdinateur() {
		log.info("Actualisation de la vue de l'ordinateur.");
		if (!defenseur.getHumainQ()) {
			modele.setCombinaisonSecrete(attaquant.getCombinaisonSecrete());
			modele.setCombinaisonProposition(defenseur.getCombinaisonProposition());
			modele.setListePanneauValidation(defenseur.getListePanneauValidation());
			vue.actualiserVueJoueur(defenseur, attaquant);
		} else {
			modele.setCombinaisonSecrete(defenseur.getCombinaisonSecrete());
			modele.setCombinaisonProposition(attaquant.getCombinaisonProposition());
			modele.setListePanneauValidation(attaquant.getListePanneauValidation());
			vue.actualiserVueJoueur(attaquant, defenseur);
		}
		vue.actualiserPanneauValidation();
	}

}