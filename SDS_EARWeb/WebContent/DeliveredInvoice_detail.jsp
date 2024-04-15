<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

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
<title>SDS | Invoice Detail</title>
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
<link rel="stylesheet" href="assets/css/ionicons.min.css"
	type="text/css" />
	<link rel="stylesheet" href="assets/css/jquery-ui.css">

<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="assets/plugins/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="assets/css/popup.css">
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

		<!-- Main Header -->
		<s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" />
		<%-- <header class="main-header">

			<!-- Logo -->
			<a href="#" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-lg"><img src="assets/SDSlogo.png"></span> <span
				class="logo-mini"><b></b>SDS</span> <!-- logo for regular state and mobile devices -->

			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>

				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li class="dropdown notifications-menu"><a
							aria-expanded="true" href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="fa fa-bell-o"></i> <span
								class="label label-warning">1</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">You have 1 notifications</li>
								<li>
									<!-- inner menu: contains the actual data -->
									<div
										style="position: relative; overflow: hidden; width: auto; height: 200px;"
										class="slimScrollDiv">
										<ul style="overflow: hidden; width: 100%; height: 200px;"
											class="menu">
											<li><a href="#"> <i class="fa fa-truck text-aqua"></i>
													10 new orders today
											</a></li>
										</ul>
										<div
											style="background: none repeat scroll 0% 0% rgb(0, 0, 0); width: 3px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 200px;"
											class="slimScrollBar"></div>
										<div
											style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: none repeat scroll 0% 0% rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"
											class="slimScrollRail"></div>
									</div>
								</li>
								<li class="footer"><a href="#">View all</a></li>
							</ul></li>



						<!-- User Account Menu -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button -->
							<table
								style="color: white; text-align: center; margin-right: 5px;">
								<tr>
									<td><s:property value="employee.employeeName" /></td>
								</tr>
								<tr>
									<td style="text-align: center;"><small id="empRole"></small></td>
								</tr>
							</table>
						</li>
						<!-- Control Sidebar Toggle Button -->
						<li><a href="#" data-toggle="control-sidebar"
							style="margin-bottom: -5px; margin-top: -5px;"><table
									style="margin: -5px; padding: 0px;">
									<tr>
										<td><i class="fa fa-user "></i></td>
									</tr>
									<tr>
										<td><i class="fa fa-angle-down fa-lg"></i></td>
									</tr>
								</table></a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">



				<!-- Sidebar Menu -->

				<ul class="sidebar-menu">
					<li class="header"><h4>
							<b>MENU</b>
						</h4></li>
					<li class="treeview"><a href="main"> <i
							class="fa  fa-line-chart"></i> <span>DashBoard</span> <i
							class="fa pull-right"></i>
					</a></li>
					<li class=" treeview"><a href="#"> <i class="fa fa-truck"></i>
							<span>Order</span> <i class="fa fa-angle-down pull-right"></i>
					</a>
						<ul class=" treeview-menu">
							<li class=""><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Order
									Search</a></li>
							<li class=""><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Book
									Order</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Open
									Orders</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Delivered
									Orders</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Cancelled
									Orders</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Returned
									Orders</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Cancel
									Order</a></li>
						</ul></li>
					<li class="active treeview"><a href="#"> <i
							class="fa fa-file-text"></i> <span>Invoice</span> <i
							class="fa fa-angle-down pull-right"></i>
					</a>
						<ul class="active treeview-menu">
							<li class="active"><a href="invoices"><i
									class="fa fa-angle-right"></i>&nbsp;&nbsp;Invoice Search</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Open
									Invoices</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Post
									Payment</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i
							class="fa fa-dashboard"></i> <span>Claim</span> <i
							class="fa fa-angle-down pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li class=""><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Claim
									Search</a></li>
							<li class=""><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Register
									Claim</a></li>
							<li class=""><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Approve
									Claim</a></li>
							<li class=""><a href="#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Accept
									Claim</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-user"></i>
							<span>Customer</span> <i class="fa fa-angle-down pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li class=""><a href="customerSearch"><i
									class="fa fa-angle-right"></i>&nbsp;&nbsp;Customer Search</a></li>
							<!--            <li><a href="Add_New.html"><i class="fa fa-circle-o"></i> Add New</a></li>-->
						</ul></li>

				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside> --%>

  <!-- Content Wrapper. Contains page content -->
  <div style="min-height: 595px;" class="content-wrapper">
   
    

    <!-- Main content -->
    <section class="content">
