<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<link rel="stylesheet" href="static/css/style.css" type="text/css"></link>
		<script type="text/javascript" src="static/js/headimage/cropbox.js"></script>
		<script type="text/javascript" src="static/js/headimage/headimage.js"></script>
		<script>
			$(function(){
				$(function(){
					$("#imgFile").on("click",function(){
						$(".thumbBox").removeAttr("style");
					});
// 					$("#btnCrop").on("click",function(){
// 						$(".preview_").removeAttr("");
// 					});
				});
			});
		</script>
		<div class="container">
			<div class="imageBox">
				<div class="thumbBox" style="background-image:url(${imgFile });"> </div>
				<!-- <div class="spinner" style="display: none">Loading...</div> -->
			</div>
			<form action="headimage/uploadHeadImage" method="POST" enctype="multipart/form-data" id="submithead">
				<div class="action">
					<div class="new-contentarea tc">
						<a href="javascript:void(0)" class="upload-img"><label for="imgFile">打开图片</label></a> 
						<input type="file" name="imgFile" id="imgFile" />
						<input type="hidden" id="x" name="x" />
		                <input type="hidden" id="y" name="y" />
		                <input type="hidden" id="w" name="w" />
		                <input type="hidden" id="h" name="h" />
					</div>
					
<!-- 				默认头像路径：resouses/uploadImages/_default/200x200.jpg -->
	                
					<input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+">
					<input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-">
					<input type="button" id="btnCrop" class="Btnsty_peyton" value="裁切">
				</div>
			</form>
			<div class="cropped" id="preview">
<!-- 				<div class="preview_" style="display:none;"> -->
					<img src="${pic3 }" align="absmiddle" style="width:30px;margin-top:4px;border-radius:30px;box-shadow:0px 0px 12px #7E7E7E;" ><p>30px*30px</p> 
					<img src="${pic2 }" align="absmiddle" style="width:50px;margin-top:4px;border-radius:50px;box-shadow:0px 0px 12px #7E7E7E;" ><p>50px*50px</p>
					<img src="${pic1 }" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;" ><p>180px*180px</p>
<!-- 				</div> -->
			</div>
		</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>