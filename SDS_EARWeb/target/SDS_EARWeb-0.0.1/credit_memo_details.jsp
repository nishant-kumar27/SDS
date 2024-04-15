<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
  <title>SDS</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
  
  <!-- Font Awesome -->
        <link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />

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
						<div class="col-md-2 col-xs-2 visible-print-inline">
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
					<small class="pull-right hidden-print" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><s:property value="getText('credit.memo.help_text')"/>  </b></small>
      <div class="row">
        <div class="col-xs-12">
          <h2 style="color: #226e71;">
             <s:property value="getText('credit.memo.header.details')"/>
             
             <div class="pull-right hidden-print"><h5>
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
                							
                    <a href="javascript:void(0);" onclick="windowPrint()"  class="pull-right"><i class="fa fa-print"></i><s:property value="getText('print.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a>
                  <a href="creditNotePDFDownload?crmemoId=<s:property value="bean.crediMemoId"/>"><i class="fa fa-save"></i> <s:property value="getText('save.label')"/>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></div>
                   
          </h2>
        </div>
        <!-- /.col -->
      </div>
       <div class="row invoice-info">
        <div class="col-sm-6 invoice-col">
        <table style="width:100%;" class="table">
            <tr><td style="text-align:right;width:50%;"> <s:property value="getText('credit.memo.details.creditmemo.id')"/> :</b></td><td><b><span id="crmemoId"><s:property value="bean.crediMemoId"/></span></b></td></tr>
       		<tr><td style="text-align:right;"> <s:property value="getText('credit.memo.details.creditmemo.date')"/>  :</b></td><td><b><s:property value="bean.crMemoDate"/></b></td></tr>
           <tr><td style="text-align:right;"> <s:property value="getText('credit.memo.details.creditmemo.amount')"/>  :</b></td><td><b><s:property value="%{getText('format.currency.args',{bean.crMemoAmount})}" /></b></td></tr>
           <tr><td style="text-align:right;"> <s:property value="getText('credit.memo.details.creditmemo.claimid')"/>  :</b></td><td><b><span id="claimId"><s:property value="bean.claimId"/></span></span></b></td></tr>
        </table>
        </div>
         <div class="col-sm-6 invoice-col">
          <table style="width:100%;" class="table">
          	<tr><td style="text-align:right;"><s:property value="getText('customer.id')"/> :</b></td><td><b><span id="customerId"><s:property value="claimTransaction.customer.customerHeader.customerHeaderPK.custId"/></span></b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('customer.name')"/>:</b></td><td><b><span id="CustomerName"><s:property value="claimTransaction.customer.customerHeader.ctNm"/></span></b></td></tr>        
        </table>
        </div>
        
        </div>
         <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped" style="margin-bottom: 2px;">
            <thead>
            <tr style="background-color:#ADC2EE;">
              <th><s:property value="getText('table.head.SNo')"/></th>
              <th><s:property value="getText('table.head.item')"/></th>
              <th class="text-right"><s:property value="getText('order.qty')"/></th>
              <th class="text-right"><s:property value="getText('price.head')"/></th>
              <th class="text-right"><s:property value="getText('table.head.tax')"/></th>
              <th class="text-center"><s:property value="getText('sales.agent')"/></th>
              <th class="text-right"><s:property value="getText('total.label')"/>(<s:property value="getText('global.currency')"/>)</th>
            </tr>
            </thead>
                 <tbody id="claimDetail">
             <s:iterator value="claimTransaction.ordTranLineItems"  status="itStatus">
            <tr>
            
             <td><s:property value="%{#itStatus.index+1}"/>.</td>
      		
      		 <td><s:property value="itemId"/><br><s:property value="deItmShrtRcpt"/></td>
             
             <td class="text-right"><s:property value="lineQnt"/></td>
      
             <td class="text-right"><s:property value="%{getText('format.currency.args',{itmPrnPrc})}"/></td>
             
             <s:set var="tax" value="%{vatLnItmRtn}"/>
             <s:if test="%{tax!=null}">
		     <td class="text-right"><s:property value="%{getText('format.currency.args',{vatLnItmRtn})}"/></td>
		     </s:if>
		     <s:else>
             <td class="text-right"><s:property value="%{getText('format.currency.args',{0})}"/></td>
             </s:else>
         	 <td class="text-center"><s:property value="SalesAgents[#itStatus.index]"/></td>
      
             <td class="text-right"> <s:property value="%{getText('format.currency.args',{extnDscLnItm})}"/></td>
   					
     	   </tr>
      </s:iterator>
            </tbody>
          </table>
        </div>
        </div>
        <!-- /.col -->
           <div class="progress" style="height: 2px;">
				<div class="progress-bar" style="width: 100%; background-color: #ADC2EE;"></div>
			</div>
        <div class="row">
        <!-- /.col -->
        <div class="col-lg-4 col-md-6 col-xs-12 pull-right">

          <div class="table-responsive">
            <table class="table">
              <tbody><tr>
                <td class="text-right" style="width:50%"><s:property value="getText('subtotal.label')"/>:</td>
                <td class="text-right"><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b><span id="Subtotal"><s:property value="%{getText('format.currency.args',{claimTransaction.ordTranSum.dkartSlsTot})}"/></span></b></td>
                  </tr>
              <tr>
                <td class="text-right"><s:property value="getText('total.discount')"/>:</td>
                <td class="text-right"> <span id="Discount"><b><s:property value="%{getText('format.currency.args',{claimTransaction.ordTranSum.dkartDscTot})}"/></b></span></td>
              </tr>
              <tr>
                <td class="text-right"><s:property value="getText('total.tax')"/>:</td>
                <td class="text-right"> <span id="TotalTax"><b><s:property value="%{getText('format.currency.args',{claimTransaction.ordTranSum.dkartTaxTot})}"/></b></span></td>
              </tr>
              <tr >
                <td class="text-right"><s:property value="getText('table.head.total')"/>:</td>
                <td class="text-right"><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b> <span id="TotalInvAmt"><s:property value="%{getText('format.currency.args',{claimTransaction.ordTranSum.dkartNetTot})}"/></span></b></td>
              </tr>
            </tbody></table>
          </div>
