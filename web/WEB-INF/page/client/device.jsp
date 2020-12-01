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
    </head>
    <body>
        <table id="demo" lay-filter="test"></table>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="bill">账单</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
        <script>
            layui.use(["table","layer","laypage"], function(){
                var table = layui.table
                    ,layer = layui.layer
                    ,laypage = layui.laypage;

                var viewTable = table.render({
                    elem: "#demo"
                    ,url:"/device/json?client_user=${client_user}"
                    ,page: true
                    ,response: {
                        statusCode: 200
                    }
                    ,toolbar: "default"
                    ,cols: [[
                        {type: 'checkbox', fixed: 'left'}
                        ,{field:"device_number", width:80, title: "表号", sort: true}
                        ,{field:"client_user", width:120, title: "用户名"}
                        ,{field:"device_type", width:80, title: "表类型", sort: true}
                        ,{field:"device_point", width:80, title: "位数", sort: true}
                        ,{field:"device_producer", width:160, title: "生产厂商"}
                        ,{field:"device_create_date", width:160, title: "生产日期", sort: true}
                        ,{field:"device_durability", width:100, title: "使用年限"}
                        ,{fixed: "right", width:200, title: "操作",align:"center", toolbar: "#barDemo"}
                    ]]
                });

                //监听行工具事件
                table.on("tool(test)", function(obj){
                    var data = obj.data;
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
                            ,area: ["500px","600px"]
                            ,success: function (layero,index){
                                var body = layer.getChildFrame("body");
                                // TODO 显示设备详细信息
                            }
                            ,cancel: function (){

                            }
                        });
                    }else if(obj.event === "bill"){
                        // TODO 跳转账单
                    }
                });

                //监听头工具栏事件
                table.on('toolbar(test)', function(obj){
                    var checkStatus = table.checkStatus(obj.config.id)
                        ,data = checkStatus.data; //获取选中的数据
                    if(obj.event === 'add'){
                        layer.open({
                            type: 2
                            ,content: "/device/add"
                            ,area: ["500px","600px"]
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

                //分页
                laypage.render({
                    elem: 'pageDemo' //分页容器的id
                    ,count: 100 //总页数
                    ,skin: '#1E9FFF' //自定义选中色值
                    //,skip: true //开启跳页
                    ,jump: function(obj, first){
                        if(!first){
                            layer.msg('第'+ obj.curr +'页', {offset: 'b'});
                        }
                    }
                });

            });
        </script>

    </body>
</html>
