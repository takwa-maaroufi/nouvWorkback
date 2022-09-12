package work365.work.controller;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work365.work.Repository.CheckoutRepo;
import work365.work.model.Product;
import work365.work.service.configuration.ShoppingConfiguration;
import work365.work.controller.Pojo.ApiResponse;
import work365.work.model.AddtoCart;
import work365.work.model.CheckoutCart;
import work365.work.service.CartService;
import work365.work.service.ProductService;


//Checkout

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    CartService cartService;
    ProductService proService;
    @Autowired
    CheckoutRepo checkoutRepo;
    //valider  votre commande
    @RequestMapping("checkout_order")
    public ResponseEntity<?> checkout_order(@RequestBody HashMap<String,String> addCartRequest) {
        try {
            String keys[] = {"userId","total_price","firstName","lastName","email","gouvernorat","ville","adress","tel"};
            if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {


            }
            String statut="en cours";
            String user_Id = addCartRequest.get("userId");
            double total_amt = Double.parseDouble(addCartRequest.get("total_price"));
            if(cartService.checkTotalAmountAgainstCart(total_amt,user_Id)) {
                List<AddtoCart> cartItems = cartService.getCartByUserId(user_Id);

                List<CheckoutCart> tmp = new ArrayList<CheckoutCart>();
                for(AddtoCart addCart : cartItems) {
                    String orderId = ""+getOrderId();

                    CheckoutCart cart = new CheckoutCart();
                    cart.setPrice(total_amt);
                    cart.setStatut(statut);

                    cart.setOrder_date(cart.getOrder_date());
                    cart.setUser_id(user_Id);
                    cart.setOrderId(orderId);
                    cart.setProduct(addCart.getProduct());
                    cart.setQty(addCart.getQty());
                    cart.setFirstName(addCartRequest.get("firstName"));
                    cart.setLastName(addCartRequest.get("lastName"));
                    cart.setEmail(addCartRequest.get("email"));
                    cart.setAdress(addCartRequest.get("adress"));
                    cart.setGouvernorat(addCartRequest.get("gouvernorat"));
                    cart.setTel(addCartRequest.get("tel"));
                    tmp.add(cart);
                }
            cartService.saveProductsForCheckout(tmp);
                return ResponseEntity.ok(new ApiResponse("Order placed successfully", ""));
            }else {
                throw new Exception("Total amount is mismatch");
            }
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    public int getOrderId() {
        Random r = new Random( System.currentTimeMillis() );
        return 10000 + r.nextInt(20000);
    }
    @RequestMapping("getOrdersByUserId")
    public ResponseEntity<?> getOrdersByUserId(@RequestBody HashMap<String,String> getCartRequest) {
        try {
            String keys[] = {"userId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
            }
            List<CheckoutCart> obj = cartService.getCheckoutCartByUserId(getCartRequest.get("userId"));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    @RequestMapping("getOrdersByOrderId")
    public ResponseEntity<?> getOrdersByOrderId(@RequestBody HashMap<String,String> getCartRequest) {
        try {
            String keys[] = {"orderId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
            }
            List<CheckoutCart> obj = cartService.getCheckoutCartByOrderId(getCartRequest.get("orderId"));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    @RequestMapping("getProductByOrderId")
    public ResponseEntity<?> getProductsByOrderId(@RequestBody HashMap<String,String> getCartRequest) {
        try {
            String keys[] = {"id"};
            if(ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
            }
            Optional<CheckoutCart> obj = cartService.getById(Long.valueOf(getCartRequest.get("id")));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    @GetMapping("/productCheckoutCart/{orderId}")
    public List<CheckoutCart> listProductBySubcategory(@PathVariable String orderId) {
        return cartService.getCheckoutCartByOrderId(orderId);
    }

    @GetMapping("/")
    public List<CheckoutCart> listCheckoutCart() {
        return cartService.getAll();
    }






}