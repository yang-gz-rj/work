package controller;

import dao.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ClientService;
import service.DeviceService;
import service.WaterBillService;
import service.WaterPriceService;
import util.VerifyCodeUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class GuideController {

    private ClientService clientService;
    private DeviceService deviceService;
    private WaterPriceService waterPriceService;
    private WaterBillService waterBillService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Autowired
    public void setWaterPriceService(WaterPriceService waterPriceService) {
        this.waterPriceService = waterPriceService;
    }

    @Autowired
    public void setWaterBillService(WaterBillService waterBillService) {
        this.waterBillService = waterBillService;
    }

    @RequestMapping("/guide")
    public void guide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Object obj_client = httpServletRequest.getSession().getAttribute("client");
        //  验证session是否存在client对象
        if (obj_client != null) {
            httpServletRequest.getRequestDispatcher("/page/guide.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            //  验证cookie是否存在client对象
            Cookie[] cookies = httpServletRequest.getCookies();
            for (Cookie cookie : cookies) {
                //  验证是否是需要的cookie
                //  获取cookie存储的用户名，密码
                Client client = clientService.getClientByUserPassword(cookie.getName(), cookie.getValue());
                if (client != null) {
                    httpServletRequest.getSession().setAttribute("client", client);
                    httpServletRequest.getRequestDispatcher("/page/guide.jsp").forward(httpServletRequest, httpServletResponse);
                    return;
                }
            }
            //  回到登录页面
            httpServletResponse.sendRedirect("/");
        }
    }

    @RequestMapping("/guide/verify")
    public void guideVerify(HttpServletResponse response, HttpSession session){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String code = VerifyCodeUtil.drawImage(output);
        //将验证码文本直接存放到session中
        session.setAttribute("verifyCode", code);
        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/guide/search")
    public void guideSearch(String model,String column,HttpServletRequest req,HttpServletResponse resp){
        switch (model){
            case "device":
                searchDevice(req,resp);
                break;
            case "water_bill":
                searchWaterBill(req,resp);
                break;
            case "water_price":
                searchWaterPrice(req,resp);
                break;
        }
    }

    // TODO 分别查询
    private void searchWaterPrice(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void searchWaterBill(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void searchDevice(HttpServletRequest req, HttpServletResponse resp) {
    }
}

