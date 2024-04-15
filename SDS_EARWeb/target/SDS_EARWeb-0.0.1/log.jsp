<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SDS Log file</title>
        <script type="text/javascript" src="jquery/jquery-2.2.4.min.js"></script>
    </head>
    <body style="margin: 10px;">
        <pre style="font-size:small;">
        <%
        	int linesToDisplay = 500;
			String jspPath = System.getProperty("user.dir")+"/servers/AdminServer/logs/";
            String fileName = "SDS_Log.log";
            String txtFilePath = jspPath + fileName;
            out.println(txtFilePath+"\n------------------------------------------------------------------------------------------------------------------");
            BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
            //BufferedReader br = new InputStreamReader(new FileInputStream(txtFilePath));
            StringBuilder sb = new StringBuilder();
            String line;
            int lineCount=0, counter=0;
			while(reader.readLine()!= null){
                lineCount++;
            }
            reader = new BufferedReader(new FileReader(txtFilePath));
            while((line = reader.readLine())!= null ){
            	if(counter++ >= lineCount-linesToDisplay)
                	sb.append(line+"\n");
            }
            out.println(sb.toString());
         %>
		</pre>
		<div style="position: fixed; bottom: 18px;left:30% ; background-color: rgba(255, 255, 255, 0.9); ">
			<button onclick="window.location.reload()" style="height: 34px;">Refresh</button>
			&nbsp;&nbsp;&nbsp;
				<label style="color: red;"><input id="checkbox" type="checkbox" checked="checked">Auto Refresh</label>
			
		</div>
		<script type="text/javascript">
			$('html,body').animate({scrollTop: document.body.scrollHeight},"slow");
			var interval = setInterval(function(){$('button').click();}, 5000);
			$('#checkbox').on('change', function(e){
				if(this.checked)
					interval = setInterval(function(){$('button').click();}, 5000);
				else
					clearInterval(interval);
			});
			
		</script>
    </body>
</html>