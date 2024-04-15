<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	// Comment in order to enable cahcing on a praticular page
	// ----------------------------------------------------------
	//response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	//response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	//response.setDateHeader("Expires", 0); // Proxies.

	// Prevent "back" after logout
	//if (session.getAttribute("employee") == null)
	//	response.sendRedirect("logout");
%>
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
<title>SDS | Customer Search</title>
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
<link rel="stylesheet" href="assets/css/Customizations.css">


<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.0 -->
<%-- <script src="Starter_files/jQuery-2.js"></script> --%>
<script type="text/javascript" src="jquery/jquery-1.12.4.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script type="text/javascript" src="Starter_files/bootstrap.js"></script>
<!-- AdminLTE App -->
<script type="text/javascript" src="Starter_files/app.js"></script>
<script type="text/javascript" src="assets/plugins/jQueryUI/jquery-ui.js"></script>


<!--[if lt IE 9]>
    <script type="text/javascript" src="assets/ie8support/html5shiv.min.js"></script>
	<script type="text/javascript" src="assets/ie8support/html5shiv-printshiv.min.js"></script>
	<script type="text/javascript" src="assets/ie8support/respond.min.js"></script>
<![endif]-->

<%-- <script type="text/javascript" src="assets/plugins/listgroup/listgroup.min.js"></script>
 --%>
