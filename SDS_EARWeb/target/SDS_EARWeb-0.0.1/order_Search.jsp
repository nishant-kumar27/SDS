<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>SDS | Order Search</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" type="text/css" >
	<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css" type="text/css" >
	<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css" type="text/css" >
	<link rel="stylesheet" href="assets/css/ionicons.min.css" type="text/css" >
    <link rel="stylesheet" href="assets/css/jquery-ui.css">
    <link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" >
    <link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
    <!-- <link rel="stylesheet" href="assets/plugins/daterangepicker/daterangepicker-bs3.css">
    <link rel="stylesheet" href="assets/css/popup.css"> -->
    <link rel="stylesheet" href="assets/css/popup.css">
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
<script>
	$("form[role='search']").hide();
    $(document).ready(function() {
    
 	$("#InvItemId").autocomplete({
			source : function(request, response) {

			//$('.ui-autocomplete-loading').removeClass("ui-autocomplete-loading");

			if (request.term.length > 2) {
				/*  document.getElementById("loadText").style.display ="none"; */
				//document.getElementById("load").style.display = "block";  // show the loading image.
				//$('.ui-autocomplete-loading').addClass("ui-autocomplete-loading");
				$("#InvItemId").addClass("ui-autocomplete-loading");

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
						$("#InvItemId").removeClass("ui-autocomplete-loading");
					}

				});
			} else {
				/* document.getElementById("load").style.display = "none"; */
				$("#InvItemId").removeClass("ui-autocomplete-loading");
			}
		}
		})
		$("#InvItemId").autocomplete("widget").attr('style',
		'max-height: 300px; overflow-y: auto; overflow-x: hidden;')//to get a scroll bar

	});
