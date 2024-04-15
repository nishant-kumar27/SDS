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
<title>SDS | Payment</title>
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
	/* box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.8); */
}

/* .btn:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.20), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
} */
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


		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">


			    <!-- Main content -->
    <section class="content">
        
        <div class="row">
            <div class="col-md-12"> 
        
            <section class="invoice">
                <small class="pull-right hidden-print" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b> <s:property value="getText('donepayment.help_text')"/></b></small>
      <!-- title row -->
      <div class="row">
        <div class="col-xs-12">
          <h2 style="color: #226e71;">
             <s:property value="getText('payment.details')"/>
              <div class="pull-right"><h5>
                    <a href="#Mail" class="pull-right hidden-print" style="text-decoration:none"><i class="fa fa-envelope"></i>  <s:property value="getText('mail.label')"/>  &nbsp;</a> 
                    <a href="javascript:void(0);" onclick="windowPrint()" class="pull-right hidden-print" style="text-decoration:none" id="print"><i class="fa fa-print"></i> <s:property value="getText('print.label')"/>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a>
                  <a href="#"  class="pull-right hidden-print" style="text-decoration:none"><i class="fa fa-save"></i> <s:property value="getText('save.label')"/>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a></div>
                  
<!--            <small class="pull-right">Date: 2/10/2014</small>--></h5>
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
        <div class=" col-md-4 invoice-col">
        <table style="width:100%;" class="table">
            <tr><td style="text-align:right;width:50%;"><s:property value="getText('customer.id')"/> :</b></td>
            <td><b><s:property value="payment_details.customerId"/></b></td></tr>
            <tr><td style="text-align:right;width:50%;"><s:property value="getText('trasaction.id')"/>:</b></td>
            <td><b><s:property value="payment_details.paymentTransactionId"/></b></td></tr>
        </table>
        </div>
          <div class=" col-md-4 invoice-col">
        <table style="width:100%;" class="table">
            <tr><td style="text-align:right;width:50%;"><s:property value="getText('customer.name')"/>:</b></td>
            <td><b><s:property value="payment_details.customerName"/></b></td></tr>
            
              <tr><td style="text-align:right;width:50%;"><s:property value="getText('collections.payment.date')"/>:</b></td>
              <td><b><s:date name="payment_details.businessDate" format="%{getText('format.date')}"/></b></td></tr>
            
            
        </table>
        </div>
          <div class="col-md-4 invoice-col">
        <table style="width:100%;" class="table">
            <tr><td style="text-align:right;width:50%;"><s:property value="getText('customer.segment')"/>:</b></td>
            <td><b><s:property value="payment_details.customerSegmentationId"/></b></td></tr>
        </table>
        </div>
        <!-- /.col -->
        
        <!-- /.col -->
        
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped">
            <thead>
            <tr style="background-color:#ADC2EE;">
              <th><s:property value="getText('table.head.SNo')"/></th>
              <th><s:property value="getText('tender.type')"/></th>
              <th><s:property value="getText('tender.details')"/></th>
              
            </tr>
            </thead>
            <tbody>
	<!-- Loop the Tender detail -->
		<s:iterator value="payment_details.tenderDetails" status="itStatus">
		<tr>
               <td><s:property value="%{#itStatus.index+1}"/></td>
               
              <td><s:property value="tenderMode"/></td>
			  <s:if test="tenderMode.toString()=='CHCK'">
                <td><table style="max-width:1000px;" class="table">
                           <tr style="text-align:right;"><th style="width:20%;"><s:property value="getText('customer.details')"/>:</th><td> </td><th style="width:20%;"><s:property value="getText('salam.details')"/>:</th><td> </td></tr>
                        <tr ><td style="width:20%;text-align:right;" style=""><s:property value="getText('payment.cheque.customerBankName')"/>:</td><td> <s:property value="customerBankName"/> </td>
                        <td style="width:20%;text-align:right;"><s:property value="getText('payment.cheque.depositBankname')"/>:</td><td><s:property value="depositBankName"/> </td></tr>
                        <tr ><td style="width:20%;text-align:right;" style=""><s:property value="getText('payment.cheque.customerBankLocation')"/>:</td><td> <s:property value="customerBankLocation"/></td>
                        <td style="width:20%;text-align:right; "><s:property value="getText('payment.cheque.depositBankLocation')"/>:</td><td><s:property value="depositBankLocation"/></td></tr>
                        <tr><td  style="text-align:right;"><s:property value="getText('payment.cheque.customerAccountNumber')"/>:</td><td><s:property value="customerAccountNo"/></td>
                        <td  style="text-align:right;" ><s:property value="getText('payment.cheque.chequeDepositDate')"/>:</td><td><s:property value="chequeDepositDate"/></tr>
                        <tr><td style="width:20%;text-align:right;"><s:property value="getText('payment.cheque.checkNumber')"/>:</td><td> <s:property value="chequeNumber"/></td>
                        <td style="text-align:right;"><s:property value="getText('payment.cheque.chequeAmount')"/>:</td><td><s:text name="global.currency"/> <s:property value="chequeAmount"/></td></tr>  
                        </table> </td>
             </s:if>
             <s:else>
             <td><s:text name="global.currency" /> <s:property value="tenderAmount"/></td>
             </s:else>
            </tr>
            </s:iterator>
            </tbody>
          </table>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <div class="row">
        <!-- /.col -->
        

          
        <a href="homePage" class="hidden-print">  
        <button type="button" class="btn btn-block pull-right hidden-print" style="width:110px;margin-top:5px; background:    #3d85c6;
background: -webkit-linear-gradient(#717171, #262626 20%, #717171); background: linear-gradient(#1770c1, #073763 50%, #1770c1);
color:         #fff;
display:       inline-block;">
           <s:property value="getText('done.button')"/>
          </button>
        </a>
        
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <!-- this row will not appear when printing -->
      <div class="row">
        <div class="col-xs-12">
          
          
        </div>
      </div>
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
<!-- having function for print --> 
     <script src="custom/printFunction.js"></script>
<!-- java script function to enable print for the payment details -->
<%--  <script>	 
	function  windowPrint(){
		window.print();
	}
</script>	 --%>
	
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