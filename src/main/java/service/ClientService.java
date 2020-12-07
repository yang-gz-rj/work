package service;

import dao.ClientDao;
import dao.DeviceDao;
import dao.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Service
public class ClientService {

    private ClientDao clientDao;
    private DeviceDao deviceDao;

    @Autowired
    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    /**
     * 通过user和password得到实体
     * @param client_user
     * @param client_password
     * @return 存在返回client,否则返回null
     */
    public Client getClientByUserPassword(String client_user, String client_password){
        return clientDao.selectByUserPassword(client_user, client_password);
    }

    /**
     * 插入client
     * @param client
     * @return 返回插入影响的条数
     */
    public Integer insertClient(Client client){
        return clientDao.insert(client);
    }

    /**
     * 更新client
     * @param client
     * @return
     */
    public Integer updateClient(Client client){
        return clientDao.update(client);
    }

    /**
     * 删除client
     * @param client_user
     * @return
     */
    public Integer deleteClientByUser(String client_user){
        deviceDao.deleteByUser(client_user);
        return clientDao.deleteByUser(client_user);
    }

    /**
     * 通过request获取client
     * @param httpServletRequest
     * @return
     */
    public Client getClient(HttpServletRequest httpServletRequest) {
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
