package com.openclassrooms.jeuxlogiques.vue.dialogue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class DialogueJeu extends JDialog {

	private static final long serialVersionUID = 1L;

	private final String jeuRecherchePlusMoins = "Recherche +/-";
	private final String jeuMastermind = "Mastermind";

	private String jeu;

	private JPanel panneauPrincipal, panneauIcone, panneauChoixJeux, panneauSelection;
	private JButton boutonOk;
	private JButton boutonAnnuler;
	private boolean okQ;

	public DialogueJeu(JFrame fenetreParente) {
		super(fenetreParente, "Sélection du jeu", true);

		/*
		 * Panneau principal
		 */
		panneauPrincipal = new JPanel(new BorderLayout());
		add(panneauPrincipal, BorderLayout.CENTER);
		panneauPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

		/*
		 * Icone
		 */
		panneauIcone = new JPanel();
		panneauPrincipal.add(panneauIcone, BorderLayout.WEST);
		panneauIcone.add(new JLabel(new ImageIcon(getClass().getResource("/aide_48.png"))));
		panneauIcone.add(Box.createHorizontalStrut(10));

		/*
		 * Choix des jeux
		 */
		panneauChoixJeux = new JPanel(new GridLayout(0, 1));
		panneauPrincipal.add(panneauChoixJeux, BorderLayout.CENTER);
		panneauChoixJeux.setBorder(BorderFactory.createTitledBorder("Sélectionnez un jeu :"));

		ButtonGroup toggleButtonGroupe = new ButtonGroup();

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

		toggleButtonRecherchePlusMoins.doClick();

		/*
		 * Validation
		 */
		JPanel panneauValidation = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panneauPrincipal.add(panneauValidation, BorderLayout.SOUTH);
		// panneauValidation.setBorder(new EmptyBorder(5, 0, 0, 0));
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