<!--     <div class="error-content hidden-lg hidden-md">
          

         
          <form class="search-form" action="Customer_Page_b.html">
            <div class="input-group">
              <input type="text" placeholder="Customer Search" class="form-control" name="search">

              <div class="input-group-btn">
          <a href="Customer_Page_b.html"><button class="btn bg-blue" name="submit"><i class="fa fa-search"></i>
                </button></a>
              </div>
            </div>
            /.input-group
          </form>
     <br><br>
    </div> -->
      <!-- Your Page Content Here -->
				<section class="invoice">
					<!-- title row -->
					<div class="row">
						<div class="col-md-3 col-xs-3 visible-print-inline">
							<b><span class="logo-lg pull-left "><img
									src="assets/SDSlogo.png"></span> <span class="logo-mini"></span></b>
						</div>
						<div class="col-md-3 col-xs-3 visible-print-inline">
							<small class="pull-left  "
								style="font-size: 9px; color: #8e8e8e;"><b> <s:i18n
										name="rispl.print.printText">
										<s:text name="salesInvoice.Address3" />
									</s:i18n>
							</b></small>
						</div>
						<div class="col-md-3 col-xs-3 visible-print-inline">
							<small class="pull-right" style="font-size: 9px; color: #8e8e8e;"><b>
									<s:i18n name="rispl.print.printText">
										<s:text name="salesInvoice.Address2" />
									</s:i18n>
							</b></small>
						</div>
						<div class="col-md-3 col-xs-3 visible-print-inline">
							<small class="pull-right"
								style="text-align: right; font-size: 9px; color: #8e8e8e;"><b>
									<s:i18n name="rispl.print.printText">
										<s:text name="salesInvoice.Address1" />
									</s:i18n>
							</b></small>
						</div>
					</div>
					<small class="pull-right hidden-print"
						style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><b><s:property
								value="getText('invoice.details.help_text')" /></b></small>
					<div class="row">
						<div class="col-xs-12">
							<s:url action="downloadInvoice" var="downloadInvoiceUrl">
								<s:param name="invoiceID" value="%{invDetail.arInvNum}" />

							</s:url>
							<h2 style="color: #226e71;">
								<s:property value="getText('delivered.invoice.detail')" />
								<div class="pull-right hidden-print">
									<h5>
										<a href="javascript:void(0);" class="pull-right" rel="popover"
											id="btpopover"
											data-content='
                					
                							<div><b><input type="checkbox" id="employee" name="employee" class="form-check-input"> <label for="employee"><s:property value="getText('login.employee')"/></label></div>
											<div><b><input type="checkbox" id="customr" name="customr" class="form-check-input"> <label for="customr"><s:property value="getText('customer.label')"/></label></div>
											<div><b><input type="checkbox" id="departmentHead" name="departmentHead" class="form-check-input"> <label for="departmentHead"><s:property value="getText('dept.head')"/></label></div>
    										<div><b><input type="checkbox" id="salesAgent" name="salesAgent" class="form-check-input"> <label for="salesAgent"><s:property value="getText('sales.agent')"/> </label></div>
    										<div><b><input type="checkbox" id="DataEntryOptr" name="DataEntryOptr" class="form-check-input"> <label for="DataEntryOptr"><s:property value="getText('dataentry.operator')"/> </label></div>
    										<div><label>Mail :</label><input type="text" id="custommails" name="check" /></div><br>
                							<div style="text-align:right;"><button type="button"  id="emailSubmitbtn">Submit</button>
                							<button type="button" data-dismiss="modal" id="cancelmail">Cancel</button></div>
                							'
											data-placement="top" data-original-title="Select"><i
											class="fa fa-envelope"></i> <s:property
												value="getText('mail.label')" /> &nbsp;</a> <a
											href="javascript:void(0);" onclick=" windowPrint()"
											style="text-decoration: none" class="pull-right"><i
											class="fa fa-print"></i> <s:property
												value="getText('print.label')" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a> <a
											href="<s:property value="#downloadInvoiceUrl"/>"
											style="text-decoration: none" class="pull-right"><i
											class="fa fa-save"></i>
										<s:property value="getText('save.label')" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a>
								</div>

								<!--            <small class="pull-right">Date: 2/10/2014</small>-->
								</h5>
							</h2>
						</div>
						<!-- /.col -->
					</div>
					<!-- info row -->
					<div class="row invoice-info">
						<div class="col-sm-4 invoice-col">
							<table style="width: 100%;" class="table">
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('invoice.no')" />:</b></td>
									<td><b><span id="InvoiceNo"><s:property
													value="invDetail.arInvNum" /></span></b></td>
								</tr>
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('invoice.date')" />:</b></td>
									<td><b><span id="invDate"><s:date
													name="invDetail.arInvDate"
													format="%{getText('format.date')}" /></span></b></td>
								</tr>
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('status.label')" />:</b></td>
									<td><b><span id="InvStatus"> <s:if
													test="invDetail.invStatus=='0'">
													<s:property value="getText('closed_inv')" />
												</s:if> <s:elseif test="invDetail.invStatus=='1'">
													<s:property value="getText('open_inv')" />
												</s:elseif> <s:else>
													<s:property value="getText('unknown_inv')" />
												</s:else>
										</span></b></td>
								</tr>
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('age.label')" />:</b></td>
									<%--  <td><s:date name="invoices.arInvDate"
			format="%{getText('format.date')}" nice="true" /></td> --%>
									<td><b></span> <s:property value="age" /></b></td>
								</tr>
							</table>
						</div>
						<!-- /.col -->
						<div class="col-sm-4 invoice-col">
							<table style="width: 100%;" class="table">
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('customer.id')" />:</b></td>
									<td><b><span id="CustomerID"><s:property
													value="orders[0].customer.customerHeader.customerHeaderPK.custId" /></span></b></td>
								</tr>
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('customer.name')" />:</b></td>
									<td><b><span id="CustomerName"><s:property
													value="orders[0].customer.customerHeader.ctNm" /></span></b></td>
								</tr>
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('customer.segment')" />:</b></td>
									<td><b><span id="CustomerSegment"><s:property
													value="orders[0].customer.customerSegmentID" /></span></b></td>
								</tr>
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('LPO.label')" />:</b></td>
									<td><b><span id="LPO"><s:property
													value="orders[0].ordTranSum.custLpoNum" /></span></b></td>
								</tr>
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('lpo.date')" />:</b></td>
									<td><b><span id="LPODate"><s:date
													name="orders[0].ordTranSum.custLpoDate"
													format="%{getText('format.date')}" /></span></b></td>
								</tr>
							</table>
						</div>
						<!-- /.col -->
						<div class="col-sm-4 invoice-col">
							<table style="width: 100%;" class="table">
								<tr>
									<td style="text-align: right; width: 50%;"><s:property
											value="getText('order.id')" />:</b></td>
									<td><b><span id="OrderID"><s:property
													value="invDetail.orderNum" /></span></b></td>
								</tr>
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('order.date')" />:</b></td>
									<td><b><span id="OrderDate"><s:date
													name="invDetail.orderDate"
													format="%{getText('format.date')}" /></span></b></td>
								</tr>
								<tr>
									<td style="text-align: right;"><s:property
											value="getText('shipping.address')" />:</b></td>
									<td style="word-break: break-word;"><address>
											<b> <s:property value="shipping" />
										</address>
										</b></td>
								</tr>
							</table>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->

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
										<th class="text-right"><s:property
												value="getText('table.head.vpn')" /></th>
										<th style="text-align: right;"><s:property
												value="getText('price.head')" /></th>
										<th style="text-align: right;"><s:property
												value="getText('order.disc.amt')" /></th>
										<th style="text-align: right;"><s:property
												value="getText('table.head.tax')" /></th>

										<th style="text-align: right;"><s:property
												value="getText('delivered.qty')" /></th>
										<th style="text-align: right;"><s:property
												value="getText('table.head.total')" />(<s:property
												value="getText('global.currency')" />)</th>
									</tr>
								</thead>
								<tbody id="orderDetail">
									<s:iterator value="orders[0].ordTranLineItems"
										status="itStatus">
										<tr>
											<td><s:property value="%{#itStatus.index+1}" />.</td>
											<td><s:property value="itemId" /><br> <s:property value="deItmShrtRcpt" /></td>
											<td style="text-align: right;"><s:property
													value="lineQnt" /></td>
											<td class="text-right"><s:property value="registryId" /></td>
											<td style="text-align: right;"><s:property
													value="%{getText('format.currency.args',{itmPrnPrc})}" /></td>

											<%--    <s:if test="%{#ordTranDscItms[0].dscPer>0}">
             <td><s:property value="ordTranDscItms[0].dscPer"/>  </td>
             </s:if>
             <s:else>
              <td><s:property value="%{0}"/> </td>
             </s:else> --%>
             
             								<%-- <s:set var="discamts" value=""/>
											<s if:test="<s:property value="ordTranDscItms[0].dscAmt"/>>0"
												<s:set var="discamts" value="ordTranDscItms[0].dscAmt"/>
											</s:if>
											<s:else>
												<s:set var="discamts" value="0" />
											</s:else>
											
											<td style="text-align: right;"><s:property value="%{getText('format.currency.args',{discamts})}"/> </td> --%>

											 <%-- <s:if test="<s:property value="ordTranDscItms[0].dscAmt"/>>0">
												<td style="text-align: right;"><s:property value="%{getText('format.currency.args',{ordTranDscItms[0].dscAmt})}"/> </td>
											</s:if>
											<s:else>
												<td style="text-align: right;"><s:property value="%{0}" /></td>
											</s:else>  --%>
											<s:set var="dscAmts" value="0"/>
				<s:if test="%{ordTranDscItms != null}">
					<s:iterator value="ordTranDscItms" status="dscList">
						<s:set var="dscAmts" value="%{#dscAmts+dscAmt}"/>
					</s:iterator>
				</s:if>

