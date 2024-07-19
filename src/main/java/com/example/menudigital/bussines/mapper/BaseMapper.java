package com.example.menudigital.bussines.mapper;



import com.example.menudigital.domain.dtos.BaseDto;
import com.example.menudigital.domain.entities.Base;

import java.util.List;
import java.util.Set;

public interface BaseMapper<E extends Base,D extends BaseDto>{
    public D toDTO(E source);
    public E toEntity(D source);
    public List<D> toDTOsList(List<E> source);
    public Set<D> toDTOsSet(Set<E> source);


}
