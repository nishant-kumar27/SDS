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
		<title>SDS | Approve Claims</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />
		<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
		<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
		<link rel="stylesheet" href="DataTables-1.10.15/media/css/dataTables.bootstrap.min.css">
		<link rel="stylesheet" href="assets/css/jquery-ui.css">
		<link rel="stylesheet" href="assets/css/Customizations.css">
		<script src="Starter_files/jQuery-2.js"></script>
		<script src="Starter_files/bootstrap.js"></script>
		<script src="Starter_files/app.js"></script>
		<script src="DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
		<script src="DataTables-1.10.15/media/js/dataTables.bootstrap.min.js"></script>
		<script src="assets/moment.min.js"></script>
		<script src="custom/dateRangeFilter.js"></script>
		
	<script type="text/javascript">
		$(function(){
			$('#claimsTable').DataTable({
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
				<small class="pull-right"
					style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><b>
					<s:property value="getText('approveclaim.help_text')"/> </b></small>
				<div class="row">
					<div class="col-md-12">
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title" style="color: #226e71;">
								<s:text name="ApproveClaimAction.table.title" /> &nbsp;<i><h3 class="box-title" style="color: #226e71;" id="Current_Date_Range"><s:property value="approveClaimRange" /></h3></i>
								</h3>
								<div class="dropdown pull-right" style="margin-right: 10px;">
									<i class="fa  fa-bars fa-lg dropdown-toggle" type="button"
										data-toggle="dropdown"></i>
									<ul
										style="margin-left: -90px; background-color: #656a6b; color: white;"
										class="dropdown-menu ">
										<li><a href="javascript:void(0)" class="week" id="week" onclick='rangeFilter("claimDate","claimList",this.id)'><s:property value="getText('current.week')"/> </a></li>
										<li><a href="javascript:void(0)" class="month" id="month" onclick='rangeFilter("claimDate","claimList",this.id)'><s:property value="getText('current.month')"/> </a></li>
										<li><a href="javascript:void(0)" class="quarter" id="quarter" onclick='rangeFilter("claimDate","claimList",this.id)'><s:property value="getText('current.quarter')"/></a></li>
										<li><a href="javascript:void(0)" class="year" id="year" onclick='rangeFilter("claimDate","claimList",this.id)'><s:property value="getText('current.year')"/></a></li>
										<li><a href="javascript:void(0)" class="none" id="none" onclick='rangeFilter("claimDate","claimList",this.id)'><s:property value="getText('none')"/></a></li>
									</ul>

								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body no-padding table-responsive">
								<table class="table table-striped" id="claimsTable">
									<thead style="background-color: #ADC2EE;">
										<tr>
										<th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
										<th class="text-center"><s:property value="getText('claim.id')"/></th>
										<th id="claimDate"><s:property value="getText('claim.date')"/></th>
										<th style="text-align:right;"><s:property value="getText('claim.total')"/> 
										</th>
										<th class="text-center"><s:property value="getText('customer.id')"/></th>
										<th><s:property value="getText('customer.name')"/></th>
										<th style="text-align:right;"><s:property value="getText('table.head.qty')"/></th>
										<th><s:property value="getText('sales.agent')"/></th>
										<th><s:property value="getText('reason.code')"/></th>
										<%-- <th><s:property value="getText('return.agent')"/></th> --%>
										</tr>
									</thead>
									<tbody id="claimList">
										<s:iterator value="approvalClaimList" status="stat">
											<tr>
												<td><s:property value="#stat.count" />.</td>
												<td><s:url action="approveClaimDetails" var="url">
														<s:param name="claimID">
															<s:property value="id.claimId" />
														</s:param>
													</s:url> <a href="<s:property value="#url" />"><s:property
															value="id.claimId" /></a></td>
												<td class="text-center"><s:date name="id.claimDate" format="%{getText('format.date')}"/></td>
												<td style="text-align:right;"><small><s:text name="global.currency" /></small> <s:property value="%{getText('format.currency.args',{claimTotal})}"/></td>
												<td class="text-center"><s:property value="customerId" /></td>
												<td><s:property value="customerName" /></td>
												<td style="text-align:right;"><s:property value="totalQuantityReturn" /></td>
												<td><s:property value="empName" /></td>
												<td><s:property value="reasonCode" /></td>
												<%-- <td></td> --%>
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
    	$('#claim').addClass('active');
    	$('#approveclaim').addClass('active');
  	</script>
  <script>
   $(function(){
	  var searchRange = "${approveClaimRange}";
	  if(searchRange == "CURRENT_WEEK"){
		    $('.week').trigger('click');
		    }else if(searchRange == "CURRENT_MONTH"){
		    $('.month').trigger('click');
		    }else if(searchRange == "CURRENT_QUARTER"){
		    $('.quarter').trigger('click');
		    }else if(searchRange == "CURRENT_YEAR"){
		    $('.year').trigger('click');
		    }else{
		    $('.none').trigger('click');
		    }  
  }); 
  </script>
</body>
</html>