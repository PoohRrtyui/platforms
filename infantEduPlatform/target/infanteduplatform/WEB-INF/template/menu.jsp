<%--
  Created by IntelliJ IDEA.
  User: PoohD
  Date: 2015/11/27
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>左侧菜单页面</title>
    <script type="text/javascript" src="static/js/menu/userType.js"></script> 
</head>
<body>
<div class="main-sidebar">
    <aside class="main-sidebar">
        <section class="sidebar">
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="搜索...">
					<span class="input-group-btn">
                		<button type="submit" name="search" id="search-btn" class="btn btn-flat">
                		<i class="fa fa-search"></i>
                		</button>
              		</span>
                </div>
            </form>
            <!-- /.search form -->
            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">

            </ul>
        </section>
    </aside>
</div>
<script type="text/javascript">
        $(function() {
        	userType.init();
        });
        </script>

