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
    public List<Device> selectByUser(@Param("client_user") String client_user, @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 删除device_number对应的设备表
     * @param device_number
     * @return
     */
    public Integer deleteByNumber(@Param("device_number") String device_number);

    /**
     * 插入device
     * @param device
     * @return
     */
    public Integer insert(@Param("device") Device device);

    /**
     * 删除client_user对应的设备表
     * @param client_user
     * @return
     */
    public Integer deleteByUser(@Param("client_user") String client_user);
}
