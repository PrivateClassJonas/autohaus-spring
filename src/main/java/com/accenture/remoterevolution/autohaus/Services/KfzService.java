package com.accenture.remoterevolution.autohaus.Services;

import com.accenture.remoterevolution.autohaus.Entities.Autohaus;
import com.accenture.remoterevolution.autohaus.Entities.Kfz;
import com.accenture.remoterevolution.autohaus.Mapper.KfzMapper;
import com.accenture.remoterevolution.autohaus.Repos.AutohausRepository;
import com.accenture.remoterevolution.autohaus.Repos.KfzRepository;
import com.accenture.remoterevolution.autohaus.DTOs.KfzDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KfzService {
    @Autowired
    private KfzMapper kfzMapper;
    //@Autowired
    private KfzRepository kfzRepository;
    private AutohausRepository autohausRepository;

    public KfzService(KfzRepository kfzRepository, AutohausRepository autohausRepository) {
        this.kfzRepository = kfzRepository;
        this.autohausRepository = autohausRepository;
    }

    public Optional<List<KfzDto>> showKfzs() {
        List<Kfz> kfzList = kfzRepository.findAll();
        if (kfzList.isEmpty()) {
            return Optional.empty();
        }
        List<KfzDto> kfzDtoList = kfzMapper.kfzsToKfzDtos(kfzList);
        Optional<List<KfzDto>> result = Optional.ofNullable(kfzDtoList);
        return result;
    }

    public Optional<KfzDto> addNewKfz(KfzDto kfzDto) {
        if (kfzDto == null) {
            return Optional.empty();
        }
        Kfz kfz = kfzMapper.kfzDtoToKfz(kfzDto);
        String guid = UUID.randomUUID().toString();
        kfz.setGuid(guid);
        Optional<Autohaus> ah = autohausRepository.findByGuid(kfzDto.getAutohausGuid());
        if (ah.isEmpty()) {
            return Optional.empty();
        }
        Autohaus ah1 = ah.get();
        kfz.setAutohaus(ah1);
        Kfz savedKfz = kfzRepository.save(kfz);
        KfzDto result = kfzMapper.kfzToKfzDto(savedKfz);
        return Optional.ofNullable(result);
    }

    public Optional<KfzDto> deleteKfz(String autohausGUID) {
        Optional<Kfz> optionalKfz = kfzRepository.findByGuid(autohausGUID);
        if (optionalKfz.isEmpty()) {
            return Optional.empty();
        }
        Kfz deleteKfz = optionalKfz.get();
        KfzDto result = kfzMapper.kfzToKfzDto(deleteKfz);
        kfzRepository.delete(deleteKfz);
        return Optional.ofNullable(result);
    }

    public KfzDto updateKfz(String autohausGUID) {
        return null;
    }

}
