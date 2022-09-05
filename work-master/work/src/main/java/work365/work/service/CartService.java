package work365.work.service;



import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import work365.work.model.AddtoCart;
import work365.work.model.CheckoutCart;

@Configuration
@EnableJpaAuditing
public interface CartService {
    List<AddtoCart> addCartbyUserIdAndProductId(long productId, String userId, int qty, double price, String productName, String imageURL) throws Exception;
    void updateQtyByCartId(long cartId,int qty,double price) throws Exception;
    List<AddtoCart> getCartByUserId(String userId);
    List<AddtoCart> removeCartByUserId(long cartId,String userId);
    List<AddtoCart> removeAllCartByUserId(String userId);
    Boolean checkTotalAmountAgainstCart(double totalAmount,String userId);
    List<CheckoutCart> getCheckoutCartByUserId(String userId);
    List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp)  throws Exception;
    List<CheckoutCart> getCheckoutCartByOrderId(String orderId);
    Optional<CheckoutCart> getById(Long id);

  //CheckOutCart
}