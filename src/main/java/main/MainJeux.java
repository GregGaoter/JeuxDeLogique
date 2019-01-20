package main;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MainJeux {

	public static void main(String[] args) {

		/*************
		 * ATTRIBUTS *
		 ************/

		final byte choixNouveauJeu = 1;
		final byte choixOptions = 2;
		final byte choixQuitterApplication = 0;
		final byte choixRecherchePlusMoins = 1;
		final byte choixMastermind = 2;
		final byte choixQuitterMenuJeux = 0;
		final byte choixOptionsJeux = 1;
		final byte choixOptionsLogs = 2;
		final byte choixQuitterMenuOptions = 0;
		final byte choixModeChallenger = 1;
		final byte choixModeDefenseur = 2;
		final byte choixModeDuel = 3;
		final byte choixRejouerMemeJeu = 1;
		final byte choixLancerAutreJeu = 2;
		final byte choixQuitterMenuFinDePartie = 0;

		byte choixMenuPrincipal;
		byte choixMenuJeux = 0;
		byte choixMenuOptions = 0;
		byte choixMenuModes;
		byte choixMenuFinDePartie = 0;

		int nbCasesCombinaison = 4;
		int nbEssais = 4;
		String reponseCorrecte;

		ArrayList<Integer> combinaisonSecrete = new ArrayList<Integer>();
		ArrayList<Integer> combinaisonProposee = new ArrayList<Integer>();
		ArrayList<String> combinaisonReponse = new ArrayList<String>();

		boolean modeDeveloppeurQ = true;

		Scanner sc = new Scanner(System.in);

		/********
		 * CODE *
		 ********/

		/*
		 * Afficher message d�marrage
		 */
		System.out.println("********************");
		System.out.println("* Jeux de logiques *");
		System.out.println("********************");

		do {
			/*
			 * Afficher menu principal
			 */
			System.out.println();
			System.out.println(creerMenu("Principal", creerMenuItem(choixNouveauJeu, "Nouveau jeu"),
					creerMenuItem(choixOptions, "Options"), creerMenuItem(choixQuitterApplication, "Quitter")));
			/*
			 * Choisir menu principal
			 */
			choixMenuPrincipal = sc.nextByte();
			/*
			 * Commutateur menu principal
			 */
			switch (choixMenuPrincipal) {
			case choixNouveauJeu:

				/*
				 * Afficher menu jeux
				 */
				System.out.println();
				System.out.println(creerMenu("Jeux", creerMenuItem(choixRecherchePlusMoins, "Recherche +/-"),
						creerMenuItem(choixMastermind, "Mastermind"),
						creerMenuItem(choixQuitterMenuJeux, "Retour au menu principal")));
				/*
				 * Choisir menu jeu
				 */
				choixMenuJeux = sc.nextByte();
				/*
				 * Commutateur menu jeux
				 */
				switch (choixMenuJeux) {
				case choixRecherchePlusMoins:
				case choixMastermind:
					/*
					 * Afficher menu modes
					 */
					System.out.println();
					System.out.println(creerMenu("Modes", creerMenuItem(choixModeChallenger, "Challenger"),
							creerMenuItem(choixModeDefenseur, "D�fenseur"), creerMenuItem(choixModeDuel, "Duel")));
					/*
					 * Choisir menu modes
					 */
					choixMenuModes = sc.nextByte();
					/*
					 * Commutateur menu modes
					 */
					switch (choixMenuModes) {
					case choixModeChallenger:
						do {
							/*
							 * D�finir combinaison secr�te
							 */
							setCombinaison(combinaisonSecrete, nbCasesCombinaison);
							/*
							 * Afficher plateau de jeu
							 */
							System.out.println();
							System.out.println("Partie de Recherche +/- en mode challenger");
							System.out.println("Combinaison secr�te de " + nbCasesCombinaison + " chiffres");
							System.out.println("-------------------------");
							/*
							 * Afficher solution si mode d�veloppeur
							 */
							if (modeDeveloppeurQ)
								System.out.println("(Combinaison secr�te : "
										+ StringUtils.join(combinaisonSecrete.toArray(), null) + ")");
							int compteurEssai = 0;
							reponseCorrecte = StringUtils.repeat("=", nbCasesCombinaison);
							do {
								compteurEssai++;
								/*
								 * Proposer combinaison
								 */
								System.out.print("Proposition " + compteurEssai + "/" + nbEssais + " : ");
								setCombinaison(combinaisonProposee, nbCasesCombinaison, sc);
								/*
								 * Calculer r�ponse
								 */
								setCombinaison(combinaisonReponse, combinaisonSecrete, combinaisonProposee);
								/*
								 * Afficher r�sultat
								 */
								System.out.println(
										"        R�ponse : " + StringUtils.join(combinaisonReponse.toArray(), null));

							} while (!StringUtils.join(combinaisonReponse.toArray(), null).equals(reponseCorrecte)
									&& compteurEssai < nbEssais);
							if (StringUtils.join(combinaisonReponse.toArray(), null).equals(reponseCorrecte))
								System.out.println("Gagn� !\n");
							else
								System.out.println("Perdu ! Combinaison secr�te : "
										+ StringUtils.join(combinaisonSecrete.toArray(), null) + "\n");
							/*
							 * Afficher menu fin de partie
							 */
							System.out.println();
							System.out.println(creerMenu("Fin de partie",
									creerMenuItem(choixRejouerMemeJeu, "Rejouer au m�me jeu"),
									creerMenuItem(choixLancerAutreJeu, "Lancer un autre jeu"),
									creerMenuItem(choixQuitterMenuFinDePartie, "Quitter")));
							/*
							 * Choisir menu fin de partie
							 */
							choixMenuFinDePartie = sc.nextByte();
							/*
							 * Commutateur menu fin de partie
							 */
							switch (choixMenuFinDePartie) {
							case choixRejouerMemeJeu:
								break;
							case choixLancerAutreJeu:
								choixMenuJeux = choixQuitterMenuJeux;
								break;
							case choixQuitterMenuFinDePartie:
								System.exit(0);
							}
						} while (choixMenuFinDePartie == choixRejouerMemeJeu);
						break;
					case choixModeDefenseur:
						// TODO Implementer le mode d�fenseur
						System.out.println("Mode d�fenseur choisi");
						break;
					case choixModeDuel:
						// TODO Implementer le mode duel
						System.out.println("Mode duel choisi");
					}
					break;
				case choixQuitterMenuJeux:
				}
				break;
			case choixOptions:
				/*
				 * Afficher menu options
				 */
				System.out.println();
				System.out.println(creerMenu("Options", creerMenuItem(choixOptionsJeux, "Jeux"),
						creerMenuItem(choixOptionsLogs, "Logs"),
						creerMenuItem(choixQuitterMenuOptions, "Retour au menu principal")));
				/*
				 * Choisir menu options
				 */
				choixMenuOptions = sc.nextByte();
				/*
				 * Commutateur menu options
				 */
				switch (choixMenuOptions) {
				case choixOptionsJeux:
					// TODO Impl�menter choix options jeux
					System.out.println("Option jeux choisi");
					break;
				case choixOptionsLogs:
					// TODO Impl�menter choix options logs
					System.out.println("Option logs choisi");
					break;
				case choixQuitterMenuOptions:
				}
				break;
			case choixQuitterApplication:
				System.exit(0);
			}
		} while (choixMenuJeux == choixQuitterMenuJeux || choixMenuOptions == choixQuitterMenuOptions);

		sc.close();

		System.out.println();
		System.out.println("*************");
		System.out.println("* Au revoir *");
		System.out.println("*************");

	}

	private static String creerMenuItem(byte selecteur, String message) {
		return selecteur + ". " + message + "\n";
	}

	private static StringBuffer creerMenu(String titre, String... menuItem) {
		String titreMenu = "Menu " + titre + "\n";
		String separateur = StringUtils.repeat('-', titreMenu.length()) + "\n";
		StringBuffer menu = new StringBuffer(titreMenu + separateur);
		for (int i = 0; i < menuItem.length; i++)
			menu.append(menuItem[i]);
		menu.deleteCharAt(menu.length() - 1);
		return menu;
	}

	private static void setCombinaison(ArrayList<Integer> combinaison, int nbCasesCombinaison) {
		combinaison.clear();
		for (int i = 0; i < nbCasesCombinaison; i++)
			combinaison.add(RandomUtils.nextInt(0, 10));
	}

	private static void setCombinaison(ArrayList<Integer> combinaison, int nbCasesCombinaison, Scanner sc) {
		combinaison.clear();
		String combinaisonString = sc.next();
		for (int i = 0; i < combinaisonString.length(); i++)
			combinaison.add(Character.getNumericValue(combinaisonString.charAt(i)));
	}

	private static void setCombinaison(ArrayList<String> combinaisonReponse, ArrayList<Integer> combinaisonSecrete,
			ArrayList<Integer> combinaisonProposee) {
		combinaisonReponse.clear();
		for (int i = 0; i < combinaisonSecrete.size(); i++) {
			if (combinaisonSecrete.get(i) < combinaisonProposee.get(i))
				combinaisonReponse.add("-");
			else if (combinaisonSecrete.get(i) > combinaisonProposee.get(i))
				combinaisonReponse.add("+");
			else
				combinaisonReponse.add("=");
		}
	}

}