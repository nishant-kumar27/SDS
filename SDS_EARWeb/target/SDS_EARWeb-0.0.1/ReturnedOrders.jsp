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
<title>SDS | Returned Orders</title>
<!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" type="text/css"/>
	<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css" type="text/css"/>
	<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css" type="text/css" />
    <link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="assets/css/jquery-ui.css">
    <link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
    <!-- <link rel="stylesheet" href="assets/plugins/daterangepicker/daterangepicker-bs3.css">
    <link rel="stylesheet" href="assets/css/popup.css"> -->
    <link rel="stylesheet" href="assets/css/Customizations.css">

  <script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
<!-- jQuery 2.2.0 -->
<!-- Select2 -->
<script src="assets/plugins/select2/select2.full.min.js"></script>
<!-- InputMask 
<script src="assets/plugins/input-mask/jquery.inputmask.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>-->
<script src="assets/plugins/jQueryUI/jquery-ui.js"></script>
<!-- date-range-picker 
<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>-->
<!-- bootstrap datepicker 
<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>-->
<script src="assets/plugins/datepicker/datepicker-bootstrap.js"></script>
<script src="assets/moment.min.js"></script>
 <script type="text/javascript" src="custom/dateRangeFilter.js"></script>
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

				<div class="row" style="margin: 5px; margin-top: -15px;">
					<!-- Your Page Content Here -->
					<label style="font-size: 25px; color: #226e71;"> <s:property
							value="getText('return.order.search')" /></label> <small
						class="pull-right"
						style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><b><s:property
								value="getText('returned.order.search.help_text')" /></b></small> <br>

					<div class="nav-tabs-custom "
						style="min-height: 100px; min-width: 700px; max-width: 750px;">
						<ul style="margin-left: 15px;" class="nav nav-pills">
							<li class="active"><a data-toggle="tab" href="#tab_1"
								aria-expanded="true"><s:property
										value="getText('order.label')" /></a></li>
							<!-- <li class=""><a data-toggle="tab" href="#tab_2" aria-expanded="false">Invoice</a></li> -->
							<li class=""><a data-toggle="tab" href="#tab_3"
								aria-expanded="false"><s:property
										value="getText('customer.label')" /></a></li>
						</ul>

						<table class="table"
							style="width: 650px;; margin-left: 15px; border-width: 0px;">
							<tr>
								<td style="width: 80%; border-top: 1px solid #ffffff;">
									<div style="height: 100px;" class="tab-content">
										<div id="tab_1" class="tab-pane active">
											<table style="width: 100%;">
												<tr>
													<td style="width: 10%;"><s:property
															value="getText('order.id')" />:</td>
													<td style="width: 30%;"><input id="orderID"
														name="orderID" style="margin: 1px; width: 52%;"
														type="text" class="form-control"></td>
												</tr>
												<tr>
													<td style="width: 10%;"><s:property
															value="getText('order.date.range')" />:</td>
													<td style="width: 30%;">

														<table>
															<tr>
																<td><input style="margin: 1px; width: 95%;"
																	type="text" placeholder="From" class="form-control"
																	id="order_date_from"></td>
																<td><input style="margin: 1px; width: 95%;"
																	type="text" placeholder="To" class="form-control"
																	id="order_date_to"></td>
															</tr>
														</table>

													</td>
												</tr>
												<tr>
													<td style="width: 10%;"><s:property
															value="getText('order.totals')" />:</td>
													<td style="width: 30%;">
														<table>
															<tr>
																<td><input id="OrderTotalFrom"
																	style="margin: 1px; width: 95%;" type="text"
																	placeholder="From" class="form-control" onkeypress='return isNumberKey(event);' onpaste="return false;"></td>
																<td><input id="OrderTotalTo"
																	style="margin: 1px; width: 95%;" type="text"
																	placeholder="To" class="form-control" onkeypress='return isNumberKey(event);' onpaste="return false;"></td>
															</tr>
														</table>


													</td>
												</tr>
											</table>
										</div>

										<!-- /.tab-pane -->
										<%--  <div id="tab_2" class="tab-pane">
               <table style="width:100%;">
                  <tr>
                  <td style="width:10%;">
                    <s:property value="getText('invoice.number.label')"/>:</td>
                      <td style="width:30%;">
                  <input id="invoiceID" style="margin:1px; width:52%; " type="text" class="form-control">
                  </td>
                  </tr>
                    <tr>
                  <td style="width:10%;">
                      <s:property value="getText('invoice.date.range')"/>:</td>
                      <td style="width:30%;">
                  
                  <table><tr><td><input style="margin:1px;width:95%;" type="text" placeholder="From" class="form-control" id="datepicker3"></td>
                            <td><input style="margin:1px;width:95%;" type="text" placeholder="To" class="form-control" id="datepicker4"></td></tr></table>
                        
                  </td>
                  </tr>
                <tr>
                    <td style="width:10%;">
                        <s:property value="getText('invoice.totals')"/>:
                    </td>
                    <td style="width:30%;">
                        <table><tr><td><input id="InvoiceTotalFrom" style="margin:1px;width:95%;" type="text" placeholder="From" class="form-control" ></td>
                            <td><input id="InvoiceTotalTo" style="margin:1px;width:95%;" type="text" placeholder="To" class="form-control"></td></tr></table>
                        
                        
                    </td>
                  </tr>
                  </table>                             
                
              </div> --%>
										<!-- /.tab-pane -->
										<div id="tab_3" class="tab-pane">
											<table style="width: 100%;">
												<tr>
													<td style="width: 10%;"><s:property
															value="getText('cust.id.label')" />:</td>
													<td style="width: 30%;"><input id="customerInfo"
														style="margin: 1px; width: 52%;" type="text"
														class="form-control"></td>
												</tr>
												<tr>
													<td style="width: 10%;"><s:property
															value="getText('item.id.label')" />:</td>
													<td style="width: 30%;"><input id="RtnItemId"
														style="margin: 1px; width: 52%;" type="text"
														class="form-control"></td>
												</tr>
											</table>
										</div>
										<!-- /.tab-pane -->
										<!-- /.tab-pane -->

										<!-- /.tab-pane<div id="divCheckbox" style="display: none;"> -->
									</div>
								</td>
								<td style="border-top: 1px solid #ffffff;">
								<form class="radio inline"style="display: none;">
									<div class="form-group" >  
                <label class="radio inline"><input id="99" type="radio" name="Invoice_Search" value="All" checked>
                <span><s:property value="getText('all_inv')"/></span></label><br>
                <label class="radio inline"><input id="1" type="radio" name="Invoice_Search" value="Open Invoices">
                <span><s:property value="getText('open_inv')"/></span></label><br>  
                <label class="radio inline"><input id="0" type="radio" name="Invoice_Search" value="Closed Invoices">
                <span><s:property value="getText('closed_inv')"/> </span></label>                    
                </div>    
                    </form>
                     <button onclick="searchInvoice()" class="btn pull-right" id="returnOrderSearch"
									style="margin-left: 0px; color: #ffffff; width: 100px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; border: 1px solid; display: inline-block;"
									type="button">
									 <s:property value="getText('search.button')"/>&nbsp;<i class="fa"></i>
								</button></td></tr></table>
           
                <lab name="Info_Text" style="font-style:italic;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="getText('message_2')"/></lab>
                
            </div>
            <!-- /.tab-content -->
              
            
       <!--  manish code -->
            
                 </div> 
             <div class="row">
			<div class="col-md-12">
		<s:set var="results" value="%{returnedOrdersVOlist.size}"/>
			
 			 <s:if test="%{#results==null}">
 			 
 			 </s:if>
 			 <s:elseif test="%{#results==0}">
   			 
   			 <label style="color:red;"><span id="noOfOrd"><s:property value="results"/></span> <s:property value="getText('orders.found')"/></label>
			</s:elseif>
			<s:elseif test="%{#results>0}">
 			
 			 <label style="color:green;"><span id="noOfOrd"><s:property value="results"/></span> <s:property value="getText('orders.found')"/></label>
 			 </s:elseif>
 			 <s:else>
 				 <label style="color:rgb(0,166,90);"><span id="noOfOrd"><s:property value="results"/></span> <s:property value="getText('orders.found')"/></label>
 			 </s:else> 
			<%-- <s:else test="!hasActionErrors()">
 				 <label style="color:rgb(0,166,90);"><span id="noOfInv"><s:property value="results"/></span> <s:property value="getText('claim.found')"/></label>
 			 </s:else> --%>
			
              <!-- END
          -->
            <!-- /.box-body --> 
        <div class="box box-primary">
            
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;"><s:property value="getText('returned.orders')"/></h3> &nbsp;<i><h3 class="box-title" style="color: #226e71;" id="Current_Date_Range"></h3></i>
                <div class="box-tools">
               <div class="dropdown pull-right" style="margin-right:10px;">
                        
                       
                        <i class="fa  fa-bars fa-lg dropdown-toggle"  type="button" data-toggle="dropdown"></i>
                        <ul style="margin-left:-90px;background-color:  #656a6b;color:white;" class="dropdown-menu ">
                        <li><a href="javascript:void(0)" class="week" id="week" onclick='rangeFilter("returndate","returnorderList",this.id);'><s:property value="getText('week_to_date')"/></a></li>
                        <li><a href="javascript:void(0)" class="month" id="month" onclick='rangeFilter("returndate","returnorderList",this.id);'><s:property value="getText('month_to_date')"/></a></li>
                        <li><a href="javascript:void(0)"  class="quarter" id="quarter" onclick='rangeFilter("returndate","returnorderList",this.id);'><s:property value="getText('quarter_to_date')"/></a></li>
                        <li><a href="javascript:void(0)"  class="year" id="year" onclick='rangeFilter("returndate","returnorderList",this.id);'><s:property value="getText('year_to_date')"/></a></li>
                        <li><a href="javascript:void(0)" class="none" id="none" onclick='rangeFilter("returndate","returnorderList",this.id);'><s:property value="getText('none')"/></a></li>
                        </ul>
                            
                </div>
                </div>
            </div>
            
            <!-- /.box-header -->
            <div class="box-body no-padding table-responsive">
                
              	<table class="table table-striped table-condensed" id="returnTable">

							<%-- <style>
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	border-top: 1px solid #d8d8d8;
}

