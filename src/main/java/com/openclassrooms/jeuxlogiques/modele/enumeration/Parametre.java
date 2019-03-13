package com.openclassrooms.jeuxlogiques.modele.enumeration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

	NbPionsCombinaison("nbPionsCombinaison"), NbEssais("nbEssais"), NbCouleursUtilisables("nbCouleursUtilisables"),
	ModeDeveloppeur("modeDeveloppeur");

	private final Logger log = Logger.getLogger(Parametre.class);

	/**
	 * La valeur du paramètre dans le fichier de configuration.
	 * 
	 * @see Parametre#getValeur()
	 */
	private int valeur;

	/**
	 * Constructeur Parametre.</br>
	 * Le constructeur initialise l'attribut valeur à la valeur du paramètre dans le
	 * fichier de configuration.
	 * 
	 * @param parametre : le nom du paramètre dans le fichier de configuration.
	 */
	private Parametre(String parametre) {
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

			// log.debug(toString() + " = " + valeur);
			inputStream.close();

		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException : " + e);
		} catch (IOException e) {
			log.error("IOException : " + e);
		}
	}

	/**
	 * Retourne la valeur du paramètre.
	 * 
	 * @return La valeur du paramètre.
	 */
	public int getValeur() {
		return valeur;
	}

}