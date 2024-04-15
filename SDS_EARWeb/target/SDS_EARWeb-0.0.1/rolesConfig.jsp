<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | Roles & Permissions</title>
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

<style type="text/css">
.errors {
	background-color: orange;
	border: 1px solid black;
	width: 400px;
	margin-bottom: 8px;
}
</style>

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

<script type="text/javascript">
	function fetchEmployeeRolePermission() {
		document.getElementById("rolePermissionSearch").submit();
	}
</script>

<script type="text/javascript">
	function isChecked(checkedValue) {
	var checkedFlag = checkedValue.id;
   	var checkedID = document.getElementById(checkedFlag).checked;
    document.getElementById(checkedFlag).value = checkedID; 
   	return checkedID;  
	}
</script>

<script type="text/javascript">
	$(function(){
		$('#admin').addClass('active');
		$('#roles').addClass('active');
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
	<div class="wrapper" id="body" style="display:none;">
		<!-- Main Header -->
		<s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" />
		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">

			<section class="content">
				<!-- Your Page Content Here -->
				<div class="row">
					<div class="col-md-4">
						<h3 style="color: #226e71;"><s:text name="roles.config"/></h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="box ">
							<div class="box-body">
								<div class="row">
									<div class="col-md-2">
										<ul class="nav nav-pills nav-stacked" id="custPriority">
											<li class="active"><a data-toggle="tab" href="#menu1"><s:text name="roles.config.create.role"/></a></li>
											<li><a data-toggle="tab" href="#menu2"><s:text name="roles.config.assign.permissions"/></a></li>
										</ul>
									</div>
									<div class="col-md-10">
										<div class="well tab-content" style="clear:both;">

											<div id="menu1" class="tab-pane fade in active">
												<s:set var="newRole" value="newRoleError" />
												<form id="createRole" action="createRole">
													<table class="table">
														<tr style="text-align: right;">
															<td style="width: 20%;"><s:text name="roles.config.create.role.roleid"/></td>
															<td><input
																style="max-width: 300px; margin-left: 5%;" type="text"
																class="form-control" id="roleID" name="roleID"
																value='<s:property value="maxRoleID"/>'
																readonly="readonly"></td>
														</tr>
														<tr style="text-align: right;">
															<td style="width: 20%;"><s:text name="roles.config.create.role.roleName"/></td>
															<td><input
																style="max-width: 300px; margin-left: 5%;" type="text" maxlength="50"
																class="form-control" id="roleName" name="roleName" required></td>
														</tr>

														<tr style="text-align: right;">
															<td style="width: 20%;"><s:text name="roles.config.create.role.redirectPages"/></td>
															<td><s:select headerKey=""
																	style="max-width: 300px; margin-left: 5%;"
																	class="form-control"
																	headerValue="%{getText('roles.config.create.role.assignHomePageHeaderValue')}"
																	list="redirectPageList" id="homePage" name="homePage" required
																	theme="simple"></s:select></td>
														</tr>
														<tr style="text-align: right;">
															<td style="width: 20%;"><s:text name="roles.config.create.role.searchCriterias"/></td>
															<td><s:select headerKey=""
																	style="max-width: 300px; margin-left: 5%;"
																	class="form-control"
																	headerValue="%{getText('roles.config.create.role.searchCriteriaHeaderValue')}"
																	list="searchCriteriaList" id="filterCriteria" name="filterCriteria" required
																	theme="simple"></s:select></td>
															<td><button class="btn " style="width: 120px;"
																	type="submit"><s:text name="roles.config.create.role.createRole"/></button>
														</tr>

														<s:if test="%{#newRole=='TRUE'}">
															<s:actionerror theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss"/>
														</s:if>
														<s:if test="%{#newRole=='FALSE'}">
															<s:actionmessage theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss"/>
														</s:if>
													</table>
												</form>

												<form id="assignHomePage" action="assignHomePage">
													<table class="table table-striped">
														<thead>
															<tr style="background-color: #ADC2EE;">
																<th style="width: 30px"><s:text name="index"/></th>
																<th><s:text name="roles.config.create.role.role"/></th>
																<th><s:text name="roles.config.create.role.rolename"/></th>
																<th><s:text name="roles.config.create.role.creationDate"/></th>
																<th><s:text name="roles.config.create.role.redirectPage"/></th>
																<th><s:text name="roles.config.create.role.searchCriteria"/></th>
															</tr>

														</thead>
														<tbody>
															<s:iterator value="empRoleList" var="list" status="stat">
																<tr>
																	<td><s:property value="%{#stat.count}" />.</td>
																	<td><s:property value="roleId" /></td>
																	<s:hidden id="newRoleID"
																		name="homePageList[%{#stat.index}].newRoleID"
																		value="%{roleId}" theme="bootstrap"></s:hidden>
																	<td><s:property value="roleDesc" /></td>
																	<s:hidden id="newRoleDesc"
																		name="homePageList[%{#stat.index}].newRoleDesc"
																		value="%{roleDesc}" theme="bootstrap"></s:hidden>
																	

																	<td><s:date name="createdDatetime"
																			format="dd-MMM-yyyy HH:mm:ss a" /></td>
																	<td><s:if test="%{#list.homePage == null}">
																			<s:select list="redirectPageList" headerKey="null"
																				headerValue="%{getText('roles.config.create.role.redirectPageValue')}"
																				id="newRedirectPage"
																				name="homePageList[%{#stat.index}].newRedirectPage"
																				theme="simple" cssStyle="width:150px;"
																				value="homePage"></s:select>
																		</s:if> <s:else>
																			<s:select list="redirectPageList"
																				var="newRedirectVar" value="homePage"
																				id="newRedirectPage"
																				name="homePageList[%{#stat.index}].newRedirectPage"
																				theme="simple" cssStyle="width:150px;"></s:select>
																		</s:else></td>
																		<td><s:if test="%{#list.searchCriteria == null}">
																			<s:select list="searchCriteriaList" headerKey="null"
																				headerValue="%{getText('roles.config.create.role.searchCriteriaValue')}"
																				id="newRedirectPage"
																				name="homePageList[%{#stat.index}].filterCriteria"
																				theme="simple" cssStyle="width:150px;"
																				value="searchCriteria"></s:select>
																		</s:if> <s:else>
																			<s:select list="searchCriteriaList"
																				var="newRedirectVar" value="searchCriteria"
																				id="newRedirectPage"
																				name="homePageList[%{#stat.index}].filterCriteria"
																				theme="simple" cssStyle="width:150px;"></s:select>
																		</s:else></td>

																</tr>
															</s:iterator>
														</tbody>
													</table>
													<button class="btn pull-right" style="width: 140px;"
														type="submit"><s:text name="roles.config.create.role.assignHome"/></button>

												</form>

											</div>


											<div id="menu2" class="tab-pane fade">
												<s:set var="permission" value="permissionError" />
												<form id="rolePermissionSearch"
													action="rolePermissionSearch">


													<div class="row">
														<div class="col-md-12">
															<div class="box box-primary">
															<s:if test="%{#permission=='TRUE'}">
																		<s:actionerror theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss"/>
																	</s:if>
																	<s:if test="%{#permission=='FALSE'}">
																		<s:actionmessage theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss"/>
																	</s:if>
																<div class="box-body">
																	<label><s:text name="roles.config.assign.permissions.selectRole"/></label>&nbsp;&nbsp;
																	<s:select headerKey="0"
																		headerValue="%{getText('roles.config.assign.permissions.permissionValue')}"
																		list="roleDesc" id="selectRoleName"
																		name="selectRoleName" theme="simple"
																		onchange="fetchEmployeeRolePermission()"></s:select>
																</div>
															</div>
														</div>
													</div>
												</form>
												<div class="row">
													<div class="col-md-12">
														<div class="box box-primary">
															<div class="box-header"></div>
															<form id="updateAccessPermission"
																name="updateAccessPermission"
																action="updateAccessPermission" method="POST">
																<div classs="box-body no-padding">
																	<table id="simpleElementTable"
																		class="table table-striped">
																		<thead>
																			<tr style="background-color: #ADC2EE;">
																				<th style="width: 10px"><s:text name="index"/></th>
																				<th><s:text name="roles.config.create.role.role"/></th>
																				<th><s:text name="roles.config.create.role.rolename"/></th>
																				<th><s:text name="roles.config.assign.permissions.functionName"/></th>
																				<th><s:text name="roles.config.assign.permissions.functionDesc"/></th>
																				<th><s:text name="roles.config.assign.permissions.enablePermissions"/></th>
																			</tr>
																		</thead>
																		<tbody>
																			<s:iterator value="accessPermissionList" var="list"
																				status="stat">
																				<tr>
																					<td><s:property value="%{#stat.count}" />.</td>
																					<td><s:property value="roleID" /></td>
																					<s:hidden
																						name="updateEmpRoleAccessList[%{#stat.index}].roleID"
																						value="%{roleID}" theme="bootstrap"></s:hidden>
																					<td><s:property value="roleName" /></td>
																					<s:hidden
																						name="updateEmpRoleAccessList[%{#stat.index}].roleName"
																						value="%{roleName}" theme="bootstrap"></s:hidden>
																					<td><s:property value="functionName" /></td>
																					<s:hidden
																						name="updateEmpRoleAccessList[%{#stat.index}].functionName"
																						value="%{functionName}" theme="bootstrap"></s:hidden>
																					<td><s:property value="functionDescription" /></td>
																					<s:hidden
																						name="updateEmpRoleAccessList[%{#stat.index}].functionDescription"
																						value="%{functionDescription}" theme="bootstrap"></s:hidden>
																					<%-- <td><s:select list="hasAccessList"
																							var="newDropDownValue" value="hasAccess"
																							id="hasAccess"
																							name="updateEmpRoleAccessList[%{#stat.index}].hasAccess"
																							theme="simple" cssStyle="width:50px;"></s:select>
																					<td> --%>
																					
																					<td>
																					<center>
																					<s:checkbox id="rolePermisssion_%{functionName}"+ name="updateEmpRoleAccessList[%{#stat.index}].hasAccess" value="%{hasAccess}"  fieldValue="%{hasAccess}"  onchange="isChecked(rolePermisssion_%{functionName})" theme="simple"/>
																					</center>
																					</td>
																				</tr>
																			</s:iterator>

																		</tbody>
																	</table>

																</div>
																<s:submit type="button"
																	class="btn btn-default pull-right" id="save" theme="bootstrap">
																	<s:text name="roles.config.assign.permissions.assignAcccess"/></s:submit>																
																<div class="box-footer clearfix"></div>
															</form>
														</div>
													</div>

												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>

		</div>

		<div style="position: fixed; height: auto;" class="control-sidebar-bg"></div>
	</div>
</body>
</html>