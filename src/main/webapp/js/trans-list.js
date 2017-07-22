var total,pageSize,curPage,totalPage,myId;
function getData(page,pageSize){
    $.ajax({
        type: 'GET',
        url:"/blog/findAllTrains",
        data: {
            'pageIndex':page,
            'pageSize':pageSize
        },
        dataType:'json',
        beforeSend:function(){
            $(".paging").append("<li id='loading'>loading...");//显示加载动画
        },
        success:function(data){
            console.log('s');
            $(".paging").empty();//清空数据区
            total = data.data.totalCount;//总记录数
            console.log(total);
            pageSize = data.data.pageSize; //每页显示条数
            curPage = page; //当前页
            totalPage = Math.ceil(total/pageSize); //总页数
            console.log(totalPage);
            var data = data.data.result;//获取数据
            var li = "";
            for (var i = 0; i < data.length; i++) { //遍历json数据列
                //console.log(data[i].courseId);
                li += '<tr><td style="display: none" id="myId">'
					+data[i].trainId+
					'</td><td class="post-title"><a href="javascript:void(0)">'
                    +data[i].trainName+
                    '</td><td>发布</td><td>'
                    +data[i].trainTime+
                    '</td><td id="edit"><a href="javascript:void(0)" id="edit">编辑</a></td><td id="del"><input type="button" class="btn btn-danger" id="del" value="删除"></td></tr>';
            };
            $("#article-list").html(li);
            console.log(li)
        },
        complete:function(){ //生成分页条
            getPageBar();
            fun();
        },
        error:function(){
            alert("数据加载失败");
        }
    });
}
//获取分页条
function getPageBar(){
    //页码大于最大页数
    if(curPage>totalPage) curPage=totalPage;
    //页码小于1
    if(curPage<1) curPage=1;
    pageStr = "<span>共"+total+"条</span><span>"+curPage
        +"/"+totalPage+"</span>";

    //如果是第一页
    if(curPage==1){
        pageStr += "<span>首页</span><span>上一页</span>";
    }else{
        pageStr += "<span><a href='javascript:void(0)' rel='1'>首页</a></span><span><a href='javascript:void(0)' rel='"+(curPage-1)+"'>上一页</a></span>";
    }

    //如果是最后页
    if(curPage>=totalPage){
        pageStr += "<span>下一页</span><span>尾页</span>";
    }else{
        pageStr += "<span><a href='javascript:void(0)' rel='"+(parseInt(curPage)+1)+"'> 下一页</a></span><span><a href='javascript:void(0)' rel='"+totalPage+"'>尾页</a></span>";
    }
    $(".paging").html(pageStr);
}

$(function(){
    getData(1,3);
});
function fun(){
    $(document).on('click',".paging span a",function(){
        var rel = $(this).attr("rel");
        console.log(rel);
        if(rel){
            getData(rel,3);
        }
    });
}
$(function() {
    $(document).on('click','#del',function(){
        myId = $(this).closest("tr").find('#myId').text();
        console.log(myId);
        $.ajax({
            type:"DELETE",
            url:"/blog/management/deleteTrain/"+myId,
            success:function() {
                alert("删除成功！");
                window.location.reload();

            },
            error:function() {
                alert("删除失败！");
            }
        })
    });
    $(document).on('click','#edit',function() {
        myId = $(this).closest("tr").find('#myId').text();
        console.log(myId);
        $('#product').hide();

        $.ajax({
            type:"GET",
            url:"/blog/management/findTrain/"+myId,
            success:function(data){
                var data = data.data;
                //console.log(data);
                $('#articel-title').val(data.trainName);
                //console.log(data.articleTitle);
                var content = data.trainContent;
                $('#customized-buttonpane').html(content);
                //console.log(data.articleContent)
            },
            error:function() {
                console.log('error');
            }


        });
        $('#editArticel').show();
    })
    $('#submit').click(function() {
        var title = $("input[type='text']").val();
        var content = $('#customized-buttonpane').html();
        $.ajax({
            type:"POST",
            url:"/blog/management/updateTrain",
            data:{
                TrainName:title,
                TrainContent:content,
                TrainId:myId

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

