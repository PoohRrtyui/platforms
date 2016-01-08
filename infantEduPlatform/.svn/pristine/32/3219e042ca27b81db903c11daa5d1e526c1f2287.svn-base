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
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">
        <script src="static/plugins/iCheck/icheck.min.js"></script>
        <div class="col-sm-10" id="settings">
            <form class="form-horizontal" id="bureauForm">
                <div class="form-group">
                    <label for="locationCode" class="col-sm-2 control-label">行政区域</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="locationCode" placeholder="Name"
                        name="locationCode">
                    </div>
                </div>
                <div class="form-group">
                    <label for="parentBureau" class="col-sm-2 control-label">上级单位</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="parentBureau"
                               placeholder="上级单位" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="bureauName" class="col-sm-2 control-label">名称</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="bureauName"
                               name="bureauName" placeholder="单位名称">
                    </div>

                    <label for="bureauAddress" class="col-sm-2 control-label">单位地址</label>

                    <div class="col-sm-4">
                        <input class="form-control" id="bureauAddress" name="bureauAddress"
                               placeholder="单位地址"/>
                    </div>
                </div>
                <%--<div class="form-group">
                    <label for="inputSkills" class="col-sm-2 control-label">单位简介</label>

                    <div class="col-sm-10">
                        <textarea type="text" class="form-control" id="inputSkills"
                                  placeholder="Skills"></textarea>
                    </div>
                </div>--%>
                <div class="form-group">
                    <label for="homepageUrl" class="col-sm-2 control-label">单位主页</label>

                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="homepageUrl"
                               name="homepageUrl" placeholder="Email">
                    </div>

                    <label for="telephone" class="col-sm-2 control-label">单位电话</label>

                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="telephone"
                               name="telephone" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="dutyPerson" class="col-sm-2 control-label">联系人</label>

                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="dutyPerson"
                               name="dutyPerson" placeholder="Email">
                    </div>
                    <label for="postalCode" class="col-sm-2 control-label">邮编</label>

                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="postalCode"
                               name="postalCode" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="input-1" class="col-sm-2 control-label">类型</label>
                    <div class="iradio_square-blue">
                        <input  type="radio" id="input-1" name="demo-radio">
                    </div>
                    <label for="input-1" class="">主管单位</label>

                    <div class="iradio_square-blue" >
                        <input  type="radio" id="input-2" name="demo-radio">
                    </div>
                    <label for="input-2" class="">普通单位</label>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-danger">保存</button>
                    </div>
                </div>
            </form>
            <script>
                $(function(){
                    $('input').iCheck({
                        labelHover : false,
                        cursor : true,
                        checkboxClass : 'icheckbox_square-blue',
                        radioClass : 'iradio_square-blue',
                        increaseArea : '20%'
                    });
                });
            </script>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
