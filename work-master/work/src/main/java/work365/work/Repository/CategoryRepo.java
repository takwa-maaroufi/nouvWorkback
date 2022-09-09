package work365.work.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    List<Category> findBycategoryNameContaining(String categoryName);
  // List<Category> findById(int categoryId);

    @Query(value = "SELECT count(*)  FROM Category")
    public int nbreCategory ();
}
