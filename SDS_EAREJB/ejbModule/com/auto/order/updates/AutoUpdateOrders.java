package com.auto.order.updates;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.retailsols.sds.transaction.ejb.TransactionService;

import rispl.dkart.services.DkartServices.SdsEntityManagerFactory;
import rispl.dkart.services.PromotionsService;
import rispl.dkart.services.ejb.transaction.OrderTransactions;
import rispl.dkart.services.entities.transaction.OrderTranDiscountItem;
import rispl.dkart.services.entities.transaction.OrderTranHeader;
import rispl.dkart.services.entities.transaction.OrderTranLineItem;
import rispl.services.item.DatabaseSourceIfc;
import rispl.services.transaction.find.OrderTransactionException;

@ManagedBean
public class AutoUpdateOrders implements DatabaseSourceIfc {

	private static final Logger LOGGER = LogManager.getLogger(AutoUpdateOrders.class);

	@Inject
	@SdsEntityManagerFactory
	EntityManagerFactory emf;

	@Inject
	OrderTransactions orderTransactions;
	
	@Inject
	TransactionService transactionService;

	public void lookupForOrdersToDuplicate() {
		LOGGER.info("Executing lookupForOrdersToDuplicate Logic");
		EntityManager em = emf.createEntityManager();

		Query select = em.createNativeQuery(autoUpdateOrders);

		List<String[]> values = select.getResultList();
		OrdersForUpdates[] ordersUpdates = null;
		Map<String, OrdersForUpdates> orderShpdQtyDtls = new HashMap<String, OrdersForUpdates>();

		for (Object[] s : values) {
			OrdersForUpdates ordersForUpdates = new OrdersForUpdates();
			if (orderShpdQtyDtls.containsKey(s[0])) {
				String itemsInMap = orderShpdQtyDtls.get(s[0]).getItemIds().get(s[1]);
				if (itemsInMap != null && itemsInMap != "") {
					BigDecimal qunty = new BigDecimal(itemsInMap);
					qunty = qunty.add((BigDecimal) s[2]);
					orderShpdQtyDtls.get(s[0]).getItemIds().put((String) s[1], qunty.toString());
				} else {
					orderShpdQtyDtls.get(s[0]).getItemIds().put((String) s[1], ((BigDecimal) s[2]).toString());
				}
			} else {
				ordersForUpdates.setOrderId((String) s[0]);
				ordersForUpdates.getItemIds().put((String) s[1], ((BigDecimal) s[2]).toString());
				orderShpdQtyDtls.put((String) s[0], ordersForUpdates);
			}
		}

		if (orderShpdQtyDtls.size() > 0) {
			LOGGER.info("Calling Order Update Partial/Complete");
			createTransaction(orderShpdQtyDtls.values());
		}
	}

	public List<OrderTranLineItem> splitTranLineItems(List<OrderTranLineItem> ordLineItms,
			PromotionsService promotions) {
		LOGGER.info("Executing splitTranLineItems Logic");
		BigDecimal oneQty = new BigDecimal(1);

		List<OrderTranLineItem> splttdOrdLineItms = new ArrayList<OrderTranLineItem>();

		for (OrderTranLineItem ordLineItm : ordLineItms) {
			if (ordLineItm.getLineQnt().compareTo(oneQty) == 0) {
				splttdOrdLineItms.add(ordLineItm);
			} else {
				// split line items based on item qty
				List<OrderTranDiscountItem> lineDiscounts = ordLineItm.getOrdTranDscItms();
				List<OrderTranLineItem> localSplittedLines = new ArrayList<OrderTranLineItem>();
				BigDecimal spltdQty = BigDecimal.ZERO;
				for (int qty = 1; qty <= ordLineItm.getLineQnt().intValue(); qty++) {
					// manual cloning.... need to have a clone method
					OrderTranLineItem temp = ordLineItm.clone();
					//OrderTranLineItem temp = SerializationUtils.clone(ordLineItm);
					temp.setLineQnt(oneQty);
					temp.setExtnLnItmRtn(temp.getItmPrnPrc().multiply(temp.getLineQnt()));
					temp.setExtnDscLnItm(temp.getExtnLnItmRtn());
					localSplittedLines.add(temp);
					spltdQty = spltdQty.add(oneQty);
				}

				if (ordLineItm.getLineQnt().compareTo(spltdQty) == 1) {// Handling
																			// Decimal
																		// Qty
																		// Lines
					OrderTranLineItem temp = ordLineItm.clone();

					temp.setLineQnt(ordLineItm.getLineQnt().subtract(spltdQty));
					temp.setExtnLnItmRtn(temp.getItmPrnPrc().multiply(temp.getLineQnt()));
					temp.setExtnDscLnItm(temp.getExtnLnItmRtn());
					localSplittedLines.add(temp);
				}

				// split discount line items on splittedLines
				if (lineDiscounts != null) {
					for (OrderTranDiscountItem discountLine : lineDiscounts) {
						promotions.prorateManualDiscounts(discountLine, localSplittedLines);
					}
				}

				// call method to calculate line item discounted price
				try {
					promotions.calculateLineItemTotals(localSplittedLines);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LOGGER.error(e.getMessage());
				}

				splttdOrdLineItms.addAll(localSplittedLines);
			}
		}

		return splttdOrdLineItms;

	}

