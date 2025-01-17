<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%-- <%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", -1);
	response.setHeader("Pragma", "no-cache");
%> --%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
-->
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | Register Claim</title>
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

<!-- Theme style -->

<link rel="stylesheet" href="assets/css/popup.css">
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="assets/css/jquery-ui.css">

<link rel="stylesheet" href="semantic/semantic.min.css">
<link rel="stylesheet" href="assets/css/Customizations.css">
<link href="custom/chosen/chosen.min.css" rel="stylesheet" />
<style>
input.wait {
	background: white url('ajax-loader.gif') no-repeat right center
}
</style>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

  <![endif]-->

<!-- jQuery 2.2.0 -->

<script src="assets/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="assets/plugins/jQueryUI/jquery-ui.js"></script>
<script src="assets/tableHeadFixer.js"></script>

<script src="assets/dist/js/semantic.min.js"></script>

<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
<script src="assets/plugins/listgroup/listgroup.min.js"></script>
<script type="text/javascript" src="custom/format.js"></script>
<script src="custom/chosen/chosen.jquery.min.js"></script>

<script type="text/javascript"> 
var globalClaimStatusNew ="<s:text name="claim.status.new"/>";     
var globalRestockingFeeItemId = "<s:text name="restockingFee.serviceitem.id"/>";    
var globalTransportationFeeItemId = "<s:text name="transportationFee.serviceitem.id"/>";    
var returnReasonCodes= ${returnReasoncodes}; 
$(document).ready(function(){
	
	/*With invoice: Change transaction level reasion code list */
	$("#returnReasonCode").change(function () {
        var array1=$('[id^=reasonCode_]').val(this.value);
		
    });
    
	/*WithOut invoice: Change transaction level reasion code list */
    $("#globalReasonCode").change(function () {
        var array1=$('[id^=reason_]').val(this.value);
		
    });
   
    
	$("#itemDetailsTable").tableHeadFixer();
		/* AJAX item Search */
		$("input#itemIDDesc").autocomplete({
		autoFocus: true,
		/*   Enabling the Qty textbox after selecting any item by firing autocomplete select event */
		  select: function( event, ui ) {
		  	$('#qty').prop("disabled", false);
		    $('#qty').focus();
		   var qtyLen = $('#qty').val().length;
			if (qtyLen > 0) {
				$('#additembutton').prop("disabled", false);
				} else {
				$('#additembutton').prop("disabled", true);
				}
			},
										   
			source : function(request, response) {

				//$('.ui-autocomplete-loading').removeClass("ui-autocomplete-loading");

				if (request.term.length > 0) {
					/*  document.getElementById("loadText").style.display ="none"; */
					//document.getElementById("load").style.display = "block";  // show the loading image.
					//$('.ui-autocomplete-loading').addClass("ui-autocomplete-loading");
					$("#itemIDDesc").addClass("wait");

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
							$("#itemIDDesc").removeClass("wait");
						}
						});
				} else {
					/* document.getElementById("load").style.display = "none"; */
					$("#itemIDDesc").removeClass("wait");
				}
			},
			focus: function( event, ui ) { event.preventDefault(); }
		});
		$("#itemIDDesc").autocomplete("widget").attr('style',
				'max-height: 300px; overflow-y: auto; overflow-x: hidden;')//to get a scroll bar

		/*   Enabling the Qty textbox based on itemIDDesc textbox input */
		$('#itemIDDesc').on('input', function(e) {
			var qtyLen = $('#qty').val().length;
			var itemIDDescLen = $('#itemIDDesc').val().length;
			$('#qty').prop("disabled", true);
			$('#additembutton').prop("disabled", true);
			});

		/*   Enabling the additembutton button based on Qty textbox input */
		$('#qty').on('input', function(e) {
		    var qty =  $(this).val();
			var qtyLen = qty.length;
			
			if (qtyLen > 0 && qty > 0 ) {
				$('#additembutton').prop("disabled", false);
				
			} else {
				$('#additembutton').prop("disabled", true);
				$(this).val(""); // dont allow qty 0
			}
		});

		/* for EDI UPload button */
			
			
			/* $(document).ready(function () {

			     // Attach Button click event listener 
			    $("#myBtn1").click(function(){

			         // show Modal
			         $('#myModal').modal('show');
			    });
			}); 
			 */
	
	   
		
// Focus to customer search once page loads
    $('#custInfo').focus();
    $("#mandate").hide();
    $('#custInfo').on('focus', function() {
      $('#noCustError').addClass('hidden');
    });

//search without invoice
//getting customer info
$('#custsearch').click(function(event){
event.preventDefault();
  if ($('#custInfo').val().trim().length <= 0) {
                        $('#custInfo').parent().removeClass('has-error');
                        $('fieldset').prop('disabled', false);
                        $("#mandate").show();
                        $('#custsearch').removeClass('fa-spinner fa-spin');
                        $('#custInfo').focus();
                        return false;
                      }

$('#multipleCust').addClass('hidden');
$('#customerSelected').addClass('hidden');		
$("#customer-list-group").empty();
$('#custsearchSpinner').addClass('fa-spinner fa-spin');
					var custInfoStr = $('#custInfo').val();
					var currencyFormat = "<s:property value="getText('format.currency')"/>";
					$.ajax(
							{
							url : 'customerlookupAtClaims',
							type : 'post',
							data : 'custInfo=' + custInfoStr,
							//timeout : 120000,
							timeout : 1800000,
							success : function(result)
							{
								
								$('#custsearchSpinner')
										.removeClass('fa-spinner fa-spin');

								if (result != null && result.found != null && result.customers != null && result.customers.length > 0)
								{
									$("#custInfo").val("");
									if (result.customers.length == 1)
									{
										//Single Customer Found
										//$.toaster({priority : 'success',title : 'Alert',message : 'One Customer found'});
										$('#custId').html(result.customers[0].customerHeader.customerHeaderPK.custId);
										//krishna disable the with invoice tab
										var tab=$("[href='#tab_1']");
										var custAvailCreditLmt=format(currencyFormat,result.customers[0].customerLimits.avCrdtLimit);
										var custBalDue=format(currencyFormat,result.customers[0].customerLimits.pendDue);
										tab.attr("href", "#");
										tab.attr("data-toggle", "");
										$('#tab1').attr("class","disabled");
										$('#cancelClaimWOI').prop("disabled", false);
										$('#ediButton').prop("disabled", false);
										//$('#custsearch').prop("disabled", true);
										//$('#custInfo').prop("disabled", true);
																			
										$('#custAvailCreditLmt').html(custAvailCreditLmt);
										$('#customerName').html(result.customers[0].customerHeader.ctNm);
										$('#customerSeg').html(result.customers[0].customerSegmentID);
										/* $('#custSeg').val(result.customers[0].customerHeader.customerHeaderPK.custId); */
										$('#custBalDue').html(custBalDue);
										$('#restckFeeAnch').removeClass('disable_a_href');
										$('#transportFeeAnch').removeClass('disable_a_href');
										$('#claimStatus').html(globalClaimStatusNew );
										var returnReasonCodesLength = $('#globalReasonCode').find("option").length;
										if(result.orderTran.salesAgentsMap!=null)
										{
											// Laxmikanth: sorting the salesAgents in alphabetical order as per uat feedback
											var map = result.orderTran.salesAgentsMap;
											var array = [];
											for (var key in map) {
											  array.push({
											    name: key,
											    value: map[key]
											 });
											}
											var sortedMap = array.sort(function(a, b) {
  												return a.value.localeCompare(b.value);	
											});
											$('#sales_Agent').empty()
										 $.each(sortedMap, function(index,value){
											        $('#sales_Agent').append( '<option value="'+value.name+'" style="font-weight:bold">'+value.value+'</option>' );
											});
										}
										$('#sales_Agent').trigger('chosen:updated');
										
										if(result.orderTran.custSites)
										{
											var map = result.orderTran.custSites;
											var array = [];
											for (var key in map) {
											  array.push({
											    name: key,
											    value: map[key]
											 });
											}
											
											var sortedMap = array.sort(function(a, b) {
  												return a.value.localeCompare(b.value);	
											});
										$('#cust_sites').empty()
										 $.each(sortedMap, function(index,value){
											        $('#cust_sites').append( '<option value="'+value.name+'" style="font-weight:bold">'+value.value+'</option>' );
											});
										}
										$('#cust_sites').trigger('chosen:updated');
										
										if(returnReasonCodesLength<=0)
										{
							                
							                $.each( returnReasonCodes, function(index,value){
											        $('#globalReasonCode').append( '<option value="'+index+'" style="font-weight:bold">'+value+'</option>' );
											});	
										}
										
						               //disable expenses if the transaction have expenses items while relinking customer
					                   var ordLineItems = result.orderTran.ordTranLineItems;
					                   if(ordLineItems != null)
					                   { 
					                       var hasRestockingFeeServiceitem=false,hasTransportationFeeServiceitem=false;
						                   $.each(ordLineItems,function(key, value) {
						                          var itemId=value.itemId;
							                   //to check RestockingFeeServiceitem is there in transaction
												if(itemId == globalRestockingFeeItemId)
												{
												  hasRestockingFeeServiceitem=true;
												}
												//to check RestockingFeeServiceitem is there in transaction
												if(itemId == globalTransportationFeeItemId)
												{
												 hasTransportationFeeServiceitem=true;
												}
							                    
						                   });
						                   updateButtons(result,hasRestockingFeeServiceitem,hasTransportationFeeServiceitem);
						                   
					                   }
										$('#itemIDDesc').prop("disabled",false);
										$('#itemIDDesc').focus();
										
									} else
									{
										//Multiple Customers Found
										$
												.each(result.customers, function(key, value)
												{
                                                        var customer = value.customerHeader;
                                                        var active = "";
                                                        if (key == 0) active = " active";
                                                        //var list_item = "<div class=\"list-group-item"+active+"\"><div class=\"row\"> <div class=\"col-xs-6\"> <h4 class=\"list-group-item-heading\" id=\"custname\">NAME</h4> </div> <div class=\"col-xs-6\"> <h4 class=\"list-group-item-heading\" id=\"custid\">CUSTID</h4> </div> </div> <div class=\"row\"> <div class=\"col-xs-6\"> <div class=\"list-group-item-text\" id=\"phone\">PHONE</div> </div> <div class=\"col-xs-6\"> <div class=\"list-group-item-text\" id=\"email\">EMAIL</div> </div> </div> <p class=\"list-group-item-text\" id=\"url\">URL</p> </div>";
                                                        //var list_item = "<div class=\"list-group-item"+active+"\"> <div class=\"row\"> <div class=\"col-xs-5\"> <h4 class=\"list-group-item-heading\" id=\"custname\">NAME</h4> </div> <div class=\"col-xs-4\"> <h4 class=\"list-group-item-heading\" id=\"custid\">CUSTID</h4> </div> <div class=\"col-xs-3\"> <h4 class=\"list-group-item-heading\" id=\"status\">STATUS</h4> </div> </div> <div class=\"row\"> <div class=\"col-xs-5\"> <div class=\"list-group-item-text\" id=\"phone\">PHONE</div> </div> <div class=\"col-xs-4\"> <div class=\"list-group-item-text\" id=\"email\">EMAIL</div> </div> </div> </div>";
														var list_item = "<div class=\"list-group-item"+active+"\"> <div class=\"row\"> <div class=\"col-xs-4\"> <h4 class=\"list-group-item-heading\" id=\"custname\">NAME</h4> </div> <div class=\"col-xs-3\"> <h4 class=\"list-group-item-heading\" id=\"custid\">CUSTID</h4> </div> <div class=\"col-xs-3\"><h4 class=\"list-group-item-heading\" id=\"phone\">PHONE</h4></div><div class=\"col-xs-2\"><h4 class=\"list-group-item-heading\" id=\"status\">STATUS</h4> </div> </div> ";
                                                        list_item = list_item.replace("NAME", /* "Name: " + */ customer.ctNm);
                                                        list_item = list_item.replace("CUSTID", /* "Cust. ID: " + */ customer.customerHeaderPK.custId);
                                                        var status;
                                                        if (customer.ctStsCd == '0') {
                                                          status = 'In-Active';
                                                        } else if (customer.ctStsCd == '1' || customer.ctStsCd == 'A') {
                                                          status = 'Active';
                                                        } else {
                                                          status = 'Deleted';
                                                        }
                                                        list_item = list_item.replace("STATUS", /* "Status: "  +*/ status);
                                                        if(customer.ctPhone)
                                                       		list_item = list_item.replace("PHONE", /* "Phone: " + */ customer.ctPhone);
                                                        else
                                                        	list_item = list_item.replace("PHONE", "&nbsp;");
                                                        if(customer.ctEmlId)
                                                        	list_item = list_item.replace("EMAIL", /* "Email: " + */ customer.ctEmlId);
                                                        else
                                                        	list_item = list_item.replace("EMAIL", "&nbsp;");
                                                        $("#customer-list-group").append(list_item);
												});

										$('#multipleCust')
												.removeClass('hidden');
										$('#customerSelected')
												.removeClass('hidden');		
										//$('#multipleCustomersModal').modal('show');

									}
									$('#noCustError').addClass('hidden');
								} else
								{ // Customer Info not found
									//$.toaster({priority : 'danger',title : 'Alert',message : 'No customers were found with this information!'});

									$('#noCustError').removeClass('hidden');
									setTimeout(function()
									{
										$('#noCustError').addClass('hidden');
									}, 20000)
								}
							},
							error : function(jqXHR, exception)
							{
								$('fieldset').prop('disabled', false);
								$('#custsearch')
										.removeClass('fa-spinner fa-spin');
								alert('An error occured while sending the request:\n' + exception);
							}
							});

					
});

