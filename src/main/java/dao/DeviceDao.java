package dao;

import dao.entity.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DeviceDao {

    List<Device> findByUser(@Param("user") String user, @Param("start") Integer start, @Param("count") Integer count);

    Integer updateEmptyByUser(@Param("user") String user);

    Integer deleteByNumber(@Param("number") String number);

    Integer insert(@Param("device") Device device);

    Device findByUserAndNumber(@Param("user") String user, @Param("number") String number);

    List<Device> findByUserAndType(@Param("user") String user, @Param("type") String tyoe
            , @Param("start") Integer start, @Param("count") Integer count);

    List<Device> findByUserAndPoint(@Param("user") String user, @Param("point") Integer point
            , @Param("start") Integer start, @Param("count") Integer count);

    List<Device> findByUserAndProducer(@Param("user") String user, @Param("producer") String producer
            , @Param("start") Integer start, @Param("count") Integer count);

    List<Device> findByUserAndCreate(@Param("user") String user, @Param("create") Date create
            , @Param("start") Integer start, @Param("count") Integer count);

    List<Device> findByUserAndDurability(@Param("user") String user, @Param("durability") Float durability
            , @Param("start") Integer start, @Param("count") Integer count);

    Integer update(@Param("device") Device device);
}
