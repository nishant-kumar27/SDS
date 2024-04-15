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
<title>SDS | Open Orders</title>
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
<%-- <script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script> --%>
<script src="assets/plugins/datepicker/datepicker-bootstrap.js"></script>
<script src="assets/moment.min.js"></script>
<script type="text/javascript" src="custom/dateRangeFilter.js"></script>

    
</head>
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
        <label style="font-size:25px;color: #226e71;"><s:property value="getText('openOrder.search')"/></label>
            <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><s:property value="getText('openorders.search.help_text')"/></b></small>
        <br>
       
      <div class="nav-tabs-custom " style="min-height:100px;min-width:700px;max-width:750px;">
            <ul style="margin-left:15px;" class="nav nav-pills">
              <li class="active"><a data-toggle="tab" href="#tab_1" aria-expanded="true"><s:property value="getText('order.label')"/></a></li>
              
              <li class=""><a data-toggle="tab" href="#tab_3" aria-expanded="false"><s:property value="getText('customer.label')"/></a></li>
            </ul>
            
 <table class="table"  style="width:650px; margin-left:15px; margin-bottom:0px; border-width: 0px;"><tr><td style="width:80%;border-top: 1px solid #ffffff;"> <div style="height:100px;" class="tab-content">
              <div id="tab_1" class="tab-pane active">
                  <table style="width:100%;">
                  <tr>
                  <td style="width:10%;">
                 <s:property value="getText('order.id')"/>:</td>
                      <td style="width:30%;">
                  <input id="orderID" name="orderID" style="margin:1px; width:52%; " type="text" class="form-control">
                  </td>
                  </tr>
                    <tr>
                  <td style="width:10%;">
                  <s:property value="getText('order.date.range')"/>:</td>
                      <td style="width:30%;">
                  
                  <table><tr><td><input style="margin:1px;width:95%;" type="text" placeholder="From" class="form-control" id="datepicker1"></td>
                            <td><input style="margin:1px;width:95%;" type="text" placeholder="To" class="form-control" id="datepicker2"></td></tr></table>
                        
                  </td>
                  </tr>
                <tr>
                    <td style="width:10%;">
                   <s:property value="getText('order.totals')"/>:
                    </td>
                    <td style="width:30%;">
                        <table><tr><td><input id="OrderTotalFrom" style="margin:1px;width:95%;" type="text" placeholder="From" class="form-control"   onkeypress='return isNumberKey(event);'></td>
                            <td><input id="OrderTotalTo" style="margin:1px;width:95%;" type="text" placeholder="To" class="form-control"  onkeypress='return isNumberKey(event);'></td></tr></table>
                        
                        
                    </td>
                  </tr>
                  </table>      
              </div>
                
              <div id="tab_3" class="tab-pane">
              <table style="width:100%;">
                  <tr>
                  <td style="width:10%;">
                  <s:property value="getText('cust.id.label')"/>:</td>
                      <td style="width:30%;">
                  <input id="customerInfo" name="customerInfo" style="margin:1px; width:52%; " type="text" class="form-control">
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
                <div class="form-group">  
                <label class="radio inline" style="margin-top: 0px;"><input id="open" type="radio" name="OpenOrder_Search" value="open" checked>
                <span>Open</span></label><br>
                <label class="radio inline" style="margin-top: 0px;"><input id="new" type="radio" name="OpenOrder_Search" value="new">
                <span>New</label><br>
                <label class="radio inline" style="margin-top: 0px;"><input id="progress" type="radio" name="OpenOrder_Search" value="progress">
                <span>In Progress</span></label><br> 
                <label class="radio inline" style="margin-top: 0px;"><input id="awaiting" type="radio" name="OpenOrder_Search" value="awaiting">
               <span>Documentation Awating</span></label>
               					
								<button  onclick="searchOpenOrders()"class="btn pull-right" id="openOrderSearch"
									style="margin-left: 0px; color: #ffffff; width: 100px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; border: 1px solid; display: inline-block;"
									type="button">
									<s:property value="getText('search.button')"/>&nbsp;<i class="fa"></i>
								</button><s:hidden name="wildcardSearch" value="true"></s:hidden>
                </div>    
                    </form></td></tr></table>
           
                <lab name="Info_Text" style="font-style:italic;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="getText('message_2')"/></lab>
                
            </div>
            <!-- /.tab-content -->
              
              
            
        
            
                 </div>     
		  
	         <s:set var="results" value="%{result.size}"/>
 			 <s:if test="%{#results==null}">
 			 <!-- this is intial case when the page is called first --> 
 			 <!-- no action required -->
 			 </s:if>
 			 <s:elseif test="%{#results==0}">
   			 <!-- zero records found -->
   			 <label style="color:red;"><span id="noOfOrd"><s:property value="results"/></span> <s:property value="getText('orders.found')"/></label>
			</s:elseif>
			<s:elseif test="%{#results>0}">
 			 <!-- More than 0 records found -->
 			 <label style="color:green;"><span id="noOfOrd"><s:property value="results"/></span> <s:property value="getText('orders.found')"/></label>
 			 </s:elseif>
 			 <!-- Mudassir to handle action errors -->
 			<s:if test="hasActionErrors()">
  <label style="color:red;"> 
      <s:actionerror/>
   </label>
