package work365.work.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "subcategories")
public class SubCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,  generator = "subcategory_generator")
    @Column(name = "subCategoryId")
    // private Long subCategoryId;
    private int subCategoryId;
    @Column(name = "name")
    private @NotBlank String subcategoryName;

    @Column(name = "description")
    private @NotBlank String description;

    @Column(name = "image")
    @Lob
    private String imageUrl;

    //  @Lob
    //  private String data;

    // @ManyToOne( cascade = CascadeType.REMOVE)
    @ManyToOne( cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Category category;

    //  @OneToMany(cascade = CascadeType.REMOVE)
    //  private Set<Product> product;

    public SubCategory() {

    }


    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }



}
