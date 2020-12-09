package dao;

import dao.entity.WaterBill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface WaterBillDao {

    Integer deleteByBillNumber(@Param("bill_number") String bill_number);

    Integer insert(@Param("bill") WaterBill waterBill);

    List<WaterBill> select();

    WaterBill selectByUserAndBillNumber(@Param("user") String user, @Param("bill_number") String bill_number
            , @Param("start") Integer start, @Param("count") Integer count);

    List<WaterBill> selectByUserAndDevice(@Param("user") String user,@Param("device_number") String device_number
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterBill> selectByUserAndGradient(@Param("user") String user, @Param("gradient") Integer gradient
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterBill> selectByUserAndUpdate(@Param("user") String user, @Param("update") Date update
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterBill> selectByUserAndInit(@Param("user") String user,@Param("init") Float init
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterBill> selectByUserAndNow(@Param("user") String user,@Param("now") Float now
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterBill> selectByUserAndOutput(@Param("user") String user, @Param("output") Date output
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterBill> selectByUserAndFee(@Param("user") String user, @Param("fee") Float fee
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterBill> selectByUserAndPay(@Param("user") String user, @Param("pay") Date pay
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterBill> selectByUser(@Param("user") String user
            , @Param("start") Integer start,@Param("count") Integer count);
}
