<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/";
%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <link rel="stylesheet" href="static/plugins/layer/skin/layer.css"
              type="text/css"/>
         <link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">
        <script type="text/javascript" src="static/js/common/base.js"></script>
        <script src="static/plugins/validate/jquery.validate.js"></script>
        <script src="static/plugins/validate/jquery.validate.extend.js"></script>
        <script src="static/plugins/layer/layer.js"></script>
        <script src="static/plugins/iCheck/icheck.min.js"></script>
        <script src="static/js/kindergarten/bulletin.js"></script>
        <script id="tpl" type="text/x-handlebars-template">
            {{#each func}}
            <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.fn}}">
                {{this.name}}
            </button>
            {{/each}}
        </script>
        <script type="text/javascript">
            $(function () {
                bulletin.init();
            });
        </script>
        <div>
            <div>
                <section class="content-header">
                    <h1>通知公告列表</h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-body">
                                    <div>
                                        <input type="button" id="addBulletin" value="添加"
                                               class="btn btn-{{this.type}} btn-sm" name="addBulletin">
                                         <input type="button" id="deleteList" value="删除"
                                               class="btn btn-{{this.type}} btn-sm" name="deleteList">
                                    </div>
                                    <table id="bulletin" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                        	<th><input type="checkbox" id="chkAll" name="chkAll" class="chkAll"></th>
                                            <th>机构Id</th>
                                            <th>标题</th>
                                            <th>类型</th>
                                            <th>公告内容</th>
                                            <th>班级Id</th>
                                            <th>级别</th>
                                            <th>创建时间</th>
                                            <th>创建人</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody></tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>
