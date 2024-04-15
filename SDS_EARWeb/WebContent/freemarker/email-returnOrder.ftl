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
<td>Order Id :</td><td align="left"><strong>${orderId} </strong>
</tr>
<tr>
<td>Order Date :</td><td align="left"><strong>${orderDate} </strong>
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