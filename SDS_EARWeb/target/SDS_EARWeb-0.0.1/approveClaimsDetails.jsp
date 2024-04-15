<%@ page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="utf-8"%>
    <!-- changed by @ jagadish because this (charset=ISO-8859-1 And  pageEncoding="ISO-8859-1) does not supporting
	to display Arabic language.  -->
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0);//proxies
	response.setDateHeader("Expires", -1);
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
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
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
    <!-- <link rel="stylesheet" href="assets/css/popup.css"> -->
    <link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="assets/css/jquery-ui.css">
    <link rel="stylesheet" href="assets/css/Customizations.css">
    <script src="Starter_files/jQuery-2.js"></script>
    <script src="Starter_files/bootstrap.js"></script>
    <script src="Starter_files/app.js"></script>
  <!-- having function for print --> 
     <script src="custom/printFunction.js"></script>
       <!-- CurrencyFormat --> 
     <script type="text/javascript" src="custom/format.js"></script>

 <script type="text/javascript">
$(function(){
	//when the claim belongs to invoice cancel
	var invoiceCancelFlag = "${claimTranHeader.getFlInvCncl()}";
	if(invoiceCancelFlag == "Y"){
		$("[id^=approvedQty_]").prop('readonly', true);
		$("[id^=approvedPrice_]").prop('readonly', true);
	}
    else{
    	$("[id^=approvedQty_]").prop('readonly', false);
    	$("[id^=approvedPrice_]").prop('readonly', false);
    }
   //when reject button is clicked
    $("#rejectBtn").click(function(e)
    {
     $("#rejectClaimNotesTxt").val("");
     $("#rejectClaimNotesModal").modal('show');
    });
    //when aprove button is clicked
	$("#approveButton").click(function(e) {
	
		$("#approveModel").modal('show');
	}); 
	 
	//when approveClaimModal "OK" button is clicked
	$("#approveClaimButton").click(
			function(e) {
				$("#approveClaimForm").submit();
			});
	
    //when rejectClaimNotesModal "OK" button is clicked
   $("#rejectClaimNotesBtn").click(function(e)
    {
      $("#hiddenRejectClaimNotesTxt").val($("#rejectClaimNotesTxt").val());    
      $("#approveClaimForm").attr("action","rejectAwaitingForApproveClaim")
      $("#approveClaimForm").submit();
    });
    
    //rejectClaimNotesTxt is filled 
    $("#rejectClaimNotesTxt").on("input",function(e){
	    if($("#rejectClaimNotesTxt").val().length > 0)
	    {
	    $("#rejectClaimNotesBtn").prop("disabled",false);
	    }
	    else
	    {
	    $("#rejectClaimNotesBtn").prop("disabled",true);
	    }
    });
   
//fires when approvedQty changed
$("body").on("input","[id^='approvedQty_']",function(e){
var element = $(this);
var aprovedQtyList=$("input[id^='approvedQty_']");
var elementId = element.attr('id');
var elementIdArr = elementId.split("_");
var rowIndex = elementIdArr[1];
var approvedQty = $(this).val();
if(approvedQty == ""){
	approvedQty = parseFloat('0.0');
	$("#"+elementId).val(approvedQty);
}
var registerdQty =  Number($("#registerQty_"+rowIndex).html());
var unitPrice = Number($('#unitPrice_'+rowIndex).html());
var extDscItm = $('#netPriceBck_'+rowIndex).val();
var regPrice =  extDscItm/Number(registerdQty);
var CurrencyFormat="<s:property value="getText('format.currency')"/>";
var digit = format(CurrencyFormat,parseFloat('0.0'));
var decimals = digit.split('.')[1].length;  // GETTING THE NO.OF DECIMALS
if(approvedQty > registerdQty )
{
	$('#aprovedQtymodal').modal('show');
 approvedQty = registerdQty;
 $(this).val(approvedQty);
}
var approvedPrice = $("#approvedPrice_"+rowIndex).val();
if(regPrice.toFixed(decimals) == approvedPrice){
	var netPrice = approvedQty*regPrice;
}else{
	var netPrice = approvedQty*approvedPrice;
}
var discPerQty = parseFloat('0.0');
discPerQty = (Number($("#itmDiscPrice_"+rowIndex).val())) / registerdQty;
var netPrice1=format(CurrencyFormat,netPrice);
//round "netPrice" to two decimals
netPrice = netPrice.toFixed(decimals);
$("#netPrice_"+rowIndex).html(netPrice1);
$("#hiddenNetPrice_"+rowIndex).val(netPrice);
var rowCount = $('#awaitingForApprovalTable >tbody >tr').length;
var tranNettotal1=0;
var dkartSlsTot1=0;
var tranNettotal=0;
var dkartDiscTot = $('#dkartDiscTot').text().replace(/,/g , "");
dkartDiscTot = Number(dkartDiscTot);
for(var i=0;i<rowCount;i++)
{
var netprice = $("#netPrice_"+i).text().replace(/,/g , "");
var dkartslstl = $("#netPrice_"+i).text().replace(/,/g , "");
 tranNettotal1 = tranNettotal1 +Number(netprice);
 dkartSlsTot1 = dkartSlsTot1 +Number(dkartslstl);
}
var ret = $('[id^=discount_]');

if(approvedQty == 0){
	$("#discount_"+rowIndex).html('0');
	$('#hiddenDiscPrice_'+rowIndex).val(parseFloat('0.0'));
}else{
	$('#discount_'+rowIndex).html(format(CurrencyFormat,(discPerQty * approvedQty)));
	$('#hiddenDiscPrice_'+rowIndex).val(discPerQty * approvedQty);
}
//round "tranNettotal" to two decimals
tranNettotal1=tranNettotal1.toFixed(decimals);
tranNettotal=format(CurrencyFormat,tranNettotal1);
$("#tranNettotal").html(tranNettotal);
$("#hiddenTranNettotal").val(tranNettotal1);
//round "subTotal" to two decimals
dkartSlsTot1 = dkartSlsTot1.toFixed(decimals);
dkartDiscTot = discPerQty * approvedQty;
//dkartSlsTot1 = dkartSlsTot1 + dkartDiscTot;

var dkartDiscTot2 = parseFloat('0.0');
ret.each(function(inde){
	var disc = $(this);
	var discVal = Number($('#'+this.id).text().replace(/,/g , ""));
	//if((discVal == 0)){
	//	dkartDiscTot2 = dkartDiscTot2 + ((Number($('#unitPrice_'+inde).html())) - (Number($('registerPrice_+inde').html())));
	//}else{
		dkartDiscTot2 = dkartDiscTot2 + discVal;	
	//}
});
// to update the discount lines
updateDiscounts(rowIndex,registerdQty,approvedQty);

dkartSlsTot1 = Number(dkartSlsTot1) + dkartDiscTot2;
var dkartSlsTot=format(CurrencyFormat,dkartSlsTot1);

$("#dkartSlsTot").html(dkartSlsTot);
$("#hiddendkartSlsTot").val(dkartSlsTot1);

var dkartDiscTot=format(CurrencyFormat,dkartDiscTot2);
$('#dkartDiscTot').html(dkartDiscTot);
var dkartDiscTot1 = Number(dkartDiscTot2.toFixed(decimals));
$('hiddenTranDisctotal').val(dkartDiscTot1);


var totalReturnQuantity=0;
for(var i=0;i<aprovedQtyList.length;i++){
	totalReturnQuantity=totalReturnQuantity+Number(aprovedQtyList[i].value);
}
$("#totalReturnQty").html(totalReturnQuantity);
$("#hiddenTotalReturnQuantity").val(totalReturnQuantity);
if (totalReturnQuantity == 0) {
    $("#approveButton").attr("disabled", true);
    $('#totalQuantityModel').modal('show');
}
else {
    $("#approveButton").removeAttr("disabled");
}
});

// field as empty if ZERO for next number
$("[id^='approvedPrice_']").keypress(function(e){
	var approvedPrice = $(this).val();
	var element = $(this);
	var elementId = element.attr('id');
	if(approvedPrice == 0){
		$("#"+elementId).val('');
	}
});
// when field is made as empty, it should consider ZERO
$("[id^='approvedQty_']").keypress(function(e){
	var approvedPrice = $(this).val();
	var element = $(this);
	var elementId = element.attr('id');
	if(approvedPrice == 0){
		$("#"+elementId).val('');
	}
});
//fires when approvedPrice changed
$("body").on("input","[id^='approvedPrice_']",function(e){
var element = $(this);
var elementId = element.attr('id');
var elementIdArr = elementId.split("_");
var rowIndex = elementIdArr[1];
var CurrencyFormat="<s:property value="getText('format.currency')"/>";
var approvedPrice = $(this).val();
var extDscItm = $('#netPriceBck_'+rowIndex).val();
if(approvedPrice == ""){
	approvedPrice = parseFloat('0.0');
	$("#"+elementId).val(approvedPrice);
}
var registerdQty =  Number($("#registerQty_"+rowIndex).html());
var registerPrice =  extDscItm/Number(registerdQty);
var zero = format(CurrencyFormat,0);
var scale = zero.split('.')[1].length;
registerPrice = registerPrice.toFixed(scale);
registerPrice =Number(registerPrice);
if(approvedPrice > registerPrice)
{
	$('#aprovedpricemodal').modal('show');
 approvedPrice = registerPrice;
 $(this).val(approvedPrice);
}
var approvedQty = $("#approvedQty_"+rowIndex).val();
var netPrice = approvedQty*approvedPrice;
var netPrice1=format(CurrencyFormat,netPrice);
//round "netPrice" to two decimals
netPrice = Number(netPrice.toFixed(scale));
$("#netPrice_"+rowIndex).html(netPrice1);
$("#hiddenNetPrice_"+rowIndex).val(netPrice);
var rowCount = $('#awaitingForApprovalTable >tbody >tr').length;
var tranNettotal1=0;
var dkartSlsTot1=0;
var dkartDiscTot = $('#dkartDiscTot').text().replace(/,/g , "");
dkartDiscTot = Number(dkartDiscTot);
for(var i=0;i<rowCount;i++)
{
	//var netprice = $("#netPrice_"+i).text().replace(',', '');
	var netprice = $("#netPrice_"+i).text().replace(/,/g , "");
	 tranNettotal1 = tranNettotal1 +Number(netprice);
	 dkartSlsTot1 = dkartSlsTot1 +Number(netprice);
}
//round "tranNettotal" to two decimals
tranNettotal1=Number(tranNettotal1.toFixed(scale));
var tranNettotal=format(CurrencyFormat,tranNettotal1);
$("#tranNettotal").html(tranNettotal);
$("#hiddenTranNettotal").val(tranNettotal1);
//round "subTotal" to two decimals
dkartSlsTot1 = Number(dkartSlsTot1.toFixed(scale));
dkartSlsTot1 = dkartSlsTot1 + dkartDiscTot;
var dkartSlsTot=format(CurrencyFormat,dkartSlsTot1);
$("#dkartSlsTot").html(dkartSlsTot);
$("#hiddendkartSlsTot").val(dkartSlsTot1);
});
});
// to update the discounts 
function updateDiscounts(discountIndex,registerdQty,approvedQty){
		var updatedDiscount = Number($('#discount_'+discountIndex).text().replace(/,/g , ""));
		var currencyFormat ="<s:property value="getText('format.currency')"/>";
		var decimals = format(currencyFormat,'0').split('.')[1].length;
		updatedDiscount = parseFloat(updatedDiscount);
		var itmLevDiscList = $('#itemLevelDiscountBck_'+discountIndex);
		var trnLevDiscList = $('#tranLevelDiscountBck_'+discountIndex);
		
		var itmDiscVal = itmLevDiscList[0].value;
		var trnDiscVal = trnLevDiscList[0].value;
		
		var itmLevSingleQtn = itmDiscVal / registerdQty;
		var trnLevSingleQtn = trnDiscVal / registerdQty;
		
		if(itmDiscVal != 0){ // ONLY ITEM LEVEL DISCOUNT
			$('#itemLevelDiscount_'+discountIndex).val(updatedDiscount);
		}
		if(trnDiscVal != 0){ // ONLY TRAN LEVEL DISCOUNT
			$('#tranLevelDiscount_'+discountIndex).val(updatedDiscount);
		}
		if(itmDiscVal != 0 && trnDiscVal != 0 && updatedDiscount != 0){ // DOUBLE DISCOUNTS
			updatedDiscount = format(currencyFormat,(updatedDiscount));
			
			var updatedItmDisc = (itmLevSingleQtn * approvedQty).toFixed(decimals);
			var updatedTrnDisc = (trnLevSingleQtn * approvedQty).toFixed(decimals);
		
			var tot = Number(itmDiscVal)+Number(trnDiscVal);
			var updatedDisc = Number(updatedItmDisc) + Number(updatedTrnDisc); 
			if(tot != updatedDisc){
				itmLDiscVal = 
				$('#itemLevelDiscount_'+discountIndex).val(format(currencyFormat,updatedItmDisc));
				$('#tranLevelDiscount_'+discountIndex).val(format(currencyFormat,updatedTrnDisc));
				
			}else{
				$('#itemLevelDiscount_'+discountIndex).val(itmDiscVal);
				$('#tranLevelDiscount_'+discountIndex).val(trnDiscVal);
			}
		}
}