</script>

   
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
       
       <div class="row" style="margin:5px;margin-top:-15px;"> 
      <!-- Your Page Content Here -->
        <label style="font-size:25px;color: #226e71;"><s:property value="getText('order.search')"/></label> <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><s:property value="getText('orders.search.help_text')"/></b></small>
        <br>
      <div class="nav-tabs-custom col-md-8" style="min-height:100px;">
            <ul class="nav nav-pills">
              <li class="active"><a data-toggle="tab" href="#tab_1" aria-expanded="true"><s:property value="getText('order.label')"/></a></li>
              <li class=""><a data-toggle="tab" href="#tab_2" aria-expanded="false"><s:property value="getText('invoice.label')"/></a></li>
              <li class=""><a data-toggle="tab" href="#tab_3" aria-expanded="false"><s:property value="getText('customer.label')"/></a></li>
            </ul>
            
            <div class="tab-content" style="height:120px;">
              <div id="tab_1" class="tab-pane active">
              <form action="order_search_with_order" name="form1" method="post" onsubmit="return OrderSearchWithOrderValidate()">
              	  <input type="hidden" name="activeTab" value="#tab_1">
                  <table style="width:100%;">
                  <tr>
                  <td style="width:30%;">
                  <s:property value="getText('order.id')"/>:</td>
                      <td style="width:30%;">
                  <input name="order_id" id="order_id" style="margin:1px;  " type="text" class="form-control">
                  </td>
                  </tr>
                  <tr>
                  <td style="width:20%;">
                  <s:property value="getText('order.date.range')"/>:</td>
                      <td style="width:40%;">
                  		<table><tr><td><input name="order_date_from" style="margin:1px;width:95%;" type="text" placeholder="From" class="form-control" id="datepicker1" autocomplete="off"></td>
                            <td><input name="order_date_to" style="margin:1px;width:95%;" type="text" placeholder="To" class="form-control" id="datepicker2" autocomplete="off"></td></tr></table>
                  		</td>
                      <td><button type="submit" class="btn pull-right"><s:property value="getText('search.button')"/></button></td>
                  </tr>
                <tr>
                    <td style="width:10%;">
                    <s:property value="getText('order.totals')"/>:
                    </td>
                    <td style="width:30%;">
                        <table><tr><td><input name="order_total_from" style="margin:1px;width:95%;" type="text" placeholder="From" class="form-control" onkeypress='return isNumberKey(event);'></td>
                            <td><input name="order_total_to" style="margin:1px;width:95%;" type="text" placeholder="To" class="form-control"  onkeypress='return isNumberKey(event);'></td></tr></table>
                        
                        
                    </td>
                  </tr>
                  </table>   
                  </form>   
              </div>
                
              <!-- /.tab-pane -->
              <div id="tab_2" class="tab-pane">
              <form action="order_search_with_invoice" method="post" name="form2" onsubmit="return OrderSearchWithInvoiceValidate()">
              	<input type="hidden" name="activeTab" value="#tab_2">
               <table style="width:100%;">
                  <tr>
                  <td style="width:30%;">
                  <s:property value="getText('invoice.number.label')"/>:</td>
                      <td style="width:30%;">
                  <input name="invoice_id" style="margin:1px;" type="text" class="form-control">
                  </td>
                  </tr>
                    <tr>
                  <td style="width:20%;">
                   <s:property value="getText('invoice.date.range')"/>:</td>
                      <td style="width:40%;">
                  <table><tr><td><input name="invoice_date_from" style="margin:1px;width:95%;" type="text" placeholder="From" class="form-control" id="datepicker3" autocomplete="off"></td>
                            <td><input name="invoice_date_to" style="margin:1px;width:95%;" type="text" placeholder="To" class="form-control" id="datepicker4" autocomplete="off"></td></tr></table>
                  </td>
                      <td><button type="submit" class="btn pull-right" ><s:property value="getText('search.button')"/></button></td>
                  </tr>
                <tr>
                    <td style="width:10%;">
                  <s:property value="getText('invoice.totals')"/>:
                    </td>
                    <td style="width:30%;">
                        <table><tr><td><input name="invoice_total_from" style="margin:1px;width:95%;" type="text" placeholder="From" class="form-control"  onkeypress='return isNumberKey(event)' ></td>
                            <td><input name="invoice_total_to" style="margin:1px;width:95%;" type="text" placeholder="To" class="form-control"  onkeypress='return isNumberKey(event)'></td></tr></table>
                    </td>
                  </tr>
                  </table>                            
                </form>
              </div>
              <!-- /.tab-pane -->
              <div id="tab_3" class="tab-pane">
              <form action="order_search_with_customer" method="post" name="form3" onsubmit="return OrderSearchWithCustomerValidate()">
              	<input type="hidden" name="activeTab" value="#tab_3">
              <table style="width:100%;">
                  <tr>
                  <td style="width:30%;">
                  	 <s:property value="getText('cust.id.label')"/>:</td>
                      	<td style="width:30%;">
                  			<input name="customer_data" id="customer_data" style="margin:1px;  " type="text" class="form-control">
                  		</td>
                  </tr>
                  <tr>
                  	<td style="width:30%;">
                  	 <s:property value="getText('item.id.label')"/>:</td>
                      <td style="width:30%;">
                  			<input id="InvItemId" name="item_data" style="margin:1px;  " type="text" class="form-control">
                  	</td><td><button type="submit" class="btn pull-right" id="orderSearch"><s:property value="getText('search.button')"/><i class="fa"></i></button>
                  	<s:hidden name="wildcardSearch" value="true"></s:hidden></td>
                  </tr>
                
              </table>    
              </form>  
              </div>
              <!-- /.tab-pane -->
                 <!-- /.tab-pane -->
              
              <!-- /.tab-pane -->
          </div>
          
              <label name="Info_Text" style="font-style:italic;">&nbsp;&nbsp;&nbsp;<s:property value="getText('message_2')"/></label>
              
               
            </div>
            <!-- /.tab-content -->
       
                 </div>
		<div class="row">
			<div class="col-md-12">
		<s:set var="results" value="%{orders.size}"/>
			
 			 <s:if test="%{#results==null}">
 			 <!-- this is intial case when the page is called first --> 
 			 
 			 <!-- no action required if test="%{#results1>0}" --> 
 			 </s:if>
 			<s:elseif test="%{#results!=null}">
 			 <!-- More than 0 records found -->
 			 <label style="color:green;"><span id="noOfOrd"><s:property value="results"/></span> <s:property value="getText('orders.found')"/></label>
 			 </s:elseif>
 			 <s:elseif test="!hasActionErrors()">
 					<label style="color:rgb(0,166,90);"><span id="noOfOrd"><s:property value="results"/></span> <s:property value="getText('orders.found')"/></label>
 			</s:elseif>
 			 
        
        <s:set var="results1" value="%{rejectClaim_List.length}"/>
		<!-- More than 0 records found -->
        <s:if test="%{#results1==0}">
 			  <label style="color:red;"><span id="noOfOrd"><s:property value="results1"/></span> <s:property value="getText('orders.found')"/></label>
 		</s:if>
 		
            <!-- /.box-body -->
        <div class="box box-primary">
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;"><s:property value="getText('all_orders')"/></h3> &nbsp;<i><h3 class="box-title" style="color: #226e71;" id="Current_Date_Range"></h3></i>
               <div class="dropdown pull-right" style="margin-right:10px;">
                        <i class="fa  fa-bars fa-lg dropdown-toggle"  type="button" data-toggle="dropdown"></i>
                        <ul style="margin-left:-90px;background-color:  #656a6b;color:white;" class="dropdown-menu ">
                        <li><a href="javascript:void(0)" class="week" id="week" onclick='rangeFilter("orderDate","orderList",this.id);'><s:property value="getText('week_to_date')"/></a></li>
                        <li><a href="javascript:void(0)" class="month" id="month" onclick='rangeFilter("orderDate","orderList",this.id);'><s:property value="getText('month_to_date')"/></a></li>
                        <li><a href="javascript:void(0)"  class="quarter" id="quarter" onclick='rangeFilter("orderDate","orderList",this.id);'><s:property value="getText('quarter_to_date')"/></a></li>
                        <li><a href="javascript:void(0)"  class="year" id="year" onclick='rangeFilter("orderDate","orderList",this.id);'><s:property value="getText('year_to_date')"/></a></li>
                        <li><a href="javascript:void(0)" class="none" id="none" onclick='rangeFilter("orderDate","orderList",this.id);'><s:property value="getText('none')"/></a></li>
                        </ul>  
                </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding table-responsive">
              <table id="order_table" class="table table-striped ">
              	<thead>
              		<tr style="background-color:#ADC2EE;">
                    <th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
                    <th><s:property value="getText('order.id')"/> </th>
                    <th style="text-align:center;" id="orderDate"><s:property value="getText('order.date')"/></th>
                    <th style="text-align:right;">
                    	<s:property value="getText('order.total')"/>
                    	<small>(<s:property value="getText('global.currency')" />)</small>
                    </th>
                    <th><s:property value="getText('customer.name')"/></th>
                    <th style="text-align:right;"><s:property value="getText('inv.no')"/></th>
                    <th style="text-align:right;"><s:property value="getText('LPO.label')"/></th>
                    <th style="text-align:right;"><s:property value="getText('no.of.items')"/></th>
                    <th><s:property value="getText('status.label')"/></th>
                   
                    <th><s:property value="getText('sales.agent')"/></th>
                    <th><s:property value="getText('delivery.date')"/></th>
                    
                </tr>
              	</thead>
                <tbody id="orderList">
                <s:iterator  value="orders" status="itStatus">
				<tr>
				<td><s:property value="#itStatus.count" />.</td>
				<td id="order_id"><a href="javascript:void(0)"><s:property value="id.orderId"/></a>  </td>
				<td style="text-align:center;"><s:date name="id.orderDate" format="%{getText('format.date')}"/>   </td>
				<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{orderTotal})}"/> </td>
				<td><s:property value="customerName"/>  </td>
				<td style="text-align:right;"><s:property value="invoiceId"/></td>
				<td style="text-align:right;"><s:property value="lpoNumber"/>  </td>
				<td style="text-align:right;"><s:property value="itemCount"/></td>
				<td><s:property value="status"/>  </td>
				
				<td><s:property value="salesAgent"/>  </td>
				<td style="text-align:center;"><s:date name="deliveryDate" format="%{getText('format.date')}"/></td>
				</tr> 
				</s:iterator>  
                
               
              </tbody></table>
           </div>
           
          </div>
        </div>
    </section>
      
      
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  


  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar 
 <div style="position: fixed; height: auto;" class="control-sidebar-bg"></div>-->
