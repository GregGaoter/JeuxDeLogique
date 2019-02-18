package com.openclassrooms.jeuxlogiques.vue.dialogue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

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
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.separateur.AlignementVertical;
import com.openclassrooms.jeuxlogiques.separateur.SeparateurHorizontal;
import com.openclassrooms.jeuxlogiques.vue.JLabelPion;

public class DialogueSelectionCombinaison extends JDialog {

	private static final long serialVersionUID = 1L;

	private final String separateurClef = "-";

	private JPanel panneauPrincipal, panneauSettings, panneauIcone, panneauTypeSelection, panneauSolution,
			panneauPionsUtilisables;
	private JButton boutonOk;
	private JButton boutonAnnuler;
	private boolean manuelQ;
	private boolean okQ;
	private HashMap<String, JLabel> listePanneauSecret;
	private HashMap<String, JLabel> listePanneauPionUtilisable;

	private Modele modele;

	public DialogueSelectionCombinaison(JFrame fenetreParente, Modele modele) {
		super(fenetreParente, "Choix de la combinaison solution", true);
		listePanneauSecret = new HashMap<>();
		listePanneauPionUtilisable = new HashMap<>();
		this.modele = modele;

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
		contraintes.gridy = 2;
		panneauSettings.add(new SeparateurHorizontal(20, AlignementVertical.Milieu), contraintes);
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.anchor = GridBagConstraints.WEST;

		/*
		 * Panneau combinaison secrète
		 */
		JPanel panneauCombinaisonSecrete = new JPanel(new GridBagLayout());
		setListePanneau(listePanneauSecret, modele.getNbPionsCombinaison(), 1, PionCommun.Vide);
		creerPanneau(panneauCombinaisonSecrete, listePanneauSecret, contraintes, "Combinaison secrète");
		contraintes.gridx = 1;
		contraintes.gridy = 3;
		panneauSettings.add(panneauCombinaisonSecrete, contraintes);

		/*
		 * Panneau des pions utilisables
		 */
		JPanel panneauPionsUtilisables = new JPanel(new GridBagLayout());
		setListePanneau(listePanneauPionUtilisable, modele.getNbPionsUtilisables(), 1, PionCommun.Vide);
		creerPanneau(panneauPionsUtilisables, listePanneauPionUtilisable, contraintes,
				"Choisissez les pions de la combinaison secrète :");
		contraintes.gridx = 1;
		contraintes.gridy = 4;
		panneauSettings.add(panneauPionsUtilisables, contraintes);
		for (int i = 0; i < modele.getJeu().getPionsJeu().length; i++)
			setPion(listePanneauPionUtilisable, getClef(i + 1, 1), modele.getJeu().getPionsJeu()[i]);

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

	private String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	private void setListePanneau(HashMap<String, JLabel> listePanneau, int xMax, int yMax, Pion pion) {
		for (int y = 1; y <= yMax; y++) {
			for (int x = 1; x <= xMax; x++)
				listePanneau.put(getClef(x, y), new JLabelPion(pion));
		}
	}

	private void creerPanneau(JPanel panneau, HashMap<String, JLabel> listePanneau, GridBagConstraints c,
			String titre) {
		panneau.setBorder(BorderFactory.createTitledBorder(titre));
		for (Map.Entry<String, JLabel> item : listePanneau.entrySet()) {
			c.gridx = Integer.parseInt(item.getKey().split(separateurClef)[0]);
			c.gridy = Integer.parseInt(item.getKey().split(separateurClef)[1]);
			panneau.add(item.getValue(), c);
		}
	}

	public void setPion(HashMap<String, JLabel> listePanneau, String clef, Pion pion) {
		listePanneau.get(clef).setIcon(new ImageIcon(getClass().getResource(pion.getNomImage())));
		listePanneau.get(clef).setText(Integer.toString(pion.getValeur()));
	}

	public HashMap<String, JLabel> getValeur() {
		okQ = false;
		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);
		return (okQ ? listePanneauSecret : null);
	}

}
