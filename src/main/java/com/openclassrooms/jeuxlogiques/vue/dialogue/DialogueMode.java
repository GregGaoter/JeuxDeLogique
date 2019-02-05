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

import com.openclassrooms.jeuxlogiques.enumeration.Mode;

public class DialogueMode extends JDialog {

	private static final long serialVersionUID = 1L;

	private Mode mode;

	private JPanel panneauPrincipal, panneauIcone, panneauChoix;
	private JButton boutonOk;
	private JButton boutonAnnuler;
	private boolean okQ;

	public DialogueMode(JFrame fenetreParente) {
		super(fenetreParente, "Sélection du mode", true);

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
		 * Choix des modes
		 */
		panneauChoix = new JPanel(new GridLayout(0, 1));
		panneauPrincipal.add(panneauChoix, BorderLayout.CENTER);
		panneauChoix.setBorder(BorderFactory.createTitledBorder("Sélectionnez un mode :"));

		ButtonGroup toggleButtonGroupe = new ButtonGroup();

		JToggleButton toggleButtonChallenger = new JToggleButton(Mode.Challenger.toString());
		toggleButtonGroupe.add(toggleButtonChallenger);
		panneauChoix.add(toggleButtonChallenger);
		toggleButtonChallenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = Mode.Challenger;
				System.out.println(Mode.Challenger.toString());
			}
		});

		JToggleButton toggleButtonDefenseur = new JToggleButton(Mode.Defenseur.toString());
		toggleButtonGroupe.add(toggleButtonDefenseur);
		panneauChoix.add(toggleButtonDefenseur);
		toggleButtonDefenseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = Mode.Defenseur;
				System.out.println(Mode.Defenseur.toString());
			}
		});

		JToggleButton toggleButtonDuel = new JToggleButton(Mode.Duel.toString());
		toggleButtonGroupe.add(toggleButtonDuel);
		panneauChoix.add(toggleButtonDuel);
		toggleButtonDuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = Mode.Duel;
				System.out.println(Mode.Duel.toString());
			}
		});

		toggleButtonChallenger.doClick();

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

	public Mode getValeur() {
		okQ = false;
		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);
		return (okQ ? mode : null);
	}

}
