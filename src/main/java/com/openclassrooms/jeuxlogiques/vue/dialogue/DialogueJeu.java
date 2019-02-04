package com.openclassrooms.jeuxlogiques.vue.dialogue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class DialogueJeu extends JDialog {

	private static final long serialVersionUID = 1L;

	private final String jeuRecherchePlusMoins = "Recherche +/-";
	private final String jeuMastermind = "Mastermind";

	private String jeu;

	private JButton boutonOk;
	private JButton boutonAnnuler;
	private boolean okQ;

	public DialogueJeu(JFrame fenetreParente) {
		super(fenetreParente, "Sélection du jeu", true);

		/*
		 * Icone
		 */
		add(new JLabel(new ImageIcon(getClass().getResource("/aide_48.png"))), BorderLayout.WEST);

		/*
		 * Choix des jeux
		 */
		JPanel panneauChoixJeux = new JPanel();
		add(panneauChoixJeux, BorderLayout.CENTER);
		panneauChoixJeux.setLayout(new BoxLayout(panneauChoixJeux, BoxLayout.Y_AXIS));
		ButtonGroup toggleButtonGroupe = new ButtonGroup();

		panneauChoixJeux.add(new JLabel("Sélectionnez un jeu :"));

		panneauChoixJeux.add(Box.createVerticalStrut(20));

		JToggleButton toggleButtonRecherchePlusMoins = new JToggleButton(jeuRecherchePlusMoins);
		toggleButtonGroupe.add(toggleButtonRecherchePlusMoins);
		panneauChoixJeux.add(toggleButtonRecherchePlusMoins);
		toggleButtonRecherchePlusMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu = jeuRecherchePlusMoins;
				System.out.println(jeu);
			}
		});

		JToggleButton toggleButtonMastermind = new JToggleButton(jeuMastermind);
		toggleButtonGroupe.add(toggleButtonMastermind);
		panneauChoixJeux.add(toggleButtonMastermind);
		toggleButtonMastermind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu = jeuMastermind;
				System.out.println(jeu);
			}
		});

		panneauChoixJeux.add(Box.createVerticalStrut(20));

		toggleButtonRecherchePlusMoins.setSelected(true);

		/*
		 * Validation
		 */
		JPanel panneauValidation = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		add(panneauValidation, BorderLayout.SOUTH);
		boutonOk = new JButton("OK");
		panneauValidation.add(boutonOk);
		boutonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okQ = true;
				setVisible(false);
			}
		});

		boutonAnnuler = new JButton("Annuler");
		panneauValidation.add(boutonAnnuler);
		boutonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okQ = false;
				setVisible(false);
			}
		});

	}

	public String getValeur() {
		okQ = false;
		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);
		return (okQ ? jeu : null);
	}

}
