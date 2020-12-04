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
    <title>device_add</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <style>
        form{
            position: absolute;
            left: 10%;
            top: 5%;
        }
    </style>
</head>
<body>
<form class="layui-form" action="#" method="post">
    <input type="hidden" name="client_user" value="${pageContext.request.getParameter('client_user')}"/>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">账单号</label>
            <div class="layui-input-inline">
                <input type="text" id="water_bill_number" name="water_bill_number" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">设备号</label>
            <div class="layui-input-inline">
                <input type="text" id="device_number" name="device_number" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">梯度</label>
            <div class="layui-input-inline">
                <input type="text" name="water_price_gradient" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">价位更新时间</label>
            <div class="layui-input-inline">
                <input type="text" name="water_price_update_date" autocomplete="off" class="layui-input demo">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">初始读数</label>
            <div class="layui-input-inline">
                <input type="text" name="water_bill_init_value"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">现在读数</label>
            <div class="layui-input-inline">
                <input type="text" name="water_bill_now_value" autocomplete="off" class="layui-input" >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">单位</label>
            <div class="layui-input-inline">
                <input type="text" name="water_bill_r_dw" autocomplete="off" class="layui-input" >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">出账日期</label>
            <div class="layui-input-inline">
                <input type="text" name="water_bill_output_date" autocomplete="off" class="layui-input demo" >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">应付费用</label>
            <div class="layui-input-inline">
                <input type="text" name="water_bill_fee" autocomplete="off" class="layui-input" >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">缴费时间</label>
            <div class="layui-input-inline">
                <input type="text" name="water_bill_pay_date" autocomplete="off" class="layui-input demo" >
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
            var field = data.field;
            layui.$.ajax({
                url: "/water/bill/add"
                ,type: "POST"
                ,data: field
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
