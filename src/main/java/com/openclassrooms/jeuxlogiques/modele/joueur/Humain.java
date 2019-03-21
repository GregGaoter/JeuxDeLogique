package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueSelectionCombinaison;

/**
 * <b>Humain est une classe implémentant les méthodes caractérisant le joueur
 * Humain.</b></br>
 * La classe Humain étend la classe abstraite Joueur.
 * 
 * @see Joueur
 * @author Grégory Gautier
 * @version 1.0
 */
public class Humain extends Joueur {

	private final static Logger log = Logger.getLogger(Humain.class);

	/**
	 * Boîte de dialogue de la sélection de la combinaison secrète.
	 * 
	 * @see DialogueSelectionCombinaison
	 */
	private DialogueSelectionCombinaison dialogueSelectionCombinaison;

	/**
	 * Pion secrète sélectionné par le joueur.
	 * 
	 * @see Humain#Humain()
	 * @see Humain#getPionSecret(int)
	 */
	private Pion pionSecret;

	/**
	 * Constructeur du joueur Humain.</br>
	 * Le constructeur initialise le nom du joueur, l'état déterminant si le joueur
	 * est Humain et son pion secret.
	 */
	public Humain() {
		log.info("Construction du joueur Humain.");
		nom = "Joueur";
		humainQ = true;
		pionSecret = PionCommun.Vide;
	}

	/**
	 * Lance la boîte de dialogue permettant de définir la combinaison secrète,
	 * récupère la combinaison secrète choisit et initialise la nouvelle combinaison
	 * secrète du joueur.
	 * 
	 * @param fenetreParente fenêtre parente de la boîte de dialogue de sélection de
	 *                       la combinaison secrète
	 * @param controleur     contrôleur du jeu
	 */
	public void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur) {
		log.info("Lancement du choix de la combinaison secrète de " + nom + ".");
		dialogueSelectionCombinaison = new DialogueSelectionCombinaison(fenetreParente, modele, controleur);
		List<Pion> combinaisonSecreteDialogue = new ArrayList<>(modele.getNbPionsCombinaison());
		combinaisonSecreteDialogue = dialogueSelectionCombinaison.getValeur();
		if (combinaisonSecreteDialogue != null) {
			combinaisonSecrete.clear();
			combinaisonSecrete.addAll(combinaisonSecreteDialogue);
		}
		dialogueSelectionCombinaison.dispose();
	}

	/**
	 * Cette méthode est vide, ne fait rien. C'est au joueur humain de définir sa
	 * combinaison proposition.
	 */
	public void setCombinaisonProposition() {
	}

	/**
	 * Cette méthode est vide, ne fait rien. Le joueur Humain ne possède pas la
	 * liste de toutes les combinaisons possibles de la partie en cours.
	 */
	protected void setListeCombinaisonsPossibles() {
	}

	/**
	 * Récupère le pion secret sélectionné par le joueur et met à jour la
	 * combinaison secrète de la boîte de dialogue.
	 * 
	 * @param x position en x du pion dans la combinaison secrète.
	 */
	public Pion getPionSecret(int x) {
		dialogueSelectionCombinaison.getCombinaisonSecrete().set(x - 1, pionSecret);
		dialogueSelectionCombinaison.actualiser();
		return pionSecret;
	}

	public void setPionSecret(Pion pionSecret) {
		this.pionSecret = pionSecret;
	}

}