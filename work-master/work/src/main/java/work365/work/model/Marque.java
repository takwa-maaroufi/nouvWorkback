package work365.work.model;


import javax.persistence.*;
import java.util.Set;

    @Entity
    @Table(name = "marques")
    public class Marque {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "marqueId")
        private int marqueId;
        private String nomMarque;
        @Lob
        private String Logo;
        private String teleMarque;
        private String emailMarque;
        @Lob
        private String contrat;

        //   @OneToMany(cascade = CascadeType.REMOVE)
        //  private Set<Product> product;

        public int getMarqueId() {
            return marqueId;
        }

        public void setMarqueId(int marqueId) {
            this.marqueId = marqueId;
        }

        public String getNomMarque() {
            return nomMarque;
        }

        public void setNomMarque(String nomMarque) {
            this.nomMarque = nomMarque;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String logo) {
            Logo = logo;
        }

        public String getTeleMarque() {
            return teleMarque;
        }

        public void setTeleMarque(String teleMarque) {
            this.teleMarque = teleMarque;
        }

        public String getEmailMarque() {
            return emailMarque;
        }

        public void setEmailMarque(String emailMarque) {
            this.emailMarque = emailMarque;
        }

        public String getContrat() {
            return contrat;
        }

        public void setContrat(String contrat) {
            this.contrat = contrat;
        }

}
