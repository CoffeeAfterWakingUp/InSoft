package kz.insoft.usercrudapp.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<DTO, ENTITY> {

    DTO toDto(ENTITY e);

    ENTITY toEntity(DTO dto);

    default List<DTO> toDtoList(Collection<ENTITY> eList) {
        if (eList == null) return new ArrayList<>();
        return eList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