//once the customer is selected and press a next button will populate customer info to show in page
$('#customerSelected').click(
function(event){
$('#customerSelectedSpinner').addClass('fa-spinner fa-spin');

var custIdStr = $('div.list-group-item.active').find('#custid').html();
      var custId = custIdStr.substr(custIdStr.lastIndexOf(':') + 1).trim();
  	  var currencyFormat = "<s:property value="getText('format.currency')"/>";
     		
                   $.ajax( {
							url : 'selectCustomerAtClaims',
							type : 'post',
							data : 'custId=' + custId,
							//timeout : 120000,
							timeout : 1800000,
							success : function(cust)
									{
								var custAvailCreditLmt=format(currencyFormat,cust.orderTran.customer.customerLimits.avCrdtLimit);
								var custBalDue=format(currencyFormat,cust.orderTran.customer.customerLimits.pendDue);
									$('#multipleCust').addClass('hidden');
									$('#customerSelectedSpinner').removeClass('fa-spinner fa-spin');
									$('#customerSelected').addClass('hidden');
									$('#custId').html(cust.orderTran.customer.customerHeader.customerHeaderPK.custId);
															
									$('#custAvailCreditLmt').html(custAvailCreditLmt);
									$('#customerName').html(cust.orderTran.customer.customerHeader.ctNm);
									$('#customerSeg').html(cust.orderTran.customer.customerSegmentID);
									/* $('#custSeg').val(result.customers[0].customerHeader.customerHeaderPK.custId); */
									$('#custBalDue').html(custBalDue);
									
									$('#restckFeeAnch').removeClass('disable_a_href');
									$('#transportFeeAnch').removeClass('disable_a_href');
									$('#claimStatus').html(globalClaimStatusNew);
									//Hanu disable the with invoice tab when customer selected
										var tab=$("[href='#tab_1']");
										tab.attr("href", "#");
										tab.attr("data-toggle", "");
										$('#tab1').attr("class","disabled");
										$('#cancelClaimWOI').prop("disabled", false);
									var returnReasonCodesLength = $('#globalReasonCode').find("option").length;
									if(cust.orderTran.salesAgentsMap!=null)
									{
									 $.each( cust.orderTran.salesAgentsMap, function(index,value){
										        $('#sales_Agent').append( '<option value="'+index+'" style="font-weight:bold">'+value+'</option>' );
										});
									}
									if(returnReasonCodesLength<=0)
									{
																	
									$.each( returnReasonCodes, function(index,value){
									        $('#globalReasonCode').append( '<option value="'+index+'" style="font-weight:bold">'+value+'</option>' );
									});	
									}
							           //disable expenses if the transaction have expenses items while relinking customer
					                   var ordLineItems = cust.orderTran.ordTranLineItems;
					                   if(ordLineItems != null)
					                   { 
					                       var hasRestockingFeeServiceitem=false,hasTransportationFeeServiceitem=false;
						                   $.each(ordLineItems,function(key, value) {
						                          var itemId=value.itemId;
							                   //to check RestockingFeeServiceitem is there in transaction
												if(itemId == globalRestockingFeeItemId)
												{
												  hasRestockingFeeServiceitem=true;
												}
												//to check RestockingFeeServiceitem is there in transaction
												if(itemId == globalTransportationFeeItemId)
												{
												 hasTransportationFeeServiceitem=true;
												}
							                    
						                   });
						                   updateButtons(cust,hasRestockingFeeServiceitem,hasTransportationFeeServiceitem);
						                   
					                   }						
						
									$('#itemIDDesc').prop("disabled",false);
									
									}
							});



}



);

//add items to table
		/* AJAX item ADDING */
		$('#additembutton').click(function(event) {
			$('#additembutton').prop("disabled", true);
			/* document.getElementById("loadButtonClick").style.display = "block";  // show the loading message. */
			var itemid = $('#itemIDDesc').val();
			var qty = $('#qty').val();
			var checkInv = "false";
			$('#spinner').addClass('fa-spinner fa-spin');
            var tranType=2;
			$.ajax({
				url : "addItemAction",
				type : "POST",
				data : {
					term : itemid,
					qty : qty,
					checkInv : checkInv,
					tranType :tranType
								
				},
				dataType : "json",
				success : function(jsonResponse) {
					
						UpdateTableContent(jsonResponse);
					
					$('#spinner').removeClass('fa-spinner fa-spin');
					//manually trigaring event
					$("#itemDetailsTable").trigger('update');

					$('#additembutton').prop("disabled", true);
					$('#itemIDDesc').val("");
					$('#qty').val("");

					$('#qty').prop("disabled", true)
				}

			});
			
		});

	//to updateOrders table content
	function UpdateTableContent(jsonResponse) {
		$("#itemDetailsTable > tbody").children('tr').remove();//removing existing table rows
		var row,hasRestockingFeeServiceitem=false,hasTransportationFeeServiceitem=false;
		var i = 1;
		var defaultResonCodeOptions;
		//to get reason codes
		var firstResoncode;
	  	
		$.each( returnReasonCodes, function(index,value){
		       defaultResonCodeOptions += '<option value="'+index+'">'+value+'</option>';
		       if(firstResoncode == null)
		       {
		        firstResoncode = index;
		       }
		});
		var managerOverride = <s:property value="managerOverride"/>;		
		
		var orderTran = jsonResponse.orderTran;

		$
				.each(
						orderTran.ordTranLineItems,
						function(key, value) {

							//Discount amt,percent function
							var discountAmt = 0, discountPer = 0,itemPrmName= "",itemPromoDivDisplayStyle="none";;
							$.each(value.ordTranDscItms, function(key, disc) {
								
								discountAmt += disc.dscAmt;
								discountPer += disc.dscPer;
								
								if(disc.prmId!=null)
								{
								 itemPrmName += disc.prmDesc;
								 itemPromoDivDisplayStyle = "block";
								}
							});
							var resonCodeOptions;
							//to get selected reson code
							if(value.rcRtnMr !=null)
							{
							var selectedReasonCodeOptions;
							 $.each( returnReasonCodes, function(index,reason){
							   if(value.rcRtnMr == index )
							   {
						        selectedReasonCodeOptions += '<option value="'+index+'" selected="selected" >'+reason+'</option>';
						       }
						       else
						       {
						        selectedReasonCodeOptions += '<option value="'+index+'">'+reason+'</option>';
						       }
						     });
						     	resonCodeOptions =	selectedReasonCodeOptions;					
							}
							else
							{
							 value.rcRtnMr=firstResoncode;
							 resonCodeOptions =	defaultResonCodeOptions;		
							}
							var returnPrice = "",returnqty= value.lineQnt;
							if(value.priceOverRideFlag == "1" )
							{
							 returnPrice =  value.overRidePrice;
							}
							if(value.returnQtyFlag == "1" )
							{
							 returnqty = value.lineQntRtn;
							}
							var rowNum = i,itemId=value.itemId;
							rowNum--;
							
							var returnPriceField;
							if(managerOverride){
								returnPriceField = '<input type="number" style="width:130px;background-color:#f7ffb7;" class="form-control"'+
									' onkeypress="return isNumericKey(event);" onInput="checkLength(9,this)"'+
									' value="'+ returnPrice +'" id="returnPrice_'+rowNum+'" min="1"'+
									' data-toggle="popover" data-trigger="focus" data-content="Will require manager override" >';
							}
							else{
								returnPriceField = '<input type="number" style="width:130px;background-color:#f7ffb7;" class="form-control"'+
									' onkeypress="return isNumericKey(event);" onInput="checkLength(9,this)"'+
									' value="'+ returnPrice +'" id="returnPrice_'+rowNum+'" min="1" >';
							}
							//var priceChangeReasonCodeMap = ${priceChangeReasonCodeMap};
							var priceChangeReasonCodeOptions = '${priceChangeReasonCodeMapAsOptions}';
							if(value.priceOvrrRsnCode != null){
								var option = '<option value="'+value.priceOvrrRsnCode+'">';
								var replaceOption = '<option value="'+value.priceOvrrRsnCode+'" selected="selected">';
								priceChangeReasonCodeOptions = priceChangeReasonCodeOptions.replace(option, replaceOption);
							}
							//$.each(priceChangeReasonCodeMap, function(key, value) {
							//    priceChangeReasonCodeOptions += $("<option/>", {value: key, text: value});
							//});
							var priceChangeReasonCodeField = function(rowNum){ 
								return '<select id="priceChangeReason_'+rowNum+'" data-index="'+rowNum+'" class="form-control">'+ 
													priceChangeReasonCodeOptions+ '</select>';}
								
							row += '<tr>'+'<td style="min-width: 10px">'+ i++ + '</td>'
									+ '<td style="min-width: 20px"><a class="delete" id="delete_item_" href="#" data-id='+rowNum+' ><i class="fa fa-trash-o"></i></a></td>'
									+ '<td><span>' + itemId + '</span><div id="itemdesc_'+rowNum+'">'+ value.pluItem.item.itmDesc+ '</div><div id="itemPromName'+rowNum+'" style="display:'+itemPromoDivDisplayStyle+'">'
									+ '<a style="color:red;font-size: 170%" tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Discounts/Promotions for" data-content="'+itemPrmName+'">'
									+ '<s:property value="getText('star.symbol')"/></a></div></td>'
									+ '<td style="text-align:right;">'+ value.itmPrnPrc + '</td>'
									+ '<td><input class="form-control" type="number" onkeypress="return isNumberKey(event)" onInput="checkLength(9,this)" value="'+ returnqty+ '" id="returnQty_'+rowNum+'" min="1" ></td>'
									+ '<td>'+ returnPriceField + '</td>'
									+ '<td>'+ priceChangeReasonCodeField(rowNum) + '</td>'
									+ '<td><div id="discount_'+rowNum+'">'+discountAmt+'</div></td>'
									+ '<td style="min-width: 40px;text-align:right;"class="netPriceClass">'+ value.extnDscLnItm+ '</td>'
									+ '<td><select id="reason_'+rowNum+'" class="form-control">'+ resonCodeOptions+ '</select></td>'
									+ '</tr>';
									
							//to check RestockingFeeServiceitem is there in transaction
							if(itemId == jsonResponse.restockingFeeServiceitemId)
							{
							  hasRestockingFeeServiceitem=true;
							}
							//to check RestockingFeeServiceitem is there in transaction
							if(itemId == jsonResponse.transportationFeeServiceitemId)
							{
							 hasTransportationFeeServiceitem=true;
							}
							

						});
		$('#itemDetailsTable > tbody').append(row);
		$('[data-toggle="popover"]').popover();
		//scroll down while adding items
		var rowpos = $('#itemDetailsTable tr:last').position();
		$('#parent').scrollTop(rowpos.top);

		/* $.each(jsonResponse.orderTran.ordTranSums, function(key, ordTranSum) { */
			var ordTranSum = jsonResponse.orderTran.ordTranSum;
			var currencyFormat = "<s:property value="getText('format.currency')"/>";
			var subtotalWOI = format(currencyFormat,  ordTranSum.dkartSlsTot);
			var totalWOI = format(currencyFormat,  ordTranSum.dkartNetTot);
			var discountsWOI = 0;
			discountsWOI = format(currencyFormat, ordTranSum.dkartDscTot);
			var expensesWOI= format(currencyFormat,  ordTranSum.dkartExpenses);
			$('#subtotalWOI').html(subtotalWOI);
			$('#totalWOI').html(totalWOI);
			$('#expensesWOI').html(expensesWOI);
			$('#discountsWOI').html(discountsWOI);

	/*	}); */
	
     updateButtons(jsonResponse,hasRestockingFeeServiceitem,hasTransportationFeeServiceitem);
     $('#itemIDDesc').focus();
	}

//to handle the expenses buttons
	function updateButtons(jsonResponse,hasRestockingFeeServiceitem,hasTransportationFeeServiceitem)
	{
	   var orderTran = jsonResponse.orderTran;
	   if(orderTran!= null && orderTran!=null)
		{
			var noOfItems = orderTran.ordTranLineItems.length;
			if(noOfItems<1)
			{				
				$('#registerClaimWOI').prop("disabled", true);
			}
			else
			{
			 $('#registerClaimWOI').prop("disabled", false);
			}
			
		}
		if(hasRestockingFeeServiceitem)
		{
		  $('#restckFeeAnch').addClass('disable_a_href');
		}
		else
		{
		  $('#restckFeeAnch').removeClass('disable_a_href');
		}
		if(hasTransportationFeeServiceitem)
		{
		 $('#transportFeeAnch').addClass('disable_a_href');
		}
		else
		{
		 $('#transportFeeAnch').removeClass('disable_a_href');
		}
	
	}
//to delete items from table
		$("body").on("click", "[id^='deleteItemfromTable']", function(e) {
		
		
		 var id = this.getAttribute("data-id");

			$.ajax({
				url : "deleteItem",
				type : "POST",
				data : {
					itemIndex : id
				},
				dataType : "json",
				success : function(jsonResponse) {
					UpdateTableContent(jsonResponse);

				}
			});

		});

//change event on text box is called when the qty is changed and left the focus, to calculate net total

$("body").on("input", "[id*='returnQty_']", function(e)
				{
					var returnQtyIndex = this.id;
		            returnQtyIndex = returnQtyIndex.substring(10,returnQtyIndex.length);
					var returnQty = this.value;
					
				if(returnQty!=null)
				{
					//to restrict -ve,zero values to enter
					if(returnQty<1)
					{
					 $(this).val("");
					 $("#registerClaimWOI").attr("disabled",true);
					}
					if(returnQty.length >8)
					{
					  $("#returnQty_"+returnQtyIndex).focus().val($("#returnQty_"+returnQtyIndex).val().slice(0,8));
					}
					else
					{
						if(returnQty==""){
							returnQty = parseFloat('0.0');
						}
						$.ajax({
							url : "returnQtyUpdate",
							type : "POST",
							data : {
								itemIndex : returnQtyIndex,
								qty : returnQty
							},
							dataType : "json",
							success : function(jsonResponse) {
								UpdateTableContent(jsonResponse);
			                    //$("#returnQty_"+returnQtyIndex).focus().val($("#returnQty_"+returnQtyIndex).val());
			                     $("#returnQty_"+returnQtyIndex).focus();
				                   var value = $("#returnQty_"+returnQtyIndex).val();
				                   $("#returnQty_"+returnQtyIndex).val('');
				                   $("#returnQty_"+returnQtyIndex).val(value); 
							}
						});
			       }			
				}
				else
				{
				  $("#registerClaimWOI").attr("disabled",true);
				}
				
			
				});
				
				
//added by srinivas 
//this event is fired if you click on the add item button with 'ENTER' key
$('#qty').keypress(function (e) {
	 var key = e.which;
	 if(key == 13)  // the enter key code
	  {
		 $('#additembutton').trigger('click');
	    return false;  
	  }
	});  
//srinivas code ended.
				
	$("body").on("change","[id*='returnPrice_']",function(e){
		var managerOverride = <s:property value="managerOverride"/>;
		if($(this).val().length>0 && managerOverride)
			$('#returnPriceChangeOverride').modal('show', $(this));
		else
			$(this).trigger('update');
	});
	
	//change event on text box is called when the price is changed and left the focus, to calculate net total
	
	$("body").on("update", "[id*='returnPrice_']", function(e)
	{
		var returnPriceIndex = this.id;
		returnPriceIndex = returnPriceIndex.substring(12,returnPriceIndex.length);
		var returnPrice = this.value;
		var returnFlag;
		var priceChangeReason = $('#priceChangeReason_'+returnPriceIndex).val();
		if(returnPrice!= null && returnPrice != "")
		{
			returnFlag =1;//return price is given
		}
		else
		{
			returnFlag =0;//return price is not given
			returnPrice = "0";
		}
		if(returnPrice!=null && returnPrice!="")
		{
			//to restrict -ve,zero values to enter
			if(returnPrice<0)
			{
				$(this).val("");
				$("#registerClaimWOI").attr("disabled",true);
			}
			else if(returnPrice.length >8)
			{
				$("#returnPrice_"+returnPriceIndex).focus().val($("#returnPrice_"+returnPriceIndex).val().slice(0,8));
			}
							
			$.ajax({
				url : "returnPriceUpdate",
				type : "POST",
				data : {
					itemIndex : returnPriceIndex,
					returnPrice : returnPrice,
					returnFlag : returnFlag,
					priceChangeReasonCode: priceChangeReason
				},
				dataType : "json",
				success : function(jsonResponse) {
					UpdateTableContent(jsonResponse);
			  		$("#returnPrice_"+returnPriceIndex).focus().val($("#returnPrice_"+returnPriceIndex).val());
				}
			});
			
		}
	/* 	else
				{
				  $("#registerClaimWOI").attr("disabled",true);
				}
		 */
	});
	
	$('#itemIDDesc').keypress(function(e){
		var key = e.which;
		 if(key == 13)  // the enter key code
		  {
			e.preventDefault();  
		  }
	});
	
	$('#password').keypress(function(e){
		var key = e.which;
		 if(key == 13)  // the enter key code
		  {
			$('#managerOverride').trigger('click');
		    return false;  
		  }
	});

	//to restrict -ve,zero values to enter
	$("body").on("input","[id*='returnPrice_']",function(e){
	  if(this.value<1)
	  {
	   $(this).val("").trigger('update');
	  }
	  
	});
	
	// Update when price change reason code field receives update
	$("body").on("change","[id*='priceChangeReason_']", function(e){
		var index = $(this).data('index');
		setTimeout(() => {
			$('#returnPrice_'+index).trigger('update');
		}, 500); 
		//return true;
	});
	//change event on select box is called when the reason code is changed, to calculate net total
	
	$("body").on("change", "[id*='reason_']", function(e)
	{
		
			var resonCodeIndex = this.id;
			resonCodeIndex = resonCodeIndex.substring(7,resonCodeIndex.length);
			var resonCode = this.value;
			
		$.ajax({
			url : "returnReasonCodeUpdate",
			type : "POST",
			data : {
				itemIndex : resonCodeIndex,
				reasonCode : resonCode
			},
			dataType : "json",
			success : function(jsonResponse) {
				UpdateTableContent(jsonResponse);
		
			}
		});
		
	});
	
	//Expenses code
