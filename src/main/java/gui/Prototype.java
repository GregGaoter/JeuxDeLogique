package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gui.enumeration.Chiffre;
import gui.enumeration.Couleur;
import gui.pion.Pion;

public class Prototype extends JFrame {

	private static final long serialVersionUID = 1L;

	JToggleButton toggleButton0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prototype frame = new Prototype();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Prototype() {

		Chiffre _0 = Chiffre.Zero;
		Chiffre _1 = Chiffre.Un;
		Chiffre _2 = Chiffre.Deux;
		Chiffre _3 = Chiffre.Trois;
		Chiffre _4 = Chiffre.Quatre;
		Chiffre _5 = Chiffre.Cinq;
		Chiffre _6 = Chiffre.Six;
		Chiffre _7 = Chiffre.Sept;
		Chiffre _8 = Chiffre.Huit;
		Chiffre _9 = Chiffre.Neuf;

		Couleur blanc = Couleur.Blanc;
		Couleur bleu = Couleur.Bleu;
		Couleur jaune = Couleur.Jaune;
		Couleur noir = Couleur.Noir;
		Couleur rouge = Couleur.Rouge;
		Couleur turquoise = Couleur.Turquoise;
		Couleur vert = Couleur.Vert;
		Couleur violet = Couleur.Violet;

		String moins = "-";
		String plus = "+";
		String egal = "=";

		int startX;

		/*
		 * Barre de menu
		 */

		JMenuBar barreMenu = new JMenuBar();
		setJMenuBar(barreMenu);

		JMenu menuJeu = new JMenu("Jeu");
		barreMenu.add(menuJeu);

		JMenu menuOption = new JMenu("Options");
		barreMenu.add(menuOption);

		JMenu menuAide = new JMenu("Aide");
		barreMenu.add(menuAide);

		/*
		 * Barre d'outils
		 */
		JToolBar barreOutils = new JToolBar();
		getContentPane().add(barreOutils, BorderLayout.NORTH);
		JButton boutonNouveauJeu = new JButton("Nouveau Jeu");
		boutonNouveauJeu.setIcon(new ImageIcon(getClass().getResource("/nouveau_jeu.png")));
		boutonNouveauJeu.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonNouveauJeu.setHorizontalTextPosition(SwingConstants.CENTER);
		barreOutils.add(boutonNouveauJeu);

		/*
		 * GridBagLayout du panneau de jeu et contraintes avec options générales
		 */
		GridBagLayout layoutPanneauJeu = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.CENTER;

		/*
		 * Panneau de jeu
		 */
		JPanel panneauJeu = new JPanel();
		getContentPane().add(panneauJeu, BorderLayout.CENTER);
		panneauJeu.setBorder(new EmptyBorder(10, 10, 10, 10));
		panneauJeu.setLayout(layoutPanneauJeu);

		/*
		 * Placement des chiffres de la combinaison solution
		 */
		gc.gridy = 0;
		startX = 2;
		for (; startX <= 5; startX++) {
			gc.gridx = startX;
			panneauJeu.add(new Pion("?"), gc);
		}

		/*
		 * Ligne 1 du jeu
		 */
		gc.gridy = 2;
		gc.gridx = 2;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 3;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 4;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 5;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 9;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 10;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 2 du jeu
		 */
		gc.gridy = 3;
		gc.gridx = 2;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 3;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 4;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 5;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 9;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 10;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 3 du jeu
		 */
		gc.gridy = 4;
		gc.gridx = 2;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 3;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 4;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 5;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 9;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 10;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 4 du jeu
		 */
		gc.gridy = 5;
		gc.gridx = 2;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 3;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 4;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 5;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 9;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 10;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 5 du jeu
		 */
		gc.gridy = 6;
		gc.gridx = 2;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 3;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 4;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 5;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 9;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 10;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 6 du jeu
		 */
		gc.gridx = 0;
		gc.gridy = 7;
		gc.fill = GridBagConstraints.NONE;
		panneauJeu.add(new JButton("Proposer"), gc);
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 3;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 4;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 5;
		panneauJeu.add(new Pion(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 9;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 10;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 7 du jeu
		 */
		gc.gridy = 8;
		gc.gridx = 2;
		panneauJeu.add(new Pion(_1), gc);
		gc.gridx = 3;
		panneauJeu.add(new Pion(_1), gc);
		gc.gridx = 4;
		panneauJeu.add(new Pion(_8), gc);
		gc.gridx = 5;
		panneauJeu.add(new Pion(_2), gc);
		gc.gridx = 7;
		panneauJeu.add(new Pion(moins), gc);
		gc.gridx = 8;
		panneauJeu.add(new Pion(moins), gc);
		gc.gridx = 9;
		panneauJeu.add(new Pion(egal), gc);
		gc.gridx = 10;
		panneauJeu.add(new Pion(egal), gc);

		/*
		 * Ligne 8 du jeu
		 */
		gc.gridy = 9;
		gc.gridx = 2;
		panneauJeu.add(new Pion(_1), gc);
		gc.gridx = 3;
		panneauJeu.add(new Pion(_5), gc);
		gc.gridx = 4;
		panneauJeu.add(new Pion(_8), gc);
		gc.gridx = 5;
		panneauJeu.add(new Pion(_2), gc);
		gc.gridx = 7;
		panneauJeu.add(new Pion(moins), gc);
		gc.gridx = 8;
		panneauJeu.add(new Pion(egal), gc);
		gc.gridx = 9;
		panneauJeu.add(new Pion(egal), gc);
		gc.gridx = 10;
		panneauJeu.add(new Pion(egal), gc);

		/*
		 * Ligne 9 du jeu
		 */
		gc.gridy = 10;
		gc.gridx = 2;
		panneauJeu.add(new Pion(_1), gc);
		gc.gridx = 3;
		panneauJeu.add(new Pion(_3), gc);
		gc.gridx = 4;
		panneauJeu.add(new Pion(_4), gc);
		gc.gridx = 5;
		panneauJeu.add(new Pion(_0), gc);
		gc.gridx = 7;
		panneauJeu.add(new Pion(moins), gc);
		gc.gridx = 8;
		panneauJeu.add(new Pion(moins), gc);
		gc.gridx = 9;
		panneauJeu.add(new Pion(plus), gc);
		gc.gridx = 10;
		panneauJeu.add(new Pion(plus), gc);

		/*
		 * Ligne 10 du jeu
		 */
		gc.gridy = 11;
		gc.gridx = 2;
		panneauJeu.add(new Pion(_4), gc);
		gc.gridx = 3;
		panneauJeu.add(new Pion(_8), gc);
		gc.gridx = 4;
		panneauJeu.add(new Pion(_3), gc);
		gc.gridx = 5;
		panneauJeu.add(new Pion(_3), gc);
		gc.gridx = 7;
		panneauJeu.add(new Pion(moins), gc);
		gc.gridx = 8;
		panneauJeu.add(new Pion(moins), gc);
		gc.gridx = 9;
		panneauJeu.add(new Pion(plus), gc);
		gc.gridx = 10;
		panneauJeu.add(new Pion(moins), gc);

		/*
		 * Séparateurs verticaux
		 */
		gc.gridy = 1;
		gc.gridx = 0;
		panneauJeu.add(Box.createVerticalStrut(10), gc);
		gc.gridy = 12;
		panneauJeu.add(Box.createVerticalStrut(10), gc);

		/*
		 * Séparateurs horizontaux
		 */
		gc.gridy = 0;
		gc.gridx = 1;
		panneauJeu.add(Box.createHorizontalStrut(10), gc);
		gc.gridx = 6;
		panneauJeu.add(Box.createHorizontalStrut(10), gc);
		/*
		 * Chiffres possibles
		 */
		gc.gridx = 0;
		gc.gridy = 13;
		gc.gridwidth = 11;
		gc.fill = GridBagConstraints.NONE;
		JPanel panneauChiffresPossibles = new JPanel();
		panneauChiffresPossibles.setLayout(new GridLayout(2, 5, 0, 0));
		panneauJeu.add(panneauChiffresPossibles, gc);
		ButtonGroup toggleButtonGroupe = new ButtonGroup();
		toggleButton0 = new JToggleButton("0");
		toggleButtonGroupe.add(toggleButton0);
		panneauChiffresPossibles.add(toggleButton0);
		JToggleButton toggleButton1 = new JToggleButton("1");
		toggleButtonGroupe.add(toggleButton1);
		panneauChiffresPossibles.add(toggleButton1);
		JToggleButton toggleButton2 = new JToggleButton("2");
		toggleButtonGroupe.add(toggleButton2);
		panneauChiffresPossibles.add(toggleButton2);
		JToggleButton toggleButton3 = new JToggleButton("3");
		toggleButtonGroupe.add(toggleButton3);
		panneauChiffresPossibles.add(toggleButton3);
		JToggleButton toggleButton4 = new JToggleButton("4");
		toggleButtonGroupe.add(toggleButton4);
		panneauChiffresPossibles.add(toggleButton4);
		JToggleButton toggleButton5 = new JToggleButton("5");
		toggleButtonGroupe.add(toggleButton5);
		panneauChiffresPossibles.add(toggleButton5);
		JToggleButton toggleButton6 = new JToggleButton("6");
		toggleButtonGroupe.add(toggleButton6);
		panneauChiffresPossibles.add(toggleButton6);
		JToggleButton toggleButton7 = new JToggleButton("7");
		toggleButtonGroupe.add(toggleButton7);
		panneauChiffresPossibles.add(toggleButton7);
		JToggleButton toggleButton8 = new JToggleButton("8");
		toggleButtonGroupe.add(toggleButton8);
		panneauChiffresPossibles.add(toggleButton8);
		JToggleButton toggleButton9 = new JToggleButton("9");
		toggleButtonGroupe.add(toggleButton9);
		panneauChiffresPossibles.add(toggleButton9);

		/*
		 * Paramètres de la fenêtre
		 */
		setTitle("Recherche +/-");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}

}
