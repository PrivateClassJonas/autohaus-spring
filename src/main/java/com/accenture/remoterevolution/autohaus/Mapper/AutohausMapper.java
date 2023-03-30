package com.accenture.remoterevolution.autohaus.Mapper;

import com.accenture.remoterevolution.autohaus.Entities.Autohaus;
import com.accenture.remoterevolution.autohaus.DTOs.AutohausDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutohausMapper {
    List<AutohausDto> autohausToAutohausDtos(List<Autohaus> autohaus);

    @Mapping(target = "ahAdresse", source = "adresse")
    Autohaus autohausDtoToAutohaus(AutohausDto dto);

    @Mapping(target = "adresse", source = "ahAdresse")
    AutohausDto autohausToAutohausDto(Autohaus autohaus);


}
