package com.openclassrooms.jeuxlogiques.modele.enumeration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

	NbPionsCombinaison("nbPionsCombinaison"), NbEssais("nbEssais"), NbCouleursUtilisables("nbCouleursUtilisables"),
	ModeDeveloppeur("modeDeveloppeur");

	private final Logger log = Logger.getLogger(Parametre.class);

	/**
	 * La valeur du param�tre dans le fichier de configuration.
	 * 
	 * @see Parametre#getValeur()
	 */
	private int valeur;

	/**
	 * Constructeur Parametre.</br>
	 * Le constructeur initialise l'attribut valeur � la valeur du param�tre dans le
	 * fichier de configuration.
	 * 
	 * @param parametre : le nom du param�tre dans le fichier de configuration.
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
				log.error("Le fichier de propri�t�s \"" + nomFichierProprietes
						+ "\" n'a pas �t� trouv� dans le classpath.");

			// R�cup�rer la valeur de la propri�t�
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
	 * Retourne la valeur du param�tre.
	 * 
	 * @return La valeur du param�tre.
	 */
	public int getValeur() {
		return valeur;
	}

}