
package work365.work.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work365.work.model.AddtoCart;
import work365.work.model.CheckoutCart;
import work365.work.model.Product;

import javax.transaction.Transactional;


@Repository
public interface CheckoutRepo  extends JpaRepository<CheckoutCart, Long> {
    //@Query("Select checkoutCart  FROM CheckoutCart checkoutCart WHERE checkoutCart.user_id=:user_id group By orderId")
    //List<CheckoutCart> getAllCheckoutByuserId(@Param("user_id")String user_id);
    @Modifying
    @Transactional
    @Query("Select checkoutCart  FROM CheckoutCart checkoutCart WHERE checkoutCart.orderId=:orderId ")
    List<CheckoutCart> getAllCheckoutByorderId(@Param("orderId")String orderId);
    @Query("Select checkoutCart  FROM CheckoutCart checkoutCart ")
    Optional<AddtoCart> getAllCheckoutByuserIdtest();
    @Query("Select checkoutCart  FROM CheckoutCart checkoutCart WHERE checkoutCart.user_id=:user_id group By user_id  ")
    List<CheckoutCart> getAllCheckoutByuserId(@Param("user_id")String user_id);



}

































/*package work365.work.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work365.work.model.AddtoCart;
import work365.work.model.CheckoutCart;
import work365.work.model.Product;

import javax.transaction.Transactional;


@Repository
public interface CheckoutRepo  extends JpaRepository<CheckoutCart, Long> {
    //@Query("Select checkoutCart  FROM CheckoutCart checkoutCart WHERE checkoutCart.user_id=:user_id group By orderId")
    //List<CheckoutCart> getAllCheckoutByuserId(@Param("user_id")String user_id);
    @Modifying
    @Transactional
    @Query("Select checkoutCart  FROM CheckoutCart checkoutCart WHERE checkoutCart.orderId=:orderId ")
    List<CheckoutCart> getAllCheckoutByorderId(@Param("orderId")String orderId);
    @Query("Select checkoutCart  FROM CheckoutCart checkoutCart ")
    Optional<AddtoCart> getAllCheckoutByuserIdtest();
    @Query("Select checkoutCart  FROM CheckoutCart checkoutCart WHERE checkoutCart.user_id=:user_id ")
    List<CheckoutCart> getAllCheckoutByuserId(@Param("user_id")String user_id);

// essai
   // List<Product> findByCheckoutCartId(long id);

}*/