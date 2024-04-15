package com.rispl.sds.paramter.parser;

public class XMLParameterReader {
	
	//DashBoard
	private String DashboardRange;
	//OrderSearch
	private String OrderSearchRange;
	private String OpenOrderSearchRange;
	private String DeliveredOrderSearchRange;
	private String CancelledOrderSearchRange;
	private String ReturnedOrderSearchRange;
	private String SaveOrderSearchRange;
	private String DataEntryRoleID;
	private String SalesAgentRoleID;
	private String DivisionHeadRoleID;
	//BookOrder
	private String EnableValidateInventory;
	private String EnableBookOrderManagerOverride;
	private String EnableRebateCustomerForExpiryItems;
	private String DiscountPercentageLimit;
	private String EnableDoubleDiscounts;
	private String EnableExceedingCustomerAvailableLimit;
	private String OrderExpiryDays;
	private String ScheduledDeliveryOrderBeforeNoOfDays;
	private String BookOrderEnableSendingMailToCustomer;
	private String BookOrderEnableSendingMailToDeptHead;
	private String BookOrderEnableSendingMailToSalesAgent;
	private String BookOrderEnableGroupingDiscountedLineItems;
	

	private String BookOrderEnableSendingMailToDataEntryOperator;
	private String InceaseAvailLimitPercenatageForSegmentA;
	private String InceaseAvailLimitPercenatageForSegmentB;
	private String InceaseAvailLimitPercenatageForSegmentC;
	
	//InvoiceSearch
	private String InvoiceSearchRange;
	private String OpenInvoiceRange;
	private String InvoiceEnableSendingMailToCustomer;
	private String InvoiceEnableSendingMailToDeptHead;
	private String InvoiceEnableSendingMailToSalesAgent;
	private String InvoiceEnableSendingMailToDataEntryOperator;
	private String EnableSDSOfflineInvoice;
	private String OfflineInvoiceGenerationTimeInMinutes;
	private String EnableInvoiceCancellation;
	private String EnableMarkAsDeliveredForOfflineInvoice;
	/*
	 *	Laxmikanth: added attribute to parameterize the RECORDPAYMENT button 
	 */
	private String EnableRecordPaymentButton; 
	
	//PaymentSearch
	private String PaymentSearchRange;
	
	//PaymentMethod
	private String EnablePaymentMethodCash;
	private String EnablePaymentmethodCheque;
	private String EnablePaymentmethodVoucher;
	private String EnablePaymentmethodCoupon;
	private String PaymentEnableSendingMailToCustomer;
	private String PaymentEnableSendingMailToDeptHead;
	private String PaymentEnableSendingMailToSalesAgent;
	private String PaymentEnableSendingMailToDataEntryOperator;
	
	//ClaimSearch
	private String ClaimSearchRange;
	private String ApproveClaimSearchRange;
	private String ClaimNeedToBeAcceptedRange;
	private String ClaimAutoAcceptedRange;
	private String RejectedClaimRange;
	
	//RegisterClaim
	private String EnableClaimWithOutInvoice;
	private String EnableRegisterClaimManagerOveride;
	private String RegisterClaimEnableSendingMailToCustomer;
	private String RegisterClaimEnableSendingMailToDeptHead;
	private String RegisterClaimEnableSendingMailToSalesAgent;
	private String RegisterClaimEnableSendingMailToDataEntryOperator;
	
	//ApproveClaim
	private String ApproveClaimEnableSendingMailToCustomer;
	private String ApproveClaimEnableSendingMailToDeptHead;
	private String ApproveClaimEnableSendingMailToSalesAgent;
	private String ApproveClaimEnableSendingMailToDataEntryOperator;
	
	//AcceptClaim
	private String EnableAutoAcceptClaim;
	private String AcceptClaimEnableSendingMailToCustomer;
	private String AcceptClaimEnableSendingMailToDeptHead;
	private String AcceptClaimEnableSendingMailToSalesAgent;
	private String AcceptClaimEnableSendingMailToDataEntryOperator;
	
