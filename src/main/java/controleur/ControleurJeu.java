package controleur;

import modele.ModeleJeu;
import vue.VueJeu;
import vue.dialogue.DialogueJeu;

public abstract class ControleurJeu {

	/*
	 * Le contr�leur est li� au mod�le et � la vue.
	 */
	protected ModeleJeu modele;
	protected VueJeu vue;

	/*
	 * Le constructeur du contr�leur re�oit le mod�le. Il cr�e ensuite la vue et
	 * initialise le mod�le.
	 */
	public ControleurJeu(ModeleJeu modele) {
		this.modele = modele;
		vue = new VueJeu(modele, this);
		vue.creerVue();
	}

	/*
	 * M�thodes communes � tous les contr�leurs
	 */
	public void lancerNouveauJeu(DialogueJeu dialogue) {
		String jeu = dialogue.getValeur();
		if (jeu != null)
			System.out.println("Jeu s�lectionn� : " + jeu);
		else
			System.out.println("Aucun jeu s�lectionn�");
		dialogue.dispose();
	}

	public void quitterApplication() {
		System.exit(0);
	}

	public void configurerLogs() {
		System.out.println("Configurer les logs");
	}

	public void consulterReglesJeux() {
		System.out.println("Consulter les r�gles des jeux");
	}

	/*
	 * La classe abstraite du contr�leur d�finit toutes les m�thodes abstraites que
	 * la vue peut appel�e sur le contr�leur.
	 */
	public abstract void configurerJeux();

}