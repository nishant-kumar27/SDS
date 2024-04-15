<html>
<head><title>ViralPatel.net - FreeMarker Hello World</title>
<style>
#container {
   width: 500px;
   height: 37px;
}
#container1 {
   width: 250px;
   height: 37px;
}

#container img {
   width: 50%;
}

#info {
background-color :#35b0e9;
height: 60px;
line-height: 50px;
text-align: center;
}
#info1 {
background-color :#FFFFFF;
height: 60px;
line-height: 50px;
text-align: center;
}
</style>

<body bgcolor="#FFFFFF">
<div id="containe">
<div>
<p>Below are the Claim Details: </p>
</div>
<div>
<table>
<tr>
<td align="left">Customer Id:</td><td align="left"><strong>${customerId} </strong></td>
</tr>
<tr>
<td align="left">Customer Name:</td><td align="left"><strong>${customerName} </strong></td>
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
<th>Order Qty</th>
<th>Item Price</th>
<th>Item Total</th>
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