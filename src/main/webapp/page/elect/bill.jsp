<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 12/4/2020
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #bill-body{
            height: 80%;
            width: 85%;
            position: absolute;
            top: 10%;
            left: 10%;
        }
    </style>
</head>
<div id="bill-body">
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
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script>

        const viewTable = table.render({
            elem: "#demo"
            ,url:"/elect/bill/json"
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
                ,{field:"elect_bill_number", width:100,align:"center",title: "账单号", sort: true}
                ,{field:"device_number", width:120, align:"center",title: "表号", sort: true}
                ,{field:"elect_price_gradient", width:110, align:"center",title: "价位梯度", sort: true}
                ,{field:"elect_price_update_date", width:140, align:"center",title: "价位更新时间", sort: true
                    ,templet:function(data){
                        return layui.util.toDateString(data.elect_price_update_date, "yyyy-MM-dd");
                    }}
                ,{field:"elect_bill_init_value", width:110, align:"center",title: "初始读数"}
                ,{field:"elect_bill_now_value", width:110, align:"center",title: "现在读数"}
                ,{field:"elect_bill_r_dw", width:80, align:"center",title: "单位"}
                ,{field:"elect_bill_output_date", width:140, align:"center",title: "出账时间",sort: true
                    ,templet:function(data){
                        return layui.util.toDateString(data.elect_bill_output_date, "yyyy-MM-dd");
                    }}
                ,{field:"elect_bill_fee", width:110, align:"center",title: "应缴费用",sort: true}
                ,{field:"elect_bill_pay_date", width:140, align:"center",title: "缴费时间",sort: true
                    ,templet:function(data){
                        return layui.util.toDateString(data.elect_bill_pay_date, "yyyy-MM-dd");
                    }}
                ,{fixed: "right", width:160, title: "操作",align:"center", toolbar: "#barDemo"}
            ]]
        });

        //监听行工具事件
        table.on("tool(test)", function(obj){
            const data = obj.data;
            if(obj.event === "del"){
                layer.confirm("真的删除行么", function(index){
                    $.ajax({
                        url: "/elect/bill/delete",
                        type: "GET",
                        dataType: "json",
                        data: {
                            "bill_number":data.elect_bill_number
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
                    ,content: "/page/elect/bill_detail.jsp"
                    ,area: ["750px","400px"]
                    ,offset: ["15%","30%"]
                    ,success: function (layero,index){
                        var body = layer.getChildFrame("body",index);
                        body.find("#elect_bill_number").val(data.elect_bill_number);
                        body.find("#device_number").val(data.device_number);
                        body.find("#elect_price_gradient").val(data.elect_price_gradient);
                        body.find("#elect_price_update_date").val(layui.util.toDateString(data.elect_price_update_date, "yyyy-MM-dd"));
                        body.find("#elect_bill_init_value").val(data.elect_bill_init_value);
                        body.find("#elect_bill_now_value").val(data.elect_bill_now_value);
                        body.find("#elect_bill_r_dw").val(data.elect_bill_r_dw);
                        body.find("#elect_bill_output_date").val(layui.util.toDateString(data.elect_bill_output_date, "yyyy-MM-dd"));
                        body.find("#elect_bill_fee").val(data.elect_bill_fee);
                        body.find("#elect_bill_pay_date").val(layui.util.toDateString(data.elect_bill_pay_date, "yyyy-MM-dd"));
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
                    ,content: "/page/elect/bill_add.jsp"
                    ,area: ["750px","450px"]
                    ,offset: ["10%","30%"]
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
                            url: "/elect/bill/delete",
                            type: "GET",
                            async: false,
                            xhrFields:{
                                withCredentials: true
                            },
                            dataType: "json",
                            data: {
                                "bill_number":data[i].elect_bill_number
                            },
                            success: function(response){
                                if(response.code != 200){
                                    layer.msg("删除"+data[i].elect_bill_number+"失败");
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

