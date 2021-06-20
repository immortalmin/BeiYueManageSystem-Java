<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>反馈管理</title>
    <base href="http://localhost:8080/BeiYueWeb_war_exploded/">
    <link rel="stylesheet" href="static/css/ImportWordPreview.css">
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
        $(document).ready(function(){
            bindClick();
            modelInit();
            if("${requestScope.importResult}"==="1"){
                $("#successAlert").show();
                setInterval(jump,3000);
                function jump() {
                    window.location.href="word?action=listAllOtherWord&curPage=1&pageSize=10";
                }
            }
        });

        function modelInit() {
            $('#del_confirm_btn').on('click',function (){
                let wid = $(this).parent().parent().parent().parent().attr('wid');
                DeleteWord(wid);
            })
        }

        function bindClick(){
            $("#importWordBtn").on("click",function (){
                window.location.href="pages/word/ImportWord.html";
            })
            $("#warningCloseBtn").on("click",function (){
                $("#successAlert").hide();
            })
        }

    </script>
</head>
<body>
    <div class="alert alert-success alert-dismissible" role="alert" id="successAlert" style="display: none;">
        <button type="button" id="warningCloseBtn" class="close"><span aria-hidden="true">&times;</span></button>
        <strong>Success!</strong> 导入成功！3秒后跳转到单词界面
    </div>
    <h2>导入结果预览:</h2>
    <div style="margin: 30px">
        <c:forEach items="${requestScope.wordList}" var="otherWord">
            <div class="word_div">
                <p>${otherWord.word_en}</p>
                <p>${otherWord.word_ch}</p>
            </div>
            <div class="examples_div">
                <p style="margin: 5px;font-size: 20px; font-weight: bold">例句：</p>
                <c:forEach items="${otherWord.sentences}" var="sentence">
                    <div class="example_div">
                        <p>${sentence.word_meaning}</p>
                        <p>${sentence.sentence_en}</p>
                        <p>${sentence.sentence_ch}</p>
                        <hr/>
                    </div>
                </c:forEach>
            </div>
            <tr><td><hr/></td></tr>
        </c:forEach>
    </div>
    <div class="button_div">
        <button class="btn btn-danger" onclick="window.location.href='pages/word/ImportWord.html'">重新导入</button>
        <button class="btn btn-success" style="margin-left: 30px;" onclick="window.location.href='word?action=confirmImport'">确认导入</button>
    </div>

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>