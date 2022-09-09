package work365.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.CategoryRepo;
import work365.work.model.Category;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;


  /* public void save(MultipartFile file , Category category) throws IOException {
        category.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        categoryRepo.save(category).getCategoryId();
    }*/

    public Category store(MultipartFile file, String categoryName, String description) throws IOException {
        Category c = new Category();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            c.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        //    c.setImage(Base64.getEncoder().encode(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        c.setCategoryName(categoryName);
        c.setDescription(description);

        return  categoryRepo.save(c);

    }
    /*


    public Category store(MultipartFile file ) throws IOException {
        Category c = new Category();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            c.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

            //    c.setImage(Base64.getEncoder().encode(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        c.setCategoryName(c.getCategoryName());
       c.setDescription(c.getDescription());
    //    c.setCategoryName(categoryName);
      //  c.setDescription(description);

        return  categoryRepo.save(c);

    }
 */

    public List<Category> listCategory() {
        return categoryRepo.findAll();
    }

    public Optional<Category> findBycategId(int categoryId) {
        return categoryRepo.findById(categoryId);
    }
    public void editCategory(MultipartFile file ,int categoryId, String categoryName,
                             String description) throws IOException{
        Category category = categoryRepo.getById(categoryId);

    //   Category  c = new Category();
        category.setCategoryName(categoryName);
        category.setDescription(description);
        category.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        categoryRepo.save(category);
    }


    public boolean  findById(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }

    public void deleteCategory(Integer categoryId) {

        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);

        optionalCategory.ifPresent(categoryRepo::delete);

    }


    public Category getFile(int categoryId) {
        return categoryRepo.findById(categoryId).get();
    }


  public void createCategory(Category category)  {

      categoryRepo.save(category);
    }

    /*    public void editCategory(int categoryId, Category updateCategory) {
        Category category = categoryRepo.getById(categoryId);
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImage(updateCategory.getImage());
        categoryRepo.save(category);
    }*/

    public int nbreCategory() {
        return categoryRepo.nbreCategory();
    }
}
