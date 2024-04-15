package rispl.dkart.services.transaction.save;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.retailsols.sds.transaction.ejb.TransactionService;

import rispl.dk.customer.Customer;
import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.ejb.LookUpCustomer;
import rispl.dkart.services.ejb.transaction.OrderTransactions;
import rispl.dkart.services.entities.tenders.TranCheckTender;
import rispl.dkart.services.entities.tenders.TranCheckTenderPK;
import rispl.dkart.services.entities.tenders.TranLineItemTender;
import rispl.dkart.services.entities.tenders.TranLineItemTenderPK;
import rispl.dkart.services.entities.tenders.TranVoucherTender;
import rispl.dkart.services.entities.tenders.TranVoucherTenderPK;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranHeaderPK;
import rispl.dkart.services.entities.transaction.OrderTranSum;
import rispl.dkart.services.entities.transaction.OrderTranSumPK;
import rispl.dkart.services.payment.PaymentDetails;
import rispl.dkart.services.payment.TenderDetails;
import rispl.services.transaction.find.OrderTransactionException;
import utility.ConfigUtils;

@Stateless(mappedName = "lookUpPaymentTransactions")
public class SavePostPaymentTransaction  implements SavePostPaymentTransactionIfc {

	/**
	 * 14/11/2016: Krishna : To Save post payment transaction
	 */
	
	@Inject
	private OrderTransactions orderTran;
	
	@Inject	@SdsEntityManagerFactory
	EntityManagerFactory emf;
	
	@Inject
	protected LookUpCustomer lookupCustomer;
	
	@Inject
	TransactionService transactionService;

	private static final Logger LOGGER = LogManager.getLogger(SavePostPaymentTransaction.class);
	
