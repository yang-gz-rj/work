package service;

import dao.DeviceDao;
import dao.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

@Service
public class DeviceService {

    private DeviceDao deviceDao;

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    /**
     * 查询该client_user的所有设备
     * @param client_user
     * @return
     */
    public List<Device> getDeviceByUser(String client_user, Integer curr, Integer limit){
        return deviceDao.selectByUser(client_user,(curr-1)*limit,limit);
    }

    /**
     * 删除device_number对应的设备表
     * @param device_number
     * @return
     */
    public Integer deleteDeviceByNumber(String device_number){
        return deviceDao.deleteByNumber(device_number);
    }

    /**
     * 插入device
     * @param device
     * @return
     */
    public Integer insertDevice(Device device){
        return deviceDao.insert(device);
    }

    /**
     * 通过device_number获取设备
     * @param device_number
     * @return
     */
    public Device getDeviceByNumber(String device_number) {
        return deviceDao.selectByNumber(device_number);
    }

    /**
     * 通过reques对象获取Device
     * @param httpServletRequest
     * @return
     */
    public Device getDevice(HttpServletRequest httpServletRequest){
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

    public List<Device> getDeviceByType(String type) {
        return deviceDao.selectByType(type);
    }

    public List<Device> getDeviceByPoint(String point) {
        return deviceDao.selectByPoint(point);
    }

    public List<Device> getDeviceByProducer(String producer) {
        return deviceDao.selectByProducer(producer);
    }

    public List<Device> getDeviceByCreateDate(String createDate) {
        return deviceDao.selectByCreateDate(createDate);
    }

    public List<Device> getDeviceByDurability(String durability) {
        return deviceDao.selectByDurability(durability);
    }
}
