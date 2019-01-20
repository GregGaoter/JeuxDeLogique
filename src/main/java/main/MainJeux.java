package main;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang3.Conversion;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

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
		String combinaisonProposeeString;

		boolean modeDeveloppeurQ = true;

		Scanner sc = new Scanner(System.in);

		/********
		 * CODE *
		 ********/

		/*
		 * Afficher message démarrage
		 */
		System.out.println("********************");
		System.out.println("* Jeux de logiques *");
		System.out.println("********************");

		do {
			/*
			 * Afficher menu principal
			 */
			System.out.println();
			System.out.println("Menu principal");
			System.out.println("--------------");
			System.out.println(choixNouveauJeu + ". Nouveau jeu");
			System.out.println(choixOptions + ". Options");
			System.out.println(choixQuitterApplication + ". Quitter");
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
				System.out.println("Menu des jeux");
				System.out.println("-------------");
				System.out.println(choixRecherchePlusMoins + ". Recherche +/-");
				System.out.println(choixMastermind + ". Mastermind");
				System.out.println(choixQuitterMenuJeux + ". Retour au menu principal");
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
					System.out.println("Menu des modes");
					System.out.println("--------------");
					System.out.println(choixModeChallenger + ". Challenger");
					System.out.println(choixModeDefenseur + ". Défenseur");
					System.out.println(choixModeDuel + ". Duel");
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
							 * Définir combinaison secrète
							 */
							combinaisonSecrete.clear();
							for (int i = 0; i < nbCasesCombinaison; i++)
								combinaisonSecrete.add(RandomUtils.nextInt(0, 10));
							/*
							 * Afficher plateau de jeu
							 */
							System.out.println();
							System.out.println("Partie de Recherche +/- en mode challenger");
							System.out.println("Combinaison secrète de " + nbCasesCombinaison + " chiffres");
							System.out.println("-------------------------");
							/*
							 * Afficher solution si mode développeur
							 */
							if (modeDeveloppeurQ)
								System.out.println("(Combinaison secrète : "
										+ StringUtils.join(combinaisonSecrete.toArray(), null) + ")");
							int compteurEssai = 0;
							reponseCorrecte = StringUtils.repeat("=", nbCasesCombinaison);
							do {
								compteurEssai++;
								/*
								 * Proposer combinaison
								 */
								System.out.print("Proposition " + compteurEssai + "/" + nbEssais + " : ");
								combinaisonProposee.clear();
								combinaisonProposeeString = sc.next();
								for (int i = 0; i < combinaisonProposeeString.length(); i++)
									combinaisonProposee
											.add(Character.getNumericValue(combinaisonProposeeString.charAt(i)));
								/*
								 * Calculer réponse
								 */
								combinaisonReponse.clear();
								for (int i = 0; i < nbCasesCombinaison; i++) {
									if (combinaisonSecrete.get(i) < combinaisonProposee.get(i))
										combinaisonReponse.add("-");
									else if (combinaisonSecrete.get(i) > combinaisonProposee.get(i))
										combinaisonReponse.add("+");
									else
										combinaisonReponse.add("=");
								}
								/*
								 * Afficher résultat
								 */
								System.out.println(
										"        Réponse : " + StringUtils.join(combinaisonReponse.toArray(), null));

							} while (!StringUtils.join(combinaisonReponse.toArray(), null).equals(reponseCorrecte)
									&& compteurEssai < nbEssais);
							if (StringUtils.join(combinaisonReponse.toArray(), null).equals(reponseCorrecte))
								System.out.println("Gagné !\n");
							else
								System.out.println("Perdu ! Combinaison secrète : "
										+ StringUtils.join(combinaisonSecrete.toArray(), null) + "\n");
							/*
							 * Afficher menu fin de partie
							 */
							System.out.println("Menu de fin de partie");
							System.out.println("---------------------");
							System.out.println(choixRejouerMemeJeu + ". Rejouer au même jeu");
							System.out.println(choixLancerAutreJeu + ". Lancer un autre jeu");
							System.out.println(choixQuitterMenuFinDePartie + ". Quitter");
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
						// TODO Implementer le mode défenseur
						System.out.println("Mode défenseur choisi");
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
				System.out.println("Menu des options");
				System.out.println("----------------");
				System.out.println("1. Jeux");
				System.out.println("2. Logs");
				System.out.println("0. Retour au menu principal");
				/*
				 * Choisir menu options
				 */
				choixMenuOptions = sc.nextByte();
				/*
				 * Commutateur menu options
				 */
				switch (choixMenuOptions) {
				case choixOptionsJeux:
					// TODO Implémenter choix options jeux
					System.out.println("Option jeux choisi");
					break;
				case choixOptionsLogs:
					// TODO Implémenter choix options logs
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

}