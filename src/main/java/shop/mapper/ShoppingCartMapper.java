package shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.model.ShoppingCartItem;

public interface ShoppingCartMapper {

    Integer findItemAmount(@Param("userId") Long userId, 
                       @Param("cellphoneId") Long cellphoneId);

    void createItem(@Param("userId") Long userId, 
                    @Param("cellphoneId") Long cellphoneId, 
                    @Param("amount") int amount);

    List<ShoppingCartItem> findAllItems(Long userId);

    void removeItem(@Param("userId") Long userId, 
                    @Param("cellphoneId") Long cellphoneId);

    void deleteItemsByUserId(Long userId);

    void updateItemAmount(@Param("userId") Long userId, 
                          @Param("cellphoneId") Long cellphoneId, 
                          @Param("amount") int amount);

}
