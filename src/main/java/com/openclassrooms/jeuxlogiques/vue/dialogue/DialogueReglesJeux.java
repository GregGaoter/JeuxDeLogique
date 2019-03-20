package com.openclassrooms.jeuxlogiques.vue.dialogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.UIDefaults;
import javax.swing.text.BadLocationException;

import org.apache.log4j.Logger;

import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;

public class DialogueReglesJeux extends JDialog {

	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger(DialogueReglesJeux.class);

	public DialogueReglesJeux(JFrame fenetreParente) throws IOException, BadLocationException {
		super(fenetreParente, "Règles des jeux", false);
		log.info("Construction de la boîte de dialogue des règles des jeux.");

		/*
		 * Panneau onglets
		 */
		JTabbedPane panneauOnglets = new JTabbedPane();
		add(panneauOnglets, BorderLayout.CENTER);

		/*
		 * Couleur de fond des panneaux de texte
		 */
		Color couleurFond = getBackground();
		UIDefaults uiDefaults = new UIDefaults();
		uiDefaults.put("TextPane[Enabled].backgroundPainter", couleurFond);

		for (Jeu jeu : Jeu.LISTE_JEUX) {
			/*
			 * Panneaux de texte
			 */
			JTextPane panneauTexte = new JTextPane();
			panneauTexte.setContentType("text/html;charset=UTF-8");
			panneauTexte.setEditable(false);
			panneauTexte.putClientProperty("Nimbus.Overrides", uiDefaults);
			panneauTexte.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
			panneauTexte.setBackground(couleurFond);

			URL url = DialogueReglesJeux.class.getResource(jeu.getNomFichierHTMLReglesJeu());
			if (url != null) {
				try {
					panneauTexte.setPage(url);
				} catch (IOException e) {
					log.error("Lecture d'une mauvaise URL du fichier d'aide HTML du jeu " + jeu.getNom() + " : " + url);
				}
			} else
				log.error("Fichier introuvable : " + jeu.getNomFichierHTMLReglesJeu());

			JScrollPane panneauAscenceurs = new JScrollPane(panneauTexte);
			panneauAscenceurs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			panneauAscenceurs.setPreferredSize(new Dimension(420, 400));
			panneauAscenceurs.setMinimumSize(new Dimension(10, 10));

			panneauOnglets.addTab(jeu.getNom(), panneauAscenceurs);
		}

		/*
		 * Bouton fermer
		 */
		JPanel panneauBouton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		add(panneauBouton, BorderLayout.SOUTH);

		JButton boutonFermer = new JButton("Fermer");
		panneauBouton.add(boutonFermer);
		boutonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		/*
		 * Paramètres du dialogue
		 */
		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);

	}

}
