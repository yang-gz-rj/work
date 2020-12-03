<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 12/2/2020
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head id="gude-head">
    <title>wes</title>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <style>
        img{
            height: 15px;
            width: 15px;
            padding-right: 10px;
        }
        #show-right{
            float: left;
            width: 85%;
            height: 100%;
            background-image: url("/image/background.png");
            background-size: 100% 100%;
            opacity:0.95;
            filter:alpha(opacity=95); /* 针对 IE8 以及更早的版本 */
        }
    </style>
</head>
<body>
<ul class="layui-nav layui-nav-tree" lay-filter="ulfilter" id="show-guide" style="float: left; height: 100%;width: 15%;">
    <li class="layui-nav-item">
        <a href="javascript:;"><img src="/image/public.png"/>公告</a>
        <dl class="layui-nav-child">
            <dd style="padding-left: 20%;"><a href="javascript:;">公共公告</a></dd>
            <dd style="padding-left: 20%;"><a href="javascript:;">个人公告</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:;"><img src="/image/client.png"/>用户管理</a>
        <dl class="layui-nav-child">
            <dd style="padding-left: 20%;"><a href="javascript:;">用户信息</a></dd>
            <dd style="padding-left: 20%;"><a href="javascript:;">设备信息</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;"><img src="/image/water.png"/>水费管理</a>
        <dl class="layui-nav-child">
            <dd style="padding-left: 20%;"><a href="javascript:;" >水价位信息</a></dd>
            <dd style="padding-left: 20%;"><a href="javascript:;" >水费账单信息</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;"><img src="/image/elect.png"/>电费管理</a>
        <dl class="layui-nav-child">
            <dd style="padding-left: 20%;"><a href="javascript:;" >电价位信息</a></dd>
            <dd style="padding-left: 20%;"><a href="javascript:;" >电费账单信息</a></dd>
        </dl>
    </li>
</ul>
<%-- 右布局 --%>
<div id="show-right">
    <%-- 用户昵称显示 --%>
    <ul class="layui-nav layui-nav-tree" id="show-user" style="float: left;width: 100%;height: 7%;">
        <li class="layui-nav-item" style="float: right;width: 8%;height: 100%;">
            <a href="javascript:;" style="text-align: center;line-heigh: 100%;height: 100%;"><img src="/image/user.png">${pageContext.request.getParameter('client_user')}</a>
            <dl class="layui-nav-child">
                <dd style="text-align: center;" onclick="logout()">注销</dd>
            </dl>
        </li>
    </ul>
    <%-- 显示动态页面 --%>
    <div id="show-frame" style="float: left;width: 100%;height: 90%;"></div>
</div>
<script src="/layui/layui.js" charset="UTF-8"></script>
<script>
    let currPage = "/client";
    const client_user = '${pageContext.request.getParameter('client_user')}';
    /* 加载所有模块 */
    let element,form,layer,$,table,laypage;

    layui.use(['element','form','layer','jquery','table','laypage'], function(){

        element = layui.element
        ,$ = layui.$
        ,layer = layui.layer
        ,form = layui.form
        ,table = layui.table
        ,laypage = layui.laypage;

        $("#show-frame").load(currPage);

        $('a').on('click', function(){
        // element.on("nav(filter)",function (elem){
            let nextPage = "";
            switch($(this).text()){
            // layer.msg(elem.text());
            // switch (elem.text()){
                case "公共公告":
                    nextPage = "/public"
                    break;
                case "个人公告":
                    nextPage = "/public"
                    break;
                case "用户信息":
                    nextPage = "/client"
                    break;
                case "设备信息":
                    nextPage = "/device?client_user="+client_user
                    break;
                case "水价位信息":
                    nextPage = "/public"
                    break;
                case "水费账单信息":
                    nextPage = "/public"
                    break;
                case "电价位信息":
                    nextPage = "/public"
                    break;
                case "电费账单信息":
                    nextPage = "/public"
                    break;
            }
            if(nextPage != "")
                if(currPage != nextPage){
                    currPage = nextPage;
                    $("#show-frame").load(currPage);
                }
        });

        element.init();
    });
</script>
</body>
</html>
