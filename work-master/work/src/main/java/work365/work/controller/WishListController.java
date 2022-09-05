package work365.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import work365.work.model.WishList;
import work365.work.configuration.ShoppingConfiguration;
import work365.work.controller.Pojo.ApiResponse;
import work365.work.model.AddtoCart;
import work365.work.model.Product;
import work365.work.model.WishList;
import work365.work.service.CartService;
import work365.work.service.WishListService;


import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.*;

//import work365.work.Repository.WishListRepository;


@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
@Autowired
WishListService wishListService;

    @RequestMapping("addProduct")
    public ResponseEntity<?> addWishwithProduct(@RequestBody HashMap<String,String> addWishRequest) {
        try {
            String keys[] = {"productId","userId","qty","price","productName","imageURL" };
            if(ShoppingConfiguration.validationWithHashMap(keys, addWishRequest)) {

            }
            String productName= addWishRequest.get("productName");
            String imageURL  = addWishRequest.get("imageURL");
            long productId = Long.parseLong(addWishRequest.get("productId"));
            String userId =  addWishRequest.get("userId");
            int qty =  Integer.parseInt(addWishRequest.get("qty"));
            double price = Double.parseDouble(addWishRequest.get("price"));
            List<WishList> obj = wishListService.addtWishbyUserIdAndProductId(productId,userId,qty,price,productName, imageURL);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }

    }


    @RequestMapping("removeProductFromCart")
    public ResponseEntity<?> removeCartwithProductId(@RequestBody HashMap<String,String> removeCartRequest) {
        try {
            String keys[] = {"userId","cartId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, removeCartRequest)) {

            }
            List<WishList> obj = wishListService.removeWishByUserId(Long.parseLong(removeCartRequest.get("cartId")), removeCartRequest.get("userId"));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @RequestMapping("getCartsByUserId")
    public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getWishRequest) {
        try {
            String keys[] = {"userId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, getWishRequest)) {
            }
            List<WishList> obj = wishListService.getWishByUserId(getWishRequest.get("userId"));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }



}