<%@ page pageEncoding="UTF-8"%>
<style>
.layui-upload-img {
    width: 128px;
    height: 128px;
    margin: 10px;
}
.imgRemark{
	display: inline-block;
	color: #999; 
	padding: 0 5px;
}
.imgdiv{
    display: inline-block;
    position: relative;
}
.deleteImg{
	position: absolute;
    right: 10px;
    height: 16px;
    width: 16px;
    font-style: inherit;
    line-height: 16px;
    font-size: 12px;
    background: red;
    color: #FFFFFF;
    text-align: center;
    border-radius: 50%;
}
</style>
<c:if test="${fntype!='view'||fn:length(imgList)>0}">
<div class="layui-form-item">
    <div class="layui-inline">
    	<input type="hidden" id="imgIds" name="imgIds" value=""/>
    	<input type="hidden" id="imgSrcs" name="imgSrcs" value=""/>
    	<label class="layui-form-label">上传图片：</label>
    	<div class="layui-input-inline">
    		<div class="layui-upload">
    		    <c:if test="${fntype!='view' }">
			  	<button type="button" class="layui-btn" id="uploadImgBtn">选择图片</button>
			  	<div class="imgRemark">
			  	   限制文件大小600KB, 支持文件格式 jpg | png | jpeg | gif
			  	</div> 
			  	</c:if>
			    <div class="layui-upload-list" id="imgList">
			      <c:forEach items="${imgList}" var="imgBean" varStatus="status">
			      	<div class="imgdiv">
			      		<img src="${contextPath }${imgBean.imgSrc }" class="layui-upload-img" onclick="previewImg(this)"
			      		data-imgid="${imgBean.id }" data-imgsrc="${imgBean.imgSrc }"/><c:if test="${fntype!='view' }"><i class="deleteImg" onclick="removeImage(this);">X</i></c:if>
			      	</div>
			      </c:forEach>
			    </div>
			</div
		</div>
	</div>
</div> 
</c:if>
<script type="text/javascript">
var imgIds = [];
var imgSrcs = [];
layui.use('upload', function(){
	  var $ = layui.jquery
	  ,upload = layui.upload;

	  //多图片上传
	  upload.render({
	    elem: '#uploadImgBtn'
	    ,url: WEBROOT + '/admin/exam/omQuestion/uploadImg' //上传接口
	    ,accept: "images" //普通文件
	    ,exts: 'jpg|png|jpeg|gif'//设置可上传文件
	    ,size: 600 //限制文件大小，单位 KB
	    ,multiple: true
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#imgList').append('<div class="imgdiv"><img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img" onclick="previewImg(this)"><i class="deleteImg"  onclick="removeImage(this);">X</i></div>')
	      });
	    }
	    ,done: function(res){
	      //上传完毕回调
	      if(res.code=='200'){
	    	  imgIds.push(res.data.imgId);
	    	  imgSrcs.push(res.data.src);
	    	  setImgIdSrc();
	      }else{
	    	  $(".imgdiv").last().remove();
	    	  return layer.msg("上传失败:"+res.message);
	      }
	    }
	  });
});

$(function () {
	$(".imgdiv").find("img").each(function(){
		imgIds.push($(this).data("imgid"));
  	    imgSrcs.push($(this).data("imgsrc"));
  	    setImgIdSrc();
	})
})

//点击删除图片
function removeImage(_this){
	layer.confirm('确定删除该图片？', {
   	  	btn: ['确定','取消'] //按钮
   	}, function(){
	   	 var index = $(_this).parent().index();
	     imgIds.splice(index,1); 
	     imgSrcs.splice(index,1);
	     setImgIdSrc();
	     $(_this).parent().remove();
	     layer.closeAll();
   	}, function(){

	});
}

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

function setImgIdSrc(){
	$("#imgIds").val(imgIds.join(","));
	$("#imgSrcs").val(imgSrcs.join(","));
}
</script>
