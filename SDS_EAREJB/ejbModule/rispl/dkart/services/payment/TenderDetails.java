package rispl.dkart.services.payment;

import java.io.Serializable;
import java.math.BigDecimal;

public class TenderDetails implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2880241737711764902L;

	public enum TenderMode
	{
		CASH,CHCK,VOUCH,QPON
	} 

	protected TenderMode tenderMode;
	protected BigDecimal tenderAmount;				 //Total tender Mount
	
	protected String customerBankName;
	protected String customerBankLocation;
	protected String customerAccountNo;
	protected String chequeNumber;
	protected byte uploadImageBytes[];
	protected String depositSlipFileName;
	protected String depositBankName;
	protected String depositBankLocation;
	protected String chequeDepositDate;
	protected String chequeAmount;					//Total Check Value
	
	protected String voucherID;
	protected String voucherValue;					//Voucher Value
	protected String voucherExpiryDate;
	protected String couponID;
	
	protected String couponValue;				//Total Coupon value
	protected String couponDate;
	
	public TenderDetails(){
		
	}
	
	public TenderMode getTenderMode() {
		return tenderMode;
	}
	public void setTenderMode(TenderMode tenderMode) {
		this.tenderMode = tenderMode;
	}
	public BigDecimal getTenderAmount() {
		return tenderAmount;
	}
	public void setTenderAmount(BigDecimal tenderAmount) {
		this.tenderAmount = tenderAmount;
	}
	public String getCustomerBankName() {
		return customerBankName;
	}
	public void setCustomerBankName(String customerBankName) {
		this.customerBankName = customerBankName;
	}
	public String getCustomerBankLocation() {
		return customerBankLocation;
	}
	public void setCustomerBankLocation(String customerBankLocation) {
		this.customerBankLocation = customerBankLocation;
	}
	public String getCustomerAccountNo() {
		return customerAccountNo;
	}
	public void setCustomerAccountNo(String customerAccountNo) {
		this.customerAccountNo = mask(customerAccountNo,"*");
	}
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public byte[] getUploadImageBytes() {
		return uploadImageBytes;
	}
	public void setUploadImageBytes(byte[] uploadImageBytes) {
		this.uploadImageBytes = uploadImageBytes;
	}
	public String getDepositSlipFileName() {
		return depositSlipFileName;
	}
	public void setDepositSlipFileName(String depositSlipFileName) {
		this.depositSlipFileName = depositSlipFileName;
	}
	public String getDepositBankLocation() {
		return depositBankLocation;
	}
	public void setDepositBankLocation(String depositBankLocation) {
		this.depositBankLocation = depositBankLocation;
	}
	public String getChequeDepositDate() {
		return chequeDepositDate;
	}
	public void setChequeDepositDate(String chequeDepositDate) {
		this.chequeDepositDate = chequeDepositDate;
	}
	public String getChequeAmount() {
		return chequeAmount;
	}
	public void setChequeAmount(String chequeAmount) {
		this.chequeAmount = chequeAmount;
	}
	public String getVoucherID() {
		return voucherID;
	}
	public void setVoucherID(String voucherID) {
		this.voucherID = voucherID;
	}
	public String getVoucherValue() {
		return voucherValue;
	}
	public void setVoucherValue(String voucherValue) {
		this.voucherValue = voucherValue;
	}
	public String getVoucherExpiryDate() {
		return voucherExpiryDate;
	}
	public void setVoucherExpiryDate(String voucherExpiryDate) {
		this.voucherExpiryDate = voucherExpiryDate;
	}
	public String getCouponID() {
		return couponID;
	}
	public void setCouponID(String couponID) {
		this.couponID = couponID;
	}
	public String getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}
	public String getCouponDate() {
		return couponDate;
	}
	public void setCouponDate(String couponDate) {
		this.couponDate = couponDate;
	}
	 
	
	
	//mask(clear.getBytes(), maskChar)
	
	  public String getDepositBankName() {
		return depositBankName;
	}

	public void setDepositBankName(String depositBankName) {
		this.depositBankName = depositBankName;
	}

	

	
	public String mask(String accountNumber, String maskChar)
	  {
		  return accountNumber.replaceAll("\\w(?=\\w{4})", maskChar);
	  }
	  
	 
	
}
