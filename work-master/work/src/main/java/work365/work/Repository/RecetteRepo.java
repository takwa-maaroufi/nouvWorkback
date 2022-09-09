package work365.work.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.Recette;

import java.util.List;

@Repository
public interface RecetteRepo extends JpaRepository<Recette, Integer> {

    List<Recette> findBynomRecetteContaining(String nomRecette);


    @Query(value = "SELECT count(*)  FROM Recette")
    public int nbreRecette ();
}