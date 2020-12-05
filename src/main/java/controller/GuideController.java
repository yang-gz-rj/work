package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GuideController {

    @RequestMapping("/guide")
    public void guide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception, IOException {
        Object client = httpServletRequest.getSession().getAttribute("client");
        if(client != null){
            httpServletRequest.getRequestDispatcher("/page/guide.jsp").forward(httpServletRequest,httpServletResponse);
        }else{
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.getWriter().write("想剽窃？想偷袭？");
        }
    }
}

