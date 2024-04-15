<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <!-- jQuery 2.2.0 -->
<script src="Starter_files/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
  <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
 <link rel="stylesheet" href="assets/css/Customizations.css">
 
    <style>
        .box {
    position: relative;
    border-radius: 3px;
    background: #ffffff;
    border-top: 3px solid #d2d6de;
    margin-bottom: 0px;
    width: 100%;
    box-shadow: 0 1px 1px rgba(0,0,0,0.1);
}
        .form-group {
    margin-bottom:0px;
}
        .input-group-addon{
            padding:3px;
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
    -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
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
select:not(:-internal-list-box) {
    overflow: visible !important;
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
        .table-striped > tbody > tr:nth-of-type(odd) {
    background-color: #e4e4e4;
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
    font-family: 'Source Sans Pro','Helvetica Neue',Helvetica,Arial,sans-serif;
}
body {
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    font-family: 'Source Sans Pro','Helvetica Neue',Helvetica,Arial,sans-serif;
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

Pseudo ::after element
*:before, *:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
     body{
            font-family: 'Source Sans Pro','Helvetica Neue',Helvetica,Arial,sans-serif;
        }
        .btn {
        border-radius: 20px;
        box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.0);
        width:100px;margin-top:0px; background:    #3d85c6;
        background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
        background:    linear-gradient(#1770c1, #073763 50%, #1770c1);  
        color:         #fff;
        border: 1px solid;
    }
    .btn:hover{
        border-radius: 20px;
        box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.0);
        width:100px;margin-top:0px; background:    #3d85c6;
        background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
        background:    linear-gradient(#1770c1, #073763 50%, #1770c1);  
        color:         #fff;
        border: 1px solid;
    }
    </style>
<style>
    .table{
        margin:0px;
        letter-spacing:0px;
        word-spacing: 0px;
    }
.btn {
            border-radius: 20px;
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.0);
            border: 1px solid;
            background:    linear-gradient(#1770c1, #073763 50%, #1770c1);                                                   
            color:         #fff;
    }
    .btn:hover {
        box-shadow: 0 8px 16px 0 rgba(0,0,0,0.20), 0 6px 20px 0 rgba(0,0,0,0.0);
        
    }
    
</style>
<style>
    
     body{
            font-family: 'Source Sans Pro','Helvetica Neue',Helvetica,Arial,sans-serif;
    }
    .sidebar-menu .treeview-menu>li>a {
   
        font-size: 13px;
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
        <small class="pull-right" style="text-align:right;font-size:14px;color:#8e8e8e;width:500px;"><b><th><s:property value="getText('order.details.help_text')"/></b></small>
      <div class="row">
        <div class="col-xs-12">
          <h2 style="color: #226e71;">
             Order Details
              <div class="pull-right hidden-print"><h5>
                    <a href="#Mail" class="pull-right"><i class="fa fa-envelope"></i><s:property value="getText('mail.label')"/> &nbsp;</a> 
                    <a href="javascript:void(0);" onclick="windowPrint()" class="pull-right"><i class="fa fa-print"></i> <s:property value="getText('print.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a>
                  <a href="#"  class="pull-right"><i class="fa fa-save"></i> <s:property value="getText('save.label')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   </a></div>
                  
<!--            <small class="pull-right">Date: 2/10/2014</small>--></h5>
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
        <div class="col-sm-4 invoice-col">
        <table style="width:100%;" class="table">
            <tr><td style="text-align:right;width:50%;"><s:property value="getText('order.id')"/>:</b></td><td><b><s:property value="order.id.orderId"/></b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('customer.id')"/>:</b></td><td><b><s:property value="customer.id.custId"/></b></td></tr>
       		<tr><td style="text-align:right;"><s:property value="getText('LPO.label')"/>:</b></td><td><b><s:property value="ord_tran_sum.custLpoNum"/></b></td></tr>
           	<tr><td style="text-align:right;"><s:property value="getText('shipping.address')"/>:</b></td><td><address><b>
            <s:property value="ord_tran_header.ctDvrInf"/>
            </b>
          </address></td></tr>
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
        <table style="width:100%;" class="table">
        	<tr><td style="text-align:right;"><s:property value="getText('order.date')"/>:</b></td><td><b><s:property value="order.id.orderDate"/></b></td></tr>
        	<tr><td style="text-align:right;"><s:property value="getText('customer.name')"/>:</b></td><td><b><s:property value="customer.ctNm"/></b></td></tr>
 			<tr><td style="text-align:right;"><s:property value="getText('lpo.date')"/>:</b></td><td><b><s:property value="ord_tran_sum.custLpoDate"/></b></td></tr>
        	
        </table>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
          <table style="width:100%;" class="table">
           	<tr><td style="text-align:right;width:50%;"><s:property value="getText('status.label')"/>:</b></td><td><b><s:property value="order.status"/></b></td></tr>
           	<tr><td style="text-align:right;"> <s:property value="getText('sales.agent')"/>:</b></td><td><b><s:property value="order.salesAgent"/></b></td></tr>
         	<tr><td style="text-align:right;"><s:property value="getText('shipment.method')"/>:</b></td>
         	<td><b>
					<s:iterator value="order_items">
							<s:if test="itmTy==2">
            						<s:property value="deItmShrtRcpt" />
            				</s:if>
            				<s:else>
            					<s:property value="getText('unknown_inv')"/>
            				</s:else>
            	</s:iterator>
				</b></td></tr>
            <tr><td style="text-align:right;"><s:property value="getText('delivery.comments')"/>:</b></td><td><b><s:property value="ord_tran_header.ctDvrInfoIns"/></b></td></tr>
        </table>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped">
            <thead>
            <tr style="background-color:#ADC2EE;">
              <th><s:property value="getText('table.head.SNo')"/></th>
              <th><s:property value="getText('table.head.item')"/></th>
              <th><s:property value="getText('order.qty')"/></th>
              <th><s:property value="getText('table.head.unitprice')"/></th>
                <th><s:property value="getText('order.disc.amt')"/></th>
                <th><s:property value="getText('table.head.tax')"/></th>
                <th><s:property value="getText('table.head.total')"/> (<s:property value="getText('global.currency')"/>)</th>
            </tr>
            </thead>
            <tbody>
            
            <s:iterator  value="order_items" status="itStatus">  
            <tr>
            	<td><s:property value="#itStatus.count" />.</td>
            	<td><s:property value="deItmShrtRcpt" /></td>
            	<td><s:property value="lineQnt" /></td>
            	
            	<s:set var="itemPrc" value=""/>
            	<s:if test="ovrdPrc!=null">
            	<s:set var="itemPrc" value="ovrdPrc"/>
            	</s:if>
            	<s:else>
            	<s:set var="itemPrc" value="itmPrnPrc"/>
            	</s:else>
            	<td><s:property value="itemPrc" /></td>

            	
            	<s:if test="flItmDsc==0">
            	<td><s:property value="%{extnLnItmRtn-extnDscLnItm}" /></td>
            	</s:if>
            	<s:else>
            	<td><s:property value="%{0}"/></td>
            	</s:else>
            	
            	<td><s:property value="vatLnItmRtn" /></td>
            	<td><s:property value="extnDscLnItm" /></td>
            	
            	            	
            	
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
        <div class="col-lg-4 col-md-6 col-xs-12 pull-right">

          <div class="table-responsive">
            <table class="table">
              <tbody><tr>
                <td style="text-align:right;" style="width:50%"><s:property value="getText('subtotal.label')"/>:</td>
                <td style="text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<s:property value="ord_tran_sum.dkartSlsTot" /></td>
              </tr>
              <tr>
                <td  style="text-align:right;"><s:property value="getText('discount.label')"/>:</td>
                <th style="text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<s:property value="ord_tran_sum.dkartDscTot" /></th>
              </tr>
              <tr>
                <td  style="text-align:right;" ><s:property value="getText('total.tax')"/>:</td>
                <th style="text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<s:property value="ord_tran_sum.dkartTaxTot" /></th>
              </tr>
              <tr style="font-size:18px;">
                <td style="text-align:right;"><s:property value="getText('net.total')"/>:</td>
                <td style="text-align:right;"><s:property value="getText('global.currency')"/>&nbsp;&nbsp;<s:property value="ord_tran_sum.dkartNetTot" /></b></td>
              </tr>
            </tbody></table>
          </div>
          
       
          
          
        
        <a href="customerSearch"><button  type="button" class="btn  pull-right hidden-print" style="color:#ffffff; width:100px;margin-top:5px; background:    #3d85c6;
			background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
			background:    linear-gradient(#1770c1, #073763 50%, #1770c1); 
			color:         #fff;
			display:       inline-block;">
           <s:property value="getText('done.button')"/>
          </button>
         </a>
      
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

  <!-- Main Footer -->
  

  
  <!-- /.control-sidebar -->
  
</div>
<!-- ./wrapper -->

<!-- ChartJS 1.0.1 -->
 <script>	 
	function  windowPrint(){
		window.print();
	}
	  </script>	

</body></html>