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
					<#if orderTran.getOrdTranSum().getIdOrd()??>
						${orderTran.getOrdTranSum().getIdOrd()}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Customer ID</small></th><th>:</th>
				<th><small>
					<#if orderTran.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId()??>
						${orderTran.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId()}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Sales Agent</small></th><th>:</th>
				<th><small>
					<#if tranLevelAgentName??>
						${tranLevelAgentName}
					<#else>
					</#if>	
				</small>
			</th>
		<tr>
		
		</tr>	
			<th>
				<small style="color:#4d4d4d">Order Date</small></th><th>:</th>
				<th><small>
					<#if ordDate??>
						<#setting date_format="dd-MMM-yyyy">
						<#setting locale="en_US">
						<#assign createdOn=ordDate> 
							${createdOn?datetime?string('dd-MMM-yyyy')}
					<#else>
					</#if>
					</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Customer Name</small></th><th>:</th>
				<th><small> 
					<#if orderTran.getCustomer().getCustomerHeader().getCtNm()??>
						${orderTran.getCustomer().getCustomerHeader().getCtNm()}
					<#else>
					</#if>	
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Shipment Method</small></th><th>:</th>
				<th><small>
					
				</small>
			</th>
		</tr>
			 
		<tr>
			<th>
				<small style="color:#4d4d4d">Delivery Date</small></th><th>:</th>
				<th><small>
					<#if ordDeliDate??>
						<#setting date_format="dd-MMM-yyyy">
						<#setting locale="en_US">
						<#assign createdOn=ordDeliDate> 
							${createdOn?datetime?string('dd-MMM-yyyy')}
					<#else>
					</#if>
					</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">LPO Number</small></th><th>:</th>
				<th><small>
					<#if orderTran.getOrdTranSum().getCustLpoNum()??>
						${orderTran.getOrdTranSum().getCustLpoNum()} 
					<#else>
					</#if>	
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Delivery Comments</small></th><th>:</th>
				<th><small> 
					<#if orderTran.ctDvrInfoIns??>
						${orderTran.ctDvrInfoIns}
					<#else>
					</#if>
				</small>
			</th>
		</tr>
		<tr>
			<th>
				<small style="color:#4d4d4d">order Status</small></th><th>:</th>
				<th><small> 
					Quoted
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">LPO Date</small></th><th>:</th>
				<th><small>
					<#if orderTran.getOrdTranSum().getCustLpoDate()??>
						<#setting date_format="dd-MMM-yyyy">
						<#setting locale="en_US">
						<#assign createdOn=orderTran.getOrdTranSum().getCustLpoDate()> 
							${createdOn?datetime?string('dd-MMM-yyyy')}
					<#else>
					</#if>
					 
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Shipping Address</small></th><th>:</th>
				<th><small> 
					<#if orderTran.ctDvrInf??>
						${orderTran.ctDvrInf}
					<#else>
					</#if>
				</small>
			</th>
		</tr>
</table>
<br><br>

<table width="100%" id="itemTable">
		<tr style="background-color:#ADC2EE;">
			<th style="text-align:left;"><h6>#</h6></th>&nbsp;
			<th style="text-align:left;"><h6>Item<h6></th>
			<th><h6>Order<br>Qty</h6></th>
			<th><h6>Ref No</h6></th>
			<th><h6>Unit Price<br>(<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Disc (<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Tax (<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Total (<@s.property value="getText('global.currency')"/>)</h6></th>
		</tr>
		<#assign totItemQty=0>
		<#list orderTran.getOrdTranLineItems() as lineItem>
			<tr>
			<td style="text-align:left;">
				<#assign res='${lineItem_index}'>
				<#assign seqNo=res?number>
				<#assign seqNo++>
				<h6>${seqNo}.</h6>
			</td>&nbsp;
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
					<#assign totItemQty=totItemQty + lineItem.lineQnt>
				<#else>
					<#assign x = 0>
  					<#assign totItemQty=totItemQty + x>
				</#if>	
				
			
			<td><h6>
				<#if lineItem.lineQnt??>
					${lineItem.lineQnt}
				<#else>
					<#assign x = 0>
  					<h6>${x}</h6>
				</#if>	
			</h6></td>
			<td><h6>
				<#if lineItem.registryId??>
					${lineItem.registryId}
				<#else>
					<#assign x = 0>
  					<h6>${x}</h6>
				</#if>	
			</h6></td>
			
			<td><h6>
				<#if lineItem.overRidePrice??>
  					<h6>${lineItem.overRidePrice}</h6>
				<#elseif lineItem.itmPrnPrc??>
  					<h6>${lineItem.itmPrnPrc}</h6>
  				<#else>
  					<#assign x = 0>
  					<h6>${x}</h6>		
				</#if>
			</h6></td>
			<td><h6>
			 ${lineItem.extnLnItmRtn - lineItem.extnDscLnItm} 
			</h6></td>
			
			<td><h6>
				<#if lineItem.vatLnItmRtn??>
					${lineItem.vatLnItmRtn}
				<#else>
					<#assign x = 0>
  					<h6>${x}</h6>
				</#if>	
			</h6></td>
			<td><h6>
				<#if lineItem.extnDscLnItm??>
					${lineItem.extnDscLnItm}
				<#else>
					<#assign x = 0>
  					<h6>${x}</h6>
				</#if>	
			</h6></td>
			</tr>
		</#list>	
</table>
<br><br>

<table align="right" width="30%">
		<tr>
			<td><small>No Of Items</small></td><td>:</td><td style="text-align:right;"><small> &nbsp;&nbsp;
					<#if orderTran.getOrdTranLineItems().size()??>
						${orderTran.getOrdTranLineItems().size()}
					<#else>
					</#if>
				</small></td>
		</tr>
		<tr>
			<td><small>Total Quantity</small></td><td>:</td><td style="text-align:right;"><small> &nbsp;&nbsp;
					<#if totItemQty??>
						${totItemQty}
					<#else>
					</#if>	   
				</small></td>
		</tr>
		<tr>
			<td><small>Sub Total</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/> &nbsp;&nbsp;
					<#if orderTran.getOrdTranSum().getDkartSlsTot()??>
						${orderTran.getOrdTranSum().getDkartSlsTot()}
					<#else>
					</#if>	
				</small></td>
		</tr>
		<tr>
			<td><small>Discount</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/> &nbsp;&nbsp;   
					<#if orderTran.getOrdTranSum().getDkartDscTot()??>
						${orderTran.getOrdTranSum().getDkartDscTot()}
					<#else>
					</#if>	
				</small></td>
		</tr>
		<tr>
			<td><small>Total Tax</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;   
					<#if orderTran.getOrdTranSum().getDkartTaxTot()??>
						${orderTran.getOrdTranSum().getDkartTaxTot()}
					<#else>
					</#if>	
				</small></td>
		</tr>
		<tr>
			<td>Net Total</td><td>:</td><td style="text-align:right;"><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;   
					<#if orderTran.getOrdTranSum().getDkartNetTot()??>
						${orderTran.getOrdTranSum().getDkartNetTot()}
					<#else>
					</#if>	
				</td>
		</tr>
</table>






