<html>
<head><title>Receipt Email</title>
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
<table>
<tr>
<h3>Thank you for your order!</h3>
</tr>
<tr><p>
Here's a summary of your purchase. </p>
</tr>
</table>
<div>
<table>
<tr>
<td align="left">Customer Id:</td><td align="left"><strong>${customerID} </strong></td>
</tr>
<tr>
<td align="left">Customer Name:</td><td align="left"><strong>${customerName} </strong></td>
</tr>
<tr>
<td align="left">Receipt Id:</td><td align="left"><strong>${ReceiptID} </strong></td>
</tr>
<tr>
<td align="left">Payment Amount:</td><td align="left"><strong>${PaymentAmount} </strong></td>
</tr>
<tr>
<td align="left">Payment Date:</td><td align="left"><strong>${PaymentDate} </strong></td>
</tr>
</table>
<div>
<h4>Items Details :<h4>
</div>
<table border="1" style="width:800px">
<thead>
<tr bgcolor="#ADC2EE">
<th>#</th>
<th>Invoice No</th>
<th>Invoice Date</th>
<th>Invoice Amt</th>
<th>Order Id</th>
<th>Order Date</th>
</tr>
</thead>
<tbody>
<#list RisplDkArPaym as ArPaym>
<tr>
 <td>${ArPaym?counter}.</td>
<td>${ArPaym.getId().getArInvNum()}</td>
<td>${InvoiceDate}</td>
<td align="right">${OrderDetailsWithInvoice.getInvoiceAmount()}</td>
<td align="right">
${OrderDetailsWithInvoice.getId().getOrderId()}</td>
<td align="right">${OrderDate}</td>
</tr>
</#list>
</tbody>
</table>
</div>
</div>
</body>
</html>