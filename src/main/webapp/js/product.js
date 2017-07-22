
$(function() {
	var myId = new Date().getTime();
	$('input[type="file"]').change(function() {
        var reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
	});
	/*$('#productKind').change(function() {
		var productKind = $('#productKind').val();
		
	})*/
	$('#submit').click(function(){
		/*var params = $('#productform').submit();*/
		myID = new Date().getTime();

		var data = new FormData($('#productform')[0]);
		$.ajax({

			type:"POST",
			url:" /blog/management/insertProduct",
			data:data,
            async:false,
            cache:false,
            contentType:false,
            processData:false,
			success:function() {
				alert("保存成功！");
			},
			error:function() {
				alert("保存失败！");
				console.log(productType);
			}

		})
		
		
	});
	
})