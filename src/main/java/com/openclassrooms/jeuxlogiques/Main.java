package com.openclassrooms.jeuxlogiques;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;

public class Main {

	// TODO Comment faire pour que les logs ne soient pas �cris dans la console pour
	// pouvoir ex�cuter le programme en ligne de commande.

	private final static Logger log = Logger.getLogger(Main.class);

	// TODO Trouver la commande pour ex�cuter le programme en ligne de commande.

	// TODO OpenClassrooms dit que le mode d�veloppeur doit �tre une propri�t�
	// sp�cifique du fichier de configuration, je ne comprends pas ce point.
	public static void main(String[] modeDeveloppeur) {
		log.info("D�marrage du jeu");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ControleurJeu(modeDeveloppeur);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}