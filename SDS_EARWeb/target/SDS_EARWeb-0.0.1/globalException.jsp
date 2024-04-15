<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SDS | Exception</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="assets/font-awesome-4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="assets/css/jquery-ui.css">
<link rel="stylesheet" href="assets/css/Customizations.css">

<script src="Starter_files/jQuery-2.js"></script>
<script src="Starter_files/bootstrap.js"></script>
<script src="Starter_files/app.js"></script>
<%-- <script src="assets/plugins/jQueryUI/jquery-ui.min.js"></script> --%>
<%-- <script src="assets/chart/Chart.min.js"></script> --%>

</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">
	<s:include value="topbar.jsp" />
	<s:include value="menubar.jsp"></s:include>
	
	<div style="min-height: 595px;" class="content-wrapper">
		<section class="content">
<!-- 	<div style="padding:10px; margin:10px; background-color: white;"> -->
			<h1><s:property value="getText('_unexpected.error')"/></h1>
			<h3>
				<s:property value="getText('_unexpected.error.name')"/>: <s:property value="exception" />
			</h3>
			
			<div>
				<input id="backButton" type="button" class="btn btn-primary" value="Back" onclick="history.back();">
			</div>
			<br>
			<a class="btn-link" role="button" data-toggle="collapse" href="#collapseException" 
				aria-expanded="false" aria-controls="collapseException">
  				Exception Details
			</a>
			<div class="collapse" id="collapseException">
  				<div class="well well-sm">
					<h5>
						<s:property value="getText('_unexpected.error.details')"/>:<s:property value="exceptionStack" />
					</h5>
  				</div>
  			</div>
		<%-- <s:property value="getText('_unexpected.error.text')"/>:<s:debug></s:debug> --%>
		</section>
	</div>
	</div>
	
	<script type="text/javascript">
		// Activate menubar link here
    	//$('#dashboard').addClass('active');
		$(function(){
			var backButtonId = "#backButton";
			if(history==null || (history!=null && history.length==1)) {
				$(backButtonId).addClass("disabled");
				$(backButtonId).prop('onclick',null).off('click'); 
			}
		});
	</script>
</body>
</html>