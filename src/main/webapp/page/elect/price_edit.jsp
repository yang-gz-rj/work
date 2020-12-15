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
    <title>price_add</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <style>
        form{
            position: absolute;
            left: 15%;
            top: 5%;
        }
    </style>
</head>
<body>
<form class="layui-form" action="#" method="post">
    <input type="hidden" name="client_user" value="${client.client_user}"/>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">价位梯度</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_price_gradient" readonly name="elect_price_gradient" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">价位更新时间</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_price_update_date" readonly name="elect_price_update_date" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">更新者</label>
            <div class="layui-input-inline">
                <input type="text" name="admin_user" id="admin_user" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">最大量度</label>
            <div class="layui-input-inline">
                <input type="text" name="elect_price_maximum" id="elect_price_maximum" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">单位</label>
            <div class="layui-input-inline">
                <input type="text" name="elect_price_dw" id="elect_price_dw"  autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">单价</label>
            <div class="layui-input-inline">
                <input type="text" name="elect_price_unit_price" id="elect_price_unit_price" autocomplete="off" class="layui-input" >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline" style="position: absolute;left: 30%;">
            <button class="layui-btn" lay-submit lay-filter="btn_submit">确认修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">回退</button>
        </div>
    </div>
</form>
<script src="/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form','layer','laydate','jquery'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,laydate = layui.laydate
            ,$ = layui.$;

        form.on("submit(btn_submit)",function (data){
            $.ajax({
                url: "/elect/price/edit"
                ,type: "POST"
                ,data: data.field
                ,dataType: "json"
                ,response: {
                    statusCode: 200
                }
                ,success: function (response){
                    if (response.code == 200)
                        layer.msg("修改成功");
                    else
                        layer.msg("修改失败");
                }
                ,error: function (){
                    layer.msg("服务器繁忙");
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
