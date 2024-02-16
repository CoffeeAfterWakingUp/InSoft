package kz.insoft.usercrudapp.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<D, E> {

    D toDto(E e);

    default List<D> toDtoList(Collection<E> eList) {
        return eList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
