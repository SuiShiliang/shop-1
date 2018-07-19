package shop.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    void create(@Param("username") String username, 
                @Param("password") String password);

}
