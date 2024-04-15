<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>SDS | Dashboard</title>
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

<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="assets/css/jquery-ui.css">
<link rel="stylesheet" href="assets/css/Customizations.css">
<script src="Starter_files/jQuery-2.js"></script>
<script src="Starter_files/bootstrap.js"></script>
<script src="Starter_files/app.js"></script>

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
		<%-- <header class="main-header">

    <!-- Logo -->
    <a href="index.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-lg"><img src="assets/SDSlogo.png"></span>
      <span class="logo-mini"><b></b>SDS</span>
      <!-- logo for regular state and mobile devices -->
      
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <form class="navbar-form navbar-left hidden-xs" role="search" style="min-width:60%">
      
              <input type="text" style="min-width:60%;" class="form-control" id="navbar-search-input" placeholder="Enter Customer ID/Name">&nbsp;
              <a href="Customer_Page_b.html"><i style="color:#fff;" class="fa fa-search fa-lg"></i></a>
      
      </form>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
         <li class="dropdown notifications-menu">
            <a aria-expanded="true" href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">1</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 1 notifications</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <div style="position: relative; overflow: hidden; width: auto; height: 200px;" class="slimScrollDiv"><ul style="overflow: hidden; width: 100%; height: 200px;" class="menu">
                  <li>
                    <a href="#">
                      <i class="fa fa-truck text-aqua"></i> 10 new orders today
                    </a>
                  </li>
                </ul><div style="background: none repeat scroll 0% 0% rgb(0, 0, 0); width: 3px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 200px;" class="slimScrollBar"></div><div style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: none repeat scroll 0% 0% rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;" class="slimScrollRail"></div></div>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li> 
          
          
          
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <table style="color:white;text-align:center;margin-right:5px;">
                  <tr><td>Alexander Pierce</td></tr>
                  <tr><td style="text-align:center;"><small>Sales Agent</small></td></tr>
            </table>  
            
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar" style="margin-bottom:-5px;margin-top:-5px;" ><table style="margin:-5px;padding:0px;"><tr><td><i class="fa fa-user "></i></td></tr><tr><td> <i class="fa fa-angle-down fa-lg"></i></td></tr></table></a>
          </li>
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
        <li class="header"><h4><b>MENU</b></h4></li>
        <li class="active treeview">
          <a href="DashBoard_Sales_Agent.html">
            <i class="fa  fa-line-chart"></i> <span>DashBoard</span> <i class="fa pull-right"></i>
          </a>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-truck"></i> <span>Order</span> <i class="fa fa-angle-down pull-right"></i>
          </a>
          <ul class="treeview-menu">
              <li class=""><a href="order_Search.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Order Search</a></li>
              <li class=""><a href="Customer_Search_neworder.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Book Order</a></li>
            <li><a href="Open_order_Search.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Open Orders</a></li>
            <li><a href="Delivered_order_Search.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Delivered Orders</a></li>
            <li><a href="Cancelled_order_Search.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Cancelled Orders</a></li>
            <li><a href="Returned_order_Search.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Returned Orders</a></li>
              <li><a href="Pending_Orders.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Pending Orders</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-file-text"></i> <span>Invoice</span> <i class="fa fa-angle-down pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li><a href="invoice_Search.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Invoice Search</a></li>
            <li><a href="Open_invoice.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Open Invoices</a></li>
            <li><a href="Payment.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Post Payment</a></li>
          </ul>
        </li>
        <li class=" treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Claim</span> <i class="fa fa-angle-down pull-right"></i>
          </a>
          <ul class=" treeview-menu">
            <li class=""><a href="claim_Search.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Claim Search</a></li>
            <li class=""><a href="Generate_Claim.html?#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Register Claim</a></li>
            <li class=""><a href="Awaiting_Claim.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Approve Claim</a></li>
            <li class=""><a href="Approve_Return.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Accept Claim</a></li>
                <li class=""><a href="Rejected_claim_Search.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Rejected Claims</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i> <span>Customer</span> <i class="fa fa-angle-down pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li class=""><a href="Customer_Search.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Customer Search</a></li>
<!--            <li><a href="Add_New.html"><i class="fa fa-circle-o"></i> Add New</a></li>-->
          </ul>
        </li>
        
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside> --%>

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">

    <!-- Main content -->
    <section class="content">
