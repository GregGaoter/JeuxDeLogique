package com.openclassrooms.jeuxlogiques.modele;

import com.openclassrooms.jeuxlogiques.vue.Observateur;

public interface SujetObservable {

	public void ajouterObservateur(Observateur observateur);

	public void supprimerObservateur(Observateur observateur);

	public void notifierObservateur();

}