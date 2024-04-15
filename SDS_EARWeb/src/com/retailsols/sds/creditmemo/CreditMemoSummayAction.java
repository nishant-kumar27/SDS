package com.retailsols.sds.creditmemo;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import rispl.db.model.claim.ClaimTranLineItem;
import rispl.dkart.services.ejb.LookUpEmployeeIfc;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

public class CreditMemoSummayAction extends DSAction{

	private static final long serialVersionUID = 1L;

	private CreditMemoBean crMemoBean;
	private OrderTranHeader claimTransaction;
	private String crmemoId,custId,custName;
	private String[] salesAgents;
	private List<String> list;
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}

	public List<OrderTranLineItem> orderTranLineItems;
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}


	
	public OrderTranHeader getClaimTransaction() {
		return claimTransaction;
	}
	public void setClaimTransaction(OrderTranHeader claimTransaction) {
		this.claimTransaction = claimTransaction;
	}
	public String getCrmemoId() {
		return crmemoId;
	}
	public void setCrmemoId(String crmemoId) {
		this.crmemoId = crmemoId;
	}
	public CreditMemoBean getBean() {
		return crMemoBean;
	}
	public void setBean(CreditMemoBean bean) {
		this.crMemoBean = bean;
	}
	
	public void setSalesAgents(String[] salesAgents) {
		
		this.salesAgents=salesAgents;
	}
	public String[] getSalesAgents() {
		
	return salesAgents;
	}

	public String execute() {
		try {
			list = new ArrayList<>();
			LookUpCreditMemoIfc trans = DKartContext.getCreditMemoDetails();
			// get the credit memo details
			setBean(trans.getCreditMemoDetails(crmemoId));
			// now get the claim details by claim id 
			setClaimTransaction(trans.getClaimDetailsByClaimID(crMemoBean.getClaimId())); 
			setSalesAgents(getSalesAgentsItemLevel(claimTransaction));
			custId=claimTransaction.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId();
			custName=claimTransaction.getCustomer().getCustomerHeader().getCtNm();
			orderTranLineItems=claimTransaction.getOrdTranLineItems();
			for (int i = 0; i < salesAgents.length; i++) {
				list.add(salesAgents[i]); //adding claim Line Items
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	private String[] getSalesAgentsItemLevel(OrderTranHeader orders) throws Exception {
		String[] emids=new String[orders.getOrdTranLineItems().size()];
		for(int i=0;i<orders.getOrdTranLineItems().size();i++){
			if(orders.getOrdTranLineItems().get(i).getEmpId()!=null){
			emids[i]=orders.getOrdTranLineItems().get(i).getEmpId();
			}
			else{
				emids[i]=orders.getEmId();
			}
		}
		LookUpEmployeeIfc lookUpEmp = DKartContext.getLookupEmployee();
		List<String> salesAgent=lookUpEmp.getSalesAgentsList(emids);
		String[] salesAgntsList=new String[salesAgent.size()];
		salesAgntsList=salesAgent.toArray(salesAgntsList);
		return salesAgntsList;
	}
}