</div>
<!-- ./wrapper -->
<!-- Hidden form data -->
<form action="orders_details_page" name="hidden_order_data" method="post">
    <input type="hidden" name="empId"/>
    <input type="hidden" name="orderId"/>
</form>
<!-- REQUIRED JS SCRIPTS -->

  

<script>
  $(function () {
	  
	  $("#order_table #order_id a").click(
		function(evt){
			console.log(evt.currentTarget["innerText"]);
			document.forms["hidden_order_data"].empId.value="<s:property value="#session['userId']"/>";
            document.forms["hidden_order_data"].orderId.value=evt.currentTarget["innerText"];
            document.forms["hidden_order_data"].submit();
			
		}
	  );
	  
	  
    //Initialize Select2 Elements
    //$(".select2").select2();

    //Datemask dd/mm/yyyy
    //$("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
    //Datemask2 mm/dd/yyyy
    //$("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
    //Money Euro
   // $("[data-mask]").inputmask();

 
  	//var datepicker = $.fn.datepicker.noConflict(); // return $.fn.datepicker to previously assigned value
	//$.fn.datepickerBS = datepicker;                 // give $().bootstrapDP the bootstrap-datepicker functionality
	
    //Date picker
       var dateFormat= "<s:text name="bootstrap.date.format"/>";
	   var datePickerProp = {todayBtn: "linked", autoclose: true, language: "en", endDate: new Date(),
	   			todayHighlight: true, format:dateFormat};
 	
      $('#datepicker1').datepickerBS(datePickerProp);
      $('#datepicker2').datepickerBS(datePickerProp);
      $('#datepicker3').datepickerBS(datePickerProp);
      $('#datepicker4').datepickerBS(datePickerProp);
 
      $("#range_orders li").click(
    	function(evt){
    		 if(evt.target.id=="week"){
    			 console.log("rage click")
    		 }
    		 else if(evt.target.id=="month"){
    			 
    		 }
    		 else if(evt.target.id=="quarter"){
    			 
    		 }
    		 else if(evt.target.id=="year"){
    			 
    		 }
    	}	  
      );
    
  });
  $('#order').addClass('active');
  $('#ordersearch').addClass('active');
