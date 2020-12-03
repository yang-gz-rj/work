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
    <title>login</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <script src="/layui/layui.js" charset="utf-8"></script>
    <style>
      body{
        height: 100%;
        width: 100%;
        background-image: url("/image/back.png");
        background-size: 100% 100%;
      }
      #show-main{
        position: absolute;
        top: 30%;
        left: 35%;
      }
    </style>
  </head>
  <body>

  <div id="show-main">
    <form class="layui-form" action="/guide" method="GET">
      <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
          <input type="text" name="client_user" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
          <input type="password" name="client_password" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
      </div>
      <div class="layui-form-item">
        <div class="layui-input-block">
          <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
          <button type="reset" class="layui-bg-green layui-btn layui-btn-primary">重置</button>
        </div>
      </div>
    </form>

    <form class="layui-form" action="/regist" method="post">
      <div class="layui-form-item">
        <div class="layui-input-block">
          <button class="layui-btn" lay-submit>注册</button>
        </div>
      </div>
    </form>
  </div>
  <script>
    layui.use(['form','layer'], function(){
      const form = layui.form;
        layer = layui.layer;
      form.on("submit(*)",function (data){
        let status=0;
        layui.$.ajax({
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
              }else{
                layer.msg("用户名密码错误");
              }
            }
            ,error: function (){
              layer.msg("服务器错误");
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
