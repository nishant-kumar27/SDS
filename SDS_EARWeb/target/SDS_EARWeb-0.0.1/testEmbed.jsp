<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Testing pdf print</title>
</head>
<body>
	<s:url action="testEmbedStream" var="streamUrl"></s:url>

	<%--<div style="float:left; width: 50%; background-color: aqua; ">
		<p>Using Embed</p>
		<embed id="embed" src="<s:property value="%{streamUrl}"/>"  width="100%" height="600"></embed>
	</div>--%>

	 <div style="float:left; width: 50%; background-color: silver;">
		<p>Using Object</p>
		<object id="obj" data="<s:property value="%{streamUrl}"/>"  width="100%" height="500"></object>
	</div> 
	
	<script type="text/javascript">
    	window.onload = function() { 
    		var embed = document.getElementById("obj");
    		embed.contentWindow.print(); 
    	}
	</script>
</body>
</html>