package com.openclassrooms.jeuxlogiques;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;

public class Main {

	private final static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		log.info("Démarrage du jeu");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ControleurJeu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}