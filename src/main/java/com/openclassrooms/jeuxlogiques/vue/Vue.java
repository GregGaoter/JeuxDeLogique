package com.openclassrooms.jeuxlogiques.vue;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.separateur.AlignementHorizontal;
import com.openclassrooms.jeuxlogiques.separateur.SeparateurVertical;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;
import com.openclassrooms.jeuxlogiques.vue.labelpion.MouseListenerGetPionProposition;
import com.openclassrooms.jeuxlogiques.vue.labelpion.MouseListenerSetPionProposition;

public class Vue implements Observateur {

	private final String separateurClef = "-";

	private Modele modele;
	private Controleur controleur;

	private HashMap<String, JLabelPion> listePanneauSecret;
	private HashMap<String, JLabelPion> listePanneauProposition;
	private HashMap<String, JLabelPion> listePanneauReponse;
	private HashMap<String, JLabelPion> listePanneauPionUtilisable;
	private HashMap<String, JPanel> listePanneauValidation;

	private GridBagConstraints contraintes;

	private JPanel panneauPrincipal;

	private JPanel panneauCombinaisonSecrete;
	private JPanel panneauProposition;
	private JPanel panneauReponse;
	private JPanel panneauPionsUtilisables;
	private JPanel panneauValidation;

	private JButton boutonValidation;

	public Vue() {
		listePanneauSecret = new HashMap<>();
		listePanneauProposition = new HashMap<>();
		listePanneauReponse = new HashMap<>();
		listePanneauPionUtilisable = new HashMap<>();
		listePanneauValidation = new HashMap<>();
	}

