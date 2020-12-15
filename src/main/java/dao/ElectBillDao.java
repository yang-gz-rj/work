package dao;

import dao.entity.ElectBill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ElectBillDao {

    Integer deleteByDeviceNumber(@Param("device_number") String device_number);

    Integer deleteByBillNumber(@Param("bill_number") String bill_number);

    Integer insert(@Param("bill") ElectBill electBill);

    ElectBill findByUserAndBillNumber(@Param("user") String user, @Param("bill_number") String bill_number
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectBill> findByUserAndDevice(@Param("user") String user, @Param("device_number") String device_number
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectBill> findByUserAndGradient(@Param("user") String user, @Param("gradient") Integer gradient
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectBill> findByUserAndUpdate(@Param("user") String user, @Param("update") Date update
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectBill> findByUserAndInit(@Param("user") String user, @Param("init") Float init
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectBill> findByUserAndNow(@Param("user") String user, @Param("now") Float now
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectBill> findByUserAndOutput(@Param("user") String user, @Param("output") Date output
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectBill> findByUserAndFee(@Param("user") String user, @Param("fee") Float fee
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectBill> findByUserAndPay(@Param("user") String user, @Param("pay") Date pay
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectBill> findByUser(@Param("user") String user
            , @Param("start") Integer start, @Param("count") Integer count);

}
