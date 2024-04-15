package com.rsi.dk.claim.approve.dao.factory;

import javax.persistence.EntityManagerFactory;

import com.rsi.dk.claim.approve.dao.ClaimApproveStatusDAO;
import com.rsi.dk.claim.approve.dao.ClaimApproveStatusDAOI;

public class ClaimApproveStatusDAOFactory {

	public static ClaimApproveStatusDAOI dao = null;

	public static ClaimApproveStatusDAOI getClaimApproveStatus(EntityManagerFactory emf) {
		dao = new ClaimApproveStatusDAO(emf);
		return dao;

	}

}
