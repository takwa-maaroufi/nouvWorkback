package work365.work.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.SubCategory;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Integer> {

    List<SubCategory> findBysubcategoryNameContaining(String subcategoryName);
    List<SubCategory> findByCategoryCategoryName(String categoryName);
    List<SubCategory> findBySubcategoryName(String subcategoryName);

}
