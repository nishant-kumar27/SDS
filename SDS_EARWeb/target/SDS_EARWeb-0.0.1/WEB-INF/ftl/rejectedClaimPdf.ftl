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
			 <center><h1 style="color: #226e71;">Rejected Claim Details</h1></center>
<br>

<table width="100%">
		<tr>
			<th><small style="color:#4d4d4d">Claim ID</small></th><th>:</th><th><small>
					<#if claimID??>
						${claimID}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th><small style="color:#4d4d4d">Rejection Date</small></th><th>:</th><th><small>
					<#if rejClaimDate??> 
						${rejClaimDate}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th><small style="color:#4d4d4d">Customer ID</small></th><th>:</th><th><small>
				<#if custId??>
						${custId}
					<#else>
					</#if>
				</small>
			</th>
		<tr>
		</tr>	
			<th><small style="color:#4d4d4d">Claim Date</small></th><th>:</th><th><small>
					<#if claimedDate??>
						<#setting date_format="dd-MMM-yyyy">
						<#setting locale="en_US">
						<#assign createdOn=claimedDate> 
						${createdOn?datetime?string('dd-MMM-yyyy')}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th><small style="color:#4d4d4d">Rejection Comments</small></th><th>:</th><th><small>
				<#if rejectClaimNotes??>
					${rejectClaimNotes}
				<#else>
				</#if>
			</small></th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Customer Name</small></th><th>:</th><th><small>
					<#if customerName??>
						${customerName}
					<#else>
					</#if>
				</small>
			</th>
		</tr>	 
		<tr>
			<th><small style="color:#4d4d4d">Status</small></th><th>:</th><th><small>
					<#if claimStatus??>
						${claimStatus}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th><small style="color:#4d4d4d">Reason Code</small></th><th>:</th><th><small>
				<#if globalReasonCodeName??>
					${globalReasonCodeName}
				<#else>
				</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Pick-up Address</small></th><th>:</th><th><small>
					<#if site_address??>
						${site_address}
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
			<th><h6>Registered<br>Qty</h6></th>
			<th><h6>Unit Price<br>(<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Registered<br>Price(<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Disc<br>(<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Final Price<br>(<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Reason Code</h6></th>
		</tr>
	<#list claimTranLineItems as lineItem>
		<tr>
			<td style="text-align:left;">
				<#assign res='${lineItem_index}'>
				<#assign seqNo=res?number>
				<#assign seqNo++>
				<h6>${seqNo}.</h6>
			</td>&nbsp;&nbsp;
			
			<td style="text-align:left;"> <h6>${lineItem.itemId} <br> ${lineItem.deItmShrtRcpt}</h6></td>
			<td style="text-align:right;"> 
				<h6><#if lineItem.lineQntRtn??>
						${lineItem.lineQntRtn}
					<#else>
					</#if></h6> 
			</td>
			<td style="text-align:right;">
				<h6><#if lineItem.itmPrnPrc??>
						${lineItem.itmPrnPrc}
					<#else>
					</#if></h6> 
			</td>
			<#assign ds=0>
  			<#assign unitDscAmt = 0>
			<#if lineItem.claimTranDscItms??>
					<#list lineItem.claimTranDscItms as dscItems>
						<#assign ds= ds + dscItems.dscAmt>
						<#assign unitDscAmt = unitDscAmt + dscItems.unitDscAmt>
					</#list>
			<#else>		
			</#if>
			<td style="text-align:right;">
				<#if lineItem.ovrdPrc??>
					<#assign regPrice = lineItem.ovrdPrc>
					<h6>${regPrice}</h6>
				<#else>
					<#assign regPrice = lineItem.itmPrnPrc - unitDscAmt>
					<h6>${regPrice}</h6>		
				</#if>
			</td>
			<td style="text-align:right;">
				<#if ds??>
					<h6>${ds}</h6>
				<#else>		
				</#if>
			<td style="text-align:right;">
				<#if lineItem.lineQntRtn??>
					<h6>${regPrice * lineItem.lineQntRtn}</h6>
				<#else>		
				</#if>
			</td>
			<td style="text-align:right;">
				<#if globalReasonCodeName??>
					<h6>${globalReasonCodeName}</h6>
				<#else>		
				</#if>
			</td>
		</tr>
	</#list>
</table>
<br><br>

<table align="right" width="30%">
		<tr>
			<td><small>Sub Total</small></td><td>:</td><td style="text-align:right;"><small> <@s.property value="getText('global.currency')"/>&nbsp;&nbsp; 
				<#if dkartSlsTot??>
					${dkartSlsTot}
				<#else>
					<#assign x=0>
					${x}
				</#if></small>
			</td>
		</tr> 
		<tr>
			<td><small>Discount</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;
				<#if dkartDiscTot??>
					${dkartDiscTot}
				<#else>
					<#assign x=0>
					${x}
				</#if></small>
			</td>
		</tr>
		<tr>
			<td><small>Expenses</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;  
				<#if dkartExpenses??>
					${dkartExpenses}
				<#else>
					<#assign x=0>
					${x}
				</#if></small>
			</td>
		</tr>
		<tr>
			<td>Total Refund</td><td>:</td><td style="text-align:right;"> <@s.property value="getText('global.currency')"/>&nbsp;&nbsp;   
				<#if dkartNetTot??>
					${dkartNetTot}
				<#else>
					<#assign x=0>
					${x}
				</#if>
			</td>
		</tr>
</table>