<!--        <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b>Find and view details of registered claims</b></small>-->
    <div class="row">
        <div class="col-md-12">
            <div class="box box-primary">
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;"> <s:property value="getText('receivables.label')"/> </h3> - &nbsp;<i><h3 class="box-title" style="color: #226e71;" id="getRangeLabel"></h3></i>
                <div class="dropdown pull-right" style="margin-right:10px;">
                        <i class="fa  fa-bars fa-lg dropdown-toggle"  type="button" data-toggle="dropdown"></i>
                        <ul id="range_invoices" style="margin-left:-150px;background-color: #656a6b;" class="dropdown-menu ">
                        <li><a id="week"> <s:property value="getText('current.week')"/> </a></li>
                        <li><a id="month"> <s:property value="getText('current.month')"/>  </a></li>
                        <li><a id="quarter">  <s:property value="getText('current.quarter')"/>  </a></li>
                        <li><a id="year">  <s:property value="getText('current.year')"/>  </a></li>
                        <li><a id="NONE">  <s:property value="getText('none')"/>  </a></li>
                    	</ul>    
                </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding table-responsive  pre-scrollable" style="max-height:500px;">
              <table class="table table-striped" id="receivables_table">
                <tbody>
                <tr style="background-color:#ADC2EE;">
                    <th style="width: 10px"> <s:property value="getText('table.head.SNo')"/> </th>
                    <th style="text-align:right;"> <s:property value="getText('inv.no')"/> </th>
                    <th style="text-align:center;"> <s:property value="getText('inv.date')"/> </th>
                    <th  style="text-align:right;"> <s:property value="getText('inv.total')"/>(<s:property value="getText('global.currency')" />)</th>
                    <th> <s:property value="getText('order.id')"/> </th>
                    <th> <s:property value="getText('customer.name')"/> </th>
                    <th style="text-align:right;"> <s:property value="getText('table.head.qty')"/> </th>
                    <th> <s:property value="getText('status.label')"/> </th>
                    <th style="text-align:right;"> <s:property value="getText('age.label')"/> </th>
                    <th  style="text-align:right;"> <s:property value="getText('receipts_header')"/>(<s:property value="getText('global.currency')" />)</th>
                    <th  style="text-align:right;"> <s:property value="getText('balance.due')"/>(<s:property value="getText('global.currency')" />)</th>
                    
                </tr>
                <s:set var="totalinvoice" value="0"/>
                <s:set var="totalrcpt" value="0"/>
                <s:set var="totalbaldue" value="0"/>
                <s:iterator  value="invoices" status="itStatus" >  
                <s:set var="totalinvoice" value="%{#totalinvoice + invoiceTotal}" />
                <s:set var="totalrcpt" value="%{#totalrcpt + (invAmount-invPendAmount)}" />
                <s:set var="totalbaldue" value="%{#totalbaldue + balanceDue}" />

 
				<fieldset>  
				<tr>
				<td><s:property value="#itStatus.count" />.</td>
				<%-- <td style="text-align:right;" id="invoice_id"><a href="#"><s:property value="id.invoiceId"/> </a>  </td> --%>
				
				<td style="text-align:right;"><a href="InvoiceDetail?invoiceID=<s:property value="id.invoiceId"/>"><s:property value="id.invoiceId"/></a>   </td>
				<td style="text-align:center;"><s:date name="id.invoiceDate" format="%{getText('format.date')}" /> </td>
				<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{invoiceTotal})}"/> </td>
				<td><s:property value="orderId"/>  </td>
				<td><s:property value="customerName"/>  </td>
				<td style="text-align:right;"><s:property value="quantity"/>  </td>
				<td><s:property value="invoiceStatus"/>  </td>
				<td style="text-align:right;"><s:property value="age"/>  </td>
				<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{invAmount-invPendAmount})}"/></td>
				<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{balanceDue})}"/>  </td>
				</tr> 
				</fieldset>  
				</s:iterator>  
               
              </tbody>
            </table>
            </div>
            
            <!-- mudassir -->
 			<!-- Add Totals in Dashboard @sharanya -->
			<div class="progress" style="height: 2px; margin-bottom: 2px;">
				<div class="progress-bar" style="width: 100%; background-color: #ADC2EE;"></div>
			</div>
			<div class="row">
			<div class="col-xs-4 col-md-5 col-xs-4 pull-right">
			<div class="table-responsive">
            <table class="table table-condensed">
              <tbody>
              	<tr><td class="pull-right"><s:property value="getText('invoice.total')"/>: <s:property value="getText('global.currency')"/> <b><s:property value="%{getText('format.currency.args',{#totalinvoice})}"/>&nbsp&nbsp&nbsp&nbsp&nbsp</b></td></tr>
               	<tr><td class="pull-right"><s:property value="getText('receipt.total')"/>: <s:property value="getText('global.currency')"/> <b><s:property value="%{getText('format.currency.args',{#totalrcpt})}"/>&nbsp&nbsp&nbsp&nbsp&nbsp</b></td></tr>
               	<tr><td class="pull-right"><s:property value="getText('balancedue.total')"/>: <s:property value="getText('global.currency')"/> <b><s:property value="%{getText('format.currency.args',{#totalbaldue})}"/>&nbsp&nbsp&nbsp&nbsp&nbsp</b></td></tr>
              </tbody>
            </table>
            </div>
            </div>
            </div>
		  
         </div>  
        </div> 
    </section>
     
    <!-- /.content -->
  </div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->


		<!-- Control Sidebar -->
		<!-- <aside class="control-sidebar control-sidebar-dark"
			style="position: fixed; height: auto;">
			Create the tabs
			
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-home"></i></a></li>
      <li class=""><a href="#control-sidebar-settings-tab" data-toggle="tab" aria-expanded="false"><i class="fa fa-gears"></i></a></li>
    </ul>

			Tab panes
			<div class="tab-content">
				Home tab content
				<div class="tab-pane active" id="control-sidebar-home-tab">
					<h3 style="text-align: center;" class="control-sidebar-heading">Alexander
						Peirce</h3>
					<h5 style="text-align: center;" class="control-sidebar-subheading">Sales
						Agent</h5>
					<div class="progress" style="height: 3px;">
						<div class="progress-bar" style="width: 100%;"></div>
					</div>

					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-key bg-blue"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Change Password</h4>


								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-language bg-blue"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Change Language</h4>


								</div>
						</a></li>
						<div class="row" style="margin: 5px; height: 3px;">
							<div class="progress" style="height: 3px;">
								<div class="progress-bar" style="width: 100%;"></div>
							</div>
						</div>
						<li><a href="login.html" style="color: #ffffff;"><button
									class="btn btn-block btn-primary" style="margin: 0 auto;"
									type="button">Logout</button></a></li>

					</ul>
					/.control-sidebar-menu

					       <h3 class="control-sidebar-heading">Tasks Progress</h3>


				</div>
				/.tab-pane
				Stats tab content
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				/.tab-pane
				Settings tab content
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>
					</form>
				</div>
				/.tab-pane
			</div>
		</aside>
		/.control-sidebar
		Add the sidebar's background. This div must be placed
       immediately after the control sidebar
		<div style="position: fixed; height: auto;" class="control-sidebar-bg"></div> -->
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->
	<form action="invoices_dashboard" name="hidden_data_for_range" method="post">
    	<input type="hidden" name="range"/>
    	<input type="hidden" name="status"/>
	</form>
	<form action="InvoiceDetail" name="invoice_details_data" method="post">
    		<input type="hidden" name="invoiceID"/>
	</form>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->


	<!-- ChartJS 1.0.1 -->

	</script>
	<script>
