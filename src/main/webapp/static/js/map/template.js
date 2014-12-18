var c = {
		Resize : {
			width : 0,
			height : 0,
			minWidth : 0,
			minHeight:0,
			topHeight : 30,
			leftWidth : 0,
			loadWidthHeight : function() {
				c.Resize.height = $(window).height();
				c.Resize.width = $(window).width();
			}
		},
		resizeTemplatePage : function() {
			c.Resize.loadWidthHeight();
			var a = c.Resize.height-c.Resize.topHeight, b = c.Resize.width-c.Resize.leftWidth;
			var frameWidth=b > c.Resize.minWidth ? b-8 : c.Resize.minWidth;
			var frameHeight=a > c.Resize.minHeight ? a-20 : c.Resize.minHeight;
			$("#main iframe").width(frameWidth);
			$("#main iframe").height(frameHeight);
			$("#main iframe").attr("width",frameWidth);
			$("#main iframe").attr("height",frameHeight);
			$("html").css({
						height : c.Resize.height,
						width : c.Resize.width
					});
			//alert("frameWidth:"+frameWidth+";"+"frameHeight:"+frameHeight);
			
		}
};