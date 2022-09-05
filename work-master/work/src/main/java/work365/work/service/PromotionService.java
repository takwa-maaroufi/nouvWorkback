package work365.work.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.PromotionRepository;
import work365.work.model.Promotion;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {
    @Autowired
    PromotionRepository promotionRepository;


    public Promotion store(MultipartFile file, String codeBarre, String libelle, String avantage, double prixintial, double prixpromo,
                           String canalDiffusion, String description, Date dateDebutPromo, Date dateFinPromo) throws IOException {
        Promotion p = new Promotion();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setCodeBarre(codeBarre);
        p.setLibelle(libelle);
        p.setAvantage(avantage);
        p.setPrixintial(prixintial);
        p.setPrixpromo(prixpromo);
        p.setCanalDiffusion(canalDiffusion);
        p.setDescription(description);
        p.setDateDebutPromo(dateDebutPromo);
        p.setDateFinPromo(dateFinPromo);

        return  promotionRepository.save(p);

    }


    public List<Promotion> listPromotion() {
        return promotionRepository.findAll();
    }

    public Optional<Promotion> findBypromoId(int promotionId) {
        return promotionRepository.findById(promotionId);
    }

    public void editPromotion(MultipartFile file, int promotionId, String codeBarre, String libelle, String avantage,
                          Date dateDebutPromo, Date dateFinPromo, String description,
                              String canalDiffusion, double prixintial, double Prixpromo )
            throws IOException {
        Promotion promotion = promotionRepository.getById(promotionId);
        promotion.setCodeBarre(codeBarre);
        promotion.setLibelle(libelle);
        promotion.setAvantage(avantage);
        promotion.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        promotion.setDateDebutPromo(dateDebutPromo);
        promotion.setDateFinPromo(dateFinPromo);
        promotion.setDescription(description);
        promotion.setCanalDiffusion(canalDiffusion);
        promotion.setPrixintial(prixintial);
        promotion.setPrixpromo(Prixpromo);

        promotionRepository.save(promotion);
    }

    public boolean findById(int promotionId) {
        return promotionRepository.findById(promotionId).isPresent();
    }


    public void deletePromotion(Integer promotionId) {

        Optional<Promotion> optionalPromotion = promotionRepository.findById(promotionId);

        optionalPromotion.ifPresent(promotionRepository::delete);

    }

 /*   public void createPromotion(Promotion promotion) {
        promotionRepository.save(promotion);
    }
*/

      /* public void editPromotion(int promotionId, Promotion updatePromotion ){
        Promotion promotion = promotionRepository.getById(promotionId);
        promotion.setCodeBarre(updatePromotion.getCodeBarre());
        promotion.setAvantage(updatePromotion.getAvantage());
        promotion.setImage(updatePromotion.getImage());
        promotion.setDateDebutPromo(updatePromotion.getDateDebutPromo());
        promotion.setDateFinPromo(updatePromotion.getDateFinPromo());
        promotion.setDescription(updatePromotion.getDescription());
        promotion.setCanalDiffusion(updatePromotion.getCanalDiffusion());
        promotion.setPrixintial(updatePromotion.getPrixintial());
        promotion.setPrixpromo(updatePromotion.getPrixpromo());

        promotionRepository.save(promotion);
    }*/
}
