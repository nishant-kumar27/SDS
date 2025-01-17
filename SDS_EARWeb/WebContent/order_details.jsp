<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
<title>SDS | Order Details</title>
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
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="assets/css/Customizations.css">
<!-- jQuery 2.2.0 -->
<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">

<style type="text/css">
.mbg-blue {
	background-color: #00c0ef !important;
}
</style>
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
				<s:if test="orderId==null || order==null">
					
					<section class="order">
						<small class="pull-right hidden-print"
							style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><b><s:property
									value="getText('orders.details.help_text')" /> </b></small>
						<div class="row">
							<div class="col-xs-12">
								<h2 style="color: #226e71; margin-top: 10px;">
									<s:property value="getText('order.details')" />
								</h2>
								<s:if test="hasActionErrors()">
									<s:actionerror  id="orderError" theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss" />
								</s:if>
								<s:else>
									<div class="alert alert-danger">No Order details were found. </div>
								</s:else>
								<p>Go back to the previous screen.</p>
								<button class="btn btn-primary" onclick="history.back();">Back</button>
								
							</div>
						</div>
					</section>
				
				</s:if>
				<s:else>
					<!-- Your Page Content Here -->
					<section class="order">
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
						<small class="pull-right hidden-print"
							style="text-align: right; font-size: 14px; color: #8e8e8e; width: 500px;"><b><s:property
									value="getText('orders.details.help_text')" /> </b></small>
						<div class="row">
						<div class="col-xs-12">
							<s:url action="downloadLPO" var="downloadLPOUrl">
								<s:param name="orderID" value="%{ord_tran_header.getOrdTranSum().getIdOrd()}" />
							</s:url>
							<s:url action="downloadOrder" var="downloadOrderUrl">
								<s:param name="orderID" value="%{order.id.orderId}" />
							</s:url>
							<h2 style="color: #226e71;">
								<s:property value="getText('order.details')" />
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
											 
											<a href="orderPDFDownload?orderId=<s:property value="order.id.orderId"/>">
												<i class="fa fa-save"></i> 
												<s:property value="getText('save.label')"/>
											</a> 
											<a href="javascript:void(0);" id="printWindow"
												style="padding:0 5px">
												<i class="fa fa-print"></i> 
												<s:property	value="getText('print.label')" /> &nbsp;
											</a>
										</h5>
									</div>								
								</h2>
							</div>
							<!-- /.col -->
						</div>
						<!-- info row -->
						<div class="row invoice-info">
							<div class="col-sm-4 invoice-col">
								<table style="width: 100%;" class="table">
									<tr>
										<td style="text-align: right; width: 42%;"><s:property
												value="getText('order.id')" /> :</b></td>
										<td><b><span id="ordId"><s:property value="order.id.orderId" /></span></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('order.date')" /> :</b></td>
										<td><b><s:date name="order.id.orderDate"
													format="%{getText('format.date')}" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right; "><s:property
												value="getText('effective.date')" /> :</b></td>
										<td><b><s:date name="order.effectiveDate"
													format="%{getText('format.date')}" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('status.label')" /> :</b></td>
										<td><b><s:property value="order.status" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('shipping.address')" /> :</b></td>
										<td style="word-break: break-word;"><address>
												<b> <s:property value="ord_tran_header.ctDvrInf" />
												</b>
											</address></td>
									</tr>
								</table>
							</div>
							<!-- /.col -->
							<div class="col-sm-4 invoice-col">
								<table style="width: 100%;" class="table">
									<tr>
										<td style="text-align: right;  width: 42%;"><s:property
												value="getText('customer.id')" /> :</b></td>
										<td><b><s:property
													value="customer.customerHeaderPK.custId" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('customer.name')" /> :</b></td>
										<td><b><s:property value="customer.ctNm" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('customer.segment')" /> :</b></td>
										<td><b><s:property value="cust_seg" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('LPO.label')" /> :</b></td>
										<td><b><s:property value="ord_tran_sum.custLpoNum" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('lpo.date')" /> :</b></td>
										<td><b><s:date name="ord_tran_sum.custLpoDate"
													format="%{getText('format.date')}" /></b></td>
									</tr>
	
	
								</table>
							</div>
							<!-- /.col -->
							<div class="col-sm-4 invoice-col">
								<table style="width: 100%;" class="table">
									<tr>
										<td style="text-align: right;  width: 42%;"><s:property
												value="getText('invoice.no')" /> :</b></td>
										<td><b><s:property value="order.invoiceId" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('shipment.method')" /> :</b></td>
										<td><b> <s:iterator
													value="ord_tran_header.getOrdTranLineItems()"
													status="itStatus">
													<s:if test="itmTy.toString().equalsIgnoreCase('2')">
														<s:property value="deItmShrtRcpt" />
													</s:if>
												</s:iterator></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('sales.agent')" /> :</b></td>
										<td style="word-break: break-word;"><b><s:property value="order.salesAgent" /></b></td>
									</tr>
									<tr>
										<td style="text-align: right;"><s:property
												value="getText('comments.label')" /> :</b></td>
										<td><b><s:property
													value="ord_tran_header.ctDvrInfoIns" /></b></td>
									</tr>
      								<c:if test = "${order.status != Quoted}">
        							<tr>	
										<td style="text-align: right;"><s:property
												value="getText('timestamp.label')" /> :</b></td>
										<td style="word-break: break-word;"><b><s:property value="ord_timestamp" /></b></td>
									</tr>
									</c:if>
								</table>
							</div>
							<!-- /.col -->
	
						</div>
						<!-- /.row -->
						<%-- <div class="row">
							<s:if
								test="%{errorMessage!=null&&!errorMessage.equalsIgnoreCase('')}">
								<ul class="bg-danger text-danger">
									<li><s:actionerror /> <s:property value="errorMessage" />
										</span></li>
								</ul>
							</s:if>
						</div> --%>
	
						<!-- Table row -->
						<div class="row">
							<div class="col-xs-12 table-responsive">
								<table class="table table-striped" style="margin-bottom: 2px;">
									<thead>
									
										<tr style="background-color: #ADC2EE;">
											<th><s:property value="getText('table.head.SNo')" /></th>
											<th><s:property value="getText('table.head.item')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('order.qty')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('table.head.vpn')" /></th>
											<s:if test ="ord_tran_header.scOrd!=7">
												<th style="text-align: right;"><s:property
													value="getText('shipped.qty')" /></th>
											</s:if>
											<s:else>	
											<th style="text-align: right;"><s:property
													value="getText('delivered.qty')" /></th>
											</s:else>
											<th style="text-align: right;"><s:property
													value="getText('table.head.unitprice')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('order.disc.amt')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('table.head.tax')" /></th>
											<th style="text-align: right;"><s:property
													value="getText('table.head.total')" /> (<s:property
													value="getText('global.currency')" />)</th>
	
										</tr>
									</thead>
									<tbody>
										<s:set var="totalItemQty" value="0" />
										<s:iterator value="order_items" status="itStatus">
											<tr>
												<s:set var="totalItemQty" value="%{#totalItemQty+lineQnt}" />
												<td><s:property value="#itStatus.count" /> <s:set
														var="bagged_qty" value="#itStatus.count" />.</td>
												<td><s:property value="itemId" /><br> <s:property
															value="deItmShrtRcpt" />
												
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
												<td style="text-align: right;"><s:property
														value="lineQnt" /></td>
												<td style="text-align: right;"><s:property
														value="registryId" /></td>
												<s:if test="shippedQty!=null ">  <!-- Stock Item -->
													<s:set var="deliveredQty" value="shippedQty" />
												</s:if>
												<s:elseif test="itmTy!=null && itmTy==2"> <!-- Service Item -->
													<s:set var="deliveredQty" value="lineQnt" />
												</s:elseif>
												<s:else>
													<s:set var="deliveredQty" value="0" />
												</s:else>
												<td style="text-align: right;"><s:property
														value="#deliveredQty" /></td>
												<%-- <s:set var="itemPrc" value="" /> --%>
												<%-- <s:if test="ovrdPrc!=null">
													<s:set var="itemPrc" value="ovrdPrc" />
												</s:if>
												<s:else>
													<s:set var="itemPrc" value="itmPrnPrc" />
												</s:else> --%>
												<td style="text-align:right;">
           										 	<s:if test="overRidePrice != null">
            										<s:property value="%{getText('format.currency.args',{overRidePrice})}"/>
           											 </s:if>
           											 <s:else>
            											<s:property value="%{getText('format.currency.args',{itmPrnPrc})}"/>
            										</s:else>
      											</td>
												<%-- <td style="text-align: right;"><s:property
														value="%{getText('format.currency.args',{#itemPrc})}" /></td> --%>
												<s:if test="flItmDsc==0">
													<td style="text-align: right;"><s:property
															value="%{getText('format.currency.args',{extnLnItmRtn-extnDscLnItm})}" /></td>
												</s:if>
												<s:else>
													<td style="text-align: right;"><s:property value="%{0}" /></td>
												</s:else>
												<td style="text-align: right;"><s:property
														value="%{getText('format.currency.args',{vatLnItmRtn})}" /></td>
												<td style="text-align: right;"><s:property
														value="%{getText('format.currency.args',{extnDscLnItm})}" /></td>
											</tr>
	
										</s:iterator>
	
	
									</tbody>
								</table>
								<div class="progress" style="height: 2px;">
									<div class="progress-bar"
										style="width: 100%; background-color: #ADC2EE;"></div>
								</div>
							</div>
							<!-- /.col -->
						</div>
	
						<!-- /.row -->
	
						<div class="row">
							<div class="col-lg-7 col-md-6 col-xs-12">
								<s:actionerror  id="orderError" theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss" />
								<s:actionmessage id="orderMessage" theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss" />
							</div>
							<!-- /.col -->
							<div class="col-lg-5 col-md-6 col-xs-12">
	
								<div class="table-responsive">
									<table class="table">
										<tbody>
											<tr>
												<td style="text-align: right;"><s:property
														value="getText('no.of.items')" /> :</td>
												<td style="text-align: right;"><b> <s:property
															value="ord_tran_header.getOrdTranLineItems().size()" /></b></td>
											</tr>
											<tr>
												<td style="text-align: right;"><s:property
														value="getText('total.qty')" /> :</td>
												<td style="text-align: right;"><b><s:property
															value="%{#totalItemQty}"/></b></td>
											</tr>
											<tr>
												<td style="text-align: right;" style="width:50%"><s:property
														value="getText('subtotal.label')" /> :</td>
												<td style="text-align: right;"><s:property
														value="getText('global.currency')" />&nbsp;&nbsp;<b><s:property
															value="%{getText('format.currency.args',{ord_tran_sum.dkartSlsTot})}" /></td>
											</tr>
											<tr>
												<td style="text-align: right;"><s:property
														value="getText('discount.label')" /> :</td>
												<td style="text-align: right;"><b><s:property
															value="%{getText('format.currency.args',{ord_tran_sum.dkartDscTot})}" /></td>
											</tr>
											<tr>
												<td style="text-align: right;"><s:property
														value="getText('total.tax')" /> :</td>
												<td style="text-align: right;"><b><s:property
															value="%{getText('format.currency.args',{ord_tran_sum.dkartTaxTot})}" /></td>
											</tr>
											<tr style="font-size: 18px;">
												<td style="text-align: right;"><s:property
														value="getText('net.total')" /> :</td>
												<td style="text-align: right;"><s:property
														value="getText('global.currency')" />&nbsp;&nbsp;<b><s:property
															value="%{getText('format.currency.args',{ord_tran_sum.dkartNetTot})}" /></b></td>
											</tr>
										</tbody>
									</table>
								</div>
								<s:if test="%{orderCancelAvailable}">
									<button type="submit" class="btn btn-primary pull-left hidden-print"
										data-toggle="modal" data-target="#confirmModal" type="submit" >
										<s:property value="getText('cancel.button')" />
									</button>
								</s:if>
								<s:if test="%{partialShippingAvailable}">
									 <button 
                                        <shiro:hasPermission name="partialShipping">
                                            class="btn btn-primary hidden-print" data-toggle="modal" data-target="#confirmPartialShip">
                                        </shiro:hasPermission>
                                        <shiro:lacksPermission name="partialShipping">
                                            class="btn btn-primary hidden-print" title="Partial Shipping permission needs to enabled" disabled="disabled">
                                        </shiro:lacksPermission>
                                        <s:property value="getText('partial.ship.button')"  />
                                    ></button>
                                </s:if>
								<button onClick="history.go(-1);return true;" type="button"
									class="btn btn-primary pull-right hidden-print" >
									<s:property value="getText('back.button')" />
								</button>
								<s:set var="oStatus" value="order.status"/>
								<s:if test="%{partialShippingAvailable==false}" >
								<s:if test="%{#oStatus== 'In-Progress'}">
									<button type="submit" class="btn btn-primary pull-left hidden-print"
										data-toggle="modal" data-target="#inprogressModal" type="submit" >
										<s:property value="getText('inProgress.button')" />
									</button>
									</s:if>
								</s:if>
	
							</div>
							<!-- /.col -->
						</div>
						<!-- /.row -->
	
						<!-- this row will not appear when printing -->
						<div class="row">
							<div class="col-xs-12"></div>
						</div>
					</section>
				</s:else>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->



		<!-- /.control-sidebar -->

	</div>
	
	<!-- To show confirm inprogress model -->
	<div id="inprogressModal" class="modal fade overlay1" role="dialog">

		<div class="modal-dialog" style="min-height: 120px;">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<s:form action="inProgressOrder" method="post">
						<p class="login-box-msg">
						<h4>
							We are going to reopen the Order ID in WMS :<b> <s:property
									value="ord_tran_header.getOrdTranSum().getIdOrd()" /></b>
						</h4>
						</p>
						
						<s:hidden name="orderId"
							value="%{ord_tran_header.getOrdTranSum().getIdOrd()}"></s:hidden>
				</div>
	
				<div>
					<table>
						<tr>
							<td style="text-align: right; width: 50%;">Do you want Reopen the Order In WMS:</td>
							<td>
								<button button class="btn btn-block btn-info"
									style="max-width: 100px; background: #bf9000; background: -webkit-linear-gradient(#073763, #3d85c6 50%, #073763); background: linear-gradient(#073763, #3d85c6 50%, #073763); color: #fff; margin: auto;"
									type="submit">Confirm</button>
							</td>
						</tr>
					</table>
				</div>

          
			</div>
			</s:form>
		</div>
	</div>
	<!-- End of model -->
	
	
	
	
	
	
	
	

	<!-- To show confirm cancel model -->
	<div id="confirmModal" class="modal fade overlay1" role="dialog">

		<div class="modal-dialog" style="min-height: 120px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<s:form action="CancelOrder" method="post">
						<p class="login-box-msg">
						<h4>
							Write Comments To Cancel Order ID :<b> <s:property
									value="ord_tran_header.getOrdTranSum().getIdOrd()" /></b>
						</h4>
						</p>
						<%-- <s:hidden name="ord_tran_header" value="ord_tran_header"></s:hidden> --%>
						<s:hidden name="orderId"
							value="%{ord_tran_header.getOrdTranSum().getIdOrd()}"></s:hidden>
				</div>
				<div class="modal-body">
					<textarea class="form-control"
						style="min-height: 120px; margin: 1px; overflow: auto;"
						 maxlength="250"
						placeholder="Please Enter Comments For Cancel:" name="transComment" required></textarea>
				</div>
				<div>
					<table>
						<tr>
							<td style="text-align: right; width: 50%;"><s:property
									value="getText('reason.code')" /> :</td>


							<td><s:select id="reasonCode" name="reasonCode"
										list="globalReasonCode" class="form-control"
										style="font-weight: bold; font-size: 12px; color: black"
										theme="simple">
									</s:select>
								</td>
							<td>
								<button button class="btn btn-block btn-info"
									style="max-width: 100px; background: #bf9000; background: -webkit-linear-gradient(#073763, #3d85c6 50%, #073763); background: linear-gradient(#073763, #3d85c6 50%, #073763); color: #fff; margin: auto;"
									type="submit">Confirm</button>
							</td>
						</tr>
					</table>
				</div>

			</div>
			</s:form>
		</div>
	</div>
	<!-- End of model -->

	<!-- ./wrapper -->

	<!-- ChartJS 1.0.1 -->
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

	<div class="modal fade" tabindex="-1" role="dialog"	id="confirmPartialShip">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Confirm Partial Shipment</h4>
				</div>
				<div class="modal-body">
					<p>Items that are ready for shipment will be invoiced and the remaining items will be cancelled. Do you want to continue with Partial Shipment?</p>
					<strong class="text-success"><p class="text-center">Below Items will be shipped</p></strong>
					<table class="table table-condensed table-striped" >
						<thead>
							<tr style="background-color: #ADC2EE;">
								<th><s:property value="getText('table.head.item')" /></th>
								<th style="text-align: right;"><s:property
										value="getText('order.qty')" /></th>
								<th style="text-align: right;">Ready to Ship Qty</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="itemsShipped" var="shipped">
								<tr>
									<td><s:property value="key" /><br> <small><s:property
												value="value[0]" /></small></td>
									<td style="text-align: right;"><s:property
											value="value[1]" /></td>
									<td style="text-align: right;"><s:property
											value="value[2]" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					
					<strong class="text-danger"><p class="text-center">Below Items will be cancelled</p></strong>
					<table class="table table-condensed table-striped" >
						<thead>
							<tr style="background-color: #ADC2EE;">
								<th><s:property value="getText('table.head.item')" /></th>
								<th style="text-align: right;"><s:property
										value="getText('order.qty')" /></th>
								<th style="text-align: right;">Cancel Qty</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="itemsNotShipped" var="notShipped">
								<tr>
									<td><s:property value="key" /><br> <small><s:property
												value="value[0]" /></small></td>
									<td style="text-align: right;"><s:property
											value="value[1]" /></td>
									<td style="text-align: right;"><s:property
											value="value[2]" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				
				
				
				</div>
				<div class="modal-footer">
					<form class="row form-inline" action="partialShipping" method="post">
						<s:hidden name="orderId" value="%{orderId}"/>
						<div class="col-xs-6">
							<label class="control-label" for="partialShippingReasonCode"><s:property value="getText('reason.code')" /> :</label>
							<s:select id="partialShippingReasonCode" name="partialShippingReasonCode"
								list="globalReasonCode" class="form-control"
								theme="simple" requiredLabel="true">
							</s:select>
						</div>
						<div class="col-xs-6">	
							<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
							<button type="submit" class="btn btn-primary">Yes</button>
						</div>
					</form>
					<!-- <button type="button" class="btn btn-primary">Yes</button> -->
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

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
        var customer=$('#customr').is(":checked");
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
	
	$('#printWindow').click(function(event) {
    	window.print();
    	var lpoSlipType = "<s:property value="ord_tran_header.getOrdTranLpo().getLpoSlipType()"/>";
		if(lpoSlipType){
			var win = window.open('PrintLPODetails?orderID=<s:property value="ord_tran_header.getOrdTranSum().getIdOrd()"/>','_blank');
		}
    });
    
  	/* $('#dwnldLPO').click(function(event) {
    	var lpoSlipType = "<s:property value="ord_tran_header.getOrdTranLpo().getLpoSlipType()"/>";
		if(lpoSlipType){
			var win = window.open('<s:property value="#downloadLPOUrl"/>','_blank');
		}
    }); */
    </script>
	
</body>
</html>