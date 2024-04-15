<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags"%>
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
	<title>SDS | Register Claim</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

	<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css" type="text/css" />
	<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css" type="text/css" />
	<link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap-theme.css" type="text/css" />
	<link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />
	<link rel="stylesheet" href="assets/css/Customizations.css">
<!-- 	
	<link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
	<link rel="stylesheet" href="assets/css/popup.css"> 
-->
	
	<script src="Starter_files/jQuery-2.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="bootstrap-3.3.6-dist/js/bootstrap.js"></script>

	<!-- AdminLTE App -->
	<script src="Starter_files/app.js"></script>
	<!-- having function for print --> 
     <script src="custom/printFunction.js"></script>
	<!-- Select2 -->
<%-- 	
	<script src="assets/plugins/select2/select2.full.min.js"></script>
	<!-- InputMask -->
	<script src="assets/plugins/input-mask/jquery.inputmask.js"></script>
	<script src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<!-- date-range-picker -->
	<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap datepicker -->
	<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>
--%>

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
	
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
					<small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><s:property value="getText('newclaimwithoutinv.help_text')"/></b></small>
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
                 					 <a href="#"  class="pull-right"><i class="fa fa-print" style="text-decoration:none" onclick="windowPrint()"></i> <s:property value="getText('print.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a>
                 						<a href="registerClaimWithInvPDFDownload?claimID=<s:property value="newClaim.claimID"/>" class="pull-right" style="text-decoration: none"><i class="fa fa-save"></i> <s:property value="getText('save.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a></div>
<!--            <small class="pull-right">Date: 2/10/2014</small>-->
          </h2>
						</div>
						<!-- /.col -->
					</div>
					<!-- info row -->
					<div class="row invoice-info">
						<div class="col-sm-4 invoice-col">
							<table style="width:100%;" class="table">
								<tr><td style="text-align:right;width:50%;"><s:property value="getText('claim.id')"/>:</b></td>
									<td><b><span id="claimId"><s:property value="newClaim.claimID"/></span></b></td></tr>
								<tr><td style="text-align:right;width:50%;"><s:property value="getText('claim.date')"/>:</b></td>
									<td><b><s:date name="newClaim.claimDate" format="%{getText('format.date')}"/></b></td>
								</tr>
							</table>
						</div>
						<!-- /.col -->
						<div class="col-sm-4 invoice-col">
							<table style="width:100%;" class="table">
								<tr><td style="text-align:right;"><s:property value="getText('reason.code')"/>:</b></td>
									<td><b><s:property value="newClaim.claimReasonCodeDesc" /></b></td></tr>
								<tr><td style="text-align:right;width:50%;"><s:property value="getText('status.label')"/>:</b></td>
									<td><b><s:property value="getText('new_status')"/></b></td>
								</tr>
								<tr><td style="text-align: right;"><s:property value="getText('sales.agent')"/>:</b></td>
							    <td><b><s:property value="salesAgentName" /></b></td>
								</tr>
							</table>
						</div>
						<!-- /.col -->
						<div class="col-sm-4 invoice-col">
							<table style="width:100%;" class="table">
							<tr><td style="text-align:right;"><s:property value="getText('customer.id')"/>:</b></td>
									<td><b><s:property value="newClaim.customerID"/></b></td></tr>
								<tr><td style="text-align:right;"><s:property value="getText('customer.name')"/>:</b></td>
									<td><b><s:property value="newClaim.customerName"/></b></td></tr>	
								<tr>
									<td style="text-align: right;"><s:property value="getText('claim.pickup.addrss')"/>:</b></td>
									<td><b><s:property value="site_address"/></b></td>
								</tr>
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
										<th style="text-align:right;"><s:property value="getText('sold.qty')"/></th>
										<th style="text-align:right;"><s:property value="getText('table.head.unitprice')"/></th>
										<th style="text-align:right;"><s:property value="getText('sold.at')"/></th>
										<th style="text-align:right;"><s:property value="getText('qty.return')"/></th>
										<th style="text-align:right;"><s:property value="getText('price.head')"/></th>
										<th style="text-align:right;"><s:property value="getText('table.head.disc')"/></th>
										<th style="text-align:right;"><s:property value="getText('final.price')"/></th>
										<th><s:property value="getText('reason.code')"/></th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="newClaim.claimItemsList" status="claimItemsStatus">
									
									<s:if test="%{claimQuantityRegistered>0}">
										<tr>
											<td><s:property value="#claimItemsStatus.count" />.
											<td><s:property value="itemID"/><br><small><s:property value="itemDescription"/></small></td>
											<td style="text-align:right;"><s:property value="quantitySold"/></td>
											<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{unitPrice})}" /></td>
											<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{priceSoldAt})}" /></td>
											<td style="text-align:right;"><s:property value="claimQuantityRegistered"/></td>
											<%-- <s:if test="%{priceChangeReasonCode==null}"> --%>
											<s:if test="%{claimPriceRegistered == null}">
											<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{priceSoldAt})}" /></td>
											<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{itemLevelDiscAmt + discountAmount})}" /></td>
											<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{priceSoldAt * claimQuantityRegistered})}" /></td>
											</s:if>
											<s:else>
												<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{claimPriceRegistered})}" /></td>
												<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{itemLevelDiscAmt + discountAmount})}" /></td>
												<td style="text-align:right;"><s:property value="%{getText('format.currency.args',{claimPriceRegistered * claimQuantityRegistered})}" /></td>
											</s:else>
									<%-- <td><s:property value="netPrice"/></td> --%>
											<td><s:property value="claimReasonCodeValue"/></td>
										</tr>
									</s:if>
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
											<td style="text-align:right;" style="width:50%"><s:property value="getText('subtotal.label')"/>:</td>
											<td><s:property value="getText('global.currency')"/>&nbsp;<b><s:property value="%{getText('format.currency.args',{claimTotals[0]})}" /></b></td>
										</tr>
										<tr>
											<td style="text-align:right;"><s:property value="getText('discounts.label')"/>:</td>
											<td><s:property value="getText('global.currency')"/>&nbsp;<b>
											<s:if test="claimTotals[3]!=null"><s:property value="%{getText('format.currency.args',{claimTotals[3]})}" /></s:if>
											<s:else><s:property value="%{getText('format.currency.args',{0.0})}"/></s:else>
											</b></td>
										</tr>
										<tr>
											<td style="text-align:right;"><s:property value="getText('expenses.label')"/>:</td>
											<td><s:property value="getText('global.currency')"/>&nbsp;<b>
											<s:if test="claimTotals[1]!=null"><s:property value="%{getText('format.currency.args',{claimTotals[1]})}" /></s:if>
											<s:else><s:property value="0"/></s:else>
											</b></td>
										</tr>

										<tr style="font-size:18px;">
											<td style="text-align:right;"><s:property value="getText('total.refund')"/>:</td>
											<td><s:property value="getText('global.currency')"/>&nbsp;<b><s:property value="%{getText('format.currency.args',{claimTotals[2]})}" /></b></td>
										</tr>
									</tbody>
								</table>
							</div>
                                                       <input type="text" name="ClaimRef"
														placeholder="Claim Ref" id="ClaimRef"
														autofocus="autofocus"
														class="form-control" >	
							<a href="generateClaim">
							 												
										
								<button type="button" onclick="sendClaimRef()" class="btn  pull-right hidden-print" style="color:#ffffff; width:100px;margin-top:5px; background:    #3d85c6;
