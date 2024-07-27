package com.example.menudigital.presentation.rest.Base;


import com.example.menudigital.domain.dtos.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseController <D extends BaseDto, ID extends Serializable> {
    ResponseEntity<D> getById(ID id);

    ResponseEntity<List<D>> getAll();
    ResponseEntity<Page<D>> getAllPaged(Pageable pageable);

    ResponseEntity<D> create(D entity);

    ResponseEntity<D> edit(D entity, ID id);

    ResponseEntity<?> deleteById(ID id);
}