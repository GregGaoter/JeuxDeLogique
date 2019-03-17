package com.openclassrooms.jeuxlogiques.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.modele.joueur.Joueur;
import com.openclassrooms.jeuxlogiques.vue.dialogue.DialogueReglesJeux;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;
import com.openclassrooms.jeuxlogiques.vue.labelpion.MouseListenerGetPionProposition;
import com.openclassrooms.jeuxlogiques.vue.labelpion.MouseListenerSetPionProposition;
import com.openclassrooms.jeuxlogiques.vue.separateur.AlignementHorizontal;
import com.openclassrooms.jeuxlogiques.vue.separateur.SeparateurVertical;

public class Vue implements Observateur {

	private final String separateurClef = "-";

	private ModeleJeu modele;
	private ControleurJeu controleur;

	private HashMap<String, JLabelPion> listePanneauSecret;
	private HashMap<String, JLabelPion> listePanneauProposition;
	private HashMap<String, JLabelPion> listePanneauReponse;
	private HashMap<String, JLabelPion> listePanneauPionUtilisable;
	private HashMap<String, JPanel> listePanneauValidation;

	private GridBagConstraints contraintes;

	private JWindow fenetreDemarrage;

	private JFrame fenetrePrincipale;
	private JPanel panneauPrincipal;

	private JMenuItem menuItemOptionJeu;
	private JMenuItem menuItemOptionJoueur;
	private JButton boutonOptionJeu;
	private JButton boutonOptionJoueur;

	private JPanel panneauCombinaisonSecrete;
	private JPanel panneauProposition;
	private JPanel panneauReponse;
	private JPanel panneauPionsUtilisables;
	private JPanel panneauValidation;

	private JLabel messageNbEssais;
	private JLabel messageFinDePartie;
	private JLabel labelVainqueur;
	private JLabel vainqueur;
	private JLabel pionSelectionne;

	private JButton boutonValidation;
	private JButton boutonRejouerMemeJeu;

	private JToggleButton toggleButtonJoueur;
	private JToggleButton toggleButtonOrdinateur;

	private JProgressBar barreProgression;

	public Vue() {
		listePanneauSecret = new HashMap<>();
		listePanneauProposition = new HashMap<>();
		listePanneauReponse = new HashMap<>();
		listePanneauPionUtilisable = new HashMap<>();
		listePanneauValidation = new HashMap<>();
	}

	public void setModele(ModeleJeu modele) {
		this.modele = modele;
		modele.ajouterObservateur(this);
	}