.table-striped>tbody>tr:nth-of-type(2n+1) {
	background-color: #F2F2F2;
}
</style>--%>
							<thead>
								<tr style="background-color: #ADC2EE;">
									<th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
									<th><s:property value="getText('order.id')"/></th>
									<th><s:property value="getText('return.order.id')"/></th>
									<th id="returndate"><s:property value="getText('return.order.date')"/></th>
									<th class="text-right"><s:property value="getText('return.order.total')"/>(<s:property value="getText('global.currency')"/>)</th>
									<th class="text-right"><s:property value="getText('customer.id')"/></th>
									<th><s:property value="getText('customer.name')"/></th>
									<th><s:property value="getText('sales.agent')"/></th>
									<th><s:property value="getText('reason.code')"/></th>
							   </tr>
							</thead>
							
							<tbody id="returnorderList">
<s:iterator value="returnedOrdersVOlist"  status="itStatus">
            <tr>      
       <td>
      <s:property value="%{#itStatus.count}" />.</td>
      <td id="OriginalOrderId">
       <a href="invoice_orders_details_page?orderId=<s:property value="originalOrderId"/>"><s:property value="originalOrderId"/></a>
      </td>
      <td id="OrderId">
       <a href="returnedOrdersDetailsPage?orderId=<s:property value="orderId"/>"><s:property value="orderId"/></a>
      </td>
      <td>
      			 <%-- <s:property value="orderDate"/> --%>
           <s:date name="orderDate" format="%{getText('format.date')}"/>
      </td>
      <td style="text-align: right;">
            <s:property value="%{getText('format.currency.args',{orderTotal})}"/>
      </td>
      <td style="text-align: right;">
             <s:property value="customerId"/>
      </td>
      <td>
             <s:property value="customerName"/>
      </td>
      <td>
             <s:property value="salesAgent"/>
      </td>
      <td style="text-align:middle;">
             <s:property value="returnReasonCodeMap[reasonCode]"/>
      </td>      
            
      </tr>
      </s:iterator>
							</tbody>
						
						</table>
							
            </div>
     
            <!-- /.box-body -->
          </div>        
    </section>
       
 <!--  <button onclick="markAsDelivered()" class="btn pull-right" id="markDelivered"
				style="margin-left: 0px; color: #ffffff; width: 200px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; border: 1px solid; display: inline-block;"
						type="button"><s:property value="getText('markas.delivered.button')"/>&nbsp;<i class="fa"></i>
			</button>  -->
    <!-- /.content -->
  </div> 

	</div>
	<!-- ./wrapper -->
