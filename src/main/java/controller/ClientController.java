package controller;

import dao.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
public class ClientController {

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/")
    public ModelAndView start(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("login/login");
        return mav;
    }

    @RequestMapping("/client")
    public ModelAndView showClient(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String client_user = httpServletRequest.getParameter("client_user");
        String client_password = httpServletRequest.getParameter("client_password");
        Client client = clientService.getClient(client_user,client_password);
        ModelAndView mav = null;
        if(client != null){
            mav = new ModelAndView("client/client");
            mav.addObject("client",client);
        }else{
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        }
        return mav;
    }

    @RequestMapping("/client/alter")
    public void alter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Client client = getClient(httpServletRequest);
        if(clientService.updateClient(client) > 0){
            httpServletRequest.removeAttribute("client");
            httpServletRequest.setAttribute("client",client);
        }
        httpServletRequest.getRequestDispatcher("/client").forward(httpServletRequest,httpServletResponse);
    }

    @RequestMapping("/regist")
    public ModelAndView regist(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("login/regist");
        return mav;
    }

    @RequestMapping("/regist/add")
    public void registAdd(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Client client = getClient(httpServletRequest);
        //  插入成功
        if(clientService.insertClient(client) > 0){
            httpServletRequest.setAttribute("client",client);
            httpServletRequest.getRequestDispatcher("/client").forward(httpServletRequest,httpServletResponse);
        }else{
            httpServletRequest.getRequestDispatcher("/regist").forward(httpServletRequest,httpServletResponse);
        }
    }

    private Client getClient(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        Client client = new Client();
        client.setClient_user(httpServletRequest.getParameter("client_user"));
        client.setClient_password(httpServletRequest.getParameter("client_password"));
        client.setClient_name(httpServletRequest.getParameter("client_name"));
        client.setClient_gender(httpServletRequest.getParameter("client_gender"));
        client.setClient_age(Integer.valueOf(httpServletRequest.getParameter("client_age")));
        client.setClient_phone(httpServletRequest.getParameter("client_phone"));
        client.setClient_address(httpServletRequest.getParameter("client_address"));
        return client;
    }

}
