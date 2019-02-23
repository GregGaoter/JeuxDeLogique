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

import com.openclassrooms.jeuxlogiques.controleur.Controleur;
import com.openclassrooms.jeuxlogiques.modele.Modele;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.separateur.AlignementVertical;
import com.openclassrooms.jeuxlogiques.separateur.SeparateurHorizontal;
import com.openclassrooms.jeuxlogiques.vue.Observateur;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;
import com.openclassrooms.jeuxlogiques.vue.labelpion.MouseListenerGetPionSecret;
import com.openclassrooms.jeuxlogiques.vue.labelpion.MouseListenerSetPionSecret;

public class DialogueSelectionCombinaison extends JDialog implements Observateur {

	private static final long serialVersionUID = 1L;

	private final String separateurClef = "-";

	private JPanel panneauPrincipal, panneauSettings, panneauIcone, panneauTypeSelection, panneauSolution,
			panneauPionsUtilisables;
	private JButton boutonOk;
	private JButton boutonAnnuler;
	private JButton boutonRefresh;
	private boolean manuelQ;
	private boolean okQ;
	private HashMap<String, JLabelPion> listePanneauSecret;
	private HashMap<String, JLabelPion> listePanneauPionUtilisable;

	private Modele modele;
	private Controleur controleur;

	public DialogueSelectionCombinaison(JFrame fenetreParente, Modele modele, Controleur controleur) {
		super(fenetreParente, "Choix de la combinaison solution", true);
		listePanneauSecret = new HashMap<>();
		listePanneauPionUtilisable = new HashMap<>();
		this.modele = modele;
		this.controleur = controleur;
		modele.ajouterObservateur(this);

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
		 * Panneau du type de s�lection
		 */
		ButtonGroup toggleButtonGroupe = new ButtonGroup();

		panneauTypeSelection = new JPanel(new GridLayout(1, 2));
		contraintes.gridx = 1;
		contraintes.gridy = 1;
		panneauSettings.add(panneauTypeSelection, contraintes);
		panneauTypeSelection.setBorder(BorderFactory.createTitledBorder("Choisissez le type de s�lection :"));

		JToggleButton toggleButtonManuel = new JToggleButton("Manuelle");
		toggleButtonGroupe.add(toggleButtonManuel);
		panneauTypeSelection.add(toggleButtonManuel);
		toggleButtonManuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manuelQ = true;
				System.out.println("Type de s�lection : manuelle");
			}
		});

		JToggleButton toggleButtonAleatoire = new JToggleButton("Al�atoire");
		toggleButtonGroupe.add(toggleButtonAleatoire);
		panneauTypeSelection.add(toggleButtonAleatoire);
		toggleButtonAleatoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manuelQ = false;
				System.out.println("Type de s�lection : al�atoire");
			}
		});

		toggleButtonManuel.doClick();

		/*
		 * S�parateur horizontal
		 */
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.gridy = 2;
		panneauSettings.add(new SeparateurHorizontal(20, AlignementVertical.Milieu), contraintes);
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.anchor = GridBagConstraints.WEST;

		/*
		 * Panneau combinaison secr�te
		 */
		JPanel panneauChoixCombinaison = new JPanel();
		contraintes.gridx = 1;
		contraintes.gridy = 3;
		panneauSettings.add(panneauChoixCombinaison, contraintes);

		JPanel panneauCombinaisonSecrete = new JPanel(new GridBagLayout());
		panneauChoixCombinaison.add(panneauCombinaisonSecrete);
		setListePanneauSecret();
		creerPanneau(panneauCombinaisonSecrete, listePanneauSecret, contraintes, "Combinaison secr�te");

		boutonRefresh = new JButton(new ImageIcon(getClass().getResource("/refresh_32.png")));
		panneauChoixCombinaison.add(boutonRefresh);
		boutonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// setCombinaisonAleatoire();
			}
		});

		/*
		 * Panneau des pions utilisables
		 */
		JPanel panneauPionsUtilisables = new JPanel(new GridBagLayout());
		setListePanneauPionUtilisables();
		creerPanneau(panneauPionsUtilisables, listePanneauPionUtilisable, contraintes,
				"Choisissez les pions de la combinaison secr�te :");
		contraintes.gridx = 1;
		contraintes.gridy = 4;
		panneauSettings.add(panneauPionsUtilisables, contraintes);

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

	private void setListePanneauPionUtilisables() {
		Pion pion;
		for (int x = 1; x <= modele.getNbPionsUtilisables(); x++) {
			pion = modele.getPionsUtilisables().get(x - 1);
			listePanneauPionUtilisable.put(getClef(x, 1),
					new JLabelPion(pion, new MouseListenerSetPionSecret(controleur, pion)));
		}
	}

	private void setListePanneauSecret() {
		for (int x = 1; x <= modele.getNbPionsCombinaison(); x++)
			listePanneauSecret.put(getClef(x, 1),
					new JLabelPion(PionCommun.Vide, new MouseListenerGetPionSecret(controleur, PionCommun.Vide, x)));
	}

	private void creerPanneau(JPanel panneau, HashMap<String, JLabelPion> listePanneau, GridBagConstraints c,
			String titre) {
		panneau.setBorder(BorderFactory.createTitledBorder(titre));
		for (Map.Entry<String, JLabelPion> item : listePanneau.entrySet()) {
			c.gridx = Integer.parseInt(item.getKey().split(separateurClef)[0]);
			c.gridy = Integer.parseInt(item.getKey().split(separateurClef)[1]);
			panneau.add(item.getValue(), c);
		}
	}

	public void setPionUtilisable(HashMap<String, JLabelPion> listePanneau, String clef, Pion pion) {
		listePanneau.get(clef).getMouseListener().setPion(pion);
		listePanneau.get(clef).setIcon(new ImageIcon(getClass().getResource(pion.getNomImage())));
		listePanneau.get(clef).setText(Integer.toString(pion.getValeur()));
	}

	public HashMap<String, JLabelPion> getValeur() {
		okQ = false;
		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);
		return (okQ ? listePanneauSecret : null);
	}

	public void actualiser() {
		for (int i = 0; i < modele.getCombinaisonSecrete().size(); i++)
			setPionUtilisable(listePanneauSecret, getClef(i + 1, 1), modele.getCombinaisonSecrete().get(i));
	}

}
