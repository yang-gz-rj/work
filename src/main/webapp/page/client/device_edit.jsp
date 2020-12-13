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
    <title>device_detail</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
</head>
<body>
<form class="layui-form" action="#" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">设备号</label>
        <div class="layui-input-block">
            <input type="text" id="device_number" name="device_number" readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" id="client_user" name="client_user" readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">
            <input type="text" id="device_type" name="device_type" readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">位数</label>
        <div class="layui-input-block">
            <input type="text" id="device_point" name="device_point" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">生产厂商</label>
        <div class="layui-input-block">
            <input type="text" id="device_producer" name="device_producer" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">使用年限</label>
        <div class="layui-input-block">
            <input type="text" id="device_durability" name="device_durability" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">生产日期</label>
            <div class="layui-input-inline">
                <input type="text" id="device_create_date" name="device_create_date" autocomplete="off" class="layui-input" >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="btn_submit">确认修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">回退</button>
        </div>
    </div>
</form>
<script src="/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(["form","layer","jquery","laydate"],function (){
        let form = layui.form
            ,layer = layui.layer
            ,$ = layui.$
            ,laydate = layui.laydate;
        laydate.render({
            elem: '#device_create_date'
            ,type: 'date'
            ,trigger: 'click'
        });
        form.on("submit(btn_submit)",function (data){
            var field = data.field;
            $.ajax({
                url: "/device/edit/filter"
                ,type: "POST"
                ,data: field
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
