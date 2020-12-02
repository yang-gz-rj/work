<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 12/2/2020
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <script src="/layui/layui.js" charset="UTF-8"></script>
    <style>
        img{
            height: 15px;
            width: 15px;
            padding-right: 10px;
        }
        dd{
            padding-left: 40px;
        }
        #show-guide{
            float: left;
            height: 100%;
            width: 15%;
        }
        #show-right{
            float: left;
            width: 85%;
            height: 100%;
            background-image: url("/image/background.png");
            background-size: 100% 100%;
            opacity:0.8;
            filter:alpha(opacity=80); /* 针对 IE8 以及更早的版本 */
        }
        #show-user{
            float: left;
            width: 100%;
            height: 7%;
        }
        #show-user li{
            float: right;
            width: 8%;
            height: 100%;
        }
        #show-user li a{
            text-align: center;
            line-heigh: 100%;
            height: 100%;
        }
        #show-frame{
            float: left;
            width: 100%;
            height: 90%;
        }
    </style>
</head>
<body>
<ul class="layui-nav layui-nav-tree" lay-filter="test" id="show-guide">
    <li class="layui-nav-item">
        <a href="javascript:void(0)"><img src="/image/public.png"/>公告</a>
        <dl class="layui-nav-child">
            <dd><a href="">公共公告</a></dd>
            <dd><a href="">个人公告</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:void(0)"><img src="/image/client.png"/>用户管理</a>
        <dl class="layui-nav-child">
            <dd><a href="">用户信息</a></dd>
            <dd><a href="">设备信息</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:void(0)"><img src="/image/water.png"/>水费管理</a>
        <dl class="layui-nav-child">
            <dd><a href="">水价位信息</a></dd>
            <dd><a href="">水费账单信息</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:void(0)"><img src="/image/elect.png"/>电费管理</a>
        <dl class="layui-nav-child">
            <dd><a href="">电价位信息</a></dd>
            <dd><a href="">电费账单信息</a></dd>
        </dl>
    </li>
</ul>
<div id="show-right">
    <ul class="layui-nav layui-nav-tree" id="show-user">
        <li class="layui-nav-item">
            <a href="javascript:void(0)"><img src="/image/user.png">我</a>
            <dl class="layui-nav-child">
                <dd style="text-align: center;">注销</dd>
            </dl>
        </li>
    </ul>
    <div id="show-frame"></div>
</div>
<script>
    layui.use('element', function(){
        var element = layui.element;

        //一些事件监听
        element.on('tab(demo)', function(data){
            console.log(data);
        });
    });
</script>
</body>
</html>
