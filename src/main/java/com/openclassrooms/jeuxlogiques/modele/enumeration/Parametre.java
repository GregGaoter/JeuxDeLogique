package com.openclassrooms.jeuxlogiques.modele.enumeration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

/**
 * <b>Parametre est l'énumération contenant les paramètres du fichier de
 * configuration config.properties.</b>
 * 
 * <p>
 * Un paramètre est caractérisé par les informations suivantes :
 * <ul>
 * <li>La valeur du paramètre dans le fichier de configuration.</li>
 * </ul>
 * </p>
 * 
 * @author Grégory Gautier
 * @version 1.0
 */
public enum Parametre {

	NbPionsCombinaison("nbPionsCombinaison", 4, 6), NbEssais("nbEssais", 4, 12),
	NbCouleursUtilisables("nbCouleursUtilisables", 4, 10), ModeDeveloppeur("modeDeveloppeur", 1, 1);

	private final Logger log = Logger.getLogger(Parametre.class);

	/**
	 * La valeur du paramètre dans le fichier de configuration.
	 * 
	 * @see Parametre#getValeur()
	 */
	private int valeur;

	/**
	 * La valeur minimum du paramètre.
	 * 
	 * @see Parametre#getMin()
	 */
	private int min;

	/**
	 * La valeur maximum du paramètre.
	 * 
	 * @see Parametre#getMax()
	 */
	private int max;

	/**
	 * Constructeur Parametre.</br>
	 * Le constructeur initialise l'attribut valeur à la valeur du paramètre dans le
	 * fichier de configuration et vérifie si la valeur est comprise entre les
	 * bornes min et max.
	 * 
	 * @param parametre le nom du paramètre dans le fichier de configuration
	 * @param min       la valeur minimum du paramètre
	 * @param max       la valeur maximum du paramètre
	 * @see Properties
	 * @see InputStream
	 */
	private Parametre(String parametre, int min, int max) {
		this.min = min;
		this.max = max;
		try {

			Properties proprietes = new Properties();
			String nomFichierProprietes = "config.properties";
			InputStream inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(nomFichierProprietes);

			if (inputStream != null)
				proprietes.load(inputStream);
			else
				log.error("Le fichier de propriétés \"" + nomFichierProprietes
						+ "\" n'a pas été trouvé dans le classpath.");

			// Récupérer la valeur de la propriété
			valeur = Integer.parseInt(proprietes.getProperty(parametre));

			// Tester la valeur de la propriété
			if (valeur < min || valeur > max) {
				log.error("La valeur du paramètre " + parametre
						+ " dans le fichier de configuration doit être comprise entre " + min + " et " + max + ".");
				JOptionPane.showMessageDialog(null,
						new Object[] { "Erreur liée aux paramètres du fichier de configuration.",
								"Veuillez contacter le développeur.", "", "Arrêt du programme." },
						"Erreur", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}

			inputStream.close();

		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException : " + e);
		} catch (IOException e) {
			log.error("IOException : " + e);
		}
	}

	/**
	 * Retourne la valeur du paramètre dans le fichier de configuration.
	 * 
	 * @return la valeur du paramètre dans le fichier de configuration
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * Retourne la valeur minimum du paramètre.
	 * 
	 * @return La valeur minimum du paramètre.
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Retourne la valeur maximum du paramètre.
	 * 
	 * @return La valeur maximum du paramètre.
	 */
	public int getMax() {
		return max;
	}

}