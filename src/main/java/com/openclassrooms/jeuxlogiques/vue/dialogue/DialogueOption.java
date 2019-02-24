package com.openclassrooms.jeuxlogiques.vue.dialogue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;

public class DialogueOption extends JDialog {

	private static final long serialVersionUID = 1L;

	private int[] listeValeurs;
	private boolean okQ;

	private JSlider sliderNbPionsCombinaisonSecrete;
	private JSlider sliderNbEssaisPossibles;
	private JSlider sliderNbCouleursUtilisables;

	public DialogueOption(JFrame fenetreParente) {
		super(fenetreParente, "Choix des options", true);
		listeValeurs = new int[3];

		/*
		 * Contraintes du panneau des settings
		 */
		GridBagConstraints contraintesSettings = new GridBagConstraints();
		contraintesSettings.fill = GridBagConstraints.NONE;
		contraintesSettings.insets = new Insets(0, 0, 0, 0);
		contraintesSettings.anchor = GridBagConstraints.WEST;

		/*
		 * Contraintes des panneaux d'options
		 */
		GridBagConstraints contraintesOptions = new GridBagConstraints();
		contraintesOptions.fill = GridBagConstraints.NONE;
		contraintesOptions.insets = new Insets(0, 0, 0, 0);
		contraintesOptions.anchor = GridBagConstraints.WEST;

		/*
		 * Panneau principal
		 */
		JPanel panneauPrincipal = new JPanel(new BorderLayout());
		add(panneauPrincipal, BorderLayout.CENTER);
		panneauPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

		/*
		 * Icone
		 */
		JPanel panneauIcone = new JPanel();
		panneauPrincipal.add(panneauIcone, BorderLayout.WEST);
		panneauIcone.add(new JLabel(new ImageIcon(getClass().getResource("/option_jeu_48.png"))));
		panneauIcone.add(Box.createHorizontalStrut(10));

		/*
		 * Panneau des settings
		 */
		JPanel panneauSettings = new JPanel(new GridBagLayout());
		panneauPrincipal.add(panneauSettings, BorderLayout.CENTER);

		/*
		 * Panneau des options générales
		 */
		JPanel panneauOptionsGenerales = new JPanel(new GridBagLayout());
		contraintesSettings.gridx = 1;
		contraintesSettings.gridy = 1;
		panneauSettings.add(panneauOptionsGenerales, contraintesSettings);
		panneauOptionsGenerales.setBorder(BorderFactory.createTitledBorder("Options générales :"));

		contraintesOptions.gridx = 1;
		contraintesOptions.gridy = 1;
		panneauOptionsGenerales.add(new JLabel("Nombre de pions de la combinaison secrète :"), contraintesOptions);
		sliderNbPionsCombinaisonSecrete = new JSlider(ModeleJeu.NB_PIONS_COMBINAISON_MIN, ModeleJeu.NB_PIONS_COMBINAISON_MAX,
				(ModeleJeu.NB_PIONS_COMBINAISON_MIN + ModeleJeu.NB_PIONS_COMBINAISON_MAX) / 2);
		contraintesOptions.gridy = 2;
		panneauOptionsGenerales.add(sliderNbPionsCombinaisonSecrete, contraintesOptions);
		sliderNbPionsCombinaisonSecrete.setMajorTickSpacing(1);
		sliderNbPionsCombinaisonSecrete.setPaintTicks(true);
		sliderNbPionsCombinaisonSecrete.setPaintLabels(true);
		sliderNbPionsCombinaisonSecrete.setPreferredSize(
				new Dimension((int) (sliderNbPionsCombinaisonSecrete.getPreferredSize().getWidth() / 2),
						(int) (sliderNbPionsCombinaisonSecrete.getPreferredSize().getHeight())));

		contraintesOptions.gridy = 3;
		panneauOptionsGenerales.add(Box.createVerticalStrut(15), contraintesOptions);

		contraintesOptions.gridy = 4;
		panneauOptionsGenerales.add(new JLabel("Nombre d'essais possibles :"), contraintesOptions);
		sliderNbEssaisPossibles = new JSlider(ModeleJeu.NB_ESSAIS_MIN, ModeleJeu.NB_ESSAIS_MAX,
				(ModeleJeu.NB_ESSAIS_MIN + ModeleJeu.NB_ESSAIS_MAX) / 2);
		contraintesOptions.gridy = 5;
		panneauOptionsGenerales.add(sliderNbEssaisPossibles, contraintesOptions);
		sliderNbEssaisPossibles.setMajorTickSpacing(1);
		sliderNbEssaisPossibles.setPaintTicks(true);
		sliderNbEssaisPossibles.setPaintLabels(true);
		sliderNbEssaisPossibles
				.setPreferredSize(new Dimension((int) (sliderNbEssaisPossibles.getPreferredSize().getWidth() * 1.5),
						(int) (sliderNbEssaisPossibles.getPreferredSize().getHeight())));

		/*
		 * Panneau des options du Mastermind
		 */
		JPanel panneauOptionsMastermind = new JPanel(new GridBagLayout());
		contraintesSettings.gridy = 2;
		panneauSettings.add(panneauOptionsMastermind, contraintesSettings);
		panneauOptionsMastermind.setBorder(BorderFactory.createTitledBorder("Options du Mastermind :"));

		contraintesOptions.gridx = 1;
		contraintesOptions.gridy = 1;
		panneauOptionsMastermind.add(new JLabel("Nombre de couleurs utilisables :"), contraintesOptions);
		sliderNbCouleursUtilisables = new JSlider(ModeleJeu.NB_COULEURS_UTILISABLES_MIN,
				ModeleJeu.NB_COULEURS_UTILISABLES_MAX,
				(ModeleJeu.NB_COULEURS_UTILISABLES_MIN + ModeleJeu.NB_COULEURS_UTILISABLES_MAX) / 2);
		contraintesOptions.gridy = 2;
		panneauOptionsMastermind.add(sliderNbCouleursUtilisables, contraintesOptions);
		sliderNbCouleursUtilisables.setMajorTickSpacing(1);
		sliderNbCouleursUtilisables.setPaintTicks(true);
		sliderNbCouleursUtilisables.setPaintLabels(true);

		/*
		 * Validation
		 */
		JPanel panneauValidation = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panneauPrincipal.add(panneauValidation, BorderLayout.SOUTH);
		JButton boutonOk = new JButton("OK");
		panneauValidation.add(boutonOk);
		boutonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listeValeurs[0] = sliderNbPionsCombinaisonSecrete.getValue();
				listeValeurs[1] = sliderNbEssaisPossibles.getValue();
				listeValeurs[2] = sliderNbCouleursUtilisables.getValue();
				okQ = true;
				setVisible(false);
			}
		});

		JButton boutonAnnuler = new JButton("Annuler");
		panneauValidation.add(boutonAnnuler);
		boutonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okQ = false;
				setVisible(false);
			}
		});

	}

	public int[] getValeur() {
		okQ = false;
		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);
		return (okQ ? listeValeurs : null);
	}

}
