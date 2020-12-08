package service;

import dao.DeviceDao;
import dao.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
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
     * @param req
     * @return
     */
    public Device getDevice(HttpServletRequest req){
        Device device = new Device();
        device.setDevice_number(req.getParameter("device_number"));
        device.setClient_user(req.getParameter("client_user"));
        device.setDevice_type(req.getParameter("device_type"));
        device.setDevice_point(Integer.parseInt(req.getParameter("device_point")));
        device.setDevice_producer(req.getParameter("device_producer"));
        device.setDevice_durability(Float.parseFloat(req.getParameter("device_durability")));
        device.setDevice_create_date(Date.valueOf(req.getParameter("device_create_date")));
        return device;
    }

    public List<Device> getDeviceByColumn(String user,String column, String input) {
        List<Device> ret = new ArrayList<>();
        List<Device> dList = deviceDao.select();
        for(Device device: dList){
            if(device.getClient_user().equals(user)){
                switch (column){
                    case "device_number":
                        if(device.getDevice_number().equals(input)){
                            ret.add(device);
                        }
                        break;
                    case "device_type":
                        if(device.getDevice_type().equals(input)){
                            ret.add(device);
                        }
                        break;
                    case "device_point":
                        if(device.getDevice_point() == Integer.valueOf(input)){
                            ret.add(device);
                        }
                        break;
                    case "device_producer":
                        if(device.getDevice_producer().equals(input)){
                            ret.add(device);
                        }
                        break;
                    case "device_create_date":
                        if(device.getDevice_create_date().toString().equals(input)){
                            ret.add(device);
                        }
                        break;
                    case "device_durability":
                        if(device.getDevice_durability() == Float.valueOf(input)){
                            ret.add(device);
                        }
                        break;
                }
            }
        }

        return ret;
    }
}
