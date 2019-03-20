package com.openclassrooms.jeuxlogiques.vue.dialogue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

public class DialogueOptionJoueur extends JDialog {

	private final static Logger log = Logger.getLogger(DialogueOptionJoueur.class);

	private static final long serialVersionUID = 1L;

	private JTextField textFieldNomJoueur;

	private JPanel panneauPrincipal, panneauIcone, panneauChoix;
	private JButton boutonOk;
	private JButton boutonAnnuler;
	private boolean okQ;

	public DialogueOptionJoueur(JFrame fenetreParente) {
		super(fenetreParente, "Options du joueur", true);
		log.info("Construction de la boîte de dialogue du réglage du nom du jeu.");

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
		panneauIcone.add(new JLabel(new ImageIcon(getClass().getResource("/images/joueur_48.png"))));
		panneauIcone.add(Box.createHorizontalStrut(10));

		/*
		 * Entrée du nom du joueur
		 */
		panneauChoix = new JPanel(new GridLayout(0, 1));
		panneauPrincipal.add(panneauChoix, BorderLayout.CENTER);
		panneauChoix.setBorder(BorderFactory.createTitledBorder("Entrer le nom du joueur :"));

		textFieldNomJoueur = new JTextField("Entrer le nom du joueur :");
		panneauChoix.add(textFieldNomJoueur);
		textFieldNomJoueur.setPreferredSize(new Dimension((int) textFieldNomJoueur.getPreferredSize().getWidth(),
				(int) textFieldNomJoueur.getPreferredSize().getHeight()));
		textFieldNomJoueur.setText("");

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

	public String getValeur() {
		okQ = false;
		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);
		String nomJoueur = textFieldNomJoueur.getText();
		nomJoueur = nomJoueur.substring(0, nomJoueur.length() <= 8 ? nomJoueur.length() : 8);
		return (okQ ? nomJoueur : null);
	}

}