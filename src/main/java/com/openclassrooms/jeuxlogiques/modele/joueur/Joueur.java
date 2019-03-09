package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;

public abstract class Joueur {

	private final String separateurClef = "-";

	protected List<Pion> combinaisonSecrete;
	protected List<Pion> combinaisonProposition;
	protected List<Pion> combinaisonReponse;

	protected ModeleJeu modele;
	protected ControleurJeu controleur;

	protected int compteurEssais;

	private Pion pionSecret;

	public Joueur() {
		combinaisonSecrete = new ArrayList<>();
		combinaisonProposition = new ArrayList<>();
		combinaisonReponse = new ArrayList<>();
	}

	public void initialiserJoueur() {
		initialiserCombinaison(combinaisonSecrete);
		initialiserCombinaison(combinaisonProposition);
		initialiserCombinaison(combinaisonReponse);
		compteurEssais = modele.getNbEssais();
		pionSecret = PionCommun.Vide;
	}

	private void initialiserCombinaison(List<Pion> combinaison) {
		combinaison.clear();
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++)
			combinaison.add(PionCommun.Vide);
	}

	public List<Pion> getCombinaisonSecrete() {
		return combinaisonSecrete;
	}

	public List<Pion> getCombinaisonProposition() {
		return combinaisonProposition;
	}

	public List<Pion> getCombinaisonReponse() {
		return combinaisonReponse;
	}

	public void setCombinaisonReponse(List<Pion> combinaisonReponse) {
		this.combinaisonReponse = combinaisonReponse;
	}

	public int getCompteurEssais() {
		return compteurEssais;
	}

	public void setCompteurEssais(int compteurEssais) {
		this.compteurEssais = compteurEssais;
	}

	public void setModele(ModeleJeu modele) {
		this.modele = modele;
		initialiserJoueur();
	}

	public void setControleur(ControleurJeu controleur) {
		this.controleur = controleur;
	}

	public Pion getPionSecret(int x) {
		combinaisonSecrete.set(x - 1, pionSecret);
		return pionSecret;
	}

	public void setPionSecret(Pion pionSecret) {
		this.pionSecret = pionSecret;
	}

	protected String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	public abstract void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur);

	public abstract void setCombinaisonProposition();

}