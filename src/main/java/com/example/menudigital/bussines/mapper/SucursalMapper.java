package com.example.menudigital.bussines.mapper;


import com.example.menudigital.bussines.services.SucursalService;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalDto;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalShortDto;
import com.example.menudigital.domain.entities.Sucursal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DomicilioMapper.class, EmpresaMapper.class, SucursalService.class})
public interface SucursalMapper extends BaseMapper<Sucursal, SucursalDto>{

    public SucursalShortDto toShortDTO(Sucursal source);

    public Sucursal shortToEntity(SucursalShortDto source);
}
