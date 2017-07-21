var myID;
$(function() {
	/*生成id*/
	var select;
	$('#select').change(function(){
		select = $('#select').val();
		console.log(select);
	})
	$('#submit').click(function() {
		myID = new Date().getTime();
		var title = $("input[type='text']").val();
		var content = $('#customized-buttonpane').html();
		var date = $('.date_picker').val();
		var time = date+select;
		console.log(date,select);
		$.ajax({
			type:"POST",
			url:"/blog/management/insertTrain",
			data:{
				'trainName':title,
				'trainContent':content,
				'trainId':myID
				//courseTime:time


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
		var data = new FormData("picture",file);
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
        oReq.open("POST", "/blog/management/uploadImage", true);
        oReq.onload = function(data) {
            console.log(data);

        };


        oReq.send(data);
	});

})