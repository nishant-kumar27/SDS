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
<title>SDS</title>
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
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/css/ionicons.min.css"
	type="text/css" />

<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="assets/css/popup.css">
<link rel="stylesheet" href="assets/css/Customizations.css">
<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>

<!-- Select2 -->
<script src="assets/plugins/select2/select2.full.min.js"></script>
<!-- InputMask -->
<script src="assets/plugins/input-mask/jquery.inputmask.js"></script>
<script
	src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- having function for print -->
<script src="custom/printFunction.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<script type="text/javascript">
	$('#dashboard').addClass('active');
</script>
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
		<!--   <header class="main-header">

    Logo
    <a href="index.html" class="logo">
      mini logo for sidebar mini 50x50 pixels
        <span class="logo-lg"><img src="assets/SDSlogo.png"></span>
      <span class="logo-mini"><b></b>SDS</span>
      logo for regular state and mobile devices
      
    </a>

    Header Navbar
    <nav class="navbar navbar-static-top" role="navigation">
      Sidebar toggle button
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      
      Navbar Right Menu
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          
          
          
          
          User Account Menu
          <li class="dropdown user user-menu">
            Menu Toggle Button
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              hidden-xs hides the username on small devices so only the image appears.
              <span class="hidden-xs">Alexander Pierce</span>
            </a>
            
          </li>
          Control Sidebar Toggle Button
          <li>
            <a href="#" data-toggle="control-sidebar" style="margin-bottom:-5px;margin-top:-5px;" ><table style="margin:-5px;padding:0px;"><tr><td><i class="fa fa-user "></i></td></tr><tr><td> <i class="fa fa-angle-down fa-lg"></i></td></tr></table></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  Left side column. contains the logo and sidebar
    <aside class="main-sidebar">

    sidebar: style can be found in sidebar.less
     <section class="sidebar">

     

      Sidebar Menu
        
      <ul class="sidebar-menu">
        <li class="header"><h4><b>MENU</b></h4></li>
        <li class="treeview">
          <a href="DashBoard_Sales_Agent.html">
            <i class="fa  fa-line-chart"></i> <span>DashBoard</span> <i class="fa pull-right"></i>
          </a>
        </li>
        <li class=" treeview">
          <a href="#">
            <i class="fa fa-truck"></i> <span>Order</span> <i class="fa fa-angle-down pull-right"></i>
          </a>
          <ul class=" treeview-menu">
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
        <li class="active treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Claim</span> <i class="fa fa-angle-down pull-right"></i>
          </a>
          <ul class="active treeview-menu">
            <li class=""><a href="claim_Search.html"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Claim Search</a></li>
            <li class="active"><a href="Generate_Claim.html?#"><i class="fa fa-angle-right"></i>&nbsp;&nbsp;Register Claim</a></li>
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
           <li><a href="Add_New.html"><i class="fa fa-circle-o"></i> Add New</a></li>
          </ul>
        </li>
        
      </ul>
      /.sidebar-menu
    </section>
    /.sidebar
  </aside> -->

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">



			<!-- Main content -->
			<section class="content">
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
						style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><b><s:property value="getText('newclaimwithoutinv.help_text')"/>
						</b></small>
					<div class="row">
						<div class="col-xs-12">
							<h2 style="color: #226e71;">
								<s:property value="getText('claim.details')"/>
								<div class="pull-right hidden-print">
									<h5>
										<a href="javascript:void(0);" class="pull-right" rel="popover" id="btpopover" data-content='
                						    
                 							<div><b><input type="checkbox" id="employee" name="employee" class="form-check-input"> <label for="employee"><s:property value="getText('login.employee')"/></label></div>
											<div><b><input type="checkbox" id="customr" name="customr" class="form-check-input"> <label for="customr"><s:property value="getText('customer.label')"/></label></div>
											<div><b><input type="checkbox" id="departmentHead" name="departmentHead" class="form-check-input"> <label for="departmentHead"><s:property value="getText('dept.head')"/></label></div>
    										<div><b><input type="checkbox" id="salesAgent" name="salesAgent" class="form-check-input"> <label for="salesAgent"><s:property value="getText('sales.agent')"/> </label></div>
    										<div><b><input type="checkbox" id="DataEntryOptr" name="DataEntryOptr" class="form-check-input"> <label for="DataEntryOptr"><s:property value="getText('dataentry.operator')"/> </label></div>
    										<div><label>Mail :</label><input type="text" id="custommails" name="custom" /></div><br>
                							<div style="text-align:right;"><button type="button"  id="emailSubmitbtn">Submit</button>
                							<button type="button" data-dismiss="modal" id="cancelmail">Cancel</button></div>
                							' data-placement="top" data-original-title="Select"><i class="fa fa-envelope"></i> <s:property value="getText('mail.label')"/>  &nbsp;</a>
										<a href="#"class="pull-right" style="text-decoration: none"onclick="windowPrint()"><i class="fa fa-print"></i> <s:property value="getText('print.label')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a> 
										<a href="registerClaimWitOutInvPDFDownload?claimID=<s:property value="claimTran.claimId"/>" class="pull-right" style="text-decoration: none"><i class="fa fa-save"></i> <s:property value="getText('save.label')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a></div>
							
								<!--            <small class="pull-right">Date: 2/10/2014</small>-->
							</h2>
						</div>
						<!-- /.col -->
					</div>
					<!-- info row -->
					<div class="row invoice-info">
						<div class="col-sm-4 invoice-col">
							<table style="width: 100%;" class="table">
								<tr><td style="text-align: right; width: 50%;"><s:property value="getText('claim.id')"/>:</b></td>
									<td><b><span id="claimId"><s:property value="claimTran.claimId" /></span></b></td></tr>
								<tr><td style="text-align: right; width: 50%;"><s:property value="getText('claim.date')"/>:</b></td>
									 <td><b><s:date name="claimDate" format="%{getText('format.date')}"/></b></td> 
								</tr>
							</table>
						</div>
						<!-- /.col -->
						<div class="col-sm-4 invoice-col">
							<table style="width: 100%;" class="table">
							<tr><td style="text-align: right;"><s:property value="getText('reason.code')"/>:</b></td><td>
								<b><s:property value="claimTran.returnReasonCodes[globalReasonCode]" /></b></td>
								</tr>
							<tr><td style="text-align: right; width: 50%;"><s:property value="getText('status.label')"/>:</b></td>
									<td><b><s:text name="claim.status.new" /></b></td>
								</tr>	
								<tr><td style="text-align: right;"><s:property value="getText('sales.agent')"/>:</b></td>
							    	<td><b><s:property value="sales_Agent" /></b></td>
								</tr>
							</table>
						</div>
						<!-- /.col -->
						<div class="col-sm-4 invoice-col">
							<table style="width: 100%;" class="table">
							<tr><td style="text-align: right;"><s:property value="getText('customer.id')"/>:</b></td><td>
								<b><s:property value="orderTran.customer.customerHeader.customerHeaderPK.custId" /></b></td>
								</tr>
							<tr><td style="text-align: right;"><s:property value="getText('customer.name')"/>:</b></td>
							    <td><b><s:property value="orderTran.customer.customerHeader.ctNm" /></b>
							    </td>
							</tr>
							<tr><td style="text-align: right;"><s:property value="getText('claim.pickup.addrss')"/>:</b></td>
							    <td><b><s:property value="cust_site_addrss" /></b>
							    </td>
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
										<th><s:property value="getText('table.head.SNo')"/></th>
										<th><s:property value="getText('table.head.item')"/></th>
										<%-- <th style="text-align:right;"><s:property value="getText('sold.qty')"/></th>
									    <th style="text-align:right;"><s:property value="getText('sold.at')"/></th> --%>
										<th style="text-align:right;"><s:property value="getText('qty.return')"/></th>
										<th style="text-align:right;"><s:property value="getText('price.head')"/></th>
										<th style="text-align:right;"><s:property value="getText('final.price')"/></th>
										<th style="text-align:center;"><s:property value="getText('reason.code')"/></th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="orderTran.ordTranLineItems" status="status">

										<tr>
											<td><s:property value="%{#status.count}" />.</td>
											<td><s:property value="itemId"/><br><s:property value="deItmShrtRcpt" /></td>
											<%-- <td style="text-align:right;"><div id="quantitySold_%{#index}">
																			<s:property value="quantitySold" />
																		</div></td>
																	<td style="text-align:right;"><div id="priceSoldAt_%{#index}">
																			<s:property value="priceSoldAt" />
																		</div></td> --%>
											<td style="text-align:right;">
												<%-- 
                  <s:property value="returnQtyFlag" />
                  <s:property value="lineQntRtn" />
                  <s:property value="lineQnt" /> --%> <s:if
													test="%{returnQtyFlag.equalsIgnoreCase('1')}">

													<s:property value="lineQntRtn" />
												</s:if> <s:else>

													<s:property value="lineQnt" />
												</s:else>
											</td>
											<td style="text-align:right;">
												<%--  <s:property value="priceOverRideFlag" />
                <s:property value="overRidePrice" />
                <s:property value="itmPrnPrc" /> --%> <s:if
													test="%{priceOverRideFlag.equalsIgnoreCase('1')}">

													<s:property value="%{getText('format.currency.args',{overRidePrice})}" />
												</s:if> <s:else>

													<s:property value="%{getText('format.currency.args',{itmPrnPrc})}" />
												</s:else>
											</td>

											<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{extnLnItmRtn})}" /></td>
											<td style="text-align:center;"><s:property
													value="claimTran.returnReasonCodes[rcRtnMr]" /></td>
										</tr>


									</s:iterator>


								</tbody>
							</table>
						</div>
						<!-- /.col -->
					</div>
					 <div class="progress" style="height: 2px;">
				<div class="progress-bar" style="width: 100%; background-color: #ADC2EE;"></div>
			</div>
					<!-- /.row -->

					<div class="row pull-right">
						<!-- /.col -->
						<div class="col-lg-12 col-md-12 col-xs-12 ">

							<div class="table-responsive">
								<table class="table">
									<tbody>
										<tr style="text-decoration:none">
											<td style="text-align: right;" style="width:50%"><s:property value="getText('subtotal.label')"/>:</td>
											<td style="text-align:right;"><s:property value="getText('global.currency')"/> <b><s:property value="%{getText('format.currency.args',{orderTran.ordTranSum.dkartSlsTot})}" /></b></td>
										</tr>
										<tr style="text-decoration:none">
											<td style="text-align: right;"><s:property value="getText('expenses.label')"/>:</td>
											<th style="text-align:right;"><s:property value="getText('global.currency')"/> <b><s:property value="%{getText('format.currency.args',{orderTran.ordTranSum.dkartExpenses})}" /></b></th>
										</tr>

										<tr style="font-size: 18px;">
											<td style="text-align: right;"><s:property value="getText('total.refund')"/>:</td>
											<td style="text-align:right;"><s:property value="getText('global.currency')"/> <b><s:property value="%{getText('format.currency.args',{orderTran.ordTranSum.dkartNetTot})}" /></b></td>
										</tr>
									</tbody>
								</table>
							</div>
							<form action="doneRegisterClaim"> 
                                                       <input type="text" name="ClaimRef"
														placeholder="Claim Ref" id="ClaimRef"
														autofocus="autofocus"
														class="form-control" >
													<s:actionerror theme="bootstrap" />
								<button type="submit" class="btn  pull-right hidden-print" onclick="sendClaimRef()"
									style="color: #ffffff; width: 100px; margin-top: 5px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; display: inline-block;">
									<s:property value="getText('done.button')"/></button>

							 </form> 

						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->

					<!-- this row will not appear when printing -->
					<div class="row">
						<div class="col-xs-12"></div>
					</div>
				</section>
				<!-- Your Page Content Here -->


			</section>
			<!-- /.content -->
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
						<button type="submit" class="btn btn-primary center-block" data-dismiss="modal" >
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
		function windowPrint() {
			window.print();
		}
		$('a[rel=popover]').popover({
	    html: 'true',
	placement: 'bottom'
	})
	</script>
	
	
	<script type="text/javascript">
	function sendClaimRef(){
		//alert("IN sendClaimRef");
		  var ClaimRef = document.getElementById("ClaimRef").value;
		  if (!ClaimRef==null || !ClaimRef == undefined || !ClaimRef == "" || !ClaimRef.length == 0)
		  {
			     
			    // addClaimRef(ClaimRef);
			 // var claimRef =ClaimRef;
				var claimId = document.getElementById("claimId").innerText;
				
				var today = new Date();
				var dd = today.getDate();
				var mm = today.getMonth() + 1; //January is 0!

				var yyyy = today.getFullYear();
				if (dd < 10) {
				  dd = '0' + dd;
				} 
				if (mm < 10) {
				  mm = '0' + mm;
				} 
				var claimDate = dd + '-' + mm + '-' + yyyy;

				  					
				$.ajax({
					url : "addClaimRefNo",
					type : "POST",
					data : {
						claimRefNo :  ClaimRef,
						claimId :   claimId,
						claimRefdate : claimDate					
					},
					dataType : "json"
					//success : function(jsonResponse) {
					//alert(JSON.stringify(jsonResponse));	      
					//}
				});	
  
			    
		 }           
		                	     
		}

		
	
		function emailSubmit(){   
		var employeecheck=$('#employee').is(":checked");
        var  customer=$('#customr').is(":checked");
		var deptheadcheck=$('#departmentHead').is(":checked");
		var empsalescheck=$('#salesAgent').is(":checked");
		var dataentrycheck=$('#DataEntryOptr').is(":checked");
        var claimId=document.getElementById("claimId").innerText;
        var custom=document.getElementById("custommails").value;
	   	   
	   if(employeecheck==false && customer == false && deptheadcheck==false && empsalescheck==false && dataentrycheck==false && (custom==null || custom=="") ){
	    $("#validatepopover").modal("show");
	   }
	   else{			   
			$.ajax({
				url : "claimdetailmail",
				type : "POST",
				data: {
				claimId : claimId,
				loginEmp : employeecheck, 
				customer : customer,
				departmentHead : deptheadcheck,
				salesAgent : empsalescheck,
				DataEntryOptr : dataentrycheck,
				custommail : custom},
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