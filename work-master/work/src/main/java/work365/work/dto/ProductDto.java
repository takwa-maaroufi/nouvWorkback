package work365.work.dto;


import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class ProductDto {
    // for create it can be optional
    // for update we need the id
    // private Long id;
    private long id;
    private @NotNull String name;
    private @NotNull String codeBarre;
    @Lob
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull String unite;
    private @NotNull int qte;
    private @NotNull Boolean available;
    //   private @NotNull Integer subcategoryId;
 /* private @NotNull int categoryId;
    private @NotNull int subcategoryId;
    private @NotNull int marqueId;*/
    //   private @NotNull int promotionId;


    //   private @NotNull Integer subcategoryId;
 /* private @NotNull int categoryId;
    private @NotNull int subcategoryId;
    private @NotNull int marqueId;*/
    //   private @NotNull int promotionId;

    public ProductDto() {
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
    /*  public int getCategoryId() {
         return categoryId;
     }

     public void setCategoryId(int categoryId) {
         this.categoryId = categoryId;
     }

    public Integer getSubCategoryId() {
         return subcategoryId;
     }

     public void setSubCategoryId(Integer subcategoryId) {
         this.subcategoryId = subcategoryId;
     }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

 /*   public int getMarqueId() {return marqueId;}

    public void setMarqueId(int marqueId) {this.marqueId = marqueId;}
*/


    // public int getPromotionId() {return promotionId;}
    /*public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }
*/
    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

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
}
