<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>反馈管理</title>
    <base href="http://localhost:8080/BeiYueWeb_war_exploded/">
    <link rel="stylesheet" href="static/css/FeedbackStyle.css">
    <link rel="stylesheet" href="static/css/OtherWordStyle.css">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="static/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" language="JavaScript">
        let webSrc = "http://www.immortalmin.com/html/cgi-bin/BeiYueManagementSystem/";
        let curPage = 1,pageCount;
        $(document).ready(function(){
            // QueryTotalCount();
            // getAllWords();
            $("#importWordBtn").on("click",function (){
                window.location.href="./ImportWord.html?dict_source=0";
            })
            // $('#delete_model').on('show.bs.modal',function (event){
            //     let button = $(event.relatedTarget)
            //     let fid = button.data('fid');
            //     let type = button.data('type');
            //     let modal = $(this)
            //     modal.attr("fid",fid);
            //     modal.attr("type",type);
            // })
            // $('#del_confirm_btn').on('click',function (){
            //     let fid = $(this).parent().parent().parent().parent().attr('fid');
            //     let type = $(this).parent().parent().parent().parent().attr('type');
            //     DeleteFeedback(fid,type);
            // })
        });
        function getAllWords(){
            $.ajax({
                url:"../C++/WordManage.cgi",
                type:"POST",
                data:{
                    "what":0,
                    "curPage":curPage,
                    "pageSize":$("#pageSizeSel").val(),
                    "dict_source":0
                },
                dataType:"json",
                success:function (data){
                    //清除之前残留的数据
                    $("#data_table tr").not("tr:first").remove();
                    for(let i=0;i<data.length;i++){
                        let del_btn = "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\".bs-example-modal-sm\" data-fid=\"\">删除</button>"
                        $("#data_table").append("<tr><td>"+data[i]["word_group"]+"</td>" +
                            "<td>"+data[i]["C_meaning"]+"</td>" +
                            "<td><button>详情</button>"+del_btn+"</td></tr>")
                    }
                    $("#data_table tr td").children("button").click(function (){
                        switch ($(this).index()){
                            case 0:
                                let num = $(this).parent().parent().index()-1;
                                window.location.href='./WordDetailPage.html?wid='+data[num]["wid"];
                                break;
                            case 1://删除功能  还没做

                                break;
                        }

                    });
                }
            });
        }
        //当前页码的改变
        let pageChangeV = function (){
            let children = $(this).siblings();
            let childrenNum = children.length+1;
            let pageIndex = $(this).index();
            if(pageIndex===0){//前一页
                curPage=Math.max(Number(curPage)-1,1);
            }else if(pageIndex===1){//首页
                curPage=1;
            }else if(pageIndex===childrenNum-2){//末页
                curPage=pageCount;
            }else if(pageIndex===childrenNum-1){//后一页
                curPage=Math.min(Number(curPage)+1,pageCount);
            }else{//普通的页码
                curPage=$(this).text();
                $(this).addClass("active");
            }
            getAllWords();
            QueryTotalCount();
        }
        let pageSizeChangeV = function (){
            curPage=1;
            QueryTotalCount();
            getAllWords();
        }

        function QueryTotalCount(){
            $.ajax({
                url:"../C++/WordManage.cgi",
                type:"GET",
                data:{
                    "what":1,
                    "dict_source":0
                },
                dataType:"text",
                success:function (data){
                    let totalCount = data;
                    $("#totalCountP").text("共 "+totalCount+" 条记录");
                    let pageSize = $("#pageSizeSel").val();
                    pageCount = Math.ceil(totalCount/pageSize);
                    while(true){
                        if($("#page_nav ul li").length<=4) break;
                        $("#page_nav ul li").eq(2).remove();
                    }
                    for(let i=Math.min(Number(curPage)+2,pageCount);i>=Math.max(Number(curPage)-2,1);i--){
                        $("#page_nav ul li").eq(1).after("<li><a href=\"#\">"+i+"</a></li>");
                        if(i===Number(curPage)) $("#page_nav ul li").eq(2).addClass("active");
                    }
                    //解绑之前绑定的点击事件
                    $("#page_nav ul li").prop("onclick",null).off("click");
                    //为页码标签绑定点击事件
                    $("#page_nav ul li").on("click",pageChangeV);

                    //清除所有按钮的disabled
                    $("#page_nav ul li").removeClass("disabled");
                    //设置按钮不可点击
                    if(Number(curPage)===1){
                        $("#page_nav ul li").eq(0).addClass("disabled");
                        $("#page_nav ul li").eq(1).addClass("disabled");
                    }
                    if(Number(curPage)===Number(pageCount)){
                        $("#page_nav ul li").eq(-1).addClass("disabled");
                        $("#page_nav ul li").eq(-2).addClass("disabled");
                    }
                }
            })
        }


        function UpdateFeedback(fid,type,progress){
            // $(this).parent().parent().parent().parent().siblings(5).text(progress);
            $.ajax({
                url:"../C++/FeedbackManage.cgi?what=1&fid="+fid+"&type="+type+"&progress="+progress,
                type:"GET",
                dataType:"text",
                success:function (){
                    //刷新当前页面，最好是数据发生变化，但是页面位置不要发生改变
                    window.location.reload();
                }
            });
        }
        function DeleteFeedback(fid,type){
            $.ajax({
                url:"../C++/FeedbackManage.cgi?what=2&fid="+fid+"&type="+type,
                type:"GET",
                dataType:"text",
                success:function (data){
                    //刷新当前页面，最好是数据发生变化，但是页面位置不要发生改变
                    console.log(data);
                    window.location.reload();
                }
            });
        }

    </script>
</head>
<body>

<p id="totalCountP">共10条记录</p>
<button id="importWordBtn"><span class="glyphicon glyphicon-open" aria-hidden="true">导入单词</span></button>
<!--    页码-->
<nav aria-label="Page navigation" id="page_nav">
    <ul class="pagination" style="margin: 5px">
        <li class="disabled">
            <a href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li class="disabled"><a href="#">首页</a></li>
        <li><a href="#">末页</a></li>
        <li>
            <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
<select id="pageSizeSel" onchange="pageSizeChangeV()">
    <option value="10">10</option>
    <option value="20">20</option>
    <option value="50">50</option>
    <option value="100">100</option>
</select>
<p style="display: inline-block;float:right;line-height: 50px;margin: 0;">行数：</p>

<!--    <div style="clear:both"></div>-->
<table border="1" id="data_table">
    <tr style="background-color: #f2f2f2;font-size: 18px;height: 50px;">
        <th>英文单词</th>
        <th>中文解释</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.wordList}" var="word">
        <tr>
            <td>${word.word_en}</td>
            <td>${word.word_ch}</td>
            <td>
                <button>详情</button>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm" data-fid="">删除</button>
            </td>
        </tr>
    </c:forEach>
</table>

<!--    删除确认框-->
<div id="delete_model" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <p style="font-size: 22px;margin: 20px;">确认删除么？</p>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="del_confirm_btn" data-dismiss="modal">确认删除</button>
            </div>
        </div>
    </div>
</div>


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>