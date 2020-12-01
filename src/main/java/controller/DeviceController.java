package controller;

import com.google.gson.Gson;
import dao.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.DeviceService;
import util.ResponseBase;

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
    public ModelAndView device(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("client/device");
        mav.addObject("client_user",httpServletRequest.getParameter("client_user"));
        return mav;
    }

    @RequestMapping("/device/add")
    public ModelAndView deviceAdd(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        ModelAndView mav = new ModelAndView("client/device_add");
        return mav;
    }

    @RequestMapping("/device/add/filter")
    public void deviceFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ResponseBase<Integer> br = new ResponseBase<Integer>();
        if(deviceService.insert(getDevice(httpServletRequest)) > 0){
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

    @RequestMapping("/device/json")
    public void deviceJson(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String client_user = httpServletRequest.getParameter("client_user");
        ResponseBase<List<Device>> br = new ResponseBase<List<Device>>();
        br.setCode(200);
        br.setData(deviceService.select(client_user));
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    @RequestMapping("/device/delete")
    public void deviceDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String device_number = httpServletRequest.getParameter("device_number");
        ResponseBase<Integer> br = new ResponseBase<Integer>();
        br.setData(deviceService.delete(device_number));
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
