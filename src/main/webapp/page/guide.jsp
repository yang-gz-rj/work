<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>水电缴费系统</title>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <style>
        img{
            height: 15px;
            width: 15px;
            padding-right: 10px;
        }
        #show-right{
            position: absolute;
            top: 0%;
            left: 15%;
            width: 85%;
            height: 100%;
            background-color: #eeeeee;
            opacity:0.95;
            filter:alpha(opacity=95); /* 针对 IE8 以及更早的版本 */
        }
        #show-icon{
            height: 7%;
            width: 15%;
            background-image: url("/image/guide.png");
            background-size: 100% 100%;
            background-color: #eeeeee;
        }
    </style>
</head>
<body style="width: 100%;height: 100%;">
<div id="show-icon" class="layui-nav-item"></div>
<ul class="layui-nav layui-nav-tree" lay-filter="guide-filter" id="show-guide" style="position: absolute;
    left: 0%; top: 7%; height: 93%;width: 15%;">
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
            <dd id="dd-user" style="padding-left: 20%;"><a href="javascript:;">用户信息</a></dd>
            <dd id="dd-device" style="padding-left: 20%;"><a href="javascript:;">设备信息</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:;"><img src="/image/water.png"/>水费管理</a>
        <dl class="layui-nav-child">
            <dd style="padding-left: 20%;"><a href="javascript:;" >水价位信息</a></dd>
            <dd id="dd-water-bill" style="padding-left: 20%;"><a href="javascript:;" >水费账单信息</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item" show="true">
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
    <ul class="layui-nav layui-nav-tree" id="show-user" style="position: absolute;top: 0%; left: 0%;
        background: #F0F0F0; width: 100%;height: 7%;">
        <form class="layui-form" style="position: absolute; left: 5%; top: 20%; height: 60%; width: 60%; color: black;">
            <label class="layui-form-label">模块</label>
            <div class="layui-input-block" style="position: absolute; left: 10%; top: 0%; height: 100%;width: 25%;">
                <select name="city" lay-verify="" >
                    <option value="">请选择一个模块</option>
                    <option value="010">用户信息</option>
                    <option value="021">设备信息</option>
                    <option value="0571">水费账单信息</option>
                    <option value="0571">水费价位信息</option>
                </select>
            </div>
            <label class="layui-form-label">字段</label>
            <div class="layui-input-block" style="position: absolute; left: 37%; top: 0%; height: 100%;width: 25%;">
                <select name="city" lay-verify="" >
                    <option value="">请选择一个字段</option>
                    <option value="010">北京</option>
                    <option value="021">上海</option>
                    <option value="0571">杭州</option>
                </select>
            </div>
            <input type="text" name="title" style="position: absolute; left: 64%; top: 0%; height: 100%;width: 25%;" required lay-verify="required" placeholder="请输入搜索内容" autocomplete="off" class="layui-input">
        </form>
        <li class="layui-nav-item" style="float: right;width: 8%;height: 100%;background: ">
            <a href="javascript:;" style="text-align: center;line-heigh: 100%;height: 100%;background: #4E5465;"><img src="/image/user.png">${pageContext.request.getParameter('client_user')}</a>
            <dl class="layui-nav-child">
                <dd style="text-align: center;" onclick="logout()">注销</dd>
                <dd style="text-align: center;" onclick="quit()">退出</dd>
            </dl>
        </li>
    </ul>
    <hr style="position: absolute; left: 0%; top: 5.7%; height:1; width: 100%;background: #555555;"/>
    <%-- 显示动态页面 --%>
    <div id="show-frame" style="float: left;width: 100%;height: 90%;"></div>
</div>
<script src="/layui/layui.js" charset="UTF-8"></script>
<script>
    function quit(){
        window.location.href = "/";
    }
    function logout(){
        $.ajax({
            type: "POST"
            ,url: "/client/delete"
            ,async: false
            ,xhrFields:{
                withCredentials: true
            }
            ,data: {
                "client_user": client_user
            }
            ,dataType: "json"
            ,success: function (response){
                if(response.code == 200){
                    window.location.href = "/";
                }else{
                    layer.msg("服务器繁忙");
                }
            }
            ,error: function (){
                layer.msg("服务器繁忙");
            }
        });
    }
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
        $("#dd-user").attr("style","padding-left: 20%;background-color:#009688;");

        $('a').on('click', function(){
            let nextPage = "";
            switch($(this).text()){
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
                    nextPage = "/water/price"
                    break;
                case "水费账单信息":
                    nextPage = "/water/bill?bill_type=all&client_user="+client_user
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
                    if(currPage != "/client"){
                        $("#dd-user").attr("style","padding-left: 20%;");
                    }
                    $("#show-frame").load(currPage);
                }
        });

    });
</script>
</body>
</html>
