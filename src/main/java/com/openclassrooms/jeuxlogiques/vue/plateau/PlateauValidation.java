package com.openclassrooms.jeuxlogiques.vue.plateau;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.vue.VueJeu;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public class PlateauValidation extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlateauValidation(VueJeu vue, FabriqueDePion fabriqueDePion) {

		setLayout(new GridBagLayout());

		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.insets = new Insets(0, 0, 0, 0);
		contraintes.anchor = GridBagConstraints.CENTER;

		JButton boutonProposition = new JButton("Valider");
		setBorder(BorderFactory.createTitledBorder("Validation"));
		contraintes.gridx = 1;
		for (int y = 0; y < vue.getModele().getNbEssais() - 1; y++) {
			contraintes.gridy = y + 1;
			add(fabriqueDePion.creerPionTransparent(), contraintes);
		}
		contraintes.gridy = vue.getModele().getNbEssais();
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		add(boutonProposition, contraintes);
		boutonProposition.setEnabled(false);
	}

}
