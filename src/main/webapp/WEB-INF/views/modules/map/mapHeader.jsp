<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div id="header" class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="brand">${fns:getConfig('productName')}</div>
		<div class="nav-collapse">
			<ul class="nav pull-right">
				<li id="themeSwitch" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					title="主题切换"><i class="icon-th-large"></i> </a>
					<ul class="dropdown-menu">
						<c:forEach items="${fns:getDictList('theme')}" var="dict">
							<li><a href="#"
								onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a>
							</li>
						</c:forEach>
					</ul> <!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
				</li>
				<shiro:user>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" title="个人信息">您好, <shiro:principal
								property="name" /> </a>
						<ul class="dropdown-menu">
							<li><a href="${ctxFront}/user/info" target="mainFrame"><i
									class="icon-user"></i>&nbsp; 个人信息</a>
							</li>
							<li><a href="${ctxFront}/user/modifyPwd" target="mainFrame"><i
									class="icon-lock"></i>&nbsp; 修改密码</a>
							</li>
						</ul></li>
					<li><a href="${ctxFront}/user/logout" title="退出登录">退出</a>
					</li>
				</shiro:user>
				<shiro:guest>
					<li><a href="#" title="欢迎您">欢迎您！</a></li>
					<li><a href="${ctxFront}/user/form" title="注册" target="mainFrame">注册</a></li>
					<li><a href="${ctx}/login" title="登录">登录</a></li>
					</li>
				</shiro:guest>
				<li>&nbsp;</li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>
