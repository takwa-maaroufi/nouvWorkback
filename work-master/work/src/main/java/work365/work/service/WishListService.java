package work365.work.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import work365.work.Repository.WishListItemRepository;
//import work365.work.Repository.WishListRepository;
import work365.work.model.*;


import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public interface WishListService {

    List<WishList> addtWishbyUserIdAndProductId(long productId, String userId, int qty, double price, String productName, String imageURL) throws Exception;
    List<WishList> getWishByUserId(String userId);
    List<WishList> removeWishByUserId(long cartId,String userId);

}