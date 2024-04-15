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

					 <center><h1 style="color: #226e71;">Claim Details</h1></center>
<br>

<table width="100%">
		<tr>
			<th>
				<small style="color:#4d4d4d">Claim ID</small></th><th>:</th><th><small>
					<#if claimID??>
						${claimID}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Reason COde</small></th><th>:</th><th><small>
					<#if globalReasonCodeName??>
						${globalReasonCodeName}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Customer ID</small></th><th>:</th><th><small>
					<#if custId??>
						${custId}
					<#else>
					</#if>	
				</small>
			</th>	
		<tr>
		</tr>	
			<th>
				<small style="color:#4d4d4d">Claim Date</small></th><th>:</th><th><small>
					<#if claimedDate??>
						<#setting date_format="dd-MMM-yyyy">
						<#setting locale="en_US">
						<#assign createdOn=claimedDate> 
						${createdOn?datetime?string('dd-MMM-yyyy')}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Status</small></th><th>:</th><th><small>
					New
				</small>
			</th>&nbsp;&nbsp;&nbsp;
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
			<th>
				<small style="color:#4d4d4d"> </small></th><th> </th><th><small>
					
					
				</small>
			</th>&nbsp;&nbsp;&nbsp;
			<th>
				<small style="color:#4d4d4d">Sales Agent</small></th><th>:</th><th><small>
					<#if salesAgentName??>
						${salesAgentName}
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
			
			<th><h6>Qty To Return</h6></th>
			<th><h6>Price(<@s.property value="getText('global.currency')"/>)</h6></th>
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
			
			<td style="text-align:left;"><h6>${lineItem.itemId} <br> ${lineItem.deItmShrtRcpt}</h6></td>
			
			<td> 
				<#if lineItem.lineQntRtn??>
  					<h6>${lineItem.lineQntRtn}</h6>
				<#elseif lineItem.lineQnt??>
  					<h6>${lineItem.lineQnt}</h6>
  				<#else>
  					<#assign x = 0>
  					<h6>${x}</h6>		
				</#if>
			</td>
			
			<td> 
				<#if lineItem.ovrdPrc??>
  					<h6>${lineItem.ovrdPrc}</h6>
				<#elseif lineItem.itmPrnPrc??>
  					<h6>${lineItem.itmPrnPrc}</h6>
  				<#else>
  					<#assign x = 0>
  					<h6>${x}</h6>		
				</#if>
			</td>
			
			<td>
				<#if lineItem.extnLnItmRtn??>
  					<h6>${lineItem.extnLnItmRtn}</h6>
				<#else>
  				</#if>
			</td>
			<td>
				<#if lineItem.rcRtnMr??>
					<h6>${returnReasonCodeMap[lineItem.rcRtnMr]}</h6>
				<#else>
				</#if>		
			</td>
		</tr>
	</#list>	
	</table>
<br><br>

<table align="right" width="30%">
		<tr>
			<td><small>Sub Total</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;
					<#if dkartSlsTot??>
						${dkartSlsTot}
					<#else>
					</#if>	
				</small>
			</td>
		</tr>
		<tr>
			<td><small>Expenses</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;   
					<#if dkartExpenses??>
						${dkartExpenses}
					<#else>
						<#assign x=0>
						${x}
					</#if>	
				</small>
			</td>
		</tr>
		<tr>
			<td>Total Refund</td><td>:</td><td style="text-align:right;"><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;
					<#if dkartNetTot??>
						${dkartNetTot}
					<#else>
					</#if>	
			</td>
		</tr>
</table>


