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
                <input type="text" id="elect_bill_number" name="elect_bill_number" readonly autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">设备号</label>
            <div class="layui-input-inline">
                <input type="text" id="device_number" name="device_number" readonly class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">梯度</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_price_gradient" name="elect_price_gradient" readonly autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">价位更新时间</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_price_update_date" name="elect_price_update_date" readonly autocomplete="off" class="layui-input demo">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">初始读数</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_bill_init_value" name="elect_bill_init_value" readonly  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">现在读数</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_bill_now_value" name="elect_bill_now_value" readonly autocomplete="off" class="layui-input" >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">单位</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_bill_r_dw" name="elect_bill_r_dw" readonly autocomplete="off" class="layui-input" >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">出账日期</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_bill_output_date" name="elect_bill_output_date" readonly autocomplete="off" class="layui-input demo" >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">应付费用</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_bill_fee" name="elect_bill_fee" readonly autocomplete="off" class="layui-input" >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">缴费时间</label>
            <div class="layui-input-inline">
                <input type="text" id="elect_bill_pay_date" name="elect_bill_pay_date" readonly autocomplete="off" class="layui-input demo" >
            </div>
        </div>
    </div>
</form>
<script src="/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function(){
        var form = layui.form;
    });
</script>
</body>
</html>
