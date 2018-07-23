package shop.mapper;

import org.apache.ibatis.annotations.Param;

public interface ShoppingCartMapper {

    boolean itemExists(@Param("userId") Long userId, 
                       @Param("cellphoneId") Long cellphoneId);

    /**
     * 指定购物车项数量加amount
     * @param userId
     * @param cellphoneId
     * @param amount
     */
    void incItemAmount(@Param("userId") Long userId, 
                       @Param("cellphoneId") Long cellphoneId, 
                       @Param("amount") int amount);

    void createItem(@Param("userId") Long userId, 
                    @Param("cellphoneId") Long cellphoneId, 
                    @Param("amount") int amount);

}
