<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>SDS | Order Details</title>

 <!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />

<!-- Theme style -->
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="assets/css/popup.css">
<link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="assets/css/jquery-ui.css">
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="assets/css/Customizations.css">

<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
     
    <!-- jQuery 2.2.0 -->
<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
<script src="custom/format.js"></script>
<!-- Script to print current page -->
 
  

<style type="text/css">
.form-group{
	margin-bottom: 0px;
}
</style>
<script type="text/javascript">
	
  $(document).ready(function() {
    $('#order').addClass('active');
    $('#pendingorders').addClass('active');
    
    $('#printWindow').click(function(event) {
    	window.print();
    	var lpoSlipType= "<s:property value="orderTran.getOrdTranLpo().getLpoSlipType()"/>";
		if(lpoSlipType){
			var win = window.open('PrintLPODetails?orderID=<s:property value="orderTran.getOrdTranSum().getIdOrd()"/>','_blank');
		}
    });
    // hidding the pop up when you click savePdf link  
    $('#savePDFOrder').click(function(event) {
    		var win = window.open('savedOrderPDFDownload?orderId=<s:property value="orderTran.getOrdTranSum().getIdOrd()"/>&tranLevelAgentName=<s:property value="TranLevelAgentName"/>');
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
    
      <!-- Your Page Content Here -->
    <div class="order">
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
            <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><s:property value="getText('order.details.help_text')"/></b></small>

      <div class="row">
        <div class="col-xs-12">
          <h2 style="color: #226e71;">
             <s:property value="getText('order.details')"/>
              <div class="pull-right hidden-print"><h5>
              <s:url action="downloadLPO" var="downloadLPOUrl">
				<s:param name="orderID" value="%{orderTran.getOrdTranSum().getIdOrd()}" />
			</s:url>
              <!-- <table class="table hidden-print">
                        <tr> -->
                    <a href="javascript:void(0)" id="savePDFOrder" style="padding: 0px 5px;"><i class="fa fa-save"></i> <s:property value="getText('save.label')"/> &nbsp; </a>
                    <a href="javascript:void(0)" id="printWindow" style="padding: 0px 5px;"><i class="fa fa-print"></i> <s:property value="getText('print.label')"/> &nbsp; </a>
					<a href="javascript:void(0)" rel="popover" id="btpopover" data-placement="top" data-original-title="Select"
                    	data-content='<div><b><input type="checkbox" id="employee" name="employee" class="form-check-input"> <label for="employee"><s:property value="getText('login.employee')"/></label></div>
										<div><b><input type="checkbox" id="customr" name="customr" class="form-check-input"> <label for="customr"><s:property value="getText('customer.label')"/></label></div>
										<div><b><input type="checkbox" id="departmentHead" name="departmentHead" class="form-check-input"> <label for="departmentHead"><s:property value="getText('dept.head')"/></label></div>
    									<div><b><input type="checkbox" id="salesAgent" name="salesAgent" class="form-check-input"> <label for="salesAgent"><s:property value="getText('sales.agent')"/> </label></div>
    									<div><b><input type="checkbox" id="DataEntryOptr" name="DataEntryOptr" class="form-check-input"> <label for="DataEntryOptr"><s:property value="getText('dataentry.operator')"/> </label></div>
    									<div><label>Mail :</label><input type="text" id="custommails" name="custom" /></div><br>
                						<div style="text-align:right;">
                						<button type="button" class="btn btn-primary btn-sm" id="emailSubmitbtn">Submit</button>
                						<button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" id="cancelmail">Cancel</button></div>'>
                		<i class="fa fa-envelope"></i> <s:property value="getText('mail.label')"/>  &nbsp;
                	</a>
                     
                        <!-- </tr>  
                  </table> -->
                   </h5>
                  </div>
                  
<!--            <small class="pull-right">Date: 2/10/2014</small>--></h5>
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
        <div class="col-sm-4 invoice-col">
        <table style="width:100%;" class="table">
            <tr><td style="text-align:right;width:50%;"><s:property value="getText('order.id')"/>:</b></td><td><b><span id="ordId"><s:property value="orderTran.getOrdTranSum().getIdOrd()"/></span></b></td></tr>
        	<tr><td style="text-align:right;"><s:property value="getText('order.date')"/>:</b></td><td><b><s:date name="orderTran.businessDate" format="%{getText('format.date')}" /></b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('delivery.date')"/>:</b></td><td><b><s:date name="orderTran.getOrdTranSum().ordDlvrDate" format="%{getText('format.date')}"/></b></td></tr>
              	<tr><td style="text-align:right;width:50%;"><s:property value="getText('order.status')"/>:</b></td><td><b>
           	<s:if test="orderTran.getTransactionStatus().toString().equalsIgnoreCase('4')"><s:property value="getText('quoted_status')"/></s:if>
			</b></td></tr>
           	<tr><td style="text-align:right;width:50%;"><s:property value="getText('shipping.address')"/> :</b></td><td style="word-break: break-word;"><address><b>
            <s:property value="orderTran.ctDvrInf"/>
            </b>
          </address></td></tr>
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
        <table style="width:100%;" class="table">
          <tr><td style="text-align:right;"> <s:property value="getText('customer.id')"/>:</b></td><td><b><s:property value="orderTran.getCustomer().getCustomerHeader().getCustomerHeaderPK().getCustId()"/></b></td></tr>
       <tr><td style="text-align:right;"><s:property value="getText('customer.name')"/>:</b></td><td><b><s:property value="orderTran.getCustomer().getCustomerHeader().getCtNm()"/></b></td></tr>
       		<tr><td style="text-align:right;"><s:property value="getText('lpo.no')"/>:</b></td><td><b><s:property value="orderTran.getOrdTranSum().getCustLpoNum()"/></b></td></tr>
 			<tr><td style="text-align:right;"><s:property value="getText('lpo.date')"/>:</b></td><td><b><s:date name="orderTran.getOrdTranSum().getCustLpoDate()" format="%{getText('format.date')}"/></b></td></tr>
        
        	
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
          <table style="width:100%;" class="table">
         
           	<tr><td style="text-align:right;"><s:property value="getText('sales.agent')"/>:</b></td><td style="word-break: break-word;"><b><s:property value="TranLevelAgentName"/></b></td></tr>
         	<tr><td style="text-align:right;"><s:property value="getText('shipment.method')"/>:</b></td><td><b> 
         	<s:iterator  value="orderTran.getOrdTranLineItems()" status="itStatus">
         		<s:if test="itmTy.toString().equalsIgnoreCase('2')">
         		<s:property value="deItmShrtRcpt"/>
         		</s:if>
         	</s:iterator>
			
			</b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('delivery.comments')"/>:</b></td><td><b><s:property value="orderTran.ctDvrInfoIns"/></b></td></tr>
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
             <th> <s:property value="getText('table.head.SNo')"/> </th>
              	<th><s:property value="getText('table.head.item')"/></th>
              	<th style="text-align: right;"><s:property value="getText('order.qty')"/></th>
              	<th  style="text-align: right;"> <s:property value="getText('table.head.vpn')"/> </th>
                <th  style="text-align: right;"><s:property value="getText('table.head.unitprice')"/></th>
                <th  style="text-align: right;"><s:property value="getText('table.head.disc')"/><small>(<s:property value="getText('table.head.disc.amt')"/>)</small> </th>
                <th  style="text-align: right;"><s:property value="getText('table.head.tax')"/> </th>
                <th  style="text-align: right;"><s:property value="getText('table.head.total')"/><small>(<s:property value="getText('global.currency')"/>)</small></th>
            </tr>
            </thead>
            <tbody>
            <s:set var="totalItemQty" value="0"/>
            <s:iterator  value="orderTran.getOrdTranLineItems()" status="itStatus">  
            <tr>
           	   <s:set var="totalItemQty" value="%{#totalItemQty+lineQnt}"/> 
            	<td><s:property value="#itStatus.count" />.</td>
            	<td><s:property value="itemId"/> - <s:property value="deItmShrtRcpt" />
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
				<td style="text-align: right;"><s:property value="lineQnt" /></td>
            	<td style="text-align: right;"><s:property value="registryId" /></td>
            	
            	
            	<td  style="text-align: right;"><s:property value="%{getText('format.currency.args',{priceOverRideFlag == 1?overRidePrice:itmPrnPrc})}"/></td>
            	
            	<td  style="text-align: right;"><s:property value="%{getText('format.currency.args',{extnLnItmRtn-extnDscLnItm})}"/></td>
            	
            	<td  style="text-align: right;"><s:property value="vatLnItmRtn" /></td>
            	<td  style="text-align: right;"><s:property value="%{getText('format.currency.args',{extnDscLnItm})}"/></td>
            </tr>
            </s:iterator>
            
        
            </tbody>
          </table>
          
				<div class="progress" style="height: 2px;">
				<div class="progress-bar" style="width: 100%; background-color: #ADC2EE;"></div>
			</div>	
          
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <div class="row">
        <!-- /.col -->
        <div class="col-lg-6 col-md-8 col-xs-12 pull-right">

          <div class="table-responsive">
            <table class="table">
              <tbody>
				<tr>
					<td style="text-align: right;"> <s:property value="getText('no.of.items')"/>:</td>
					<td style="text-align: right;"><b> <s:property value="orderTran.getOrdTranLineItems().size()"/> </b></td>
				</tr>
				<tr>
					<td style="text-align: right;"><s:property value="getText('total.qty')"/>:</td>
					<td style="text-align: right;"><b><s:text name="%{#totalItemQty}"></s:text></b></td>
				</tr>
				<tr>
                <td style="text-align:right;" style="width:50%"> <s:property value="getText('subtotal.label')"/>:</td>
                <td style="text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<b><s:property value="%{getText('format.currency.args',{orderTran.getOrdTranSum().getDkartSlsTot()})}"/></b></td>
              </tr>
              <tr>
                <td  style="text-align:right;"><s:property value="getText('total.discount')"/> :</td>
                <td style="text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<b><s:property value="%{getText('format.currency.args',{orderTran.getOrdTranSum().getDkartDscTot()})}"/></b></td>
              </tr>
              <tr>
                <td  style="text-align:right;" ><s:property value="getText('total.tax')"/> :</td>
                <td style="text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<b><s:property value="orderTran.getOrdTranSum().getDkartTaxTot()" /></b></td>
              </tr>
              <tr style="font-size:18px;">
                <td style="text-align:right;"><s:property value="getText('net.total')"/>:</td>
                <td style="text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<b><s:property value="%{getText('format.currency.args',{orderTran.getOrdTranSum().getDkartNetTot()})}"/></b></td>
              </tr>
            </tbody>
            </table></div>
            <div class="text-right pull-right hidden-print">
	            <button  type="button" class="btn btn-primary" id="cancelbutton" >
	           		<s:property value="getText('cancel.button')"/>
	           		<span id="cancelspinner" class="fa" aria-hidden="true"></span>
	           	</button>	
	           	<!-- changed to override credit limit low popup to override upon validating credit limit @Laxmikanth-->
	           	<button id="editOrdBut"  type="button" class="btn btn-primary" >
	            	<s:property value="getText('edit.button')"/>
	            	<span id="editspinner" class="fa" aria-hidden="true"></span>
	          		</button>
	          	
	            <button id="saveBttn"  type="button"class="btn btn-primary">
	            	<s:property value="getText('save.button')"/>
	            	<span id="savespinner" class="fa" aria-hidden="true"></span>
	          	</button>
	         	 
	            <button id="cnfrmOrdBut"  type="button" class="btn btn-primary" ><!-- onclick="checkinv()"> -->
	            	<s:property value="getText('confirm.button')"/>
	            	<span id="confirmspinner" class="fa" aria-hidden="true"></span>
	          	</button>
          	</div>
          
							
		<%-- <div class="row">
         <div class="col-md-3 col-sm-12 hidden-print" >
       <!--  <a href="#cancelPopup"> --><button  type="button" class="btn btn-primary pull-right" id="cancelbutton" >
           <s:property value="getText('cancel.button')"/> 
          </button>
          <!-- </a> -->
          </div>
          <div class="col-md-3 col-sm-12 hidden-print">
          <!-- mudassir pendingOrderStatusChanged insted of editOrder -->
          <a href="pendingOrderStatusChanged"><button  type="button" class="btn btn-primary pull-right" onclick="preventBack=false;" >
            <s:property value="getText('edit.button')"/>
          </button>
          </a>
          </div>
          <div class="col-md-3 col-sm-12 hidden-print">
          <a href="SuppendSalesOrder"><button  type="button"class="btn btn-primary pull-right" onclick="preventBack=false;">
            <s:property value="getText('save.button')"/>
          </button>
          </a>
          </div>
          <div class="col-md-3 col-sm-12 hidden-print">
            <!-- mudassir pendingOrderConfirm insted of bookSalesOrder -->
          
           <!--  <a href="pendingOrderConfirm"> --><button  type="button" class="btn btn-primary pull-right"  onclick="checkinv()">
            <s:property value="getText('confirm.button')"/>
          </button>
          <!-- </a> -->
		</div>
       
        </div> --%>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <!-- this row will not appear when printing -->
      <div class="row">
        <div class="col-xs-12">
          
          
        </div>
      </div>
    </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  

  
  <!-- /.control-sidebar -->
  <form name="invcheck" action="bookSalesOrder">
  
  </form>
  <form name="edit" action="pendingOrderStatusChanged">
  
  </form>
</div>
<!-- ./wrapper -->

<%-- <div id="cancelPopup" class="overlay1">
  <div class="popup" style="width:500px;min-height:150px;margin:auto;margin-top:90px;">
     
     
     <h4 style="text-align:center"> <s:property value="getText('_promptmsg.cancelorder')"/></h4>
    <br>
   <table style="margin:auto;">
        <td>
               <a href="CancelSalesOrder"><button class="pull-left btn btn-block btn-info"  type="button"><s:property value="getText('yes.button')"/></button></a>
        </td>
          <td style="width:10%;"></td>
        <td>
             <a href="#"><button class="pull-right btn btn-block btn-info"  type="button"><s:property value="getText('no.button')"/></button></a>
        </td>
      </table>
    <br>
  </div>
</div>
 --%>

  
<!-- modal to show when you click on cancel button @sharanya -->
<div class="modal fade bs-example-modal-sm" id="cancel"
			data-backdrop="static" data-keyboard="true" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 id="errorTitle" class="modal-title">
							<s:property value="getText('message_4')" />
						</h4>
					</div>
					<div class="modal-footer" style="margin:auto;max-width:300px;">
		
						<button id="nocancel" type="submit" class="pull-right btn btn-primary center-block"
							data-dismiss="modal">
							<s:property value="getText('no.button')" />
							&nbsp;
						</button>
						<form name="cancelorder" action="CancelSalesOrder">
						<button class="pull-left btn btn-primary center-block"
							id="cancelorder" onclick="preventBack=false;">
							<s:property value="getText('yes.button')" />
							&nbsp;
						</button>
						  </form>
						
					</div>
				</div>

			</div>
		</div>
<!-- inv popup -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <center> <b> <h3 class="modal-title">Inventory Check</h3></b></center>
        </div>
       <div class="modal-body pre-scrollable " style="max-height: 240px;">
     	<!-- <center>	<h4 id="itemList"> </h4></center> -->
     	 <!-- <center> --><h4>The Following Items do not have Enough Inventory</h4><!-- </center> -->
     <!-- <center> -->	<table class="table table-condensed" >
  								<thead>
								<tr>
									
									<th>Item Id</th>
									<th>Order Qty</th>
									<th>Available Quantity</th>
								
							  </tr>
							</thead>
							<tbody id="noInvItemList">
		
    						</tbody>
    	</table><!-- </center> -->
        </div>
        <div class="modal-footer">
		<!-- <center>  -->
          <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="goToEditOrder()">Edit</button> 
          <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="proceedFurther()">Override</button> 
          <button id="modalClose" type="button" class="btn btn-primary" data-dismiss="modal">Close</button> 
        <!--   </center> -->
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
		
		<div class="modal fade" id="creditLimitOverride" tabindex="-1" role="dialog" data-backdrop="static"
				aria-labelledby="creditLimitOverrideLabel" aria-hidden="true">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" aria-label="Close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="creditLimitOverrideLabel">
							Credit Limit Low
						</h4>
					</div>
				<div class="modal-body">
					
					<form action="overrideCreditLimit" method="post" id="creditLimitOverrideForm" 
						class="form-horizontal" role="form">
						<p id="errorMsg"></p>
						<div class="form-group">
							<label class="control-label col-xs-6" for="" style="padding-top: 1px">
							Credit Limit</label>
							<div class="col-xs-6" style="padding-left: 0px">
								<small><s:text	name="global.currency" />&nbsp;</small>
								<b class="form-control-static" id="avlCrdLmt1"><s:property value="%{getText('format.currency.args',{orderTran.transCrdtLimit})}" /></b>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-6" for="newCreditLimit" style="padding-top: 4px">
							New Credit Limit</label>
							<div class="col-xs-6" style="padding-left: 0px">
								<input type="number" class="form-control" id="newCreditLimit" name="newCreditLimit"
									placeholder="Enter New Credit Limit" required="required" min="0" autocomplete="off">
							</div>
						</div>
						<hr>
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
						<div class="form-group text-center" style="margin-top: 10px">
							<button class="btn btn-primary" data-dismiss="modal">Cancel</button>
							<s:if test="%{creditLimitOverride}">
								<button type="submit" class="btn btn-primary">
									Override<i class="hidden">&nbsp;<i class="fa fa-spin fa-spinner" aria-hidden="true"></i></i>
								</button>
							</s:if>
							<s:else>
								<button type="reset" class="btn btn-primary disabled" title="Use parameter 'EnableExceedingCustomerAvailableLimit' to enable this feature">
									Override<i class="hidden">&nbsp;<i class="fa fa-spin fa-spinner" aria-hidden="true"></i></i>
								</button>
							</s:else>
						</div>
						<div class="alert alert-danger hidden" id="managerOverrideErrorResponse"
							style="padding: 4px; margin-top: 10px; margin-bottom: 0px" >
						</div>
						
					</form>
					<div class="form-group hidden" id="managerOverrideSuccessDiv">
						<div class="alert alert-success" id="managerOverrideSuccessResponse"
							style="padding: 4px; margin-top: 0px; margin-bottom: 10px" >
						</div>
						<p>To proceed with booking the order please press "Confirm".</p>
						<button type="submit" class="btn btn-primary center-block" data-dismiss="modal">Ok</button>
					</div>
				</div>
				<%-- <div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							Override</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<s:property value="getText('ok.button')" /></button>
					</div>  --%>
				</div>
			</div>
			<script type="text/javascript">
			<!--laxmikanth start [for new credit limit field validation, not to allow +,-] -->
				$("#newCreditLimit").keypress(function(e) {
  					var newCreditLimitVal = $(this).val();
					var key = e.which;
					if((key < 48) || (key > 57) || newCreditLimitVal.length > 13 ){
						if(key == '46'){
							if((newCreditLimitVal.indexOf('.') != -1) ){
								e.preventDefault();
							}
							return;
						}
						e.preventDefault();
					}
				});
			
				function managerOverrideErrorResponse(response){
					$('#managerOverrideErrorResponse').html('Override could not be processed:<br><strong>'+response+'</strong>');
					$('#managerOverrideErrorResponse').removeClass('hidden');
				}
				function hideManagerOverrideResponse(){
					$('#managerOverrideErrorResponse').addClass('hidden');
				}
				function managerOverrideSuccessResponse(response){
					var currency = "<s:property value="getText('global.currency')"/>";
					$('#managerOverrideSuccessResponse').html("New Credit Limit has been successfully set to "+currency+" "+response);
					$('#creditLimitOverrideForm').addClass('hidden');
					$('#managerOverrideSuccessDiv').removeClass('hidden');
				}
				function loading(){
					$("#creditLimitOverrideForm :input").attr("readonly", true);
					$("#creditLimitOverrideForm button i").removeClass('hidden');
				}
				function doneLoading(){
					$("#creditLimitOverrideForm :input").attr('readonly',false);
					$("#creditLimitOverrideForm button i").addClass('hidden');
				}
				function resetOverrideModal(){
					$("#creditLimitOverrideForm").get(0).reset();
					$("#managerOverrideSuccessResponse").html('');
					$("#managerOverrideErrorResponse").addClass('hidden');
					$("#creditLimitOverrideForm").removeClass('hidden');
					$("#managerOverrideSuccessDiv").addClass('hidden');
				}
				
				$('#creditLimitOverride').on('show.bs.modal', function (e) {
				  resetOverrideModal();
				})
				
				$('#creditLimitOverrideForm').submit(function(e){
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
							else if(!data.validCreditLimit){
								managerOverrideErrorResponse('Invalid Credit Limit');
							}
							else{
								$.ajax({
									type: 'post',
									url: 'reloadOrdTran',
									data: '',
									timeout: 10000
								}).done(function(data, textStatus, jqXHR) {
									$("#avlCrdLmt1").html(format(currencyFormat,data.orderTran.transCrdtLimit));
									//UpdateTableContent(data);
									managerOverrideSuccessResponse(format(currencyFormat,data.orderTran.transCrdtLimit));
								}).fail(function(jqXHR, textStatus, errorThrown) {
									//TODO
								});	
							}
						}
						
					}).fail(function(jqXHR, textStatus, errorThrown) {
						doneLoading();
						managerOverrideErrorResponse(textStatus);
						
			            alert('An error occured while processing the request:\n' + exception);
			           	
					}).always(function(){
						
					});
					
				});
				
			</script>
		</div>

<script type="text/javascript">
$(function(){
	$("#cancelbutton").click(function(e)
		    {
		    	/* $('#cancelspinner').addClass('fa-spinner fa-spin');
		    	$('#cancelbutton').prop("disabled", true);
		    	$('#saveBttn').prop("disabled", true);
				$('#cnfrmOrdBut').prop("disabled", true);
				$('#editOrdBut').prop("disabled", true); */
		     $("#cancel").modal('show');
		    });
});


$("#nocancel").click(
		function(e) {
				/* $('#cancelspinner').removeClass('fa-spinner fa-spin');
				$('#cancelbutton').prop("disabled", false);
				$('#saveBttn').prop("disabled", false);
				$('#cnfrmOrdBut').prop("disabled", false);
				$('#editOrdBut').prop("disabled", false); */
		}); 

$("#cancelorder").click(
		function(e) {
			$("#CancelSalesOrder").submit();
		});  
		
</script>

<script type="text/javascript">
$("#modalClose").click(function(){
	$('#confirmspinner').removeClass('fa-spinner fa-spin');
	$('#cnfrmOrdBut').prop("disabled", false);
	$('#editOrdBut').prop("disabled", false);
	$('#saveBttn').prop("disabled", false);
	$('#cancelbutton').prop("disabled", false);
});

$("#saveBttn").click(function(){
	preventBack=false;
	$('#savespinner').addClass('fa-spinner fa-spin');
	$('#saveBttn').prop("disabled", true);
	$('#cnfrmOrdBut').prop("disabled", true);
	$('#editOrdBut').prop("disabled", true);
	$('#cancelbutton').prop("disabled", true);
	location.href = "SuppendSalesOrder";
});

$("#cnfrmOrdBut").click(function(){

	/* $('#confirmspinner').addClass('fa-spinner fa-spin');
	$('#cnfrmOrdBut').prop("disabled", true);
	$('#editOrdBut').prop("disabled", true);
	$('#saveBttn').prop("disabled", true);
	$('#cancelbutton').prop("disabled", true); */
	var validateSalesOrder = $.post("validateSalesOrderAjax", {});
  	validateSalesOrder.done(function(data) {
    		if(data.actionErrors!=null && data.actionErrors.length>0)
    		{
    			$("#errorMsg").html(data.actionErrors);
    			$("#avlCrdLmt1").html(format(currencyFormat,data.fieldErrors.creditLimit[0]));
    			$("#creditLimitOverride").modal('show');
    			/* $('#confirmspinner').removeClass('fa-spinner fa-spin');
				$('#cnfrmOrdBut').prop("disabled", false);
				$('#editOrdBut').prop("disabled", false);
				$('#saveBttn').prop("disabled", false);
				$('#cancelbutton').prop("disabled", false); */
    		}
    		else
    			checkinv();
  		});
  		validateSalesOrder.fail(function(jqXHR, textStatus, errorThrown) {
  				/* $('#confirmspinner').removeClass('fa-spinner fa-spin');
				$('#cnfrmOrdBut').prop("disabled", false);
				$('#editOrdBut').prop("disabled", false);
				$('#saveBttn').prop("disabled", false);
				$('#cancelbutton').prop("disabled", false); */
   			alert("error");
  		});
});

$("#editOrdBut").click(function(){
	preventBack=false;
	$('#editspinner').addClass('fa-spinner fa-spin');
	$('#editOrdBut').prop("disabled", true);
	
	$('#saveBttn').prop("disabled", true);
	$('#cnfrmOrdBut').prop("disabled", true);
	$('#cancelbutton').prop("disabled", true);
	
		var validateSalesOrder = $.post("validateSalesOrderAjax", {});
		
  		validateSalesOrder.done(function(data) {
    		if(data.actionErrors!=null && data.actionErrors.length>0)
    		{
    			$('#editOrdBut').prop("disabled", false);
    			$("#errorMsg").html(data.actionErrors);
    			$("#avlCrdLmt1").html(format(currencyFormat,data.fieldErrors.creditLimit[0]));
    			$("#creditLimitOverride").modal('show');
    			$('#editspinner').removeClass('fa-spinner fa-spin');
    			$('#editOrdBut').prop("disabled", false);
				$('#saveBttn').prop("disabled", false);
				$('#cnfrmOrdBut').prop("disabled", false);
				$('#cancelbutton').prop("disabled", false);
    		}else
    			location.href = "pendingOrderStatusChanged";
  		});
  		validateSalesOrder.fail(function(jqXHR, textStatus, errorThrown) {
  			$('#editspinner').removeClass('fa-spinner fa-spin');
    		$('#editOrdBut').prop("disabled", false);
			$('#saveBttn').prop("disabled", false);
			$('#cnfrmOrdBut').prop("disabled", false);
			$('#cancelbutton').prop("disabled", false);
   			alert("error");
  		});
});



	var currencyFormat = "<s:property value="getText('format.currency')"/>";
	
	// laxmikanth: method to check creditLimit, if exceeds diplaying popup to override the credit limit
	function validateOnEdit(){
		$('#spinner').addClass('fa-spinner fa-spin');
		$('#editOrdBut').prop("disabled", true);
		var validateSalesOrder = $.post("validateSalesOrderAjax", {});
		
  		validateSalesOrder.done(function(data) {
    		if(data.actionErrors!=null && data.actionErrors.length>0)
    		{
    			$('#editOrdBut').prop("disabled", false);
    			$("#errorMsg").html(data.actionErrors);
    			$("#avlCrdLmt1").html(format(currencyFormat,data.fieldErrors.creditLimit[0]));
    			$("#creditLimitOverride").modal('show');
    			$('#spinner').removeClass('fa-spinner fa-spin');
    		}else
    			location.href = "pendingOrderStatusChanged";
  		});
  		validateSalesOrder.fail(function(jqXHR, textStatus, errorThrown) {
   			alert("error");
  		});
	}
	
function checkinv() {
	  
	    
	      $.getJSON('checkInventory', {
	        
	      }, 
	      function(jsonResponse) {
	      
	      
	       var res=  jsonResponse.invResult;
	       if(res===""){
	    	   $('#myModal').modal('show');
	    	   console.log(jsonResponse.inventoryCheckMap)
	    	   $("#noInvItemList").empty(); 
	    	
	    	   $.each(jsonResponse.inventoryCheckMap,
	    				function(key,value){
	    		   var item="<tr><td>ID</td><td >ORDER</td><td >QTY</td></tr>";
	    		   var temp=value.split("#");
	    		   item=item.replace("ID", key);
	    		   item=item.replace("QTY",temp[0]);
	    		   item=item.replace("ORDER",temp[1]);
	    		   $("#noInvItemList").append(item);
	    	   }   
	    	   
	    	   );
	       }
	       else if(res==="fail"){
	    	 
	    	   window.alert("error occured while validating the inventory"); 
	       }
	       else if (res==="proceed"){
	       	preventBack=false;
	       	document.forms["invcheck"].submit(); 
	       }
	   
	      });
	     
	}
	
	function validateSalesOrder()
	{
		var validateSalesOrder = $.post("validateSalesOrderAjax", {});
		
  		validateSalesOrder.done(function(data) {
    		if(data.actionErrors!=null && data.actionErrors.length>0)
    		{
    			$("#errorMsg").html(data.actionErrors);
    			$("#avlCrdLmt1").html(format(currencyFormat,data.fieldErrors.creditLimit[0]));
    			$("#creditLimitOverride").modal('show');
    		}
    		else
    			checkinv();
  		});
  		validateSalesOrder.fail(function(jqXHR, textStatus, errorThrown) {
   			alert("error");
  		});
		
	}

</script>
<script type="text/javascript">
function proceedFurther(){
	preventBack=false;
	 document.forms["invcheck"].submit(); 
}

</script>
<script type="text/javascript">
function goToEditOrder(){
	preventBack=false;	
	 document.forms["edit"].submit(); 
}
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
        var ordrId=document.getElementById("ordId").innerHTML;
	   	   
	   if(employeecheck==false && customer == false && deptheadcheck==false && empsalescheck==false && dataentrycheck==false && (custom==null || custom=="") ){
	    $("#validatepopover").modal("show");
	   }
	   else{			   
			$.ajax({
				url : "orderdetailmail",
				type : "POST",
				data: {
				ordrId : ordrId,
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
   
 </script>
 <script type="text/javascript">
    window.onbeforeunload = confirmExit;
    var preventBack=<s:property value="preventBack" />;
    function confirmExit() {
    	if(preventBack)
        return "You have attempted to leave this page. Are you sure?";
    }
</script>
<script type="text/javascript">
 $('#dwnldLPO').click(function(event) {
    	var lpoSlipType = "<s:property value="orderTran.getOrdTranLpo().getLpoSlipType()"/>";
		if(lpoSlipType){
			var win = window.open('<s:property value="#downloadLPOUrl"/>','_blank');
		}
    });
</script>
</body></html>