<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>详情界面</title>
    <base href="http://localhost:8080/BeiYueWeb_war_exploded/">
    <link rel="stylesheet" href="static/css/WordDetail.css">
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
        let wid;
        $(document).ready(function() {
            // wid = getQueryVariable("wid");
            //获取单词的详细信息
            // getWord();
            //获取例句
            // getExampleSentences();
        });
        function getQueryVariable(variable)
        {
            let query = window.location.search.substring(1);
            let vars = query.split("&");
            for (let i=0;i<vars.length;i++) {
                let pair = vars[i].split("=");
                if(pair[0] === variable){return pair[1];}
            }
            return(false);
        }
        function getWord(){
            $.ajax({
                url:"../C++/WordManage.cgi",
                type:"POST",
                data:{
                    "what":2,
                    "wid":wid,
                    "dict_source":0
                },
                dataType:"json",
                success:function (data){
                    console.log(data);
                    $("#word_div").find("p").eq(0).text(data["word_en"]);
                    $("#word_div").find("p").eq(1).text(data["word_ch"]);
                    $("#word_div").find("p").eq(2).text("来源："+data["source"]);
                }
            });
        }
        function getExampleSentences(){
            $.ajax({
                url:"../C++/WordManage.cgi",
                type:"POST",
                data:{
                    "what":3,
                    "wid":wid,
                    "dict_source":0
                },
                dataType:"json",
                success:function (data){
                    console.log(data);
                    for(let i=0;i<data.length;i++){
                        let exampleDiv = "<div class=\"example_div\">\n" +
                            "                <p>"+data[i]["word_meaning"]+"</p>\n" +
                            "                <p>来源："+data[i]["source"]+"</p>\n" +
                            "                <p>"+data[i]["E_sentence"]+"</p>\n" +
                            "                <p>"+data[i]["C_translate"]+"</p>\n" +
                            "            </div>";
                        let separateLine = "<hr class=\"separate_line\">";
                        if(i!=0) $("#examples_div").append(separateLine);
                        $("#examples_div").append(exampleDiv);
                    }

                }
            });
        }
    </script>
</head>
<body>
    <div style="margin: 30px">
        <!--        单词释义-->
        <div id="word_div">
            <p>${requestScope.otherWord.word_en}</p>
            <p>${requestScope.otherWord.word_ch}</p>
            <p>来源:${requestScope.otherWord.source}</p>
        </div>
        <!--        例句-->
        <div id="examples_div">
            <p style="margin: 5px;font-size: 20px; font-weight: bold">例句：</p>
            <c:forEach items="${requestScope.otherSentences}" var="otherSentence">
                <div class="example_div">
                    <p>${otherSentence.word_meaning}</p>
                    <p>来源:${otherSentence.source}</p>
                    <p>${otherSentence.sentence_en}</p>
                    <p>${otherSentence.sentence_ch}</p>
                </div>
            </c:forEach>

        </div>
    </div>


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>