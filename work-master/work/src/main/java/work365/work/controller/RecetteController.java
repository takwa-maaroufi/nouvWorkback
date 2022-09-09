package work365.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.RecetteRepo;
import work365.work.message.ResponseMessage;
import work365.work.model.Category;
import work365.work.model.Product;
import work365.work.model.Recette;
import work365.work.payload.response.ApiResponse;
import work365.work.service.RecetteService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/recette")
//@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200")
public class RecetteController {

    @Autowired
    RecetteRepo recetteRepo;
    @Autowired
    RecetteService recetteService;




    @GetMapping("/nbrRecette")
    public int nbreRecette(){
        return recetteRepo.nbreRecette();
    }

    @GetMapping("/recettes")
    public ResponseEntity<List<Recette>> getAllRecettes(@RequestParam(required = false) String nomRecette) {
        try {
            List<Recette> recettes = new ArrayList<Recette>();

            if (nomRecette == null)
                recetteRepo.findAll().forEach(recettes::add);
            else
                recetteRepo.findBynomRecetteContaining(nomRecette).forEach(recettes::add);

            if (recettes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(recettes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> store(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("nomRecette") String nomRecette,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("products") int[] products
                                                 //@RequestParam("productId") int productId
    ) {  String message = "";

        try {


            recetteService.store(file, nomRecette, description, products);


            message = "Uploaded the file successfully: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }



    @GetMapping("/")
    public List<Recette> listRecette() {
        return recetteService.listRecette();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recette> post(@PathVariable int id) {
        Optional<Recette> rec = recetteService.findByIdrecette(id);
        return rec.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> editRecette(@PathVariable("id") int id,
                                                   @RequestParam("file") MultipartFile file,
                                                   @RequestParam("nomRecette") String nomRecette,
                                                   @RequestParam("description") String description) throws IOException {
        System.out.println("id " + id);
        if (!recetteService.findById(id)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "recette does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        recetteService.editRecette(file, id, nomRecette, description);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "recette has been updated"), HttpStatus.OK);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteRecette(@PathVariable("id") Integer id) {


        recetteService.deleteRecette(id);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }





}