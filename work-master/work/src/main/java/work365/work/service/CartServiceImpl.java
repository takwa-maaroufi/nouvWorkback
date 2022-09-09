package work365.work.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;
import work365.work.Repository.AddToCartRepo;
import work365.work.Repository.CheckoutRepo;
import work365.work.model.*;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    AddToCartRepo addCartRepo;
    @Autowired
    CheckoutRepo checkOutRepo;
    @Autowired
    ProductService proServices;
    @Autowired
    UserService userService;
    @Autowired
    WishListServiceImpl wishListService;

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    @Override
    public Optional<CheckoutCart> getById(Long id){
        return checkOutRepo.findById(id);
    }


    @Override
    public List<AddtoCart> addCartbyUserIdAndProductId(long productId, String userId,int qty,double price, String productName, String imageURL) throws Exception {
        try {
            if(addCartRepo.getCartByProductIdAnduserId(userId, productId).isPresent()){
                throw new Exception("Product is already exist.");
            }
            AddtoCart obj = new AddtoCart();
            User user = userService.getUser(userId);
            obj.setUser(user);
            obj.setQty(qty);
            obj.setUser_id(user.getEmail());
            obj.setProductName(productName);
            obj.setImageURL(imageURL);
            Product pro = proServices.getProductsById(productId);
            obj.setProduct(pro);

            //TODO price has to check with qty
            obj.setPrice(price);
            addCartRepo.save(obj);
            return this.getCartByUserId(userId);
        }catch(Exception e) {
            e.printStackTrace();
            logger.error(""+e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<AddtoCart> getCartByUserId(String userId) {
        return addCartRepo.getCartByuserId(userId);
    }
    @Override
    public List<CheckoutCart> getCheckoutCartByOrderId(String orderId) {
        return checkOutRepo.getAllCheckoutByorderId(orderId);
    }
    @Override
    public List<CheckoutCart> getCheckoutCartByUserId(String userId) {
        return checkOutRepo.getAllCheckoutByuserId(userId);
    }

    @Override
    public List<AddtoCart> removeCartByUserId(long cartId, String userId) {
        addCartRepo.deleteCartByIdAndUserId(userId, cartId);
        return this.getCartByUserId(userId);
    }

    @Override
    public void updateQtyByCartId(long cartId, int qty, double price) throws Exception {
        addCartRepo.updateQtyByCartId(cartId,price,qty);
    }

    @Override
    public Boolean checkTotalAmountAgainstCart(double totalAmount,String userId) {
        double total_amount =addCartRepo.getTotalAmountByUserId(userId);
        if(total_amount == totalAmount) {
            return true;
        }
        System.out.print("Error from request "+total_amount +" --db-- "+ totalAmount);
        return false;
    }



    @Override
    public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
        try {
            String user_id = tmp.get(0).getUser_id();
            if(tmp.size() >0) {
                checkOutRepo.saveAll(tmp);
                this.removeAllCartByUserId(user_id);
                return this.getCheckoutCartByUserId(user_id);
            }
            else {
                throw  new Exception("Should not be empty");
            }
        }catch(Exception e) {
            throw new Exception("Error while checkout "+e.getMessage());
        }

    }


    @Override
    public List<AddtoCart> removeAllCartByUserId(String userId) {
        addCartRepo.deleteAllCartByUserId(userId);
        return null;
    }

}


































/*package work365.work.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;
import work365.work.Repository.AddToCartRepo;
import work365.work.Repository.CheckoutRepo;
import work365.work.model.*;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    AddToCartRepo addCartRepo;
    @Autowired
    CheckoutRepo checkOutRepo;
    @Autowired
    ProductService proServices;
@Autowired
UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
@Override
public Optional<CheckoutCart> getById(Long id){
    return checkOutRepo.findById(id);
}
    @Override
    public List<AddtoCart> addCartbyUserIdAndProductId(long productId, String userId,int qty,double price, String productName, String imageURL) throws Exception {
        try {
            if(addCartRepo.getCartByProductIdAnduserId(userId, productId).isPresent()){
                throw new Exception("Product is already exist.");
            }
            AddtoCart obj = new AddtoCart();
            User user = userService.getUser(userId);
            obj.setUser(user);
            obj.setQty(qty);
            obj.setUser_id(user.getEmail());
            obj.setProductName(productName);
            obj.setImageURL(imageURL);
            Product pro = proServices.getProductsById(productId);
            obj.setProduct(pro);
            //TODO price has to check with qty
            obj.setPrice(price);
            addCartRepo.save(obj);
            return this.getCartByUserId(userId);
        }catch(Exception e) {
            e.printStackTrace();
            logger.error(""+e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<AddtoCart> getCartByUserId(String userId) {
        return addCartRepo.getCartByuserId(userId);
    }
    @Override
    public List<CheckoutCart> getCheckoutCartByOrderId(String orderId) {
        return checkOutRepo.getAllCheckoutByorderId(orderId);
    }
    @Override
    public List<CheckoutCart> getCheckoutCartByUserId(String userId) {
        return checkOutRepo.getAllCheckoutByuserId(userId);
    }

    @Override
    public List<AddtoCart> removeCartByUserId(long cartId, String userId) {
        addCartRepo.deleteCartByIdAndUserId(userId, cartId);
        return this.getCartByUserId(userId);
    }

    @Override
    public void updateQtyByCartId(long cartId, int qty, double price) throws Exception {
        addCartRepo.updateQtyByCartId(cartId,price,qty);
    }

    @Override
    public Boolean checkTotalAmountAgainstCart(double totalAmount,String userId) {
        double total_amount =addCartRepo.getTotalAmountByUserId(userId);
        if(total_amount == totalAmount) {
            return true;
        }
        System.out.print("Error from request "+total_amount +" --db-- "+ totalAmount);
        return false;
    }



    @Override
    public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
        try {
            String user_id = tmp.get(0).getUser_id();
            if(tmp.size() >0) {
                checkOutRepo.saveAll(tmp);
                this.removeAllCartByUserId(user_id);
                return this.getCheckoutCartByUserId(user_id);
            }
            else {
                throw  new Exception("Should not be empty");
            }
        }catch(Exception e) {
            throw new Exception("Error while checkout "+e.getMessage());
        }

    }


    @Override
    public List<AddtoCart> removeAllCartByUserId(String userId) {
        addCartRepo.deleteAllCartByUserId(userId);
        return null;
    }

}

 */