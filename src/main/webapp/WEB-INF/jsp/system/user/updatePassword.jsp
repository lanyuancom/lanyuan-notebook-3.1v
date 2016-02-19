<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@include file="/common/common.jspf"%>
	<script type="text/javascript" src="${ctx}/js/system/user/updatePassword.js"></script>
	<style type="text/css">
		.col-sm-3 {
			width: 25%;
			float: left;
		}
		.col-sm-9 {
			width: 75%;
			float: left;
		}
	</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="formUpdatePwd" name="form" class="form-horizontal" method="post" action="${ctx}/user/editPassword.shtml">
		<input type="hidden" class="form-control checkacc" value="${userSession.id}" name="userFormMap.id" id="id">
		<input type="hidden" class="form-control checkacc" value="${userSession.accountName}" name="userFormMap.accountName" id="accountName">
		<input type="hidden" class="form-control checkacc" value="${userSession.password}" name="userFormMap.password" id="password">
		<section class="panel panel-default">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">新密码</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入新密码" name="userFormMap.newpassword" id="newpassword" >
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">确认密码</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入确认密码" name="userFormMap.confirmpassword" id="confirmpassword" >
					</div>
				</div>
			</div>
			<footer class="panel-footer text-right bg-light lter">
				<button type="submit" class="btn btn-success btn-s-xs">修改</button>
			</footer> 
		</section>
	</form>
	<script type="text/javascript">
		onloadurl();
	</script>
</body>
</html>