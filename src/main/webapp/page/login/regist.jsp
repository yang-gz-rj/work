<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 11/30/2020
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <script src="/layui/layui.js" charset="utf-8"></script>
    <style>
        body{
            height: 100%;
            width: 100%;
            background-image: url("/image/back.jpg");
            background-size: 100% 100%;
        }
        #show-main{
            position: absolute;
            top: 15%;
            left: 35%;
        }
    </style>
</head>
<body>
<div id="show-main">
    <form class="layui-form" action="/guide" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="client_user" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" name="client_password" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="client_name" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="client_gender" value="男" title="男" checked="">
                <input type="radio" name="client_gender" value="女" title="女" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-block">
                <input type="tel" name="client_age" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="tel" name="client_phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">家庭地址</label>
            <div class="layui-input-block">
                <input type="text" name="client_address" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="regist">确认注册</button>
                <button type="reset" class="layui-btn layui-btn-primary">回退</button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use(['form','jquery','layer'], function(){
        var form = layui.form
            ,$ = layui.$
            ,layer = layui.layer;
        form.render();
        form.on("submit(regist)",function (data){
            var status = 0;
            $.ajax({
                type:"POST"
                ,url: "/regist/add"
                ,async: false
                ,xhrFields: {
                    withCredentials: true
                }
                ,data: data.field
                ,dataType:"json"
                ,success: function (response){
                    if(response.code != 200){
                        layer.msg("用户名已存在");
                    }else{
                        status = 1;
                    }
                }
                ,error: function (){
                    layer.msg("服务器繁忙");
                }
            });
            if(status == 1){
                return true;
            }else{
                return false;
            }
        });
    });
</script>
</body>
</html>