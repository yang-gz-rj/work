<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 11/21/2020
  Time: 9:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>device</title>
        <link rel="stylesheet" href="/layui/css/layui.css" media="all">
        <script src="/layui/layui.all.js" charset="utf-8"></script>
        <style>
            #device-body{
                height: 80%;
                width: 70%;
                position: absolute;
                top: 8%;
                left: 25%;
            }
        </style>
    </head>
    <div id="device-body">
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
            </div>
        </script>
        <table id="demo" lay-filter="test"></table>
        <div id="pageLimit"></div>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="bill">账单</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
        <script>
            //执行一个laypage实例
            laypage.render({
                elem: 'pageLimit' //注意，这里的 test1 是 ID，不用加 # 号
                ,count: ${device_count}
                ,limit: 10
                ,jump: function(obj, first){
                    //首次不执行
                    if(!first){
                        viewTable.reload({
                            url: "/device/json?client_user=${client_user}&curr="+obj.curr+"&limit=10"
                        });
                    }
                }
            });

            const viewTable = table.render({
                elem: "#demo"
                ,url:"/device/json?client_user=${client_user}&curr=1&limit=10"
                ,page: false
                ,response: {
                    statusCode: 200
                }
                ,toolbar: "#toolbarDemo"
                ,cols: [[
                    {field:"device_number", width:80,align:"center",title: "表号", sort: true}
                    ,{field:"client_user", width:120, align:"center",title: "用户名"}
                    ,{field:"device_type", width:80, align:"center",title: "表类型", sort: true}
                    ,{field:"device_point", width:80, align:"center",title: "位数", sort: true}
                    ,{field:"device_producer", width:140, align:"center",title: "生产厂商"}
                    ,{field:"device_create_date", width:130, align:"center",title: "生产日期", sort: true}
                    ,{field:"device_durability", width:110, align:"center",title: "使用年限",sort: true}
                    ,{fixed: "right", width:200, title: "操作",align:"center", toolbar: "#barDemo"}
                ]]
            });

            //监听行工具事件
            table.on("tool(test)", function(obj){
                const data = obj.data;
                if(obj.event === "del"){
                    layer.confirm("真的删除行么", function(index){
                        layui.$.ajax({
                            url: "/device/delete",
                            type: "GET",
                            dataType: "json",
                            data: {
                                "device_number":data.device_number
                            },
                            success: function(response){
                                if(response.code == 200){
                                    obj.del();
                                    layer.close(index);
                                    viewTable.reload();
                                    layer.msg("操作成功");
                                }else{
                                    layer.close(index);
                                    layer.msg("操作失败");
                                }
                            },
                            error: function(){
                                layer.close(index);
                                layer.msg("服务器繁忙");
                            }
                        });
                    });
                } else if(obj.event === "detail"){
                    layer.open({
                        type: 2
                        ,content: "/device/detail"
                        ,area: ["450px","500px"]
                        ,success: function (layero,index){
                            var body = layer.getChildFrame("body",index);
                            body.find("#device_number").val(data.device_number);
                            body.find("#client_user").val(data.client_user);
                            body.find("#device_type").val(data.device_type);
                            body.find("#device_point").val(data.device_point);
                            body.find("#device_producer").val(data.device_producer);
                            body.find("#device_create_date").val(data.device_create_date);
                            body.find("#device_durability").val(data.device_durability);
                        }
                    });
                }else if(obj.event === "bill"){
                    // TODO 跳转账单
                }
            });

            //监听头工具栏事件
            table.on('toolbar(test)', function(obj){
                const checkStatus = table.checkStatus(obj.config.id)
                    ,data = checkStatus.data; //获取选中的数据
                if(obj.event === 'add'){
                    layer.open({
                        type: 2
                        ,content: "/device/add"
                        ,area: ["450px","500px"]
                        ,success: function (layero,index){
                            var body = layer.getChildFrame("body",index);
                            body.find("#client_user").attr("value","${client_user}");
                        }
                        ,cancel: function (){
                            viewTable.reload();
                        }
                    });
                }else if(obj.event === 'delete'){
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        layer.msg('删除');
                    }
                }
            });
        </script>

    </div>
</html>
