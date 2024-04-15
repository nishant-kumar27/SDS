package rispl.ds.payment;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.dispatcher.SessionMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rispl.dk.Employee.EmployeeIfc;
import rispl.dk.customer.CustomerIfc;
import rispl.dkart.services.ejb.LookUpCustomerIfc;
import rispl.dkart.services.entities.customer.CustomerSiteInvoice;
import rispl.dkart.services.payment.PaymentDetails;
import rispl.dkart.services.payment.TenderDetails;
import rispl.dkart.services.payment.TenderDetails.TenderMode;
import rispl.dkart.services.transaction.save.SavePostPaymentTransactionIfc;
import rispl.dkservices.common.CustomerSearchCriteria;
import rispl.ds.DSAction;
import rispl.ds.context.DKartContext;

/**
 * 
 */
public class Payment extends DSAction {

	private static final long serialVersionUID = 1L;
	private static final String PAYMENT_SESSION_DATA = "payment_details";
	static final Logger LOGGER = LogManager.getLogger(Payment.class);
	private boolean status;
	//private SessionMap<String, Object> sessionMap;
	private EmployeeIfc employee;
	private BigDecimal pendingAmount;
	private BigDecimal amtBeingPaid = new BigDecimal("0.00");
	private String customerId, invoiceNo;
	private String customerName;
	private int noOfInvoices;
	TenderDetails tender_line = new TenderDetails();
	private PaymentDetails payment_details;
	private String tenderMode;
	private File depositSlip; // Uploaded check slip file path
	private String depositSlipFileName; //to get the check slip file name
	private BigDecimal cashAmount; // Total cash amount
	private int index; // index of tender to delete when delete is clicked in UI

	// Not viewed in html page
	private CustomerSearchCriteria criteria;
	private CustomerIfc[] customer;

	public Payment() {

	}

	public String paymentSearch() {
		//sessionMap = super.getSessionmap();
		employee = getEmployee();
		if (employee == null) {
			return "logout";
		}
		return SUCCESS;
	}

	public String recordPayment() {
		status = false;
		//sessionMap = super.getSessionmap();
		employee = super.getEmployee();
		// if customer is null the redirect to payment search action
		if (customerId != null && !customerId.equalsIgnoreCase("")) {
			payment_details = new PaymentDetails();
			payment_details.setCustomerId(customerId);
			payment_details.setOpratorId(employee.getEmployeeId());
			payment_details.setCustomerInvoiceId(invoiceNo);
			
			CustomerSiteInvoice csi=(CustomerSiteInvoice) getFromSession(SESSION.INVOICE_DETAIL);
			if(csi!=null)
			{
			 payment_details.setCustomerSiteID(csi.getCustomerSite().getCustomerSitePK().getCustSiteId().toString());
			}
			criteria = new CustomerSearchCriteria();
			criteria.setCustomerId(customerId);
			try {
				LookUpCustomerIfc dao = DKartContext.getLookUpCustomer();
				customer = dao.lookUpCust(criteria);
				if (customer != null) {
					customerName = customer[0].getCustomerHeader().getCtNm();
					payment_details.setCustomerName(customerName);
					noOfInvoices = customer[0].getCustomerSite().get(0).getCustomerSiteInvoiceList().size();
					pendingAmount = customer[0].getCustomerLimits().getPendDue();
					String custSeg = customer[0].getCustomerSegmentID();
					payment_details.setCustomerSegmentationId(custSeg);
				}
			} catch (Exception e) {
				LOGGER.error("Error occured during Record Payment",e);
				e.printStackTrace();
				return ERROR;
			}
			// Store payment data to session object
			saveSessionData();

			status = true;
			return SUCCESS;
		} else {
			return NONE;
		}

	}

	private void saveSessionData() {
		putInSession(SESSION.PAYMENT_SESSION_DATA, payment_details);
		putInSession(SESSION.AMOUNT_BEING_PAID, amtBeingPaid);
		putInSession(SESSION.PENDING_AMOUNT, pendingAmount);
	}

	public String addTender() throws IOException {
		status = false;
		//sessionMap = super.getSessionmap();
		String result = NONE;
		// If SessionMap has is PAYMENT_SESSION_DATA
		if (sessionContains(SESSION.PAYMENT_SESSION_DATA)) {
			loadSessionData();
			result = AddPaymentDetails();
		}

		return result;
	}

	private void loadSessionData() {
		payment_details = (PaymentDetails) getFromSession(SESSION.PAYMENT_SESSION_DATA);
		amtBeingPaid = (BigDecimal) getFromSession(SESSION.AMOUNT_BEING_PAID);
		pendingAmount = (BigDecimal) getFromSession(SESSION.PENDING_AMOUNT);

	}

