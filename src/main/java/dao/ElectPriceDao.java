package dao;

import dao.entity.ElectPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ElectPriceDao {


    List<ElectPrice> findAll();

    List<ElectPrice> findWithLimit(@Param("start") Integer start, @Param("count") Integer count);

    /**
     * 通过主键删除electprice
     * @param gradient
     * @param update_date
     * @return
     */
    Integer deleteByGD(@Param("gradient") Integer gradient, @Param("update_date") Date update_date);

    /**
     * 插入electPrice
     * @param electPrice
     * @return
     */
    Integer insert(@Param("price") ElectPrice electPrice);

    List<ElectPrice> findByGradient(@Param("gradient") Integer gradient
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectPrice> findByUpdate(@Param("update") Date update
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectPrice> findByAdmin(@Param("admin") String admin
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectPrice> findByMaximum(@Param("maximum") Float maximum
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectPrice> findByDw(@Param("dw") String dw
            , @Param("start") Integer start, @Param("count") Integer count);

    List<ElectPrice> findByUnit(@Param("unit") Float unit
            , @Param("start") Integer start, @Param("count") Integer count);

    Integer update(@Param("electPrice") ElectPrice electPrice);
}