//restockingFee
$('#restockingFeeBtn').click(function(event)
{
event.preventDefault();

$('#restockingFeeSpinner').addClass('fa-spinner fa-spin');

var checkInv = "false";
var price =$('#restockingFeeTxt').val();
var tranType=2;
	$.ajax({
				url : "addItemAction",
				type : "POST",
				data : {
					fee : "RestockingFee" ,
					qty : "1",
					checkInv : checkInv,
					price : price,
					tranType : tranType
					
				},
				dataType : "json",
				success : function(jsonResponse) {
				var actionErrors = jsonResponse.actionErrors;
				if(actionErrors.length>0)
				{
				 $('#AlertDialogModal').find('#warn').html(actionErrors[0]);
				 $('#AlertDialogModal').modal('show');
				 return;
				}
					
						UpdateTableContent(jsonResponse);
					    
				}

			});

 /* var orderId = $('#orderId').val();
               $.ajax({
                        url : "InvoiceSearch",
                        type : "POST",
                        data : {orderId : orderId},
                        dataType : "json",
                        success : function(jsonResponse) {
                              
					    }
                        
                }); */
               
              
               
 $('#restockingFeeSpinner').removeClass('fa-spinner fa-spin');
});
//Transportation fee
$('#transportFeeBtn').click(function(event)
{
event.preventDefault();
$('#transportFeeSpinner').addClass('fa-spinner fa-spin');
var checkInv = "false";
var price =$('#transportFeeTxt').val();
var tranType=2;
	$.ajax({
				url : "addItemAction",
				type : "POST",
				data : {
					fee : "TransportFee" ,
					qty : "1",
					checkInv : checkInv,
					price : price,
					tranType : tranType
					
				},
				dataType : "json",
				success : function(jsonResponse) {
				var actionErrors = jsonResponse.actionErrors;
				if(actionErrors.length>0)
				{				 				 
				 $('#AlertDialogModal').find('#warn').html(actionErrors[0]);
				 $('#AlertDialogModal').modal('show');
				 return;
				}
					
						UpdateTableContent(jsonResponse);
						
					
				}

			});



$('#transportFeeSpinner').removeClass('fa-spinner fa-spin');	
		
});

/*   Enabling the restockingFeeBtn  based on restockingFeeTxt textbox input */
		$('#restockingFeeTxt').on('input', function(e) {
			var restockingFee = $('#restockingFeeTxt').val();
			
			if(restockingFee>0)
			{
			 $('#restockingFeeBtn').prop("disabled", false);
			}
			else
			{
			 $(this).val("");
			 $('#restockingFeeBtn').prop("disabled", true);
			}
		});
		
		/*   Enabling the transportFeeBtn  based on transportFeeTxt textbox input */
		$('#transportFeeTxt').on('input', function(e) {
			var transportFee = $('#transportFeeTxt').val();
			
			if (transportFee > 0 ) {
				$('#transportFeeBtn').prop("disabled", false);
			} else {
			    $(this).val("");
				$('#transportFeeBtn').prop("disabled", true);
			}
		});
		
		//to get focus on first input field on all model popups
		$('.modal').on('shown.bs.modal', function () {
  $(this).find('input:first').focus();
          });
          
          //when enter key is pressed button will be clicked
    $('.modal-content').keypress(function(e){
    if(e.which == 13) {
      $(this).find('button').click();
    }
  });
  
  	function modifyTranSalesAgnt(){
		var agent = document.getElementById("sales_Agent").value;
		if(agent!=null){
		$.ajax({
			url : "modifyTranlvlSlsAgnt",
			type : "POST",
			data : {
				agentId : agent
			},
			dataType : "json",
/* 			success : function(jsonResponse) {
	if(jsonResponse.actionErrors != null && jsonResponse.actionErrors.length !== 0 ){
			$('#noAvlCrdLmt').modal('show');
			}else 
				if (jsonResponse.inventoryAvail) {
					UpdateTableContent(jsonResponse);
				}
		
	} */
		});
		}
	}
  
});
/* delete confirmation --Sharanya */
$(function(){
	 $("body").on('click', "[id^='delete_item_']", function(e) { 
	$("#deleteItemModaltxt").val("");
		     $("#deleteItemModal").modal('show');
		     var id = this.getAttribute("data-id");
		     $("#deleteItemfromTable").attr("data-id",id);
		    });
}); 
  </script>


<script type="text/javascript">
//Saideep scripts
//---------------
//1.Make the menubar active for registerclaim
//2.Extend jQuery to add animation feature using animate.css
//3.Function to refresh return totals
//4.Function to refresh return line items
//5.Handle button click for search with invoice
//6.Init all popovers
//7.Return price manager override
//8.Return quantity min/max validation
//9.Load reason codes
/* 
$(document).ready(function() {
	var returnItems;
	var reasonCodes;
	var newClaim;
	
	//{}
	function createNewClaim()
	{
		$.getJSON("createNewClaim",function(data,event){
			if(jQuery.type(data) === "object")
				newClaim = data;
		});
	}
	createNewClaim();
	
	//{}
	function updateNewClaim()
	{
		var stringy = JSON.stringify(newClaim);
		$.ajax({
			url: 'updateNewClaim',
			data: {claimJson:stringy},
			type: 'post',
			success: function(result, status, xhr) {
					console.log('Claim updated.');
					}
		});
	}
	
    //{1}
	$('#claim').addClass('active');
	$('#registerclaim').addClass('active');
	
	//{2}
	$.fn.extend({
		animateCss: function(animationName) {
			var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
			$(this).addClass('animated ' + animationName).one(animationEnd, function() {
				$(this).removeClass('animated ' + animationName);
			});
      	}
    });
    
    //{3}
	function refreshTotals() {
		subTotal = 0, expenses = 0, netTotal = 0;
		$.each(returnItems, function(i, val) {
			var qty = parseInt($('#returnQty' + i).val());
			var price = parseInt(returnItems[i].itmPrnPrc);
			subTotal += qty * price;
		});
      netTotal = subTotal + expenses;
      $('#subTotal').html(subTotal);
      $('#expenses').html(expenses);
      $('#netTotal').html(netTotal);
      
      $('#subTotal').closest('table').animateCss('fadeIn');
    }
    
    //{4}
    function refreshLineItems() {
      $('#returnItems > tbody').empty();
      if (returnItems.length > 0) {
        var lineItem = "";
        for (var i = 0; i < returnItems.length; i++) {
          lineItem = '<tr><td>'
                  + (i + 1)
                  + '</td><td>'
                  + returnItems[i].itemId
                  + '<br><small>'
                  + returnItems[i].deItmShrtRcpt
                  + '</small></td><td>'
                  + returnItems[i].lineQnt
                  + '</td><td style="">'
                  + returnItems[i].itmPrnPrc
                  + '</td> <td style="">'
                  + (returnItems[i].lineQnt * returnItems[i].itmPrnPrc)
                  + '</td> <td style="max-width: 50px;"><div class="form-group"> <input type="number" id="returnQty'+i+'" min="0" max="'+returnItems[i].lineQnt+'" value="0" style="width: 90px;"> </div></td> <td style=""><div class="form-group"> <input type="number" min="0" id="priceOverride'+i+'" style="width:130px;background-color:#f7ffb7;" data-toggle="popover" data-trigger="focus" data-content="Will require manager override"><button class="hidden" id="overrideButton'+i+'" data-toggle="modal" data-target="#returnPriceChangeOverride" data-id="priceOverride'+i+'">.</button> </div> </td> <td>'+$('#returnReasonCode')[0].outerHTML.replace('returnReasonCode','reasonCode'+i)+'</td> </tr>';

			$('#returnItems > tbody').append(lineItem);
			
        }$('#returnItems > tbody > tr').animateCss('slideInUp'); //fadeIn pulse
      }
      refreshTotals();
    }
    
    //{9}
    function loadReasonCodes(codes)
    {
    	reasonCodes = codes;
    	var select='<option></option>';
    	$.each(reasonCodes, function(index,value){
        	select += '<option value="'+index+'">'+value+'</option>';
        	$('#returnReasonCode').focus();
        });
    	$('#returnReasonCode').html(select);
    		 
    }*/
    
    //{5}
/*     $('#searchWithInvoice').click(function(e) {
      e.preventDefault();
      $('#registerClaim').addClass('disabled');
      var invoiceId = $('#invoiceId');
      invoiceId.parent().removeClass('has-error');
      if (invoiceId.val().length < 1) {
        invoiceId.parent().addClass('has-error');
        invoiceId.focus();
        return;
      } else {
        //ajaxcall
        var ajaxData = 'invoiceID=' + $('#invoiceId').val();
        var ajaxUrl = 'orderSearchByOrder';

        $.ajax({
          url: ajaxUrl,
          data: ajaxData,
          type: 'post',
          //timeout : 15000,
          success: function(result, status, xhr) {
          	if(jQuery.type(result)==="string" && result.search("SDS | Log in")>=0)
          	{
          		$(location).attr("href", "logout");
          		return;
          	}
          	returnItems = result.lineItems;
          	
          	if(result.actionErrors.length>0)
          	{
          		$('#custID').html("");
          		$('#custName').html("");
          		$('#custClass').html("");
          		$('#invoiceID').html("");
          		$('#status').html("");
          		setTimeout(alert(result.actionErrors[0]), 500);
          		loadReasonCodes(result.returnReasonCodes);
          		refreshLineItems();
          		return;
          	}
          	$('#invoiceId').val('');
   			$('#custID').html(result.customerID);
   			console.log(newClaim);
   			newClaim.claimTranSums[0].ordIdCt=result.customerID;
            $('#custName').html(result.customerName);
            $('#custClass').html(result.customerSegment);
            $('#invoiceID').html(result.invoiceID);
            $('#status').html("New");
            loadReasonCodes(result.returnReasonCodes);
            refreshLineItems();
          },
          error: function(jqXHR, exception) {
            alert(exception);
          }
        });
      }
    }); */
    
    //{7}
