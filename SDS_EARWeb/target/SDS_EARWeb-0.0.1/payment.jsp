
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", -1);
	response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS |Payment</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<!-- Ionicons -->
<!-- Theme style -->
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="assets/css/Customizations.css">

<script src="Starter_files/jQuery-2.js"></script>
<script src="Starter_files/bootstrap.js"></script>
<script src="Starter_files/app.js"></script>
<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>

<style>
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

user



 



agent



 



stylesheet








select






:not



 



(
:-internal-list-box



 



)
{
overflow






:



 



visible



 



!
important






;
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

.table-striped>tbody>tr:nth-of-type(odd) {
	background-color: #e4e4e4;
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
.table {
	margin: 0px;
	letter-spacing: 0px;
	word-spacing: 0px;
}

.btn {
	border-radius: 20px;
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
	width: 100px;
	margin-top: 0px;
	background: #3d85c6;
	background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
	background: linear-gradient(#1770c1, #073763 50%, #1770c1);
	color: #fff;
	border: 1px solid;
}

.btn:hover {
	border-radius: 20px;
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
	width: 100px;
	margin-top: 0px;
	background: #3d85c6;
	background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
	background: linear-gradient(#1770c1, #073763 50%, #1770c1);
	color: #fff;
	border: 1px solid;
}

.dropdown-menu>li>a {
	color: #f5f5f5;
	background-color: #656a6b;
}
</style>
<style>
.sidebar-menu .treeview-menu>li>a {
	font-size: 13px;
}

.delete {
	
}

body {
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
}

/* to make the upload slip as link */
.fileUpload {
    position: relative;
    overflow: hidden;
    margin: 2px;
}
.fileUpload input.upload {
    position: absolute;
    top: 0;
    right: 0;
    margin: 0;
    padding: 0;
	font-size: 20px;
	cursor: pointer;
    opacity: 0;
    filter: alpha(opacity=0);
}
</style>
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
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" />

		<!-- Main Header -->


		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">

			<!-- Main content -->
			<section class="content">

				<!-- Your Page Content Here -->

				<div class="row">
					<div class="col-md-12">
						<div class="box">
							<small class="pull-right"
								style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><b><s:property value="getText('payment.details.help_text')"/></b></small>

							<div class="box-header" style="color: #226e71;">
								<h3><s:property value="getText('payment.details')"/></h3>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-md-4">
										<table style="width: 100%;" class="table">
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('customer.id')"/>:</b></td>
												<td><b><s:property
															value="payment_details.customerId" /></b></td>
											</tr>

											<tr>

												<s:if test="payment_details.customerInvoiceId==null">
													<td style="text-align: right; width: 50%;"><s:property value="getText('no.of.Pending_Inv')"/>:</b>
													</td>
													<td><b><s:property value="noOfInvoices" /></b></td>
											</tr>
											</s:if>
											<s:else>
												<td style="text-align: right; width: 50%;"><s:property value="getText('invoice.no')"/>:</b></td>
												<td><b><s:property value="payment_details.customerInvoiceId" /></b></td>
												</tr>
											</s:else>

										</table>
									</div>
									<div class="col-md-4">
										<table style="width: 100%;" class="table">
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('customer.name')"/>:</b>
												</td>
												<td><b><s:property
															value="payment_details.customerName" /></b></td>
											</tr>
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('total.pending.amt')"/>:</b>
												</td>
												<td><s:text name="global.currency" /> <b><s:property value="pendingAmount" /></b></td>
											</tr>
										</table>
									</div>
									<div class="col-md-4">
										<table style="width: 100%;" class="table">
											<tr>
												<td style="text-align: right; width: 50%;"><s:property value="getText('customer.segment')"/>:</b>
												</td>
												<td><b><s:property
															value="payment_details.customerSegmentationId" /></b></td>
											</tr>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<h3 style="text-align: center">
													 <%-- <s:if test="hasActionErrors()">
													   <div class="errors">
													      <s:actionerror/>
													   </div>
													</s:if>  --%>
											<s:property value="getText('amount.being.paid')"/> :
											<th id="amtBeingPaid"><s:text name="global.currency" />
												<s:property value="amtBeingPaid" /></th>
										</h3>
										<!-- <h3 style="text-align:center">Amount Being Paid : <label id="amtBeingPaid">QAR 0</label></h3>  -->
									</div>
								</div>
								<br>
								<h4><s:property value="getText('payment.method')"/></h4>
								<div class="row">
									<div class="col-md-2">
										<ul class="nav nav-pills nav-stacked" id="tenderType">
											<li class="active"><a data-toggle="pill" href="#menu1"><s:property value="getText('tender.cash')"/></a></li>
											<s:if test="payment_details.customerInvoiceId!=null">
												<li><a data-toggle="pill" href="#menu2"><s:property value="getText('tender.cheque')"/></a></li>
											</s:if>
											<li><a data-toggle="pill" href="#menu3"><s:property value="getText('tender.vocher')"/></a></li>
											<li><a data-toggle="pill" href="#menu4"><s:property value="getText('tender.coupon')"/></a></li>
										</ul>
									</div>
									<div class="col-md-10">
										<div class="tab-content"
											style="min-height: 200px; overflow-y: hidden;">
											<div id="menu1" class="tab-pane fade active in">
												<s:form id="chequeFormData" method="post"
													enctype="multipart/form-data" action="addPayment">
													<table style="max-width: 700px;" class="table">

														<tr style="text-align: right;" id="">
															<td><s:property value="getText('payment.cash.amount')"/><label style="color: red;">*</label></td>
															<td><input id="cashAmount"
																style="max-width: 50%; margin-left: 10%;" type="number"
																class="form-control" name="tender_line.tenderAmount"></td>
														</tr>
													</table>
											</div>
											<div id="menu2" class="tab-pane fade">
												<table style="maxs-width: 1000px;" class="table">


													<tr style="text-align: right;">
														<th style="width: 20%;"><s:property value="getText('customer.details')"/></th>
														<td></td>
														<th style="width: 20%;">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="getText('salam.details')"/></th>
														<td></td>
													</tr>
													<tr style="text-align: right;">
														<td style="width: 20%;"><s:property value="getText('payment.cheque.customerBankName')"/><label
															style="color: red;">*</label></td>
														<td><input id="customerBankName"
															style="max-width: 80%; margin-left: 10%;" type="text"
															class="form-control" name="tender_line.customerBankName"></td>
														<td style="width: 20%;"><s:property value="getText('payment.cheque.depositBankname')"/><label
															style="color: red;">*</label></td>
														<td><input id="depositBankName"
															style="max-width: 80%; margin-left: 10%;" type="text"
															class="form-control" name="tender_line.depositBankName"></td>
													</tr>
													<tr style="text-align: right;">
														<td style="width: 20%;"><s:property value="getText('payment.cheque.customerBankLocation')"/></td>
														<td><input id="customerBankLocation"
															style="max-width: 80%; margin-left: 10%;" type="text"
															class="form-control"
															name="tender_line.customerBankLocation"></td>
														<td style="width: 20%;"><s:property value="getText('payment.cheque.depositBankLocation')"/></td>
														<td><input id="depositBankLocation"
															style="max-width: 80%; margin-left: 10%;" type="text"
															class="form-control"
															name="tender_line.depositBankLocation"></td>
													</tr>
													<tr style="text-align: right;">
														<td><s:property value="getText('payment.cheque.customerAccountNumber')"/><label style="color: red;">*</label></td>
														<td><input id="customerAccountNo"
															style="max-width: 80%; margin-left: 10%;" type="number"
															class="form-control" name="tender_line.customerAccountNo"></td>
														<td><s:property value="getText('payment.cheque.chequeDepositDate')"/><label style="color: red;">*</label></td>
														<td><input id="chequeDepositDate"
															style="max-width: 80%; margin-left: 10%;" type="text"
															class="form-control" name="tender_line.chequeDepositDate"></td>
													</tr>
													<tr style="text-align: right;">
														<td style="width: 20%;"><s:property value="getText('payment.cheque.checkNumber')"/><label
															style="color: red;">*</label></td>
														<td><input id="chequeNumber"
															style="max-width: 80%; margin-left: 10%;" type="number"
															class="form-control" name="tender_line.chequeNumber"></td>
														<td><s:property value="getText('payment.cheque.chequeAmount')"/><label style="color: red;">*</label></td>
														<td><input id="chequeAmount"
															style="max-width: 80%; margin-left: 10%;" type="number"
															class="form-control" name="tender_line.chequeAmount"></td>
													</tr>
													<tr style="text-align: right;">
														<td><s:property value="getText('payment.cheque.depositSlip')"/></td>
														<td style="text-align: left"><div class="fileUpload btn-link" style="text-align: left;">
																	<i class="fa fa-paperclip" id="sliplink" ><s:property value="getText('payment.cheque.uploadcheck')"/></i><input 
																		type="file" class="upload" name="depositSlip" id="depositSlip" accept=".png,.jpeg,.jpg,.pdf,.bmp" />
																</div></td>
														
														<!--   <a><i class="fa fa-paperclip">Upload Slip</i></a> -->
														
														</td>
														<td></td>
													</tr>
												</table>
											</div>
											<div id="menu3" class="tab-pane fade">
												<table style="max-width: 700px;" class="table">
													<tr style="text-align: right;">
														<td style="width: 20%;"><s:property value="getText('payment.voucher.voucherId')"/><label
															style="color: red;">*</label></td>
														<td><input id="voucherID"
															style="max-width: 50%; margin-left: 10%;" type="text"
															class="form-control" name="tender_line.voucherID"
															maxlength="150"></td>
													</tr>
													<tr style="text-align: right;">
														<td><s:property value="getText('payment.voucher.voucherValue')"/><label style="color: red;">*</label></td>
														<td><input id="voucherValue"
															style="max-width: 50%; margin-left: 10%;" type="number"
															class="form-control" name="tender_line.voucherValue"
															onkeypress="return myFunction()"></td>
													</tr>
													<tr style="text-align: right;">
														<td><s:property value="getText('payment.voucher.expiryDate')"/><label style="color: red;">*</label></td>
														<td><input id="voucherExpiryDate"
															style="max-width: 50%; margin-left: 10%;" type="text"
															class="form-control" name="tender_line.voucherExpiryDate"></td>
													</tr>
												</table>
											</div>

											<div id="menu4" class="tab-pane fade">
												<table style="max-width: 700px;" class="table">
													<tr style="text-align: right;">
														<td style="width: 20%;"><s:property value="getText('payment.coupon.couponId')"/><label
															style="color: red;">*</label></td>
														<td><input id="couponID"
															style="max-width: 50%; margin-left: 10%;" type="text"
															class="form-control" name="tender_line.couponID"
															maxlength="15"></td>
													</tr>
													<tr style="text-align: right;">
														<td><s:property value="getText('payment.coupon.couponValue')"/><label style="color: red;">*</label></td>
														<td><input id="couponValue"
															style="max-width: 50%; margin-left: 10%;" type="number"
															class="form-control" name="tender_line.couponValue"></td>
													</tr>
													<tr style="text-align: right;">
														<td><s:property value="getText('payment.coupon.couponDate')"/><label style="color: red;">*</label></td>
														<td><input id="couponDate"
															style="max-width: 50%; margin-left: 10%;" type="text"
															class="form-control" name="tender_line.customerBankName"></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4 pull-right">
										<s:text id="" name="tenderMode" var="tenderMode"></s:text>
										<s:textfield id="tenderMode" type="hidden" name="tenderMode"
											value="Cash"></s:textfield>

										<!-- <a href="#.html"><button id="addPayment" class="btn btn-block  pull-right " style="width:120px;margin:1px;background:    #666;background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
					background:    linear-gradient(#1770c1, #073763 50%, #1770c1);
					color:         #fff;
					border: 1px solid;
					display:       inline-block;                          
					" type="button" >Add Payment<i class="fa"></i></button></a> -->

										<s:submit id="addPayment" class="btn btn-block  pull-right "
											style="width:110px;margin:1px;background:    #666;background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
					background:    linear-gradient(#1770c1, #073763 50%, #1770c1);
					color:         #fff;
					border: 1px solid;
					display:       inline-block;                          
					"
											type="button" name="Add Payment" value="Add Payment"><s:property value="getText('add.payment.button')"/></s:submit>

									</div>
								</div>

								</s:form>

								<div class="row" id="tenderTable">
									<div class="col-md-3"></div>
									<div class="col-md-6 center">
										<table class="table table-striped">
											<thead>
												<tr style="background-color: #ADC2EE;">
													<th><s:property value="getText('table.head.SNo')"/></th>
													<th><s:property value="getText('table.head.delete')"/></th>
													<th><s:property value="getText('tender.type')"/></th>
													<th><s:property value="getText('amount.being.paid')"/></th>
												</tr>
												<s:iterator value="payment_details.tenderDetails"
													status="itStatus">
													<tr>
														<th><s:property value="%{#itStatus.index+1}" /></th>
														<th><s:a rowid="%{#itStatus.index}" class="delete"
																href="javascript:;">
																<i class="fa fa-trash-o"></i>
															</s:a></th>
														<s:if test="tenderMode.toString()=='CASH'">
															<th><s:property value="getText('tender.cash')"/></th>
															<th><s:property value="tenderAmount" /></th>
														</s:if>
														<s:elseif test="tenderMode.toString()=='CHCK'">
															<th><s:property value="getText('tender.cheque')"/></th>
															<th><s:property value="chequeAmount" /></th>
														</s:elseif>
														<s:elseif test="tenderMode.toString()=='VOUCH'">
															<th><s:property value="getText('tender.vocher')"/></th>
															<th><s:property value="tenderAmount" /></th>
														</s:elseif>
														<s:elseif test="tenderMode.toString()=='QPON'">
															<th><s:property value="getText('tender.coupon')"/></th>
															<th><s:property value="tenderAmount" /></th>
														</s:elseif>
													</tr>

												</s:iterator>
												<tr>
													<td></td>
													<td></td>
													<th><s:property value="getText('table.head.total')"/></th>
													<th><s:property
															value="payment_details.totalAmountPaid" /></th>
												</tr>

												<br>
											</thead>
											<tbody id="tenderList">

											</tbody>
										</table>
									</div>
								</div>
								
								<!-- modal to show when payment is unsuccessful @Srinivas Reddy G-->
								<div class="modal fade bs-example-modal-sm" id="paymentUnsuccessful"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"><s:property value="getText('message_13')"/></h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal"><s:property value="getText('ok.button')"/>&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>
		<!-- End of modal -->
		
		<!-- modal to show if the fields are not filled(Kindly fill all the details) @Srinivas Reddy G-->
		<div class="modal fade bs-example-modal-sm" id="fillDetails"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"><s:property value="getText('message_14')"/></h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal"><s:property value="getText('ok.button')"/>&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>
		<!-- End of modal -->
		
								<div class="row">

									<div class="col-md-3 pull-right">
										<div class="row">
											<div class="col-md-6">
												<s:form action="cancelPayment" name="cancel_payment"
													method="post">
													<s:submit id="cancelPaymentButton"
														class="btn btn-block  pull-right"
														style="width:110px;margin:1px;background:    #666;
background:    -webkit-linear-gradient(#717171, #262626 20%, #717171);
	background:    linear-gradient(#1770c1, #073763 50%, #1770c1);
border: 1px solid;
color:         #fff;
display:       inline-block;                          
"
														type="button"><s:property value="getText('cancel.button')"/><i class="fa"></i>
													</s:submit>
												</s:form>
											</div>
											<div class="col-md-6">
												<s:if test="payment_details.totalAmountPaid>=amtBeingPaid">
													<a href="javascript:;"><button id="postPaymentButton"
															class="btn btn-block  pull-right"
															style="width: 110px; margin: 1px; background: #666; background: -webkit-linear-gradient(#717171, #262626 20%, #717171); background: linear-gradient(#1770c1, #073763 50%, #1770c1); border: 1px solid; color: #fff; display: inline-block;"
															type="button">
															<s:property value="getText('post.payment.button')"/><i class="fa"></i>
														</button></a>
												</s:if>
												<s:else>
													<a href="javascript:;"><button id="postPaymentButton"
															class="btn btn-block  pull-right"
															style="width: 110px; margin: 1px; background: #666; background: -webkit-linear-gradient(#717171, #262626 20%, #717171); background: -linear-gradient(#717171, #262626 20%, #717171); border: 1px solid; color: #fff; display: inline-block;"
															type="button" disabled="true">
															<s:property value="getText('post.payment.button')"/><i class="fa"></i>
														</button></a>
												</s:else>
											</div>
											<!-- Modal to show when the Upload Check file type is not matched -->
		<div class="modal fade bs-example-modal-sm" id="checkSlipModel"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 id="errorTitle" class="modal-title"><s:property value="getText('message_15')"/></h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal"><s:property value="getText('ok.button')"/>&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>
		<!-- End of modal -->
											<!--  cancel payment section  -->
											<%-- <s:form action="cancelPayment" id="cancelPayment" method="post">
<s:if test="payment_details.totalAmountPaid>0">
<a href="javascript:;"><button id="cancelPaymentButton" class="btn btn-block  pull-right" style="width:130px;margin:1px;background:    #666;
background:    -webkit-linear-gradient(#717171, #262626 20%, #717171);
background:    -linear-gradient(#717171, #262626 20%, #717171);
border: 1px solid;
color:         #fff;
display:       inline-block;                          
" type="button" disabled="false">Cancel<i class="fa"></i></button></a></s:if>
  </s:form>
  <s:else>
  <a href="javascript:;"><button id="cancelPaymentButton" class="btn btn-block  pull-right" style="width:130px;margin:1px;background:    #666;
background:    -webkit-linear-gradient(#717171, #262626 20%, #717171);
background:    -linear-gradient(#717171, #262626 20%, #717171);
border: 1px solid;
color:         #fff;
display:       inline-block;                          
" type="button" disabled="true">Cancel<i class="fa"></i></button></a>
  </s:else> --%>
											<!-- end of cancel payment section -->

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

				<s:actionerror theme="bootstrap"></s:actionerror>

			</section>
			<!-- /.content -->
		</div>
		<form action="donePayment" name="hidden_payment_action" method="post">

		</form>

		<s:form action="deleteLineItem" name="hidden_delete_action"
			method="post">
			<s:textfield id="index" type="hidden" name="index" value=""></s:textfield>
		</s:form>


	</div>

	<script type="text/javascript">
		var payment_details;
		var CASH = 'Cash', CHEQUE = 'Cheque', VOUCHER = 'Voucher', COUPON = 'Coupon';
		$(function() {
			//Date picker
			$('#couponDate').datepicker({
				autoclose : true
			});
			$('#voucherExpiryDate').datepicker({
				autoclose : true
			});
			$('#chequeDepositDate').datepicker({
				autoclose : true
			});

			$('#addPayment').click(function() {
				console.log();
				var mode = $('#tenderType li.active a').html();
				$("#tenderMode").val(mode);
				if (validateData(mode)) {
					return true

				} else {
					return false;
				}

			});

			$("#postPaymentButton").click(
					function() {
						$('#postPaymentButton i')
								.addClass('fa-spinner fa-spin');
						$('#postPaymentButton ').prop('disabled', true);
						$.ajax({
							url : 'savePayment',
							type : 'post',
							success : function(result, status, xhr) {
								$('#postPaymentButton i').removeClass(
										'fa-spinner fa-spin');
								$('#postPaymentButton ')
										.prop('disabled', false);
								if (result.status) {
									//alert("Payment Succesfull");
									document.forms["hidden_payment_action"]
											.submit();
								} else {
									//alert("Payment UnSuccessfull");
									$('#paymentUnsuccessful').modal('show');
								}
							},
							error : function(jqXHR, exception) {
								//alert("Payment UnSuccessfull");
								$('#paymentUnsuccessful').modal('show');
							}
						});
					});

			$("body").on("click", ".delete", function(evt) {
				var index = this.getAttribute("rowid");
				$("#index").val(index);
				document.forms["hidden_delete_action"].submit();
			});

		});

		function updateTables(result) {
			$("#tenderList ").children('tr').remove();
			$("#tenderTable").addClass("hidden");
			$("#postPaymentButton").addClass("hidden");
			$
					.each(
							result.payment_details.tenderDetails,
							function(key, value) {
								$("#tenderTable").removeClass("hidden");
								$("#postPaymentButton").removeClass("hidden");
								var amounts, type;
								switch (value.tenderMode) {
								case 'CASH':
									type = CASH;
									break;
								case 'VOUCH':
									type = VOUCHER;
									break;
								case 'CHCK':
									type = CHEQUE;
									break;
								case 'QPON':
									type = COUPON;
									break;
								}
								amount = value.tenderAmount;
								var tender = '<tr><td>SNO</td><td>DEL</td><td>TYPE</td><td>AMOUNT</td></tr>';
								tender = tender
										.replace(
												'DEL',
												'<a rowid="'+key+'" class="delete" href="javascript:;"><i class="fa fa-trash-o"></i></a>');
								tender = tender.replace('SNO', (key + 1) + '.');
								tender = tender.replace('AMOUNT', amount);
								tender = tender.replace('TYPE', type);
								$("#tenderList").append(tender);

							});
			var totaltender = '<tr><td></td><td></td><th>Total</th><th>AMOUNT</th></tr>';
			totaltender = totaltender.replace('AMOUNT',
					result.payment_details.totalAmountPaid);
			$("#tenderList").append(totaltender);
			$("#amtBeingPaid").text(result.payment_details.totalAmountPaid);
		}

		function validateData(mode) {
			if (mode == CASH) {
				if ($.isNumeric($("#cashAmount").val())
						&& $("#cashAmount").val() > 0)
					return true;
			} else if (mode == CHEQUE) {
				if ($("#customerBankName").val().length > 0) {
					if ($.isNumeric($("#customerAccountNo").val())) {
						if ($.isNumeric($("#chequeNumber").val())) {
							if ($("#depositBankName").val().length > 0) {
								if (Date.parse($("#chequeDepositDate").val())) {
									if ($.isNumeric($("#chequeAmount").val())) {
										return true;
									}
								}
							}
						}
					}
				}
			} else if (mode == VOUCHER) {
				if ($("#voucherID").val().length > 0) {
					if ($.isNumeric($("#voucherValue").val())) {
						if (Date.parse($("#voucherExpiryDate").val())) {
							return true;
						}
					}
				}
			} else if (mode == COUPON) {
				if (Date.parse($("#couponDate").val())) {
					if ($("#couponID").val().length > 0) {
						if ($.isNumeric($("#couponValue").val())) {
							return true;
						}
					}
				}
			}
			$('#fillDetails').modal('show');
			return false;
		}

		function clearFields() {
			$('#cashAmount').val('');
			$('#voucherID').val('');
			$('#voucherValue').val('');
			$("#voucherExpiryDate").val('');
			$("#couponDate").val('');
			$("#couponID").val('');
			$("#couponValue").val('');
			$("#customerBankName").val('');
			$("#customerBankLocation").val('');
			$("#customerAccountNo").val('');
			$("#chequeNumber").val('');
			$("#depositBankName").val('');
			$("#depositSlipFileName").val('');
			$("#depositBankLocation").val('');
			$("#chequeDepositDate").val('');
			$("#chequeAmount").val('');
		}
		$('#invoice').addClass('active');
		$('#postPaymentSearch').addClass('active');
		
		//function to validate the Uploaded Check Slip
		$(function(){
			  $('#depositSlip').change(function(){
				var name="";
			    var url = $(this).val();
			    var fileExtension = "";
			    var allowedfileTypes="<s:property value="getText('global.fileTypesAllowed')"/>";
			    if (url.lastIndexOf(".") > 0) {
			        fileExtension = url.substring(url.lastIndexOf(".") + 1, url.length);
			    }
			    if (allowedfileTypes.indexOf(fileExtension.toLowerCase()) > -1) {
			    	 name=url.replace(/C:\\fakepath\\/i, '')
			    	    var inner=$('#sliplink').html(name)
			        return true;
			    }
			    else {
			      /*  alert("You must select a GIF file for upload"); */
			        $('#checkSlipModel').modal('show');
			      $("#depositSlip").val('');
			       var inner=$('#sliplink').html('Upload Check'); 
			        return false;
			    }

			  });

			});
	</script>
</body>
</html>