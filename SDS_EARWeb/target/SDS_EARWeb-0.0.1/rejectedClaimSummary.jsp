<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0);//proxies
	response.setDateHeader("Expires", -1);
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS|Rejected Claim</title>
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
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="assets/css/popup.css">
<link rel="stylesheet" href="assets/css/Customizations.css">

<script src="Starter_files/jQuery-2.js"></script>
<script src="Starter_files/bootstrap.js"></script>
<script src="Starter_files/app.js"></script>
<script src="assets/plugins/select2/select2.full.min.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.js"></script>
<script
	src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>


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
		
		<!-- Left side column. contains the logo and sidebar -->
		<s:include value="menubar.jsp" />
		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">



			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="box">
							<div class="row" style="text-align: center">
								<h2>Claim Rejected</h2>
							</div>
							<div class="row ">
								<div class="col-md-6 ">
									<table style="width: 100%;" class="table">
										<tr>
											<td style="text-align: right; width: 50%;">Claim ID:</b></td>
											<td><b><s:property value="claimID"/></b></td>
										</tr>
										<tr>
											<td style="text-align: right;">Customer ID:</b></td>
											<td><b><s:property value="custId"/></b></td>
										</tr>
										<tr>
											<td style="text-align: right;">Customer Name:</b></td>
											<td><b><s:property value="customerName"/></b></td>
										</tr>
										<tr>
											<td style="text-align: right;"><s:property value="getText('claim.pickup.addrss')"/>:</b></td>
											<td><b><s:property value="site_address"/></b></td>
										</tr>
									</table>
								</div>
								<div class="col-md-6 ">
									<table style="width: 100%;" class="table">
										<tr>
											<td style="text-align: right;">Claim Date:</b></td>
											<td><b><s:date name="claimedDate" format="%{getText('format.date')}"/></b></td>
										</tr>
										<tr>
											<td style="text-align: right;">Net Total:</b></td>
											<td><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<b><s:property value="dkartNetTot" /></b></td>
										</tr>
										<tr>
											<td style="text-align: right;">Rejected Comments:</b></td>
											<td style="word-break: break-word;"><b><s:property value="rejectClaimNotes"/></b></td>
										</tr>
									</table>
								</div>
							</div>
							<div class="row" style="text-align: center">
								<a href="approveClaim"><button class="btn">Done</button></a>
							</div>
							<br>&nbsp;
						</div>
					</div>
					<div class="col-md-2"></div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<aside class="control-sidebar control-sidebar-dark"
			style="position: fixed; height: auto;">
			<!-- Create the tabs -->
			<!--
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-home"></i></a></li>
      <li class=""><a href="#control-sidebar-settings-tab" data-toggle="tab" aria-expanded="false"><i class="fa fa-gears"></i></a></li>
    </ul>
-->
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
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
					<!-- /.control-sidebar-menu -->

					<!--        <h3 class="control-sidebar-heading">Tasks Progress</h3>-->


				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>



					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>


		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div style="position: fixed; height: auto;" class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->


	<div id="confirm_popup" class="overlay1">
		<div class="popup" style="min-height: 100px; max-width: 800px;">

			<div class="form-group has-feedback">
				<div class="row">
					<h4 style="text-align: center;">
						<b>Confirming Order</b>
					</h4>
				</div>
				<div class="row">
					<div class="col-md-6">
						<a href="Customer_Search_neworder.html">
							<button class="btn "
								style="width: 120px; background: #bf9000; background: -webkit-linear-gradient(#073763, #3d85c6 50%, #073763); background: linear-gradient(#073763, #3d85c6 50%, #073763); color: #fff;"
								type="submit" class="btn ">Ok</button>
						</a>
					</div>
					<div class="col-md-6">
						<button class="btn pull-right"
							style="width: 120px; background: #bf9000; background: -webkit-linear-gradient(#073763, #3d85c6 50%, #073763); background: linear-gradient(#073763, #3d85c6 50%, #073763); color: #fff;"
							type="submit" class="btn ">Upload LPO</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>