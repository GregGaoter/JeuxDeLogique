package com.openclassrooms.jeuxlogiques.controleur.service;

import java.util.List;

import com.openclassrooms.jeuxlogiques.modele.enumeration.Pion;

public interface ServiceDeCalcul {

	public List<Pion> calculerReponse(List<Pion> proposition, List<Pion> solution);

}
