package controller;

import com.google.gson.Gson;
import dao.entity.Client;
import dao.entity.Device;
import dao.entity.ElectBill;
import dao.entity.ElectPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DeviceService;
import service.ElectBillService;
import service.ElectPriceService;
import util.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@Controller
public class ElectController {

    private ElectBillService electBillService;
    private ElectPriceService electPriceService;
    private DeviceService deviceService;
    private final Gson gson = new Gson();

    @Autowired
    public void setElectBillService(ElectBillService electBillService) {
        this.electBillService = electBillService;
    }

    @Autowired
    public void setElectPriceService(ElectPriceService electPriceService) {
        this.electPriceService = electPriceService;
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
    @RequestMapping("/elect/bill")
    public void electBill(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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

        req.getRequestDispatcher("/page/elect/bill.jsp").forward(req, resp);
    }

    /**
     * 获取电费账单数据
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/elect/bill/json")
    public void electBillJson(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String column = (String) req.getSession().getAttribute("column");
        int page = Integer.valueOf(req.getParameter("page"));
        int limit = Integer.valueOf(req.getParameter("limit"));

        BaseResponse<List<ElectBill>> br = new BaseResponse<List<ElectBill>>();
        br.setCode(200);
        Client client = (Client) req.getSession().getAttribute("client");

        if(column == null){
            String bill_type = (String) req.getSession().getAttribute("bill_type");
            if(bill_type.equals("single")){
                String device_number = (String) req.getSession().getAttribute("device_number");
                br.setData(electBillService.getElectBillByUserAndDevice(client.getClient_user(),device_number
                        , page, limit));
                br.setCount(electBillService.getElectBillByUserAndDevice(client.getClient_user(),device_number
                        , 1, Integer.MAX_VALUE).size());
            }else{
                br.setData(electBillService.getElectBillByUser(client.getClient_user(),page,limit));
                br.setCount(electBillService.getElectBillByUser(client.getClient_user(),1,Integer.MAX_VALUE).size());
            }
            // 进行搜索
        }else{
            String input = (String) req.getSession().getAttribute("input");
            br.setData(electBillService.getElectBillByColumn(client.getClient_user(),column,input,page,limit));
            br.setCount(electBillService.getElectBillByColumn(client.getClient_user(),column,input,1,Integer.MAX_VALUE).size());
        }

        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     * 删除电费账单信息
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/elect/bill/delete")
    public void electBillDelete(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        String bill_number = req.getParameter("bill_number");
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(electBillService.deleteBillByBNumber(bill_number) > 0){
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
     * 添加电费账单信息
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/elect/bill/add")
    public void electBillAdd(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        ElectBill electBill = electBillService.getElectBill(req);
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        Client client = (Client) req.getSession().getAttribute("client");
        Device device = deviceService.getDeviceByNumber(client.getClient_user(),electBill.getDevice_number());
        if(device != null && device.getDevice_type().equals("电表") && electBillService.insertElectBill(electBill) > 0){
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
     * 跳转到电价位页面
     * @param req
     * @param resp
     */
    @RequestMapping("/elect/price")
    public void electPrice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
        req.getRequestDispatcher("/page/elect/price.jsp").forward(req, resp);
    }

    /**
     * 获取电价位信息数据
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/elect/price/json")
    public void electPriceJson(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BaseResponse<List<ElectPrice>> br = new BaseResponse<List<ElectPrice>>();
        br.setCode(200);

        int page  = Integer.valueOf(req.getParameter("page"));
        int limit  = Integer.valueOf(req.getParameter("limit"));

        String column = (String) req.getSession().getAttribute("column");
        if(column == null){
            br.setData(electPriceService.getElectPriceLimit(page,limit));
            br.setCount(electPriceService.getElectPriceLimit(1,Integer.MAX_VALUE).size());
        }else{
            Client client = (Client) req.getSession().getAttribute("client");
            String input = (String) req.getSession().getAttribute("input");
            br.setData(electPriceService.getElectPriceByColumn(client.getClient_user(),column,input
                    ,page,limit));
            br.setCount(electPriceService.getElectPriceByColumn(client.getClient_user(),column,input
                    ,1,Integer.MAX_VALUE).size());
        }

        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     * 删除电价位信息
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/elect/price/delete")
    public void electPriceDelete(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        int gradient = Integer.valueOf(req.getParameter("gradient"));
        Date update_date = Date.valueOf(req.getParameter("update_date"));
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(electPriceService.deleteElectPriceByGD(gradient,update_date) > 0){
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
    @RequestMapping("/elect/price/add")
    public void electPriceAdd(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        ElectPrice electPrice = electPriceService.getElectPrice(req);
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(electPriceService.insertElectPrice(electPrice) > 0){
            br.setCode(200);
        }else{
            br.setCode(300);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    @RequestMapping("/elect/price/edit")
    public void electPriceEdit(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        ElectPrice electPrice = electPriceService.getElectPrice(req);
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(electPriceService.updateElectPrice(electPrice)>0){
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
