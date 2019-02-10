package com.openclassrooms.jeuxlogiques.modele;

import com.openclassrooms.jeuxlogiques.enumeration.Couleur;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public class ModeleMastermind extends Modele {

	protected void setPionsUtilisables(FabriqueDePion fabriqueDePion) {
		for (int i = 0; i < nbPionsUtilisables; i++)
			pionsUtilisables.add(fabriqueDePion.creerPionJeu(Couleur.values()[i]));
	}
}
