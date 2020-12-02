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
    <script src="/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<form class="layui-form" action="#" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">设备号</label>
        <div class="layui-input-block">
            <input type="text" name="device_number"  autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" id="client_user" name="client_user" readonly class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">
            <input type="radio" name="device_type" value="水表" title="水表" checked="">
            <input type="radio" name="device_type" value="电表" title="电表" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">位数</label>
        <div class="layui-input-block">
            <input type="text" name="device_point" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">生产厂商</label>
        <div class="layui-input-block">
            <input type="text" name="device_producer" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">使用年限</label>
        <div class="layui-input-block">
            <input type="text" name="device_durability"  autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">生产日期</label>
            <div class="layui-input-inline">
                <input type="text" name="device_create_date" autocomplete="off" class="layui-input" id="demo" placeholder="yyyy-MM-dd">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="btn_submit">确认添加</button>
            <button type="reset" class="layui-btn layui-btn-primary">回退</button>
        </div>
    </div>
</form>
<script>
    layui.use(['form','layer','laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,laydate = layui.laydate;

        laydate.render({
            elem: '#demo'
            ,type: 'date'
            ,trigger: 'click'
        });

        form.on("submit(btn_submit)",function (data){
            var field = data.field;
            layui.$.ajax({
                url: "/device/add/filter"
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
