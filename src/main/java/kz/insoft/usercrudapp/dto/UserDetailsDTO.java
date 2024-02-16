package kz.insoft.usercrudapp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

    @JsonProperty(value = "created_time", required = false)
    private LocalDateTime createdTime;

    @JsonProperty(value = "updated_time", required = false)
    private LocalDateTime updatedTime;

    @JsonProperty(value = "active", required = true)
    private boolean isActive;
}