	/*
	 * Method to clone line items
	 */
	public List<OrderTranLineItem> cloneLineItems(OrderTranHeader transaction, PromotionsService promotions) {
		LOGGER.info("Executing cloneLineItems Logic");
		List<OrderTranLineItem> splittedItems = new ArrayList<OrderTranLineItem>();
		List<OrderTranLineItem> tranLineItms = transaction.getOrdTranLineItems();
		splittedItems = splitTranLineItems(tranLineItms, promotions);
		return splittedItems;
	}

/*	Krishna: Created new Method
 *
  public void createTransaction(Collection<OrdersForUpdates> ordersUpdates) {
		LOGGER.info("Executing createTransaction Logic");
		EntityManager em;
		try {
			em = emf.createEntityManager();
			for (OrdersForUpdates orderUpdate : ordersUpdates) {
				PromotionsTesting promotions = new PromotionsTesting();
				Query orders = em.createNamedQuery("INVOICE_DETAILS_ORDERID_TYPE", OrderTranHeader.class);
				orders.setParameter("orderId", orderUpdate.getOrderId());
				orders.setParameter("ordTyp", "23");

				List<OrderTranHeader> orderInitiateRecords = orders.getResultList();

				//Get partially Shipped line items quantity
				orders.setParameter("ordTyp", "26");
				List<OrderTranHeader> partialShippedRecords = orders.getResultList();

				//loop over order initiate records to split line items along with discounts
				if (orderInitiateRecords.size() > 0) {
					////////////////get the order initiate status order only
					OrderTranHeader header = orderInitiateRecords.get(0);
					List<OrderTranLineItem> ordInitSplttdLines = cloneLineItems(header, promotions);
					List<OrderTranLineItem> partialSplttdLines = new ArrayList<OrderTranLineItem>();
					if (partialShippedRecords.size() > 0) {
						for (OrderTranHeader prtlShpdRcd : partialShippedRecords) {
							List<OrderTranLineItem> prtlList = cloneLineItems(prtlShpdRcd, promotions);
							partialSplttdLines.addAll(prtlList);
						}
					}

					//Remove allready shipped and duplicated records from initial records
					for (OrderTranLineItem prtlyShpdLine : partialSplttdLines) {
						OrderTranLineItem removeLineItm = null;
						for (OrderTranLineItem tranLineinit : ordInitSplttdLines) {
							if (tranLineinit.getItemId().equalsIgnoreCase(prtlyShpdLine.getItemId())
									&& tranLineinit.getId().getOrdLnItmSeq() == prtlyShpdLine.getId().getOrdLnItmSeq()
									&& tranLineinit.getExtnDscLnItm().compareTo(prtlyShpdLine.getExtnDscLnItm()) == 0) {
								removeLineItm = tranLineinit;
								break;
							}
						}
						if (removeLineItm != null) {
							ordInitSplttdLines.remove(removeLineItm);
						}
					}

					//new duplicate records that needs to be persisted in DB
					Map<String, String> ordShpdItmQty = orderUpdate.getItemIds();
					List<OrderTranLineItem> newDummyRecods = new ArrayList<OrderTranLineItem>();
					Set<String> shippdItemnumbers = ordShpdItmQty.keySet();
					for (String itmNumbr : shippdItemnumbers) {
						List<OrderTranLineItem> tobeRremovedLines = new ArrayList<OrderTranLineItem>();
						BigDecimal shippdQty = BigDecimal.ZERO;
						if (ordShpdItmQty.containsKey(itmNumbr)) {
							shippdQty = new BigDecimal(ordShpdItmQty.get(itmNumbr));
						}

						if (shippdQty.compareTo(BigDecimal.ZERO) != 0) {
							BigDecimal localQty = BigDecimal.ZERO;
							for (OrderTranLineItem ordLine : ordInitSplttdLines) {

								if (ordLine.getItemId().equalsIgnoreCase(itmNumbr)) {
									localQty = localQty.add(ordLine.getLineQnt());
									tobeRremovedLines.add(ordLine);
								}

								if (localQty.compareTo(shippdQty) == 0) {
									break;
								}
							}
						}

						ordInitSplttdLines.removeAll(tobeRremovedLines);
						newDummyRecods.addAll(tobeRremovedLines);
					}

					//group new dummy records based on line sequence number
					List<OrderTranLineItem> finalDuplicateRecords = new ArrayList<OrderTranLineItem>();
					for (OrderTranLineItem newDumRcd : newDummyRecods) {
						boolean found = false;
						for (OrderTranLineItem finalUpd : finalDuplicateRecords) {
							if (finalUpd.getId().getOrdLnItmSeq() == newDumRcd.getId().getOrdLnItmSeq()
									&& finalUpd.getItemId().equalsIgnoreCase(newDumRcd.getItemId())) {

								finalUpd.setLineQnt(finalUpd.getLineQnt().add(newDumRcd.getLineQnt()));
								finalUpd.setExtnLnItmRtn(finalUpd.getExtnLnItmRtn().add(newDumRcd.getExtnLnItmRtn()));
								finalUpd.setExtnDscLnItm(finalUpd.getExtnDscLnItm().add(newDumRcd.getExtnDscLnItm()));

								List<OrderTranDiscountItem> orderTranDiscountItems = newDumRcd.getOrdTranDscItms();
								if (orderTranDiscountItems != null && orderTranDiscountItems.size() > 0) {
									List<OrderTranDiscountItem> orderTranDiscountItemsGrpd = finalUpd
											.getOrdTranDscItms();
									if (orderTranDiscountItemsGrpd == null) {
										orderTranDiscountItemsGrpd = new ArrayList<OrderTranDiscountItem>();
										orderTranDiscountItemsGrpd.addAll(orderTranDiscountItems);
									} else {
										for (OrderTranDiscountItem newDisc : orderTranDiscountItems) {
											for (OrderTranDiscountItem grpdDisc : orderTranDiscountItemsGrpd) {
												boolean disFound = false;
												if (grpdDisc.getTyDsc() != null
														&& grpdDisc.getTyDsc()
																.compareTo(DKartConstantsIfc.DIS_PROM_AUTO) == 0
														&& newDisc.getTyDsc() != null
														&& newDisc.getTyDsc()
																.compareTo(DKartConstantsIfc.DIS_PROM_AUTO) == 0
														&& grpdDisc.getPrmCmpDtlid()
																.compareTo(newDisc.getPrmCmpDtlid()) == 0) {
													grpdDisc.setDscAmt(grpdDisc.getDscAmt().add(newDisc.getDscAmt()));
													disFound = true;
												} else if (grpdDisc.getTyDsc() != null
														&& grpdDisc.getTyDsc()
																.compareTo(DKartConstantsIfc.DIS_PROM_MNUL) == 0
														&& newDisc.getTyDsc() != null
														&& newDisc.getTyDsc()
																.compareTo(DKartConstantsIfc.DIS_PROM_MNUL) == 0
														&& grpdDisc.getPrmId().compareTo(newDisc.getPrmId()) == 0) {
													grpdDisc.setDscAmt(grpdDisc.getDscAmt().add(newDisc.getDscAmt()));
													disFound = true;
												}

												if (!disFound) {
													orderTranDiscountItemsGrpd.add(newDisc);
												}
											}
										}
									}
									orderTranDiscountItemsGrpd.size();
									finalUpd.setOrdTranDscItms(orderTranDiscountItemsGrpd);
								}
								found = true;
								break;
							}
						}

						if (!found) {
							finalDuplicateRecords.add(newDumRcd);
						}
					}
					//promotions.calculateLineItemTotals(finalDuplicateRecords);

					BigDecimal subTotal = BigDecimal.ZERO;
					BigDecimal discTotal = BigDecimal.ZERO;
					BigDecimal taxtotal = BigDecimal.ZERO;
					for (OrderTranLineItem finalLineItm : finalDuplicateRecords) {
						subTotal = subTotal.add(finalLineItm.getExtnLnItmRtn());
						discTotal = discTotal.add(finalLineItm.getExtnDscLnItm());
					}

					BigDecimal discAmt = subTotal.subtract(discTotal);
					List<OrderTranSum> orderTranSums = header.getOrdTranSums();
					for(OrderTranSum orderTranSum : orderTranSums){
					orderTranSum.setDkartSlsTot(subTotal);
					orderTranSum.setDkartDscTot(discAmt);
					orderTranSum.setDkartNetTot(discTotal);
					orderTranSum.setDkartTaxTot(taxtotal);
					}
					
					header.setOrdTranSums(orderTranSums);
					header.setOrdTranLineItems(finalDuplicateRecords);
					if (ordInitSplttdLines.size() > 0) {
						header.setOrdTy("26");
					} else {
						header.setOrdTy("24");
					}
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String tranDate = format.format(new Date());
					header.getId().setDcDyOrd(tranDate);
					//OrderTransactions ejbRef = new OrderTransactions(); //(OrderTransactions) new InitialContext().lookup("java:rispl/dkart/services/ejb/transaction/OrderTransactions");
					header.getId().setTrnSeq(orderTransactions.getTransactionSequence());
					try {
						LOGGER.info("Calling saveCompletedTransactions method");
						orderTransactions.saveCompletedTransactions(header);
					} catch (OrderTransactionException e) {
						e.printStackTrace();
						LOGGER.error(e);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(),e);
		}

	}*/

	
	//Krishna: Only for complete shipment
	public void createTransaction(Collection<OrdersForUpdates> ordersUpdates) {
		LOGGER.info("Executing createTransaction Logic");
		EntityManager em;		
		em = emf.createEntityManager();
		for (OrdersForUpdates orderUpdate : ordersUpdates)
		{
			try {
				Query orders = em.createNamedQuery("INVOICE_DETAILS_ORDERID_TYPE", OrderTranHeader.class);
				orders.setParameter("orderId", orderUpdate.getOrderId());
				orders.setParameter("ordTyp", "23");//added by hanu
				orders.setParameter("tranStatus", new BigDecimal(2));//added by hanu				
				orders.setParameter("scOrd", new BigDecimal(3));//added by hanu
				List<OrderTranHeader> orderInitiateRecords = orders.getResultList();

				/*
				 * //Get partially Shipped line items quantity
				 * orders.setParameter("ordTyp", "26"); List<OrderTranHeader>
				 * partialShippedRecords = orders.getResultList();
				 * 
				 * //Get partially Shipped line items quantity
				 * orders.setParameter("ordTyp", "24"); List<OrderTranHeader>
				 * fullShippedRecords = orders.getResultList();
				 */

				// if Order initiate has one Record and fully shipped then allow complete Order to generate
				if (orderInitiateRecords.size() == 1
						&& isEligibleForFullShipment(orderInitiateRecords.get(0), orderUpdate)) {
					OrderTranHeader header = orderInitiateRecords.get(0);
					header.setOrdTy("24");
					header.setScOrd(new BigDecimal(5)); //Saideep: Issue 514: Complete order status not updating correctly
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String tranDate = format.format(new Date());
					header.getId().setDcDyOrd(tranDate);
					header.getId().setTrnSeq(transactionService.getTranSeq(header.getId().getRtStrId()));
					LOGGER.info("Calling saveCompletedTransactions method");
					orderTransactions.saveCompletedTransactions(header);
				}

			} catch (OrderTransactionException e) {
				LOGGER.error(e);
			} catch (Exception e) {
				LOGGER.error(e.toString(), e);
			}

		}
	}

