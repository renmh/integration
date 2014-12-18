var spots=[];
function AppendSpots(){
	if(arguments.length>0){
		for(var e in arguments[0]){
			spots.push(arguments[0][e]);
		}
	}
}
var TileMap={
	VERSION_NUMBER: "Beta 1.0",
	_getScriptLocation: function() {
	    for (var a = /(^|(.*?\/))(TileMap[^\/]*?\.js)(\?|$)/,b = document.getElementsByTagName("script"), c, d = "", e = 0, f = b.length; e < f; e++)
	    { 
	    	c = b[e].getAttribute("src");
	    	if (c = c.match(a)) {
		        d = c[1];
		        return d;
	    	}
	    }
	} 
}; 
TileMap.Class = function() {
  var a = arguments.length,
  b = arguments[0],
  c = arguments[a - 1],
  d = "function" == typeof c.initialize ? c.initialize: function() {
      b.prototype.initialize.apply(this, arguments);
  };
  1 < a ? (a = [d, b].concat(Array.prototype.slice.call(arguments).slice(1, a - 1), c), TileMap.inherit.apply(null, a)) : d.prototype = c;
  return d;
};
TileMap.inherit = function(a, b) {
  var c = function() {};
  c.prototype = b.prototype;
  a.prototype = new c;
  var d, e, c = 2;
  for (d = arguments.length; c < d; c++) e = arguments[c],
  "function" === typeof e && (e = e.prototype),
  TileMap.Util.extend(a.prototype, e)
};
TileMap.Util = TileMap.Util || {};
TileMap.Util.extend = function(a, b) {
  a = a || {};
  if (b) {
      for (var c in b) {
          var d = b[c];
          void 0 !== d && (a[c] = d)
      }
      "function" == typeof window.Event && b instanceof window.Event || (!b.hasOwnProperty || !b.hasOwnProperty("toString")) || (a.toString = b.toString)
  }
  return a;
};
TileMap.Util.DEFAULT_PRECISION = 14;
TileMap.Util.toFloat = function(a, b) {
    null == b && (b = TileMap.Util.DEFAULT_PRECISION);
    "number" !== typeof a && (a = parseFloat(a));
    return 0 === b ? a: parseFloat(a.toPrecision(b))
};
TileMap.Util.getImagesLocation = function() {
    return TileMap.ImgPath || TileMap._getScriptLocation() + "theme/default/img/"
};
TileMap.Util.getImageLocation = function(a) {
    return TileMap.Util.getImagesLocation() + a
};
TileMap.Util.DEFAULT_PRECISION = 14;
TileMap.Util.toFloat = function(a, b) {
    null == b && (b = TileMap.Util.DEFAULT_PRECISION);
    "number" !== typeof a && (a = parseFloat(a));
    return 0 === b ? a: parseFloat(a.toPrecision(b))
};
TileMap.Util.isArray = function(a) {
    return "[object Array]" === Object.prototype.toString.call(a)
};
TileMap.Util.getBrowserVersion = function() {
   if(navigator.userAgent.indexOf("MSIE")>0) {  
        return "MSIE";  
   }  
   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){  
        return "Firefox";  
   }  
   if(isSafari=navigator.userAgent.indexOf("Safari")>0) {  
        return "Safari";  
   }   
   if(isCamino=navigator.userAgent.indexOf("Camino")>0){  
        return "Camino";  
   }  
   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){  
        return "Gecko";  
   }  
   return "";
};
TileMap.Util.getElement = function() {
    for (var a = [], b = 0, c = arguments.length; b < c; b++) {
        var d = arguments[b];
        "string" == typeof d && (d = document.getElementById(d));
        if (1 == arguments.length) return d;
        a.push(d)
    }
    return a
};
TileMap.Util.dotless = /\./g;
TileMap.Util.modifyDOMElement = function(a, b, c, d, e, f, g, h,i) {
    b && (a.id = b.replace(TileMap.Util.dotless, "_"));
    c && (a.style.left = c.x + "px", a.style.top = c.y + "px");
    d && (a.style.width = d.w + "px", a.style.height = d.h + "px");
    e && (a.style.position = e);
    f && (a.style.border = f);
    g && (a.style.overflow = g);
    i && (a.style.zIndex = i);
    0 <= parseFloat(h) && 1 > parseFloat(h) ? (a.style.filter = "alpha(opacity=" + 100 * h + ")", a.style.opacity = h) : 1 == parseFloat(h) && (a.style.filter = "", a.style.opacity = "")
};
TileMap.Util.createAlphaImageDiv = function(a, b, c, d, e, f, g, h, k) {
    var l = TileMap.Util.createDiv();
    k = TileMap.Util.createImage(null, null, null, null, null, null, null, k);
    k.className = "olAlphaImg";
    l.appendChild(k);
    TileMap.Util.modifyAlphaImageDiv(l, a, b, c, d, e, f, g, h);
    return l
};
TileMap.Util.createDiv = function(a, b, c, d, e, f, g, h) {
    var k = document.createElement("div");
    d && (k.style.backgroundImage = "url(" + d + ")");
    a || (a = TileMap.Util.createUniqueID("TMLayersDiv"));
    e || (e = "absolute");
    TileMap.Util.modifyDOMElement(k, a, b, c, e, f, g, h);
    return k
};
TileMap.Util.createImage = function(a, b, c, d, e, f, g, h) {
    var k = document.createElement("img");
    a || (a = TileMap.Util.createUniqueID("TileMapDiv"));
    e || (e = "relative");
    TileMap.Util.modifyDOMElement(k, a, b, c, e, f, null, g);
    h && (k.style.display = "none", b = function() {
        k.style.display = "";
        TileMap.Event.stopObservingElement(k)
    },
    TileMap.Event.observe(k, "load", b), TileMap.Event.observe(k, "error", b));
    k.style.alt = a;
    k.galleryImg = "no";
    d && (k.src = d);
    return k
};
TileMap.Util.alphaHackNeeded = null;
TileMap.Util.alphaHack = function() {
    if (null == TileMap.Util.alphaHackNeeded) {
        var a = navigator.appVersion.split("MSIE"),
        a = parseFloat(a[1]),
        b = !1;
        try {
            b = !!document.body.filters
        } catch(c) {}
        TileMap.Util.alphaHackNeeded = b && 5.5 <= a && 7 > a
    }
    return TileMap.Util.alphaHackNeeded
};
TileMap.Util.modifyAlphaImageDiv = function(a, b, c, d, e, f, g, h, k) {
	TileMap.Util.modifyDOMElement(a, b, c, d, f, null, null, k,500);
    b = a.childNodes[0];
    e && (b.src = e);
    TileMap.Util.modifyDOMElement(b, a.id + "_innerImage", null, d, "relative", g);
    TileMap.Util.alphaHack() && ("none" != a.style.display && (a.style.display = "inline-block"), null == h && (h = "scale"), a.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + b.src + "', sizingMethod='" + h + "')", 0 <= parseFloat(a.style.opacity) && 1 > parseFloat(a.style.opacity) && (a.style.filter += " alpha(opacity=" + 100 * a.style.opacity + ")"), b.style.filter = "alpha(opacity=0)")
};
TileMap.Util.lastSeqID = 0;
TileMap.Util.createUniqueID = function(a) {
    a = null == a ? "id_": a.replace(TileMap.Util.dotless, "_");
    TileMap.Util.lastSeqID += 1;
    return a + TileMap.Util.lastSeqID
};
TileMap.Util.showMouseTips=function(e,tips){
	var left=e.clientX+"px";
	var top=e.clientY+23+"px";
	var width=tips.length*13;
	if(!$("#areaTips").html()){
		var tipsDiv="<div id=\"areaTips\" style=\"position:absolute;z-index:999999;top:"+top+";left:"+left+";width:"+width+"px\">";
		var tipsContent="<div style=\"font-size:12px;height:16px;white-space:nowrap;boder:1px solid #333333;padding:2px;background-color:#ffffcc\">"+tips+"</div>";;
		$("body").append(tipsDiv+tipsContent+"</div>");
	}else{
		$("#areaTips").css({
			left:left,
			top:top
		});
	}
};
Array.prototype.in_array = function(e)
{
	for(var i=0;i<this.length;i++){
		if(this[i]==e){
			return true;
		}
	}
	return false;
};
TileMap.Bounds = TileMap.Class({
	left: null,
    bottom: null,
    right: null,
    top: null,
    centerLonLat: null,
    initialize: function(a, b, c, d) {
    	 null != a && (this.left = TileMap.Util.toFloat(a));
         null != b && (this.bottom = TileMap.Util.toFloat(b));
         null != c && (this.right = TileMap.Util.toFloat(c));
         null != d && (this.top = TileMap.Util.toFloat(d))
    },
    getWidth:function(){
    	return Math.round(this.right-this.left);
    },
    getHeight:function(){
    	return Math.round(this.bottom-this.top);
    },
    getCenter:function(){
    	return {x:Math.abs(this.left)+Math.ceil(this.getWidth()/2),y:Math.abs(this.top)+Math.ceil(this.getHeight()/2)};
    }
});
TileMap.Map = TileMap.Class({
	id: null,
	layers: null,
	layerids:null,
	center: null,
	zoom: 0,
	numZoomLevels: 16,
	size: null,
	layerContainerOriginPx: null,
	unitScale:100,// 缩放级别为0时,100像素代表一个单位
	center:{lon:100,lat:90},// 绝对单位
	div:null,
	popups:[],
	markers:[],
	theme:null,
	mapAreaPg:null,
	mapArea:null,
	areaObj:null,
	baseLayer:null,
	initialize: function(a, b) {
		this.id=a;
		this.layers = [];
		this.layerids=[];
		this.zoom=b.zoom;
		this.theme=TileMap._getScriptLocation() + "theme/default/style.css";
		if(b.center){
			this.center=b.center;
		}
		this.div=document.getElementById(this.id);
		var parentframe=$("#main iframe",parent.document);
		// alert("parentframeWidth:"+$(parentframe).width()+";"+"parentframeHeight:"+$(parentframe).height());
		$(this.div).css({
			height : $(parentframe).height(),
			width : $(parentframe).width()
		});
		// this.mapAreaPg=document.createElement("img");
    	// this.mapAreaPg.setAttribute("id","MapSpotImage");
    	// this.mapAreaPg.setAttribute("src",
		// "js/default/images/HotAreaBackground.gif");
    	// this.mapAreaPg.setAttribute("usemap","#MapArea");
    	// this.mapAreaPg.setAttribute("style","-webkit-user-select: none;
		// position: absolute; top: -200px; left: -200px; z-index: 400; opacity:
		// 0;width:"+mapAreaPgWidth+";height:"+mapAreaPgHeight+";");
		this.mapAreaPg=document.getElementById("MapSpotImage");
		this.mapAreaPg.style.width=$(window).width()+400+"px";
        this.mapAreaPg.style.height=$(window).height()+400+"px";
    	document.getElementsByTagName("body")[0].appendChild(this.mapAreaPg);
    	this.mapArea=document.createElement("map");
    	this.mapArea.setAttribute("id","MapArea");
    	this.mapArea.setAttribute("name","MapArea");
    	document.getElementsByTagName("body")[0].appendChild(this.mapArea);
    	
		// 为map 加上可拖动的元素
		var mapDragListener=new TileMap.Drag.Map(this.id,this,this.mapAreaPg);
		mapDragListener.drag();
		if (this.theme) {
           var c=document.createElement("link");
           c.setAttribute("rel", "stylesheet");
           c.setAttribute("type", "text/css");
           c.setAttribute("href", this.theme);
           document.getElementsByTagName("head")[0].appendChild(c);
        }
		
	},
	getCenter: function() {
		var pxCenter=this.getBounds().getCenter();
		var lon=pxCenter.x/this.getUnitScale();
		var lat=pxCenter.y/this.getUnitScale();
		this.center={lon:lon,lat:lat};
		// alert("x:"+pxCenter.x+";y:"+pxCenter.y+";lon:"+lon+";lat:"+lat);
		return this.center;
	},
	getPxCenter:function(){
		return {
			x:this.center.lon*this.getUnitScale(),
			y:this.center.lat*this.getUnitScale()
			};
	},
	getViewPortPxFromLonLat:function(a){
		return {
				x:a.lon*this.getUnitScale()+"px",
				y:a.lat*this.getUnitScale()+"px"
			};
	},getUnitScale:function(){
		var zoomScale={0:8,1:4,2:2,3:1};
		return zoomScale[this.zoom]/8;
	},getCoordsScale:function(){
		return this.getUnitScale();
	},addLayer: function(a) {
		this.layers.push(a);
		if(this.layers.length==1){
			this.baseLayer=a;
		}
	},
	// 获得边界
	getBounds:function(){
		var left=$(this.div).offset().left;
		var top=$(this.div).offset().top;
		var bottom=$(this.div).height()+top;
		var right=$(this.div).width()+left;
		//alert($(this.div).height()+"-"+$(this.div).width()+"-"+$(document).height()+"-"+$(document).width());
		var bounds=new TileMap.Bounds(left,bottom,right,top);
		return bounds;
	},
	getMapLeftTop:function(){
		var bounds=this.getBounds();
		// alert("width:"+$(this.div).width()+";"+"height:"+$(this.div).height());
		var pxCenter=this.getPxCenter();
		var left=pxCenter.x-Math.ceil(bounds.getWidth()/2);
		var top=pxCenter.y-Math.ceil(bounds.getHeight()/2);
		//alert("x:"+pxCenter.x+";y:"+pxCenter.y);
		//alert("left:"+left+";top:"+top);
		return {left:left,top:top};
	},
	setCenter:function(c){
		this.center=c;
		var mapPxLeftTop=this.getMapLeftTop();
		$(this.div).css({
			left:-mapPxLeftTop.left,
			top:-mapPxLeftTop.top
		});
		// $(this.mapAreaPg).css({
		// left:-mapPxLeftTop.left,
		// top:-mapPxLeftTop.top
		// });
		for(var a=0;a<this.layers.length;a++){
			this.layers[a].draw(this);
		}
		this.refreshAll();
	},
	getZoom:function(){
		return this.zoom;
	},
	zoomTo:function(z){
		var center=this.getCenter();
		this.zoom=z;
		this.setCenter(center);
	},
	zoomIn: function() {
        this.zoomTo(this.getZoom() + 1);
    },
    zoomOut: function() {
        this.zoomTo(this.getZoom() - 1);
    },
    addPopup:function(p,isVisible){
    	if(isVisible){
    		p.isVisible=isVisible;
    	}
    	p.map=this;
    	var lonlat=p.lonlat;
    	// alert("lon"+lonlat.lon+";lat:"+lonlat.lat);
    	var size=p.size;
    	if(isVisible){
    		this.setCenter(lonlat);
    	}
    	var px=this.getViewPortPxFromLonLat(lonlat);
    	$("body").append($(p.div));
    	var left=parseInt(px.x)-p.size.width/2+$(p.map.div).offset().left;
    	var top=parseInt(px.y)-p.size.height-40+$(p.map.div).offset().top;
    	$("#"+p.id).css({left:left,top:top});
    	$("#"+p.id+" .Map_bubble_buttons").bind("click",function(){
			p.destory();
		});
    	this.popups.push(p);
    },
    refreshPopup:function(){
    	for(var i=0;i<this.popups.length;i++){
    		var p=this.popups[i];
    		var lonlat=p.lonlat;
    		var size=p.size;
        	var px=this.getViewPortPxFromLonLat(lonlat);
        	var left=parseInt(px.x)-p.size.width/2+$(p.map.div).offset().left;
        	var top=parseInt(px.y)-p.size.height-40+$(p.map.div).offset().top;
        	$("#"+p.id).css({left:left,top:top});
    	}
    },
    removePopup:function(a){
    	var newArray=[];
    	for(var b=0;b<this.popups.length;b++){
			if(this.popups[b].id!=a.id){
				newArray.push(this.popups[b]);
			}else{
				$("#"+a.id).remove();
			}
		}
    	this.popups=newArray;
    	$("#"+a.id).remove();
    },
    addMarker:function(m){
    	this.markers.push(m);
    	m.map || (m.map = this, this.drawMarker(m));
    	/**
    	m.map=this;
    	var lonlat=m.lonlat;
    	// alert("lon"+lonlat.lon+";lat:"+lonlat.lat);
    	var size=m.icon.size;
    	var px=this.getViewPortPxFromLonLat(m.lonlat);
    	$("body").append($(m.div));
    	var left=parseInt(px.x)-size.width/2+$(m.map.div).offset().left;
    	var top=parseInt(px.y)-size.height-40+$(m.map.div).offset().top;
    	// 添加图片偏移
    	left+=m.icon.offset.x;
    	top+=m.icon.offset.y;
    	$("#"+m.id).css({left:left,top:top});
    	*/
    },
    drawMarker: function(a) {
        var px = this.getViewPortPxFromLonLat(a.lonlat);
        var left=parseInt(px.x)+$(a.map.div).offset().left;
    	var top=parseInt(px.y)+$(a.map.div).offset().top;
        var b={
        		x:left,
				y:top
			};
        null == b ? a.display(!1) : a.isDrawn() ? a.icon && a.icon.moveTo(b) : (a = a.draw(b), document.body.appendChild(a));
    },
    refreshMarker:function(){
    	for(var i=0;i<this.markers.length;i++){
    		var p=this.markers[i];
    		this.drawMarker(p);
    	}
    },
    removeMarker:function(a){
    	var newArray=[];
    	for(var b=0;b<this.markers.length;b++){
			if(this.markers[b].id!=a.id){
				newArray.push(this.markers[b]);
			}else{
				$("#"+a.id).remove();
			}
		}
    	this.markers=newArray;
    },
    refreshAll:function(){
    	this.refreshMarker();
    	this.refreshPopup();
    	this.baseLayer.drawMapArea(this);
    },
    addArea:function(){
    	var bounds=a.getBounds();
    },
	// 获得偏移量
	CLASS_NAME: "TileMap.Map"
});
TileMap.Size = TileMap.Class({
    w: 0,
    h: 0,
    initialize: function(a, b) {
        this.w = parseFloat(a);
        this.h = parseFloat(b)
    },
    toString: function() {
        return "w=" + this.w + ",h=" + this.h
    },
    clone: function() {
        return new TileMap.Size(this.w, this.h)
    },
    equals: function(a) {
        var b = !1;
        null != a && (b = this.w == a.w && this.h == a.h || isNaN(this.w) && isNaN(this.h) && isNaN(a.w) && isNaN(a.h));
        return b
    },
    CLASS_NAME: "TileMap.Size"
});
TileMap.LonLat = TileMap.Class({
    lon: 0,
    lat: 0,
    initialize: function(a, b) {
    	TileMap.Util.isArray(a) && (b = a[1], a = a[0]);
        this.lon = TileMap.Util.toFloat(a);
        this.lat = TileMap.Util.toFloat(b)
    },
    toString: function() {
        return "lon=" + this.lon + ",lat=" + this.lat
    },
    toShortString: function() {
        return this.lon + ", " + this.lat
    },
    clone: function() {
        return new TileMap.LonLat(this.lon, this.lat)
    },
    add: function(a, b) {
        if (null == a || null == b) throw new TypeError("LonLat.add cannot receive null values");
        return new TileMap.LonLat(this.lon + TileMap.Util.toFloat(a), this.lat + TileMap.Util.toFloat(b))
    },
    equals: function(a) {
        var b = !1;
        null != a && (b = this.lon == a.lon && this.lat == a.lat || isNaN(this.lon) && isNaN(this.lat) && isNaN(a.lon) && isNaN(a.lat));
        return b
    },
    wrapDateLine: function(a) {
        var b = this.clone();
        if (a) {
            for (; b.lon < a.left;) b.lon += a.getWidth();
            for (; b.lon > a.right;) b.lon -= a.getWidth()
        }
        return b
    },
    CLASS_NAME: "TileMap.LonLat"
});
TileMap.Pixel = TileMap.Class({
    x: 0,
    y: 0,
    initialize: function(a, b) {
        this.x = parseFloat(a);
        this.y = parseFloat(b)
    },
    toString: function() {
        return "x=" + this.x + ",y=" + this.y
    },
    clone: function() {
        return new TileMap.Pixel(this.x, this.y)
    },
    equals: function(a) {
        var b = !1;
        null != a && (b = this.x == a.x && this.y == a.y || isNaN(this.x) && isNaN(this.y) && isNaN(a.x) && isNaN(a.y));
        return b
    },
    distanceTo: function(a) {
        return Math.sqrt(Math.pow(this.x - a.x, 2) + Math.pow(this.y - a.y, 2))
    },
    add: function(a, b) {
        if (null == a || null == b) throw new TypeError("Pixel.add cannot receive null values");
        return new TileMap.Pixel(this.x + a, this.y + b)
    },
    offset: function(a) {
        var b = this.clone();
        a && (b = this.add(a.x, a.y));
        return b
    },
    CLASS_NAME: "TileMap.Pixel"
});
TileMap.Layer = TileMap.Class({
	id: null,
    name: null,
    div: null,
    initialize: function(a, b) {
    	this.id=a;
    	this.name=a;
    	this.div="<div id=\""+this.id+"\" style=\"position:absolute;top:0px;left:0px;z-index:10\"></div>";
    }
});
TileMap.Layer.HTTPRequest = TileMap.Class(TileMap.Layer, {
	url: null,
    params: null,
    initialize: function(a, b, c, d) {
    	TileMap.Layer.prototype.initialize.apply(this, [a, d]);
        this.url = b;
        this.params || (this.params = TileMap.Util.extend({},
        c));
    }
});
TileMap.Layer.WMS = TileMap.Class(TileMap.Layer.HTTPRequest, {
    fullRequestString:null,
    tiles:[],
	initialize: function(a, b, c, d) {
    	TileMap.Layer.HTTPRequest.prototype.initialize.apply(this, [a, b,c,d]);
        fullRequestString=this.getFullRequestString();
    },
    getFullRequestString:function(){
    	var parameter="";
    	var index=1;
    	for(var o in this.params){
    		if(index==1){
    			parameter=o+"="+this.params[o];
    		}
    		parameter+=o+"="+this.params[o];
    	}
    	return this.url+"?"+parameter;
    },
    draw:function(a,bbox){
    	var layerid=$(this.div).attr("id");
    	if(!a.layerids.in_array(layerid)){
    		a.layerids.push(layerid);
    		$(a.div).append(this.div);// 图层放入地图中
    	}
    	var bounds=bbox||a.getBounds();
    	var zoom=a.zoom;
    	// var center=a.getPxCenter();
    	var para={zoom:zoom,"bbox.left":bounds.left,"bbox.bottom":bounds.bottom,"bbox.right":bounds.right,"bbox.top":bounds.top};
    	// alert(bounds.left+";"+bounds.bottom+";"+bounds.right+";"+bounds.top);
    	var _this=this;
    	jQuery.getJSON(this.url,para,function(json){
    		_this.renderer(json);
    		//_this.drawMapArea(a, bounds);
    	});
    },
    drawMapArea:function(a,bounds){
    	if(!bounds){
    		bounds=a.getBounds();
    	}
    	// TODO 根据参数获得 热点区域
    	$(a.mapArea).empty();
    	for(var d=0;d<spots.length;d++){
    		var coords=spots[d].Coords;
    		if(!coords){
    			continue;
    		}
    		var id=spots[d].ID;
    		var coordsArray=coords[0].split(",");
    		var newCoords="";
    		for(var i=0;i<coordsArray.length;i++){
    			if(i==0){
    				newCoords=parseInt(coordsArray[i])*a.getCoordsScale()-Math.abs(bounds.left)+200;
    			}else{
    				var result="";
	    			if(i%2==0){
	    				result=parseInt(coordsArray[i])*a.getCoordsScale()-Math.abs(bounds.left)+200;
	    			}else{
	    				result=parseInt(coordsArray[i])*a.getCoordsScale()-Math.abs(bounds.top)+200;
	    			}
	    			newCoords+=","+result.toString();
    			}
    		}
    		var area="<area id=\"area"+id+"\" shape=\"poly\" coords=\""+newCoords+"\" href=\"javascript:;\">";
    		$(a.mapArea).append($(area));
    		var _this=this;
    		$("#area"+id).mouseover(function(e){
    			var spot=_this.getCoordsById(this.id);
    			a.areaObj.removeMapArea();
    			a.areaObj.addMapArea(spot.Coords);
    			return false;
    		});
    		$("#area"+id).mouseout(function(){
    			$("#areaTips").remove();
    			a.areaObj.removeMapArea();
    			return false;
    		});
    		$("#area"+id).mousemove(function(e){
    			var spot=_this.getCoordsById(this.id);
    			TileMap.Util.showMouseTips(e,spot.Name);
    			return false;
    		});
    		$("#area"+id).mousedown(function(e){
    			$("#areaTips").remove();
    			a.areaObj.areaMouseDownFn(this);
    			return false;
    		});
    	}
    },
    getCoordsById:function(id){
    	id=id.substr(4,id.length);
    	for(var d=0;d<spots.length;d++){
    		var idT=spots[d].ID;
    		if(id==idT){
    			return spots[d];
    		}
    		
    	}
    },
    renderer:function(data){
    	if (data.success) {
			if (data.images == null || data.images.length == 0) {
				return;
			}
			var _this=this;
			$(data.images).each(
					function(i, d) {
						var imgId = "Map_PicCell_MapPicLayer_" + d.zoom + d.imgUrl.replace(",", "_");
						// 隐藏 其他缩放级别
						$("img[id^='Map_PicCell_MapPicLayer_']").each(function(){
							var idTemp=$(this).attr("id");
							if(idTemp.indexOf("Map_PicCell_MapPicLayer_"+d.zoom)>-1){
								$(this).show();
							}else{
								$(this).hide();
							}
						});
						if(!_this.tiles.in_array(imgId)){
							_this.tiles.push(imgId);
							if(_this.tiles.length>100){// 100张图片
								_this.tiles.shift();
							}
							var img = "<img id=\""
								+ imgId
								+ "\" src=\"getImg.do?imgName="
								+ d.imgUrl
								+ "&zoom="
								+ d.zoom
								+ "\" style=\"position:absolute;width:256px;height:256px;z-index:1;top:"
								+ d.top
								+ "px;left:"
								+ d.left
								+ "px;visibility:visible;\">";
							$("#"+_this.id).append(img);
						}
					});
		}else{
			alert(data.msg||"getJson failure");
		}
    }
});
TileMap.Drag = TileMap.Class({
	disX:null,
	disY:null,
    oDiv: null,
    startLeft:null,
    startTop:null,
    initialize: function(id) {
    	this.oDiv=document.getElementById(id);
    	this.disX=0;
    	this.disY=0;
    	this.oDiv.drag=this;
    	TileMap.Drag.prototype.fnDown=function (ev)
    	{
    		var oEvent=ev||event;
    		this.startLeft=this.oDiv.offsetLeft;
    		this.startTop=this.oDiv.offsetTop;
    		this.disX=oEvent.clientX-this.oDiv.offsetLeft;
    		this.disY=oEvent.clientY-this.oDiv.offsetTop;
    		
    		document.onmousemove=function (ev)
    		{
    			document.getElementById(id).drag.fnMove(ev);
    		};
    		document.onmouseup=function ()
    		{
    			document.getElementById(id).drag.fnUp();
    		};
    	};

    	TileMap.Drag.prototype.fnMove=function (ev)
    	{
    		var oEvent=ev||event;
    		if(!(TileMap.Util.getBrowserVersion()=="Firefox")){
    			oEvent.cancelBubble=true;
    			oEvent.returnValue = false;
    		}
    		this.oDiv.style.left=oEvent.clientX-this.disX+'px';
    		this.oDiv.style.top=oEvent.clientY-this.disY+'px';
    	};
    },
    drag:function(){
    	var _this=this;
    	this.oDiv.onmousedown=function(ev){
    		_this.fnDown(ev);
    		return false;
    	};
    }
});
TileMap.Drag.Map = TileMap.Class(TileMap.Drag, {
	map:null,
	mapAreaPg:null,
	initialize: function(id,m,mapAreaPg){
		TileMap.Drag.prototype.initialize.apply(this, [id]);
		TileMap.Drag.prototype.fnMove=function (ev)
    	{
			var oEvent=ev||event;
    		if(!(TileMap.Util.getBrowserVersion()=="Firefox")){
    			oEvent.cancelBubble=true;
    			oEvent.returnValue = false;
    		}
    		this.oDiv.style.left=oEvent.clientX-this.disX+'px';
    		this.oDiv.style.top=oEvent.clientY-this.disY+'px';
    		// 改变popup的位置
    		m.refreshAll();
    		this.load();
    	};
    	
    	this.map=m;
    	this.mapAreaPg=mapAreaPg;
    	// 绑定滚动事件
    	var _this=this;
    	if(!(TileMap.Util.getBrowserVersion()=="Firefox")){
    		this.mapAreaPg.onmousewheel=function(ev){
        		_this.mouseWhell(ev, m);
        	};
        	this.oDiv.onmousewheel=function(ev){
        		_this.mouseWhell(ev, m);
        	};
    	}else{
    		this.mapAreaPg.addEventListener("DOMMouseScroll",function(ev){
        		_this.mouseWhell(ev, m);
        	});
    	}
    	TileMap.Drag.Map.prototype.fnUp=function ()
    	{   
    		document.onmousemove=null;
    		document.onmouseup=null;
    		this.map.baseLayer.drawMapArea(this.map);
    	};
    },
    mouseWhell:function(ev,m){
		var oEvent=ev||event;
		var delta=0;
		if(oEvent.wheelDelta){// IE/Opera/Chrome
			delta=oEvent.wheelDelta; 
		}else if(oEvent.detail){// Firefox
			delta=oEvent.detail; 
		} 
		var zoom=m.getZoom();
		if(delta > 0){
			if(zoom<3){
				m.zoomIn();
			}
		}else{
			if(zoom>0){
			 m.zoomOut();
			}
		}
		return false;
    },
    load:function(){
    	var bounds=this.map.getBounds();
    	var horizontalMove=Math.abs(bounds.left-this.startLeft);
    	var verticalMove=Math.abs(bounds.top-this.startTop);
		if(horizontalMove>300||verticalMove>300){
			for(var a=0;a<this.map.layers.length;a++){
				this.map.layers[a].draw(this.map,bounds);
			}
		}
    },
    drag:function(){
    	var _this=this;
    	this.mapAreaPg.onmousedown=function(ev){
    		$(_this.map.mapArea).empty();
    		_this.fnDown(ev);
    		return false;
    	};
    	this.oDiv.onmousedown=function(ev){
    		$(_this.map.mapArea).empty();
    		_this.fnDown(ev);
    		return false;
    	};
    }
});
TileMap.Popup = TileMap.Class({
	id:null,
	lonlat: null,
	title:null,
	content:null,
	size:null,
	div:null,
	isVisible:false,
	map:null,
	initialize: function(id,title,content,lonlat,size){
		this.id=id;
		this.lonlat=lonlat;
		this.title=title;
		this.content=content;
		this.size=size;
		var divTop="<div id=\""+this.id+"\" class=\"Map_bubble_pop\" style=\"width: "+this.size.width+"px;\"><div class=\"Map_bubble_top\" style=\"height: 31px\">"
					+"<div class=\"Map_bubble_title\" style=\"height: 30px;width: "+this.size.width+"px;\">"
					+"<p class=\"iw_poi_title\"><a class=\"title\">"+this.title+"</a></p></div>"
					+"<div class=\"Map_bubble_buttons\" style=\"height: 30px;\"><img src=\""+TileMap._getScriptLocation()+"theme/default/img/iw_close.gif\"></div></div>";
		var divCenter="<div class=\"Map_bubble_center\" style=\"height:"+(this.size.height-62)+"px;width: "+this.size.width+"px;\">"
				    +"<div class=\"Map_bubble_content\" style=\"height:"+(this.size.height-62)+"px;width: "+this.size.width+"px;\">"
				    +"<div class=\"iw_poi_conTop\"><div class=\"iw_poi_content\">"+this.content+"</div></div></div></div>";
		var tail="<img class=\"tail\" style=\"width: 58px; height: 31px;left:"+(this.size.width/2-20)+"px;\" src=\""+TileMap._getScriptLocation()+"theme/default/img/iw_tail.png\"></div>";
		this.div=divTop+divCenter+tail;
		if($("#"+this.id).html()){
			this.destory();
		}
	},
	destory:function(){
		this.map.removePopup(this);
	}
});

