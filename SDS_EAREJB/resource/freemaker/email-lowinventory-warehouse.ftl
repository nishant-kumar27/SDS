<html>
	<body>
		<h3><u>Inventory Details</u></h3>
		<#list lowInventoryDetails as lowInvDet>
			Order Id: ${lowInvDet.orderId} 
			<table style="width:200px;">
				<tr>
					<td><u>#</u></td>
					<td><u>Item Id</u></td>
					<td><u>Inventory</u></td>
				</tr>
				<#list lowInvDet.inventoryDetailsList as invDet>
					<tr>
						<td>${invDet?counter}</td>
						<td>${invDet[0]}</td>
						<td>${invDet[1]}</td>
					</tr>
				</#list>
			</table>
			<br>
		</#list>
	</body>
</html>