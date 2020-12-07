package service;

import dao.WaterPriceDao;
import dao.entity.WaterPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

@Controller
public class WaterPriceService {

    private WaterPriceDao waterPriceDao;

    @Autowired
    public void setWaterPriceDao(WaterPriceDao waterPriceDao) {
        this.waterPriceDao = waterPriceDao;
    }

    public List<WaterPrice> getWaterPrice(){
        return waterPriceDao.select();
    }

    public List<WaterPrice> getWaterPriceLimit(Integer curr, Integer limit){
        return waterPriceDao.selectLimit((curr-1)*limit,limit);
    }

    public Integer deleteWaterPriceByGD(Integer gradient, Date update_date) {
        return waterPriceDao.deleteByGD(gradient,update_date);
    }

    public Integer insertWaterPrice(WaterPrice waterPrice) {
        return waterPriceDao.insert(waterPrice);
    }

    public WaterPrice getWaterPrice(HttpServletRequest httpServletRequest) {
        try {
            httpServletRequest.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        WaterPrice waterPrice = new WaterPrice();
        waterPrice.setWater_price_gradient(Integer.valueOf(httpServletRequest.getParameter("water_price_gradient")));
        waterPrice.setWater_price_update_date(Date.valueOf(httpServletRequest.getParameter("water_price_update_date")));
        waterPrice.setAdmin_user(httpServletRequest.getParameter("admin_user"));
        waterPrice.setWater_price_maximum(Float.valueOf(httpServletRequest.getParameter("water_price_maximum")));
        waterPrice.setWater_price_dw(httpServletRequest.getParameter("water_price_dw"));
        waterPrice.setWater_price_unit_price(Float.valueOf(httpServletRequest.getParameter("water_price_unit_price")));
        return waterPrice;
    }
}
