package com.accenture.remoterevolution.autohaus.Services;

import com.accenture.remoterevolution.autohaus.Entities.Autohaus;
import com.accenture.remoterevolution.autohaus.DTOs.AutohausDto;
import com.accenture.remoterevolution.autohaus.Mapper.AutohausMapper;
import com.accenture.remoterevolution.autohaus.Repos.AutohausRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AutohausService {

    @Autowired
    private AutohausMapper autohausMapper;
    private AutohausRepository autohausRepository;

    public AutohausService(AutohausRepository autohausRepository) {
        this.autohausRepository = autohausRepository;
    }


    public Optional<List<AutohausDto>> showAutohaus() {
        List<Autohaus> autohausList = autohausRepository.findAll();
        if (autohausList.isEmpty()) {
            return Optional.empty();
        }
        List<AutohausDto> autohausDtoList = autohausMapper.autohausToAutohausDtos(autohausList);
        if (autohausDtoList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(autohausDtoList);
    }


    public Optional<AutohausDto> showAutohausById(String autohausGUID) {
        if (autohausGUID == null || autohausGUID.length() == 0) {
            return Optional.empty();
        }
        Optional<Autohaus> optionalAutohaus = autohausRepository.findByGuid(autohausGUID);
        if (optionalAutohaus.isEmpty()) {
            return Optional.empty();
        }
        Autohaus autohaus = optionalAutohaus.get();
        AutohausDto autohausDto = autohausMapper.autohausToAutohausDto(autohaus);
        return Optional.ofNullable(autohausDto);
    }


    public Optional<AutohausDto> addNewAutohaus(AutohausDto autohausDto) {
        if (autohausDto == null) {
            return Optional.empty();
        }
        Autohaus autohaus = autohausMapper.autohausDtoToAutohaus(autohausDto);
        String guid = UUID.randomUUID().toString();
        autohaus.setGuid(guid);
        Autohaus savedAutohaus = autohausRepository.save(autohaus);
        if (savedAutohaus == null) {
            return Optional.empty();
        }
        AutohausDto result = autohausMapper.autohausToAutohausDto(savedAutohaus);
        return Optional.ofNullable(result);
    }

    public Optional<List<AutohausDto>> addNewAutohause(AutohausDto[] autohausDtos) {
        if (autohausDtos == null || autohausDtos.length == 0) {
            return Optional.empty();
        }
        List<Autohaus> autohause = new ArrayList<Autohaus>(autohausDtos.length);
        Autohaus save = null;
        for (AutohausDto dto : autohausDtos) {
            String guid = UUID.randomUUID().toString();
            dto.setGuid(guid);
            save = autohausMapper.autohausDtoToAutohaus(dto);
            autohausRepository.save(save);
            autohause.add(save);
        }
        if (autohause.isEmpty() || autohause.size() == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(autohausMapper.autohausToAutohausDtos(autohause));
    }


    public Optional<AutohausDto> deleteAutohausById(String autohausGUID) {
        if (autohausGUID == null || autohausGUID.length() == 0) {
            return Optional.empty();
        }
        Optional<Autohaus> optionalAutohaus = autohausRepository.findByGuid(autohausGUID);
        if (optionalAutohaus.isEmpty()) {
            return Optional.empty();
        }
        Autohaus deleteAutohaus = optionalAutohaus.get();
        autohausRepository.delete(deleteAutohaus);
        AutohausDto result = autohausMapper.autohausToAutohausDto(deleteAutohaus);
        return Optional.ofNullable(result);
    }


    public Optional<List<AutohausDto>> deleteAutohausWithNull() {
        List<Autohaus> autohausList = new LinkedList<>();
        for (Autohaus autohaus : autohausRepository.findAll()) {
            if (autohaus.getGuid() == null) {
                autohausList.add(autohaus);
                autohausRepository.delete(autohaus);
            }
        }
        List<AutohausDto> autohausDtoList = autohausMapper.autohausToAutohausDtos(autohausList);
        return Optional.ofNullable(autohausDtoList);
    }


    public Optional<AutohausDto> updateAutohausByID(String autohausGUID, AutohausDto autohausDto) {
        if ((autohausGUID == null || autohausGUID.length() == 0) || autohausDto == null) {
            return Optional.empty();
        }
        Optional<Autohaus> optionalAutohaus = autohausRepository.findByGuid(autohausGUID);
        if (optionalAutohaus.isEmpty()) {
            return Optional.empty();
        }
        Autohaus updateAutohaus = optionalAutohaus.get();
        updateAutohaus.setAhAdresse(autohausDto.getAdresse());
        autohausRepository.save(updateAutohaus);
        return Optional.ofNullable(autohausMapper.autohausToAutohausDto(updateAutohaus));
    }
}
