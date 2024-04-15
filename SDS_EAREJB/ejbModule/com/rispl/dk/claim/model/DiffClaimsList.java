package com.rispl.dk.claim.model;

import java.util.Vector;

import rispl.dkart.services.detail.claim.ClaimDetailTable;

public class DiffClaimsList {
	Vector<ClaimDetailTable> needToBeAccepted;
	Vector<ClaimDetailTable> autoAccepted;

	public Vector<ClaimDetailTable> getNeedToBeAccepted() {
		return needToBeAccepted;
	}

	public void setNeedToBeAccepted(Vector<ClaimDetailTable> needToBeAccepted) {
		this.needToBeAccepted = needToBeAccepted;
	}

	public Vector<ClaimDetailTable> getAutoAccepted() {
		return autoAccepted;
	}

	public void setAutoAccepted(Vector<ClaimDetailTable> autoAccepted) {
		this.autoAccepted = autoAccepted;
	}

}
