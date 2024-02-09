package kz.insoft.usercrudapp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
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



}