	private String AddPaymentDetails() throws IOException {
		payment_details = (PaymentDetails) getFromSession(SESSION.PAYMENT_SESSION_DATA);
		employee = super.getEmployee();
		if (payment_details == null) {
			return ERROR;
		}

		if (tenderMode != null) {
			ArrayList<TenderDetails> tender_list = payment_details.getTenderDetails();

			switch (tenderMode.toUpperCase()) {
			case "CASH": {
				tender_line.setTenderMode(TenderMode.CASH);
				tender_list.add(tender_line);
				break;
			}
			case "VOUCHER": {
				tender_line.setTenderMode(TenderMode.VOUCH);
				tender_line.setTenderAmount(new BigDecimal(tender_line.getVoucherValue()));

				tender_list.add(tender_line);
				break;
			}
			case "CHEQUE": {
				tender_line.setTenderMode(TenderMode.CHCK);
				tender_line.setTenderAmount(new BigDecimal(tender_line.getChequeAmount()));
				if (depositSlip != null) {
					tender_line.setUploadImageBytes(FileUtils.readFileToByteArray(depositSlip));
					tender_line.setDepositSlipFileName(getFileExtension(depositSlipFileName));
				}
				tender_list.add(tender_line);
				break;
			}
			case "COUPON": {
				tender_line.setTenderMode(TenderMode.QPON);
				tender_line.setTenderAmount(new BigDecimal(tender_line.getCouponValue()));
				tender_list.add(tender_line);
				break;
			}
			}
			payment_details.calculateTotalAmount();
			putInSession(SESSION.PAYMENT_SESSION_DATA, payment_details);
		}
		return SUCCESS;
	}

	public String deleteTender() {
		status = false;
		//sessionMap = super.getSessionmap();
		loadSessionData();
		TenderDetails tender_line = payment_details.getTenderDetails().get(index);
		payment_details
				.setTotalAmountPaid(payment_details.getTotalAmountPaid().subtract(tender_line.getTenderAmount()));
		payment_details.getTenderDetails().remove(tender_line);
		status = true;
		return SUCCESS;
	}

	public String savePayment() {
		status = false;
		String result;
		//sessionMap = super.getSessionmap();
		payment_details = (PaymentDetails) getFromSession(SESSION.PAYMENT_SESSION_DATA);
		try {
			//OrderTransactionsIfc dao = DKartContext.getLookupOrder();
			SavePostPaymentTransactionIfc ppt=DKartContext.getPaymentLookup();
			payment_details = ppt.saveTransaction(payment_details);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.toString(), e);
			return ERROR;
		}
		status = true;
		if (payment_details.getPaymentTransactionId() != null
				&& !payment_details.getPaymentTransactionId().equalsIgnoreCase("ERROR")) {
			putInSession(SESSION.PAYMENT_SESSION_DATA, payment_details);
			return SUCCESS;

		} else {
			return ERROR;
		}

	}

	public String donePayment() {
		//sessionMap = super.getSessionmap();
		payment_details = (PaymentDetails) getFromSession(SESSION.PAYMENT_SESSION_DATA);
		removeSessionData();
		return SUCCESS;
	}

	// method to cancel the Payment
	public String cancelPayment() {
		//sessionMap = super.getSessionmap();
		removeSessionData();
		return SUCCESS;
	}

	private void removeSessionData() {
		removeFromSession(SESSION.PAYMENT_SESSION_DATA);
		removeFromSession(SESSION.AMOUNT_BEING_PAID);
		removeFromSession(SESSION.PENDING_AMOUNT);

	}

	// method to get the uploaded check slip Extension
	public String getFileExtension(String file) {
		String[] for_split_extension = file.split("[.]");
		return for_split_extension[1];
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/*public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public EmployeeIfc getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeIfc employee) {
		this.employee = employee;
	}*/

	public BigDecimal getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(BigDecimal pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getDepositSlipFileName() {
		return depositSlipFileName;
	}

	public void setDepositSlipFileName(String depositSlipFileName) {
		this.depositSlipFileName = depositSlipFileName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getNoOfInvoices() {
		return noOfInvoices;
	}

	public void setNoOfInvoices(int noOfInvoices) {
		this.noOfInvoices = noOfInvoices;
	}

	public TenderDetails getTender_line() {
		return tender_line;
	}

	public void setTender_line(TenderDetails tender_line) {
		this.tender_line = tender_line;
	}

	public PaymentDetails getPayment_details() {
		return payment_details;
	}

	public void setPayment_details(PaymentDetails payment_details) {
		this.payment_details = payment_details;
	}

	public String getTenderMode() {
		return tenderMode;
	}

	public void setTenderMode(String tenderMode) {
		this.tenderMode = tenderMode;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public CustomerSearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(CustomerSearchCriteria criteria) {
		this.criteria = criteria;
	}

	public CustomerIfc[] getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerIfc[] customer) {
		this.customer = customer;
	}

	public BigDecimal getAmtBeingPaid() {
		return amtBeingPaid;
	}

	public void setAmtBeingPaid(BigDecimal amtBeingPaid) {
		this.amtBeingPaid = amtBeingPaid;
	}

	public File getDepositSlip() {
		return depositSlip;
	}

	public void setDepositSlip(File depositSlip) {
		this.depositSlip = depositSlip;
	}

}
