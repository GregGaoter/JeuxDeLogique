package com.openclassrooms.jeuxlogiques.main;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.controleur.ControleurRecherchePlusMoins;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleRecherchePlusMoins;

public class Main {

	public static void main(String[] args) {
		ModeleJeu modele = new ModeleRecherchePlusMoins();
		ControleurJeu controleur = new ControleurRecherchePlusMoins(modele);
	}

}
