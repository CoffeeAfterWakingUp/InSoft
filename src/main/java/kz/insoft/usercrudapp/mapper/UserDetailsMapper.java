package kz.insoft.usercrudapp.mapper;

import kz.insoft.usercrudapp.dto.UserDetailsDTO;
import kz.insoft.usercrudapp.entity.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper implements Mapper<UserDetailsDTO, UserDetails> {

    public UserDetailsDTO toDto(UserDetails userDetails) {
        if (userDetails == null) return null;
        return UserDetailsDTO.builder()
                .createdTime(userDetails.getCreatedTime())
                .isActive(userDetails.isActive())
                .updatedTime(userDetails.getUpdatedTime())
                .build();
    }
}