<td class="text-right"><s:property value="%{getText('format.currency.args',{#dscAmts})}"/></td>
											<td style="text-align: right;"><s:property
													value="vatLnItmRtn" /></td>

											<s:set var="deliveredQty" value="" />
											<s:if test="lineQnt!=null && showDeliveredQuantity">
												<s:set var="deliveredQty" value="lineQnt" />
											</s:if>
											<s:else>
												<s:set var="deliveredQty" value="0" />
											</s:else>
											<td style="text-align: right;"><s:property
													value="deliveredQty" /></td>

											<td style="text-align: right;"><s:property
													value="%{getText('format.currency.args',{extnDscLnItm})}" /></td>

										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<!-- /.col -->
					</div>
					<div class="progress" style="height: 2px;">
						<div class="progress-bar"
							style="width: 100%; background-color: #ADC2EE;"></div>
					</div>
					<!-- /.row -->

					<div class="row">
						<!-- /.col -->
						<div class="col-lg-4 col-md-6 col-xs-12 pull-right">

							<div class="table-responsive">
								<table class="table">
									<tbody>
										<tr>
											<td style="text-align: right; width: 50%"><s:property
													value="getText('subtotal.label')" />:</td>
											<td style="text-align: right;"><s:property
													value="getText('global.currency')" />&nbsp;&nbsp; <b><span
													id="Subtotal"><s:property
															value="%{getText('format.currency.args',{invDetail.invAmount})}" /></span></b></td>
										</tr>
										<tr>
											<td style="text-align: right;"><s:property
													value="getText('discount.label')" />:</td>
											<td style="text-align: right;"><s:property
													value="getText('global.currency')" />&nbsp;&nbsp; <b><span
													id="Discount"><s:property
															value="%{getText('format.currency.args',{orders[0].ordTranSum.dkartDscTot})}" /></b>
											</th>
											</td>
										</tr>
										<tr>
											<td style="text-align: right;"><s:property
													value="getText('total.tax')" />:</td>
											<td style="text-align: right;"><s:property
													value="getText('global.currency')" />&nbsp;&nbsp; <b><span
													id="TotalTax"><s:property
															value="orders[0].ordTranSum.dkartTaxTot" /></b>
											</th>
											</td>
										</tr>
										<tr style="font-size: 18px;">
											<td style="text-align: right; width: 50%"><s:property
													value="getText('table.head.total')" />:</td>
											<td style="text-align: right;"><s:property
													value="getText('global.currency')" />&nbsp;&nbsp; <b><s:property
														value="%{getText('format.currency.args',{invDetail.invAmount})}" /></span></b></td>
										</tr>
									</tbody>
								</table>
							</div>
							<button onClick="history.go(-1);return true;" type="button"
								class="btn  pull-right hidden-print"
								style="color: #ffffff; width: 100px; margin-top: 5px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; display: inline-block;">
								<s:property value="getText('back.button')" />
							</button>


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
  

  <!-- Control Sidebar -->
 <aside class="control-sidebar control-sidebar-dark" style="position: fixed; height: auto;">
    <!-- Create the tabs -->
