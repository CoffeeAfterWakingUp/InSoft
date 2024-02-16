package kz.insoft.usercrudapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @JsonProperty(value = "id", required = false)
    private Long id;

    @JsonProperty(value = "first_name", required = true)
    private String firstName;

    @JsonProperty(value = "last_name", required = true)
    private String lastName;

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty(value = "address", required = true)
    private String address;

    @JsonProperty(value = "birth_date", required = true)
    private LocalDate birthDate;

    @JsonProperty(value = "details", required = true)
    private UserDetailsDTO details;

    @JsonProperty(value = "phones", required = false)
    private List<UserPhoneDTO> phones;

    @JsonProperty(value = "departments", required = true)
    private List<DepartmentDTO> departments;

}
