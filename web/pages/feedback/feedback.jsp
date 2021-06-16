<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>反馈管理</title>
    <!--    <base href="http://localhost:8080/BeiYueWeb/">-->
    <base href="http://localhost:8080/BeiYueWeb_war_exploded/">
    <link rel="stylesheet" href="static/css/FeedbackStyle.css">
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
        let curPage = ${requestScope.curPage},pageCount=10,pageSize=${requestScope.pageSize},totalCount = ${requestScope.totalCount},data = [];
        $(document).ready(function () {
            dataInit();
            setPage();
            bindClick();
            modelInit();

        })

        function dataInit() {
            let data_i=0;
            $("#totalCountP").text(${requestScope.totalCount});
            <c:forEach items = "${requestScope.feedbackList}" var="feedback">
            data[data_i] = [];
            data[data_i][0]  = "${feedback.user.username}";
            data[data_i][1]  = "${feedback.what}";
            data[data_i][2]  = "${feedback.description}";
            data[data_i][3]  = "${feedback.phone_model}";
            data[data_i][4]  = "${feedback.contact}";
            data[data_i][5]  = "${feedback.add_time}";
            data[data_i][6]  = "${feedback.progress}";
            data[data_i][7]  = "${feedback.img_path}";
            data_i++;
            </c:forEach>
        }

        function modelInit(){
            $('#delete_model').on('show.bs.modal',function (event){
                let button = $(event.relatedTarget)
                let fid = button.data('fid');
                let type = button.data('type');
                let modal = $(this)
                modal.attr("fid",fid);
                modal.attr("type",type);
            })
            $('#del_confirm_btn').on('click',function (){
                let fid = $(this).parent().parent().parent().parent().attr('fid');
                let what = $(this).parent().parent().parent().parent().attr('type');
                DeleteFeedback(fid,what);
            })

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
            window.location.href="feedback?action=list&curPage="+curPage+"&pageSize="+pageSize;
        }
        let pageSizeChangeV = function (){
            curPage=1;
            pageSize = $("#pageSizeSel").val();
            window.location.href="feedback?action=list&curPage="+curPage+"&pageSize="+pageSize;
        }

        function setPage(){
            $("#totalCountP").text("共 "+totalCount+" 条记录");
            $("#pageSizeSel").find("option[value="+pageSize+"]").attr("selected",true);
            pageCount = Math.ceil(totalCount/pageSize);
            while(true){
                if($("#page_nav ul li").length<=4) break;
                $("#page_nav ul li").eq(2).remove();
            }
            for(let i=Math.min(Number(curPage)+2,pageCount);i>=Math.max(Number(curPage)-2,1);i--){
                // $("#page_nav ul li").eq(1).after("<li><a href=\"feedback?action=list&curPage="+i+"&pageSize="+pageSize+"\">"+i+"</a></li>");
                $("#page_nav ul li").eq(1).after("<li><a href=\"#\" onclick='return false;'>"+i+"</a></li>");
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
        function bindClick(){
            $("#data_table tr td").children("button").click(function (){
                switch ($(this).index()){
                    case 0:
                        let Model = $("#EditModel");
                        //清除所有图片
                        Model.find("tr").eq(7).empty();
                        const num = $(this).parent().parent().index()-1;
                        Model.modal();
                        Model.find("tr").eq(0).find("td").eq(1).text(data[num][0]);
                        let what;
                        if("0"===data[num][1]) what="错误反馈";
                        else what="功能建议";
                        Model.find("tr").eq(1).find("td").eq(1).text(what);
                        Model.find("tr").eq(2).find("td").eq(1).text(data[num][2]);
                        Model.find("tr").eq(3).find("td").eq(1).text(data[num][3]);
                        Model.find("tr").eq(4).find("td").eq(1).text(data[num][4]);
                        Model.find("tr").eq(5).find("td").eq(1).text(data[num][5]);
                        Model.find("tr").eq(6).find("td").eq(1).text(data[num][6]);
                        if(data[num][7]==="") break;
                        let img_paths = data[num][7].split('#');
                        //显示图片
                        for(let i=0;i<img_paths.length;i++){
                            Model.find("tr").eq(7).append("<img src='http://www.immortalmin.com/word/img/feedback/"+img_paths[i]+"' class='feedback_img img-thumbnail'>");
                        }
                        //设置监听，点击图片，放大图片
                        //不够好,而且尺寸有问题
                        $(".feedback_img").click(function (){
                            if($(this).width()<=120){
                                $(this).siblings().width(120);
                                $(this).width(500);
                            }else{
                                $(this).width(120);
                            }
                        })
                        break;
                    case 1://修改进度

                        break;
                    case 2://删除

                        break;
                }

            });
        }
        function UpdateFeedback(fid,what,progress){
            // $.ajax({
            //     url:"feedback?action=updateProgress",
            //     type:"POST",
            //     data:{'fid':fid,'what':what,'progress':progress,'curPage':curPage,'pageSize':pageSize},
            //     dataType:"text",
            //     // success:function (){
            //     //     //刷新当前页面，最好是数据发生变化，但是页面位置不要发生改变
            //     //     window.location.reload();
            //     // }
            // });
            $.post("feedback?action=updateProgress",{'fid':fid,'what':what,'progress':progress},function () {
                window.location.reload();
                // window.location.href="feedback?action=list&curPage="+curPage+"&pageSize="+pageSize;
            });
            return false;
        }
        function DeleteFeedback(fid,what){
            $.post("feedback?action=delete",{'fid':fid,'what':what},function () {
                window.location.reload();
                // window.location.href="feedback?action=list&curPage="+curPage+"&pageSize="+pageSize;
            });
        }

    </script>
</head>
<body>

    <p id="totalCountP" style="display: inline-block;float:left;line-height: 50px;margin: 0 0 0 20px;">共${requestScope.totalCount}条记录</p>
    <!--    页码-->
    <nav aria-label="Page navigation" id="page_nav">
        <ul class="pagination" style="margin: 5px">
            <li class="disabled">
                <a href="#" aria-label="Previous" onclick="return false;">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li class="disabled"><a href="#" onclick="return false;">首页</a></li>
            <li><a href="#" onclick="return false;">末页</a></li>
            <li>
                <a href="#" aria-label="Next" onclick="return false;">
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
            <th>用户名</th>
            <th>反馈类型</th>
            <th>描述</th>
            <th>添加时间</th>
            <th>处理进度</th>
            <th>操作</th>
        </tr>
        <c:forEach items = "${requestScope.feedbackList}" var="feedback">
            <tr>
                <td>${feedback.user.username}</td>
                <c:if test="${feedback.what=='0'}"><td>错误反馈</td></c:if>
                <c:if test="${feedback.what=='1'}"><td>功能建议</td></c:if>
                <td>${feedback.description}</td>
                <td>${feedback.add_time}</td>
                <td>${feedback.progress}</td>
                <td>
                    <button>详情</button>
                    <c:if test="${feedback.what==0}">
                        <div class="btn-group">
                            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="font-size: medium;background-color: #5cb85c;!important;">
                                修改进度
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#" onclick="return UpdateFeedback(${feedback.fid},${feedback.what},0)">待处理</a></li>
                                <li><a href="#" onclick="return UpdateFeedback(${feedback.fid},${feedback.what},1)">修复中</a></li>
                                <li><a href="#" onclick="return UpdateFeedback(${feedback.fid},${feedback.what},2)">已修复</a></li>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${feedback.what==1}">
                        <div class="btn-group">
                            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="font-size: medium;background-color: #5cb85c;!important;">
                                修改进度
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#" onclick="return UpdateFeedback(${feedback.fid},${feedback.what},0)">待处理</a></li>
                                <li><a href="#" onclick="return UpdateFeedback(${feedback.fid},${feedback.what},1)">已采纳</a></li>
                                <li><a href="#" onclick="return UpdateFeedback(${feedback.fid},${feedback.what},2)">未采纳</a></li>
                                <li><a href="#" onclick="return UpdateFeedback(${feedback.fid},${feedback.what},3)">实现中</a></li>
                                <li><a href="#" onclick="return UpdateFeedback(${feedback.fid},${feedback.what},4)">已实现</a></li>
                            </ul>
                        </div>
                    </c:if>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm" data-fid="${feedback.fid}" data-type="${feedback.what}">删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>

    <!--    模态对话框-->
    <div class="modal fade" id="EditModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">详细信息</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <table id="edit_table" class="table table-striped" style="width: 90%;margin: 0 auto">
                            <tr>
                                <td>用户名：</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>反馈类型：</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>描述：</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>手机型号：</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>联系方式：</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>添加时间：</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>处理进度：</td>
                                <td></td>
                            </tr>
                            <tr>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

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