	private boolean isEligibleForFullShipment(OrderTranHeader orderInitiateRecords,
			OrdersForUpdates orderUpdate) 
	{
		boolean result=false;
		double totalItemsShipped=getShippedQuantity(orderUpdate);
		double totalItemInOrdertransaction=getOrderInitiateQuantity(orderInitiateRecords.getOrdTranLineItems());
		if(totalItemsShipped>0
				&&totalItemInOrdertransaction>0
				&&totalItemsShipped==totalItemInOrdertransaction)
		{
			result= true;
		}
		
		LOGGER.info("Order Id: "+ orderUpdate.getOrderId()+" is Eligible For FullShipment: "+ result);
		LOGGER.info("Total Items Shipped= "+ totalItemsShipped+" and total Item In Ordertransaction = " +totalItemInOrdertransaction);
		return result;
	}

	private double getOrderInitiateQuantity(List<OrderTranLineItem> ordTranLineItems)
	{
		double totalItemInOrdertransaction=0.00d;
		for(OrderTranLineItem TranLineItem:ordTranLineItems)
		{
			if(TranLineItem.getItmTy()!=null
					&&TranLineItem.getItmTy().compareTo(new BigDecimal("2"))==0)// Service Item
			{
				continue;
			}
			totalItemInOrdertransaction+=TranLineItem.getLineQnt().doubleValue();
		}
		return totalItemInOrdertransaction;
	}

	private double getShippedQuantity(OrdersForUpdates orderUpdate) {
		double totalItemsShipped=0.00d;
		for(String itemID:orderUpdate.getItemIds().keySet())
		{
			String qty=orderUpdate.getItemIds().get(itemID);
			totalItemsShipped+=Double.parseDouble(qty);
		}
		return  totalItemsShipped;
	}

	public static void main(String args[]) {

		AutoUpdateOrders order = new AutoUpdateOrders();
		order.lookupForOrdersToDuplicate();

	}
	
}
