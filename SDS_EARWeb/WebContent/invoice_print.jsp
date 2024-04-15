<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Arrays" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang3.time.DateUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>

<html>
<head>

<style type="text/css">
 @media print { 
@page { margin: 0; }
#Header, #Footer { display: none !important; }
}
</style>
<style>

@page {
    @bottom {
	content: "Page " counter(page) " of " counter(pages)
    }
}
</style>
  <%-- <style media="print">
 @page {
  size: auto;
  margin: 0;
  
} 
</style> --%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<!-- <meta charset="utf-8"> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | <s:property value="getText('sales.invoice')"/></title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="assets/css/print.css" media="print" 
    type="text/css" /> 
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css"
	type="text/css" />
<!-- <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css"
	type="text/css" /> -->
<link rel="stylesheet" href="assets/css/ionicons.min.css"
	type="text/css" />
	<link rel="stylesheet" href="assets/css/jquery-ui.css">

<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css"
	type="text/css" />
<!-- <link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="assets/plugins/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="assets/css/popup.css"> -->
<link rel="stylesheet" href="assets/css/Customizations.css">

<script src="Starter_files/jQuery-2.js"></script>

<script src="Starter_files/bootstrap.js"></script>

<script src="Starter_files/app.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
<%-- <style>
 @media print { 
     .table th { 
        background-color: #ddd !important; 
    } 
    
      h5 {page-break-before: always;}
}

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
	font-size: 13px;
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
</style> --%>
<%-- <style>
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
</style> --%>

</head>

<body class="skin-blue sidebar-collapse">


   <div class="wrapper">
    

		<%-- <s:include value="topbar.jsp" />
		<s:include value="menubar.jsp" /> --%>
		
	<div style="min-height: 595px;" class="content-wrapper" >
   
    
    <div class="content" >

    <div class="invoice" id="wrapper">
    
    
    
    
<%-- <s:set var = "breakLoop" value = "%{false}" />
				 	<s:iterator  var='taxItem1' value='getExciseTaxItem'>
				 	<s:if test = "!#breakLoop">
				 	<s:property value='taxItem1'/> 
				 	<s:set var = "breakLoop" value = "%{true}"/>
                    </s:if>
					 </s:iterator>  --%>

    
    
    <%
    
    /* Map<String, String> exciseItm = new HashMap<String, String>();
    exciseItm.put("102127619", "4.75");
    exciseItm.put("128049090", "19");
    exciseItm.put("102122640", "7");
    exciseItm.put("102127651", "4.75");
    exciseItm.put("109208201", "19");

    request.setAttribute("exciseItm", exciseItm); */
    //List<String> taxItem = new ArrayList<String>(exciseItm.keySet());
     
    //request.setAttribute("alist", taxItem);
    
   /* for(int i = 0; i < header.size(); i++) {
           String option = (String)header.get(i);  */
           
   %> 
  
  
  
  
   <%--  <c:set var="taxItem" value="<%=taxItem%>" />  --%>
