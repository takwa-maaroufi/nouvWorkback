package work365.work.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.Product;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBynameContaining(String name);
    List <Product> findByCategoryCategoryName(String categoryName);
List<Product> findBySubcategorySubcategoryName( String subcategoryName );
    List<Product> findAll();
    List<Product> findByCheckoutCartId(long id);

    Product findByName(String name);
Product findByCodeBarre(String codeBarre);

    @Query(value = "SELECT count(*)  FROM Product")
    public int nbreProd ();
}


