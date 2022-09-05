package work365.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.PromotionRepository;
import work365.work.message.ResponseMessage;
import work365.work.model.Promotion;
import work365.work.payload.response.ApiResponse;
import work365.work.service.PromotionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promotion")
public class PromotionContoller {

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    PromotionService promotionService;

    @GetMapping("/promotions")
    public ResponseEntity<List<Promotion>> getAllPromotions(@RequestParam(required = false) String libelle) {
        try {
            List<Promotion> promotions = new ArrayList<Promotion>();

            if (libelle == null)
                promotionRepository.findAll().forEach(promotions::add);
            else
                promotionRepository.findBylibelleContaining(libelle).forEach(promotions::add);

            if (promotions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(promotions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload" )
    public ResponseEntity<ResponseMessage> store(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("codeBarre") String codeBarre,
                                                 @RequestParam("libelle") String libelle,
                                                 @RequestParam("avantage") String avantage,
                                                 @RequestParam("prixintial") double prixintial,
                                                 @RequestParam("prixpromo") double prixpromo,
                                                 @RequestParam("canalDiffusion") String canalDiffusion,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("dateDebutPromo") Date dateDebutPromo,
                                                 @RequestParam("dateFinPromo") Date dateFinPromo) {
        String message = "";
        try {

           promotionService.store(file, codeBarre,libelle, avantage, prixintial, prixpromo,
            canalDiffusion, description, dateDebutPromo, dateFinPromo);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }


    @GetMapping("/")
    public List<Promotion> listPromotion() {
        return promotionService.listPromotion();
    }

    @GetMapping("/{promotionId}")
    public ResponseEntity<Promotion> post(@PathVariable int promotionId) {
        Optional<Promotion> promo = promotionService.findBypromoId(promotionId);
        return promo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @PutMapping("/update/{promotionId}")
    public ResponseEntity<ApiResponse> editPromotion(@RequestParam("file") MultipartFile file,
                                                     @PathVariable("promotionId") int promotionId,
                                                     @RequestParam("codeBarre") String codeBarre,
                                                     @RequestParam("libelle") String libelle,
                                                     @RequestParam("avantage") String avantage,
                                                     @RequestParam("prixintial") double prixintial,
                                                     @RequestParam("prixpromo") double prixpromo,
                                                     @RequestParam("canalDiffusion") String canalDiffusion,
                                                     @RequestParam("description") String description,
                                                     @RequestParam("dateDebutPromo") Date dateDebutPromo,
                                                     @RequestParam("dateFinPromo") Date dateFinPromo) throws IOException {
        System.out.println("promotion id " +promotionId);
        if (!promotionService.findById(promotionId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Promotion does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        promotionService.editPromotion(file, promotionId,  codeBarre, libelle, avantage,
                 dateDebutPromo, dateFinPromo, canalDiffusion, description, prixintial, prixpromo);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Promotion has been updated"), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{promotionId}")
    public ResponseEntity<ApiResponse> deletePromotion(@PathVariable("promotionId") Integer promotionId) {

        promotionService.deletePromotion(promotionId);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }



    /*   @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPromotion(@RequestBody Promotion promotion) {
        promotionService.createPromotion(promotion);
        return new ResponseEntity<>(new ApiResponse(true, "a new promotion created"), HttpStatus.CREATED);
    }
*/

    /* @PostMapping("/update/{promotionId}")
    public ResponseEntity<ApiResponse> editPromotion(@PathVariable("promotionId") int promotionId,
                                                  @RequestBody Promotion promotion) {
        System.out.println("promotion id " +promotionId);
        if (!promotionService.findById(promotionId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Promotion does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        promotionService.editPromotion(promotionId, promotion);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Promotion has been updated"), HttpStatus.OK);
    }*/
}
