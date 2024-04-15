<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | Customer Configuration</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<!-- <link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="assets/css/popup.css"> -->
<link rel="stylesheet" href="assets/css/jquery-ui.css">
<link rel="stylesheet" href="assets/css/Customizations.css"
	type="text/css" />
<script src="Starter_files/jQuery-2.js"></script>
<script src="Starter_files/bootstrap.js"></script>
<script src="Starter_files/app.js"></script>
<%-- <script src="assets/plugins/select2/select2.full.min.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.js"></script>
<script
	src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script> --%>

<script type="text/javascript">
	$(document).ready(function() {
		$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
			localStorage.setItem('activeTab', $(e.target).attr('href'));
		});
		var activeTab = localStorage.getItem('activeTab');
		if (activeTab) {
			$('#custPriority a[href="' + activeTab + '"]').tab('show');
		}
	});
</script>

<style type="text/css">
.errors {
	background-color: orange;
	border: 1px solid black;
	width: 400px;
	margin-bottom: 8px;
}
</style>

<script type="text/javascript">
	$(function() {
		$('#admin').addClass('active');
		$('#customersegments').addClass('active');
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#body').show();
		$('#header').hide();
	});
</script>


</head>

<body class="skin-blue sidebar-mini">
	<div id="header"></div>
	<div class="wrapper" id="body" style="display: none;">
		<!-- Main Header -->
		<s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" />
		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">

			<section class="content">
				<!-- Your Page Content Here -->
				<div class="row">
					<div class="col-md-4">
						<h3 style="color: #226e71;">
							<s:text name="customer.config" />
						</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="box ">
							<div class="box-body">
								<div class="row">
									<div class="col-md-2">
										<ul class="nav nav-pills nav-stacked" " id="custPriority">
											<li class="active"><a data-toggle="tab" href="#menu1"><s:text
														name="customer.config.map" /></a></li>
											<%-- <li><a data-toggle="tab" href="#menu2"><s:text name="customer.config.segment"/></a></li> --%>
											<li><a data-toggle="tab" href="#menu2"><s:text
														name="customer.config.assignSegmentPriorityMenu" /></a></li>
										</ul>
									</div>
									<div class="col-md-10">
										<div class="well tab-content" style="clear:both;">

											<div id="menu1" class="tab-pane fade in active">
												<s:set var="newSegment" value="newSegmentError" />
												<form id="createNewSegment" action="createNewSegment">
													<table class="table">
														<tr style="text-align: right;">
															<td style="width: 20%;"><s:text
																	name="customer.config.segmentID" /></td>
															<td><s:select headerKey=""
																	style="max-width: 300px; margin-left: 5%;"
																	class="form-control"
																	headerValue="%{getText('customer.config.segmentHeaderValue')}"
																	list="segmentList" id="newSegmentID"
																	name="newSegmentID" theme="simple" required></s:select></td>
														</tr>
														<tr style="text-align: right;">
															<td style="width: 20%;"><s:text
																	name="customer.config.divisionID" /></td>
															<td><s:select headerKey=""
																	style="max-width: 300px; margin-left: 5%;"
																	class="form-control"
																	headerValue="%{getText('customer.config.divisionHeadValue')}"
																	list="divisionList" id="newDivisionId"
																	name="newDivisionId" theme="simple" required></s:select></td>
														</tr>

														<tr style="text-align: right;">
															<td style="width: 20%;"><s:text
																	name="customer.config.storeID" /></td>
															<!-- <td><input
																style="max-width: 300px; margin-left: 5%;" type="text"
																class="form-control" id="newStoreId" name="newStoreId" required></td> -->
															<td><s:select headerKey=""
																	style="max-width: 300px; margin-left: 5%;"
																	class="form-control"
																	headerValue="%{getText('customer.config.store')}"
																	listKey="key" listValue="value+' - '+key"
																	list="storeDetails" id="newStoreId" name="newStoreId"
																	theme="simple" required></s:select></td>
															<td><center>
																	<button class="btn " style="width: 120px;"
																		type="submit">
																		<s:text name="customer.config.mapSegment" />
																	</button>
																</center></td>
														</tr>


														<tr>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<s:if test="%{#newSegment=='TRUE'}">
															<s:actionerror theme="bootstrap"
																cssClass="alert-dismissible" onclick="dismiss" />
														</s:if>
														<s:if test="%{#newSegment=='FALSE'}">
															<s:actionmessage theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss"/>
														</s:if>
													</table>
												</form>

												<form id="fetchSegmentDetails" action="fetchSegmentDetails"
													method="post">
													<table class="table table-striped">
														<thead>
															<tr style="background-color: #ADC2EE;">
																<th style="width: 35px"><s:text name="index" /></th>
																<th><s:text name="customer.config.segmentId" /></th>
																<th><s:text
																		name="customer.config.segmentDescription" /></th>
															</tr>
														</thead>

														<tbody>
															<s:iterator value="segmentDetails" var="list"
																status="stat">
																<tr>
																	<td><s:property value="%{#stat.count}" />.</td>
																	<td><s:property value="segmentId" /></td>
																	<td><s:property value="segmentDescription" /></td>
																</tr>
															</s:iterator>
														</tbody>
													</table>
												</form>

												<form id="displaySegStoreMap" action="displaySegStoreMap"
													method="post">
													<table class="table table-striped">
														<thead>
															<tr style="background-color: #ADC2EE;">
																<th style="width: 35px"><s:text name="index" /></th>
																<th>Segment</th>
																<th>Division</th>
																<th>Store</th>
																<th>Store Id</th>
															</tr>
														</thead>

														<tbody>
															<s:iterator value="segmentStoreMapList" var="segstore"
																status="stat">
																<tr>
																	<td><s:property value="%{#stat.count}" />.</td>
																	<td><s:property value="segment" /></td>
																	<td><s:property value="division" /></td>
																	<td><s:property value="store" /></td>
																	<td><s:property value="storeId" /></td>
																</tr>
															</s:iterator>
														</tbody>
													</table>
												</form>

											</div>


											<%-- <div id="menu2" class="tab-pane fade">
												<s:set var="assignSegment" value="assignSegmentError" />
												<form id="assignSegmentCustomerSearch"
													action="assignSegmentCustomerSearch">
													<table class="table">
														<tr style="text-align: right;">
															<td style="width: 20%;"><s:text name="customer.config.serachCustomer"/></td>
															<td><input style="max-width: 400px;"
																placeholder="Customer ID/Name" type="text"
																class="form-control-original" id="custInfo1"
																name="custInfo1"></td>
															<td><button class="btn btn-block"
																	style="border-top-left-radius: 20px; border-bottom-left-radius: 20px;"
																	type="submit"><s:text name="customer.config.serach"/></button></td>

														</tr>
														<s:if test="%{#assignSegment=='TRUE'}">
															<s:actionerror theme="bootstrap" />
														</s:if>
													</table>
												</form>

												<form id="assignSegment" action="assignSegment">
													<table class="table">
														<tr>
															<td style="text-align: right;"><s:text name="customer.config.customerID"/></td>
															<td><b><s:property value="customerId1" /></b></td>
															<s:hidden type="hidden" name="customerId1"
																id="customerId1" />
															<td style="text-align: right;"><s:text name="customer.config.customerName"/></td>
															<td><b><s:property value="customerName1" /></b></td>
															<td style="text-align: right;"><s:text name="customer.config.classification"/></td>
															<td><b><s:property value="classification1" /></b></td>
														</tr>
														<tr>
															<td style="text-align: right;"><s:text name="customer.config.currentPriority"/></td>
															<td><b><s:property value="currentPriority1" /></b></td>
															<td style="text-align: right;"><s:text name="customer.config.currentAssignedSegment"/></td>
															<td><b><s:property value="segment1" /></b></td>
															<td style="text-align: right;"><s:text name="customer.config.division"/></td>
															<td><b><s:property value="division1" /></b></td>
														</tr>
													</table>
													<table class="table">
														<tr>
															<td style="width: 20%; text-align: right;"><s:text name="customer.config.segmentToUpdate"/></td>
															<td><s:select headerKey="0" list="segmentList"
																	value="segment1" id="selectSegment"
																	name="selectSegment" theme="simple"></s:select></td>
															<td><s:if test="%{#assignSegment=='TRUE'}">
																	<s:if test="hasActionErrors()">
																		<button class="btn pull-left" style="width: 120px;"
																			type="submit" disabled="disabled"><s:text name="customer.config.assignSegment"/></button>
																	</s:if>
																</s:if> <s:else>
																	<button class="btn pull-left" style="width: 120px;"
																		type="submit"><s:text name="customer.config.assignSegment"/></button>
																</s:else></td>
														</tr>
													</table>
												</form>
											</div> --%>

											<div id="menu2" class="tab-pane fade">
												<s:set var="assignPriority" value="assignPriorityError" />
												<form id="priorityCustomerSearch"
													name="priorityCustomerSearch"
													action="priorityCustomerSearch" method="POST">
													<table class="table">

														<tr style="text-align: right;">
															<td style="width: 20%;"><s:text
																	name="customer.config.serachCustomer" /></td>
															<td><input
																style="max-width: 350px; margin-left: 5%;"
																placeholder="Customer ID/Name" type="text"
																class="form-control" id="custInfo" name="custInfo"
																name="custInfo" required> </input></td>
															<td><button class="btn btn-block" id="claimSearch"
																	style="border-top-left-radius: 20px; border-bottom-left-radius: 20px;"
																	type="submit">
																	<s:text name="customer.config.serach" />
																</button></td>
														</tr>
														<s:if test="%{#assignPriority=='TRUE'}">
															<s:actionerror theme="bootstrap"
																cssClass="alert-dismissible" onclick="dismiss" />
														</s:if>
														<s:if test="%{#assignPriority=='FALSE'}">
															<s:actionmessage theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss"/>
														</s:if>
													</table>
												</form>
												
																<br>
				<s:if test="customerList!=null && customerList.size() > 1">
					<div class="well well-sm" id="multipleCust">
						<div class="container-fluid" style="background-color:#ADC2EE;margin-right: 16px; margin-bottom:2px;">
							<div class="row">
								<div class="col-xs-1"><p class="p-margin-6"><b>#</b></p></div>
								<div class="col-xs-5"><p class="p-margin-6"><b><s:property value="getText('customer.name')"/></b></p></div>
		  						<div class="col-xs-2"><p class="p-margin-6"><b><s:property value="getText('customer.id')"/></b></p></div>
		  						<div class="col-xs-2"><p class="p-margin-6"><b><s:property value="getText('phone.no')"/></b></p></div>
		  						<div class="col-xs-2"><p class="p-margin-6"><b><s:property value="getText('status.label')"/></b></p></div>
							</div>
						</div>
						<ul class="list-group pre-scrollable" id="customer-list-group"	style="max-height: 260px;">
							<s:iterator value="customerList" status="customerListStatus">
								<li class="list-group-item container-fluid" style="padding: 6px 0px;">
										<span class="col-xs-1">
											<s:property value="#customerListStatus.count"/>
										</span>
										<span class="col-xs-5">
											<s:property value="customerHeader.ctNm"/>
										</span>
										<span class="col-xs-2">
												<a href="priorityCustomerSearch?custInfo=
													<s:property value="customerHeader.customerHeaderPK.custId"/>"><s:property value="customerHeader.customerHeaderPK.custId"/>
												</a>
												<!-- <s:param name="customerId" value="customerHeader.customerHeaderPK.custId"></s:param> -->
												
										</span>
										<span class="col-xs-2">
												<s:if test="customerHeader.ctPhone!=null && customerHeader.ctPhone.length > 0">
													<s:property value="customerHeader.ctPhone"/>
												</s:if>
												<s:else>
														</s:else>
										</span>
										<span class="col-xs-2">
												<s:set var="stsCd" value="customerHeader.ctStsCd"></s:set>
												<s:if test="%{#stsCd=='0'}">
													<s:property value="getText('inactive_status')"/>
												</s:if>
												<s:elseif test="%{#stsCd=='1' || #stsCd=='A' }">
													<s:property value="getText('active_status')"/>
												</s:elseif>
												<s:else>
													<s:property value="getText('deleted_status')"/>
												</s:else>
										</span>
								</li>
							</s:iterator>
						</div>
	
					</ul>
					<%-- <button type="button" class="btn btn-primary pull-right hidden"
						id="customerSelected"><s:property value="getText('next.button')"/></button> --%>
				</s:if>
												
												<form id="assignPriority" action="assignPriority">
													<table class="table">
														<tr>
															<td style="width: 20%; text-align: right;"><s:text
																	name="customer.config.customerID" /></td>
															<td><b><s:property value="customerId" /></b></td>
															<s:hidden type="hidden" name="customerId" id="customerId" />
															<td style="width: 20%; text-align: right;"><s:text
																	name="customer.config.customerName" /></td>
															<td><b><s:property value="customerName" /></b></td>
															<%-- <td style="text-align: right;"><s:text name="customer.config.classification"/></td>
															<td><b><s:property value="classification" /></b></td> --%>
														</tr>
														<tr>
															<td style="width: 20%; text-align: right;"><s:text
																	name="customer.config.currentPriority" /></td>
															<td><b><s:property value="currentPriority" /></b></td>
															<td style="width: 20%; text-align: right;"><s:text
																	name="customer.config.currentAssignedSegment" /></td>
															<td><b><s:property value="segment" /></b></td>
															<td style="width: 20%; text-align: right;"><s:text
																	name="customer.config.division" /></td>
															<td><b><s:property value="division" /></b></td>
														</tr>
													</table>
													<table class="table">

														<tr>
															<td style="width: 20%; text-align: right;"><s:text
																	name="customer.config.segmentToUpdate" /></td>
															<td><s:select headerKey="0" value="segment"
																	list="segmentList" id="selectSegment"
																	name="selectSegment" theme="simple"></s:select></td>
															<td style="width: 20%; text-align: right;"><s:text
																	name="customer.config.priorityToUpdate" /></td>
															<td><s:select headerKey="0" value="currentPriority"
																	list="priorityList" id="priority" name="priority"
																	theme="simple"></s:select></td>
															<td><s:if test="%{permission}"> 
																		<button class="btn pull-left" style="width: 175px;"
																			type="submit">
																			<s:text name="customer.config.assignSegmentPriority" />
																		</button>
																</s:if> <s:else>
																	<button class="btn pull-left" style="width: 175px;"
																		type="submit" disabled="disabled">
																		<s:text name="customer.config.assignSegmentPriority" />
																	</button>
																</s:else></td>
														</tr>
													</table>
												</form>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>

		<div style="position: fixed; height: auto;" class="control-sidebar-bg"></div>
	</div>

	<script type="text/javascript">
		$("#custInfo").autocomplete({
			autoFocus : true,
			minLength : 2,
			delay : 500,
			source : function(request, response) {
				$("#custError").addClass('hidden');
				$.ajax({
					url : "customerLookupAjax",
					timeout : 10000,
					dataType : "json",
					data : {
						custInfo : request.term,
						wildcardSearch : true,
						maxCustomers : 10
					},
					success : function(data) {
						response(data);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						if (isPageBeingRefreshed)
							return;
						response();
						alert(textStatus);
						/* if(jqXHR.status!=null && jqXHR.status==403)
						{
						location.reload(true);
						alert('Session has timed out. Please re-login.');
						}   */
					}
				});
			},
			response : function(event, ui) {
				$(this).removeClass("ui-autocomplete-loading");
			},
			select : function(event, ui) {
				$(this).val(ui.item.customerHeader.customerHeaderPK.custId);
				$("#claimSearch").trigger('click');// .get(0).submit();//
				return false;
			}
		}).autocomplete("instance")._renderItem = function(ul, item) {
			return $("<li>").append(
					"<div>" + item.customerHeader.ctNm + " - "
							+ item.customerHeader.customerHeaderPK.custId
							+ "</div>").appendTo(ul);
		};
	</script>




	<script type="text/javascript">
		// Highlight Menubar
		$("#customer").addClass('active');
		$("#customersearch").addClass('active');
		// Hide topbar customer search form
		$("#topbarCustomerLookup").addClass('hidden');
		// Focus to customer info textbox
		$("#customerInfo").focus();
	</script>



</body>
</html>