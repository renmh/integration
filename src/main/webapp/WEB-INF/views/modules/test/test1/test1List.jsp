<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>功能管理</title>
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
		<li class="active"><a href="${ctx}/test/test1/test1/">功能列表</a></li>
		<shiro:hasPermission name="test:test1:test1:edit"><li><a href="${ctx}/test/test1/test1/form">功能添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="test1" action="${ctx}/test/test1/test1/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>代码</th><th class="sort name">名称</th><th>创建时间</th><th>创建人</th><th>修改时间</th><th>修改人</th><th>备注</th><shiro:hasPermission name="test:test1:test1:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="test1">
			<tr>
				<td>${test1.dm}</td>
				<td><a href="${ctx}/test/test1/test1/form?id=${test1.id}">${test1.name}</a></td>
				<td>${test1.createDate}</td>
				<td>${test1.createBy.loginName}</td>
				<td>${test1.updateDate}</td>
				<td>${test1.updateBy.loginName}</td>
				<td>${test1.remarks}</td>
				<shiro:hasPermission name="test:test1:test1:edit"><td>
    				<a href="${ctx}/test/test1/test1/form?id=${test1.id}">修改</a>
					<a href="${ctx}/test/test1/test1/delete?id=${test1.id}" onclick="return confirmx('确认要删除该功能吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
