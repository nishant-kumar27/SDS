<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>SDS | Customer Info</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css" type="text/css" />
	<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css" type="text/css" />
    <link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="assets/css/jquery-ui.css">
    <link rel="stylesheet" href="assets/css/Customizations.css" type="text/css" />
    <link rel="stylesheet" href="assets/plugins/datatables/jquery.dataTables.css" type="text/css">
    <link rel="stylesheet" href="assets/plugins/datatables/dataTables.bootstrap.css" type="text/css">

<!-- REQUIRED JS SCRIPTS -->

    <script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
<script src="assets/plugins/slimScroll/jquery.slimscroll.js"></script>

<script src="assets/plugins/datatables/jquery.dataTables.js"></script>
<script src="assets/plugins/datatables/dataTables.bootstrap.js"></script>
<!-- jQuery 2.2.0 -->
<style>
    
        .customer-header-details >* >* >* {
            border:2px solid #e4e4e4;
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

  <s:include value="topbar.jsp"/>
  <s:include value="menubar.jsp"/>
  
  <!-- Content Wrapper. Contains page content -->
  <div style="min-height: 595px;" class="content-wrapper">
   
    

    <!-- Main content -->
    <section class="content">
		<!-- Breadcrumbs if required
		<ol class="breadcrumb" style="padding: 0px 15px; margin-bottom: 10px;">
		  <li class="breadcrumb-item">SDS</li>
		  <li class="breadcrumb-item">Customer</li>
		  <li class="breadcrumb-item"><a href="customerSearch">Customer Search</a></li>
		  <li class="breadcrumb-item active">Customer Information</li>
		</ol> -->
      <div class="row">
        <div class="col-md-12">
        
    <div class="box box-widget widget-user-2" >
            <!-- Add the bg color to the header using any of the bg-* classes -->
                <div class="widget-user-header bg-blue" >
              <div class=" pull-right" style="margin-top:-20px;">
                <div id="container">
                    <img id="image" src="assets/dist/img/Silver_Diamond.png" />
                    <p id="text" style="color:black"><s:property	value="customer.customerSegmentID" /></p>                    
                </div>
              </div>
              <!-- /.widget-user-image -->
                <h3 style="margin-top:0px;"><s:property value="customer.customerHeader.ctNm"/></h3>
                <h5 style="margin-bottom:-4px;"><s:property value="getText('customer.id')"/>:&nbsp;<s:property value="customer.customerHeader.customerHeaderPK.custId"/></h5>
                </div>  
                <div class="row">
                    <div class="col-md-6">
                        <table class="table table-condensed table-border customer-header-details" >
                        <thead>
                        <tr><th style="color:#226e71;width:50%; " class="padding-left-20"><s:property value="getText('customer_info')"/></th><th></th></tr>
                         </thead>
                        <tbody>
                        <tr><td class="padding-left-20"><s:property value="getText('status.label')"/>:</td><td>
                        	<s:set var="stsCd" value="customer.customerHeader.ctStsCd"></s:set>
							<s:if test="%{#stsCd=='0'}">
								<strong><s:property value="getText('inactive_status')"/></strong>
							</s:if>
							<s:elseif test="%{#stsCd=='1' || #stsCd=='A' }">
								<strong><s:property value="getText('active_status')"/></strong>
							</s:elseif>
							<s:else>
								<strong><s:property value="getText('deleted_status')"/></strong>
							</s:else></td></tr>
                        <tr><td class="padding-left-20"><s:property value="getText('payment.terms')"/>:</td><td>
                        
								<s:if test="%{customer.paymentTerms.payIn == '0' || customer.paymentTerms.payIn == 0}">
											<strong><s:property value="getText('immediate.label')"/></strong>
										</s:if>
										<s:else>
											<strong><s:property	value="customer.paymentTerms.payIn" /></strong>&nbsp;<s:property value="getText('days.label')"/>
										</s:else>
								</td></tr>    
                        <tr><td class="padding-left-20"><s:property value="getText('total.credit.limit')"/>:</td><td>
                        	<small><s:property	value="getText('global.currency')"/></small>
                        	<strong><s:property	value="%{getText('format.currency.args',{customer.customerLimits.crdtLimit})}"/>
                        	</strong></td></tr>
                        <tr><td class="padding-left-20"><s:property value="getText('available.credit.limit')"/>:</td><td>
                        	<small><s:property	value="getText('global.currency')"/></small>
                        	<strong><s:property	value="%{getText('format.currency.args',{customer.customerLimits.avCrdtLimit})}"/>
                        	</strong></td></tr>
                        <tr><td class="padding-left-20"><s:property value="getText('receivables.label')"/>:</td><td>
                        	<small><s:property value="getText('global.currency')"/></small>
                        	<strong><s:property	value="%{getText('format.currency.args',{customer.customerLimits.pendDue})}"/>
                        	</strong></td></tr>
                        <tr><td class="padding-left-20"><s:property value="getText('billing.address')"/>:</td>
                        	<td style="word-break: break-word; font-weight: bold;">
                        		<s:iterator var="address"
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
								</s:iterator>
							</td></tr>
                        </table>
                    </div>
                    <div class="col-md-6">
                        <table class="table table-condensed table-border customer-header-details" >
                        <tr><th class="padding-left-20" style="width:50%;color: #226e71;"><s:property value="getText('orders.label')"/></th><th></th></tr>
                        <tr><td class="padding-left-20"><s:property value="getText('total.valueof.neworders')"/>:</td><td>
                        	<small><s:property value="getText('global.currency')"/></small>&nbsp;
                        	<strong><s:property value="%{getText('format.currency.args',{customer.customerTotals[1]})}"/></strong></td></tr>
                        <tr><td class="padding-left-20"><s:property value="getText('total.valueof.openorders')"/>:</td><td>
							<small><s:property value="getText('global.currency')"/></small>&nbsp;
							<strong><s:property value="%{getText('format.currency.args',{customer.customerTotals[2]})}"/></strong></td></tr>
                        <tr><td class="padding-left-20"><s:property value="getText('total.valueof.Inprogressorders')"/>:</td><td>
							<small><s:property value="getText('global.currency')"/></small>&nbsp;
							<strong><s:property value="%{getText('format.currency.args',{customer.customerTotals[3]})}"/></strong></td></tr>
                        <tr><th class="padding-left-20" style="color: #226e71;"><s:property value="getText('invoices.label')"/></th><th></th></tr>
                        <tr><td class="padding-left-20"><s:property value="getText('total.valueof.currentinvoices')"/>:</td><td>
							<small><s:property value="getText('global.currency')"/></small>&nbsp;
							<strong><s:property value="%{getText('format.currency.args',{customer.customerTotals[4]})}"/></strong></td></tr>
                        <tr><td class="padding-left-20"><s:property value="getText('total.valueof.overdueinvoices')"/>:</td><td>
							<small><s:property value="getText('global.currency')"/></small>&nbsp;
							<strong><s:property value="%{getText('format.currency.args',{customer.customerTotals[5]})}"/></strong></td></tr>
                        <%-- <tr>
                            <td>
                            	<s:actionerror theme="bootstrap" style="!important" />
                            </td>
                            <td>
                                <a class="btn btn-block" href="validateCustomer">New Order</a>
                            </td>
                        </tr> --%>
                        </tbody>
                        </table>
                    </div>
                    
 		 <div class="row" style="margin-bottom:10px;">
                    	<div class="col-md-6"></div>
                    	<div class="col-md-6">
                    		<div class="row">
                    			<div class="col-xs-7">
                    				<s:actionerror theme="bootstrap" style="!important" cssStyle="margin-left:14px; margin-bottom:0px;"
                    					cssClass="alert-dismissible" onclick="dismiss"/>
                    			</div>
                    			<div class="col-xs-5">
                    				<form action="customerValidate" id="customerValidate" method="post">
		                    			<input type="hidden" name="customerId" value="<s:property value="customerId"/>">
		                    			<input type="hidden" name="wildcardSearch" value="true">
		                    			<button type="submit" class="pull-left btn btn-primary">
		                    				<s:property value="%{getText('neworder.button')}"/>
		                    			</button>
		                    		</form>
		                    		<form action="ediUploadFileSelect" id="customerValidate" method="post">
		                    			<input type="hidden" name="customerID" value="<s:property value="customerId"/>">
		                    			<button type="submit" class="pull-left btn btn-secoundry" style="width:120px">
		                    				<s:property value="%{getText('edi.neworder')}"/>
		                    			</button>
		                    		</form>
                    			</div>
                    		</div>	
                    	</div>
                    </div>
                </div>
        </div>
            
    
    
        </div>
        </div>
        <div class="row">
        	<!-- OPEN ORDERS  -->
            <div class="col-md-12 col-lg-4" >
            <div class="box" >
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;"><s:property value="getText('open_orders')"/></h3>
                <!-- <div class="dropdown pull-right" style="margin-right:10px;">
                        <i class="fa  fa-bars fa-lg dropdown-toggle"  type="button" data-toggle="dropdown"></i>
                        <ul style="margin-left:-90px;background-color:  #656a6b;color:white;" class="dropdown-menu ">
                        <li><a href="#">New Orders</a></li>
                        <li><a href="#">Open Orders</a></li>
                        <li><a href="#">In-Progress Orders</a></li>
                        </ul>
                </div> -->
            </div>
            <div class="box-body no-padding table-responsive" style="min-height: 40px; max-height: 160px; overflow: auto;">
            <table class="table table-condensed table-striped" id="openOrders">
                <thead style="background-color:rgb(173, 194, 238);">
                <tr>
                    <th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
                    <th><s:property value="getText('order.no')"/></th>
                    <th class="text-nowrap"><s:property value="getText('order.date')"/></th>
                    <th><s:property value="getText('status.label')"/></th>
                </tr>
                </thead>
                <tbody>
                <s:if test="customer.customerOrderDetails !=null">
					<s:iterator value="customer.customerOrderDetails" status="orderStatus">
						 <s:if test="%{orderStatus <=3 && #orderStatus.count<=noOfOpenOrdersToDisplay}">
							<tr>
								<td><s:property value="#orderStatus.count" />.</td>
								<td><s:form action="orders_details_page" method="POST"><s:hidden name="orderId" value="%{orderId}"></s:hidden>
								<s:a href="javascript:void(0)" onclick="$(this).closest('form').submit();"><s:property value="orderId" /></s:a></s:form></td>
								<td><s:property value="orderDate" /></td>
								<s:if test="%{orderStatus == 0}">
									<td><s:property value="getText('new_status')"/></td>
								</s:if>
								<s:elseif
									test="%{orderStatus == 1}">
									<td><s:property value="getText('open_status')"/></td>
								</s:elseif>
								<s:elseif
									test="%{orderStatus == 3}">
									<td><s:property value="getText('inprogress_status')"/></td>
								</s:elseif>
								<s:else>
									<td>-</td>
								</s:else>
								<%-- <td style="padding-right: 32px;"><p class="text-right"><s:property value="orderTotal" /></p></td> --%>
							</tr>
						</s:if>
					</s:iterator>
				</s:if>
              </tbody>
            </table>
            </div>
            
            
            <!-- /.box-body -->
            </div>
            </div>
            <!-- RECEIPTS  -->
            <div class="col-md-6 col-lg-4">
            <div class="box" >
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;"><s:property value="getText('receipts_header')"/></h3>
                
            </div>
            
            <!-- /.box-header -->
            <div class="box-body no-padding table-responsive" style="min-height: 40px; max-height: 160px; overflow: auto;">
            <table class="table table-condensed table-striped">
                <thead style="background-color:rgb(173, 194, 238);">
                <tr>
                    <th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
                    <th><s:property value="getText('receipt.id')"/></th>
                    <th class="text-center"><s:property value="getText('receipt.date')"/></th>
                    <th class="text-center"><s:property value="getText('applied.invoice')"/></th>
                </tr>
                </thead>
                <tbody>
                <s:if test="customer.customerReceipts !=null">
					<s:iterator value="customer.customerReceipts" var="rec" status="receiptStatus">
							<tr>
								<td><s:property value="#receiptStatus.count"/>.</td>
								<td><s:form action="receiptDetails" method="POST"><s:hidden name="receiptId" value="%{arPaymNum}"></s:hidden>
								<s:a href="javascript:void(0)" onclick="$(this).closest('form').submit();"><s:property value="arPaymNum" /></s:a></s:form>
								</td>
								<td class="text-center"><s:date name="arPaymDate" format="%{getText('format.date')}"></s:date></td>
								<td class="text-center">
								<a href="InvoiceDetail?invoiceID=<s:property value="id.arInvNum"/>"><s:property value="id.arInvNum" /></a>
								</td>
							</tr>

					</s:iterator>
				</s:if>
                </tr>
                </tbody>
            </table>
            
            </div>
            
            
            <!-- /.box-body -->
            </div>    
            </div>
            <!-- CREDIT NOTES  -->
            <div class="col-md-6 col-lg-4">
            <div class="box" >
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;"><s:property value="getText('credit_notes')"/></h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding table-responsive" style="min-height: 40px; max-height: 160px; overflow: auto;">
            <table class="table table-condensed table-striped">
                <thead style="background-color:rgb(173, 194, 238);">
                	<tr style="background-color:#ADC2EE;">
                    	<th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
                    	<th><s:property value="getText('note.id')"/></th>
                    	<th class="text-center"><s:property value="getText('note.date')"/></th>
                    	<th class="text-center fin-data"><s:property value="getText('note.amt')"/><small>(<s:property value="getText('global.currency')"/>)</small></th>
                	</tr>
                </thead>
                <tbody>
                	<s:if test="customer.customerCreditMemos !=null">
						<s:iterator value="customer.customerCreditMemos" var="credit" status="creditStatus">
							<tr>
								<td><s:property value="#creditStatus.count"/>.</td>
								<td><s:form action="credit_memo_details_page" method="POST"><s:hidden name="crmemoId" value="%{id.crMemoNum}"></s:hidden>
								<s:a href="javascript:void(0)" onclick="$(this).closest('form').submit();"><s:property value="id.crMemoNum" /></s:a></s:form></td>
								<td class="text-center"><s:date name="crMemoDate" format="%{getText('format.date')}"></s:date></td>
								<td class="text-right"><s:property value="%{getText('format.currency.args',{crMemoAmount})}" /></td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>
            </table>
            
            </div>
            
            
            <!-- /.box-body -->
            </div>    
            </div>
            
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="box">
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;"><s:property value="getText('open_invoices')"/></h3>
                 <!-- <div class="box-tools">
                <div class="dropdown " style="margin-right:5px;">
                        
                        <i class="fa  fa-bars fa-lg dropdown-toggle"  type="button" data-toggle="dropdown"></i>
                        <ul style="margin-left:-180px;background-color: #656a6b;" class="dropdown-menu ">
                        <li><a href="#">Overdue by 7-15 Days</a></li>
                        <li><a href="#">Overdue by 15-30 Days</a></li>
                             <li><a href="#">Overdue by 30-45 Days</a></li>
                             <li><a href="#">Overdue by 45-60 Days</a></li>
                             <li><a href="#">Overdue by 60-90 Days</a></li>
                        </ul>
                    </div>
                </div> -->
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding table-responsive">
             <table class="table table-condensed table-striped">
                <thead>
                <tr style="background-color:#ADC2EE;">
                    <th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
                    <th><s:property value="getText('inv.no')"/></th>
                    <th class="text-center"><s:property value="getText('inv.date')"/></th>
                    <th class="text-right"><s:property value="getText('inv.total')"/><small>(<s:property value="getText('global.currency')" />)</small></th>
                    <th class="text-right"><s:property value="getText('receipt.qar')"/><small>(<s:property value="getText('global.currency')" />)</small></th>
                    <th class="text-right"><s:property value="getText('balance.due')"/><small>(<s:property value="getText('global.currency')" />)</small></th>
                    <th><s:property value="getText('age.label')"/><small>(<s:property value="getText('days.label')"/>)</small></th>
                    <th><s:property value="getText('order.id')"/></th>    
                    <th class="text-center"><s:property value="getText('order.date')"/></th>
                    <th class="text-center"><s:property value="getText('sales.agent')"/></th>
                    <th class="text-center"><s:property value="getText('shipment.date')"/></th>
                </tr>
                </thead>
                <tbody>
                <s:if test="customer.customerSite !=null">
					<s:set var="count" value="1" />
					<s:iterator var="sites" value="customer.customerSite" status="siteStatus">
						<s:if test="#sites.customerSiteInvoiceList !=null">
							<s:iterator var="invoices" value="#sites.customerSiteInvoiceList" status="invoiceStatus">
								<s:if test="%{#invoices.invStatus=='1' && #count<=noOfOpenInvoicesToDisplay }">
								<tr>
									<td><s:property value="#count" />.</td>
									<td><s:form action="InvoiceDetail" method="POST"><s:hidden name="invoiceID" value="%{#invoices.arInvNum}"></s:hidden>
									<s:a href="javascript:void(0)" onclick="$(this).closest('form').submit();"><s:property value="#invoices.arInvNum" /></s:a></s:form>
									</td>
									<td class="text-center"><s:date name="#invoices.arInvDate" id="arInvDate"
											format="%{getText('format.date')}"></s:date>
										<s:property value="#arInvDate"/>
									</td>
									<td class="text-right">								
										<s:property value="%{getText('format.currency.args',{#invoices.invAmount})}" /></td>
									<%-- <s:if test="%{#invoices.invStatus=='0'}">
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
									</s:elseif> --%>
									<td class="text-right">								
										<s:property value="%{getText('format.currency.args',{#invoices.invAmount-#invoices.invPendAmount})}" /></td>
									<td class="text-right">								
										<s:property value="%{getText('format.currency.args',{#invoices.invPendAmount})}" /></td>
									<td class="text-center">
										<%-- <s:date name="#invoices.arInvDate"
											format="%{getText('format.date')}" nice="true" /> --%>
										<%-- <s:bean name="rispl.ds.DateUtil" var="dateComp">
												<s:param name="date1" value="#arInvDate"></s:param>
										</s:bean> --%>
										<s:property value="%{invoiceAge[#invoices.orderNum]}"/>
									</td>
											
									<td><s:form action="orders_details_page" method="POST"><s:hidden name="orderId" value="%{#invoices.orderNum}"></s:hidden>
									<s:a href="javascript:void(0)" onclick="$(this).closest('form').submit();"><s:property value="#invoices.orderNum" /></s:a></s:form></td>
									<td class="text-center"><s:date name="#invoices.orderDate"
											format="%{getText('format.date')}" ></s:date></td>
									<td class="text-center"><s:property value="%{orderSalesAgents[#invoices.orderNum]}"/> </td>
									<td class="text-center"><s:date name="#invoices.arInvDate" id="arInvDate"
											format="%{getText('format.date')}"></s:date><s:property value="#arInvDate"/></td>
									
									
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