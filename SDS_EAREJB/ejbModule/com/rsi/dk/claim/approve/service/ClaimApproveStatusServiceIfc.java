package com.rsi.dk.claim.approve.service;

import javax.ejb.Remote;

@Remote
public interface ClaimApproveStatusServiceIfc {
	
	public void sendClaimApproveDetailsToWMS() throws Exception;

}