</s:if>
        
            
            <!-- /.box-body -->
        <div class="box box-primary">
            
            <div class="box-header">
              <h3 class="box-title" style="color: #226e71;"><s:property value="getText('all_orders')"/></h3>  &nbsp;<i><h3 class="box-title" style="color: #226e71;" id="Current_Date_Range"></h3></i>
                <div class="box-tools">
               <div class="dropdown pull-right" style="margin-right:10px;">
                        
                       
                        <i class="fa  fa-bars fa-lg dropdown-toggle"  type="button" data-toggle="dropdown"></i>
                        <ul style="margin-left:-90px;background-color:  #656a6b;color:white;" class="dropdown-menu ">
                       <li><a href="javascript:void(0)" class="week" id="week" onclick='rangeFilter("orderDate","openOrderList",this.id);'><s:property value="getText('week_to_date')"/></a></li>
                        <li><a href="javascript:void(0)" class="month" id="month" onclick='rangeFilter("orderDate","openOrderList",this.id);'><s:property value="getText('month_to_date')"/></a></li>
                        <li><a href="javascript:void(0)"  class="quarter" id="quarter" onclick='rangeFilter("orderDate","openOrderList",this.id);'><s:property value="getText('quarter_to_date')"/></a></li>
                        <li><a href="javascript:void(0)"  class="year" id="year" onclick='rangeFilter("orderDate","openOrderList",this.id);'><s:property value="getText('year_to_date')"/></a></li>
                        <li><a href="javascript:void(0)" class="none" id="none" onclick='rangeFilter("orderDate","openOrderList",this.id);'><s:property value="getText('none')"/></a></li>
                        </ul>
                            
                </div>
                </div>
            </div>
            
            <!-- /.box-header -->
            <div class="box-body no-padding table-responsive">
                
              	<table class="table table-striped table-condensed" id="openOrderTable" style="margin-bottom: 0px;">

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
              		<tr style="background-color:#ADC2EE;">
                    <th style="width: 10px"><s:property value="getText('table.head.SNo')"/></th>
                    <th><s:property value="getText('order.id')"/> </th>
                    <th style="text-align:center;" id="orderDate"><s:property value="getText('order.date')"/></th>
                    <th style="text-align:right;">
                    	<s:property value="getText('order.total')"/>
                    	<small>(<s:property value="getText('global.currency')" />)</small>
                    </th>
                    <th style="text-align: right;"><s:property value="getText('customer.id')"/></th>
                    <th><s:property value="getText('customer.name')"/></th>
                    <th><s:property value="getText('status.label')"/></th>
                      <th style="text-align:right;"><s:property value="getText('LPO.label')"/></th>
                        <th style="text-align:right;"><s:property value="getText('no.of.items')"/></th>
                    <th><s:property value="getText('sales.agent')"/></th>
                    <th><s:property value="getText('delivery.date')"/></th>
                    
                </tr>
              	</thead>
							<tbody id="openOrderList">
				
 <s:iterator value="result"  status="itStatus">
 	<tr>
				<td><s:property value="#itStatus.count" />.</td>
			<%-- 	  <td id="OrderId">
    
     
       <s:url action="OpenOrderClick" var="url">
													<s:param name="delvOrderkey">
															<s:property value="id.orderId"/>@<s:property value="id.trnSeq"/>@<s:property value="id.rtStrId"/>@<s:property value="id.ordWs"/>@<s:property value="id.dcDyOrd"/>
													</s:param>
													
													
													</s:url> 
													<s:a href="%{#url}"><s:property value="id.orderId"/></s:a> 
     
      </td> --%>
				<td><a href="orders_details_page?orderId=<s:property value="id.orderId"/>"><s:property value="id.orderId"/></a>   </td>
				
				<td style="text-align:center;"><s:date name="id.orderDate" format="%{getText('format.date')}"/>   </td>
				<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{orderTotal})}"/> </td>
				<td><s:property value="custId"/>  </td>
				<td><s:property value="customerName"/>  </td>
			<td><s:property value="status"/>  </td>
				<td style="text-align:right;"><s:property value="lpoNumber"/>  </td>
				<td style="text-align:right;"><s:property value="itemCount"/></td>
				
				
				<td><s:property value="salesAgent"/>  </td>
				<td style="text-align:center;"><s:date name="orderDate" format="%{getText('format.date')}"/></td>
				</tr> 
 
       </s:iterator>
		</tbody>
		</table>
						

            </div>
            <!-- /.box-body -->
          </div>
    </section>
      
      
    <!-- /.content -->
  </div>

	</div>

		
		
	<script>
	/* $("form[role='search']").hide(); */
    $(function() {
		/* AJAX item Search */
	$("input#InvItemId").autocomplete({
	/*   Enabling the Qty textbox after selecting any item by firing autocomplete select event */
		source : function(request, response) {

			if (request.term.length > 2) {
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
						$("#InvItemId").removeClass("ui-autocomplete-loading");
					}

				});
			} else {
				$("#InvItemId").removeClass("ui-autocomplete-loading");
			}
		}
	});
	
	$("#InvItemId").autocomplete("widget").attr('style',
			'max-height: 300px; overflow-y: auto; overflow-x: hidden;')//to get a scroll bar


