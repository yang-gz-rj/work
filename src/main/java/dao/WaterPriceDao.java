package dao;

import dao.entity.WaterPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface WaterPriceDao {


    List<WaterPrice> findAll();

    List<WaterPrice> findWithLimit(@Param("start") Integer start, @Param("count") Integer count);

    /**
     * 通过主键删除waterprice
     * @param gradient
     * @param update_date
     * @return
     */
    Integer deleteByGD(@Param("gradient") Integer gradient, @Param("update_date") Date update_date);

    /**
     * 插入waterPrice
     * @param waterPrice
     * @return
     */
    Integer insert(@Param("price") WaterPrice waterPrice);

    List<WaterPrice> findByGradient(@Param("gradient") Integer gradient
            , @Param("start") Integer start, @Param("count") Integer count);

    List<WaterPrice> findByUpdate(@Param("update") Date update
            , @Param("start") Integer start, @Param("count") Integer count);

    List<WaterPrice> findByAdmin(@Param("admin") String admin
            , @Param("start") Integer start, @Param("count") Integer count);

    List<WaterPrice> findByMaximum(@Param("maximum") Float maximum
            , @Param("start") Integer start, @Param("count") Integer count);

    List<WaterPrice> findByDw(@Param("dw") String dw
            , @Param("start") Integer start, @Param("count") Integer count);

    List<WaterPrice> findByUnit(@Param("unit") Float unit
            , @Param("start") Integer start, @Param("count") Integer count);

    Integer update(@Param("waterPrice") WaterPrice waterPrice);
}
