console.log(000);
$(function() {
	var str = '';
	$.ajax({
		type:"GET",
		url:"/blog/findAllTrains",
		data:{
			pageIndex:1,
            pageSize:5
		},
		success:function(data) {
			if (data.status==1) {
				var data = data.data.result;
				for (var i = 0; i < data.length; i++) {
					str += '<tr><td class="post-title"><a href="trans.html">'
					+data[i].trainName+
					'</td><td>发布</td><td>'
					+data[i].trainTime+
					'</td><td id="edit"><a href="trans.html">编辑</a></td><td id="del"><input type="button" class="btn btn-danger" id="del" value="删除" onclick="del()"></td></tr>';		
				}
				console.log(data[0]);
				$('.articel-list').append(str);
			}
			
			
		},
		error:function() {
			console.log("can get data!");
		}
	});
	
})
    function del(){
		$.ajax({
			type:"DELETE",
			url:"/blog/management/deleteTrain/{"+myID+"}",
			success:function() {
				alert("删除成功！");
				window.location.reload();

			},
			error:function() {
				alert("删除失败！");
			}
		})
	}