TileMap.Icon = TileMap.Class({
    url: null,
    size: null,
    offset: null,
    calculateOffset: null,
    imageDiv: null,
    px: null,
    initialize: function(a, b, c, d) {
        this.url = a;
        this.size = b || {
            w: 20,
            h: 20
        };
        this.offset = c || {
            x: -(this.size.w / 2),
            y: -(this.size.h / 2)
        };
        this.calculateOffset = d;
        a = TileMap.Util.createUniqueID("TM_Icon_");
        this.imageDiv = TileMap.Util.createAlphaImageDiv(a)
    },
    destroy: function() {
        this.erase();
        TileMap.Event.stopObservingElement(this.imageDiv.firstChild);
        this.imageDiv.innerHTML = "";
        this.imageDiv = null
    },
    clone: function() {
        return new TileMap.Icon(this.url, this.size, this.offset, this.calculateOffset)
    },
    setSize: function(a) {
        null != a && (this.size = a);
        this.draw()
    },
    setUrl: function(a) {
        null != a && (this.url = a);
        this.draw()
    },
    draw: function(a) {
    	TileMap.Util.modifyAlphaImageDiv(this.imageDiv, null, null, this.size, this.url, "absolute");
        this.moveTo(a);
        return this.imageDiv
    },
    erase: function() {
        null != this.imageDiv && null != this.imageDiv.parentNode && TileMap.Element.remove(this.imageDiv)
    },
    setOpacity: function(a) {
    	TileMap.Util.modifyAlphaImageDiv(this.imageDiv, null, null, null, null, null, null, null, a)
    },
    moveTo: function(a) {
        null != a && (this.px = a);
        null != this.imageDiv && (null == this.px ? this.display(!1) : (this.calculateOffset && (this.offset = this.calculateOffset(this.size)), TileMap.Util.modifyAlphaImageDiv(this.imageDiv, null, {
            x: this.px.x + this.offset.x,
            y: this.px.y + this.offset.y
        })))
    },
    display: function(a) {
        this.imageDiv.style.display = a ? "": "none"
    },
    isDrawn: function() {
        return this.imageDiv && this.imageDiv.parentNode && 11 != this.imageDiv.parentNode.nodeType
    },
    CLASS_NAME: "TileMap.Icon"
});
TileMap.Marker = TileMap.Class({
    icon: null,
    lonlat: null,
    events: null,
    map: null,
    initialize: function(a, b) {
        this.lonlat = a;
        var c = b ? b: TileMap.Marker.defaultIcon();
        null == this.icon ? this.icon = c: (this.icon.url = c.url, this.icon.size = c.size, this.icon.offset = c.offset, this.icon.calculateOffset = c.calculateOffset);
        this.events = new TileMap.Events(this, this.icon.imageDiv)
    },
    destroy: function() {
        this.erase();
        this.map = null;
        this.events.destroy();
        this.events = null;
        null != this.icon && (this.icon.destroy(), this.icon = null)
    },
    draw: function(a) {
        return this.icon.draw(a)
    },
    erase: function() {
        null != this.icon && this.icon.erase()
    },
    moveTo: function(a) {
        null != a && null != this.icon && this.icon.moveTo(a);
        this.lonlat = this.map.getLonLatFromLayerPx(a)
    },
    isDrawn: function() {
        return this.icon && this.icon.isDrawn()
    },
    onScreen: function() {
        var a = !1;
        this.map && (a = this.map.getExtent().containsLonLat(this.lonlat));
        return a
    },
    inflate: function(a) {
        this.icon && this.icon.setSize({
            w: this.icon.size.w * a,
            h: this.icon.size.h * a
        })
    },
    setOpacity: function(a) {
        this.icon.setOpacity(a)
    },
    setUrl: function(a) {
        this.icon.setUrl(a)
    },
    display: function(a) {
        this.icon.display(a)
    },
    CLASS_NAME: "TileMap.Marker"
});
TileMap.Marker.defaultIcon = function() {
    return new TileMap.Icon(TileMap.Util.getImageLocation("poi.png"), {
        w: 21,
        h: 25
    },
    {
        x: -10.5,
        y: -25
    })
};
TileMap.Area = TileMap.Class({
	id:null,
	tipID:null,
	coords:null,
	areaID:null,
	map:null,
	areaMouseDownFn:null,
	initialize: function(id,map){
		this.id=id;
		this.map=map;
		this.tipID="tipID";
		this.areaID="areaID";
		this.map.areaObj=this;
	},
	showArea:function(coords){
		var vmlOrSvg="";
		if(TileMap.Util.getBrowserVersion()=="MSIE"){
			vmlOrSvg="<v:polyline id=\""+this.areaID+"\" points=\""+coords+"\" style=\"Z-INDEX:999999;POSITION:absolute;\" strokedashstyle=\"dashdot\" strokecolor=\"yellow\">" 
				+"<v:fill opacity=\"26214f\" endcap=\"round\"></v:fill>"
				+"</v:polyline>";
		}else{
			vmlOrSvg="<svg overflow=\"visible\" id=\""+this.areaID+"\" style=\"top:0px;left:0px;position:absolute;overflow:visible;z-index:9000;\">"
				+"<polyline points=\""+coords+"\" fill-opacity=\"0.4\" style=\"fill:yellow;stroke:green;stroke-width:1px\"></polyline>"
				+"</svg>";
		}
		// if($("#"+this.areaID)){
		// $("#"+this.areaID).remove();
		// }
		// alert(vmlOrSvg);
		$("#"+this.map.id).append($(vmlOrSvg));
	},
	showTip:function(){
		
	},
	addMapArea:function(coords){
		var coordsArray=coords[0].split(",");
		var newCoords="";
		for(var i=0;i<coordsArray.length;i++){
			if(i==0){
				newCoords=parseInt(coordsArray[i])*(this.map.getCoordsScale());
			}else{
				var result=parseInt(coordsArray[i])*(this.map.getCoordsScale());
    			newCoords+=","+result.toString();
			}
		}
		this.showArea(newCoords);
	},
	removeMapArea:function(){
		$("#"+this.areaID).remove();
	},
	addAllMapArea:function(){
		for(var d=0;d<spots.length;d++){
			var coords=spots[d].Coords;
			if(!coords||coords.length==0){
				continue;
			}
			var coordsArray=coords[0].split(",");
    		var newCoords="";
    		for(var i=0;i<coordsArray.length;i++){
    			if(i==0){
    				newCoords=parseInt(coordsArray[i])*(this.map.getCoordsScale());
    			}else{
    				var result=parseInt(coordsArray[i])*(this.map.getCoordsScale());
	    			newCoords+=","+result.toString();
    			}
    		}
			this.showArea(newCoords);
		}
	},
	addMouseDownFn:function(fn){
		this.areaMouseDownFn=fn;
	}
});
TileMap.Event = {
	    observers: !1,
	    KEY_SPACE: 32,
	    KEY_BACKSPACE: 8,
	    KEY_TAB: 9,
	    KEY_RETURN: 13,
	    KEY_ESC: 27,
	    KEY_LEFT: 37,
	    KEY_UP: 38,
	    KEY_RIGHT: 39,
	    KEY_DOWN: 40,
	    KEY_DELETE: 46,
	    element: function(a) {
	        return a.target || a.srcElement
	    },
	    isSingleTouch: function(a) {
	        return a.touches && 1 == a.touches.length
	    },
	    isMultiTouch: function(a) {
	        return a.touches && 1 < a.touches.length
	    },
	    isLeftClick: function(a) {
	        return a.which && 1 == a.which || a.button && 1 == a.button
	    },
	    isRightClick: function(a) {
	        return a.which && 3 == a.which || a.button && 2 == a.button
	    },
	    stop: function(a, b) {
	        b || TileMap.Event.preventDefault(a);
	        a.stopPropagation ? a.stopPropagation() : a.cancelBubble = !0
	    },
	    preventDefault: function(a) {
	        a.preventDefault ? a.preventDefault() : a.returnValue = !1
	    },
	    findElement: function(a, b) {
	        for (var c = TileMap.Event.element(a); c.parentNode && (!c.tagName || c.tagName.toUpperCase() != b.toUpperCase());) c = c.parentNode;
	        return c
	    },
	    observe: function(a, b, c, d) {
	        a = TileMap.Util.getElement(a);
	        d = d || !1;
	        "keypress" == b && (navigator.appVersion.match(/Konqueror|Safari|KHTML/) || a.attachEvent) && (b = "keydown");
	        this.observers || (this.observers = {});
	        if (!a._eventCacheID) {
	            var e = "eventCacheID_";
	            a.id && (e = a.id + "_" + e);
	            a._eventCacheID = TileMap.Util.createUniqueID(e)
	        }
	        e = a._eventCacheID;
	        this.observers[e] || (this.observers[e] = []);
	        this.observers[e].push({
	            element: a,
	            name: b,
	            observer: c,
	            useCapture: d
	        });
	        a.addEventListener ? a.addEventListener(b, c, d) : a.attachEvent && a.attachEvent("on" + b, c)
	    },
	    stopObservingElement: function(a) {
	        a = TileMap.Util.getElement(a)._eventCacheID;
	        this._removeElementObservers(TileMap.Event.observers[a])
	    },
	    _removeElementObservers: function(a) {
	        if (a) for (var b = a.length - 1; 0 <= b; b--) {
	            var c = a[b];
	            TileMap.Event.stopObserving.apply(this, [c.element, c.name, c.observer, c.useCapture])
	        }
	    },
	    stopObserving: function(a, b, c, d) {
	        d = d || !1;
	        a = TileMap.Util.getElement(a);
	        var e = a._eventCacheID;
	        "keypress" == b && (navigator.appVersion.match(/Konqueror|Safari|KHTML/) || a.detachEvent) && (b = "keydown");
	        var f = !1,
	        g = TileMap.Event.observers[e];
	        if (g) for (var h = 0; ! f && h < g.length;) {
	            var k = g[h];
	            if (k.name == b && k.observer == c && k.useCapture == d) {
	                g.splice(h, 1);
	                0 == g.length && delete TileMap.Event.observers[e];
	                f = !0;
	                break
	            }
	            h++
	        }
	        f && (a.removeEventListener ? a.removeEventListener(b, c, d) : a && a.detachEvent && a.detachEvent("on" + b, c));
	        return f
	    },
	    unloadCache: function() {
	        if (TileMap.Event && TileMap.Event.observers) {
	            for (var a in TileMap.Event.observers) TileMap.Event._removeElementObservers.apply(this, [TileMap.Event.observers[a]]);
	            TileMap.Event.observers = !1
	        }
	    },
	    CLASS_NAME: "TileMap.Event"
	};
	TileMap.Event.observe(window, "unload", TileMap.Event.unloadCache, !1);
	TileMap.Events = TileMap.Class({
	    BROWSER_EVENTS: "mouseover mouseout mousedown mouseup mousemove click dblclick rightclick dblrightclick resize focus blur touchstart touchmove touchend keydown".split(" "),
	    listeners: null,
	    object: null,
	    element: null,
	    eventHandler: null,
	    fallThrough: null,
	    includeXY: !1,
	    extensions: null,
	    extensionCount: null,
	    clearMouseListener: null,
	    initialize: function(a, b, c, d, e) {
	        TileMap.Util.extend(this, e);
	        this.object = a;
	        this.fallThrough = d;
	        this.listeners = {};
	        this.extensions = {};
	        this.extensionCount = {};
	        this._msTouches = [];
	        null != b && this.attachToElement(b)
	    },
	    destroy: function() {
	        for (var a in this.extensions)"boolean" !== typeof this.extensions[a] && this.extensions[a].destroy();
	        this.extensions = null;
	        this.element && (TileMap.Event.stopObservingElement(this.element), this.element.hasScrollEvent && TileMap.Event.stopObserving(window, "scroll", this.clearMouseListener));
	        this.eventHandler = this.fallThrough = this.object = this.listeners = this.element = null
	    },
	    addEventType: function(a) {},
	    attachToElement: function(a) {
	        this.element ? TileMap.Event.stopObservingElement(this.element) : (this.eventHandler = TileMap.Function.bindAsEventListener(this.handleBrowserEvent, this), this.clearMouseListener = TileMap.Function.bind(this.clearMouseCache, this));
	        this.element = a;
	        for (var b = !!window.navigator.msMaxTouchPoints,
	        c, d = 0,
	        e = this.BROWSER_EVENTS.length; d < e; d++) c = this.BROWSER_EVENTS[d],
	        TileMap.Event.observe(a, c, this.eventHandler),
	        b && 0 === c.indexOf("touch") && this.addMsTouchListener(a, c, this.eventHandler);
	        TileMap.Event.observe(a, "dragstart", TileMap.Event.stop)
	    },
	    on: function(a) {
	        for (var b in a)"scope" != b && a.hasOwnProperty(b) && this.register(b, a.scope, a[b])
	    },
	    register: function(a, b, c, d) {
	        a in TileMap.Events && !this.extensions[a] && (this.extensions[a] = new TileMap.Events[a](this));
	        if (null != c) {
	            null == b && (b = this.object);
	            var e = this.listeners[a];
	            e || (e = [], this.listeners[a] = e, this.extensionCount[a] = 0);
	            b = {
	                obj: b,
	                func: c
	            };
	            d ? (e.splice(this.extensionCount[a], 0, b), "object" === typeof d && d.extension && this.extensionCount[a]++) : e.push(b)
	        }
	    },
	    registerPriority: function(a, b, c) {
	        this.register(a, b, c, !0)
	    },
	    un: function(a) {
	        for (var b in a)"scope" != b && a.hasOwnProperty(b) && this.unregister(b, a.scope, a[b])
	    },
	    unregister: function(a, b, c) {
	        null == b && (b = this.object);
	        a = this.listeners[a];
	        if (null != a) for (var d = 0,
	        e = a.length; d < e; d++) if (a[d].obj == b && a[d].func == c) {
	            a.splice(d, 1);
	            break
	        }
	    },
	    remove: function(a) {
	        null != this.listeners[a] && (this.listeners[a] = [])
	    },
	    triggerEvent: function(a, b) {
	        var c = this.listeners[a];
	        if (c && 0 != c.length) {
	            null == b && (b = {});
	            b.object = this.object;
	            b.element = this.element;
	            b.type || (b.type = a);
	            for (var c = c.slice(), d, e = 0, f = c.length; e < f && (d = c[e], d = d.func.apply(d.obj, [b]), void 0 == d || !1 != d); e++);
	            this.fallThrough || TileMap.Event.stop(b, !0);
	            return d
	        }
	    },
	    handleBrowserEvent: function(a) {
	        var b = a.type,
	        c = this.listeners[b];
	        if (c && 0 != c.length) {
	            if ((c = a.touches) && c[0]) {
	                for (var d = 0,
	                e = 0,
	                f = c.length,
	                g, h = 0; h < f; ++h) g = this.getTouchClientXY(c[h]),
	                d += g.clientX,
	                e += g.clientY;
	                a.clientX = d / f;
	                a.clientY = e / f
	            }
	            this.includeXY && (a.xy = this.getMousePosition(a));
	            this.triggerEvent(b, a)
	        }
	    },
	    getTouchClientXY: function(a) {
	        var b = window.olMockWin || window,
	        c = b.pageXOffset,
	        b = b.pageYOffset,
	        d = a.clientX,
	        e = a.clientY;
	        if (0 === a.pageY && Math.floor(e) > Math.floor(a.pageY) || 0 === a.pageX && Math.floor(d) > Math.floor(a.pageX)) d -= c,
	        e -= b;
	        else if (e < a.pageY - b || d < a.pageX - c) d = a.pageX - c,
	        e = a.pageY - b;
	        a.olClientX = d;
	        a.olClientY = e;
	        return {
	            clientX: d,
	            clientY: e
	        }
	    },
	    clearMouseCache: function() {
	        this.element.scrolls = null;
	        this.element.lefttop = null;
	        this.element.offsets = null
	    },
	    getMousePosition: function(a) {
	        this.includeXY ? this.element.hasScrollEvent || (TileMap.Event.observe(window, "scroll", this.clearMouseListener), this.element.hasScrollEvent = !0) : this.clearMouseCache();
	        if (!this.element.scrolls) {
	            var b = TileMap.Util.getViewportElement();
	            this.element.scrolls = [window.pageXOffset || b.scrollLeft, window.pageYOffset || b.scrollTop]
	        }
	        this.element.lefttop || (this.element.lefttop = [document.documentElement.clientLeft || 0, document.documentElement.clientTop || 0]);
	        this.element.offsets || (this.element.offsets = TileMap.Util.pagePosition(this.element));
	        return new TileMap.Pixel(a.clientX + this.element.scrolls[0] - this.element.offsets[0] - this.element.lefttop[0], a.clientY + this.element.scrolls[1] - this.element.offsets[1] - this.element.lefttop[1])
	    },
	    addMsTouchListener: function(a, b, c) {
	        function d(a) {
	            c(TileMap.Util.applyDefaults({
	                stopPropagation: function() {
	                    for (var a = e.length - 1; 0 <= a; --a) e[a].stopPropagation()
	                },
	                preventDefault: function() {
	                    for (var a = e.length - 1; 0 <= a; --a) e[a].preventDefault()
	                },
	                type: b
	            },
	            a))
	        }
	        var e = this._msTouches;
	        switch (b) {
	        case "touchstart":
	            return this.addMsTouchListenerStart(a, b, d);
	        case "touchend":
	            return this.addMsTouchListenerEnd(a, b, d);
	        case "touchmove":
	            return this.addMsTouchListenerMove(a, b, d);
	        default:
	            throw "Unknown touch event type";
	        }
	    },
	    addMsTouchListenerStart: function(a, b, c) {
	        var d = this._msTouches;
	        TileMap.Event.observe(a, "MSPointerDown",
	        function(a) {
	            for (var b = !1,
	            g = 0,
	            h = d.length; g < h; ++g) if (d[g].pointerId == a.pointerId) {
	                b = !0;
	                break
	            }
	            b || d.push(a);
	            a.touches = d.slice();
	            c(a)
	        });
	        TileMap.Event.observe(a, "MSPointerUp",
	        function(a) {
	            for (var b = 0,
	            c = d.length; b < c; ++b) if (d[b].pointerId == a.pointerId) {
	                d.splice(b, 1);
	                break
	            }
	        })
	    },
	    addMsTouchListenerMove: function(a, b, c) {
	        var d = this._msTouches;
	        TileMap.Event.observe(a, "MSPointerMove",
	        function(a) {
	            if (a.pointerType != a.MSPOINTER_TYPE_MOUSE || 0 != a.buttons) if (1 != d.length || d[0].pageX != a.pageX || d[0].pageY != a.pageY) {
	                for (var b = 0,
	                g = d.length; b < g; ++b) if (d[b].pointerId == a.pointerId) {
	                    d[b] = a;
	                    break
	                }
	                a.touches = d.slice();
	                c(a)
	            }
	        })
	    },
	    addMsTouchListenerEnd: function(a, b, c) {
	        var d = this._msTouches;
	        TileMap.Event.observe(a, "MSPointerUp",
	        function(a) {
	            for (var b = 0,
	            g = d.length; b < g; ++b) if (d[b].pointerId == a.pointerId) {
	                d.splice(b, 1);
	                break
	            }
	            a.touches = d.slice();
	            c(a)
	        })
	    },
	    CLASS_NAME: "TileMap.Events"
	});
	TileMap.Function = {
		    bind: function(a, b) {
		        var c = Array.prototype.slice.apply(arguments, [2]);
		        return function() {
		            var d = c.concat(Array.prototype.slice.apply(arguments, [0]));
		            return a.apply(b, d)
		        }
		    },
		    bindAsEventListener: function(a, b) {
		        return function(c) {
		            return a.call(b, c || window.event)
		        }
		    },
		    False: function() {
		        return ! 1
		    },
		    True: function() {
		        return ! 0
		    },
		    Void: function() {}
		};
