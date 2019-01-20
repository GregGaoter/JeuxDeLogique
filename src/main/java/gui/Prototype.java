package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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

public class Prototype extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Prototype() {

		/*
		 * Layout de la fenêtre
		 */
		getContentPane().setLayout(new BorderLayout(0, 0));

		/*
		 * Barre de menu
		 */

		JMenuBar barreMenu = new JMenuBar();
		setJMenuBar(barreMenu);

		JMenu menuJeu = new JMenu("Jeu");
		barreMenu.add(menuJeu);

		JMenu menuConfiguration = new JMenu("Options");
		barreMenu.add(menuConfiguration);

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
		 * GridBagLayout et contraintes avec options générales
		 */
		GridBagLayout layoutPanneauJeu = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;// Remplissage des composants
		gc.insets = new Insets(0, 0, 0, 0);// Marge entre les composants (top, left, bottom, right)
		gc.anchor = GridBagConstraints.CENTER;// Placement du composant s'il n'occupe pas tout l'espace

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
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 10, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("?", SwingConstants.CENTER), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabelGrand("?", SwingConstants.CENTER), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabelGrand("?", SwingConstants.CENTER), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabelGrand("?", SwingConstants.CENTER), gc);

		/*
		 * Ligne 1 du jeu
		 */
		gc.gridy = 1;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 5;
		gc.insets = new Insets(0, 10, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 6;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 2 du jeu
		 */
		gc.gridy = 2;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 5;
		gc.insets = new Insets(0, 10, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 6;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 3 du jeu
		 */
		gc.gridy = 3;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 5;
		gc.insets = new Insets(0, 10, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 6;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 4 du jeu
		 */
		gc.gridy = 4;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 5;
		gc.insets = new Insets(0, 10, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 6;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 5 du jeu
		 */
		gc.gridx = 0;
		gc.gridy = 5;
		gc.insets = new Insets(0, 0, 0, 10);// (top, left, bottom, right)
		panneauJeu.add(new JButton("Proposer"), gc);
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabel(new ImageIcon(getClass().getResource("/trou_24.png"))), gc);
		gc.gridx = 5;
		gc.insets = new Insets(0, 10, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 6;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabel(), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabel(), gc);

		/*
		 * Ligne 6 du jeu
		 */
		gc.gridy = 6;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("7", SwingConstants.CENTER), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabelGrand("0", SwingConstants.CENTER), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabelGrand("3", SwingConstants.CENTER), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabelGrand("3", SwingConstants.CENTER), gc);
		gc.gridx = 5;
		gc.insets = new Insets(0, 10, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("=", SwingConstants.CENTER), gc);
		gc.gridx = 6;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("=", SwingConstants.CENTER), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabelGrand("+", SwingConstants.CENTER), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabelGrand("=", SwingConstants.CENTER), gc);

		/*
		 * Ligne 7 du jeu
		 */
		gc.gridy = 7;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("1", SwingConstants.CENTER), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabelGrand("2", SwingConstants.CENTER), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabelGrand("6", SwingConstants.CENTER), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabelGrand("9", SwingConstants.CENTER), gc);
		gc.gridx = 5;
		gc.insets = new Insets(0, 10, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("+", SwingConstants.CENTER), gc);
		gc.gridx = 6;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("+", SwingConstants.CENTER), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabelGrand("-", SwingConstants.CENTER), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabelGrand("-", SwingConstants.CENTER), gc);

		/*
		 * Ligne 8 du jeu
		 */
		gc.gridy = 8;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("9", SwingConstants.CENTER), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabelGrand("9", SwingConstants.CENTER), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabelGrand("9", SwingConstants.CENTER), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabelGrand("9", SwingConstants.CENTER), gc);
		gc.gridx = 5;
		gc.insets = new Insets(0, 10, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("+", SwingConstants.CENTER), gc);
		gc.gridx = 6;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("-", SwingConstants.CENTER), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabelGrand("=", SwingConstants.CENTER), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabelGrand("+", SwingConstants.CENTER), gc);

		/*
		 * Ligne 9 du jeu
		 */
		gc.gridy = 9;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("7", SwingConstants.CENTER), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabelGrand("5", SwingConstants.CENTER), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabelGrand("3", SwingConstants.CENTER), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabelGrand("6", SwingConstants.CENTER), gc);
		gc.gridx = 5;
		gc.insets = new Insets(0, 10, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("-", SwingConstants.CENTER), gc);
		gc.gridx = 6;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("+", SwingConstants.CENTER), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabelGrand("-", SwingConstants.CENTER), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabelGrand("+", SwingConstants.CENTER), gc);

		/*
		 * Ligne 10 du jeu
		 */
		gc.gridy = 10;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("4", SwingConstants.CENTER), gc);
		gc.gridx = 2;
		panneauJeu.add(new JLabelGrand("4", SwingConstants.CENTER), gc);
		gc.gridx = 3;
		panneauJeu.add(new JLabelGrand("2", SwingConstants.CENTER), gc);
		gc.gridx = 4;
		panneauJeu.add(new JLabelGrand("4", SwingConstants.CENTER), gc);
		gc.gridx = 5;
		gc.insets = new Insets(0, 10, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("+", SwingConstants.CENTER), gc);
		gc.gridx = 6;
		gc.insets = new Insets(0, 0, 0, 0);// (top, left, bottom, right)
		panneauJeu.add(new JLabelGrand("-", SwingConstants.CENTER), gc);
		gc.gridx = 7;
		panneauJeu.add(new JLabelGrand("+", SwingConstants.CENTER), gc);
		gc.gridx = 8;
		panneauJeu.add(new JLabelGrand("+", SwingConstants.CENTER), gc);

		/*
		 * Chiffres possibles
		 */
		gc.gridx = 0;
		gc.gridy = 13;
		gc.gridwidth = 9;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(10, 0, 0, 0);// (top, left, bottom, right)
		JPanel panneauChiffresPossibles = new JPanel();
		panneauChiffresPossibles.setLayout(new GridLayout(2, 5, 0, 0));
		panneauJeu.add(panneauChiffresPossibles, gc);
		ButtonGroup toggleButtonGroupe = new ButtonGroup();
		JToggleButton toggleButton0 = new JToggleButton("0");
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
