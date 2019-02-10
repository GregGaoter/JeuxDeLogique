package com.openclassrooms.jeuxlogiques.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.modele.Modele;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueJeu;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePionInitiale;
import com.openclassrooms.jeuxlogiques.vue.plateau.ConstructeurPlateauPion;
import com.openclassrooms.jeuxlogiques.vue.plateau.ConstructeurPlateauProposition;
import com.openclassrooms.jeuxlogiques.vue.plateau.ConstructeurPlateauReponse;
import com.openclassrooms.jeuxlogiques.vue.plateau.ConstructeurPlateauSolution;
import com.openclassrooms.jeuxlogiques.vue.plateau.ConstructeurPlateauValidation;
import com.openclassrooms.jeuxlogiques.vue.plateau.DirecteurConstructionPlateau;
import com.openclassrooms.jeuxlogiques.vue.plateau.PlateauJeu;
import com.openclassrooms.jeuxlogiques.vue.plateau.PlateauValidation;
import com.openclassrooms.jeuxlogiques.vue.separateur.AlignementHorizontal;
import com.openclassrooms.jeuxlogiques.vue.separateur.SeparateurVertical;

public class Vue implements Observateur {

	/*
	 * La vue contient une r�f�rence au mod�le et au contr�leur. Le contr�leur n'est
	 * utilis� que par l'interface de contr�le.
	 */
	private Modele modele;
	private Controleur controleur;

	private FabriqueDePion fabriqueDePion;

	private JWindow fenetreDemarrage;
	private JFrame fenetrePrincipale;
	private JPanel panneauPrincipal;
	private JMenuBar barreDeMenu;
	private JMenu menuJeu, menuOption, menuAide;
	private JMenuItem menuItemJeuNouveauJeu, menuItemJeuQuitter, menuItemOptionJeu, menuItemOptionLogs,
			menuItemAideRegles;
	private JToolBar barreOutils;
	private JButton boutonNouveauJeu, boutonOptionJeu, boutonOptionLogs, bouttonAide;
	private JProgressBar barreProgression;

	/*
	 * Le constructeur de la vue re�oit une r�f�rence du mod�le et du contr�leur qui
	 * sont stock�es dans les variables d'instances. La vue s'enregistre comme
	 * observateur dans le constructeur.
	 */
	public Vue(Modele modele, Controleur controleur) {
		this.modele = modele;
		this.controleur = controleur;
		fabriqueDePion = new FabriqueDePionInitiale();
		modele.ajouterObservateur(this);
	}

	public synchronized void creerVue() {

		/*
		 * Look and feel
		 */
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}

		/*
		 * Fen�tre principale
		 */
		fenetrePrincipale = new JFrame("Jeux de logique");
		fenetrePrincipale.setIconImage(new ImageIcon(getClass().getResource("/game_16.png")).getImage());

		/*
		 * Barre de menu
		 */
		barreDeMenu = new JMenuBar();
		fenetrePrincipale.setJMenuBar(barreDeMenu);

		menuJeu = new JMenu("Jeu");
		barreDeMenu.add(menuJeu);

