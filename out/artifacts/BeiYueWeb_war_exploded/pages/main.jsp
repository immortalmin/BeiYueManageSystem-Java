<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>主界面</title>
    <base href="http://localhost:8080/BeiYueWeb_war_exploded/">
    <link rel="stylesheet" href="static/css/MainStyle.css">
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
        let webSrc = "http://www.immortalmin.com/html/cgi-bin/BeiYueManagementSystem/HTML/";
        $(document).ready(function(){
            // $("#profile_nav").hover(function (){
            //     $("#profile").show();
            // },function (){
            //     $("#profile").hide();
            // });

            modelInit();
            $(".subMenu").css("display","none");
            $(".menu_level_1").click(function (){
                let indexOfLi = $(this).index();
                $("#wordSubMenu li").removeClass("li_selected");
                switch (indexOfLi){
                    case 0:

                        break;
                    case 1:
                        $("#content_body").attr("src","feedback?action=list&curPage=1&pageSize=10");
                        $(this).addClass("li_selected");
                        $(this).siblings().removeClass("li_selected");
                        $("#wordSubMenu").css("display","none");
                        break;
                    case 2:
                        $(this).addClass("li_selected");
                        $(this).siblings().removeClass("li_selected");
                        let wordSubMenu = $("#wordSubMenu");
                        if(wordSubMenu.css("display")==="none"){
                            wordSubMenu.css("display","");
                        }else{
                            wordSubMenu.css("display","none");
                        }
                        break;
                    case 4:
                        $("#content_body").attr("src","user?action=list&curPage=1&pageSize=10");
                        $(this).addClass("li_selected");
                        $(this).siblings().removeClass("li_selected");
                        $("#wordSubMenu").css("display","none");
                        break;
                    case 5:
                        $("#content_body").attr("src",webSrc+"FlawPage.html");
                        $(this).addClass("li_selected");
                        $(this).siblings().removeClass("li_selected");
                        $("#wordSubMenu").css("display","none");
                        break;
                    default:
                        break;
                }
            });
            $("#wordSubMenu li").on("click",function (){
                let indexOfLi = $(this).index();
                switch (indexOfLi){
                    case 0://恋练不忘
                        $("#content_body").attr("src","word?action=listAllWords&curPage=1&pageSize=10&dict_source=1");
                        $(this).addClass("li_selected");
                        $(this).siblings().removeClass("li_selected");
                        break;
                    case 1://柯林斯词典
                        $("#content_body").attr("src","word?action=listAllWords&curPage=1&pageSize=10&dict_source=2");
                        $(this).addClass("li_selected");
                        $(this).siblings().removeClass("li_selected");
                        break;
                    case 2://由用户添加的单词/词组
                        $("#content_body").attr("src","word?action=listAllWords&curPage=1&pageSize=10&dict_source=0");
                        $(this).addClass("li_selected");
                        $(this).siblings().removeClass("li_selected");
                        break;
                }
            });
            if("${sessionScope.user.username}"===""){//如果刷新后，session信息失效，则需要重新登录
                window.location.href="login";
            }
        });
        function modelInit(){
            // $('#logout_model').on('show.bs.modal',function (event){
            //     let button = $(event.relatedTarget)
            //     let modal = $(this)
            // })
            $('#logout_confirm_btn').on('click',function (){
                window.location.href="login?action=logout";
            })
        }

    </script>

</head>
<body>
    <!--导航栏-->
    <nav class="navbar navbar-default" style="margin-bottom: 0">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="http://www.immortalmin.com/html/cgi-bin/BeiYueManagementSystem/HTML/MainPage.html">北月单词后台管理系统</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="font-size: 18px;padding-top: 10px;padding-bottom: 10px">
                            <c:if test="${sessionScope.user.login_mode=='0'}">
                                <img id="profile_photo" src="http://www.immortalmin.com/word/img/profile/${sessionScope.user.profile_photo}">
                            </c:if>
                            <c:if test="${sessionScope.user.login_mode=='1'}">
                                <img id="profile_photo" src="${sessionScope.user.profile_photo}">
                            </c:if>
                            ${sessionScope.user.username}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">个人信息</a></li>
                            <li><a href="#">设置</a></li>
                            <li><a href="#">其他</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#" onclick="return false;" data-toggle="modal" data-target=".bs-example-modal-sm-logout">退出</a></li>
<%--                            <li><button data-toggle="modal" data-target=".bs-example-modal-sm-logout">退出</button></li>--%>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </nav>
    <div id="menu">
        <ul>
            <li class="menu_level_1">首页</li>
            <li class="menu_level_1 li_selected">反馈管理</li>
            <li class="menu_level_1">单词管理</li>
            <ul class="subMenu" id="wordSubMenu">
                <li>-&nbsp;恋练不忘</li>
                <li>-&nbsp;柯林斯词典</li>
                <li>-&nbsp;用户添加的单词</li>
            </ul>
            <li class="menu_level_1">用户管理</li>
<%--            <li class="menu_level_1">流量监控</li>--%>
        </ul>
    </div>
    <div id="content">
        <iframe id="content_body" src="feedback?action=list&curPage=1&pageSize=10" frameborder="0" scrolling="yes"></iframe>
    </div>

<!--    <button data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Open modal for @mdo</button>-->

<!--    模态对话框-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">New message</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="recipient-name" class="control-label">Recipient:</label>
                            <input type="text" class="form-control" id="recipient-name">
                        </div>
                        <div class="form-group">
                            <label for="message-text" class="control-label">Message:</label>
                            <textarea class="form-control" id="message-text"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Send message</button>
                </div>
            </div>
        </div>
    </div>

    <!-- logout确认框 -->
    <div id="logout_model" class="modal fade bs-example-modal-sm-logout" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <p style="font-size: 22px;margin: 20px;">确认退出登录么？</p>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="logout_confirm_btn" data-dismiss="modal">确认</button>
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