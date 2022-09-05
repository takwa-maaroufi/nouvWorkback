package work365.work.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotionId")
    private int promotionId;
    private String codeBarre;
    private String libelle;
    private String avantage;
    private double prixintial;
    private double prixpromo;
    private String canalDiffusion;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebutPromo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinPromo;
    @Lob
    private String image;

    //   @OneToMany(cascade = CascadeType.REMOVE)
    //  private Set<Product> product ;

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public String getAvantage() {
        return avantage;
    }

    public void setAvantage(String avantage) {
        this.avantage = avantage;
    }

    public double getPrixintial() {
        return prixintial;
    }

    public void setPrixintial(double prixintial) {
        this.prixintial = prixintial;
    }

    public double getPrixpromo() {
        return prixpromo;
    }

    public void setPrixpromo(double prixpromo) {
        this.prixpromo = prixpromo;
    }

    public String getCanalDiffusion() {
        return canalDiffusion;
    }

    public void setCanalDiffusion(String canalDiffusion) {
        this.canalDiffusion = canalDiffusion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebutPromo() {
        return dateDebutPromo;
    }

    public void setDateDebutPromo(Date dateDebutPromo) {
        this.dateDebutPromo = dateDebutPromo;
    }

    public Date getDateFinPromo() {
        return dateFinPromo;
    }

    public void setDateFinPromo(Date dateFinPromo) {
        this.dateFinPromo = dateFinPromo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLibelle() {return libelle;}

    public void setLibelle(String libelle) {this.libelle = libelle;}
}
