package com.example.kkproject.service;

import com.example.kkproject.controller.URepository;
import com.example.kkproject.model.UDomain;
import com.example.kkproject.model.UDto;
import com.google.common.hash.Hashing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UService {

    @Autowired
    URepository uRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<UDto> getAll(){
        return mapList(uRepository.findAll(), UDto.class);
    }

    public String search(String shortedUrl ){
        return  uRepository.getOriginalLink(shortedUrl);
    }


    public void insertDataToTable(String url) {
        UDto uDto = new UDto();
        uDto.setOriginalUrl(url);
        uDto.setCreated(LocalDateTime.now());
        uDto.setShortedUrl(shortingUrl(url));
        UDomain uDomain1 = modelMapper.map(uDto, UDomain.class);
        uRepository.saveAndFlush(uDomain1);
    }

    private String shortingUrl(String url){
        return Hashing.murmur3_32_fixed().hashString(url.concat(LocalDateTime.now().toString()), StandardCharsets.UTF_8).toString();
    }


    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {

        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
