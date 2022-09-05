package work365.work.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date date;
    private String profession;
    private String ville;
    private String gouvernorat;
    private String adress;
    private String nomenfant;
    private  String tel;
    private String situationFamilliale;
    private Date date1;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;
    private  boolean enabled;
    private  String verificationCode;
    @Lob
    @Column(name = "User_image")
    private String image;


}



