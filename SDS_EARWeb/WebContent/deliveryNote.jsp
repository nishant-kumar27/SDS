<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | Delivery Note</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css"
	type="text/css" />
<!-- <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css" -->
<!-- 	type="text/css" /> -->
<link rel="stylesheet" href="assets/css/ionicons.min.css"
	type="text/css" />
	<link rel="stylesheet" href="assets/css/jquery-ui.css">

<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<!-- <link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="assets/plugins/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="assets/css/popup.css"> -->
<link rel="stylesheet" href="assets/css/Customizations.css">
<!-- REQUIRED JS SCRIPTS -->

<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
<!-- jQuery 2.2.0 -->



<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
<%-- <style>
 @media print { 
     .table th { 
        background-color: #ddd !important; 
    } 
    
     h5 {page-break-before: always;}
    
}

.box {
	position: relative;
	border-radius: 3px;
	background: #ffffff;
	border-top: 3px solid #d2d6de;
	margin-bottom: 0px;
	width: 100%;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 0px;
}

.input-group-addon {
	padding: 3px;
}

.form-control {
	display: block;
	width: 100%;
	height: 25px;
	/* padding: 6px 12px; */
	padding: 0px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}

input, button, select, textarea {
	font-family: inherit;
	font-size: inherit;
	line-height: inherit;
}

button, select {
	text-transform: none;
}

button, input, optgroup, select, textarea {
	margin: 0;
	font: inherit;
	color: inherit;
}

* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

user agent stylesheet
keygen, select, select[size="0"], select[size="1"] {
	border-radius: 0px;
	border-color: rgb(169, 169, 169);
}

user agent stylesheet
select:not(:-internal-list-box)
{
overflow:visible!important;
}

user agent stylesheet
select {
	-webkit-appearance: menulist;
	box-sizing: border-box;
	align-items: center;
	border-image-source: initial;
	border-image-slice: initial;
	border-image-width: initial;
	border-image-outset: initial;
	border-image-repeat: initial;
	white-space: pre;
	-webkit-rtl-ordering: logical;
	color: black;
	background-color: white;
	cursor: default;
	border-width: 1px;
	border-style: solid;
	border-color: initial;
}

user agent stylesheet
keygen, select {
	border-radius: 5px;
}

user agent stylesheet
input, textarea, keygen, select, button {
	text-rendering: auto;
	color: initial;
	letter-spacing: normal;
	word-spacing: normal;
	text-transform: none;
	text-indent: 0px;
	text-shadow: none;
	display: inline-block;
	text-align: start;
	margin: 0em 0em 0em 0em;
	font: 13.3333px Arial;
}

user agent stylesheet
input, textarea, keygen, select, button, meter, progress {
	-webkit-writing-mode: horizontal-tb;
}

Inherited from table
table {
	border-spacing: 0;
	border-collapse: collapse;
}

user agent stylesheet
table {
	display: table;
	border-collapse: separate;
	border-spacing: 2px;
	border-color: grey;
}

Inherited from body.skin-blue.sidebar-mini
body {
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
}

body {
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
	font-weight: 400;
	overflow-x: hidden;
	overflow-y: auto;
}

body {
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size: 14px;
	line-height: 1.42857143;
	color: #333;
	background-color: #fff;
}

