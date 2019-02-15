package com.openclassrooms.jeuxlogiques;

import java.awt.EventQueue;

import com.openclassrooms.jeuxlogiques.controleur.Controleur;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Controleur();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}