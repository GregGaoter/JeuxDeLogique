package com.openclassrooms.jeuxlogiques.vue.dialogue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.RandomUtils;

import com.openclassrooms.jeuxlogiques.controleur.ControleurJeu;
import com.openclassrooms.jeuxlogiques.modele.ModeleJeu;
import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;
import com.openclassrooms.jeuxlogiques.modele.enumeration.PionCommun;
import com.openclassrooms.jeuxlogiques.vue.Observateur;
import com.openclassrooms.jeuxlogiques.vue.labelpion.JLabelPion;
import com.openclassrooms.jeuxlogiques.vue.labelpion.MouseListenerGetPionSecret;
import com.openclassrooms.jeuxlogiques.vue.labelpion.MouseListenerSetPionSecret;
import com.openclassrooms.jeuxlogiques.vue.separateur.AlignementVertical;
import com.openclassrooms.jeuxlogiques.vue.separateur.SeparateurHorizontal;

public class DialogueSelectionCombinaison extends JDialog implements Observateur {

	private static final long serialVersionUID = 1L;

	private final String separateurClef = "-";

	private JPanel panneauPrincipal, panneauSettings, panneauIcone, panneauTypeSelection, panneauSolution,
			panneauPionsUtilisables;
	private JButton boutonOk;
	private JButton boutonRefresh;
	private boolean manuelQ;
	private boolean okQ;
	private HashMap<String, JLabelPion> listePanneauSecret;
	private HashMap<String, JLabelPion> listePanneauPionUtilisable;
	private List<Pion> combinaisonSecrete;

	private ModeleJeu modele;
	private ControleurJeu controleur;

	public DialogueSelectionCombinaison(JFrame fenetreParente, ModeleJeu modele, ControleurJeu controleur) {
		super(fenetreParente, "Choix de la combinaison secrète", true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				JOptionPane.showMessageDialog(fenetreParente,
						new Object[] { "La sélection de la combinaison secrète ne peut pas être annulée.",
								"Vous devez cliquer sur le bouton OK." });
			}
		});
		listePanneauSecret = new HashMap<>();
		listePanneauPionUtilisable = new HashMap<>();
		combinaisonSecrete = new ArrayList<>(listePanneauSecret.size());
		this.modele = modele;
		this.controleur = controleur;
		setCombinaisonSecrete();
		boutonRefresh = new JButton(new ImageIcon(getClass().getResource("/images/refresh_32.png")));
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
		panneauIcone.add(new JLabel(new ImageIcon(getClass().getResource("/images/clef_48.png"))));
		panneauIcone.add(Box.createHorizontalStrut(10));

		/*
		 * Panneau des settings
		 */
		panneauSettings = new JPanel(new GridBagLayout());
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
				boutonRefresh.setEnabled(false);
			}
		});

		JToggleButton toggleButtonAleatoire = new JToggleButton("Aléatoire");
		toggleButtonGroupe.add(toggleButtonAleatoire);
		panneauTypeSelection.add(toggleButtonAleatoire);
		toggleButtonAleatoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manuelQ = false;
				setCombinaisonAleatoire();
				boutonRefresh.setEnabled(true);
			}
		});

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
		JPanel panneauChoixCombinaison = new JPanel();
		contraintes.gridx = 1;
		contraintes.gridy = 3;
		panneauSettings.add(panneauChoixCombinaison, contraintes);

		JPanel panneauCombinaisonSecrete = new JPanel(new GridBagLayout());
		panneauChoixCombinaison.add(panneauCombinaisonSecrete);
		setListePanneauSecret();
		creerPanneau(panneauCombinaisonSecrete, listePanneauSecret, contraintes, "Combinaison secrète");

		panneauChoixCombinaison.add(boutonRefresh);
		boutonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCombinaisonAleatoire();
			}
		});

		/*
		 * Panneau des pions utilisables
		 */
		JPanel panneauPionsUtilisables = new JPanel(new GridBagLayout());
		setListePanneauPionUtilisables();
		creerPanneau(panneauPionsUtilisables, listePanneauPionUtilisable, contraintes,
				"Choisissez les pions de la combinaison secrète :");
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

		toggleButtonManuel.doClick();
		boutonOk.setEnabled(false);

	}

	private String getClef(int x, int y) {
		return String.valueOf(x) + separateurClef + String.valueOf(y);
	}

	public List<Pion> getCombinaisonSecrete() {
		return combinaisonSecrete;
	}

	private void setListePanneauPionUtilisables() {
		listePanneauPionUtilisable.clear();
		Pion pion;
		for (int x = 1; x <= modele.getNbPionsUtilisables(); x++) {
			pion = modele.getPionsUtilisables().get(x - 1);
			listePanneauPionUtilisable.put(getClef(x, 1),
					new JLabelPion(pion, new MouseListenerSetPionSecret(controleur, pion)));
		}
	}

	private void setListePanneauSecret() {
		listePanneauSecret.clear();
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

	public void setPion(HashMap<String, JLabelPion> listePanneau, String clef, Pion pion) {
		listePanneau.get(clef).getMouseListener().setPion(pion);
		listePanneau.get(clef).setIcon(new ImageIcon(getClass().getResource(pion.getNomImage())));
		listePanneau.get(clef).setText(Integer.toString(pion.getValeur()));
	}

	private void setCombinaisonAleatoire() {
		Pion pionAleatoire;
		combinaisonSecrete.clear();
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++) {
			pionAleatoire = modele.getPionsUtilisables().get(RandomUtils.nextInt(0, modele.getNbPionsUtilisables()));
			setPion(listePanneauSecret, getClef(i, 1), pionAleatoire);
			combinaisonSecrete.add(pionAleatoire);
			// modele.setPionSecret(pionAleatoire);
			// modele.getPionSecret(i);
		}
		boutonOk.setEnabled(true);
	}

	private void setCombinaisonSecrete() {
		combinaisonSecrete.clear();
		for (int i = 1; i <= modele.getNbPionsCombinaison(); i++)
			combinaisonSecrete.add(PionCommun.Vide);
	}

	public List<Pion> getValeur() {
		okQ = false;
		pack();
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		return (okQ ? combinaisonSecrete : null);
	}

	public void actualiser() {
		boutonOk.setEnabled(!combinaisonSecrete.contains(PionCommun.Vide));
		for (int i = 0; i < modele.getNbPionsCombinaison(); i++)
			setPion(listePanneauSecret, getClef(i + 1, 1), combinaisonSecrete.get(i));
	}

}
