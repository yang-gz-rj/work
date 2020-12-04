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
}
