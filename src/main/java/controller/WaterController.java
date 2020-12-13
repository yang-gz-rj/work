package controller;

import com.google.gson.Gson;
import dao.entity.Client;
import dao.entity.Device;
import dao.entity.WaterBill;
import dao.entity.WaterPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DeviceService;
import service.WaterBillService;
import service.WaterPriceService;
import util.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@Controller
public class WaterController {

    private WaterBillService waterBillService;
    private WaterPriceService waterPriceService;
    private DeviceService deviceService;
    private final Gson gson = new Gson();

    @Autowired
    public void setWaterBillService(WaterBillService waterBillService) {
        this.waterBillService = waterBillService;
    }

    @Autowired
    public void setWaterPriceService(WaterPriceService waterPriceService) {
        this.waterPriceService = waterPriceService;
    }

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * 跳转到账单页面
     *  判断是全显示，还是单显示（针对设备号进行显示）
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping("/water/bill")
    public void waterBill(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Client client = (Client) req.getSession().getAttribute("client");
        String column = req.getParameter("column");

        // 并非是搜素
        if(column == null){
            if(req.getSession().getAttribute("column") != null){
                req.getSession().removeAttribute("column");
                req.getSession().removeAttribute("input");
            }
            String device_number = req.getParameter("device_number");
            // 并非是点击“账单”进入
            if(device_number == null){
                req.getSession().setAttribute("bill_type","all");
            }else{
                req.getSession().setAttribute("bill_type","single");
                req.getSession().setAttribute("device_number",device_number);
            }

        }else{
            String input = req.getParameter("input");
            req.getSession().setAttribute("column",column);
            req.getSession().setAttribute("input",input);
        }

        req.getRequestDispatcher("/page/water/bill.jsp").forward(req, resp);
    }

    /**
     * 获取水费账单数据
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/water/bill/json")
    public void waterBillJson(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String column = (String) req.getSession().getAttribute("column");
        int page = Integer.valueOf(req.getParameter("page"));
        int limit = Integer.valueOf(req.getParameter("limit"));

        BaseResponse<List<WaterBill>> br = new BaseResponse<List<WaterBill>>();
        br.setCode(200);
        Client client = (Client) req.getSession().getAttribute("client");

        if(column == null){
            String bill_type = (String) req.getSession().getAttribute("bill_type");
            if(bill_type.equals("single")){
                String device_number = (String) req.getSession().getAttribute("device_number");
                br.setData(waterBillService.getWaterBillByUserAndDevice(client.getClient_user(),device_number
                        , page, limit));
                br.setCount(waterBillService.getWaterBillByUserAndDevice(client.getClient_user(),device_number
                        , 1, Integer.MAX_VALUE).size());
            }else{
                br.setData(waterBillService.getWaterBillByUser(client.getClient_user(),page,limit));
                br.setCount(waterBillService.getWaterBillByUser(client.getClient_user(),1,Integer.MAX_VALUE).size());
            }
        // 进行搜索
        }else{
            String input = (String) req.getSession().getAttribute("input");
            br.setData(waterBillService.getWaterBillByColumn(client.getClient_user(),column,input,page,limit));
            br.setCount(waterBillService.getWaterBillByColumn(client.getClient_user(),column,input,1,Integer.MAX_VALUE).size());
        }

        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     * 删除水费账单信息
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/water/bill/delete")
    public void waterBillDelete(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        String bill_number = req.getParameter("bill_number");
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(waterBillService.deleteBillByBNumber(bill_number) > 0){
            br.setCode(200);
        }else{
            br.setCode(300);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     * 添加水费账单信息
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/water/bill/add")
    public void waterBillAdd(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        WaterBill waterBill = waterBillService.getWaterBill(req);
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        Client client = (Client) req.getSession().getAttribute("client");
        Device device = deviceService.getDeviceByNumber(client.getClient_user(),waterBill.getDevice_number());
        if(device != null && device.getDevice_type().equals("水表") && waterBillService.insertWaterBill(waterBill) > 0){
            br.setCode(200);
        }else{
            br.setCode(300);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     * 跳转到水价位页面
     * @param req
     * @param resp
     */
    @RequestMapping("/water/price")
    public void waterPrice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String column = req.getParameter("column");
        if(column == null){
            if(req.getSession().getAttribute("column") != null){
                req.getSession().removeAttribute("column");
                req.getSession().removeAttribute("input");
            }
        }else{
            String input = req.getParameter("input");
            req.getSession().setAttribute("column",column);
            req.getSession().setAttribute("input",input);
        }
        req.getRequestDispatcher("/page/water/price.jsp").forward(req, resp);
    }

    /**
     * 获取水价位信息数据
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/water/price/json")
    public void waterPriceJson(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BaseResponse<List<WaterPrice>> br = new BaseResponse<List<WaterPrice>>();
        br.setCode(200);

        int page  = Integer.valueOf(req.getParameter("page"));
        int limit  = Integer.valueOf(req.getParameter("limit"));

        String column = (String) req.getSession().getAttribute("column");
        if(column == null){
            br.setData(waterPriceService.getWaterPriceLimit(page,limit));
            br.setCount(waterPriceService.getWaterPriceLimit(1,Integer.MAX_VALUE).size());
        }else{
            Client client = (Client) req.getSession().getAttribute("client");
            String input = (String) req.getSession().getAttribute("input");
            br.setData(waterPriceService.getWaterPriceByColumn(client.getClient_user(),column,input
                    ,page,limit));
            br.setCount(waterPriceService.getWaterPriceByColumn(client.getClient_user(),column,input
                    ,1,Integer.MAX_VALUE).size());
        }

        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     * 删除水价位信息
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/water/price/delete")
    public void waterPriceDelete(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        int gradient = Integer.valueOf(req.getParameter("gradient"));
        Date update_date = Date.valueOf(req.getParameter("update_date"));
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(waterPriceService.deleteWaterPriceByGD(gradient,update_date) > 0){
            br.setCode(200);
        }else{
            br.setCode(300);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     * 添加价位信息
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/water/price/add")
    public void waterPriceAdd(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        WaterPrice waterPrice = waterPriceService.getWaterPrice(req);
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(waterPriceService.insertWaterPrice(waterPrice) > 0){
            br.setCode(200);
        }else{
            br.setCode(300);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

}
