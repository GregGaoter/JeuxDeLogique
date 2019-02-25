package com.openclassrooms.jeuxlogiques;

import java.awt.EventQueue;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;

public class Main {

	// private final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		// logger.info("D�marrage du jeu");
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