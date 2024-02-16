package kz.insoft.usercrudapp.mapper;

import kz.insoft.usercrudapp.dto.UserPhoneDTO;
import kz.insoft.usercrudapp.entity.UserPhone;
import org.springframework.stereotype.Component;

@Component
public class UserPhoneMapper implements Mapper<UserPhoneDTO, UserPhone> {

    public UserPhoneDTO toDto(UserPhone userPhone) {
        if (userPhone == null) return null;
        return UserPhoneDTO.builder()
                .phoneNumber(userPhone.getPhoneNumber())
                .type(userPhone.getType())
                .build();
    }
}
