package com.rispl.sds.parameter.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.ejb.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.w3c.dom.Document;

import com.rispl.sds.parameter.generated.SDSParameterImport;
import com.rispl.sds.paramter.parser.ParameterHandler;
import com.rispl.sds.paramter.parser.SimpleElementParameterGroup;
import com.rispl.sds.paramter.parser.UpdateXMLParamter;
import com.rispl.sds.paramter.parser.XMLParameterReader;

//@Stateless(mappedName = "sdsparameterService")
@Singleton(mappedName = "sdsparameterService")
public class ParameterConfigurationService implements ParameterConfigurationServiceIfc {

	private static final Logger LOGGER = LogManager.getLogger(ParameterConfigurationService.class);
	private static final String PARAMETERS_XML = "SDSParameter.xml";
	private static final String PARAMETERS_XSD = "SDSParameter.xsd";
	
	@SuppressWarnings("unused")
	@Override
	public void updateSDSParameterXMl(ArrayList<UpdateXMLParamter> updateXMLFileList) throws ParameterException {
		ParameterHandler parameterHandler = new ParameterHandler(PARAMETERS_XML, PARAMETERS_XSD);
		if (parameterHandler.validateXMLWithXSD()) {
			if (!updateXMLFileList.isEmpty()) {
				try {
					for (UpdateXMLParamter xmlParamter : updateXMLFileList) {
						Document document = parameterHandler.getDocumentDetails();
						String simpleElement = xmlParamter.getSimpleElement();
						String newValue = xmlParamter.getNewValue();
						newValue = newValue.replaceFirst ("^0*", "");
						if (newValue.isEmpty()) {newValue = "0";}
						String parameterGrpElement = xmlParamter.getParameterGrpElement();
						String currentValue = parameterHandler.replaceValue(document,parameterGrpElement, simpleElement, newValue.trim());
						Transformer t = TransformerFactory.newInstance().newTransformer();
						Result result = new StreamResult(new File(parameterHandler.getXmlFile()));
						Source source = new DOMSource(document);
						t.transform(source, result);
						try {
							parameterHandler.validateXMLWithXSD();
						} catch (Exception e) {
							LOGGER.warn("Unable To Update Parameter Configuration For The Requested Value :"
									+ simpleElement + " Current Value :" + currentValue + "New Value :" + newValue + e);
							parameterHandler.replaceValue(document, parameterGrpElement,simpleElement, currentValue);
							Transformer t1 = TransformerFactory.newInstance().newTransformer();
							Result result1 = new StreamResult(new File(parameterHandler.getXmlFile()));
							Source source1 = new DOMSource(document);
							t.transform(source1, result1);
							LOGGER.warn("Parameter Configuration Reverted Back To Original Value");
						}
					}
					LOGGER.info("Paramter Configuration Value Updated Successfully");

				} catch (Exception e) {
					LOGGER.error("Unable To Update The Parameter Configuration For The Requested Value :" + e);
					throw new ParameterException(e);
				}

			} else {
				LOGGER.warn("There Are No Element Set To Update The Parameter Configuration");
			}
		}

	}

	@Override
	public ArrayList<String> getParametergroupDetailsList() {
		ArrayList<String> parameterGroupist = new ArrayList<String>();
		parameterGroupist.add("DashBoard");
		parameterGroupist.add("OrderSearch");
		parameterGroupist.add("BookOrder");
		parameterGroupist.add("InvoiceSearch");
		parameterGroupist.add("PaymentSearch");
		parameterGroupist.add("PaymentMethod");
		parameterGroupist.add("ClaimSearch");
		parameterGroupist.add("RegisterClaim");
		parameterGroupist.add("ApproveClaim");
		parameterGroupist.add("AcceptClaim");
		parameterGroupist.add("CustomerSearch");
		parameterGroupist.add("InventoryNotification");
		parameterGroupist.add("NewInvoiceNotification");
		return parameterGroupist;

	}

