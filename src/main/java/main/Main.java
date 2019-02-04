package main;

import controleur.ControleurJeu;
import controleur.ControleurRecherchePlusMoins;
import modele.ModeleJeu;
import modele.ModeleRecherchePlusMoins;

public class Main {

	public static void main(String[] args) {
		ModeleJeu modele = new ModeleRecherchePlusMoins();
		ControleurJeu controleur = new ControleurRecherchePlusMoins(modele);
	}

}
