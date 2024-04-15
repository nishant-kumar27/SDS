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
<title>SDS | Payment Search</title>
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
	<link rel="stylesheet" href="assets/css/jquery-ui.css">

<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="assets/plugins/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="assets/css/popup.css">
<link rel="stylesheet" href="assets/css/Customizations.css">
<link rel="stylesheet" href="assets/plugins/datatables/dataTables.bootstrap.css">

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
<script src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="assets/plugins/jQueryUI/jquery-ui.js"></script>
<!-- date-range-picker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->


    


    
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
       
        <div class="row" style="margin:5px;margin-top:-15px;"> 
      <!-- Your Page Content Here -->
        <label style="font-size:25px;color: #226e71;"><s:property value="getText('payment.label')"/></label>
            <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><s:property value="getText('payment.search.help_text')"/></b></small>
        <br>
       
      <div class="nav-tabs-custom " style="min-height:100px;min-width:700px;max-width:750px;">
            <ul style="margin-left:15px;" class="nav nav-pills">
            <!--   <li class=""><a data-toggle="tab" href="#tab_1" aria-expanded="false">Order</a></li> -->
              <li class="active"><a data-toggle="tab" href="#tab_2" aria-expanded="true"><s:property value="getText('invoice.label')"/></a></li>
              <li class=""><a data-toggle="tab" href="#tab_3" aria-expanded="false"><s:property value="getText('customer.label')"/></a></li>
            </ul>
            
 <table class="table"  style="width:650px;;margin-left:15px; border-width: 0px;"><tr><td style="width:80%;border-top: 1px solid #ffffff;"> <div style="height:100px;" class="tab-content">
              <%-- <div id="tab_1" class="tab-pane active">
                  <table style="width:100%;">
                  <tr>
                  <td style="width:10%;">
                  <span >Order ID: </span></td>
                      <td style="width:30%;">
                  <input id="orderID" name="orderID" style="margin:1px; width:52%; " type="text" class="form-control">
                  </td>
                  </tr>
                    <tr>
                  <td style="width:10%;">
                  <span >Order Date Range: </span></td>
                      <td style="width:30%;">
                  
                  <table><tr><td><input style="margin:1px;width:95%;" type="text" placeholder="From" class="form-control" id="datepicker1"></td>
                            <td><input style="margin:1px;width:95%;" type="text" placeholder="To" class="form-control" id="datepicker2"></td></tr></table>
                        
                  </td>
                  </tr>
                <tr>
                    <td style="width:10%;">
                    <span >Order Totals: </span>
                    </td>
                    <td style="width:30%;">
                        <table><tr><td><input id="OrderTotalFrom" style="margin:1px;width:95%;" type="text" placeholder="From" class="form-control" ></td>
                            <td><input id="OrderTotalTo" style="margin:1px;width:95%;" type="text" placeholder="To" class="form-control"></td></tr></table>
                        
                        
                    </td>
                  </tr>
                  </table>      
              </div> --%>
                
              <!-- /.tab-pane -->
              <div id="tab_2" class="tab-pane active">
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
                
              </div>
              <!-- /.tab-pane -->
              <div id="tab_3" class="tab-pane">
              <table style="width:100%;">
                  <tr>
                  <td style="width:10%;">
                  <s:property value="getText('cust.id.label')"/>:</td>
                      <td style="width:30%;">
                  <input id="customerInfo" style="margin:1px; width:52%; " type="text" class="form-control">
                  </td>
                  </tr>
                    <tr>
                  <td style="width:10%;">
                <s:property value="getText('item.id.label')"/>:</td>
                      <td style="width:30%;">
                  <input id="InvItemId" style="margin:1px; width:52%; " type="text" class="form-control">
                  </td>
                  </tr>   </table>      
              </div>
              <!-- /.tab-pane -->
                 <!-- /.tab-pane -->

              <!-- /.tab-pane -->
          </div>
          </td><td style="border-top: 1px solid #ffffff;"><form class="radio inline ">
                <div class="form-group" style="display: none;">  
                <label class="radio inline"><input id="99" type="radio" name="Invoice_Search" value="All" >
                <span><s:property value="getText('all_inv')"/></span></label><br>
                <label class="radio inline"><input id="1" type="radio" name="Invoice_Search" value="Open Invoices" checked>
                <span><s:property value="getText('open_inv')"/></span></label><br>  
                <label class="radio inline"><input id="0" type="radio" name="Invoice_Search" value="Closed Invoices">
                <span><s:property value="getText('closed_inv')"/></span></label><br> 
                <label class="radio inline"><input id="OverDue" type="radio" name="Invoice_Search" value="Over Due">
                <span><s:property value="getText('overdue_inv')"/></span></label></div> 
                   <!--  <button class="btn pull-right" id="invSearch"
									style="margin-left: 0px; color: #ffffff; width: 100px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; border: 1px solid; display: inline-block;"
									type="button">
									Search&nbsp;<i class="fa"></i>
								</button> -->
								<button  onclick="searchInvoice()"class="btn pull-right" id="invSearch"
									style="margin-left: 0px; color: #ffffff; width: 100px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; border: 1px solid; display: inline-block;"
									type="button">
									<s:property value="getText('search.button')"/>&nbsp;<i class="fa"></i>
								</button>
                   
                    </form></td></tr></table>
           
                <lab name="Info_Text" style="font-style:italic;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="getText('message_2')"/></lab>
                
            </div>
            <!-- /.tab-content -->
              
              
            
        
            
                 </div>     
		                 <s:set var="results" value="%{invoices.size}"/>	             	
				<s:set var="noOfClaims" value="#results!= null ? #results : 0"/>
           			<s:if test="!hasActionErrors()">
 				 <label style="color:rgb(0,166,90);"><span id="noOfInv"><s:property value="noOfClaims"/></span> <s:property value="getText('records.found')"/></label>
 			 </s:if>
 			 <s:else>
 			 	 <label style="color:red;"><span id="noOfInv"><s:property value="noOfClaims"/></span> <s:property value="getText('records.found')"/></label>
 			 </s:else>   
 			 <span id="enbleRcrd" hidden><s:property value="EnblRcrdPaymnt"/></span>
		                <s:set var="results" value="%{enblRcrdPaymn}"/> 
            <!-- /.box-body -->
        <div class="box box-primary">
            
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;"><s:property value="getText('pending.payments')"/></h3>
                <div class="box-tools">
               <div class="dropdown pull-right" style="margin-right:10px;">
                        
                       
                        <i class="fa  fa-bars fa-lg dropdown-toggle"  type="button" data-toggle="dropdown"></i>
                        <ul style="margin-left:-90px;background-color:  #656a6b;color:white;" class="dropdown-menu ">
                        <li><a href="#"><s:property value="getText('week_to_date')"/></a></li>
                        <li><a href="#"><s:property value="getText('month_to_date')"/></a></li>
                        <li><a href="#"><s:property value="getText('year_to_date')"/></a></li>
                        </ul>
                            
                </div>
                </div>
            </div>
            
            <!-- /.box-header -->
            <div class="box-body no-padding table-responsive">
                
              	<table class="table table-striped table-condensed" id="invoiceTable">

							<%-- <style>
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	border-top: 1px solid #d8d8d8;
}

