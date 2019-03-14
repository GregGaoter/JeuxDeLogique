package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.lang3.RandomUtils;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public class Ordinateur extends Joueur {

	public Ordinateur() {
		nom = "Ordinateur";
		humainQ = false;
	}

	public void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur) {
		Pion pionAleatoire;
		combinaisonSecrete.clear();
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++) {
			pionAleatoire = modele.getPionsUtilisables().get(RandomUtils.nextInt(0, modele.getNbPionsUtilisables()));
			combinaisonSecrete.add(pionAleatoire);
		}
	}

	public void setCombinaisonProposition() {
		List<Pion> nouvelleCombinaisonProposition = controleur.setCombinaisonProposition(combinaisonProposition,
				combinaisonReponse);
		combinaisonProposition.clear();
		combinaisonProposition.addAll(nouvelleCombinaisonProposition);
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++)
			setPion(listePanneauProposition, getClef(i, compteurEssais), combinaisonProposition.get(i - 1));
	}

}