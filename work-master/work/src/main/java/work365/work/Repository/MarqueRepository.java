package work365.work.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.Marque;

import java.util.List;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, Integer> {

    List<Marque> findBynomMarqueContaining(String nomMarque);

    @Query(value = "SELECT count(*)  FROM Marque")
    public int nbreMarque ();
}