<!-- <form action="DeliveredOrders_details_page" name="hidden_order_data" method="post">
    <input type="hidden" name="empId"/>
    <input type="hidden" name="orderId"/>
</form> -->

	<!-- when no invoices found @mallikarjun -->
		<div class="modal fade bs-example-modal-sm" id="noOrdFnd"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"><s:property value="getText('message_3')"/></h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal"><s:property value="getText('ok.button')"/>&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>
		
		<script>
		var range = "${range}";
  $(function() {
    	

		/* AJAX item Search */
	$("input#RtnItemId").autocomplete({
	/*   Enabling the Qty textbox after selecting any item by firing autocomplete select event */
		source : function(request, response) {

			//$('.ui-autocomplete-loading').removeClass("ui-autocomplete-loading");

			if (request.term.length > 2) {
				/*  document.getElementById("loadText").style.display ="none"; */
				//document.getElementById("load").style.display = "block";  // show the loading image.
				//$('.ui-autocomplete-loading').addClass("ui-autocomplete-loading");
				$("#RtnItemId").addClass("ui-autocomplete-loading");

				$.ajax({
					url : "searchItemAction",
					type : "POST",
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(jsonResponse) {
						response(jsonResponse.list);
						/*   document.getElementById("load").style.display = "none"; */
						$("#RtnItemId").removeClass("ui-autocomplete-loading");
					}

				});
			} else {
				/* document.getElementById("load").style.display = "none"; */
				$("#RtnItemId").removeClass("ui-autocomplete-loading");
			}
		}
	});
	
	$("#RtnItemId").autocomplete("widget").attr('style',
			'max-height: 300px; overflow-y: auto; overflow-x: hidden;')//to get a scroll bar

<%-- 
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
      $("[data-mask]").inputmask(); --%>
    
      
      var dateFormat= "<s:text name="bootstrap.date.format"/>";
      var datePickerProp = {todayBtn: "linked", autoclose: true, todayHighlight: true, format:dateFormat,endDate: new Date(),autoclose: true};
       $('#order_date_from').datepickerBS(datePickerProp);
      $('#order_date_to').datepickerBS(datePickerProp);
      $('#datepicker3').datepickerBS(datePickerProp);
      $('#datepicker4').datepickerBS(datePickerProp);
     /*  //Date range picker
      $('#reservation').daterangepicker();
      $('#reservation1').daterangepicker();
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
      });  */

     
    });
  </script>
  
  
    <script>
    function searchInvoice(){
    	
    	 var activeTab = $('.nav-tabs-custom div.tab-pane.active').attr('id');
    	 
    	//validating the fields not to be empty
    	  function isValidDataInTab(activeTab)
    	    {
    	    	var result= false;
    	    	/* $('#invoiceList').empty();
    	    	document.getElementById("noOfInv").innerHTML="0"; */
    	    	
    	    	
    	    	if(activeTab=='tab_1')
    	    	{	
    	    		if($('#orderID').val().length >= 1
    	    			||$('#order_date_from').val().length>=1 || $('#order_date_to').val().length>=1
    	    			||$('#OrderTotalFrom').val().length>=1 || $('#OrderTotalTo').val().length>=1)
    	    			{
    	    			result =true;
    	    			}else
    	    			{
    	    				result=false;
    	    			} 		
    	    	}else if(activeTab=='tab_2')
    	    	{
    	    		if($('#invoiceID').val().length >= 1
    	    				||$('#order_date_from').val().length>=1 || $('#order_date_to').val().length>=1
    	    				||$('#InvoiceTotalFrom').val().length>=1 || $('#InvoiceTotalTo').val().length>=1)
    	        			{
    	        			result =true;
    	        			}else
    	        			{
    	        				result =false;
    	        			}
    	    	}else if(activeTab=='tab_3')
    	    	{
    	    		if($('#customerInfo').val().length >= 1
    	        			||$('#RtnItemId').val().length>=1 )
    	        			{
    	        			   result =true;
    	        			}else
    	        			{
    	        				result= false;
    	        			}
    	    	}
    	    	if(!result)
    	    	{ 
    	    		$("lab[name='Info_Text']").css("color", "red");
    	    		
    	    	}else{
    	    		{ 
    	        		$("lab[name='Info_Text']").css("color", "black");
    	        		
    	        	}
    	    	}
    	    	return result;
    	    }
    	if(isValidDataInTab(activeTab)){
    		document.forms["hidden_order_data"].invoiceStatus.value=$('input[name="Invoice_Search"]:checked').attr('id');
    		document.forms["hidden_order_data"].invType.value= $('input[name=Invoice_Search]:checked').val(); 
    		document.forms["hidden_order_data"].activeTab.value= activeTab;
    		
    		if(activeTab === "tab_1"){
    			document.forms["hidden_order_data"].orderID.value=$('#orderID').val();
    			document.forms["hidden_order_data"].order_date_from.value=$('#order_date_from').val();
    			document.forms["hidden_order_data"].order_date_to.value=$('#order_date_to').val();
    			document.forms["hidden_order_data"].OrderTotalFrom.value=$('#OrderTotalFrom').val();
    			document.forms["hidden_order_data"].OrderTotalTo.value=$('#OrderTotalTo').val();
    			
    			document.hidden_order_data.action ="returnedOrdersByOrderInfo";
    		}/* else
    			if(activeTab === "tab_2"){
    				 document.forms["hidden_order_data"].invoiceID.value=$('#invoiceID').val();
    				 document.forms["hidden_order_data"].datepicker3.value=$('#datepicker3').val();
    				 document.forms["hidden_order_data"].datepicker4.value=$('#datepicker4').val();
    				 document.forms["hidden_order_data"].InvoiceTotalFrom.value=$('#InvoiceTotalFrom').val();
    				 document.forms["hidden_order_data"].InvoiceTotalTo.value=$('#InvoiceTotalTo').val();
    				 
    				 document.hidden_order_data.action ="returnedOrdersByOrderInfo";
    				 
    			} */else
    				if(activeTab === "tab_3"){
    					document.forms["hidden_order_data"].customerInfo.value=$('#customerInfo').val();
    					document.forms["hidden_order_data"].RtnItemId.value=$('#RtnItemId').val();
    					
    					document.hidden_order_data.action ="returnedOrdersByCustInfo";
    				}
    		                     
            //clearing the input search fields
            $("#orderID").val('');
            $("#order_date_from").val('');
            $("#order_date_to").val('');
            $("#OrderTotalFrom").val('');
            $("#OrderTotalTo").val('');
            
            $("#invoiceID").val('');
            $("#datepicker3").val('');
            $("#datepicker4").val('');
            $("#InvoiceTotalFrom").val('');
            $("#InvoiceTotalTo").val('');
            
            $("#customerInfo").val('');
            $("#RtnItemId").val('');
            
            document.forms["hidden_order_data"].submit();
    	}
    }    
    </script>
    
  
   <form name="hidden_order_data" method="post">
   
   	<input type="hidden" name="invoiceStatus"/>
    <input type="hidden" name="invType"/>
    <input type="hidden" name="activeTab"/>
    
    <input type="hidden" name="orderID"/>
    <input type="hidden" name="order_date_from"/>
    <input type="hidden" name="order_date_to"/>
    <input type="hidden" name="OrderTotalFrom"/>
    <input type="hidden" name="OrderTotalTo"/>
    
    <input type="hidden" name="invoiceID"/>
    <input type="hidden" name="datepicker3"/>
    <input type="hidden" name="datepicker4"/>
    <input type="hidden" name="InvoiceTotalFrom"/>
    <input type="hidden" name="InvoiceTotalTo"/>
    
    <input type="hidden" name="customerInfo"/>
    <input type="hidden" name="RtnItemId"/>
     </form>
	 <%-- <script type="text/javascript">
	 $("form[role='search']").hide();
    $(function() {
    	 $('#markDelivered').prop('disabled', true);

		/* AJAX item Search */
	$("input#RtnItemId").autocomplete({
	/*   Enabling the Qty textbox after selecting any item by firing autocomplete select event */
		source : function(request, response) {

			//$('.ui-autocomplete-loading').removeClass("ui-autocomplete-loading");

			if (request.term.length > 2) {
				/*  document.getElementById("loadText").style.display ="none"; */
				//document.getElementById("load").style.display = "block";  // show the loading image.
				//$('.ui-autocomplete-loading').addClass("ui-autocomplete-loading");
				$("#RtnItemId").addClass("wait");

				$.ajax({
					url : "searchItemAction",
					type : "POST",
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(jsonResponse) {
						response(jsonResponse.list);
						/*   document.getElementById("load").style.display = "none"; */
						$("#RtnItemId").removeClass("wait");
					}

				});
			} else {
				/* document.getElementById("load").style.display = "none"; */
				$("#RtnItemId").removeClass("wait");
			}
		}
	});
	
	$("#RtnItemId").autocomplete("widget").attr('style',
			'max-height: 300px; overflow-y: auto; overflow-x: hidden;')//to get a scroll bar


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
    
      
      var dateFormat= "<s:text name="bootstrap.date.format"/>";
      var datePickerProp = {todayBtn: "linked", autoclose: true, todayHighlight: true, format: dateFormat};
      $('#datepicker1').datepickerBS(datePickerProp);
      $('#datepicker2').datepickerBS(datePickerProp);
      $('#datepicker3').datepickerBS(datePickerProp);
      $('#datepicker4').datepickerBS(datePickerProp);
      //Date range picker
      $('#reservation').daterangepicker();
      $('#reservation1').daterangepicker();
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
    });
  </script> --%>

	<script type="text/javascript">
	
    function calcAge(date) {
      var d = moment(date).format('MM/DD/YY');
      alert(d);
    }

    function isValidDataInTab(activeTab)
    {
    	var result= false;
    	if(activeTab=='tab_1')
    	{	
    		if($('#orderID').val().length >= 1
    			||($('#order_date_from').val().length>=6 || $('#order_date_to').val().length>=6)
    			||($('#OrderTotalFrom').val().length>=1 || $('#OrderTotalTo').val().length>=1))
    			{
    			result =true;
    			}else
    			{
    				result=false;
    			} 		
    	}else if(activeTab=='tab_2')
    	{
    		if($('#invoiceID').val().length >= 1
        			||($('#datepicker3').val().length>=6 || $('#datepicker4').val().length>=6)
        			||($('#InvoiceTotalFrom').val().length>=1 || $('#InvoiceTotalTo').val().length>=1))
        			{
        			result =true;
        			}else
        			{
        				result =false;
        			}
    	}else if(activeTab=='tab_3')
    	{
    		if($('#customerInfo').val().length >= 1
        			||$('#RtnItemId').val().length>=1 )
        			{
        			   result =true;
        			}else
        			{
        				result= false;
        			}
    	}
    	if(!result)
    	{ 
    		$("lab[name='Info_Text']").css("color", "red");
    		
    	}
    	return result;
    }
    
    
    $('#invSearch')
            .click(
                    function(e)
                    {
                      //disable search button and animate
                      $('#invSearch').prop('disabled', true);
                      $('#invSearch i').addClass('fa-spinner fa-spin');

                      //Init data
                      var ajaxData;
                      var ajaxUrl = '';

                      // Get values for invoice search
                      var orderID = $('#orderID').val();
                      var invoiceID = $('#invoiceID').val();
                      var customerInfo = $('#customerInfo').val();
                      var RtnItemId = $('#RtnItemId').val();
                     var invTyp= $('input[name=Invoice_Search]:checked').val();
					 
                      // Check selected Invoice Status @ krishna
                      var invoiceStatus=$('input[name="Invoice_Search"]:checked').attr('id');
                      //Check active tab, which determines invoice search-by
                      var activeTab = $('.nav-tabs-custom div.tab-pane.active').attr('id');
                     
                      $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', true);
						
                     console.log("\nIs the valid data is present in tab "+activeTab+" : "+isValidDataInTab(activeTab));
                     console.log("\n radio inline : "+ $('.radio inline').val())
                      // Validate for required fields
                      if (activeTab === 'tab_1') 
                      {
                        if (!isValidDataInTab(activeTab)) 
                        {
                          $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                          $('#orderID').focus();
                          $('#invSearch').prop('disabled', false);
                          $('#invSearch i').removeClass('fa-spinner fa-spin');
                          return;
                        } else 
                        {
                          ajaxData = {orderID:orderID,
                        		      order_date_from: $('#order_date_from').val(),
                        		      order_date_to: $('#order_date_to').val(),
                        		      OrderTotalFrom: $('#OrderTotalFrom').val(),
                        		      OrderTotalTo: $('#OrderTotalTo').val(),
                        		      invoiceStatus:invoiceStatus,
                        		      activeTab:activeTab
                        		      };
                          ajaxUrl = 'invoiceSearchByOrder';
                        }
                      }/*  else if (activeTab === 'tab_2')
                      {
                        if (!isValidDataInTab(activeTab))
                        {
                          $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                          $('#invoiceID').focus();
                          $('#invSearch').prop('disabled', false);
                          $('#invSearch i').removeClass('fa-spinner fa-spin');
                          return;
                        } else {
                          ajaxData = {invoiceID :invoiceID,
                        		  	  datepicker3: $('#datepicker3').val(),
                    		      	  datepicker4: $('#datepicker4').val(),
                    		      	  InvoiceTotalFrom: $('#InvoiceTotalFrom').val(),
                    		      	  InvoiceTotalTo: $('#InvoiceTotalTo').val(),
                    		      	  invoiceStatus:invoiceStatus,
                    		      	  activeTab:activeTab
                          			 };
                          ajaxUrl = 'invoiceSearchByOrder';
                        } 
                      } */else if (activeTab === 'tab_3') 
                       {
                        if (!isValidDataInTab(activeTab)) {
                          $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                          $('#customerInfo').focus();
                          $('#invSearch').prop('disabled', false);
                          $('#invSearch i').removeClass('fa-spinner fa-spin');

                          return;
                        } else {
                         $('#orderList').empty();
                         $('#noOfInvoices').html(0);
                          ajaxData = 'customerInfo=' + customerInfo +'&invType='+ invTyp +'&RtnItemId='+ RtnItemId;
                          ajaxUrl = 'orderSearchbyCustomer';
                          }
                          
                        }
                      

                      $.ajax({
                                url: ajaxUrl,
                                type: 'post',
                                data: ajaxData,
                                //timeout : 15000,
                                success: function(result, status, xhr) {
                                  $('#invSearch').prop('disabled', false);
                                  $('#invSearch i').removeClass('fa-spinner fa-spin');
                                  $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                                  if (result.actionErrors.length > 0) {
                                	$("lab[name='Info_Text']").css("color", "Black");
                                    $('#orderList').empty();
                                    $('#noOfInvoices').html(0);
                                    window.setTimeout(function() {
                                    $('#noOrdFnd').modal('show');
                                    }, 50);
                                    return;
                                  }
                                  if (result.orders != null) {
                                	  $('#markDelivered').prop('disabled', false);
                                	  $("lab[name='Info_Text']").css("color", "Black");
                                    $('#orderList').empty();
                                    $('#noOfInvoices').html(result.orders.length);
                                    $
                                            .each(
                                                    result.orders,
                                                    function(key, value) {
                                                      var invoice = '<tr> <td>SNO</td> <td><a href=# id="invoice_'+key+'">CLAIMID</a></td> <td>CLAIMDATE</td> <td>CLAIMTOTAL</td> <td>CUSTOMERID</td> <td>CUSTOMERNAME</td> <td>REASONCODE</td></tr>';
                                                      invoice = invoice.replace('SNO', (key + 1) + '.');
                                                      invoice = invoice.replace('CLAIMID', value.arInvNum);
                                                      invoice = invoice.replace('CLAIMDATE', moment(value.arInvDate).format('MM/DD/YY'));
                                                      invoice = invoice.replace('CLAIMTOTAL', value.invAmount);
                                                      invoice = invoice.replace('CUSTOMERID', moment().diff(value.arInvDate, 'days'));
                                                      invoice = invoice.replace('CUSTOMERNAME', result.customerName[key]);
                                                      invoice = invoice.replace('REASONCODE', value.orderNum);

                                                      $('#orderList').append(invoice);
                                                    });

                                  }
                                },
                                error: function(jqXHR, exception) {
                                  $('#invSearch').prop('disabled', false);
                                  $('#invSearch i').removeClass('fa-spinner fa-spin');
                                  $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                                 
                                }
                              });
                    });
    
  $("body").on("click", "[id*='invoice_']", function(e) {
      //e.preventDefaults();
      var invoiceID = $(this).html();
    		  var myWindow =  window.open("readyToAcceptClaimDetails?claimId=" + invoiceID, "_parent");
    });
  </script>
	<%-- <script type="text/javascript">
    $('#order').addClass('active');
    $('#returnedorders').addClass('active');
  </script>  --%>
  
  <%-- <script type="text/javascript">  	
	function markAsDelivered() {
   var ordersLst = document.getElementsByName("
											checkedOrders");
   var count=0; for (var i=0; i
											< ordersLst.length; i++) { if (ordersLst[i].checked) {
											count=count+1; } } var ordersMarked = new Array(count); for
											(var i = 0; i < ordersLst.length; i++) { if
											(ordersLst[i].checked) { ordersMarked[i] =
											ordersLst[i].value; } } $.ajax({ type: "POST",
											data:'ordersMarked='+ordersMarked, url: "MarkOrderDelivered"
											}); }
											</script> --%>


	<script type="text/javascript">
		$.fn.pressEnter = function(fn) {

			return this.each(function() {
				$(this).bind('enterPress', fn);
				$(this).keyup(function(e) {
					if (e.keyCode == 13) {
						$(this).trigger("enterPress");
					}
				})
			});
		};

		$('#orderID').pressEnter(function() {
			$('#invSearch').trigger("click");
		});
		$('#customerInfo').pressEnter(function() {
			$('#invSearch').trigger("click");
		});
	</script>
	<script type="text/javascript">
 	 document.onkeydown = function (evt) {
	  var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
	  if (keyCode == 13) {
		  document.getElementById('returnOrderSearch').click();
	  }
	 else {
	    return true;
	  }
	};
  </script>
<script type="text/javascript">
	$(function(){ 
		$("#customerInfo").autocomplete({
			autoFocus: true,
			minLength: 2,
			delay: 500,
			source: function(request, response) {
				$.ajax({
				  url: "customerLookupAjax",
				  timeout: 10000,
				  dataType: "json",
				  data: {
				    custInfo: request.term,
				    wildcardSearch: true,
				    maxCustomers: 10
				  },
				  success: function(data) {
				  	response(data);
				  },
				  error: function(jqXHR, textStatus, errorThrown){
				  	if(isPageBeingRefreshed) return;
				  	response();
				  	alert(textStatus);                  
	              }
				});
			},
			response: function( event, ui ) {
				$(this).removeClass("ui-autocomplete-loading");
			},
			select: function(event, ui) {
				$(this).val(ui.item.customerHeader.customerHeaderPK.custId);
				//$("#returnOrderSearch").trigger('click');// .get(0).submit();//
				return false;
			}
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append( "<div>" + item.customerHeader.ctNm + " - " + item.customerHeader.customerHeaderPK.custId + "</div>" )
	        .appendTo( ul );
	    };
    });
	$('#order').addClass('active');
    $('#returnedorders').addClass('active');
</script>
<script>
 $(function(){
	  var searchRange = "${range}";
	  if(searchRange == "CURRENT_WEEK"){
		    $('.week').trigger('click');
		    }else if(searchRange == "CURRENT_MONTH"){
		    $('.month').trigger('click');
		    }else if(searchRange == "CURRENT_QUARTER"){
		    $('.quarter').trigger('click');
		    }else if(searchRange == "CURRENT_YEAR"){
		    $('.year').trigger('click');
		    }else if(searchRange == "NONE"){
		    $('.none').trigger('click');
		    } 
  }); 
 
 function isNumberKey(evt)
 {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48  || charCode > 57))
       return false;
    return true;
 }
  </script>
</body>
</html>
