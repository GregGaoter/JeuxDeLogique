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

import com.openclassrooms.jeuxlogiques.modele.jeu.Jeu;

public class DialogueJeu extends JDialog {

	private static final long serialVersionUID = 1L;

	private Jeu jeuSelectionne;

	private JPanel panneauPrincipal, panneauIcone, panneauChoix;
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
		panneauIcone.add(new JLabel(new ImageIcon(getClass().getResource("/game_48.png"))));
		panneauIcone.add(Box.createHorizontalStrut(10));

		/*
		 * Choix des jeux
		 */
		panneauChoix = new JPanel(new GridLayout(0, 1));
		panneauPrincipal.add(panneauChoix, BorderLayout.CENTER);
		panneauChoix.setBorder(BorderFactory.createTitledBorder("Sélectionnez un jeu :"));

		ButtonGroup toggleButtonGroupe = new ButtonGroup();

		for (Jeu jeu : Jeu.LISTE_JEUX) {
			JToggleButton toggleButton = new JToggleButton(jeu.getNom());
			toggleButtonGroupe.add(toggleButton);
			panneauChoix.add(toggleButton);
			toggleButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jeuSelectionne = jeu;
				}
			});
		}

		toggleButtonGroupe.getElements().nextElement().doClick();

		/*
		 * Validation
		 */
		JPanel panneauValidation = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panneauPrincipal.add(panneauValidation, BorderLayout.SOUTH);
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

	public Jeu getValeur() {
		okQ = false;
		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);
		return (okQ ? jeuSelectionne : null);
	}

}
