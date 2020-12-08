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
import java.util.Enumeration;

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

    /**
     * 跳转到guide页面
     *  处理判读用户是否存在
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/guide")
    public void guide(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Object obj_client = req.getSession().getAttribute("client");
        //  验证session是否存在client对象
        if (obj_client != null) {
            req.getRequestDispatcher("/page/guide.jsp").forward(req, resp);
        } else {
            //  验证cookie是否存在client对象
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                //  验证是否是需要的cookie
                //  获取cookie存储的用户名，密码
                Client client = clientService.getClientByUserPassword(cookie.getName(), cookie.getValue());
                if (client != null) {
                    req.getSession().setAttribute("client", client);
                    req.getRequestDispatcher("/page/guide.jsp").forward(req, resp);
                    return;
                }
            }
            //  回到登录页面
            resp.sendRedirect("/");
        }
    }

    /**
     * 更新验证码
     * @param resp
     * @param session
     */
    @RequestMapping("/guide/verify")
    public void guideVerify(HttpServletResponse resp, HttpSession session){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String code = VerifyCodeUtil.drawImage(output);
        //将验证码文本直接存放到session中
        session.setAttribute("verifyCode", code);
        try {
            ServletOutputStream out = resp.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/exit")
    public void exit(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            session.removeAttribute(attributeNames.nextElement());
        }
        resp.sendRedirect("/");
    }

}

