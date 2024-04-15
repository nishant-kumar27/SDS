<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	// Comment in order to enable cahcing on a praticular page
	// ----------------------------------------------------------
	//response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	//response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	//response.setDateHeader("Expires", 0); // Proxies.

	// Prevent "back" after logout
	//if (session.getAttribute("employee") == null)
	//	response.sendRedirect("logout");
%>
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
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<title>SDS | Reset Password</title>

<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
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

		<!-- Main Header -->
		<s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">

			<!-- Main content -->
			<section class="content"> 
			
				<div class="row">
					<div class="col-xs-12">
						<label style="font-size:25px;color: #226e71;"><s:text name="Reset Password" /></label>
						<form action="lookupEmployee" method="post" id="lookupEmployee" class="search-form">
							<div class="row">
								<div class="col-xs-7">
									<input type="text" name="empInfo" id="empInfo" class="form-control-original" 
										placeholder="Employee ID / Login ID"
										autocomplete="off" required>
								</div>
								<div class="col-xs-3">
									<%-- <s:hidden name="wildcardSearch" value="true"></s:hidden> --%>
									<button type="submit" id="btnSubmit" class="btn btn-primary">
										<s:property value="getText('search.button')" />
									</button>
								</div>
							</div>
						</form>
						<div class="row">
							<div class="col-xs-12 col-sm-7">
								<s:actionerror  id="empError" theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss" />
								<s:actionmessage id="empMessage" theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss" />
							</div>
						</div>
					</div>
				</div>
				<br>
				<s:if test="emp!=null">
					<div class="col-xs-12 col-sm-10 col-md-7 well well-sm" id="emp">
						<div class="row p-margin-6">
							<div class="col-xs-5">Employee ID:</div>
							<div class="col-xs-7"><b><s:property value="emp.employeeId"/></b></div>
						</div>
						<div class="row p-margin-6">
							<div class="col-xs-5">Employee Name:</div>
							<div class="col-xs-7"><b><s:property value="emp.employeeName"/></b></div>
						</div>
						<div class="row p-margin-6">
							<div class="col-xs-5">Employee Email:</div>
							<div class="col-xs-7"><b><s:property value="emp.email"/></b></div>
						</div>
						<div class="row p-margin-6">
							<div class="col-xs-5">Last Password change date:</div>
							<div class="col-xs-7"><b><s:date name="emp.pswdCreateTime"/></b></div>
						</div>
						
						<form action="resetPassword" class="pull-right" method="post">
							<s:hidden name="empInfo" value="%{empInfo}"></s:hidden>
							<button type="submit" class="btn btn-primary" style="width:120px;">Reset Password</button>
						</form>
					</div>
				</s:if>
 			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	<!-- /.wrapper -->

	<script type="text/javascript">
		
		$('#admin').addClass('active');
    	$('#resetpassword').addClass('active');

	</script>
</body>
</html>

