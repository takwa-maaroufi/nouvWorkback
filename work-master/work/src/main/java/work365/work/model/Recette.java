package work365.work.model;


import javax.persistence.*;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recette")
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nomRecette;
    private String description;
    @Lob
    private String imageRecette;
    /*@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<RecetteProduct> recetteProducts;*/
    @ManyToMany(
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "recette_products",
            joinColumns = { @JoinColumn(name = "recette_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") })
    private List<Product> products;
    public Recette() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomRecette() {
        return nomRecette;
    }

    public void setNomRecette(String nomRecette) {
        this.nomRecette = nomRecette;
    }

    public String getImageRecette() {
        return imageRecette;
    }

    public void setImageRecette(String imageRecette) {
        this.imageRecette = imageRecette;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* public List<RecetteProduct> getRecetteProducts() {
        return recetteProducts;
    }
    public void setRecetteProducts(List<RecetteProduct> recetteProducts) {
        this.recetteProducts = recetteProducts;
    }*/

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}