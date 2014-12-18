<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>功能测试管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/test/test1/testEntity/">功能测试列表</a></li>
		<shiro:hasPermission name="test:test1:testEntity:edit"><li><a href="${ctx}/test/test1/testEntity/form">功能测试添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testEntity" action="${ctx}/test/test1/testEntity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>代码</th><th>名称</th><th>创建时间</th><th>创建人</th><th>修改时间</th><th>修改人</th><th>备注</th><shiro:hasPermission name="test:test1:testEntity:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="testEntity">
			<tr>
				<td>${testEntity.dm}</td>
				<td><a href="${ctx}/test/test1/testEntity/form?id=${testEntity.id}">${testEntity.name}</a></td>
				<td>${testEntity.createDate}</td>
				<td>${testEntity.createBy.loginName}</td>
				<td>${testEntity.updateDate}</td>
				<td>${testEntity.updateBy.loginName}</td>
				<td>${testEntity.remarks}</td>
				<shiro:hasPermission name="test:test1:testEntity:edit"><td>
    				<a href="${ctx}/test/test1/testEntity/form?id=${testEntity.id}">修改</a>
					<a href="${ctx}/test/test1/testEntity/delete?id=${testEntity.id}" onclick="return confirmx('确认要删除该功能测试吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
