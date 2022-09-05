package work365.work.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import work365.work.model.Product;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    private int categoryId;
    // private long categoryId;
    @Column(name = "Category_name")
    private String categoryName;
    @Column(name = "Category_description")
    private String description;
    @Lob
    @Column(name = "Category_image")
    private String image;
    //  private byte[] image;


    /*
        @OneToMany(cascade = CascadeType.REMOVE)
      // @OneToMany(cascade = CascadeType.MERGE)
        private Set<SubCategory> subCategory;
    */
    public Category() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
