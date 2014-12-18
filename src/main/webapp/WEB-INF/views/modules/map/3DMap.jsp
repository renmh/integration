<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html xmlns:v="urn:schemas-microsoft-com:vml"
	xmlns:svg="http://www.w3.org/2000/svg"
	xmlns:xlink="http://www.w3.org/1999/xlink"
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>区域管理</title>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/js/map/TileMap.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/map/11,5.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/map/map.js"></script>
<STYLE>
v\:* {
	behavior: url(#default#VML);
}
</STYLE>
</head>
<body style="overflow:hidden;">
	<div id="MAP_Layer_BoxTop"
		style="position:absolute;top:0px;left:0px;z-index:300;"></div>
	<img id="MapSpotImage"
		src="${ctxStatic}/js/default/images/HotAreaBackground.gif"
		usemap="#MapArea"
		style="-webkit-user-select: none; position: absolute; top: -200px; left: -200px; z-index: 400; opacity: 0;">
</body>
</html>