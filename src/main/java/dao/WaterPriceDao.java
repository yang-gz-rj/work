package dao;

import dao.entity.WaterPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface WaterPriceDao {

    /**
     * 查询所有价位信息
     * @return
     */
    public List<WaterPrice> select();

    /**
     * 分页查询
     * @param start
     * @param count
     * @return
     */
    public List<WaterPrice> selectLimit(@Param("start") Integer start, @Param("count") Integer count);

    /**
     * 通过主键删除waterprice
     * @param gradient
     * @param update_date
     * @return
     */
    public Integer deleteByGD(@Param("gradient") Integer gradient, @Param("update_date") Date update_date);

    /**
     * 插入waterPrice
     * @param waterPrice
     * @return
     */
    Integer insert(@Param("price") WaterPrice waterPrice);

    List<WaterPrice> selectByGradient(@Param("gradient") Integer gradient
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterPrice> selectByUpdate(@Param("update") Date update
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterPrice> selectByAdmin(@Param("admin") String admin
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterPrice> selectByMaximum(@Param("maximum") Float maximum
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterPrice> selectByDw(@Param("dw") String dw
            ,@Param("start") Integer start,@Param("count") Integer count);

    List<WaterPrice> selectByUnit(@Param("unit") Float unit
            ,@Param("start") Integer start,@Param("count") Integer count);
}
