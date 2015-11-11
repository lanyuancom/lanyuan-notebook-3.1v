<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/common/common.jspf"%>
</head>
<style type="text/css">

#mytable {
 width: 660px;
 padding: 0;
 margin: 0;
}

caption {
 padding: 0 0 5px 0;
 width: 660px;  
 font: italic 13px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
 text-align: right;
}

th {
 font: bold 13px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
 color: #4f6b72;
 border-right: 1px solid #C1DAD7;
 border-bottom: 1px solid #C1DAD7;
 border-top: 1px solid #C1DAD7;
 letter-spacing: 2px;
 text-transform: uppercase;
 text-align: left;
 padding: 6px 6px 6px 12px;
}

th.nobg {
 border-top: 0;
 border-left: 0;
 border-right: 1px solid #C1DAD7;
 system: none;
}

#mytable td {
 border-right: 1px solid #C1DAD7;
 border-bottom: 1px solid #C1DAD7;
 system: #fff;
 font-size:11px;
 padding: 6px 6px 6px 12px;
 color: #4f6b72;
}

.lanyuan_bb{
border-bottom: 1px solid #C1DAD7;
}

td.alt {
 system: #F5FAFA;
 color: #797268;
}

th.spec {
 border-left: 1px solid #C1DAD7;
 border-top: 0;
 system: #fff ;
 font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
}

th.specalt {
 border-left: 1px solid #C1DAD7;
 border-top: 1px solid #C1DAD7;
 system: #f5fafa ;
 font: bold 13px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
 color: #797268;
}
/*---------for IE 5.x bug*/
html>body td{ font-size:13px;}
</style>
<script type="text/javascript">
function smenu(obj,id){  
	  $("input[_key='menu_1_"+id+"']").each(function(){
	   $(this).prop("checked",obj.checked);
	  });
	  $("input[_key='menu_1_1_"+id+"']").each(function(){
		   $(this).prop("checked",obj.checked);
		  });
};
function menu_1(obj,id,pid){  
	  $("input[_key_2='menu_1_1_"+id+"']").each(function(){
		   $(this).prop("checked",obj.checked);
	});
	  if(obj.checked==true){
		  $("input[_key='menu_"+pid+"']").each(function(){
			   $(this).prop("checked",obj.checked);
		});
	  }
};
function menu_1_1(obj,id,pid){  
	if(obj.checked==true){
		  $("input[_key_1='menu_1_1_"+id+"']").each(function(){
			   $(this).prop("checked",obj.checked);
		});
		  $("input[_key='menu_"+pid+"']").each(function(){
			   $(this).prop("checked",obj.checked);
		});
	}
}
function closeWin(){
	layer.confirm('是否关闭窗口？', {icon: 3,offset: '-100px'}, function(index) {
    	parent.layer.close(parent.pageii);
    	return false;
	});
}
function sub(){
		ly.ajax({
			async : false, //请勿改成异步，下面有些程序依赖此请数据
			type : "POST",
			data : $("#from").serializeJson(),
			url : rootPath + '/resources/addUserRes.shtml',
			dataType : 'json',
			success : function(json) {
				if (json == "success") {
						layer.confirm('分配成功！是否关闭窗口？',{icon: 3,offset: '-100px'}, function(index) {
				        	parent.layer.close(parent.pageii);
				        	return false;
 						 });
				} else {
					layer.alert(json,{icon: 2,offset: '-100px'});
				}
				;
			}
		});
// window.location="${pageContext.servletContext.contextPath }/function/addRoleFun?roleId=${roleId}&functionId="+fids;
}
</script>
<body>
<form method="post" id="from" name="form">
<input id='userId' name="userId" type="hidden" value="${param.userId}">
<input id='roleId' name="roleId" type="hidden" value="${param.roleId}">
<table id="mytable" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series">
 <tr>
    <th scope="row" abbr="L2 Cache" class="specalt">一级菜单</th>
    <th scope="row" abbr="L2 Cache" class="specalt"><span>二级菜单</span><span style="float: right;margin-right: 150px;">按扭</span></th>
  </tr>
  <c:forEach items="${permissions}" var="k">
  <tr>
    <th scope="row" abbr="L2 Cache" class="specalt">
    <input type="checkbox" name="resId" id="menu" _key="menu_${k.id}" onclick="smenu(this,'${k.id}')" value="${k.id}">
    ${k.name}
    </th>
    <th scope="row" abbr="L2 Cache" class="specalt">
    <table id="mytable" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series" style="width: 100%;height: 100%;">
    <c:forEach items="${k.children}" var="kc">
    <tr>
    <th scope="row" abbr="L2 Cache" class="specalt">
    <input type="checkbox"  name="resId" id="menu" _key="menu_1_${k.id}" _key_1="menu_1_1_${kc.id}" onclick="menu_1(this,'${kc.id}','${k.id}')"  value="${kc.id}">
    ${kc.name}
    </th>
     <th>
    <c:if test="${not empty kc.children}">
   
    <table id="mytable" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series" style="width: 100%;height: 100%;">
    <c:forEach items="${kc.children}" var="kcc">
    <tr>
    <th scope="row" abbr="L2 Cache" class="specalt">
    <input type="checkbox"  name="resId" id="menu" _key="menu_1_1_${k.id}" _key_2="menu_1_1_${kc.id}" onclick="menu_1_1(this,'${kc.id}','${k.id}')" value="${kcc.id}">
    ${kcc.name}
    </th>
     </tr>
    </c:forEach>
   
    </table>
    
    </c:if>
    </th>
     </tr>
    </c:forEach>
   
    </table>
    </th>
  </tr>
</c:forEach>
</table>
<br>
<div class="doc-buttons" style="text-align: center;">
			<a href="#" class="btn btn-s-md btn-success btn-rounded" onclick="sub()">保存</a>
<a href="#" class="btn btn-s-md btn-info btn-rounded" onclick="closeWin();">关闭</a>
</div>
	<br>
	</form>
	<script type="text/javascript">
	$.ajax({
		type : "POST",
		data : {
			"resFormMap.userId" : "${param.userId}",
			"resFormMap.roleId" : "${param.roleId}"
		},
		url : rootPath + '/resources/findRes.shtml',
		dataType : 'json',
		success : function(json) {
			for (index in json) {
				$("input[name='resId']:checkbox[value='" + json[index].id + "']").prop(
						'checked', 'true');
			}
		}
	});
	</script>
</body>
</html>