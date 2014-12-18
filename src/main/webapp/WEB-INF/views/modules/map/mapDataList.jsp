<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>地图数据管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<style type="text/css">.sort{color:#0663A2;cursor:pointer;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
			// 表格排序
			tableSort({callBack : page});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"将同一缩放级别的js文件进行压缩导入，文件名为缩放级别，例如：0.rar"});
			});
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/manager/mapData/").submit();
	    	return false;
	    }
	    function importData(){
	    	alert(1);
	    }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/manager/mapData/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/manager/mapData/">地图数据列表</a></li>
		<shiro:hasPermission name="manager:map:edit"><li><a href="${ctx}/manager/mapData/form">地图数据添加</a></li></shiro:hasPermission>
	</ul>
	
	<form:form id="searchForm" modelAttribute="mapData" action="${ctx}/manager/mapData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<div>
			<label>名称：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			&nbsp;<input id="btnImport" class="btn btn-primary" type="button" value="数据导入"/>
		</div>
	</form:form>
	
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>ID</th><th class="sort name">名称</th><th class="sort zoom">缩放级别</th><th>弹窗位置X</th><th>弹窗位置Y</th><th>中心位置X</th><th>中心位置Y</th><th>分组</th><shiro:hasPermission name="manager:map:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="mapData">
			<tr>
				<td>${mapData.dm}</td>
				<td><a href="${ctx}/manager/mapData/form?id=${mapData.id}">${mapData.name}</a></td>
				<td>${mapData.zoom}</td>
				<td>${mapData.lx}</td>
				<td>${mapData.ly}</td>
				<td>${mapData.cx}</td>
				<td>${mapData.cy}</td>
				<td>${fns:getDictLabel(mapData.group, 'geo_group', '无')}</td>
				<shiro:hasPermission name="manager:map:edit"><td>
    				<a href="${ctx}/manager/mapData/form?id=${mapData.id}">修改</a>
					<a href="${ctx}/manager/mapData/delete?id=${mapData.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>