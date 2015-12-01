<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/1
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <link rel="stylesheet" href="static/plugins/zTree3.5.18/css/metroStyle/metroStyle.css">
        <script src="static/plugins/zTree3.5.18/js/jquery.ztree.all-3.5.min.js"></script>
        <script src="static/js/bureau/bureauInfo.js"></script>
        <script>
            $(function () {
                bureau.init();
            });
        </script>
        <div class="col-xs-2">
            <div class="row">
                <ul id="bureauTree" class="ztree"></ul>
            </div>
        </div>
        <div class="col-xs-10">
            <div class="row">
                <div class="col-md-8">
                    <div class="box box-solid">
                        <div class="box-header with-border">
                            <i class="fa fa-text-width"></i>

                            <h3 class="box-title">单位基本信息</h3>
                        </div>
                        <!-- /.box-header -->
                        <div id="bureau-box" class="box-body">
                            <div class="col-xs-5">
                                <dl class="dl-horizontal">
                                    <dt>单位名称</dt>
                                    <dd>南京市教育局</dd>
                                    <dt>单位编号</dt>
                                    <dd>NJ200100</dd>
                                    <dt>单位类型</dt>
                                    <dd>主管单位</dd>
                                </dl>
                            </div>
                            <div class="col-xs-5">
                                <dl class="dl-horizontal">
                                    <dt>父单位编号</dt>
                                    <dd>NJ200000</dd>
                                    <dt>父单位名称</dt>
                                    <dd>江苏省教育厅</dd>
                                </dl>
                            </div>
                        </div>
                        <!-- /.box-body -->
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-4">
                    <div class="box box-solid box-primary">
                        <div class="box-header">
                            <div>
                                <h3 class="widget-user-username">Alexander Pierce</h3>
                                <h5 class="widget-user-desc">Founder &amp; CEO</h5>
                            </div>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-sm-4 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">3,200</h5>
                                        <span class="description-text">SALES</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                                <div class="col-sm-4 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">13,000</h5>
                                        <span class="description-text">FOLLOWERS</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                                <div class="col-sm-4">
                                    <div class="description-block">
                                        <h5 class="description-header">35</h5>
                                        <span class="description-text">PRODUCTS</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                            </div>
                        </div><!-- /.box-body -->
                    </div>
                </div>
            </div>
        </div>
        <script>


        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>

