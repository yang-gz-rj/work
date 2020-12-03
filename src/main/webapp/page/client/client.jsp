<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 11/29/2020
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <script src="/layui/layui.js" charset="utf-8"></script>
</head>
<body>
    <form class="layui-form" action="/client/alter" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="client_user" value="${client.client_user}" readonly autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" name="client_password" value="${client.client_password}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="client_name" value="${client.client_name}" readonly autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" id="boy" name="client_gender" value="男" title="男" >
                <input type="radio" id="girl" name="client_gender" value="女" title="女" >
            </div>
            <script>
                var client_gender = '${client.client_gender}';
                if(client_gender == '男'){
                    document.getElementById('boy').setAttribute('checked','');
                }else{
                    document.getElementById('girl').setAttribute('checked','');
                }
            </script>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-block">
                <input type="tel" name="client_age" value="${client.client_age}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="tel" name="client_phone" value="${client.client_phone}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">家庭地址</label>
            <div class="layui-input-block">
                <input type="text" name="client_address" value="${client.client_address}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">余额</label>
            <div class="layui-input-block">
                <input type="text" name="client_money" value="${client.client_money}" readonly autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="btn_submit">确认修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">回退</button>
            </div>
        </div>
    </form>
    <script>
        layui.use('form', function(){
            let form = layui.form;
            form.render();
            form.on("submit(btn_submit)",function (data){

            });
        });
    </script>
</body>
</html>
