package controller;

import com.google.gson.Gson;
import dao.entity.Client;
import dao.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DeviceService;
import util.BaseResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeviceController {

    private DeviceService deviceService;
    private final Gson gson = new Gson();

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
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
        req.setCharacterEncoding("UTF-8");
        Client client = (Client) req.getSession().getAttribute("client");
        req.getSession().setAttribute("device_count",deviceService.getDeviceByUser(client.getClient_user(),1,Integer.MAX_VALUE).size());
        req.getRequestDispatcher("/page/client/device.jsp").forward(req,resp);
    }

    // TODO 查找获得的数据
    @RequestMapping("/device/search")
    public void deviceSearch(String column, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String input = req.getParameter("input");
        BaseResponse<List<Device>> br = new BaseResponse<List<Device>>();
        List<Device> list=null;
        br.setCode(200);
        switch (column){
            case "device_number":
                list = new ArrayList<Device>();
                list.add(deviceService.getDeviceByNumber(input));
                break;
            case "device_type":
                list = deviceService.getDeviceByType(input);
                break;
            case "device_point":
                list = deviceService.getDeviceByPoint(input);
                break;
            case "device_producer":
                list = deviceService.getDeviceByProducer(input);
                break;
            case "device_create_date":
                list = deviceService.getDeviceByCreateDate(input);
                break;
            case "device_durability":
                list = deviceService.getDeviceByDurability(input);
                break;
        }
        br.setData(list);
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     * 跳转到设备添加页面
     * @return
     */
    @RequestMapping("/device/add")
    public void deviceAdd(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/client/device_add.jsp").forward(req,resp);
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
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }


    /**
     * 跳转到设备详情页面
     * @return
     */
    @RequestMapping("/device/detail")
    public void deviceDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/client/device_detail.jsp").forward(req,resp);
    }

    /**
     * 获取设备信息
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/device/json")
    public void deviceJson(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("UTF-8");
        Client client = (Client)req.getSession().getAttribute("client");
        int curr = Integer.valueOf(req.getParameter("curr"));
        int limit = Integer.valueOf(req.getParameter("limit"));

        BaseResponse<List<Device>> br = new BaseResponse<List<Device>>();
        br.setCode(200);
        br.setData(deviceService.getDeviceByUser(client.getClient_user(),curr,limit));
        resp.setContentType("text/html;charset=utf-8");
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
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        br.setData(deviceService.deleteDeviceByNumber(device_number));
        if(br.getData() > 0){
            br.setCode(200);
        }else{
            br.setCode(300);
        }
        Gson gson = new Gson();
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

}
