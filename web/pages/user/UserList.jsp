<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户管理</title>
    <base href="http://localhost:8080/BeiYueWeb_war_exploded/">
    <link rel="stylesheet" href="static/css/FeedbackStyle.css">
    <link rel="stylesheet" href="static/css/User.css">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.js"></script>

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="static/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" language="JavaScript">
        let webSrc = "http://www.immortalmin.com/html/cgi-bin/BeiYueManagementSystem/";
        let curPage = ${requestScope.curPage},pageCount=10,pageSize = ${requestScope.pageSize},totalCount = ${requestScope.totalCount},uid;
        $(document).ready(function(){
            setPage();
            modelInit();
        });

        let modelInit = function(){
            $('#resetPwdModal').on('show.bs.modal', function (event) {
                $("#newPwd").val("");
                $("#warningP").hide();
                $("#successAlert").hide();
                let button = $(event.relatedTarget) // Button that triggered the modal
                uid = button.data('whatever') // Extract info from data-* attributes
            })
            $("#resetBtn").on("click",function (){
                //检查新密码的格式是否符合要求
                let newPwd = $("#newPwd").val();
                console.log(newPwd);
                if(PwdJudge(newPwd)===true){
                    ResetPwd(uid,newPwd);
                }else{
                    $("#warningP").css("display","inline");
                }

            })
            $("#warningCloseBtn").on("click",function (){
                $("#successAlert").hide();
            })
        }

        function ResetPwd(uid,newPwd){
            $.post("user?action=update",{'uid':uid,'newPwd':newPwd},function () {
                $("#successAlert").show();
                $("#resetPwdModal").click();
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
            window.location.href="user?action=list&curPage="+curPage+"&pageSize="+pageSize;
        }
        let pageSizeChangeV = function (){
            curPage=1;
            pageSize = $("#pageSizeSel").val();
            window.location.href="user?action=list&curPage="+curPage+"&pageSize="+pageSize;
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
                $("#page_nav ul li").eq(1).after("<li><a href='#' onclick=\"return false;\">"+i+"</a></li>");
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

        function PwdJudge(newPwd){
            let reg=/^(?![0-9])(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;   /*定义验证表达式*/
            return reg.test(newPwd);
        }

    </script>
</head>
<body>
<div class="alert alert-success alert-dismissible" role="alert" id="successAlert" style="display: none;">
    <button type="button" id="warningCloseBtn" class="close"><span aria-hidden="true">&times;</span></button>
    <strong>Success!</strong> 成功重置密码！
</div>
<p id="totalCountP">共${requestScope.totalCount}条记录</p>
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
        <th>用户类型</th>
        <th>手机号码</th>
        <th>电子邮箱</th>
        <th>个性签名</th>
        <th>上一次登录时间</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.userList}" var="user">
        <tr>
            <c:if test="${user.login_mode==0}">
                <td><img src="http://www.immortalmin.com/word/img/profile/${user.profile_photo}" class="profileImg">${user.username}</td>
                <td>普通用户</td>
            </c:if>
            <c:if test="${user.login_mode==1}">
                <td><img src="${user.profile_photo}" class="profileImg">${user.username}</td>
                <td>QQ用户</td>
            </c:if>
            <td>${user.telephone}<c:if test="${user.telephone==null}">(空)</c:if></td>
            <td>${user.email}<c:if test="${user.email==''}">(空)</c:if><c:if test="${user.email==null}">(空)</c:if></td>
            <td>${user.motto}<c:if test="${user.motto==''}">(空)</c:if></td>
            <td>${user.last_login_string}<c:if test="${user.last_login_string==null}">(空)</c:if></td>
            <td>
                <button class="resetPwdBtn btn btn-primary" data-toggle="modal" data-target="#resetPwdModal" data-whatever="${user.uid}">重置密码</button>
            </td>
        </tr>
    </c:forEach>
</table>

<!--    重置密码编辑框-->
<div class="modal fade" id="resetPwdModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">重置密码</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="newPwd" class="control-label">新密码:</label>
                        <p id="warningP">（6-16位数字字母混合，且首位不能是数字）</p>
                        <input type="text" class="form-control" id="newPwd" placeholder="6-16位数字字母混合，且首位不能是数字">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="resetBtn">重置</button>
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