<!-- REQUIRED JS SCRIPTS -->

  
        </div>
      
        <!-- /.col -->

        <!-- /.col -->
  
      </div>
         <div class="row">
        <!-- /.col -->
        <div class="col-lg-4 col-md-6 col-xs-12 pull-right">
        	  <button onClick="history.go(-1);return true;" type="button" class="btn  pull-right hidden-print" style="color:#ffffff; width:100px;margin-top:5px; background:    #3d85c6;
			background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
			background:    linear-gradient(#1770c1, #073763 50%, #1770c1); 
			color:         #fff;
			display:       inline-block;">
            <s:property value="getText('back.button')"/>
          </button>
      
        </div>
        <!-- /.col -->
      </div>
    </section>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  

  
  <!-- /.control-sidebar -->
  
</div>

<!-- To show confirm cancel model -->

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

<!-- ./wrapper -->

<!-- ChartJS 1.0.1 -->


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
        var customer=$('#customr').is(":checked");
		var deptheadcheck=$('#departmentHead').is(":checked");
		var empsalescheck=$('#salesAgent').is(":checked");
		var dataentrycheck=$('#DataEntryOptr').is(":checked");
		var custom=document.getElementById("custommails").value;
		var claimId=document.getElementById("claimId").innerHTML;
		var crmemoId=document.getElementById("crmemoId").innerHTML;

	   	   
	   if(employeecheck==false && customer == false && deptheadcheck==false && empsalescheck==false && dataentrycheck==false && (custom==null || custom=="" ) ){
	    $("#validatepopover").modal("show");
	   }
	   else{			   
			$.ajax({
				url : "creditmemodetailmail",
				type : "POST",
				data: {
				claimId : claimId,
				crmemoId : crmemoId,
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