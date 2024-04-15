<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<style type="text/css">
.errorMessage{list-style: none;}
</style>
</head>
<%-- <script type="text/javascript">
$(function(){
	 $("#confirmorder").click(function(e)
		    {
		     $("#printOption").modal('show');
		    });
}); 

$("#printorder").click(
		function(e) {
			$("#customerSearch").submit();
		});  
		
</script> --%>


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
								<h2><s:property value="invStatus"/></h2>
							</div>
								<s:if test="hasActionErrors()">
									<div class="row" style="text-align: center">
     									 <s:actionerror/>
     								</div>
								</s:if>
								<s:else>
								
								<div class="row ">
								<div class="col-md-6 ">
									<table style="width: 100%;" class="table">
										<tr>
											<td style="text-align: right; width: 50%;"><s:property value="getText('invoice.no')"/>:</b></td>
											<td><b><s:property value="invoiceId"/></b></td>
										</tr>
										<tr>
											<td style="text-align: right; width: 50%;"><s:property value="getText('order.id')"/>:</b></td>
											<td><b><s:property value="orderId"/></b></td>
										</tr>
										
									</table>
								</div>
								<div class="col-md-6 ">
									<table style="width: 100%;" class="table">
									<tr>
											<td style="text-align: right;"><s:property value="getText('customer.id')"/>:</b></td>
											<td><b><s:property value="custId"/></b></td>
										</tr>
										<tr>
											<td style="text-align: right;"><s:property value="getText('customer.name')"/>:</b></td>
											<td><b><s:property value="custName"/></b></td>
										</tr>
										
									</table>
								</div>
							</div>
							</s:else>
							
							<form action="invoices"> 
							<div class="row" style="text-align: center">
								<button type="submit" id="confirmorder" class="btn"><s:property value="getText('done.button')"/></button></a>
							</div>
							</form>
							<br>&nbsp;
						</div>
					</div>
					
		 			<!-- added by sharanya for popup -->
					
				<div id="confirm_order" class="overlay1">
   					 <div class="popup" style="min-height:100px;max-width:800px;">
    
      					<div class="form-group has-feedback">
         				<div class="row">
           					<h4 style="text-align:center;"><b><s:property value="getText('_promptmsg.confirm.order')"/></b></h4>
          			</div>
          <div class="row">
            <div class="col-md-6">
                  <a href="customerSearch">
                    <button class="btn " style="width:120px;background:    #bf9000;
                       background:    -webkit-linear-gradient(#073763, #3d85c6 50%, #073763);
                       background:    linear-gradient(#073763, #3d85c6 50%, #073763);color: #fff;" type="submit" class="btn "><s:property value="getText('yes.button')"/></button>
                  </a>
            </div>
              <div class="col-md-6">
                  <a href="customerSearch">
                    <button class="btn pull-right" style="width:120px;background:    #bf9000;
                       background:    -webkit-linear-gradient(#073763, #3d85c6 50%, #073763);
                       background:    linear-gradient(#073763, #3d85c6 50%, #073763);color:#fff;" type="submit" class="btn "><s:property value="getText('no.button')"/></button>
                </a>
            </div>
            </div>
      </div>
    </div>
    </div> 
    <!-- modal to show when you click on done button @sharanya -->
    <%-- <form action="customerSearch">
    <div class="modal fade bs-example-modal-sm" id="printOption"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 id="errorTitle" class="modal-title">
							<s:property value="getText('_promptmsg.confirm.order')" />
						</h4>
					</div>
					<div class="modal-footer" style="margin:auto;max-width:300px;">
						<button type="submit" class="pull-right btn btn-primary center-block"
							 id="printorder">
							<s:property value="getText('no.button')" />
							&nbsp;
						</button>
						
						<button type="submit" class="pull-left btn btn-primary center-block"
							id="printorder">
							<s:property value="getText('yes.button')" />
							&nbsp;
						</button>
					</div>
				</div>

			</div>
			</div>
			</form> --%>
			
			
					<div class="col-md-2"></div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->



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
						<b><s:property value="getText('confirm.order')"/></b>
					</h4>
				</div>
				<div class="row">
					<div class="col-md-6">
						<a href="Customer_Search_neworder.html">
							<button class="btn "
								style="width: 120px; background: #bf9000; background: -webkit-linear-gradient(#073763, #3d85c6 50%, #073763); background: linear-gradient(#073763, #3d85c6 50%, #073763); color: #fff;"
								type="submit" class="btn "><s:property value="getText('ok.button')"/></button>
						</a>
					</div>
					<div class="col-md-6">
						<button class="btn pull-right"
							style="width: 120px; background: #bf9000; background: -webkit-linear-gradient(#073763, #3d85c6 50%, #073763); background: linear-gradient(#073763, #3d85c6 50%, #073763); color: #fff;"
							type="submit" class="btn "><s:property value="getText('upload.lpo')"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>