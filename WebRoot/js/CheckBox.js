// 得到用户选中的ID(字符串用,隔开)
function getCheckIds(name) {
    var checkIds = "";
    var eles = document.getElementsByName(name);
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

//得到所有选中的值
function getCheckedValue(name) {
    var ids = getCheckIds(name);
    if (ids == "" || ids.indexOf(",") == 0) {
        alert("请选择一条或多条数据进行操作");
        return;
    }
    return ids;
}
//得到单个选中的值
function getSingleCheckedValue(name) {
    var ids = getCheckIds(name);
    if (ids == "" || ids.indexOf(",") > 0) {
        alert("请选择一条数据进行操作");
        return;
    }
    return ids;
}
var istrue=false;
	//all select
	function checkAll(name){
		var tree=document.getElementsByName(name);
		var i;
		if(!istrue){
				for(i=0;i<tree.length;i++){
					tree[i].checked=true;
					istrue=true;
				}
		}else{
			for(i=0;i<tree.length;i++){
				tree[i].checked=false;
				istrue=false;
			}
		}
	}