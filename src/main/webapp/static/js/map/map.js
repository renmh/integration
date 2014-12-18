//lon : 2816,lat : 1536  zoom:3 东大街位置
var map, baseLayer,area;
function init() {
	var options = {
		units : "unit",
		zoom : 2
	};
	map = new TileMap.Map("MAP_Layer_BoxTop", options);
	baseLayer = new TileMap.Layer.WMS("MapPicLayer", "getMap.do", {}, null);
	map.addLayer(baseLayer);
	map.setCenter({
		lon : 22528,
		lat : 12288
	});
	//划区域
	area=new TileMap.Area("allArea",map);
	area.addMouseDownFn(function(areaObj){
		var content="里可以放个table,参见map.js 代码 new TileMap.Popup"
			+"里可以放个table,参见map.js 代码 new TileMap.Popup";
		var p=new TileMap.Popup("testPopup","测试建筑",content,{lon:22528,lat:12288},{width:400,height:200});
		map.addPopup(p,true);
	});
	map.baseLayer.drawMapArea(map,null);
	//area.addAllMapArea();
	var size=new TileMap.Size(40,40);
	//默认的图片路径
	var defaultImg=TileMap.Util.getImagesLocation();
	//设置偏移量
	var offset=new TileMap.Pixel(0,-40);
	var icon=new TileMap.Icon(defaultImg+"poi.png",size,offset);
	var lonlat=new TileMap.LonLat(22528,12288);
	var marker=new TileMap.Marker(lonlat,icon);
	map.addMarker(marker);
	marker.events.register("click",marker,function(evt){
		alert("您点击了marker");
	});
}
$(document).ready(function() {
	init();
});