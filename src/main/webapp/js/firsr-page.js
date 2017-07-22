var src;
console.log(1)
$(function() {
	$('#upload').change(function() {
		$(this).closest('li').find('.mask').hide();
		//var file = this.files[0];
        var reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
		var data = new FormData($('#form1')[0]);
		//formData.append("bannerImg",bannerImg);
		$.ajax({
			type:"post",
			url:"/blog/management/insertBanner",
            data:data,
            async:false,
            cache:false,
            contentType:false,
            processData:false,
			success:function(data) {
				alert('上传成功！');
                src = data;
                console.log(src);
				// window.location.reload();

			},
			error:function() {
				alert('上传失败！');
			}

		});
        /*var oReq = new XMLHttpRequest();
        oReq.open("POST", "/blog/management/uploadImage", true);
        oReq.onload = function(data) {
            console.log(data);

        };


        oReq.send(formData);*/
        $(this).closest('li').find('.img').show();
        $(this).closest('li').find('.img').attr('src',src);
	})
})