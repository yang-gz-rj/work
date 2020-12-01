package dao;

import dao.entity.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDao {

    /**
     * 查询该client_user的所有设备
     * @param client_user
     * @return
     */
    public List<Device> select(@Param("client_user") String client_user);

    /**
     * 删除device_number对应的设备表
     * @param device_number
     * @return
     */
    public Integer delete(@Param("device_number") String device_number);
}
