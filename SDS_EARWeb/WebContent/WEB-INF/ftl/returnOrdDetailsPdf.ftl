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
					 <center><h1 style="color: #226e71;">Returned Order Detail</h1></center>
<br>

<table width="100%">
		<tr>
			<th>
				<small style="color:#4d4d4d">Return Order ID</small></th><th>:</th>
				<th><small>
					<#if ord_tran_sum.idOrd??>
						${ord_tran_sum.idOrd}
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
				<small style="color:#4d4d4d">Status</small></th><th>:</th>
				<th><small>
					<#if order.status??>
						${order.status}
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
				<small style="color:#4d4d4d"></small></th><th></th>
				<th><small>
					

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
				<small style="color:#4d4d4d">Reason Code</small></th><th>:</th>
				<th><small> 
					<#if returnReasonCodeMap[ord_tran_header.returnReasonCode]??>
						${returnReasonCodeMap[ord_tran_header.returnReasonCode]}
					<#else>
					</#if>				
				</small>
			</th>
		</tr>
</table>
<br><br>

<table width="100%" id="itemTable">
		<tr style="background-color:#ADC2EE;">
			<th style="text-align:left;"><h6>#</h6></th>&nbsp;&nbsp;
			<th style="text-align:left;"><h6>Item<h6></th>
			<th><h6>Order Qty</h6></th>
			<th><h6>Registered Qty</h6></th>
			<th><h6>Approved Qty</h6></th>
			<th><h6>Received Qty</h6></th>
			<th><h6>Accepted<br>Price(<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Reason Code</h6></td>
			<th><h6>Net Price<br>(<@s.property value="getText('global.currency')"/>)</h6></th>
		</tr>
<#assign totItemQty=0>
		<#list claim_items as lineItem>
			<tr>
			<td style="text-align:left;">
				<#assign res='${lineItem_index}'>
				<#assign seqNo=res?number>
				<#assign seqNo++>
				<h6>${seqNo}.</h6>
			</td>&nbsp;&nbsp;
			<td style="text-align:left;"><h6>${lineItem.itemId} <br> ${lineItem.deItmShrtRcpt}</h6></td>
				<#if lineItem.whReceiveQty??>
					<#assign totItemQty=totItemQty + lineItem.whReceiveQty>
				<#else>
					<#assign x=0>
					<#assign totItemQty=totItemQty + x>
				</#if>	
			<td><h6>
				<#if lineItem.lineQnt??>
					${lineItem.lineQnt}
				<#else>
				</#if>	
			</h6></td>
			<td><h6>
				<#if lineItem.lineQntRtn??>
					${lineItem.lineQntRtn}
				<#else>
				</#if>	
			</h6></td>
			<td><h6>
				<#if lineItem.apprClaimQty??>
					${lineItem.apprClaimQty}
				<#else>
				</#if>	
			</h6></td>
			<td><h6>
				<#if lineItem.whReceiveQty??>
					${lineItem.whReceiveQty}
					
				<#else>
				</#if>	
			</h6></td>
			<td><h6>
				<#if lineItem.accptClaimPrice??>
					${lineItem.accptClaimPrice}
				<#else>
				</#if>	
			</h6></td>
			<td><h6>
				<#if lineItem.rcRtnMr??>
					${returnReasonCodeMap[lineItem.rcRtnMr]}
				<#else>
				</#if>	
			</h6></td>
			<td><h6>
				${lineItem.apprClaimPrice * lineItem.whReceiveQty}
			</h6></td>
			</tr>
		</#list>	
	</table>
<br><br>

<table align="right" width="30%">
		<tr>
			<td><small>No Of Items</small></td><td>:</td><td style="text-align:right;"><small> &nbsp;&nbsp;
				<#if claim_tran_header.getClaimTranLineItems().size()??>
					${claim_tran_header.getClaimTranLineItems().size()}
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
			<td><small>Total Tax</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/> &nbsp;&nbsp;  
				<#if ord_tran_sum.dkartTaxTot??>
					${ord_tran_sum.dkartTaxTot}
				<#else>
				</#if>		
				</small></td>
		</tr>
		<tr>
			<td>Net Total</td><td>:</td><td style="text-align:right;"><@s.property value="getText('global.currency')"/> &nbsp;&nbsp;   
					<#if ord_tran_sum.dkartNetTot??>
						${ord_tran_sum.dkartNetTot}
					<#else>
					</#if>		
				</td>
		</tr>
</table>






