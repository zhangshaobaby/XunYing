//全选or 全清
	function checkAll(obj,checkboxName){
		var objs = document.getElementsByName(checkboxName);
		for(i=0;i<objs.length;i++){
			objs[i].checked=obj.checked;
		}
	}
	 function hiddenDiv(id){
		document.getElementById(id).style.display = "none";
	}
	// 得到用户选中的ID(字符串用,隔开)
	function getCheckIds(checkboxName) {
		var checkIds = "";
		var eles = document.getElementsByName(checkboxName);
		for (var i = 0; i < eles.length; i++) {
			if (eles[i].checked) {
				if (checkIds == "") {
					checkIds = checkIds + eles[i].value;
				} else {
					checkIds = checkIds + "," + eles[i].value;
				}
			}
		}
		return checkIds;
	}
	//上一页/下一页 首页/尾页
	function nextPage(url,currPage) {
	if(url.indexOf("?")>0){
	 url += "&currPage="+currPage;
	 }else{
	 url += "?currPage="+currPage;
	 }
		window.location.href=url;
	}
		//上一页/下一页 首页/尾页 适用于带form参数
	function nextPageToForm(currPage){
	var formobj=document.forms[0];
    if(formobj!=null){
	 	$("<input id='currPage' name='currPage'  type='hidden' value=\""+currPage+"\">").appendTo(jQuery(formobj));
	        jQuery(formobj).submit();
	     }
	}
		//上一页/下一页 首页/尾页 适用于带form名称参数
	function nextPageToFormName(currPage,formName){
	var formobj=document.forms[formName];
	if(formobj!=null){
	 var url = jQuery(formobj).attr("action");
	 var data = jQuery(formobj).serialize();
	 if(url.indexOf("?")>0){
	 url += "&currPage="+currPage;
	 }else{
	 url += "?currPage="+currPage;
	 }
	 url=url+"&"+data;
	 createTableData(url);
	}
	}
	//分页查询跳转页面
	function goPage(url,totalPage){
		var goPage = document.getElementById("goPage").value;
		if (goPage != null && goPage != "") {
				if (/^[0-9]+$/.test(goPage)) {
					if(parseInt(goPage) > totalPage){
					goPage = totalPage;
					}
				url +="?currPage="+goPage;
				location.href = url;
			} else {
				alert("请输入正确的页码！");
				document.getElementById("goPage").value = "";
			}
		} else {
			alert("请输入正确的页码！");
		}
	}
	//分页查询跳转页面 适用于带form参数
	function goPageToForm(totalPage){
		var goPage = document.getElementById("goPage").value;
		if (goPage != null && goPage != "") {
				if (/^[0-9]+$/.test(goPage)) {
					if(parseInt(goPage) > parseInt(totalPage)){
					goPage = totalPage;
					}
					var currPage=goPage;
				var formobj=document.forms[0];
	   if(formobj!=null){
	 	$("<input id='currPage' name='currPage'  type='hidden' value=\""+currPage+"\">").appendTo(jQuery(formobj));
	        jQuery(formobj).submit();
	     }
			} else {
				alert("请输入正确的页码！");
				document.getElementById("goPage").value = "";
			}
		} else {
			alert("请输入正确的页码！");
		}
	}
	//分页查询跳转页面 适用于带form名称参数
	function goPageToFormName(totalPage,formName){
		var goPage = document.getElementById(formName+"_goPage").value;
		if (goPage != null && goPage != "") {
				if (goPage ^ [0 - 9] * $) {
					if(parseInt(goPage) > parseInt(totalPage)){
					goPage = totalPage;
					}
					var currPage=goPage;
				var formobj=document.forms[formName];
	   if(formobj!=null){
	    var url = jQuery(formobj).attr("action");
	    var data = jQuery(formobj).serialize();
		if(url.indexOf("?")>0){
	 	 url += "&currPage="+currPage;
	 	}else{
	 		url += "?currPage="+currPage;
	 	}
 			url=url+"&"+data;
	        createTableData(url);
	     }
			} else {
				alert("请输入正确的页码！");
				document.getElementById(formName+"_goPage").value = "";
			}
		} else {
			alert("请输入正确的页码！");
		}
	}
	function bizEdit(url,id){
	if(id!=""){
	url+="?id="+id;
	 }
	  window.open(url);
	}
	function bizDel(url,id){
	 if(id==""){
	  alert("请选择数据");
	  return;
	  }
	  url+="?ids="+id;
	  	jQuery.ajax({type:"POST", url:url, beforeSend:function () {
	}, success:function (result) {
	 if(result==true){
		window.location.reload();
		if(window.parent.frames["treeFrame"]!=null){
		window.parent.frames["treeFrame"].location.reload();
		}
		
	 }else{
	  alert("删除失败");
	 }
		
	}, error:function (result) {
		alert("\u4fdd\u5b58\u5931\u8d25");
	}});
	}
	//del many
	function bizDelAll(url,checkboxName){
	 var ids=getCheckIds(checkboxName);
	 if(ids==""){
	  alert("请选择数据");
	  return;
	  }
	  bizDel(url,ids);
	}
	//save and reload opener
	function submitForm(){
	var url = jQuery("#dataform").attr("action");
	var data = jQuery("#dataform").serialize();
	jQuery.ajax({type:"POST", url:url, data:data, beforeSend:function () {
	}, success:function (result) {
	 if(result=="suceess"){
	    alert("\u4fdd\u5b58\u6210\u529f");
		window.opener.location.reload();
		if(window.opener.parent.frames["treeFrame"]!=null){
		window.opener.parent.frames["treeFrame"].location.reload();
		}
		window.close();
	
	 }else{
	  alert("保存失败");
	 }
		
	}, error:function (result) {
		alert("\u4fdd\u5b58\u5931\u8d25");
	}});
	}
	//设置列表页上的异步分页
