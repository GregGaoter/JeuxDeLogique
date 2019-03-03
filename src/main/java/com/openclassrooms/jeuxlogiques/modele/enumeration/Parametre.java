package com.openclassrooms.jeuxlogiques.modele.enumeration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public enum Parametre {

	NbPionsCombinaison("nbPionsCombinaison"), NbEssais("nbEssais"), NbCouleursUtilisables("nbCouleursUtilisables");

	private final Logger log = Logger.getLogger(Parametre.class);

	private int valeur;

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

			log.debug(toString() + " = " + valeur);
			inputStream.close();

		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException : " + e);
		} catch (IOException e) {
			log.error("IOException : " + e);
		}
	}

	public int getValeur() {
		return valeur;
	}

}