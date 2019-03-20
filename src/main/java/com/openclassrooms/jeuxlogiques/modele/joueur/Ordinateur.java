package com.openclassrooms.jeuxlogiques.modele.joueur;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public class Ordinateur extends Joueur {

	private final static Logger log = Logger.getLogger(Ordinateur.class);

	public Ordinateur() {
		log.info("Construction du joueur ordinateur.");
		nom = "Ordinateur";
		humainQ = false;
	}

	public void setCombinaisonSecrete(JFrame fenetreParente, ControleurJeu controleur) {
		log.info("Lancement du choix de la combinaison secrète de l'ordinateur.");
		Pion pionAleatoire;
		combinaisonSecrete.clear();
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++) {
			pionAleatoire = modele.getPionsUtilisables().get(RandomUtils.nextInt(0, modele.getNbPionsUtilisables()));
			combinaisonSecrete.add(pionAleatoire);
		}
	}

	public void setCombinaisonProposition() {
		log.info("Lancement du choix de la combinaison proposition de l'ordinateur.");
		List<Pion> nouvelleCombinaisonProposition = controleur.setCombinaisonProposition(listeCombinaisonsPossibles,
				combinaisonProposition, combinaisonReponse);
		combinaisonProposition.clear();
		combinaisonProposition.addAll(nouvelleCombinaisonProposition);
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++)
			setPion(listePanneauProposition, getClef(i, compteurEssais), combinaisonProposition.get(i - 1));
	}

	// Source :
	// https://codereview.stackexchange.com/questions/41510/calculate-all-possible-combinations-of-given-characters
	protected void setListeCombinaisonsPossibles() {
		log.info("Calcul de la liste de toutes les combinaisons possibles pour l'ordinateur.");
		if (controleur.getJeu().getLoadCombinaisonsPossiblesQ()
				&& controleur.getMode().getLoadCombinaisonsPossiblesQ()) {
			nbCombinaisonsPossibles = (int) Math.pow(modele.getNbPionsUtilisables(), modele.getNbPionsCombinaison());
			listeCombinaisonsPossibles = new ArrayList<>(nbCombinaisonsPossibles);
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
	}

}