	@Override
	public ArrayList<SimpleElementParameterGroup> getSimpleElementParameterGroup(String parameterGroup)
			throws ParameterException {
		ArrayList<SimpleElementParameterGroup> simpleElementParamGroupList = new ArrayList<SimpleElementParameterGroup>();
		ParameterHandler parameterHandler = new ParameterHandler(PARAMETERS_XML, PARAMETERS_XSD);
		if (parameterHandler.validateXMLWithXSD()) {
			if (parameterGroup != null) {
				try {
					Document document = parameterHandler.getDocumentDetails();
					simpleElementParamGroupList = parameterHandler.returnSimpleTypeElement(document, parameterGroup);
				} catch (Exception e) {
					LOGGER.error("Unable To Fetch Simple Paramter Group ELements Based On The Parameter Group: " + e);
					throw new ParameterException(
							"Unable To Fetch The Paramter Details For The Group " + parameterGroup);
				}
			} else {
				LOGGER.error("Please Enter The Parameter Group." + "Parameter Group :" + parameterGroup);
			}

		}
		return simpleElementParamGroupList;
	}

	@Override
	public XMLParameterReader fetchXMLParameterValues() throws ParameterException {
		InputStream inputStream = null;
		JAXBContext jaxbContext = null;
		Unmarshaller jaxbUnmarshaller = null;
		XMLParameterReader xmlReader = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(PARAMETERS_XML);
			jaxbContext = JAXBContext.newInstance(SDSParameterImport.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			SDSParameterImport sdsParameterImport = (SDSParameterImport) jaxbUnmarshaller.unmarshal(inputStream);
			
			//DashBoard
			xmlReader = new XMLParameterReader();
			xmlReader.setDashboardRange(sdsParameterImport.getSDSParameter().getDashBoard().getDashboardRange().toString());
			
			//OrderSearch			
			xmlReader.setOrderSearchRange(
					sdsParameterImport.getSDSParameter().getOrder().getOrderSearch().getOrderSearchRange().toString());
			xmlReader.setOpenOrderSearchRange(sdsParameterImport.getSDSParameter().getOrder().getOrderSearch()
					.getOpenOrderSearchRange().toString());
			xmlReader.setDeliveredOrderSearchRange(sdsParameterImport.getSDSParameter().getOrder().getOrderSearch()
					.getDeliveredOrderSearchRange().toString());
			xmlReader.setCancelledOrderSearchRange(sdsParameterImport.getSDSParameter().getOrder().getOrderSearch()
					.getCancelledOrderSearchRange().toString());
			xmlReader.setReturnedOrderSearchRange(sdsParameterImport.getSDSParameter().getOrder().getOrderSearch()
					.getReturnedOrderSearchRange().toString());
			xmlReader.setSaveOrderSearchRange(sdsParameterImport.getSDSParameter().getOrder().getOrderSearch()
					.getSaveOrderSearchRange().toString());
			xmlReader.setDataEntryRoleID(sdsParameterImport.getSDSParameter().getOrder().getOrderSearch().getDataEntryRoleID().toString());
			xmlReader.setSalesAgentRoleID(sdsParameterImport.getSDSParameter().getOrder().getOrderSearch().getSalesAgentRoleID().toString());
			xmlReader.setDivisionHeadRoleID(sdsParameterImport.getSDSParameter().getOrder().getOrderSearch().getDivisionHeadRoleID().toString());
			
			//BookOrder
			xmlReader.setEnableValidateInventory(String.valueOf(
					sdsParameterImport.getSDSParameter().getOrder().getBookOrder().getCheckForAvailableInventory()));
			xmlReader.setEnableBookOrderManagerOverride(String
					.valueOf(sdsParameterImport.getSDSParameter().getOrder().getBookOrder().getEnableBookOrderManagerOverride()));
			xmlReader.setEnableRebateCustomerForExpiryItems(String.valueOf(sdsParameterImport.getSDSParameter()
					.getOrder().getBookOrder().getEnableRebateCustomerForExpiryItems()));
			xmlReader.setDiscountPercentageLimit(String.valueOf(
					sdsParameterImport.getSDSParameter().getOrder().getBookOrder().getDiscountPercentageLimit()));
			xmlReader.setEnableDoubleDiscounts(String.valueOf(
					sdsParameterImport.getSDSParameter().getOrder().getBookOrder().getEnableDoubleDiscounts()));
			xmlReader.setOrderExpiryDays(String.valueOf(
					sdsParameterImport.getSDSParameter().getOrder().getBookOrder().getOrderExpiryDays()));
			xmlReader.setScheduledDeliveryOrderBeforeNoOfDays(String.valueOf(
					sdsParameterImport.getSDSParameter().getOrder().getBookOrder().getScheduledDeliveryOrderBeforeNoOfDays()));
			xmlReader.setBookOrderEnableSendingMailToCustomer(String.valueOf(
					sdsParameterImport.getSDSParameter().getOrder().getBookOrder().getEnableSendingMailToCustomer()));
			xmlReader.setBookOrderEnableSendingMailToDeptHead(String.valueOf(
					sdsParameterImport.getSDSParameter().getOrder().getBookOrder().getEnableSendingMailToDeptHead()));
			xmlReader.setBookOrderEnableSendingMailToSalesAgent(String.valueOf(
					sdsParameterImport.getSDSParameter().getOrder().getBookOrder().getEnableSendingMailToSalesAgent()));
			xmlReader.setEnableExceedingCustomerAvailableLimit(String.valueOf(sdsParameterImport.getSDSParameter()
					.getOrder().getBookOrder().getEnableExceedingCustomerAvailableLimit()));
			xmlReader.setBookOrderEnableGroupingDiscountedLineItems(String.valueOf(sdsParameterImport.getSDSParameter()
					.getOrder().getBookOrder().getEnableGroupingDiscountedLineItems()));
			xmlReader.setBookOrderEnableSendingMailToDataEntryOperator(String.valueOf(sdsParameterImport.getSDSParameter()
					.getOrder().getBookOrder().getEnableSendingMailToDataEntryOperator()));
			xmlReader.setInceaseAvailLimitPercenatageForSegmentA(String.valueOf(sdsParameterImport.getSDSParameter()
					.getOrder().getBookOrder().getInceaseAvailLimitPercenatageForSegmentA()));
			xmlReader.setInceaseAvailLimitPercenatageForSegmentB(String.valueOf(sdsParameterImport.getSDSParameter()
					.getOrder().getBookOrder().getInceaseAvailLimitPercenatageForSegmentB()));
			xmlReader.setInceaseAvailLimitPercenatageForSegmentC(String.valueOf(sdsParameterImport.getSDSParameter()
					.getOrder().getBookOrder().getInceaseAvailLimitPercenatageForSegmentC()));
			
			//InvoiceSearch
			xmlReader.setInvoiceSearchRange(sdsParameterImport.getSDSParameter().getInvoice().getInvoiceSearch()
					.getInvoiceSearchRange().toString());
			xmlReader.setOpenInvoiceRange(sdsParameterImport.getSDSParameter().getInvoice().getInvoiceSearch()
					.getOpenInvoiceRange().toString());
			xmlReader.setInvoiceEnableSendingMailToCustomer(String.valueOf(
					sdsParameterImport.getSDSParameter().getInvoice().getInvoiceSearch().getEnableSendingMailToCustomer()));
			xmlReader.setInvoiceEnableSendingMailToDeptHead(String.valueOf(
					sdsParameterImport.getSDSParameter().getInvoice().getInvoiceSearch().getEnableSendingMailToDeptHead()));
			xmlReader.setInvoiceEnableSendingMailToSalesAgent(String.valueOf(
					sdsParameterImport.getSDSParameter().getInvoice().getInvoiceSearch().getEnableSendingMailToSalesAgent()));
			xmlReader.setInvoiceEnableSendingMailToDataEntryOperator(String.valueOf(sdsParameterImport.getSDSParameter()
					.getInvoice().getInvoiceSearch().getEnableSendingMailToDataEntryOperator()));
			xmlReader.setEnableSDSOfflineInvoice(String.valueOf(sdsParameterImport.getSDSParameter()
					.getInvoice().getInvoiceSearch().getEnableSDSOfflineInvoice()));
			xmlReader.setOfflineInvoiceGenerationTimeInMinutes(String.valueOf(sdsParameterImport.getSDSParameter()
					.getInvoice().getInvoiceSearch().getOfflineInvoiceGenerationTimeInMinutes()));
			xmlReader.setEnableMarkAsDeliveredForOfflineInvoice((String.valueOf(sdsParameterImport.getSDSParameter()
					.getInvoice().getInvoiceSearch().getEnableMarkAsDeliveredForOfflineInvoice())));
			xmlReader.setEnableInvoiceCancellation(String.valueOf(sdsParameterImport.getSDSParameter()
					.getInvoice().getInvoiceSearch().getEnableInvoiceCancellation()));
			/*
			 *	Laxmikanth: added to bind the recordPayment option
			 */
			xmlReader.setEnableRecordPaymentButton(String.valueOf(sdsParameterImport.getSDSParameter()
					.getInvoice().getInvoiceSearch().getEnableRecordPaymentButton()));
			
			//PaymentSearch
			xmlReader.setPaymentSearchRange(sdsParameterImport.getSDSParameter().getPayment().getPaymentSearch()
					.getPaymentSearchRange().toString());
			
			//PaymentMethod
			xmlReader.setEnablePaymentMethodCash(String.valueOf(
					sdsParameterImport.getSDSParameter().getPayment().getPaymentMethod().getEnablePaymentMethodCash()));
			xmlReader.setEnablePaymentmethodCheque(String.valueOf(sdsParameterImport.getSDSParameter().getPayment()
					.getPaymentMethod().getEnablePaymentmethodCheque()));
			xmlReader.setEnablePaymentmethodVoucher(String.valueOf(sdsParameterImport.getSDSParameter().getPayment()
					.getPaymentMethod().getEnablePaymentmethodVoucher()));
			xmlReader.setEnablePaymentmethodCoupon(String.valueOf(sdsParameterImport.getSDSParameter().getPayment()
					.getPaymentMethod().getEnablePaymentmethodCoupon()));
			xmlReader.setPaymentEnableSendingMailToCustomer(String.valueOf(
					sdsParameterImport.getSDSParameter().getPayment().getPaymentMethod().getEnableSendingMailToCustomer()));
			xmlReader.setPaymentEnableSendingMailToDeptHead(String.valueOf(
					sdsParameterImport.getSDSParameter().getPayment().getPaymentMethod().getEnableSendingMailToDeptHead()));
			xmlReader.setPaymentEnableSendingMailToSalesAgent(String.valueOf(
					sdsParameterImport.getSDSParameter().getPayment().getPaymentMethod().getEnableSendingMailToSalesAgent()));
			xmlReader.setPaymentEnableSendingMailToDataEntryOperator(String.valueOf(
					sdsParameterImport.getSDSParameter().getPayment().getPaymentMethod().getEnableSendingMailToDataEntryOperator()));

			//ClaimSearch
			xmlReader.setClaimSearchRange(
					sdsParameterImport.getSDSParameter().getClaim().getClaimSearch().getClaimSearchRange().toString());
			xmlReader.setApproveClaimSearchRange(sdsParameterImport.getSDSParameter().getClaim().getClaimSearch()
					.getApproveClaimSearchRange().toString());
			xmlReader.setClaimNeedToBeAcceptedRange(sdsParameterImport.getSDSParameter().getClaim().getClaimSearch()
					.getClaimNeedToBeAcceptedRange().toString());
			xmlReader.setClaimAutoAcceptedRange(sdsParameterImport.getSDSParameter().getClaim().getClaimSearch()
					.getClaimAutoAcceptedRange().toString());
			xmlReader.setRejectedClaimRange(sdsParameterImport.getSDSParameter().getClaim().getClaimSearch()
					.getRejectedClaimRange().toString());
			
			//RegisterClaim
			xmlReader.setEnableClaimWithOutInvoice(String.valueOf(
					sdsParameterImport.getSDSParameter().getClaim().getRegisterClaim().getEnableClaimWithOutInvoice()));
			xmlReader.setEnableRegisterClaimManagerOveride(String
					.valueOf(sdsParameterImport.getSDSParameter().getClaim().getRegisterClaim().getEnableRegisterClaimManagerOveride()));
            xmlReader.setRegisterClaimEnableSendingMailToCustomer(String.valueOf(
		            sdsParameterImport.getSDSParameter().getClaim().getRegisterClaim().getEnableSendingMailToCustomer()));
            xmlReader.setRegisterClaimEnableSendingMailToDeptHead(String.valueOf(
		            sdsParameterImport.getSDSParameter().getClaim().getRegisterClaim().getEnableSendingMailToDeptHead()));
            xmlReader.setRegisterClaimEnableSendingMailToSalesAgent(String.valueOf(
		            sdsParameterImport.getSDSParameter().getClaim().getRegisterClaim().getEnableSendingMailToSalesAgent()));
            xmlReader.setRegisterClaimEnableSendingMailToDataEntryOperator(String.valueOf(
		            sdsParameterImport.getSDSParameter().getClaim().getRegisterClaim().getEnableSendingMailToDataEntryOperator()));

            //ApproveClaim        	
        	 xmlReader.setApproveClaimEnableSendingMailToCustomer(String.valueOf(
 		            sdsParameterImport.getSDSParameter().getClaim().getApproveClaim().getEnableSendingMailToCustomer()));
             xmlReader.setApproveClaimEnableSendingMailToDeptHead(String.valueOf(
 		            sdsParameterImport.getSDSParameter().getClaim().getApproveClaim().getEnableSendingMailToDeptHead()));
             xmlReader.setApproveClaimEnableSendingMailToSalesAgent(String.valueOf(
 		            sdsParameterImport.getSDSParameter().getClaim().getApproveClaim().getEnableSendingMailToSalesAgent()));
             xmlReader.setApproveClaimEnableSendingMailToDataEntryOperator(String.valueOf(
  		            sdsParameterImport.getSDSParameter().getClaim().getApproveClaim().getEnableSendingMailToDataEntryOperator()));
        	
            //AcceptClaim    
             xmlReader.setEnableAutoAcceptClaim(String.valueOf(
         			sdsParameterImport.getSDSParameter().getClaim().getAcceptClaim().getEnableAutoAcceptClaim()));
             xmlReader.setAcceptClaimEnableSendingMailToCustomer(String.valueOf(
  		            sdsParameterImport.getSDSParameter().getClaim().getAcceptClaim().getEnableSendingMailToCustomer()));
              xmlReader.setAcceptClaimEnableSendingMailToDeptHead(String.valueOf(
  		            sdsParameterImport.getSDSParameter().getClaim().getAcceptClaim().getEnableSendingMailToDeptHead()));
              xmlReader.setAcceptClaimEnableSendingMailToSalesAgent(String.valueOf(
  		            sdsParameterImport.getSDSParameter().getClaim().getAcceptClaim().getEnableSendingMailToSalesAgent()));
              xmlReader.setAcceptClaimEnableSendingMailToDataEntryOperator(String.valueOf(
    		            sdsParameterImport.getSDSParameter().getClaim().getAcceptClaim().getEnableSendingMailToDataEntryOperator()));
              
            //Customer Search
			xmlReader.setCustomerNoOfOpenOrderDisplay(String.valueOf(sdsParameterImport.getSDSParameter().getCustomer()
					.getCustomerSearch().getCustomerNoOfOpenOrderDisplay()));
			xmlReader.setCustomerNoOfReceiptDisplay(String.valueOf(sdsParameterImport.getSDSParameter().getCustomer()
					.getCustomerSearch().getCustomerNoOfReceiptDisplay()));
			xmlReader.setCustomerNoOfCreditNoteDisplay(String.valueOf(sdsParameterImport.getSDSParameter().getCustomer()
					.getCustomerSearch().getCustomerNoOfCreditNoteDisplay()));
			xmlReader.setCustomerNoOfOpenInvoiceDisplay(String.valueOf(sdsParameterImport.getSDSParameter()
					.getCustomer().getCustomerSearch().getCustomerNoOfOpenInvoiceDisplay()));
			
			//LowInventoryNotification
			xmlReader.setEnableLowInventoryNotification(String.valueOf(sdsParameterImport.getSDSParameter().getNotification()
					.getInventoryNotification().getEnableLowInventoryNotification()));
			xmlReader.setLowInventoryNotificationThreshold(String.valueOf(sdsParameterImport.getSDSParameter().getNotification()
					.getInventoryNotification().getLowInventoryNotificationThreshold()));
			xmlReader.setLowInventoryNotificationEmail(String.valueOf(sdsParameterImport.getSDSParameter().getNotification()
					.getInventoryNotification().getLowInventoryNotificationEmail()));
			
			//NewInvoiceNotification
			xmlReader.setEnableNewInvoiceNotification(String.valueOf(sdsParameterImport.getSDSParameter().getNotification()
					.getNewInvoiceNotification().getEnableNewInvoiceNotification()));
			xmlReader.setNewInvoiceNotificationRange(String.valueOf(sdsParameterImport.getSDSParameter().getNotification()
					.getNewInvoiceNotification().getNewInvoiceNotificationRange()));
			
		}

		catch (JAXBException e) {
			LOGGER.error("Unable To Fetch The XML Values From The Paramtere: " + e);
		}

		catch (Exception e) {
			LOGGER.error("Unable To Fetch The XML Values From The Paramtere:" + e);
		}

		finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				LOGGER.error("Unable To Close The Input Stream :" + e);
			}
		}
		return xmlReader;

	}

}
