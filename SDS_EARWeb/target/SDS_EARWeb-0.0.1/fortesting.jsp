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
<title>SDS | Returned Orders</title>
<!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" type="text/css"/>
	<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css" type="text/css"/>
	<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css" type="text/css" />
    <link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="assets/css/jquery-ui.css">
    <link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
    <!-- <link rel="stylesheet" href="assets/plugins/daterangepicker/daterangepicker-bs3.css">
    <link rel="stylesheet" href="assets/css/popup.css"> -->
    <link rel="stylesheet" href="assets/css/Customizations.css">

  <script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
<!-- jQuery 2.2.0 -->
<!-- Select2 -->
<script src="assets/plugins/select2/select2.full.min.js"></script>
<!-- InputMask 
<script src="assets/plugins/input-mask/jquery.inputmask.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>-->
<script src="assets/plugins/jQueryUI/jquery-ui.js"></script>
<!-- date-range-picker 
<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>-->
<!-- bootstrap datepicker 
<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>-->
<script src="assets/plugins/datepicker/datepicker-bootstrap.js"></script>
<script src="assets/moment.min.js"></script>
 <script type="text/javascript" src="custom/dateRangeFilter.js"></script>
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
		


<header class="main-header">

	<a href="homePage" class="logo">
		<span class="logo-lg">
			<img alt="logo" src="assets/SDSlogo.png">
		</span>
		<span class="logo-mini"><b>SDS</b></span>
	</a>

	<nav class="navbar navbar-static-top" role="navigation">

		<a href="javascript:void(0)" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<form class="navbar-form navbar-left hidden-xs " style="min-width: 50%; padding:3px; " 
			role="search" id="topbarCustomerLookup"
			action="customerLookup" method="post">
			<input type="hidden" name="wildcardSearch" value="true">
			<input type="text" style="min-width: 50%;" class="form-control"
				id="topbarCustInfo" name="custInfo"
				placeholder="Customer ID/Name"
				autocomplete="off" required="required">&nbsp;
			<button type="submit" class="btn-link">
				<i style="color: white;" class="fa fa-search fa-lg"></i>
			</button>
			<!-- <a href="javascript:void(0)">
				<i style="color: #fff;" class="fa fa-search fa-lg" id="custsearch"></i>
			</a> -->

		</form>

		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
			<!-- Commented by sharanya  -->
				<!-- <li class="dropdown notifications-menu"><a aria-expanded="true"
					href="#" class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fa fa-bell-o"></i> 
				</a>
					<ul class="dropdown-menu">
						<li class="header"></li>
						<li>
							inner menu: contains the actual data
							<div
								style="position: relative; overflow: hidden; width: auto; height: 200px;"
								class="slimScrollDiv">
								<ul style="overflow: hidden; width: 100%; height: 200px;"
									class="menu">
									
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
					</ul></li> -->



				<!-- User Account Menu -->
				<li class="dropdown user user-menu text-center">
					<a href="javascript:void(0)" data-toggle="control-sidebar" style="padding: 5px 10px;">
						SDS-Admin<br>
						<small>Administrator</small>
					</a>
				</li>
				<!-- Control Sidebar Toggle Button -->
				<li class="dropdown user user-menu text-center">
					<a href="javascript:void(0)" data-toggle="control-sidebar" style="padding: 5px 10px;">
						<i class="fa fa-user "></i><br>
						<i class="fa fa-angle-down fa-lg"></i>
					</a>
				</li>
			</ul>
		</div>
	</nav>
</header>



<aside class="control-sidebar control-sidebar-dark"
	style="position: fixed; height: auto;">

	<div class="tab-content">
		<!-- Home tab content -->
		<div class="tab-pane active" id="control-sidebar-home-tab">
			<h3 style="text-align: center;" class="control-sidebar-heading">
				SDS-Admin
			</h3>
			<small>
			<div class="row">
				<div class="col-xs-6 text-right">Emp ID:</div>
				<div class="col-xs-6" style="color:white; padding-left: 0px;">4208</div>
			</div>
			<div class="row">
				<div class="col-xs-6 text-right">Emp Role:</div>
				<div class="col-xs-6" style="color:white; padding-left: 0px;">Administrator</div>
			</div>
			<br>
			
			</small>
			<br>
			<div class="progress" style="height: 3px;">
				<div class="progress-bar" style="width: 100%;"></div>
			</div>

			<ul class="control-sidebar-menu">
				<li><a href="changepswd"> <i
						class="menu-icon fa fa-key bg-blue"></i>

						<div class="menu-info">
							<h4 class="control-sidebar-subheading">Change Password</h4>


						</div>
				</a></li>
				
				<!-- commented by sharanya  -->
				<!-- <li><a href="javascript:void(0)"> <i
						class="menu-icon fa fa-language bg-blue"></i>
						<div class="menu-info">
							<h4 class="control-sidebar-subheading">Change Language</h4>
						</div>
				</a></li> -->
				<div class="row" style="margin: 5px; height: 3px;">
					<div class="progress" style="height: 3px;">
						<div class="progress-bar" style="width: 100%;"></div>
					</div>
				</div>
				<!-- <li><a href="logout" style="color: #ffffff;"><button
							class="btn btn-block btn-primary" style="margin: 0 auto;"
							type="button">Logout</button></a></li> -->
				<li style="margin: 20px;">
					
					<button	class="btn btn-block btn-primary" style="margin: 0 auto;"
							onclick="location.href='/Salam_SDS/logout.action';">
						Logout</button>
				</li>

			</ul>
		</div>
	</div>
