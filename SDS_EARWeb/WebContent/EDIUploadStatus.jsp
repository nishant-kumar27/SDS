<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>SDS</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
  
  <!-- Font Awesome -->
        <link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />

  <!-- Theme style -->
  <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="assets/css/Customizations.css">
    <!-- jQuery 2.2.0 -->
<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
  <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
  
  <style type="text/css">
  .mbg-blue {
    background-color: #00c0ef !important;
     }
  </style>
  <style type="text/css">
.errorMessage{
	list-style: none;
	color: red;
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
    
      <!-- Your Page Content Here -->
    <section class="invoice">
      <!-- title row -->
      <div class="row">
        <div class="col-xs-12">
          <h2 style="color: #226e71;">
             <s:property value="getText('edi.uoload.success')"/>
          </h2>
        </div>
        <!-- /.col -->
      </div>
       <hr/>
       <div class="row">
       
        <div class="col-md-5">
        	       	
			
        	<table class="table table-bordered" width="40%">
			<tr><td><s:property value="getText('customer.id')"/></td><td><b></b><s:property value="customerID"/></b></td></tr>
        	<tr><td><s:property value="getText('edi.upload.delivery.address')"/></td><td><b><s:property value="deleveryAddr"/></b></td></tr>
			<tr><td><s:property value="getText('order.date')"/></td><td><b><s:date name="orderDate" format="%{getText('format.date')}" /></b></td></tr>
			<tr><td><s:property value="getText('order.id')"/></td><td><b><s:property value="orderId"/></b></td></tr>
			<tr><td><s:property value="getText('sales.agent')"/></td><td><b><s:property value="salesAgentName"/></b></td></tr>
			<tr><td><s:property value="getText('order.total')"/></td><td>
				<strong><s:property value="%{getText('format.currency.args',{orderTotal})}"/></strong>
			</td></tr>
        	<tr><td colspan="2" align="right">
				<form action="customerSearch" method="post">
        			<button type="submit" class="btn"><s:property value="getText('done.button')"/></button>
        		</form>
        	</td></tr>
			</table>
        	
        </div>
        
        </div>
         <div class="row">
        <!-- /.col -->
        <!-- /.col -->
      </div>
      <!-- /.box-header -->
    </section>
     
    
    </section>
    <!-- /.content -->
    
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  

  
  <!-- /.control-sidebar -->
  
</div>

<!-- To show confirm cancel model -->
	
	<!-- End of model -->

<!-- ./wrapper -->

<!-- ChartJS 1.0.1 -->
 
</body></html>