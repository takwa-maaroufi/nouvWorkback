package work365.work.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Date;


@Entity
@Table(name = "add_to_cart")
public class AddtoCart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    @JsonIgnore

    @OneToOne(fetch = FetchType.LAZY)
    Product product;


    @JsonIgnore

    @OneToOne(fetch = FetchType.LAZY)
    User user;
    //Long product_id;
    int qty;
    double price;
    String user_id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date added_date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
  private void onCreate(){
        added_date=new Date();
    }

    @Lob
    private @NotNull String imageURL;



    private @NotNull String productName;


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