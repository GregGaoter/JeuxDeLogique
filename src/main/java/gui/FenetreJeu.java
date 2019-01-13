package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreJeu extends JFrame {

	private static final long serialVersionUID = 1L;

	public FenetreJeu() {

		/*
		 * Layout de la fenêtre
		 */
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panneauJeu = new JPanel();
		getContentPane().add(panneauJeu, BorderLayout.CENTER);

		/*
		 * Paramètres de la fenêtre
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

}
