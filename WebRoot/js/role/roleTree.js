Ext.onReady(function(){ 
    var tree = new Ext.tree.TreePanel({   
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
            dataUrl:'auth/role/getRoleJsonTreeNode.action?id='+oper            
        })
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