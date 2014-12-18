<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html xmlns:v="urn:schemas-microsoft-com:vml"
	xmlns:svg="http://www.w3.org/2000/svg"
	xmlns:xlink="http://www.w3.org/1999/xlink"
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${fns:getConfig('productName')}</title>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="doc"></div>
	<div id="main">
		<iframe id="mainFrame" name="mainFrame" src="${ctxFront}/map/mapInit"
			scrolling="auto" frameborder="no" width="100%" height="550"></iframe>
	</div>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>
