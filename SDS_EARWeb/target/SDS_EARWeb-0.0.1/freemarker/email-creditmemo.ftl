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
<td align="left">Customer Name :</td><td align="left"><strong>${customerName}</strong>
</tr>
<tr>
<td>Claim Id :</td><td align="left"><strong>${claimId} </strong>
</tr>
<tr>
<td>Claim Memo Id :</td><td align="left"><strong>${creditMemoId} </strong>
</tr>
<tr>
<td>Claim Memo Creation Date :</td><td align="left"><strong>${creditMemoCreationDate} </strong>
</tr>
<tr>
<td>Credit Memo Amount :</td><td align="left">QAR <strong>${creditMemoAmt} </strong>
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
<th>Accepted Qty</th>
<th>Accepted Price</th>
<th>Total</th>
</tr>
</thead>
<tbody>
<#list ClaimTranItemList as ClaimTranItem>
<tr>
<td>${ClaimTranItem?counter}.</td>
<td>${ClaimTranItem.getItemId()}</td>
<td>${ClaimTranItem.getDeItmShrtRcpt()}</td>
<td align="right">${ClaimTranItem.getWhReceiveQty()}</td>
<td align="right">${ClaimTranItem.getAccptClaimPrice()}</td>
<td align="right">${ClaimTranItem.getAccptClaimPrice() * ClaimTranItem.getWhReceiveQty()}</td>
</tr>
</#list>
</tbody>
</table>

</div>
</body>
</html> 