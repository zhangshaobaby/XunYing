
jQuery(document).ready(function () {
	getMenu("menuLeft", "");
});
function getMenu(divid, parentMenuid) {
	url = base + "/auth/menu/getIndexMenu";
	if (parentMenuid != "") {
		url += "?id=" + parentMenuid;
	}
	jQuery.ajax({type:"POST", url:url, success:function (result) {
		if (result != null && result != "[]") {
			result = eval(result);
			var str = "";
			if (result.length > 0) {
				var ul = jQuery("<ul></ul>").appendTo(jQuery("#" + divid));
				jQuery.each(result, function (i, item) {
					var menuClass = item.menuClass;
					if (i == 0) {
						menuClass += "_h";
					}
					var li = jQuery("<li class=\"" + menuClass + "\"></li>").appendTo(jQuery(ul));
					var hrefEl ="";
					if(item.url.indexOf("?")>0){
					 hrefEl=jQuery("<a href='" + base + item.url + "&random="+ Math.random()+"' target='framecont'>" + item.menuLabel + "</a>").appendTo(jQuery(li));
					}else{
					 hrefEl=jQuery("<a href='" + base + item.url + "?random="+ Math.random()+"' target='framecont'>" + item.menuLabel + "</a>").appendTo(jQuery(li));
					}
					
					
				});
				menuclick  ()
			}
		}
	}});
}