.table-striped>tbody>tr:nth-of-type(2n+1) {
	background-color: #F2F2F2;
}
</style> --%>
								<thead>
								<tr style="background-color: #ADC2EE;">
									<th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
									<th><s:property value="getText('inv.no')"/></th>
									<th><s:property value="getText('invoice.date')"/></th>
									<th style="text-align:right;"><s:property value="getText('invoice.total')"/></th>
									<th><s:property value="getText('customer.name')"/></th>
									<th style="text-align:right;"><s:property value="getText('customer.id')"/></th>
									<th><s:property value="getText('status.label')"/></th>
									<th><s:property value="getText('age.days')"/></th>									
									<th><s:property value="getText('order.id')"/></th>
									<th style="text-align:right;"><s:property value="getText('received.amt')"/></th>
									<th style="text-align:right;"><s:property value="getText('balance.due')"/></th>

								</tr>
							</thead>
							<tbody id="invoiceList">
				
 <s:iterator value="invoices"  status="itStatus">
            <tr>
            <s:set var="Index" value="%{#itStatus.index}"></s:set>
      
       <td>
      <s:property value="%{#Index+1}"/>.
      </td>
      <td id="InvoiceId">
       <a href="InvoiceDetail?invoiceID=<s:property value="arInvNum"/>"><s:property value="arInvNum"/></a>
      </td>
      <td>
             <s:date name="arInvDate" format="%{getText('format.date')}" />
      </td>
       <td style="text-align:right;">
             <s:property value="invAmount"/>
      </td>    
       <td>
             <s:property value="customerName[#Index]"/>
      </td>
      
       <td style="text-align:right;" id="customerId">
             <s:property value="customerSite.customerSitePK.custId"/>
      </td>
      
      
      <s:if test="invStatus=='1'">
       <td>
      <s:property value="getText('open_inv')"/>             
      </td>
      </s:if>
      <s:elseif test="invStatus=='0'">
      <td>      
       <s:property value="getText('closed_inv')"/>
      </td>
      </s:elseif>
      <s:else><td><s:property value="getText('unknown_inv')"/></td></s:else>
      
      
         
             <!-- if less than 24 hours the invoice generated then put '0' days in Age--> 
      <td><s:property value="%{((CurrentDate.getTime() - arInvDate.getTime()) / (1000 * 60 * 60 * 24) )}"/> <s:property value="getText('days.label')"/></td>

       <td>
             <a href="invoice_orders_details_page?orderId=<s:property value="orderNum"/>"><s:property value="orderNum"/>
      </td>
       <td style="text-align:right;">
             <s:property value="%{invAmount-invPendAmount}"/>
      </td>
       <td style="text-align:right;">
             <s:property value="invPendAmount"/>
      </td>
            
      </tr>
      </s:iterator>
		</tbody>
		</table>
		
		    <div id="paymentInfo" class="col-md-8 pull-right hidden" >
            
                <table style="width:100%;">
                <tr>
                <td style="text-align:right;" ><s:property value="getText('total.pending.amt')"/>:</td>&nbsp;
                <td style="text-align:right;margin:1px;width:30%;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<b><s:property value="AmtToPay"/>
                </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>

                <tr>
                <td style="text-align:right;"><s:property value="getText('amt.being.paid')"/>: </td>
                <td><b><input style="text-align:right;margin:10px;width:70%;" id="amtBeingPaid" type="text"  value="0.00" class="form-control"></b></td>
                

                </tr>
                  
                </table>    
                 </div> 
						

            </div>
            <!-- /.box-body -->
          </div>
        
        
        
        
