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
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">价位梯度</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_price_gradient" name="elect_price_gradient" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">价位更新时间</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_price_update_date" name="elect_price_update_date" class="layui-input demo">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">更新者</label>
            <div class="layui-input-inline">
                <input type="text" name="admin_user" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">最大量度</label>
            <div class="layui-input-inline">
                <input type="text" name="elect_price_maximum" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">单位</label>
            <div class="layui-input-inline">
                <input type="text" name="elect_price_dw"  autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">单价</label>
            <div class="layui-input-inline">
                <input type="text" name="elect_price_unit_price" autocomplete="off" class="layui-input" >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline" style="position: absolute;left: 30%;">
            <button class="layui-btn" lay-submit lay-filter="btn_submit">确认添加</button>
            <button type="reset" class="layui-btn layui-btn-primary">回退</button>
        </div>
    </div>
</form>
<script src="/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form','layer','laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,laydate = layui.laydate;

        laydate.render({
            elem: '.demo'
            ,type: 'date'
            ,trigger: 'click'
        });

        form.on("submit(btn_submit)",function (data){
            layui.$.ajax({
                url: "/elect/price/add"
                ,type: "POST"
                ,data: data.field
                ,dataType: "json"
                ,response: {
                    statusCode: 200
                }
                ,success: function (response){
                    if (response.code == 200)
                        layer.msg("添加成功");
                    else
                        layer.msg("添加失败");
                }
                ,error: function (){
                    layer.msg("添加失败");
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
