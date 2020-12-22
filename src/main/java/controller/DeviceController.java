package controller;

import com.google.gson.Gson;
import dao.entity.Client;
import dao.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DeviceService;
import service.ElectBillService;
import service.WaterBillService;
import util.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class DeviceController {

    private DeviceService deviceService;
    private WaterBillService waterBillService;
    private ElectBillService electBillService;
    private final Gson gson = new Gson();

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Autowired
    public void setWaterBillService(WaterBillService waterBillService) {
        this.waterBillService = waterBillService;
    }

    @Autowired
    public void setElectBillService(ElectBillService electBillService) {
        this.electBillService = electBillService;
    }

    /**
     * 跳转用户设备页面
     *  存储device_count到session
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping("/device")
    public void device(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Client client = (Client) req.getSession().getAttribute("client");
        String column = req.getParameter("column");
        if(column == null){
            // 清空搜素选项内容
            if(req.getSession().getAttribute("column") != null){
                req.getSession().removeAttribute("column");
                req.getSession().removeAttribute("input");
            }
        //  进行搜素    
        }else{
            String input = req.getParameter("input");
            req.getSession().setAttribute("column",column);
            req.getSession().setAttribute("input",input);
        }
        req.getRequestDispatcher("/page/client/device.jsp").forward(req,resp);
    }

    /**
     * 尝试添加设备
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/device/add/filter")
    public void deviceAddFilter(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(deviceService.insertDevice(deviceService.getDevice(req)) > 0){
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
     * 获取设备信息
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/device/json")
    public void deviceJson(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Client client = (Client)req.getSession().getAttribute("client");
        String column = (String)req.getSession().getAttribute("column");
        int page = Integer.valueOf(req.getParameter("page"));
        int limit = Integer.valueOf(req.getParameter("limit"));
        BaseResponse<List<Device>> br = new BaseResponse<>();
        br.setCode(200);
        // 并没有进行搜素
        List<Device> devices;
        if(column == null){
            devices = deviceService.getDeviceByUser(client.getClient_user(), page, limit);
            br.setCount(deviceService.getDeviceByUser(client.getClient_user(), 1, Integer.MAX_VALUE).size());
            //  进行搜素
        }else{
            devices = deviceService.getDeviceByColumn(client.getClient_user(), column
                    , (String) req.getSession().getAttribute("input"), page, limit);

            br.setCount(deviceService.getDeviceByColumn(client.getClient_user(), column
                    , (String) req.getSession().getAttribute("input"), 1, Integer.MAX_VALUE).size());
        }
        br.setData(devices);
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();

    }

    /**
     * 删除设备
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/device/delete")
    public void deviceDelete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String device_number = req.getParameter("device_number");
        String type = req.getParameter("type");
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        //  删除设备表对应的账单
        if(type.equals("水表")){
            waterBillService.deleteBillByDeviceNumber(device_number);
        }else{
            electBillService.deleteBillByNumber(device_number);
        }
        int row = deviceService.deleteDeviceByNumber(device_number,type);
        if(row > 0){
            br.setCode(200);
        }else{
            br.setCode(300);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    @RequestMapping("/device/edit/filter")
    public void deviceEditFilter(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Device device = deviceService.getDevice(req);
        BaseResponse<Integer> br = new BaseResponse<>();
        if(deviceService.updateDevice(device) > 0){
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
