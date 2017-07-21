var myID;
$(function() {
	/*生成id*/
	
	

	$('#submit').click(function() {
        myID = new Date().getTime();
        console.log(myID);
		var title = $("input[type='text']").val();
		var content = $('#customized-buttonpane').html();
        console.log(content);
        $.ajax({
			type:"post",
			url:"/blog/management/insertArticle",
			data:{
				articleTitle:title,
				articleContent:content,
				articleId:myID

			},
			success:function(data) {
				if(data.status==1){
                    alert("保存成功！");
				}
				console.log(title);
			},
			error:function() {
				alert("保存失败！");
			}
		})
	});
	$(document).on("change",'input[type="file"]',function() {
		console.log(23);
		var file = this.files[0];
		console.log(file);
		var data = new FormData("picture",file);
		data.append("imageRef",myID);
		var oReq = new XMLHttpRequest();
		oReq.open("POST", "/blog/management/uploadImage", true);
		  oReq.onload = function(data) {
		    console.log(data);
		    
		  };


          oReq.send(data);

		/*$.ajax({
			type:"POST",
			url:"http://rapapi.org/mockjsdata/22327/management/uploadImage",
			data:{
				data
			},
			success:function() {
				console.log('success');
			},
			erroe:function() {
				console.log('erroe');
			}
		})*/
	});
	/*var EventUtil = {
   
	 // 添加事件监听 
		 add: function(element, type, callback){
		    
		  if(element.addEventListener){
		   element.addEventListener(type, callback, false);
		  } else if(element.attachEvent){
		   element.attachEvent('on' + type, callback);
		  } else {
		   element['on' + type] = callback;
		  }
		 }
	}
	var file = $('input[type="file"]');
	EventUtil.add(file,"click",function() {
		console.log(23);
		var file = this.files[0];
		console.log(file);
		var data = new formData("picture",file);
		data.append("imageRef",myID);
	})*/
})