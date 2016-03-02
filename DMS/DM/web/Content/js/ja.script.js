var currentFontSize = 4;

function revertStyles(fontsize){
	currentFontSize = fontsize;
	changeFontSize(0);
}

function changeFontSize(sizeDifference){
	//get css font size
	var rule = getRuleByName("body.fs" + (currentFontSize + sizeDifference));
	if (rule){
		document.body.style.fontSize = rule.style.fontSize;
		currentFontSize = currentFontSize + sizeDifference;
		createCookie("FontSize", currentFontSize, 365);
		equalHeightInit();
	}
	return;
};

function getRuleByName(ruleName){
	for (i=0; i<document.styleSheets.length; i++){
		var style = document.styleSheets[i];
		var rules = style.cssRules?style.cssRules:style.rules;
		if (rules){
			for (j = 0; j<rules.length; j++){
				if (rules[j].selectorText.trim().toUpperCase() == ruleName.trim().toUpperCase()){
					return rules[j];
				}
			}
		}
	}
	return null;
}

function setActiveStyleSheet(title) {
	createCookie("ColorCSS", title, 365);
	window.location.reload();
	return;
}

function createCookie(name,value,days) {
  if (days) {
    var date = new Date();
    date.setTime(date.getTime()+(days*24*60*60*1000));
    var expires = "; expires="+date.toGMTString();
  }
  else expires = "";
  document.cookie = name+"="+value+expires+"; path=/";
}

function setScreenType(screentype){
	createCookie("ScreenType", screentype, 365);
	window.location.reload();
	return;
}

String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };

function changeToolHilite(oldtool, newtool) {
	if (oldtool != newtool) {
		if (oldtool) {
			oldtool.src = oldtool.src.replace(/-hilite/,'');
		}
		newtool.src = newtool.src.replace(/.gif$/,'-hilite.gif');
	}
}

//addEvent - attach a function to an event
function jaAddEvent(obj, evType, fn){ 
 if (obj.addEventListener){ 
   obj.addEventListener(evType, fn, false); 
   return true; 
 } else if (obj.attachEvent){ 
   var r = obj.attachEvent("on"+evType, fn); 
   return r; 
 } else { 
   return false; 
 } 
}

function getElem (id) {
	var obj = document.getElementById (id);
	if (!obj) return null;
	var divs = obj.getElementsByTagName ('div');
	if (divs && divs.length >= 1) return divs[divs.length - 1];
	return null;
}

function getFirstDiv (id) {
	var obj = document.getElementById (id);
	if (!obj) return null;
	var divs = obj.getElementsByTagName ('div');
	if (divs && divs.length >= 1) return divs[0];
	return obj;
}

function instr(str, item){
	var arr = str.split(" ");
	for (var i = 0; i < arr.length; i++){
		if (arr[i] == item) return true;
	}
	return false;
}

function equalHeightInit (){
	var ja_content = document.getElementById ('ja-contentwrap');
	var ja_col1 = document.getElementById ('ja-col1');
	var ja_col2 = document.getElementById ('ja-col2');

	var maxh = 0;
	maxh = (ja_content && ja_content.scrollHeight > maxh)? ja_content.scrollHeight:maxh;
	maxh = (ja_col1 && ja_col1.scrollHeight > maxh)? ja_col1.scrollHeight:maxh;
	maxh = (ja_col2 && ja_col2.scrollHeight > maxh)? ja_col2.scrollHeight:maxh;

	if (ja_col1 && ja_col1.scrollHeight < maxh)
	{
		var ja_inners = getElementsByClass ("ja-innerpad", ja_col1, "DIV");	
		if (ja_inners && ja_inners.length)
		{
			ja_inners[0].style.height = maxh -40 + 'px';
		}
	}
}

jaAddEvent (window, 'load', equalHeightInit);
jaAddEvent (window, 'resize', equalHeightInit);

function preloadImages () {
	var imgs = new Array();
	for (var i = 0; i < arguments.length; i++) {
		var imgsrc = arguments[i];
		imgs[i] = new Image();
		imgs[i].src = imgsrc;
	}
}


function getElementsByClass(searchClass,node,tag) {
	var classElements = new Array();
	var j = 0;
	if ( node == null )
		node = document;
	if ( tag == null )
		tag = '*';
	var els = node.getElementsByTagName(tag);
	var elsLen = els.length;
	var pattern = new RegExp('(^|\\s)'+searchClass+'(\\s|$)');
	for (var i = 0; i < elsLen; i++) {
		if ( pattern.test(els[i].className) ) {
			classElements[j] = els[i];
			j++;
		}
	}
	//alert(searchClass + j);
	return classElements;
}


function fixIE() {
	var objs = getElementsByClass ("createdate", null, "TD");
	if (objs) {
		for (var i=0; i<objs.length; i++){
			objs[i].innerHTML = "<span>" + objs[i].innerHTML + "</span>";
		}
	}

	//var objs = getElementsByClass('.*-transbg');
	var objs = $ES('#ja-cssmenu li ul');
	if (!objs) return;
	for(var i=0; i<objs.length;i++){
		makeTransBg(objs[i]);
	}
}

if(isIE6()) jaAddEvent (window, 'load', fixIE);

function makeTransBg(el){
	var obj = $(el);
	if(!obj) return;
	if (obj.tagName == 'IMG') {
		//This is an image
		var bgimg = obj.src;
		obj.setStyle('height',obj.offsetHeight);
		obj.setStyle('width',obj.offsetWidth);
		obj.src = 'images/blank.png';
		obj.setStyle ('visibility', 'visible');
		obj.setStyle('filter', "progid:DXImageTransform.Microsoft.AlphaImageLoader(src="+bgimg+", sizingMethod='scale')");
	}else{
		//Background
		var bgimg = obj.getStyle('backgroundImage');
		var pattern = new RegExp('url\s*[\(]+(.*)[\)]+');
		if ((m = pattern.exec(bgimg))) bgimg = m[1];
		obj.setStyle('background', 'none');
		//if(!obj.getStyle('position'))
		if(obj.getStyle('position')!='absolute' && obj.getStyle('position')!='relative') {
			obj.setStyle('position', 'relative');
		}
		//Get all child
		var childnodes = obj.childNodes;
		for(var j=0;j<childnodes.length;j++){
			if((child = $(childnodes[j]))) {
				if(child.getStyle('position')!='absolute' && child.getStyle('position')!='relative') {
					child.setStyle('position', 'relative');
				}
				child.setStyle('z-index',2);
			}
		}
		//Create background layer:
		var bgdiv = new Element('DIV');
		bgdiv.setStyle('position', 'absolute');
		bgdiv.setStyle('top', 0);
		bgdiv.setStyle('left', 0);
		bgdiv.setStyle('width', obj.offsetWidth - 4);
		bgdiv.setStyle('height', obj.offsetHeight - 4);
		bgdiv.setStyle('z-index', '1');
		bgdiv.setStyle('filter', "progid:DXImageTransform.Microsoft.AlphaImageLoader(src="+bgimg+", sizingMethod='scale')");
		bgdiv.inject(obj);
	}
}

function isIE6() {
	version=0
	if (navigator.appVersion.indexOf("MSIE")!=-1){
		temp=navigator.appVersion.split("MSIE")
		version=parseFloat(temp[1])
	}
	return (version && (version < 7));
}