<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/resources/edit.js"></script>
<style type="text/css">
#but button {
	margin-bottom: 5px;
	margin-right: 5px;
}
.col-sm-3 {
	width: 15%;
	float: left;
}

.col-sm-9 {
	width: 85%;
	float: left;
}

label[class^="btn btn-default"] {
	margin-top: -4px;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/resources/editEntity.shtml">
		<input type="hidden" value="${resources.id}" name="resFormMap.id"
			id="id">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">菜单名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkacc"
						placeholder="请输入菜单名称" name="resFormMap.name" id="name"
						value="${resources.name}">
				</div>
			</div>

			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">菜单标识</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkacc"
						placeholder="请输入菜单标识" name="resFormMap.resKey" id="resKey"
						value="${resources.resKey}">
				</div>
			</div>

			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">菜单url</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkacc"
						placeholder="请输入菜单url" name="resFormMap.resUrl" id="resUrl"
						value="${resources.resUrl}">
				</div>
			</div>

			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">上级菜单</label>
				<div class="col-sm-9">
					<select id="parentId" name="resFormMap.parentId"
						class="form-control m-b">
					</select>
				</div>
			</div>

			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">菜单类型</label>
				<div class="col-sm-9">
						<select id="type" name="resFormMap.type" class="form-control m-b"
							tabindex="-1" onchange="but(this)">
							<option value="0">------ 目录 ------</option>
							<option value="1">------ 菜单 ------</option>
							<option value="2">------ 按扭 ------</option>
						</select>
				</div>
			</div>
			<div class="form-group" id="divbut" style="display: none;">
				<label class="col-sm-3 control-label">选择</label>
				<div class="col-sm-9">
					<div id="but" class="doc-buttons"></div>
					<font color="red">可自定义填入html代码</font>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">图标</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkacc"
						placeholder="请输入icon" name="resFormMap.icon" id="icon"
						value="${resources.icon}">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否隐藏</label>
				<div class="col-sm-9">
					<input id="gritter-light" type="checkbox"
						<c:if test="${resources.ishide eq 1}"> checked="checked"</c:if>
						name="resFormMap.ishide" id="ishide"
						class="ace ace-switch ace-switch-5" value="1">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">菜单描述</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkacc"
						placeholder="请输入菜单描述" name="resFormMap.description"
						id="description" value="${resources.description}">
				</div>
			</div>

		</div>
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> </section>
	</form>
	<script type="text/javascript">
		$("#type").val("${resources.type}");
		if ("${resources.type}" == "2") {
			showBut();
		}
		byRes("${resources.parentId}");
	</script>
</body>
</html>