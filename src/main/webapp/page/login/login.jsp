<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 11/28/2020
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
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
        top: 25%;
        left: 40%;
        width: 20%;
        height: 30%;
        background: #f8f8f8;
        border: 1px solid #f8f8f8;
      }
      form div{
        float: left;
      }
    </style>
  </head>
  <body>

  <div id="show-main">
    <form class="layui-form" action="/guide" method="POST">
      <div style="width: 100%;">
          <input type="text" name="client_user" placeholder="请输入用户名" class="layui-input"/>
      </div>
      <div style="width: 100%; padding-top: 4%;">
        <input type="text" name="client_password" placeholder="请输入密码" class="layui-input"/>
      </div>
      <div style="width: 100%; padding-top: 4%;">
        <input type="text" name="verifyCode" style="width: 70%;float:left;" placeholder="请输入验证码" class="layui-input"/>
        <img id="verifyCodeImg" style="width: 30%;float: left;" alt="点击更换" src="/guide/verify"
             title="点击更换" onclick="change()">
      </div>
      <div style="width: 100%; padding-top: 4%;">
        <button class="layui-btn" lay-submit lay-filter="login" style="float: left;margin-left: 5%;">立即提交</button>
        <button type="reset" class="layui-bg-green layui-btn layui-btn-primary" style="float: right;margin-right: 5%;">重置</button>
      </div>
      <div style="width: 100%; padding-top: 4%;">
        <span style="float: left;">忘记密码?</span>
        <span id="regist" style="float: right;cursor: grabbing;">立即注册</span>
      </div>
    </form>
  </div>
  <script>

    function change() {
      var verifyCode = document.getElementById("verifyCodeImg");
      verifyCode.src = "/guide/verify?time=" + Math.random(1000);
    }

    layui.use(['form','layer','jquery'], function(){
      const form = layui.form;
        layer = layui.layer;

      let $ = layui.$;

      $("#regist").on('click',function (){
        window.location.href = "/regist";
      });

      form.on("submit(login)",function (data){
        let status=0;
        $.ajax({
            type: "GET"
            ,url: "/client/filter"
            ,xhrFields: {
              withCredentials: true
            }
            ,data:data.field
            ,async: false // 同步
            ,dataType:"json"
            ,success: function (response){
              if(response.code == 200){
                status = 1;
              }else if(response.code == 400) {
                layer.msg("验证码错误");
              }else{
                layer.msg("用户名密码错误");
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
