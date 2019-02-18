package com.openclassrooms.jeuxlogiques.modele.enumeration;

public enum Mode {

	Challenger(false), Defenseur(true), Duel(true);

	private boolean selectionCombinaisonQ;

	private Mode(boolean combinaisonQ) {
		this.selectionCombinaisonQ = combinaisonQ;
	}

	public boolean getSelectionCombinaisonQ() {
		return selectionCombinaisonQ;
	}

}
