package work365.work.service;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work365.work.dto.RoleDto;
import work365.work.dto.UserDTO;
import work365.work.model.Role;
import work365.work.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService  {

   @Autowired
   private  UserDTO userDTO;
   @Autowired
   private RoleDto roleDto;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private JavaMailSender javaMailSender;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        roleDto.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("USER");
        roleDto.save(userRole);

        User adminUser = new User();
        adminUser.setEmail("admin.1@gmail.com");
        adminUser.setPassword(getEncodedPassword("admin@pass"));
        adminUser.setFirstName("admin");
        adminUser.setLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDTO.save(adminUser);

       User user = new User();
       user.setEmail("salwa.1@gmail.com");
        user.setPassword(getEncodedPassword("salwa@pass"));
        user.setFirstName("salwa");
        user.setLastName("hsin");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
    userDTO.save(user);
    }

   /* public User registerNewUser(User user  , String siteURL)
            throws UnsupportedEncodingException, MessagingException {

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        userDTO.save(user);
        Role role = roleDto.findById("USER").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

         userDTO.save(user);
        sendVerificationEmail(user,siteURL);

        return user;
    }
*/


   public User registerNewUser(MultipartFile file,String email, String password, String firstName, String lastName, String profession, String ville,String gouvernorat,String adress,String tel , String situationFamilliale,Date date, String siteURL) throws IOException, MessagingException {
       User user = new User();
       String fileName = StringUtils.cleanPath(file.getOriginalFilename());
       if(fileName.contains(".."))
       {
           System.out.println("not a a valid file");
       }
       try {
           user.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
           //    c.setImage(Base64.getEncoder().encode(file.getBytes()));
       } catch (IOException e) {
           e.printStackTrace();
       }
       String randomCode = RandomString.make(64);
       user.setVerificationCode(randomCode);
       user.setEnabled(false);
       user.setPassword(password);
       user.setEmail(email);
       user.setFirstName(firstName);
       user.setLastName(lastName);
       user.setAdress(adress);
       user.setDate(date);
       user.setGouvernorat(gouvernorat);
       user.setVille(ville);
       user.setSituationFamilliale(situationFamilliale);
       user.setProfession(profession);
       user.setTel(tel);



       Role role = roleDto.findById("USER").get();
       Set<Role> userRoles = new HashSet<>();
       userRoles.add(role);
       user.setRole(userRoles);
       user.setPassword(getEncodedPassword(user.getPassword()));

       userDTO.save(user);
       sendVerificationEmail(user,siteURL);
    return  userDTO.save(user);
   }
    public User getUser(String email){
      return userDTO.getById(email);
    }





    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
   public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {

        String subject = "Please verify your registration";
        String senderName = "365 Team";
        String mailContent = " <p>Dear " + user.getFirstName() + user.getLastName() + ",</p>";
       mailContent += "<p> Please Click the link bellow to verify: </p> ";

       String VerifyURL = siteURL + "/verify?verificationCode=" + user.getVerificationCode();

       mailContent += "<h3><a href=\"" + VerifyURL + "\">VERIFY</a></h3>";

       mailContent += "<p> Thank you <br> the 365 Team </p>";


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("365tunisien@gmail.com",senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent,true);
        javaMailSender.send(message);


    }



    public boolean verify(String verificationCode) {
        User user = userDTO.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userDTO.save(user);

            return true;
        }

    }

    }

