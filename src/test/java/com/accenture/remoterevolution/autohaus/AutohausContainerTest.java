package com.accenture.remoterevolution.autohaus;


import com.accenture.remoterevolution.autohaus.DTOs.AutohausDto;
import com.accenture.remoterevolution.autohaus.Services.AutohausService;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AutohausContainerTest {

    @Autowired
    private AutohausService autohausService;
    @Container
    public static MySQLContainer database = (MySQLContainer) new MySQLContainer("mysql:latest")
            .withDatabaseName("autohausdb_test");



    @BeforeAll
    public static void setUpDockerContainer(){
        database.withReuse(true);
        database.withInitScript("src/test/resources/init.sql");
        database.start();

    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
        registry.add("spring.datasource.driver-class-name", database::getDriverClassName);
    }

    @Test
    public void testCreatingAutohausAndShowItAfter(){
        AutohausDto autohausDto = new AutohausDto();
        autohausDto.setAdresse("Teststrasse 1");

        String addedAutohaus = ((autohausService.addNewAutohaus(autohausDto)).get()).getGuid();

        AutohausDto showAutohaus = (autohausService.showAutohausById(addedAutohaus)).get();


    }

    @AfterAll
    public static void stopDockerContainer(){
        database.stop();
    }

}
