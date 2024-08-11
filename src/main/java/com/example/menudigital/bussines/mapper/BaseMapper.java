package com.example.menudigital.bussines.mapper;



import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.entities.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface BaseMapper<E extends Base,D extends BaseDto>{
    public D toDTO(E source);
    public E toEntity(D source);
    public List<D> toDTOsList(List<E> source);
    public Set<D> toDTOsSet(Set<E> source);
    default Page<D> toDTOsPage(Page<E> source) {
        List<D> dtoList = source.getContent().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, source.getPageable(), source.getTotalElements());
    }


}