		menuItemJeuNouveauJeu = new JMenuItem("Nouveau jeu",
				new ImageIcon(getClass().getResource("/nouveau_jeu_16.png")));
		menuJeu.add(menuItemJeuNouveauJeu);
		menuItemJeuNouveauJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.lancerNouveauJeu(new DialogueJeu(fenetrePrincipale));
			}
		});

		menuJeu.addSeparator();

		menuItemJeuQuitter = new JMenuItem("Quitter", new ImageIcon(getClass().getResource("/quitter_16.png")));
		menuJeu.add(menuItemJeuQuitter);
		menuItemJeuQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.quitterApplication();
			}
		});

		menuOption = new JMenu("Options");
		barreDeMenu.add(menuOption);

		menuItemOptionJeu = new JMenuItem("Jeux", new ImageIcon(getClass().getResource("/option_jeu_16.png")));
		menuOption.add(menuItemOptionJeu);
		menuItemOptionJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.configurerJeux();
			}
		});

		menuItemOptionLogs = new JMenuItem("Logs", new ImageIcon(getClass().getResource("/option_logs_16.png")));
		menuOption.add(menuItemOptionLogs);
		menuItemOptionLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.configurerLogs();
			}
		});

		menuAide = new JMenu("Aide");
		barreDeMenu.add(menuAide);

		menuItemAideRegles = new JMenuItem("R�gles de jeux", new ImageIcon(getClass().getResource("/aide_16.png")));
		menuAide.add(menuItemAideRegles);
		menuItemAideRegles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.consulterReglesJeux();
			}
		});

		/*
		 * Barre d'outils
		 */
		barreOutils = new JToolBar();
		fenetrePrincipale.add(barreOutils, BorderLayout.NORTH);
		barreOutils.setLayout(new GridBagLayout());
		barreOutils.setAlignmentX(0.0f);
		GridBagConstraints contraintesBarreOutils = new GridBagConstraints();
		contraintesBarreOutils.fill = GridBagConstraints.BOTH;
		contraintesBarreOutils.insets = new Insets(0, 0, 0, 0);
		contraintesBarreOutils.anchor = GridBagConstraints.WEST;
		contraintesBarreOutils.gridx = 1;
		contraintesBarreOutils.gridy = 1;

		boutonNouveauJeu = new JButton("Nouveau Jeu", new ImageIcon(getClass().getResource("/nouveau_jeu_32.png")));
		boutonNouveauJeu.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonNouveauJeu.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonNouveauJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.lancerNouveauJeu(new DialogueJeu(fenetrePrincipale));
			}
		});
		barreOutils.add(boutonNouveauJeu, contraintesBarreOutils);

		contraintesBarreOutils.gridx++;
		barreOutils.add(new SeparateurVertical(20, AlignementHorizontal.Centre), contraintesBarreOutils);

		boutonOptionJeu = new JButton("Options jeux", new ImageIcon(getClass().getResource("/option_jeu_32.png")));
		boutonOptionJeu.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonOptionJeu.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonOptionJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.configurerJeux();
			}
		});
		contraintesBarreOutils.gridx++;
		barreOutils.add(boutonOptionJeu, contraintesBarreOutils);

		boutonOptionLogs = new JButton("Options logs", new ImageIcon(getClass().getResource("/option_logs_32.png")));
		boutonOptionLogs.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonOptionLogs.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonOptionLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.configurerLogs();
			}
		});
		contraintesBarreOutils.gridx++;
		barreOutils.add(boutonOptionLogs, contraintesBarreOutils);

		contraintesBarreOutils.gridx++;
		barreOutils.add(new SeparateurVertical(20, AlignementHorizontal.Centre), contraintesBarreOutils);

		bouttonAide = new JButton("R�gles de jeux", new ImageIcon(getClass().getResource("/aide_32.png")));
		bouttonAide.setVerticalTextPosition(SwingConstants.BOTTOM);
		bouttonAide.setHorizontalTextPosition(SwingConstants.CENTER);
		bouttonAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.consulterReglesJeux();
			}
		});
		contraintesBarreOutils.gridx++;
		barreOutils.add(bouttonAide, contraintesBarreOutils);

		/*
		 * Contraintes des GridBagLayout
		 */
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.insets = new Insets(0, 0, 0, 0);
		contraintes.anchor = GridBagConstraints.CENTER;

		/*
		 * Panneau principal
		 */
		panneauPrincipal = new JPanel();
		fenetrePrincipale.add(panneauPrincipal, BorderLayout.CENTER);
		panneauPrincipal.setLayout(new GridBagLayout());
		panneauPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

		/*
		 * Plateau solution
		 */
		contraintes.gridx = 2;
		contraintes.gridy = 1;
		DirecteurConstructionPlateau directeurConstructionPlateau = new DirecteurConstructionPlateau(
				new ConstructeurPlateauSolution(new PlateauJeu(1, modele.getSolution(), "Solution")));
		directeurConstructionPlateau.construirePlateau();
		panneauPrincipal.add(directeurConstructionPlateau.getPlateau(), contraintes);

		/*
		 * Plateau proposition
		 */
		contraintes.gridx = 2;
		contraintes.gridy = 2;
		directeurConstructionPlateau = new DirecteurConstructionPlateau(new ConstructeurPlateauProposition(
				new PlateauJeu(modele.getNbEssais(), modele.getProposition(), "Proposition")));
		directeurConstructionPlateau.construirePlateau();
		panneauPrincipal.add(directeurConstructionPlateau.getPlateau(), contraintes);

		/*
		 * Plateau r�ponse
		 */
		contraintes.gridx = 3;
		contraintes.gridy = 2;
		directeurConstructionPlateau = new DirecteurConstructionPlateau(
				new ConstructeurPlateauReponse(new PlateauJeu(modele.getNbEssais(), modele.getReponse(), "R�ponse")));
		directeurConstructionPlateau.construirePlateau();
		panneauPrincipal.add(directeurConstructionPlateau.getPlateau(), contraintes);

		/*
		 * Plateau validation
		 */
		contraintes.gridx = 1;
		contraintes.gridy = 2;
		directeurConstructionPlateau = new DirecteurConstructionPlateau(new ConstructeurPlateauValidation(
				new PlateauValidation(modele.getNbEssais(), modele.getValidation(), "Validation")));
		directeurConstructionPlateau.construirePlateau();
		panneauPrincipal.add(directeurConstructionPlateau.getPlateau(), contraintes);

		/*
		 * Plateau pions
		 */
		contraintes.gridx = 1;
		contraintes.gridy = 3;
		contraintes.gridwidth = 3;
		contraintes.fill = GridBagConstraints.NONE;
		directeurConstructionPlateau = new DirecteurConstructionPlateau(
				new ConstructeurPlateauPion(new PlateauJeu(1, modele.getPionsUtilisables(), "Pions")));
		directeurConstructionPlateau.construirePlateau();
		panneauPrincipal.add(directeurConstructionPlateau.getPlateau(), contraintes);
		contraintes.gridwidth = 1;
		contraintes.fill = GridBagConstraints.BOTH;

		/*
		 * Param�tres de la fen�tre principale
		 */
		fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetrePrincipale.pack();
		fenetrePrincipale.setLocationRelativeTo(null);
		fenetrePrincipale.setVisible(true);
	}

	public synchronized void creerFenetreDemarrage() {

		/*
		 * Look and feel
		 */
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}

		fenetreDemarrage = new JWindow();

		JLabel labelImage = new JLabel(new ImageIcon(getClass().getResource("/image_demarrage.png")));
		labelImage.setLayout(new BorderLayout());
		fenetreDemarrage.add(labelImage, BorderLayout.CENTER);

		JPanel panneauBarreProgression = new JPanel();
		panneauBarreProgression.setLayout(new BoxLayout(panneauBarreProgression, BoxLayout.Y_AXIS));
		labelImage.add(panneauBarreProgression, BorderLayout.SOUTH);
		panneauBarreProgression.setBackground(new Color(0, 0, 0, 0));

		JLabel labelDemarrage = new JLabel("D�marrage...");
		labelDemarrage.setAlignmentX(0.15f);
		labelDemarrage.setFont(new Font(labelDemarrage.getFont().getFontName(), Font.ITALIC,
				(int) (labelDemarrage.getFont().getSize() * .95)));
		panneauBarreProgression.add(labelDemarrage);

		barreProgression = new JProgressBar(0, 100);
		panneauBarreProgression.add(barreProgression);
		barreProgression.setStringPainted(true);

		fenetreDemarrage.pack();
		fenetreDemarrage.setLocationRelativeTo(null);
		fenetreDemarrage.setVisible(true);
	}

	public synchronized void runBarreProgression() {
		for (int i = 1; i <= 100; i++) {
			try {
				barreProgression.setValue(i);
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
		}
		fenetreDemarrage.dispose();
	}

	public Modele getModele() {
		return modele;
	}

	public Controleur getControleur() {
		return controleur;
	}

	public void setModele(Modele modele) {
		this.modele = modele;
	}

	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
	}

	public JFrame getFenetrePrincipale() {
		return fenetrePrincipale;
	}

	public FabriqueDePion getFabriqueDePion() {
		return fabriqueDePion;
	}

	public void actualiser() {

	}

}