<style>

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

		<s:include value="topbar.jsp"></s:include>
		<s:include value="menubar.jsp"></s:include>

		<!-- Content Wrapper. Contains page content -->
		<div style="min-height: 595px;" class="content-wrapper">


			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->
				<div class="row">
					<div class="col-xs-12">
						<label style="font-size:25px;color: #226e71;"><s:property value="getText('menu.customersearch')"/></label>
						<form action="customerLookup" method="post" id="customerLookup" class="search-form">
							<div class="row">
								<div class="col-xs-7">
									<input type="text" name="custInfo" id="custInfo" class="form-control-original form-control" 
										placeholder="<s:property value="getText('cust.id.label')"/>"
										autocomplete="off" required>
								</div>
								<div class="col-xs-3">
									<s:hidden name="wildcardSearch" value="true"></s:hidden>
									<button type="submit" id="btnSubmit" class="btn btn-primary">
										<s:property value="getText('search.button')" />
										<i class="fa" id="custsearch"></i>
									</button>
								</div>
							</div>
						</form>
						<div class="row">
							<div class="col-xs-12 col-sm-7">
								<s:actionerror  id="custError" theme="bootstrap" cssClass="alert-dismissible" onclick="dismiss" />
								<s:actionmessage id="custMessage" theme="bootstrap" 
										cssClass="col-xs-12 col-sm-7 alert-dismissible" onclick="dismiss" />
							</div>
						</div>
						</div>
				</div>

				<br>
				<s:if test="customerList!=null && customerList.size() > 0">
					<div class="well well-sm" id="multipleCust">
						<div class="container-fluid" style="background-color:#ADC2EE;margin-right: 16px; margin-bottom:2px;">
							<div class="row">
								<div class="col-xs-1 col-sm-1"><p class="p-margin-6"><b>#</b></p></div>
								<div class="col-xs-7 col-sm-5"><p class="p-margin-6"><b><s:property value="getText('customer.name')"/></b></p></div>
		  						<div class="col-xs-4 col-sm-2"><p class="p-margin-6"><b><s:property value="getText('customer.id')"/></b></p></div>
		  						<div class="col-xs-7 col-xs-offset-1 col-sm-2 col-sm-offset-0"><p class="p-margin-6"><b><s:property value="getText('phone.no')"/></b></p></div>
		  						<div class="col-xs-4 col-sm-2"><p class="p-margin-6"><b><s:property value="getText('status.label')"/></b></p></div>
							</div>
						</div>
						<ul class="list-group pre-scrollable" id="customer-list-group"	style="height:auto; max-height: 60vh;">
							<s:iterator value="customerList" status="customerListStatus">
								<li class="list-group-item container-fluid" style="padding: 6px 0px;">
									<span class="col-xs-1 col-sm-1">
										<s:property value="#customerListStatus.count"/>
									</span>
									<span class="col-xs-7 col-sm-5">
										<s:property value="customerHeader.ctNm"/>
									</span>
									<span class="col-xs-4 col-sm-2">
										<s:a action="customerInfo" cssStyle="font-weight: bold;">
											<s:param name="customerId" value="customerHeader.customerHeaderPK.custId"></s:param>
											<s:property value="customerHeader.customerHeaderPK.custId"/>
										</s:a>
									</span>
									<span class="col-xs-7 col-xs-offset-1 col-sm-2 col-sm-offset-0">
										<s:if test="customerHeader.ctPhone!=null && customerHeader.ctPhone.length > 0">
											<s:property value="customerHeader.ctPhone"/>
										</s:if>
										<s:else>-</s:else>
									</span>
									<span class="col-xs-4 col-sm-2">
										<s:set var="stsCd" value="customerHeader.ctStsCd"></s:set>
										<s:if test="%{#stsCd=='0'}">
											<s:property value="getText('inactive_status')"/>
										</s:if>
										<s:elseif test="%{#stsCd=='1' || #stsCd=='A' }">
											<s:property value="getText('active_status')"/>
										</s:elseif>
										<s:else>
											<s:property value="getText('deleted_status')"/>
										</s:else>
									</span>
								</li>
							</s:iterator>
						</div>
	
					</ul>
					<%-- <button type="button" class="btn btn-primary pull-right hidden"
						id="customerSelected"><s:property value="getText('next.button')"/></button> --%>
				</s:if>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->

	</div>
	<!-- ./wrapper -->
	<%-- <script type="text/javascript">
		var isPageBeingRefreshed = false;

		$(window).on('beforeunload', function(){
    		isPageBeingRefreshed = true;
		});
						
	</script> --%>
	
	<script type="text/javascript">
		
		$("#custInfo").autocomplete({
			autoFocus: true,
			minLength: 2,
			delay: 500,
			source: function(request, response) {
				$("#custError").addClass('hidden');
				$.ajax({
				  url: "customerLookupAjax",
				   	timeout: 180000,
				  /* timeout : 1800000, */
				  dataType: "json",
				  data: {
				    custInfo: request.term,
				    wildcardSearch: true,
				    maxCustomers: 10
				  },
				  success: function(data) {
				  	response(data);
				  },
				  error: function(jqXHR, textStatus, errorThrown){
				  	if(isPageBeingRefreshed) return;
				  	response();
				  	alert(textStatus);         
	              }
				});
			},
			response: function( event, ui ) {
				$(this).removeClass("ui-autocomplete-loading");
			},
			select: function(event, ui) {
				$(this).val(ui.item.customerHeader.customerHeaderPK.custId);
				$("#customerLookup").get(0).submit();
				return false;
			}
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append( "<div>" + item.customerHeader.ctNm + " - " + item.customerHeader.customerHeaderPK.custId + "</div>" )
	        .appendTo( ul );
	    };
	    
	</script>
	
	
	
	
	<script type="text/javascript">
	    // Highlight Menubar
	    $("#customer").addClass('active');
	    $("#customersearch").addClass('active');
	    // Hide topbar customer search form
	    $("#topbarCustomerLookup").addClass('hidden');
	    // Focus to customer info textbox
	    $("#custInfo").focus();
	</script>

	<script type="text/javascript">
		function handleBackFunctionality()
		{
		    var previousPageURL = "<%=session.getAttribute("PreviousPageURL")%>";
		    if (document.referrer == previousPageURL)
		    {
		        alert("if block");
		    }
		    else
		    {
		    	alert("else block");
		    }
		}
	</script>
</body>
</html>