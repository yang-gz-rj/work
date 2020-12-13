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
        #show-logo{
            height: 10%;
            width: 15%;
            background-size: 100% 100%;
            background-color: #393D49;
            text-align: center;
            color: #ffffff;
            line-height: 65px;
            font-size: 20px;
        }
    </style>
</head>
<body style="width: 100%;height: 100%;">
<div id="show-logo" class="layui-nav-item layui-bg-cyan">
    水电缴费系统
</div>
<ul class="layui-nav layui-nav-tree" lay-filter="guide-filter" id="show-guide" style="position: absolute;
    left: 0%; top: 10%; height: 90%;width: 15%;">
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
    <%-- 查找表单 --%>
    <ul class="layui-nav layui-nav-tree" id="show-user" style="position: absolute;top: 0%; left: 0%;
        background: #F0F0F0; width: 85%;height: 10%;">
        <form class="layui-form" style="position: absolute; left: 2%; top: 20%; height: 60%; width: 60%; color: black;">
            <div class="layui-input-block" style="position: absolute; left: 0%; top: 0%; height: 100%;width: 25%;">
                <select id="select_model" name="model" lay-verify="" lay-filter="select_model">
                    <option value="">请选择一个模块</option>
                    <option value="/device">设备信息</option>
                    <option value="/water/bill">水费账单信息</option>
                    <option value="/water/price">水费价位信息</option>
                </select>
            </div>
            <div class="layui-input-block" style="position: absolute; left: 27%; top: 0%; height: 100%;width: 25%;">
                <select name="column" lay-verify="" id="select_column" lay-filter="select_column">
                    <option value="">请选择一个字段</option>
                </select>
            </div>
            <div class="layui-input-block" style="position: absolute; left: 54%; top: 0%; height: 100%;width: 25%;">
                <input type="text" id="search-input" name="input"  required lay-verify="required" placeholder="请选择模块和字段" autocomplete="off" class="layui-input">
            </div>
        </form>
    </ul>
    <%-- 用户显示 --%>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                ${client.client_user}
            </a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;" onclick="logout()">注销账户</a></dd>
                <dd><a href="javascript:;" onclick="quit()">退出登录</a></dd>
            </dl>
        </li>
    </ul>
<%--    <ul class="layui-nav" style="position: absolute;right: 0%;width: 15%;height: 10%;">--%>
<%--        <li class="layui-nav-item">--%>
<%--            <a href=""><img src="/image/user.png" class="layui-nav-img">${client.client_user==null?"请登录":client.client_user}</a>--%>
<%--            <dl class="layui-nav-child">--%>
<%--                <dd><a href="javascript:;" onclick="logout()">注销账户</a></dd>--%>
<%--                <dd><a href="javascript:;" onclick="quit()">退出登录</a></dd>--%>
<%--            </dl>--%>
<%--        </li>--%>
<%--    </ul>--%>
    <%-- 分割线 --%>
    <hr style="position: absolute; left: 0%; top: 8.5%; height:1px; width: 100%;background: #555555;"/>
    <%-- 显示动态页面 --%>
    <div id="show-frame" style="float: left;width: 100%;height: 90%;"></div>
