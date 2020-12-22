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
        <style>
            #device-body{
                height: 80%;
                width: 85%;
                position: absolute;
                top: 10%;
                left: 10%;
            }
        </style>
    </head>
    <div id="device-body">
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
                <button class="layui-btn layui-btn-sm" lay-event="del">多行删除</button>
            </div>
        </script>
        <table id="demo" lay-filter="test"></table>
        <div id="pageLimit" style="padding-left: 30%;padding-top: 0%;"></div>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="bill">账单</a>
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
        <script>

            const viewTable = table.render({
                elem: "#demo"
                ,url:"/device/json"
                ,page: true
                ,response: {
                    statusCode: 200
                }
                ,height: $(window).height()*0.80
                ,width: $(window).width()*0.75
                ,cellMinWidth: $(window).height()*0.80*0.1
                ,toolbar: "#toolbarDemo"
                ,cols: [[
                    {type: "checkbox",fixed: 'left'}
                    ,{field:"device_number", width:80,align:"center",title: "表号", sort: true}
                    ,{field:"client_user", width:120, align:"center",title: "用户名"}
                    ,{field:"device_type", width:80, align:"center",title: "表类型", sort: true}
                    ,{field:"device_point", width:80, align:"center",title: "位数", sort: true}
                    ,{field:"device_producer", width:140, align:"center",title: "生产厂商"}
                    ,{field:"device_create_date", width:130, align:"center",title: "生产日期", sort: true
                    ,templet:function(data){
                        return layui.util.toDateString(data.device_create_date, "yyyy-MM-dd");
                    }}
                    ,{field:"device_durability", width:110, align:"center",title: "使用年限",sort: true}
                    ,{fixed: "right", width:230, title: "操作",align:"center", toolbar: "#barDemo"}
                ]]
            });

            //监听行工具事件
            table.on("tool(test)", function(obj){
                const data = obj.data;
                if(obj.event === "del"){
                    layer.confirm("真的删除行么", function(index){
                        $.ajax({
                            url: "/device/delete",
                            type: "GET",
                            dataType: "json",
                            data: {
                                "device_number":data.device_number
                                ,"type":data.device_type
                            },
                            success: function(response){
                                if(response.code == 200){
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
                        ,content: "/page/client/device_detail.jsp"
                        ,area: ["400px","450px"]
                        ,success: function (layero,index){
                            var body = layer.getChildFrame("body",index);
                            body.find("#device_number").val(data.device_number);
                            body.find("#client_user").val(data.client_user);
                            body.find("#device_type").val(data.device_type);
                            body.find("#device_point").val(data.device_point);
                            body.find("#device_producer").val(data.device_producer);
                            body.find("#device_create_date").val(layui.util.toDateString(data.device_create_date, "yyyy-MM-dd"));
                            body.find("#device_durability").val(data.device_durability);
                        }
                    });
                }else if(obj.event === "bill"){
                    // 跳转水费账单
                    if(data.device_type == '水表'){
                        currPage = "/water/bill?device_number="+data.device_number;
                    //  跳转电费账单
                    }else{
                        currPage = "/elect/bill?device_number="+data.device_number;
                    }
                    $("#show-frame").load(currPage);
                }else if(obj.event === 'edit'){
                    layer.open({
                        type: 2
                        ,content: "/page/client/device_edit.jsp"
                        ,area: ["400px","500px"]
                        ,success: function (layero,index){
                            var body = layer.getChildFrame("body",index);
                            body.find("#device_number").val(data.device_number);
                            body.find("#client_user").val(data.client_user);
                            body.find("#device_type").val(data.device_type);
                            body.find("#device_point").val(data.device_point);
                            body.find("#device_producer").val(data.device_producer);
                            body.find("#device_create_date").val(layui.util.toDateString(data.device_create_date, "yyyy-MM-dd"));
                            body.find("#device_durability").val(data.device_durability);
                        }
                        ,cancel: function (){
                            viewTable.reload();
                        }
                    });
                }
            });

            //监听头工具栏事件
            table.on('toolbar(test)', function(obj){
                const checkStatus = table.checkStatus(obj.config.id)
                    ,data = checkStatus.data; //获取选中的数据
                if(obj.event === 'add'){
                    layer.open({
                        type: 2
                        ,content: "/page/client/device_add.jsp"
                        ,area: ["450px","500px"]
                        ,success: function (layero,index){
                            var body = layer.getChildFrame("body",index);
                            body.find("#client_user").attr("value","${client.client_user}");
                        }
                        ,cancel: function (){
                            viewTable.reload();
                        }
                    });
                }else if(obj.event === 'del'){
                    if(data.length <= 0){
                        layer.msg("请选择至少一行");
                    }else{
                        let length = data.length;
                        let flag;
                        for(let i=0; i<length; i++){
                            flag = 0;
                            $.ajax({
                                url: "/device/delete",
                                type: "GET",
                                dataType: "json",
                                async: false,
                                xhrFields:{
                                  withCredentials: true
                                },
                                data: {
                                    "device_number":data[i].device_number
                                    ,"type":data[i].device_type
                                },
                                success: function(response){
                                    if(response.code != 200){
                                        layer.msg("删除"+data[i].device_number+"失败");
                                    }
                                },
                                error: function(){
                                    layer.msg("服务器繁忙");
                                    flag = 1;
                                }
                            });
                            if(flag == 1) {
                                break;
                            }
                        }
                        viewTable.reload();
                        if(flag == 0){
                            layer.msg("删除完成");
                        }
                    }
                }
            });
        </script>

    </div>
</html>
