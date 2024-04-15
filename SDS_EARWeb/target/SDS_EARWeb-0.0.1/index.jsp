
<%
	if(session.getAttribute("employee")==null) {
		response.sendRedirect("loginPage");
		return;
	}
	response.sendRedirect("homePage");
%>