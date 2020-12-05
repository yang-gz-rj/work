package dao;

import dao.entity.WaterPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

}
