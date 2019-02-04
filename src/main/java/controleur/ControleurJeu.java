package controleur;

import modele.ModeleJeu;
import vue.VueJeu;
import vue.dialogue.DialogueJeu;

public abstract class ControleurJeu {

	/*
	 * Le contrôleur est lié au modèle et à la vue.
	 */
	protected ModeleJeu modele;
	protected VueJeu vue;

	/*
	 * Le constructeur du contrôleur reçoit le modèle. Il crée ensuite la vue et
	 * initialise le modèle.
	 */
	public ControleurJeu(ModeleJeu modele) {
		this.modele = modele;
		vue = new VueJeu(modele, this);
		vue.creerVue();
	}

	/*
	 * Méthodes communes à tous les contrôleurs
	 */
	public void lancerNouveauJeu(DialogueJeu dialogue) {
		String jeu = dialogue.getValeur();
		if (jeu != null)
			System.out.println("Jeu sélectionné : " + jeu);
		else
			System.out.println("Aucun jeu sélectionné");
		dialogue.dispose();
	}

	public void quitterApplication() {
		System.exit(0);
	}

	public void configurerLogs() {
		System.out.println("Configurer les logs");
	}

	public void consulterReglesJeux() {
		System.out.println("Consulter les règles des jeux");
	}

	/*
	 * La classe abstraite du contrôleur définit toutes les méthodes abstraites que
	 * la vue peut appelée sur le contrôleur.
	 */
	public abstract void configurerJeux();

}