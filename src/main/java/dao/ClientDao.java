package dao;

import dao.entity.Client;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao {

    Client findByUserPassword(@Param("client_user") String client_user, @Param("client_password") String client_password);

    Integer insert(@Param("client") Client client);

    Integer update(@Param("client") Client client);

    Integer deleteByUser(@Param("client_user") String client_user);
}
