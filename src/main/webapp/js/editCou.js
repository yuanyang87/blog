console.log(myID);
$(function() {
	$.ajax({
		type:"GET",
		url:"/blog/findAllcourses",
		success:function(data){
			var data = data.data.result;
			console.log(data);
			for(var i=0;i<data.length;i++) {
				console.log(data[i].courseId);
				if (data[i].courseId == myID) {
					$('#articel-title').val(data[i].courseTitle);
					console.log(data[i].courseTitle);
					var content = data[i].courseContent
					$('#customized-buttonpane').html(content);
					console.log(data[i].courseContent)
				}
			}
		},
		error:function() {
			console.log('error');
		}

	});

	$('#submit').click(function() {
		var title = $("input[type='text']").val();
		var content = $('#customized-buttonpane').html();
		$.ajax({
			type:"GET",
			url:"/blog/management/updatecourse",
			data:{
				courseTitle:title,
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
})