Inherited from html
html {
	font-size: 10px;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

html {
	font-family: sans-serif;
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
}

Pseudo ::before element
*:before, *:after {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.table-striped>tbody>tr:nth-of-type(odd) {
	background-color: #e4e4e4;
}

Pseudo ::after element
*:before, *:after {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

body {
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
}
</style>
<style>
/*
@media (max-width: 1200px) { 
    body    {
        font-size: 12px !important;
    }
        .section {
            font-size: 12px !important;
        }
    
}
*/
.table {
	margin: 0px;
	letter-spacing: 0px;
	word-spacing: 0px;
}

.btn {
	border-radius: 20px;
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
	border: 1px solid;
	background: linear-gradient(#1770c1, #073763 50%, #1770c1);
	color: #fff;
}

.btn:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.20), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
}
</style>
<style>
.radio {
	margin: 0px;
}
</style>
<style>
body {
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
}

.sidebar-menu .treeview-menu>li>a {
	font-size: 13px;
}
.col-center {
   float: none;
   margin-right: auto;
   margin-left: auto;
 }
        
        small{
            font-size:10px;
        }
        
</style> --%>

</head>

<body class="skin-blue sidebar-collapse">
	<div class="wrapper">

		<%-- <s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" /> --%>
		
	<div style="min-height: 595px;" class="content-wrapper">
   
	<div class="content">

	<div class="invoice" id="wrapper">

		<div class="row">
		    <div class="col-xs-3">
		    	<img alt="logo" src="assets/SDSlogo.png">
		    </div>
		    <div class="col-xs-3 text-right">
		    	<small style="font-size:9px;color:#8e8e8e;"><b>
		        <s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address3" /></s:i18n>
		        </b></small>
		    </div>
		    <div class="col-xs-3 text-right">
		    	<small style="font-size:9px;color:#8e8e8e;"><b>
				<s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address2" /></s:i18n>
				</b></small>
		    </div>
		    <div class="col-xs-3 text-right">
		    	<small style="text-align:right;font-size:9px;color:#8e8e8e;"><b>
		        <s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address1" /></s:i18n>
		        </b></small>
		    </div>
		</div>

		<div class="row">
		  <div class="col-xs-12 text-center">
		    <h2 style="color: #226e71;" >
				<u><s:property value="getText('van.cash.sales.invoice')"/></u>
		    </h2>
		  </div>
		</div>

		<div class="row invoice-info">
			<div class="col-xs-4 invoice-col">
				<table style="margin-bottom: 0px;" class="table table-bordered table-condensed">
					<tr>
						<td>
							<s:property	value="getText('invoice.no')" />: 
							<b><span id="InvoiceNo"><s:property	value="invDetail.arInvNum" /></span></b>
						</td>
					</tr>
					<tr>
						<td>
							<s:property	value="getText('invoice.date')" />: 
							<b><span id="invDate"><s:date name="invDetail.arInvDate" format="%{getText('format.date')}" /></span></b>
						</td>
					</tr>
					<tr>
						<td>
							<s:property value="getText('status.label')" />: 
							<b><span id="InvStatus">
								<s:if test="invDetail.invStatus=='0'">
									<s:property value="getText('closed_inv')" />
								</s:if>
								<s:elseif test="invDetail.invStatus=='1'">
									<s:property value="getText('open_inv')" />
								</s:elseif>
								<s:else>
									<s:property value="getText('unknown_inv')" />
								</s:else>
							</span></b>
						</td>
					</tr>
					<tr>
						<td>
							Payment Terms: 
							<b><span>
								<s:property	value="orders[0].customer.paymentTerms.payIn" /> 
								<s:property	value="getText('print.inv.dayslabel')" />
							</span></b>
						</td>
					</tr>
				</table>
			</div>

			<div class="col-xs-4 invoice-col">
				<table style="margin-bottom: 0px;" class="table table-bordered table-condensed">
					<tr>
						<td>
							<s:property	value="getText('customer.id')" /> : 
							<b><span id="customerId">
								<s:property value="orders[0].customer.customerHeader.customerHeaderPK.custId" />
							</span></b>
						</td>
					</tr>
					<tr>
						<td>
							<s:property value="getText('customer.name')" />: 
							<b><span id="CustomerName">
								<s:property	value="orders[0].customer.customerHeader.ctNm" />
							</span></b>
						</td>
					</tr>
					<tr>
						<td>
							<s:property	value="getText('LPO.label')" />: 
							<b><span id="LPO">
								<s:property value="orders[0].ordTranSum.custLpoNum" />
							</span></b>
						</td>
					</tr>
					<tr>
						<td>
							<s:property	value="getText('lpo.date')" />: 
							<b><span id="LPODate">
								<s:date name="orders[0].ordTranSum.custLpoDate"	format="%{getText('format.date')}" />
							</span></b>
						</td>
					</tr>
				</table>
			</div>

			<div class="col-xs-4 invoice-col">
				<table style="margin-bottom: 0px;" class="table table-bordered table-condensed">
					<tr>
						<td>
							<s:property value="getText('order.id')" />: 
							<b><span id="OrderID">
								<s:property	value="invDetail.orderNum" />
							</span></b>
						</td>
					</tr>
					<tr>
						<td>
							<s:property	value="getText('order.date')" />: 
							<b><span id="OrderDate">
								<s:date	name="invDetail.orderDate" format="%{getText('format.date')}" />
							</span></b>
						</td>
					</tr>
				</table>
			</div>

			<div class="col-xs-12">
				<table class="table table-bordered table-condensed">
					<tr>
						<td>Delivery Address: <b><s:property value="orders[0].ctDvrInf" /></b></td>
					</tr>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12 table-responsive">
				<table class="table table-condensed table-bordered">
					<thead>
						<tr style="background-color: #ADC2EE;">
							<th class="text-center"><s:property value="getText('table.head.SNo')" /></th>
							<th class="text-center"><s:property value="getText('print.inv.plu')" /></th>
							<th class="text-center"><s:property value="getText('print.inv.desc')" /></th>
							<th class="text-center"><s:property value="getText('table.head.qty')" /></th>
						</tr>
					</thead>

					<tbody id="orderDetail">
						<s:set var="totalItemQty" value="0" />
						<s:set var="count" value="%{0}"></s:set>
						<s:iterator value="orders[0].ordTranLineItems" status="itStatus">
							<s:set var="totalItemQty" value="%{#totalItemQty + lineQnt}" />
							<s:if test="%{itmTy!=2}">
								<tr>
									<s:set var="count" value="%{#count+1}"></s:set>
									<td class="text-right"><s:property value="count" /></td>
									<td><s:property value="itemId" /></td>
									<td><s:property value="deItmShrtRcpt" /></td>

									<td class="text-right"><s:property value="lineQnt" /></td>
								</tr>
							</s:if>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-4 pull-right">
				<!-- <div class="table-responsive"> -->
					<table class="table table-condensed table-bordered">
						<tbody>
							<tr>
								<td style="width: 60%">Total Quantity:</td>
								<td class="text-right"><b><s:property value="%{#totalItemQty}" /></b></td>
							</tr>
							<tr>
								<td>Total Items:</td>
								<td class="text-right"><b><s:property value="orders[0].ordTranLineItems.size()" /></b></td>
							</tr>
						</tbody>
					</table>
				<!-- </div> -->
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-12 table-responsive">
				<table class="table table-bordered">
					<tr style="height: 50px;">
						<td style="width: 40%; vertical-align: middle;">Sales Agent Signature:</td>
						<td style="width: 40%; vertical-align: middle;">Sales Agent Name: 
							<b><s:property value="salesAgntNme" /></b>
						</td>
						<td style="vertical-align: middle;">Date:</td>
					</tr>
				</table>
			</div>
			<div class="col-xs-12 table-responsive">
				<table class="table table-bordered">
					<tr style="height: 50px;">
						<td style="width: 40%; vertical-align: middle;">Customer Signature:</td>
						<td style="width: 40%; vertical-align: middle;">Customer Name:</td>
						<td style="vertical-align: middle;">Date:</td>
					</tr>
					<tr style="height: 50px;">
						<td colspan="3" style="vertical-align: middle;">Customer Comments:</td>
					</tr>
				</table>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-12 table-responsive">
				<h3 style="color: #226e71; text-align: center;">
					<s:property value="getText('print.inv.serialnoheading')" />
				</h3>
				<span style="padding:4px;">
					<s:property value="getText('order.id')" />: 
					<b><s:property value="invDetail.orderNum" /></b>
				</span>
				<table class="table table-condensed table-bordered">
					<thead>
						<tr style="background-color: #ADC2EE;">
							<th class="text-center" style="width: 10%;"><s:property value="getText('table.head.SNo')" /></th>
							<th class="text-center" style="width: 20%;"><s:property value="getText('print.inv.itemid')" /></th>
							<th class="text-center"><s:property value="getText('print.inv.serialno')" /></th>
						</tr>
					</thead>
					<tbody>
						<s:set var="count" value="%{0}"></s:set>
						<s:if test="%{slnoMap!=null && slnoMap.size()>0}">
							<s:iterator value="slnoMap" status="rowstatus">
								<tr>
									<td class="text-right"><s:property value="#rowstatus.count" /></td>
									<td><s:property value="key" /></td>
									<td><s:property value="value" /></td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr><td colspan="3" class="text-center"><i>None</i></td></tr>
						</s:else>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</div>
</div>
<script>	 
	function  windowPrint(){
		window.print();
	}
</script>	

<s:if test="%{download == true}">
	<form name="downloadLPO" action="downloadLPO" method="post">
		<input type="hidden" name="orderID" value="<s:property value="invDetail.orderNum"/>">
	<form>
	
	<script src="custom/pdf/jspdf.debug.js"></script>
	<script src="custom/pdf/html2canvas.js"></script>
	<script src="custom/pdf/html2pdf.js"></script>
	<script type="text/javascript">
		$(function(){
			var downloadLPO = function(pdf){
				var lponum = "<s:property value="orders[0].ordTranLpo.lpoSlipType"/>";
				if(lponum){
					setTimeout(function() {document.downloadLPO.submit();}, 1000);
				}
				
				setTimeout(function(){
					$(document).on('mouseenter', 'body',  function(e){
						console.log('close window');
        				window.close();
					});
				}, 2000);
				
			}
		
			var element = $('#wrapper').html();
			var pdf = html2pdf(element, {
				margin:       0.2,
				filename:     'DeliveryNote_${invDetail.orderNum}.pdf',
				image:        { type: 'jpeg', quality: 0.99 },
				html2canvas:  { dpi: 300, letterRendering: true },
				jsPDF:        { unit: 'in', format: 'a4', orientation: 'landscape' },
				callback: downloadLPO
			});
		});
	</script>
</s:if>
<s:else>
	<script type="text/javascript">
		$(document).ready(function() {
		window.focus();
		var lponum = "<s:property value="orders[0].ordTranLpo.lpoSlipType"/>";
		if(lponum){
			window.print();
			window.location='PrintLPODetails?orderID=<s:property value="invDetail.orderNum"/>';
		}else{
			window.print();
			window.close();
		}
		});
	</script> 
</s:else>
</body>
</html>