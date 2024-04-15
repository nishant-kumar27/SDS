package com.retailsols.sds.ediupload;

import java.util.ArrayList;

import javax.ejb.Remote;

import rispl.dk.itemLookUp.PLUItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;

@Remote
public interface EDIOrderUploadIfc {

	public void saveEDIOrder(String custId, String siteId, ArrayList<PLUItem> items, OrderTranHeader ordHead);
}