<%-- <c:forEach items="${alist}" var="listItem">
       ${listItem} <br /> --%>
	    <div class="row">
		    <%-- <div class="col-xs-3">
		    	<img alt="logo" src="assets/SDSlogo.png">
		    </div>
		    <div class="col-xs-3">
		    	<img alt="SDSAddress" src="assets/SDSA.png">
		    </div>
		    <div class="col-xs-3 text-right">
		    	<small style="font-size:9px;color:#8e8e8e;"><b>
		        <s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address3" /></s:i18n>
		        </b></small>
		    </div>
		    <div class="col-xs-3 text-right">
		    	<small style="font-size:9px;color:#8e8e8e;"><b>
				<s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address2" /></s:i18n>
				</b></small>
		    </div>
		    <div class="col-xs-3 text-right">
		    	<small style="text-align:right;font-size:9px;color:#8e8e8e;"><b>
		        <s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address1" /></s:i18n>
		        </b></small>
		    </div> --%>
		</div>
		<div class="row">
		
		<!--  kunal-->
			<%--<s:if test="%{download == true}"> --%>
		    <div class="col-xs-3">
		    	<img alt="logo" src="assets/SDSlogo.png">
		    </div>
		    <div class="col-xs-3 text-right">
		    	<small style="font-size:12px;color:#000000;"><b>
		        <s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address3" /></s:i18n>
		        </b></small>
		    </div>
		    <div class="col-xs-3 text-right">
		    	<small style="font-size:12px;color:#000000;"><b>
				<s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address2" /></s:i18n>
				</b></small>
		    </div>
		    <div class="col-xs-3 text-right">
		    	<small style="text-align:right;font-size:12px;color:#000000;"><b>
		        <s:i18n name="rispl.print.printText" ><s:text name="salesInvoice.Address1" /></s:i18n>
		        </b></small>
		    </div>
			<%--</s:if> --%>
		</div>
		
		<div class="row">
		  <div class="col-xs-12 text-center">
		    <h2 style="color: #000000;" >
				<u><b><s:property value="getText('sales.invoice')"/></b></u>
		    </h2>
		  </div>
		</div>

		<div class="row invoice-info">
		
			<div class="col-sm-4 invoice-col">
				<table class="table table-condensed table-bordered" >
					<tr>
						<td class="text-right" style="width:40%;"><b><s:property value="getText('invoice.no')"/>:</b></td>
						<td><b><span id="InvoiceNo"><%-- <s:property value="invDetail.arInvNum"/> --%>
				 	<s:set var = "breakLoop" value = "%{false}" />
				 	<s:iterator  var='item' value='invDetail.arInvNum.split("-")'>
				 	<s:if test = "!#breakLoop">
				 	<s:property value='item'/> *
				 	<s:set var = "breakLoop" value = "%{true}"/>
                    </s:if>
					 </s:iterator>
				
						</span></b></td>
  
					</tr>
					<tr>
						<td class="text-right"><b><s:property value="getText('invoice.date')"/>:</b></td>
						<td><b><span id="invDate"><s:date name="invDetail.arInvDate" format="%{getText('format.date')}" /></span></b></td>
					</tr>
					<tr>
						<td class="text-right"><b><s:property value="getText('status.label')"/>:</b></td>
						<td><b><span id="InvStatus">
							<s:if test="invDetail.invStatus=='0'"><s:property value="getText('closed_inv')"/></s:if>
							<s:elseif test="invDetail.invStatus=='1'"><s:property value="getText('open_inv')"/></s:elseif>
							<s:else><s:property value="getText('unknown_inv')"/></s:else>
							</span></b>
						</td>
					</tr>
					<tr>
						<td class="text-right"><b><s:property value="getText('payment_terms.label')"/>:</b></td>
						<td><b><s:property value="orders[0].customer.paymentTerms.payIn"/>&nbsp Days</b></td>
						

					</tr> 
				
					<tr>
					
						<td class="text-right"><b><s:property value="getText('due_date.label')"/>:</b></td>
						<td><b><s:property value="invDueDate"/></b></td>
					</tr> 
					
				</table>
			</div>

			<div class="col-sm-4  invoice-col">
				<table class="table table-condensed table-bordered">
					<tr>
						<td class="text-right" style="width:40%;"><b><s:property value="getText('customer.id')"/>:</b></td>
						<td><b><span id="customerId"><s:property value="orders[0].customer.customerHeader.customerHeaderPK.custId"/></span></b></td>
					</tr>
					<tr>
						<td class="text-right"><b><s:property value="getText('customer.name')"/>:</b></td>
						<td><b><span id="CustomerName"><s:property value="orders[0].customer.customerHeader.ctNm"/></span></b></td>
					</tr>        
					<%--<tr>
						 <td class="text-right"><b><s:property value="getText('customer.segment')"/>:</b></td>
						<td><b><span id="CustomerSegment"><s:property value="orders[0].customer.customerSegmentID"/></span></b></td> 
					</tr>--%>
					<tr>
						<td class="text-right"><b><s:property value="getText('LPO.label')"/>:<b></td>
						<td><b><span id="LPO"><s:property value="orders[0].ordTranSum.custLpoNum"/></span></b></td>
					</tr>
					<tr>
						<td class="text-right"><b><s:property value="getText('lpo.date')"/>:<b></td>
						<td><b><span id="LPODate"><s:date name="orders[0].ordTranSum.custLpoDate" format="%{getText('format.date')}" /></span></b></td>
					</tr>
				</table>
			</div>

			<div class="col-sm-4 invoice-col">
				<table class="table table-condensed table-bordered">
					<tr>
						<td class="text-right" style="width:40%;"><b><s:property value="getText('order.id')"/>:</b></td>
						<td><b><span id="OrderID"><s:property value="invDetail.orderNum"/></span></b></td>
					</tr>
					<tr>
						<td style="text-align:right;"><b><s:property value="getText('order.date')"/>:</b></td>
						<td><b><span id="OrderDate"><s:date name="invDetail.orderDate" format="%{getText('format.date')}" /></span></b></td>
					</tr>
					<tr>
						<td style="text-align:right;"><b><s:property value="getText('shipping.address')"/>:</b></td>
						<td><address><b><span id="shpngadrs"><s:property value="shipping"/></span></b></address></td>
					</tr> 
					
					<tr>
						<td style="text-align:right;"><b><s:property value="getText('remarks.label')"/>:</b></td>
						<td><b><span id="remarks"><s:property value="orders[0].ctDvrInfoIns"/></span></b></td>
					</tr>
					<tr>
						<td style="text-align:right;"><b><s:property value="getText('salesman.code')"/>:</b></td>
						<td><address><b><span id="salesagnt">
 
					<s:set var = "breakLoop" value = "%{false}" />
				 	<s:iterator  var='saleAgent' value='SalesAgents'>
				 	<s:if test = "!#breakLoop">
				 	<s:property value='saleAgent'/> 
				 	<s:set var = "breakLoop" value = "%{true}"/>
                    </s:if>
					 </s:iterator> 
					 
					
					</span></b></address></td>
					
					</tr>  
				</table>
			</div>
		
		</div>

      <div class="row">
        <div class="col-xs-12" >
        	<table class="table table-condensed table-bordered">
        	
	          	<thead>
	          				
		            <tr style="background-color:#ADC2EE;">
		            
		              <th class="text-center"><s:property value="getText('table.head.SNo')"/></th>
		              <th class="text-center"><s:property value="getText('table.head.item')"/></th>
		              <!-- <th class="text-center">Description</th> -->
		              <th class="text-center"><s:property value="getText('table.head.description')"/></th>
		              <th class="text-center"><s:property value="getText('table.head.qty')"/></th>
		              <th class="text-center"><s:property value="getText('table.head.vpn')"/></th>
		              <th class="text-center"><s:property value="getText('table.head.unitprice')"/></th>
		              <th class="text-center"><s:property value="getText('subtotal.label')"/></th>
		              <th class="text-center"><s:property value="getText('discount.label')"/></th>
		              <th class="text-center"><s:property value="getText('table.head.ExciseTax')"/></th>
		              <th class="text-center"><s:property value="getText('table.head.tax')"/></th>
		              <%-- <th class="text-center"><s:property value="getText('sales.agent')"/></th> --%>
		              <th class="text-center"><s:property value="getText('total.label')"/></th>
		            </tr>
	            </thead>
		      	<tbody id="orderDetail">
		             <s:set var="qtyTot" value="%{0}"></s:set>
		              <c:set var="totalb" value='0.00'/>
		             <s:iterator value="orders[0].ordTranLineItems" status="itStatus">
		            	<tr>
		            		<s:set var="count" value="%{#itStatus.index+1}"></s:set>
		            		<td class="text-right"><b><s:property value="%{#itStatus.index+1}"/></b></td>
		            		
		            		
		            		<%-- <c:set var="taxItem" value="<%=taxItem%>" />
		            		<c:set var="itemId1" value="${itemId}" />
                           
                           
          <c:choose>
         <c:when test="${fn:contains(taxItem,itemId1)}"> --%>
         
       <%--  <c:set var="getExciseTaxItem">value="${getExciseTaxItem}"</c:set>--%>

