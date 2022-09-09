package work365.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.SubCategoryRepo;
import work365.work.message.ResponseMessage;
import work365.work.model.Category;
import work365.work.model.SubCategory;
import work365.work.payload.response.ApiResponse;
import work365.work.service.SubCategoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryController {

    @Autowired
    SubCategoryRepo subcategoryRepo;

    @Autowired
    SubCategoryRepo subCategoryRepo;

    @Autowired
    SubCategoryService subcategoryService;

    @GetMapping("/nbrSubCategory")
    public int nbreSubCategory(){
        return subcategoryService.nbreSubCategory();
    }


    @GetMapping("/SubCat/{categoryName}")
    @PreAuthorize("hasRole('USER')")
    public List<SubCategory> listSubCategoryByCategory(@PathVariable String categoryName) {
        return subcategoryService.listSubCategoryByCategory(categoryName);
    }
    @GetMapping("/productSubcategory/{subcategoryName}")
    @PreAuthorize("hasRole('USER')")
    public List<SubCategory> listSubCategoryBySubcategory(@PathVariable String subcategoryName) {
        return subcategoryService.listSubCategoryBySubcategory(subcategoryName);
    }


    @GetMapping("/subcategories")
    public ResponseEntity<List<SubCategory>> getAllSubCategories(@RequestParam(required = false) String subcategoryName) {
        try {
            List<SubCategory> subCategories = new ArrayList<SubCategory>();

            if (subcategoryName == null)
                subCategoryRepo.findAll().forEach(subCategories::add);
            else
                subCategoryRepo.findBysubcategoryNameContaining(subcategoryName).forEach(subCategories::add);

            if (subCategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(subCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload" )
    public ResponseEntity<ResponseMessage> store(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("subcategoryName") String subcategoryName,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("categoryId") int categoryId){
        String message = "";
        try {

            subcategoryService.store(file, subcategoryName, description, categoryId);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

    @GetMapping("/")
    public List<SubCategory> listSubCategory() {
        return subcategoryService.listSubCategory();
    }



    @GetMapping("/{subcategoryId}")
    public ResponseEntity<SubCategory> post(@PathVariable int subcategoryId) {
        Optional<SubCategory> sub = subcategoryService.findBysouscatId(subcategoryId);
        return sub.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @PutMapping("/update/{subcategoryId}")
    public ResponseEntity<ApiResponse> updateSubCategory(@PathVariable("subcategoryId") int subcategoryId,
                                                         @RequestParam("file") MultipartFile file,
                                                         @RequestParam("subcategoryName") String subcategoryName,
                                                         @RequestParam("description") String description,
                                                         @RequestParam("categoryId") Category categoryId ) throws IOException {
        System.out.println("subcategory id " + subcategoryId);
        if (!subcategoryService.findById(subcategoryId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "sub-category does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        subcategoryService.editSubCategory(file, subcategoryId, subcategoryName, description, categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "sub-category has been updated"), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{subcategoryId}")
    public ResponseEntity<ApiResponse> deleteSubCategory(@PathVariable("subcategoryId") Integer subcategoryId) {

    	subcategoryService.deleteSubcategory(subcategoryId);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }
     /*   @PostMapping("/create")
    public ResponseEntity<ApiResponse> createSubCategory(@RequestBody SubCategory subcategory) {
        subcategoryService.createSubCategory(subcategory);
        return new ResponseEntity<>(new ApiResponse(true, "a new sub-category created"), HttpStatus.CREATED);
    }
*/

   /*

    @PostMapping("/update/{subcategoryId}")
    public ResponseEntity<ApiResponse> updateSubCategory(@PathVariable("subcategoryId") int subcategoryId,
            @RequestBody SubCategory subcategory) {
        System.out.println("subcategory id " + subcategoryId);
        if (!subcategoryService.findById(subcategoryId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "sub-category does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        subcategoryService.editSubCategory(subcategoryId, subcategory);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "sub-category has been updated"), HttpStatus.OK);
    }
    */
}