	//CustomerSearch
	private String CustomerNoOfOpenOrderDisplay;
	private String CustomerNoOfReceiptDisplay;
	private String CustomerNoOfCreditNoteDisplay;
	private String CustomerNoOfOpenInvoiceDisplay;
	
	//LowInventoryNotification
	private String EnableLowInventoryNotification;
	private String LowInventoryNotificationThreshold;
	private String LowInventoryNotificationEmail;
	
	//NewInvoiceNotification
	private String EnableNewInvoiceNotification;
	private String NewInvoiceNotificationRange;
	
	public String getEnableInvoiceCancellation() {
		return EnableInvoiceCancellation;
	}
	public void setEnableInvoiceCancellation(String enableInvoiceCancellationForOfflineInvoice) {
		EnableInvoiceCancellation = enableInvoiceCancellationForOfflineInvoice;
	}
	public String getEnableMarkAsDeliveredForOfflineInvoice() {
		return EnableMarkAsDeliveredForOfflineInvoice;
	}
	public void setEnableMarkAsDeliveredForOfflineInvoice(String enableMarkAsDeliveredForOfflineInvoice) {
		EnableMarkAsDeliveredForOfflineInvoice = enableMarkAsDeliveredForOfflineInvoice;
	}
	public String getBookOrderEnableSendingMailToDataEntryOperator() {
		return BookOrderEnableSendingMailToDataEntryOperator;
	}
	public void setBookOrderEnableSendingMailToDataEntryOperator(String bookOrderEnableSendingMailToDataEntryOperator) {
		BookOrderEnableSendingMailToDataEntryOperator = bookOrderEnableSendingMailToDataEntryOperator;
	}
	public String getInceaseAvailLimitPercenatageForSegmentA() {
		return InceaseAvailLimitPercenatageForSegmentA;
	}
	public void setInceaseAvailLimitPercenatageForSegmentA(String inceaseAvailLimitPercenatageForSegmentA) {
		InceaseAvailLimitPercenatageForSegmentA = inceaseAvailLimitPercenatageForSegmentA;
	}
	public String getInceaseAvailLimitPercenatageForSegmentB() {
		return InceaseAvailLimitPercenatageForSegmentB;
	}
	public void setInceaseAvailLimitPercenatageForSegmentB(String inceaseAvailLimitPercenatageForSegmentB) {
		InceaseAvailLimitPercenatageForSegmentB = inceaseAvailLimitPercenatageForSegmentB;
	}
	public String getInceaseAvailLimitPercenatageForSegmentC() {
		return InceaseAvailLimitPercenatageForSegmentC;
	}
	public void setInceaseAvailLimitPercenatageForSegmentC(String inceaseAvailLimitPercenatageForSegmentC) {
		InceaseAvailLimitPercenatageForSegmentC = inceaseAvailLimitPercenatageForSegmentC;
	}
	public String getInvoiceEnableSendingMailToDataEntryOperator() {
		return InvoiceEnableSendingMailToDataEntryOperator;
	}
	public void setInvoiceEnableSendingMailToDataEntryOperator(String invoiceEnableSendingMailToDataEntryOperator) {
		InvoiceEnableSendingMailToDataEntryOperator = invoiceEnableSendingMailToDataEntryOperator;
	}
	public String getPaymentEnableSendingMailToDataEntryOperator() {
		return PaymentEnableSendingMailToDataEntryOperator;
	}
	public void setPaymentEnableSendingMailToDataEntryOperator(String paymentEnableSendingMailToDataEntryOperator) {
		PaymentEnableSendingMailToDataEntryOperator = paymentEnableSendingMailToDataEntryOperator;
	}
	public String getRegisterClaimEnableSendingMailToDataEntryOperator() {
		return RegisterClaimEnableSendingMailToDataEntryOperator;
	}
	public void setRegisterClaimEnableSendingMailToDataEntryOperator(
			String registerClaimEnableSendingMailToDataEntryOperator) {
		RegisterClaimEnableSendingMailToDataEntryOperator = registerClaimEnableSendingMailToDataEntryOperator;
	}
	public String getApproveClaimEnableSendingMailToDataEntryOperator() {
		return ApproveClaimEnableSendingMailToDataEntryOperator;
	}
	public void setApproveClaimEnableSendingMailToDataEntryOperator(
			String approveClaimEnableSendingMailToDataEntryOperator) {
		ApproveClaimEnableSendingMailToDataEntryOperator = approveClaimEnableSendingMailToDataEntryOperator;
	}
	public String getAcceptClaimEnableSendingMailToDataEntryOperator() {
		return AcceptClaimEnableSendingMailToDataEntryOperator;
	}
	public void setAcceptClaimEnableSendingMailToDataEntryOperator(String acceptClaimEnableSendingMailToDataEntryOperator) {
		AcceptClaimEnableSendingMailToDataEntryOperator = acceptClaimEnableSendingMailToDataEntryOperator;
	}
	