<%--  <c:forEach var="entry" items="${getExciseTaxItem}"> 
<c:set var="taxItem1" value="${entry.key}"/>
</c:forEach> --%>




        
          <c:set var="itemId1" value="${itemId}" />
          <c:choose>
           <c:when test="${fn:contains(ExcisTaxItemList,itemId1)}">
                                   
                                  <c:set var="a">${getExciseTaxItem[itemId1]}</c:set>
								   <c:set var="unitPricewithoutE">${itemPrice-a}</c:set>
								   <c:set var="unitPricewithoutsub">${lineQnt*unitPricewithoutE}</c:set>
                                  <c:set var="itemT">${lineQnt*a}</c:set>
                                  <c:set var="totalb">${totalb+itemT}</c:set>
                                                          
         </c:when>
         <c:otherwise>
            <c:set var="itemT" value='0.00'/>
            <c:set var="totalb">${totalb+itemT}</c:set>
            <c:set var="a" value='0.00'/>
            <c:set var="unitPricewithoutE">${itemPrice}</c:set>
         <%--   withoutTax:: ${itemPrice} --%>
			<c:set var="unitPricewithoutsub">${itemPrice*lineQnt}</c:set>
         
         
         </c:otherwise>
         </c:choose>
          
          
                           
        <%--  <c:choose>
         
         
         
         <c:when test = "${itemId == '102122640'}">
								  <c:set var="a" value='7.00'/>
								   <c:set var="unitPricewithoutE">${itemPrice-a}</c:set>
								   <c:set var="unitPricewithoutsub">${lineQnt*unitPricewithoutE}</c:set>
                                  <c:set var="itemT">${lineQnt*a}</c:set>
                                  <c:set var="totalb">${totalb+itemT}</c:set>
                                                          
         </c:when>
         
         <c:when test ="${itemId == '102127619' || itemId == '102127651'}">
								  <c:set var="a" value='4.75'/>
								   
								  <c:set var="unitPricewithoutE">${itemPrice-a}</c:set>
								  <c:set var="unitPricewithoutsub">${lineQnt*unitPricewithoutE}</c:set>
                                  <c:set var="itemT">${lineQnt*a}</c:set>
                                  <c:set var="totalb">${totalb+itemT}</c:set>
                                   
         </c:when>
         <c:when test ="${itemId == '109208201' || itemId == '128049090'}">
         
								  <c:set var="a" value='19.00'/>
								  <c:set var="unitPricewithoutE">${itemPrice-a}</c:set>
								  <c:set var="unitPricewithoutsub">${lineQnt*unitPricewithoutE}</c:set>
                                  <c:set var="itemT">${lineQnt*a}</c:set>
                                  <c:set var="totalb">${totalb+itemT}</c:set>
                                  
         </c:when>
         
         <c:otherwise>
            <c:set var="itemT" value='0.00'/>
            <c:set var="totalb">${totalb+itemT}</c:set>
            <c:set var="a" value='0.00'/>
            <c:set var="unitPricewithoutE">${itemPrice}</c:set>
           withoutTax:: ${itemPrice}
			<c:set var="unitPricewithoutsub">${itemPrice*lineQnt}</c:set>
         </c:otherwise>
         </c:choose> --%>
      
                           
                           
                            <%-- 
                                  <c:set var="a" value="*" />
                                  <c:set var="itemT">${itemId1}${a}</c:set>
                            
                            
                            
                                  <td>${itemT} - <s:property value="deItmShrtRcpt"/>
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
													
								</s:iterator>
		      				</td>
                                  
                                  
                                  </c:if>
                           
  </c:when>
  <c:otherwise> --%>
   
  
                       
		      				<td><b><s:property value="itemId"/> <%-- - <s:property value="deItmShrtRcpt"/> --%>
		      					<s:iterator value="ordTranDscItms" status="lineId">
									<s:set var="resncds" value="orderTran.getReasonCodes()['Discount']"></b></s:set>
									
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
													
								</s:iterator>
		      				</td>
		      				
		      				<%-- </c:otherwise>
