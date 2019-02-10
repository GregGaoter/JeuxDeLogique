package com.openclassrooms.jeuxlogiques.enumeration;

public enum Mode {

	Challenger(false), Defenseur(true), Duel(true);

	private boolean combinaisonQ;

	private Mode(boolean combinaisonQ) {
		this.combinaisonQ = combinaisonQ;
	}

	public boolean getCombinaisonQ() {
		return combinaisonQ;
	}

}