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
<td align="left">Customer Id:</td><td align="left"><strong>${customerId} </strong></td>
</tr>
<tr>
<td align="left">Customer Name:</td><td align="left"><strong>${customerName} </strong></td>
</tr>
<tr>
<td align="left">Order Id :</td><td align="left"><strong>${orderId} </strong></td>
</tr>
<tr>
<td align="left">Order Date :</td><td align="left"><strong>${orderDate} </strong></td>
</tr>
<tr>
<td align="left">Order Total:</td><td align="left">QAR <strong>${netTotal} </strong></td>
</tr>
</table>
</div>
</div>
</body>

</html>