//console.log(myId);
console.log(myID);
$(function() {
	$.ajax({
		type:"GET",
		url:"/blog/findAllArticles",
		success:function(data){
			var data = data.data.result;
			console.log(data);
			for(var i=0;i<data.length;i++) {
				console.log(data[i].articleId);
				if (data[i].articleId == myId) {
					$('#articel-title').val(data[i].articleTitle);
					console.log(data[i].articleTitle);
					var content = data[i].articleContent;
					$('#customized-buttonpane').html(content);
					console.log(data[i].articleContent)
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
			url:"/blog/management/updateArticle",
			data:{
				articleTitle:title,
				articelContent:content,
				articleId:myId

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