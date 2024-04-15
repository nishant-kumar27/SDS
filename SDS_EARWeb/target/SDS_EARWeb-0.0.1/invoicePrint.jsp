<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<style>
.box {
	position: relative;
	border-radius: 3px;
	background: #ffffff;
	border-top: 3px solid #d2d6de;
	margin-bottom: 0px;
	width: 100%;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 0px;
}

.input-group-addon {
	padding: 3px;
}

.form-control {
	display: block;
	width: 100%;
	height: 25px;
	/* padding: 6px 12px; */
	padding: 0px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}

input, button, select, textarea {
	font-family: inherit;
	font-size: inherit;
	line-height: inherit;
}

button, select {
	text-transform: none;
}

button, input, optgroup, select, textarea {
	margin: 0;
	font: inherit;
	color: inherit;
}

* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

user agent stylesheet
keygen, select, select[size="0"], select[size="1"] {
	border-radius: 0px;
	border-color: rgb(169, 169, 169);
}

user agent stylesheet
select:not(:-internal-list-box)
{
overflow:visible!important;
}

user agent stylesheet
select {
	-webkit-appearance: menulist;
	box-sizing: border-box;
	align-items: center;
	border-image-source: initial;
	border-image-slice: initial;
	border-image-width: initial;
	border-image-outset: initial;
	border-image-repeat: initial;
	white-space: pre;
	-webkit-rtl-ordering: logical;
	color: black;
	background-color: white;
	cursor: default;
	border-width: 1px;
	border-style: solid;
	border-color: initial;
}

user agent stylesheet
keygen, select {
	border-radius: 5px;
}

user agent stylesheet
input, textarea, keygen, select, button {
	text-rendering: auto;
	color: initial;
	letter-spacing: normal;
	word-spacing: normal;
	text-transform: none;
	text-indent: 0px;
	text-shadow: none;
	display: inline-block;
	text-align: start;
	margin: 0em 0em 0em 0em;
	font: 13.3333px Arial;
}

user agent stylesheet
input, textarea, keygen, select, button, meter, progress {
	-webkit-writing-mode: horizontal-tb;
}

Inherited from table
table {
	border-spacing: 0;
	border-collapse: collapse;
}

user agent stylesheet
table {
	display: table;
	border-collapse: separate;
	border-spacing: 2px;
	border-color: grey;
}

Inherited from body.skin-blue.sidebar-mini
body {
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
}

body {
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
	font-weight: 400;
	overflow-x: hidden;
	overflow-y: auto;
}

body {
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size: 14px;
	line-height: 1.42857143;
	color: #333;
	background-color: #fff;
}