/*       //Initialize Select2 Elements
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
      }); */
 //Date picker
       var dateFormat= "<s:text name="bootstrap.date.format"/>";
	   var datePickerProp = {todayBtn: "linked", autoclose: true, language: "en", endDate: new Date(),
	   			todayHighlight: true, format:dateFormat};
 	
      $('#datepicker1').datepickerBS(datePickerProp);
      $('#datepicker2').datepickerBS(datePickerProp);
      $('#datepicker3').datepickerBS(datePickerProp);
      $('#datepicker4').datepickerBS(datePickerProp);
     
    });
  </script>
  
  
    <script>
    function searchOpenOrders(){
    	
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
    	    			||$('#datepicker1').val().length>=1 || $('#datepicker2').val().length>=1
    	    			||$('#OrderTotalFrom').val().length>=1 || $('#OrderTotalTo').val().length>=1)
    	    			{
    	    			result =true;
    	    			}else
    	    			{
    	    				result=false;
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
    		document.forms["Open_Order_hidden_order_data"].openOrderStatus.value=$('input[name="OpenOrder_Search"]:checked').attr('id');
    		document.forms["Open_Order_hidden_order_data"].activeTab.value= activeTab;
    		
    		if(activeTab === "tab_1"){
    			document.forms["Open_Order_hidden_order_data"].orderID.value=$('#orderID').val();
    			document.forms["Open_Order_hidden_order_data"].orderFromDate.value=$('#datepicker1').val();
    			document.forms["Open_Order_hidden_order_data"].ordertoDate.value=$('#datepicker2').val();
    			document.forms["Open_Order_hidden_order_data"].OrderTotalFrom.value=$('#OrderTotalFrom').val();
    			document.forms["Open_Order_hidden_order_data"].OrderTotalTo.value=$('#OrderTotalTo').val();
    			
    			document.Open_Order_hidden_order_data.action ="openOrdersSearchByOrder";
    		}else
    				if(activeTab === "tab_3"){
    					document.forms["Open_Order_hidden_order_data"].customerInfo.value=$('#customerInfo').val();
    					document.forms["Open_Order_hidden_order_data"].InvItemId.value=$('#InvItemId').val();
    					
    					document.Open_Order_hidden_order_data.action ="openOrdersSearchByCustomer";
    				}
    		                     
            //clearing the input search fields
            $("#orderID").val('');
            $("#datepicker1").val('');
            $("#datepicker2").val('');
            $("#OrderTotalFrom").val('');
            $("#OrderTotalTo").val('');
            $("#customerInfo").val('');
            $("#InvItemId").val('');
            
            document.forms["Open_Order_hidden_order_data"].submit();
    	}
    }    
    </script>
    
  
   <form name="Open_Order_hidden_order_data" method="post">
   
   	<input type="hidden" name="openOrderStatus"/>
    <input type="hidden" name="activeTab"/>
    <input type="hidden" name="orderID"/>
    <input type="hidden" name="orderFromDate"/>
    <input type="hidden" name="ordertoDate"/>
    <input type="hidden" name="OrderTotalFrom"/>
    <input type="hidden" name="OrderTotalTo"/>
    <input type="hidden" name="customerInfo"/>
    <input type="hidden" name="InvItemId"/>
     </form>

  <script type="text/javascript">
  document.onkeydown = function (evt) {
	  var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
	  if (keyCode == 13) {
		  document.getElementById('openOrderSearch').click();
	  }
	 else {
	    return true;
	  }
	};
  </script>
 
  
	<script type="text/javascript">
	/* $(function(){
		$('#openOrderTable').DataTable({
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
	}); */
   $('#order').addClass('active');
  $('#openorders').addClass('active');
  </script>
  

	<script type="text/javascript">
		$("#customerInfo").autocomplete({
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
				//$("#openOrderSearch")..trigger('click');// get(0).submit();//
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

</body>
</html>


