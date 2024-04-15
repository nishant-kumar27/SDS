<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>SDS | Customer Information</title>

<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css" type="text/css" />
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css" type="text/css" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap-theme.min.css" type="text/css" />
<link rel="stylesheet" href="assets/css/ionicons.min.css" type="text/css" />
<link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />
<link rel="stylesheet" href="assets/css/Customizations.css" type="text/css" />
<link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="assets/css/popup.css">

<!-- REQUIRED JS SCRIPTS -->
<script src="Starter_files/jQuery-2.js"></script>
<script src="Starter_files/bootstrap.js"></script>
<script src="Starter_files/app.js"></script>


</head>

<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<s:include value="menubar.jsp" />
		<s:include value="topbar.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">



			<section class="content">
				
				<div class="row">
					<div class="col-md-6">

				<div class="box box-widget widget-user-2">
				<div class="widget-user-header bg-blue" style="height:80px;">
					<div class=" pull-right" style="margin-top:-20px;">
                		<div id="container">
                    		<img id="image" src="assets/dist/img/ClassSilver.png" />
                    		<p  class="segment-label" >
                    			<s:property	value="customer.customerSegmentID" /> 
                    		</p>
                		</div>
              		</div>
								<h3 style="margin-top: 0px;">
									<span id="customername"><s:property
											value="customer.customerHeader.ctNm" /></span>
								</h3>
								<h5>
									Customer ID: <span id="customerid"><s:property
											value="customer.customerHeader.customerHeaderPK.custId" /></span>
								</h5>
							</div>

							<table class="table ">
								<tr>
									<td>Status:</td>
									<td id="status"><strong><s:set var="stsCd"
											value="customer.customerHeader.ctStsCd"></s:set> <s:if
											test="%{#stsCd=='0'}">
											<p class="text-danger">In-Active</p>
										</s:if> <s:elseif test="%{#stsCd=='1' || #stsCd=='A' }">
											<p class="text-success">
												<strong>Active</strong>
											</p>
										</s:elseif> <s:else>
											<p class="text-danger">
												<strong>Deleted</strong>
											</p>
										</s:else></strong></td>
								</tr>
								<tr>
									<td style="width: 40%;">Billing Details:</td>
									<td id="billingaddr"><strong><s:iterator var="address"
											value="customer.customerSite[0].customerSiteAddressList"
											status="status">
											<s:if test="#address.tyAds.equals('0')">
												<s:property value="#address.a1Cnct" />
												<br>
												<s:if test="#address.a2Cnct!=null">
													<s:property value="#address.a2Cnct" />
													<br>
												</s:if>
												<s:if test="#address.a3Cnct!=null">
													<s:property value="#address.a3Cnct" />
													<br>
												</s:if>
												<s:if test="#address.a4Cnct!=null">
													<s:property value="#address.a4Cnct" />
													<br>
												</s:if>
												<s:property value="#address.ciCnct" />,<s:property
													value="#address.pcCnct" />
												<br>
												<s:property value="#address.coCnct" />
												<br>
											</s:if>
										</s:iterator></strong></td>
								</tr>
								<tr>
									<td>Payment Terms:</td>
									<td id="terms"><strong>
										<s:if test="%{customer.paymentTerms.payIn == '0' || customer.paymentTerms.payIn == 0}">
											Immediate
										</s:if>
										<s:else>
											<s:property	value="customer.paymentTerms.payIn" />&nbsp;Days
										</s:else>
									</strong></td>
								</tr>
								<tr>
									<td>Total Credit Limit:</td>
									<td id="creditlimit">
										<strong>
											<s:property	value="getText('global.currency')" />
											<s:property	value="%{getText('format.currency.args',{customer.customerLimits.crdtLimit})}" />
										</strong>
									</td>
								</tr>
								<tr>
									<td>Available Credit Limit:</td>
									<td id="availcredit">
										<strong>
											<s:property value="getText('global.currency')" />
											<s:property	value="%{getText('format.currency.args',{customer.customerLimits.avCrdtLimit})}" />
										</strong>
									</td>
								</tr>
								<tr>
									<td>Receivables:</td>
									<td id="receivables">
										<strong>
											<s:property	value="getText('global.currency')" />
											<s:property	value="%{getText('format.currency.args',{customer.customerLimits.pendDue})}" />
										</strong>
									</td>
								</tr>
							</table>
						</div>



					</div>
					<div class="col-md-6">

						<div class="box">
							<div class="box-header">
								<h4 class="box-title" style="color: #226e71;">
									Open Orders
								</h4>
							</div>
							<!-- /.box-header -->
							<div class="box-body no-padding table-responsive pre-scrollable"
								style="max-height: 240px;">
								<table class="table table-striped">
									<thead>
										<tr style="background-color:#ADC2EE;">
											<th style="width: 10px">#</th>
											<th>Order ID</th>
											<th>Order Date</th>
											<th>Status</th>
											<th>Value <small>(<s:property value="getText('global.currency')" />)</small></th>
										</tr>
									</thead>
									<tbody id="openorders">
										<s:if test="customer.customerOrderDetails !=null">
											<s:iterator value="customer.customerOrderDetails" status="orderStatus">
												<s:if test="%{orderStatus <= 4}">
													<tr>
														<td><s:property value="#orderStatus.count" />.</td>
														<td><s:property value="orderId" /></td>
														<td><s:property value="orderDate" /></td>
														<s:if test="%{orderStatus == 0}">
															<td>New</td>
														</s:if>
														<s:elseif
															test="%{orderStatus == 1}">
															<td>Open</td>
														</s:elseif>
														<s:elseif
															test="%{orderStatus == 2 || orderStatus == 3 || orderStatus == 4}">
															<td>In-Progress</td>
														</s:elseif>
														<s:else>
															<td>-</td>
														</s:else>
														<td style="padding-right: 32px;"><p class="text-right"><s:property value="orderTotal" /></p></td>
													</tr>
												</s:if>
											</s:iterator>
										</s:if>
									</tbody>
								</table>
							</div>
							<!-- /.box-body -->
						</div>

						<div class="form-group">
							<div class="box-body">
								<a class="btn btn-blue pull-right"  href="validateCustomer">New Order</a>
								<s:actionerror theme="bootstrap" style="!important" />
							</div>
						</div>

					</div>
				</div>
				<div class="row" style="margin: 0%;">
					<div class="box">
						<div class="box-header">
							<h4 class="box-title" style="color: #226e71;">
								Open Invoices
							</h4>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding table-responsive">
							<table class="table table-striped">
								<thead>
									<tr style="background-color:#ADC2EE;">
										<th>#</th>
										<th>Invoice No</th>
										<th>Invoice Date</th>
										<th>Invoice Amt</th>										
										<th>Status</th>
										<th>Overdue By</th>	
										<th>Age</th>									
										<th>Order ID</th>
										<th>Order Date</th>	
									</tr>
								</thead>
								<tbody id="invoices">
									<s:if test="customer.customerSite !=null">
										<s:set var="count" value="1" />
										<s:iterator var="sites" value="customer.customerSite"
											status="siteStatus">
											<s:if test="#sites.customerSiteInvoiceList !=null">
												<s:iterator var="invoices"
													value="#sites.customerSiteInvoiceList"
													status="invoiceStatus">
													<s:if test="%{#invoices.invStatus=='1'}">
													<tr>
														<td><s:property value="#count" />.</td>
														<td><s:property value="#invoices.arInvNum" /></td>
														<td><s:date name="#invoices.arInvDate" id="arInvDate"
																format="%{getText('format.date')}"></s:date>
															<s:property value="#arInvDate"/>
														</td>
														<td><s:property value="getText('global.currency')" />
														
															<s:property value="#invoices.invAmount" /></td>
															<s:if test="%{#invoices.invStatus=='0'}">
															<td>Closed</td>
															<td>-</td>
														</s:if>
														<s:elseif test="%{#invoices.invStatus=='1'}">
															<td>Open</td>				
															<s:set var="arDate" value="#invoices.arInvDate" />
															<jsp:useBean id="arDate" type="java.util.Date" />
															<s:set var="payIn" value="customer.paymentTerms.payIn" />
															<jsp:useBean id="payIn" type="java.math.BigInteger" />
															<%
																java.util.Date currentDate = new java.util.Date();
																					long diff = currentDate.getTime() - arDate.getTime();
																					long diffDays = java.util.concurrent.TimeUnit.DAYS.convert(diff,
																							java.util.concurrent.TimeUnit.MILLISECONDS);
																					if (diffDays > payIn.longValue())
																						out.println("<td>" + (diffDays - payIn.longValue()) + " Days</td>");
																					else
																						out.println("<td>-</td>");
															%>
														</s:elseif>
														
														<td>
															<%-- <s:date name="#invoices.arInvDate"
																format="%{getText('format.date')}" nice="true" /> --%>
															<s:bean name="rispl.ds.DateUtil" var="dateComp">
																	<s:param name="date1" value="#arInvDate"></s:param>
															</s:bean>
															<s:property value="#dateComp.diffInDays"/> Days
														</td>
																
														<td><s:property value="#invoices.orderNum" /></td>
														
														<td><s:date name="#invoices.orderDate"
																format="%{getText('format.date')}" ></s:date></td>
													</tr>
													<s:set var="count" value="#count+1" />
													</s:if>
												</s:iterator>
											</s:if>
										</s:iterator>
									</s:if>

								</tbody>
							</table>
						</div>
						<!-- /.box-body -->
					</div>

				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		
	</div>
	<!-- ./wrapper -->

	<script type="text/javascript">
    $('#customer').addClass('active');
    $('#customersearch').addClass('active');
  </script>
</body>
</html>