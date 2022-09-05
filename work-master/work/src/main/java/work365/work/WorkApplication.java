package work365.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import work365.work.dto.UserDTO;
import work365.work.model.Category;
import work365.work.model.Product;
import work365.work.model.User;

import java.util.Date;
@SpringBootApplication
public class WorkApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(WorkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
