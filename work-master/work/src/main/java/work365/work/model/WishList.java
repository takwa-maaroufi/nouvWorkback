package work365.work.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wishlist")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    User user;
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;


    //Long product_id;
    int qty;
    double price;
    String user_id;
    String productName;

    @Lob
    private @NotNull String imageURL;





    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductName() {
        return product.getName();
    }
}








































/*package work365.work.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wishlist")
public class WishList {

    @Id
    long id;
    @JsonIgnore

    @OneToOne(fetch = FetchType.LAZY)
    Product product;
    //Long product_id;

    String user_id;
    int qty;
    double price;

    String productName;

    @Lob
    private @NotNull String imageURL;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date added_date;


    @PrePersist
    private void onCreate() {
        added_date = new Date();
    }


    public Date getAdded_date() {
        return added_date;
    }

    public void setAdded_date(Date added_date) {
        this.added_date = added_date;
    }



    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductName() {
        return product.getName();
    }
}
*/