background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
background:    linear-gradient(#1770c1, #073763 50%, #1770c1);  
color:         #fff;
display:       inline-block;">
									<s:property value="getText('done.button')"/>
								</button>
							</a>
<!-- <form action="editClaimWI">
								<button type="submit" class="btn  pull-right hidden-print"
									style="color: #ffffff; width: 100px; margin-top: 5px; background: #3d85c6; background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; display: inline-block;">
									edit</button>
							</form> -->
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
				<!-- Your Page Content Here -->


			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		
	</div>
	<!-- ./wrapper -->
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
	function sendClaimRef(){
		//alert("IN sendClaimRef");
		  var ClaimRef = document.getElementById("ClaimRef").value;
		  if (!ClaimRef==null || !ClaimRef == undefined || !ClaimRef == "" || !ClaimRef.length == 0)
		  {
			     
			    // addClaimRef(ClaimRef);
			 // var claimRef =ClaimRef;
				var claimId = document.getElementById("claimId").innerText;
				
				var today = new Date();
				var dd = today.getDate();
				var mm = today.getMonth() + 1; //January is 0!

				var yyyy = today.getFullYear();
				if (dd < 10) {
				  dd = '0' + dd;
				} 
				if (mm < 10) {
				  mm = '0' + mm;
				} 
				var claimDate = dd + '-' + mm + '-' + yyyy;

				  					
				$.ajax({
					url : "addClaimRefNo",
					type : "POST",
					data : {
						claimRefNo :  ClaimRef,
						claimId :   claimId,
						claimRefdate : claimDate					
					},
					dataType : "json"
					//success : function(jsonResponse) {
					//alert(JSON.stringify(jsonResponse));	      
					//}
				});	
  
			    
		 }           
		                	     
		}
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
        var custom=document.getElementById("custommails").value;
    	var claimId=document.getElementById("claimId").innerHTML;
	   	   
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