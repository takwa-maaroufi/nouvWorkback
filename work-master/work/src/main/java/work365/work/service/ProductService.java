package work365.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.CheckoutRepo;
import work365.work.Repository.ProductRepository;
import work365.work.dto.ProductDto;
import work365.work.model.Category;
import work365.work.model.Marque;
import work365.work.model.Product;
import work365.work.model.SubCategory;

import java.io.IOException;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CheckoutRepo checkoutRepo;


    public Product store(MultipartFile file, String name, String codeBarre, double price,
                         String description, String unite, int qte, Boolean available
            , int categoryId, int subCategoryId, int marqueId) throws IOException {
        Product p = new Product();
        //  ProductDto productDto = new ProductDto();
        Marque m = new Marque();
        m.setMarqueId(marqueId);
        SubCategory s = new SubCategory();
        s.setSubCategoryId(subCategoryId);
        Category c = new Category();
        c.setCategoryId(categoryId);
        //  Promotion pr = new Promotion();
        // pr.setPromotionId(promotionId);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            p.setImageURL(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setName(name);
        p.setDescription(description);
        p.setCodeBarre(codeBarre);
        p.setAvailable(available);
        p.setUnite(unite);
        p.setQte(qte);
        p.setPrice(price);
        p.setMarque(m);
        //  p.setPromotion(pr);
        p.setSubCategory(s);
        p.setCategory(c);

        return productRepository.save(p);

    }

    /*   public void createProduct(ProductDto productDto, SubCategory subcategory, Marque marque,
                                 Promotion promotion) {
           Product product = new Product();
           product.setDescription(productDto.getDescription());
           product.setImageURL(productDto.getImageURL());
           product.setName(productDto.getName());
           product.setSubCategory(subcategory);
           product.setMarque(marque);
           product.setPromotion(promotion);
           product.setPrice(productDto.getPrice());
           product.setAvailable(productDto.getAvailable());
           product.setCodeBarre(productDto.getCodeBarre());
           product.setUnite(productDto.getUnite());
           product.setQte(productDto.getQte());
           productRepository.save(product);
       }
   */
    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setId(product.getSubCategory().getSubCategoryId());
        productDto.setId(product.getCategory().getCategoryId());
        productDto.setId(product.getMarque().getMarqueId());
        //   product.setId(product.getPromotion().getPromotionId());
        productDto.setPrice(product.getPrice());
        productDto.setAvailable(product.getAvailable());
        productDto.setQte(product.getQte());
        productDto.setUnite(product.getUnite());
        productDto.setCodeBarre(product.getCodeBarre());

        productDto.setId(product.getId());
        return productDto;
    }

    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    public List<Product> listProductByCategory(String categoryName) {
        return productRepository.findByCategoryCategoryName(categoryName);
    }
    public List<Product> listProductByCheckoutCart(long id) {
        return productRepository.findByCheckoutCartId(id);
    }

    public List<Product> listProductBySubcategory(String subcategoryName) {

        return productRepository.findBySubcategorySubcategoryName(subcategoryName);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : allProducts) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public Optional<Product> findByproductId(long id) {
        return productRepository.findById(id);
    }


    public void updateProduct(ProductDto productDto, long productId) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        // throw an exception if product does not exists
        if (!optionalProduct.isPresent()) {
            throw new Exception("product not present");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQte(productDto.getQte());
        product.setUnite(productDto.getUnite());
        product.setAvailable(productDto.getAvailable());
        product.setCodeBarre(productDto.getCodeBarre());

        productRepository.save(product);
    }

   /* public Product findById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }*/


    public boolean findById(long id) {
        return productRepository.findById(id).isPresent();
    }

    public void deleteProduct(long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        optionalProduct.ifPresent(productRepository::delete);

    }
    public Product getProductsById(long productId) throws Exception {
        return productRepository.findById(productId).orElseThrow(() ->new Exception("Product is not found"));
    }

    public void editProduct(MultipartFile file, long id, String name, String codeBarre, double price,
                            String description, String unite, int qte, Boolean available,
                            Category categoryId, SubCategory subCategoryId, Marque marqueId) throws IOException {
        Product product = productRepository.getById(id);
        product.setName(name);
        product.setCodeBarre(codeBarre);
        product.setPrice(price);
        product.setDescription(description);
        product.setUnite(unite);
        product.setQte(qte);
        product.setAvailable(available);
        product.setImageURL(Base64.getEncoder().encodeToString(file.getBytes()));
        product.setCategory(categoryId);
        product.setSubCategory(subCategoryId);
        product.setMarque(marqueId);

        productRepository.save(product);
    }



   /* public Product findById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }*/

    public int nbreProd() {
        return productRepository.nbreProd();
    }



}

