package dao;

import dao.entity.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDao {

    List<Device> selectByUser(@Param("user") String user, @Param("start") Integer start, @Param("count") Integer count);

    Integer deleteByUser(@Param("user") String user);

    Integer deleteByNumber(@Param("number") String number);

    Integer insert(@Param("device") Device device);

    List<Device> select();

    Device selectByNumber(@Param("number") String number);
}
