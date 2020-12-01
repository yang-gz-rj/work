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
        <title>Title</title>
        <link rel="stylesheet" href="/layui/css/layui.css" media="all">
        <script src="/layui/layui.all.js" charset="utf-8"></script>
    </head>
    <body>
        <table id="demo" lay-filter="test"></table>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="detail">查看账单</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
        <script>
            layui.use("table", function(){
                var table = layui.table;

                var viewTable = table.render({
                    elem: "#demo"
                    ,url:"/device/json?client_user=${client_user}"
                    ,page: false
                    ,response: {
                        statusCode: 200 //规定成功的状态码，默认：0
                    }
                    ,toolbar: "#toolbarDemo" //开启头部工具栏，并为其绑定左侧模板
                    ,cols: [[
                        {field:"device_number", width:80, title: "表号", sort: true}
                        ,{field:"client_user", width:120, title: "用户名"}
                        ,{field:"device_type", width:80, title: "表类型"}
                        ,{field:"device_point", width:80, title: "位数"}
                        ,{field:"device_producer", width:160, title: "生产厂商"}
                        ,{field:"device_create_date", width:160, title: "生产日期"}
                        ,{field:"device_durability", width:80, title: "使用年限"}
                        ,{fixed: "right", width:200, title: "操作",align:"center", toolbar: "#barDemo"}
                    ]]
                });

                //监听行工具事件
                table.on("tool(test)", function(obj){
                    var data = obj.data;
                    //console.log(obj)
                    if(obj.event === "del"){
                        layer.confirm("真的删除行么", function(index){
                            layui.$.ajax({
                                url: "/device/delete",
                                type: "GET",
                                dataType: "json",
                                data: {
                                    "type":"delete",
                                    "sno":data.sno
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
                            type: 2,
                            content: "detail.jsp",
                            area: ["500px","300px"],
                            success: function(layero, index){
                                const body = layer.getChildFrame("body",index);
                                body.find("#sname").val(data.sname);
                                body.find("#sno").val(data.sno);
                                body.find("#ssex").val(data.ssex);
                            }
                        });
                    }
                });
            });
        </script>

    </body>
</html>
