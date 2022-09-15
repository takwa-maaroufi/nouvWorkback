
package work365.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.*;
import work365.work.message.ResponseMessage;
import work365.work.model.Category;
import work365.work.model.Marque;
import work365.work.model.Product;
import work365.work.model.SubCategory;
//import work365.work.payload.Repository.*;
import work365.work.payload.response.ApiResponse;
import work365.work.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    SubCategoryRepo subcategoryRepo;

    @Autowired
    MarqueRepository marqueRepository;

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/nbrProduit")
    public int nbreProd(){
        return productService.nbreProd();
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
        try {
            List<Product> products = new ArrayList<Product>();

            if (name == null)
                productRepository.findAll().forEach(products::add);
            else
                productRepository.findBynameContaining(name).forEach(products::add);

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/upload" )
    public ResponseEntity<ResponseMessage> store(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("codeBarre") String codeBarre,
                                                 @RequestParam("price") double price,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("unite") String unite,
                                                 @RequestParam("qte") int qte,
                                                 @RequestParam("available") Boolean available,
                                                 @RequestParam("categoryId")  int categoryId,
                                                 @RequestParam("subCategoryId") int subCategoryId,
                                                 @RequestParam("marqueId") int marqueId) {
        String message = "";
        try {

            productService.store(file,name ,codeBarre, price, description, unite, qte,available,
                    categoryId, subCategoryId, marqueId);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> post(@PathVariable int id) {
        Optional<Product> prod = productService.findByproductId(id);
        return prod.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }




  /*  @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        Optional<SubCategory> optionalSubCategory = subcategoryRepo.findById(productDto.getSubCategoryId());
        if (!optionalSubCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "sub-category does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Marque> optionalMarque = marqueRepository.findById(productDto.getMarqueId());
        if (!optionalMarque.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Brand does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Promotion> optionalPromotion = promotionRepository.findById(productDto.getPromotionId());
        if (!optionalPromotion.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Promotion does not exists"),
                    HttpStatus.BAD_REQUEST);
        }

        productService.createProduct(productDto, optionalSubCategory.get(), optionalMarque.get(), optionalPromotion.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }
    */


    @GetMapping("/productCategory/{categoryName}")
    public List<Product> listProductByCategory(@PathVariable String categoryName) {
        return productService.listProductByCategory(categoryName);
    }
    @GetMapping("/productSubcategory/{subcategoryName}")
    public List<Product> listProductBySubcategory(@PathVariable String subcategoryName) {
        return productService.listProductBySubcategory(subcategoryName);
    }
    @GetMapping("/")
    public List<Product> listProduct() {
        return productService.listProduct();
    }
   /* public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }*/

    // create an api to edit the product
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updatePro(@PathVariable("id") int id,
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("codeBarre") String codeBarre,
                                                 @RequestParam("price") double price,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("unite") String unite,
                                                 @RequestParam("qte") int qte,
                                                 @RequestParam("available") Boolean available,
                                                 @RequestParam("categoryId") Category categoryId,
                                                 @RequestParam("subCategoryId") SubCategory subCategoryId,
                                                 @RequestParam("marqueId") Marque marqueId) throws IOException {
        System.out.println("id " + id);
        if (!productService.findById(id)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "product does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        productService.editProduct(file, id, name, codeBarre, price, description, unite, qte, available,
                categoryId, subCategoryId, marqueId );
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }
/*
    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId,
            @RequestBody ProductDto productDto) throws Exception {
        Optional<SubCategory> optionalSubCategory = subcategoryRepo.findById(productDto.getSubCategoryId());
        if (!optionalSubCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "sub-category does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Marque> optionalMarque = marqueRepository.findById(productDto.getMarqueId());
        if (!optionalMarque.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Brand does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Category> optionalPromotion = categoryRepo.findById(productDto.getCategoryId());
        if (!optionalPromotion.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }*/

    /* @DeleteMapping("/delete/{id}")
     public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") int id) {
         productRepository.deleteById(id);

         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Integer id) {

        //  productRepository.deleteById(id);
        productService.deleteProduct(id);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }

}
