var mainContent = ['banner','articel','product','aboutUs','Mass'];
var bannerList = ['firstPage','stroe'];
$(function() {
	$('.info-do li').hover(function() {
		$(this).find('.second-list').show();
	},function() {
		$(this).find('.second-list').hide();
	});
	/*$('.schedule-all').hover(function() {
		$('#schedule').show();
	},function() {
		$('#schedule').hide();
	});*/
	$(".info-do li ul li").click(function() {
		event.stopPropagation();
		var index = $(this).index();
		$(this).addClass("highlight").siblings().removeClass("highlight");
		$(".main-content").hide();
		$('#'+bannerList[index]).show();

		console.log(bannerList[index]);

	});
	/*$('.banner-kind li').hover(function(){
		$(this).find(".mask").show();
		
	},function(){
		$(this).find(".mask").hide();
	});*/
	$("#text-mas").click(function(){
		$('#text-mas').hide();
		$('.textEdit').hide();
		$('#text-edit').show();
		$('.articel-list').show();
		console.log(2)
	});
	$("#text-edit").click(function(){
		$('#text-mas').show();
		$('.textEdit').show();
		$('#text-edit').hide();
		$('.articel-list').hide();
	});
});