	public String getOrderExpiryDays() {
		return OrderExpiryDays;
	}
	public void setOrderExpiryDays(String orderExpiryDays) {
		OrderExpiryDays = orderExpiryDays;
	}
	public String getScheduledDeliveryOrderBeforeNoOfDays() {
		return ScheduledDeliveryOrderBeforeNoOfDays;
	}
	public void setScheduledDeliveryOrderBeforeNoOfDays(String scheduledDeliveryOrderBeforeNoOfDays) {
		ScheduledDeliveryOrderBeforeNoOfDays = scheduledDeliveryOrderBeforeNoOfDays;
	}
	public String getBookOrderEnableSendingMailToCustomer() {
		return BookOrderEnableSendingMailToCustomer;
	}
	public void setBookOrderEnableSendingMailToCustomer(String bookOrderEnableSendingMailToCustomer) {
		BookOrderEnableSendingMailToCustomer = bookOrderEnableSendingMailToCustomer;
	}
	public String getBookOrderEnableSendingMailToDeptHead() {
		return BookOrderEnableSendingMailToDeptHead;
	}
	public void setBookOrderEnableSendingMailToDeptHead(String bookOrderEnableSendingMailToDeptHead) {
		BookOrderEnableSendingMailToDeptHead = bookOrderEnableSendingMailToDeptHead;
	}
	public String getBookOrderEnableSendingMailToSalesAgent() {
		return BookOrderEnableSendingMailToSalesAgent;
	}
	public void setBookOrderEnableSendingMailToSalesAgent(String bookOrderEnableSendingMailToSalesAgent) {
		BookOrderEnableSendingMailToSalesAgent = bookOrderEnableSendingMailToSalesAgent;
	}
	public String getInvoiceEnableSendingMailToCustomer() {
		return InvoiceEnableSendingMailToCustomer;
	}
	public void setInvoiceEnableSendingMailToCustomer(String invoiceEnableSendingMailToCustomer) {
		InvoiceEnableSendingMailToCustomer = invoiceEnableSendingMailToCustomer;
	}
	public String getInvoiceEnableSendingMailToDeptHead() {
		return InvoiceEnableSendingMailToDeptHead;
	}
	public void setInvoiceEnableSendingMailToDeptHead(String invoiceEnableSendingMailToDeptHead) {
		InvoiceEnableSendingMailToDeptHead = invoiceEnableSendingMailToDeptHead;
	}
	public String getInvoiceEnableSendingMailToSalesAgent() {
		return InvoiceEnableSendingMailToSalesAgent;
	}
	public void setInvoiceEnableSendingMailToSalesAgent(String invoiceEnableSendingMailToSalesAgent) {
		InvoiceEnableSendingMailToSalesAgent = invoiceEnableSendingMailToSalesAgent;
	}
	public String getPaymentEnableSendingMailToCustomer() {
		return PaymentEnableSendingMailToCustomer;
	}
	public void setPaymentEnableSendingMailToCustomer(String paymentEnableSendingMailToCustomer) {
		PaymentEnableSendingMailToCustomer = paymentEnableSendingMailToCustomer;
	}
	public String getPaymentEnableSendingMailToDeptHead() {
		return PaymentEnableSendingMailToDeptHead;
	}
	public void setPaymentEnableSendingMailToDeptHead(String paymentEnableSendingMailToDeptHead) {
		PaymentEnableSendingMailToDeptHead = paymentEnableSendingMailToDeptHead;
	}
	public String getPaymentEnableSendingMailToSalesAgent() {
		return PaymentEnableSendingMailToSalesAgent;
	}
	public void setPaymentEnableSendingMailToSalesAgent(String paymentEnableSendingMailToSalesAgent) {
		PaymentEnableSendingMailToSalesAgent = paymentEnableSendingMailToSalesAgent;
	}
	public String getRegisterClaimEnableSendingMailToCustomer() {
		return RegisterClaimEnableSendingMailToCustomer;
	}
	public void setRegisterClaimEnableSendingMailToCustomer(String registerClaimEnableSendingMailToCustomer) {
		RegisterClaimEnableSendingMailToCustomer = registerClaimEnableSendingMailToCustomer;
	}
	public String getRegisterClaimEnableSendingMailToDeptHead() {
		return RegisterClaimEnableSendingMailToDeptHead;
	}
	public void setRegisterClaimEnableSendingMailToDeptHead(String registerClaimEnableSendingMailToDeptHead) {
		RegisterClaimEnableSendingMailToDeptHead = registerClaimEnableSendingMailToDeptHead;
	}
	public String getRegisterClaimEnableSendingMailToSalesAgent() {
		return RegisterClaimEnableSendingMailToSalesAgent;
	}
	public void setRegisterClaimEnableSendingMailToSalesAgent(String registerClaimEnableSendingMailToSalesAgent) {
		RegisterClaimEnableSendingMailToSalesAgent = registerClaimEnableSendingMailToSalesAgent;
	}
	public String getApproveClaimEnableSendingMailToCustomer() {
		return ApproveClaimEnableSendingMailToCustomer;
	}
	public void setApproveClaimEnableSendingMailToCustomer(String approveClaimEnableSendingMailToCustomer) {
		ApproveClaimEnableSendingMailToCustomer = approveClaimEnableSendingMailToCustomer;
	}
	public String getApproveClaimEnableSendingMailToDeptHead() {
		return ApproveClaimEnableSendingMailToDeptHead;
	}
	public void setApproveClaimEnableSendingMailToDeptHead(String approveClaimEnableSendingMailToDeptHead) {
		ApproveClaimEnableSendingMailToDeptHead = approveClaimEnableSendingMailToDeptHead;
	}
	public String getApproveClaimEnableSendingMailToSalesAgent() {
		return ApproveClaimEnableSendingMailToSalesAgent;
	}
	public void setApproveClaimEnableSendingMailToSalesAgent(String approveClaimEnableSendingMailToSalesAgent) {
		ApproveClaimEnableSendingMailToSalesAgent = approveClaimEnableSendingMailToSalesAgent;
	}
	public String getAcceptClaimEnableSendingMailToCustomer() {
		return AcceptClaimEnableSendingMailToCustomer;
	}
	public void setAcceptClaimEnableSendingMailToCustomer(String acceptClaimEnableSendingMailToCustomer) {
		AcceptClaimEnableSendingMailToCustomer = acceptClaimEnableSendingMailToCustomer;
	}
	public String getAcceptClaimEnableSendingMailToDeptHead() {
		return AcceptClaimEnableSendingMailToDeptHead;
	}
	public void setAcceptClaimEnableSendingMailToDeptHead(String acceptClaimEnableSendingMailToDeptHead) {
		AcceptClaimEnableSendingMailToDeptHead = acceptClaimEnableSendingMailToDeptHead;
	}
	public String getAcceptClaimEnableSendingMailToSalesAgent() {
		return AcceptClaimEnableSendingMailToSalesAgent;
	}
	public void setAcceptClaimEnableSendingMailToSalesAgent(String acceptClaimEnableSendingMailToSalesAgent) {
		AcceptClaimEnableSendingMailToSalesAgent = acceptClaimEnableSendingMailToSalesAgent;
	}
	public String getOrderSearchRange() {
		return OrderSearchRange;
	}
	public String getDashboardRange() {
		return DashboardRange;
	}
	public void setDashboardRange(String dashboardRange) {
		DashboardRange = dashboardRange;
	}
	public void setOrderSearchRange(String orderSearchRange) {
		OrderSearchRange = orderSearchRange;
	}
	public String getOpenOrderSearchRange() {
		return OpenOrderSearchRange;
	}
	public void setOpenOrderSearchRange(String openOrderSearchRange) {
		OpenOrderSearchRange = openOrderSearchRange;
	}
	public String getDeliveredOrderSearchRange() {
		return DeliveredOrderSearchRange;
	}
	public void setDeliveredOrderSearchRange(String deliveredOrderSearchRange) {
		DeliveredOrderSearchRange = deliveredOrderSearchRange;
	}
	public String getCancelledOrderSearchRange() {
		return CancelledOrderSearchRange;
	}
	public void setCancelledOrderSearchRange(String cancelledOrderSearchRange) {
		CancelledOrderSearchRange = cancelledOrderSearchRange;
	}
	public String getReturnedOrderSearchRange() {
		return ReturnedOrderSearchRange;
	}
	public void setReturnedOrderSearchRange(String returnedOrderSearchRange) {
		ReturnedOrderSearchRange = returnedOrderSearchRange;
	}
	public String getSaveOrderSearchRange() {
		return SaveOrderSearchRange;
	}
	public void setSaveOrderSearchRange(String saveOrderSearchRange) {
		SaveOrderSearchRange = saveOrderSearchRange;
	}
	public String getEnableValidateInventory() {
		return EnableValidateInventory;
	}
	public void setEnableValidateInventory(String enableValidateInventory) {
		EnableValidateInventory = enableValidateInventory;
	}
	public String getEnableBookOrderManagerOverride() {
		return EnableBookOrderManagerOverride;
	}
	public void setEnableBookOrderManagerOverride(String enableBookOrderManagerOverride) {
		EnableBookOrderManagerOverride = enableBookOrderManagerOverride;
	}
	public String getEnableRebateCustomerForExpiryItems() {
		return EnableRebateCustomerForExpiryItems;
	}
	public void setEnableRebateCustomerForExpiryItems(String enableRebateCustomerForExpiryItems) {
		EnableRebateCustomerForExpiryItems = enableRebateCustomerForExpiryItems;
	}
	public String getDiscountPercentageLimit() {
		return DiscountPercentageLimit;
	}
	public void setDiscountPercentageLimit(String discountPercentageLimit) {
		DiscountPercentageLimit = discountPercentageLimit;
	}
	public String getEnableDoubleDiscounts() {
		return EnableDoubleDiscounts;
	}
	public void setEnableDoubleDiscounts(String enableDoubleDiscounts) {
		EnableDoubleDiscounts = enableDoubleDiscounts;
	}
	public String getEnableExceedingCustomerAvailableLimit() {
		return EnableExceedingCustomerAvailableLimit;
	}
	public void setEnableExceedingCustomerAvailableLimit(String enableExceedingCustomerAvailableLimit) {
		EnableExceedingCustomerAvailableLimit = enableExceedingCustomerAvailableLimit;
	}
	public String getInvoiceSearchRange() {
		return InvoiceSearchRange;
	}
	public void setInvoiceSearchRange(String invoiceSearchRange) {
		InvoiceSearchRange = invoiceSearchRange;
	}
	public String getOpenInvoiceRange() {
		return OpenInvoiceRange;
	}
	public void setOpenInvoiceRange(String openInvoiceRange) {
		OpenInvoiceRange = openInvoiceRange;
	}
	public String getPaymentSearchRange() {
		return PaymentSearchRange;
	}
	public void setPaymentSearchRange(String paymentSearchRange) {
		PaymentSearchRange = paymentSearchRange;
	}
	public String getEnablePaymentMethodCash() {
		return EnablePaymentMethodCash;
	}
	public void setEnablePaymentMethodCash(String enablePaymentMethodCash) {
		EnablePaymentMethodCash = enablePaymentMethodCash;
	}
	public String getEnablePaymentmethodCheque() {
		return EnablePaymentmethodCheque;
	}
	public void setEnablePaymentmethodCheque(String enablePaymentmethodCheque) {
		EnablePaymentmethodCheque = enablePaymentmethodCheque;
	}
	public String getEnablePaymentmethodVoucher() {
		return EnablePaymentmethodVoucher;
	}
	public void setEnablePaymentmethodVoucher(String enablePaymentmethodVoucher) {
		EnablePaymentmethodVoucher = enablePaymentmethodVoucher;
	}
	public String getEnablePaymentmethodCoupon() {
		return EnablePaymentmethodCoupon;
	}
	public void setEnablePaymentmethodCoupon(String enablePaymentmethodCoupon) {
		EnablePaymentmethodCoupon = enablePaymentmethodCoupon;
	}
	public String getClaimSearchRange() {
		return ClaimSearchRange;
	}
	public void setClaimSearchRange(String claimSearchRange) {
		ClaimSearchRange = claimSearchRange;
	}
	public String getApproveClaimSearchRange() {
		return ApproveClaimSearchRange;
	}
	public void setApproveClaimSearchRange(String approveClaimSearchRange) {
		ApproveClaimSearchRange = approveClaimSearchRange;
	}
	public String getClaimNeedToBeAcceptedRange() {
		return ClaimNeedToBeAcceptedRange;
	}
	public void setClaimNeedToBeAcceptedRange(String claimNeedToBeAcceptedRange) {
		ClaimNeedToBeAcceptedRange = claimNeedToBeAcceptedRange;
	}
	public String getClaimAutoAcceptedRange() {
		return ClaimAutoAcceptedRange;
	}
	public void setClaimAutoAcceptedRange(String claimAutoAcceptedRange) {
		ClaimAutoAcceptedRange = claimAutoAcceptedRange;
	}
	public String getRejectedClaimRange() {
		return RejectedClaimRange;
	}
	public void setRejectedClaimRange(String rejectedClaimRange) {
		RejectedClaimRange = rejectedClaimRange;
	}
	public String getEnableAutoAcceptClaim() {
		return EnableAutoAcceptClaim;
	}
	public void setEnableAutoAcceptClaim(String enableAutoAcceptClaim) {
		EnableAutoAcceptClaim = enableAutoAcceptClaim;
	}
	public String getEnableClaimWithOutInvoice() {
		return EnableClaimWithOutInvoice;
	}
	public void setEnableClaimWithOutInvoice(String enableClaimWithOutInvoice) {
		EnableClaimWithOutInvoice = enableClaimWithOutInvoice;
	}
	public String getEnableRegisterClaimManagerOveride() {
		return EnableRegisterClaimManagerOveride;
	}
	public void setEnableRegisterClaimManagerOveride(String enableRegisterClaimManagerOveride) {
		EnableRegisterClaimManagerOveride = enableRegisterClaimManagerOveride;
	}
	public String getCustomerNoOfOpenOrderDisplay() {
		return CustomerNoOfOpenOrderDisplay;
	}
	public void setCustomerNoOfOpenOrderDisplay(String customerNoOfOpenOrderDisplay) {
		CustomerNoOfOpenOrderDisplay = customerNoOfOpenOrderDisplay;
	}
	public String getCustomerNoOfReceiptDisplay() {
		return CustomerNoOfReceiptDisplay;
	}
	public void setCustomerNoOfReceiptDisplay(String customerNoOfReceiptDisplay) {
		CustomerNoOfReceiptDisplay = customerNoOfReceiptDisplay;
	}
	public String getCustomerNoOfCreditNoteDisplay() {
		return CustomerNoOfCreditNoteDisplay;
	}
	public void setCustomerNoOfCreditNoteDisplay(String customerNoOfCreditNoteDisplay) {
		CustomerNoOfCreditNoteDisplay = customerNoOfCreditNoteDisplay;
	}
	public String getCustomerNoOfOpenInvoiceDisplay() {
		return CustomerNoOfOpenInvoiceDisplay;
	}
	public void setCustomerNoOfOpenInvoiceDisplay(String customerNoOfOpenInvoiceDisplay) {
		CustomerNoOfOpenInvoiceDisplay = customerNoOfOpenInvoiceDisplay;
	}
	public String getBookOrderEnableGroupingDiscountedLineItems() {
		return BookOrderEnableGroupingDiscountedLineItems;
	}
	public void setBookOrderEnableGroupingDiscountedLineItems(String bookOrderEnableGroupingDiscountedLineItems) {
		BookOrderEnableGroupingDiscountedLineItems = bookOrderEnableGroupingDiscountedLineItems;
	}
	public String getDataEntryRoleID() {
		return DataEntryRoleID;
	}
	public void setDataEntryRoleID(String dataEntryRoleID) {
		DataEntryRoleID = dataEntryRoleID;
	}
	public String getSalesAgentRoleID() {
		return SalesAgentRoleID;
	}
	public void setSalesAgentRoleID(String salesAgentRoleID) {
		SalesAgentRoleID = salesAgentRoleID;
	}
	public String getDivisionHeadRoleID() {
		return DivisionHeadRoleID;
	}
	public void setDivisionHeadRoleID(String divisionHeadRoleID) {
		DivisionHeadRoleID = divisionHeadRoleID;
	}
	