<%--         
     		arjun	<s:if test="hasActionErrors()">
 			  <div class="errors">
     		 <s:actionerror/>
   </div>
</s:if>    --%>
        
        
        
    </section>
        <div class="row " >
        	<div class="col-md-4 pull-right ">
            	
                	<button onclick="myFunction()"  id="recordPaymentButton" class="btn btn-block  pull-right hidden " style="width:170px;margin:1px;                          
                    type="button"><s:property value="getText('record.payment.button')"/><i class="fa"></i></button>
            	
        	</div>
        </div> 
        <form action="recordPayment" name="hidden_payment_data" method="post">
        	<input type="hidden" name="pendingAmount">
        	<input type="hidden" name="amtBeingPaid">
        	<input type="hidden" name="customerId">
        	<input type="hidden" name="noOfInvoices">
        </form>
      
    <!-- /.content -->
  </div>

	</div>
	<!-- ./wrapper -->
	<!-- when no amtbeingpaid found @mallikarjun -->
	<div class="modal fade bs-example-modal-sm" id="zeroNotAllowed"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"><s:property value="getText('message_10')"/></h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal"><s:property value="getText('ok.button')"/>&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>
		
		
	<script>
	$("form[role='search']").hide();
    $(function() {
    	

		/* AJAX item Search */
	$("input#InvItemId").autocomplete({
	/*   Enabling the Qty textbox after selecting any item by firing autocomplete select event */
		source : function(request, response) {

			//$('.ui-autocomplete-loading').removeClass("ui-autocomplete-loading");

			if (request.term.length > 2) {
				/*  document.getElementById("loadText").style.display ="none"; */
				//document.getElementById("load").style.display = "block";  // show the loading image.
				//$('.ui-autocomplete-loading').addClass("ui-autocomplete-loading");
				$("#InvItemId").addClass("wait");

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
						$("#InvItemId").removeClass("wait");
					}

				});
			} else {
				/* document.getElementById("load").style.display = "none"; */
				$("#InvItemId").removeClass("wait");
			}
		}
	});
	
	$("#InvItemId").autocomplete("widget").attr('style',
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
      var datePickerProp = {todayBtn: "linked", autoclose: true, todayHighlight: true,format : dateFormat};
      $('#datepicker1').datepicker(datePickerProp);
      $('#datepicker2').datepicker(datePickerProp);
      $('#datepicker3').datepicker(datePickerProp);
      $('#datepicker4').datepicker(datePickerProp);
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
  </script>
  
  
    <script>
    function searchInvoice(){
    	
    	 var activeTab = $('.nav-tabs-custom div.tab-pane.active').attr('id');
    	 $("#paymentInfo").addClass("hidden");
		 $("#recordPaymentButton").addClass("hidden");

    	//validating the fields not to be empty
    	  function isValidDataInTab(activeTab)
    	    {
    	    	var result= false;
    	    	$('#invoiceList').empty();
    	    	document.getElementById("noOfInv").innerHTML="0";
    	    	
    	    	
    	    	/* if(activeTab=='tab_1')
    	    	{	
    	    		if($('#orderID').val().length >= 1
    	    			||$('#datepicker1').val().length>=1 || $('#datepicker2').val().length>=1
    	    			||$('#OrderTotalFrom').val().length>=1 || $('#OrderTotalTo').val().length>=1)
    	    			{
    	    			result =true;
    	    			}else
    	    			{
    	    				result=false;
    	    			} 		
    	    	}else  */
    	    	if(activeTab=='tab_2')
    	    	{
    	    		if($('#invoiceID').val().length >= 1
    	    				||$('#datepicker3').val().length>=1 || $('#datepicker4').val().length>=1
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
    	        			||$('#InvItemId').val().length>=1 )
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
    		
    		/* if(activeTab === "tab_1"){
    			document.forms["hidden_order_data"].orderID.value=$('#orderID').val();
    			document.forms["hidden_order_data"].datepicker1.value=$('#datepicker1').val();
    			document.forms["hidden_order_data"].datepicker2.value=$('#datepicker2').val();
    			document.forms["hidden_order_data"].OrderTotalFrom.value=$('#OrderTotalFrom').val();
    			document.forms["hidden_order_data"].OrderTotalTo.value=$('#OrderTotalTo').val();
    			
    			document.hidden_order_data.action ="invoiceSearchByOrder";
    		}else */
    			if(activeTab === "tab_2"){
    				 document.forms["hidden_order_data"].invoiceID.value=$('#invoiceID').val();
    				 document.forms["hidden_order_data"].datepicker3.value=$('#datepicker3').val();
    				 document.forms["hidden_order_data"].datepicker4.value=$('#datepicker4').val();
    				 document.forms["hidden_order_data"].InvoiceTotalFrom.value=$('#InvoiceTotalFrom').val();
    				 document.forms["hidden_order_data"].InvoiceTotalTo.value=$('#InvoiceTotalTo').val();
    				 
    				 document.hidden_order_data.action ="paymntInvoiceSearchByOrder";
    				 
    			}else
    				if(activeTab === "tab_3"){
    					document.forms["hidden_order_data"].customerInfo.value=$('#customerInfo').val();
    					document.forms["hidden_order_data"].InvItemId.value=$('#InvItemId').val();
    					
    					document.hidden_order_data.action ="paymntInvoiceSearchByCustomer";
    				}
    		                     
            //clearing the input search fields
            $("#orderID").val('');
            $("#datepicker1").val('');
            $("#datepicker2").val('');
            $("#OrderTotalFrom").val('');
            $("#OrderTotalTo").val('');
            
            $("#invoiceID").val('');
            $("#datepicker3").val('');
            $("#datepicker4").val('');
            $("#InvoiceTotalFrom").val('');
            $("#InvoiceTotalTo").val('');
            
            $("#customerInfo").val('');
            $("#InvItemId").val('');
            
            document.forms["hidden_order_data"].submit();
    	}
    }    
    </script>
    
  
   <form name="hidden_order_data" method="post">
   
   	<input type="hidden" name="invoiceStatus"/>
    <input type="hidden" name="invType"/>
    <input type="hidden" name="activeTab"/>
    
    <input type="hidden" name="orderID"/>
    <input type="hidden" name="datepicker1"/>
    <input type="hidden" name="datepicker2"/>
    <input type="hidden" name="OrderTotalFrom"/>
    <input type="hidden" name="OrderTotalTo"/>
    
    <input type="hidden" name="invoiceID"/>
    <input type="hidden" name="datepicker3"/>
    <input type="hidden" name="datepicker4"/>
    <input type="hidden" name="InvoiceTotalFrom"/>
    <input type="hidden" name="InvoiceTotalTo"/>
    
    <input type="hidden" name="customerInfo"/>
    <input type="hidden" name="InvItemId"/>
     </form>
<%-- 
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
    			||$('#claimItemId1').val().length>=1 )
    			{
    			result =true;
    			}else
    			{
    				result=false;
    			} 		
    	}else if(activeTab=='tab_2')
    	{
    		if($('#claimID').val().length >= 1
        			||$('#claimItemId2').val().length>=1 )
        			{
        			result =true;
        			}else
        			{
        				result =false;
        			}
    	}else if(activeTab=='tab_3')
    	{
    		if($('#customerInfo').val().length >= 1
        			||$('#claimItemId3').val().length>=1 )
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
    
    
    $('#claimSearch')
            .click(
                    function(e)
                    {
                      //disable search button and animate
                      $('#claimSearch').prop('disabled', true);
                      $('#claimSearch i').addClass('fa-spinner fa-spin');

                      //Init data
                      var ajaxData;
                      var ajaxUrl = '';

                      // Get values for invoice search
                      var orderID = $('#orderID').val();
                      var claimID = $('#claimID').val();
                      var customerInfo = $('#customerInfo').val();
                      var claimItemId = null;
                      if(activeTab=='tab_1' && $('#claimItemId1').val()!=null){
                    	  claimItemId = $('#claimItemId1').val();
                      }else if(activeTab=='tab_2' && $('#claimItemId2').val()!=null)
                    	  {
                    	  claimItemId = $('#claimItemId2').val();
                    	  }
                      else if(activeTab=='tab_3' && $('#claimItemId3').val()!=null)
                	  {
                	  claimItemId = $('#claimItemId3').val();
                	  }
                      
                     //var invTyp= $('input[name=Invoice_Search]:checked').val();
					 
                      // Check selected Invoice Status @ krishna
                    // var rejectClaim_Listtatus=$('input[name="Invoice_Search"]:checked').attr('id');
                      //Check active tab, which determines invoice search-by
                      var activeTab = $('.nav-tabs-custom div.tab-pane.active').attr('id');
                     
                      $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', true);
						
                     console.log("\nIs the valid data is present in tab "+activeTab+" : "+isValidDataInTab(activeTab));
                   //  console.log("\n radio inline : "+ $('.radio inline').val())
                      // Validate for required fields
                      if (activeTab === 'tab_1') 
                      {
                        if (!isValidDataInTab(activeTab)) 
                        {
                          $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                          $('#orderID').focus();
                          $('#claimSearch').prop('disabled', false);
                          $('#claimSearch i').removeClass('fa-spinner fa-spin');
                          return;
                        } else 
                        {
                          ajaxData = {orderID:orderID,
                        		      activeTab:activeTab
                        		      };
                          ajaxUrl = 'rejectClaimByOrderId';
                        }
                      } else if (activeTab === 'tab_2')
                      {
                        if (!isValidDataInTab(activeTab))
                        {
                          $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                          $('#claimID').focus();
                          $('#claimSearch').prop('disabled', false);
                          $('#claimSearch i').removeClass('fa-spinner fa-spin');
                          return;
                        } else {
                          ajaxData = {claimID :claimID,
                    		      	  activeTab:activeTab
                          			 };
                          ajaxUrl = 'rejectClaimByClaimId';
                        }
                      } else if (activeTab === 'tab_3') 
                       {
                        if (!isValidDataInTab(activeTab)) {
                          $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                          $('#customerInfo').focus();
                          $('#claimSearch').prop('disabled', false);
                          $('#claimSearch i').removeClass('fa-spinner fa-spin');

                          return;
                        } else {
                          ajaxData = 'customerInfo=' + customerInfo  +'&claimItemId='+ claimItemId;
                          ajaxUrl = 'rejectClaimByCustomerName';
                          }
                          
                        }
                      

                      $.ajax({
                                url: ajaxUrl,
                                type: 'post',
                                data: ajaxData,
                                //timeout : 15000,
                                success: function(result, status, xhr) {
                                  $('#claimSearch').prop('disabled', false);
                                  $('#claimSearch i').removeClass('fa-spinner fa-spin');
                                  $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                                  if (result.actionErrors.length > 0) {
                                	$("lab[name='Info_Text']").css("color", "Black");
                                    $('#claimList').empty();
                                    $('#noOfClaims').html(0);
                                    window.setTimeout(function() {
                                    	$('#noInvFnd').modal('show');
                                    }, 50);
                                    return;
                                  }
                                  if (result.rejectClaim_List != null) {
                                	  $("lab[name='Info_Text']").css("color", "Black");
                                    $('#claimList').empty();
                                    $('#noOfClaims').html(result.rejectClaim_List.length);
                                    $
                                            .each(
                                                    result.rejectClaim_List,
                                                    function(key, value) {
                                                      var invoice = '<tr> <td>SNO</td> <td><a href=# id="invoice_'+key+'">CLAIMID</a></td> <td>CLAIMDATE</td> <td>CLAIMTOTAL</td> <td>CUSTOMERID</td> <td>CUSTOMERNAME</td> <td>REASONCODE</td></tr>';
                                                      invoice = invoice.replace('SNO', (key + 1) + '.');
                                                      invoice = invoice.replace('CLAIMID', value.arInvNum);
                                                      invoice = invoice.replace('CLAIMDATE', moment(value.arInvDate).format('MM/DD/YY'));
                                                      invoice = invoice.replace('CLAIMTOTAL', value.invAmount);
                                                      invoice = invoice.replace('CUSTOMERID', moment().diff(value.arInvDate, 'days'));
                                                      invoice = invoice.replace('CUSTOMERNAME', result.customerName[key]);
                                                      invoice = invoice.replace('REASONCODE', value.orderNum);

                                                      $('#claimList').append(invoice);
                                                    });

                                  }
                                },
                                error: function(jqXHR, exception) {
                                  $('#claimSearch').prop('disabled', false);
                                  $('#claimSearch i').removeClass('fa-spinner fa-spin');
                                  $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                                  alert(exception);
                                }
                              });
                    });

    $("body").on("click", "[id*='invoice_']", function(e) {
      //e.preventDefaults();
      var claimID = $(this).html();
      var myWindow = window.open("rejectClaimDetail?claimID=" + claimID, "_parent");

    });
  </script> --%>
  
 
 	<script type="text/javascript">	
  	var list=document.getElementById("rejlist").innerText;
	if(list!=null && list.length<=0){
		$('#noClmFnd').modal('show');
	}
	 </script>
	 
	 	<script type="text/javascript">	 	 
	 if($("#enbleRcrd").val!=null && document.getElementById("enbleRcrd").innerHTML=='true'){
		 $("#paymentInfo").removeClass("hidden");
		 $("#recordPaymentButton").removeClass("hidden");
	 }else
	 {
		 $("#paymentInfo").addClass("hidden");
		 $("#recordPaymentButton").addClass("hidden");
	 }
	 </script>
	 
	 <script>
function myFunction() {
	if(amtBeingPaid.value>0){
		
		
		document.forms["hidden_payment_data"].amtBeingPaid.value=amtBeingPaid.value;
        document.forms["hidden_payment_data"].customerId.value=document.getElementById("customerId").innerText;

        document.forms["hidden_payment_data"].submit();
	}else{

		$('#zeroNotAllowed').modal('show');
	}
			
		
}
</script>
  <script type="text/javascript">
 	 document.onkeydown = function (evt) {
	  var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
	  if (keyCode == 13) {
		  document.getElementById('invSearch').click();
	  }
	 else {
	    return true;
	  }
	};
  </script>
  
	<script type="text/javascript">
	$(function(){
		$('#invoiceTable').DataTable({
		    searching: false,
			ordering: false,
			info: false,
			lengthChange: false,
			scrollY: "400px",
			scrollCollapse: true,
			paging: false,
			"language": {
				"emptyTable": " "
			}
		});
	});
	 $('#invoice').addClass('active');
	   $('#postPaymentSearch').addClass('active');
  </script>
  
</body>
</html>


 