	public void setControleur(ControleurJeu controleur) {
		this.controleur = controleur;
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

		JLabel labelImage = new JLabel(new ImageIcon(getClass().getResource("/images/image_demarrage.png")));
		labelImage.setLayout(new BorderLayout());
		fenetreDemarrage.add(labelImage, BorderLayout.CENTER);

		JPanel panneauBarreProgression = new JPanel();
		panneauBarreProgression.setLayout(new BoxLayout(panneauBarreProgression, BoxLayout.Y_AXIS));
		labelImage.add(panneauBarreProgression, BorderLayout.SOUTH);
		panneauBarreProgression.setBackground(new Color(0, 0, 0, 0));

		JLabel labelDemarrage = new JLabel("Démarrage...");
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
		fenetrePrincipale = new JFrame("Jeux de logique");
		fenetrePrincipale.setIconImage(new ImageIcon(getClass().getResource("/images/game_16.png")).getImage());

		/*
		 * Barre de menu
		 */
		JMenuBar barreDeMenu = new JMenuBar();
		fenetrePrincipale.setJMenuBar(barreDeMenu);

		JMenu menuJeu = new JMenu("Jeu");
		barreDeMenu.add(menuJeu);

		JMenuItem menuItemJeuNouveauJeu = new JMenuItem("Nouveau jeu",
				new ImageIcon(getClass().getResource("/images/nouveau_jeu_16.png")));
		menuJeu.add(menuItemJeuNouveauJeu);
		menuItemJeuNouveauJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.lancerDialogueJeu(fenetrePrincipale);
			}
		});

		menuJeu.addSeparator();

		JMenuItem menuItemJeuQuitter = new JMenuItem("Quitter",
				new ImageIcon(getClass().getResource("/images/quitter_16.png")));
		menuJeu.add(menuItemJeuQuitter);
		menuItemJeuQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenu menuOption = new JMenu("Options");
		barreDeMenu.add(menuOption);

		menuItemOptionJeu = new JMenuItem("Jeux", new ImageIcon(getClass().getResource("/images/option_jeu_16.png")));
		menuOption.add(menuItemOptionJeu);
		menuItemOptionJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.lancerDialogueOption(fenetrePrincipale);
			}
		});

		menuItemOptionJoueur = new JMenuItem("Joueur", new ImageIcon(getClass().getResource("/images/joueur_16.png")));
		menuOption.add(menuItemOptionJoueur);
		menuItemOptionJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.lancerDialogueOptionJoueur(fenetrePrincipale);
			}
		});

		JMenu menuAide = new JMenu("Aide");
		barreDeMenu.add(menuAide);

		JMenuItem menuItemAideRegles = new JMenuItem("Règles de jeux",
				new ImageIcon(getClass().getResource("/images/aide_16.png")));
		menuAide.add(menuItemAideRegles);
		menuItemAideRegles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new DialogueReglesJeux(fenetrePrincipale);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		});

		/*
		 * GridBagConstraints
		 */
		contraintes = new GridBagConstraints();
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
				new ImageIcon(getClass().getResource("/images/nouveau_jeu_32.png")));
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
		barreOutils.add(new SeparateurVertical(10, AlignementHorizontal.Centre), contraintes);

		boutonOptionJeu = new JButton("Options jeux",
				new ImageIcon(getClass().getResource("/images/option_jeu_32.png")));
		boutonOptionJeu.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonOptionJeu.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonOptionJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.lancerDialogueOption(fenetrePrincipale);
			}
		});
		contraintes.gridx++;
		barreOutils.add(boutonOptionJeu, contraintes);

		boutonOptionJoueur = new JButton("Options joueur",
				new ImageIcon(getClass().getResource("/images/joueur_32.png")));
		boutonOptionJoueur.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonOptionJoueur.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonOptionJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.lancerDialogueOptionJoueur(fenetrePrincipale);
			}
		});
		contraintes.gridx++;
		barreOutils.add(boutonOptionJoueur, contraintes);

		contraintes.gridx++;
		barreOutils.add(new SeparateurVertical(5, AlignementHorizontal.Droite), contraintes);

		JPanel panneauVainqueur = new JPanel(new GridBagLayout());
		panneauBarreOutils.add(panneauVainqueur, BorderLayout.CENTER);

		labelVainqueur = new JLabel("Vainqueur");
		contraintes.gridx = 1;
		contraintes.gridy = 1;
		panneauVainqueur.add(labelVainqueur, contraintes);
		labelVainqueur.setHorizontalAlignment(SwingConstants.CENTER);
		labelVainqueur
				.setFont(new Font(labelVainqueur.getFont().getName(), Font.BOLD, labelVainqueur.getFont().getSize()));

		vainqueur = new JLabel("-");
		contraintes.gridx = 1;
		contraintes.gridy = 2;
		panneauVainqueur.add(vainqueur, contraintes);
		vainqueur.setHorizontalAlignment(SwingConstants.CENTER);
		vainqueur.setFont(new Font(vainqueur.getFont().getName(), Font.BOLD, vainqueur.getFont().getSize() * 2));
		vainqueur.setForeground(new Color(0, 127, 0));

		boutonRejouerMemeJeu = new JButton("Rejouer", new ImageIcon(getClass().getResource("/images/rejouer_32.png")));
		panneauBarreOutils.add(boutonRejouerMemeJeu, BorderLayout.EAST);
		boutonRejouerMemeJeu.setEnabled(false);
		boutonRejouerMemeJeu.setVerticalTextPosition(SwingConstants.BOTTOM);
		boutonRejouerMemeJeu.setHorizontalTextPosition(SwingConstants.CENTER);
		boutonRejouerMemeJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.rejouerMemeJeu(fenetrePrincipale);
			}
		});

		/*
		 * Panneau principal
		 */
		panneauPrincipal = new JPanel(new GridBagLayout());
		fenetrePrincipale.add(panneauPrincipal, BorderLayout.CENTER);
		panneauPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

		initialiserPanneaux();

		/*
		 * Paramètres de la fenêtre principale
		 */
		fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetrePrincipale.setVisible(true);

	}

	public void initialiserPanneaux() {

		panneauPrincipal.removeAll();

		/*
		 * Panneau joueur courant
		 */
		JPanel panneauJoueurCourant = new JPanel(new GridLayout(2, 1));
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.gridx = 1;
		contraintes.gridy = 1;
		panneauPrincipal.add(panneauJoueurCourant, contraintes);
		contraintes.fill = GridBagConstraints.BOTH;

		ButtonGroup buttonGroupJoueurCourant = new ButtonGroup();

		toggleButtonJoueur = new JToggleButton(modele.getNomJoueur());
		buttonGroupJoueurCourant.add(toggleButtonJoueur);
		panneauJoueurCourant.add(toggleButtonJoueur);
		toggleButtonJoueur.setEnabled(false);
		toggleButtonJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.setVueJoueur();
			}
		});

		toggleButtonOrdinateur = new JToggleButton("Ordinateur");
		buttonGroupJoueurCourant.add(toggleButtonOrdinateur);
		panneauJoueurCourant.add(toggleButtonOrdinateur);
		toggleButtonOrdinateur.setEnabled(false);
		toggleButtonOrdinateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.setVueOrdinateur();
			}
		});

		/*
		 * Panneau informations parties
		 */
		JPanel panneauInformationsPartie = new JPanel(new GridBagLayout());
		contraintes.gridx = 3;
		contraintes.gridy = 1;
		panneauPrincipal.add(panneauInformationsPartie, contraintes);

		/*
		 * Label sélection
		 */
		JLabel selection = new JLabel("Sélection");
		selection.setHorizontalAlignment(SwingConstants.CENTER);
		contraintes.gridx = 1;
		contraintes.gridy = 1;
		panneauInformationsPartie.add(selection, contraintes);
		selection.setHorizontalAlignment(SwingConstants.CENTER);

		/*
		 * Pion sélectionné
		 */
		pionSelectionne = new JLabel();
		pionSelectionne.setHorizontalAlignment(SwingConstants.CENTER);
		contraintes.gridx = 1;
		contraintes.gridy = 2;
		panneauInformationsPartie.add(pionSelectionne, contraintes);
		pionSelectionne.setIcon(new ImageIcon(getClass().getResource(PionCommun.Vide.getNomImage())));

		contraintes.gridx = 2;
		contraintes.gridy = 1;
		panneauInformationsPartie.add(Box.createHorizontalStrut(20), contraintes);

		/*
		 * Label message Essai n°
		 */
		JLabel messageEssais = new JLabel("Essai n°");
		messageEssais.setHorizontalAlignment(SwingConstants.CENTER);
		contraintes.gridx = 3;
		contraintes.gridy = 1;
		panneauInformationsPartie.add(messageEssais, contraintes);

		/*
		 * Label nombre d'essais
		 */
		messageNbEssais = new JLabel("01 / " + (modele.getNbEssais() < 10 ? "0" : "") + modele.getNbEssais());
		messageNbEssais.setHorizontalAlignment(SwingConstants.CENTER);
		messageNbEssais.setFont(new Font(messageNbEssais.getFont().getName(), Font.PLAIN, 28));
		contraintes.gridx = 3;
		contraintes.gridy = 2;
		panneauInformationsPartie.add(messageNbEssais, contraintes);

		/*
		 * Panneau combinaison secrète
		 */
		panneauCombinaisonSecrete = new JPanel(new GridBagLayout());
		panneauCombinaisonSecrete.setBorder(BorderFactory.createTitledBorder("Combinaison secrète"));
		setListePanneauPionProposition(listePanneauSecret, modele.getNbPionsCombinaison(), 1, PionCommun.Vide);
		creerPanneau(panneauCombinaisonSecrete, listePanneauSecret, contraintes);
		contraintes.gridx = 2;
		contraintes.gridy = 1;
		panneauPrincipal.add(panneauCombinaisonSecrete, contraintes);

		/*
		 * Panneau proposition
		 */
		panneauProposition = new JPanel(new GridBagLayout());
		panneauProposition.setBorder(BorderFactory.createTitledBorder("Proposition"));
		setListePanneauPionProposition(listePanneauProposition, modele.getNbPionsCombinaison(), modele.getNbEssais(),
				PionCommun.Vide);
		creerPanneau(panneauProposition, listePanneauProposition, contraintes);
		contraintes.gridx = 2;
		contraintes.gridy = 2;
		panneauPrincipal.add(panneauProposition, contraintes);

		/*
		 * Panneau réponse
		 */
		panneauReponse = new JPanel(new GridBagLayout());
		panneauReponse.setBorder(BorderFactory.createTitledBorder("Réponse"));
		setListePanneauPionProposition(listePanneauReponse, modele.getNbPionsCombinaison(), modele.getNbEssais(),
				PionCommun.Vide);
		creerPanneau(panneauReponse, listePanneauReponse, contraintes);
		contraintes.gridx = 3;
		contraintes.gridy = 2;
		panneauPrincipal.add(panneauReponse, contraintes);

		/*
		 * Panneau pions utilisables
		 */
		panneauPionsUtilisables = new JPanel(new GridBagLayout());
		panneauPionsUtilisables.setBorder(BorderFactory.createTitledBorder("Pions utilisables"));
		setListePanneauPionUtilisables();
		creerPanneau(panneauPionsUtilisables, listePanneauPionUtilisable, contraintes);
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
		creerPanneauValidation();

		/*
		 * Dimension et position de la fenêtre principale
		 */
		fenetrePrincipale.pack();
		fenetrePrincipale.setLocationRelativeTo(null);

	}

	public JPanel getPanneauPrincipal() {
		return panneauPrincipal;
	}

	public JMenuItem getMenuItemOptionJeu() {
		return menuItemOptionJeu;
	}

	public JButton getBoutonOptionJeu() {
		return boutonOptionJeu;
	}

	public JMenuItem getMenuItemOptionJoueur() {
		return menuItemOptionJoueur;
	}

	public JButton getBoutonOptionJoueur() {
		return boutonOptionJoueur;
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

	public void setListePanneauSecret(HashMap<String, JLabelPion> listePanneauSecret) {
		this.listePanneauSecret = listePanneauSecret;
	}

	public void setListePanneauProposition(HashMap<String, JLabelPion> listePanneauProposition) {
		this.listePanneauProposition = listePanneauProposition;
	}

	public void setListePanneauReponse(HashMap<String, JLabelPion> listePanneauReponse) {
		this.listePanneauReponse = listePanneauReponse;
	}

	public void setListePanneauValidation(HashMap<String, JPanel> listePanneauValidation) {
		this.listePanneauValidation = listePanneauValidation;
	}

	public JLabel getMessageNbEssais() {
		return messageNbEssais;
	}

	public JLabel getMessageFinDePartie() {
		return messageFinDePartie;
	}

	public JButton getBoutonRejouerMemeJeu() {
		return boutonRejouerMemeJeu;
	}

	public JLabel getLabelVainqueur() {
		return labelVainqueur;
	}

	public JLabel getVainqueur() {
		return vainqueur;
	}

	public JToggleButton getToggleButtonJoueur() {
		return toggleButtonJoueur;
	}

	public JToggleButton getToggleButtonOrdinateur() {
		return toggleButtonOrdinateur;
	}

	public JLabel getPionSelectionne() {
		return pionSelectionne;
	}

	public String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	private void setListePanneauPionUtilisables() {
		listePanneauPionUtilisable.clear();
		Pion pion;
		for (int x = 1; x <= modele.getPionsUtilisables().size(); x++) {
			pion = modele.getPionsUtilisables().get(x - 1);
			listePanneauPionUtilisable.put(getClef(x, 1),
					new JLabelPion(pion, new MouseListenerSetPionProposition(controleur, pion)));
		}
	}

	public void setListePanneauPionProposition(HashMap<String, JLabelPion> listePanneau, int xMax, int yMax,
			Pion pion) {
		listePanneau.clear();
		for (int y = 1; y <= yMax; y++) {
			for (int x = 1; x <= xMax; x++)
				listePanneau.put(getClef(x, y),
						new JLabelPion(pion, new MouseListenerGetPionProposition(controleur, pion, x)));
		}
	}

	private void creerPanneau(JPanel panneau, HashMap<String, JLabelPion> listePanneau, GridBagConstraints c) {
		panneau.removeAll();
		for (Map.Entry<String, JLabelPion> item : listePanneau.entrySet()) {
			c.gridx = Integer.parseInt(item.getKey().split(separateurClef)[0]);
			c.gridy = Integer.parseInt(item.getKey().split(separateurClef)[1]);
			panneau.add(item.getValue(), c);
		}
	}

	private void creerPanneauValidation() {
		panneauValidation.removeAll();
		listePanneauValidation.clear();
		for (int y = 1; y <= modele.getNbEssais(); y++)
			listePanneauValidation.put(getClef(1, y), new PanneauBoutonValidation(boutonValidation));
		for (Map.Entry<String, JPanel> item : listePanneauValidation.entrySet()) {
			contraintes.gridx = Integer.parseInt(item.getKey().split(separateurClef)[0]);
			contraintes.gridy = Integer.parseInt(item.getKey().split(separateurClef)[1]);
			panneauValidation.add(item.getValue(), contraintes);
		}
		listePanneauValidation.get(getClef(1, modele.getNbEssais())).add(boutonValidation);
	}

	public void setPion(HashMap<String, JLabelPion> listePanneau, String clef, Pion pion) {
		listePanneau.get(clef).getMouseListener().setPion(pion);
		listePanneau.get(clef).setIcon(new ImageIcon(getClass().getResource(pion.getNomImage())));
		listePanneau.get(clef).setText(Integer.toString(pion.getValeur()));
	}

	public void actualiser() {
		Joueur attaquant = modele.getAttaquant();
		attaquant.getBoutonValidation().setEnabled(!modele.getCombinaisonProposition().contains(PionCommun.Vide));
		String clef;
		for (int y = 1; y <= modele.getNbEssais(); y++) {
			for (int x = 1; x <= modele.getNbPionsCombinaison(); x++) {
				clef = getClef(x, y);
				setPion(listePanneauProposition, clef, attaquant.getListePanneauProposition().get(clef));
				setPion(listePanneauReponse, clef, attaquant.getListePanneauReponse().get(clef));
			}
		}
		if (attaquant.getHumainQ())
			toggleButtonJoueur.setSelected(true);
		else
			toggleButtonOrdinateur.setSelected(true);
	}

	public void actualiserPanneauValidation() {
		panneauValidation.removeAll();
		for (Map.Entry<String, JPanel> item : modele.getListePanneauValidation().entrySet()) {
			contraintes.gridx = Integer.parseInt(item.getKey().split(separateurClef)[0]);
			contraintes.gridy = Integer.parseInt(item.getKey().split(separateurClef)[1]);
			panneauValidation.add(item.getValue(), contraintes);
		}
		panneauValidation.repaint();
	}

	public void actualiserVueJoueur(Joueur attaquant, Joueur defenseur) {
		String clef;
		for (int y = 1; y <= modele.getNbEssais(); y++) {
			for (int x = 1; x <= modele.getNbPionsCombinaison(); x++) {
				clef = getClef(x, y);
				setPion(listePanneauProposition, clef, attaquant.getListePanneauProposition().get(clef));
				setPion(listePanneauReponse, clef, attaquant.getListePanneauReponse().get(clef));
			}
		}
		if (controleur.getGagnantQ() || controleur.getModeDeveloppeurQ())
			controleur.afficherCombinaisonSecrete(defenseur);
	}

}