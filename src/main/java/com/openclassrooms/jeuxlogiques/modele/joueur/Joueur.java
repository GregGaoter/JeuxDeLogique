package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.SujetObservable;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.vue.Observateur;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;
import com.openclassrooms.jeuxlogiques.vue.labelpion.MouseListenerGetPionProposition;

public abstract class Joueur implements SujetObservable {

	private final String separateurClef = "-";

	protected List<Pion> combinaisonSecrete;
	protected List<Pion> combinaisonProposition;
	protected List<Pion> combinaisonReponse;

	protected HashMap<String, JLabelPion> listePanneauProposition;
	protected HashMap<String, JLabelPion> listePanneauReponse;
	protected HashMap<String, JPanel> listePanneauValidation;

	protected List<Observateur> listeObservateurs;

	protected ModeleJeu modele;
	protected ControleurJeu controleur;

	protected int compteurEssais;

	private Pion pionSecret;

	public Joueur() {
		combinaisonSecrete = new ArrayList<>();
		combinaisonProposition = new ArrayList<>();
		combinaisonReponse = new ArrayList<>();
		listePanneauProposition = new HashMap<>();
		listePanneauReponse = new HashMap<>();
		listePanneauValidation = new HashMap<>();
		listeObservateurs = new ArrayList<>();
	}

	public void initialiserJoueur() {
		initialiserCombinaison(combinaisonSecrete);
		initialiserCombinaison(combinaisonProposition);
		initialiserCombinaison(combinaisonReponse);
		setListePanneauPion(listePanneauProposition, modele.getNbPionsCombinaison(), modele.getNbEssais(),
				PionCommun.Vide);
		setListePanneauPion(listePanneauReponse, modele.getNbPionsCombinaison(), modele.getNbEssais(), PionCommun.Vide);
		compteurEssais = modele.getNbEssais();
		pionSecret = PionCommun.Vide;
	}

	private void initialiserCombinaison(List<Pion> combinaison) {
		combinaison.clear();
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++)
			combinaison.add(PionCommun.Vide);
	}

	private void setListePanneauPion(HashMap<String, JLabelPion> listePanneau, int xMax, int yMax, Pion pion) {
		listePanneau.clear();
		for (int y = 1; y <= yMax; y++) {
			for (int x = 1; x <= xMax; x++)
				listePanneau.put(getClef(x, y),
						new JLabelPion(pion, new MouseListenerGetPionProposition(controleur, pion, x)));
		}
	}

	public void setPion(HashMap<String, JLabelPion> listePanneau, String clef, Pion pion) {
		listePanneau.get(clef).getMouseListener().setPion(pion);
		listePanneau.get(clef).setIcon(new ImageIcon(getClass().getResource(pion.getNomImage())));
		listePanneau.get(clef).setText(Integer.toString(pion.getValeur()));
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

	public HashMap<String, JLabelPion> getListePanneauProposition() {
		return listePanneauProposition;
	}

	public HashMap<String, JLabelPion> getListePanneauReponse() {
		return listePanneauReponse;
	}

	public HashMap<String, JPanel> getListePanneauValidation() {
		return listePanneauValidation;
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

	public String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	public void ajouterObservateur(Observateur observateur) {
		if (!listeObservateurs.contains(observateur))
			listeObservateurs.add(observateur);
	}

	public void supprimerObservateur(Observateur observateur) {
		listeObservateurs.remove(observateur);
	}

	public void notifierObservateur() {
		for (Observateur observateur : listeObservateurs)
			observateur.actualiser();
	}

	public abstract void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur);

	public abstract void setCombinaisonProposition();

}