<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/2
  Time: 11:01
  单位操作页面 - 添加 修改
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>单位操作页面</title>
    <link rel="stylesheet" href="static/plugins/bootstrap-select/css/bootstrap-select.css">
    <link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">
</head>
<body>

<div class="col-sm-12" id="settings">
    <form class="form-horizontal" id="bureauForm">
        <div class="form-group">
            <input type="hidden" id="bureauId" name="organizationId" value="${BureauId}">
            <label for="province" class="col-sm-2 control-label">行政区域</label>
            <input  type="hidden" name="locationCode">
            <div class="col-sm-3">
                <select id="province" name="locationCode" class="selectpicker " data-live-search="true" title='省份'>
                </select>
            </div>
            <div class="col-sm-3">
                <select id="city" name="locationCode" class="selectpicker " data-live-search="true" title='市级'>
                </select>
            </div>
            <div class="col-sm-3">
                <select id="region" name="locationCode" class="selectpicker " data-live-search="true" title='区域'>
                </select>
            </div>

        </div>
        <div class="form-group">
            <label for="parentBureau" class="col-sm-2 control-label">上级单位</label>

            <div class="col-sm-4">
                <input type="text" class="form-control required" id="parentBureau"
                       name="parentBureauName" placeholder="上级单位" readOnly="true">
                <input type="hidden" id="newPId" name="parentBureauId">
                <input type="hidden" id="oldPId">

                <div id="bureauContent" class="form-control"
                     style="width:270px;height:auto;display:none; position: absolute;">
                    <ul id="parentBureauTree" class="ztree"></ul>
                </div>
            </div>
            <label for="organizationCode" class="col-sm-2 control-label">单位机构代码</label>

            <div class="col-sm-4">
                <input type="text" class="form-control required character" id="organizationCode"
                       name="organizationCode" placeholder="单位机构代码">
            </div>
        </div>
        <div class="form-group">
            <label for="bureauName" class="col-sm-2 control-label">单位名称</label>

            <div class="col-sm-4">
                <input type="text" class="form-control required chinesezimu" id="bureauName"
                       name="bureauName" placeholder="单位名称">
            </div>

            <label for="bureauAddress" class="col-sm-2 control-label">单位地址</label>

            <div class="col-sm-4">
                <input class="form-control chinesezimu" id="bureauAddress" name="bureauAddress"
                       placeholder="单位地址"/>
            </div>
        </div>
        <div class="form-group">
            <label for="homepageUrl" class="col-sm-2 control-label">单位主页</label>

            <div class="col-sm-4">
                <input type="text" class="form-control url" id="homepageUrl"
                       name="homepageUrl" placeholder="单位主页">
            </div>

            <label for="telephone" class="col-sm-2 control-label">单位电话</label>

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
            <label for="input-1" class="col-sm-2 control-label">类型</label>

            <div class="col-sm-10">
                <div class="iradio_square-blue">
                    <input class="required" type="radio" id="input-1" name="orgType" value="1">
                </div>
                <label for="input-1" class="">主管单位</label>

                <div class="iradio_square-blue">
                    <input class="required" type="radio" id="input-2" name="orgType" value="0">
                </div>
                <label for="input-2" class="">普通单位</label>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-5 col-sm-7">
                <button type="button" id="bureauSave" class="btn btn-danger">保存</button>
            </div>
        </div>
    </form>
    <script src="static/plugins/iCheck/icheck.min.js"></script>
    <script type="text/javascript"
            src="static/plugins/bootstrap-select/js/bootstrap-select.js"></script>
    <script src="static/js/bureau/bureauOp.js"></script>
</div>
</body>
</html>
