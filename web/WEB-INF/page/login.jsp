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
  </head>
  <body>
    <form class="layui-form" action="/show/client.pg" method="post">
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
          <button class="layui-btn" lay-submit>立即提交</button>
          <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
    <script>
      layui.use('form', function(){
        var form = layui.form;
        form.render();
      });
    </script>
  </body>
</html>
