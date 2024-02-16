package kz.insoft.usercrudapp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@EqualsAndHashCode(exclude = {"userDetails", "phoneList", "departments"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "First Name has not to be blank!")
    @Column(name = "FIRST_NAME", nullable = false, length = 255)
    private String firstName;

    @NotBlank(message = "Last Name has not to be blank!")
    @Column(name = "LAST_NAME", nullable = false, length = 255)
    private String lastName;

    @NotBlank(message = "Email has not to be blank!")
    @Email(message = "Email has to in the right format!")
    @Column(name = "EMAIL", nullable = false, unique = true, length = 255)
    private String email;

    @NotBlank(message = "Address has not to be blank!")
    @Column(name = "ADDRESS", nullable = false, length = 255)
    private String address;

    @NotNull(message = "Birth Date has not to be null!")
    @PastOrPresent(message = "Birth Date has to be correct!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Column(name = "TEXT")
    private String text;


    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL)
    private UserDetails userDetails;


    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<UserPhone> phoneList = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Department> departments = new HashSet<>();



    public void setUserDetails(UserDetails userDetails) {
        if (userDetails != null) {
            userDetails.setUser(this);
        } else {
            if (this.userDetails != null) {
                this.userDetails.setUser(null);
            }
        }
        this.userDetails = userDetails;
    }

    public void addPhone(UserPhone userPhone) {
        phoneList.add(userPhone);
        userPhone.setUser(this);
    }

    public void removePhone(UserPhone userPhone) {
        phoneList.remove(userPhone);
        userPhone.setUser(null);
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", address='" + address + '\'' +
//                ", birthDate=" + birthDate +
//                ", text='" + text + '\'' +
//                '}';
//    }
}
