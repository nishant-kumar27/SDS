<style>
@page
{
	size: landscape;	<#-- To Print this PDF page in Landscape -->
	<#-- size: portrait; -->  	<#-- To Print this PDF page in Portrait -->
	margin: 1cm;
}


#itemTable {
table {
    border-collapse: collapse;
    width: 100%;
	  }
}
#itemTable th, #itemTable td {
    text-align: right;
    padding: 0px;
}
#itemTable tr:nth-child(odd){background-color: #ddd}


#table {
    border-collapse: collapse;
    width: 100%;
}
th, td {
    padding: 8px;
    text-align: left;
    border-bottom: 0.2px solid #ddd;
}
tr:hover {background-color:#f5f5f5;}
</style>

<table width="100%">
	<tr> 
		<td><img alt="logo" src="assets/SDSlogo.png"></td>
		<td style="text-align:right;"> <h6 style="color:#4d4d4d">
			<@s.i18n
				name="rispl.print.printText">
				<@s.text name="salesInvoice.Address2" />
			</@s.i18n>
		</h6></td>
		<td style="text-align:right;"> <h6 style="color:#4d4d4d">
			<@s.i18n
				name="rispl.print.printText">
				<@s.text name="salesInvoice.Address1" />
			</@s.i18n>
		</h6></td>
	</tr>
</table>


					 <center><h1 style="color: #226e71;">Order Details</h1></center>
<br>

<table width="100%">
		<tr>
			<th>
				<small style="color:#4d4d4d">Order ID</small></th><th>:</th>
				<th><small>
					<#if order.id.orderId??>
						${order.id.orderId}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Customer ID</small></th><th>:</th>
				<th><small>
					<#if customer.customerHeaderPK.custId??>
						${customer.customerHeaderPK.custId}
					<#else>
					</#if>	
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Invoice No</small></th><th>:</th>
				<th><small>
					<#if order.invoiceId??>
						${order.invoiceId}
					<#else>
					</#if>	
				</small>
			</th>
		<tr>
		</tr>	
			<th>
				<small style="color:#4d4d4d">Order Date</small></th><th>:</th>
				<th><small>
					<#if order.id.orderDate??>
						<#setting date_format="dd-MMM-yyyy">
						<#setting locale="en_US">
						<#assign createdOn=order.id.orderDate> 
						${createdOn?datetime?string('dd-MMM-yyyy')}
					<#else>
					</#if>
					</small>	
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Customer Name</small></th><th>:</th>
				<th><small> 
					<#if customer.ctNm??>	
						${customer.ctNm}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Shipment Method</small></th><th>:</th>
				<th><small></small>
			</th>
		</tr>
		<tr>
			<th>
				<small style="color:#4d4d4d">Effective Date</small></th><th>:</th>
				<th><small>
					<#if order.effectiveDate??>
						<#setting date_format="dd-MMM-yyyy">
						<#setting locale="en_US">
						<#assign createdOn=order.effectiveDate> 
							${createdOn?datetime?string('dd-MMM-yyyy')}
					<#else>
					</#if>
					</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Customer Segment</small></th><th>:</th>
				<th><small>
					<#if cust_seg??>
						${cust_seg}
					<#else>
					</#if>	
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Sales Agent</small></th><th>:</th>
				<th><small>
					<#if order.salesAgent??>
						${order.salesAgent}
					<#else>
					</#if>	
				</small>
			</th>
		</tr>
		<tr>
			<th>
				<small style="color:#4d4d4d">Status</small></th><th>:</th>
				<th><small>
					<#if order.status??>
						${order.status}
					<#else>
					</#if>	
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">LPO No</small></th><th>:</th>
				<th><small>
					<#if ord_tran_sum.custLpoNum??>
						${ord_tran_sum.custLpoNum}
					<#else>
					</#if>	
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Comment</small></th><th>:</th>
				<th><small>
					<#if ord_tran_header.ctDvrInfoIns??>
						${ord_tran_header.ctDvrInfoIns}
					<#else>
					</#if>
				</small>
			</th>
		</tr>
		<tr>
			<th>
				<small style="color:#4d4d4d">Shipping Address</small></th><th>:</th>
				<th><small>
					<#if ord_tran_header.ctDvrInf??>
						${ord_tran_header.ctDvrInf}
					<#else>
					</#if>	
					</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">LPO Date</small></th><th>:</th>
				<th><small>
					<#if ord_tran_sum.custLpoDate??>
						<#setting date_format="dd-MMM-yyyy">
					<#setting locale="en_US">
					<#assign createdOn=ord_tran_sum.custLpoDate> 
							${createdOn?datetime?string('dd-MMM-yyyy')}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Approval Time</small></th><th>:</th>
				<th><small>
					<#if ord_timestamp??>
						<#setting date_format="MMM d, yyyy h:mm:ss a">
						<#setting locale="en_US">
						<#assign createdOn=ord_timestamp> 
							${createdOn?datetime?string('MMM d, yyyy h:mm:ss a')}
					<#else>
					</#if>
					</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
		</tr>
		
</table>
<br><br>

<table width="100%" id="itemTable">
		<tr style="background-color:#ADC2EE;">
			<th style="text-align:left;"><h6>#</h6></th>&nbsp;&nbsp;
			<th style="text-align:left;"><h6>Item<h6></th>
			<th><h6>Order<br>Qty</h6></th>
			<th><h6>Ref No</h6></th>
			<th><h6>Shipped<br>Qty</h6></th>
			<th><h6>Unit Price<br>(<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Disc Amt<br>(<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Tax (<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Total (<@s.property value="getText('global.currency')"/>)</h6></th>
		</tr>
		<#assign totItemsQty=0>
		<#list order_items as lineItem>
		<tr>
			<td style="text-align:left;">
				<#assign res='${lineItem_index}'>
				<#assign seqNo=res?number>
				<#assign seqNo++>
				<h6>${seqNo}.</h6>
			</td>&nbsp;&nbsp;
			<td style="text-align:left;"><h6>${lineItem.itemId} <br> ${lineItem.deItmShrtRcpt}<br>
				<#list lineItem.ordTranDscItms as lineItems>
					<#--Dispalying the discount description when order have Item & Transcation level Discount -->
					<#if lineItems.prmId??>
					<#else>
						<#if lineItems.tyDsc==0 && (lineItems.dscPer > 0)>
							I_${getAllDiscountReasnCode[lineItems.discReasonCode]} (%): ${lineItems.dscPer}<br>
						<#elseif lineItems.tyDsc==0 && (lineItems.dscAmt > 0)>
							I_${getAllDiscountReasnCode[lineItems.discReasonCode]} (Amt): ${lineItems.dscAmt}<br>
						<#elseif lineItems.tyDsc==1 && (lineItems.dscPer > 0)>
							T_${getAllDiscountReasnCode[lineItems.discReasonCode]} (%): ${lineItems.dscPer}<br>
						<#elseif lineItems.tyDsc==1 && (lineItems.dscAmt > 0)>
							T_${getAllDiscountReasnCode[lineItems.discReasonCode]} (Amt): ${lineItems.dscAmt}<br>
						</#if>
					</#if>

					<#--Dispalying the discount description when order have only Promotion level Discount -->	
					<#if lineItems.prmId??>
						<#if lineItems.tyDsc==0 && (lineItems.dscPer > 0)>
							<#if lineItems.prmDesc??>
								P_${lineItems.prmDesc} (%): ${lineItems.dscPer}<br>
							<#else>
								P_ (%): ${lineItems.dscAmt}<br>
							</#if>
						<#elseif lineItems.tyDsc==0 && (lineItems.dscAmt > 0)>
							<#if lineItems.prmDesc??>
								P_${lineItems.prmDesc} (Amt): ${lineItems.dscAmt}<br>
							<#else>
								P_ (Amt): ${lineItems.dscAmt}<br>
							</#if>
						<#elseif lineItems.tyDsc==1 && (lineItems.dscPer > 0)>
							<#if lineItems.prmDesc??>
								P_${lineItems.prmDesc} (%): ${lineItems.dscPer}<br>
							<#else>
								P_ (%): ${lineItems.dscAmt}<br>
							</#if>	
						<#elseif lineItems.tyDsc==1 && (lineItems.dscAmt > 0)>
							<#if lineItems.prmDesc??>
								P_${lineItems.prmDesc} (Amt): ${lineItems.dscAmt}<br>
							<#else>
								P_ (Amt): ${lineItems.dscAmt}<br>
							</#if>					
						</#if>
					<#else>
					</#if>
				</#list></h6>
			</td>
			<#if lineItem.lineQnt??>
				<#assign totItemsQty=totItemsQty+lineItem.lineQnt>
			<#else>
				<#assign x=0>
				<#assign totItemsQty=totItemsQty+x>
			</#if>
			
			<td><h6>
				<#if lineItem.lineQnt??>
					${lineItem.lineQnt}
				<#else>
				</#if>	
			</h6></td>
			<td><h6>
				<#if lineItem.registryId??>
					${lineItem.registryId}
				<#else>
				</#if>	
			</h6></td>
			<td><h6>
				<#if lineItem.shippedQty??>
					${lineItem.shippedQty}
				<#elseif lineItem.itmTy?? && lineItem.itmTy=2>
					${lineItem.lineQnt}
				<#else>	
					<#assign deliQty=0>
					${deliQty}
				</#if>
			</h6></td>
			<td><h6>
				<#if lineItem.overRidePrice??>
					${lineItem.overRidePrice}
				<#elseif lineItem.itmPrnPrc??>
					${lineItem.itmPrnPrc}
				<#else>
					<#assign x=0>
					${x}	
				</#if>	
			</h6></td>
			<td><h6>
				${lineItem.extnLnItmRtn-lineItem.extnDscLnItm}
			</h6></td>
			<td><h6>
				<#if lineItem.vatLnItmRtn??>
					${lineItem.vatLnItmRtn}
				<#else>
				</#if>	
			</h6></td>
			<td><h6>
				<#if lineItem.extnDscLnItm??>
					${lineItem.extnDscLnItm}
				<#else>
				</#if>	
			</h6></td>
		</tr>
		</#list>
</table>
<br><br>

<table align="right" width="30%">
		<tr>
			<td><small>No Of Items</small></td><td>:</td><td style="text-align:right;"><small>&nbsp;&nbsp;
					<#if ord_tran_header.getOrdTranLineItems().size()??>
						${ord_tran_header.getOrdTranLineItems().size()}
					<#else>
					</#if>	
				</small>
			</td>
		</tr>
		<tr>
			<td><small>Total Quantity </small></td><td>:</td><td style="text-align:right;"><small>&nbsp;&nbsp;
					<#if totItemsQty??>
						${totItemsQty}
					<#else>
					</#if>	
				</small>
			</td>
		</tr>
		<tr>
			<td><small>Sub Total</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;
					<#if ord_tran_sum.dkartSlsTot??>
						${ord_tran_sum.dkartSlsTot}
					<#else>
					</#if>	
				</small>
			</td>
		</tr>
		<tr>
			<td><small>Discount</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;
					<#if ord_tran_sum.dkartDscTot??>
						${ord_tran_sum.dkartDscTot}
					<#else>
					</#if>	
				</small>
   			</td>
		</tr>
		<tr>
			<td><small>Total Tax</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;   
					<#if ord_tran_sum.dkartTaxTot??>
						${ord_tran_sum.dkartTaxTot}
					<#else>
					</#if>	
				</small>
			</td>
		</tr>
		<tr>
			<td>Net Total</td><td>:</td><td style="text-align:right;"><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;
					<#if ord_tran_sum.dkartNetTot??>
						${ord_tran_sum.dkartNetTot}
					<#else>
					</#if>
			</td>
		</tr>
</table>