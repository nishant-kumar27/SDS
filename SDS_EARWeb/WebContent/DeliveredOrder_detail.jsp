<%@ page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="utf-8"%>
    <!-- changed by @ jagadish because this (charset=ISO-8859-1 And  pageEncoding="ISO-8859-1) does not supporting
	to display Arabic language.  -->
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
  <title>SDS | Order Details</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
  <!-- Font Awesome -->
        <link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />

  <!-- Theme style -->
  <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
    <!-- jQuery 2.2.0 -->
<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
  <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<!--  <link rel="stylesheet" href="assets/css/popup.css"> -->
<link rel="stylesheet" href="assets/css/jquery-ui.css">
<link rel="stylesheet" href="assets/css/Customizations.css">
   
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
								<small class="pull-right"
									style="font-size: 9px; color: #8e8e8e;"><b> <s:i18n
											name="rispl.print.printText">
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
        <small class="pull-right hidden-print" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><s:property value="getText('orders.details.help_text')"/></b></small>
      <div class="row">
        <div class="col-xs-12">
          <h2 style="color: #226e71;">
            <s:property value="getText('delivered.order.detail')"/>
            <s:url action="downloadLPO" var="downloadLPOUrl">
				<s:param name="orderID" value="%{ord_tran_header.getOrdTranSum().getIdOrd()}" />
			</s:url>
              <div class="pull-right hidden-print"><h5>
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
                    <a id="printWindow" href="#"  class="pull-right"><i class="fa fa-print"></i> <s:property value="getText('print.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a>
                  <a href="deliveredorderPDFDownload?orderId=<s:property value="order.id.orderId"/>"><i class="fa fa-save"></i><s:property value="getText('save.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a></div>
                  
<!--            <small class="pull-right">Date: 2/10/2014</small>--></h5>
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
        <div class="col-sm-4 invoice-col">
        <table style="width:100%;" class="table">
            <tr><td style="text-align:right;width:50%;"><s:property value="getText('order.id')"/>:</b></td><td><b><span id="ordId"><s:property value="order.id.orderId"/></span></b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('order.date')"/>:</b></td><td><b><s:date name="order.id.orderDate" format="%{getText('format.date')}"/></b></td></tr>
            <tr><td style="text-align:right;width:50%;"><s:property value="getText('effective.date')"/>:</b></td><td><b><s:date name="order.effectiveDate" format="%{getText('format.date')}"/></b></td></tr>
            <tr><td style="text-align:right;width:50%;"><s:property value="getText('order.status')"/>:</b></td><td><b><s:property value="order.status"/></b></td></tr>
           	<tr><td style="text-align:right;"><s:property value="getText('shipping.address')"/>:</b></td><td style="word-break: break-word;"><address><b>
            <s:property value="ord_tran_header.ctDvrInf"/>
            </b>
          </address></td></tr>
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
        <table style="width:100%;" class="table">
        	 <tr><td style="text-align:right;"><s:property value="getText('customer.id')"/>:</b></td><td><b><s:property value="customer.customerHeaderPK.custId"/></b></td></tr>
        	<tr><td style="text-align:right;"><s:property value="getText('customer.name')"/>:</b></td><td><b><s:property value="customer.ctNm"/></b></td></tr>
        	<tr><td style="text-align:right;"><s:property value="getText('customer.segment')"/>:</b></td><td><b><s:property value="cust_seg"/></b></td></tr>
        	<tr><td style="text-align:right;"><s:property value="getText('LPO.label')"/>:</b></td><td><b><s:property value="ord_tran_sum.custLpoNum"/></b></td></tr>
 			<tr><td style="text-align:right;"><s:property value="getText('lpo.date')"/>:</b></td><td><b><s:date name="ord_tran_sum.custLpoDate" format="%{getText('format.date')}"/></b></td></tr>
        	
        	
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
          <table style="width:100%;" class="table">
            <tr><td style="text-align:right;"><s:property value="getText('invoice.no')"/>:</b></td><td><b><s:property value="order.invoiceId"/></b></td></tr>
         	<tr><td style="text-align:right;"><s:property value="getText('shipment.method')"/>:</b></td><td><b>
         	 <s:iterator  value="ord_tran_header.getOrdTranLineItems()" status="itStatus">
         		<s:if test="itmTy.toString().equalsIgnoreCase('2')">
         		<s:property value="deItmShrtRcpt"/>
         		</s:if>
         	</s:iterator> 
         	</b></td></tr>
       		<tr><td style="text-align:right;"><s:property value="getText('sales.agent')"/>:</b></td><td><b><s:property value="order.salesAgent"/></b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('comments.label')"/>:</b></td><td><b><s:property value="ord_tran_header.ctDvrInfoIns"/></b></td></tr>
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
              <th style="text-align: right;"><s:property value="getText('order.qty')"/> </th>
              <th style="text-align: right;"><s:property value="getText('table.head.vpn')"/></th>
              <th style="text-align: right;"><s:property value="getText('delivered.qty')"/></th>
              <th style="text-align: right;"><s:property value="getText('table.head.unitprice')"/></th>
              <th style="text-align: right;"><s:property value="getText('order.disc.amt')"/></th>
              <th style="text-align: right;"><s:property value="getText('table.head.tax')"/></th>
              <th style="text-align: right;"><s:property value="getText('table.head.total')"/>(<s:property value="getText('global.currency')"/>)</th>
            </tr>
            </thead>
            <tbody>
            
            <s:iterator  value="order_items" status="itStatus">  
            <tr>
            	<td><s:property value="#itStatus.count" />.</td>
            	<td><s:property value="itemId" /></br><s:property value="deItmShrtRcpt" />
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
            	<s:set var="deliveredQty" value="0" />
				<s:if test="shippedQty!=null" >
					<s:set var="deliveredQty" value="shippedQty" />
				</s:if>
            	<td style="text-align: right;"><s:property value="#deliveredQty" /></td>
            	
            	<s:set var="itemPrc" value=""/>
            	<s:if test="ovrdPrc!=null">
            	<s:set var="itemPrc" value="ovrdPrc"/>
            	</s:if>
            	<s:else>
            	<s:set var="itemPrc" value="itmPrnPrc"/>
            	</s:else>
            	<td style="text-align: right;"><s:property value="%{getText('format.currency.args',{#itemPrc})}"/></td>
            	<s:if test="flItmDsc==0">
            	<td style="text-align: right;"><s:property value="%{getText('format.currency.args',{extnLnItmRtn-extnDscLnItm})}"/></td>
            	</s:if>
            	<s:else>
            	<td style="text-align: right;"><s:property value="%{0}"/></td>
            	</s:else>

            	<td style="text-align: right;"><s:property value="%{getText('format.currency.args',{vatLnItmRtn})}"/></td>
            	
            	<td style="text-align: right;"><s:property value="%{getText('format.currency.args',{extnDscLnItm})}"/></td>
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
                <td style="text-align:right;" style="width:50%"><s:property value="getText('subtotal.label')"/>:</td>
                <td style="text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<b><s:property value="%{getText('format.currency.args',{ord_tran_sum.dkartSlsTot})}"/></td>
              </tr>
              <tr>
                <td  style="text-align:right;"><s:property value="getText('discount.label')"/> :</td>
                <td style="text-align:right;"><s:property value="%{getText('format.currency.args',{ord_tran_sum.dkartDscTot})}"/></th>
              </tr>
              <tr>
                <td  style="text-align:right;" ><s:property value="getText('total.tax')"/>:</td>
                <td style="text-align:right;"><s:property value="%{getText('format.currency.args',{ord_tran_sum.dkartTaxTot})}"/></th>
              </tr>
              <tr style="font-size:18px;">
                <td style="text-align:right;"><s:property value="getText('net.total')"/>:</td>
                <td style="text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<b><s:property value="%{getText('format.currency.args',{ord_tran_sum.dkartNetTot})}"/></b></td>
              </tr>
            </tbody></table>
          </div>
          <tr>
          <td>
           
        <td>
        <button onClick="history.go(-1);return true;" type="button" class="btn  pull-right hidden-print" style="color:#ffffff; width:100px;margin-top:5px; background:    #3d85c6;
			background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
			background:    linear-gradient(#1770c1, #073763 50%, #1770c1); 
			color:         #fff;
			display:       inline-block;">
           <s:property value="getText('back.button')"/>
          </button>
          </td>   
<s:set var="mychar" value="@" />                
          <td>
       <!--    <a href="#popup">    -->
		<button  type="button" data-toggle="modal" data-target="#myModal" class="btn  pull-left hidden-print" 
			style="color:#ffffff; width:140px;margin-top:5px; background: #3d85c6; color: #fff; display: inline-block;
			background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);	background: linear-gradient(#1770c1, #073763 50%, #1770c1);"
			<s:if test="%{ord_tran_header.scOrd==7}">disabled</s:if> >
            <s:property value="getText('markas.delivered.button')"/>
		</button>
    <!--      </a>  -->
          </td>
          
<!--             <button class="btn pull-right" id="markDelivered"
				style="margin-left: 0px; color: #ffffff; width: 200px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); 
				background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; border: 1px solid; display: inline-block;"
				type="button">Mark As Delivered&nbsp;<i class="fa"></i>
			</button>  
			</td> -->
 
          </tr>
      
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <!-- this row will not appear when printing -->
      <div class="row">
        <div class="col-xs-12">
          
          
        </div>
      </div>
    </section>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <center>  <h4 class="modal-title"><s:property value="getText('delivery.details')"/>:</h4></center>
        </div>
        <div class="modal-body">
        <textarea  class="form-control" style="min-height:120px;margin:1px;overflow:auto;" placeholder="Delivery Notes" id="deliverycom"> </textarea>
        </div>
        <div class="modal-footer">
          <center> <button type="button" class="btn btn-default" data-dismiss="modal" onclick="markAsDelivered()"><s:property value="getText('confirm.button')"/></button> </center>
        </div>
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
  

  
  <!-- /.control-sidebar -->
  
</div>
<!-- ./wrapper -->

<!-- ChartJS 1.0.1 -->

 <script type="text/javascript">  	
	function markAsDelivered() {
	    var ordersMarked = new Array(1);
	    var v1=<s:property value="order.id.orderId"/>
	    var v2=<s:property value="ord_tran_sum.id.trnSeq"/>
	    var v3=<s:property value="ord_tran_sum.id.rtStrId"/>
	    var v4= '<s:property value="ord_tran_sum.id.ordWs"/>'
	    var v5='<s:property value="ord_tran_sum.id.dcDyOrd"/>'
	    var v6='@';
	    var res=v1+v6+v2+v6+v3+v6+v4+v6+v5;
	
		res +='@'+$('#deliverycom').val()
	    ordersMarked[0]=res;
	    $("#deliverycom").val('');
		$.ajax({
			type: "POST",
			data:'ordersMarked='+ordersMarked,
			url: "MarkOrderDeliveredInDOScreen1"
	    }).done(function(data) {
	    	//Refresh the page after updating
	    	window.location.reload();
	  	});
    
	}
  </script>
<script>
function div_show() {
	document.getElementById('popup').style.display = "block";
	}
</script>
	<script type="text/javascript">
    	$('#order').addClass('active');
		$('#deliveredorders').addClass('active');
	</script>
	
	<script>
function printfun() {
    window.print();
}
$('a[rel=popover]').popover({
    html: 'true',
placement: 'bottom'
});

$('#printWindow').click(function(event) {
    	window.print();
    	var lpoSlipType = "<s:property value="ord_tran_header.getOrdTranLpo().getLpoSlipType()"/>";
		if(lpoSlipType){
			var win = window.open('PrintLPODetails?orderID=<s:property value="ord_tran_header.getOrdTranSum().getIdOrd()"/>','_blank');
		}
    });
    
    $('#dwnldLPO').click(function(event) {
    	var lpoSlipType = "<s:property value="ord_tran_header.getOrdTranLpo().getLpoSlipType()"/>";
		if(lpoSlipType){
			var win = window.open('<s:property value="#downloadLPOUrl"/>','_blank');
		}
    });
</script>
	
	<script type="text/javascript">
		function emailSubmit(){   
		var employeecheck=$('#employee').is(":checked");
        var  customer=$('#customr').is(":checked");
		var deptheadcheck=$('#departmentHead').is(":checked");
		var empsalescheck=$('#salesAgent').is(":checked");
		var dataentrycheck=$('#DataEntryOptr').is(":checked");
        var ordrId=document.getElementById("ordId").innerText;
        var custom=document.getElementById("custommails").value;
	   	   
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
</body></html>