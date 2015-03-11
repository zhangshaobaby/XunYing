var tree;
Ext.onReady(function(){ 
    tree = new Ext.tree.TreePanel({   
        useArrows: true,
        id:"treeId",
	    el:"tree-div",  
        autoScroll: true,
        animate: true,
        enableDD:false,// 是否支持拖放 
        bodyStyle:'background-color:transparent' ,
        containerScroll: true,
        border: false,
        onlyLeafCheckable: false,//对树所有结点都可选
        loader: new Ext.tree.TreeLoader({
            dataUrl:'auth/menu/getMenuJsonTreeCheckedNode.action'            
        })
    });
    tree.on('beforeload',function(node){
    if(node.id=="-1"){
       tree.loader.dataUrl = "auth/menu/getMenuJsonTreeCheckedNode.action?roleid="+roleid;
    }else{
     tree.loader.dataUrl = "auth/menu/getMenuJsonTreeCheckedNode.action?roleid="+roleid+"&id="+node.id;
    }
        });
        //节点选中事件
      tree.on('checkChange',function(node){
     var nodeChecked=node.attributes.checked;
      var data="menuid="+node.id+"&"+"roleid="+roleid;
      var url="";
      if(nodeChecked){
      //保存
        url ="auth/roleMenu/saveRoleMenu";
      }else{
      //删除
        url="auth/roleMenu/delRoleMenu"; 
      }
       sendPostMethod(url,data);
       casecadeParentAndChilds(node,nodeChecked);
       });
     var root = new Ext.tree.AsyncTreeNode({
        text: '菜单管理',
        id:'-1'
    });
    tree.setRootNode(root);
	//默认展开第一层级
	tree.getRootNode().expand(false,true);
    tree.render();
});
//级联操作子节点
function  casecadeChilds(node,state){
//是否具有子节点 级联操作
  if (node.hasChildNodes()) {
        for (var j = 0; j < node.childNodes.length; j++) {
           node.childNodes[j].ui.toggleCheck(state);
           node.childNodes[j].attributes.checked=state;
      var data="menuid="+node.childNodes[j].id+"&"+"roleid="+roleid;
      var url="";
      if(state){
      //保存
        url ="auth/roleMenu/saveRoleMenu";
      }else{
      //删除
        url="auth/roleMenu/delRoleMenu"; 
      }
      sendPostMethod(url,data);
      casecadeChilds(node.childNodes[j],state);      
  }
}
}
//级联操作父对象
function  casecadeParent(node,state){
//是否具有父节点 级联操作
  var parentNode=node.parentNode;
  if(parentNode!=null&&parentNode.id!="-1"){
   //如果子节点选中 则父节点选中 
  if(state){
    parentNode.ui.toggleCheck(state);
   parentNode.attributes.checked=state;
        var data="menuid="+parentNode.id+"&"+"roleid="+roleid;
      var url="";
      if(state){
      //保存
        url ="auth/roleMenu/saveRoleMenu";
      }else{
      //删除
        url="auth/roleMenu/delRoleMenu"; 
      }
      sendPostMethod(url,data);
      casecadeParent(parentNode,state);
  }
  }
}
//级联操作父子节点
function casecadeParentAndChilds(node,nodeChecked)
 {
     tree.suspendEvents(); //暂停所有监听事件的执行
     //级联选中父节点
       casecadeParent(node,nodeChecked);
       //级联选中子节点
       casecadeChilds(node,nodeChecked);
     tree.resumeEvents();  //重新开始所有监听事件的执行   
}

//发送ajax请求
function sendPostMethod(url,data){
	jQuery.ajax({type:"POST", url:url, data:data, beforeSend:function () {
	}, success:function (result) {
	 if(result=="suceess"){
	   // alert("已保存");
	 }else{
	 // alert("保存失败");
	 }	
	}, error:function (result) {
		//alert("保存失败");
	}});
}
