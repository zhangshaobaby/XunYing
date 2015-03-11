function subForm(){	
	var boolean = true;	
	$("input[type='text']").each(function(index,item){
		if(!$(item).hasClass("allowNull")){
			if($(item).val()==""){
				$(item).focus();
				$(item).css("border","1px solid red")
				boolean = false;
			}else{
				$(item).css("border","1px solid #999999");
			}
		}
		if($(item).hasClass("double_vali")){
			if($(item).hasClass("allowNull")&&$(item).val()==""){
			
			}else if(!/^(\d+\.)?\d+$/.test($(item).val())){
				$(item).focus();
				$(item).css("border","1px solid red")
				boolean = false;
			}
		}
		if($(item).hasClass("int_vali")){
			if($(item).hasClass("allowNull")&&$(item).val()==""){
			
			}else if(/^[0-9]*(\.[0]+)?$/.test($(item).val())){
				$(item).val($(item).val().replace(/\.0+/g,""));
			}else{
				$(item).focus();
				$(item).css("border","1px solid red")
				boolean = false;
			}
		}
	});
	return boolean;
}