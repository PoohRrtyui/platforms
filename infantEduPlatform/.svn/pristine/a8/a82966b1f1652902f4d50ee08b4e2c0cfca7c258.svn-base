<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String contextPath = request.getContextPath();
//contextPath = (contextPath == null || contextPath == "") ? "/":contextPath;
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath;
%>
<base href="<%=basePath%>">
<!--layer,bootstrap基本样式 及js-->
<link rel="stylesheet" href="<%=basePath%>/static/plugins/layer/skin/layer.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>/static/plugins/bootstrap/css/bootstrap.min.css"/>
<script type="text/javascript" src="<%=basePath%>/static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/plugins/dataTables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/plugins/dataTables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/plugins/layer/layer.min.js"></script>
<script type="text/javascript">
    var BEDU = BEDU || {};
    BEDU.contextPath = '<%=contextPath%>';
    BEDU.basePath = '<%=basePath%>';
</script>