/* 	$(document).on("change", "[id^=priceOverride]", function() {
      //alert('manager override');
     	// call the hidden button besides manual price change to trigger manager override 
		//$(this).siblings('button').click();
      	$(this).val('');
    });
    
    
    //{}
    var overridePrice;
	$('#returnPriceChangeOverride').on('shown.bs.modal', function (e) {
		$('#managerID').val('');
		$('#managerPwd').val('');
		
		var caller = $(e.relatedTarget);
  		overridePrice = $('#'+caller.data('id')).val();
  		$('#'+caller.data('id')).val('');
	});
	
	//{}
	$('#managerOverride').on("click", function(e){
		e.preventDefault();
		var managerID = $('#managerID');
		var managerPwd = $('#managerPwd');
		
		if(managerID.val().length < 1) {managerID.focus(); return;}
		if(managerPwd.val().length < 1) {managerPwd.focus(); return;}
		
		$.ajax({
          url: 'managerLogin',
          data: {loginid:managerID.val(),password:managerPwd.val()},
          type: 'post',
          //timeout : 15000,
          success: function(result, status, xhr) {
          	if(result.actionErrors.length>0)
          	{
          		managerID.val('');
          		managerID.focus();
          		managerPwd.val('');
          		$('#returnPriceChangeOverride > div > div').animateCss('shake');
          		return;
          	}
          	
          
          },
          error: function(jqXHR, exception) {
            alert(exception);
          }
		});
	
	});
	
	//{}
	$(document).on("change","#tab_1", function(e) {
		var valid= 1, returnItems=0;
		var returnReasonCode = $('#returnReasonCode');
		if(returnReasonCode.val()==null || returnReasonCode.val()=="")
		{
			returnReasonCode.focus();
			valid=0;
		}
		
		$('[id^=returnQty_]').each(function(index){
			//alert(control);
			if(isFinite($(this).val()) && $(this).val()>0)
			{
				returnItems+=$(this).val();
				var reasonCode = $('#reasonCode'+index);
				
				if(reasonCode.val()===undefined || reasonCode.val().length<1)
				{
					reasonCode.focus();
					valid=0;
					return false;
				}
				
			}
			
		});
		
		
		if(valid && returnItems>0)
		{
			$('#registerClaim').removeClass('disabled');
			
			newClaim.rcRtnMr=returnReasonCode.val();
			
			$('[id^=returnQty]').each(function(index){
				if(isFinite($(this).val()) && $(this).val()>0)
				{
					var returnQty = $(this).val();
					var reasonCode = $('#reasonCode'+index);
					newClaim.claimTranLineItems[index].itemId=
					newClaim.claimTranLineItems[index].lineQntRtn=returnQty;
				}
			});
		}
		else
		{
			$('#registerClaim').addClass('disabled');
		}

	
	});
	
  }); */
  $(document).ready(function(){
	    $('[data-toggle="popover"]').popover();   
	});
  	$(function(){
  		$('#claim').addClass('active');
		$('#registerclaim').addClass('active');
  	});
  	
	$(document).change(function(){
		//{6}
		$('[data-toggle="popover"]').popover();
	});
	
	function hideErrors()
	{
		$('.alert.alert-danger').addClass('hidden');
		 $("#mandate").hide();
	}
	//-------------------------------------------------------
	
	$(document).on('change','[id^=returnQuantity_]',function(e){
		var qty = $(this);
		var max = parseFloat(qty.attr('max'));
		var value = parseFloat(qty.val());
		if (value > max) qty.val(max);
		if (value < 0.001 || isNaN(value)) qty.val('0');
		manageFields(this.id);
		updateTotals();
	});
	
	//Krishna
	$(document).on('keyup','[id^=returnQuantity_]',function(e){
		var qty = $(this);
		var max = parseFloat(qty.attr('max'));
		var value = parseFloat(qty.val());
		if (value > max) qty.val(max);
		if (value < 0.001 ) qty.val('0');
		manageFields(this.id);
		updateTotals();
	});
	
	function manageFields(id){
		var netPrice = parseFloat("0.0");
		var soldAt = parseFloat("0.0");
		var qtyVal = parseFloat("0.0");
		if(id != undefined){
			var pointer = id.split("_");
		}
		
		if(pointer[0] == "priceOverride"){
			soldAt = $("#"+id).val();
			if(soldAt == ""){
				soldAt = $("[name='newClaim.claimItemsList["+pointer[1]+"].priceSoldAt']").val();
			}
			qtyVal  = $("#returnQuantity_"+pointer[1]).val();
		}else{
			var overridePrice = $('#priceOverride_'+pointer[1]).val();
			if(overridePrice != ""){
				soldAt  = overridePrice;
			}else
				soldAt = $("[name='newClaim.claimItemsList["+pointer[1]+"].priceSoldAt']").val();
			qtyVal  = $('#'+id).val();			
		}
		netPrice += qtyVal * soldAt;
		var currencyFormat = "<s:property value="getText('format.currency')"/>";
		$('#netPrice_'+pointer[1]).html(format(currencyFormat,netPrice));
		if(qtyVal == 0){
			$('#netPrice_'+pointer[1]).html('0');
			$('#transLevelDisc_'+pointer[1]).html('0');
			$('#itemlevelDisc_'+pointer[1]).html('0');
		}
	}
	//
	$(document).on('change keyup','[id^=priceOverride_]',function(e){
		var prcOvrdId = $(this).attr('id');
		prcOvrdId = prcOvrdId.substring(prcOvrdId.indexOf('_')+1);
		
		var overriddenPrc = $(this);
		if(overriddenPrc.val() == $("[name='newClaim.claimItemsList["+prcOvrdId+"].priceSoldAt']").val() || 
				overriddenPrc.val()==0) overriddenPrc.val('');

		var qty = $('#returnQuantity_'+prcOvrdId).val();
		qty = parseFloat(qty);
		manageFields(this.id);
		if(!(qty<=0)) updateTotals();
	});
	
	$(document).ready(function(){
		updateTotals();
	});
	//
	function updateTotals()
	{
		var currencyFormat = "<s:property value="getText('format.currency')"/>";
		var ret = $('[id^=returnQuantity_]');
		var totalReturnQty = parseFloat("0.00");
		var subTotal=parseFloat("0.00"), expenses=parseFloat("0.00"), netTotal=parseFloat("0.00");;
		var discounts = parseFloat("0.0");
		//var netPrice = parseFloat("0.0");
		var currencyFormat = "<s:property value="getText('format.currency')"/>";
		ret.each(function(index){
			var qty = $(this);
			var price = $("[name='newClaim.claimItemsList["+index+"].unitPrice']").val();
			var soldAt = $("[name='newClaim.claimItemsList["+index+"].priceSoldAt']").val();
			//Check for overrided price
			var overridePrc = $("[name='newClaim.claimItemsList["+index+"].claimPriceRegistered']");
			var tranLevelDiscAmt = $("[name='newClaim.claimItemsList["+index+"].discountAmount']").val();
			var itemLevelDiscAmt = $("[name='newClaim.claimItemsList["+index+"].itemLevelDiscAmt']").val();
			var qtySold = $("[name='newClaim.claimItemsList["+index+"].quantitySold']").val();
			if(qtySold != 0 && !isNaN(tranLevelDiscAmt)){
				tranLevelDiscAmt = tranLevelDiscAmt/qtySold;
				if(qtySold != 1 && qtySold > 1){
					itemLevelDiscAmt = itemLevelDiscAmt/qtySold;
				}
			}
			tranLevelDiscAmt = parseFloat(tranLevelDiscAmt);
			itemLevelDiscAmt = parseFloat(itemLevelDiscAmt);
			if(isNaN(tranLevelDiscAmt) && qtySold == 0){
				tranLevelDiscAmt = parseFloat("0.0");
			}
			if(isNaN(itemLevelDiscAmt)){
				itemLevelDiscAmt = parseFloat("0.0");
			}
			if(overridePrc.val().trim().length > 0 && !isNaN(overridePrc.val()))
			{
				price = overridePrc.val();
				price = Number(price) + tranLevelDiscAmt+itemLevelDiscAmt; 
			}
			qty = parseFloat(qty.val());
			price = parseFloat(price); 
			if(qty > 0)
			{
				totalReturnQty +=qty;
				subTotal += qty * price;
				//netPrice += qty * soldAt;
				discounts = discounts+(itemLevelDiscAmt * qty) + (tranLevelDiscAmt * qty);
			}
			
			$('#transLevelDisc_'+index).html(format(currencyFormat,(tranLevelDiscAmt * qty)));
			//itemLevelDiscAmt = itemLevelDiscAmt * qty
			$('#itemlevelDisc_'+index).html(format(currencyFormat,itemLevelDiscAmt * qty));
			//manageFields(this.id);
		});
		//Calculate expenses
		{}
		//subTotal = subTotal + discounts;		
		//netTotal = subTotal + expenses - discounts;
		netTotal = calculateNetTotal();
		//Fix decimal to 2 places
		{
			var decimalPlaces = 2;
			totalReturnQty.toFixed(decimalPlaces);
			subTotal.toFixed(decimalPlaces);
			expenses.toFixed(decimalPlaces);
			netTotal.toFixed(decimalPlaces);
		}
		
		// UPDATE UI
		var subTotalWI= format(currencyFormat,subTotal);
		var netTotalWI= format(currencyFormat,netTotal);
		var discountsWI = format(currencyFormat,discounts);
		var expensesWI= format(currencyFormat,expenses);
		$('#subTotalWI').html(subTotalWI);
		$('#discountsWI').html(discountsWI);
		$('#expensesWI').html(expensesWI);
		$('#netTotalWI').html(netTotalWI);
		//$('#netPrice_'+index).val(netPrice);
		// Animate totals update
		//$('#subTotalWI').closest('table').transition('clear queue').transition('pulse','500ms');
		
		if(totalReturnQty > 0) $('#registerClaimWI').attr('disabled',false);
		else $('#registerClaimWI').attr('disabled',true);
		
		/* $(function(){
			$('#registerClaimWI').on('click', function(e){
			
			var ajaxData = JSON.stringify({claimJson:newClaimJson});
			alert(ajaxData);
				$.ajax({
					url: 'saveClaim',
					method: 'POST',
					data: ajaxData,
					//contentType: "application/json; charset=utf-8",
					success: function(result, status, xhr){
							alert(result);
					},
					error: function(jqXHR, exception){
						alert(exception);
					}
				});
				
			});
		}); */
	}
	// TO CALCULATE THE NETTOTAL
	function calculateNetTotal(){
		var netPrice = $('[id^=netPrice_]');
		var netTotal = parseFloat("0.0");
		netPrice.each(function(index){
			var netPriceVal = Number($('#'+this.id).text().replace(/,/g , ""));
			netTotal += netPriceVal;
		});
		return netTotal;
	}
</script>

<script type="text/javascript">
  $(function(){
  	$('#sales_Agent').chosen({width: "100%"});
  });
  $(function(){
  	$('#cust_sites').chosen({width: "100%"});
  });
</script>
<!-- validation for input field --Sharanya -->
<script type="text/Javascript">
function checkLength(len,ele){
	  var fieldLength = ele.value.length;
	  if(fieldLength <= len){
	    return true;
	  }
	  else
	  {
	    var str = ele.value;
	    str = str.substring(0, str.length - 1);
	    ele.value = str;
	  }
	}
	
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48  || charCode > 57))
      return false;
   return true;
}

