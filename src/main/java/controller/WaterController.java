package controller;

import com.google.gson.Gson;
import dao.entity.Device;
import dao.entity.WaterBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.DeviceService;
import service.WaterBillService;
import util.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WaterController {

    private WaterBillService waterBillService;
    private DeviceService deviceService;
    private final Gson gson = new Gson();

    @Autowired
    public void setWaterBillService(WaterBillService waterBillService) {
        this.waterBillService = waterBillService;
    }

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping("/water/bill")
    public ModelAndView waterBill(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletRequest.setCharacterEncoding("utf-8");
        ModelAndView mav = new ModelAndView("/page/water/bill.jsp");
        String client_user = httpServletRequest.getParameter("client_user");
        mav.addObject("client_user",client_user);
        if(client_user != null && !client_user.equals("")){
            mav.addObject("bill_type","all");
            List<Device> devices = deviceService.getDeviceByUser(client_user,1,Integer.MAX_VALUE);
            int count = 0;
            for(Device device: devices){
                count += waterBillService.getWaterBillByDevice(device.getDevice_number()).size();
            }
            mav.addObject("bill_count",count);
        }else{
            mav.addObject("bill_type","single");
            String device_number = httpServletRequest.getParameter("device_number");
            mav.addObject("device_number",device_number);
            mav.addObject("bill_count",waterBillService.getWaterBillByDevice(device_number).size());
        }
        return mav;
    }

    @RequestMapping("/water/bill/json")
    public void waterBillJson(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletRequest.setCharacterEncoding("utf-8");
        String bill_type = httpServletRequest.getParameter("bill_type");
        BaseResponse<List<WaterBill>> br = new BaseResponse<List<WaterBill>>();
        if(bill_type.equals("single")){
            String device_number = httpServletRequest.getParameter("device_number");
            br.setData(waterBillService.getWaterBillByDevice(device_number));
        }else{
            String client_user = httpServletRequest.getParameter("client_user");
            List<Device> devices = deviceService.getDeviceByUser(client_user, 1, Integer.MAX_VALUE);
            List<WaterBill> waterBills = new ArrayList<WaterBill>();
            for(Device device: devices){
                List<WaterBill> bills = waterBillService.getWaterBillByDevice(device.getDevice_number());
                for(WaterBill bill: bills){
                    waterBills.add(bill);
                }
            }
            br.setData(waterBills);
        }

        br.setCode(200);

        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    @RequestMapping("/water/bill/delete")
    public void waterBillDelete(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{
        httpServletRequest.setCharacterEncoding("utf-8");
        String bill_number = httpServletRequest.getParameter("bill_number");
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(waterBillService.deleteBillByBNumber(bill_number) > 0){
            br.setCode(200);
        }else{
            br.setCode(300);
        }
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

}
