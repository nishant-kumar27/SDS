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

		

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">

			<!-- Main content -->
			<section class="content">
				<!--        <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b>Find and view details of registered claims</b></small>-->
				<div class="row">
					<div class="col-md-12">
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title" style="color: #226e71;"> <s:property value="getText('claims.label')"/> </h3> - &nbsp;<i><h3 class="box-title" style="color: #226e71;" id="getRangeLabel"></h3></i>
								<div class="dropdown pull-right" style="margin-right: 10px;">
									<i class="fa  fa-bars fa-lg dropdown-toggle" type="button"
										data-toggle="dropdown"></i>
									<ul id="claim_orders" style="margin-left: -90px; background-color: #656a6b; color: white;"
										class="dropdown-menu ">
										 <li><a id="week"> <s:property value="getText('current.week')"/> </a></li>
                        <li><a id="month"> <s:property value="getText('current.month')"/>  </a></li>
                        <li><a id="quarter">  <s:property value="getText('current.quarter')"/>  </a></li>
                        <li><a id="year">  <s:property value="getText('current.year')"/>  </a></li>
                        <li><a id="NONE">  <s:property value="getText('none')"/>  </a></li>
									</ul>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body no-padding table-responsive pre-scrollable" style="max-height:500px;">
								<table class="table table-striped">
									<thead>
											<tr style="background-color: #ADC2EE;">
											<th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
											<th><s:property value="getText('claim.id')"/></th>
											<th style="text-align:center;"><s:property value="getText('claim.date')"/></th>
											<th style="text-align:right;"><s:property value="getText('customer.id')"/></th>
											<th><s:property value="getText('customer.name')"/></th>
											<th style="text-align:right;"><s:property value="getText('table.head.qty')"/></th>	
											<th><s:property value="getText('status.label')"/></th>
											<th><s:property value="getText('sales.agent')"/></th>
											<th><s:property value="getText('reason.code')"/></th>
											<th style="text-align:right;"><s:property value="getText('claim.total')"/>(<s:property value="getText('global.currency')" />)</th>
										</tr>
									</thead>
									<tbody>
										 <s:set var="claimstotals" value="0"/>
										 <s:iterator  value="claims" status="itStatus">  
										 <s:set var="claimstotals" value="%{#claimstotals + claimTotal}" />
											<fieldset>  
											<tr>
												<td><s:property value="#itStatus.count" />.</td>
												
												<%-- <td id="order_id"><a href="#"><s:property value="id.claimId"/></a>   </td> --%>
												<td><a href="ClaimSearchDetails?claimID=<s:property value="id.claimId"/>"><s:property value="id.claimId"/></a>   </td>
												
												<td style="text-align:center;"><s:date name="id.claimDate" format="%{getText('format.date')}"/>  </td>
												<td style="text-align:right;"><s:property value="customerId"/>  </td>
												<td><s:property value="customerName"/>  </td>
												<td style="text-align:right;"><s:property value="totalQuantityReturn"/> </td>
												<td><s:property value="StatusName"/>  </td>
												<td style="word-break: break-word;"><s:property value="empName"/></td>
												<td><s:property value="reasonCode"/>  </td>
												<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{claimTotal})}"/> </td>
											</tr> 
											</fieldset>  
										</s:iterator>
										

									</tbody>
								</table>
							</div>
							<!-- /.box-body -->
					 <!-- Add Totals in Dashboard @sharanya -->		
				<div class="progress" style="height: 2px; margin-bottom: 2px;">
				<div class="progress-bar" style="width: 100%; background-color: #ADC2EE;"></div>
			</div>
			<div class="row"> 
			<div class="col-xs-4 col-md-5 col-xs-4 pull-right">
			<div class="table-responsive">
            <table class="table table-condensed">
              <tbody>
              	<tr><td class="pull-right"><s:property value="getText('claim.total')"/>: <s:property value="getText('global.currency')"/> <b><s:property value="%{getText('format.currency.args',{#claimstotals})}"/>&nbsp&nbsp&nbsp&nbsp&nbsp</b></td></tr>
              </tbody>
            </table>
            </div>
            </div>
          </div>
						</div>
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		
	</div>
	<!-- ./wrapper -->
	<form action="claims_dashboard" name="hidden_data_for_range" method="post">
    	<input type="hidden" name="range"/>
    	<input type="hidden" name="status"/>
	</form>
	

	<!-- ChartJS 1.0.1 -->

	</script>
	<script type="text/javascript">
	claim_range="quarter";
	 $("#claim_orders li").click(
		        function(evt){
		            claim_range=evt.target.id;
		            document.forms["hidden_data_for_range"].range.value=claim_range;
		            document.forms["hidden_data_for_range"].status.value="<s:property value="status"/>";
		            document.forms["hidden_data_for_range"].submit();
		        }
		    ); 
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