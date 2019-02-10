package com.openclassrooms.jeuxlogiques.vue.dialogue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import com.openclassrooms.jeuxlogiques.modele.Modele;
import com.openclassrooms.jeuxlogiques.vue.plateau.ConstructeurPlateauPion;
import com.openclassrooms.jeuxlogiques.vue.plateau.ConstructeurPlateauSolution;
import com.openclassrooms.jeuxlogiques.vue.plateau.DirecteurConstructionPlateau;
import com.openclassrooms.jeuxlogiques.vue.plateau.PlateauJeu;
import com.openclassrooms.jeuxlogiques.vue.separateur.AlignementVertical;
import com.openclassrooms.jeuxlogiques.vue.separateur.SeparateurHorizontal;

public class DialogueCombinaison extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel panneauPrincipal, panneauSettings, panneauIcone, panneauTypeSelection, panneauSolution,
			panneauPionsUtilisables;
	private JButton boutonOk;
	private JButton boutonAnnuler;
	private boolean manuelQ;
	private boolean okQ;
	private ArrayList<?> solution;

	public DialogueCombinaison(JFrame fenetreParente, Modele modele) {
		super(fenetreParente, "Choix de la combinaison solution", true);
		solution = new ArrayList<>();

		/*
		 * Contraintes du GridBagLayout
		 */
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.insets = new Insets(0, 0, 0, 0);
		contraintes.anchor = GridBagConstraints.WEST;

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
		panneauIcone.add(new JLabel(new ImageIcon(getClass().getResource("/clef_48.png"))));
		panneauIcone.add(Box.createHorizontalStrut(10));

		/*
		 * Panneau des settings
		 */
		panneauSettings = new JPanel();
		panneauSettings.setLayout(new GridBagLayout());
		panneauPrincipal.add(panneauSettings, BorderLayout.CENTER);

		/*
		 * Panneau du type de sélection
		 */
		ButtonGroup toggleButtonGroupe = new ButtonGroup();

		panneauTypeSelection = new JPanel(new GridLayout(1, 2));
		contraintes.gridx = 1;
		contraintes.gridy = 1;
		panneauSettings.add(panneauTypeSelection, contraintes);
		panneauTypeSelection.setBorder(BorderFactory.createTitledBorder("Choisissez le type de sélection :"));

		JToggleButton toggleButtonManuel = new JToggleButton("Manuelle");
		toggleButtonGroupe.add(toggleButtonManuel);
		panneauTypeSelection.add(toggleButtonManuel);
		toggleButtonManuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manuelQ = true;
				System.out.println("Type de sélection : manuelle");
			}
		});

		JToggleButton toggleButtonAleatoire = new JToggleButton("Aléatoire");
		toggleButtonGroupe.add(toggleButtonAleatoire);
		panneauTypeSelection.add(toggleButtonAleatoire);
		toggleButtonAleatoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manuelQ = false;
				System.out.println("Type de sélection : aléatoire");
			}
		});

		toggleButtonManuel.doClick();

		/*
		 * Séparateur horizontal
		 */
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.gridy++;
		panneauSettings.add(new SeparateurHorizontal(20, AlignementVertical.Milieu), contraintes);
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.anchor = GridBagConstraints.WEST;

		/*
		 * Panneau de la solution
		 */
		DirecteurConstructionPlateau directeurConstructionPlateau = new DirecteurConstructionPlateau(
				new ConstructeurPlateauPion(new PlateauJeu(1, modele.getSolution(), "Pions utilisables :")));
		directeurConstructionPlateau.construirePlateau();
		contraintes.gridy++;
		panneauSettings.add(directeurConstructionPlateau.getPlateau(), contraintes);

		/*
		 * Panneau des pions utilisables
		 */
		directeurConstructionPlateau = new DirecteurConstructionPlateau(new ConstructeurPlateauSolution(
				new PlateauJeu(1, modele.getPionsUtilisables(), "Choisissez les pions de la solution :")));
		directeurConstructionPlateau.construirePlateau();
		contraintes.gridy++;
		panneauSettings.add(directeurConstructionPlateau.getPlateau(), contraintes);

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

	public ArrayList<?> getValeur() {
		okQ = false;
		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);
		return (okQ ? solution : null);
	}

}
