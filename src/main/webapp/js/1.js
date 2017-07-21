
$(function() {
	var str = '';
	var pageIndex = 1;
	var pageSize = 5;
	/*$.ajax({
		type:"GET",
		url:"http://rapapi.org/mockjsdata/22327/findAllProducts",
		data:{
			pageIndex:pageIndex,
            pageSize:pageSize
		},
		success:function(data) {
			if (data.status==1) {
				var data = data.data.result;
				var count = data.totalCount;
				var liTab = 3;
				var pageCon = count/liTab;
				var medCur=Math.ceil(liTab/2);
				for (var i = 0; i < data.length; i++) {
					str += '<tr><td class="post-title"><a href="product.html">'
					+data[i].productName+
					'</a></td><td>'
					+data[i].productType+
					'</td><td>'
					+data[i].productContent+
					'</td><td><img src="'
					+data[i].productImg+
					'" width="50" height="50"></td><td>'
					+data[i].productPrice+
					'</td><td>是</td><td><a href="product.html">编辑</a></td><td id="del"><input type="button" class="btn btn-danger" id="del" value="删除" onclick="del()"></td></tr>';		
				}
		
				$('.articel-list').append(str);
				var clipInit=function () {
					console.log(88)
					var paging = '';
					paging += '<ul>';
					paging +="<li class='disbled' id='firstPage' onclick='FirstPage()'>首页</li>";
					paging +="<li class='disbled' id='lastPage' onclick='LastPage()'><</li>";
					paging +="<div id='pageUl' class='pageUl'>";
					if(liTab<=pageCon){
						for(var i=1;i<=liTab;i++){
							paging+="<li id='clip"+i+"' onclick='pageInt(&#039;clip"+i+"&#039;,&#039;"+liTab+"&#039;,&#039;"+medCur+"&#039;)'>"+i+"</li>";
						}
					}else{
						for(var i=1;i<=liTab;i++){
							paging+="<li id='clip"+i+"' onclick='pageInt(&#039;clip"+i+"&#039;,&#039;"+pageCon+"&#039;,&#039;"+medCur+"&#039;)'>"+i+"</li>";
						}
					}
					paging +="</div>";
					paging +="<li class='BORDER' id='nextPage' onclick='NextPage()'>></li>";
					paging +="<li class='BORDER' id='endPage' onclick='EndPage()' style='border-right:1px solid #ccc'>尾页</li>";
					paging +="</ul>";
					$(".paging").html(paging);
					function pageInt(obj,page,medCur){
						var value=parseInt($("#"+obj).text());
						if(value <= medCur){
							clipPage(value,page);
						}else if(value > medCur){
							clipPageMax(value,page,medCur);
						}
					}
					pageInt('clip1',pageCon,medCur);
				}
				clipInit();
				//设置当点击的值小于预设固定值
				function clipPage(cur,page){
					var str="";
					for(var i=1;i<=page;i++){
						var liId="clip"+i;
		                if(cur==i){
		    	            $("#"+liId).attr("class","curPage");
		    	            $("#link"+i).show();
		                }else{
		    	            $("#"+liId).attr("class","BORDER");
		    	            $("#link"+i).hide();
		                }
		                $("#"+liId).text(i);
		            }
		            pageControl(cur);
		            console.log('设置当点击的值小于预设固定值');
	            }
	            //设置的中转站，根据获取的值更改操作
                pageInt=function (obj,page,medCur){
	                var value=parseInt($("#"+obj).text());
	                if(value <= medCur){
		                clipPage(value,page);
	                }else if(value > medCur){
		                clipPageMax(value,page,medCur);
	                }
	                console.log('设置的中转站，根据获取的值更改操作');
                }
                //设置当获取的值大于预设固定值
                function clipPageMax (cur,page,medCur){
                	var str="";
                	var startNum=cur-medCur+1;
                	var maxPage=startNum+parseInt(page)-1;
                	$(".link").hide();
                	if(maxPage<pageCon){ 
                		alert(1);for(var i=1;i<=page;i++){
                			var liId="clip"+i;;
                			if(medCur==i){
                				$("#"+liId).attr("class","curPage");
                				$("#link"+(i+1)).show();
                			}else{
                				$("#"+liId).attr("class","BORDER");					    			    
                			}
                			$("#clip"+i).text(startNum);
                			startNum++;
                		}
                	}else{
                		alert(2);
                		var end = new RegExp(/\d+$/);
                		var page=parseInt(end.exec(page));
                		var curT=cur-pageCon+page;
                		var maxP=pageCon;
                		for(var i=page;i>=1;i--){
                			var liId="clip"+i;
                			if(curT==i){
                				$("#"+liId).attr("class","curPage");
                				$("#link"+(i+2)).show();
                			}else{
                				$("#"+liId).attr("class","BORDER");
                			}
                			$("#"+liId).text(maxP);
                			maxP--;
                		}

                	}
                	pageControl(cur);
                	console.log('//设置当获取的值大于预设固定值');
                }
    			//首页，尾页，上一页，下一页 的样式
    			function pageControl(cur){
    				if(cur==1){
    					$("#firstPage").attr("class","disbled");
    					$("#lastPage").attr("class","disbled");
    					$("#nextPage").attr("class","BORDER");
    					$("#endPage").attr("class","BORDER");
    				}else if(cur==pageCon){
    					$("#firstPage").attr("class","BORDER");
    					$("#lastPage").attr("class","BORDER");
    					$("#nextPage").attr("class","disbled");
    					$("#endPage").attr("class","disbled");
    				}else{
    					$("#firstPage").attr("class","BORDER");
    					$("#lastPage").attr("class","BORDER");
    					$("#nextPage").attr("class","BORDER");
    					$("#endPage").attr("class","BORDER");
    				}
    				console.log('首页，尾页，上一页，下一页 的样式')
    			}
    			//第一页 显示
    			FirstPage=function (){
    				var forNum=parseInt(liTab);
    				clipPage(1,forNum);
    			}
    			//尾页 显示
			    EndPage=function (){
				    var maxV=parseInt(pageCon);
				    clipPageMax(maxV,liTab,medCur);
			    }
			    //上一页 显示
			    LastPage=function (){
				    var choice=$(".curPage").attr('id');
				    var obj=$("#"+choice).prev().attr('id');
				    pageInt(obj,liTab,medCur);
			    }
			    //下一页 显示
			    NextPage=function (){
				    var choice=$(".curPage").attr('id');
				    var obj=$("#"+choice).next().attr('id');
				    pageInt(obj,liTab,medCur);
			    }
			}
			
			
		},
		error:function() {
			console.log("can get data!");
		}
	});*/
	
})
    function del(){
		$.ajax({
			type:"DELETE",
			url:"/blog/management/deleteProduct/{"+myID+"}",
			success:function() {
				alert("删除成功！");
				window.location.reload();

			},
			error:function() {
				alert("删除失败！");
			}
		})
	}