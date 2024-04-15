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
<title>SDS </title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="assets/css/Customizations.css">

<script src="Starter_files/jQuery-2.js"></script>
<script src="Starter_files/bootstrap.js"></script>
<script src="Starter_files/app.js"></script>

</head>

<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">

			<!-- Main content -->
			<section class="content"> 
			
				<div class="well well-sm"><h4><span class="fa fa-arrow-left fa-lg" style="color:#3c8dbc;" aria-hidden="true"></span>&nbsp;&nbsp;Please select from the menu options</h4></well>

 			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	<!-- /.wrapper -->

	<script type="text/javascript">
		// Activate menubar link here
    	//$('#dashboard').addClass('active');
    	//Chiranjibee Comments to Activate Admin Pannel Menu Bar
    	//$(function(){
		//$('#admin').addClass('active');
	});
	</script>
</body>
</html>

