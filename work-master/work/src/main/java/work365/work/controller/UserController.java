package work365.work.controller;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work365.work.message.ResponseMessage;
import work365.work.model.User;
import work365.work.service.JwtService;
import work365.work.service.UserService;
import work365.work.service.Utility;
import work365.work.utile.JwtUtil;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import static work365.work.service.Utility.getSiteURL;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    /*
        @PostMapping({"/registerNewUser"})
        public User registerNewUser(@RequestBody User user, HttpServletRequest request,@RequestParam("file") MultipartFile file ) throws MessagingException, UnsupportedEncodingException {
            String randomCode = RandomString.make(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            return userService.registerNewUser(user,getSiteURL(request));
        }*/
    @PostMapping("/registerNewUser")
    public ResponseEntity<ResponseMessage> registerNewUser(@RequestParam("file") MultipartFile file,
                                                           @RequestParam("email") String email,
                                                           @RequestParam("password") String password,
                                                           @RequestParam("firstName") String firstName,
                                                           @RequestParam("lastName") String lastName,
                                                           @RequestParam("profession") String profession,
                                                           @RequestParam("ville") String ville,
                                                           @RequestParam("gouvernorat") String gouvernorat,
                                                           @RequestParam("adress") String adress,
                                                           @RequestParam("tel") String tel,
                                                           @RequestParam("situationFamilliale") String situationFamilliale,
                                                           Date date, HttpServletRequest request

    ) {
        User user = new User();
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        String message = "";

        try {


            userService.registerNewUser(file, email, password, firstName, lastName, profession, ville, gouvernorat, adress, tel, situationFamilliale, date, getSiteURL(request));


            message = "Uploaded the file successfully: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('USER')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("verificationCode") String verificationCode) {
        if (userService.verify(verificationCode)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }



}