</script>

<script type="text/javascript">
	var activeTab = '<s:property value="activeTab" />';
	if (activeTab) {
	   $('ul li a[href="' + activeTab + '"]').tab('show');
	}

</script>

<script type="text/javascript">
		$("#customer_data").autocomplete({
			autoFocus: true,
			minLength: 2,
			delay: 500,
			source: function(request, response) {
				$("#custError").addClass('hidden');
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
	              	/* if(jqXHR.status!=null && jqXHR.status==403)
	                {
	                	location.reload(true);
	                	alert('Session has timed out. Please re-login.');
	                }   */                
	              }
				});
			},
			response: function( event, ui ) {
				$(this).removeClass("ui-autocomplete-loading");
			},
			select: function(event, ui) {
				$(this).val(ui.item.customerHeader.customerHeaderPK.custId);
				//$("#orderSearch").trigger('click');//.get(0).submit();//
				return false;
			}
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append( "<div>" + item.customerHeader.ctNm + " - " + item.customerHeader.customerHeaderPK.custId + "</div>" )
	        .appendTo( ul );
	    };
	    
	</script>
	
	<%-- <script type="text/javascript">
	    // Highlight Menubar
	    $("#customer").addClass('active');
	    $("#customersearch").addClass('active');
	    // Hide topbar customer search form
	    $("#topbarCustomerLookup").addClass('hidden');
	    // Focus to customer info textbox
	    $("#customerInfo").focus();
	</script> --%>

<script>  
function OrderSearchWithOrderValidate()
{
	var result0=document.form1.order_id.value;
	var result1=document.form1.order_date_from.value;
	var result2=document.form1.order_date_to.value;
	var result3=document.form1.order_total_from.value;
	var result4=document.form1.order_total_to.value;
  
    	   if((result0==null || result0=="")&&(result1==null || result1=="")&&(result2==null || result2=="")&&(result3==null || result3=="")&&(result4==null || result4==""))
    	   {
    		  $("label[name='Info_Text']").css("color", "red");
    	   			return false;  
    	   }
}  
</script>  

<script>  

function OrderSearchWithInvoiceValidate()
{
	var result0=document.form2.invoice_id.value;
	var result1=document.form2.invoice_date_from.value;
	var result2=document.form2.invoice_date_to.value;
	var result3=document.form2.invoice_total_from.value;
	var result4=document.form2.invoice_total_to.value;
  
    	   if((result0==null || result0=="")&&(result1==null || result1=="")&&(result2==null || result2=="")&&(result3==null || result3=="")&&(result4==null || result4==""))
    	   {
    		  $("label[name='Info_Text']").css("color", "red");
    	   			return false;  
    	   }
}  
</script>  

<script>  
function OrderSearchWithCustomerValidate()
{
	var result0=document.form3.customer_data.value;
	var result1=document.form3.item_data.value;
  
    	   if((result0==null || result0=="")&&(result1==null || result1==""))
    	   {
    		  $("label[name='Info_Text']").css("color", "red");
    	   			return false;  
    	   }
}  
</script>  
<script>
 $(function(){
	  var searchRange = "${searchRange}";
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
</body></html>