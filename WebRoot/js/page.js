function prePage(){
		//if($("#prePage").val()!=1){
			$("#currPage").val($("#prePage").val())
			$("#pageForm").submit();
		//}
	}
	function nextPage(){
		//if($("#currPage").val()!=$("#totalPage").val()){
			$("#currPage").val($("#nextPage").val())
			$("#pageForm").submit();
		//}
	}
	function firstPage(){
		//if($("#currPage").val()!=1){
			$("#currPage").val(1)
			$("#pageForm").submit();
		//}
	}
	function finalPage(){
		$("#currPage").val($("#totalPage").val())
		$("#pageForm").submit();
	}