<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 12/1/2020
  Time: 9:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>device_detail</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <script src="/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<form class="layui-form" action="#" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">设备号</label>
        <div class="layui-input-block">
            <input type="text" id="device_number" readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" id="client_user" readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">
            <input type="text" id="device_type" readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">位数</label>
        <div class="layui-input-block">
            <input type="text" id="device_point" readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">生产厂商</label>
        <div class="layui-input-block">
            <input type="text" id="device_producer" readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">使用年限</label>
        <div class="layui-input-block">
            <input type="text" id="device_durability" readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">生产日期</label>
            <div class="layui-input-inline">
                <input type="text" id="device_create_date" readonly autocomplete="off" class="layui-input" >
            </div>
        </div>
    </div>
</form>
<script>
    layui.use("form",function (){

    });
</script>
</body>
</html>
