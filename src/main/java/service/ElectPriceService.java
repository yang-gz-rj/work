package service;

import dao.ElectPriceDao;
import dao.entity.ElectPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ElectPriceService {

    private ElectPriceDao electPriceDao;

    @Autowired
    public void setElectPriceDao(ElectPriceDao electPriceDao) {
        this.electPriceDao = electPriceDao;
    }

    /**
     * 获得所有的价位信息
     * @return
     */
    public List<ElectPrice> getElectPrice(){
        return electPriceDao.findAll();
    }

    /**
     * 获取某一页的价位信息
     * @param curr
     * @param limit
     * @return
     */
    public List<ElectPrice> getElectPriceLimit(Integer curr, Integer limit){
        return electPriceDao.findWithLimit((curr-1)*limit,limit);
    }

    /**
     * 删除价位信息通过gradient和update_date
     * @param gradient
     * @param update_date
     * @return
     */
    public Integer deleteElectPriceByGD(Integer gradient, Date update_date) {
        return electPriceDao.deleteByGD(gradient,update_date);
    }

    /**
     * 插入价位信息
     * @param electPrice
     * @return
     */
    public Integer insertElectPrice(ElectPrice electPrice) {
        return electPriceDao.insert(electPrice);
    }

    /**
     * 通过request对象获取价位信息
     * @param req
     * @return
     */
    public ElectPrice getElectPrice(HttpServletRequest req) {
        ElectPrice electPrice = new ElectPrice();
        electPrice.setElect_price_gradient(Integer.valueOf(req.getParameter("elect_price_gradient")));
        electPrice.setElect_price_update_date(Date.valueOf(req.getParameter("elect_price_update_date")));
        electPrice.setAdmin_user(req.getParameter("admin_user"));
        electPrice.setElect_price_maximum(Float.valueOf(req.getParameter("elect_price_maximum")));
        electPrice.setElect_price_dw(req.getParameter("elect_price_dw"));
        electPrice.setElect_price_unit_price(Float.valueOf(req.getParameter("elect_price_unit_price")));
        return electPrice;
    }

    public List<ElectPrice> getElectPriceByColumn(String user,String column, String input,int curr,int limit) {
        List<ElectPrice> ret = new ArrayList<>();

        int start = (curr-1)*limit;

        switch (column){
            case "elect_price_gradient":
                ret = electPriceDao.findByGradient(Integer.valueOf(input),start,limit);
                break;
            case "elect_price_update_date":
                ret = electPriceDao.findByUpdate(Date.valueOf(input),start,limit);
                break;
            case "admin_user":
                ret = electPriceDao.findByAdmin(input,start,limit);
                break;
            case "elect_price_maximum":
                ret = electPriceDao.findByMaximum(Float.valueOf(input),start,limit);
                break;
            case "elect_price_dw":
                ret = electPriceDao.findByDw(input,start,limit);
                break;
            case "elect_price_unit_price":
                ret = electPriceDao.findByUnit(Float.valueOf(input),start,limit);
                break;
        }

        return ret;
    }

    public Integer updateElectPrice(ElectPrice electPrice) {
        return electPriceDao.update(electPrice);
    }
}
