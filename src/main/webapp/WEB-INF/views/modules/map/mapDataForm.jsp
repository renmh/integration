<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/manager/mapData/">地图数据列表</a></li>
		<shiro:hasPermission name="manager:map:edit"><li class="active"><a href="${ctx}/manager/mapData/form">地图数据${not empty mapData.id?'修改':'添加'}</a></li></shiro:hasPermission>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="mapData" action="${ctx}/manager/mapData/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="value">ID:</label>
			<div class="controls">
				<form:input path="dm" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="label">名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="label">缩放级别（0,1,2,3）:</label>
			<div class="controls">
				<form:input path="zoom" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="type">弹窗位置X:</label>
			<div class="controls">
				<form:input path="lx" htmlEscape="false" maxlength="150" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="description">弹窗位置Y:</label>
			<div class="controls">
				<form:input path="ly" htmlEscape="false" maxlength="150" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sort">中心位置X:</label>
			<div class="controls">
				<form:input path="cx" htmlEscape="false" maxlength="150" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sort">中心位置Y:</label>
			<div class="controls">
				<form:input path="cy" htmlEscape="false" maxlength="150" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sort">所属分类:</label>
			<div class="controls">
				<form:select path="group" >
					<form:options items="${fns:getDictList('geo_group')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sort">坐标区域:</label>
			<div class="controls">
				<form:textarea path="coords" htmlEscape="false" rows="3" maxlength="5000" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="manager:map:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>