</c:choose> --%>
		      				
		                    <td class="text-left"><b><s:property value="deItmShrtRcpt"/></b></td>
		      				<s:set var="qtyTot" value="%{lineQnt+#qtyTot}"></s:set>
		            		<td class="text-right"><b><s:property value="lineQnt"/></b></td>
		            		
		             		<td><b><s:property value="registryId"/></b></td>
		             		
		             		<s:set var="itemPrice" value="0"/>
		             		<s:if test="overRidePrice != null"><s:set var="itemPrice" value="overRidePrice" />		             		
		             		</s:if>
						    <s:else><s:set var="itemPrice" value="itmPrnPrc" /></s:else>
							<%-- <td class="text-right"><s:property value="%{getText('format.currency.args',{#itemPrice})}" /></td> --%>
							 <c:set var="untitpricewithputEx">${itemPrice-a}</c:set>
						  
							<td class="text-right"><b>${itemPrice-a}</b></td>
							<%-- <td class="text-right"><s:property value="%{getText('format.currency.args',{lineQnt * #itemPrice})}"/></td>
							 --%>
						
							 <td class="text-right"><b>${untitpricewithputEx * lineQnt}</b></td>
							 <c:set var="subtotalwithoutexcess">${untitpricewithputEx * lineQnt}</c:set>
							 <c:set var="subtotalwithoutexcesst">${subtotalwithoutexcess + subtotalwithoutexcesst}</c:set>
		     				<s:set var="dscAmts" value="0"/>
							<s:if test="%{ordTranDscItms != null}">
								<s:iterator value="ordTranDscItms" status="dscList">
									<s:set var="dscAmts" value="%{#dscAmts+dscAmt}"/>
								</s:iterator>
							</s:if>
							<td class="text-right"><b><s:property value="%{getText('format.currency.args',{#dscAmts})}"/></b></td>
														
							
							<td class="text-right"><b>${itemT}</b></td>
							
		            		<td class="text-right"><b><s:property value="%{getText('format.currency.args',{vatLnItmRtn})}"/></b></td>
		            		
		           			<%-- <td><s:property value="SalesAgents[#itStatus.index]"/></td> --%>
		      <s:set var="t1" value="extnDscLnItm"/>
		     
		      <c:set var="t3">${itemT+extnDscLnItm}</c:set>
		     <%--  <td class="text-right">${t3}</td> --%>
		             	<td class="text-right"><b> <s:property value="%{getText('format.currency.args',{extnDscLnItm})}"/></b></td> 
		      			</tr>
		      			
		      		</s:iterator>
		      	</tbody>
        	</table>
        </div>
      </div>
		
		<div class="row" style="page-break-inside:avoid;">
		
			<div class="col-xs-4 pull-right">
	        	<div class="table-responsive">
		        	<table class="table table-condensed  table-bordered">
			        	<tbody>
							<tr>
			                	<td class="text-right" style="width:40%;"><b><s:property value="getText('subtotal.label')"/>:</b></td>
			                	<td class="text-right">
			                		<b><s:property value="getText('global.currency')"/>&nbsp;
			                		<span id="Subtotal"><b>
			                		<%-- ${subtotalwithoutexcesst} --%>
			                		
			                		
			                		<fmt:formatNumber type = "number" pattern = "#,##0.00" value = "${subtotalwithoutexcesst}" />
			                		<%-- <s:property value="%{getText('format.currency.args',{subtotalwithoutexcesst}})}"/> --%>
			                			<%-- <s:property value="%{getText('format.currency.args',{orders[0].ordTranSum.dkartSlsTot})}"/> --%>
			                		</b></span></b>
			                	</td>
			                </tr>
							<tr>
								<td class="text-right"><b><s:property value="getText('discount.label')"/>:</b></td>
								<td class="text-right">
									<b><s:property value="getText('global.currency')"/>&nbsp;
									<span id="Discount"><b>
										<s:property value="%{getText('format.currency.args',{orders[0].ordTranSum.dkartDscTot})}"/>
									</b></span></b>
								</td>
							</tr>
							<tr>
								<td class="text-right"><b><s:property value="getText('total.tax')"/>:</b></td>
								<td class="text-right">
									<b><s:property value="getText('global.currency')"/>&nbsp;
									<span id="TotalTax"><b>
									${totalb}
										<%-- <s:property value="%{getText('format.currency.args',{orders[0].ordTranSum.dkartTaxTot})}"/> --%>
									</b></span></b>
								</td>
							</tr>
							<tr>
								<td class="text-right"><b><s:property value="getText('table.head.total')"/>:</b></td>
								<td class="text-right">
									<b><s:property value="getText('global.currency')"/>&nbsp;
									<b><span id="TotalInvAmt">
									<s:set var="totalInvamt" value="invDetail.invAmount"/>
							<c:set var="totalets">${totalInvamt+totalb}</c:set>
									<%-- ${totalets} --%>
										 <b><s:property value="%{getText('format.currency.args',{invDetail.invAmount})}"/> 
									</span></b><b>
								</td>
							</tr>
							<tr>
								<s:set var="totalInvamt" value="invDetail.invAmount"/>
								<s:set var="totalPenamt" value="invDetail.invPendAmount"/>
								<c:set var="totalp">${totalPenamt+totalb}</c:set>
								<td class="text-right"><b><s:property value="getText('received.amt')"/>:<b></td>
								<td class="text-right">
									<b><s:property value="getText('global.currency')"/>&nbsp;
									<span id="ReceivedAmt"><b>
									<c:set var="totaletp">${totalets-totalp}</c:set>
									<%-- ${totaletp} --%>
										 <b><s:property value="%{getText('format.currency.args',{#totalInvamt-#totalPenamt})}"/> 
									</b></span></b></b>
								</td>
							</tr>
							<tr>
			                	<td class="text-right"><b><s:property value="getText('balance.due')"/>:</b></td>
			                	<td class="text-right">
			                		<b><s:property value="getText('global.currency')"/>&nbsp;
			                		<span id="BalanceDue">
			                		<%-- ${totalp} --%>
			                		
			                			 <b><s:property value="%{getText('format.currency.args',{invDetail.invPendAmount})}"/> 
			                		</b></span></b>
			                	</td>
							</tr>
			            </tbody>
		            </table>
				</div>
			</div>
			<br><br><br>
			
			<%-- <c:if test="${listItem eq 'Customer copy'}"> --%>
		<div class="row">
		<div style="position:absolute;text-align:center; float:left; width:150px; height:75px;left:5px ">
  <div style="position: absolute; bottom: 2; right:0; left:0;margin: auto; margin-top: 2em; margin-bottom: 4em;"><hr style="border-color: #8e8e8e;">	</div>
  <div style="position: absolute; bottom: 0;right:0; left:0;"><i><b>Issued By</b></i>
  </div> 
