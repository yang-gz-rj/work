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
        Client client = (Client) req.getSession().getAttribute("client");
        String column = req.getParameter("column");
        if(column == null){
            // 清空搜素选项内容
            if(req.getSession().getAttribute("column") != null){
                req.getSession().removeAttribute("column");
                req.getSession().removeAttribute("input");
            }
            req.getSession().setAttribute("device_count",deviceService.getDeviceByUser(client.getClient_user()
                    ,1,Integer.MAX_VALUE).size());
        //  进行搜素    
        }else{
            String input = req.getParameter("input");
            req.getSession().setAttribute("column",column);
            req.getSession().setAttribute("input",input);
            req.getSession().setAttribute("device_count",deviceService.getDeviceByColumn(client.getClient_user()
                    ,column,input,1,Integer.MAX_VALUE).size());
        }
        req.getRequestDispatcher("/page/client/device.jsp").forward(req,resp);
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
        Client client = (Client)req.getSession().getAttribute("client");
        String column = (String)req.getSession().getAttribute("column");
        int curr = Integer.valueOf(req.getParameter("curr"));
        int limit = Integer.valueOf(req.getParameter("limit"));
        BaseResponse<List<Device>> br = new BaseResponse<>();
        br.setCode(200);
        // 并没有进行搜素
        if(column == null){
            br.setData(deviceService.getDeviceByUser(client.getClient_user(),curr,limit));
        //  进行搜素
        }else{
            br.setData(deviceService.getDeviceByColumn(client.getClient_user(), column
                    , (String) req.getSession().getAttribute("input"),curr, limit));
        }
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
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

}
