package dao;

import dao.entity.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DeviceDao {

    List<Device> selectByUser(@Param("user") String user, @Param("start") Integer start, @Param("count") Integer count);

    Integer deleteByUser(@Param("user") String user);

    Integer deleteByNumber(@Param("number") String number);

    Integer insert(@Param("device") Device device);

    List<Device> selectByUserAndNumber(@Param("user") String user, @Param("number") String number
            , @Param("start") Integer start, @Param("count") Integer count);

    List<Device> selectByUserAndType(@Param("user") String user, @Param("type") String tyoe
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<Device> selectByUserAndPoint(@Param("user") String user, @Param("point") Integer point
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<Device> selectByUserAndProducer(@Param("user") String user, @Param("producer") String producer
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<Device> selectByUserAndCreate(@Param("user") String user, @Param("create") Date create
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<Device> selectByUserAndDurability(@Param("user") String user, @Param("durability") Float durability
            ,@Param("start") Integer start,@Param("count") Integer count);
}
