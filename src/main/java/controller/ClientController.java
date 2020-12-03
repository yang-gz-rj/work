package controller;

import com.google.gson.Gson;
import dao.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ClientService;
import util.BaseResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
public class ClientController {

    private ClientService clientService;
    private final Gson gson = new Gson();

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/")
    public ModelAndView start() {
        return new ModelAndView("/page/login/login.jsp");
    }

    @RequestMapping("/client")
    public void client(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/page/client/client.jsp").forward(httpServletRequest,httpServletResponse);
    }

    @RequestMapping("/client/filter")
    public void clientFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletRequest.setCharacterEncoding("utf-8");
        String client_user = httpServletRequest.getParameter("client_user");
        String client_password = httpServletRequest.getParameter("client_password");
        Client client = clientService.getClient(client_user,client_password);
        BaseResponse<Client> br = new BaseResponse<Client>();
        if(client != null){
            br.setCode(200);
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("client",client);
        }else{
            br.setCode(300);
        }
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    @RequestMapping("/client/alter")
    public void clientAlter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Client client = getClient(httpServletRequest);
        if(clientService.updateClient(client) > 0){
            httpServletRequest.removeAttribute("client");
            httpServletRequest.setAttribute("client",client);
        }
        httpServletRequest.getRequestDispatcher("/client").forward(httpServletRequest,httpServletResponse);
    }

    @RequestMapping("/regist")
    public ModelAndView regist(HttpServletResponse httpServletResponse) throws Exception {
        return new ModelAndView("/page/login/regist.jsp");
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

    private Client getClient(HttpServletRequest httpServletRequest) {
        try {
            httpServletRequest.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
