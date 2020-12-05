package controller;

import dao.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ClientService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GuideController {

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/guide")
    public void guide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception, IOException {
        Object obj_client = httpServletRequest.getSession().getAttribute("client");
        //  验证session是否存在client对象
        if(obj_client != null){
            httpServletRequest.getRequestDispatcher("/page/guide.jsp").forward(httpServletRequest,httpServletResponse);
        }else{
            //  验证cookie是否存在client对象
            Cookie[] cookies = httpServletRequest.getCookies();
            for(Cookie cookie: cookies){
                String comment = cookie.getComment();
                //  验证是否是需要的cookie
                if(comment != null && comment.equals("wes use")){
                    //  获取cookie存储的用户名，密码
                    Client client = clientService.getClientByUserPassword(cookie.getName(),cookie.getValue());
                    httpServletRequest.getSession().setAttribute("client",client);
                    httpServletRequest.getRequestDispatcher("/page/guide.jsp").forward(httpServletRequest,httpServletResponse);
                    return;
                }
            }
            //  回到登录页面
            httpServletResponse.sendRedirect("/");
        }
    }
}

