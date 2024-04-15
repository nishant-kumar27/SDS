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

<link href="custom/chosen/chosen.min.css" rel="stylesheet" />
<script src="custom/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="custom/format.js"></script>

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
.form-control {
	display: block;
	width: 100%;
	height: 25px;
	/* padding: 6px 12px; */
	padding: 0px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
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
        <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><s:property value="getText('edi.help_text')"/>  </b></small>
      <div class="row">
        <div class="col-xs-12">
          <h2 style="color: #226e71;">
             <s:property value="getText('edi.options.header')"/>
          </h2>
        </div>
        <!-- /.col -->
      </div>
       <hr/>
       <div class="row">
       
        <div class="col-md-7">
        	       	
			<form action="handleEDIUpload" method="post" onSubmit="$('#inprocess').addClass('fa-spinner fa-spin');;$('#confirmorder').prop('disabled',true);">
			<input type="hidden" name = "customerID" value="<s:property value="customerID"/>"/>
			<input type="hidden" name = "ediFileName" value="<s:property value="ediFileFileName"/>"/>
        	<table class="table table-bordered" width="50%">
			<tr><td><s:property value="getText('customer.id')"/></td><td><b><s:property value="customerID"/></b></td></tr>
        	<tr><td><s:property value="getText('edi.file.delivery.address')"/></td><td><s:property value="deliveryAddress"/></td></tr>
			<tr><td><s:property value="getText('edi.order.select.delivery.address')"/></td>
				<td>
				<select id="siteId" name="siteId" class="form-control"
											style="margin: 1px;" autofocus>
												<s:iterator value="customer.customerSite">
													<s:iterator value="customerSiteAddressList">
														<s:if test="%{tyAds=='1'}">
															<option
																value=<s:property value="[1].customerSitePK.custSiteId"/>
																<s:if test ="%{orderTran.getIdBtchInvResv()== [1].customerSitePK.custSiteId}">selected</s:if>>
																<s:property value="a1Cnct" />
																<s:if test="%{a2Cnct!=null}">, <s:property
																		value="a2Cnct" />
																</s:if>
																<s:if test="%{ciCnct!=null}">, <s:property
																		value="ciCnct" />
																</s:if>
																<s:if test="%{coCnct!=null}">, <s:property
																		value="coCnct" />
																</s:if>
																<s:if test="%{pcCnct!=null}">, <s:property
																		value="pcCnct" />
																</s:if>
															</option>
														</s:if>
													</s:iterator>
												</s:iterator>
										</select>
										</td></tr>
			<tr><td><s:property value="getText('order.effective.date')"/></td><td><s:property value="orderDate"/></td></tr>
			<tr><td><s:property value="getText('edi.order.deliveryDate')"/></td><td><s:property value="deliverDate"/></td></tr>
			
			<tr><td><s:property value="getText('edi.order.default.sales.agent')"/></td>
				<td><s:select theme="bootstrap" id="sales_Agent"
						list="%{(SalesAgentList!=null?SalesAgentList:{})}"
						class="form-control" style="margin: 1px;"
						value="orderTran.getEmId()"
						name="salesAgent">
					</s:select>
				</td>
			</tr>
        	<tr><td colspan="2" align="right">
        	
        	<button type="submit" id="confirmorder" class="btn"><s:property value="getText('edi.order.process.file')"/>
        		<span id="inprocess" class="fa" aria-hidden="true"></span>
        	</button>
        	<button  onClick="history.go(-1);return true;" type="button" class="btn" style="color:#ffffff; width:100px; background:    #3d85c6;
			background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
			background:    linear-gradient(#1770c1, #073763 50%, #1770c1); 
			color:         #fff;
			display:       inline-block;">
            <s:property value="getText('back.button')"/>
            <s:token/> <!-- To Avoid Duplicate form submissions -->
            </button>
        	</td></tr>
			</table>
			</form>
        	
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

<script type="text/javascript">
	$(function(){
  		$('#sales_Agent').chosen();
  	});
</script>
 	
</body></html>