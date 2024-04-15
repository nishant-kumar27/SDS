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
<title>SDS | Invoice Detail</title>
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
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="assets/css/Customizations.css">

<!-- REQUIRED JS SCRIPTS -->

<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
<!-- jQuery 2.2.0 -->



<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<script type="text/javascript">
	$(document).ready(function() {
		$('#invcncl').bind("click", function() {
			$('#confirmInvoiceCancel').modal('show');
			return false;
		});
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
		<s:include value="menubar.jsp" />
		
  <!-- Content Wrapper. Contains page content -->
  <div style="min-height: 595px;" class="content-wrapper">
   
    

    <!-- Main content -->
    <section class="content">
<!--     <div class="error-content hidden-lg hidden-md">
          

         
          <form class="search-form" action="Customer_Page_b.html">
            <div class="input-group">
              <input type="text" placeholder="Customer Search" class="form-control" name="search">

              <div class="input-group-btn">
          <a href="Customer_Page_b.html"><button class="btn bg-blue" name="submit"><i class="fa fa-search"></i>
                </button></a>
              </div>
            </div>
            /.input-group
          </form>
     <br><br>
    </div> -->
      <!-- Your Page Content Here -->
    <section class="invoice">
    
      <!-- title row -->
       <span class="logo-lg visible-print-inline"><img
			src="assets/SDSlogo.png"></span> <span class="logo-mini"><b></b></span>
      
        <small class="pull-right hidden-print" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><s:property value="getText('invoice.details.help_text')"/></b></small>
      <div class="row">
        <div class="col-xs-12">
        	<s:url action="downloadInvoice" var="downloadInvoiceUrl">
				<s:param name="invoiceID" value="%{invDetail.arInvNum}" />
			</s:url>
			
			<s:url action="downloadLPO" var="downloadLPOUrl">
				<s:param name="orderID" value="%{invDetail.orderNum}" />
			</s:url>
			
				<s:actionmessage theme="bootstrap" cssClass="alert alert-success"/>
          <h2 style="color: #226e71;">
             <s:property value="getText('invoice.details')"/>
              <div class="pull-right hidden-print"><h5>
                     <%--     <a href="PrintnewInvoice?invoiceID=<s:property value="invDetail.arInvNum"/>" style="text-decoration:none" class="pull-right"><i class="fa fa-envelope"></i> <s:property value="getText('mail.label')"/> &nbsp;</a>    --%>
                     <a href="javascript:void(0);" class="pull-right" rel="popover" id="btpopover" data-content='
                					
                							<div><b><input type="checkbox" id="employee" name="employee" class="form-check-input"> <label for="employee"><s:property value="getText('login.employee')"/></label></div>
											<div><b><input type="checkbox" id="customr" name="customr" class="form-check-input"> <label for="customr"><s:property value="getText('customer.label')"/></label></div>
											<div><b><input type="checkbox" id="departmentHead" name="departmentHead" class="form-check-input"> <label for="departmentHead"><s:property value="getText('dept.head')"/></label></div>
    										<div><b><input type="checkbox" id="salesAgent" name="salesAgent" class="form-check-input"> <label for="salesAgent"><s:property value="getText('sales.agent')"/> </label></div>
    										<div><b><input type="checkbox" id="DataEntryOptr" name="DataEntryOptr" class="form-check-input"> <label for="DataEntryOptr"><s:property value="getText('dataentry.operator')"/> </label></div>
    										<div><label>Mail :</label><input type="text" id="custommails" name="check" /></div><br>
                							<div style="text-align:right;"><button type="button"  id="emailSubmitbtn">Submit</button>
                							<button type="button" data-dismiss="modal" id="cancelmail">Cancel</button></div>
                							' data-placement="top" data-original-title="Select"><i class="fa fa-envelope"></i> <s:property value="getText('mail.label')"/>  &nbsp;</a>
                    <a target="_blank" href="PrintInvoice?invoiceID=<s:property value="invDetail.arInvNum"/>" style="text-decoration:none" class="pull-right"><i class="fa fa-print"></i><s:property value="getText('print.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                  <a target="_blank" id="dwnldLPO" href="<s:property value="#downloadInvoiceUrl"/>" style="text-decoration:none" class="pull-right"><i class="fa fa-save"></i> <s:property value="getText('save.label')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </a></h5></div>
<!--            <small class="pull-right">Date: 2/10/2014</small>-->
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
      
        <div class="col-sm-4 invoice-col">
        <table style="width:100%;" class="table">
            <tr><td style="text-align:right;"><s:property value="getText('invoice.no')"/>:</b></td><td><b><span
					id="InvoiceNo"><s:property value="invDetail.arInvNum"/></span></b></td></tr>
			<tr><td style="text-align:right;"><s:property value="getText('billing.date')"/>:</b></td><td><b><span id="invDate"><s:date name="invDetail.arInvDate" format="%{getText('format.date')}" /></span></b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('status.label')"/>:</b></td><td><b><span id="InvStatus">
																		<s:if test="invDetail.invStatus=='0'"><s:property value="getText('closed_inv')"/></s:if>
																		<s:elseif test="invDetail.invStatus=='1'"><s:property value="getText('open_inv')"/></s:elseif>
																		<s:else><s:property value="getText('unknown_inv')"/></s:else>
																		</span></b></td></tr>
		    <tr><td style="text-align:right;"><s:property value="getText('age.label')"/>:</b></td><%--  <td><s:date name="invoices.arInvDate"
			format="%{getText('format.date')}" nice="true" /></td> --%><td><b></span> <s:property value="age"/></b></td></tr>
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
          <table style="width:100%;" class="table">
          	<tr><td style="text-align:right;"><s:property value="getText('customer.id')"/> :</b></td><td><b><span id="customerId"><s:property value="orders[0].customer.customerHeader.customerHeaderPK.custId"/></span></b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('customer.name')"/>:</b></td><td><b><span id="CustomerName"><s:property value="orders[0].customer.customerHeader.ctNm"/></span></b></td></tr>        
       		<tr><td style="text-align:right;"><s:property value="getText('customer.segment')"/>:</b></td><td><b><span id="CustomerSegment"><s:property value="orders[0].customer.customerSegmentID"/></span></b></td></tr>
        	<tr><td style="text-align:right;"><s:property value="getText('LPO.label')"/>:</b></td><td><b><span
					id="LPO"><s:property value="orders[0].ordTranSum.custLpoNum"/></span></b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('lpo.date')"/>:</b></td><td><b><span id="LPODate"><s:date name="orders[0].ordTranSum.custLpoDate" format="%{getText('format.date')}" /></span></b></td></tr>
 
        
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
          <table style="width:100%;" class="table">
            <tr><td style="text-align:right;width:50%;"> <s:property value="getText('order.id')"/>:</b></td><td><b><span
					id="OrderID"><s:property value="invDetail.orderNum"/></span></b></td></tr>
			<tr><td style="text-align:right;"><s:property value="getText('order.date')"/>:</b></td><td><b><span id="OrderDate"><s:date name="orderDateInfo" format="%{getText('format.date')}" /></span></b></td></tr>
          	<tr><td style="text-align:right;"><s:property value="getText('shipping.address')"/>:</b></td><td style="word-break: break-word;"><address><b>
               <s:property value="shipping"/>
               </address></b></td></tr>   
            <tr align="right" ><td  class="visible-print-inline" style="text-align:right;"><s:property value="getText('CRN.label')"/>:</td>
          <td  class="visible-print-inline"><b><s:i18n name="retailsols.struts.invoiceText" ><s:text name="CRN" /></s:i18n></b></td></tr>  
        </table>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped" style="margin-bottom: 2px;">
            <thead>
            <tr style="background-color:#ADC2EE;">
              <th><s:property value="getText('table.head.SNo')"/></th>
              <th><s:property value="getText('table.head.item')"/></th>
              <th class="text-right"><s:property value="getText('order.qty')"/></th>
              <th class="text-right"><s:property value="getText('table.head.vpn')"/></th>
              <th class="text-right"><s:property value="getText('price.head')"/></th>
              <th class="text-right"><s:property value="getText('discount.amt')"/></th>
              <th class="text-right"><s:property value="getText('table.head.tax')"/></th>
              <th class="text-center"><s:property value="getText('sales.agent')"/></th>
			  <s:if test="orders[0].scOrd!=7">
					<th style="text-align: right;"><s:property
							value="getText('shipped.qty')" /></th>
			  </s:if>
			  <s:else>
					<th style="text-align: right;"><s:property
							value="getText('delivered.qty')" /></th>
			  </s:else>
			 <th class="text-right"><s:property value="getText('total.label')"/>(<s:property value="getText('global.currency')"/>)</th>
            </tr>
            </thead>
         <%-- <tbody id="orderDetail">
         <s:set var = "breakLoop" value = "%{false}"/>
         <s:set var="totalDisc" value="%{0}"></s:set>
           <s:iterator value="orders[0].ordTranLineItems"  status="itStatus">
           
         <s:iterator value="invDetail.invQtyShp">
             <s:if test="[1].itemId==itemId">
            <tr>
            <td><s:property value="%{#itStatus.index+1}"/>.</td>
      <td><s:property value="itemId"/><br><s:property value="deItmShrtRcpt"/></td>
            <td><s:property value="lineQnt"/></td>
            
            <s:if test="priceOverRideFlag==1"> <td><s:property value="overRidePrice"/></td> </s:if>
            <s:else><td><s:property value="itmPrnPrc"/></td></s:else>
            
            
            
             <s:set var="discountedItems" value="%{ordTranDscItms[0].dscAmt}"/>
              <s:if test="%{#discountedItems!='0'}">
             <td><s:property value="ordTranDscItms[0].dscAmt"/></td>
             <s:set var="totalDisc" value="%{#totalDisc+#discountedItems}"></s:set>
             </s:if>        
             <s:else>
             <td><s:property value="%{0}"/></td>
             </s:else>
      
           <td><s:property value="vatLnItmRtn"/></td>
            <td><s:property value="SalesAgents[#itStatus.index]"/></td>
      
    <s:set var="shpdqty" value="invDetail.invQtyShp[0].shpQty"/>
    <td><s:property value="shpdqty"/></td>
    
                <s:set var="invLinItmTotal" value="%{#shpdqty*itmPrnPrc-#discountedItems}"/>
             <td><s:property value="invLinItmTotal"/></td>
       
             <td><s:property value="extnDscLnItm"/></td> 
    
      </tr>
      <s:set var = "breakLoop" value = "%{true}"/>
      </s:if>
      </s:iterator>
      </s:iterator>
            </tbody> --%>
            
            <tbody id="orderDetail">
             <s:iterator value="orders[0].ordTranLineItems"  status="itStatus">
            <tr>
            <td>
      <s:property value="%{#itStatus.index+1}"/>.
      </td>
      <td>
       <s:property value="itemId"/><br><s:property value="deItmShrtRcpt"/>
       
	      <s:iterator value="ordTranDscItms" status="lineId">
			<s:set var="resncds" value="orderTran.getReasonCodes()['Discount']"></s:set>
			
			<s:if test="prmId==null && tyDsc==0 && dscPer>0">
				<div style="padding-left:10px; display: <s:property value="%{((dscPer==null)?'none':'block')}" />">
					I_<s:property value="%{getAllDiscountReasnCode[discReasonCode]}"/> <small>(%)</small>: 
					<s:property value="dscPer" />
				</div>
			</s:if>
			<s:elseif test="prmId==null && tyDsc==0 && dscAmt>0">
				<div style="padding-left:10px; display: <s:property value="%{((dscAmt==null)?'none':'block')}" />">
					I_<s:property value="%{getAllDiscountReasnCode[discReasonCode]}"/> <small>(Amt)</small>: 
					<s:property value="dscAmt" />
				</div>
			</s:elseif>
			<s:elseif test="prmId==null && tyDsc==1 && dscPer>0">
				<div style="padding-left:10px; display: <s:property value="%{((dscPer==null)?'none':'block')}" />">
					T_<s:property value="%{getAllDiscountReasnCode[discReasonCode]}"/> <small>(%)</small>: 
					<s:property value="dscPer" />
				</div>
			</s:elseif>
			<s:elseif test="prmId==null && tyDsc==1 && dscAmt>0">
				<div style="padding-left:10px; display: <s:property value="%{((dscAmt==null)?'none':'block')}" />">
					T_<s:property value="%{getAllDiscountReasnCode[discReasonCode]}"/> <small>(Amt)</small>: 
					<s:property value="dscAmt" />
				</div>
			</s:elseif>
			<s:elseif test="prmId!=null && tyDsc==1 && dscAmt>0">
				<div style="padding-left:10px; display: <s:property value="%{((dscAmt==null)?'none':'block')}" />">
					P_<s:property value="prmDesc"/> <small>(Amt)</small>: 
					<s:property value="dscAmt" />
				</div>
			</s:elseif>
			<s:elseif test="prmId!=null && tyDsc==0 && dscAmt>0">
				<div style="padding-left:10px; display: <s:property value="%{((dscAmt==null)?'none':'block')}" />">
					P_<s:property value="prmDesc"/> <small>(Amt)</small>: 
					<s:property value="dscAmt" />
					</div>
			</s:elseif>
			<s:elseif test="prmId!=null && tyDsc==1 && dscPer>0">
				<div style="padding-left:10px; display: <s:property value="%{((dscAmt==null)?'none':'block')}" />">
					P_<s:property value="prmDesc"/> <small>(%)</small>: 
					<s:property value="dscPer" />
				</div>
			</s:elseif>
			<s:elseif test="prmId!=null && tyDsc==0 && dscPer>0">
				<div style="padding-left:10px; display: <s:property value="%{((dscAmt==null)?'none':'block')}" />">
					P_<s:property value="prmDesc"/> <small>(%)</small>: 
					<s:property value="dscPer" />
					</div>
			</s:elseif>
		</s:iterator>
      </td>
            <td class="text-right">
             <s:property value="lineQnt"/>
      </td>
       <td class="text-right">
             <s:property value="registryId"/>
      </td>
            <td style="text-align:right;">
            <s:if test="overRidePrice != null">
            	<s:property value="%{getText('format.currency.args',{overRidePrice})}"/>
            </s:if>
            <s:else>
            	<s:property value="%{getText('format.currency.args',{itmPrnPrc})}"/>
            </s:else>
      </td>
      
      
      <%--              <s:if test="%{#ordTranDscItms[0].dscPer>0}">
             <td><s:property value="ordTranDscItms[0].dscPer"/>  </td>
             </s:if>
             <s:else>
              <td><s:property value="%{0}"/> </td>
             </s:else> --%>
            
             <%--  <s:if test="<s:property value="%{ordTranDscItms[0].dscAmt}"/>">
             <td><s:property value="ordTranDscItms[0].dscAmt"/> </td>
             </s:if>
             <s:else>
             <td><s:property value="%{0}"/></td>
             </s:else> --%>
             <%-- <s:set var="discountedItems" value="%{ordTranDscItms[0].dscAmt}"/>
              <s:if test="%{#discountedItems>0}">
             <td class="text-right"><s:property value="%{getText('format.currency.args',{ordTranDscItms[0].dscAmt})}"/></td>
             </s:if>        
             <s:else>
             <td class="text-right"><s:property value="%{0}"/></td>
             </s:else> --%>
     		<s:set var="dscAmts" value="0"/>
				<s:if test="%{ordTranDscItms != null}">
					<s:iterator value="ordTranDscItms" status="dscList">
						<s:set var="dscAmts" value="%{#dscAmts+dscAmt}"/>
					</s:iterator>
				</s:if>
			<td class="text-right"><s:property value="%{getText('format.currency.args',{#dscAmts})}"/></td>
            <td class="text-right"><s:property value="%{getText('format.currency.args',{vatLnItmRtn})}"/></td>
            <td class="text-center"><s:property value="SalesAgents[#itStatus.index]"/></td>
      
      <%-- <s:if test="invDetail.invStatus=='0'">
      <td class="text-right"><s:property value="lineQnt"/></td></s:if>
                   
       <s:else><td class="text-right"><s:property value="%{0}"/> </td></s:else> --%>
			<s:set var="deliveredQty" value="" />
			<s:if test="lineQnt!=null && showDeliveredQuantity">
			<s:set var="deliveredQty" value="lineQnt" />
			</s:if>
			<s:else>
			<s:set var="deliveredQty" value="0" />
			</s:else>
			<td style="text-align: right;"><s:property
			value="deliveredQty" /></td>

             <td class="text-right"> <s:property value="%{getText('format.currency.args',{extnDscLnItm})}"/></td>
   					
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
              <tbody><tr>
                <td class="text-right" style="width:50%"><s:property value="getText('subtotal.label')"/>:</td>
                <td class="text-right"><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b><span id="Subtotal"><s:property value="%{getText('format.currency.args',{orders[0].ordTranSum.dkartSlsTot})}"/></span></b></td>
                  </tr>
              <tr>
                <td class="text-right"><s:property value="getText('total.discount')"/>:</td>
                <td class="text-right"> <span id="Discount"><b><s:property value="%{getText('format.currency.args',{orders[0].ordTranSum.dkartDscTot})}"/></b></span></td>
              </tr>
              <tr>
                <td class="text-right"><s:property value="getText('total.tax')"/>:</td>
                <td class="text-right"> <span id="TotalTax"><b><s:property value="%{getText('format.currency.args',{orders[0].ordTranSum.dkartTaxTot})}"/></b></span></td>
              </tr>
              <tr >
                <td class="text-right"><s:property value="getText('table.head.total')"/>:</td>
                <td class="text-right"><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b> <span id="TotalInvAmt"><s:property value="%{getText('format.currency.args',{invDetail.invAmount})}"/></span></b></td>
              </tr>
                  <tr>
                  <s:set var="totalInvamt" value="invDetail.invAmount"/>
                  <s:set var="totalPenamt" value="invDetail.invPendAmount"/>
                  <td class="text-right" style="width:50%;"><s:property value="getText('received.amt')"/>:</td>
                  <td class="text-right"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;
				                 
                   <span id="ReceivedAmt"><b><%-- <s:property value="%{#totalInvamt-#totalPenamt}"/> --%>
                  <s:property value="%{getText('format.currency.args',{#totalInvamt-#totalPenamt})}"/></b></span></td></tr>
                  <tr>
                <td class="text-right"><s:property value="getText('balance.due')"/>:</td>
                <td class="text-right"><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b> <span id="BalanceDue"><s:property value="%{getText('format.currency.args',{invDetail.invPendAmount})}"/></span></b></td>
              </tr>
                  <tr class="hidden-print">
                <td class="text-right"><s:property value="getText('amt.being.paid')"/>:</td>
                <td><b><input style="text-align:right;" id="amtBeingPaid" style="max-width:100%;" type="text"  value="0.00" class="form-control"></td></b>
                </tr>
            </tbody></table>
          </div>
          </div>
          </div>
      <form action="recordPayment" name="hidden_order_data" method="post">
    <input type="hidden" name="amtBeingPaid"/>
    <input type="hidden" name="customerId"/>
    <input type="hidden" name="invoiceNo"/>
     </form>
<!-- REQUIRED JS SCRIPTS -->

  
  
<%-- 			<button id="paymnt" onclick="myFunction()" type="button" class="btn  pull-right hidden-print" style="color:#ffffff; width:140px;margin-top:5px; background:    #3d85c6;
				background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
				background:    linear-gradient(#1770c1, #073763 50%, #1770c1);  
				color:         #fff;
				display:       inline-block;">
            <s:property value="getText('record.payment.button')"/>
          </button>  --%>
        <!--   uncommented the above payment button enable and comment the below payment button -->
        	<div class="row">
        	<div class="col-lg-6 pull-right">
        	<button  onClick="history.go(-1);return true;" type="button" class="btn  pull-right hidden-print" style="color:#ffffff; width:100px;margin-top:5px; background:    #3d85c6;
			background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
			background:    linear-gradient(#1770c1, #073763 50%, #1770c1); 
			color:         #fff;
			display:       inline-block;">
            <s:property value="getText('back.button')"/>
          </button> 
        <!-- Laxmikanth: depends on parameter(enableRecordPaymentButton) enabling the button -->
          <s:if test="%{enableRecordPaymentButton}">
          	<button id="paymnt" onclick="" type="button" class="btn  pull-right hidden-print " style="color:#ffffff; width:140px;margin-top:5px; background:    #3d85c6;
				background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
				background:    linear-gradient(#1770c1, #073763 50%, #1770c1);  
				color:         #fff;
				display:       inline-block;">
            <s:property value="getText('record.payment.button')"/>
          </button> 
          </s:if>
			<form action = "cancelInvoice" method="post">
			<s:hidden name="invoiceId" value="%{invDetail.arInvNum}"></s:hidden>
			<s:hidden name="orderId" value="%{invDetail.orderNum}"></s:hidden>
          <button id="invcncl" type="button" class="btn  pull-right hidden-print" style="color:#ffffff; width:120px;margin-top:5px; background:    #3d85c6;

			background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
			background:    linear-gradient(#1770c1, #073763 50%, #1770c1); 
			color:         #fff;
			display:       inline-block;">
            <s:property value="getText('invoice.cancel.button.cancel')"/>
          </button> 
          </form>
    	   
    	
    	   
          
<%--           <a href="invoiceCancellation?invNum=<s:property value="invDetail.arInvNum"/>" class="hidden-print"><button id="cancelInvoice" type="button" class="btn  pull-left" style="color:#ffffff; width:100px;margin-top:5px; background:    #3d85c6;
			background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
			background:    linear-gradient(#1770c1, #073763 50%, #1770c1); 
			color:         #fff;
			display:       inline-block;">
           <s:property value="getText('cancel.button')"/>
          </button></a> --%>
           
       
       <%--     <div class="row"><s:property value="order_Lpocntnt"/><img  src="LpoImg"/><s:property value="LpoImg"/><s:action name="LPOIMAGE" executeResult="true"></s:action></div> --%>
        </div>
        </div>
           
        <div class="col-lg-8 col-md-6 col-xs-6 pull-left visible-print-inline">
        <h3><s:property value="getText('open_invoices')"/></h3>
          <table class="table table-striped table-bordered">
            <thead>
            <tr style="background-color:#ADC2EE;">
              <th><s:property value="getText('table.head.SNo')"/></th>
              <th><s:property value="getText('invoice.no')"/></th>
              <th><s:property value="getText('invoice.date')"/></th>
              <th><s:property value="getText('invoice.total')"/></th>
              <th><s:property value="getText('received.amt')"/></th>
              <th><s:property value="getText('balance.due')"/></th>
              <!-- <th>EXTRA1</th>
              <th>EXTRA2</th> -->
            </tr>
            </thead>
            
            <tbody>
            <s:set var="Index" value="%{0}"></s:set>
             <s:iterator value="invoices"  status="itStatus">
             <s:if test="#Index < 3">
             <s:if test="%{invDetail.arInvNum!=arInvNum}">
            <tr>
            <td>
      <s:property value="%{#Index+1}"/>
      </td>
      <td><s:property value="arInvNum"/>
      </td>
      <td>
             <s:property value="arInvDate"/>
      </td>
       <td>
             <s:property value="invAmount"/>
      </td>
       <td>
             <s:property value="%{invAmount-invPendAmount}"/>
      </td>
       <td>
             <s:property value="invPendAmount"/>
      </td>
            <s:set var="Index" value="%{#Index+1}"></s:set>
      </tr>
      </s:if>
      </s:if>
      </s:iterator>
      </tbody>
 
          </table>
        </div>
      
        <!-- /.col -->
     

        <!-- /.col -->
        
  
      
      
      <!-- /.row -->

    </section>
    </section>
    <!-- /.content -->
    <div class="visible-print-inline">
    <header ><b><s:property value="getText('message_9')"/>:</b></header><h6><s:property value="getText('CompanyTerms')" /></h6>
</div>
  </div>
  <!-- /.content-wrapper -->

</div>
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
						<button type="submit" class="btn btn-primary center-block" data-dismiss="modal" id="mailconfirmation">
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
		<!-- Invoice Cancel Confirmation Modal Start -->
	<div class="modal fade bs-example-modal-sm" id="confirmInvoiceCancel"
		data-backdrop="static" data-keyboard="true" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body text-center">
					<button type="submit" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<s:property value="getText('invoice.cancel.confirmation')"></s:property>
						<br/>
						<form action="cancelInvoice" method="post">
							<s:hidden name="invoiceId" value="%{invDetail.arInvNum}"></s:hidden>
							<s:hidden name="orderId" value="%{invDetail.orderNum}"></s:hidden>
							<button type="submit" class="btn hidden-print">
								<s:property value="getText('invoice.cancel.confirm.yes')"></s:property>
							</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">
								<s:property value="getText('invoice.cancel.confirm.no')"></s:property>
							</button>
						</form>
					</h4>
				</div>
			</div>
		</div>
	</div>
	<!-- End of Modal for confirmation of invoice cancel -->
<script>
function myFunction() {
	if(amtBeingPaid.value>0){
		
		document.forms["hidden_order_data"].amtBeingPaid.value=amtBeingPaid.value;
        document.forms["hidden_order_data"].customerId.value=$('#customerId').html();
        document.forms["hidden_order_data"].invoiceNo.value=$('#InvoiceNo').html();
        document.forms["hidden_order_data"].submit();
	}else{

		$('#zeroNotAllowed').modal('show');
	}
			
		
}
</script>
</script>
<!-- to disable if balanceDue is 0 -->
<script type="text/javascript">
	if(document.getElementById("BalanceDue").innerText<=0){
		$('#paymnt').prop('disabled', true);
		$('#amtBeingPaid').prop('disabled', true);
		
	}else
		{
			$('#paymnt').prop('disabled', true);
			$('#amtBeingPaid').prop('disabled', true);
		}
	
/* 	if(document.getElementById("invCncl").innerText=='Open'){
		$('#cancelInvoice').prop('disabled', false);
	}else{
		$('#cancelInvoice').prop('disabled', true);
	} */


    $('#invoice').addClass('active');
    $('#invoicesearch').addClass('active');

    /* added by lokesh for invoice cancel button disable start */
    if(Number(document.getElementById("ReceivedAmt").innerText) == Number("0")){ // validation change;@laxmikanth
		$('#invcncl').prop('enabled', true);
	}
    else{
    	$('#invcncl').prop('disabled', true);
    }
    /* added by lokesh for invoice cancel button disable end */
	  </script>	
	  
	  <script>	 
	function  windowPrint(){
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
		var custom=document.getElementById("custommails").value;
		var invoiceID=document.getElementById("InvoiceNo").innerHTML;

	   	   
	   if(employeecheck==false && customer == false && deptheadcheck==false && empsalescheck==false && dataentrycheck==false && (custom==null || custom=="" ) ){
	    $("#validatepopover").modal("show");
	   }
	   else{			   
			$.ajax({
				url : "invoicedetailmail",
				type : "POST",
				data: {
				invoiceID : invoiceID,
				loginEmp : employeecheck, 
				customer:customer,
				departmentHead:deptheadcheck,
				salesAgent:empsalescheck,
				DataEntryOptr:dataentrycheck,
				custommail:custom},
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
	
	/* $('#dwnldLPO').click(function(event) {
    	var lpoSlipType = "<s:property value="orders[0].getOrdTranLpo().getLpoSlipType()"/>";
		if(lpoSlipType){
			var win = window.open('<s:property value="#downloadLPOUrl"/>','_blank');
		}
    }); */
 </script>
</body>
</html>