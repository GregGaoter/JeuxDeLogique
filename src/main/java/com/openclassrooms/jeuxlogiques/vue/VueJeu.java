package com.openclassrooms.jeuxlogiques.vue;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueJeu;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePionInitiale;
import com.openclassrooms.jeuxlogiques.vue.plateau.PlateauPions;
import com.openclassrooms.jeuxlogiques.vue.plateau.PlateauProposition;
import com.openclassrooms.jeuxlogiques.vue.plateau.PlateauReponse;
import com.openclassrooms.jeuxlogiques.vue.plateau.PlateauSolution;
import com.openclassrooms.jeuxlogiques.vue.plateau.PlateauValidation;
import com.openclassrooms.jeuxlogiques.vue.separateur.AlignementHorizontal;
import com.openclassrooms.jeuxlogiques.vue.separateur.SeparateurVertical;

public class VueJeu implements Observateur {

	/*
	 * La vue contient une référence au modèle et au contrôleur. Le contrôleur n'est
	 * utilisé que par l'interface de contrôle.
	 */
	private ModeleJeu modele;
	private ControleurJeu controleur;

	private FabriqueDePion fabriqueDePion;

	private JFrame fenetrePrincipale;
	private JPanel panneauPrincipal;
	private JMenuBar barreDeMenu;
	private JMenu menuJeu, menuOption, menuAide;
	private JMenuItem menuItemJeuNouveauJeu, menuItemJeuQuitter, menuItemOptionJeu, menuItemOptionLogs,
			menuItemAideRegles;
	private JToolBar barreOutils;
	private JButton boutonNouveauJeu, boutonOptionJeu, boutonOptionLogs, bouttonAide;

	/*
	 * Le constructeur de la vue reçoit une référence du modèle et du contrôleur qui
	 * sont stockées dans les variables d'instances. La vue s'enregistre comme
	 * observateur dans le constructeur.
	 */
	public VueJeu(ModeleJeu modele, ControleurJeu controleur) {
		this.modele = modele;
		this.controleur = controleur;
		fabriqueDePion = new FabriqueDePionInitiale();
		modele.ajouterObservateur(this);
	}

	public void creerVue() {

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
		 * Fenêtre principale
		 */
		fenetrePrincipale = new JFrame("Jeux de logique");
		fenetrePrincipale.setIconImage(new ImageIcon(getClass().getResource("/coffre_fort_16.png")).getImage());

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

		menuItemAideRegles = new JMenuItem("Règles de jeux", new ImageIcon(getClass().getResource("/aide_16.png")));
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

		boutonNouveauJeu = new JButton("Nouveau Jeu", new ImageIcon(getClass().getResource("/nouveau_jeu_32.png")));
		boutonNouveauJeu.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonNouveauJeu.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonNouveauJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.lancerNouveauJeu(new DialogueJeu(fenetrePrincipale));
			}
		});
		barreOutils.add(boutonNouveauJeu);

		barreOutils.add(new SeparateurVertical(20, AlignementHorizontal.Centre));

		boutonOptionJeu = new JButton("Options jeux", new ImageIcon(getClass().getResource("/option_jeu_32.png")));
		boutonOptionJeu.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonOptionJeu.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonOptionJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.configurerJeux();
			}
		});
		barreOutils.add(boutonOptionJeu);

		boutonOptionLogs = new JButton("Options logs", new ImageIcon(getClass().getResource("/option_logs_32.png")));
		boutonOptionLogs.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonOptionLogs.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonOptionLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.configurerLogs();
			}
		});
		barreOutils.add(boutonOptionLogs);

		barreOutils.add(new SeparateurVertical(20, AlignementHorizontal.Centre));

		bouttonAide = new JButton("Règles de jeux", new ImageIcon(getClass().getResource("/aide_32.png")));
		bouttonAide.setVerticalTextPosition(SwingConstants.BOTTOM);
		bouttonAide.setHorizontalTextPosition(SwingConstants.CENTER);
		bouttonAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.consulterReglesJeux();
			}
		});
		barreOutils.add(bouttonAide);

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
		panneauPrincipal.add(new PlateauSolution(this), contraintes);

		/*
		 * Plateau proposition
		 */
		contraintes.gridx = 2;
		contraintes.gridy = 2;
		panneauPrincipal.add(new PlateauProposition(this), contraintes);

		/*
		 * Plateau réponse
		 */
		contraintes.gridx = 3;
		contraintes.gridy = 2;
		panneauPrincipal.add(new PlateauReponse(this), contraintes);

		/*
		 * Plateau validation
		 */
		contraintes.gridx = 1;
		contraintes.gridy = 2;
		panneauPrincipal.add(new PlateauValidation(this), contraintes);

		/*
		 * Plateau pions
		 */
		contraintes.gridx = 1;
		contraintes.gridy = 3;
		contraintes.gridwidth = 3;
		contraintes.fill = GridBagConstraints.NONE;
		panneauPrincipal.add(new PlateauPions(this), contraintes);
		contraintes.gridwidth = 1;
		contraintes.fill = GridBagConstraints.BOTH;

		/*
		 * Paramètres de la fenêtre principale
		 */
		fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetrePrincipale.pack();
		fenetrePrincipale.setLocationRelativeTo(null);
		fenetrePrincipale.setVisible(true);
	}

	public ModeleJeu getModele() {
		return modele;
	}

	public FabriqueDePion getFabriqueDePion() {
		return fabriqueDePion;
	}

	public void actualiser() {

	}

}
