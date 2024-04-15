<%@ page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="utf-8"%>
    <!-- changed by @ jagadish because this (charset=ISO-8859-1 And  pageEncoding="ISO-8859-1) does not supporting
	to display Arabic language.  -->
<%@taglib prefix="s" uri="/struts-tags"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store, must-revalidate");
	response.setDateHeader("Expires", -1);
	response.setHeader("Pragma", "no-cache ");
%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SDS | Claim Details</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="assets/css/popup.css">
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
    $(function() {
    	
    	//when the claim belongs to invoice cancel
    	var invoiceCancelFlag = "${invoiceCancelFlag}";
    	console.log(invoiceCancelFlag);
		if(invoiceCancelFlag == "Y"){
			$("[id^=acceptedPrice_]").prop('readonly', true);
		}
	    else{
	    	$("[id^=acceptedPrice_]").prop('readonly', false);
	    }
  //when accept button is clicked
	$("#acceptButton").click(function(e) {
	
		$("#acceptModel").modal('show');
	}); 
	 
	//when approveClaimModal "OK" button is clicked
	$("#acceptClaimButton").click(
			function(e) {
				$("#saveAcceptClaim").submit();
			});
    
    $(function(e){
  
    //fires when accepted price changed
    $("body").on("input","[id^='acceptedPrice_']",function(e){
    var element = $(this);
    var elementId = element.attr("id");
    var elementIdArr = elementId.split("_");
    var rowIndex = elementIdArr[1];
    var receivedQty = $("#receiveQty_"+rowIndex).html();
    var accepedPrice = $(this).val();
    var CurrencyFormat="<s:property value="getText('format.currency')"/>";
    var netPrice = accepedPrice * Number(receivedQty);
    var netPrice1=format(CurrencyFormat,netPrice);
    
 	var digit = format(CurrencyFormat,parseFloat('0.0'));
 	var decimals = digit.split('.')[1].length;
    
    //round "netPrice" to two decimals
	//netPrice = Math.round(netPrice*100)/100;
    netPrice = netPrice.toFixed(decimals);
	$("#hiddenNetPrice_"+rowIndex).val(netPrice);
	$("#netPrice_"+rowIndex).html(netPrice1);
	
	var rowCount = $('#awaitingForAcceptTable >tbody >tr').length;
	var tranNettotal1=0;
	var dkartSlsTot1=0;
	//var dkartDscTot = $('#dkartDscTot').text().replace(/,/g , "");
	var dkartDscTot = parseFloat('0.0');
	dkartDscTot = Number(dkartDscTot);
	for(var i=0;i<rowCount;i++)
	{
	//var netprice = $("#netPrice_"+i).text().replace(',', '');
	var netprice = $("#netPrice_"+i).text().replace(/,/g , "");
	 tranNettotal1 = tranNettotal1 +Number(netprice);
	 dkartSlsTot1 = dkartSlsTot1 +Number(netprice);
	 
	 // line discount to calculate total discount
	 var disc = $('#discount_'+i).text().replace(/,/g, "");
	 dkartDscTot = dkartDscTot + Number(disc);
	}
	//round "tranNettotal" to two decimals
	//tranNettotal1=Math.round(tranNettotal1*100)/100;
	tranNettotal1 = tranNettotal1.toFixed(decimals);
	var tranNettotal=format(CurrencyFormat,tranNettotal1);
	$("#tranNettotal").html(tranNettotal);
	$("#hiddenTranNettotal").val(tranNettotal1);
	//round "subTotal" to two decimals
	//dkartSlsTot1 =Math.round(dkartSlsTot1*100)/100;
	dkartSlsTot1 = Number(dkartSlsTot1.toFixed(decimals));
	if(dkartDscTot != 0 && tranNettotal1 != 0){
		dkartSlsTot1 += dkartDscTot;
	}else{
		$('#dkartDscTot').html('0');
	}
	var dkartSlsTot=format(CurrencyFormat,dkartSlsTot1);
	$('#dkartDscTot').html(format(CurrencyFormat,dkartDscTot));
	$("#dkartSlsTot").html(dkartSlsTot);
	$("#hiddendkartSlsTot").val(dkartSlsTot1);
    });
    
    });
    
    });
     </script>
</head>
<body class="skin-blue sidebar-mini">
<!-- <form action="bookorder" method="POST"> -->
<div class="wrapper" >

<s:include value="menubar.jsp" />
<s:include value="topbar.jsp" />

	
<!-- Content Wrapper. Contains page content -->
<div style="min-height: 595px;" class="content-wrapper">

 <!-- Your Page Content Here -->
    <section class="invoice">
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
        <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><s:property value="getText('claim.details.help_text')"/></small>
      <div class="row">
        <div class="col-xs-12">
          <h2 style="color: #226e71;">
             <s:property value="getText('claim.details')"/>
              <div class="pull-right"><h5>
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

                    <a href="javascript:void(0);" onclick="windowPrint()"class="pull-right"><i class="fa fa-print"></i><s:property value="getText('print.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a>
                  <a href="acceptClaimPDFDownload?claimID=<s:property value="claimId"/>"><i class="fa fa-save"></i><s:property value="getText('save.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a></div>
<!--            <small class="pull-right">Date: 2/10/2014</small>-->
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
        <div class="col-sm-4 invoice-col">
        <table style="width:100%;" class="table">  
        <tr><td style="text-align:right;"><s:property value="getText('claim.id')"/>:</td><td><b><span id="claimId"><s:property value="claimId"/></span></b></td></tr>
         <tr><td style="text-align:right;width:50%;"><s:property value="getText('claim.date')"/>:</td><td><b><s:date name="claimedDate" format="%{getText('format.date')}"/></b></td></tr> 
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
          <table style="width:100%;" class="table">
            <tr><td style="text-align:right;"><s:property value="getText('invoice.no')"/>:</td><td><b><s:property value="invoicenNo"/></b></td></tr>
        <tr><td style="text-align:right;width:50%;"><s:property value="getText('status.label')"/>:</td><td><b><s:property value="claimStatus"/></b></td></tr>
        <tr><td style="text-align: right;"><s:property value="getText('sales.agent')"/>:</b></td><td><b><s:property value="salesAgentName" /></b></td></tr>
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">  
          <table style="width:100%;" class="table">
           <tr><td style="text-align:right;"><s:property value="getText('customer.id')"/>:</td><td><b><s:property value="custId"/></b></td></tr>
              <tr><td style="text-align:right;"><s:property value="getText('customer.name')"/>:</td><td><b><s:property value="customerName"/></b></td></tr>  
              <tr><td style="text-align:right;"><s:property value="getText('claim.pickup.addrss')"/> :</td><td><b><s:property value="site_address"/></b></td></tr>
        </table>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
<form action="saveAcceptClaim" id="saveAcceptClaim" method="post">
      <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped" id="awaitingForAcceptTable" style="margin-bottom: 2px;">
            <thead>
            <tr style="background-color:#ADC2EE;">
              <th><s:property value="getText('table.head.SNo')"/></th>
              <th><s:property value="getText('table.head.item')"/></th>
                <th style="text-align:right;"><s:property value="getText('sold.qty')"/></th>
                <th style="text-align:right;"><s:property value="getText('registered.qty')"/></th>
               
              <th style="text-align:right;"><s:property value="getText('approved.qty')"/></th>
              <th style="text-align:right;"><s:property value="getText('received.qty')"/></th>
              <th style="text-align:right;"><s:property value="getText('accepted.price')"/></th>
              <th style="text-align:right;"><s:property value="getText('table.head.disc')"/></th>
                <th style="text-align:right;"><s:property value="getText('net.price')"/></th>
                <th style="text-align:center;"><s:property value="getText('reason.code')"/></th>
                <th style="text-align:center;" title="Warehouse Disposition Code">Disp. Code</th>
            </tr>
            </thead>
            <tbody>
				<s:iterator value="claimTranLineItems" status="itStatus">
				
										
										<!-- sharanya -->
										<s:if test="%{apprClaimQty!=whReceiveQty}">
										<tr style="color:blue;">
										</s:if>
										<s:else>
										<tr style="color:black;">
									</s:else>
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
               <td style="text-align:right;"><s:property value="apprClaimQty"/></td>
               <td style="text-align:right;"><span id="receiveQty_<s:property value="%{#itStatus.index}"/>"><s:property value="whReceiveQty"/></span></td>
               <td><input type="text" style="text-align:right;max-width:80px;" size="1;" class="form-control" onkeypress="return isNumberKey(event);" onInput="checkLength(14,this)" onpaste="return false;" name ="acceptedPriceList[<s:property value="%{#itStatus.index}"/>]" id="acceptedPrice_<s:property value="%{#itStatus.index}"/>"  value="<s:property value="apprClaimPrice" />" ></td>
              <%-- <s:set var="dscAmts" value="0"/>
	           <s:if test="%{claimTranDscItms != null}">
		           <s:iterator value="claimTranDscItms" status="dscList">
		           		<s:set var="dscAmts" value="%{#dscAmts+dscAmt}"/>
		           </s:iterator>
	           </s:if>
	           <s:set var="perLnDisc" value="%{#dscAmts / apprClaimQty}"></s:set>  --%>
	            <s:set var="dscAmts" value="0"/>
	           <s:if test="%{claimTranDscItms != null}">
	           <s:if test="%{claimTranDscItms.size() == 1}">
	           <s:set var="dscAmts" value="%{claimTranDscItms.get(0).dscAmt}"/>
	           </s:if>
	           <s:else>
	            <s:iterator value="claimTranDscItms" status="dscList">
	           <s:set var="dscAmts" value="%{#dscAmts+dscAmt}"/>
	           </s:iterator>
	           </s:else>
	           </s:if>
               <td style="text-align:right;"><span
												id="discount_<s:property value="%{#itStatus.index}"/>"><s:property value="%{getText('format.currency.args',{#dscAmts})}"/></span>
											<%-- <input type="hidden"
												name="discountsList[<s:property value="%{#itStatus.index}"/>]"
												id="hiddenDiscPrice_<s:property value="%{#itStatus.index}"/>"
												value=%{#dscAmts}></td> --%>
               <td style="text-align:right;"><span id="netPrice_<s:property value="%{#itStatus.index}"/>"><s:property value="%{getText('format.currency.args',{extnDscLnItm})}"/></span>
                <input type="hidden"  name="netPriceList[<s:property value="%{#itStatus.index}"/>]" id="hiddenNetPrice_<s:property value="%{#itStatus.index}"/>" value=${extnDscLnItm}>
               	<%-- <input type="hidden" name="discountsList[<s:property value="%{#itStatus.index}"/>]"	id="hiddenDiscPrice_<s:property value="%{#itStatus.index}"/>" value=%{#dscAmts}></td> --%>
               <td style="text-align:center;">${returnReasonCodeMap[rcRtnMr] }</td>
               <td class="text-center"><b><i><s:property value='dispostionCode'/></b></i></td>
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
                <td style="width:50%;text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b> <b><span id="dkartSlsTot"> <s:property value="%{getText('format.currency.args',{dkartSlsTot})}"/></b>
                						     <input type="hidden"
												name="dkartSlsTot2" id="hiddendkartSlsTot"
												value="<s:property value="dkartSlsTot" />"></tr>
           <tr>
          <%--  <tr>
             							   <s:set var="totalReturnQty" value="0"/>
             							  <s:iterator  value="claimTranLineItems" status="itStatus">  
            								<s:set var="totalReturnQty" value="%{#totalReturnQty+lineQntRtn}"/>
            								</s:iterator> 
        						             <td style="width:50%;text-align:right;"><s:property value="getText('total.qty')"/>:</td>
                						     <td style="width:50%;text-align:right;"> <b><span id="totalReturnQty"><s:text name="%{#totalReturnQty}"/></b><input type="hidden"
												name="approvedQty" id="hiddenTotalReturnQuantity"
												value="<s:property value="approvedQty"/>">
											</td></b>
             							</tr> --%>
            <%-- <td style="text-align:right;"><s:property value="getText('discount.label')"/>:</td>
               <td style="width:50%;text-align:right;"><b>0</b></td> --%>
               </tr>
             <tr>
              <td style="text-align:right;"><s:property value="getText('total.discount')"/>:</td>
              <td style="width:50%;text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;  <b><span id="dkartDscTot"><s:property value="%{getText('format.currency.args',{dkartDscTot})}"/></span></b></td>
           </tr>
          <tr>
              <td style="text-align:right;"><s:property value="getText('total.tax')"/>:</td>
              <td style="width:50%;text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;  <b><s:property value="%{getText('format.currency.args',{dkartTaxTot})}"/></b></td>
           </tr>
             <tr style="font-size:16px;">
                <td style="text-align:right;"><s:property value="getText('total.refund')"/>:</td>
                 <td style="width:50%;text-align:right;"><s:property value="getText('global.currency')"/> <b><span id="tranNettotal"><s:property value="%{getText('format.currency.args',{dkartNetTot})}"/></b></span>
                <input type="hidden" name="dkartNetTot2" id="hiddenTranNettotal" value="<s:property value="dkartNetTot" />">
                </td>
              </tr>
            </tbody>
             </table>
          </div> 
                  
	<!-- modal to show if the the accept button is clicked @Srinivas Reddy G-->
		<div class="modal fade bs-example-modal-sm" id="acceptModel"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						 <button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title"><s:property value="getText('_promptmsg.apcceptclaim')" /></h4>
					</div>
					<div class="modal-footer" style="margin:auto;max-width:300px;">
					
						<button type="submit" id="acceptClaimButton" class="pull-left btn btn-primary center-block"><s:property value="getText('yes.button')" />&nbsp;</button>	
						<button type="submit" class="pull-right btn btn-primary center-block" data-dismiss="modal"><s:property value="getText('no.button')" />&nbsp;</button>					
					</div>
				</div>

			</div>
		</div>
		<!-- End of modal -->

           <input type="button" value="Accept" class="btn  pull-right hidden-print"
								style="color:#ffffff; width:100px;margin-top:5px; background:    #3d85c6;
background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
background:    linear-gradient(#1770c1, #073763 50%, #1770c1);  
color:         #fff;
display:       inline-block;" id="acceptButton" />

       
                
<%--           <a href="<s:url action="rejectAwaitingForAcceptClaim"/>">    
        <button type="button" class="btn  pull-right" style="width:100px;background:    #666;margin-top:5px;
background:    -webkit-linear-gradient(#717171, #262626 20%, #717171);
background:    -linear-gradient(#717171, #262626 20%, #717171);
color:         #fff;">
            Reject
          </button>
        </a> --%>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    
</form>
      <!-- this row will not appear when printing -->
      <div class="row">
        <div class="col-xs-12">
          
          
        </div>
      </div>
   

</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		
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
    	$('#claim').addClass('active');
    	$('#acceptclaim').addClass('active');
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
<!--  validation for input field--Sharanya -->
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
	
//validation to accept only numbers(0-9),->,<-,backspace	
// modified code to allow decimals:@laxmikanth
function isNumberKey(evt)
{
	var currencyFormat = "<s:property value="getText('format.currency')"/>";
	var digit = format(currencyFormat,parseFloat('0.0'));
	var allowedDecimals = digit.split('.')[1].length;
	var charCode = (evt.which) ? evt.which : event.keyCode;
	var targettedId = evt.target.id;
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
	if (charCode == 47) return false; // NOT TO ALLOW '/' SYMBOLE
		
	if (charCode > 31 && (charCode < 46  || charCode > 57))
      return false;
	
	if(dot >= 1 && charCode == 46){
		return false;
	}
   return true;
}
</script>
 <%-- <script type="text/javascript">
$(document).ready(function(){
    var claimTranLineItem = "${claimTranLineItems}";
    alert(claimTranLineItem);
    var claimTranDisc = "${claimTranLineItems.claimTranDscItms}"
    alert(claimTranDisc);
    alert(claimTranLineItem);
});
</script> 
 --%>
</body>
</html>