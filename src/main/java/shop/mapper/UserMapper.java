package shop.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import shop.model.User;

public interface UserMapper {

    void create(@Param("username") String username, 
                @Param("password") String password);

    User findOneByUsername(String username);

    void updateLastLoginTime(@Param("id") Long id, 
                             @Param("date") Date date);

}
