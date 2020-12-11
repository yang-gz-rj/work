package service;

import dao.DeviceDao;
import dao.WaterBillDao;
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
    public List<WaterBill> getWaterBillByUserAndDevice(String user,String device_number, int curr, int limit) {
        return waterBillDao.findByUserAndDevice(user,device_number,(curr-1)*limit,limit);
    }

    /**
     * 通过bill_number删除对应账单
     * @param bill_number
     * @return
     */
    public Integer deleteBillByBNumber(String bill_number) {
        return waterBillDao.deleteByBillNumber(bill_number);
    }

    /**
     * 插入waterbill
     * @param waterBill
     * @return
     */
    public Integer insertWaterBill(WaterBill waterBill) {
        return waterBillDao.insert(waterBill);
    }

    /**
     * 通过request对象获取账单
     * @param req
     * @return
     */
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

    /**
     * 通过输入input和选择的字段column获取用户user对应的账单
     * @param user
     * @param column
     * @param input
     * @param curr
     * @param limit
     * @return
     */
    public List<WaterBill> getWaterBillByColumn(String user, String column, String input,int curr,int limit) {

        List<WaterBill> ret = new ArrayList<>();

        int start = (curr-1)*limit;

        // 查找账单号
        if(column.equals("water_bill_number")){
            ret.add(waterBillDao.findByUserAndBillNumber(user,input,start,limit));
            return ret;
        }

        switch (column){
            case "device_number":
                ret = waterBillDao.findByUserAndDevice(user,input,start,limit);
                break;
            case "water_price_gradient":
                ret = waterBillDao.findByUserAndGradient(user,Integer.valueOf(input),start,limit);
                break;
            case "water_price_update_date":
                ret = waterBillDao.findByUserAndUpdate(user,Date.valueOf(input),start,limit);
                break;
            case "water_bill_init_value":
                ret = waterBillDao.findByUserAndInit(user,Float.valueOf(input),start,limit);
                break;
            case "water_bill_now_value":
                ret = waterBillDao.findByUserAndNow(user,Float.valueOf(input),start,limit);
                break;
            case "water_bill_output_date":
                ret = waterBillDao.findByUserAndOutput(user,Date.valueOf(input),start,limit);
                break;
            case "water_bill_fee":
                ret = waterBillDao.findByUserAndFee(user,Float.valueOf(input),start,limit);
                break;
            case "water_bill_pay_date":
                ret = waterBillDao.findByUserAndPay(user,Date.valueOf(input),start,limit);
                break;
        }
        return ret;
    }

    /**
     * 获取用户user的账单
     * @param user
     * @param curr
     * @param limit
     * @return
     */
    public List<WaterBill> getWaterBillByUser(String user, int curr, int limit) {
        return waterBillDao.findByUser(user,(curr-1)*limit,limit);
    }
}