<!--
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-home"></i></a></li>
      <li class=""><a href="#control-sidebar-settings-tab" data-toggle="tab" aria-expanded="false"><i class="fa fa-gears"></i></a></li>
    </ul>
-->
    <!-- Tab panes -->

  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div style="position: fixed; height: auto;" class="control-sidebar-bg"></div>
</div>

<div class="modal fade bs-example-modal-sm" id="mailsendinfo"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body text-center">
						<button type="submit" class="close" data-dismiss="modal">&times;</button>
						<h4>
							Mail has been sent successfully
						</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="submit" class="btn btn-primary center-block" data-dismiss="modal" id="mailconfirmation">
							<s:property value="getText('ok.button')" />
							&nbsp;
						</button>
					</div>
				</div>

			</div>
		</div>



<div class="modal fade bs-example-modal-sm" id="validatepopover"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body text-center">
						<button type="submit" class="close" data-dismiss="modal">&times;</button>
						<h4>
							Check Atleast One checkbox
						</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="submit" class="btn btn-primary center-block" data-dismiss="modal" id="mailconfirmation">
							<s:property value="getText('ok.button')" />
							&nbsp;
						</button>
					</div>
				</div>

			</div>
		</div>

<div class="modal fade bs-example-modal-sm" id="errorsendingmail"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body text-center">
						<button type="submit" class="close" data-dismiss="modal">&times;</button>
						<h4>
							Failure Sending Mail
						</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="submit" class="btn btn-primary center-block" data-dismiss="modal" id="mailconfirmation">
							<s:property value="getText('ok.button')" />
							&nbsp;
						</button>
					</div>
				</div>

			</div>
		</div>

  
	 <script>	 
	function  windowPrint(){
		window.print();
	}
	$('a[rel=popover]').popover({
	    html: 'true',
	placement: 'bottom'
	})
	
	</script>	


   <script type="text/javascript">
		function emailSubmit(){   
		var employeecheck=$('#employee').is(":checked");
        var customer=$('#customr').is(":checked");
		var deptheadcheck=$('#departmentHead').is(":checked");
		var empsalescheck=$('#salesAgent').is(":checked");
		var dataentrycheck=$('#DataEntryOptr').is(":checked");
		var custom=document.getElementById("custommails").value;
		var invoiceID=document.getElementById("InvoiceNo").innerHTML;

	   	   
	   if(employeecheck==false && customer == false && deptheadcheck==false && empsalescheck==false && dataentrycheck==false && (custom==null || custom=="") ){
	    $("#validatepopover").modal("show");
	   }
	   else{			   
			$.ajax({
				url : "invoicedetailmail",
				type : "POST",
				data: {
				invoiceID : invoiceID,
 				loginEmp : employeecheck, 
				customer:customer,
				departmentHead:deptheadcheck,
				salesAgent:empsalescheck,
				DataEntryOptr:dataentrycheck,
				custommail:custom},
				dataType : "json",
				 statusCode:{		
					 200:function(){
					 $("#mailsendinfo").modal("show");
					 $("#btpopover").popover("hide"); 
					 },
					 404:function()	{
					 $("#errorsendingmail").modal("show");
					 $("#btpopover").popover("hide");
					 }					
					} 
				}); 
				}	   
     }	
    $(document).on('click', '#emailSubmitbtn', function(){
	emailSubmit(); 			 
    });
    $(document).on('click', '#cancelmail', function(){	
    $("#btpopover").popover("hide");
    });
    </script>
</body>
</html>