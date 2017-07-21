
$(function() {
	var productKind;	
	var productImg;
	$('input[type="file"]').change(function() {
		var file = this.files[0];
		console.log(file);
		productImg = file.name
	});
	$('#productKind').change(function() {
		productKind = $('#productKind').val();
		
	})
	$('#submit').click(function(){
		/*var params = $('#productform').submit();*/
		myID = new Date().getTime();
		var productType = $('#productType').val();
		var productPrice = $('#productPrice').val();
		var productContent = $('#productContent').val();
		var productIdName = $('#productIdName').val();
		console.log(productContent);
		console.log(productType);
		console.log(productPrice);
		$.ajax({

			type:"POST",
			url:" /management/insertProduct",
			data:{
				productKind:productKind,
				productType:productType,
				productPrice:productPrice,
				productContent:productContent,
				productIdName:productIdName,
				productImg:productImg
			},
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