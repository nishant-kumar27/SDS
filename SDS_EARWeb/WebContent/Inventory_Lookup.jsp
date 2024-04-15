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
<title>SDS | Inventory Lookup</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<!-- <link rel="stylesheet" href="assets/ionicons.css"> -->
<!-- Theme style -->
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="assets/css/jquery-ui.css">


<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.0 -->

<!-- Bootstrap 3.3.6 -->
<script src="assets/plugins/jQuery/jQuery-2.2.0.min.js"></script>

<script src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="Starter_files/app.js"></script>
<script src="assets/plugins/jQueryUI/jquery-ui.js"></script>

<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<link rel="stylesheet" href="assets/css/Customizations.css">
<%-- <script type="text/javascript"src="assets/plugins/listgroup/listgroup.min.js"></script>
<!-- InputMask -->
<script src="assets/plugins/input-mask/jquery.inputmask.js"></script>
<script
	src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
 --%>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

  <![endif]-->
<style>
body {
	font-family: font-family : "Helvetica Neue" Helvetica, Arial, sans-serif;
	;	 
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
    

    .table{
        margin:0px;
        letter-spacing:0px;
        word-spacing: 0px;
        background: #FFFFFF; 
    }

.btn {
        border-radius: 20px;
        box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.0);
        width:100px;margin-top:0px; background:    #3d85c6;
        background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
        background:    linear-gradient(#1770c1, #073763 50%, #1770c1);  
        color:         #fff;
        border: 1px solid;
        width :100px;
    
}

.btn:hover{
        border-radius: 20px;
        box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.0);
        width:100px;margin-top:0px; background:    #3d85c6;
        background:    -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1);
        background:    linear-gradient(#1770c1, #073763 50%, #1770c1);  
        color:         #fff;
        border: 1px solid;
        width :100px;
}
    
</style>
<%--  <style type="text/css">
.errors {
	background-color:#FFCCCC;
	border:1px solid #CC0000;
	width:400px;
	margin-bottom:8px;
}
.errors li{
	list-style: none;
}
</style> --%>
<script>
	$("form[role='search']").hide();
    $(document).ready(function() {
    
 	$("input#InvItemId").autocomplete({
			source : function(request, response) {

			//$('.ui-autocomplete-loading').removeClass("ui-autocomplete-loading");

			if (request.term.length > 2) {
				/*  document.getElementById("loadText").style.display ="none"; */
				//document.getElementById("load").style.display = "block";  // show the loading image.
				//$('.ui-autocomplete-loading').addClass("ui-autocomplete-loading");
				$("#InvItemId").addClass("ui-autocomplete-input");

				$.ajax({
					url : "searchItemAction",
					type : "POST",
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(jsonResponse) {
						response(jsonResponse.list);
						/*   document.getElementById("load").style.display = "none"; */
						$("#InvItemId").removeClass("ui-autocomplete-input");
					}

				});
			} else {
				/* document.getElementById("load").style.display = "none"; */
				$("#InvItemId").removeClass("ui-autocomplete-input");
			}
		}
		})
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

		<s:include value="topbar.jsp"></s:include>
		<s:include value="menubar.jsp"></s:include>

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">


			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->
				<div class="row">
					<label style="font-size:25px;color: #226e71;">&nbsp;&nbsp;&nbsp;<s:property value="getText('inventory.lookup')"/> </label>
					<form action="inventorylookupitem" class="search-form" id="inventorylookup">
						<fieldset>
							<div class="col-md-10">
								<div class="input-group">
									<input type="text" placeholder="Search" class="form-control" name="itemID" id="InvItemId" style="width: 85%; min-height:30px;" required>
								
								
								    <div class="input-group-btn">
										<div class="col-md-2">
											<button  onclick="validateItemField()" class="btn pull-right" id="itemInfo"
												style="margin-left: 0px; color: #ffffff; width: 110px; background: #3d85c6; 
												background: -webkit-linear-gradient(#1770c1, #073763 50%, #1770c1); 
												background: linear-gradient(#1770c1, #073763 50%, #1770c1); color: #fff; 
												border: 1px solid; display: inline-block;"
												type="submit"><s:property value="getText('search.button')"/><i class="fa"></i>
											</button> 
										</div>
									</div>
								</div>
							</div>
						</fieldset>
					</form>
					
				</div>
				<div style="width:61%";>
    			<s:actionerror theme="bootstrap"></s:actionerror> </div>
    						 
    						   <!-- <div name="noitemError" id="noitemError" class="alert alert-danger hidden"
								role="alert" style="color: #fff;">No item found
								with this information!</div> -->
								
								
							 <%-- <s:if test="hasActionErrors()">
						     <div class="errors">
      						<s:actionerror/>
  							</div>
   							</s:if> --%> 
						
				
							
    
    
   
 	<div class="row inventory-info" ><br/>
       <div class="col-md-7 inv-col">
         <table class="table well" id="inventorytble" style="min-height:110px;">
            <tr><td style="text-align:left;"><s:property value="getText('item.id')"/>:</b></td>
           			 <td><b><span id="itemID"><s:property value="pl.item.Id.ItmId"/></span></b></td></tr>
            <tr><td style="text-align:left;"><s:property value="getText('desc.label')"/>:</b></td><td><b><span
					id="Desc"><s:property value="pl.item.ItmDesc"/></span></b></td></tr>
           <tr><td style="text-align:left;width:50%;"><s:property value="getText('available.quantity')"/>:</b></td><td><b><span
					id="AvailInv"><s:property value="pl.inventory"/></span></b></td></tr>
           <%-- <tr><td style="text-align:left;"><s:property value="getText('price.head')"/>:</b></td><td><b><span
					id="Price"><s:property value="pl.itemPrice.slUnAmt"/></span></b></td></tr>  --%>
					
        </table></div></div>
		</section></div>
		
		
		
		
		
		

		
    <script type="text/javascript">
       // Focus to inventory lookup once page loads
    $('#InvItemId').focus();
    </script>    
  
    	 
    
	<script>
	function validateItemField(){
	var itemInfoTxt=document.getElementById("InvItemId").value;
		if(itemInfoTxt.length<=0){
		  $('#InvItemId').addClass('has-error');
		  $('#InvItemId').parent().addClass('has-error');
		  $('#InvItemId').focus();
		}else{
		  document.forms["hidden_order_data"].itemID.value=itemInfoTxt;
		  document.forms["hidden_order_data"].submit();
		  }
		 
	}
	</script>	
	 
   <form action="inventorylookupitem" name="hidden_order_data" method="post">   
   	<input type="hidden" name="itemID"/>
	</form>
	 <script>
       $('#inventorylookupitem').addClass('active');  
     </script>
   </body>
   </html>
