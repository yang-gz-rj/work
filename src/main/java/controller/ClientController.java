package controller;

import com.google.gson.Gson;
import dao.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ClientService;
import util.BaseResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class ClientController {

    private ClientService clientService;
    private final Gson gson = new Gson();

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * 用户登录页面显示
     *  session存在用户则直接跳转guide页面，否则登录页面
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/")
    public void start(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        // session存在用户
        if(req.getSession().getAttribute("client") != null){
            resp.sendRedirect("/guide");
        }else{
            req.getRequestDispatcher("/page/login/login.jsp").forward(req,resp);
        }
    }

    /**
     * 跳转到用户页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/client")
    public void client(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/client/client.jsp").forward(req,resp);
    }

    /**
     * 验证用户名密码
     *  验证成功则把用户存储在session
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/client/filter")
    public void clientFilter(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String client_user = req.getParameter("client_user");
        String client_password = req.getParameter("client_password");
        String verifyCode = req.getParameter("verifyCode").toLowerCase();

        BaseResponse<Client> br = new BaseResponse<Client>();
        // 验证码不正确
        if(!verifyCode.equals(req.getSession().getAttribute("verifyCode").toString().toLowerCase())){
            br.setCode(400);
        }else{
            Client client = clientService.getClientByUserPassword(client_user,client_password);
            if(client != null){
                br.setCode(200);
                //  存储用户在session
                req.getSession().setAttribute("client",client);
            }else{
                br.setCode(300);
            }
        }
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     * 修改用户信息
     *  修改后保存信息到session
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/client/alter")
    public void clientAlter(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Client client = clientService.getClient(req);
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(clientService.updateClient(client) > 0){
            br.setCode(200);
            req.getSession().removeAttribute("client");
            //  更新session中的用户信息
            req.getSession().setAttribute("client",client);
        }else{
            br.setCode(300);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     *  注销用户
     *   删除session存储的用户
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/client/delete")
    public void clientDelete(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        String client_user = req.getParameter("client_user");
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        if(clientService.deleteClientByUser(client_user) > 0){
            br.setCode(200);
            req.getSession().removeAttribute("client");
        }else{
            br.setCode(300);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

    /**
     * 跳转到注册页面
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/regist")
    public void regist(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        req.getRequestDispatcher("/page/client/regist.jsp").forward(req,resp);
    }

    /**
     * 注册用户
     *  成功则把用户存储在session中，否则啥也不做
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/regist/add")
    public void registAdd(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Client client = clientService.getClient(req);
        BaseResponse<Integer> br = new BaseResponse<Integer>();
        //  添加成功
        if(clientService.insertClient(client) > 0){
            br.setCode(200);
            // 存储用户
            req.getSession().setAttribute("client",client);
        }else{
            br.setCode(300);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(br));
        pw.flush();
        pw.close();
    }

}
