<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function() {
	$("#groupsForSelect").dblclick(function() {
		selected();
	});
	$("#selectGroups").dblclick(function() {
		unselected();
	});
});
function selected() {
	var selOpt = $("#groupsForSelect option:selected");

	selOpt.remove();
	var selObj = $("#selectGroups");
	selObj.append(selOpt);

	var selOpt = $("#selectGroups")[0];
	ids = "";
	for (var i = 0; i < selOpt.length; i++) {
		ids += (selOpt[i].value  + ",");
	}

	if (ids != "") {
		ids = ids.substring(0, ids.length - 1);
	}
	$('#txtGroupsSelect').val(ids);
}

function selectedAll() {
	var selOpt = $("#groupsForSelect option");

	selOpt.remove();
	var selObj = $("#selectGroups");
	selObj.append(selOpt);

	var selOpt = $("#selectGroups")[0];
	ids = "";
	for (var i = 0; i < selOpt.length; i++) {
		ids += (selOpt[i].value  + ",");
	}

	if (ids != "") {
		ids = ids.substring(0, ids.length - 1);
	}
	$('#txtGroupsSelect').val(ids);
}

function unselected() {
	var selOpt = $("#selectGroups option:selected");
	selOpt.remove();
	var selObj = $("#groupsForSelect");
	selObj.append(selOpt);

	var selOpt = $("#selectGroups")[0];
	ids = "";
	for (var i = 0; i < selOpt.length; i++) {
		ids += (selOpt[i].value + ",");
	}
	
	if (ids != "") {
		ids = ids.substring(0, ids.length - 1);
	}
	$('#txtGroupsSelect').val(ids);
}

function unselectedAll() {
	var selOpt = $("#selectGroups option");
	selOpt.remove();
	var selObj = $("#groupsForSelect");
	selObj.append(selOpt);

	$('#txtGroupsSelect').val("");
}
</script>
<div class="form-group">
<input id="txtGroupsSelect" type="hidden" value="${txtRoleSelect}"
			name="txtGroupsSelect" />
	<label for="host" class="col-sm-3 control-label">角色</label>
	<div class="col-sm-9">
		<table class="tweenBoxTable" name="groups_tweenbox"
			id="groups_tweenbox" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td>已角色</td>
					<td></td>
					<td>可角色</td>
				</tr>
				<tr>
					<td><select id="selectGroups" multiple="multiple"
						class="input-large" name="selectGroups"
						style="height: 150px; width: 150px">
						<c:forEach items="${userRole}" var="key">
						<option value="${key.id}">${key.name}</option>
						</c:forEach>
					</select></td>
					<td align="center">
						<div style="margin-left: 5px; margin-right: 5px">
							<button onclick="selectedAll()" class="btn btn-primary"
								type="button" style="width: 50px;" title="全选">&lt;&lt;</button>
						</div>
						<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
							<button onclick="selected()" class="btn btn-primary"
								type="button" style="width: 50px;" title="选择">&lt;</button>
						</div>
						<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
							<button onclick="unselected()" class="btn btn-primary"
								type="button" style="width: 50px;" title="取消">&gt;</button>
						</div>
						<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px">
							<button onclick="unselectedAll()" class="btn btn-primary"
								type="button" style="width: 50px;" title="全取消">&gt;&gt;</button>
						</div>
					</td>
					<td><select id="groupsForSelect"
						multiple="multiple" class="input-large"
						style="height: 150px; width: 150px">
						<c:forEach items="${role}" var="key">
						<option value="${key.id}">${key.name}</option>
						</c:forEach>
					</select></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>