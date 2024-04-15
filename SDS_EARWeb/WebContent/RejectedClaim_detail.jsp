<%@ page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="utf-8"%>
    <!-- changed by @ jagadish because this (charset=ISO-8859-1 And  pageEncoding="ISO-8859-1) does not supporting
	to display Arabic language.  -->
<%@taglib prefix="s" uri="/struts-tags"%>
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
    <link rel="stylesheet" href="assets/css/popup.css">
    <link rel="stylesheet" href="assets/font-awesome-4.6.3/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="assets/css/Customizations.css">
    <script src="Starter_files/jQuery-2.js"></script>
    <script src="Starter_files/bootstrap.js"></script>
    <script src="Starter_files/app.js"></script>
  

    
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
        
    <div class="error-content hidden-lg hidden-md">



					<form class="search-form hidden-print" action="Customer_Page_b.html"
						method="get">
						<div class="input-group">
							<input type="text" placeholder="Customer Search"
								class="form-control" name="search">

							<div class="input-group-btn">
								<a href="Customer_Page_b.html"><button class="btn bg-blue"
										name="submit">
										<i class="fa fa-search"></i>
									</button></a>
							</div>
						</div>
						<!-- /.input-group -->
					</form>
					<br><br>
    </div>
        
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
        <small class="pull-right hidden-print" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><s:property value="getText('claim.details.help_text')"/></small>
      <div class="row">
        <div class="col-xs-12">
          <h2 style="color: #226e71;">
             <s:property value="getText('rejected.claimdetails')"/>
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
                    <a href="javascript:void(0);"  onclick=" windowPrint()" class="pull-right"><i class="fa fa-print"></i>  <s:property value="getText('print.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a>
                  <a href="rejectedClaimPDFDownload?claimID=<s:property value="claimID"/>"><i class="fa fa-save"></i>  <s:property value="getText('save.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a></div>
<!--            <small class="pull-right">Date: 2/10/2014</small>-->
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
        <div class="col-sm-4 invoice-col">
        <table style="width:100%;" class="table">   
         <tr><td style="text-align:right;"><s:property value="getText('claim.id')"/>:</td><td><b><span id="claimId"><s:property value="claimID"/></span></b></td></tr>
         <tr><td style="text-align:right;width:50%;"><s:property value="getText('claim.date')"/>:</td><td><b><s:date name="claimedDate" format="%{getText('format.date')}"/></b></td></tr>
         <tr><td style="text-align:right;width:50%;"><s:property value="getText('status.label')"/>:</td><td><b><s:property value="claimStatus"/></b></td></tr>
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
          <table style="width:100%;" class="table">
            <tr><td style="text-align:right;"><s:property value="getText('rejection.date')"/>:</td><td><b><s:date name="rejectedClaimDate" format="%{getText('format.date')}"/></b></td></tr>
      	 	<tr><td style="text-align:right;width:50%;"><s:property value="getText('rejection.comments')"/>:</td><td><b><s:property value="rejectClaimNotes"/></b></td></tr> 
       		<tr><td style="text-align:right;"><s:property value="getText('reason.code')"/>:</td><td><b><s:property value="globalReasonCodeName"/></b></td></tr>
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">  
          <table style="width:100%;" class="table">      
         	<tr><td style="text-align:right;"><s:property value="getText('customer.id')"/>:</td><td><b><s:property value="custId"/></b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('customer.name')"/>:</td><td><b><s:property value="customerName"/></b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('claim.pickup.addrss')"/>:</td><td><b><s:property value="site_address"/></b></td></tr>
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
                <th style="text-align:right;"><s:property value="getText('registered.qty')"/></th>
                <th style="text-align:right;"><s:property value="getText('table.head.unitprice')"/></th>
               <th style="text-align:right;"><s:property value="getText('registered.price')"/></th>
               <th style="text-align:right;"><s:property value="getText('table.head.disc')"/></th>
               <th style="text-align:right;"><s:property value="getText('final.price')"/></th>
                <th style="text-align:center;"><s:property value="getText('reason.code')"/></th>
            </tr>
            </thead>
            <tbody>
             <s:iterator  value="claimTranLineItems" status="itStatus">  
            <tr>
               <td><s:property value="#itStatus.count" />.</td>
               <td><s:property value="itemId"/><br><s:property value="deItmShrtRcpt"/></td>
               <td style="text-align:right;"><s:property value="lineQntRtn" /></td>
               <td style="text-align:right;"><s:property value="itmPrnPrc" /></td>
               <s:set var="itm_price" value="itmPrnPrc"/>
               <s:set var="disc" value="0"/>
               <s:set var="unitDsc" value="0"/>
               <s:iterator value="claimTranDscItms" status="discStatus">
               		<s:set var="disc" value="#disc + (unitDscAmt * lineQntRtn)"/>
               		<s:set var="unitDsc" value="#unitDsc + unitDscAmt"/>
               </s:iterator>
               <s:set var="itm_price" value="#itm_price - #unitDsc"/>
               <s:if test="%{ovrdPrc != null}">
               	<s:set var="itm_price" value="ovrdPrc"/>
               </s:if>
               <td style="text-align:right;"> <s:property value="%{getText('format.currency.args',{#itm_price})}"/></td>
               <td style="text-align:right;"> <s:property value="%{getText('format.currency.args',{#disc})}"/></td>
               <td style="text-align:right;"><s:property value="%{getText('format.currency.args',{#itm_price * lineQntRtn})}"/></td>
               <td style="text-align:center;">${returnReasonCodeMap[rcRtnMr] }</td>
               
                           <%-- 
             <td>
             <s:set var="lnQty" value=""/>
             <s:if test="%{lineQntRtn==null}">
             <s:set var="lnQty" value="%{lineQnt}"/>
             </s:if>
             <s:else>
             <s:set var="lnQty" value="%{lineQntRtn}"/>
             </s:else>
             <s:property value="lnQty" />
             </td> --%>
               <%-- <td><s:property value="apprClaimQty"/></td>
               <td><s:property value="whReceiveQty"/></td> --%>
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
                <td style="width:50%;text-align:right;"><s:property value="getText('subtotal.label')"/>:</td>
                <td style="width:50%;text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b> <s:property value="%{getText('format.currency.args',{dkartSlsTot})}"/></b>
              </tr>
             <tr>
                <td style="text-align:right;"><s:property value="getText('discount.label')"/>:</td>
                <td style="width:50%;text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b><s:property value="%{getText('format.currency.args',{dkartDiscTot})}"/></b></td>
              </tr>
              <tr style="text-decoration:none">
				<td style="text-align: right;"><s:property value="getText('expenses.label')"/>:</td>
				<td style="width:50%;text-align:right;"><s:property value="%{getText('format.currency.args',{orderTran.ordTranSum.dkartExpenses})}"/></b></th>
			 </tr>
             <tr style="font-size:16px;">
                <td style="text-align:right;"><s:property value="getText('total.refund')"/>:</td>
                <td style="width:50%;text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b><s:property value="%{getText('format.currency.args',{dkartNetTot})}"/></b></td>
              </tr>
            </tbody>
             </table>
          </div>
 <%--        <a href="<s:url action="SaveApproveClaim"/>">    
        <button type="button" class="btn  pull-left" style="color:#ffffff; width:100px;margin-top:5px; background:    #3d85c6;
background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
background:    linear-gradient(#1770c1, #073763 50%, #1770c1);  
color:         #fff;
display:       inline-block;">
            Approve
          </button>
        </a>
                
        <a href="<s:url action="rejectClaimButton"/>">    
        <button type="button" class="btn  pull-right" style="width:100px;background:    #666;margin-top:5px;
background:    -webkit-linear-gradient(#717171, #262626 20%, #717171);
background:    -linear-gradient(#717171, #262626 20%, #717171);
color:         #fff;">
            Reject
          </button>
        </a> --%>
           
           <button onclick="history.go(-1);return true;" type="button" class="btn  pull-right hidden-print" style="color:#ffffff; width:100px;margin-top:5px; background:    #3d85c6;
background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
background:    linear-gradient(#1770c1, #073763 50%, #1770c1);  
color:         #fff;
display:       inline-block;">
           <s:property value="getText('done.button')"/>
          </button>
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
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  

  <!-- Control Sidebar -->
  
    
   
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div style="position: fixed; height: auto;" class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

	<script type="text/javascript">
    $('#claim').addClass('active');
    $('#rejectedclaims').addClass('active');
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


<!-- ChartJS 1.0.1 -->

</body></html>