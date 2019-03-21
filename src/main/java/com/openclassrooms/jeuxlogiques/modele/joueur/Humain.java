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
 * <b>Humain est une classe impl�mentant les m�thodes caract�risant le joueur
 * Humain.</b></br>
 * La classe Humain �tend la classe abstraite Joueur.
 * 
 * @see Joueur
 * @author Gr�gory Gautier
 * @version 1.0
 */
public class Humain extends Joueur {

	private final static Logger log = Logger.getLogger(Humain.class);

	/**
	 * Bo�te de dialogue de la s�lection de la combinaison secr�te.
	 * 
	 * @see DialogueSelectionCombinaison
	 */
	private DialogueSelectionCombinaison dialogueSelectionCombinaison;

	/**
	 * Pion secr�te s�lectionn� par le joueur.
	 * 
	 * @see Humain#Humain()
	 * @see Humain#getPionSecret(int)
	 */
	private Pion pionSecret;

	/**
	 * Constructeur du joueur Humain.</br>
	 * Le constructeur initialise le nom du joueur, l'�tat d�terminant si le joueur
	 * est Humain et son pion secret.
	 */
	public Humain() {
		log.info("Construction du joueur Humain.");
		nom = "Joueur";
		humainQ = true;
		pionSecret = PionCommun.Vide;
	}

	/**
	 * Lance la bo�te de dialogue permettant de d�finir la combinaison secr�te,
	 * r�cup�re la combinaison secr�te choisit et initialise la nouvelle combinaison
	 * secr�te du joueur.
	 * 
	 * @param fenetreParente fen�tre parente de la bo�te de dialogue de s�lection de
	 *                       la combinaison secr�te
	 * @param controleur     contr�leur du jeu
	 */
	public void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur) {
		log.info("Lancement du choix de la combinaison secr�te de " + nom + ".");
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
	 * Cette m�thode est vide, ne fait rien. C'est au joueur humain de d�finir sa
	 * combinaison proposition.
	 */
	public void setCombinaisonProposition() {
	}

	/**
	 * Cette m�thode est vide, ne fait rien. Le joueur Humain ne poss�de pas la
	 * liste de toutes les combinaisons possibles de la partie en cours.
	 */
	protected void setListeCombinaisonsPossibles() {
	}

	/**
	 * R�cup�re le pion secret s�lectionn� par le joueur et met � jour la
	 * combinaison secr�te de la bo�te de dialogue.
	 * 
	 * @param x position en x du pion dans la combinaison secr�te.
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