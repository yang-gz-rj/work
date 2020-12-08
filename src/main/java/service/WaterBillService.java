package service;

import dao.DeviceDao;
import dao.WaterBillDao;
import dao.entity.Device;
import dao.entity.WaterBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class WaterBillService {

    private WaterBillDao waterBillDao;
    private DeviceDao deviceDao;

    @Autowired
    public void setWaterBillDao(WaterBillDao waterBillDao) {
        this.waterBillDao = waterBillDao;
    }

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
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

    public WaterBill getWaterBill(HttpServletRequest req) {
        WaterBill waterBill = new WaterBill();
        waterBill.setWater_bill_number(req.getParameter("water_bill_number"));
        waterBill.setDevice_number(req.getParameter("device_number"));
        waterBill.setWater_price_gradient(Integer.valueOf(req.getParameter("water_price_gradient")));
        waterBill.setWater_price_update_date(Date.valueOf(req.getParameter("water_price_update_date")));

        waterBill.setWater_bill_init_value(Float.valueOf(req.getParameter("water_bill_init_value")));
        waterBill.setWater_bill_now_value(Float.valueOf(req.getParameter("water_bill_now_value")));
        waterBill.setWater_bill_r_dw(req.getParameter("water_bill_r_dw"));
        waterBill.setWater_bill_output_date(Date.valueOf(req.getParameter("water_bill_output_date")));
        waterBill.setWater_bill_fee(Float.valueOf(req.getParameter("water_bill_fee")));
        waterBill.setWater_bill_pay_date(Date.valueOf(req.getParameter("water_bill_pay_date")));

        return waterBill;
    }

    // 查找所有设备，进行匹配input和column
    public List<WaterBill> getWaterBillByColumn(String user, String column, String input) {
        // 所有设备
        List<Device> dList = deviceDao.selectByUser(user,1,Integer.MAX_VALUE);
        // 获取所有账单
        List<WaterBill> wList = waterBillDao.select();

        List<WaterBill> ret = new ArrayList<>();
        for(Device device: dList){
            for(WaterBill waterBill: wList){
                if(device.getClient_user().equals(user)){
                    switch (column){
                        case "water_bill_number":
                            if(waterBill.getWater_bill_number().equals(input)){
                                ret.add(waterBill);
                            }
                            break;
                        case "device_number":
                            if(waterBill.getDevice_number().equals(input)){
                                ret.add(waterBill);
                            }
                            break;
                        case "water_price_gradient":
                            if(waterBill.getWater_price_gradient() == Integer.valueOf(input)){
                                ret.add(waterBill);
                            }
                            break;
                        case "water_price_update_date":
                            if(waterBill.getWater_price_update_date().toString().equals(input)){
                                ret.add(waterBill);
                            }
                            break;
                        case "water_bill_init_value":
                            if(waterBill.getWater_bill_init_value() == Float.valueOf(input)){
                                ret.add(waterBill);
                            }
                            break;
                        case "water_bill_now_value":
                            if(waterBill.getWater_bill_now_value() == Float.valueOf(input)){
                                ret.add(waterBill);
                            }
                            break;
                        case "water_bill_output_date":
                            if(waterBill.getWater_bill_output_date().toString().equals(input)){
                                ret.add(waterBill);
                            }
                            break;
                        case "water_bill_fee":
                            if(waterBill.getWater_bill_fee() == Float.valueOf(input)){
                                ret.add(waterBill);
                            }
                            break;
                        case "water_bill_pay_date":
                            if(waterBill.getWater_bill_pay_date().toString().equals(input)){
                                ret.add(waterBill);
                            }
                            break;
                    }
                }
            }
        }

        return ret;
    }
}
