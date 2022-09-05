package work365.work.controller;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work365.work.configuration.ShoppingConfiguration;
import work365.work.controller.Pojo.ApiResponse;
import work365.work.model.AddtoCart;
import work365.work.service.CartService;

//Panier
@RestController
@RequestMapping("api/addtocart")
public class AddtoCartController {

    @Autowired
    CartService cartService;
    @RequestMapping("addProduct")
    public ResponseEntity<?> addCartwithProduct(@RequestBody HashMap<String,String> addCartRequest) {
        try {
            String keys[] = {"productId","userId","qty","price","productName","imageURL" };
            if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

            }
            String productName= addCartRequest.get("productName");
            String imageURL  = addCartRequest.get("imageURL");
            long productId = Long.parseLong(addCartRequest.get("productId"));
            String userId =  addCartRequest.get("userId");
            int qty =  Integer.parseInt(addCartRequest.get("qty"));
            double price = Double.parseDouble(addCartRequest.get("price"));
            List<AddtoCart> obj = cartService.addCartbyUserIdAndProductId(productId,userId,qty,price,productName, imageURL);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }

    }

    @RequestMapping("updateQtyForCart")
    public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String,String> addCartRequest) {
        try {
            String keys[] = {"cartId","userId","qty","price"};
            if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

            }
            long cartId = Long.parseLong(addCartRequest.get("cartId"));
            String userId =  addCartRequest.get("userId");
            int qty =  Integer.parseInt(addCartRequest.get("qty"));
            double price = Double.parseDouble(addCartRequest.get("price"));
            cartService.updateQtyByCartId(cartId, qty, price);
            List<AddtoCart> obj = cartService.getCartByUserId(userId);
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
            List<AddtoCart> obj = cartService.removeCartByUserId(Long.parseLong(removeCartRequest.get("cartId")),
                    removeCartRequest.get("userId"));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    @RequestMapping("removeProductsFromCart")
    public ResponseEntity<?> removeProductsFromCart(@RequestBody HashMap<String,String> removeCartRequest) {
        try {
            String keys[] = {"userId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, removeCartRequest)) {

            }
            List<AddtoCart> obj = cartService.removeAllCartByUserId(removeCartRequest.get("userId"));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @RequestMapping("getCartsByUserId")
    public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getCartRequest) {
        try {
            String keys[] = {"userId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
            }
            List<AddtoCart> obj = cartService.getCartByUserId(getCartRequest.get("userId"));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    
}