package com.openclassrooms.jeuxlogiques;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;

public class Main {

	private final static Logger log = Logger.getLogger(Main.class);

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