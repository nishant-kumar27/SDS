<html>
<head><title> Claim Details </title>
<body>
<div>
<div>

<p>Below are the Claim Details: </p>
</div>
<div>
<table>
<tr>
<td align="left">Customer Id :</td><td align="left"><strong>${customerId}</strong>
</tr>
<tr>
<td>Customer Name :</td><td align="left"><strong>${customerName}</strong>
 </tr>
<tr>
<td>Claim Id :</td><td align="left"><strong>${claimId} </strong>
</tr>
<tr>
<td>Claim Date :</td><td align="left"><strong>${claimDate} </strong>
</tr>
<tr>
<td>Net Total :</td><td align="left">QAR <strong>${netTotal} </strong>
</tr>
</table>
</div>
<h4>Items Details :<h4>
</div>
<table border="1" style="width:800px">
<thead>
<tr bgcolor="#ADC2EE">
<th>#</th>
<th>Item Id</th>
<th>Item Desc</th>
<th>Registered Qty</th>
<th>Registered Price</th>
<th>Total</th>
</tr>
</thead>
<tbody>
<#list ClaimTranItemList as ClaimTranItem>
<tr>
<td>${ClaimTranItem?counter}.</td>
<td>${ClaimTranItem.getItemId()}</td>
<td>${ClaimTranItem.getDeItmShrtRcpt()}</td>
<td align="right">${ClaimTranItem.getLineQntRtn()}</td>
<td align="right">${ClaimTranItem.getItmPrnPrc()}</td>
<td align="right">${ClaimTranItem.getItmPrnPrc() * ClaimTranItem.getLineQntRtn()}</td>
</tr>
</#list>
</tbody>
</table>

</div>
</body>
</html> 