</aside>
<!-- /.control-sidebar -->
<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
<div style="position: fixed; height: auto;" class="control-sidebar-bg"></div>

<script type="text/javascript" src="assets/plugins/jQueryUI/jquery-ui.js"></script>
<script type="text/javascript">
	$(function(){ 
		$("#topbarCustInfo").autocomplete({
			autoFocus: true,
			minLength: 2,
			delay: 500,
			source: function(request, response) {
				$.ajax({
				  url: "customerLookupAjax",
				  timeout: 180000,
				  dataType: "json",
				  data: {
				    custInfo: request.term,
				    wildcardSearch: true,
				    maxCustomers: 10
				  },
				  success: function(data) {
				  	response(data);
				  },
				  error: function(jqXHR, textStatus, errorThrown){
				  	if(isPageBeingRefreshed) return;
				  	response();
				  	alert(textStatus);                  
	              }
				});
			},
			response: function( event, ui ) {
				$(this).removeClass("ui-autocomplete-loading");
			},
			select: function(event, ui) {
				$(this).val(ui.item.customerHeader.customerHeaderPK.custId);
				$("#topbarCustomerLookup").get(0).submit();
				return false;
			}
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append( "<div>" + item.customerHeader.ctNm + " - " + item.customerHeader.customerHeaderPK.custId + "</div>" )
	        .appendTo( ul );
	    };
    });
</script>
	
<script type="text/javascript">
	$(function(){
		//Disable cut copy paste
	    /* $("body").bind("cut copy paste", function (e) {
	    	console.log(e.type+" is disabled");
	        e.preventDefault();
	    }); 
	   
	    //Disable mouse right click
	    $("body").on("contextmenu", function(e){
	    	console.log("Right Click is disabled");
	        return false;
	    });
	    
	    //Disable double click
	    $("body").on("dblclick", function(e){
	    	console.log("Double Click is disabled");
    		e.preventDefault();
  		}); */
  		
  		//Saideep:
  		//Global Ajax Error for handling session timeout, use global:false in your ajax calls to skip this global function
  		$(document).bind("ajaxError", function(event, jqXHR, ajaxSettings, thrownError){
			if(jqXHR.status!=null && jqXHR.status==401)
			{
				preventBack = false;
				alert('Session has timed out. Please re-login.');
	        	//location.reload(true);
	        	window.location.href="/Salam_SDS/logout.action";
			}
		});
	});
</script>



<script type="text/javascript">
	
	var isPageBeingRefreshed = false;
	$(window).on('beforeunload', function(){
   		isPageBeingRefreshed = true;
	});
						
</script>

		





<style>
	.padding-left-4px{
		padding-left:4px;
	}
	.nav>li.disabled>a{
		color: #777;
	}
</style>
<aside class="main-sidebar">
	<section class="sidebar">
		<ul class="sidebar-menu nav">
			<li class="header" style="color:#01ffdd;">
				
				<h4><b>QATAR
				<!-- store name has to display in the book order screen @sharanya start -->
				<span style="white-space: normal; font-size:18px"><b></b></span>
				<!-- sharanya end -->
				 </b></h4>
			</li>
			<!-- DASHBOARD PERMISSION START -->
			
				<li class="treeview" id="dashboard">
					<a href="dashboard"> 
			
			
				<i class="fa fa-line-chart"></i> 
					<span>DashBoard</span> 
				</a>
			</li>
			<!-- END -->
			
			<li class="treeview" id="order">
				<a href="javascript:void(0)">
					<i class="fa fa-truck"></i>
					<span>Order</span> 
					<i class="fa fa-angle-down pull-right"></i>
				</a>
				<ul class="treeview-menu nav">
					<!-- ORDER SEARCH PERMISSION START -->
					
						<li id="ordersearch">
							<a href="order_Search">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Order Search
							</span>
						</a>
					</li>
					<!-- END -->
					
					<!-- NEW ORDER PERMISSION START -->
					
						<li id="bookorder">
							<a href="newOrder">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								New Order
							</span>
							
						</a>
					</li>
					<!-- END -->
					
					<!-- OPEN ORDERS PERMISSION START -->
					
						<li id="openorders">
							<a href="openorders">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Open Orders
							</span>
						</a>
					</li>
					<!-- END -->
					
					<!-- DELIVERED ORDERS PERMISSION START -->
					
						<li id="deliveredorders">
							<a href="deliveredOrders">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Delivered Orders
							</span>
						</a>
					</li>
					<!-- END -->
					
					<!-- CANCELLED ORDERS PERMISSION START -->
					
						<li id="cancelledorders">
							<a href="cancelledOrders">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Cancelled Orders
							</span>
						</a>
					</li>
					<!-- END -->
					
					<!-- RETURNED ORDERS PERMISSION START -->
					
						<li id="returnedorders">
							<a href="returnedOrders">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Returned Orders
							</span>
						</a>
					</li>
					<!-- END -->
					
					<!-- PENDING ORDERS PERMISSION START -->
					
						<li id="pendingorders">
							<a href="savedOrder">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Saved Orders
							</span>
						</a>
					</li>
					<!-- END -->
				</ul>
			</li>	
			<li class="treeview" id="invoice">
				<a href="javascript:void(0)">
					<i class="fa fa-file-text"></i>
					<span>Invoice</span>
					<i class="fa fa-angle-down pull-right"></i>
				</a>
				<ul class="treeview-menu nav">
					<!-- INVOICE SEARCH PERMISSION START -->
					
						<li id="invoicesearch">
							<a href="invoices">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Invoice Search
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- OPEN INVOICES PERMISSION START -->
					
						<li id="openinvoices">
							<a href="OpenInvoices">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Open Invoices
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- POST PAYMENT PERMISSION START -->
					
					
						<li class="disabled hidden" id="postPaymentSearch">
							<a href="javascript:void(0)">
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Post Payment
							</span>
						</a>
					</li>
					<!-- END -->
				</ul>		
			</li>
			<li class="treeview" id="claim">
				<a href="javascript:void(0)">
					<i class="fa fa-dashboard"></i>
					<span>Claim</span>
					<i class="fa fa-angle-down pull-right"></i>
				</a>
				<ul class="treeview-menu nav">
					<!-- CLAIM SEARCH PERMISSION START -->
					
						<li id="claimsearch">
							<a href="claim_search">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Claim Search
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- REGISTER CLAIM PERMISSION START -->
					
						<li id="registerclaim">
							<a href="generateClaim">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								New Claim
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- APPROVE CLAIM PERMISSION START -->
					
						<li id="approveclaim">
							<a href="approveClaim">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Approve Claim
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- ACCEPT CLAIM PERMISSION START -->
					
						<li id="acceptclaim">
							<a href="claimAccept">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Accept Claim
							</span>
						</a>
					</li>
					<!-- END -->
				
					<!-- REJECT CLAIM PERMISSION START -->
					
						<li id="rejectedclaims">
							<a href="rejectClaim">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Rejected Claims
							</span>
						</a>
					</li>
					<!-- END -->
				</ul>
			</li>		
			<li class="treeview" id="customer">
				<a href="javascript:void(0)">
					<i class="fa fa-user"></i>
					<span>Customer</span>
					<i class="fa fa-angle-down pull-right"></i>
				</a>
				<ul class="treeview-menu nav">
					<!-- CUSTOMER SEARCH PERMISSION START -->
					
						<li id="customersearch">
							<a href="customerSearch">
					
					
							<i class="fa fa-angle-right"></i>
							<span class="padding-left-4px">
								Customer Search
							</span>
						</a>
					</li>
					<!-- END -->		
				</ul>
			</li>
			<!-- INVENTORY PERMISSION START -->
			
				<li class="treeview" id="inventorylookupitem">
					<a href="Inventory_Lookup"> 
			
			
				<i class="fa fa-cubes"></i> 
					<span>Inventory Lookup</span> 
				</a>
			</li>
			<!-- END -->	
			
			
				<!-- Chiranjibee Comments To Add Admin Panel To Menubar page -->
				<li class="treeview" id="admin">
					<a href="javascript:void(0)">
						<i class="glyphicon glyphicon-cog"></i>
						<span>Admin Panel</span>
						<i class="fa fa-angle-down pull-right"></i>
					</a>
					<ul class="treeview-menu nav">
						<!-- ROLES PERMISSION START -->
						
							<li id="roles">
								<a href="rolesConfig">
						
						
								<i class="fa fa-angle-right"></i>
								<span class="padding-left-4px">
									Roles & Permissions
								</span>
							</a>
						</li>
						<!-- END -->
						
						<!-- PARAMETER PERMISSION START -->
						
							<li id="parameters">
								<a href="paramterConfig">
						
						
								<i class="fa fa-angle-right"></i>
								<span class="padding-left-4px">
									Parameter Management
								</span>
							</a>
						</li>
						<!-- END -->
						
						<!-- CUSTOMER SEGMENTS PERMISSION START -->
						
							<li id="customersegments">
								<a href="customerConfig">
						
						
								<i class="fa fa-angle-right"></i>
								<span class="padding-left-4px">
									Customer Enrichment
								</span>
							</a>
						</li>
						<!-- END -->
						<!-- Chiranjibee Comments To Add Admin Panel To Menubar page -->
						
						<!-- RESET PASSWORD PERMISSION START -->
						
							<li id="resetpassword">
								<a href="resetPasswordPage">
						
						
								<i class="fa fa-angle-right"></i>
								<span class="padding-left-4px">
									Reset Password
								</span>
							</a>
						</li>
						<!-- END -->
					</ul>
				</li>
				
			
				
		</ul>
		
		
			
		
	</section>
	<div style="position: absolute; bottom: 2%; left: 8%; color: gray; word-break: break-word;">
		<small>Build:&nbsp; 20180118 V1.0</small>
	</div>
</aside>



		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">



			<!-- Main content -->
			<section class="content">


				<!-- Your Page Content Here -->

				<div class="row" style="margin: 5px; margin-top: -15px;">
					<!-- Your Page Content Here -->
					<label style="font-size: 25px; color: #226e71;"> Returned Order Search</label> <small
						class="pull-right"
						style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><b>Search for returned orders using order or customer details</b></small> <br>

					<div class="nav-tabs-custom "
						style="min-height: 100px; min-width: 700px; max-width: 750px;">
						<ul style="margin-left: 15px;" class="nav nav-pills">
							<li class="active"><a data-toggle="tab" href="#tab_1"
								aria-expanded="true">Order</a></li>
							<!-- <li class=""><a data-toggle="tab" href="#tab_2" aria-expanded="false">Invoice</a></li> -->
							<li class=""><a data-toggle="tab" href="#tab_3"
								aria-expanded="false">Customer</a></li>
						</ul>

						<table class="table"
							style="width: 650px;; margin-left: 15px; border-width: 0px;">
							<tr>
								<td style="width: 80%; border-top: 1px solid #ffffff;">
									<div style="height: 100px;" class="tab-content">
										<div id="tab_1" class="tab-pane active">
											<table style="width: 100%;">
												<tr>
													<td style="width: 10%;">Order ID:</td>
													<td style="width: 30%;"><input id="orderID"
														name="orderID" style="margin: 1px; width: 52%;"
														type="text" class="form-control"></td>
												</tr>
												<tr>
													<td style="width: 10%;">Order Date Range:</td>
													<td style="width: 30%;">

														<table>
															<tr>
																<td><input style="margin: 1px; width: 95%;"
																	type="text" placeholder="From" class="form-control"
																	id="order_date_from"></td>
																<td><input style="margin: 1px; width: 95%;"
																	type="text" placeholder="To" class="form-control"
																	id="order_date_to"></td>
															</tr>
														</table>

													</td>
												</tr>
												<tr>
													<td style="width: 10%;">Order Totals:</td>
													<td style="width: 30%;">
														<table>
															<tr>
																<td><input id="OrderTotalFrom"
																	style="margin: 1px; width: 95%;" type="text"
																	placeholder="From" class="form-control" onkeypress='return isNumberKey(event);' onpaste="return false;"></td>
																<td><input id="OrderTotalTo"
																	style="margin: 1px; width: 95%;" type="text"
																	placeholder="To" class="form-control" onkeypress='return isNumberKey(event);' onpaste="return false;"></td>
															</tr>
														</table>


													</td>
												</tr>
											</table>
										</div>

										<!-- /.tab-pane -->
										
										<!-- /.tab-pane -->
										<div id="tab_3" class="tab-pane">
											<table style="width: 100%;">
												<tr>
													<td style="width: 10%;">Customer ID/Name:</td>
													<td style="width: 30%;"><input id="customerInfo"
														style="margin: 1px; width: 52%;" type="text"
														class="form-control"></td>
												</tr>
												<tr>
													<td style="width: 10%;">Item ID/Description:</td>
													<td style="width: 30%;"><input id="RtnItemId"
														style="margin: 1px; width: 52%;" type="text"
														class="form-control"></td>
												</tr>
											</table>
										</div>
										<!-- /.tab-pane -->
										<!-- /.tab-pane -->

										<!-- /.tab-pane<div id="divCheckbox" style="display: none;"> -->
									</div>
								</td>
								<td style="border-top: 1px solid #ffffff;"><form
										class="radio inline ">
										<div class="form-group"
											style="display: none;>  
                <label class="radio inline"><input id="99" type="radio" name="Invoice_Search" value="All" checked>
                <span>All</span></label><br>
                <label class="radio inline"><input id="1" type="radio" name="Invoice_Search" value="Open Invoices">
                <span>Open</span></label><br>  
                <label class="radio inline"><input id="0" type="radio" name="Invoice_Search" value="Closed Invoices">
                <span>Closed </span></label>                    
                </div>    
                    </form>
                     <button onclick="searchInvoice()" class="btn pull-right" id="returnOrderSearch"
									style="margin-left: 0px; color: #ffffff; width: 100px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; border: 1px solid; display: inline-block;"
									type="button">
									 Search&nbsp;<i class="fa"></i>
								</button></td></tr></table>
           
                <lab name="Info_Text" style="font-style:italic;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Atleast one field is mandatory</lab>
                
            </div>
            <!-- /.tab-content -->
              
            
       <!--  manish code -->
            
                 </div> 
             <div class="row">
			<div class="col-md-12">
		
			
              <!-- END
          -->
            <!-- /.box-body --> 
        <div class="box box-primary">
            
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;">Returned Orders</h3> &nbsp;<i><h3 class="box-title" style="color: #226e71;" id="Current_Date_Range"></h3></i>
                <div class="box-tools">
               <div class="dropdown pull-right" style="margin-right:10px;">
                        
                       
                        <i class="fa  fa-bars fa-lg dropdown-toggle"  type="button" data-toggle="dropdown"></i>
                        <ul style="margin-left:-90px;background-color:  #656a6b;color:white;" class="dropdown-menu ">
                        <li><a href="javascript:void(0)" class="week" id="week" onclick='rangeFilter("returndate","returnorderList",this.id);'>Week To Date</a></li>
                        <li><a href="javascript:void(0)" class="month" id="month" onclick='rangeFilter("returndate","returnorderList",this.id);'>Month To Date</a></li>
                        <li><a href="javascript:void(0)"  class="quarter" id="quarter" onclick='rangeFilter("returndate","returnorderList",this.id);'>Quarter To Date</a></li>
                        <li><a href="javascript:void(0)"  class="year" id="year" onclick='rangeFilter("returndate","returnorderList",this.id);'>Year To Date</a></li>
                        <li><a href="javascript:void(0)" class="none" id="none" onclick='rangeFilter("returndate","returnorderList",this.id);'>None</a></li>
                        </ul>
                            
                </div>
                </div>
            </div>
            
            <!-- /.box-header -->
            <div class="box-body no-padding table-responsive">
                
              	<table class="table table-striped table-condensed" id="returnTable">

							
							<thead>
								<tr style="background-color: #ADC2EE;">
									<th style="width: 10px">#</th>
									<th>Order ID</th>
									<th>Return Order ID</th>
									<th id="returndate">Return Order Date</th>
									<th class="text-right">Return Order Total(QAR)</th>
									<th class="text-right">Customer ID</th>
									<th>Customer Name</th>
									<th>Sales Agent</th>
									<th>Reason Code</th>
							   </tr>
							</thead>
							
							<tbody id="returnorderList">

							</tbody>
						
						</table>
							
            </div>
     
            <!-- /.box-body -->
          </div>        
    </section>
       
 <!--  <button onclick="markAsDelivered()" class="btn pull-right" id="markDelivered"
				style="margin-left: 0px; color: #ffffff; width: 200px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; border: 1px solid; display: inline-block;"
						type="button">Mark As Delivered&nbsp;<i class="fa"></i>
			</button>  -->
    <!-- /.content -->
  </div> 

	</div>
	<!-- ./wrapper -->
<!-- <form action="DeliveredOrders_details_page" name="hidden_order_data" method="post">
    <input type="hidden" name="empId"/>
    <input type="hidden" name="orderId"/>
</form> -->

	<!-- when no invoices found @mallikarjun -->
		<div class="modal fade bs-example-modal-sm" id="noOrdFnd"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">No Orders found for the entered information</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal">Ok&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>
		
		<script>
		var range = "";
  $(function() {
    	

		/* AJAX item Search */
	$("input#RtnItemId").autocomplete({
	/*   Enabling the Qty textbox after selecting any item by firing autocomplete select event */
		source : function(request, response) {

			//$('.ui-autocomplete-loading').removeClass("ui-autocomplete-loading");

			if (request.term.length > 2) {
				/*  document.getElementById("loadText").style.display ="none"; */
				//document.getElementById("load").style.display = "block";  // show the loading image.
				//$('.ui-autocomplete-loading').addClass("ui-autocomplete-loading");
				$("#RtnItemId").addClass("ui-autocomplete-loading");

				$.ajax({
					url : "searchItemAction",
					type : "POST",
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(jsonResponse) {
						response(jsonResponse.list);
						/*   document.getElementById("load").style.display = "none"; */
						$("#RtnItemId").removeClass("ui-autocomplete-loading");
					}

				});
			} else {
				/* document.getElementById("load").style.display = "none"; */
				$("#RtnItemId").removeClass("ui-autocomplete-loading");
			}
		}
	});
	
	$("#RtnItemId").autocomplete("widget").attr('style',
			'max-height: 300px; overflow-y: auto; overflow-x: hidden;')//to get a scroll bar


    
      
      var dateFormat= "dd-M-yyyy";
      var datePickerProp = {todayBtn: "linked", autoclose: true, todayHighlight: true, format:dateFormat,endDate: new Date(),autoclose: true};
       $('#order_date_from').datepickerBS(datePickerProp);
      $('#order_date_to').datepickerBS(datePickerProp);
      $('#datepicker3').datepickerBS(datePickerProp);
      $('#datepicker4').datepickerBS(datePickerProp);
     /*  //Date range picker
      $('#reservation').daterangepicker();
      $('#reservation1').daterangepicker();
      //Date range picker with time picker
      $('#reservationtime').daterangepicker({
        timePicker: true,
        timePickerIncrement: 30,
        format: 'MM/DD/YYYY h:mm A'
      });
      //Date range as a button
      $('#daterange-btn').daterangepicker({
        ranges: {
          'Today': [moment(), moment()],
          'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days': [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month': [moment().startOf('month'), moment().endOf('month')],
          'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate: moment()
      }, function(start, end) {
        $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
      });  */

     
    });
  </script>
  
  
    <script>
    function searchInvoice(){
    	
    	 var activeTab = $('.nav-tabs-custom div.tab-pane.active').attr('id');
    	 
    	//validating the fields not to be empty
    	  function isValidDataInTab(activeTab)
    	    {
    	    	var result= false;
    	    	/* $('#invoiceList').empty();
    	    	document.getElementById("noOfInv").innerHTML="0"; */
    	    	
    	    	
    	    	if(activeTab=='tab_1')
    	    	{	
    	    		if($('#orderID').val().length >= 1
    	    			||$('#order_date_from').val().length>=1 || $('#order_date_to').val().length>=1
    	    			||$('#OrderTotalFrom').val().length>=1 || $('#OrderTotalTo').val().length>=1)
    	    			{
    	    			result =true;
    	    			}else
    	    			{
    	    				result=false;
    	    			} 		
    	    	}else if(activeTab=='tab_2')
    	    	{
    	    		if($('#invoiceID').val().length >= 1
    	    				||$('#order_date_from').val().length>=1 || $('#order_date_to').val().length>=1
    	    				||$('#InvoiceTotalFrom').val().length>=1 || $('#InvoiceTotalTo').val().length>=1)
    	        			{
    	        			result =true;
    	        			}else
    	        			{
    	        				result =false;
    	        			}
    	    	}else if(activeTab=='tab_3')
    	    	{
    	    		if($('#customerInfo').val().length >= 1
    	        			||$('#RtnItemId').val().length>=1 )
    	        			{
    	        			   result =true;
    	        			}else
    	        			{
    	        				result= false;
    	        			}
    	    	}
    	    	if(!result)
    	    	{ 
    	    		$("lab[name='Info_Text']").css("color", "red");
    	    		
    	    	}else{
    	    		{ 
    	        		$("lab[name='Info_Text']").css("color", "black");
    	        		
    	        	}
    	    	}
    	    	return result;
    	    }
    	if(isValidDataInTab(activeTab)){
    		document.forms["hidden_order_data"].invoiceStatus.value=$('input[name="Invoice_Search"]:checked').attr('id');
    		document.forms["hidden_order_data"].invType.value= $('input[name=Invoice_Search]:checked').val(); 
    		document.forms["hidden_order_data"].activeTab.value= activeTab;
    		
    		if(activeTab === "tab_1"){
    			document.forms["hidden_order_data"].orderID.value=$('#orderID').val();
    			document.forms["hidden_order_data"].order_date_from.value=$('#order_date_from').val();
    			document.forms["hidden_order_data"].order_date_to.value=$('#order_date_to').val();
    			document.forms["hidden_order_data"].OrderTotalFrom.value=$('#OrderTotalFrom').val();
    			document.forms["hidden_order_data"].OrderTotalTo.value=$('#OrderTotalTo').val();
    			
    			document.hidden_order_data.action ="returnedOrdersByOrderInfo";
    		}/* else
    			if(activeTab === "tab_2"){
    				 document.forms["hidden_order_data"].invoiceID.value=$('#invoiceID').val();
    				 document.forms["hidden_order_data"].datepicker3.value=$('#datepicker3').val();
    				 document.forms["hidden_order_data"].datepicker4.value=$('#datepicker4').val();
    				 document.forms["hidden_order_data"].InvoiceTotalFrom.value=$('#InvoiceTotalFrom').val();
    				 document.forms["hidden_order_data"].InvoiceTotalTo.value=$('#InvoiceTotalTo').val();
    				 
    				 document.hidden_order_data.action ="returnedOrdersByOrderInfo";
    				 
    			} */else
    				if(activeTab === "tab_3"){
    					document.forms["hidden_order_data"].customerInfo.value=$('#customerInfo').val();
    					document.forms["hidden_order_data"].RtnItemId.value=$('#RtnItemId').val();
    					
    					document.hidden_order_data.action ="returnedOrdersByCustInfo";
    				}
    		                     
            //clearing the input search fields
            $("#orderID").val('');
            $("#order_date_from").val('');
            $("#order_date_to").val('');
            $("#OrderTotalFrom").val('');
            $("#OrderTotalTo").val('');
            
            $("#invoiceID").val('');
            $("#datepicker3").val('');
            $("#datepicker4").val('');
            $("#InvoiceTotalFrom").val('');
            $("#InvoiceTotalTo").val('');
            
            $("#customerInfo").val('');
            $("#RtnItemId").val('');
            
            document.forms["hidden_order_data"].submit();
    	}
    }    
    </script>
    
  
   <form name="hidden_order_data" method="post">
   
   	<input type="hidden" name="invoiceStatus"/>
    <input type="hidden" name="invType"/>
    <input type="hidden" name="activeTab"/>
    
    <input type="hidden" name="orderID"/>
    <input type="hidden" name="order_date_from"/>
    <input type="hidden" name="order_date_to"/>
    <input type="hidden" name="OrderTotalFrom"/>
    <input type="hidden" name="OrderTotalTo"/>
    
    <input type="hidden" name="invoiceID"/>
    <input type="hidden" name="datepicker3"/>
    <input type="hidden" name="datepicker4"/>
    <input type="hidden" name="InvoiceTotalFrom"/>
    <input type="hidden" name="InvoiceTotalTo"/>
    
    <input type="hidden" name="customerInfo"/>
    <input type="hidden" name="RtnItemId"/>
     </form>
	 

	<script type="text/javascript">
	
    function calcAge(date) {
      var d = moment(date).format('MM/DD/YY');
      alert(d);
    }

    function isValidDataInTab(activeTab)
    {
    	var result= false;
    	if(activeTab=='tab_1')
    	{	
    		if($('#orderID').val().length >= 1
    			||($('#order_date_from').val().length>=6 || $('#order_date_to').val().length>=6)
    			||($('#OrderTotalFrom').val().length>=1 || $('#OrderTotalTo').val().length>=1))
    			{
    			result =true;
    			}else
    			{
    				result=false;
    			} 		
    	}else if(activeTab=='tab_2')
    	{
    		if($('#invoiceID').val().length >= 1
        			||($('#datepicker3').val().length>=6 || $('#datepicker4').val().length>=6)
        			||($('#InvoiceTotalFrom').val().length>=1 || $('#InvoiceTotalTo').val().length>=1))
        			{
        			result =true;
        			}else
        			{
        				result =false;
        			}
    	}else if(activeTab=='tab_3')
    	{
    		if($('#customerInfo').val().length >= 1
        			||$('#RtnItemId').val().length>=1 )
        			{
        			   result =true;
        			}else
        			{
        				result= false;
        			}
    	}
    	if(!result)
    	{ 
    		$("lab[name='Info_Text']").css("color", "red");
    		
    	}
    	return result;
    }
    
    
    $('#invSearch')
            .click(
                    function(e)
                    {
                      //disable search button and animate
                      $('#invSearch').prop('disabled', true);
                      $('#invSearch i').addClass('fa-spinner fa-spin');

                      //Init data
                      var ajaxData;
                      var ajaxUrl = '';

                      // Get values for invoice search
                      var orderID = $('#orderID').val();
                      var invoiceID = $('#invoiceID').val();
                      var customerInfo = $('#customerInfo').val();
                      var RtnItemId = $('#RtnItemId').val();
                     var invTyp= $('input[name=Invoice_Search]:checked').val();
					 
                      // Check selected Invoice Status @ krishna
                      var invoiceStatus=$('input[name="Invoice_Search"]:checked').attr('id');
                      //Check active tab, which determines invoice search-by
                      var activeTab = $('.nav-tabs-custom div.tab-pane.active').attr('id');
                     
                      $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', true);
						
                     console.log("\nIs the valid data is present in tab "+activeTab+" : "+isValidDataInTab(activeTab));
                     console.log("\n radio inline : "+ $('.radio inline').val())
                      // Validate for required fields
                      if (activeTab === 'tab_1') 
                      {
                        if (!isValidDataInTab(activeTab)) 
                        {
                          $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                          $('#orderID').focus();
                          $('#invSearch').prop('disabled', false);
                          $('#invSearch i').removeClass('fa-spinner fa-spin');
                          return;
                        } else 
                        {
                          ajaxData = {orderID:orderID,
                        		      order_date_from: $('#order_date_from').val(),
                        		      order_date_to: $('#order_date_to').val(),
                        		      OrderTotalFrom: $('#OrderTotalFrom').val(),
                        		      OrderTotalTo: $('#OrderTotalTo').val(),
                        		      invoiceStatus:invoiceStatus,
                        		      activeTab:activeTab
                        		      };
                          ajaxUrl = 'invoiceSearchByOrder';
                        }
                      }/*  else if (activeTab === 'tab_2')
                      {
                        if (!isValidDataInTab(activeTab))
                        {
                          $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                          $('#invoiceID').focus();
                          $('#invSearch').prop('disabled', false);
                          $('#invSearch i').removeClass('fa-spinner fa-spin');
                          return;
                        } else {
                          ajaxData = {invoiceID :invoiceID,
                        		  	  datepicker3: $('#datepicker3').val(),
                    		      	  datepicker4: $('#datepicker4').val(),
                    		      	  InvoiceTotalFrom: $('#InvoiceTotalFrom').val(),
                    		      	  InvoiceTotalTo: $('#InvoiceTotalTo').val(),
                    		      	  invoiceStatus:invoiceStatus,
                    		      	  activeTab:activeTab
                          			 };
                          ajaxUrl = 'invoiceSearchByOrder';
                        } 
                      } */else if (activeTab === 'tab_3') 
                       {
                        if (!isValidDataInTab(activeTab)) {
                          $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                          $('#customerInfo').focus();
                          $('#invSearch').prop('disabled', false);
                          $('#invSearch i').removeClass('fa-spinner fa-spin');

                          return;
                        } else {
                         $('#orderList').empty();
                         $('#noOfInvoices').html(0);
                          ajaxData = 'customerInfo=' + customerInfo +'&invType='+ invTyp +'&RtnItemId='+ RtnItemId;
                          ajaxUrl = 'orderSearchbyCustomer';
                          }
                          
                        }
                      

                      $.ajax({
                                url: ajaxUrl,
                                type: 'post',
                                data: ajaxData,
                                //timeout : 15000,
                                success: function(result, status, xhr) {
                                  $('#invSearch').prop('disabled', false);
                                  $('#invSearch i').removeClass('fa-spinner fa-spin');
                                  $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                                  if (result.actionErrors.length > 0) {
                                	$("lab[name='Info_Text']").css("color", "Black");
                                    $('#orderList').empty();
                                    $('#noOfInvoices').html(0);
                                    window.setTimeout(function() {
                                    $('#noOrdFnd').modal('show');
                                    }, 50);
                                    return;
                                  }
                                  if (result.orders != null) {
                                	  $('#markDelivered').prop('disabled', false);
                                	  $("lab[name='Info_Text']").css("color", "Black");
                                    $('#orderList').empty();
                                    $('#noOfInvoices').html(result.orders.length);
                                    $
                                            .each(
                                                    result.orders,
                                                    function(key, value) {
                                                      var invoice = '<tr> <td>SNO</td> <td><a href=# id="invoice_'+key+'">CLAIMID</a></td> <td>CLAIMDATE</td> <td>CLAIMTOTAL</td> <td>CUSTOMERID</td> <td>CUSTOMERNAME</td> <td>REASONCODE</td></tr>';
                                                      invoice = invoice.replace('SNO', (key + 1) + '.');
                                                      invoice = invoice.replace('CLAIMID', value.arInvNum);
                                                      invoice = invoice.replace('CLAIMDATE', moment(value.arInvDate).format('MM/DD/YY'));
                                                      invoice = invoice.replace('CLAIMTOTAL', value.invAmount);
                                                      invoice = invoice.replace('CUSTOMERID', moment().diff(value.arInvDate, 'days'));
                                                      invoice = invoice.replace('CUSTOMERNAME', result.customerName[key]);
                                                      invoice = invoice.replace('REASONCODE', value.orderNum);

                                                      $('#orderList').append(invoice);
                                                    });

                                  }
                                },
                                error: function(jqXHR, exception) {
                                  $('#invSearch').prop('disabled', false);
                                  $('#invSearch i').removeClass('fa-spinner fa-spin');
                                  $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                                 
                                }
                              });
                    });
    
  $("body").on("click", "[id*='invoice_']", function(e) {
      //e.preventDefaults();
      var invoiceID = $(this).html();
    		  var myWindow =  window.open("readyToAcceptClaimDetails?claimId=" + invoiceID, "_parent");
    });
  </script>
	
  
  


	<script type="text/javascript">
		$.fn.pressEnter = function(fn) {

			return this.each(function() {
				$(this).bind('enterPress', fn);
				$(this).keyup(function(e) {
					if (e.keyCode == 13) {
						$(this).trigger("enterPress");
					}
				})
			});
		};

		$('#orderID').pressEnter(function() {
			$('#invSearch').trigger("click");
		});
		$('#customerInfo').pressEnter(function() {
			$('#invSearch').trigger("click");
		});
	</script>
	<script type="text/javascript">
 	 document.onkeydown = function (evt) {
	  var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
	  if (keyCode == 13) {
		  document.getElementById('returnOrderSearch').click();
	  }
	 else {
	    return true;
	  }
	};
  </script>
<script type="text/javascript">
	$(function(){ 
		$("#customerInfo").autocomplete({
			autoFocus: true,
			minLength: 2,
			delay: 500,
			source: function(request, response) {
				$.ajax({
				  url: "customerLookupAjax",
				  timeout: 10000,
				  dataType: "json",
				  data: {
				    custInfo: request.term,
				    wildcardSearch: true,
				    maxCustomers: 10
				  },
				  success: function(data) {
				  	response(data);
				  },
				  error: function(jqXHR, textStatus, errorThrown){
				  	if(isPageBeingRefreshed) return;
				  	response();
				  	alert(textStatus);                  
	              }
				});
			},
			response: function( event, ui ) {
				$(this).removeClass("ui-autocomplete-loading");
			},
			select: function(event, ui) {
				$(this).val(ui.item.customerHeader.customerHeaderPK.custId);
				//$("#returnOrderSearch").trigger('click');// .get(0).submit();//
				return false;
			}
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append( "<div>" + item.customerHeader.ctNm + " - " + item.customerHeader.customerHeaderPK.custId + "</div>" )
	        .appendTo( ul );
	    };
    });
	$('#order').addClass('active');
    $('#returnedorders').addClass('active');
</script>
<script>
 $(function(){
	  var searchRange = "";
	  if(searchRange == "CURRENT_WEEK"){
		    $('.week').trigger('click');
		    }else if(searchRange == "CURRENT_MONTH"){
		    $('.month').trigger('click');
		    }else if(searchRange == "CURRENT_QUARTER"){
		    $('.quarter').trigger('click');
		    }else if(searchRange == "CURRENT_YEAR"){
		    $('.year').trigger('click');
		    }else if(searchRange == "NONE"){
		    $('.none').trigger('click');
		    } 
  }); 
 
 function isNumberKey(evt)
 {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48  || charCode > 57))
       return false;
    return true;
 }
  </script>
</body>
</html>
