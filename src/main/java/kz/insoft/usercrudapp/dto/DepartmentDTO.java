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
public class DepartmentDTO {

    @JsonProperty(value = "id", required = false)
    private Long id;

    @JsonProperty(value = "name", required = true)
    private String name;
}