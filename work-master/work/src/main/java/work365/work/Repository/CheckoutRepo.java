
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

    //@Query("Select checkoutCart  FROM CheckoutCart checkoutCart")
    //@Query(value="Select id, adress, date, email, first_name, gouvernorat, last_name, order_id, order_date, price, qty, statut, tel, user_id, ville FROM CheckoutCart ORDER BY order_id", nativeQuery=true)
    @Query(value="Select * FROM `checkout_cart` group by order_id", nativeQuery=true)
    List<CheckoutCart> getAll();
    // List<CheckoutCart> findAll();

}