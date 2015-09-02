var dialog;
var grid;
$(function() {
	grid = lyGrid({
				id : 'paging',
				l_column : [ {
					colkey : "id",
					name : "id",
					hide : true
				}, {
					colkey : "accountName",
					name : "账号"
				}, {
					colkey : "module",
					name : "模块"
				}, {
					colkey : "methods",
					name : "方法"
				}, {
					colkey : "actionTime",
					name : "响应时间",
					width : "150px"
				} , {
					colkey : "userIP",
					name : "IP地址"
				}, {
					colkey : "operTime",
					name : "执行时间",
					renderData : function(rowindex,data, rowdata, column) {
						return new Date(data).format("yyyy-MM-dd hh:mm:ss");
					}
				}, {
					colkey : "description",
					name : "执行描述"
				}],
				jsonUrl : rootPath + '/log/findByPage.shtml',
				checkbox : false
			});
	$("#searchForm").click("click", function() {//绑定查询按扭
		var searchParams = $("#fenye").serializeJson();
		grid.setOptions({
			data : searchParams
		});
	});
});