function setPageFun(bizPageData,divId,formName){
	var currPage,prePage,nextPage,totalPage,totalRecord,rows;
	if(bizPageData == null){
		currPage = 1;
		prePage = 1;
		nextPage = 1;
		totalPage = 1;
		totalRecord = 0;
		rows = 10;
	}else{
		currPage = bizPageData.currPage;
		prePage = bizPageData.prePage;
		nextPage = bizPageData.nextPage;
		totalPage = bizPageData.totalPage;
		totalRecord = bizPageData.totalRecord;
		rows = bizPageData.rows;
	}
	var str = "<span>共"+totalRecord+"条记录</span>&nbsp;&nbsp;";
	if(totalRecord == 0){
		str += "<span>首页</span>&nbsp;&nbsp;"
		str += "<span>上一页</span>&nbsp;";
		str += ""+currPage+"/"+totalPage+"&nbsp;";
		str += "<span>下一页</span>&nbsp;&nbsp;";
		str += "<span>末页</span>&nbsp;&nbsp;";
	}else{
		if(currPage == "1"){
			str += "<span>首页</span>&nbsp;&nbsp;"
			str += "<span>上一页</span>&nbsp;";
		}else{
			str += "<a style='text-decoration: none' href='javascript:nextPageToFormName(1,\"" + formName + "\")'>首页</a>&nbsp;&nbsp;";
			str += "<a style='text-decoration: none' href='javascript:nextPageToFormName(\"" + prePage + "\",\"" + formName + "\")'>上一页</a>&nbsp;";
		}
		str += ""+currPage+"/"+totalPage+"&nbsp;";
		if(currPage == totalPage){
			str += "<span>下一页</span>&nbsp;&nbsp;";
			str += "<span>末页</span>&nbsp;&nbsp;";
		}else{
			str += "<a style='text-decoration: none' href='javascript:nextPageToFormName(\"" + nextPage + "\",\"" + formName + "\")'>下一页</a>&nbsp;&nbsp;";
			str += "<a style='text-decoration: none' href='javascript:nextPageToFormName(\"" + totalPage + "\",\"" + formName + "\")'>末页</a>&nbsp;&nbsp;";
		}
		var goPageText = formName+"_goPage";
		str += "<span>第 <input type='text' size='2' id="+goPageText+"  /> 页 <a href='javascript:goPageToFormName(\"" + totalPage + "\",\"" + formName + "\")'>跳转</a></span>";
	}
	 $(str).appendTo($("#"+divId));
}
	
	