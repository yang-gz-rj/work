package dao;

import dao.entity.Client;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao {
    /**
     * 通过user和password得到实体
     * @param client_user
     * @param client_password
     * @return 存在返回client,否则返回null
     */
    public Client select(@Param("client_user") String client_user, @Param("client_password") String client_password);

    /**
     * 插入client
     * @param client
     * @return 返回插入影响的条数
     */
    public Integer insert(@Param("client") Client client);
}
