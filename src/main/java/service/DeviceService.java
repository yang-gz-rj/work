package service;

import dao.DeviceDao;
import dao.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Device> select(String client_user){
        return deviceDao.select(client_user);
    }

    /**
     * 删除device_number对应的设备表
     * @param device_number
     * @return
     */
    public Integer delete(String device_number){
        return deviceDao.delete(device_number);
    }

    /**
     * 插入device
     * @param device
     * @return
     */
    public Integer insert(Device device){
        return deviceDao.insert(device);
    }
}