function isNumericKey(evt)
{
	var currencyFormat = "<s:property value="getText('format.currency')"/>";
	var digit = format(currencyFormat,parseFloat('0.0'));
	var allowedDecimals = digit.split('.')[1].length;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	
	var targettedId = evt.target.id;
	var targettedIdCaseInSensitive = targettedId.toLowerCase();
	if(targettedIdCaseInSensitive.includes('qty') || targettedIdCaseInSensitive.includes('quantity')){
		if(charCode == 46){
			evt.preventDefault(); // NOT TO ALLOW DOT FOR QUANTITY FIELD
		}
	}
	var val = $('#'+targettedId).val();
	var dot = val.indexOf('.');
	var noOfDecimals = parseFloat('0.0');
	if(val != undefined && dot >= 1){
		noOfDecimals = val.split('.')[1].length;
	}
	
	if ( charCode !== 8){
		if(allowedDecimals == noOfDecimals){
			return false;
		}}
	
   if (charCode > 31 && (charCode < 46  || charCode > 57))
      return false;
	
	if(dot >= 1 && charCode == 46){
		return false;
	}
   return true;
}

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

		<s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">



			<!-- Main content -->

			<section class="content">
				<small class="pull-right"
					style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><b><s:property value="getText('newclaim.help_text')"/> </b> </small>
				<!-- Your Page Content Here -->
				<div class="row" style="margin: 0%;">
					<div class="nav-tabs-custom">
						<ul class="nav nav-pills">

							<li class="active" id="tab1"><a data-toggle="tab"
								href="#tab_1" aria-expanded="true" hidden><s:property value="getText('with.invoice')"/> </a></li>
							<s:if test="%{newClaim==null||newClaim.claimItemsList==null}">
								<li class=""><a id="claimWithoutInvoice" data-toggle="tab" href="#tab_2"
									aria-expanded="false"><s:property value="getText('without.invoice')"/> </a></li>
							</s:if>
							<s:else>
								<li class="disabled"><a data-toggle="" href="javascript:void(0)"
									aria-expanded="false"><s:property value="getText('without.invoice')"/></a></li>
							</s:else>
						</ul>
						<div class="tab-content">
							<div id="tab_1" class="tab-pane active">
								<div class="well">
									<div class="row">
										<form action="invoiceSearchByOrderOrInvoice" method="post"
											theme="bootstrap">
											<div class="col-md-2"></div>
											<div class="col-md-6 col-xs-12">

												<div class="form-group">
													<input type="text" name="invoiceOrOrderID"
														placeholder="Invoice No/Order ID" id="invoiceId"
														autocomplete="off" autofocus="autofocus"
														class="form-control" required="required"
														oninput="hideErrors()">
													<s:actionerror theme="bootstrap" />
												</div>
 <%--  <div class="col-xs-12 col-sm-7">
								<s:actionerror  id="custError" theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss" />
								<s:actionmessage id="custMessage" theme="bootstrap" 
										cssClass="col-xs-12 col-sm-7 alert-dismissible" onclick="dismiss" />
							</div>  --%>
											</div>
											<div class=" col-md-2 ">
												<button type="submit" class="btn btn-block  pull-right "
													id="searchWithInvoice"><s:property value="getText('search.button')"/></button>
											</div>
										</form>
									</div>
								</div>
								<form id ="saveClaim" action="saveClaim" method="post" theme="bootstrap">
									<div class="row">
										<div class="col-sm-4 invoice-col">
											<table style="width: 100%;" class="table">
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('customer.id')"/>:</td>
													<td><b id="custID"><s:property
																value="newClaim.customerID" /></b></td>
													<s:hidden name="newClaim.customerID"
														value="%{newClaim.customerID}" />
												</tr>
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('customer.name')"/>:</td>
													<td><b id="custName"><s:property
																value="newClaim.customerName" /></b></td>
													<s:hidden name="newClaim.customerName"
														value="%{newClaim.customerName}" />
												</tr>
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('claim.pickup.addrss')"/>:</td>
													<td><b id="custAddrss"><s:property
																value="newClaim.site_address" /></b></td>
													<s:hidden name="newClaim.site_address"
														value="%{newClaim.site_address}" />
													<s:hidden name="newClaim.siteId"
														value="%{newClaim.siteId}" />
												</tr>

											</table>
										</div>
										<!-- /.col -->
										<div class="col-sm-4 invoice-col">
											<table style="width: 100%;" class="table">
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('status.label')"/>:</td>
													<td><b id="status"><s:if
																test="newClaim.claimStatus==0"><s:property value="getText('new_status')"/></s:if></b></td>
													<s:hidden name="newClaim.claimStatus"
														value="%{newClaim.claimStatus}" />
												</tr>
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('customer.segment')"/>:</td>
													<td><b id="custClass"><s:property
																value="newClaim.customerSegment" /></b></td>
													<s:hidden name="newClaim.customerSegment"
														value="%{newClaim.customerSegment}" />
												</tr>
											</table>
										</div>
										<!-- /.col -->
										<div class="col-sm-4 invoice-col">
											<table style="width: 100%;" class="table">
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('originalinv.no')"/>:</td>
													<td><b id="invoiceID"><s:property
																value="newClaim.originalInvoiceID" /></b></td>
													<s:hidden name="newClaim.originalInvoiceID"
														value="%{newClaim.originalInvoiceID}" />
													<s:hidden name="newClaim.originalOrderID"
														value="%{newClaim.originalOrderID}" />
												</tr>
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('sales.agent')"/>
														:</td>
													<td><b><s:property value="newClaim.salesAgent" /></b></td>
													<s:hidden name="newClaim.salesAgentId"
														value="%{newClaim.salesAgentId}" />
												</tr>
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('reason.code')"/>:</td>
													<td><s:select
															list="%{(returnReasonCodesMap==null)?{}:returnReasonCodesMap}"
															id="returnReasonCode" name="newClaim.claimReasonCode"
															cssClass="input-xs" theme="bootstrap">
														</s:select></td>
												</tr>

											</table>
										</div>
									</div>
									<div class="row ">

										<div class="col-md-12 ">
											<div class="table-responsive">
												<!-- Krishna: -->
												<table style="width: 100%; margin: 0px;" class="bg-blue">
													<tbody>
														<tr>
															<td>
																<div class="dropdown pull-right"
																	style="margin: 5px; margin-right: 10px;">
																	<s:property value="getText('claimoptions.head')"/> <i class="fa  fa-bars  dropdown-toggle"
																		type="button" data-toggle="dropdown"></i>
																	<ul
																		style="margin-left: 0px; background-color: #656a6b;"
																		class="dropdown-menu ">
																		<li><a id="restckFeeAnch" href="#"
																				data-toggle="modal" data-target="#restckFeeModal"
																				class="disable_a_href"><s:property value="getText('claimoptions.head1')"/> </a></li>
																			<li><a id="transportFeeAnch" href="#"
																				data-toggle="modal" data-target="#transportFeeModal"
																				class="disable_a_href"><s:property value="getText('claimoptions.head2')"/></a></li>
																	</ul>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
												<!--  -->
												<table class="table table-striped" id="returnItems">
													<thead>
														<tr style="background-color: #ADC2EE;">
															<th style="min-width: 10px"><s:property value="getText('table.head.SNo')"/></th>
															<th style="min-width: 100px"><s:property value="getText('table.head.item')"/></th>
															<th style="min-width: 90px;text-align:right;"><s:property value="getText('available.qty.return')"/></th>
															<th style="min-width: 50px;text-align:right;"><s:property value="getText('table.head.unitprice')"/></th>
															<th style="min-width: 50px;text-align:right;"><s:property value="getText('sold.at')"/></th>
															<th style="min-width: 70px;text-align:right;"><s:property value="getText('discount.applied')"/></th>
															<th style="min-width: 80px;text-align:right;"><s:property value="getText('net.price')"/></th>
															<th style="min-width: 50px;"><s:property value="getText('qty.return')"/></th>
															<th style="min-width: 100px;"><s:property value="getText('return.price')"/></th>
															<th style="min-width: 60px;">Reason for Price Change</th>
															<th style="min-width: 100px"><s:property value="getText('return.reasoncode')"/></th>
														</tr>
													</thead>
													<tbody>
														<s:if
															test="%{newClaim.claimItemsList!=null && newClaim.claimItemsList.size()>0}">
															<s:iterator value="newClaim.claimItemsList"
																status="status">
																<s:set var="index" value="#status.index"></s:set>
																<tr>
																	<td><s:property value="#status.count" /></td>
																	<td><div id="itemID_%{#index}">
																			<s:property value="itemID" />
																		</div>
																		<small><s:property value="itemDescription" /></small>
																		<div id="discDsc_<s:property value="%{#index}"/>">
																			<s:if test="%{dscList != null}">
																				<a style="color:red;font-size: 170%" tabindex="0" role="button" data-toggle="popover" data-container="body" data-html="true" data-trigger="focus" 
																					title="Discounts/Promotions for"  
																					data-content="
																						<s:if test="%{tranDiscDsc != null && dscList.get(0).prmType == 0}"><div>T_<s:property value="tranDiscDsc"/></div></s:if> 
																						<s:if test="%{itemDiscDsc != null}"><div>I_<s:property value="itemDiscDsc"/></div></s:if>
																						<s:iterator value="dscList" status="disc1">
																					 		<s:if test="%{prmDesc != null}"><div>P_<s:property value="prmDesc"/></div></s:if>
																					 	</s:iterator>">
																					<s:property value="getText('star.symbol')"/>
																				</a>
																			</s:if>
																		</div>
																	</td>	
																	<td style="text-align:right;"><div id="quantitySold_<s:property value="%{#index}"/>">
																			<s:property value="quantitySold" />
																		</div></td>
																	<td style="text-align:right;"><div id="unitPrice_%{#index}">
																			<s:property value="%{getText('format.currency.args',{unitPrice})}"/>
																		</div></td>
																	<td style="text-align:right;"><div id="priceSoldAt_%{#index}">
																			<s:property value="%{getText('format.currency.args',{priceSoldAt})}"/>
																		</div></td>
																	<td style="min-width: 40px;text-align:right;" id="discountApplied_<s:property value="%{#index}"/>">
																			<s:if test="%{dscList != null}">
																				<s:iterator value="dscList" status="disc">
																					<s:if test="%{tyDsc == 1}"><div id="transDiscDiv_<s:property value="%{#index}"/>">T_<span id="transLevelDisc_<s:property value="%{#index}"/>"><s:property value="%{getText('format.currency.args',{discountAmount})}"/></span><%-- <span>(<s:property value="getText('global.currency')" />)</span> --%></div></s:if>
																					<s:if test="%{tyDsc == 0}"><div id="itemDiscDiv_<s:property value="%{#index}"/>">I_<span id="itemlevelDisc_<s:property value="%{#index}"/>"><s:property value="%{getText('format.currency.args',{itemLevelDiscAmt})}"/></span><%-- <span>(<s:property value="getText('global.currency')" />)</span> --%></div></s:if>
																				</s:iterator>
																			</s:if>
																			<s:else>
																				<span><s:property value="%{getText('format.currency.args',{0.00})}"/></span>
																		</s:else>
																	</td>
																	<%-- <td style="text-align:right;"><s:property value="netPrice" /></td> --%>																																		
																	<td style="text-align:right;">
																		<span id="netPrice_<s:property value="%{#index}"/>">
																			<s:property value="%{getText('format.currency.args',{netPrice})}"/>
																		</span>
																	</td>
																	<td style="max-width: 50px;">
																		<!-- <input type="number" id="returnQty%{#status.index}" min="0" -->
																		<!-- max="%{quantitySold}" value="0" -->
																		<!-- style="width: 90px;"> --> 
																		<s:textfield name="newClaim.claimItemsList[%{#index}].claimQuantityRegistered"
																			id="returnQuantity_%{#status.index}" type="number" min="0" max="%{quantitySold}" 
																			style="width: 60px;text-align:right;size=1;" onkeypress="return isNumericKey(event);" theme="simple" class="form-control"></s:textfield>
																	</td>
																	<td>
																	<s:if test="%{managerOverride==true}">
																		<s:textfield class="form-control" size="1;" type="number" min="0"
																			id="priceOverride_%{#index}"	name="newClaim.claimItemsList[%{#index}].claimPriceRegistered"
																			style="width: 100px;background-color: #f7ffb7;text-align:right;" theme="simple" 
																			data-toggle="popover" data-trigger="focus" data-content="Will require manager override"
																			onchange="if($(this).val().length>0)$('#returnPriceChangeOverride').modal('show', $(this))"
																			data-index="%{#index}" value="%{claimPriceRegistered}" onkeypress="return isNumericKey(event);" oninput="checkLength(14,this);">
																		</s:textfield>
																	</s:if>
																	<s:else>
																		<s:textfield class="form-control" size="1;" type="number" min="0" 
																			id="priceOverride_%{#index}"	name="newClaim.claimItemsList[%{#index}].claimPriceRegistered"
																			style="width: 100px;background-color: #f7ffb7;text-align:right;" theme="simple"
																			value="%{claimPriceRegistered}" onkeypress="return isNumericKey(event);" oninput="checkLength(14,this);">
																		</s:textfield>
																	</s:else>
																	</td>
																	<td>
																		<s:select theme="simple" class="form-control" style="width: 100px;" value=""
																			list="priceChangeReasonCodeMap" id="priceChangeReasonCode_%{#index}"
																			name="newClaim.claimItemsList[%{#index}].priceChangeReasonCode" >
																		</s:select>
																	</td>
																	<td><s:select class="form-control input-xs" theme="simple"
																			list="%{(returnReasonCodesMap==null)?{}:returnReasonCodesMap}"
																			name="newClaim.claimItemsList[%{#index}].claimReasonCode"
																			id="reasonCode_%{#index}" >
																		</s:select>
																	</td>
																</tr>
															</s:iterator>
														</s:if>
													</tbody>
												</table>
												<div class="container hidden">
													<s:if
														test="%{newClaim.claimItemsList!=null && newClaim.claimItemsList.size()>0}">
														<s:iterator value="newClaim.claimItemsList"
															status="status">
															<s:set var="index" value="#status.index"></s:set>
															<s:hidden
																name="newClaim.claimItemsList[%{#index}].itemID"
																value="%{itemID}" />
															<s:hidden
																name="newClaim.claimItemsList[%{#index}].itemDescription"
																value="%{itemDescription}" />
															<s:hidden
																name="newClaim.claimItemsList[%{#index}].quantitySold"
																value="%{quantitySold}" />
															<s:hidden
																name="newClaim.claimItemsList[%{#index}].unitPrice"
																value="%{unitPrice}" />
															<s:hidden
																name="newClaim.claimItemsList[%{#index}].priceSoldAt"
																value="%{priceSoldAt}" />
															<s:hidden 
																name="newClaim.claimItemsList[%{#index}].discountAmount"
																value="%{discountAmount}"></s:hidden>
															<s:hidden 
																name="newClaim.claimItemsList[%{#index}].discountPercent"
																value="%{discountPercent}"></s:hidden>
															<s:hidden 
																name="newClaim.claimItemsList[%{#index}].itemLevelDiscAmt"
																value="%{itemLevelDiscAmt}"></s:hidden>
															<s:hidden 
																name="newClaim.claimItemsList[%{#index}].itemLevelDiscPer"
																value="%{itemLevelDiscPer}"></s:hidden>
															<s:hidden 
																name="newClaim.claimItemsList[%{#index}].tranDiscDsc"
																value="%{tranDiscDsc}"></s:hidden>
															<s:hidden 
																name="newClaim.claimItemsList[%{#index}].itemDiscDsc"
																value="%{itemDiscDsc}"></s:hidden>
															<s:hidden
																name="newClaim.claimItemsList[%{#index}].netPrice"
																value="%{netPrice}" />
															<s:iterator value="dscList" status="dsc">
																<s:set var="index1" value="#dsc.index"></s:set>
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].dscAmt" 
																	value="%{dscAmt}"/>
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].dscPer" 
																	value="%{dscPer}"/>
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].unitDscAmt" 
																	value="%{unitDscAmt}"/>	
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].prmCmpDtlid" 
																	value="%{prmCmpDtlid}"/>
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].tyDsc" 
																	value="%{tyDsc}"/>
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].prmDesc" 
																	value="%{prmDesc}"/>
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].discReasonCode" 
																	value="%{discReasonCode}"/>
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].prmCmpId" 
																	value="%{prmCmpId}"/>
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].prmId" 
																	value="%{prmId}"/>
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].prmType" 
																	value="%{prmType}"/>
																<s:hidden 
																	name="newClaim.claimItemsList[%{#index}].dscList[%{#index1}].srcTrgList" 
																	value="%{srcTrgList}"/>
															</s:iterator>
														</s:iterator>
													</s:if>
												</div>
											</div>
										</div>
									</div>
									<div class="row">

										<div class="col-md-4 pull-right" style="margin-top: 10px;">
											<div class="box ">

												<div class="box-body no-padding">
													<table class="table table-bordered">
														<tbody>
															<tr style="border: 2px solid #e4e4e4;">
																<th  
																	style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property value="getText('subtotal.label')"/></th>
																<td style="text-align:right;" "color: #000; border: 2px solid #e4e4e4;"><s:property
																		value="getText('global.currency')" /><s:property value="%{getText('format.currency.args',{subTotal})}"/>
																		&nbsp;<label
																	id="subTotalWI"></label>
																<!-- <div class="dropdown pull-right"
																		style="margin-right: 10px;">


																		<i class="fa  fa-plus  dropdown-toggle" type="button"
																			data-toggle="dropdown"></i>
																		<ul
																			style="margin-left: 0px; background-color: #656a6b;"
																			class="dropdown-menu ">
																			<li><a id="restckFeeAnchWI" href="#"
																				data-toggle="modal" data-target="#restckFeeModalWI"
																				class="disable_a_href">Restocking Fee</a></li>
																			<li><a id="transportFeeAnchWI" href="#"
																				data-toggle="modal"
																				data-target="#transportFeeModalWI"
																				class="disable_a_href">Transportation fee</a></li>

																		</ul>

																	</div> -->
																</td>

															</tr>
															<tr style="border: 2px solid #e4e4e4;">
																<th
																	style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property value="getText('discounts.label')"/></th>
																<td style="text-align:right;" "color: #000; border: 2px solid #e4e4e4;"><s:property
																		value="getText('global.currency')" /><s:property value="%{getText('format.currency.args',{discounts})}"/>
																		&nbsp;<label
																	id="discountsWI"></label></td>
															</tr>
															<tr style="border: 2px solid #e4e4e4;">
																<th
																	style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property value="getText('expenses.label')"/></th>
																<td style="text-align:right;" "color: #000; border: 2px solid #e4e4e4;"><s:property value="%{getText('format.currency.args',{expenses})}"/>
																		&nbsp;<label
																	id="expensesWI"></label></td>
															</tr>

															<tr
																style="border: 2px solid #e4e4e4; background-color: #6e7071; color: white;">
																<th
																	style="width: 40%; font-weight: 900; border: 2px solid #e4e4e4; font-size: 17px;"><s:property value="getText('net.total')"/></th>
																<td style="text-align:right;" "border: 2px solid #e4e4e4; font-size: 20px;"><s:property
																		value="getText('global.currency')" /><s:property value="%{getText('format.currency.args',{netTotal})}"/>&nbsp;<label
																	id="netTotalWI"></label></td>
															</tr>
															<tr>
															</tr>
														</tbody>
													</table>
												</div>
											</div>

										</div>
									</div>
									<div class="row">
										<div class="col-md-4 col-xs-12 pull-right">
											<button class="btn btn-block"
												style="color: #ffffff; width: 150px; margin-top: 5px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); border: 1px solid; color: #fff; display: inline-block;"
												type="button" id="registerClaimWI" disabled><s:property value="getText('register.claim.button')"/>
												</button>
											<s:if test="%{newClaim==null||newClaim.claimItemsList==null}">
												<a href="cancelRegisterClaim">
													<button class="btn btn-block "
														style="color: #ffffff; width: 150px; margin-top: 5px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); border: 1px solid; color: #fff; display: inline-block;"
														id="cancelClaimWI" type="button" disabled><s:property value="getText('cancel.claim.button')"/>
														</button>
												</a>
											</s:if>
											<s:else>
												<a href="cancelRegisterClaim">
													<button class="btn btn-block "
														style="color: #ffffff; width: 150px; margin-top: 5px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); border: 1px solid; color: #fff; display: inline-block;"
														id="cancelClaimWI" type="button"><s:property value="getText('cancel.claim.button')"/></button>
												</a>
											</s:else>
										</div>
									</div>
								</form>
							</div>
							<!-- /.tab-pane -->
							<div id="tab_2" class="tab-pane ">
								
									<div class="well">
										<div class="row">
											<div class="col-md-2  "></div>
											     <s:if test="%{RegClaimPerWthOutInv}">
												<div class="col-md-6 col-xs-12">
													<div class="form-group">
														<input type="text" placeholder=" Customer Id/Name"
														required="required"
															id="custInfo" name="custInfo" class="form-control"
															oninput="hideErrors()"/>
													</div><span id="mandate"><font color="red"><s:property value="getText('claimWOI.custsearch.mandater.error')"/></font></span>
														</div>
												</s:if>
											<s:else>
												<div class="col-md-6 col-xs-12">
													<div class="form-group">
														<input type="text" placeholder=" Customer Id/Name"
															title="Use parameter 'EnableClaimWithOutInvoice' to enable this feature"
															id="custInfo" name="custInfo" class="form-control"
															oninput="hideErrors()" disabled/>
													</div>
													</div>
											</s:else>
											<div class=" col-md-2 ">
											  
												<button class="btn btn-block  pull-right " type="submit"
													id="custsearch" type="button">
													<s:property value="getText('search.button')"/> &nbsp;<span id="custsearchSpinner" class="fa"
														aria-hidden="true"></span>
												</button>
												 <s:hidden name="wildcardSearch" value="true"></s:hidden>
											</div>



										</div>
									</div>
									<div id="noCustError" class="alert alert-danger hidden"
										role="alert" style="color: #fff;"><s:property value="getText('message_15')"/>
										</div>

									<div class="well well-sm hidden" id="multipleCust">
							<div class="container-fluid" style="background-color:#ADC2EE;margin-right: 16px; margin-bottom:2px;">
							<div class="row">
								<div class="col-xs-4"><h4><s:property value="getText('customer.name')"/></h4></div>
		  						<div class="col-xs-3"><h4><s:property value="getText('customer.id')"/></h4></div>
		  						<div class="col-xs-3"><h4><s:property value="getText('phone.no')"/></h4></div>
		  						<div class="col-xs-2"><h4><s:property value="getText('status.label')"/></h4></div>
							</div></div>
							<div class="list-group pre-scrollable" id="customer-list-group"
								style="cursor: pointer;max-height: 260px;"></div>
		
						</div>
									<button type="button" class="btn btn-primary pull-right hidden"
										id="customerSelected">
										<s:property value="getText('next.button')"/> &nbsp;<span id="customerSelectedSpinner" class="fa"
											aria-hidden="true"></span>
									</button>
				<form id ="saveClaimWOI" action="registerClaim" method="POST">
									<div class="row">
										<div class="col-sm-4 invoice-col">
											<table style="width: 100%;" class="table">
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('customer.id')"/>
														:</td>
													<td><b id="custId" /></td>
												</tr>
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('customer.name')"/>
														:</td>
													<td><b id="customerName" /></td>
												</tr>
												<tr>
													<td  style="text-align: right; width: 50%;"><s:property
																value="getText('claim.pickup.addrss') "/>:</td>
													<td><s:select theme="bootstrap" id="cust_sites" name="cust_site"
															list="%{(CusSitesList!=null?CusSitesList:{})}"
															class="form-control" style="font-weight: bold; font-size: 12px; color: black;margin: 1px;">
														</s:select></td>
												</tr>

											</table>
										</div>
										<!-- /.col -->
										<div class="col-sm-4 invoice-col">
											<table style="width: 100%;" class="table">
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('status.label')"/>:</td>
													<td><b id="claimStatus" /></td>
												</tr>
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('customer.segment')"/>:</td>
													<td><b id="customerSeg" /></td>
												</tr>
												<tr>
													<td  style="text-align: right; width: 50%;"><s:property
																value="getText('sales.agent')" />:</td>
													<td><s:select theme="bootstrap" id="sales_Agent" name="sales_Agent"
													list="%{(SalesAgentList!=null?SalesAgentList:{})}"
															class="form-control" style="font-weight: bold; font-size: 12px; color: black;margin: 1px;"														
															onchange="modifyTranSalesAgnt()" value="cust.orderTran.getEmId()">
														</s:select></td>
												</tr>
												
											</table>
										</div>
										<!-- /.col -->
										<div class="col-sm-4 invoice-col">
											<table style="width: 100%;" class="table">

												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('available.credit.limit')"/>
														:</td>
													<td><b><s:text name="global.currency" /></b> <b id="custAvailCreditLmt" /></td>
												</tr>
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('balance.due')"/>
														:</td>
													<td><b><s:text name="global.currency" /></b> <b id="custBalDue" /></td>
												</tr>
												<tr>
													<td style="text-align: right; width: 50%;"><s:property value="getText('reason.code')"/>
														:</td>
													<td><select id="globalReasonCode"
														name="globalReasonCode" class="form-control"
														style="font-weight: bold; font-size: 12px; color: black">

													</select></td>
												</tr>

											</table>
										</div>


									</div>
									<%--                <div class="row ">
        <div class="col-md-12 ">
            <table style="width:100%;margin:0px; " class="bg-blue">
                <tbody>
                    <tr>
                    <td style="width:40%;">
                        <input style="margin-left:5px;padding-left: 5px;" class="form-control" placeholder="Enter Item/Scan Item" type="text">
                    </td>
                    <td style="width:1%;"></td>
                    <td style="width:80px;">
                        <input style="width:80px;padding-left: 5px;" class="form-control " placeholder="QTY" type="text">
                    </td>
                    <td style="width:1%;"></td>
                    <td style="">
                      <button type="submit" style="width: 100px; margin: 2px; background: #666; background: -webkit-linear-gradient(#717171, #262626 20%, #717171); border: 1px solid rgba(255, 255, 255, 0.77); font: normal 100 15px/1 sans-serif; background: linear-gradient(#717171, #262626 20%, #717171); color: #fff; display: inline-block;"
						class="btn  " id="additembutton" disabled="true">
						Add &nbsp;<span id="spinner" class="fa" aria-hidden="true"></span>
					</button>
                    </td>
                        
                    <td>
                    

                    </td>
                    </tr>
                    
                </tbody>
                </table>
        <div class="table-responsive">
        
        <table class="table table-striped">
        <thead>  
            <tr style="width:100%;">
                
            </tr>
                <tr style="background-color:#ADC2EE;" >
                <th style="min-width: 10px">#</th>
                <th style="max-width: 35px; position: relative; top: 0px; background-color: rgb(173, 194, 238);">Del</th>    
                <th style="min-width: 100px">Item</th>
                <th style="min-width: 80px">Unit Price</th>
                
                <th style="max-width: 90px;">Return Qty</th>
                <th style="min-width: 120px">Return Price</th>
                <th style="min-width: 120px;">Net Price</th>    
                    
                <th style="min-width: 100px">Reason Code</th>
                  
                 
                </tr>
        </thead>
        <tbody>
        
        <tr>
                <td >1</td>
                <td><a href="#"><i class="fa fa-trash-o"></i></a></td>
                <td>10000007<br><small>Red Bull</small></td>
                <td>7.00</td>
                
                <td style="max-width:50px;"><div class="form-group"><input type="text" style="width:90px;"></div></td>
                <td style=""><div class="form-group"><a href="#manager_override"><input type="text" style="width:130px;background-color:#f7ffb7;"></a></div></td>
                <td style="">QAR 49,000</td>
                <td ><select class="form-control">
                    <option>option 1</option>
                    <option>option 2</option>
                    <option>option 3</option>
                    <option>option 4</option>
                    <option>option 5</option>
                  </select></td>
                
                  
        </tr> 
        
        </tbody>
        </table>    
        </div>
        
        </div>
        </div>
                   --%>
									<div class="row">
										<div class="col-md-12">
											<div class="box bg-blue"
												style="margin-bottom: 5px; margin-bottom: 5px;">
												<table style="width: 100%; margin: 1px;">
													<tbody>
														<tr>
															<td style="width: 40%;"><input
																style="margin-left: 5px;" class="form-control"
																id="itemIDDesc" placeholder="Enter Item/Scan Item"
																type="text" disabled="true"
																pattern="[a-zA-Z][a-zA-Z0-9\s]*"> <!--  <div id="load" style="display:none"><img src="ajax-loader.gif"/></div> -->
															</td>
															<td style="width: 1%;"></td>
															<td style="width: 80px;"><input style="width: 80px;"
																class="form-control " placeholder="QTY" type="number"
																id="qty" name="qty" disabled="true" min="1" step="1" onkeypress='return isNumberKey(event);' onInput="checkLength(9,this)"></td>
															<td style="width: 1%;"></td>
															<td style="">
																<shiro:hasPermission name="addItemAction">
																	<button type="submit"
																		style="width: 100px; margin: 2px; background: #666; background: -webkit-linear-gradient(#717171, #262626 20%, #717171); border: 1px solid rgba(255, 255, 255, 0.77); font: normal 100 15px/1 sans-serif; background: linear-gradient(#717171, #262626 20%, #717171); color: #fff; display: inline-block;"
																		class="btn  " id="additembutton" disabled="true">
																		<s:property value="getText('add.button')"/> &nbsp;<span id="spinner" class="fa"
																			aria-hidden="true"></span>
																	</button>
																</shiro:hasPermission>
																<shiro:lacksPermission name="addItemAction">
																	<button type="button"
																		style="width: 100px; margin: 2px; background: #666; background: -webkit-linear-gradient(#717171, #262626 20%, #717171); border: 1px solid rgba(255, 255, 255, 0.77); font: normal 100 15px/1 sans-serif; background: linear-gradient(#717171, #262626 20%, #717171); color: #fff; display: inline-block;"
																		class="btn  " disabled="true" title="please enable the 'addItemAction' permission">
																		<s:property value="getText('add.button')"/> &nbsp;<span id="spinner" class="fa"
																			aria-hidden="true"></span>	
																	</button>
																</shiro:lacksPermission>
															</td>



															<td>


															<!-- 	<form action="ediUploadFileSelectForClaim" 
																	id="customerValidate" method="post">-->
																	<%-- <input type="hidden" name="customerID"
																		value="<s:property value="customerId"/>"> --%>
																	<button class="btn btn-block "
																		style="color: #ffffff; width: 140px; margin-top: 1px; margin-bottom: 1px; background: #3d85c6; 
																		background: -webkit-linear-gradient(#1770c1, #073763 20%, #1770c1); 
																		background: linear-gradient(#1770c1, #073763 20%, #1770c1); border: 1px solid; 
																		color: #fff; display: inline-block;"
										                             
																		
																		type="button" disabled id ="ediButton" data-toggle="modal" data-target="#myModal" >

																		<s:property value="%{getText('edi.claim')}" />
																	</button>
																	
																<!-- </form> -->

															</td>
															
	<script type="text/javascript">
	function customerVal() {
	 if(document.getElementById("custID").value==="") { 
            document.getElementById('ediButton').disabled = true; 
        } else { 
            document.getElementById('ediButton').disabled = false;
        }
    }
	</script>			
															
							
															
															
															
               					
															<td>
																<div class="dropdown pull-right"
																	style="margin: 5px; margin-right: 10px;">
																	<s:property value="getText('claimoptions.head')"/> <i class="fa  fa-bars  dropdown-toggle"
																		type="button" data-toggle="dropdown"></i>
																	<ul
																		style="margin-left: 0px; background-color: #656a6b;"
																		class="dropdown-menu ">
				
																		<li><a id="restckFeeAnch" href="#"
																				data-toggle="modal" data-target="#restckFeeModal"
																				class="disable_a_href"><s:property value="getText('claimoptions.head1')"/> </a></li>
																			<li><a id="transportFeeAnch" href="#"
																				data-toggle="modal" data-target="#transportFeeModal"
																				class="disable_a_href"><s:property value="getText('claimoptions.head2')"/></a></li>
																	</ul>
																</div></td>
                                                            
														</tr>

													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">

										<!-- Item table -->
										<div class="col-xs-12">
											<div class="box box-default" style="margin-top: 0px;">

												<!-- /.box-header -->
												<div id="parent" class="box-body no-padding">
													<table id="itemDetailsTable" class=" table table-striped">
														<thead>

															<tr style="background-color: #ADC2EE;">
																<th style="max-width: 20px"><s:property value="getText('table.head.SNo')"/></th>
																<th style="max-width: 35px; position: relative; top: 0px;"><s:property value="getText('table.head.delete')"/></th>
																<th style="min-width: 100px"><s:property value="getText('table.head.item')"/></th>
																<th style="max-width: 60px;text-align:right;"><s:property value="getText('table.head.unitprice')"/></th>
																<th style="max-width: 40px"><s:property value="getText('return.Qty')"/></th>
																<th style="max-width: 90px;"><s:property value="getText('return.price')"/></th>
																<th style="max-width: 90px;">Reason for Price Change</th>
																<th style="max-width: 90px;"><s:property value="getText('table.head.disc')"/></th>
																<th style="min-width: 120px;text-align:right;"><s:property value="getText('net.price')"/></th>
																<th style="min-width: 90px"><s:property value="getText('reason.code')"/></th>


															</tr>
														</thead>
														<tbody>


														</tbody>
													</table>
												</div>
												<!-- /.box-body -->
											</div>
										</div>
									</div>


									<div class="row">

										<div class="col-md-4 pull-right" style="margin-top: 10px;">
											<div class="box ">

												<div class="box-body no-padding">
													<table class="table table-bordered">
														<tbody>
															<tr style="border: 2px solid #e4e4e4;">
																<th
																	style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property value="getText('subtotal.label')"/> </th>
																<td style="text-align:right;" "color: #000; border: 2px solid #e4e4e4;"> <s:text name="global.currency"/> <label
																	id="subtotalWOI">  </label>
																<%-- <div class="dropdown pull-right"
																		style="margin-right: 10px;">


																		<i class="fa  fa-plus  dropdown-toggle" type="button"
																			data-toggle="dropdown"></i>
																		<ul
																			style="margin-left: 0px; background-color: #656a6b;"
																			class="dropdown-menu ">
																			<li><a id="restckFeeAnch" href="#"
																				data-toggle="modal" data-target="#restckFeeModal"
																				class="disable_a_href"><s:property value="getText('claimoptions.head1')"/> </a></li>
																			<li><a id="transportFeeAnch" href="#"
																				data-toggle="modal" data-target="#transportFeeModal"
																				class="disable_a_href"><s:property value="getText('claimoptions.head2')"/></a></li>

																		</ul>

																	</div> --%></td>

															</tr>
															<tr style="border: 2px solid #e4e4e4;">
																<th
																	style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property value="getText('discounts.label')"/></th>
																<td style="text-align:right;" "color: #000; border: 2px solid #e4e4e4;"
																	><s:text name="global.currency"/> <label
																	id="discountsWOI"></label>
																	</td>
															</tr>
															<tr style="border: 2px solid #e4e4e4;">
																<th
																	style="width: 30%; font-weight: 500; border: 2px solid #e4e4e4;"><s:property value="getText('expenses.label')"/></th>
																<td style="text-align:right;" "color: #000; border: 2px solid #e4e4e4;"
																	><s:text name="global.currency"/> <label
																	id="expensesWOI"></label>
																	</td>
															</tr>

															<tr
																style="border: 2px solid #e4e4e4; background-color: #6e7071; color: white;">
																<th
																	style="width: 40%; font-weight: 900; border: 2px solid #e4e4e4; font-size: 17px;"><s:property value="getText('net.total')"/>
																	</th>
																<td style="text-align:right;" "border: 2px solid #e4e4e4; font-size: 20px;"> <s:text name="global.currency"/> <label
																	id="totalWOI"></label>
																</td>
															</tr>
															<tr>
															</tr>
														</tbody>
													</table>
												</div>
											</div>

										</div>
									</div>
									<div class="row">
										<div class="col-md-4 col-xs-12 pull-right">
											<!-- <a href="#confirmation"> -->
											<button class="btn btn-block "
												style="color: #ffffff; width: 150px; margin-top: 5px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); border: 1px solid; color: #fff; display: inline-block;"
												type="button" id="registerClaimWOI" disabled><s:property value="getText('register.claim.button')"/>
												</button>
	<%-- 												<div id="confirmation" class="overlay1">
  <div class="popup" style="min-height:120px;" >
    <h4 class="login-box-msg" style="color:black;"><s:property value="getText('_promptmsg.claim.registered')"/></h4>
   
    <table style="margin: auto;">
					<tbody>
						<tr>
							<td> <a href="registerClaim"><button class="btn btn-block btn-info" style="margin:auto" type="submit"><s:property value="getText('yes.button')"/></button></a></td>
							<td style="width: 10%;"></td>
							<td><a href="#"><button class="btn btn-block btn-info" style="margin:auto" type="button"><s:property value="getText('no.button')"/></button></a></a></td>
						</tr>
					</tbody>
				</table> --%>
  <%-- 
    <a href="registerClaim"><button class="btn btn-block btn-info" style="margin:auto" type="submit"><s:property value="getText('ok.button')"/></button></a>
    <a href="#"><button class="btn btn-block btn-info" style="margin:auto" type="button"><s:property value="getText('no.button')"/></button></a> 
    
  </div>
</div>--%>
								</form>

																
								<a href="cancelRegisterClaim">
									<button class="btn btn-block "
										style="color: #ffffff; width: 150px; margin-top: 5px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); border: 1px solid; color: #fff; display: inline-block;"
										id="cancelClaimWOI" type="button" disabled><s:property value="getText('cancel.claim.button')"/>
										</button>
								</a>

							</div>
						</div>



					</div>
					<!--  tab_2 closed     -->
				</div>
				<!-- /.tab-content -->
		</div>
	</div>

	</section>


	<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->

	<!-- Main Footer -->


	<!-- Control Sidebar -->
	<!--  <aside class="control-sidebar control-sidebar-dark" style="position: fixed; height: auto;">
    Create the tabs

    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-home"></i></a></li>
      <li class=""><a href="#control-sidebar-settings-tab" data-toggle="tab" aria-expanded="false"><i class="fa fa-gears"></i></a></li>
    </ul>

    Tab panes
    <div class="tab-content">
      Home tab content
      <div class="tab-pane active" id="control-sidebar-home-tab">
        <h3 style="text-align:center;" class="control-sidebar-heading">Alexander Peirce</h3>
          <h5 style="text-align:center;" class="control-sidebar-subheading">Sales Agent</h5>
       <div class="progress" style="height:3px;">
                <div class="progress-bar" style="width: 100%;"></div>
              </div>
        
          <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-key bg-blue"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Change Password</h4>

                
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-language bg-blue"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Change Language</h4>

                
              </div>
            </a>
          </li>
              <div class="row" style="margin:5px;height:3px;">
    <div class="progress" style="height:3px;">
                <div class="progress-bar" style="width: 100%;"></div>
              </div>
    </div>
          <li>
             <a href="login.html" style="color:#ffffff;"><button class="btn btn-block btn-primary" style="margin:0 auto;" type="button">Logout</button></a>
          </li>
          
        </ul>
        /.control-sidebar-menu

       <h3 class="control-sidebar-heading">Tasks Progress</h3>
        

      </div>
      /.tab-pane
      Stats tab content
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      /.tab-pane
      Settings tab content
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>


          
        </form>
      </div>
      /.tab-pane
    </div>
  </aside>
  /.control-sidebar
  Add the sidebar's background. This div must be placed
       immediately after the control sidebar
  <div style="position: fixed; height: auto;" class="control-sidebar-bg"></div>
</div>
./wrapper
<div id="manager_override" class="overlay1">
  <div class="popup" style="min-height:120px;width:300px;" >
     <form  action="#" method="get">
        <h5 style="text-align:center;"><b>You are not authorized to override the price. Kindly provide manager credentials</b></h5>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="User Id">
        
          <br>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="Password">
        <br>
        
        
        <button style="width:100px;margin-left:29%;" type="submit" class="btn ">Override </button>
      </div>
      
    </form>
      <a class="close" style="margin-top:-10px;" href="#">&times;</a>
  </div>
</div>
<div id="manager_override2" class="overlay1">
  <div class="popup" style="min-height:120px;width:300px;" >
     <form  action="#" method="get">
        <h5 style="text-align:center;"><b>You are not authorized to return without invoice. Kindly provide manager credentials</b></h5>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="User Id">
        
          <br>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="Password">
        <br>
        
        
        <button style="width:100px;margin-left:29%;" type="submit" class="btn ">Override </button>
      </div>
      
    </form>
      <a class="close" style="margin-top:-10px;" href="#">&times;</a>
  </div>
</div>
 -->

	REQUIRED JS SCRIPTS
	<%-- <div id="popup" class="overlay1">
  <div class="popup" style="min-height:120px;" >
    <p class="login-box-msg" style="color:black;"><b>Enter Restocking Fee To be Applied:</b></p>
    
   <p class="login-box-msg"><input type="text" id="restockingFeeTxt" style="width:100px;"></p>
    <a href="#Generate_Claim.html"><button class="btn btn-block btn-info" style="max-width:80px;background:    #bf9000;
background:    -webkit-linear-gradient(#073763, #3d85c6 50%, #073763);
background:    linear-gradient(#073763, #3d85c6 50%, #073763);                                                               
color:         #fff;margin:auto;" type="submit" id="restockingFeeBtn">Confirm&nbsp;<span id="restockingFeeSpinner" class="fa" aria-hidden="true"></span></button></a>
    <a class="close" href="#Generate_Claim.html">&times;</a>
    
  </div>
</div> --%>
	<%-- <div id="popup1" class="overlay1">
  <div class="popup" style="min-height:120px;" >
    <p class="login-box-msg" style="color:black;"><b>Enter Transportation Fee To be Applied:</b></p>
    
   <p class="login-box-msg"><input type="text" id="transportFeeTxt" style="width:100px;"></p>
    <a href="#Generate_Claim.html"><button class="btn btn-block btn-info" style="max-width:80px;background:    #bf9000;
background:    -webkit-linear-gradient(#073763, #3d85c6 50%, #073763);
background:    linear-gradient(#073763, #3d85c6 50%, #073763);                                                               
color:         #fff;margin:auto;" type="submit" id="transportFeeBtn">Confirm &nbsp;<span id="transportFeeSpinner" class="fa" aria-hidden="true"></span></button></a>
    <a class="close" href="#Generate_Claim.html">&times;</a>
    
  </div>
</div> --%>
	<!--Modal  -->
	<!--restckFeeModal-->
	<div class="modal fade bs-example-modal-sm" id="restckFeeModal"
		data-backdrtop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">

		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<s:text name="restocking.fee.applied" />
					</h4>
				</div>
				<div class="modal-body">
					<input type="number" min="1" class="center-block"
						id="restockingFeeTxt">
				</div>
				<div class="modal-footer text-center">
					<button type="submit" class="btn btn-primary center-block"
						data-dismiss="modal" id="restockingFeeBtn" disabled>
						<s:property value="getText('ok.button')"/>&nbsp;<span id="restockingFeeSpinner" class="fa"
							aria-hidden="true"></span>
					</button>

				</div>
			</div>

		</div>
	</div>
	<!-- modal to show when you click on registerclaim button @sharanya --> 
    <div class="modal fade bs-example-modal-sm" id="withinvcemodal"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 id="errorTitle" class="modal-title">
							<s:property value="getText('_promptmsg.claim.registered')" />
						</h4>
					</div>
					<div class="modal-footer " style="margin:auto;max-width:300px;">
						<button type="button" class="pull-right btn btn-primary center-block"
							data-dismiss="modal" >
							<s:property value="getText('no.button')" />
							&nbsp;
						</button>						
						<button type="submit" class="pull-left btn btn-primary center-block"
							data-dismiss="modal" id="registeredclaimwth" >
							<s:property value="getText('yes.button')" />
							&nbsp;
						</button>																
					</div>
				</div>

			</div>
			</div>
			
	<div class="modal fade bs-example-modal-sm" id="withoutinvcemodal"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 id="errorTitle" class="modal-title">
							<s:property value="getText('_promptmsg.claim.registered')" />
						</h4>
					</div>
				<div class="modal-footer " style="margin:auto;max-width:300px;">
						
						
						<button type="button" class="pull-right btn btn-primary center-block"
							data-dismiss="modal" >
							<s:property value="getText('no.button')" />
							&nbsp;
						</button>						
						<button type="submit" class="pull-left btn btn-primary center-block"
							data-dismiss="modal" id="registeredclaimwthout" >
							<s:property value="getText('yes.button')" />
							&nbsp;
						</button>						
			   </div>
			</div>

		</div>
	</div>		

	<!--transportFeeModal-->
	<div class="modal fade bs-example-modal-sm" id="transportFeeModal"
		data-backdrtop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">

		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<s:text name="transportation.fee.applied" />
					</h4>
				</div>
				<div class="modal-body">
					<input type="number" min="1" class="center-block"
						id="transportFeeTxt">
				</div>
				<div class="modal-footer text-center">
					<button type="submit" class="btn btn-primary center-block"
						data-dismiss="modal" id="transportFeeBtn" disabled>
						<s:property value="getText('ok.button')"/>&nbsp;<span id="transportFeeSpinner" class="fa"
							aria-hidden="true"></span>
					</button>

				</div>
			</div>

		</div>
	</div>
	<!-- AlertDialogModal -->
	<div class="modal fade bs-example-modal-sm" id="AlertDialogModal"
		data-backdrtop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">

		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<span id="warn"></span>
				</div>


			</div>

		</div>
	</div>
	<div class="modal fade" id="returnPriceChangeOverride" role="dialog"
		data-keyboard="false" data-backdrop="static" aria-labelledby="returnPriceChangeOverrideLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"><s:property value="getText('manager.override')"/></h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="managerOverride" method="post" id="returnPriceChangeOverrideForm">
							<p><s:property value="getText('_promptmsg.manager.override.price')"/></p>
							<div class="form-group">
								<label class="control-label col-xs-4" for="loginid" style="padding-top: 4px">
									Login ID</label>
								<div class="col-xs-8" style="padding-left: 0px">
									<input type="text" class="form-control" id="loginid" name="loginid" placeholder="Enter Login ID" 
										required="required" autocomplete="off">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-xs-4" for="password" style="padding-top: 4px">
								Password</label>
								<div class="col-xs-8" style="padding-left: 0px">
									<input type="password" class="form-control" id="password" name="password" placeholder="Enter password"
										 required="required">
								</div>
							</div>
							<%-- <hr style="margin: 6px 0;">
							<div class="form-group">
								<label class="control-label col-xs-5" for="priceChangeReasonCode" style="padding-top: 0px">
									<s:property value="getText('pricechange.reasoncode')"/>
								</label>
								<div class="col-xs-7" style="padding-top:8px; padding-left: 0px">
									<s:select cssClass="form-control" theme="simple" required="required" data-index="-1"
										id="priceChangeReasonCode" list="priceChangeReasonCodeMap" />
								</div>
							</div> --%>
							<div class="form-group text-center">
								<button class="btn btn-primary" data-dismiss="modal">Cancel</button>
								<button type="submit" class="btn btn-primary" id="managerOverride" >
									Override<i class="hidden">&nbsp;<i class="fa fa-spin fa-spinner" aria-hidden="true"></i></i>
								</button>
							</div>
							<div class="alert alert-danger hidden" id="managerOverrideErrorResponse"
								style="padding: 4px; margin-top: 10px; margin-bottom: 0px" >
							</div>
						</form>
						<div class="form-group hidden" id="managerOverrideSuccessDiv">
							<div class="alert alert-success" id="managerOverrideSuccessResponse"
								style="padding: 4px; margin-top: 0px; margin-bottom: 10px" >
							</div>
							<button class="btn btn-primary center-block" data-dismiss="modal">Ok</button>
						</div>
					</div>
					<!-- <div class="modal-footer">
						<input type="button" class="btn btn-default" id="managerOverride"
							value="Override">
					</div> -->
			</div>
		</div>
		

		
		
		<script type="text/javascript">
			var managerOverride=false;
			var relatedTarget;
			
			var priceChangeOverrideModal = $('#returnPriceChangeOverride');
			var managerOverrideError = $('#managerOverrideErrorResponse');
			var managerOverrideSuccess = $('#managerOverrideSuccessResponse');
			var priceChangeOverrideForm = $('#returnPriceChangeOverrideForm');
			var priceChangeOverrideFormFields = $("#returnPriceChangeOverrideForm :input");
			//var priceChangeReasonCode = $('#priceChangeReasonCode');
			//var priceChangeReasonCodeIndex = function(index){ return $('#priceChangeReasonCode_'+index); };
			var priceChangeOverrideFormSpinner = $("#returnPriceChangeOverrideForm button i");
			var managerOverrideSuccessDiv = $('#managerOverrideSuccessDiv');
			
			function managerOverrideErrorResponse(response){
				managerOverrideError.html('Override could not be processed:<br><strong>'+response+'</strong>');
				managerOverrideError.removeClass('hidden');
			}
			function hideManagerOverrideResponse(){
				managerOverrideError.addClass('hidden');
			}
			function managerOverrideSuccessResponse(response){
				managerOverrideSuccess.html("Return Price has been successfully overridden");
				priceChangeOverrideForm.addClass('hidden');
				managerOverrideSuccessDiv.removeClass('hidden');
			}
			function loading(){
				priceChangeOverrideFormFields.attr("readonly", true);
				priceChangeOverrideFormSpinner.removeClass('hidden');
			}
			function doneLoading(){
				priceChangeOverrideFormFields.attr('readonly',false);
				priceChangeOverrideFormSpinner.addClass('hidden');
			}
			function resetOverrideModal(index){
				priceChangeOverrideForm.get(0).reset();
				//priceChangeReasonCode.val('');
				managerOverrideSuccess.html('');
				managerOverrideError.addClass('hidden');
				priceChangeOverrideForm.removeClass('hidden');
				managerOverrideSuccessDiv.addClass('hidden');
				//resetReasonCodeListener(index)
			}
			
			/* function resetReasonCodeListener(index){
				priceChangeReasonCode.off();
				priceChangeReasonCode.change(function(){
					var selectedVal = $(this).val();
					priceChangeReasonCodeIndex(index).val(selectedVal);
				});
			} */
			
			priceChangeOverrideModal.on('show.bs.modal', function (e) {
				relatedTarget = $(e.relatedTarget);
				var index = relatedTarget.data("index");
				resetOverrideModal(index);
				managerOverride = false;
			})
			
			priceChangeOverrideModal.on('hide.bs.modal', function (e) {
				//priceChangeReasonCode.off();
  				if(!managerOverride && !!relatedTarget) {
					relatedTarget.val("").trigger("change");
				}
			});
			
			priceChangeOverrideForm.submit(function(e){
				e.preventDefault();
				
				hideManagerOverrideResponse();
				var data = $(this).serialize();
				loading();
				$.ajax({
					type: $(this).attr('method'),
					url: $(this).attr('action'),
					data: data,
					timeout: 10000
					
				}).done(function(data, textStatus, jqXHR) {
					doneLoading();
					if(!data.validCredentials){
						managerOverrideErrorResponse('Invalid Credentials');
					}
					else{
						if(!data.managerOverride){
							managerOverrideErrorResponse('Not Authorized');
						}
						else{
							managerOverride = true;
							managerOverrideSuccessResponse();
							// Used for triggering totals update in register claim without invoice
							relatedTarget.trigger('update');
						}
					}
					
				}).fail(function(jqXHR, textStatus, errorThrown) {
					doneLoading();
					managerOverrideErrorResponse(textStatus);
					
		            alert('An error occured while processing the request:\n' + errorThrown);

				}).always(function(){
					
				});
				
			});
		
		
		</script>
	</div>

<!--modal to show a popup when to click on delete button @sharanya  -->
		<div class="modal fade bs-example-modal-sm" id="deleteItemModal"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content" style="margin:auto;max-width:250px;">
					<div class="modal-body text-center">
						<button type="submit" class="close" data-dismiss="modal">&times;</button>
						<h4>
							Are you sure you want to delete this item ?
						</h4>
					</div>
					<div class="modal-footer" style="margin:auto;max-width:300px;">
						<input type="number" hidden="true" id="deleteItemModaltxt"/>
						<button type="submit" class="pull-left btn btn-primary center-block"
							data-dismiss="modal" id="deleteItemfromTable">
							<s:property value="getText('yes.button')" />
							&nbsp;
						</button>
					
					    <button type="submit" class="pull-right btn btn-primary center-block"
							data-dismiss="modal">
							<s:property value="getText('no.button')" />
							&nbsp;
						</button>
						
					</div>
				</div>

			</div>
		</div>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->


	<!-- ChartJS 1.0.1 -->
	</div>
	<script type="text/javascript">
	//with invoice registerclaim button
 $(function(){
	$("#registerClaimWI").click(function(e)
		    {
		     $("#withinvcemodal").modal('show');
		    });
});

//when ok button clicked
$("body").on("click","[id='registeredclaimwth']",function(){
			$("#saveClaim").submit();
		});   
		
	//without invoice registerclaim button	
$(function(){
	$("#registerClaimWOI").click(function(e)
		    {
		     $("#withoutinvcemodal").modal('show');
		    });
});
//when ok button clicked
$("body").on("click","[id='registeredclaimwthout']",function(){
			$("#saveClaimWOI").submit();
		});  
		
</script>
<script type="text/javascript">
   /* window.onbeforeunload = confirmExit;
    var preventBack=<s:property value="preventBack" />;
    function confirmExit() {
    	if(preventBack)
        return "You have attempted to leave this page. Are you sure?";
    } */
</script>
<script type="text/javascript">
/* var enableClaimWithInvoice = <s:property value="enableWithInvoiceFlag"/>; 
if(enableClaimWithInvoice)
	{
	// Enable #invoiceId
    $( "#invoiceId" ).prop( "disabled", false );
    $( "#searchWithInvoice" ).prop( "disabled", false );
	}else{
	// Disable #invoiceId
    $( "#invoiceId" ).prop( "disabled", true );
    $( "#searchWithInvoice" ).prop( "disabled", true );
	} */
</script> 

<script type="text/javascript">
		$("#custInfo").autocomplete({
			autoFocus: true,
			minLength: 2,
			delay: 500,
			source: function(request, response) {
				$("#custError").addClass('hidden');
				$.ajax({
				  url: "customerLookupAjax",
				 /*  timeout: 10000, */
				  timeout : 1800000,
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
				$("#custsearch").trigger('click');// .get(0).submit();//
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
	
</body>
<script type="text/javascript">
function loadFileAsText(){
  var fileToLoad = document.getElementById("fileToLoad").files[0];
  var f=fileToLoad;
  if (document.getElementById("fileToLoad").value == "")
  {
     alert("You forgot to attach file!");
     return false;  
 }
//var files = evt.target.files; 
// FileList object

  
    
        var reader = new FileReader();
        reader.onload = (function(reader)
        {
            return function()
            {
                var contents = reader.result;
                var lines = contents.split('\n');
                //alert(lines);
                var i;
                for (i = 1; i < lines.length-1; i++) { 
                  var line1= lines[i].split(',');
                	var itemfromExcel = line1[0];
                	var returnQty= line1[1];
                	var returnPrice=line1[2];
                	var returnReasonPrice=line1[3];
                	//alert(itemfromExcel);
                    addiItemfromExcel(itemfromExcel,returnQty,returnPrice,returnReasonPrice);
                	
                }
              
            }
        })(reader);

        reader.readAsText(f);  
}



//add items to table from excel
		/* AJAX item ADDING */
		function addiItemfromExcel(itemfromExcel,returnQty,returnPrice,returnReasonPrice){
	//alert("itemfromExcel=="+itemfromExcel+"returnQty::"+returnQty +"returnPrice::"+returnPrice+"returnReasonPrice::"+returnReasonPrice)
			var itemid =itemfromExcel;
			var qty = returnQty;
			var returnPrice=returnPrice;
			var returnReasonPrice=returnReasonPrice;
			var str2 = returnPrice+"-"+returnReasonPrice;
			var checkInv = "false";
			$('#spinner').addClass('fa-spinner fa-spin');
            var tranType=2;
			$.ajax({
				url : "addItemAction",
				type : "POST",
				data : {
					term : itemid,
					qty : qty,
					checkInv : checkInv,
					tranType :tranType,
					returnPriceFromExl:str2
								
				},
				dataType : "json",
				success : function(jsonResponse) {
					//alert(JSON.stringify(jsonResponse));
					      // var obj = { returnPrice: "returnPrice" };
					     //  var data= jsonResponse;
					      // data.orderTran.ordTranLineItems.returnPrice=returnPrice;
                       // data.push(obj);
						UpdateTableContent(jsonResponse);
					
					$('#spinner').removeClass('fa-spinner fa-spin');
					//manually trigaring event
					$("#itemDetailsTable").trigger('update');

					//$('#additembutton').prop("disabled", true);
					$('#itemIDDesc').val("");
					$('#qty').val("");

					$('#qty').prop("disabled", true)
				}

			});
			
		}
		
		//to updateOrders table content
		function UpdateTableContent(jsonResponse) {
			$("#itemDetailsTable > tbody").children('tr').remove();//removing existing table rows
			var row,hasRestockingFeeServiceitem=false,hasTransportationFeeServiceitem=false;
			var i = 1;
		
			var defaultResonCodeOptions;
			//to get reason codes
			var firstResoncode;
		  	
			$.each( returnReasonCodes, function(index,value){
			       defaultResonCodeOptions += '<option value="'+index+'">'+value+'</option>';
			       if(firstResoncode == null)
			       {
			        firstResoncode = index;
			       }
			});
			var managerOverride = <s:property value="managerOverride"/>;		
			
			var orderTran = jsonResponse.orderTran;
			//alert("row ORDLINE=="+JSON.stringify(orderTran.ordTranLineItems));
			$
					.each(
							orderTran.ordTranLineItems,
							function(key, value) {
							
						
							//alert("from JSON::::"+value.rprice);
							var frr=value.rprice;
							 var res = frr.split("-");
							 var returnP=res[0];
							 var rReason=res[1];
								//Discount amt,percent function
								var discountAmt = 0, discountPer = 0,itemPrmName= "",itemPromoDivDisplayStyle="none";;
								$.each(value.ordTranDscItms, function(key, disc) {
									
									discountAmt += disc.dscAmt;
									discountPer += disc.dscPer;
									
									if(disc.prmId!=null)
									{
									 itemPrmName += disc.prmDesc;
									 itemPromoDivDisplayStyle = "block";
									}
								});
								var resonCodeOptions;
								//to get selected reson code
								if(value.rcRtnMr !=null)
								{
								var selectedReasonCodeOptions;
								 $.each( returnReasonCodes, function(index,reason){
								   if(value.rcRtnMr == index )
								   {
							        selectedReasonCodeOptions += '<option value="'+index+'" selected="selected" >'+reason+'</option>';
							       }
							       else
							       {
							        selectedReasonCodeOptions += '<option value="'+index+'">'+reason+'</option>';
							       }
							     });
							     	resonCodeOptions =	selectedReasonCodeOptions;					
								}
								else
								{
								 value.rcRtnMr=firstResoncode;
								 resonCodeOptions =	defaultResonCodeOptions;		
								}
								 var returnPrice = "",returnqty= value.lineQnt;
								if(value.priceOverRideFlag == "1" )
								{
								 returnPrice =  value.overRidePrice;
								 //returnPrice= returnPrice;
								}
								if(value.returnQtyFlag == "1" )
								{
								 returnqty = value.lineQntRtn;
								}
								var rowNum = i,itemId=value.itemId;
								rowNum--;
								
								var returnPriceField;
								if(managerOverride){
									returnPriceField = '<input type="number" style="width:130px;background-color:#f7ffb7;" class="form-control"'+
										' onkeypress="return isNumericKey(event);" onInput="checkLength(9,this)"'+
										' value="'+ returnP +'" id="returnPrice_'+rowNum+'" min="1"'+
										' data-toggle="popover" data-trigger="focus" data-content="Will require manager override" >';
								}
								else{
									returnPriceField = '<input type="number" style="width:130px;background-color:#f7ffb7;" class="form-control"'+
									
										' disabled  value="'+ returnP +'" id="returnPrice_'+rowNum+'" min="1" >';
								}
								//var priceChangeReasonCodeMap = ${priceChangeReasonCodeMap};
								var priceChangeReasonCodeOptions = '${priceChangeReasonCodeMapAsOptions}';
								if(value.priceOvrrRsnCode != null){
									var option = '<option value="'+value.priceOvrrRsnCode+'">';
									var replaceOption = '<option value="'+value.priceOvrrRsnCode+'" selected="selected">';
									priceChangeReasonCodeOptions = priceChangeReasonCodeOptions.replace(option, replaceOption);
								}
								//$.each(priceChangeReasonCodeMap, function(key, value) {
								//    priceChangeReasonCodeOptions += $("<option/>", {value: key, text: value});
								//});
								var priceChangeReasonCodeField = function(rowNum){ 
									return '<select id="priceChangeReason_'+rowNum+'" data-index="'+rowNum+'" class="form-control">'+ 
														priceChangeReasonCodeOptions+ '</select>';}
								
								value.extnDscLnItm=returnP*returnqty;
							
									//alert("value of net total"+value.extnDscLnItm+":::Rprice::::"+rPrice+":::::Rqty:::::"+returnqty);
								row += '<tr>'+'<td style="min-width: 10px">'+ i++ + '</td>'
										+ '<td style="min-width: 20px"></td>'
										+ '<td><span>' + itemId + '</span><div id="itemdesc_'+rowNum+'">'+ value.pluItem.item.itmDesc+ '</div><div id="itemPromName'+rowNum+'" style="display:'+itemPromoDivDisplayStyle+'">'
										+ '<a style="color:red;font-size: 170%" tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Discounts/Promotions for" data-content="'+itemPrmName+'">'
										+ '<s:property value="getText('star.symbol')"/></a></div></td>'
										+ '<td style="text-align:right;">'+ value.itmPrnPrc + '</td>'
										+ '<td><input class="form-control" type="number" disabled value="'+ returnqty+ '" id="returnQty_'+rowNum+'" min="1" ></td>'
										+ '<td>'+ returnPriceField + '</td>'
										+ '<td>'+ rReason + '</td>'
										/* + '<td>'+ priceChangeReasonCodeField(rowNum) + '</td>' */
										+ '<td><div id="discount_'+rowNum+'">'+discountAmt+'</div></td>'
										+ '<td style="min-width: 40px;text-align:right;"class="netPriceClass">'+ value.extnDscLnItm+ '</td>'
										+ '<td><select disabled id="reason_'+rowNum+'" class="form-control">'+ resonCodeOptions+ '</select></td>'
										+ '</tr>';
										
								//to check RestockingFeeServiceitem is there in transaction
								if(itemId == jsonResponse.restockingFeeServiceitemId)
								{
								  hasRestockingFeeServiceitem=true;
								}
								//to check RestockingFeeServiceitem is there in transaction
								if(itemId == jsonResponse.transportationFeeServiceitemId)
								{
								 hasTransportationFeeServiceitem=true;
								}
								

							});
			//alert("Row:::"+row)
			$('#itemDetailsTable > tbody').append(row);
			
			$('[data-toggle="popover"]').popover();
			//scroll down while adding items
			var rowpos = $('#itemDetailsTable tr:last').position();
			$('#parent').scrollTop(rowpos.top);

			var noOfItems = orderTran.ordTranLineItems.length;
			if(noOfItems<1)
			{				
				$('#registerClaimWOI').prop("disabled", true);
			}
			else
			{
			 $('#registerClaimWOI').prop("disabled", false);
			}
			//updateTotals();
			/*  $.each(jsonResponse.orderTran.ordTranSums, function(key, ordTranSum) {  */
				var ordTranSum = jsonResponse.orderTran.ordTranSum;
				var currencyFormat = "<s:property value="getText('format.currency')"/>";
				var subtotalWOI = format(currencyFormat,  ordTranSum.dkartSlsTot);
				var totalWOI = format(currencyFormat,  ordTranSum.dkartNetTot);
				var discountsWOI = 0;
				discountsWOI = format(currencyFormat, ordTranSum.dkartDscTot);
				var expensesWOI= format(currencyFormat,  ordTranSum.dkartExpenses);
				$('#subtotalWOI').html(subtotalWOI);
				$('#totalWOI').html(totalWOI);
				$('#expensesWOI').html(expensesWOI);
				$('#discountsWOI').html(discountsWOI);

			/* });  */
		
	    // updateButtons(jsonResponse,hasRestockingFeeServiceitem,hasTransportationFeeServiceitem);
	     $('#itemIDDesc').focus();
		}


		</script>



<div class="modal fade" role="dialog"	id="myModal">
		 <div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Claim EDI Upload</h4>
				</div>
			<table class="table table-bordered" width="50%">
        <!-- 	<form action="uploadEDIFileClaim" method="post" enctype="multipart/form-data"> -->
			<%-- <tr><td><s:property value="getText('customer.id')"/></td><td><b><s:property value="newClaim.customerID"/></b></td></tr> --%>
        	<tr><td><s:property value="getText('edi.selectFile')"/></td><td><input id="fileToLoad" type="file" name="fileToLoad" accept=".csv"/></td></tr> 
        	<tr><td colspan="3" align="right">
        	 <!-- <td><input type="file" id="fileToLoad"></td> -->
            <button onclick="loadFileAsText()" class="btn" data-dismiss="modal">Process</button><td> 
        	<%-- <button onclick="loadFileAsText()" id="uploadEdiFile" class="btn"><s:property value="getText('edi.button.Upload')"/></button>  --%>
        	<button  onClick="history.go(-1);return true;" type="button" class="pull-right btn btn-secoundry" style="color:#ffffff; width:100px; background:    #3d85c6;
			background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
			background:    linear-gradient(#1770c1, #073763 50%, #1770c1); 
			color:         #fff;
			display:       inline-block;">
            <s:property value="getText('back.button')"/>
          	</button>
        	
        	</td></tr>
        <!-- 	</form> -->
			</table>
				
				<%-- <div class="modal-footer">
					<form class="row form-inline" action="ediUploadFileSelectForClaim" method="post">
						<s:hidden name="customerId" value="%{"newClaim.customerID"}"/>
						<div class="col-xs-6">	
							<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
							<button type="submit" class="btn btn-primary">Process</button>
						</div>
					</form>
				</div> --%>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->	 

<!--modal to show a popup when item is not in table   -->
		<div class="modal fade bs-example-modal-sm" id="itemNotFoundModal"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content" style="margin:auto;max-width:250px;">
					<div class="modal-body text-center">
						<button type="submit" class="close" data-dismiss="modal">&times;</button>
						<h4>
							Item is not belongs to customer .
						</h4>
					</div>
					<div class="modal-footer" style="margin:auto;max-width:300px;">
						
					    <button type="submit" class="pull-right btn btn-primary center-block"
							data-dismiss="modal">
							<s:property value="getText('no.button')" />
							&nbsp;
						</button>
						
					</div>
				</div>

			</div>
		</div>


		<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                 <h4 class="modal-title" id="myModalLabel">Modal title</h4>

            </div>
            <div class="modal-body">...</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div> -->
</html>