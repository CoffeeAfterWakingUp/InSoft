package kz.insoft.usercrudapp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    @NotBlank(message = "First Name has not to be blank!")
    private String firstName;

    @NotBlank(message = "Last Name has not to be blank!")
    private String lastName;

    @NotBlank(message = "Email has not to be blank!")
    @Email(message = "Email has to in the right format!")
    private String email;

    @NotBlank(message = "Address has not to be blank!")
    private String address;

    @NotNull(message = "Birth Date has not to be null!")
    @PastOrPresent(message = "Birth Date has to be correct!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;



}
