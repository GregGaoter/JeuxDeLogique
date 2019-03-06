package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Parametre;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public abstract class Joueur {

	private final String separateurClef = "-";

	protected List<Pion> combinaisonSecrete;
	protected List<Pion> combinaisonProposition;
	protected List<Pion> combinaisonReponse;

	protected int compteurEssais;

	public Joueur() {
		combinaisonSecrete = new ArrayList<>();
		combinaisonProposition = new ArrayList<>();
		combinaisonReponse = new ArrayList<>();
		compteurEssais = Parametre.NbEssais.getValeur();
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

	protected String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	public abstract void setCombinaisonSecrete(JFrame fenetreParente, ModeleJeu modele, ControleurJeu controleur);

	public abstract void setCombinaisonProposition();

}