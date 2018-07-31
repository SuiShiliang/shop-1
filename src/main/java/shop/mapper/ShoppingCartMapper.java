package shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.model.ShoppingCartItem;

public interface ShoppingCartMapper {

    Integer findItemQuantity(@Param("userId") Long userId, 
                       @Param("cellphoneId") Long cellphoneId);

    void createItem(@Param("userId") Long userId, 
                    @Param("cellphoneId") Long cellphoneId, 
                    @Param("quantity") int quantity);

    List<ShoppingCartItem> findAllItems(Long userId);

    void removeItem(@Param("userId") Long userId, 
                    @Param("cellphoneId") Long cellphoneId);

    void deleteItemsByUserId(Long userId);

    void updateItemQuantity(@Param("userId") Long userId, 
                            @Param("cellphoneId") Long cellphoneId, 
                            @Param("quantity") int quantity);

}
