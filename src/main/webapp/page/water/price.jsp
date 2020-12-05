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
            width: 65%;
            position: absolute;
            top: 8%;
            left: 25%;
        }
    </style>
</head>
<div id="price-body">
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        </div>
    </script>
    <table id="demo" lay-filter="test"></table>
    <div id="pageLimit"></div>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script>
        //执行一个laypage实例
        laypage.render({
            elem: 'pageLimit' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: ${price_count}
            ,limit: 10
            ,jump: function(obj, first){
                //首次不执行
                if(!first){
                    viewTable.reload({
                        url: "/water/price/json?curr="+obj.curr+"&limit=10"
                    });
                }
            }
        });

        const viewTable = table.render({
            elem: "#demo"
            ,url:"/water/price/json?curr=1&limit=10"
            ,page: false
            ,response: {
                statusCode: 200
            }
            ,toolbar: "#toolbarDemo"
            ,cols: [[
                {field:"water_price_gradient", width:140,align:"center",title: "价位梯度", sort: true}
                ,{field:"water_price_update_date", width:160, align:"center",title: "价位更新时间", sort: true
                ,templet:function(data){
                        return layui.util.toDateString(data.water_price_update_date, "yyyy-MM-dd");
                    }}
                ,{field:"admin_user", width:110, align:"center",title: "提供者", sort: true}
                ,{field:"water_price_maximum", width:140, align:"center",title: "最大量度", sort: true}
                ,{field:"water_price_dw", width:110, align:"center",title: "单位"}
                ,{field:"water_price_unit_price", width:110, align:"center",title: "单价"}
                ,{fixed: "right", width:110, title: "操作",align:"center", toolbar: "#barDemo"}
            ]]
        });

        //监听行工具事件
        table.on("tool(test)", function(obj){
            const data = obj.data;
            if(obj.event === "del"){
                layer.confirm("真的删除行么", function(index){
                    layui.$.ajax({
                        url: "/water/price/delete",
                        type: "GET",
                        dataType: "json",
                        data: {
                            "gradient":data.water_price_gradient
                            ,"update_date":layui.util.toDateString(data.water_price_update_date, "yyyy-MM-dd")
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
            }
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            const checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data;
            if(obj.event === 'add'){
                layer.open({
                    type: 2
                    ,content: "/page/water/price_add.jsp?client_user=${client_user}"
                    ,area: ["480px","500px"]
                    ,offset: ["10%","40%"]
                    ,cancel: function (){
                        viewTable.reload();
                    }
                });
            }
        });
    </script>

</div>
</html>


