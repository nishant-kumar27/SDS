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
<title>SDS | Book Order</title>
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


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
<style>
.radio {
	margin: 0px;
}

.btn {
	border-radius: 20px;
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.8);
}

.btn:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.20), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
}
</style>
<style>
body {
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
}
</style>
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
		<%--   <header class="main-header">

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
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-user fa-lg"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  Left side column. contains the logo and sidebar
  <aside class="main-sidebar">

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
          <ul class="treeview-menu">
              <li><a href="order_Search.html"><i class="fa fa-circle-o"></i> Search</a></li>
              <li><a href="Customer_Search.html"><i class="fa fa-circle-o"></i> New Orders</a></li>
            <li><a href="Open_order_Search.html"><i class="fa fa-circle-o"></i> Open Orders</a></li>
            <li><a href="Incomplete_order_Search.html"><i class="fa fa-circle-o"></i> Incomplete Orders</a></li>
            <li><a href="Delivered_order_Search.html"><i class="fa fa-circle-o"></i> Delivered Orders</a></li>
            <li><a href="Cancelled_order_Search.html"><i class="fa fa-circle-o"></i> Cancelled Orders</a></li>
            <li><a href="Returned_order_Search.html"><i class="fa fa-circle-o"></i> Returned Orders</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-file-text"></i> <span>Invoice</span> <i class="fa fa-angle-down pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li><a href="invoice_Search.html"><i class="fa fa-circle-o"></i> Search</a></li>
            <li><a href="Open_invoice.html"><i class="fa fa-circle-o"></i> Open Invoice</a></li>
            <li><a href="Payment.html"><i class="fa fa-circle-o"></i> Payment</a></li>
          </ul>
        </li>
        <li class=" treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Claim</span> <i class="fa fa-angle-down pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li class=""><a href="Generate_Claim.html"><i class="fa fa-circle-o"></i> Generate Claim</a></li>
            <li class=""><a href="Awaiting_Claim.html"><i class="fa fa-circle-o"></i> Awaiting Claim</a></li>
            <li class=""><a href="Approve_Return.html"><i class="fa fa-circle-o"></i> Approve Return</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i> <span>Customer</span> <i class="fa fa-angle-down pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li class=""><a href="Customer_Search.html"><i class="fa fa-circle-o"></i> Search</a></li>
           <li><a href="Add_New.html"><i class="fa fa-circle-o"></i> Add New</a></li>
          </ul>
        </li>
        
      </ul>
      /.sidebar-menu
    </section>
    /.sidebar
  </aside> --%>

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">


			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-6">
						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title" style="width: 100%;">
									Order Created <a href="homePage" style="color: #ffffff;"><button
											class="btn btn-block  pull-right"
											style="color: #ffffff; width: 100px; margin-top: 5px; background: #3d85c6; background: -webkit-linear-gradient(#073763, #3d85c6 50%, #073763); background: linear-gradient(#073763, #3d85c6 50%, #073763); color: #fff; display: inline-block;"
											type="button">Done</button></a>
								</h3>


							</div>

							<!-- /.box-body -->

							<!-- /.box-footer-->
						</div>
					</div>
				</div>
				<!-- Your Page Content Here -->


			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->



		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

	<script src="Starter_files/jQuery-2.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="Starter_files/bootstrap.js"></script>
	<!-- AdminLTE App -->
	<script src="Starter_files/app.js"></script>
	<!-- jQuery 2.2.0 -->




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

<%-- 	<script>
    $(function() {
      //Initialize Select2 Elements
      $(".select2").select2();

      //Datemask dd/mm/yyyy
      $("#datemask").inputmask("dd/mm/yyyy", {
        "placeholder": "dd/mm/yyyy"
      });
      //Datemask2 mm/dd/yyyy
      $("#datemask2").inputmask("mm/dd/yyyy", {
        "placeholder": "mm/dd/yyyy"
      });
      //Money Euro
      $("[data-mask]").inputmask();

      //Date range picker
      $('#reservation').daterangepicker();
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
      });

      //Date picker
      $('#lpo_datepicker').datepicker({
        autoclose: true
      });
      $('#effective_datepicker').datepicker({
        autoclose: true
      });
      $('#delivery_datepicker').datepicker({
        autoclose: true
      });
      //iCheck for checkbox and radio inputs
      $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass: 'iradio_minimal-blue'
      });
      //Red color scheme for iCheck
      $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
        checkboxClass: 'icheckbox_minimal-red',
        radioClass: 'iradio_minimal-red'
      });
      //Flat red color scheme for iCheck
      $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
        checkboxClass: 'icheckbox_flat-green',
        radioClass: 'iradio_flat-green'
      });

      //Colorpicker
      $(".my-colorpicker1").colorpicker();
      //color picker with addon
      $(".my-colorpicker2").colorpicker();

      //Timepicker
      $(".timepicker").timepicker({
        showInputs: false
      });
    });
  </script> --%>
</body>
</html>