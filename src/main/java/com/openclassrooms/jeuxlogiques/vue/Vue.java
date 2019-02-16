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

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.modele.Modele;

public class Vue implements Observateur {

	private Modele modele;
	private Controleur controleur;

	public void setModele(Modele modele) {
		this.modele = modele;
	}

	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
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
		 * Fenêtre principale
		 */
		JFrame fenetrePrincipale = new JFrame("Jeux de logique");
		fenetrePrincipale.setIconImage(new ImageIcon(getClass().getResource("/game_16.png")).getImage());

		/*
		 * Barre de menu
		 */
		JMenuBar barreDeMenu = new JMenuBar();
		fenetrePrincipale.setJMenuBar(barreDeMenu);

		JMenu menuJeu = new JMenu("Jeu");
		barreDeMenu.add(menuJeu);

		JMenuItem menuItemJeuNouveauJeu = new JMenuItem("Nouveau jeu",
				new ImageIcon(getClass().getResource("/nouveau_jeu_16.png")));
		menuJeu.add(menuItemJeuNouveauJeu);
		menuItemJeuNouveauJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		menuJeu.addSeparator();

		JMenuItem menuItemJeuQuitter = new JMenuItem("Quitter",
				new ImageIcon(getClass().getResource("/quitter_16.png")));
		menuJeu.add(menuItemJeuQuitter);
		menuItemJeuQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JMenu menuOption = new JMenu("Options");
		barreDeMenu.add(menuOption);

		JMenuItem menuItemOptionJeu = new JMenuItem("Jeux",
				new ImageIcon(getClass().getResource("/option_jeu_16.png")));
		menuOption.add(menuItemOptionJeu);
		menuItemOptionJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JMenuItem menuItemOptionLogs = new JMenuItem("Logs",
				new ImageIcon(getClass().getResource("/option_logs_16.png")));
		menuOption.add(menuItemOptionLogs);
		menuItemOptionLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JMenu menuAide = new JMenu("Aide");
		barreDeMenu.add(menuAide);

		JMenuItem menuItemAideRegles = new JMenuItem("Règles de jeux",
				new ImageIcon(getClass().getResource("/aide_16.png")));
		menuAide.add(menuItemAideRegles);
		menuItemAideRegles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		/*
		 * GridBagConstraints
		 */
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.insets = new Insets(0, 0, 0, 0);
		contraintes.anchor = GridBagConstraints.CENTER;

		/*
		 * Barre d'outils
		 */
		JPanel panneauBarreOutils = new JPanel(new BorderLayout());
		fenetrePrincipale.add(panneauBarreOutils);
		panneauBarreOutils.setBorder(new EmptyBorder(0, 20, 0, 0));
		JToolBar barreOutils = new JToolBar();
		panneauBarreOutils.add(barreOutils, BorderLayout.WEST);
		barreOutils.setLayout(new GridBagLayout());

		JButton boutonNouveauJeu = new JButton("Nouveau Jeu",
				new ImageIcon(getClass().getResource("/nouveau_jeu_32.png")));
		boutonNouveauJeu.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonNouveauJeu.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonNouveauJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		contraintes.gridx = 1;
		contraintes.gridy = 1;
		barreOutils.add(boutonNouveauJeu, contraintes);

		/*
		 * Paramètres de la fenêtre principale
		 */
		fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetrePrincipale.pack();
		fenetrePrincipale.setLocationRelativeTo(null);
		fenetrePrincipale.setVisible(true);

	}

	public void actualiser() {

	}

}