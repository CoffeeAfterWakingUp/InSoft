package kz.insoft.usercrudapp.mapper;

import kz.insoft.usercrudapp.dto.UserDTO;
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

    @Override
    public UserDetails toEntity(UserDetailsDTO userDetailsDTO) {
        if (userDetailsDTO == null) return null;
        return UserDetails.builder()
                .updatedTime(userDetailsDTO.getUpdatedTime())
                .createdTime(userDetailsDTO.getCreatedTime())
                .isActive(userDetailsDTO.isActive())
                .build();
    }
}
