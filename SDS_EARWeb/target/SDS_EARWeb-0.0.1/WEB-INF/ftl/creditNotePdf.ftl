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
					 <center><h1 style="color: #226e71;">Credit Memo Details</h1></center>
<br>

<table width="100%">
		<tr>
			<th>
			<small style="color:#4d4d4d">Credit Memo ID</small></th><th>:</th><th><small>
					<#if bean.crediMemoId??>
						${bean.crediMemoId}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<th>
			<small style="color:#4d4d4d">Customer ID</small></th><th>:</th><th><small>
					<#if custId??>
						${custId}
					<#else>
					</#if>
				</small>
			</th>
		</tr>
		<tr>	
			<th>
			<small style="color:#4d4d4d">Credit Memo Creation Date</small></th><th>:</th><th><small>
					<#if bean.crMemoDate??>
						${bean.crMemoDate}
					<#else>
					</#if>
				</small>
			</th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
			<th>
			<small style="color:#4d4d4d">Credit Memo Amount</small></th><th>:</th><th><small>
					<#if bean.crMemoAmount??>
						${bean.crMemoAmount}
					<#else>
					</#if>
				</small></th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<th>
			<small style="color:#4d4d4d">Claim ID</small></th><th>:</th><th><small>
					<#if bean.claimId??>
						${bean.claimId}
					<#else>
					</#if>
				</small></th>
		</tr>
</table>
<br><br>

<table width="100%" id="itemTable">
		<tr style="background-color:#ADC2EE;">
			<th style="text-align:left;"><h6>#</h6></th>&nbsp;&nbsp;
			<th style="text-align:left;"><h6>Item<h6></th>
			<th><h6>Order<br>Qty</h6></th>
			<th><h6>Price (<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Tax (<@s.property value="getText('global.currency')"/>)</h6></th>
			<th><h6>Sales Agent</h6></th>
			<th><h6>Total (<@s.property value="getText('global.currency')"/>)</h6></th>
		</tr>
	<#list orderTranLineItems as lineItem>
		<tr>
			<td style="text-align:left;">
				<#assign res='${lineItem_index}'>
				<#assign seqNo=res?number>
				<#assign seqNo++>
				<h6>${seqNo}.</h6>
			</td>&nbsp;&nbsp;
			
			<td style="text-align:left;"><h6>${lineItem.itemId} <br> ${lineItem.deItmShrtRcpt}</h6></td>
			<td>
			<#if lineItem.lineQnt??>
  					<h6>${lineItem.lineQnt}</h6>
  				<#else>
  					<#assign x = 0>
  					<h6>${x}</h6>		
				</#if>
				
			<td>
				<#if lineItem.itmPrnPrc??>
					<h6>${lineItem.itmPrnPrc}</h6>
				<#else>
				</#if>	
			</td>
			<td>
				<#if lineItem.vatLnItmRtn??>
					<h6>${lineItem.vatLnItmRtn}</h6>
				<#else>
					<#assign tax=0>
					<h6>${tax}</h6>
				</#if>
			</td>
			<td>
				<#assign mappedArray = []>
				<#list list as l>
    			<#assign mappedArray= mappedArray + ["<p>${l}</p>"]>
				</#list>
				<#assign final=seqNo-1>
				<h6>${mappedArray[final]}</h6>
			</td>
			<td>
				<#if lineItem.extnDscLnItm??>
  					<h6>${lineItem.extnDscLnItm}</h6>
				<#else>
  					<#assign x = 0>
  					<h6>${x}</h6>			
  				</#if>
  			</td>	
		</tr>
	</#list>	
</table>
<br><br>

<table align="right" width="30%">
		<tr>
			<td><small>Sub Total</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;  
			<#if claimTransaction.ordTranSum.dkartSlsTot??>
				${claimTransaction.ordTranSum.dkartSlsTot}</td>
			<#else>
			</#if></small>
			</td>	
		</tr>
		<tr>
			<td><small>Discount</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;  
			<#if claimTransaction.ordTranSum.dkartDscTot??>
				${claimTransaction.ordTranSum.dkartDscTot}</td>
			<#else>
				<#assign x=0>
					${x}
			</#if></small>
			</td>	
		</tr>
		<tr>
			<td><small>Total Tax</small></td><td>:</td><td style="text-align:right;"><small><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;  
				<#if claimTransaction.ordTranSum.dkartTaxTot??>
					${claimTransaction.ordTranSum.dkartTaxTot}
				<#else>
					<#assign x=0>
						${x}
				</#if></small>	
				</td>
		</tr>
		<tr>
			<td>Total</td><td>:</td><td style="text-align:right;"><@s.property value="getText('global.currency')"/>&nbsp;&nbsp;  
				<#if claimTransaction.ordTranSum.dkartNetTot??>
					${claimTransaction.ordTranSum.dkartNetTot}
				<#else>
				</#if>
			</td>
		</tr>
</table>
