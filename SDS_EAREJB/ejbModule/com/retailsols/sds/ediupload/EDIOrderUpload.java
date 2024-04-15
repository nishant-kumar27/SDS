package com.retailsols.sds.ediupload;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import rispl.dk.itemLookUp.PLUItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;

@Stateless(mappedName="ediOrderUpload")
@LocalBean
public class EDIOrderUpload implements EDIOrderUploadIfc{

	@Override
	public void saveEDIOrder(String custId, String siteId, ArrayList<PLUItem> items, OrderTranHeader ordHead) {
		// now save the order details to database as saved order
		
	}
}
