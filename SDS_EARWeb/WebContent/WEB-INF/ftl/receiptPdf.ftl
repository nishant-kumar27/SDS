<style>
@page
{
	size: landscape;	<#-- To Display this PDF page in Landscape -->
	<#-- size: portrait; -->  	<#-- To Display this PDF page in Portrait -->
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

					 					 <center><h1 style="color: #226e71;">Receipt Details</h1></center>
<br>

<table width="100%">
		<tr>
			<th><small style="color:#4d4d4d">Receipt ID</small></th><th>:</th><th><small>
				<#if receipt.arPaymNum??>
					${receipt.arPaymNum}
				<#else>
				</#if>	
			</small></th>&nbsp;&nbsp;
			<th><small style="color:#4d4d4d">Payment Mode</small></th><th>:</th><th><small>
				<#if receipt.paymMode??>
					${receipt.paymMode}
				<#else>
				</#if>	
			</small></th>&nbsp;&nbsp;&nbsp;&nbsp;
			<th><small style="color:#4d4d4d">Customer ID</small></th><th>:</th><th><small>
				<#if receipt.custId??>
					${receipt.custId}
				<#else>
				</#if>	
			</small></th>
		<tr>
		</tr>	
			<th><small style="color:#4d4d4d">Payment Date</small></th><th>:</th><th><small>
				<#if receipt.arPaymDate??>
					<#setting date_format="dd-MMM-yyyy">
					<#setting locale="en_US">
					<#assign createdOn=receipt.arPaymDate> 
					${createdOn?datetime?string('dd-MMM-yyyy')}
				<#else>
				</#if>			
			</small></th>&nbsp;&nbsp;
			<th><small style="color:#4d4d4d">Payment Amount</small></th><th>:</th><th><small>
				<#if receipt.arPaymAmount??>
					${receipt.arPaymAmount}
				<#else>
				</#if>
			</small></th>&nbsp;&nbsp;&nbsp;&nbsp;
			<th>
			<small style="color:#4d4d4d">Customer Name</small></th><th>:</th><th><small>
					<#if custName??>
						${custName}
					<#else>
					</#if>
				</small>
			</th>
		</tr>	 
		<tr>
			<th><small style="color:#4d4d4d">Payment Currency</small></th><th>:</th><th><small>
			<#if receipt.currencyCode??>
					${receipt.currencyCode}
				<#else>
				</#if>
			</small></th>
		</tr>
</table>
<br><br>

					<center><h1 style="color: #226e71;">Applied Invoice Details</h1></center>
<table width="100%" id="itemTable">
		<tr style="background-color:#ADC2EE;">
			<th style="text-align:left;"><h6>#</h6></th>&nbsp;&nbsp;
			<th style="text-align:left;"><h6>Invoice No<h6></th>
			<th><h6>invoice Date</h6></th>
			<th><h6>Invoice Total<br>(<@s.property value="getText('global.currency')"/>) </h6></th>
			<th><h6>Order ID</h6></th>
			<th><h6>Order Date</h6></th>
		</tr>
	<#list invoice as inv>
		<tr>
			<td style="text-align:left;">
				<#assign res='${inv_index}'>
				<#assign seqNo=res?number>
				<#assign seqNo++>
				<h6>${seqNo}.</h6>
			</td>&nbsp;&nbsp;
			<td style="text-align:left;">
				<#if inv.invoiceId??>
					<h6>${inv.invoiceId}</h6>
				<#else>
				</#if>
			</td>
			<td>
				<#if inv.invoiceDate??>
					<#setting date_format="dd-MMM-yyyy">
					<#setting locale="en_US">
					<#assign createdOn=inv.invoiceDate> 
					<h6>${createdOn?datetime?string('dd-MMM-yyyy')}</h6>
				<#else>
				</#if>	
			</td>
			<td>
				<#if inv.invoiceAmount??>
					<h6>${inv.invoiceAmount}</h6>
				<#else>
				</#if>	
			</td>
			<td>
				<#if inv.id.orderId??>
					<h6>${inv.id.orderId}</h6>
				<#else>
				</#if>	
			</td>
			<td>
				<#if inv.id.dcDyOrd??>
					<h6>${inv.id.dcDyOrd}</h6>
				<#else>
				</#if>	
			</td>
		</tr>
	</#list>	
	</table>