</div>

<div style="position:absolute;text-align:center; float:left; width:150px; height:75px; left:175px">
  <div style="position: absolute; bottom: 2; right:0; left:0;margin: auto; margin-top: 2em; margin-bottom: 4em;"><hr style="border-color: #8e8e8e;">	</div>
  <div style="position: absolute; bottom: 0;right:0; left:0;"><i><b>Delivered By</b></i>
  </div> 
</div>
<div style="position:absolute;text-align:center; float:left; width:150px; height:75px; left:350px ">
  <div style="position: absolute; bottom: 2; right:0; left:0;margin: auto; margin-top: 2em; margin-bottom: 4em;"><hr style="border-color: #8e8e8e;"></div>
  <div style="position: absolute; bottom: 0;right:0; left:0;"><i><b>Customer signature</b></i>
  </div>
		
		</div>
		</div>
		<br>
		<!-- <div class="col-xs-12">
				
				<h6>NOTE</h6>
				<h6>* Items inclusive of Excise Tax</h6>
			</div> -->
        <%--  </c:if>  --%>
			<br>
			<div class="col-xs-12">
				<header><b><s:property value="getText('print.terms.conditions')"/>:</b></header>
				<h6><b><s:text name="print.terms.companyTerms" /></b></h6>
			</div>     
            
		</div>
			
			
		</div>
  		<!-- <br>
  		<hr style="border-color: #8e8e8e;">		
		<br/> -->
		<br/>
		
		
		<%-- <div class="row" style="page-break-before:always">
		<br>

		<div class="col-xs-12 text-center"><br><br><h3><s:property value="getText('print.mini.statement')"/></h3></div>
		</div>
		<div class="row">
			<div class="col-xs-6 le-print-inline">
				<h4 class="text-center"><s:property value="getText('invoices.label')"/></h4>
				<table class="table table-condensed  table-bordered">
					<thead>
						<tr style="background-color:#ADC2EE;">
							<th class="text-center"><s:property value="getText('table.head.SNo')"/></th>
							<th class="text-center"><s:property value="getText('invoice.no')"/></th>
							<th class="text-center"><s:property value="getText('invoice.date')"/></th>
							<th class="text-center"><s:property value="getText('invoice.total')"/></th>
							<th class="text-center"><s:property value="getText('received.amt')"/></th>
							<th class="text-center"><s:property value="getText('balance.due')"/></th>
			            </tr>
					</thead>
		            <tbody>
		            	
		            	<s:if test="%{invoices != null && invoices.size() > 0}">
		            		<s:set var="Index" value="%{0}"></s:set>
							<s:iterator value="invoices" status="itStatus">
								<s:if test="%{invDetail.arInvNum!=arInvNum && #Index<3}">
						            <tr>
						            	<td class="text-right"><s:property value="%{#Index+1}"/></td>
						      			<td><s:property value="arInvNum"/></td>
						      			<td>
						      			<s:set var = "breakLoop" value = "%{false}" />
				 	<s:iterator  var='item' value='arInvNum.split("-")'>
				 	<s:if test = "!#breakLoop">
				 	<s:property value='item'/>*
				 	<s:set var = "breakLoop" value = "%{true}"/>
                    </s:if>
					 </s:iterator></td>
						      			<td class="text-center"><s:date name="arInvDate" format="%{getText('format.date')}" /></td>
						       			<td class="text-right"><s:property value="%{getText('format.currency.args',{invAmount})}"/></td>
										<td class="text-right"><s:property value="%{getText('format.currency.args',{invAmount-invPendAmount})}"/></td>
										<td class="text-right"><s:property value="%{getText('format.currency.args',{invPendAmount})}"/></td>
						            	<s:set var="Index" value="%{#Index+1}"></s:set>
									</tr>
								</s:if>	
							</s:iterator>
						 </s:if>
						<s:else>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</s:else>
					</tbody>
				</table>
			</div>
           
			<div class="col-xs-6 pull-right  le-print-inline">
				<h4 class="text-center"><s:property value="getText('orders.label')"/></h4>
				<table class="table table-condensed  table-bordered">
		            <thead>
			            <tr style="background-color:#ADC2EE;">
							<th class="text-center">#</th>
							<th class="text-center"><s:property value="getText('order.id')"/></th>
							<th class="text-center"><s:property value="getText('order.date')"/></th>
							<th class="text-center"><s:property value="getText('order.totals')"/></th>
			            </tr>
		            </thead>           
					<tbody>
						<s:if test="%{minStorders != null && minStorders.size() > 0}">
							<s:set var="Index" value="%{0}"></s:set>
							<s:iterator value="minStorders"  status="itStatus">
								<s:if test="%{invDetail.orderNum!=orderId && #Index < 3}">
									<tr>
										<td><s:property value="%{#Index+1}"/></td>
										<td class="text-right"><s:property value="orderId"/></td>
										<td class="text-center"><s:date name="orderDate" format="%{getText('format.date')}" /></td>
										<td class="text-right"><s:property value="orderTotal"/></td>
										<s:set var="Index" value="%{#Index+1}"></s:set>
									</tr>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</s:else>
					</tbody>
				</table>
			</div>
        </div>  
		<br>
		
		<!-- <div class="row" style="page-break-after:always"> -->
			<div class="col-xs-12">
				<header><b><s:property value="getText('print.terms.conditions')"/>:</b></header>
				<h6><s:text name="print.terms.companyTerms" /></h6>
			</div>   --%>  
            
		</div>
		<%-- <s:form name="printDeliveryNote" action="PrintDelivereyNote" theme="simple">
			<s:param name="invoiceID" value="invDetail.arInvNum"></s:param>
		</s:form> --%>
		
   <%--  </c:forEach>  --%>
 
    </div>
    </div>    
