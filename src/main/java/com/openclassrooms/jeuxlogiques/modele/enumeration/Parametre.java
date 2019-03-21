package com.openclassrooms.jeuxlogiques.modele.enumeration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

/**
 * <b>Parametre est l'�num�ration contenant les param�tres du fichier de
 * configuration config.properties.</b>
 * 
 * <p>
 * Un param�tre est caract�ris� par les informations suivantes :
 * <ul>
 * <li>La valeur du param�tre dans le fichier de configuration.</li>
 * </ul>
 * </p>
 * 
 * @author Gr�gory Gautier
 * @version 1.0
 */
public enum Parametre {

	NbPionsCombinaison("nbPionsCombinaison", 4, 6), NbEssais("nbEssais", 4, 12),
	NbCouleursUtilisables("nbCouleursUtilisables", 4, 10), ModeDeveloppeur("modeDeveloppeur", 1, 1);

	private final Logger log = Logger.getLogger(Parametre.class);

	/**
	 * La valeur du param�tre dans le fichier de configuration.
	 * 
	 * @see Parametre#getValeur()
	 */
	private int valeur;

	/**
	 * La valeur minimum du param�tre.
	 * 
	 * @see Parametre#getMin()
	 */
	private int min;

	/**
	 * La valeur maximum du param�tre.
	 * 
	 * @see Parametre#getMax()
	 */
	private int max;

	/**
	 * Constructeur Parametre.</br>
	 * Le constructeur initialise l'attribut valeur � la valeur du param�tre dans le
	 * fichier de configuration et v�rifie si la valeur est comprise entre les
	 * bornes min et max.
	 * 
	 * @param parametre le nom du param�tre dans le fichier de configuration
	 * @param min       la valeur minimum du param�tre
	 * @param max       la valeur maximum du param�tre
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
				log.error("Le fichier de propri�t�s \"" + nomFichierProprietes
						+ "\" n'a pas �t� trouv� dans le classpath.");

			// R�cup�rer la valeur de la propri�t�
			valeur = Integer.parseInt(proprietes.getProperty(parametre));

			// Tester la valeur de la propri�t�
			if (valeur < min || valeur > max) {
				log.error("La valeur du param�tre " + parametre
						+ " dans le fichier de configuration doit �tre comprise entre " + min + " et " + max + ".");
				JOptionPane.showMessageDialog(null,
						new Object[] { "Erreur li�e aux param�tres du fichier de configuration.",
								"Veuillez contacter le d�veloppeur.", "", "Arr�t du programme." },
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
	 * Retourne la valeur du param�tre dans le fichier de configuration.
	 * 
	 * @return la valeur du param�tre dans le fichier de configuration
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * Retourne la valeur minimum du param�tre.
	 * 
	 * @return La valeur minimum du param�tre.
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Retourne la valeur maximum du param�tre.
	 * 
	 * @return La valeur maximum du param�tre.
	 */
	public int getMax() {
		return max;
	}

}