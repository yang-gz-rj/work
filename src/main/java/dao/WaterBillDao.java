package dao;

import dao.entity.WaterBill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterBillDao {

    /**
     * 获取device_number的账单
     * @param device_number
     * @return
     */
    public List<WaterBill> selectByDevice(@Param("device_number") String device_number);

    /**
     * 通过bill_number删除对应账单
     * @param bill_number
     * @return
     */
    public Integer deleteByBNumber(@Param("bill_number") String bill_number);

    /**
     * 插入waterbill
     * @param waterBill
     * @return
     */
    public Integer insert(@Param("bill") WaterBill waterBill);

    List<WaterBill> select();
}
