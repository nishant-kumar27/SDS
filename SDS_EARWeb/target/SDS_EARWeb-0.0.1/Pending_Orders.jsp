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
		<title>SDS | Pending Orders</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />
		<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
		<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
		<link rel="stylesheet" href="assets/css/jquery-ui.css">
		<link rel="stylesheet" href="assets/css/Customizations.css">
		<script src="Starter_files/jQuery-2.js"></script>
		<script src="Starter_files/bootstrap.js"></script>
		<script src="Starter_files/app.js"></script>
		<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
		<script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
		<script src="assets/moment.min.js"></script>
		<script src="custom/dateRangeFilter.js"></script>
		
	<script type="text/javascript">
		$(function(){
			$('#pendingOrderTable').DataTable({
			    searching: false,
				ordering: false,
				info: false,
				lengthChange: false,
				scrollY: "400px",
				scrollCollapse: true,
				paging: false,
				"language": {
					"emptyTable": "No matching records found"
				}
			});
		});
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

		<s:include value="topbar.jsp"></s:include>
		<s:include value="menubar.jsp"></s:include>
		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">



			<!-- Main content -->
			<section class="content">
			
				<div class="row">
					<div class="col-md-12">
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title" style="color: #226e71;">
								<s:property value="getText('saved.orders')"/>  -&nbsp;<i><h3 class="box-title" style="color: #226e71;" id="Current_Date_Range"><s:property value="pendingOrderRange" /></h3></i>
								</h3>
								<div class="dropdown pull-right" style="margin-right: 10px;">
									<i class="fa  fa-bars fa-lg dropdown-toggle" type="button"
										data-toggle="dropdown"></i>
									<ul
										style="margin-left: -90px; background-color: #656a6b; color: white;"
										class="dropdown-menu ">
										<li><a href="javascript:void(0)" class="week" id="week" onclick='rangeFilter("orderDate","orderList",this.id);'><s:property value="getText('week_to_date')"/></a></li>
                        				<li><a href="javascript:void(0)" class="month" id="month" onclick='rangeFilter("orderDate","orderList",this.id);'><s:property value="getText('month_to_date')"/></a></li>
                        				<li><a href="javascript:void(0)"  class="quarter" id="quarter" onclick='rangeFilter("orderDate","orderList",this.id);'><s:property value="getText('quarter_to_date')"/></a></li>
                        				<li><a href="javascript:void(0)"  class="year" id="year" onclick='rangeFilter("orderDate","orderList",this.id);'><s:property value="getText('year_to_date')"/></a></li>
                       					<li><a href="javascript:void(0)" class="none" id="none" onclick='rangeFilter("orderDate","orderList",this.id);'><s:property value="getText('none')"/></a></li>
									</ul>

								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body no-padding table-responsive">
								<table class="table table-striped" id="pendingOrderTable">
									<thead style="background-color: #ADC2EE;">
										<tr>
										<th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
										<th><s:property value="getText('order.id')"/></th>
										<th id="orderDate"><s:property value="getText('order.date')"/></th>
										<th style="text-align: right;""><s:property value="getText('order.total')"/>(<s:property value="getText('global.currency')"/>)
										</th>
										<th><s:property value="getText('customer.name')"/></th>
										<th><s:property value="getText('status.label')"/></th>
										<th style="text-align: right;"><s:property value="getText('LPO.label')"/></th>
										<th style="text-align: right;"><s:property value="getText('table.head.qty')"/></th>
										<th style="text-align: center;"><s:property value="getText('sales.agent')"/></th>
										<th><s:property value="getText('effective.date')"/></th>
										</tr>
									</thead>
									<tbody id="orderList">
										<s:iterator value="pboList" status="stat">
											<tr>
												<td><s:property value="#stat.count" />.</td>
												<td>
												<%-- <s:url action="pendingOrderClick" var="url">
														<s:param name="idRow">
														<s:property value="#stat.count"/>
														</s:param>
													</s:url> <a href="<s:property value="#url" />"><s:property value="ord_id"/></a>
												<s:url action="quoteOrderAction" var="url">
													<s:param name="orderTran">
															<s:property value="head" />
														</s:param>
													</s:url> --%>
													
											 <s:url action="savedOrderClick" var="url">
													<s:param name="orderId">
															<s:property value="ord_id"/>
													</s:param>
													<s:param name="tranLevelAgentName">
															<s:property value="salesAgent"/>
													</s:param>
													
													</s:url> 
													
											 	
											 <s:a href="%{#url}"><s:property value="ord_id"/></s:a> 
										 	
												</td>
												<td><s:property value="ordDate" /></td>
												<td style="text-align: right;"><s:property value="%{getText('format.currency.args',{ordTotal})}"/> </td>
												<td><s:property value="custname" /></td>
												<td>
												<s:property value="getText('quoted_status')"/></td>
												<td style="text-align: right;"><s:property value="lpo" /></td>
												<td style="text-align: right;"><s:property value="quantity" /></td>
												<td style="text-align: center;"><s:property value="salesAgent" /></td>
													<td><s:property value="effectiveDate" /></td>
											</tr>
										</s:iterator>

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
	</script>
	<script type="text/javascript">
	 $('#order').addClass('active');
    	$('#pendingorders').addClass('active');
  	</script>
  	<script type="text/javascript">
  	 <script>
 $(function(){
	  var searchRange = "${pendingOrderRange}";
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
</script>
  	</body>
</html>