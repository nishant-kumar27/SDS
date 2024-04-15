<html>
	<body>
		<h3><u>Invoice Details</u></h3>
		<table>
			<tr><td>Invoice No: </td><td>${invoice.arInvNum} </td></tr>
			<tr><td>Invoice Date: </td><td>${invoice.arInvDate?date} </td></tr>
			<tr><td>Invoice Amount: </td><td>${invoice.invAmount?string["0.##"]} </td></tr>
			<tr><td>Customer: </td><td>${invoice.customerSite.customerSitePK.custId} </td></tr>
			<tr><td>Order No: </td><td>${invoice.orderNum} </td></tr>
			<tr><td>Order Date: </td><td>${invoice.orderDate?date} </td></tr>
		</table>
	</body>
</html>