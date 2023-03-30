package com.accenture.remoterevolution.autohaus;

import com.accenture.remoterevolution.autohaus.DTOs.AutohausDto;
import com.accenture.remoterevolution.autohaus.Entities.Autohaus;
import com.accenture.remoterevolution.autohaus.Mapper.AutohausMapper;
import com.accenture.remoterevolution.autohaus.Repos.AutohausRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AutohausApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AutohausRepository autohausRepository;

    @Autowired
    private AutohausMapper autohausMapper;

    @BeforeEach
    void databaseLoads() {
        autohausRepository.deleteAll();
        autohausRepository.save(createAutohaus("12345", "Junitstrasse 1"));
        autohausRepository.save(createAutohaus("1234", "Junitstrasse 2"));
        autohausRepository.save(createAutohaus("123", "Junitstrasse 3"));
        autohausRepository.save(createAutohaus("123456", "Junitstrasse 4"));
        autohausRepository.save(createAutohaus("12346", "Junitstrasse 5"));
    }

    @Test
    public void showAutohausByIdMock() throws Exception {
        Autohaus myAutohaus = createAutohaus("1", "Junitjupitergasse 1");
        autohausRepository.save(myAutohaus);
        MvcResult mvcResult = null;
        mvcResult = mvc.perform(get("/autohaus/" + myAutohaus.getGuid()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        AutohausDto expected = autohausMapper.autohausToAutohausDto(myAutohaus);
        String content = mvcResult.getResponse().getContentAsString();
        AutohausDto actual = new ObjectMapper().readValue(content, AutohausDto.class);

        assertEquals(expected.getGuid(), actual.getGuid());
        assertEquals(expected.getAdresse(), actual.getAdresse());

    }

    @Test
    public void showAutohauseMock() throws Exception {
        Autohaus myAutohaus = createAutohaus("1", "Junitjupitergasse 1");
        autohausRepository.save(myAutohaus);
        MvcResult mvcResult = null;
        mvcResult = mvc.perform(get("/autohaus/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        List<AutohausDto> autohausDtoList = new ObjectMapper().readValue(content, new TypeReference<List<AutohausDto>>() {
        });
        String x = autohausDtoList.get(1).getGuid();
        assertEquals("12345", autohausDtoList.get(0).getGuid());
        assertEquals("1234", autohausDtoList.get(1).getGuid());
        assertEquals("123", autohausDtoList.get(2).getGuid());
        assertEquals("123456", autohausDtoList.get(3).getGuid());
        assertEquals("12346", autohausDtoList.get(4).getGuid());
        assertEquals("1", autohausDtoList.get(5).getGuid());
    }

    @Test
    public void postAutohausMock() throws Exception {
        Autohaus myAutohaus = createAutohaus("", "ThisWasJustPostet 1");
        AutohausDto dto = autohausMapper.autohausToAutohausDto(myAutohaus);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        MvcResult result = mvc.perform(post("/autohaus/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        AutohausDto actual = objectMapper.readValue(result.getResponse().getContentAsString(), AutohausDto.class);
        int status = result.getResponse().getStatus();
        assertEquals("ThisWasJustPostet 1", actual.getAdresse());
        assertEquals(200, status);
    }

    private Autohaus createAutohaus(String guid, String adresse) {
        Autohaus autohaus = new Autohaus();
        autohaus.setAhAdresse(adresse);
        autohaus.setGuid(guid);
        return autohaus;
    }
}
