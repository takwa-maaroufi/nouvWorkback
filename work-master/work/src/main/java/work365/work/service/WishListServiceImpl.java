package work365.work.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work365.work.Repository.AddToCartRepo;
import work365.work.Repository.CheckoutRepo;
import work365.work.Repository.WishListRepository;
import work365.work.model.Product;
import work365.work.model.WishList;

import java.util.List;
@Service
public class WishListServiceImpl implements WishListService{
    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductService proServices;
    private static final Logger logger = LoggerFactory.getLogger(WishListServiceImpl.class);

    @Override
    public List<WishList> addtWishbyUserIdAndProductId(long productId, String userId, int qty, double price, String productName, String imageURL) throws Exception {
        try {
            if(wishListRepository.getWishByProductIdAnduserId(userId, productId).isPresent()){
                throw new Exception("Product is already exist.");
            }
            WishList obj = new WishList();
            obj.setQty(qty);
            obj.setUser_id(userId);
            obj.setProductName(productName);
            obj.setImageURL(imageURL);
            Product pro = proServices.getProductsById(productId);
            obj.setId(pro.getId());

            obj.setProduct(pro);
            //TODO price has to check with qty
            obj.setPrice(price);
            wishListRepository.save(obj);
            return this.getWishByUserId(userId);
        }catch(Exception e) {
            e.printStackTrace();
            logger.error(""+e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<WishList> getWishByUserId(String userId) {
        return wishListRepository.getWishByuserId(userId);
    }

    @Override
    public List<WishList> removeWishByUserId(long cartId, String userId) {
        wishListRepository.deleteCartByIdAndUserId(userId, cartId);
        return this.getWishByUserId(userId);
    }
}