<s:if test="%{download == true}">
	<s:form name="downloadDeliveryNote" action="downloadDeliveryNote" theme="simple">
		<s:param name="invoiceID" value="invDetail.arInvNum"></s:param>
	</s:form>
	
	<script src="custom/pdf/jspdf.debug.js"></script>
	<script src="custom/pdf/html2canvas.js"></script>
	<script src="custom/pdf/html2pdf.js"></script>
	<script type="text/javascript">
	
	
	
		
	<%-- var downloadDeliveryNote = function(pdf){
	setTimeout(function() {
    document.forms["downloadDeliveryNote"].submit();}, 500);
	
	
		var element = $('#wrapper').html();
		console.log(element);
		
		var pdf = html2pdf(element, {
			margin:       0.2,
			filename:     'Invoice_${invDetail.arInvNum}.pdf',
			image:        { type: 'jpeg', quality: 0.99 },
			html2canvas:  { dpi: 300, letterRendering: true,allowTaint:true },
			jsPDF:        { unit: 'in', format: 'a4', orientation:'landscape'},
			
			callback: downloadDeliveryNote
		});  --%> 
		
		var downloadDeliveryNote = function () {
        document.forms["downloadDeliveryNote"].submit();
    }

    var element = document.getElementById('wrapper');
    html2pdf().from(element)
        .set({
            margin: 0.2,
            filename: 'Invoice_${invDetail.arInvNum}.pdf',
            image: { type: 'png', quality: 0.99 },
            html2canvas: { dpi: 300, letterRendering: true, allowTaint: true },
            jsPDF: { unit: 'in', format: 'a4', orientation: 'landscape' }
        })
        .save()
        .then(downloadDeliveryNote);
	
	</script>
 </s:if>
 <s:else>
 	<s:form name="printDeliveryNote" action="PrintDelivereyNote" theme="simple">
		<s:param name="invoiceID" value="invDetail.arInvNum"></s:param>
	</s:form>
 
	<script type="text/javascript"> 
		window.print();
		window.setTimeout(myFunction, 3000);
		
		//document.printDeliveryNote.submit(); 
	
		
		
		
		
		
	function myFunction() {
		document.printDeliveryNote.submit(); 
	}	
	</script>
 </s:else>
</body>
</html>