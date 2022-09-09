package work365.work.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.SubCategoryRepo;
import work365.work.model.Category;
import work365.work.model.SubCategory;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    @Autowired
    SubCategoryRepo subcategoryRepo;


 public SubCategory store(MultipartFile file, String subcategoryName,
                          String description,
                          int categoryId) throws IOException {
     SubCategory s = new SubCategory();
     Category c = new Category();
     c.setCategoryId(categoryId);
     //categoryId = c.setCategoryId(categoryId);
     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
     if(fileName.contains(".."))
     {
         System.out.println("not a a valid file");
     }
     try {
         s.setImageUrl(Base64.getEncoder().encodeToString(file.getBytes()));
     } catch (IOException e) {
         e.printStackTrace();
     }
     s.setSubcategoryName(subcategoryName);
     s.setDescription(description);
     s.setCategory(c);
     return  subcategoryRepo.save(s);

 }

    public void store(MultipartFile file){}
    public List<SubCategory> listSubCategory() {
        return subcategoryRepo.findAll();
    }

    public Optional<SubCategory> findBysouscatId(int subCategoryId) {
        return subcategoryRepo.findById(subCategoryId);
    }

    public List<SubCategory> listSubCategoryByCategory(String categoryName) {
        return subcategoryRepo.findByCategoryCategoryName(categoryName);
    }
    public List<SubCategory> listSubCategoryBySubcategory(String subcategoryName) {
        return subcategoryRepo.findBySubcategoryName(subcategoryName);
    }
    public void editSubCategory(MultipartFile file , int subcategoryId, String subcategoryName,
                                String description, Category  categoryId) throws IOException {
        SubCategory subcategory = subcategoryRepo.getById(subcategoryId);
        subcategory.setSubcategoryName(subcategoryName);
        subcategory.setDescription(description);
        subcategory.setImageUrl(Base64.getEncoder().encodeToString(file.getBytes()));
        subcategory.setCategory(categoryId);
        subcategoryRepo.save(subcategory);
    }

    public boolean findById(int subcategoryId) {
        return subcategoryRepo.findById(subcategoryId).isPresent();
    }
    
    public void deleteSubcategory(Integer subcategoryId) {

        Optional<SubCategory> optionalSubCategory = subcategoryRepo.findById(subcategoryId);

        optionalSubCategory.ifPresent(subcategoryRepo::delete);

    }

     /*  public void createSubCategory(SubCategory subcategory) {
        subcategoryRepo.save(subcategory);
    }
*/

      /*  public void editSubCategory(int subcategoryId, SubCategory updateSubCategory) {
        SubCategory subcategory = subcategoryRepo.getById(subcategoryId);
        subcategory.setSubcategoryName(updateSubCategory.getSubcategoryName());
        subcategory.setDescription(updateSubCategory.getDescription());
        subcategory.setImageUrl(updateSubCategory.getImageUrl());
        subcategory.setCategory(updateSubCategory.getCategory());
        subcategoryRepo.save(subcategory);
    }*/


    public int nbreSubCategory() {
        return subcategoryRepo.nbreSubCategory();
    }


}
