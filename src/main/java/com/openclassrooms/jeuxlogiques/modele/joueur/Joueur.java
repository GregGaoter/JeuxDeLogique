package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.SujetObservable;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.vue.Observateur;
import com.openclassrooms.jeuxlogiques.vue.PanneauBoutonValidation;

public abstract class Joueur implements SujetObservable {

	private final String separateurClef = "-";

	protected List<List<Pion>> listeCombinaisonsPossibles;
	protected int nbCombinaisonsPossibles;

	protected List<Pion> combinaisonSecrete;
	protected List<Pion> combinaisonProposition;
	protected List<Pion> combinaisonReponse;

	protected HashMap<String, Pion> listePanneauProposition;
	protected HashMap<String, Pion> listePanneauReponse;
	protected HashMap<String, JPanel> listePanneauValidation;

	protected List<Observateur> listeObservateurs;

	protected ModeleJeu modele;
	protected ControleurJeu controleur;

	protected String nom;
	protected int compteurEssais;
	private boolean attaquantQ;
	protected boolean humainQ;
	private boolean vainqueurQ;

	private Pion pionSecret;
	private JButton boutonValidation;

	public Joueur() {
		combinaisonSecrete = new ArrayList<>();
		combinaisonProposition = new ArrayList<>();
		combinaisonReponse = new ArrayList<>();
		listePanneauProposition = new HashMap<>();
		listePanneauReponse = new HashMap<>();
		listePanneauValidation = new HashMap<>();
		vainqueurQ = false;
		boutonValidation = new JButton("Valider");
		boutonValidation.setEnabled(false);
		boutonValidation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.calculerReponse();
			}
		});
		listeObservateurs = new ArrayList<>();
	}

	public void initialiserJoueur() {
		compteurEssais = modele.getNbEssais();
		initialiserCombinaison(combinaisonSecrete);
		initialiserCombinaison(combinaisonProposition);
		initialiserCombinaison(combinaisonReponse);
		setListePanneauPion(listePanneauProposition, modele.getNbPionsCombinaison(), modele.getNbEssais(),
				PionCommun.Vide);
		setListePanneauPion(listePanneauReponse, modele.getNbPionsCombinaison(), modele.getNbEssais(), PionCommun.Vide);
		setListePanneauValidation();
		nbCombinaisonsPossibles = (int) Math.pow(modele.getNbPionsUtilisables(), modele.getNbPionsCombinaison());
		listeCombinaisonsPossibles = new ArrayList<>(nbCombinaisonsPossibles);
		setListeCombinaisonsPossibles();
		pionSecret = PionCommun.Vide;
	}

	private void initialiserCombinaison(List<Pion> combinaison) {
		combinaison.clear();
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++)
			combinaison.add(PionCommun.Vide);
	}

	private void setListePanneauPion(HashMap<String, Pion> listePanneau, int xMax, int yMax, Pion pion) {
		listePanneau.clear();
		for (int y = 1; y <= yMax; y++) {
			for (int x = 1; x <= xMax; x++)
				listePanneau.put(getClef(x, y), pion);
		}
	}

	private void setListePanneauValidation() {
		for (int y = 1; y <= modele.getNbEssais(); y++)
			listePanneauValidation.put(getClef(1, y), new PanneauBoutonValidation(boutonValidation));
		listePanneauValidation.get(getClef(1, compteurEssais)).add(boutonValidation);
	}

	public void actualiserPanneauValidation() {
		listePanneauValidation.get(getClef(1, compteurEssais + 1)).removeAll();
		listePanneauValidation.get(getClef(1, compteurEssais)).add(boutonValidation);
	}

	// Source :
	// https://codereview.stackexchange.com/questions/41510/calculate-all-possible-combinations-of-given-characters
	private void setListeCombinaisonsPossibles() {
		int carry;
		int[] indices = new int[modele.getNbPionsCombinaison()];
		ArrayList<Pion> combinaison;
		do {
			combinaison = new ArrayList<>(modele.getNbPionsCombinaison());
			for (int index : indices)
				combinaison.add(modele.getPionsUtilisables().get(index));
			listeCombinaisonsPossibles.add(combinaison);
			carry = 1;
			for (int i = indices.length - 1; i >= 0; i--) {
				if (carry == 0)
					break;
				indices[i] += carry;
				carry = 0;
				if (indices[i] == modele.getNbPionsUtilisables()) {
					carry = 1;
					indices[i] = 0;
				}
			}
		} while (carry != 1);
	}

	public HashMap<String, JPanel> getListePanneauValidation() {
		return listePanneauValidation;
	}

	public void setPion(HashMap<String, Pion> listePanneau, String clef, Pion pion) {
		listePanneau.put(clef, pion);
	}

	public List<List<Pion>> getListeCombinaisonsPossibles() {
		return listeCombinaisonsPossibles;
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
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++)
			setPion(listePanneauReponse, getClef(i, compteurEssais), combinaisonReponse.get(i - 1));
	}

	public HashMap<String, Pion> getListePanneauProposition() {
		return listePanneauProposition;
	}

	public HashMap<String, Pion> getListePanneauReponse() {
		return listePanneauReponse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCompteurEssais() {
		return compteurEssais;
	}

	public void setCompteurEssais(int compteurEssais) {
		this.compteurEssais = compteurEssais;
	}

	public JButton getBoutonValidation() {
		return boutonValidation;
	}

	public boolean getAttaquantQ() {
		return attaquantQ;
	}

	public void setAttaquantQ(boolean attaquantQ) {
		this.attaquantQ = attaquantQ;
	}

	public boolean getHumainQ() {
		return humainQ;
	}

	public boolean getVainqueurQ() {
		return vainqueurQ;
	}

	public void setVainqueurQ(boolean vainqueurQ) {
		this.vainqueurQ = vainqueurQ;
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

	public void decrementerCompteurEssais() {
		compteurEssais--;
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