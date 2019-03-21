package com.openclassrooms.jeuxlogiques.modele.mode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.joueur.Humain;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.modele.joueur.Ordinateur;
import com.openclassrooms.jeuxlogiques.vue.Vue;

/**
 * <b>Mode est une classe abstraite fournissant des méthodes permettant de
 * caractériser chaque mode. </br>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public abstract class Mode {

	private final static Logger log = Logger.getLogger(Mode.class);

	/**
	 * Liste de tous les modes disponibles. La liste créée une instance de chaque
	 * mode.
	 */
	public static final Mode[] LISTE_MODES = { new Challenger(), new Defenseur(), new Duel() };

	/**
	 * Liste des joueurs défenseurs de la partie en cours.
	 * 
	 * @see Mode#Mode()
	 */
	protected List<Joueur> listeDefenseurs;

	/**
	 * Liste des joueurs attaquants de la partie en cours.
	 * 
	 * @see Mode#Mode()
	 */
	protected List<Joueur> listeAttaquants;

	/**
	 * Joueur Humain.
	 * 
	 * @see Mode#Mode()
	 */
	protected Joueur humain;

	/**
	 * Joueur ordinateur.
	 * 
	 * @see Mode#Mode()
	 */
	protected Joueur ordinateur;

	/**
	 * Contient l'état pour savoir si la liste des combinaisons possibles doit être
	 * charhée.
	 */
	protected boolean loadCombinaisonsPossiblesQ;

	/**
	 * Constructeur du mode.
	 * <p>
	 * Le constructeur initialise :
	 * <ul>
	 * <li>La liste des défenseurs</li>
	 * <li>La liste des attaquants</li>
	 * <li>Un nouveau joueur Humain</li>
	 * <li>Un nouveau joueur ordinateur</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Humain#Humain()
	 * @see Ordinateur#Ordinateur()
	 * @see Mode#setListeDefenseurs()
	 * @see Mode#setListeAttaquants()
	 * @see Mode#setLoadCombinaisonsPossiblesQ()
	 */
	public Mode() {
		log.info("Construction du mode.");
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

	/**
	 * Calcul si un joueur est victorieux à l'issue d'un tour pour les modes
	 * Challenger et Defenseur.</br>
	 * 
	 * @param modele     modèle du jeu
	 * @param controleur controleur du jeu
	 * @param vue        vue du jeu
	 */
	public void calculerVainqueur(ModeleJeu modele, ControleurJeu controleur, Vue vue) {
		log.info("Calcul du vainqueur du mode " + getNom());
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

	/**
	 * Vérifie si le joueur est vainqueur.
	 * 
	 * @param joueur     joueur
	 * @param controleur controleur du jeu
	 * @return true si le joueur est vainqueur, false si le joueur n'est pas
	 *         vainqueur
	 */
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