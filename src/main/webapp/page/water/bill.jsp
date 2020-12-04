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
            width: 70%;
            position: absolute;
            top: 8%;
            left: 25%;
        }
    </style>
</head>
<div id="bill-body">
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        </div>
    </script>
    <table id="demo" lay-filter="test"></table>
    <div id="pageLimit"></div>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script>
        //执行一个laypage实例
        laypage.render({
            elem: 'pageLimit' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: ${bill_count}
            ,limit: 10
            ,jump: function(obj, first){
                //首次不执行
                if(!first){
                    viewTable.reload({
                        url: "/water/bill/json?bill_type=${bill_type}&client_user=${client_user}&device_number=${device_number}&curr="+obj.curr+"&limit=10"
                    });
                }
            }
        });

        const viewTable = table.render({
            elem: "#demo"
            ,url:"/water/bill/json?bill_type=${bill_type}&client_user=${client_user}&device_number=${device_number}&curr=1&limit=10"
            ,page: false
            ,response: {
                statusCode: 200
            }
            ,toolbar: "#toolbarDemo"
            ,cols: [[
                {field:"water_bill_number", width:80,align:"center",title: "账单号", sort: true}
                ,{field:"device_number", width:120, align:"center",title: "表号", sort: true}
                ,{field:"water_price_gradient", width:80, align:"center",title: "价位梯度", sort: true}
                ,{field:"water_price_update_date", width:80, align:"center",title: "价位更新时间", sort: true}
                ,{field:"water_bill_init_value", width:140, align:"center",title: "初始读数",sort: true}
                ,{field:"water_bill_now_value", width:130, align:"center",title: "现在读数", sort: true}
                ,{field:"water_bill_r_dw", width:110, align:"center",title: "单位",sort: true}
                ,{field:"water_bill_output_date", width:110, align:"center",title: "出账时间",sort: true}
                ,{field:"water_bill_fee", width:110, align:"center",title: "应缴费用",sort: true}
                ,{field:"water_bill_pay_date", width:110, align:"center",title: "缴费时间",sort: true}
                ,{fixed: "right", width:210, title: "操作",align:"center", toolbar: "#barDemo"}
            ]]
        });

        //监听行工具事件
        table.on("tool(test)", function(obj){
            const data = obj.data;
            if(obj.event === "del"){
                layer.confirm("真的删除行么", function(index){
                    layui.$.ajax({
                        url: "/water/bill/delete",
                        type: "GET",
                        dataType: "json",
                        data: {
                            "bill_number":data.water_bill_number
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
                // TODO 账单详细信息
                layer.open({
                    type: 2
                    ,content: "/water/bill/detail"
                    ,area: ["450px","500px"]
                    ,success: function (layero,index){
                        var body = layer.getChildFrame("body",index);
                        // body.find("#device_number").val(data.device_number);
                        // body.find("#client_user").val(data.client_user);
                        // body.find("#device_type").val(data.device_type);
                        // body.find("#device_point").val(data.device_point);
                        // body.find("#device_producer").val(data.device_producer);
                        // body.find("#device_create_date").val(data.device_create_date);
                        // body.find("#device_durability").val(data.device_durability);
                    }
                });
            }
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            const checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            if(obj.event === 'add'){
                // TODO 账单的添加
                layer.open({
                    type: 2
                    ,content: "/water/bill/add"
                    ,area: ["450px","500px"]
                    ,success: function (layero,index){
                        var body = layer.getChildFrame("body",index);
                        body.find("#client_user").attr("value","${client_user}");
                    }
                    ,cancel: function (){
                        viewTable.reload();
                    }
                });
            }
        });
    </script>

</div>
</html>

