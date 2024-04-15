<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | Order Details</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
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
<!-- jQuery 2.2.0 -->
<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>


<%-- <style type="text/css">
.mbg-blue {
	background-color: #00c0ef !important;
}
</style>
<style>
 @media print { 
     .table th { 
        background-color: #ddd !important; 
    } 
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
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="skin-blue sidebar-collapse">
	<div id="mainDiv" class="wrapper">

<%-- 
		<s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" /> --%>

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">

			<!-- Main content -->
			<section class="content">
					<!-- Your Page Content Here -->
					<section class="order">
						<!-- title row -->
					<div class="row">
						<div class="col-md-3 col-xs-3">
							<b><span class="logo-lg pull-left "><img alt="logo"
									src="assets/SDSlogo.png"></span> <span class="logo-mini"></span></b>
						</div>
						<div class="col-md-3 col-xs-3">
							<small class="pull-left  "
								style="font-size: 9px; color: #8e8e8e;"><b> <s:i18n
										name="rispl.print.printText">
										<s:text name="salesInvoice.Address3" />
									</s:i18n>
							</b></small>
						</div>
						<div class="col-md-3 col-xs-3">
							<small class="pull-right" style="font-size: 9px; color: #8e8e8e;"><b>
									<s:i18n name="rispl.print.printText">
										<s:text name="salesInvoice.Address2" />
									</s:i18n>
							</b></small>
						</div>
						<div class="col-md-3 col-xs-3">
							<small class="pull-right"
								style="text-align: right; font-size: 9px; color: #8e8e8e;"><b>
									<s:i18n name="rispl.print.printText">
										<s:text name="salesInvoice.Address1" />
									</s:i18n>
							</b></small>
						</div>
					</div>
					<br /> <br /> <br />
					<div class="row">
						
							<!-- /.col -->
						</div>
						<!-- info row -->
						<div class="row invoice-info">
							<div class="col-sm-4 invoice-col">
								<table style="width: 100%;" class="table">
									<tr>
										<td style="text-align: right; width: 42%;"><s:property
												value="getText('order.id')" /> :</b></td>
										<td><b><span id="ordId"><s:property value="order.id.orderId" /></span></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('order.date')" /> :</b></td>
										<td><b><s:date name="order.id.orderDate"
													format="%{getText('format.date')}" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right; "><s:property
												value="getText('effective.date')" /> :</b></td>
										<td><b><s:date name="order.effectiveDate"
													format="%{getText('format.date')}" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('status.label')" /> :</b></td>
										<td><b><s:property value="order.status" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('shipping.address')" /> :</b></td>
										<td style="word-break: break-word;"><address>
												<b> <s:property value="ord_tran_header.ctDvrInf" />
												</b>
											</address></td>
									</tr>
								</table>
							</div>
							<!-- /.col -->
							<div class="col-sm-4 invoice-col">
								<table style="width: 100%;" class="table">
									<tr>
										<td style="text-align: right;  width: 42%;"><s:property
												value="getText('customer.id')" /> :</b></td>
										<td><b><s:property
													value="customer.customerHeaderPK.custId" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('customer.name')" /> :</b></td>
										<td><b><s:property value="customer.ctNm" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('customer.segment')" /> :</b></td>
										<td><b><s:property value="cust_seg" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('LPO.label')" /> :</b></td>
										<td><b><s:property value="ord_tran_sum.custLpoNum" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('lpo.date')" /> :</b></td>
										<td><b><s:date name="ord_tran_sum.custLpoDate"
													format="%{getText('format.date')}" /></b></td>
									</tr>
	
	
								</table>
							</div>
							<!-- /.col -->
							<div class="col-sm-4 invoice-col">
								<table style="width: 100%;" class="table">
									
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('shipment.method')" /> :</b></td>
										<td><b> <s:iterator
													value="ord_tran_header.getOrdTranLineItems()"
													status="itStatus">
													<s:if test="itmTy.toString().equalsIgnoreCase('2')">
														<s:property value="deItmShrtRcpt" />
													</s:if>
												</s:iterator></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('sales.agent')" /> :</b></td>
										<td style="word-break: break-word;"><b><s:property value="order.salesAgent" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('comments.label')" /> :</b></td>
										<td><b><s:property value="order_comment" /></b></td> <%-- ord_tran_header.ctDvrInfoIns --%>
									</tr>
								</table>
							</div>
							<!-- /.col -->
	
						</div>
						<!-- /.row -->
						<div class="row">
							<s:if
								test="%{errorMessage!=null&&!errorMessage.equalsIgnoreCase('')}">
								<ul class="bg-danger text-danger">
									<li><s:actionerror /> <s:property value="errorMessage" />
										</span></li>
								</ul>
							</s:if>
						</div>
	
						<!-- Table row -->
						<div class="row">
							<div class="col-xs-12 table-responsive">
								<table class="table table-striped" style="margin-bottom: 2px;">
									<thead>
										<tr style="background-color: #ADC2EE;">
											<th><s:property value="getText('table.head.SNo')" /></th>
											<th><s:property value="getText('table.head.item')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('order.qty')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('table.head.vpn')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('delivered.qty')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('table.head.unitprice')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('order.disc.amt')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('table.head.tax')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('table.head.total')" /> (<s:property
													value="getText('global.currency')" />)</th>
	
										</tr>
									</thead>
									<tbody>
										<s:set var="totalItemQty" value="0" />
										<s:iterator value="order_items" status="itStatus">
											<tr>
												<s:set var="totalItemQty" value="%{#totalItemQty+lineQnt}" />
												<td><s:property value="#itStatus.count"/>.<s:set
														var="bagged_qty" value="#itStatus.count" /></td>
												<td><s:property value="itemId" /><br> <s:property
															value="deItmShrtRcpt" /></td>
												<td style="text-align: right;"><s:property
														value="lineQnt" /></td>
												<td style="text-align: right;"><s:property
														value="registryId" /></td>
												<s:set var="deliveredQty" value="" />
												<s:if test="lineQnt!=null && showDeliveredQuantity">
													<s:set var="deliveredQty" value="lineQnt" />
												</s:if>
												<s:else>
													<s:set var="deliveredQty" value="0" />
												</s:else>
												<td style="text-align: right;"><s:property
														value="deliveredQty" /></td>
	
												<s:set var="itemPrc" value="" />
												<s:if test="overRidePrice!=null">
													<s:set var="itemPrc" value="overRidePrice" />
												</s:if>
												<s:else>
													<s:set var="itemPrc" value="itmPrnPrc" />
												</s:else>
												<td style="text-align: right;"><s:property
														value="%{getText('format.currency.args',{#itemPrc})}" /></td>
												<s:if test="flItmDsc==0">
													<td style="text-align: right;"><s:property
															value="%{getText('format.currency.args',{extnLnItmRtn-extnDscLnItm})}" /></td>
												</s:if>
												<s:else>
													<td style="text-align: right;"><s:property value="%{0}" /></td>
												</s:else>
												<td style="text-align: right;"><s:property
														value="%{getText('format.currency.args',{vatLnItmRtn})}" /></td>
												<td style="text-align: right;"><s:property
														value="%{getText('format.currency.args',{extnDscLnItm})}" /></td>
											</tr>
	
										</s:iterator>
									</tbody>
								</table>
								<div class="progress" style="height: 2px;">
									<div class="progress-bar"
										style="width: 100%; background-color: #ADC2EE;"></div>
								</div>
							</div>
							<!-- /.col -->
						</div>
	
						<!-- /.row -->
	
						<div class="row">
							<!-- /.col -->
							<div class="col-xs-4  pull-right">
									<table class="table table-condensed table-bordered">
										<tbody>
											<tr>
												<td style="text-align: right;"><s:property
														value="getText('no.of.items')" /> :</td>
												<td style="text-align: right;"><b> <s:property
															value="ord_tran_header.getOrdTranLineItems().size()" /></b></td>
											</tr>
											<tr>
												<td style="text-align: right;"><s:property
														value="getText('total.qty')" /> :</td>
												<td style="text-align: right;"><b><s:text
															name="%{#totalItemQty}"></s:text></b></td>
											</tr>
											<tr>
												<td style="text-align: right;" style="width:50%"><s:property
														value="getText('subtotal.label')" /> :</td>
												<td style="text-align: right;"><s:property
														value="getText('global.currency')" />&nbsp;&nbsp;<b><s:property
															value="%{getText('format.currency.args',{ord_tran_sum.dkartSlsTot})}" /></td>
											</tr>
											<tr>
												<td style="text-align: right;"><s:property
														value="getText('discount.label')" /> :</td>
												<td style="text-align: right;"><b><s:property
															value="%{getText('format.currency.args',{ord_tran_sum.dkartDscTot})}" /></td>
											</tr>
											<tr>
												<td style="text-align: right;"><s:property
														value="getText('total.tax')" /> :</td>
												<td style="text-align: right;"><b><s:property
															value="%{getText('format.currency.args',{ord_tran_sum.dkartTaxTot})}" /></td>
											</tr>
											<tr style="font-size: 18px;">
												<td style="text-align: right;"><s:property
														value="getText('net.total')" /> :</td>
												<td style="text-align: right;"><s:property
														value="getText('global.currency')" />&nbsp;&nbsp;<b><s:property
															value="%{getText('format.currency.args',{ord_tran_sum.dkartNetTot})}" /></b></td>
											</tr>
										</tbody>
									</table>
								
							</div>
							<!-- /.col -->
						</div>
						<!-- /.row -->
	
						<!-- this row will not appear when printing -->
						<div class="row">
							<div class="col-xs-12"></div>
						</div>
					</section>
			</section>
			
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->

		<!-- /.control-sidebar -->

	</div>

	<!-- To show confirm cancel model -->
	
	<!-- End of model -->

	<!-- ./wrapper -->

	<!-- ChartJS 1.0.1 -->
	
<s:if test="%{download == true}">

	<form name="downloadLPO" action="downloadLPO" method="post">
		<input type="hidden" name="orderID" value="<s:property value="order.id.orderId"/>">
	<form>
	
	<script src="custom/pdf/jspdf.debug.js"></script>
	<script src="custom/pdf/html2canvas.js"></script>
	<script src="custom/pdf/html2pdf.js"></script>
	<script type="text/javascript">
		$(function() {
			var downloadLPO = function(pdf){
				var lpo = "${ord_tran_header.ordTranLpo.lpoSlipType}";
				if(lpo){
					setTimeout(() => {document.downloadLPO.submit();}, 1000);
				}
				
				setTimeout(() => {
					$(document).on('mouseenter', 'body',  function(e){
						console.log('close window');
        				window.close();
					});
				}, 1000);
			}
			
			var element = $('.order').html();
			var pdf = html2pdf(element, {
				margin:       0.2,
				filename:     'Order_${order.id.orderId}.pdf',
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
		var orderId = "<s:property value="ord_tran_header.ordTranLpo.lpoSlipType"/>";
		if(orderId){
			window.print();
			window.location='PrintLPODetails?orderID=<s:property value="order.id.orderId"/>';
		}else{
			window.print();
			window.close();
		}
		});
	</script> 
</s:else>

</body>
</html>