package work365.work.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import work365.work.model.Product;
import work365.work.model.User;

import java.util.Optional;
@Repository
public interface UserDTO  extends JpaRepository<User,String> {

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);

    User findByEmail(String email);


}
