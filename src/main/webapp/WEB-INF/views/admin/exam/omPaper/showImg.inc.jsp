<%@ page pageEncoding="UTF-8"%>
<style>
.layui-upload-img {
    width: 128px;
    height: 128px;
    margin: 10px;
}
.imgdiv{
    display: inline-block;
    position: relative;
}
</style>
<script type="text/javascript">
//预览图片
function previewImg(_this) { 
    var imgSrc = $(_this).attr("src");
    if(imgSrc){
    	var width=800;
   	    var height=600;
   	    var bi = 1;
   	    getImageWidth(imgSrc,function(w,h){
   	        bi = h/w;
   	        height = width*bi      
   	    });
   	    var imgHtml = '<div><img src="'+ imgSrc +'" style="width:'+width+'px;height:'+height+'px;"/></div>'; 
   	    //弹出层 
   	    parent.layer.open({ 
   	        type: 1, 
   	        shade: 0.8,
   	        area: [(width+10)+'px',(height+40+10*bi)+'px'],
   	        closeBtn:1,
   	        skin:'layui-layer-nobg',
   	        shadeClose:true,//点击外围关闭弹窗 
   	        scrollbar: false,
   	        title: "图片预览", //不显示标题  
   	        content: imgHtml //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
   	    });
    }
}

//获取图片真实宽高
function getImageWidth(url,callback){
    var img = new Image();
    img.src = url;
    // 如果图片被缓存，则直接返回缓存数据
    if(img.complete){
        callback(img.width, img.height);
    }else{
        img.onload = function(){
            callback(img.width, img.height);
        }
    }
}
</script>
