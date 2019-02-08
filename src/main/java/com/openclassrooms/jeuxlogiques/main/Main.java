package com.openclassrooms.jeuxlogiques.main;

import com.openclassrooms.jeuxlogiques.controleur.ControleurInitial;
import com.openclassrooms.jeuxlogiques.modele.Modele;
import com.openclassrooms.jeuxlogiques.modele.ModeleInitial;

public class Main {

	public static void main(String[] args) {
		Modele modele = new ModeleInitial();
		new ControleurInitial(modele);
	}

}