$(function(){
    var invoice_range="quarter";
    
    $("#range_invoices li").click(
        function(evt){
        	invoice_range=evt.target.id;
            document.forms["hidden_data_for_range"].range.value=invoice_range;
            document.forms["hidden_data_for_range"].status.value="<s:property value="status"/>";
            document.forms["hidden_data_for_range"].submit();
        }
    ); 
    
    $("#receivables_table #invoice_id a").click(function(evt){
		 document.forms["invoice_details_data"].invoiceID.value=evt.currentTarget["innerText"];
		 document.forms["invoice_details_data"].submit();
	});
   
    	  
});    
    
    
</script>
 <script type="text/javascript">
   var searchRange = "${range}";
	$(function(){
             if(searchRange=="week"){
            	 document.getElementById("getRangeLabel").innerHTML = "<s:property value="getText('current.week')" />";
             }
             else if(searchRange=="month"){
            	 document.getElementById("getRangeLabel").innerHTML = "<s:property value="getText('current.month')" />";
             }
             else if(searchRange=="quarter"){
            	 document.getElementById("getRangeLabel").innerHTML = "<s:property value="getText('current.quarter')" />";
             }
             else if(searchRange=="year"){
            	 document.getElementById("getRangeLabel").innerHTML = "<s:property value="getText('current.year')" />";
             }
             else if(searchRange=="NONE"){
            	 document.getElementById("getRangeLabel").innerHTML = "<s:property value="getText('none')" />";
             }
   }); 
  </script>
	<script type="text/javascript">
    $('#dashboard').addClass('active');
  </script>
</body>
</html>

</html>