Inherited from html
html {
	font-size: 10px;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

html {
	font-family: sans-serif;
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
}

Pseudo ::before element
*:before, *:after {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.table-striped>tbody>tr:nth-of-type(odd) {
	background-color: #e4e4e4;
}

Pseudo ::after element
*:before, *:after {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

body {
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
}
</style>
<style>
/*
@media (max-width: 1200px) { 
    body    {
        font-size: 12px !important;
    }
        .section {
            font-size: 12px !important;
        }
    
}
*/
.table {
	margin: 0px;
	letter-spacing: 0px;
	word-spacing: 0px;
}

.btn {
	border-radius: 20px;
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
	border: 1px solid;
	background: linear-gradient(#1770c1, #073763 50%, #1770c1);
	color: #fff;
}

.btn:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.20), 0 6px 20px 0
		rgba(0, 0, 0, 0.0);
}
</style>
<style>
.radio {
	margin: 0px;
}
</style>
<style>
body {
	font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial,
		sans-serif;
}

.sidebar-menu .treeview-menu>li>a {
	font-size: 13px;
}
.col-center {
   float: none;
   margin-right: auto;
   margin-left: auto;
 }
        
        small{
            font-size:10px;
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
    <div class="row">
    <div class="col-md-12">
    <table>
    <tr>
    <b><span class="logo-lg pull-right "><img
			src="assets/SDSlogo.png"></span> <span class="logo-mini"></b></span>
      
        <small class="pull-left  " style="text-align:left;font-size:9px;color:#8e8e8e;width:500px;"><b>
        <s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address1" /></s:i18n>
        </b></small>

<small class="pull-center  " style="text-align:left;font-size:9px;color:#8e8e8e;width:500px;"><b>
<s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address2" /></s:i18n>
</b></small>


</tr>
</table></div>
</div>
    
        <div class="row">
            <div class=" col-xs-3 col-center" >
                
                <table style="width:100%"> 
                    <tr><th style="text-align:center"><h3><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.header1" /></s:i18n></h3></th></tr>
                    <tr><td style="text-align:center"><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.header2" /></s:i18n><b><span id="InvoiceNo"><s:property value="invDetail.arInvNum"/></span></b></td></tr>
                
                </table>
            </div>
        </div>
        <div class="row">
            <br>        
        </div>
        <div class="row invoice-info">
        
            <div class="col-xs-6">
            <table class="table table-bordered">
            <tr>
                <td><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.shipAddress"/></s:i18n><br>
                <b><span id="CustomerName"><s:property value="orders[0].customer.customerHeader.ctNm"/></span><br>
                <s:property value="shipping"/>
                </b></td>
                </tr></table>
            </div>
            <div class="col-xs-6 pull-right">
                <table class="table table-bordered">
                    <tbody>
                    <tr>
                        <td><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.salesperson"/></s:i18n></td>
                        <td><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.ordRef"/></s:i18n></td>
                        <td><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.InvDate"/></s:i18n></td>
                    </tr>
                    <tr>
                        <td><b><s:property value="SalesAgents[0]"/></b></td>
                        <td><b><span id="OrderID"><s:property value="invDetail.orderNum"/></span></b></td>
                        <td><b><span id="invDate"><s:property value="invDetail.arInvDate"/></span></b></td>
                    </tr>
                        <tr>
                        <td><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.custCode"/></s:i18n></td>
                        <td><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.LPOnum"/></s:i18n></td>
                        <td><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.terms"/></s:i18n></td>
                    </tr>
                    <tr>
                        <td><b><span id="customerId"><s:property value="orders[0].customer.customerHeader.customerHeaderPK.custId"/></span></b></td>
                        <td><b><span id="LPO"><s:property value="orders[0].ordTranSum.custLpoNum"/></span></b></td>
                        <td><b><span id="PayTerms"><s:property value="orders[0].customer.paymentTerms.payIn"/></span> Days</b></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
     
      <!-- info row -->
      
      <!-- /.row -->

<div class="row">
            <br>        
        </div>


      <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped table-bordered">
            <thead>
            <tr style="background-color:#ADC2EE;">
              <th><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.columnHeader1"/></s:i18n></th>
              <th><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.columnHeader2"/></s:i18n></th>
                <th><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.columnHeader3"/></s:i18n></th>
                <th class="fin-data"><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.columnHeader4"/></s:i18n></th>
              <th class="fin-data"><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.columnHeader5"/></s:i18n></th>
                <th class="fin-data"><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.columnHeader6"/></s:i18n></th>
            </tr>
            </thead>
        <tbody id="orderDetail">
             <s:iterator value="orders[0].ordTranLineItems"  status="itStatus">
            <tr>
            <td>
      <s:property value="%{#itStatus.index+1}"/>
      </td>
      <td>
       <s:property value="itemId"/>
      </td>
      <td>
       <s:property value="deItmShrtRcpt"/>
      </td>
            
            <td style="text-align:right;">
             <s:property value="itmPrnPrc"/>
      </td>
      
      <td style="text-align:right;">
             <s:property value="lineQnt"/>
      </td>
      <%--              <s:if test="%{#ordTranDscItms[0].dscPer>0}">
             <td><s:property value="ordTranDscItms[0].dscPer"/>  </td>
             </s:if>
             <s:else>
              <td><s:property value="%{0}"/> </td>
             </s:else> --%>
            
           <%--    <s if: test="<s:property value="ordTranDscItms[0].dscAmt"/>>0"
             <td><s:property value="ordTranDscItms[0].dscAmt"/> </td>
             </s if>
             <s else>
             <td><s:property value="%{0}"/></td>
             </s else> --%>
     
           <%--  <td><s:property value="vatLnItmRtn"/></td> --%>
            <%-- <td><s:property value="SalesAgents[#itStatus.index]"/></td> --%>
      
    <%--   <s:if test="invDetail.invStatus=='0'"><td><s:property value="lineQnt"/></td></s:if>
                   
       <s:else><td><s:property value="%{0}"/> </td></s:else> --%>
       
             <td style="text-align:right;"> <s:property value="extnDscLnItm"/></td>
    
      </tr>
      </s:iterator>
            </tbody>
          </table>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <div class="row">
        <!-- /.col -->
          <div class="col-xs-6">
              <small style="font-size:15px;"><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.commentsLabel"/></s:i18n></small>
          
          </div>
        <div class="col-lg-2 col-md-2 col-xs-2 pull-right">

          <div class="table-responsive">
            <table class="table table-totals table-bordered">
              <tbody>
              <tr>
                <td style="text-align:right;"><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.totalLabel"/></s:i18n><s:property value="getText('global.currency')"/>&nbsp;&nbsp; <b> <span id="TotalInvAmt">
                <s:property value="invDetail.invAmount"/></span></b></td>
              </tr>
            </tbody></table>
          </div>
        <div class="row">
           <div class="col-md-12">
            <small style="text-align:center;font-size:9px;color:#8e8e8e;"><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Note1"/></s:i18n></small></div>
           </div>
       
        </div>
        <!-- /.col -->
      </div>
        <div class="row">
            <br><br><br><br><br>
        
        </div>
        <div class="row">
            <div class="col-xs-4">
                <small><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Note2"/></s:i18n></small>
            </div>
            <div class="col-xs-4">
                <small ><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Note3"/></s:i18n></small>
            </div>
            <div class="col-xs-4">
                <table>
                    <tr><td>____________________________</td></tr>
                    <tr><td><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.issuedbyLabel"/></s:i18n></td></tr>
                </table>
            </div>
        </div>
        <div class="row">
            <br><br>
        </div>
        <div class="row">
            <div class="col-xs-4">
               <br>
            </div>
            <div class="col-xs-4">
                <table>
                    <tr><td>____________________________</td></tr>
                    <tr><td><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.custsigLabel"/></s:i18n></td></tr>
                </table>
            </div>
            <div class="col-xs-4">
                <table>
                    <tr><td>____________________________</td></tr>
                    <tr><td><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.delvbyLabel"/></s:i18n></td></tr>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6 col-center">
                <small><s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.billsinfoLabel"/></s:i18n></small>
            </div>
        </div>
      <!-- /.row -->

      <!-- this row will not appear when printing -->
   
    </section>
    </section>
    <!-- /.content -->
<%--     <div class="visible-print-inline">
    <header ><b>Terms and Conditons:</b></header><h6><s:property value="getText('CompanyTerms')" /></h6>
</div> --%>
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
						<h4 class="modal-title">"Amt Being Paid" Value should be greater than Zero</h4>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary center-block" data-dismiss="modal">OK&nbsp;</button>						
					</div>
				</div>

			</div>
		</div>

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
			$('#paymnt').prop('disabled', false);
			$('#amtBeingPaid').prop('disabled', false);
		}
	  </script>	
	  
	  <script>	 
	function  windowPrint(){
		window.print();
	}
	  </script>	

	<script type="text/javascript">
	window.print();
	history.go(-2);
    $('#invoice').addClass('active');
    $('#invoicesearch').addClass('active');
  </script>
</body>
</html>