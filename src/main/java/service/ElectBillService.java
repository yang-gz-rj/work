package service;

import dao.DeviceDao;
import dao.ElectBillDao;
import dao.entity.ElectBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElectBillService {

    private ElectBillDao electBillDao;
    private DeviceDao deviceDao;

    @Autowired
    public void setElectBillDao(ElectBillDao electBillDao) {
        this.electBillDao = electBillDao;
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
    public List<ElectBill> getElectBillByUserAndDevice(String user,String device_number, int curr, int limit) {
        return electBillDao.findByUserAndDevice(user,device_number,(curr-1)*limit,limit);
    }

    /**
     * 通过bill_number删除对应账单
     * @param bill_number
     * @return
     */
    public Integer deleteBillByBNumber(String bill_number) {
        return electBillDao.deleteByBillNumber(bill_number);
    }

    /**
     * 插入electbill
     * @param electBill
     * @return
     */
    public Integer insertElectBill(ElectBill electBill) {
        return electBillDao.insert(electBill);
    }

    /**
     * 通过request对象获取账单
     * @param req
     * @return
     */
    public ElectBill getElectBill(HttpServletRequest req) {
        ElectBill electBill = new ElectBill();
        electBill.setElect_bill_number(req.getParameter("elect_bill_number"));
        electBill.setDevice_number(req.getParameter("device_number"));
        electBill.setElect_price_gradient(Integer.valueOf(req.getParameter("elect_price_gradient")));
        electBill.setElect_price_update_date(Date.valueOf(req.getParameter("elect_price_update_date")));

        electBill.setElect_bill_init_value(Float.valueOf(req.getParameter("elect_bill_init_value")));
        electBill.setElect_bill_now_value(Float.valueOf(req.getParameter("elect_bill_now_value")));
        electBill.setElect_bill_r_dw(req.getParameter("elect_bill_r_dw"));
        electBill.setElect_bill_output_date(Date.valueOf(req.getParameter("elect_bill_output_date")));
        electBill.setElect_bill_fee(Float.valueOf(req.getParameter("elect_bill_fee")));
        electBill.setElect_bill_pay_date(Date.valueOf(req.getParameter("elect_bill_pay_date")));

        return electBill;
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
    public List<ElectBill> getElectBillByColumn(String user, String column, String input,int curr,int limit) {

        List<ElectBill> ret = null;

        int start = (curr-1)*limit;

        // 查找账单号
        if(column.equals("elect_bill_number")){
            ElectBill wb = electBillDao.findByUserAndBillNumber(user, input, start, limit);
            if(wb != null){
                ret = new ArrayList<>();
                ret.add(wb);
            }
            return ret;
        }

        switch (column){
            case "device_number":
                ret = electBillDao.findByUserAndDevice(user,input,start,limit);
                break;
            case "elect_price_gradient":
                ret = electBillDao.findByUserAndGradient(user,Integer.valueOf(input),start,limit);
                break;
            case "elect_price_update_date":
                ret = electBillDao.findByUserAndUpdate(user,Date.valueOf(input),start,limit);
                break;
            case "elect_bill_init_value":
                ret = electBillDao.findByUserAndInit(user,Float.valueOf(input),start,limit);
                break;
            case "elect_bill_now_value":
                ret = electBillDao.findByUserAndNow(user,Float.valueOf(input),start,limit);
                break;
            case "elect_bill_output_date":
                ret = electBillDao.findByUserAndOutput(user,Date.valueOf(input),start,limit);
                break;
            case "elect_bill_fee":
                ret = electBillDao.findByUserAndFee(user,Float.valueOf(input),start,limit);
                break;
            case "elect_bill_pay_date":
                ret = electBillDao.findByUserAndPay(user,Date.valueOf(input),start,limit);
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
    public List<ElectBill> getElectBillByUser(String user, int curr, int limit) {
        return electBillDao.findByUser(user,(curr-1)*limit,limit);
    }
}
