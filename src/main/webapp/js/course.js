var myID;
console.log(10);
$(function() {
	/*生成id*/
	
	

	$('#submit').click(function() {
        myID = new Date().getTime();
        console.log(myID);
		var title = $("input[type='text']").val();
		var content = $('#customized-buttonpane').html();
		$.ajax({
			type:"POST",
			url:"/blog/management/insertCourse",
			data:{
				courseName:title,
				courseContent:content,
				courseId:myID

			},
			success:function() {
				alert("保存成功！");
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
		var data = new formData("picture",file);
		data.append("imageRef",myID);
		/*$.ajax({
			type:"GET",
			url:"",
			// data:{
			// 	data
			// },
			success:function() {
				console.log('success');
			},
			erroe:function() {
				console.log('erroe');
			}
		})*/
        var oReq = new XMLHttpRequest();
        oReq.open("POST", "http://rapapi.org/mockjsdata/22327/management/uploadImage", true);
        oReq.onload = function(data) {
            console.log(data);

        };


        oReq.send(data);
	});

})