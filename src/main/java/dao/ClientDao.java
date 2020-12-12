package dao;

import dao.entity.Client;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao {

    Client findByUserPassword(@Param("user") String user, @Param("password") String password);

    Integer insert(@Param("client") Client client);

    Integer update(@Param("client") Client client);

    Integer deleteByUser(@Param("client_user") String client_user);
}
