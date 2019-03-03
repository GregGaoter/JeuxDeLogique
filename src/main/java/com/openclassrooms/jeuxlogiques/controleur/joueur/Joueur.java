package com.openclassrooms.jeuxlogiques.controleur.joueur;

import java.util.HashMap;

import javax.swing.JFrame;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;
import com.openclassrooms.jeuxlogiques.vue.labelpion.MouseListenerGetPionSecret;

public abstract class Joueur {

	protected final String separateurClef = "-";

	protected ModeleJeu modele;
	protected ControleurJeu controleur;
	protected HashMap<String, JLabelPion> listePanneauSecret;

	public Joueur(ModeleJeu modele, ControleurJeu controleur, HashMap<String, JLabelPion> listePanneauSecret) {
		this.modele = modele;
		this.controleur = controleur;
		listePanneauSecret = new HashMap<>();
		setListePanneauSecret();
	}

	private String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	private void setListePanneauSecret() {
		listePanneauSecret.clear();
		for (int x = 1; x <= modele.getNbPionsCombinaison(); x++)
			listePanneauSecret.put(getClef(x, 1),
					new JLabelPion(PionCommun.Vide, new MouseListenerGetPionSecret(controleur, PionCommun.Vide, x)));
	}

	public abstract HashMap<String, JLabelPion> getCombinaisonSecrete(JFrame fenetreParente, ModeleJeu modele,
			ControleurJeu controleur);

}