	@Override
	public OrderTranHeader initializePaymentTransaction(PaymentDetails pd)
	{
		OrderTranHeader resultTranHeader=null;
		try
		{
			LOGGER.info("initialize PaymentTransaction......");
			OrderTranHeader tranHeader=new OrderTranHeader();
			
			OrderTranHeaderPK headerPk = new OrderTranHeaderPK();
			
			String storeID =lookupCustomer.getCustomerStoreID(pd.getCustomerId());
			if(storeID.equalsIgnoreCase(""))
			{
			  storeID = ConfigUtils.getInstance().getSDSStoreID();
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String tranDate = format.format(new Date());
			long tranSequenceId = transactionService.getTranSeq(storeID);
			String workStationId=ConfigUtils.getInstance().getSDSWorkstationID();
			
			headerPk.setRtStrId(storeID);
			headerPk.setDcDyOrd(tranDate);
			headerPk.setOrdWs(workStationId);
			headerPk.setTrnSeq(tranSequenceId);
			tranHeader.setId(headerPk);
			tranHeader.setTsOrdBgn(new Timestamp(new Date().getTime()));
			
			
			OrderTranSumPK sumpk = new OrderTranSumPK();
			sumpk.setRtStrId(storeID);
			sumpk.setDcDyOrd(tranDate);
			sumpk.setOrdWs(workStationId);
			sumpk.setTrnSeq(tranSequenceId);
			
			OrderTranSum sum = new OrderTranSum();
			sum.setId(sumpk);
			sum.setDkartNetTot(pd.getTotalAmountPaid());
			sum.setDkartTndTot(pd.getTotalAmountPaid());
			sum.setFlSndCtPhy("0");
			sum.setDkartTaxIncTot(pd.getTotalAmountPaid());
			sum.setOrdIdCt(pd.getCustomerId());
			sum.setOrdTranHeader(tranHeader);
			sum.setIdOrdArNmb(pd.getCustomerInvoiceId());
			List<OrderTranSum> ordTranSums = new ArrayList<OrderTranSum>();
			ordTranSums.add(sum);
			
			
			
			Customer customer=new Customer();
			customer.setCustomerLimits(orderTran.getCustomerlimits(pd.getCustomerId()));
			tranHeader.setCustomer(customer);
			
			tranHeader.setOrdTranSum(sum);
			tranHeader.setOrdTy(orderTran.PAYMENT_TRANSACTION_TYPE);
			tranHeader.setIdOpr(pd.getOpratorId());
			tranHeader.setEmId(pd.getOpratorId());
			tranHeader.setIdTrlogBtch("-3");
			tranHeader.setIdTlogBtch(new BigDecimal("-3"));
		    tranHeader.setScPstPrcs(new BigDecimal("01"));
		    tranHeader.setTransactionStatus(new BigDecimal("1"));
		    tranHeader.setScOrd(new BigDecimal("1"));
			tranHeader.setReasonCodes(orderTran.getAllReasonCodes());
			tranHeader.setIdBtchInvResv(pd.getCustomerSiteID());
			LOGGER.info("Feteched Reason Code" + tranHeader.getReasonCodes().size());
			EntityManager em=getEntityManager();
			em.getTransaction().begin();
			em.persist(tranHeader);
			em.getTransaction().commit();
			resultTranHeader=tranHeader;
			
		}catch(Exception e)
		{
			LOGGER.error(e);
			System.out.println(e);
		}
		
		return resultTranHeader;
	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public PaymentDetails saveTransaction(PaymentDetails pd)
	{
		PaymentDetails result = null;
		OrderTranHeader tranHeader = initializePaymentTransaction(pd);

		pd.setStoreId(tranHeader.getId().getRtStrId());
		pd.setWorkStationID(tranHeader.getId().getOrdWs());
		pd.setTransactionSequenceID(tranHeader.getId().getTrnSeq());
		pd.setBusinessDate(tranHeader.getId().getDcDyOrd());

		TranLineItemTender[] tenders = getTenderLines(pd);
		orderTran.updateCustomerCreditLimit(tranHeader.getCustomer().getCustomerLimits(), pd.getTotalAmountPaid());
		try {
			String transactionID=completePaymentTransaction(tranHeader, tenders);
			pd.setPaymentTransactionId(transactionID);
			result=pd;
		} catch (Exception e) {
			LOGGER.error("Error Ocured Ocurren while Saving Payments \n" + e);
			result = null;
		}
		return result;
	}
	
	private TranLineItemTender[] getTenderLines(PaymentDetails pd) {
		ArrayList<TranLineItemTender> tenders = new ArrayList<TranLineItemTender>();

		int lineIndex = 0;
		if (pd.getTenderDetails() != null)
			for (TenderDetails td : pd.getTenderDetails()) {
				switch (td.getTenderMode()) {
				case CASH: {
					tenders.add(AddTenderLine(pd, td, lineIndex));
					lineIndex++;
					break;
				}

				case CHCK: {
					TranLineItemTender tlitd = AddTenderLine(pd, td, lineIndex);
					tlitd.setDkTranChkTnds(getCheckTenderLine(pd, tlitd, td, lineIndex));
					tenders.add(tlitd);
					lineIndex++;
					break;
				}
				case VOUCH: {
					TranLineItemTender tlitd = AddTenderLine(pd, td, lineIndex);
					tlitd.setDkTranLtmVchrTnds(getVoucherTenderLine(pd, td, tlitd, lineIndex));
					tenders.add(tlitd);
					lineIndex++;
					break;
				}

				case QPON: {
					TranLineItemTender tlitd = AddTenderLine(pd, td, lineIndex);
					tenders.add(tlitd);
					lineIndex++;
					break;
				}

				}

			}
		return (TranLineItemTender[]) tenders.toArray(new TranLineItemTender[tenders.size()]);
	}
	
	
	private List<TranCheckTender> getCheckTenderLine(PaymentDetails pd, TranLineItemTender tlitd, TenderDetails td,
			int lineIndex) {
		List<TranCheckTender> checkList = new ArrayList<>();

		TranCheckTender tct = new TranCheckTender();

		TranCheckTenderPK pk = new TranCheckTenderPK();
		pk.setDcDyOrd(pd.getBusinessDate());
		pk.setOrdWs(pd.getWorkStationID());
		pk.setRtStrId(pd.getWorkStationID());
		pk.setTrnSeq(pd.getTransactionSequenceID());
		pk.setTrnLnItmSeq(lineIndex);
		tct.setId(pk);

		tct.setDkBnkId(td.getCustomerBankName());
		tct.setTrk1Id(td.getCustomerBankLocation());
		tct.setDkAiChk(td.getChequeNumber());
		tct.setAcntMskChk(td.getCustomerAccountNo());
		
		
		tct.setTrk2Id(td.getDepositBankLocation());
		tct.setLuMthAzn(td.getDepositBankName());
		tct.setDkBirthDate(td.getChequeDepositDate());
		
		
		tct.setCheckSlipImage(td.getUploadImageBytes());
		tct.setFileType(td.getDepositSlipFileName());
		tct.setDkTranLtmTnd(tlitd);
		

		checkList.add(tct);

		return checkList;
	}

	private List<TranVoucherTender> getVoucherTenderLine(PaymentDetails pd, TenderDetails td, TranLineItemTender tlitd,
			int lineIndex) {
		List<TranVoucherTender> voucherList = new ArrayList<>();

		TranVoucherTender tvt = new TranVoucherTender();

		TranVoucherTenderPK pk = new TranVoucherTenderPK();
		pk.setDcDyOrd(pd.getBusinessDate());
		pk.setOrdWs(pd.getWorkStationID());
		pk.setRtStrId(pd.getStoreId());
		pk.setTrnSeq(pd.getTransactionSequenceID());
		pk.setTrnLnItmSeq(lineIndex);
		tvt.setId(pk);
		tvt.setIdVchr(td.getVoucherID());
		tvt.setMoCrVchr(new BigDecimal(td.getVoucherValue()));
		tvt.setScVchrTnd("REDEEM");
		tvt.setDkTranLtmTnd(tlitd);
		voucherList.add(tvt);

		return voucherList;
	}

	private TranLineItemTender AddTenderLine(PaymentDetails pd, TenderDetails td, int lineIndex) {
		TranLineItemTender tlid = new TranLineItemTender();

		TranLineItemTenderPK pk = new TranLineItemTenderPK();
		pk.setDcDyOrd(pd.getBusinessDate());
		pk.setOrdWs(pd.getWorkStationID());
		pk.setRtStrId(pd.getStoreId());
		pk.setTrnSeq(pd.getTransactionSequenceID());
		pk.setTrnLnItmSeq(lineIndex);
		tlid.setId(pk);
		tlid.setTyTnd(td.getTenderMode().name());
		tlid.setMoItmLnTnd(td.getTenderAmount());

		return tlid;
	}

		
	public String completePaymentTransaction(OrderTranHeader tranHeader, TranLineItemTender[] tenders) throws OrderTransactionException
	{
		String result="ERROR";
		//To handel this case
		orderTran.saveTenderLineItems(tenders);
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		tranHeader.setTransactionStatus(new BigDecimal("2"));
		tranHeader.setScOrd(new BigDecimal("2"));
		tranHeader.setScPstPrcs(new BigDecimal("01"));
		tranHeader.setTsCrtRcrd(new Timestamp(new Date().getTime()));
		tranHeader.setTsOrdEnd(new Timestamp(new Date().getTime()));
		tranHeader.setIdTrlogBtch("-1");
		tranHeader.setIdTlogBtch(new BigDecimal("-1"));
		em.merge(tranHeader);
		em.merge(tranHeader.getCustomer().getCustomerLimits());
		em.getTransaction().commit();
		result=orderTran.getOrderId(tranHeader);		
		return result;
	}



}
