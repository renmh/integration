<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="webDefault"/>
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 7px 0 10px;}
		#header {margin:0 0 5px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} 
	</style>
</head>
<body>
	<div id="main">
		<%@ include file="mapHeader.jsp"%>
		<div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="right">
					<iframe id="mainFrame" name="mainFrame" src="${ctxFront}/map/mapInit" scrolling="no" frameborder="no"
						width="100%" height="550"></iframe>
				</div>
			</div>
			<div id="footer" class="row-fluid">
				Copyright &copy; 2012-${fns:getConfig('copyrightYear')}
				${fns:getConfig('productName')} - Powered By <a
					href="#" target="_blank">长天科技集团</a>
				${fns:getConfig('version')}
			</div>
		</div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = "160"; // 左侧窗口大小
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs=getWindowSize().toString().split(",");
			$("#menuFrame, #mainFrame, #openClose").height((strs[0]<minHeight?minHeight:strs[0])-$("#header").height()-$("#footer").height()-32);
			if(strs[1]<minWidth){
				$("#main").css("width",minWidth-10);
				$("html,body").css({"overflow":"auto","overflow-x":"auto","overflow-y":"auto"});
			}else{
				$("#main").css("width","auto");
				$("html,body").css({"overflow":"hidden","overflow-x":"hidden","overflow-y":"hidden"});
			}
			$("#right").width($("#content").width());
			$("#mainFrame").width($("#content").width());
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>