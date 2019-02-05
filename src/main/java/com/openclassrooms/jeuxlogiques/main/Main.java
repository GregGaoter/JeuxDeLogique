package com.openclassrooms.jeuxlogiques.main;

import com.openclassrooms.jeuxlogiques.controleur.ControleurInitial;
import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleInitial;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;

public class Main {

	public static void main(String[] args) {
		ModeleJeu modele = new ModeleInitial();
		ControleurJeu controleur = new ControleurInitial(modele);
	}

}
