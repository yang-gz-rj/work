package service;

import dao.WaterPriceDao;
import dao.entity.WaterPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class WaterPriceService {

    private WaterPriceDao waterPriceDao;

    @Autowired
    public void setWaterPriceDao(WaterPriceDao waterPriceDao) {
        this.waterPriceDao = waterPriceDao;
    }

    /**
     * 获得所有的价位信息
     * @return
     */
    public List<WaterPrice> getWaterPrice(){
        return waterPriceDao.findAll();
    }

    /**
     * 获取某一页的价位信息
     * @param curr
     * @param limit
     * @return
     */
    public List<WaterPrice> getWaterPriceLimit(Integer curr, Integer limit){
        return waterPriceDao.findWithLimit((curr-1)*limit,limit);
    }

    /**
     * 删除价位信息通过gradient和update_date
     * @param gradient
     * @param update_date
     * @return
     */
    public Integer deleteWaterPriceByGD(Integer gradient, Date update_date) {
        return waterPriceDao.deleteByGD(gradient,update_date);
    }

    /**
     * 插入价位信息
     * @param waterPrice
     * @return
     */
    public Integer insertWaterPrice(WaterPrice waterPrice) {
        return waterPriceDao.insert(waterPrice);
    }

    /**
     * 通过request对象获取价位信息
     * @param req
     * @return
     */
    public WaterPrice getWaterPrice(HttpServletRequest req) {
        WaterPrice waterPrice = new WaterPrice();
        waterPrice.setWater_price_gradient(Integer.valueOf(req.getParameter("water_price_gradient")));
        waterPrice.setWater_price_update_date(Date.valueOf(req.getParameter("water_price_update_date")));
        waterPrice.setAdmin_user(req.getParameter("admin_user"));
        waterPrice.setWater_price_maximum(Float.valueOf(req.getParameter("water_price_maximum")));
        waterPrice.setWater_price_dw(req.getParameter("water_price_dw"));
        waterPrice.setWater_price_unit_price(Float.valueOf(req.getParameter("water_price_unit_price")));
        return waterPrice;
    }

    public List<WaterPrice> getWaterPriceByColumn(String user,String column, String input,int curr,int limit) {
        List<WaterPrice> ret = new ArrayList<>();

        int start = (curr-1)*limit;

        switch (column){
            case "water_price_gradient":
                ret = waterPriceDao.findByGradient(Integer.valueOf(input),start,limit);
                break;
            case "water_price_update_date":
                ret = waterPriceDao.findByUpdate(Date.valueOf(input),start,limit);
                break;
            case "admin_user":
                ret = waterPriceDao.findByAdmin(input,start,limit);
                break;
            case "water_price_maximum":
                ret = waterPriceDao.findByMaximum(Float.valueOf(input),start,limit);
                break;
            case "water_price_dw":
                ret = waterPriceDao.findByDw(input,start,limit);
                break;
            case "water_price_unit_price":
                ret = waterPriceDao.findByUnit(Float.valueOf(input),start,limit);
                break;
        }
        
        return ret;
    }

    public Integer updateWaterPrice(WaterPrice waterPrice) {
        return waterPriceDao.update(waterPrice);
    }
}
