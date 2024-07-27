package com.example.menudigital.bussines.mapper;


import com.example.menudigital.bussines.services.EmpresaService;
import com.example.menudigital.bussines.services.SucursalService;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalCreateDto;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalDto;
import com.example.menudigital.domain.dtos.sucursalDto.SucursalShortDto;
import com.example.menudigital.domain.entities.Sucursal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DomicilioMapper.class, EmpresaMapper.class, SucursalService.class, EmpresaService.class})
public interface SucursalMapper extends BaseMapper<Sucursal, SucursalDto>{

    public SucursalShortDto toShortDTO(Sucursal source);

    @Mapping(target = "empresa",source = "idEmpresa",qualifiedByName = "getById")
    public Sucursal toEntityCreate(SucursalCreateDto source);


}
