<%--
  Created by IntelliJ IDEA.
  User: Pooh
  Date: 2015/12/7
  Time: 19:34
  学校操作页面 - 添加 修改
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>学校操作页面</title>
    <link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">
</head>
<body>

<div class="col-sm-12" id="settings">
    <form class="form-horizontal" id="schoolForm">
        <div class="form-group">
            <label for="schoolCode" class="col-sm-2 control-label">学校组织机构代码</label>
            <div class="col-sm-4">
                <input type="text" class="form-control required character" id="schoolCode"
                       name="schoolCode" placeholder="学校组织机构代码">
            </div>
            <label for="bureauName" class="col-sm-2 control-label">所属单位</label>
            <div class="col-sm-4">
                <input type="text" class="form-control required" id="bureauName"
                       name = "bureauName" placeholder="所属单位" readOnly="true">
                <input type="hidden" id="schoolId" name="schoolId" value="${schoolId}">
                <input type="hidden"  name="bureauId">
                <div id="bureauContent" class="form-control"
                     style="width:270px;height:auto;display:none; position: absolute;">
                    <ul id="parentBureauTree"  class="ztree"></ul>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="schoolName" class="col-sm-2 control-label">学校名称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control required chinesezimu" id="schoolName"
                       name="schoolName" placeholder="学校名称">
            </div>

            <label for="schoolAddress" class="col-sm-2 control-label">学校地址</label>

            <div class="col-sm-4">
                <input type="text" class="form-control chinesezimu" id="schoolAddress" name="schoolAddress"
                       placeholder="学校地址"/>
            </div>
        </div>
        <div class="form-group">
            <label for="homepageUrl" class="col-sm-2 control-label">主页地址</label>

            <div class="col-sm-4">
                <input type="text" class="form-control url" id="homepageUrl"
                       name="homepageUrl" placeholder="主页地址">
            </div>

            <label for="telephone" class="col-sm-2 control-label">学校电话</label>

            <div class="col-sm-4">
                <input type="text" class="form-control fax" id="telephone"
                       name="telephone" placeholder="单位电话">
            </div>
        </div>
        <div class="form-group">
            <label for="dutyPerson" class="col-sm-2 control-label">联系人</label>

            <div class="col-sm-4">
                <input type="text" class="form-control chinesezimu" id="dutyPerson"
                       name="dutyPerson" placeholder="联系人">
            </div>
            <label for="postalCode" class="col-sm-2 control-label">邮编</label>

            <div class="col-sm-4">
                <input type="text" class="form-control zipcode" id="postalCode"
                       name="postalCode" placeholder="邮编">
            </div>
        </div>
        <div class="form-group">
            <label for="input-1" class="col-sm-2 control-label">学校类型</label>
            <div class="col-sm-10">
                <div class="iradio_square-blue">
                    <input  class="required" type="radio" id="input-1" name="schoolStyle" value="0">
                </div>
                <label for="input-1" class="">公办</label>

                <div class="iradio_square-blue" >
                    <input  class="required" type="radio" id="input-2" name="schoolStyle" value="1">
                </div>
                <label for="input-2" class="">民办</label>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-5 col-sm-7">
                <button type="button" id="schoolSave" class="btn btn-danger">保存</button>
            </div>
        </div>
    </form>
    <script src="static/plugins/iCheck/icheck.min.js"></script>
    <script src="static/js/school/schoolOp.js"></script>
</div>
</body>
</html>
