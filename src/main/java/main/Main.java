package main;

import javax.swing.SwingUtilities;

import gui.FenetreJeu;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FenetreJeu();
			}
		});
	}
}