package com.rsi.dk.claim.approve.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.xml.ws.WebServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rsi.dk.claim.approve.dao.factory.ClaimApproveStatusDAOFactory;
import com.rsi.dk.claim.approve.dto.ClaimApproveListDTO;
import com.rsi.dk.wms.service.CustomerOrder;
import com.rsi.dk.wms.service.CustomerOrderException;
import com.rsi.dk.wms.service.CustomerOrderService;

import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import utility.ConfigUtils;
import wms.dk.rsi.customerdetails.CustomerOrderReturnDetails;
import wms.dk.rsi.customerdetails.CustomerOrderReturnStatus;

@Stateless(mappedName = "sendClaimDetails")
@LocalBean
public class ClaimApproveStatusService implements ClaimApproveStatusServiceIfc {

	private static final Logger LOGGER = LogManager.getLogger(ClaimApproveStatusService.class);

	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	@Override
	public void sendClaimApproveDetailsToWMS() throws Exception {
		// TODO Auto-generated method stub
		try {
			LOGGER.info("sendClaimApproveDetailsToWMS Method Started Executing..");
			postClaimApproveDetails();
			LOGGER.info("sendClaimApproveDetailsToWMS Method Completed Successfully");
		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	public void postClaimApproveDetails() throws Exception {
		LOGGER.info("postClaimApproveDetails Started Executing..");
		LOGGER.info("Calling webservice Method To Post Claim Details");
		CustomerOrderService orderService = null;
		CustomerOrder port = null;
		List<CustomerOrderReturnDetails> returnDetailList = null;
		List<CustomerOrderReturnStatus> returnStatusList = null;
		boolean flag = false;
		try {
			orderService = new CustomerOrderService(ConfigUtils.getInstance().getCustomerOrderServiceUrl());
			port = orderService.getCustomerOrderPort();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("Unable To Access The Web Service " + e);
			flag = false;
		}

		ClaimApproveListDTO dto = new ClaimApproveListDTO(emf);
		try {
			returnDetailList = dto.getCustomerOrderReturnDetails();
		} catch (Exception e) {
			LOGGER.error("Unable To Fetch The Approve Claim Request From The Database " + e);
			flag = false;
		}
		if (!returnDetailList.isEmpty()) {
			try {
				returnStatusList = port.returnCustomerOrder(returnDetailList);
				flag = true;
			} catch (CustomerOrderException e) {
				LOGGER.error("Customer Order Exception " + e);
				flag = false;
			} catch (WebServiceException e1) {
				LOGGER.error("Web Service Exception " + e1);
				flag = false;
			} catch (Exception e2) {
				LOGGER.error("Exception Occured While Calling The Web Service " + e2);
				flag = false;
			}

			if (flag) {
				if (!returnStatusList.isEmpty()) {
					try {
						flag = ClaimApproveStatusDAOFactory.getClaimApproveStatus(emf)
								.updateProcessedClaimReqToWms(returnStatusList);
					} catch (Exception e) {
						LOGGER.error("Unable to Update The The WMS Flag which ever transfered to WMS" + e);
					}
				}
			}
		} else {
			LOGGER.info("There Is No Approve Claim Request To Post To WMS");
		}
		LOGGER.info("postClaimApproveDetails Method Completed Successfully");
	}
}
