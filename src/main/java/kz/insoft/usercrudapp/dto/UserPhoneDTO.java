package kz.insoft.usercrudapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPhoneDTO {

    @JsonProperty(value = "phoneNumber", required = false)
    private String phoneNumber;

    @JsonProperty(value = "type", required = false)
    private String type;
}
