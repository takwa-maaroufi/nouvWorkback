package work365.work.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  //  List<User> findAll();

 //   User findByFirstName(String firstName);

  //  User findByEmail(String email);

    @Query(value = "SELECT count(*)  FROM User")
    public int nbreClient();
}