package service;

import dao.WaterBillDao;
import dao.entity.WaterBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterBillService {

    private WaterBillDao waterBillDao;

    @Autowired
    public void setWaterBillDao(WaterBillDao waterBillDao) {
        this.waterBillDao = waterBillDao;
    }

    /**
     * 获取device_number的账单
     * @param device_number
     * @return
     */
    public List<WaterBill> getWaterBillByDevice(String device_number) {
        return waterBillDao.selectByDevice(device_number);
    }

    /**
     * 通过bill_number删除对应账单
     * @param bill_number
     * @return
     */
    public Integer deleteBillByBNumber(String bill_number) {
        return waterBillDao.deleteByBNumber(bill_number);
    }

    /**
     * 插入waterbill
     * @param waterBill
     * @return
     */
    public Integer insertWaterBill(WaterBill waterBill) {
        return waterBillDao.insert(waterBill);
    }
}
