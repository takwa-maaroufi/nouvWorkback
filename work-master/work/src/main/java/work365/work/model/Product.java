package work365.work.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@AllArgsConstructor
@Entity
@Table(name = "products" )
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    private @NotNull String name;
    private @NotNull String codeBarre;

    private @NotNull double price;
    private @NotNull String description;


    private @NotNull String unite;
    private @NotNull int qte;
    private @NotNull Boolean available;

  /*  @ColumnDefault("0")
    private Integer productStatus;
    @NotNull
    @Min(0)
    private Integer productStock;*/
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;
    // Many to one relationship
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subcategory_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SubCategory subcategory;

    // Many to one relationship
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "marque_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Marque marque;
    // one to one relationship
    @OneToOne(cascade = CascadeType.ALL)
     private CheckoutCart checkoutCart;



    @Lob
    private @NotNull String imageURL;// Many to one relationship
  /*  @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "promotion_id")
    Promotion promotion;
*/

    public Product() {

    }

    public SubCategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public SubCategory getSubCategory() {
        return subcategory;
    }

    public void setSubCategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Marque getMarque() {return marque; }

    public void setMarque(Marque marque) {this.marque = marque;}

 /*   public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
*/


    public String getUnite() {
        return unite;
    }


    public void setUnite(String unite) {
        this.unite = unite;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
  /*  public Product(){
        this.quantity=1;
    }
*/
}
