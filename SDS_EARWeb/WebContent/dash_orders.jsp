<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>SDS | Dashboard</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<!-- Ionicons -->
<!-- Theme style -->
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

		<s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" />



		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">

			<!-- Main content -->
			<section class="content">
<!--        <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b>Find and view details of registered claims</b></small>-->
    <div class="row">
        <div class="col-md-12">
            <div class="box box-primary">
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;"> <s:property value="getText('orders.label')"/> </h3> - &nbsp;<i><h3 class="box-title" style="color: #226e71;" id="getRangeLabel"></h3></i>
                <div class="dropdown pull-right" style="margin-right:10px;">
                        <i class="fa  fa-bars fa-lg dropdown-toggle"  type="button" data-toggle="dropdown"></i>
                        <ul id="range_orders" style="margin-left:-150px;background-color: #656a6b;" class="dropdown-menu ">
                        <li><a id="week"> <s:property value="getText('current.week')"/> </a></li>
                        <li><a id="month"> <s:property value="getText('current.month')"/>  </a></li>
                        <li><a id="quarter">  <s:property value="getText('current.quarter')"/>  </a></li>
                        <li><a id="year">  <s:property value="getText('current.year')"/>  </a></li>
                        <li><a id="NONE">  <s:property value="getText('none')"/>  </a></li>
                    </ul>   
                </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding table-responsive pre-scrollable" style="max-height:500px;">
              <table id="order_table" class="table table-striped">
                <tbody>
                <tr style="background-color:#ADC2EE;">
                    <th style="width: 10px"> <s:property value="getText('table.head.SNo')"/> </th>
                    <th> <s:property value="getText('order.id')"/> </th>
                    <th  style="text-align:center;"><s:property value="getText('order.date')"/> </th>
                    
                    <th> <s:property value="getText('customer.name')"/> </th>
                    <th> <s:property value="getText('status.label')"/> </th>
                    <th style="text-align:right;"><s:property value="getText('LPO.label')"/> </th>
					
                   
                    <th> <s:property value="getText('sales.agent')"/> </th>
                    <th> <s:property value="getText('effective.date')"/> </th>
                    <th style="text-align:right;"><s:property value="getText('order.total')"/>(<s:property value="getText('global.currency')" />)</th>
                    
                </tr>
                <s:set var="totalorders" value="0"/>
                <s:iterator  value="orders" status="itStatus">  
                <s:set var="totalorders" value="%{#totalorders + orderTotal}" />
				<fieldset>  
				<tr>
				<td><s:property value="#itStatus.count" />.</td>
				<%-- <td id="order_id"><a href="#"><s:property value="id.orderId"/></a>   </td> --%>
				<td><a href="orders_details_page?orderId=<s:property value="id.orderId"/>"><s:property value="id.orderId"/></a>   </td>
				<td style="text-align:center;"><s:date name="id.orderDate" format="%{getText('format.date')}"/>  </td>
				
				
				<td><s:property value="customerName"/>  </td>
				<td><s:property value="status"/>  </td>
				<td style="text-align:right;"><s:property value="lpoNumber"/>  </td>
			
				<td style="word-break: break-word;"><s:property value="salesAgent"/>  </td>
				<td><s:date name="effectiveDate" format="%{getText('format.date')}"/>  </td>
				<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{orderTotal})}"/> </td>
				</tr> 
				</fieldset>  
				</s:iterator>  
                
               
              </tbody>
            </table>
            </div>
             <!-- Add Totals in Dashboard @sharanya -->
          <div class="progress" style="height: 2px; margin-bottom: 2px;">
				<div class="progress-bar" style="width: 100%; background-color: #ADC2EE;"></div>
			</div>
			<div class="row"> 
			<div class="col-xs-4 col-md-5 col-xs-4 pull-right">
			<div class="table-responsive">
            <table class="table table-condensed">
              <tbody>
              	<tr><td class="pull-right"><s:property value="getText('order.total')"/>: <s:property value="getText('global.currency')"/> <b><s:property value="%{getText('format.currency.args',{#totalorders})}"/>&nbsp&nbsp&nbsp&nbsp&nbsp</b></td></tr>
              </tbody>
            </table>
            </div>
            </div>
          </div>
        </div>
    </div>
    </section>
	<form action="orders_dashboard" name="hidden_data_for_range" method="post">
    	<input type="hidden" name="empId"/>
    	<input type="hidden" name="range"/>
    	<input type="hidden" name="status"/>
	</form>
	<form action="orders_details_page" name="hidden_data_for_details" method="post">
    	<input type="hidden" name="empId"/>
    	<input type="hidden" name="orderId"/>
	</form>
			<!-- /.content -->
		</div>


	</script>
	<script>
$(function(){
    var order_range="quarter";
    
    $("#range_orders li").click(
        function(evt){
            order_range=evt.target.id;
            document.forms["hidden_data_for_range"].empId.value="<s:property value="#session['userId']"/>";
            document.forms["hidden_data_for_range"].range.value=order_range;
            document.forms["hidden_data_for_range"].status.value="<s:property value="status"/>";
            document.forms["hidden_data_for_range"].submit();
        }
    ); 
    
    $("#order_table #order_id a").click(
    		function(evt){
    			console.log(evt.currentTarget["innerText"]);
    			document.forms["hidden_data_for_details"].empId.value="<s:property value="#session['userId']"/>";
                document.forms["hidden_data_for_details"].orderId.value=evt.currentTarget["innerText"];
                document.forms["hidden_data_for_details"].submit();
    			
    		}
    );
    	  
});    
    
    
</script>
  <script type="text/javascript">
   var searchRange = "${range}";
	$(function(){
             if(searchRange=="week"){
            	 document.getElementById("getRangeLabel").innerHTML = "<s:property value="getText('current.week')" />";
             }
             else if(searchRange=="month"){
            	 document.getElementById("getRangeLabel").innerHTML = "<s:property value="getText('current.month')" />";
             }
             else if(searchRange=="quarter"){
            	 document.getElementById("getRangeLabel").innerHTML = "<s:property value="getText('current.quarter')" />";
             }
             else if(searchRange=="year"){
            	 document.getElementById("getRangeLabel").innerHTML = "<s:property value="getText('current.year')" />";
             }
             else if(searchRange=="NONE"){
            	 document.getElementById("getRangeLabel").innerHTML = "<s:property value="getText('none')" />";
             }
   }); 
  </script>
	<script type="text/javascript">
    $('#dashboard').addClass('active');
  </script>
</body>
</html>