package com.accenture.remoterevolution.autohaus.Mapper;

import com.accenture.remoterevolution.autohaus.Entities.Kfz;
import com.accenture.remoterevolution.autohaus.DTOs.KfzDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AutohausMapper.class})
public interface KfzMapper {
    List<KfzDto> kfzsToKfzDtos(List<Kfz> kfz);

    Kfz kfzDtoToKfz(KfzDto dto);

    @Mapping(target = "autohausGuid", source = "autohaus.guid")
    @Mapping(target = "autohausAdresse", source = "autohaus.ahAdresse")
    KfzDto kfzToKfzDto(Kfz kfz);

}
