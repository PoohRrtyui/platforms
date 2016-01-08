<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	request.setAttribute("path", request.getContextPath());
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
