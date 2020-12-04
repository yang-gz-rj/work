package controller;

import com.google.gson.Gson;
import dao.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.DeviceService;
import util.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

@Controller
public class DeviceController {

    private DeviceService deviceService;
    private final Gson gson = new Gson();

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping("/device")
    public ModelAndView device(HttpServletRequest httpServletRequest) throws Exception {
        ModelAndView mav = new ModelAndView("/page/client/device.jsp");
        httpServletRequest.setCharacterEncoding("UTF-8");
        String client_user = httpServletRequest.getParameter("client_user");
        mav.addObject("device_count",deviceService.getDeviceByUser(client_user,1,Integer.MAX_VALUE).size());
        mav.addObject("client_user",client_user);
        return mav;
    }

    @RequestMapping("/device/add")
    public ModelAndView deviceAdd(){
        return new ModelAndView("/page/client/device_add.jsp");
    }

    @RequestMapping("/device/add/filter")
    public void deviceAddFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(deviceService.insertDevice(getDevice(httpServletRequest)) > 0){
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


    @RequestMapping("/device/detail")
    public ModelAndView deviceDetail(){
        ModelAndView mav = new ModelAndView("/page/client/device_detail.jsp");
        return mav;
    }

    @RequestMapping("/device/json")
    public void deviceJson(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String client_user = httpServletRequest.getParameter("client_user");
        int curr = Integer.valueOf(httpServletRequest.getParameter("curr"));
        int limit = Integer.valueOf(httpServletRequest.getParameter("limit"));

        BaseResponse<List<Device>> br = new BaseResponse<List<Device>>();
        br.setCode(200);
        br.setData(deviceService.getDeviceByUser(client_user,curr,limit));
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    @RequestMapping("/device/delete")
    public void deviceDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String device_number = httpServletRequest.getParameter("device_number");
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        br.setData(deviceService.deleteDeviceByNumber(device_number));
        if(br.getData() > 0){
            br.setCode(200);
        }else{
            br.setCode(300);
        }
        Gson gson = new Gson();
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    private Device getDevice(HttpServletRequest httpServletRequest){
        Device device = new Device();
        try {
            httpServletRequest.setCharacterEncoding("utf-8");
            device.setDevice_number(httpServletRequest.getParameter("device_number"));
            device.setClient_user(httpServletRequest.getParameter("client_user"));
            device.setDevice_type(httpServletRequest.getParameter("device_type"));
            device.setDevice_point(Integer.parseInt(httpServletRequest.getParameter("device_point")));
            device.setDevice_producer(httpServletRequest.getParameter("device_producer"));
            device.setDevice_durability(Float.parseFloat(httpServletRequest.getParameter("device_durability")));
            device.setDevice_create_date(Date.valueOf(httpServletRequest.getParameter("device_create_date")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return device;
    }

}
