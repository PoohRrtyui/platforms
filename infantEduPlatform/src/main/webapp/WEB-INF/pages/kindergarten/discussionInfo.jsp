<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<link rel="stylesheet" href="static/plugins/layer/skin/layer.css"
			type="text/css" />
		<link rel="stylesheet" href="static/plugins/iCheck/square/blue.css">
		<link rel="stylesheet" href="static/plugins/zTree3.5.18/css/metroStyle/metroStyle.css">
		<script src="static/plugins/validate/jquery.validate.js"></script>
		<script src="static/plugins/validate/jquery.validate.extend.js"></script>
		<script src="static/plugins/layer/layer.js"></script>
		<script src="static/plugins/iCheck/icheck.min.js"></script>
        <script src="static/plugins/zTree3.5.18/js/jquery.ztree.all-3.5.min.js"></script>
        <script src="static/js/kindergarten/discussionInfo.js"></script>
		<script id="tpl" type="text/x-handlebars-template">
            {{#each func}}
            <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.fn}}">
                {{this.name}}
            </button>
            {{/each}}
        </script>
		<script type="text/javascript">
			$(function() {
				discussion.init();
			});
		</script>
		<div class="col-xs-1">
            <div class="row">
                <ul id="ztree" class="ztree"></ul>
            </div>
        </div>
		<div  class="col-xs-9">
			<div>
				<section class="content-header">
					<h1>交流信息列表</h1>
				</section>
				<section class="content">
					<div class="row">
						<div class="col-xs-12">
							<div class="box">
								<div class="box-body">
								<div>
                                        <input type="button" id="deleteDiscussion" value="删除"
                                               class="btn btn-{{this.type}} btn-sm" name="delete">
                                    </div>
									<table id="discussion" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th><input type="checkbox" id="chkAll" name="chkAll"
													class="chkAll"></th>
												<th>创建人</th>
												<th>头像</th>
												<th>创建时间</th>
												<th>交流内容</th>
												<th>点赞总数</th>
												<th>评论总数</th>
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
