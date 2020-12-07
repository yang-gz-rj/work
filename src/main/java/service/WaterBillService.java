package service;

import dao.WaterBillDao;
import dao.entity.WaterBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
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

    public WaterBill getWaterBill(HttpServletRequest httpServletRequest) {
        try {
            httpServletRequest.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        WaterBill waterBill = new WaterBill();
        waterBill.setWater_bill_number(httpServletRequest.getParameter("water_bill_number"));
        waterBill.setDevice_number(httpServletRequest.getParameter("device_number"));
        waterBill.setWater_price_gradient(Integer.valueOf(httpServletRequest.getParameter("water_price_gradient")));
        waterBill.setWater_price_update_date(Date.valueOf(httpServletRequest.getParameter("water_price_update_date")));

        waterBill.setWater_bill_init_value(Float.valueOf(httpServletRequest.getParameter("water_bill_init_value")));
        waterBill.setWater_bill_now_value(Float.valueOf(httpServletRequest.getParameter("water_bill_now_value")));
        waterBill.setWater_bill_r_dw(httpServletRequest.getParameter("water_bill_r_dw"));
        waterBill.setWater_bill_output_date(Date.valueOf(httpServletRequest.getParameter("water_bill_output_date")));
        waterBill.setWater_bill_fee(Float.valueOf(httpServletRequest.getParameter("water_bill_fee")));
        waterBill.setWater_bill_pay_date(Date.valueOf(httpServletRequest.getParameter("water_bill_pay_date")));

        return waterBill;
    }
}
