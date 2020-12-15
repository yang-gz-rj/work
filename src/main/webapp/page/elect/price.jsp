<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 12/5/2020
  Time: 9:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #price-body{
            height: 80%;
            width: 85%;
            position: absolute;
            top: 10%;
            left: 10%;
        }
    </style>
</head>
<div id="price-body">
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
            <button class="layui-btn layui-btn-sm" lay-event="del">多行删除</button>
        </div>
    </script>
    <table id="demo" lay-filter="test"></table>
    <div id="pageLimit" style="padding-left: 30%;padding-top: 0%;"></div>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script>

        const viewTable = table.render({
            elem: "#demo"
            ,url:"/elect/price/json"
            ,page: true
            ,response: {
                statusCode: 200
            }
            ,height: $(window).height()*0.80
            ,width: $(window).width()*0.75
            ,cellMinWidth: $(window).height()*0.80*0.1
            ,toolbar: "#toolbarDemo"
            ,cols: [[
                {type:"checkbox",fixed: "left"}
                ,{field:"elect_price_gradient", width:140,align:"center",title: "价位梯度", sort: true}
                ,{field:"elect_price_update_date", width:160, align:"center",title: "价位更新时间", sort: true
                    ,templet:function(data){
                        return layui.util.toDateString(data.elect_price_update_date, "yyyy-MM-dd");
                    }}
                ,{field:"admin_user", width:110, align:"center",title: "提供者", sort: true}
                ,{field:"elect_price_maximum", width:140, align:"center",title: "最大量度", sort: true}
                ,{field:"elect_price_dw", width:110, align:"center",title: "单位"}
                ,{field:"elect_price_unit_price", width:110, align:"center",title: "单价"}
                ,{fixed: "right", width:130, title: "操作",align:"center", toolbar: "#barDemo"}
            ]]
        });

        //监听行工具事件
        table.on("tool(test)", function(obj){
            const data = obj.data;
            if(obj.event === "del"){
                layer.confirm("真的删除行么", function(index){
                    $.ajax({
                        url: "/elect/price/delete",
                        type: "GET",
                        dataType: "json",
                        data: {
                            "gradient":data.elect_price_gradient
                            ,"update_date":layui.util.toDateString(data.elect_price_update_date, "yyyy-MM-dd")
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
            }else if(obj.event === 'edit'){
                layer.open({
                    type: 2
                    ,content: "/page/elect/price_edit.jsp"
                    ,area: ["480px","500px"]
                    ,offset: ["10%","40%"]
                    ,success: function (layero,index){
                        var body = layer.getChildFrame("body",index);
                        body.find("#elect_price_gradient").val(data.elect_price_gradient);
                        body.find("#elect_price_update_date").val(layui.util.toDateString(data.elect_price_update_date,"yyyy-MM-dd"));
                        body.find("#admin_user").val(data.admin_user);
                        body.find("#elect_price_maximum").val(data.elect_price_maximum);
                        body.find("#elect_price_dw").val(data.elect_price_dw);
                        body.find("#elect_price_unit_price").val(data.elect_price_unit_price);
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
                ,data = checkStatus.data;
            if(obj.event === 'add'){
                layer.open({
                    type: 2
                    ,content: "/page/elect/price_add.jsp"
                    ,area: ["480px","500px"]
                    ,offset: ["10%","40%"]
                    ,cancel: function (){
                        viewTable.reload();
                    }
                });
            }else if(obj.event === 'del'){
                if(data.length <= 0){
                    layer.msg("请选择至少一行");
                }else {
                    let length = data.length;
                    let flag;
                    for (let i = 0; i < length; i++) {
                        flag = 0;
                        $.ajax({
                            url: "/elect/price/delete",
                            type: "GET",
                            async: false,
                            xhrFields:{
                                withCredentials: true
                            },
                            dataType: "json",
                            data: {
                                "gradient":data[i].elect_price_gradient
                                ,"update_date":layui.util.toDateString(data[i].elect_price_update_date, "yyyy-MM-dd")
                            },
                            success: function(response){
                                if(response.code != 200){
                                    layer.msg("操作失败");
                                }
                            },
                            error: function(){
                                flag = 1;
                                layer.msg("服务器繁忙");
                            }
                        });
                        if (flag == 1) {
                            break;
                        }
                    }
                    viewTable.reload();
                    if (flag == 0) {
                        layer.msg("删除完成");
                    }
                }
            }
        });
    </script>

</div>
</html>


