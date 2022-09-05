package work365.work.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work365.work.model.AddtoCart;
import work365.work.model.User;
import work365.work.model.WishList;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

    //remove cart by userid and cartId,
    //getCartByuserId
    @Query("Select wishlist  FROM WishList wishlist WHERE wishlist.user_id=:user_id")
    List<WishList> getWishByuserId(@Param("user_id")String user_id);
    @Query("Select wishlist  FROM WishList wishlist ")
    Optional<WishList> getWishByuserIdtest();
    @Query("Select wishlist  FROM WishList wishlist WHERE wishlist.product.id= :product_id and wishlist.user_id=:user_id")
    Optional<WishList> getWishByProductIdAnduserId(@Param("user_id")String user_id,@Param("product_id")Long product_id);
    @Modifying
    @Transactional
    @Query("DELETE  FROM WishList wishlist WHERE wishlist.id =:cart_id   and wishlist.user_id=:user_id")
    void deleteCartByIdAndUserId(@Param("user_id")String user_id,@Param("cart_id")Long cart_id);
    @Modifying
    @Transactional
    @Query("DELETE  FROM WishList wishlist WHERE   wishlist.user_id=:user_id")
    void deleteAllCartByUserId(@Param("user_id")String user_id);

    @Modifying
    @Transactional
    @Query("DELETE  FROM WishList wishlist WHERE wishlist.user_id=:user_id")
    void deleteAllCartUserId(@Param("user_id")String user_id);

}
