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
<title>SDS | Accept Claims</title>
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
<!-- Select2 
<script src="assets/plugins/select2/select2.full.min.js"></script>-->
<!-- InputMask
<script src="assets/plugins/input-mask/jquery.inputmask.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script> -->
<!-- date-range-picker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<%-- <script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
 --%><!-- bootstrap datepicker -->
<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="assets/moment.min.js"></script>
<script src="custom/dateRangeFilter.js"></script>
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
 

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

				<!-- /.box-body -->
				<div class="row" style="margin: 5px; margin-top: -15px;">
					<br>
					<!-- Your Page Content Here -->
					<label style="font-size: 25px; color: #226e71;"></label> <small
						class="pull-right"
						style="text-align: right; font-size: 14px; color: #404040; width: 500px;"><b><s:property
								value="getText('acceptclaim.search.help_text')" /></b></small>
					<div class="box box-primary">

						<div class="box-header">
							<h3 class="box-title" style="color: #226e71;">
								<s:property value="getText('needtoaccept.claims')" /> &nbsp;<i><h3 class="box-title" style="color: #226e71;" id="Current_Date_Range"><s:property value="acceptClaimRange" /></h3></i>
							</h3>
							<div class="box-tools">
								<div class="dropdown pull-right" style="margin-right: 10px;">
									<i class="fa  fa-bars fa-lg dropdown-toggle" type="button"
										data-toggle="dropdown"></i>
									<ul
										style="margin-left: -90px; background-color: #656a6b; color: white;"
										class="dropdown-menu ">
										<li><a href="javascript:void(0)" class="week" id="week" onclick='rangeFilter("claimDate","claimList",this.id)'><s:property value="getText('current.week')"/> </a></li>
										<li><a href="javascript:void(0)" class="month" id="month" onclick='rangeFilter("claimDate","claimList",this.id)'><s:property value="getText('current.month')"/> </a></li>
										<li><a href="javascript:void(0)" class="quarter" id="quarter" onclick='rangeFilter("claimDate","claimList",this.id)'><s:property value="getText('current.quarter')"/></a></li>
										<li><a href="javascript:void(0)" class="year" id="year" onclick='rangeFilter("claimDate","claimList",this.id)'><s:property value="getText('current.year')"/></a></li>
										<li><a href="javascript:void(0)" class="none" id="none" onclick='rangeFilter("claimDate","claimList",this.id)'><s:property value="getText('none')"/></a></li>
									</ul>
								</div>
							</div>
						</div>

						<!-- /.box-header -->
						<div class="box-body no-padding table-responsive">

							<table class="table table-striped">
								<thead>
									<tr style="background-color: #ADC2EE;">
										<th style="width: 10px"><s:property
												value="getText('table.head.SNo')" /></th>
										<th><s:property value="getText('claim.id')" /></th>
										<th id="claimDate"><s:property value="getText('claim.date')" /> </th>
										<th style="text-align: right;"><s:property
												value="getText('claim.total')" />(<small><s:text
														name="global.currency" /></small>)</th>
										<th><s:property value="getText('received.date')" /></th>
										<th style="text-align: right;"><s:property
												value="getText('customer.id')" /></th>
										<th><s:property value="getText('customer.name')" /></th>
										<th><s:property value="getText('sales.agent')" /></th>
										<th><s:property value="getText('reason.code')" /></th>
									</tr>
								</thead>
								<tbody id="claimList">
									<s:iterator value="acceptClaim_List1.needToBeAccepted"
										status="itStatus">
										<tr>
											<td><s:property value="%{#itStatus.index+1}" />.</td>
											<td id="ClaimId"><a
												href="readyToAcceptClaimDetails?claimId=<s:property value="claimId"/>">
													<s:property value="claimId" />
											</a></td>
											<td><s:date name="claimDate"
													format="%{getText('format.date')}" /></td>
											<td class="text-right">
											 <s:property value="%{getText('format.currency.args',{claimTotal})}"/> 
											</td>
											<td><s:date name="receivedDate"
													format="%{getText('format.date')}" /></td>
											<td style="text-align: right;"><s:property value="customerId" /></td>
											<td><s:property value="customerName" /></td>
											<td><s:property value="salesAgent" /></td>
											<td><s:property value="reasonCode" /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
					<br>
					<br>
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title" style="color: #226e71;">
								<s:property value="getText('autoaccepted.claims')"/> &nbsp;<i><h3 class="box-title" style="color: #226e71;" id="Current_Date_Range1"><s:property value="autoAcceptClaimRange" /></h3></i>
							</h3>
							<div class="box-tools">
								<div class="dropdown pull-right" style="margin-right: 10px;">
									<i class="fa  fa-bars fa-lg dropdown-toggle" type="button"
										data-toggle="dropdown"></i>
									<ul
										style="margin-left: -90px; background-color: #656a6b; color: white;"
										class="dropdown-menu">
									<li><a href="javascript:void(0)" class="week1" id="week1" onclick='rangeFilter("claimDate1","autoAcceptclaimList",this.id)'><s:property value="getText('current.week')"/> </a></li>
										<li><a href="javascript:void(0)" class="month1" id="month1" onclick='rangeFilter("claimDate1","autoAcceptclaimList",this.id)'><s:property value="getText('current.month')"/> </a></li>
										<li><a href="javascript:void(0)" class="quarter1" id="quarter1" onclick='rangeFilter("claimDate1","autoAcceptclaimList",this.id)'><s:property value="getText('current.quarter')"/></a></li>
										<li><a href="javascript:void(0)" class="year1" id="year1" onclick='rangeFilter("claimDate1","autoAcceptclaimList",this.id)'><s:property value="getText('current.year')"/></a></li>
										<li><a href="javascript:void(0)" class="none1" id="none1" onclick='rangeFilter("claimDate1","autoAcceptclaimList",this.id)'><s:property value="getText('none')"/></a></li>
									
									</ul>
								</div>
							</div>
						</div>

						<!-- /.box-header -->
						<div class="box-body no-padding table-responsive">
							<table class="table table-striped">
								<thead>
									<tr style="background-color: #ADC2EE;">
										<th style="width: 10px"><s:property
												value="getText('table.head.SNo')" /></th>
										<th><s:property value="getText('claim.id')" /></th>
										<th id="claimDate1"><s:property value="getText('claim.date')" /> </th>
										<th style="text-align: right;"><s:property
												value="getText('claim.total')" />(<small><s:text
														name="global.currency" /></small>)</th>
										<th><s:property value="getText('received.date')" /></th>
										<th style="text-align: right;"><s:property
												value="getText('customer.id')" /></th>
										<th><s:property value="getText('customer.name')" /></th>
										<th><s:property value="getText('sales.agent')" /></th>
										<th><s:property value="getText('reason.code')" /></th>
									</tr>
								</thead>
								<tbody id="autoAcceptclaimList">
									<s:iterator value="acceptClaim_List1.autoAccepted"
										status="itStatus">
										<tr>
											<td><s:property value="%{#itStatus.index+1}" />.</td>
											<td id="ClaimId"><a
												href="ClaimSearchDetails?claimID=<s:property value="claimId"/>">
													<s:property value="claimId" />
											</a></td>
											<td><s:date name="claimDate"
													format="%{getText('format.date')}" /></td>
											<td style="text-align: right;"> 
												 <s:property value="%{getText('format.currency.args',{claimTotal})}"/> 
											</td>
											<td><s:date name="receivedDate"
													format="%{getText('format.date')}" /></td>
											<td style="text-align: right;"><s:property	value="customerId" /></td>
											<td><s:property value="customerName" /></td>
											<td><s:property value="salesAgent" /></td>
											<td><s:property value="reasonCode" /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
					<!-- /.box-body -->
				</div>


			</section>
		</div>
		<%--       
    <!-- /.content -->
  </div>

	</div>
	<!-- ./wrapper -->
	<!-- when no invoices found @mallikarjun -->
		<div class="modal fade bs-example-modal-sm" id="noInvFnd"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">No Invoices found for the entered information</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal">OK&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>

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
	<script src="assets/plugins/jQueryUI/jquery-ui.js"></script>

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
      $('#datepicker1').datepicker({
        autoclose: true
      });
      $('#datepicker2').datepicker({
        autoclose: true
      });
      $('#datepicker3').datepicker({
        autoclose: true
      });
      $('#datepicker4').datepicker({
        autoclose: true
      });
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
    			||($('#datepicker1').val().length>=6 || $('#datepicker2').val().length>=6)
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
                      var InvItemId = $('#InvItemId').val();
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
                        		      datepicker1: $('#datepicker1').val(),
                        		      datepicker2: $('#datepicker2').val(),
                        		      OrderTotalFrom: $('#OrderTotalFrom').val(),
                        		      OrderTotalTo: $('#OrderTotalTo').val(),
                        		      invoiceStatus:invoiceStatus,
                        		      activeTab:activeTab
                        		      };
                          ajaxUrl = 'invoiceSearchByOrder';
                        }
                      } else if (activeTab === 'tab_2')
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
                      } else if (activeTab === 'tab_3') 
                       {
                        if (!isValidDataInTab(activeTab)) {
                          $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                          $('#customerInfo').focus();
                          $('#invSearch').prop('disabled', false);
                          $('#invSearch i').removeClass('fa-spinner fa-spin');

                          return;
                        } else {
                          ajaxData = 'customerInfo=' + customerInfo +'&invType='+ invTyp +'&InvItemId='+ InvItemId;
                          ajaxUrl = 'invoiceSearchByCustomer';
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
                                    $('#invoiceList').empty();
                                    $('#noOfInvoices').html(0);
                                    window.setTimeout(function() {
                                    	$('#noInvFnd').modal('show');
                                    }, 50);
                                    return;
                                  }
                                  if (result.invoices != null) {
                                	  $("lab[name='Info_Text']").css("color", "Black");
                                    $('#invoiceList').empty();
                                    $('#noOfInvoices').html(result.invoices.length);
                                    $
                                            .each(
                                                    result.invoices,
                                                    function(key, value) {
                                                      var invoice = '<tr> <td>SNO</td> <td><a href=# id="invoice_'+key+'">INVOICENO</a></td> <td>INVOICEDATE</td> <td>INVOICETOTAL</td> <td>INVOICEAGE</td> <td>CUSTOMER</td> <td>ORDERID</td> <td>AMOUNTPAID</td> <td>BALANCEDUE</td> </tr>';
                                                      invoice = invoice.replace('SNO', (key + 1) + '.');
                                                      invoice = invoice.replace('INVOICENO', value.arInvNum);
                                                      invoice = invoice.replace('INVOICEDATE', moment(value.arInvDate).format('MM/DD/YY'));
                                                      invoice = invoice.replace('INVOICETOTAL', value.invAmount);
                                                      invoice = invoice.replace('INVOICEAGE', moment().diff(value.arInvDate, 'days'));
                                                      invoice = invoice.replace('CUSTOMER', result.customerName[key]);
                                                      invoice = invoice.replace('ORDERID', value.orderNum);
                                                      invoice = invoice.replace('AMOUNTPAID', (value.invAmount - value.invPendAmount));
                                                      invoice = invoice.replace('BALANCEDUE', value.invPendAmount);

                                                      $('#invoiceList').append(invoice);
                                                    });

                                  }
                                },
                                error: function(jqXHR, exception) {
                                  $('#invSearch').prop('disabled', false);
                                  $('#invSearch i').removeClass('fa-spinner fa-spin');
                                  $('#tab_1 *,#tab_2 *,#tab_3 *').prop('disabled', false);
                                  alert(exception);
                                }
                              });
                    });

    $("body").on("click", "[id*='invoice_']", function(e) {
      //e.preventDefaults();
      var invoiceID = $(this).html();
      var myWindow = window.open("InvoiceDetail?invoiceID=" + invoiceID, "_parent");

    });
  </script>
	<script type="text/javascript">
    $('#claim').addClass('active');
    $('#acceptclaim').addClass('active');
  </script>


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
    $('#invoiceID').pressEnter(function() {
      $('#invSearch').trigger("click");
    });
       $('#customerInfo').pressEnter(function() {
      $('#invSearch').trigger("click");
    });
  </script> --%>
   <script>
      $("body").on("click", "[id*='invoice_']", function(e) {
      //e.preventDefaults();
      var claim_Id = $(this).html();
      var myWindow = window.open("readyToAcceptClaimDetails?invoiceID=" + $('#Claim_Id').html(), "_parent");

    });
  </script>
  
	<script type="text/javascript">
    $('#claim').addClass('active');
    $('#acceptclaim').addClass('active');
  </script>
  <script>
  $(function(){
	  var searchRange = "${acceptClaimRange}";
	  var autoAcceptsearchRange = "${autoAcceptClaimRange}";
	  if(searchRange == "CURRENT_WEEK"){
	  $('.week').trigger('click');
  	}else if(searchRange == "CURRENT_MONTH"){
  	$('.month').trigger('click');
  	}else if(searchRange == "CURRENT_QUARTER"){
  	  	$('.quarter').trigger('click');
 	 }else if(searchRange == "CURRENT_YEAR"){
  	$('.year').trigger('click');
 	 }else{
 	 $('.none').trigger('click');
  }  
	  //auto accept
	  if(autoAcceptsearchRange == "CURRENT_WEEK"){
		  $('.week1').trigger('click');
	  	}else if(autoAcceptsearchRange == "CURRENT_MONTH"){
	  	$('.month1').trigger('click');
	 	 }else if(autoAcceptsearchRange == "CURRENT_QUARTER"){
	   	  	$('.quarter1').trigger('click');
	 	 }else if(autoAcceptsearchRange == "CURRENT_YEAR"){
	  	$('.year1').trigger('click');
	 	 }else{
	 	 $('.none1').trigger('click');
	  }  
  });
  </script>
</body>
</html>

