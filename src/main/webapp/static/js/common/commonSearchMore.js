//更多查询条件
function showMoreInput(t_this){
	var title = $(t_this).html();
	if(title=='更 多'){
		$(".moreSearch").show();
		$(t_this).html('收 起');
	}else{
		$(".moreSearch").hide();
		$(t_this).html('更 多');
		$(".moreSearch input").val("");
		$(".moreSearch select").val(" ");
		form.render('select');
	}
}


$(function(){
	document.onkeydown=keyDownSearch;
})

//按enter自动查询
function keyDownSearch(e) {
    // 兼容FF和IE和Opera
    var theEvent = e || window.event;
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
    	reloadTable();
        return false;
    }
    return true;
}