	public void setModele(Modele modele) {
		this.modele = modele;
		modele.ajouterObservateur(this);
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
				controleur.lancerDialogueJeu(fenetrePrincipale);
			}
		});

		menuJeu.addSeparator();

		JMenuItem menuItemJeuQuitter = new JMenuItem("Quitter",
				new ImageIcon(getClass().getResource("/quitter_16.png")));
		menuJeu.add(menuItemJeuQuitter);
		menuItemJeuQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
		contraintes.gridy = 1;

		/*
		 * Barre d'outils
		 */
		JPanel panneauBarreOutils = new JPanel(new BorderLayout());
		fenetrePrincipale.add(panneauBarreOutils, BorderLayout.NORTH);
		JToolBar barreOutils = new JToolBar();
		panneauBarreOutils.add(barreOutils, BorderLayout.WEST);
		barreOutils.setLayout(new GridBagLayout());

		contraintes.gridx = 1;
		barreOutils.add(Box.createHorizontalStrut(10));

		JButton boutonNouveauJeu = new JButton("Nouveau Jeu",
				new ImageIcon(getClass().getResource("/nouveau_jeu_32.png")));
		boutonNouveauJeu.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonNouveauJeu.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonNouveauJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.lancerDialogueJeu(fenetrePrincipale);
			}
		});
		contraintes.gridx++;
		barreOutils.add(boutonNouveauJeu, contraintes);

		contraintes.gridx++;
		barreOutils.add(new SeparateurVertical(20, AlignementHorizontal.Centre), contraintes);

		JButton boutonOptionJeu = new JButton("Options jeux",
				new ImageIcon(getClass().getResource("/option_jeu_32.png")));
		boutonOptionJeu.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonOptionJeu.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonOptionJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		contraintes.gridx++;
		barreOutils.add(boutonOptionJeu, contraintes);

		JButton boutonOptionLogs = new JButton("Options logs",
				new ImageIcon(getClass().getResource("/option_logs_32.png")));
		boutonOptionLogs.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonOptionLogs.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonOptionLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		contraintes.gridx++;
		barreOutils.add(boutonOptionLogs, contraintes);

		contraintes.gridx++;
		barreOutils.add(new SeparateurVertical(20, AlignementHorizontal.Centre), contraintes);

		JButton bouttonAide = new JButton("Règles de jeux", new ImageIcon(getClass().getResource("/aide_32.png")));
		bouttonAide.setVerticalTextPosition(SwingConstants.BOTTOM);
		bouttonAide.setHorizontalTextPosition(SwingConstants.CENTER);
		bouttonAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		contraintes.gridx++;
		barreOutils.add(bouttonAide, contraintes);

		/*
		 * Panneau principal
		 */
		panneauPrincipal = new JPanel(new GridBagLayout());
		fenetrePrincipale.add(panneauPrincipal, BorderLayout.CENTER);
		panneauPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

		/*
		 * Panneau combinaison secrète
		 */
		panneauCombinaisonSecrete = new JPanel(new GridBagLayout());
		setListePanneauPionProposition(listePanneauSecret, modele.getNbPionsCombinaison(), 1, PionCommun.Vide);
		creerPanneau(panneauCombinaisonSecrete, listePanneauSecret, contraintes, "Combinaison secrète");
		contraintes.gridx = 2;
		contraintes.gridy = 1;
		panneauPrincipal.add(panneauCombinaisonSecrete, contraintes);

		/*
		 * Panneau proposition
		 */
		panneauProposition = new JPanel(new GridBagLayout());
		setListePanneauPionProposition(listePanneauProposition, modele.getNbPionsCombinaison(), modele.getNbEssais(),
				PionCommun.Vide);
		creerPanneau(panneauProposition, listePanneauProposition, contraintes, "Proposition");
		contraintes.gridx = 2;
		contraintes.gridy = 2;
		panneauPrincipal.add(panneauProposition, contraintes);

		/*
		 * Panneau réponse
		 */
		panneauReponse = new JPanel(new GridBagLayout());
		setListePanneauPionProposition(listePanneauReponse, modele.getNbPionsCombinaison(), modele.getNbEssais(),
				PionCommun.Vide);
		creerPanneau(panneauReponse, listePanneauReponse, contraintes, "Réponse");
		contraintes.gridx = 3;
		contraintes.gridy = 2;
		panneauPrincipal.add(panneauReponse, contraintes);

		/*
		 * Panneau pions utilisables
		 */
		panneauPionsUtilisables = new JPanel(new GridBagLayout());
		setListePanneauPionUtilisables();
		creerPanneau(panneauPionsUtilisables, listePanneauPionUtilisable, contraintes, "Pions utilisables");
		contraintes.gridx = 1;
		contraintes.gridy = 3;
		contraintes.gridwidth = 3;
		contraintes.fill = GridBagConstraints.NONE;
		panneauPrincipal.add(panneauPionsUtilisables, contraintes);
		contraintes.gridwidth = 1;
		contraintes.fill = GridBagConstraints.BOTH;

		/*
		 * Panneau validation
		 */
		boutonValidation = new JButton("Valider");
		boutonValidation.setEnabled(false);
		panneauValidation = new JPanel(new GridBagLayout());
		contraintes.gridx = 1;
		contraintes.gridy = 2;
		panneauPrincipal.add(panneauValidation, contraintes);
		panneauValidation.setBorder(BorderFactory.createTitledBorder("Validation"));
		for (int y = 1; y <= modele.getNbEssais(); y++)
			listePanneauValidation.put(getClef(1, y), new PanneauBoutonValidation(boutonValidation));
		for (Map.Entry<String, JPanel> item : listePanneauValidation.entrySet()) {
			contraintes.gridx = Integer.parseInt(item.getKey().split(separateurClef)[0]);
			contraintes.gridy = Integer.parseInt(item.getKey().split(separateurClef)[1]);
			panneauValidation.add(item.getValue(), contraintes);
		}
		listePanneauValidation.get(getClef(1, modele.getNbEssais())).add(boutonValidation);
		boutonValidation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.calculerReponse();
			}
		});

		/*
		 * Paramètres de la fenêtre principale
		 */
		fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetrePrincipale.pack();
		fenetrePrincipale.setLocationRelativeTo(null);
		fenetrePrincipale.setVisible(true);

	}

	public JPanel getPanneauPrincipal() {
		return panneauPrincipal;
	}

	public HashMap<String, JLabelPion> getListePanneauSecret() {
		return listePanneauSecret;
	}

	public HashMap<String, JLabelPion> getListePanneauProposition() {
		return listePanneauProposition;
	}

	public HashMap<String, JLabelPion> getListePanneauReponse() {
		return listePanneauReponse;
	}

	public HashMap<String, JLabelPion> getListePanneauPionUtilisable() {
		return listePanneauPionUtilisable;
	}

	public HashMap<String, JPanel> getListePanneauValidation() {
		return listePanneauValidation;
	}

	public JButton getBoutonValidation() {
		return boutonValidation;
	}

	private String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	private void setListePanneauPionUtilisables() {
		Pion pion;
		for (int x = 1; x <= modele.getPionsUtilisables().size(); x++) {
			pion = modele.getPionsUtilisables().get(x - 1);
			listePanneauPionUtilisable.put(getClef(x, 1),
					new JLabelPion(pion, new MouseListenerSetPionProposition(controleur, pion)));
		}
	}

	public void setListePanneauPionProposition(HashMap<String, JLabelPion> listePanneau, int xMax, int yMax,
			Pion pion) {
		for (int y = 1; y <= yMax; y++) {
			for (int x = 1; x <= xMax; x++)
				listePanneau.put(getClef(x, y),
						new JLabelPion(pion, new MouseListenerGetPionProposition(controleur, pion, x)));
		}
	}

	private void creerPanneau(JPanel panneau, HashMap<String, JLabelPion> listePanneau, GridBagConstraints c,
			String titre) {
		panneau.setBorder(BorderFactory.createTitledBorder(titre));
		for (Map.Entry<String, JLabelPion> item : listePanneau.entrySet()) {
			c.gridx = Integer.parseInt(item.getKey().split(separateurClef)[0]);
			c.gridy = Integer.parseInt(item.getKey().split(separateurClef)[1]);
			panneau.add(item.getValue(), c);
		}
	}

	public void setPion(HashMap<String, JLabelPion> listePanneau, String clef, Pion pion) {
		listePanneau.get(clef).getMouseListener().setPion(pion);
		listePanneau.get(clef).setIcon(new ImageIcon(getClass().getResource(pion.getNomImage())));
		listePanneau.get(clef).setText(Integer.toString(pion.getValeur()));
	}

	public void actualiser() {
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++) {
			setPion(listePanneauProposition, getClef(i + 1, modele.getCompteurEssais()),
					modele.getCombinaisonProposition().get(i));
			setPion(listePanneauReponse, getClef(i + 1, modele.getCompteurEssais()),
					modele.getCombinaisonReponse().get(i));
		}
		if (!modele.getCombinaisonProposition().contains(PionCommun.Vide))
			boutonValidation.setEnabled(true);
	}

}