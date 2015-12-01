<%--
  Created by IntelliJ IDEA.
  User: PoohD
  Date: 2015/11/27
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/";
%>
<html>
<base href="<%=basePath%>">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title> Input Data</title>
</head>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <body>
        <div>
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    数据列表
                    <small>advanced tables</small>
                </h1>
            </section>
            <!-- Main content -->
            <section class="content">
                <div class="row">
                    <div class="col-xs-6">
                        <div class=" col-xs-3">这是输入框sss</div>
                        <div class=" col-xs-3"><label><input type="text" name="name"/> </label></div>
                        <div class=" col-xs-3"></div>
                        <div class=" col-xs-3"></div>
                        <div class=" col-xs-3"></div>
                        <div class=" col-xs-3"></div>
                    </div>
                </div>
            </section>
        </div>
        </body>
    </tiles:putAttribute>
</tiles:insertDefinition>
</html>