</div>
<script src="/layui/layui.js" charset="UTF-8"></script>
<script>
    let currPage = "/client";
    // 加载所有模块
    let element,form,layer,$,table,laypage;

    layui.use(['element','form','layer','jquery','table','laypage'], function(){

        element = layui.element
        ,$ = layui.$
        ,layer = layui.layer
        ,form = layui.form
        ,table = layui.table
        ,laypage = layui.laypage;

        // 初始化页面为client
        $("#show-frame").load(currPage);
        // 设置css
        $("#dd-user").attr("style","padding-left: 20%;background-color:#009688;");

        // 监听模块选择
        form.on('select(select_model)', function(data){
            switch (data.value){
                case "/device":
                    select_device();
                    break;
                case "/water/bill":
                    select_water_bill();
                    break;
                case "/water/price":
                    select_water_price();
                    break;
            }

            form.render();
        });

        // 监听字段选择
        form.on('select(select_column)', function(data){
            if(data.value != ""){
                if(data.value.endsWith("date")){
                    $("#search-input").attr("placeholder","请输入时间:yyyy-MM-dd");
                }else{
                    $("#search-input").attr("placeholder","请输入搜索内容");
                }
            }
        });

        // 监听搜索框回车
        $('#search-input').on('keyup',function (event){
            if(event.keyCode === 13){
                if($("#select_model").val() == ""){
                    layer.msg("请选择模型");
                }else if($("#select_column").val() == ""){
                    layer.msg("请选择字段");
                }else{
                    // TODO 查找路径
                    //       /device?colunm=*&input=*
                    let nextPage = $("#select_model").val()+"?column="+$("#select_column").val()+"&input="+$("#search-input").val();
                    if(currPage != nextPage){
                        currPage = nextPage;
                    }
                    $("#show-frame").load(currPage);
                }
            }
        });

        // 监听左侧导航，进行相应跳转
        $('a').on('click', function(){
            let nextPage = "";
            switch($(this).text()){
                case "公共公告":
                    nextPage = "/public";
                    break;
                case "个人公告":
                    nextPage = "/public";
                    break;
                case "用户信息":
                    nextPage = "/client";
                    break;
                case "设备信息":
                    nextPage = "/device";
                    break;
                case "水价位信息":
                    nextPage = "/water/price";
                    break;
                case "水费账单信息":
                    nextPage = "/water/bill";
                    break;
                case "电价位信息":
                    nextPage = "/public";
                    break;
                case "电费账单信息":
                    nextPage = "/public";
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

    // 退出登录
    function quit(){
        window.location.href = "/exit";
    }

    // 注销用户
    function logout(){
        layer.confirm("确定注销账户吗？", function(index){
            let flag = 0;
            $.ajax({
                type: "GET"
                ,url: "/client/delete"
                ,async: false
                ,xhrFields:{
                    withCredentials: true
                }
                ,dataType: "json"
                ,success: function (response){
                    if(response.code != 200){
                        flag = 1;
                        layer.msg("服务器繁忙");
                    }
                }
                ,error: function (){
                    flag = 1;
                    layer.msg("服务器繁忙");
                }
            });
            if(flag == 0){
                quit();
            }
        });

    }

    function select_device(){
        let options = "<option value=\"\">请选择一个字段</option>";
        options += "<option value=\"device_number\">表号</option>";
        options += "<option value=\"device_type\">表类型</option>";
        options += "<option value=\"device_point\">位数</option>";
        options += "<option value=\"device_producer\">生产厂商</option>";
        options += "<option value=\"device_create_date\">生产日期</option>";
        options += "<option value=\"device_durability\">使用年限</option>";

        $("#select_column").html(options);
    }

    function select_water_bill(){
        let options = "<option value=\"\">请选择一个字段</option>";
        options += "<option value=\"water_bill_number\">账单号</option>";
        options += "<option value=\"device_number\">表号</option>";
        options += "<option value=\"water_price_gradient\">价位梯度</option>";
        options += "<option value=\"water_price_update_date\">价位更新时间</option>";
        options += "<option value=\"water_bill_init_value\">初始读数</option>";
        options += "<option value=\"water_bill_now_value\">现在读数</option>";
        options += "<option value=\"water_bill_output_date\">出账时间</option>";
        options += "<option value=\"water_bill_fee\">应缴费用</option>";
        options += "<option value=\"water_bill_pay_date\">缴费时间</option>";

        $("#select_column").html(options);
    }

    function select_water_price(){
        let options = "<option value=\"\">请选择一个字段</option>";
        options += "<option value=\"water_price_gradient\">价位梯度</option>";
        options += "<option value=\"water_price_update_date\">价位更新时间</option>";
        options += "<option value=\"admin_user\">提供者</option>";
        options += "<option value=\"water_price_maximum\">最大量度</option>";
        options += "<option value=\"water_price_dw\">单位</option>";
        options += "<option value=\"water_price_unit_price\">单价</option>";

        $("#select_column").html(options);
    }

</script>
</body>
</html>
