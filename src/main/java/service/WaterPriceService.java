package service;

import dao.WaterPriceDao;
import dao.entity.WaterPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
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

    public List<WaterPrice> getWaterPriceByColumn(String column, String input) {
        List<WaterPrice> wpList = waterPriceDao.select();
        List<WaterPrice> ret = new ArrayList<>();

        for(WaterPrice waterPrice: wpList){
            switch (column){
                case "water_price_gradient":
                    if(waterPrice.getWater_price_gradient().equals(Integer.valueOf(input))){
                        ret.add(waterPrice);
                    }
                    break;
                case "water_price_update_date":
                    if(waterPrice.getWater_price_update_date().toString().equals(input)){
                        ret.add(waterPrice);
                    }
                    break;
                case "admin_user":
                    if(waterPrice.getAdmin_user().equals(input)){
                        ret.add(waterPrice);
                    }
                    break;
                case "water_price_maximum":
                    if(waterPrice.getWater_price_maximum().equals(Float.valueOf(input))){
                        ret.add(waterPrice);
                    }
                    break;
                case "water_price_dw":
                    if(waterPrice.getWater_price_dw().equals(input)){
                        ret.add(waterPrice);
                    }
                    break;
                case "water_price_unit_price":
                    if(waterPrice.getWater_price_unit_price().equals(Float.valueOf(input))){
                        ret.add(waterPrice);
                    }
                    break;
            }
        }
        
        return ret;
    }
}