	public String getEnableSDSOfflineInvoice() {
		return EnableSDSOfflineInvoice;
	}
	public void setEnableSDSOfflineInvoice(String enableSDSOfflineInvoice) {
		EnableSDSOfflineInvoice = enableSDSOfflineInvoice;
	}
	public String getOfflineInvoiceGenerationTimeInMinutes() {
		return OfflineInvoiceGenerationTimeInMinutes;
	}
	public void setOfflineInvoiceGenerationTimeInMinutes(String offlineInvoiceGenerationTimeInMinutes) {
		OfflineInvoiceGenerationTimeInMinutes = offlineInvoiceGenerationTimeInMinutes;
	}
	public String getEnableLowInventoryNotification() {
		return EnableLowInventoryNotification;
	}
	public void setEnableLowInventoryNotification(String enableLowInventoryNotification) {
		EnableLowInventoryNotification = enableLowInventoryNotification;
	}
	public String getLowInventoryNotificationThreshold() {
		return LowInventoryNotificationThreshold;
	}
	public void setLowInventoryNotificationThreshold(String lowInventoryNotificationThreshold) {
		LowInventoryNotificationThreshold = lowInventoryNotificationThreshold;
	}
	public String getLowInventoryNotificationEmail() {
		return LowInventoryNotificationEmail;
	}
	public void setLowInventoryNotificationEmail(String lowInventoryNotificationEmail) {
		LowInventoryNotificationEmail = lowInventoryNotificationEmail;
	}
	public String getEnableRecordPaymentButton() {
		return EnableRecordPaymentButton;
	}
	public void setEnableRecordPaymentButton(String enableRecordPaymentButton) {
		EnableRecordPaymentButton = enableRecordPaymentButton;
	}
	public String getEnableNewInvoiceNotification() {
		return EnableNewInvoiceNotification;
	}
	public void setEnableNewInvoiceNotification(String enableNewInvoiceNotification) {
		EnableNewInvoiceNotification = enableNewInvoiceNotification;
	}
	public String getNewInvoiceNotificationRange() {
		return NewInvoiceNotificationRange;
	}
	public void setNewInvoiceNotificationRange(String newInvoiceNotificationRange) {
		NewInvoiceNotificationRange = newInvoiceNotificationRange;
	}
	
}
