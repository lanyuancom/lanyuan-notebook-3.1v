$(function() {
	// 异步加载所有菜单列表
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			ly.ajaxSubmit(form,{//验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data == "success") {
						layer.confirm('编辑成功!是否关闭窗口?', function(index) {
							parent.grid.loadData();
				        	parent.layer.close(parent.pageii);
				        	return false;
						});
					} else {
						layer.alert('编辑失败！', 3);
					}
				}
			});
		},
		rules : {
			resKey : {
				required : true
			},
			resUrl : {
				required : true
			}
		},
		messages : {
			resKey : {
				required : "菜单标识不能为空"
			},
			resUrl : {
				required : "url连接不能为空"
			}
		},
		errorPlacement : function(error, element) {// 自定义提示错误位置
			$(".l_err").css('display', 'block');
			// element.css('border','3px solid #FFCCCC');
			$(".l_err").html(error.html());
		},
		success : function(label) {// 验证通过后
			$(".l_err").css('display', 'none');
		}
	});
});
function but(v){
	if(v.value==2){
		 showBut();
	}else{
		$("#divbut").css("display","none");
	}
}
function toBut(b){
	$("#description").val($("#"+b.id).html());
}
function showBut(){
	$("#divbut").css("display","block");
	var url = rootPath + '/resources/findByButtom.shtml';
	var data = CommnUtil.ajax(url, null,"json");
	if (data != null) {
		var bb = $("#but");
		bb.html('');
		for ( var i = 0; i < data.length; i++) {
			bb.append("<span onclick=\"toBut(this)\" id=\"span_"+data[i].id+"\">"+ data[i].buttom+"</span>");
		}
	} else {
		layer.msg("获取按扭列表失败！");
	}
}
function byRes(id){
	var url = rootPath + '/resources/reslists.shtml';
	var data = CommnUtil.ajax(url, null,"json");
	if (data != null) {
		var h = "<option value='0'>------顶级目录------</option>";
		for ( var i = 0; i < data.length; i++) {
			if(parseInt(id,10)==parseInt(data[i].id,10)){
				h+="<option value='" + data[i].id + "' selected='selected'>"
								+ data[i].name + "</option>";
			}else{
				h+="<option value='" + data[i].id + "'>"+ data[i].name + "</option>";
			}
		}
		$("#parentId").html(h);
	} else {
		bootbox.alert("获取菜单信息错误，请联系管理员！");
	}
}