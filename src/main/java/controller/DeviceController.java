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
import java.util.List;

@Controller
public class DeviceController {

    private DeviceService deviceService;
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

    @RequestMapping("/device/json")
    public void deviceJson(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String client_user = httpServletRequest.getParameter("client_user");
        ResponseBase<List<Device>> br = new ResponseBase<List<Device>>();
        br.setCode(200);
        br.setData(deviceService.select(client_user));
        Gson gson = new Gson();
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

}
