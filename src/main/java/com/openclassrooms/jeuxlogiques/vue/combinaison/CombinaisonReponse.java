package com.openclassrooms.jeuxlogiques.vue.combinaison;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.openclassrooms.jeuxlogiques.enumeration.Reponse;
import com.openclassrooms.jeuxlogiques.vue.Vue;
import com.openclassrooms.jeuxlogiques.vue.pion.FabriqueDePion;

public class CombinaisonReponse extends JPanel {

	private static final long serialVersionUID = 1L;

	public CombinaisonReponse(Vue vue, FabriqueDePion fabriqueDePion) {
		setLayout(new GridLayout(1, vue.getModele().getNbPionsCombinaison()));
		for (int i = 0; i < vue.getModele().getNbPionsCombinaison(); i++)
			add(fabriqueDePion.creerPionReponse(Reponse.Plus));
	}

}
