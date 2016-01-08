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
        <script src="static/js/codeMaster/codeMaster.js"></script>
        <script id="tpl" type="text/x-handlebars-template">
            {{#each func}}
            <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.fn}}">
                {{this.name}}
            </button>
            {{/each}}
        </script>
        <script type="text/javascript">
            $(function () {
                codeMaster.init();
            });
        </script>
        <div>
            <div>
                <section class="content-header">
                    <h1>数据字典列表</h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-body">
                                    <div>
                                        <input type="button" id="addCodeMasterInfo" value="添加"
                                               class="btn btn-{{this.type}} btn-sm" name="add">
                                    </div>
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>排序号</th>
                                            <th>大分类编号</th>
                                            <th>大分类名称</th>
                                            <th>中分类编号</th>
                                            <th>中分类名称</th>
                                            <th>小分类编号</th>
                                            <th>小分类名称</th>
                                            <th>系统字段</th>
                                            <th>描述</th>
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
