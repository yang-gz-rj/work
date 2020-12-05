package service;

import dao.WaterPriceDao;
import dao.entity.WaterPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