</script>
<!--  validation for input feild--Sharanya -->
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
	
//to validate whether the entered key is number(0-9),->,<-,backspace
// modified code to enter decimals:@laxmikanth
function isNumberKey(evt)
{
	var currencyFormat = "<s:property value="getText('format.currency')"/>";
	var digit = format(currencyFormat,parseFloat('0.0'));
	var allowedDecimals = digit.split('.')[1].length;
	var charCode = (evt.which) ? evt.which : event.keyCode;
	var targettedId = evt.target.id;
	var val = $('#'+targettedId).val();
	if(targettedId.includes("approvedQty")){
		if(charCode == 46){
			evt.preventDefault(); // NOT TO ALLOW DOT FOR QUANTITY FIELD
		}
	}
	var dot = val.indexOf('.');
	var noOfDecimals = parseFloat('0.0');
	if(val != undefined && dot >= 1){
		noOfDecimals = val.split('.')[1].length;
	}
	
	if ( charCode !== 8){
	if(allowedDecimals == noOfDecimals){
		return false;
	}
	}
   if (charCode > 31 && (charCode < 46  || charCode > 57))
      return false;
	
	if(dot >= 1 && charCode == 46){ // NOT TO ALLOW MORE NO.OF DOTS
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

		<!-- Main Header -->

		<!-- Left side column. contains the logo and sidebar -->
		<s:include value="topbar.jsp"></s:include>
		<s:include value="menubar.jsp"></s:include>

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">
			<!-- Main content -->
			<section class="content">

				<!-- Your Page Content Here -->
				<section class="claim">
					<!-- title row -->
					<div class="row">
						<div class="col-md-3 col-xs-3 visible-print-inline">
							<b><span class="logo-lg pull-left "><img
									src="assets/SDSlogo.png"></span> <span class="logo-mini"></span></b>
						</div>
						<div class="col-md-3 col-xs-3 visible-print-inline">
							<small class="pull-left  "
								style="font-size: 9px; color: #8e8e8e;"><b> <s:i18n
										name="rispl.print.printText">
										<s:text name="salesInvoice.Address3" />
									</s:i18n>
							</b></small>
						</div>
						<div class="col-md-3 col-xs-3 visible-print-inline">
							<small class="pull-right" style="font-size: 9px; color: #8e8e8e;"><b>
									<s:i18n name="rispl.print.printText">
										<s:text name="salesInvoice.Address2" />
									</s:i18n>
							</b></small>
						</div>
						<div class="col-md-3 col-xs-3 visible-print-inline">
							<small class="pull-right"
								style="text-align: right; font-size: 9px; color: #8e8e8e;"><b>
									<s:i18n name="rispl.print.printText">
										<s:text name="salesInvoice.Address1" />
									</s:i18n>
							</b></small>
						</div>
					</div>
					<small class="pull-right hidden-print"
						style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><s:property value="getText('claim.details.help_text')"/>
						</small>
					<div class="row">
						<div class="col-xs-12">
							<h2 style="color: #226e71;">
							<s:property value="getText('claim.details')"/>
								<div class="pull-right hidden-print">
									<h5>
										   <a href="javascript:void(0);" class="pull-right" rel="popover" id="btpopover" data-content='
                						    
                 							<div><b><input type="checkbox" id="employee" name="employee" class="form-check-input"> <label for="employee"><s:property value="getText('login.employee')"/></label></div>
											<div><b><input type="checkbox" id="customr" name="customr" class="form-check-input"> <label for="customr"><s:property value="getText('customer.label')"/></label></div>
											<div><b><input type="checkbox" id="departmentHead" name="departmentHead" class="form-check-input"> <label for="departmentHead"><s:property value="getText('dept.head')"/></label></div>
    										<div><b><input type="checkbox" id="salesAgent" name="salesAgent" class="form-check-input"> <label for="salesAgent"><s:property value="getText('sales.agent')"/> </label></div>
    										<div><b><input type="checkbox" id="DataEntryOptr" name="DataEntryOptr" class="form-check-input"> <label for="DataEntryOptr"><s:property value="getText('dataentry.operator')"/> </label></div>
    										<div><label>Mail :</label><input type="text" id="custommails" name="custom" /></div><br>
                							<div style="text-align:right;"><button type="button"  id="emailSubmitbtn">Submit</button>
                							<button type="button" data-dismiss="modal" id="cancelmail">Cancel</button></div>
                							' data-placement="top" data-original-title="Select"><i class="fa fa-envelope"></i> <s:property value="getText('mail.label')"/>  &nbsp;</a>
											<a href="javascript:void(0);" onclick="windowPrint()" class="pull-right" style="text-decoration:none" id="print"><i class="fa fa-print"></i> <s:property value="getText('print.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a> 
											<a href="approveClaimPDFDownload?claimID=<s:property value="claimID"/>"><i class="fa fa-save"></i> <s:property value="getText('save.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a>
								</div>
								<!--            <small class="pull-right">Date: 2/10/2014</small>-->
							</h2>
						</div>
						<!-- /.col -->
					</div>
					<!-- info row -->
					<div class="row invoice-info">
						<div class="col-sm-4 invoice-col">
							<table style="width: 100%;" class="table">
								
								<tr>
									<td style="text-align: right;"><s:property value="getText('claim.id')"/>:</td>
									<td><b><span id="claimId"><s:property value="claimID" /></span></b></td>
								</tr>
								<tr>
									<td style="text-align: right; width: 50%;"><s:property value="getText('claim.date')"/>:</td>
									<td><b><s:date name="claimedDate" format="%{getText('format.date')}"/></b></td>
								</tr>

							</table>
						</div>
						<!-- /.col -->
						<div class="col-sm-4 invoice-col">
							<table style="width: 100%;" class="table">
							<tr>
									<td style="text-align: right;"><s:property value="getText('invoice.no')"/>:</td>
									<td><b><s:property value="invoicenNo" /></b></td>
								</tr>
								<tr>
									<td style="text-align: right; width: 50%;"><s:property value="getText('status.label')"/>:</td>
									<td><b><s:property value="claimStatus" /></b></td>
									<%-- <td><b><s:property value="claimPerStatus" /></b></td> --%>
									
								</tr>
								<tr><td style="text-align: right;"><s:property value="getText('sales.agent')"/>:</b></td>
							    <td><b><s:property value="salesAgentName" /></b></td>
								</tr>
							</table>
						</div>
						<!-- /.col -->
						<div class="col-sm-4 invoice-col">
							<table style="width: 100%;" class="table">
							<tr>
									<td style="text-align: right;"><s:property value="getText('customer.id')"/>:</td>
									<td><b><s:property value="custId" /></b></td>
								</tr>
								<tr>
									<td style="text-align: right;"><s:property value="getText('customer.name')"/>:</td>
									<td><b><s:property value="customerName" /></b></td>
								</tr>
								<tr>
									<td style="text-align:right;"><s:property value="getText('claim.pickup.addrss')"/> :</td><td><b><s:property value="site_address"/></b></td>
								</tr>
							</table>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
					<form id="approveClaimForm" action="saveApproveClaim" method="post"/>
					<!-- Table row -->
					<div class="row">
						<div class="col-xs-12 table-responsive">
							<table class="table table-striped" id="awaitingForApprovalTable" style="margin-bottom: 2px;">
								<thead>
									<tr style="background-color: #ADC2EE;">
										<th> <s:property value="getText('table.head.SNo')"/></th>
										<th><s:property value="getText('table.head.item')"/></th>
										<th style="text-align:right;"><s:property value="getText('sold.qty')"/></th>
										<th style="text-align:right;"><s:property value="getText('registered.qty')"/></th>
										<th style="text-align:right;"><s:property value="getText('table.head.unitprice')"/></th>
										<th style="text-align:right;"><s:property value="getText('registered.price')"/></th>
										<th><s:property value="getText('approved.qty')"/></th>
										<th><s:property value="getText('approved.price')"/></th>
										<th style="text-align:right;"><s:property value="getText('table.head.disc')"/></th>
										<th style="text-align:right;"><s:property value="getText('net.price')"/></th>
										<th style="text-align:center;"><s:property value="getText('reason.code')"/></th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="claimTranLineItems" status="itStatus">
										<tr>
											<td><s:property value="%{#itStatus.count}" />.</td>
											<td><s:property value="itemId" /><br><s:property value="deItmShrtRcpt" /></td>
											<td style="text-align:right;"><s:property value="lineQnt" /></td>
											<s:set var="lnQty" value="" />
											<td style="text-align:right;"><span
												id="registerQty_<s:property value="%{#itStatus.index}"/>">
													 <s:if
														test="%{lineQntRtn==null}">
														<s:set var="lnQty" value="%{lineQnt}" />
													</s:if> <s:else>
														<s:set var="lnQty" value="%{lineQntRtn}" />
													</s:else> <s:property value="lnQty" />
											</span></td>
											<td style="text-align:right;"><span id="unitPrice_<s:property value="%{#itStatus.index}"/>"><s:property value="itmPrnPrc" /></span></td>
											<s:set var="dscAmts" value="0"/>
											<s:set var="itmLevDisc" value="0"/>
											<s:set var="trnLevDisc" value="0"/>
											<s:set var="unitDscAmt" value="0"/>
             								<s:if test="%{claimTranDscItms != null}">
	             								<s:iterator value="claimTranDscItms" status="dscList">
	             									<s:set var="dscAmts" value="%{#dscAmts+dscAmt}"/>
	             									<s:if test="%{tyDsc == 0}">
	             										<s:set var="itmLevDisc" value="%{dscAmt}"/>
	             									</s:if>
	             									<s:if test="%{tyDsc == 1}">
	             										<s:set var="trnLevDisc" value="%{dscAmt}"/>
	             									</s:if>
	             									<s:set var="unitDscAmt" value="%{#unitDscAmt + unitDscAmt}"/>
	             								</s:iterator>
             								</s:if>
											<td style="text-align:right;"><span
												id="registerPrice_<s:property value="%{#itStatus.index}"/>">
													<s:set var="lnPrice" value="%{itmPrnPrc - #unitDscAmt}" />
													<s:if test="%{ovrdPrc != null}">
														<s:set var="lnPrice" value="ovrdPrc"/>
													</s:if> 
													<s:property value="%{getText('format.currency.args',{#lnPrice})}"/>
											</span></td>
											<td><input type="number" class="form-control" min="1"  onkeypress="return isNumberKey(event);" onInput="checkLength(14,this)" onpaste="return false;"
												name="approvedQtyList[<s:property value="%{#itStatus.index}"/>]"
												id="approvedQty_<s:property value="%{#itStatus.index}"/>"
												value="<s:property value="lnQty" />"
												style="max-width: 90px; text-align:right;" data-toggle="tooltip"
												data-content="Should not be after delivery date"
												placeholder="Approved Qty"></td>
											<td><input type="number" class="form-control" min="1" onkeypress="return isNumberKey(event);" onInput="checkLength(14,this)" onpaste="return false;"
												name="approvedPriceList[<s:property value="%{#itStatus.index}"/>]"
												id="approvedPrice_<s:property value="%{#itStatus.index}"/>"
												value=<s:property value="lnPrice"/>
												style="max-width: 100px; text-align:right;"></td>
											<td style="text-align:right;"><span
												id="discount_<s:property value="%{#itStatus.index}"/>"><s:property value="%{getText('format.currency.args',{#dscAmts})}"/></span>
											<input type="hidden"
												name="discountsList[<s:property value="%{#itStatus.index}"/>]"
												id="hiddenDiscPrice_<s:property value="%{#itStatus.index}"/>"
												value=${dscAmts}>
											<input type="hidden" name="itemLevelDiscountList[<s:property value="%{#itStatus.index}"/>]" id="itemLevelDiscount_<s:property value="%{#itStatus.index}"/>" value=${itmLevDisc}>
											<input type="hidden" name="tranLevelDiscountList[<s:property value="%{#itStatus.index}"/>]" id="tranLevelDiscount_<s:property value="%{#itStatus.index}"/>" value=${trnLevDisc}>
											<%--MAINTAIN THIS FOR CLAIM REGISTERED DISCOUNT BACKUP--%>
											<input type="hidden"
												id="itmDiscPrice_<s:property value="%{#itStatus.index}"/>"
												value=${dscAmts}>
											<input type="hidden" name="itemLevelDiscountListBck[<s:property value="%{#itStatus.index}"/>]" id="itemLevelDiscountBck_<s:property value="%{#itStatus.index}"/>" value=${itmLevDisc}>
											<input type="hidden" name="tranLevelDiscountListBck[<s:property value="%{#itStatus.index}"/>]" id="tranLevelDiscountBck_<s:property value="%{#itStatus.index}"/>" value=${trnLevDisc}>
											</td>
											<td style="text-align:right;"><span
												id="netPrice_<s:property value="%{#itStatus.index}"/>"><s:property value="%{getText('format.currency.args',{extnDscLnItm})}"/></span>
												<input type="hidden" id="netPriceBck_<s:property value="%{#itStatus.index}"/>" value="${extnDscLnItm}">
 												<input type="hidden"
												name="netPriceList[<s:property value="%{#itStatus.index}"/>]"
												id="hiddenNetPrice_<s:property value="%{#itStatus.index}"/>"
												value="${extnDscLnItm}"></td>
											<td style="text-align:center;">${returnReasonCodeMap[rcRtnMr]}</td>
										</tr>
									</s:iterator>

								</tbody>



							</table>
						</div>
						<!-- /.col -->
					</div>
					 <div class="progress" style="height: 2px;">
				<div class="progress-bar" style="width: 100%; background-color: #ADC2EE;"></div>
			</div>
					<!-- /.row -->

					<div class="row">
						<!-- /.col -->
						<div class="col-lg-4 col-md-6 col-xs-12 pull-right">

							<div class="table-responsive">
								<table class="table">
									<tbody>
										 <tr>
        						             <td style="width:50%;text-align:right;"><s:property value="getText('subtotal.label')"/>:</td>
                						     <td style="width:50%;text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b><span id="dkartSlsTot"> <s:property value="%{getText('format.currency.args',{dkartSlsTot})}"/></b>
                						     <input type="hidden"
												name="dkartSlsTot2" id="hiddendkartSlsTot"
												value="<s:property value="dkartSlsTot" />">
             							</tr>
             							<tr>
                						    <td style="text-align:right;"><s:property value="getText('discount.label')"/>:</td>
                 						    <td style="width:50%;text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;  <b><span id="dkartDiscTot"><s:property value="%{getText('format.currency.args',{dkartDiscTot})}"/></span></b></td>
											<input type="hidden"
												name="dkartDiscTot" id="hiddenTranDisctotal"
												value="<s:property value="dkartDiscTot" />">
											</td>
             						   </tr>
             							 <tr>
             							   <s:set var="totalReturnQty" value="0"/>
             							  <s:iterator  value="claimTranLineItems" status="itStatus">  
            								<s:set var="totalReturnQty" value="%{#totalReturnQty+lineQntRtn}"/>
            								</s:iterator> 
        						             <td style="width:50%;text-align:right;"><s:property value="getText('total.qty')"/>:</td>
                						     <td style="width:50%;text-align:right;"> <b><span id="totalReturnQty"><s:text name="%{#totalReturnQty}"/></b><input type="hidden"
												name="approvedQty" id="hiddenTotalReturnQuantity"
												value="<s:property value="approvedQty"/>">
											</td></b>
             							</tr>
             						 <tr>
                						   <td style="text-align:right;"><s:property value="getText('total.tax')"/>:</td>
                						   <td style="width:50%;text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;  <b><s:property value="%{getText('format.currency.args',{dkartTaxTot})}"/></b></td>
              						</tr>
										<tr style="font-size: 16px;">
											<td style="text-align: right;"><s:property value="getText('total.refund')"/>:</td>
											<td style="text-align: right;"><s:property value="getText('global.currency')"/> <b><span id="tranNettotal"><s:property value="%{getText('format.currency.args',{dkartNetTot})}"/></b></span> <input type="hidden"
												name="dkartNetTot2" id="hiddenTranNettotal"
												value="<s:property value="dkartNetTot" />">
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- modal to show if appove button is clicked and asking for confirmation @Srinivas Reddy G-->
		<div class="modal fade bs-example-modal-sm" id="approveModel"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"><s:property value="getText('_promptmsg.approveclaim')" /></h4>
					</div>
					<div class="modal-footer" style="margin:auto;max-width:300px;">
						<button type="submit" id="approveClaimButton" class="pull-left btn btn-primary center-block" data-dismiss="modal"><s:property value="getText('yes.button')" />&nbsp;</button>					
					
					
						<button type="submit" id="approveClaimButton" class="pull-right btn btn-primary center-block" data-dismiss="modal"><s:property value="getText('no.button')" />&nbsp;</button>					
					</div>
				</div>

			</div>
		</div>
		<!-- End of modal -->
							<textarea id="hiddenRejectClaimNotesTxt" name="rejectClaimNotes"
								style="display: none;"></textarea>

							<!-- <input type="button" value="Approve" class="btn  pull-left hidden-print "
								style="color: #ffffff; width: 100px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; display: inline-block;"
								id="approveButton"> -->
								
						
                         <div class="col-md-12">
                         <div class="col-md-8">
                         <s:set var="claimPerStatus" value="claimPerStatus"/>
                          <s:if test="%{#claimPerStatus=='false'}">
                          <input type="button"  value="Approve" class="btn  pull-left hidden-print " 
								style="color: #ffffff; width: 100px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; display: inline-block;"
								id="approveButton1" disabled>
								<br><br>
						<table style="width:100%;" ><tr><td style="background-color:#ff0000; padding:10px; color:#fff;">Claim can be approve only for customers with enough Available Claim Limit.<td></tr></table>
						
							
                              </s:if>
                         <s:else>
                             <input type="button" value="Approve" class="btn  pull-left hidden-print "
								style="color: #ffffff; width: 100px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; display: inline-block;"
								id="approveButton">
                          </s:else>
                         </div>
                           <div class="col-md-4">
                          <%--  <a href="<s:url action="rejectAwaitingForApproveClaim"/>">     --%>
							<button type="button" id="rejectBtn" class="btn  pull-right hidden-print"
								style="width: 100px;  background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #ffffff;">
								<s:property value="getText('reject.button')"/></button>
							<!--  </a> -->
						
                           </div>
                         </div>
	                               
	
						
						</div>
						
						<!-- /.col -->
					</div>
					<!-- /.row -->
					</form>

					<!-- this row will not appear when printing -->
					<div class="row">
						<div class="col-xs-12"></div>
					</div>
				</section>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->


		<!-- Control Sidebar -->



		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div style="position: fixed; height: auto;" class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

<!-- modal to show if the approved price is more than registered price @Srinivas Reddy G-->
		<div class="modal fade bs-example-modal-sm" id="aprovedpricemodal"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Approved price must be less than Registered price</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal">OK&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>
		<!-- End of modal -->
		
		<!-- modal to show if the approved Qty is more than registered Qty @Srinivas Reddy G-->
		<div class="modal fade bs-example-modal-sm" id="aprovedQtymodal"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Approved Qty must be less than Registered Qty</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal">OK&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>
		<!-- End of modal -->
		
		<!-- modal to show if the total approved Qty lessthan One  @Srinivas Reddy G-->
		<div class="modal fade bs-example-modal-sm" id="totalQuantityModel"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Total Approved Qty must be greater than zero</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal">OK&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>
		<!-- End of modal -->
	<!--rejectClaimNotesModal-->
	<div class="modal fade bs-example-modal-sm" id="rejectClaimNotesModal"
		data-backdrtop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">

		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<s:text name="reject.comments.label"/>						
					</h4>
				</div>
				<div class="modal-body">
					<textarea id="rejectClaimNotesTxt" class="form-control"
						style="margin: 1px; overflow: auto;"
						placeholder="Reject Claim Notes" maxlength="120"> </textarea>
				</div>
				<div class="modal-footer text-center">
					<button type="submit" class="btn btn-primary center-block"
						data-dismiss="modal" id="rejectClaimNotesBtn" disabled>
						<s:property value="getText('ok.button')"/>&nbsp;<span id="transportFeeSpinner" class="fa"
							aria-hidden="true"></span>
					</button>

				</div>
			</div>

		</div>
	</div>
	<div class="modal fade bs-example-modal-sm" id="mailsendinfo"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body text-center">
						<button type="submit" class="close" data-dismiss="modal">&times;</button>
						<h4>
							Mail has been sent successfully
						</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="submit" class="btn btn-primary center-block" data-dismiss="modal" id="mailconfirmation">
							<s:property value="getText('ok.button')" />
							&nbsp;
						</button>
					</div>
				</div>

			</div>
		</div>



<div class="modal fade bs-example-modal-sm" id="validatepopover"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body text-center">
						<button type="submit" class="close" data-dismiss="modal">&times;</button>
						<h4>
							Check Atleast One checkbox
						</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="submit" class="btn btn-primary center-block" data-dismiss="modal" >
							<s:property value="getText('ok.button')" />
							&nbsp;
						</button>
					</div>
				</div>

			</div>
		</div>
<div class="modal fade bs-example-modal-sm" id="errorsendingmail"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body text-center">
						<button type="submit" class="close" data-dismiss="modal">&times;</button>
						<h4>
							Failure Sending Mail
						</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="submit" class="btn btn-primary center-block" data-dismiss="modal" id="mailconfirmation">
							<s:property value="getText('ok.button')" />
							&nbsp;
						</button>
					</div>
				</div>

			</div>
		</div>

	<script type="text/javascript">
    	$('#claim').addClass('active');
    	$('#approveclaim').addClass('active');
    	
  	</script>
  	
  	<script>
		function windowPrint() {
			window.print();
		}
		$('a[rel=popover]').popover({
	    html: 'true',
	placement: 'bottom'
	})
	</script>
	
	
	<script type="text/javascript">
		function emailSubmit(){   
		var employeecheck=$('#employee').is(":checked");
        var  customer=$('#customr').is(":checked");
		var deptheadcheck=$('#departmentHead').is(":checked");
		var empsalescheck=$('#salesAgent').is(":checked");
		var dataentrycheck=$('#DataEntryOptr').is(":checked");
       	var claimId=document.getElementById("claimId").innerText;
        var custom=document.getElementById("custommails").value;
	   	   
	   if(employeecheck==false && customer == false && deptheadcheck==false && empsalescheck==false && dataentrycheck==false && (custom==null || custom=="") ){
	    $("#validatepopover").modal("show");
	   }
	   else{			   
			$.ajax({
				url : "claimdetailmail",
				type : "POST",
				data: {
				claimId : claimId,
				loginEmp : employeecheck, 
				customer : customer,
				departmentHead : deptheadcheck,
				salesAgent : empsalescheck,
				DataEntryOptr : dataentrycheck,
				custommail : custom},
			    statusCode:{		
				 200:function(){
				 $("#mailsendinfo").modal("show");
				 $("#btpopover").popover("hide"); 
				 },
				 404:function()	{
				 $("#errorsendingmail").modal("show");
				 $("#btpopover").popover("hide");
				 }					
				} 
				
				}); 
				
				}	   
      }	
    $(document).on('click', '#emailSubmitbtn', function(){
	emailSubmit(); 			 
	});
	$(document).on('click', '#cancelmail', function(){	
	$("#btpopover").popover("hide");
	});
   
 </script>
</body>
</html>